package space.bbkr.aquarius.mixins;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.bbkr.aquarius.Aquarius;

@Mixin(EntityPlayer.class)
public abstract class MixinFlippers extends EntityLivingBase {

    public MixinFlippers(EntityType<?> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }

    @Inject(method = "updateTurtleHelmet", at = @At("HEAD"))
    private void updateTurtleHelmet(CallbackInfo ci) {
        ItemStack stackFeet = this.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (stackFeet.getItem() == Aquarius.FLIPPERS) {
            if (this.areEyesInFluid(FluidTags.WATER)) this.addPotionEffect(new PotionEffect(MobEffects.DOLPHINS_GRACE, 200, 0, false, false, true));
            else if (this.areEyesInFluid(FluidTags.WATER)) this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 1, false, false, true));
        }
    }
}
