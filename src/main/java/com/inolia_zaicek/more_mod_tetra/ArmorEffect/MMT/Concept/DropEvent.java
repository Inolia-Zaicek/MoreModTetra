package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.concept_of_substance_Effect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class DropEvent {
    @SubscribeEvent
    public static void entityKilled(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            if (event.getSource().getEntity() instanceof LivingEntity attacker && !EntityType.getKey(event.getEntity().getType()).toString().equals("goety:obsidian_monolith")) {
                int number = 0;
                //物质
                number += (int) (MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, concept_of_substance_Effect) * 1);
                if (number > 0) {
                    for (int i = 0; i < number; i++) {
                        Level level = attacker.level();
                        LootTable loot = ((MinecraftServer) Objects.requireNonNull(level.getServer())).getLootData().getLootTable(event.getEntity().getType().getDefaultLootTable());
                        LootParams context = (new LootParams.Builder((ServerLevel) level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(event.getEntity().blockPosition())).withParameter(LootContextParams.THIS_ENTITY, event.getEntity()).withParameter(LootContextParams.DAMAGE_SOURCE, attacker.damageSources().playerAttack((Player) attacker)).create(LootContextParamSets.ENTITY);
                        List<ItemStack> drops = loot.getRandomItems(context);
                        for (ItemStack drop : drops) {
                            ItemEntity itementity = new ItemEntity(level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), drop);
                            itementity.setDefaultPickUpDelay();
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F), (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                    }
                }

            }
        }
    }
}
