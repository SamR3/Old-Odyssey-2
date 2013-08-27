package nekto.odyssey.render;

import nekto.odyssey.ref.EnumLaserColor;
import nekto.odyssey.ref.EnumLaserType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderLaser extends Render
{
    private EnumLaserType renderType;
    private EnumLaserColor color;

    public RenderLaser(EnumLaserType type, EnumLaserColor laserColor)
    {
        this.renderType = type;
        this.color = laserColor;
    }

    @Override
    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1)
    {
        int red;
        int green;
        int blue;

        int alpha = 255;

        switch (color)
        {
            case RED:
                red = 255;
                green = 0;
                blue = 0;
                break;

            case GREEN:
                red = 0;
                green = 255;
                blue = 0;
                break;

            case BLUE:
                red = 0;
                green = 0;
                blue = 255;
                break;

            case PURPLE:
                red = 255;
                green = 0;
                blue = 255;
                break;

            case WHITE:
                red = 255;
                green = 255;
                blue = 255;
                break;

            default:
                red = 0;
                green = 0;
                blue = 0;
                break;
        }

        this.loadTexture("/item/arrows.png");
        GL11.glPushMatrix();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        GL11.glTranslatef((float) d0, (float) d1, (float) d2);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f1 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        float f10 = 0.04625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f11 = -f1;

        if (f11 > 0.0F)
        {
            float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
            GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
        }

        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-2.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);

        switch (this.renderType)
        {
            case STANDARD_SMALL:
                for (int i = 0; i < 72; ++i)
                {
                    GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glNormal3f(0.0F, 0.0F, f10);
                    tessellator.startDrawingQuads();
                    tessellator.setColorRGBA(red, green, blue, alpha);
                    tessellator.addVertex(-8.0D, -1.0D, 0.0D);
                    tessellator.addVertex(8.0D, -1.0D, 0.0D);
                    tessellator.addVertex(8.0D, 1.0D, 0.0D);
                    tessellator.addVertex(-8.0D, 1.0D, 0.0D);
                    tessellator.draw();
                }
                break;

            default:
                break;
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

}