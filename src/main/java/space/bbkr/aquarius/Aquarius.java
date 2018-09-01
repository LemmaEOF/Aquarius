package space.bbkr.aquarius;

import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.listener.EntityTypeAdder;
import org.dimdev.rift.listener.ItemAdder;

public class Aquarius implements ItemAdder, EntityTypeAdder {

    public static final Item FLIPPERS = new ItemArmor(ArmorMaterial.TURTLE, EntityEquipmentSlot.FEET, new Item.Builder().group(ItemGroup.COMBAT));
    public static final Item PRISMARINE_ROD = new Item(new Item.Builder().group(ItemGroup.MISC));
    public static final Item HOOKSHOT = new ItemHookshot(new Item.Builder().group(ItemGroup.TOOLS));
    public static EntityType HOOKSHOT_ENTITY;

    @Override
    public void registerItems() {
        Item.register(new ResourceLocation("aquarius:flippers"), FLIPPERS);
        Item.register(new ResourceLocation("aquarius:prismarine_rod"), PRISMARINE_ROD);
        Item.register(new ResourceLocation("aquarius:hookshot"), HOOKSHOT);
    }

    @Override
    public void registerEntityTypes() {
        HOOKSHOT_ENTITY = EntityType.register("aquarius:hookshot", EntityType.Builder.create(EntityHookshot.class, EntityHookshot::new));
    }
}
