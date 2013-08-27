package nekto.odyssey.render;

import nekto.odyssey.render.model.ModelPlasmaBomb;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class RenderBomb extends Render
{

    private ModelPlasmaBomb model;

    public RenderBomb()
    {
        this.model = new ModelPlasmaBomb();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float f, float f1)
    {
        GL11.glPushMatrix();

        this.renderManager.renderEngine.bindTexture("sdklfjlskd");

        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glPushMatrix();

        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        this.model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

}
