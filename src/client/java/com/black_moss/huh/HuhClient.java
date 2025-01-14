package com.black_moss.huh;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class HuhClient implements ClientModInitializer {
	private static final KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.huh.huh",
			InputUtil.GLFW_KEY_H,
			KeyBinding.MISC_CATEGORY
	));
	private static final SoundEvent HUH_SOUND_EVENT = SoundEvent.of(Identifier.of("huh", "huh"));

	@Override
	public void onInitializeClient() {

		// 添加按键事件监听器
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.world!=null && client.player!=null && keyBinding.isPressed()) {
				client.world.playSound(client.player, client.player.getX(), client.player.getY(), client.player.getZ(), HUH_SOUND_EVENT, SoundCategory.PLAYERS);
			}
		});
	}
}
