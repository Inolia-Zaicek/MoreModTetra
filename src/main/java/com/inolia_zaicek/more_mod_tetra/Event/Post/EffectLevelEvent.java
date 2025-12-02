package com.inolia_zaicek.more_mod_tetra.Event.Post;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.ArrayList;
import java.util.List;

@Event.HasResult
public class EffectLevelEvent extends Event {
    //固定增减
    private float fixedDamage = 0.0f;
    private float normalMulti = 0.0f;
    //怎么工作的知道吗上面两个计算完后*它，如果小于1就是减，大于1增，不是吧
    private List<Float> independentMulti = new ArrayList<>();
    private final LivingEntity attacker;
    private final LivingEntity target;
    public final LivingHurtEvent hurtEvent;
    public EffectLevelEvent(LivingEntity attacker, LivingEntity target, LivingHurtEvent hurtEvent) {
        this.attacker = attacker;
        this.target = target;
        this.hurtEvent = hurtEvent;
    }

    public float getFixedDamage() {
        return fixedDamage;
    }

    public void setFixedDamage(float fixedDamage) {
        this.fixedDamage = fixedDamage;
    }

    public float getNormalMulti() {
        return normalMulti;
    }

    public void setNormalMulti(float normalMulti) {
        this.normalMulti = normalMulti;
    }

    public void addNormalMulti(float normalMulti) {
        this.normalMulti += normalMulti;
    }

    public List<Float> getIndependentMulti() {
        return List.copyOf(independentMulti);
    }

    public void addIndependentMulti(float independentMulti) {
        this.independentMulti.add(independentMulti);
    }

    public LivingEntity getTarget() {
        return target;
    }

    public LivingEntity getAttacker() {
        return attacker;
    }
}
