package space.bbkr.aquarius;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.listener.ItemAdder;

public class Aquarius implements ItemAdder {

    public static final Item FLIPPERS = new ItemArmor(ArmorMaterial.TURTLE, EntityEquipmentSlot.FEET, new Item.Builder().group(ItemGroup.COMBAT));
    public static final Item PRISMARINE_ROD = new Item(new Item.Builder().group(ItemGroup.MISC));

    @Override
    public void registerItems() {
        Item.registerItem(new ResourceLocation("aquarius:flippers"), FLIPPERS);
        Item.registerItem(new ResourceLocation("aquarius:prismarine_rod"), PRISMARINE_ROD);
    }
}
