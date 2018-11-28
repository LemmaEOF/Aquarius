package space.bbkr.aquarius;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;

public class RenderChorusConduit extends TileEntityRenderer<TileEntityChorusConduit> {
    private static final ResourceLocation baseTex = new ResourceLocation("aquarius:textures/entity/chorus_conduit/base.png");
    private static final ResourceLocation cageTex = new ResourceLocation("aquarius:textures/entity/chorus_conduit/cage.png");
    private static final ResourceLocation windTex = new ResourceLocation("aquarius:textures/entity/chorus_conduit/wind.png");
    private static final ResourceLocation verticalWindTex = new ResourceLocation("aquarius:textures/entity/chorus_conduit/wind_vertical.png");
    private static final ResourceLocation openEyeTex = new ResourceLocation("aquarius:textures/entity/chorus_conduit/open_eye.png");
    private static final ResourceLocation closedEyeTex = new ResourceLocation("aquarius:textures/entity/chorus_conduit/closed_eye.png");
    private final ModelBase shellModel = new RenderChorusConduit.ShellModel();
    private final ModelBase cageModel = new RenderChorusConduit.CageModel();
    private final RenderChorusConduit.WindModel windModel = new RenderChorusConduit.WindModel();
    private final RenderChorusConduit.EyeModel eyeModel = new RenderChorusConduit.EyeModel();

    public RenderChorusConduit() {

    }

    public void render(TileEntityChorusConduit conduit, double x, double y, double z, float partialTicks, int p_render_9_) {
        float currentTicks = (float)conduit.ticksExisted + partialTicks;
        float rotPoint;
        if (!conduit.isActive()) {
            rotPoint = conduit.drawTESR(0.0F);
            this.bindTexture(baseTex);
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
            GlStateManager.rotatef(rotPoint, 0.0F, 1.0F, 0.0F);
            this.shellModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GlStateManager.popMatrix();
        } else if (conduit.isActive()) {
            rotPoint = conduit.drawTESR(partialTicks) * 57.295776F;
            float bobPoint = MathHelper.sin(currentTicks * 0.1F) / 2.0F + 0.5F;
            bobPoint += bobPoint * bobPoint;
            this.bindTexture(cageTex);
            GlStateManager.disableCull();
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float)x + 0.5F, (float)y + 0.3F + bobPoint * 0.2F, (float)z + 0.5F);
            GlStateManager.rotatef(rotPoint, 0.5F, 1.0F, 0.5F);
            this.cageModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GlStateManager.popMatrix();
            int windFrame = conduit.ticksExisted / 3 % RenderChorusConduit.WindModel.windCount;
            this.windModel.setFrame(windFrame);
            int frameLoop = conduit.ticksExisted / (3 * RenderChorusConduit.WindModel.windCount) % 3;
            switch(frameLoop) {
                case 0:
                    this.bindTexture(windTex);
                    GlStateManager.pushMatrix();
                    GlStateManager.translatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
                    this.windModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                    GlStateManager.popMatrix();
                    GlStateManager.pushMatrix();
                    GlStateManager.translatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
                    GlStateManager.scalef(0.875F, 0.875F, 0.875F);
                    GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    this.windModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                    GlStateManager.popMatrix();
                    break;
                case 1:
                    this.bindTexture(verticalWindTex);
                    GlStateManager.pushMatrix();
                    GlStateManager.translatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
                    GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
                    this.windModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                    GlStateManager.popMatrix();
                    GlStateManager.pushMatrix();
                    GlStateManager.translatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
                    GlStateManager.scalef(0.875F, 0.875F, 0.875F);
                    GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    this.windModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                    GlStateManager.popMatrix();
                    break;
                case 2:
                    this.bindTexture(windTex);
                    GlStateManager.pushMatrix();
                    GlStateManager.translatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
                    GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    this.windModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                    GlStateManager.popMatrix();
                    GlStateManager.pushMatrix();
                    GlStateManager.translatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
                    GlStateManager.scalef(0.875F, 0.875F, 0.875F);
                    GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    this.windModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                    GlStateManager.popMatrix();
            }

            Entity viewer = Minecraft.getInstance().getRenderViewEntity();
            Vec2f look = Vec2f.ZERO;
            if (viewer != null) {
                look = viewer.getPitchYaw();
            }

            if (conduit.isEyeOpen()) {
                this.bindTexture(openEyeTex);
            } else {
                this.bindTexture(closedEyeTex);
            }

            GlStateManager.pushMatrix();
            GlStateManager.translatef((float)x + 0.5F, (float)y + 0.3F + bobPoint * 0.2F, (float)z + 0.5F);
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.rotatef(-look.y, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef(look.x, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
            this.eyeModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.083333336F);
            GlStateManager.popMatrix();
        }

        super.render(conduit, x, y, z, partialTicks, p_render_9_);
    }

    static class EyeModel extends ModelBase {
        private final ModelRenderer renderer;

        public EyeModel() {
            this.textureWidth = 8;
            this.textureHeight = 8;
            this.renderer = new ModelRenderer(this, 0, 0);
            this.renderer.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.01F);
        }

        public void render(Entity conduit, float x, float y, float z, float p_render_5_, float p_render_6_, float p_render_7_) {
            this.renderer.render(p_render_7_);
        }
    }

    static class WindModel extends ModelBase {
        public static int windCount = 22;
        private final ModelRenderer[] renderer;
        private int frame;

        public WindModel() {
            this.renderer = new ModelRenderer[windCount];
            this.textureWidth = 64;
            this.textureHeight = 1024;

            for(int i = 0; i < windCount; ++i) {
                this.renderer[i] = new ModelRenderer(this, 0, 32 * i);
                this.renderer[i].addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
            }

        }

        public void render(Entity conduit, float x, float y, float z, float p_render_5_, float p_render_6_, float p_render_7_) {
            this.renderer[this.frame].render(p_render_7_);
        }

        public void setFrame(int frame) {
            this.frame = frame;
        }
    }

    static class CageModel extends ModelBase {
        private final ModelRenderer renderer;

        public CageModel() {
            this.textureWidth = 32;
            this.textureHeight = 16;
            this.renderer = new ModelRenderer(this, 0, 0);
            this.renderer.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
        }

        public void render(Entity conduit, float x, float y, float z, float p_render_5_, float p_render_6_, float p_render_7_) {
            this.renderer.render(p_render_7_);
        }
    }

    static class ShellModel extends ModelBase {
        private final ModelRenderer renderer;

        public ShellModel() {
            this.textureWidth = 32;
            this.textureHeight = 16;
            this.renderer = new ModelRenderer(this, 0, 0);
            this.renderer.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
        }

        public void render(Entity conduit, float x, float y, float z, float p_render_5_, float p_render_6_, float p_render_7_) {
            this.renderer.render(p_render_7_);
        }
    }
}
