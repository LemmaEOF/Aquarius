package space.bbkr.aquarius;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import org.dimdev.rift.listener.*;

public class Aquarius implements BlockAdder, ItemAdder, EntityTypeAdder, MobEffectAdder, TileEntityTypeAdder {

    public static final Block CHORUS_CONDUIT = new BlockChorusConduit(Block.Properties.create(Material.GLASS).hardnessAndResistance(3.0F, 3.0F).lightValue(15));
    public static final Item FLIPPERS = new ItemArmor(ArmorMaterial.TURTLE, EntityEquipmentSlot.FEET, new Item.Properties().group(ItemGroup.COMBAT));
    public static final Item PRISMARINE_ROD = new Item(new Item.Properties().group(ItemGroup.MISC));
    public static final Item HOOKSHOT = new ItemHookshot(new Item.Properties().group(ItemGroup.TOOLS));
    public static EntityType HOOKSHOT_ENTITY;
    public static Potion ATLANTEAN = new AquariusPotion(false, 0x1dd186).setIcon(9, 0).setBeneficial();
    public static TileEntityType<TileEntityChorusConduit> CHORUS_CONDUIT_TE;

    @Override
    public void registerBlocks() {
        Block.register(new ResourceLocation("aquarius:chorus_conduit"), CHORUS_CONDUIT);
    }

    @Override
    public void registerItems() {
        Item.register(new ResourceLocation("aquarius:flippers"), FLIPPERS);
        Item.register(new ResourceLocation("aquarius:prismarine_rod"), PRISMARINE_ROD);
        //Item.register(new ResourceLocation("aquarius:hookshot"), HOOKSHOT);
        Item.register(CHORUS_CONDUIT, ItemGroup.MISC);
    }

    @Override
    public void registerEntityTypes() {
//        HOOKSHOT_ENTITY = EntityType.register("aquarius:hookshot", EntityType.Builder.create(EntityHookshot.class, EntityHookshot::new));
    }

    @Override
    public void registerMobEffects() {
        RegistryNamespaced.MOB_EFFECT.register(31, new ResourceLocation("aquarius:atlantean"), ATLANTEAN);
    }

    @Override
    public void registerTileEntityTypes() {
        CHORUS_CONDUIT_TE = TileEntityType.register("aquarius:chorus_conduit", TileEntityType.Builder.create(TileEntityChorusConduit::new));
        RegistryNamespaced.BLOCK_ENTITY_TYPE.register(33, new ResourceLocation("aquarius:chorus_conduit"), CHORUS_CONDUIT_TE);
    }
}
