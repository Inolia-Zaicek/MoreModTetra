package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class    KillerAuraEffectTrait {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(killerAuraEffectTraitEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                killerAuraEffectTraitName, 0, 3, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(killerAuraEffectTraitTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (ModList.get().isLoaded("l2complements")) {
            CuriosApi.getCuriosInventory(livingEntity).ifPresent(inv -> inv.findCurios
                    (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                    slotResult -> {
                        slotResult.stack();
                        ItemStack itemStack = slotResult.stack();
                        IModularItem curiousItem = (IModularItem) itemStack.getItem();
                        //获取一下玩家主副手
                        ItemStack mainHandItem = livingEntity.getMainHandItem();
                        ItemStack offhandItem = livingEntity.getOffhandItem();
                        int effectLevel = 0;
                        effectLevel += curiousItem.getEffectLevel(itemStack, killerAuraEffectTraitEffect);
                        if (mainHandItem.getItem() instanceof IModularItem item) {
                            float mainEffectLevel = item.getEffectLevel(mainHandItem, killerAuraEffectTraitEffect);
                            if (mainEffectLevel > 0) {
                                effectLevel +=  mainEffectLevel;
                            }
                        }
                        if (offhandItem.getItem() instanceof IModularItem item) {
                            float offEffectLevel = item.getEffectLevel(offhandItem, killerAuraEffectTraitEffect);
                            if (offEffectLevel > 0) {
                                effectLevel += (int) offEffectLevel;
                            }
                        }
                        if (effectLevel > 0&&livingEntity.tickCount % 20 == 0) {
                            var mobList = MMTUtil.mobList(7,livingEntity);
                            for (Mob mobs:mobList){
                                if(mobs!=null) {
                                    //获取伤害类型
                                    mobs.invulnerableTime=0;
                                    if(livingEntity instanceof Player player) {
                                        mobs.setLastHurtByPlayer(player);
                                    }
                                    float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                                    mobs.hurt(mobs.damageSources().mobAttack(livingEntity),atk*effectLevel*0.15f);
                                    if(livingEntity instanceof Player player) {
                                        mobs.setLastHurtByPlayer(player);
                                    }
                                }
                            }
                        }
                        }
            ));
        }
    }
}
