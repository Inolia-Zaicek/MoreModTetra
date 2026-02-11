package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell;

import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimal;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

public class MMTFreezeTetraEffect {
    public static final ItemEffect freezeOnHit = ItemEffect.get("irons_spellbooks:freeze");
    public static final String freezeName = "irons_spellbooks.tetra_effect.freeze";
    public static final String freezeTooltip = "irons_spellbooks.tetra_effect.freeze.tooltip";

    @OnlyIn(Dist.CLIENT)
    public static void addGuiBars() {
        IStatGetter effectStatGetter = new StatGetterEffectLevel(freezeOnHit, (double)1.0F);
        GuiStatBar effectBar = new GuiStatBar(0, 0, 59, "irons_spellbooks.tetra_effect.freeze", (double)0.0F, (double)30.0F, false, effectStatGetter, LabelGetterBasic.decimalLabel, new TooltipGetterDecimal("irons_spellbooks.tetra_effect.freeze.tooltip", effectStatGetter));
        WorkbenchStatsGui.addBar(effectBar);
        HoloStatsGui.addBar(effectBar);
    }
    public static void handleLivingAttackEvent(LivingAttackEvent event) {
        LivingEntity attackedEntity = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();
        if (attacker instanceof LivingEntity livingAttacker) {
            ItemStack heldStack = livingAttacker.getMainHandItem();
            Item var7 = heldStack.getItem();
            if (var7 instanceof ModularItem item) {
                int level = item.getEffectLevel(heldStack, freezeOnHit);
                if (level > 0) {
                    if (attackedEntity.canFreeze()) {
                        attackedEntity.setTicksFrozen(attackedEntity.getTicksFrozen() + level * 20);
                    }

                    MagicManager.spawnParticles(attackedEntity.level, ParticleHelper.SNOWFLAKE, attackedEntity.getX(), attackedEntity.getY() + (double)(attackedEntity.getBbHeight() * 0.5F), attackedEntity.getZ(), 10, (double)(attackedEntity.getBbWidth() * 0.5F), (double)(attackedEntity.getBbHeight() * 0.5F), (double)(attackedEntity.getBbWidth() * 0.5F), 0.03, false);
                }
            }
        }

    }
}
