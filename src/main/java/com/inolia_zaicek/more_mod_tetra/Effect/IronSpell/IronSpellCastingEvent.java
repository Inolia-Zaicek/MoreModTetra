package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.TetraRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.TetraItem;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister.iron_spell_casting;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class IronSpellCastingEvent {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(ironSpellCastingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ironSpellCastingName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ironSpellCastingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void use(PlayerInteractEvent.RightClickItem event) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            LivingEntity entity = event.getEntity();
            ItemStack itemStack = event.getItemStack();
            // 检查实体是否是玩家
            if (!(entity instanceof Player)) {
                return;
            }
            Player player = (Player) entity;
            //判断tetra工具
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            if (mainHandItem.getItem() instanceof IModularItem || offhandItem.getItem() instanceof IModularItem) {
                if (!mainHandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))
                        && !offhandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))) {
                    //获取法术选择
                    SpellSelectionManager spellSelectionManager = new SpellSelectionManager(player);
                    SpellSelectionManager.SelectionOption selectionOption = spellSelectionManager.getSelection();
                    //如果没有选中法术，或者选中的是空的法术数据，则不让事件继续
                    if (selectionOption == null || selectionOption.spellData.equals(SpellData.EMPTY)) {
                        return;
                    }
                    SpellData spellData = selectionOption.spellData;
                    if (player.level().isClientSide()) {
                        // 客户端逻辑
                        if (ClientMagicData.isCasting()) {
                            // 如果客户端已经正在施法，取消这次使用事件，防止重复操作
                            event.setCanceled(true);
                            return;
                        } else {
                            // 检查法力、冷却、是否学会
                            if (ClientMagicData.getPlayerMana() < spellData.getSpell().getManaCost(spellData.getLevel())
                                    || ClientMagicData.getCooldowns().isOnCooldown(spellData.getSpell())
                                    || !ClientMagicData.getSyncedSpellData(player).isSpellLearned(spellData.getSpell())) {
                                return;
                            } else {
                                event.setCanceled(true); // 取消事件，阻止默认的物品使用
                                return;
                            }
                        }
                    }
                    //主手有tag，主手施法
                    if (player.getMainHandItem().is(iron_spell_casting)) {
                        String castingSlot = SpellSelectionManager.MAINHAND;
                        if (spellData.getSpell().attemptInitiateCast(itemStack, spellData.getLevel(), player.level(), player, selectionOption.getCastSource(), true, castingSlot)) {
                            event.setCanceled(true); // 取消默认的物品使用过程
                        }
                    } else if (player.getOffhandItem().is(iron_spell_casting)) {
                        String castingSlot = SpellSelectionManager.OFFHAND;
                        if (spellData.getSpell().attemptInitiateCast(itemStack, spellData.getLevel(), player.level(), player, selectionOption.getCastSource(), true, castingSlot)) {
                            event.setCanceled(true); // 取消默认的物品使用过程
                        }
                    }
                }
            }
        }
    }
}