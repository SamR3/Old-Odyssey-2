package nekto.odyssey.mobs.render;

import nekto.odyssey.mobs.core.EntityReaver;
import nekto.odyssey.mobs.render.model.ModelReaver;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

public class RenderReaver extends RenderBiped
{
    protected ModelReaver reaverModel;

    public RenderReaver()
    {
        super(new ModelReaver(), 0.5F);
        reaverModel = (ModelReaver) mainModel;
    }

    protected int shouldReaverRenderPass(EntityReaver par1EntityReaver, int par2, float par3)
    {
        return -1;
    }

    public void renderReaver(EntityReaver par1EntityReaver, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityReaver, par2, par4, par6, par8, par9);
    }

    protected void renderReaverEquipedItems(EntityReaver par1EntityReaver, float par2)
    {
        super.renderEquippedItems(par1EntityReaver, par2);
    }

    protected void preRenderReaver(EntityReaver par1EntityReaver, float par2)
    {
        float f1 = 0.9375F;
        
        shadowSize = 0.5F;
    
        GL11.glScalef(f1, f1, f1);
    }

    @Override
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.preRenderReaver((EntityReaver) par1EntityLiving, par2);
    }

    @Override
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.shouldReaverRenderPass((EntityReaver) par1EntityLiving, par2, par3);
    }

    @Override
    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        this.renderReaverEquipedItems((EntityReaver) par1EntityLiving, par2);
    }

    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderReaver((EntityReaver) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderReaver((EntityReaver) par1Entity, par2, par4, par6, par8, par9);
    }
}
