package space.bbkr.aquarius;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.listener.*;
import org.dimdev.rift.listener.client.TileEntityRendererAdder;

import java.util.Map;

public class Aquarius implements BlockAdder, ItemAdder, EntityTypeAdder, MobEffectAdder, TileEntityTypeAdder, TileEntityRendererAdder {

    public static final Block CHORUS_CONDUIT = new BlockChorusConduit(Block.Builder.create(Material.GLASS, MapColor.DIAMOND).hardnessAndResistance(3.0F, 3.0F).lightValue(15));
    public static final Item FLIPPERS = new ItemArmor(ArmorMaterial.TURTLE, EntityEquipmentSlot.FEET, new Item.Builder().group(ItemGroup.COMBAT));
    public static final Item PRISMARINE_ROD = new Item(new Item.Builder().group(ItemGroup.MISC));
    public static final Item HOOKSHOT = new ItemHookshot(new Item.Builder().group(ItemGroup.TOOLS));
    public static EntityType HOOKSHOT_ENTITY;
    public static Potion AIR_SWIMMER = new AquariusPotion(false, 0x1dd186).setIcon(9, 0).setBeneficial();
    public static TileEntityType<TileEntityChorusConduit> CHORUS_CONDUIT_TE;

    @Override
    public void registerBlocks() {
        Block.register(new ResourceLocation("aquarius:chorus_conduit"), CHORUS_CONDUIT);
    }

    @Override
    public void registerItems() {
        Item.register(new ResourceLocation("aquarius:flippers"), FLIPPERS);
        Item.register(new ResourceLocation("aquarius:prismarine_rod"), PRISMARINE_ROD);
        Item.register(new ResourceLocation("aquarius:hookshot"), HOOKSHOT);
        Item.register(CHORUS_CONDUIT, ItemGroup.DECORATIONS);
    }

    @Override
    public void registerEntityTypes() {
        HOOKSHOT_ENTITY = EntityType.register("aquarius:hookshot", EntityType.Builder.create(EntityHookshot.class, EntityHookshot::new));
    }

    @Override
    public void registerMobEffects() {
        Potion.REGISTRY.register(31, new ResourceLocation("aquarius:air_swimmer"), AIR_SWIMMER);
    }

    @Override
    public void registerTileEntityTypes() {
        CHORUS_CONDUIT_TE = TileEntityType.registerTileEntityType("aquarius:chorus_conduit", TileEntityType.Builder.create(TileEntityChorusConduit::new));
    }

    @Override
    public void addTileEntityRenderers(Map<Class<? extends TileEntity>, TileEntityRenderer<? extends TileEntity>> renderers) {
        renderers.put(TileEntityChorusConduit.class, new RenderChorusConduit());
    }
}
