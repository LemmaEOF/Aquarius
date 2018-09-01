package space.bbkr.aquarius.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketSpawnObject;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import space.bbkr.aquarius.EntityHookshot;


@Mixin(EntityTrackerEntry.class)
public class MixinEntityTrackerEntry
{
    @Shadow @Final private Entity trackedEntity;

    @Inject(method = "createSpawnPacket", at = @At("INVOKE"), cancellable = true)
    private void createAquariusSpawnPacket(CallbackInfoReturnable<Packet<?>> info) {
        if (this.trackedEntity instanceof EntityHookshot) {
            EntityHookshot hookshot = (EntityHookshot) this.trackedEntity;
            info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 969));
        }
    }
}
