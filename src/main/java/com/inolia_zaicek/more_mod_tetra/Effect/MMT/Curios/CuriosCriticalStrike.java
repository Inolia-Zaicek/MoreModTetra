package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import javax.swing.text.InternationalFormatter;
import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class CuriosCriticalStrike {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        //双数据的显示代码要改
        var statGetter1 = new StatGetterEffectLevel(curiosCriticalStrikeEffect, 1);
        var statGetter2 = new StatGetterEffectEfficiency(curiosCriticalStrikeEffect, 1);
        IStatGetter[] statGetters = { statGetter1,statGetter2};
        IStatFormat[] statFormats = {StatFormat.noDecimal,StatFormat.noDecimal};
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosCriticalStrikeName, 0, 100, false, false, false,
                statGetter1, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(curiosCriticalStrikeTooltip, statGetters,statFormats)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                float chance = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player,curiosCriticalStrikeEffect);
                float damage = MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(player,curiosCriticalStrikeEffect);
                Random random = new Random();
                if (chance > 0&&random.nextInt(100) <= chance) {
                    //最终伤害：与原版不同，是200————200%爆伤
                    float finish = event.getAmount() * (1 + damage / 100);
                    event.setAmount(finish);
                }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                float chance = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player,curiosCriticalStrikeEffect);
                float damage = MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(player,curiosCriticalStrikeEffect);
                Random random = new Random();
                if (chance > 0&&random.nextInt(100) <= chance) {
                    //最终伤害：与原版不同，是200————200%爆伤
                    float finish = event.getAmount() * (1 + damage / 100);
                    event.setAmount(finish);
                }
            }
        }
    }
}
