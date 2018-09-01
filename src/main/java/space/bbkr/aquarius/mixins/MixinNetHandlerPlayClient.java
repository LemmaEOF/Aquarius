package space.bbkr.aquarius.mixins;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.network.play.server.SPacketSpawnObject;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.bbkr.aquarius.EntityHookshot;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Shadow private WorldClient world;

    @Inject(method = "handleSpawnObject", at = @At("RETURN"))
    public void handleAquariusSpawnObject(SPacketSpawnObject packetIn, CallbackInfo ci) {
        double d0 = packetIn.getX();
        double d1 = packetIn.getY();
        double d2 = packetIn.getZ();
        Entity hookshotEntity = null;

        if (packetIn.getType() == 969) {
            hookshotEntity = new EntityHookshot(this.world, d0, d1, d2);
            packetIn.setData(0);
        }

        if (hookshotEntity != null) {
            EntityTracker.updateServerPosition(hookshotEntity, d0, d1, d2);
            hookshotEntity.rotationPitch = (float)(packetIn.getPitch() * 360) / 256.0F;
            hookshotEntity.rotationYaw = (float)(packetIn.getYaw() * 360) / 256.0F;
            Entity[] centityy = hookshotEntity.getParts();

            if (centityy != null) {
                int i = packetIn.getEntityID() - hookshotEntity.getEntityId();

                for (Entity entity2 : centityy) {
                    entity2.setEntityId(entity2.getEntityId() + i);
                }
            }

            hookshotEntity.setEntityId(packetIn.getEntityID());
            hookshotEntity.setUniqueId(packetIn.getUniqueId());
            this.world.addEntityToWorld(packetIn.getEntityID(), hookshotEntity);
        }
    }
}
