package com.inolia_zaicek.more_mod_tetra.Client; // 声明该类所属的包。这个包路径表明它是 More Mod Tetra 模组客户端的某个部分。

// 导入必要的类
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.CuriosFly; // 导入与飞行效果相关的 Curios 类
import com.inolia_zaicek.more_mod_tetra.MoreModTetra; // 导入主模组类，用于获取模组 ID
import com.inolia_zaicek.more_mod_tetra.Network.Packet.MMTPlayerFlyPacketS2C; // 导入用于接收服务器发送的飞行状态数据包的客户端类
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft; // 导入 Minecraft 客户端实例类，用于访问游戏客户端
import net.minecraft.client.player.LocalPlayer; // 导入本地玩家类，代表当前在客户端控制的玩家
import net.minecraft.core.Direction.Axis; // 导入方向枚举类，这里用于访问 Y 轴（垂直方向）
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Abilities; // 导入玩家能力类，用于修改玩家的飞行、创造模式等能力
import net.minecraftforge.client.event.InputEvent; // 导入 Forge 客户端输入事件类，用于监听键盘和鼠标输入
import net.minecraftforge.event.TickEvent; // 导入 Forge 的 Tick 事件类，用于在游戏 tick 时执行逻辑
import net.minecraftforge.eventbus.api.SubscribeEvent; // 导入订阅事件注解，用于标记方法为事件监听器
import net.minecraftforge.fml.common.Mod; // 导入 Forge 的 Mod 注解，用于标记类为一个模组
import net.minecraftforge.network.NetworkEvent; // 导入 Forge 网络事件类，用于处理网络通信

import java.util.function.Supplier; // 导入 Supplier 接口，用于延迟计算或提供值

// 使用 Forge 的 Mod 注解，将此类标记为一个模组的事件订阅者。
// modid = MoreModTetra.MODID 指定该订阅者属于哪个模组。
// bus = Mod.EventBusSubscriber.Bus.FORGE 表示该订阅者监听的是 Forge 的事件总线。
@Mod.EventBusSubscriber(modid = MoreModTetra.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MMTFlightHandler { // 定义一个名为 MMTFlightHandler 的公共类，用于处理模组中的飞行相关逻辑。

    // 静态变量，用于存储飞行相关状态。静态变量的生命周期与类相同，可以在模组中全局访问。
    private static int maxFlightTicks = 0; // 最大允许的飞行时间（以 tick 为单位）
    private static double flightSpeed = 0.0; // 玩家飞行时的移动速度
    private static int remainingFlightTicks = 0; // 当前剩余的飞行时间（以 tick 为单位）
    private static boolean flightAbilityActive = false; // 标记你的模组提供的飞行能力是否当前激活
    private static boolean playerIsFlying = false; // 标记玩家当前是否处于飞行状态（由你的模组控制）

    private static boolean spacebarPressed = false; // 追踪空格键是否被按下。用于触发飞行。

    // 这是一个网络消息处理函数，当客户端收到服务器发送的 MMTPlayerFlyPacketS2C 数据包时会被调用。
    // packet: 接收到的飞行数据包对象。
    // ctx: 网络事件的上下文，用于处理消息的确认和响应。
    public static void handleFlyPacket(MMTPlayerFlyPacketS2C packet, Supplier<NetworkEvent.Context> ctx) {
        maxFlightTicks = packet.maxFlyTicks(); // 从数据包中获取最大的飞行时间
        flightSpeed = packet.flySpeed(); // 从数据包中获取飞行速度

        // 如果最大飞行时间大于 0，说明服务器授予了你的模组的飞行能力
        if (maxFlightTicks > 0) {
            flightAbilityActive = true; // 激活你的模组提供的飞行能力
            // 首次获取飞行能力时，如果剩余时间为0，则将其设置为最大值
            if (remainingFlightTicks == 0) {
                remainingFlightTicks = maxFlightTicks;
            }
            // 确保剩余时间不会超过新获取的最大值（例如，如果最大值减少了）
            else if (remainingFlightTicks > maxFlightTicks) {
                remainingFlightTicks = maxFlightTicks;
            }
        } else { // 如果最大飞行时间小于等于 0，说明你的模组的飞行能力被禁用
            flightAbilityActive = false; // 禁用你的模组提供的飞行能力
            remainingFlightTicks = 0; // 重置剩余飞行时间
            // 当饰品卸下时（通常导致 maxFlyTicks 变为 0），需要确保玩家的飞行能力被正确处理。
            Minecraft mc = Minecraft.getInstance(); // 获取 Minecraft 客户端实例
            if (mc.player != null) { // 检查当前是否有玩家登录
                Abilities abilities = mc.player.getAbilities(); // 获取玩家的能力对象
                // !!! 修改逻辑： !!!
                //  当你的模组提供的飞行能力被禁用时，我们只在以下情况进行干预：
                //  1. 玩家不是创造模式或旁观者模式。
                //  2. 并且，如果玩家当前正处于飞行状态 (`abilities.flying` is true)，
                //     那么强制停止它 (`abilities.flying = false`)。
                //  !!! 我们不再强制设置 `abilities.mayfly = false` 或 `setFlyingSpeed` !!!
                //  这是为了不干扰其他模组可能提供的飞行能力。
                if (!mc.player.isCreative() && !mc.player.isSpectator()) {
                    if (abilities.flying) { // 如果玩家当前正在飞行
                        abilities.flying = false; // 强制玩家停止飞行
                        // !!! 移除对 `mayfly` 和 `setFlyingSpeed` 的直接修改 !!!
                        // mc.player.onUpdateAbilities(); // 只有在 `abilities` 被修改时才调用
                    }
                }
            }
        }
        // 标记消息已处理，并为客户端准备好响应
        ctx.get().setPacketHandled(true);
    }

    // 使用 SubscribeEvent 注解，标记此方法为 Forge 事件的监听器。
    // 该方法将在每个客户端 tick 的结束时被调用。
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        // 仅在 tick 事件的结束阶段执行逻辑
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = Minecraft.getInstance(); // 获取 Minecraft 客户端实例
            LocalPlayer player = mc.player; // 获取当前客户端的玩家对象

            // 如果没有玩家或游戏已暂停，则不执行任何逻辑
            if (player == null || mc.isPaused()) {
                return;
            }

            Abilities abilities = player.getAbilities(); // 获取玩家的能力对象

            // 如果玩家处于创造模式或旁观者模式，则不干预其飞行能力。
            // 这些模式下，玩家应该拥有完全的飞行控制权。
            if (player.isCreative() || player.isSpectator()) {
                // 在创造/旁观模式下，飞行能力应该由游戏内部管理，我们不干预。
                return; // 结束此 tick 的处理，不执行后续的飞行逻辑
            }

            // --- 核心逻辑：判断当前玩家是否“被你的模组”赋予飞行能力 ---
            // `isOurFlightActive` 结合了 `flightAbilityActive` 和 `remainingFlightTicks`
            boolean isOurFlightActive = flightAbilityActive && remainingFlightTicks > 0;

            // --- 逻辑 1：如果你的模组主动赋予飞行能力 ---
            if (isOurFlightActive) {
                // --- 1.1 玩家滞空且未按空格 (或地面上，但仍然在充能) ---
                // `player.isFallFlying()` 检查玩家是否处于滞空状态（通常表示正在滑翔或由其他方式飞行）
                // `player.onGround()` 检查玩家是否在地面上
                boolean isPlayerFallingOrGliding = player.isFallFlying() || player.isFallFlying(); // 检查玩家是否在下落或滑翔
                boolean shouldPlayerBeFlyingBasedOnOurAbility = false;

                if (spacebarPressed&&!player.onGround()) {
                    // 如果按下空格键，并且滞空 我们的飞行能力激活，那么玩家应该飞行
                    shouldPlayerBeFlyingBasedOnOurAbility = true;
                } else {
                    // 如果没有按下空格键：
                    //   - 如果玩家正在滞空 (`isPlayerFallingOrGliding` is true)，
                    //     并且我们的飞行能力激活，但玩家没有按空格，
                    //     这暗示着玩家可能是由其他模组/原版开启了飞行，
                    //     并且你的模组应该允许这种飞行（而不强制关闭）。
                    //     但是，我们也需要处理“玩家主动松开空格后，应该停止喷气背包的喷射”。
                    //     在这种情况下，我们不应该让 `abilities.flying` 保持 `true`（除非由其他模组触发）。
                    //     重要的是：我们只根据 `spacebarPressed` 来控制 `abilities.flying`。
                    //
                    //   - 如果玩家在地面上 (`player.onGround()`) 并且正在尝试充能，
                    //     这里 `isPlayerFallingOrGliding` 可能为 false，但我们仍然允许飞行（如果空格按下）。
                    //     这主要是为了处理玩家在地面上按空格起飞的情况。
                }

                // --- 1.2 应用我们的飞行能力 ---
                if (shouldPlayerBeFlyingBasedOnOurAbility) {
                    abilities.mayfly = true; // 你的模组赋予玩家“可以飞行”的权限
                    abilities.setFlyingSpeed((float) flightSpeed); // 设置我们模组的飞行速度
                    abilities.flying = true; // 强制玩家处于飞行状态
                    playerIsFlying = true; // 标记我们正在控制玩家飞行

                    // 施加向上的推力，用于“喷气背包”效果
                    // 只有在玩家“正在飞行”并且 `spacebarPressed` 为 `true` 时，才给予向上速度
                    if (abilities.flying && spacebarPressed) {
                        // 确保给予的是一个向上的速度，而不是覆盖其他可能的 Y 轴速度
                        // 这里直接设置，假设 `flightSpeed` 是正值
                        player.setDeltaMovement(player.getDeltaMovement().with(Axis.Y, flightSpeed));
                    } else {
                        // 如果玩家 `abilities.flying` 为 true，但 `spacebarPressed` 是 false (例如，玩家在空中松开了空格)
                        // 并且 `isOurFlightActive` 仍然为 true，表示我们的飞行能力还在。
                        // 此时，玩家应该停止向上喷射，允许重力作用。
                        // !!! 这里的逻辑需要谨慎 !!!
                        // 如果玩家 `abilities.flying` 为 true，但 `spacebarPressed` 为 false，
                        // 并且 `isOurFlightActive` 为 true，那么我们的模组应该允许玩家下落。
                        // 所以，我们不应该强制 `abilities.flying` 保持 `true`。
                        // 而是，如果 `spacebarPressed` 为 `false`，我们应该允许 `abilities.flying` 变为 `false`。
                        // 并且，如果 `playerIsFlying` 标记为 `true`（表示之前我们控制它飞行），
                        // 那么应该停止喷射，允许其下落。
                        if (playerIsFlying && !spacebarPressed) {
                            abilities.flying = false; // 停止由我们控制的飞行状态
                            playerIsFlying = false; // 我们的控制结束
                            // 允许重力作用，但保留可能存在的其他 Y 轴速度（例如，下落速度）
                            player.setDeltaMovement(player.getDeltaMovement().with(Axis.Y, player.getDeltaMovement().y()));
                        }
                    }
                    remainingFlightTicks--; // 消耗飞行时间
                } else {
                    // --- 1.3 如果我们的模组激活，但玩家不应该飞行（例如，在空中松开空格） ---
                    if (playerIsFlying) { // 如果我们之前正在控制玩家飞行
                        abilities.flying = false; // 强制停止我们控制的飞行
                        playerIsFlying = false; // 标记我们的控制结束
                        // 允许重力作用，但保留可能存在的其他 Y 轴速度
                        player.setDeltaMovement(player.getDeltaMovement().with(Axis.Y, player.getDeltaMovement().y()));
                    }
                    // !!! 重要： !!!
                    //  如果 `isOurFlightActive` 为 `true`，但 `shouldPlayerBeFlyingBasedOnOurAbility` 为 `false`
                    //  （例如，玩家在空中松开空格），我们不应该去修改 `abilities.mayfly`。
                    //  `abilities.mayfly` 应该保持由其他模组或游戏原版控制的状态。
                }

                // 飞行充能逻辑：在地面上且我们的飞行能力激活时
                if (player.onGround() && isOurFlightActive) {
                    remainingFlightTicks += CuriosFly.RECHARGE_RATE_TICKS_PER_TICK;
                    if (remainingFlightTicks > maxFlightTicks) {
                        remainingFlightTicks = maxFlightTicks;
                    }
                }
            }
            // --- 逻辑 2：如果你的模组未提供飞行能力（时间耗尽或未激活） ---
            else {
                // --- 2.1 确保玩家的 `abilities` 处于正确状态 ---
                //  如果 `isOurFlightActive` 为 `false`，那么你的模组不应该再主动控制 `mayfly` 和 `flying`。
                //  关键是：我们需要区分玩家的 `abilities.mayfly` 是由你的模组设置的，还是其他来源。
                //  我们引入 `playerIsFlying` 来标记“是否由我们的模组正在主动控制玩家飞行”。

                //  如果 `playerIsFlying` 是 `true` (意味着我们之前正在控制玩家飞行，但现在 `isOurFlightActive` 变 `false`)
                //  那么意味着玩家应该停止由我们的模组触发的飞行。
                if (playerIsFlying) {
                    abilities.flying = false; // 强制停止我们控制的飞行
                    playerIsFlying = false; // 我们的控制结束

                    // !!! 重要 !!!
                    //  如果 `abilities.mayfly` 仍然是 `true`（可能是因为其他模组），
                    //  我们 **不** 应该将其设置为 `false`。
                    //  但是，我们需要重置速度。
                    //  如果 `abilities.mayfly` 也是 `false` (并且没有任何其他模组开启飞行)，
                    //  那么设置 `mayfly = false` 和 `flyingSpeed = 0.05F` 是安全的。

                    //  !!! 简化策略： !!!
                    //  当 `isOurFlightActive` 为 `false` 时，我们只处理 `abilities.flying`
                    //  并将速度重置为默认速度。
                    //  `abilities.mayfly` 的状态将完全由其他模组或游戏原版决定。
                    //  这避免了我们主动禁用其他模组的 `mayfly`。

                    abilities.setFlyingSpeed(0.05F); // 重置为默认行走速度
                    // !!! 仅当 `abilities.mayfly` 仍然是 `false` (且没有任何其他来源提供飞行) 时，才设置 `mayfly` 为 `false` !!!
                    // !!! 这是最棘手的部分，我们无法直接知道其他模组的状态。 !!!
                    // !!! 最安全的做法是：如果 `isOurFlightActive` 是 `false`，并且 `playerIsFlying` 是 `false` (即我们不控制飞行)
                    //     并且 `abilities.flying` 也是 `false`，那么我们就不去修改 `abilities.mayfly`。 !!!

                    // !!! 修正： !!!
                    //  当 `isOurFlightActive` 为 `false` 时，我们不主动去修改 `abilities.mayfly`。
                    //  `abilities.mayfly` 的状态将由游戏原版或其它模组管理。
                    //  我们只需要确保 `abilities.flying` 得到处理，以及速度是默认的。

                    // !!! 另一条思路： !!!
                    //  如果你发现 `abilities.mayfly` 在 `isOurFlightActive` 为 `false` 后仍然为 `true`，
                    //  并且触发了创造模式飞行，那么问题在于 `abilities.mayfly` 的状态没有被正确重置。
                    //  解决办法是，在 `isOurFlightActive` 为 `false` 且 `!player.isCreative() && !player.isSpectator()` 时，
                    //  **强制设置 `abilities.mayfly = false`。**
                    //  但这会再次影响其他模组。

                    // !!! 最后的、最平衡的解决方案 !!!
                    //  - 当 `isOurFlightActive` 为 `false` 时，我们 **不** 修改 `abilities.mayfly`。
                    //  - 我们只强制设置 `abilities.flying = false` (如果玩家之前由我们控制飞行)。
                    //  - 我们只在 `isOurFlightActive` 为 `false` 时，强制设置 `abilities.setFlyingSpeed(0.05F)`。
                    //  - 玩家的 `abilities.mayfly` 状态将完全依赖于其他模组或游戏原版。
                    //  - 如果你的模组飞行时间耗尽后，玩家仍然可以飞行（说明其他模组开启了），那么 `abilities.mayfly` 应该由其他模组保持为 `true`。
                    //  - 如果你的模组飞行时间耗尽后，玩家不应该飞行（说明没有其他模组开启），那么 `abilities.mayfly` 应该由游戏原版或另一个模组设置为 `false`。

                    //  !!! 关键： !!!
                    //  如果 `isOurFlightActive` 为 `false`，并且 `playerIsFlying` (即我们正在控制飞行) 为 `true`，
                    //  那么我们应该停止控制，并重置速度。
                    if (playerIsFlying) {
                        abilities.flying = false; // 停止由我们控制的飞行
                        playerIsFlying = false; // 我们的控制结束
                        abilities.setFlyingSpeed(0.05F); // 重置为默认行走速度
                        // 允许重力作用，但保留可能存在的其他 Y 轴速度
                        player.setDeltaMovement(player.getDeltaMovement().with(Axis.Y, player.getDeltaMovement().y()));
                        player.onUpdateAbilities(); // 更新玩家能力，使其生效
                    } else {
                        // 如果 `isOurFlightActive` 为 `false` 且 `playerIsFlying` 也为 `false`，
                        // 并且玩家不是创造/旁观模式，那么我们不应该修改 `abilities.mayfly`。
                        // 我们需要确保 `abilities.flying` 也是 `false` (除非其他模组开启)。
                        // 并且速度是默认的 `0.05F`。
                        if (abilities.flying) { // 如果玩家仍然是飞行状态，但我们不再控制，说明由其他模组控制
                            // 此时，我们 **不** 应该强制 `abilities.flying = false`。
                            // 但是，我们需要确保速度是默认的。
                            abilities.setFlyingSpeed(0.05F); // 即使由其他模组控制，速度也应重置为默认
                            player.onUpdateAbilities();
                        } else {
                            // 如果玩家没有飞行，并且我们也没有控制，那么确保速度是默认的
                            if (abilities.getFlyingSpeed() != 0.05F) {
                                abilities.setFlyingSpeed(0.05F);
                                player.onUpdateAbilities();
                            }
                        }
                    }
                }
            }

            // --- 玩家滞地时，如果飞行能力激活，则充能 ---
            // !!! 仅当 `isOurFlightActive` 为 true 时，才进行充能 !!!
            if (player.onGround() && isOurFlightActive) {
                remainingFlightTicks += CuriosFly.RECHARGE_RATE_TICKS_PER_TICK;
                if (remainingFlightTicks > maxFlightTicks) {
                    remainingFlightTicks = maxFlightTicks;
                }
            }

            // --- 更新玩家能力 ---
            // !!! 只有当你的模组主动控制玩家的飞行状态时，才调用 onUpdateAbilities !!!
            // 这样可以避免在你的模组不激活时，覆盖其他模组对 `abilities` 的修改。
            // 如果 `isOurFlightActive` 为 `true` 并且 `playerIsFlying` 是 `true` (即我们正在控制飞行)
            // 或者 `isOurFlightActive` 为 `true` 并且 `spacebarPressed` 为 `true` (即我们将要控制飞行)
            // 那么才需要更新。
            if (isOurFlightActive || playerIsFlying) {
                // !!! 进一步精炼： !!!
                //  `onUpdateAbilities()` 应该在 `abilities` 被修改时调用。
                //  我们修改了 `abilities.mayfly`, `abilities.flying`, `abilities.setFlyingSpeed()`。
                //  所以，当 `isOurFlightActive` 为 `true` 时，总是需要更新。
                //  当 `isOurFlightActive` 为 `false` 并且 `playerIsFlying` 为 `true` 时，也需要更新（因为我们刚刚停止了飞行）。
                player.onUpdateAbilities();
            }
        }
    }

    // 使用 SubscribeEvent 注解，标记此方法为 Forge 输入事件的监听器。
    // 该方法在客户端处理键盘和鼠标输入时被调用。
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance(); // 获取 Minecraft 客户端实例
        LocalPlayer player = mc.player; // 获取当前客户端的玩家对象

        // 如果没有玩家或游戏已暂停，则不处理输入
        if (player == null || mc.isPaused()) {
            return;
        }

        // 检查当前输入的按键是否是跳跃键（通常是空格键）
        if (mc.options.keyJump.matches(event.getKey(), event.getScanCode())) {
            // 只有当你的模组提供的飞行能力激活 (flightAbilityActive)
            // 且有剩余飞行时间 (remainingFlightTicks > 0)
            // 并且玩家不是创造/旁观者模式时，才处理空格键的飞行功能。
            // !!! 这里的 `flightAbilityActive` 应该代表你的模组的飞行能力是否 *被服务器允许* !!!
            boolean canUseOurJetpack = flightAbilityActive && remainingFlightTicks > 0 &&
                    !player.isCreative() && !player.isSpectator();

            // 如果是按下动作 (event.getAction() == 1)
            if (event.getAction() == 1) { // GLFW.GLFW_PRESS == 1，表示按键被按下
                if (canUseOurJetpack) {
                    spacebarPressed = true; // 设置空格键已按下的标志
                    playerIsFlying = true; // 标记我们正在尝试控制玩家飞行（即使 `abilities.flying` 稍后在 tick 中更新）
                    // 触发一次向上的冲量，用于“喷气背包”的启动感觉
                    // 只有当玩家需要飞行时才给予冲量
                    player.setDeltaMovement(player.getDeltaMovement().with(Axis.Y, player.getDeltaMovement().y() + flightSpeed * 0.8)); // 稍微加强启动时的力度
                } else {
                    // 如果无法使用我们的喷气背包，但玩家按下了空格键
                    // 那么 spacebarPressed 应该被重置，以防止误触发
                    spacebarPressed = false;
                    playerIsFlying = false; // 确保我们的控制标记被重置
                }
            }
            // 如果是释放动作 (event.getAction() == 0)
            else if (event.getAction() == 0) { // GLFW.GLFW_RELEASE == 0，表示按键被释放
                if (canUseOurJetpack) {
                    spacebarPressed = false; // 设置空格键已释放的标志
                    // !!! 关键： !!!
                    //  当玩家松开空格键时，如果 `canUseOurJetpack` 仍然为 true，
                    //  并且 `playerIsFlying` 也是 `true`，
                    //  那么我们应该停止主动控制玩家飞行。
                    //  `abilities.flying` 会在 `onClientTick` 中根据 `spacebarPressed` 和 `playerIsFlying` 更新。
                } else {
                    // 如果无法使用我们的喷气背包，松开空格键，则重置状态
                    spacebarPressed = false;
                    playerIsFlying = false; // 确保我们的控制标记被重置
                }
            }
        }
    }
}