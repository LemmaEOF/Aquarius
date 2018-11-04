package space.bbkr.aquarius.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import space.bbkr.aquarius.EntityHookshot;

@Mixin(EntityTracker.class)
public class MixinEntityTracker
{
    @Redirect(method = "track(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTracker;track(Lnet/minecraft/entity/Entity;IIZ)V", ordinal = 1))
    public void trackHookshotOrElseTrackArrow(final EntityTracker tracker, final Entity entity, final int trackingRange, final int updateInterval, final boolean sendVelocityUpdates) {
        if (entity instanceof EntityHookshot) {
            tracker.track(entity, 160, 20, true);
        } else {
            tracker.track(entity, trackingRange, updateInterval, sendVelocityUpdates);
        }
    }
}
