package com.inolia_zaicek.more_mod_tetra.Effect.Botania;

import net.minecraft.world.entity.LivingEntity;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import vazkii.botania.common.entity.PixieEntity;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class PixieSummon {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(pixieSummonEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                pixieSummonName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(pixieSummonTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("botania")) {
            Random random = new Random();
            var attacked=event.getEntity();
            if (event.getSource().getEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, pixieSummonEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, pixieSummonEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0&& random.nextInt(100) <= (effectLevel) ) {
                    float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    float number=atk*(50+ (float) (10 * effectLevel) /2)/100;
                    PixieEntity entity =new PixieEntity(player.level());
                    //仇恨目标————主人————？————(伤害？)
                    entity.setProps(mob,player,0,number);
                    entity.setPos(player.getRandomX(4),player.getRandomY()+0.5*player.getBbHeight(),player.getRandomZ(4));
                    player.level().addFreshEntity(entity);
                }
            }
            //挨打
            if (attacked instanceof Player player&&event.getSource().getEntity() instanceof LivingEntity) {
                LivingEntity mob = event.getSource().getEntity().getControllingPassenger();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, pixieSummonEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, pixieSummonEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                //我手持武器
                //判断等级与近战
                if (effectLevel > 0&&mob!=null&& random.nextInt(100) <= (effectLevel) ) {
                    float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    float number=atk*(50+ (float) (10 * effectLevel) /2)/100;
                    PixieEntity entity =new PixieEntity(player.level());
                    //仇恨目标————主人————？————(伤害？)
                    entity.setProps(mob,player,0,number);
                    entity.setPos(player.getRandomX(4),player.getRandomY()+0.5*player.getBbHeight(),player.getRandomZ(4));
                    player.level().addFreshEntity(entity);
                }
            }
        }
    }
}
