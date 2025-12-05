package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.FE;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import com.inolia_zaicek.more_tetra_tools.MoreTetraTools;
import com.mega.uom.common.attribute.ModAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class FantasyTrace {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(fantasy_traceEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                fantasy_traceName, 0, 400, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(fantasy_traceTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    private static final ResourceLocation fantasy_devour_number = new ResourceLocation(MoreTetraTools.MODID, "fantasy_devour_nbt");
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offHandItem = livingEntity.getOffhandItem();
            if (mainHandItem.getItem() instanceof IModularItem item && item.getEffectLevel(mainHandItem, fantasy_traceEffect) > 0) {
                float level =item.getEffectLevel(mainHandItem, fantasy_traceEffect);
                CompoundTag persistentData = mainHandItem.getOrCreateTag();
                int number = persistentData.getInt(String.valueOf(fantasy_devour_number));
                if (number >= 7) {
                    float fire = (float) livingEntity.getAttributeValue(ModAttributes.FANTASY_SPELL_POWER.get());
                    float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    float finish = fire * atk * level / 100;
                    var FIRE_MAGIC_DAMAGE = MMTTickZero.source(livingEntity.level(), DamageTypes.MAGIC);
                    var mobList = MMTUtil.mobList(25, target);
                    for (Mob mobs : mobList) {
                        if (mobs != null) {
                            //获取伤害类型
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                            mobs.hurt(FIRE_MAGIC_DAMAGE, finish);
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                        }
                    }
                    persistentData.putInt(String.valueOf(fantasy_devour_number), 0);
                } else {
                    persistentData.putInt(String.valueOf(fantasy_devour_number), number + 1);
                }
            }else if (offHandItem.getItem() instanceof IModularItem item && item.getEffectLevel(offHandItem, fantasy_traceEffect) > 0) {
                float level =item.getEffectLevel(offHandItem, fantasy_traceEffect);
                CompoundTag persistentData = offHandItem.getOrCreateTag();
                int number = persistentData.getInt(String.valueOf(fantasy_devour_number));
                if (number >= 7) {
                    float fire = (float) livingEntity.getAttributeValue(ModAttributes.FANTASY_SPELL_POWER.get());
                    float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    float finish = fire * atk * level / 100;
                    var FIRE_MAGIC_DAMAGE = MMTTickZero.source(livingEntity.level(), DamageTypes.MAGIC);
                    var mobList = MMTUtil.mobList(25, target);
                    for (Mob mobs : mobList) {
                        if (mobs != null) {
                            //获取伤害类型
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                            mobs.hurt(FIRE_MAGIC_DAMAGE, finish);
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                        }
                    }
                    persistentData.putInt(String.valueOf(fantasy_devour_number), 0);
                } else {
                    persistentData.putInt(String.valueOf(fantasy_devour_number), number + 1);
                }
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offHandItem = livingEntity.getOffhandItem();
            if (mainHandItem.getItem() instanceof IModularItem item && item.getEffectLevel(mainHandItem, fantasy_traceEffect) > 0) {
                float level =item.getEffectLevel(mainHandItem, fantasy_traceEffect);
                CompoundTag persistentData = mainHandItem.getOrCreateTag();
                int number = persistentData.getInt(String.valueOf(fantasy_devour_number));
                if (number >= 7) {
                    float fire = (float) livingEntity.getAttributeValue(ModAttributes.FANTASY_SPELL_POWER.get());
                    float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    float finish = fire * atk * level / 100;
                    var FIRE_MAGIC_DAMAGE = MMTTickZero.source(livingEntity.level(), DamageTypes.MAGIC);
                    var mobList = MMTUtil.mobList(25, target);
                    for (Mob mobs : mobList) {
                        if (mobs != null) {
                            //获取伤害类型
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                            mobs.hurt(FIRE_MAGIC_DAMAGE, finish);
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                        }
                    }
                    persistentData.putInt(String.valueOf(fantasy_devour_number), 0);
                } else {
                    persistentData.putInt(String.valueOf(fantasy_devour_number), number + 1);
                }
            }else if (offHandItem.getItem() instanceof IModularItem item && item.getEffectLevel(offHandItem, fantasy_traceEffect) > 0) {
                float level =item.getEffectLevel(offHandItem, fantasy_traceEffect);
                CompoundTag persistentData = offHandItem.getOrCreateTag();
                int number = persistentData.getInt(String.valueOf(fantasy_devour_number));
                if (number >= 7) {
                    float fire = (float) livingEntity.getAttributeValue(ModAttributes.FANTASY_SPELL_POWER.get());
                    float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    float finish = fire * atk * level / 100;
                    var FIRE_MAGIC_DAMAGE = MMTTickZero.source(livingEntity.level(), DamageTypes.MAGIC);
                    var mobList = MMTUtil.mobList(25, target);
                    for (Mob mobs : mobList) {
                        if (mobs != null) {
                            //获取伤害类型
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                            mobs.hurt(FIRE_MAGIC_DAMAGE, finish);
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                        }
                    }
                    persistentData.putInt(String.valueOf(fantasy_devour_number), 0);
                } else {
                    persistentData.putInt(String.valueOf(fantasy_devour_number), number + 1);
                }
            }
        }
    }
}
