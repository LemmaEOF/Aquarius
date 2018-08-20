package space.bbkr.aquarius;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSimpleFoiled;
import org.dimdev.rift.listener.ItemAdder;
import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;


public class Aquarius implements ItemAdder, InitializationListener {

    public static final Item FLIPPERS = new ItemSimpleFoiled(new Item.Builder().group(ItemGroup.REDSTONE).rarity(EnumRarity.RARE));

    @Override
    public void registerItems() {
        Item.registerItem("flippers", FLIPPERS);
    }

    @Override
    public void onInitialization() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.pgeg.json");
    }
}
