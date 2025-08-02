package com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire;

import com.github.alexthe666.iceandfire.entity.EntityGhostSword; // 导入 Ghost Sword 实体的类
import com.github.alexthe666.iceandfire.entity.IafEntityRegistry; // 导入游戏实体注册表，用于获取 Ghost Sword 实体类型
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.google.common.collect.Multimap; // 用于处理属性集合
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Network.GhostSwordChannel;
import com.inolia_zaicek.more_mod_tetra.Network.Packet.GhostSwordPacket;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.ChatFormatting; // 用于文本颜色格式化
import net.minecraft.network.chat.Component; // 用于 Minecraft 的文本组件
import net.minecraft.sounds.SoundEvents; // 游戏内音效事件
import net.minecraft.world.InteractionHand; // 玩家交互手（主手/副手）
import net.minecraft.world.entity.EquipmentSlot; // 装备槽位
import net.minecraft.world.entity.LivingEntity; // 任何会受伤和死亡的实体
import net.minecraft.world.entity.ai.attributes.Attribute; // 实体属性（如攻击力）
import net.minecraft.world.entity.ai.attributes.AttributeModifier; // 属性修改器
import net.minecraft.world.entity.ai.attributes.Attributes; // Minecraft 内置的常见属性
import net.minecraft.world.entity.player.Player; // 玩家实体
import net.minecraft.world.item.Item; // Minecraft 物品基类
import net.minecraft.world.item.ItemStack; // Minecraft 物品堆（一个或多个相同物品的集合）
import net.minecraft.world.item.SwordItem; // 剑物品基类
import net.minecraft.world.item.TooltipFlag; // 物品提示信息标志
import net.minecraft.world.level.Level; // Minecraft 世界（服务器端/客户端）
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull; // JetBrains 注解，表示该参数或返回值不应为 null
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import javax.annotation.Nullable; // JSR 305 注解，表示参数或返回值可以为 null
import java.util.List; // 列表接口

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class GhostSword {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(ghostSwordEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ghostSwordName, 0, 5, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ghostSwordTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(PlayerInteractEvent.LeftClickEmpty event) {
        if(ModList.get().isLoaded("iceandfire")) {
            var playerEntity = event.getEntity();
            ItemStack mainHandItem = playerEntity.getMainHandItem();
            int nEffectLevel = 0;
            int ubwEffectLevel = 1;
            if (mainHandItem.getItem() instanceof ModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, ghostSwordEffect);
                if (mainEffectLevel > 0) {
                    nEffectLevel += (int) mainEffectLevel;
                }
            }
            if (mainHandItem.getItem() instanceof ModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, unlimitedPhantasmalBladeWorksEffect);
                if (mainEffectLevel > 0) {
                    ubwEffectLevel += (int) mainEffectLevel;
                }
            }
            //基础等级50
            if (nEffectLevel > 0) {
                // 如果在冷却中，则退出方法，不执行任何操作。
                if (playerEntity.getCooldowns().isOnCooldown(mainHandItem.getItem()))
                    return;
                //发包
                    GhostSwordChannel.CHANNEL.sendToServer(new GhostSwordPacket(nEffectLevel, ubwEffectLevel));

            }
        }
    }

}
