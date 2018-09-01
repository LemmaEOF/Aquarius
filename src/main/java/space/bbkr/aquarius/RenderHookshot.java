package space.bbkr.aquarius;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHookshot extends RenderArrow<EntityHookshot> {

    public RenderHookshot(RenderManager manager) {
        super(manager);
    }

    protected ResourceLocation getEntityTexture(EntityHookshot hookshot) {
        return new ResourceLocation("textures/entity/projectiles/arrow.png");
    }
}
