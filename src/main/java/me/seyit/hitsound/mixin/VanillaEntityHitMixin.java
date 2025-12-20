package me.seyit.hitsound.mixin;
import me.seyit.hitsound.config.ConfigHandler;
import me.seyit.hitsound.util.FileManager;
import net.minecraft.client.sound.AbstractSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSoundInstance.class)
public abstract class VanillaEntityHitMixin {
    @Inject(method = "getVolume", at = @At("RETURN"), cancellable = true)
    private void lowerVanillaHitSound(CallbackInfoReturnable<Float> cir) {
        AbstractSoundInstance instance = (AbstractSoundInstance) (Object) this;
        String soundId = instance.getId().toString();
        if (instance == null || instance.getId() == null) return;

        // Controleer op het specifieke geluid
        if (soundId.equals("minecraft:entity.player.attack.sweep")||
                soundId.equals("minecraft:entity.player.attack.strong")||
                soundId.equals("minecraft:entity.player.attack.knockback"))
        {
            float volume = ConfigHandler.configData.vanillaHitSoundVolume;
            cir.setReturnValue(cir.getReturnValue() * volume);
        }
        if (soundId.equals("minecraft:entity.player.attack.crit")){
            float volume = ConfigHandler.configData.vanillaCritSoundVolume;
            cir.setReturnValue(cir.getReturnValue() * volume);
        }
    }
}