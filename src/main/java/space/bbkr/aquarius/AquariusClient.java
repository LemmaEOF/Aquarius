package space.bbkr.aquarius;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.dimdev.rift.listener.client.EntityRendererAdder;

import java.util.Map;

public class AquariusClient implements EntityRendererAdder {

    @Override
    public void addEntityRenderers(Map<Class<? extends Entity>, Render<? extends Entity>> entityRenderMap, RenderManager renderManager) {
        entityRenderMap.put(EntityHookshot.class, new RenderHookshot(renderManager));
    }
}
