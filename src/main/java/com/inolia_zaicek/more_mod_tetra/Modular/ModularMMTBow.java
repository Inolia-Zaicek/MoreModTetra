package com.inolia_zaicek.more_mod_tetra.Modular;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import se.mickelus.mutil.network.PacketHandler;
import se.mickelus.mutil.util.CastOptional;
import se.mickelus.tetra.TetraMod;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.effect.FocusEffect;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.event.ModularLooseProjectilesEvent;
import se.mickelus.tetra.event.ModularProjectileSpawnEvent;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.bow.ProjectileMotionPacket;
import se.mickelus.tetra.module.ItemModule;
import se.mickelus.tetra.module.ItemUpgradeRegistry;
import se.mickelus.tetra.module.SchematicRegistry;
import se.mickelus.tetra.module.data.*;
import se.mickelus.tetra.module.schematic.RepairSchematic;
import se.mickelus.tetra.properties.AttributeHelper;
import se.mickelus.tetra.properties.TetraAttributes;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static se.mickelus.tetra.items.modular.impl.bow.ModularBowItem.getArrowVelocity;

public class ModularMMTBow extends BowItem implements IModularItem {

    private final Cache<String, Multimap<Attribute, AttributeModifier>> attributeCache;
    private final Cache<String, ToolData> toolCache;
    private final Cache<String, EffectData> effectCache;
    private final Cache<String, ItemProperties> propertyCache;
    protected SynergyData[] synergies;
    public final static String identifier = "modular_mmt_bow";
    public ModularMMTBow() {
        super(new Properties().stacksTo(1));
        this.attributeCache = CacheBuilder.newBuilder().maximumSize(1000L).expireAfterWrite(5L, TimeUnit.MINUTES).build();
        this.toolCache = CacheBuilder.newBuilder().maximumSize(1000L).expireAfterWrite(5L, TimeUnit.MINUTES).build();
        this.effectCache = CacheBuilder.newBuilder().maximumSize(1000L).expireAfterWrite(5L, TimeUnit.MINUTES).build();
        this.propertyCache = CacheBuilder.newBuilder().maximumSize(1000L).expireAfterWrite(5L, TimeUnit.MINUTES).build();
        this.synergies = new SynergyData[0];
        DataManager.instance.moduleData.onReload(this::clearCaches);
        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this, identifier));
    }
    public void commonInit(PacketHandler packetHandler) {
        DataManager.instance.synergyData.onReload(() -> synergies = DataManager.instance.synergyData.getOrdered("modular_mmt_bow/"));
    }
    @Override
    public int getHoneBase(ItemStack itemStack) {
        return 450;
    }
    @Override
    public int getHoneIntegrityMultiplier(ItemStack itemStack) {
        return 200;
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack itemStack) {
        if (this.isBroken(itemStack)) {
            return AttributeHelper.emptyMap;
        } else if (slot == EquipmentSlot.MAINHAND) {
            return this.getAttributeModifiersCached(itemStack);
        } else {
            return slot == EquipmentSlot.OFFHAND ? (Multimap)this.getAttributeModifiersCached(itemStack).entries().stream().filter((entry) -> !((Attribute)entry.getKey()).equals(Attributes.ATTACK_DAMAGE) && !((Attribute)entry.getKey()).equals(Attributes.ATTACK_DAMAGE)).collect(Multimaps.toMultimap(Map.Entry::getKey, Map.Entry::getValue, ArrayListMultimap::create)) : AttributeHelper.emptyMap;
        }
    }


    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.addAll(this.getTooltip(stack, world, flag));
    }
    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public void clearCaches() {
        this.attributeCache.invalidateAll();
        this.toolCache.invalidateAll();
        this.effectCache.invalidateAll();
        this.propertyCache.invalidateAll();

    }

    @Override//主要
    public String[] getMajorModuleKeys(ItemStack itemStack) {
        return new String[]{"modular_mmt_bow/stave", "modular_mmt_bow/string"};
    }
    @Override
    public GuiModuleOffsets getMajorGuiOffsets(ItemStack itemStack) {
        return new GuiModuleOffsets( 5,-1, 5,18);
    }
    @Override//次要
    public String[] getMinorModuleKeys(ItemStack itemStack) {
        return new String[]{"modular_mmt_bow/riser"};
    }
    @Override
    public GuiModuleOffsets getMinorGuiOffsets(ItemStack itemStack) {
        // 返回预先定义的minorOffsets，用于在GUI中定位次要模块。
        return new GuiModuleOffsets(-13, -2, -22, 12, -13, 26);
    }
    @Override
    public String[] getRequiredModules(ItemStack itemStack) {
        return new String[]{"modular_mmt_bow/string","modular_mmt_bow/stave"};
    }

    @Override
    public boolean canGainHoneProgress(ItemStack itemStack) {
        return true;
    }

    @Override
    public Cache<String, Multimap<Attribute, AttributeModifier>> getAttributeModifierCache() {
        return attributeCache;
    }

    @Override
    public Cache<String, EffectData> getEffectDataCache() {
        return effectCache;
    }

    @Override
    public Cache<String, ItemProperties> getPropertyCache() {
        return propertyCache;
    }

    @Override
    public SynergyData[] getAllSynergyData(ItemStack itemStack) {
        return synergies;
    }
    /// 自己的部分
    @Override // 覆盖父类ModularItem的方法。
    public Collection<ItemModule> getAllModules(ItemStack stack) {
        // 获取物品的NBT数据。NBT数据用于持久化存储物品的自定义信息，包括所安装的模块。
        CompoundTag stackTag = stack.getTag();
        // 检查NBT数据是否存在，如果不存在（例如物品刚创建时），则返回空列表。
        if (stackTag != null) {
            // 使用Stream API来处理模块。
            return Stream.concat( // 合并两个Stream：主要模块的Stream和次要模块的Stream。
                            Arrays.stream(getMajorModuleKeys(stack)), // 获取该物品定义的主要模块键。
                            Arrays.stream(getMinorModuleKeys(stack)) // 获取该物品定义的次要模块键。
                    )
                    // 从NBT数据中获取每个模块键对应的字符串值（模块的注册名）。
                    .map(stackTag::getString)
                    // 使用ItemUpgradeRegistry来查找对应的ItemModule对象。
                    // ItemUpgradeRegistry.instance::getModule 是一个方法引用，相当于 lambda: moduleKey -> ItemUpgradeRegistry.instance.getModule(moduleKey)。
                    .map(ItemUpgradeRegistry.instance::getModule)
                    // 过滤掉那些未能成功加载的模块（即registry.getModule返回null的情况）。
                    .filter(Objects::nonNull)
                    // 将过滤后的所有模块收集到一个List中。
                    .collect(Collectors.toList());
        }

        // 如果NBT数据为空，则返回一个空的不可变列表。
        return Collections.emptyList();
    }
    /// 重命名
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return Component.literal(this.getItemName(stack));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack bowStack = player.getItemInHand(hand);
        boolean hasAmmo = !player.getProjectile(this.getItem().getDefaultInstance()).isEmpty();
        if (this.isBroken(bowStack)) {
            return InteractionResultHolder.pass(bowStack);
        } else {
            InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onArrowNock(bowStack, world, player, hand, hasAmmo);
            if (ret != null) {
                return ret;
            } else if (!hasAmmo && !player.getAbilities().instabuild
                    && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.INFINITY_ARROWS, bowStack) <= 0) {
                return InteractionResultHolder.fail(bowStack);
            } else {
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(bowStack);
            }
        }
    }
    @Override
    public void releaseUsing(ItemStack itemStack, Level world, LivingEntity entity, int timeLeft) {
        if (this.getEffectLevel(itemStack, ItemEffect.overbowed) > 0 && timeLeft <= 0) {
            entity.stopUsingItem();
            CastOptional.cast(entity, Player.class).ifPresent((player) -> player.getCooldowns().addCooldown(this, 10));
        } else {
            this.fireArrow(itemStack, world, entity, timeLeft);
        }

    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level world, LivingEntity entity) {
        if (this.getEffectLevel(itemStack, ItemEffect.overbowed) > 0) {
            entity.stopUsingItem();
            CastOptional.cast(entity, Player.class).ifPresent((player) -> player.getCooldowns().addCooldown(this, 10));
        }

        return super.finishUsingItem(itemStack, world, entity);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack itemStack, int count) {
        if (this.getEffectLevel(itemStack, ItemEffect.releaseLatch) > 0 && this.getProgress(itemStack, entity) >= 1.0F) {
            entity.releaseUsingItem();
        }

    }
    protected void fireArrow(ItemStack itemStack, Level world, LivingEntity entity, int timeLeft) {
        if (itemStack.getItem() instanceof IModularItem ModularItem) {
            if (entity instanceof Player player) {
                ModularItem.tickProgression(player, itemStack, 3);
            } else {
                ModularItem.tickProgression(null, itemStack, 3);
            }
        }
        if (entity instanceof Player player) {
            ItemStack ammoStack = player.getProjectile( player.getUseItem() );
            boolean playerInfinite = this.isInfinite(player, itemStack, ammoStack);
            int drawProgress = Math.round(this.getProgress(itemStack, entity) * 20.0F);
            drawProgress = ForgeEventFactory.onArrowLoose(itemStack, world, player, drawProgress, !ammoStack.isEmpty() || playerInfinite);
            if (drawProgress < 0) {
                return;
            }

            if (!ammoStack.isEmpty() || playerInfinite) {
                if (ammoStack.isEmpty()) {
                    ammoStack = new ItemStack(Items.ARROW);
                }

                double strength = this.getAttributeValue(itemStack, (Attribute)TetraAttributes.drawStrength.get());
                float velocityBonus = (float)this.getEffectLevel(itemStack, ItemEffect.velocity) / 100.0F;
                int suspendLevel = this.getEffectLevel(itemStack, ItemEffect.suspend);
                ArrowItem ammoItem = (ArrowItem)CastOptional.cast(ammoStack.getItem(), ArrowItem.class).orElse((ArrowItem)Items.ARROW);
                boolean infiniteAmmo = player.getAbilities().instabuild || ammoItem.isInfinite(ammoStack, itemStack, player);
                ModularLooseProjectilesEvent looseProjectilesEvent = new ModularLooseProjectilesEvent(itemStack, ammoStack, player, world, drawProgress, this.getAttributeValue(itemStack, (Attribute)TetraAttributes.drawStrength.get()), suspendLevel > 0, getArrowVelocity(drawProgress, strength, (float)this.getEffectLevel(itemStack, ItemEffect.velocity) / 100.0F, suspendLevel > 0), (double)this.getEffectEfficiency(itemStack, ItemEffect.multishot), Math.max(0.0F, 100.0F - this.getEffectEfficiency(itemStack, ItemEffect.spread) - FocusEffect.getSpreadReduction(player, itemStack)), player.getAbilities().instabuild || ammoItem.isInfinite(ammoStack, itemStack, player), Mth.clamp(this.getEffectLevel(itemStack, ItemEffect.multishot), 1, infiniteAmmo ? 64 : ammoStack.getCount()), (double)player.getXRot(), (double)player.getYRot());
                MinecraftForge.EVENT_BUS.post(looseProjectilesEvent);
                ammoStack = looseProjectilesEvent.getAmmoStack();
                ImmutableList<Function<AbstractArrow, AbstractArrow>> projectileRemappers = looseProjectilesEvent.getProjectileRemappers();
                strength = looseProjectilesEvent.getStrength();
                boolean hasSuspend = looseProjectilesEvent.isHasSuspend();
                float projectileVelocity = looseProjectilesEvent.getProjectileVelocity();
                double multishotSpread = looseProjectilesEvent.getMultishotSpread();
                float accuracy = looseProjectilesEvent.getAccuracy();
                infiniteAmmo = looseProjectilesEvent.isInfiniteAmmo();
                int count = looseProjectilesEvent.getCount();
                double basePitch = looseProjectilesEvent.getBasePitch();
                double baseYaw = looseProjectilesEvent.getBaseYaw();
                if (projectileVelocity > 0.1F) {
                    if (!world.isClientSide) {
                        int powerLevel = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, itemStack);
                        int punchLevel = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, itemStack);
                        int flameLevel = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack);
                        int piercingLevel = this.getEffectLevel(itemStack, ItemEffect.piercing) + EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PIERCING, itemStack);

                        for(int i = 0; i < count; ++i) {
                            double yaw = baseYaw - multishotSpread * (double)(count - 1) / (double)2.0F + multishotSpread * (double)i;
                            fireProjectile(itemStack, world, (ArrowItem)ammoStack.getItem(), ammoStack, projectileRemappers, player, (float)basePitch, (float)yaw, projectileVelocity, accuracy, drawProgress, strength, powerLevel, punchLevel, flameLevel, piercingLevel, hasSuspend, infiniteAmmo);
                        }

                        this.applyDamage(1, itemStack, player);
                        this.applyNegativeUsageEffects(entity, itemStack, (double)1.0F);
                        if (drawProgress > 15) {
                            this.applyPositiveUsageEffects(entity, itemStack, (double)1.0F);
                        }
                    }

                    float pitchBase = projectileVelocity;
                    if (velocityBonus > 0.0F) {
                        pitchBase = projectileVelocity - projectileVelocity * velocityBonus;
                    } else if (hasSuspend) {
                        pitchBase = projectileVelocity / 2.0F;
                    }

                    world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 0.8F + projectileVelocity * 0.2F, 1.9F + world.random.nextFloat() * 0.2F - pitchBase * 0.8F);
                    if (!infiniteAmmo && !player.getAbilities().instabuild) {
                        ammoStack.shrink(count);
                        if (ammoStack.isEmpty()) {
                            player.getInventory().removeItem(ammoStack);
                        }
                    }

                    FocusEffect.onFireArrow(player, itemStack);
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }

    }
    public static void fireProjectile(ItemStack itemStack, Level world, ArrowItem ammoItem, ItemStack ammoStack, ImmutableList<Function<AbstractArrow, AbstractArrow>> projectileRemappers, Player player, float basePitch, float yaw, float projectileVelocity, float accuracy, int drawProgress, double strength, int powerLevel, int punchLevel, int flameLevel, int piercingLevel, boolean hasSuspend, boolean infiniteAmmo) {
        AbstractArrow projectile = ammoItem.createArrow(world, ammoStack, player);

        Function<AbstractArrow, AbstractArrow> remapper;
        for(UnmodifiableIterator var20 = projectileRemappers.iterator(); var20.hasNext(); projectile = (AbstractArrow)remapper.apply(projectile)) {
            remapper = (Function)var20.next();
        }

        projectile.shootFromRotation(player, basePitch, yaw, 0.0F, projectileVelocity * 3.0F, accuracy);
        if (drawProgress >= 20) {
            projectile.setCritArrow(true);
        }

        projectile.setBaseDamage(projectile.getBaseDamage() - (double)2.0F + strength / (double)3.0F);
        if (powerLevel > 0) {
            projectile.setBaseDamage(projectile.getBaseDamage() + (double)powerLevel * (double)0.5F + (double)0.5F);
        }

        if (projectileVelocity > 1.0F) {
            projectile.setBaseDamage(projectile.getBaseDamage() / (double)projectileVelocity);
        }

        if (punchLevel > 0) {
            projectile.setKnockback(punchLevel);
        }

        if (flameLevel > 0) {
            projectile.setSecondsOnFire(100);
        }

        if (piercingLevel > 0) {
            projectile.setPierceLevel((byte)piercingLevel);
        }

        if (hasSuspend && drawProgress >= 20) {
            projectile.setNoGravity(true);
        }

        if (infiniteAmmo || player.getAbilities().instabuild && (ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW)) {
            projectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }

        if (hasSuspend && drawProgress >= 20) {
            Vec3 projDir = projectile.getDeltaMovement().normalize();
            Vec3 projPos = projectile.position();

            for(int j = 0; j < 4; ++j) {
                Vec3 pos = projPos.add(projDir.scale((double)(2 + j * 2)));
                ((ServerLevel)world).sendParticles(ParticleTypes.END_ROD, pos.x(), pos.y(), pos.z(), 1, (double)0.0F, (double)0.0F, (double)0.0F, 0.01);
            }
        }

        world.addFreshEntity(projectile);
        ModularProjectileSpawnEvent event = new ModularProjectileSpawnEvent(itemStack, ammoStack, player, projectile, world, drawProgress);
        MinecraftForge.EVENT_BUS.post(event);
        if (projectileVelocity * 3.0F > 4.0F) {
            TetraMod.packetHandler.sendToAllPlayersNear(new ProjectileMotionPacket(projectile), projectile.blockPosition(), (double)512.0F, world.dimension());
        }

    }


    private boolean isInfinite(Player player, ItemStack bowStack, ItemStack ammoStack) {
        return player.getAbilities().instabuild || ammoStack.isEmpty() && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bowStack) > 0 || (Boolean)CastOptional.cast(ammoStack.getItem(), ArrowItem.class).map((item) -> item.isInfinite(ammoStack, bowStack, player)).orElse(false);
    }

    public int getDrawDuration(ItemStack itemStack) {
        return Math.max((int)((double)20.0F * (this.getAttributeValue(itemStack, (Attribute)TetraAttributes.drawSpeed.get()) - (double)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, itemStack) * 0.2)), 1);
    }

    public float getProgress(ItemStack itemStack, @Nullable LivingEntity entity) {
        return (Float)Optional.ofNullable(entity).filter((e) -> e.getUseItemRemainingTicks() > 0).filter((e) -> itemStack.equals(e.getUseItem())).map((e) -> (float)(this.getUseDuration(itemStack) - e.getUseItemRemainingTicks()) * 1.0F / (float)this.getDrawDuration(itemStack)).orElse(0.0F);
    }

    public float getOverbowProgress(ItemStack itemStack, @Nullable LivingEntity entity) {
        int overbowedLevel = this.getEffectLevel(itemStack, ItemEffect.overbowed);
        return overbowedLevel > 0 ? (Float)Optional.ofNullable(entity).filter((e) -> itemStack.equals(e.getUseItem())).map(LivingEntity::getUseItemRemainingTicks).map((useCount) -> 1.0F - (float)useCount / ((float)overbowedLevel * 2.0F)).map((progress) -> Mth.clamp(progress, 0.0F, 1.0F)).orElse(0.0F) : 0.0F;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        int overbowedLevel = this.getEffectLevel(itemStack, ItemEffect.overbowed);
        return overbowedLevel > 0 ? overbowedLevel * 2 + this.getDrawDuration(itemStack) : '邈';
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public boolean canBeDepleted() {
        return true;
    }

    private String getDrawVariant(ItemStack itemStack, @Nullable LivingEntity entity) {
        float progress = this.getProgress(itemStack, entity);
        if (progress == 0.0F) {
            return "item";
        } else if ((double)progress < 0.65) {
            return "draw_0";
        } else {
            return (double)progress < 0.9 ? "draw_1" : "draw_2";
        }
    }

    @Override
    public String getModelCacheKey(ItemStack itemStack, LivingEntity entity) {
        String var10000 = IModularItem.super.getModelCacheKey(itemStack, entity);
        return var10000 + ":" + this.getDrawVariant(itemStack, entity);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ImmutableList<ModuleModel> getModels(ItemStack itemStack, @Nullable LivingEntity entity) {
        // 依据拉弓进度获取模型类型，仍保留切换“item”->“draw_0”->“draw_1”->“draw_2”等状态
        String drawVariant = getDrawVariant(itemStack, entity);

        // 获取所有模块的模型，并过滤只保留type等于当前drawVariant或静态（"static"）的模型
        ImmutableList<ModuleModel> baseModels = this.getAllModules(itemStack).stream()
                .sorted(Comparator.comparing(m -> m.getRenderLayer())) // 按渲染层排序
                .flatMap(module -> Arrays.stream(module.getModels(itemStack)))
                .filter(Objects::nonNull)
                .filter(model -> model.type.equals(drawVariant) || model.type.equals("static"))
                .sorted(Comparator.comparing(ModuleModel::getRenderLayer))
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));

        // 不再额外添加箭矢模型，直接返回模块模型列表
        return baseModels;
    }

    protected int baseDurability;
    @Override
    public int getMaxDamage(ItemStack itemStack) {
        return (Integer)Optional.of(this.getPropertiesCached(itemStack)).map((properties) -> (float)(properties.durability + this.baseDurability) * properties.durabilityMultiplier).map(Math::round).orElse(0);
    }

    @Override
    public void setDamage(ItemStack itemStack, int damage) {
        super.setDamage(itemStack, Math.min(itemStack.getMaxDamage() - 1, damage));
    }
}
