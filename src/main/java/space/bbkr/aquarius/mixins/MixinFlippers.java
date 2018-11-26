package space.bbkr.aquarius.mixins;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.bbkr.aquarius.Aquarius;

@Mixin(EntityPlayer.class)
public abstract class MixinFlippers extends EntityLivingBase {

    private boolean wasLastAirSwimming = false;

    @Shadow public abstract boolean isSwimming();

    public MixinFlippers(EntityType<?> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }

    @Inject(method = "updateTurtleHelmet", at = @At("HEAD"))
    private void updateTurtleHelmet(CallbackInfo ci) {
        ItemStack stackFeet = this.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (stackFeet.getItem() == Aquarius.FLIPPERS) {
            if (this.isInWater()) this.addPotionEffect(new PotionEffect(MobEffects.DOLPHINS_GRACE, 20, 0, true, false, true));
            else this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 0, true, false, true));
        }
    }

    @Inject(method = "updateSwimming", at = @At("TAIL"))
    public void updateAirSwimming(CallbackInfo ci) {
        if (this.isPotionActive(Aquarius.AIR_SWIMMER)) {
            this.setSwimming((this.isSprinting() || this.wasLastAirSwimming) && !this.isRiding() && !this.isSneaking());
            this.wasLastAirSwimming = (this.isSwimming());
            this.inWater = this.isSwimming();
            if (this.isSwimming()) {
                this.fallDistance = 0.0F;
                Vec3d look = this.getLookVec();
                //TODO: figure out how to get this to only happen when there's key input
                move(MoverType.SELF, look.x/4, look.y/4, look.z/4);
            }
        }
    }



}
