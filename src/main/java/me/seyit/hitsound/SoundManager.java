package me.seyit.hitsound;

import me.seyit.hitsound.config.ConfigHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class SoundManager {
    public static final Identifier HIT_SOUND_ID = HitSound.HIT_SOUND_ID;
    public static SoundEvent HIT_SOUND_EVENT;

    public void registerSounds() {
        if (!Registries.SOUND_EVENT.containsId(HIT_SOUND_ID)) {
            HIT_SOUND_EVENT = SoundEvent.of(HIT_SOUND_ID);
            Registry.register(Registries.SOUND_EVENT, HIT_SOUND_ID, HIT_SOUND_EVENT);
        } else {
            HIT_SOUND_EVENT = Registries.SOUND_EVENT.get(HIT_SOUND_ID);
        }
    }

    public void playHitSound() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            float volume = ConfigHandler.configData.hitSoundVolume;
            client.player.playSound(HIT_SOUND_EVENT, volume, 1.0F);
        }
    }
}
