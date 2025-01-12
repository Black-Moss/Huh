package com.black_moss.huh;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HuhMod implements ModInitializer {
    public static final String MOD_ID = "huh";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static KeyBinding keyBinding;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");

        // 注册按键事件监听器
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + MOD_ID + ".huh_sound",
            InputUtil.Type.KEYSYM,
            InputUtil.GLFW_KEY_H,
            "category." + MOD_ID + ".tutorial"
        ));

        // 添加按键事件监听器
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.isPressed()) {
                client.player.sendMessage(Text.literal("H key pressed!"), false);
                client.world.playSound(client.player, client.player.getX(), client.player.getY(), client.player.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_HARP, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        });
    }
}