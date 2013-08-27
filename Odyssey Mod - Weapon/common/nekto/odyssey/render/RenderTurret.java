package nekto.odyssey.render;

import nekto.odyssey.render.model.ModelTurretSmall;
import nekto.odyssey.turret.tile.TileEntityTurret;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class RenderTurret extends TileEntitySpecialRenderer
{
    public ModelTurretSmall model;

    public RenderTurret()
    {
        this.model = new ModelTurretSmall();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();

        this.bindTextureByName("/mods/odyssey/textures/blocks/SmallTurretTexture.png");

        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glPushMatrix();

        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        renderBarrel((TileEntityTurret) tileentity);

        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

    private void renderBarrel(TileEntityTurret tileentity)
    {
        float f = MathHelper.sin(tileentity.getHover() / 10.0F) * 0.03F;

        GL11.glRotatef(((TileEntityTurret) tileentity).getYaw(), 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, f, 0.0F);

        this.model.renderCannon(0.0625F, -(((TileEntityTurret) tileentity).getPitch() / 90));
    }
}
