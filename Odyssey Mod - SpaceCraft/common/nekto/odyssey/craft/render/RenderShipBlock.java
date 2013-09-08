package nekto.odyssey.craft.render;

import nekto.odyssey.craft.entity.EntityShipBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderShipBlock extends Render
{

    @Override
    public void doRender(Entity entity, double realX, double realY, double realZ, float rotationYaw, float entityBrightness)
    {
        GL11.glPushMatrix();

        EntityShipBlock blockEntity = (EntityShipBlock) entity;

        float rotation = rotationYaw;

        int blockID = blockEntity.blockID;

        RenderBlocks renderBlocks = Minecraft.getMinecraft().renderGlobal.globalRenderBlocks;

        Block renderThisBlock = Block.blocksList[blockID];

        GL11.glTranslatef((float) realX, (float) realY, (float) realZ);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        // GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        GL11.glTranslatef((float) -blockEntity.posX, (float) -blockEntity.posY, (float) -blockEntity.posZ);
        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.setNormal(0.0F, 1.0F, 0.0F);

        renderBlocks.renderBlockByRenderType(renderThisBlock, (int) blockEntity.posX, (int) blockEntity.posY, (int) blockEntity.posZ);

        Tessellator.instance.draw();
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
