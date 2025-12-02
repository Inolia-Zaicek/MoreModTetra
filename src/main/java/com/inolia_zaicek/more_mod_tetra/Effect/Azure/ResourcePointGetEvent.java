package com.inolia_zaicek.more_mod_tetra.Effect.Azure;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import se.mickelus.tetra.items.modular.IModularItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class ResourcePointGetEvent {
    private static final String resource_point_nbt = MoreModTetra.MODID + ":resource_point";
    @SubscribeEvent
    public static void pointGet(BlockEvent.BreakEvent event) {
        //获取拆方块的玩家
        Player player = event.getPlayer();
        CompoundTag compoundTag = player.getPersistentData();
        Level level = player.level();
        //获取资源点
        int resourcePoint = compoundTag.getInt(resource_point_nbt);
        BlockState brokenState = event.getState();
        //判断是否是矿石类型方块
        boolean isOreBlock = brokenState.is(net.minecraftforge.common.Tags.Blocks.ORES);
        //石头
        boolean isStoneBlock = brokenState.is(Tags.Blocks.STONE);
        //凝矿
        int gatherOreLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(player,gather_ore_Effect);
        int gatherOreEfficiency = (int) MMTEffectHelper.getInstance().getMainOffHandSumEffectEfficiency(player,gather_ore_Effect);
        //矿石精华
        int mineralEssenceLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(player,mineral_essence_Effect);
        float mineralEssenceEfficiency = MMTEffectHelper.getInstance().getMainOffHandSumEffectEfficiency(player,mineral_essence_Effect);
        //考古
        int archaeologyLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(player,archaeology_Effect);
        int archaeologyEfficiency = (int) MMTEffectHelper.getInstance().getMainOffHandSumEffectEfficiency(player,archaeology_Effect);
        //精准采集等级
        int totalLootingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player);
        //判断
        List<ItemStack> extraDrops = new ArrayList<>();
        int number = 0;//基础增加资源点数额
        if(gatherOreLevel>0){
            number+=gatherOreLevel;
        }
        //资源点满100
        if(resourcePoint>=100){
            if(gatherOreEfficiency>0) {
                Random random = new Random();
                int randRoll = random.nextInt(101);// 0-99
                if (randRoll <= 10) {
                    extraDrops.add(new ItemStack(Items.ANCIENT_DEBRIS, gatherOreEfficiency));
                }
                if (randRoll <= 20) {
                    extraDrops.add(new ItemStack(Items.DIAMOND, gatherOreEfficiency));
                }
                if (randRoll <= 30) {
                    extraDrops.add(new ItemStack(Items.EMERALD, 2*gatherOreEfficiency));
                }
                if (randRoll <= 40) {
                    extraDrops.add(new ItemStack(Items.LAPIS_LAZULI, 3*gatherOreEfficiency));
                }
                if (randRoll <= 50) {
                    extraDrops.add(new ItemStack(Items.REDSTONE, 3*gatherOreEfficiency));
                }
                if (randRoll <= 60) {
                    extraDrops.add(new ItemStack(Items.RAW_GOLD, gatherOreEfficiency));
                }
                if (randRoll <= 70) {
                    extraDrops.add(new ItemStack(Items.RAW_IRON, gatherOreEfficiency));
                }
                if (randRoll <= 80) {
                    extraDrops.add(new ItemStack(Items.RAW_COPPER, gatherOreEfficiency));
                }
                if (randRoll <= 99) {
                    extraDrops.add(new ItemStack(Items.COAL, 3*gatherOreEfficiency));
                }
                number-=100;
            }
        }
        if(totalLootingLevel<=0) {
            //矿石类
            if (isOreBlock) {
                if(mineralEssenceLevel > 0) {
                    number += gatherOreLevel;
                }
                //考古
                if(archaeologyLevel>0&&archaeologyEfficiency>0){
                    Random random = new Random();
                    int randRoll = random.nextInt(101);// 0-99
                    if (randRoll <= archaeologyLevel) {
                        extraDrops.add(new ItemStack(Items.ANCIENT_DEBRIS, archaeologyEfficiency));
                    }
                }
            }
            if(mineralEssenceEfficiency>0&&isStoneBlock){
                Random random = new Random();
                int randRoll = random.nextInt(90);// 0-99
                if (randRoll <= 10) {
                    extraDrops.add(new ItemStack(Items.ANCIENT_DEBRIS, 1));
                }else if (randRoll <= 20) {
                    extraDrops.add(new ItemStack(Items.DIAMOND, 1));
                }else if (randRoll <= 30) {
                    extraDrops.add(new ItemStack(Items.EMERALD, 2));
                }else if (randRoll <= 40) {
                    extraDrops.add(new ItemStack(Items.LAPIS_LAZULI, 3));
                }else if (randRoll <= 50) {
                    extraDrops.add(new ItemStack(Items.REDSTONE, 3));
                }else if (randRoll <= 60) {
                    extraDrops.add(new ItemStack(Items.RAW_GOLD, 1));
                }else if (randRoll <= 70) {
                    extraDrops.add(new ItemStack(Items.RAW_IRON, 1));
                }else if (randRoll <= 80) {
                    extraDrops.add(new ItemStack(Items.RAW_COPPER, 1));
                }else {
                    extraDrops.add(new ItemStack(Items.COAL, 3));
                }
            }
        }
        //掉落物集体结算
        if (!extraDrops.isEmpty()) {
            BlockPos centerPos = event.getPos();
            Vec3 spawnPos = new Vec3(centerPos.getX() + 0.5, centerPos.getY() + 0.5, centerPos.getZ() + 0.5);
            Objects.requireNonNull(level.getServer()).execute(() -> {
                for (ItemStack stack : extraDrops) {
                    if (!stack.isEmpty()) {
                        ItemEntity itemEntity = new ItemEntity(level, spawnPos.x, spawnPos.y, spawnPos.z, stack.copy());level.addFreshEntity(itemEntity);
                    }
                }
            });
        }
        //资源点结算
        if(number!=0){
            if(number<0) {
                compoundTag.putInt(resource_point_nbt, Math.max(0, resourcePoint - number));
            }else{
                compoundTag.putInt(resource_point_nbt, resourcePoint+number);
            }
        }
    }
    //狩猎

    @SubscribeEvent
    public static void entityKilled(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            Random random = new Random();
            if (event.getSource().getDirectEntity() instanceof Player player) {
                int chance = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(player,hunting_Effect);
                int effectLevel = (int) MMTEffectHelper.getInstance().getMainOffHandSumEffectEfficiency(player,hunting_Effect);
                if (effectLevel > 0&&random.nextInt(101)<=chance) {
                    for (int i = 0; i < effectLevel; i++) {
                        Level level = player.level();
                        LootTable loot = ((MinecraftServer) Objects.requireNonNull(level.getServer())).getLootData().getLootTable(event.getEntity().getType().getDefaultLootTable());
                        LootParams context = (new LootParams.Builder((ServerLevel) level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(event.getEntity().blockPosition())).withParameter(LootContextParams.THIS_ENTITY, event.getEntity()).withParameter(LootContextParams.DAMAGE_SOURCE, player.damageSources().playerAttack(player)).create(LootContextParamSets.ENTITY);
                        List<ItemStack> drops = loot.getRandomItems(context);
                        for (ItemStack drop : drops) {
                            ItemEntity itementity = new ItemEntity(level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), drop);
                            itementity.setDefaultPickUpDelay();
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F), (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                    }
                }
            }
            else if (event.getSource().getEntity() instanceof Player player) {
                int chance = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(player,hunting_Effect);
                int effectLevel = (int) MMTEffectHelper.getInstance().getMainOffHandSumEffectEfficiency(player,hunting_Effect);
                if (effectLevel > 0&&random.nextInt(101)<=chance) {
                    for (int i = 0; i < effectLevel; i++) {
                        Level level = player.level();
                        LootTable loot = ((MinecraftServer) Objects.requireNonNull(level.getServer())).getLootData().getLootTable(event.getEntity().getType().getDefaultLootTable());
                        LootParams context = (new LootParams.Builder((ServerLevel) level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(event.getEntity().blockPosition())).withParameter(LootContextParams.THIS_ENTITY, event.getEntity()).withParameter(LootContextParams.DAMAGE_SOURCE, player.damageSources().playerAttack(player)).create(LootContextParamSets.ENTITY);
                        List<ItemStack> drops = loot.getRandomItems(context);
                        for (ItemStack drop : drops) {
                            ItemEntity itementity = new ItemEntity(level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), drop);
                            itementity.setDefaultPickUpDelay();
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F), (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                    }
                }
            }
        }
    }
    //点石成金
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offHandItem = player.getOffhandItem();

        // 假设你已有对某个自定义 Item 的判断
        if (!(mainHandItem.getItem() instanceof IModularItem item)) return;

        // 计算炼成等级与效果强度
        float toolDamage = item.getEffectLevel(mainHandItem, turning_stone_into_gold_Effect);
        int level = (int) item.getEffectEfficiency(mainHandItem, turning_stone_into_gold_Effect);

        if (level > 0) {
            Level world = event.getLevel();
            BlockPos targetPos = event.getPos();

            // 获取当前目标方块
            BlockState currentState = world.getBlockState(targetPos);
            Block nextBlock = getBlock(currentState, level);
            if (nextBlock != null) {
                // 预计算耐久扣除，用于判断是否足以进行转化
                boolean canConsume = true;
                int damageToApply = 0;

                if (toolDamage > 0.0f && mainHandItem.isDamageableItem()) {
                    int maxDamage = mainHandItem.getMaxDamage();
                    // 百分比扣除，向下取整，非正数不扣
                    damageToApply = (int) Math.floor((toolDamage / 100.0f) * maxDamage);
                    // 限制在 [0, maxDamage-1]
                    if (damageToApply < 0) damageToApply = 0;
                    if (damageToApply > maxDamage - 1) damageToApply = maxDamage - 1;

                    // 如果计算结果为 0，则视为耐久不足/不够扣除，禁止转化
                    if (damageToApply <= 0) {
                        canConsume = false;
                    }
                }

                if (canConsume) {
                    BlockState nextState = nextBlock.defaultBlockState(); // 使用 defaultBlockState()
                    // 将目标方块替换为下一个方块的默认状态
                    world.setBlockAndUpdate(targetPos, nextState);

                    // 只有在确实扣除了耐久时才应用耐久变化
                    if (damageToApply > 0) {
                        mainHandItem.setDamageValue(mainHandItem.getDamageValue() + damageToApply);
                    }

                    event.setCanceled(true);
                } else {
                    // 耐久不足，禁止转化并提示玩家
                    player.sendSystemMessage(net.minecraft.network.chat.Component.literal("tooltip.more_mod_tetra:turning_stone_into_gold.error")
                            .withStyle(ChatFormatting.GRAY));
                    // 如果你还希望执行额外逻辑，可以在这里添加
                    event.setCanceled(true); // 根据你的需求决定是否要取消事件
                }
                player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), 5);//设置冷却时间
            }
        }
    }
    //方法
    private static @Nullable Block getBlock(BlockState currentState, int level) {
        Block currentBlock = currentState.getBlock();
        Block nextBlock = null;
        if (currentBlock == Blocks.STONE && level >= 1) {
            nextBlock = Blocks.COAL_BLOCK;
        }
        else if (currentBlock == Blocks.COAL_BLOCK && level >= 2) {
            nextBlock = Blocks.IRON_BLOCK;
        }
        else if (currentBlock == Blocks.IRON_BLOCK && level >= 3) {
            nextBlock = Blocks.GOLD_BLOCK;
        }
        else if (currentBlock == Blocks.GOLD_BLOCK && level >= 4) {
            nextBlock = Blocks.EMERALD_BLOCK;
        }
        else if (currentBlock == Blocks.EMERALD_BLOCK && level >= 5) {
            nextBlock = Blocks.DIAMOND_BLOCK;
        }
        return nextBlock;
    }
}
