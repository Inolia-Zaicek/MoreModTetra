package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
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

import java.util.List;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CuriosJank {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosJankEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosJankName, 0, 20, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosJankTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity player = event.getEntity();
            int effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, curiosJankEffect);
            if (effectLevel > 0 && player.level() instanceof ServerLevel level) {
                BlockPos target = player.getOnPos();
                Entity entity = player;
                // 1. 搜索附近的物品实体：
                List<ItemEntity> items = level.getEntities(EntityType.ITEM, new AABB(target).inflate(effectLevel * 0.5), Entity::isAlive);
                // 3. 处理搜寻到的物品实体：
                //    - `items.forEach(item -> { ... })`: 遍历列表中的每一个 `item`（物品实体）。
                items.forEach(item -> {
                    level.sendParticles(ParticleTypes.REVERSE_PORTAL, item.getX() + item.getBbWidth() / 2, item.getY() + item.getBbHeight() / 2,
                            item.getZ() + item.getBbWidth() / 2, 1, 0, 0, 0, 0);
                    item.moveTo(entity.getPosition(0));
                    item.setPickUpDelay(0);
                });
                level.getEntities(EntityType.EXPERIENCE_ORB, new AABB(target).inflate(effectLevel * 0.5), Entity::isAlive).forEach(orb -> orb.moveTo(entity.getPosition(0)));
            }
        }
}
