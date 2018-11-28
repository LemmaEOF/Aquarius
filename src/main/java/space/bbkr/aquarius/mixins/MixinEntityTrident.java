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
import net.minecraft.util.DamageSource;
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

    @Inject(method = "b", // this needs to be a notch name because the refmap throws a fit if it isn't and that's not nice
            at = @At(value = "INVOKE", target = "Laxs;X()Z"), // seriously, what is mixin's problem with this one @Inject
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            remap = false)
    public void onHitEntity(RayTraceResult p_onHitEntity_1_, CallbackInfo ci, Entity lvt_2_1_, float lvt_3_1_, DamageSource lvt_4_2_, SoundEvent lvt_5_1_, float lvt_6_2_) {
        if ((this.world.isThundering() && EnchantmentHelper.hasChannelingEnchantment(this.thrownStack)) || (this.world.isRaining() && getChannelingLevel(thrownStack) >= 2) || getChannelingLevel(thrownStack) == 3) {
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
