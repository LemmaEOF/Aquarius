package space.bbkr.aquarius;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import org.dimdev.rift.listener.client.EntityRendererAdder;
import org.dimdev.rift.listener.client.TileEntityRendererAdder;

import java.util.Map;

public class AquariusClient implements /*EntityRendererAdder,*/ TileEntityRendererAdder {

//    @Override
//    public void addEntityRenderers(Map<Class<? extends Entity>, Render<? extends Entity>> entityRenderMap, RenderManager renderManager) {
//        entityRenderMap.put(EntityHookshot.class, new RenderHookshot(renderManager));
//    }

    @Override
    public void addTileEntityRenderers(Map<Class<? extends TileEntity>, TileEntityRenderer<? extends TileEntity>> renderers) {
        renderers.put(TileEntityChorusConduit.class, new RenderChorusConduit());
    }
}
