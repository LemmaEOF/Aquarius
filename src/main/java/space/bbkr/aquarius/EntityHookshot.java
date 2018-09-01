package space.bbkr.aquarius;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityHookshot extends EntityArrow {
    private ItemStack thrownStack;
    private boolean dealtDamage;
    public int returningTicks;

    public EntityHookshot(World world) {
        super(Aquarius.HOOKSHOT_ENTITY, world);
    }

    public EntityHookshot(World world, EntityLivingBase thrower, ItemStack stack) {
        super(Aquarius.HOOKSHOT_ENTITY, thrower, world);
        this.thrownStack = new ItemStack(Aquarius.HOOKSHOT);
        this.thrownStack = stack.copy();
    }

    public EntityHookshot(World world, double posX, double posY, double posZ) {
        super(Aquarius.HOOKSHOT_ENTITY, posX, posY, posZ, world);
        this.thrownStack = new ItemStack(Aquarius.HOOKSHOT);
    }

    @Override
    protected ItemStack getArrowStack() {
        return this.thrownStack.copy();
    }

}
