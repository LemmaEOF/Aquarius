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
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityTrident.class)
public abstract class MixinEntityTrident extends EntityArrow {

    @Shadow private ItemStack thrownStack;
    @Shadow private boolean dealtDamage;

    public MixinEntityTrident(World world, EntityLivingBase thrower, ItemStack stack) {
        super(EntityType.TRIDENT, thrower, world);
        this.thrownStack = stack;
    }

    private int getChannelingLevel(ItemStack stack) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.CHANNELING, stack);
    }

    /**
     * @author b0undarybreaker
     * @reason mixin being bad with injection, again
     */
    @Overwrite
    protected void onHitEntity(RayTraceResult p_onHitEntity_1_) {
        Entity lvt_2_1_ = p_onHitEntity_1_.entity;
        float lvt_3_1_ = 8.0F;
        if (lvt_2_1_ instanceof EntityLivingBase) {
            EntityLivingBase lvt_4_1_ = (EntityLivingBase)lvt_2_1_;
            lvt_3_1_ += EnchantmentHelper.getModifierForCreature(this.thrownStack, lvt_4_1_.getCreatureAttribute());
        }

        Entity lvt_4_2_ = this.func_212360_k();
        DamageSource lvt_5_1_ = DamageSource.causeTridentDamage(this, (Entity)(lvt_4_2_ == null ? this : lvt_4_2_));
        this.dealtDamage = true;
        SoundEvent lvt_6_1_ = SoundEvents.ITEM_TRIDENT_HIT;
        if (lvt_2_1_.attackEntityFrom(lvt_5_1_, lvt_3_1_) && lvt_2_1_ instanceof EntityLivingBase) {
            EntityLivingBase lvt_7_1_ = (EntityLivingBase)lvt_2_1_;
            if (lvt_4_2_ instanceof EntityLivingBase) {
                EnchantmentHelper.applyThornEnchantments(lvt_7_1_, lvt_4_2_);
                EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)lvt_4_2_, lvt_7_1_);
            }

            this.arrowHit(lvt_7_1_);
        }

        this.motionX *= -0.009999999776482582D;
        this.motionY *= -0.10000000149011612D;
        this.motionZ *= -0.009999999776482582D;
        float lvt_7_2_ = 1.0F;
        if ((this.world.isThundering() && EnchantmentHelper.hasChanneling(this.thrownStack)) || (this.world.isRaining() && getChannelingLevel(thrownStack) >= 2) || getChannelingLevel(thrownStack) == 3) {
            BlockPos lvt_8_1_ = lvt_2_1_.getPosition();
            if (this.world.canSeeSky(lvt_8_1_)) {
                EntityLightningBolt lvt_9_1_ = new EntityLightningBolt(this.world, (double)lvt_8_1_.getX() + 0.5D, (double)lvt_8_1_.getY(), (double)lvt_8_1_.getZ() + 0.5D, false);
                lvt_9_1_.setCaster(lvt_4_2_ instanceof EntityPlayerMP ? (EntityPlayerMP)lvt_4_2_ : null);
                this.world.addWeatherEffect(lvt_9_1_);
                lvt_6_1_ = SoundEvents.ITEM_TRIDENT_THUNDER;
                lvt_7_2_ = 5.0F;
            }
        }

        this.playSound(lvt_6_1_, lvt_7_2_, 1.0F);
    }

}
