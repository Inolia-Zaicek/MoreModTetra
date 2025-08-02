package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.RosmontisIngotEffect;

import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.lang.reflect.Field;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Notes {
    public static boolean isBoss(LivingEntity living) {
        try {
            Class<?> clazz = living.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == ServerBossEvent.class) {
                    field.setAccessible(true);
                    ServerBossEvent bossEvent = (ServerBossEvent) field.get(living);
                    if (bossEvent != null) {
                        return true;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            System.err.println("反射坠机，无法获取ServerBossEvent字段");
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(notesEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                notesName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(notesTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //血条判断
        if (isBoss(event.getEntity())) {
            //攻击
            if (event.getSource().getDirectEntity() instanceof Player player) {
                player.sendSystemMessage(Component.literal("传送至记录点！").withStyle(ChatFormatting.GREEN));
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, notesEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, notesEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if (MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
                        event.setAmount(event.getAmount() * (1 + (float) effectLevel / 100));
                    }else{
                        player.sendSystemMessage(Component.literal("传送至记录点！").withStyle(ChatFormatting.GREEN));
                        event.setAmount(event.getAmount() * (1 + (float) effectLevel / 50));
                    }
                }
            }
        }
    }
}