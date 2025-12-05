package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister.iron_spell_casting;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class IronSpellCastingEvent {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        // ... (UI相关代码，保持不变)
        var statGetter = new StatGetterEffectLevel(ironSpellCastingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ironSpellCastingName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ironSpellCastingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void use(PlayerInteractEvent.RightClickItem event) {
        // 1. 检查 irons_spellbooks 模组是否加载
        if (!ModList.get().isLoaded("irons_spellbooks")) {
            return;
        }

        LivingEntity entity = event.getEntity();
        ItemStack clickedItem = event.getItemStack(); // 玩家右键点击的物品
        InteractionHand hand = event.getHand();     // 玩家进行交互的手

        // 2. 确保实体是玩家
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player) entity;

        // ----------------------------------------------------------------------
        // 核心施法逻辑 - 仅当玩家手持的物品满足特定条件时触发
        // ----------------------------------------------------------------------
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offhandItem = player.getOffhandItem();

        ItemStack activeItem = null; // 用于施法的实际物品
        String castingSlot = null; // 施法槽

        boolean canCast = false; // 标记是否满足施法条件

        // 检查主手物品
        if (mainHandItem.getItem() instanceof IModularItem mainModularItem) {
            float mainEffectLevel = mainModularItem.getEffectLevel(mainHandItem, ironSpellCastingEffect);
            if (mainEffectLevel > 0) {
                // 如果是 IModularItem 并且 ironSpellCastingEffect 等级大于0
                if (hand == InteractionHand.MAIN_HAND) {
                    activeItem = mainHandItem;
                    castingSlot = SpellSelectionManager.MAINHAND;
                    canCast = true;
                }
            }
        }
        // 如果主手不满足，或者不是主手交互，则检查副手
        if (!canCast) {
            if (offhandItem.getItem() instanceof IModularItem offhandModularItem) {
                float offhandEffectLevel = offhandModularItem.getEffectLevel(offhandItem, ironSpellCastingEffect);
                if (offhandEffectLevel > 0) {
                    // 如果是 IModularItem 并且 ironSpellCastingEffect 等级大于0
                    if (hand == InteractionHand.OFF_HAND) {
                        activeItem = offhandItem;
                        castingSlot = SpellSelectionManager.OFFHAND;
                        canCast = true;
                    }
                }
            }
        }

        // 另外，也保留原有的 `iron_spell_casting` 物品直接判断逻辑
        boolean isMainHandIronSpellCasting = mainHandItem.is(iron_spell_casting);
        boolean isOffhandIronSpellCasting = offhandItem.is(iron_spell_casting);

        if (!canCast) { // 如果通过 IModularItem 词条等级判断未满足，则检查是否是 iron_spell_casting 物品
            if (isMainHandIronSpellCasting && hand == InteractionHand.MAIN_HAND) {
                activeItem = mainHandItem;
                castingSlot = SpellSelectionManager.MAINHAND;
                canCast = true;
            } else if (isOffhandIronSpellCasting && hand == InteractionHand.OFF_HAND) {
                activeItem = offhandItem;
                castingSlot = SpellSelectionManager.OFFHAND;
                canCast = true;
            }
        } else { // 如果通过 IModularItem 词条等级判断已满足，并且是主手交互，那么 activeItem 和 castingSlot 已经设置好了
            // 但我们需要确保，如果玩家右键点击的物品不是 activeItem，则不进行处理
            if (hand == InteractionHand.MAIN_HAND && !mainHandItem.is(activeItem.getItem())) {
                return;
            } else if (hand == InteractionHand.OFF_HAND && !offhandItem.is(activeItem.getItem())) {
                return;
            }
        }


        // 如果以上所有条件都不满足，则不进行处理
        if (!canCast || activeItem == null) {
            return;
        }

        // ----------------------------------------------------------------------
        // 获取法术选择 (参考 kamen_tinker 逻辑)
        // ----------------------------------------------------------------------
        SpellSelectionManager spellSelectionManager = new SpellSelectionManager(player);
        SpellSelectionManager.SelectionOption selectionOption = spellSelectionManager.getSelection();

        // 如果没有选中法术，或者选中的是空的法术数据，则不进行施法
        if (selectionOption == null || selectionOption.spellData.equals(SpellData.EMPTY)) {
            // 这里不取消事件，让原有的物品使用行为发生（如果它有的话）
            return;
        }
        SpellData spellData = selectionOption.spellData;

        // ----------------------------------------------------------------------
        // 客户端逻辑 (参考 kamen_tinker 逻辑)
        // ----------------------------------------------------------------------
        if (player.level().isClientSide()) {
            if (ClientMagicData.isCasting()) {
                // 如果客户端已经正在施法，取消这次使用事件，防止重复操作
                event.setCanceled(true);
                return;
            } else if (ClientMagicData.getPlayerMana() < spellData.getSpell().getManaCost(spellData.getLevel())
                    || ClientMagicData.getCooldowns().isOnCooldown(spellData.getSpell())
                    || !ClientMagicData.getSyncedSpellData(player).isSpellLearned(spellData.getSpell())) {
                // 资源不足或其他原因，不进行施法
                // 这里不取消事件，让原有的物品使用行为发生（如果它有的话）
                return;
            } else {
                // 客户端准备施法，取消事件，阻止默认物品使用，交由 attemptInitiateCast 处理
                event.setCanceled(true);
                return;
            }
        }
        // ----------------------------------------------------------------------
        // 服务器端逻辑 (参考 kamen_tinker 逻辑)
        // ----------------------------------------------------------------------
        else {
            // 尝试施法
            // activeItem 就是玩家手持的，满足条件的物品
            if (spellData.getSpell().attemptInitiateCast(activeItem, spellData.getLevel(), player.level(), player, selectionOption.getCastSource(), true, castingSlot)) {
                //如果是tetra物品，提升完整度
                if (activeItem.getItem() instanceof IModularItem ModularItem) {
                    ModularItem.tickProgression(player, activeItem, 10);
                }
                event.setCanceled(true); // 取消默认的物品使用过程
            } else {
                // 施法失败 (例如服务器端也做了检查，但失败了)
                event.setCanceled(true); // 依然取消默认物品使用，避免奇怪行为
            }
        }
    }
}