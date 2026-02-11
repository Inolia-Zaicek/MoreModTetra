package com.inolia_zaicek.more_mod_tetra.ArmorEffect;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class WhiteAttack {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (event.getAttacker() != null&&event.getAttacked()!=null) {
            var mob = event.getAttacked();
            var map = mob.getActiveEffectsMap();
            LivingEntity attacker = event.getAttacker();
            int chance = 0;
            float time = 20;
            //剑鞘
            if(MMTDamageSourceHelper.isMeleeAttack(event.hurtEvent.getSource())) {
                chance += MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(attacker, white_scabbard_Effect);
                time += MMTCuriosHelper.getInstance().getCuriosEffectMaxEfficiency(attacker, white_scabbard_Effect) * 20;
            }
            //箭袋
            else if (event.hurtEvent.getSource().is(DamageTypes.ARROW)) {
                chance += MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(attacker, white_quiver_Effect);
                time += MMTCuriosHelper.getInstance().getCuriosEffectMaxEfficiency(attacker, white_quiver_Effect) * 20;
            }
            //书袋
            else{
                chance += MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(attacker, white_bag_Effect);
                time += MMTCuriosHelper.getInstance().getCuriosEffectMaxEfficiency(attacker, white_bag_Effect) * 20;
            }
            Random random = new Random();
            if(chance>0&&random.nextInt(101) <= chance*1.1F){
                //原版
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_minecraft_weakness_attack_Effect)) {
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                            "minecraft", "weakness"))), (int) time, 0));
                    if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "weakness"))))) {
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "weakness")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("minecraft", "weakness"))), (int) time, 0));
                    }
                }
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_minecraft_poison_attack_Effect)) {
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                            "minecraft", "poison"))), (int) time, 0));
                    if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "poison"))))) {
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "poison")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("minecraft", "poison"))), (int) time, 0));
                    }
                }
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_minecraft_levitation_attack_Effect)) {
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                            "minecraft", "levitation"))), (int) time, 0));
                    if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "levitation"))))) {
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "levitation")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("minecraft", "levitation"))), (int) time, 0));
                    }
                }
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_minecraft_slowness_attack_Effect)) {
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                            "minecraft", "slowness"))), (int) time, 0));
                    if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "slowness"))))) {
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "slowness")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("minecraft", "slowness"))), (int) time, 0));
                    }
                }
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_minecraft_wither_attack_Effect)) {
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                            "minecraft", "wither"))), (int) time, 0));
                    if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "wither"))))) {
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "wither")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("minecraft", "wither"))), (int) time, 0));
                    }
                }
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_minecraft_glowing_attack_Effect)) {
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                            "minecraft", "glowing"))), (int) time, 0));
                    if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "glowing"))))) {
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "glowing")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("minecraft", "glowing"))), (int) time, 0));
                    }
                }
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_minecraft_blindness_attack_Effect)) {
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                            "minecraft", "blindness"))), (int) time, 0));
                    if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "blindness"))))) {
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("minecraft", "blindness")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("minecraft", "blindness"))), (int) time, 0));
                    }
                }
                //诡厄
                if (ModList.get().isLoaded("goety")) {
                    //
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_cursed_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "cursed"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "cursed"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_freezing_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "freezing"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "freezing"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "freezing")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "freezing"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_sapped_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "sapped"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "sapped"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "sapped")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "sapped"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_wane_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "wane"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "wane"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "wane")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "wane"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_busted_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "busted"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "busted"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "busted")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "busted"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_ender_ground_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "ender_ground"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "ender_ground"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "ender_ground")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "ender_ground"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_plunge_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "plunge"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "plunge"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "plunge")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "plunge"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_flammable_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "flammable"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "flammable"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "flammable")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "flammable"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_wild_rage_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "wild_rage"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "wild_rage"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "wild_rage")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "wild_rage"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_acid_venom_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "acid_venom"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "acid_venom"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "acid_venom")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "acid_venom"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_tangled_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "tangled"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "tangled"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "tangled")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "tangled"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_goety_void_touched_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "goety", "void_touched"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "void_touched"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "void_touched")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("goety", "void_touched"))), (int) time, 0));
                        }
                    }
                    //
                }
                //铁魔法
                if (ModList.get().isLoaded("irons_spellbooks")) {
                    //破甲
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_3_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 2));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 2));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_4_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 3));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 3));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_5_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 4));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 4));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_6_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 5));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 5));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_7_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 6));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 6));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_8_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 7));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 7));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_9_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 8));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 8));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_10_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 9));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 9));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_11_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 10));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 10));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_12_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 11));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 11));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_rend_13_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "rend"))), (int) time, 12));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "rend"))), (int) time, 12));
                        }
                    }
                    //枯萎
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_blight_1_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "blight"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_blight_2_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "blight"))), (int) time, 1));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))), (int) time, 1));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_blight_3_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "blight"))), (int) time, 2));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))), (int) time, 2));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_blight_4_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "blight"))), (int) time, 3));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))), (int) time, 3));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_blight_5_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "blight"))), (int) time, 4));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))), (int) time, 4));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_blight_6_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "blight"))), (int) time, 5));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))), (int) time, 5));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_blight_7_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "blight"))), (int) time, 6));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))), (int) time, 6));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_irons_spellbooks_blight_8_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "irons_spellbooks", "blight"))), (int) time, 7));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))),
                                    new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "blight"))), (int) time, 7));
                        }
                    }
                    //
                }
                //神化（属性
                if (ModList.get().isLoaded("attributeslib")) {
                    //
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_attributeslib_bleeding_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "attributeslib", "bleeding"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "bleeding"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "bleeding")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("attributeslib", "bleeding"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_attributeslib_sundering_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "attributeslib", "sundering"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "sundering"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "sundering")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("attributeslib", "sundering"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_attributeslib_grievous_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "attributeslib", "grievous"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "grievous"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "grievous")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("attributeslib", "grievous"))), (int) time, 0));
                        }
                    }
                    //
                }
                //灾变
                if (ModList.get().isLoaded("cataclysm")) {
                    //
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_cataclysm_blazing_brand_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "cataclysm", "blazing_brand"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "blazing_brand"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "blazing_brand")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("cataclysm", "blazing_brand"))), (int) time, 0));
                        }
                    }
                    if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(attacker, white_cataclysm_abyssal_curse_attack_Effect)) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(
                                "cataclysm", "abyssal_curse"))), (int) time, 0));
                        if (!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "abyssal_curse"))))) {
                            map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "abyssal_curse")))
                                    , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                            new ResourceLocation("cataclysm", "abyssal_curse"))), (int) time, 0));
                        }
                    }
                    //
                }
            }
        }
    }
}
