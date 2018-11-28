package space.bbkr.aquarius;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemHookshot extends Item {

    public ItemHookshot(Properties builder) {
        super(builder);
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.SPEAR;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase thrower, int useTicks) {
        if (thrower instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)thrower;
            int usedLength = this.getMaxItemUseDuration(stack) - useTicks;
            if (usedLength >= 10) {
                if (!world.isRemote) {
                    EntityHookshot hook = new EntityHookshot(world, player, stack);
                    hook.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F + 0f * 0.5F, 1.0F);
                    if (player.abilities.isCreativeMode) {
                        hook.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                    }

                    world.spawnEntity(hook);
//                    if (!lvt_5_1_.capabilities.isCreativeMode) {
//                        lvt_5_1_.inventory.deleteStack(p_onPlayerStoppedUsing_1_);
//                    }

                }

                player.addStat(StatList.ITEM_USED.get(this));
                SoundEvent sound = SoundEvents.ITEM_TRIDENT_THROW;

                world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }

        }
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
//        if (stack.getItemDamage() >= stack.getMaxDamage()) { //eventually switch to the hook being active
//            return new ActionResult(EnumActionResult.FAIL, stack);
//        } else {
            player.setActiveHand(hand);
            return new ActionResult(EnumActionResult.SUCCESS, stack);
//        }
    }
}
