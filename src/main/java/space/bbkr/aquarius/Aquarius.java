package space.bbkr.aquarius;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.listener.ItemAdder;

public class Aquarius implements ItemAdder {

    public static final Item FLIPPERS = new ItemArmor(ArmorMaterial.TURTLE, EntityEquipmentSlot.FEET, new Item.Builder().group(ItemGroup.COMBAT));

    @Override
    public void registerItems() {
        Item.registerItem(new ResourceLocation("aquarius:flippers"), FLIPPERS);
    }
}
