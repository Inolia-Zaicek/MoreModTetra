package com.inolia_zaicek.more_mod_tetra.Network.Packet;

import com.github.alexthe666.iceandfire.entity.EntityGhostSword;
import com.github.alexthe666.iceandfire.entity.IafEntityRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GhostSwordPacket {
    private int nEffectLevel; // 添加一个字段来存储 effectLevel
    private int ubwEffectLevel;
        // 添加一个构造函数，接受 int 类型的 effectLevel
        public GhostSwordPacket(int nEffectLevel,int ubwEffectLevel) {
            this.nEffectLevel = nEffectLevel;
            this.ubwEffectLevel = ubwEffectLevel;
        }
    public static boolean handle(GhostSwordPacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        if(ModList.get().isLoaded("iceandfire")) {
            //获取上下文
            var context = ctxSupplier.get();
            context.enqueueWork(() -> {
                //要做什么
                var player = context.getSender();
                ItemStack mainHandItem = player.getMainHandItem();
                int effectLevel=msg.nEffectLevel;
                int ubwEffectLevel=msg.ubwEffectLevel;
                //获取攻击力
                double power = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                //获取攻击速度
                float speed = (float) player.getAttributeValue(Attributes.ATTACK_SPEED);
                // 播放一个音效，这里使用的是僵尸感染的音效，可能是为了营造一种诡异或特殊的氛围。
                player.playSound(SoundEvents.ZOMBIE_INFECT, 1, 1);
                //实体类型-世界-发射者-伤害值
                EntityGhostSword shot = new EntityGhostSword(IafEntityRegistry.GHOST_SWORD.get(), player.level(), player,
                        power*( (double) effectLevel /100 ) );
                // - 0.5f: 实体的前进速度的“散布”或“随机性”（通常是0以实现精确发射，这里的值可能控制发射的精确度或速度衰减）。
                // 设置 Ghost Sword 的发射方向和速度---getXY获取玩家看的角度---偏移---速度---散射
                if(ubwEffectLevel>1) {
                    shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1+(float) effectLevel /600 +speed/3
                            , Math.max(0,0.5f - (float) effectLevel /300) );
                }else{
                    shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1+(float) effectLevel /600
                            , Math.max(0,0.5f - (float) effectLevel /300) );
                }
                // 将创建的 Ghost Sword 实体添加到世界中。addFreshEntity() 方法会负责将实体同步到客户端（如果是在服务器端执行）。
                player.level().addFreshEntity(shot);
                // - 1: 物品损失1点耐久度。
                mainHandItem.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                //攻速收益的冷却缩减
                int cooldown = (int) Math.min(12, (speed*4) );
                // 为该物品设置冷却时间。
                if(ubwEffectLevel>1) {
                    player.getCooldowns().addCooldown(mainHandItem.getItem(), 20 - cooldown);
                }else{
                    player.getCooldowns().addCooldown(mainHandItem.getItem(), 20);
                }
            });
            context.setPacketHandled(true);
        }
            return true;
        }
        // 修改 encode 方法，将 effectLevel 写入 buf
        public static void encode (GhostSwordPacket packet, FriendlyByteBuf buf){
            buf.writeInt(packet.nEffectLevel);
            buf.writeInt(packet.ubwEffectLevel);
        }
        public static GhostSwordPacket decode (FriendlyByteBuf buf){
            if(ModList.get().isLoaded("iceandfire")) {
                return new GhostSwordPacket(buf.readInt(),buf.readInt());
            }
            return null;
        }

}