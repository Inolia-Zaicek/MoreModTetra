package com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.IceDragonSteel;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class IceDragonPower {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(iceDragonPowerEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                iceDragonPowerName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(iceDragonPowerTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("iceandfire")) {
            //攻击
            if (event.getSource().getDirectEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, iceDragonPowerEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, iceDragonPowerEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    var map = mob.getActiveEffectsMap();
                    //缓慢增伤
                    if(mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)){
                        event.setAmount(event.getAmount()*(1+ (float) effectLevel /100) );
                    }
                    //龙霜
                    if(mob.hasEffect(MMTEffectsRegister.DragonIce.get())){
                        int buffLevel = mob.getEffect(MMTEffectsRegister.DragonIce.get()).getAmplifier();
                        if(buffLevel<4) {
                            map.put(MMTEffectsRegister.DragonIce.get(),
                                    new MobEffectInstance(MMTEffectsRegister.DragonIce.get(), 200, buffLevel + 1));
                        }else{
                            var mobList = MMTUtil.mobList(3,mob);
                            for (Mob mobs:mobList){
                                if(mobs!=null) {
                                    mobs.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200,2));
                                    //获取伤害类型
                                    mobs.invulnerableTime=0;
                                    mobs.setLastHurtByPlayer(player);
                                    float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                                    mobs.hurt(mobs.damageSources().freeze(),atk*1.5f);
                                    mobs.setLastHurtByPlayer(player);
                                }
                            }
                            mob.removeEffect(MMTEffectsRegister.DragonIce.get());
                        }
                    }else{
                        map.put(MMTEffectsRegister.DragonIce.get(),
                                new MobEffectInstance(MMTEffectsRegister.DragonIce.get(), 200, 0));
                    }
                    }
                }
            }
        }
    }
