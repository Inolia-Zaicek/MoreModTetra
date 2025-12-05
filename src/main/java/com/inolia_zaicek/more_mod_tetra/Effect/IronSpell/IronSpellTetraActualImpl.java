package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell;

import com.inolia_zaicek.more_mod_tetra.Effect.MMTITetraProxy;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.compat.tetra.StatGetterPercentAttribute;
import io.redspace.ironsspellbooks.compat.tetra.effects.FreezeTetraEffect;
import io.redspace.ironsspellbooks.compat.tetra.effects.ManaSiphonTetraEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterPercentage;
import se.mickelus.tetra.items.modular.impl.ModularBladedItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

public class IronSpellTetraActualImpl implements MMTITetraProxy {

    @OnlyIn(Dist.CLIENT)
    public static void initClient() {

        FreezeTetraEffect.addGuiBars();
        ManaSiphonTetraEffect.addGuiBars();

        createPercentAttributeBar(AttributeRegistry.SUMMON_DAMAGE.get(), "summon_damage");
        createPercentAttributeBar(AttributeRegistry.MANA_REGEN.get(), "mana_regen");
        createPercentAttributeBar(AttributeRegistry.CAST_TIME_REDUCTION.get(), "cast_time_reduction");
        createPercentAttributeBar(AttributeRegistry.FIRE_MAGIC_RESIST.get(), "fire_magic_resist");
        createPercentAttributeBar(AttributeRegistry.ICE_MAGIC_RESIST.get(), "ice_magic_resist");
        createPercentAttributeBar(AttributeRegistry.LIGHTNING_MAGIC_RESIST.get(), "lightning_magic_resist");
        createPercentAttributeBar(AttributeRegistry.HOLY_MAGIC_RESIST.get(), "holy_magic_resist");
        createPercentAttributeBar(AttributeRegistry.ENDER_MAGIC_RESIST.get(), "ender_magic_resist");
        createPercentAttributeBar(AttributeRegistry.BLOOD_MAGIC_RESIST.get(), "blood_magic_resist");
        createPercentAttributeBar(AttributeRegistry.EVOCATION_MAGIC_RESIST.get(), "evocation_magic_resist");
        createPercentAttributeBar(AttributeRegistry.NATURE_MAGIC_RESIST.get(), "nature_magic_resist");
        createPercentAttributeBar(AttributeRegistry.ELDRITCH_MAGIC_RESIST.get(), "eldritch_magic_resist");
        createPercentAttributeBar(AttributeRegistry.SPELL_POWER.get(), "spell_power");
        createPercentAttributeBar(AttributeRegistry.ELDRITCH_SPELL_POWER.get(), "eldritch_spell_power");
    }

    @Override
    public boolean canImbue(ItemStack itemStack) {
        if (itemStack.getItem() instanceof ModularBladedItem) {
            return true;
        }
        return false;
    }

    @Override
    public void handleLivingAttackEvent(LivingAttackEvent event) {
        if (!event.getEntity().level().isClientSide) {
            FreezeTetraEffect.handleLivingAttackEvent(event);
            ManaSiphonTetraEffect.handleLivingAttackEvent(event);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void createPercentAttributeBar(Attribute attribute, String languageKey) {
        IStatGetter statGetter = new StatGetterPercentAttribute(attribute);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength, attribute.getDescriptionId(), 0, 100, false,
                statGetter,
                LabelGetterBasic.percentageLabel,
                new TooltipGetterPercentage(MoreModTetra.MODID + ".tetra_bar." + languageKey + ".tooltip", statGetter));
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
}