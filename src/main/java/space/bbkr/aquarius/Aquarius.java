package space.bbkr.aquarius;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSimpleFoiled;
import org.dimdev.rift.listener.ItemAdder;

public class Aquarius implements ItemAdder {

    public static final Item FLIPPERS = new ItemSimpleFoiled(new Item.Builder().group(ItemGroup.REDSTONE).rarity(EnumRarity.RARE));

    @Override
    public void registerItems() {
        Item.registerItem("flippers", FLIPPERS);
    }
}
