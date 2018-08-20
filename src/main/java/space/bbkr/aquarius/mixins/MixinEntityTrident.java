package space.bbkr.aquarius.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTrident;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityTrident.class)
public abstract class MixinEntityTrident extends EntityArrow {

    @Shadow
    private ItemStack thrownStack;

    public MixinEntityTrident(World world, EntityLivingBase thrower, ItemStack stack) {
        super(EntityType.TRIDENT, thrower, world);
        this.thrownStack = stack;
    }

    private int getChannelingLevel(ItemStack stack) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.CHANNELING, stack);
    }

    @Inject(method = "onHitEntity(Lnet/minecraft/util/math/RayTraceResult;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isThundering()Z"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            remap = false)
    public void onHitEntity(RayTraceResult ray, CallbackInfo ci, Entity lvt_2_1_, SoundEvent lvt_5_1_, float lvt_6_2_) {
        if ((this.world.isThundering() && EnchantmentHelper.func_203192_h(this.thrownStack)) || (this.world.isRaining() && getChannelingLevel(thrownStack) >= 2) || getChannelingLevel(thrownStack) == 3) {
            BlockPos entityPos = lvt_2_1_.getPosition();
            if (this.world.canSeeSky(entityPos)) {
                EntityLightningBolt lightning = new EntityLightningBolt(this.world, (double)entityPos.getX(), (double)entityPos.getY(), (double)entityPos.getZ(), false);
                lightning.setCaster(this.shootingEntity instanceof EntityPlayerMP ? (EntityPlayerMP)this.shootingEntity : null);
                this.world.addWeatherEffect(lightning);
                lvt_5_1_ = SoundEvents.ITEM_TRIDENT_THUNDER;
                lvt_6_2_ = 5.0F;
            }
        }

        this.playSound(lvt_5_1_, lvt_6_2_, 1.0F);
        ci.cancel();
    }

}
