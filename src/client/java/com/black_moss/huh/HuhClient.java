package com.black_moss.huh;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.black_moss.huh.Huh.MOD_ID;

public class HuhClient implements ClientModInitializer {
	private static KeyBinding keyBinding;
	private static boolean wasKeyPressed = false; // 新增变量，记录上一帧按键状态
	private static final SoundEvent HUH_SOUND_EVENT = SoundEvent.of(Identifier.of(MOD_ID, "huh"));

	@Override
	public void onInitializeClient() {
		// 注册按键事件监听器
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key." + MOD_ID + ".huh",
				InputUtil.Type.KEYSYM,
				InputUtil.GLFW_KEY_H,
				KeyBinding.MISC_CATEGORY
		));

		// 添加按键事件监听器
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			boolean isKeyPressed = keyBinding.isPressed();
			if (isKeyPressed && !wasKeyPressed) { // 检查按键是否从释放状态变为按下状态
				client.world.playSound(client.player, client.player.getX(), client.player.getY(), client.player.getZ(), HUH_SOUND_EVENT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			}
			wasKeyPressed = isKeyPressed; // 更新上一帧按键状态
		});
	}
}
