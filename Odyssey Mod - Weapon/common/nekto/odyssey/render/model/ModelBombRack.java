package nekto.odyssey.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBombRack extends ModelBase
{
    // fields
    ModelRenderer Rack;
    ModelRenderer Arm1;
    ModelRenderer Arm2;
    ModelRenderer Arm3;
    ModelRenderer Arm4;

    public ModelBombRack()
    {
        textureWidth = 64;
        textureHeight = 64;
        setTextureOffset("Rack.Rail1", 0, 0);
        setTextureOffset("Rack.Rail2", 0, 0);
        setTextureOffset("Rack.Crossbar1", 0, 0);
        setTextureOffset("Rack.Crossbar2", 0, 0);
        setTextureOffset("Rack.Top", 0, 0);
        setTextureOffset("Rack.Stand1", 0, 0);
        setTextureOffset("Rack.Stand2", 0, 0);
        setTextureOffset("Arm1.Arm11", 0, 0);
        setTextureOffset("Arm1.Lip11", 0, 0);
        setTextureOffset("Arm2.Arm21", 0, 0);
        setTextureOffset("Arm2.Lip21", 0, 0);
        setTextureOffset("Arm3.Arm31", 0, 0);
        setTextureOffset("Arm3.Lip31", 0, 0);
        setTextureOffset("Arm4.Arm41", 0, 0);
        setTextureOffset("Arm4.Lip41", 0, 0);

        Rack = new ModelRenderer(this, "Rack");
        Rack.setRotationPoint(-5F, 15F, -8F);
        setRotation(Rack, 0F, 0F, 0F);
        Rack.mirror = true;
        Rack.addBox("Rail1", -1F, 0F, 1F, 2, 2, 14);
        Rack.addBox("Rail2", 9F, 0F, 1F, 2, 2, 14);
        Rack.addBox("Crossbar1", 0F, 0F, 13F, 10, 1, 2);
        Rack.addBox("Crossbar2", 0F, 0F, 1F, 10, 1, 3);
        Rack.addBox("Top", 0F, -1F, 2F, 10, 1, 12);
        Rack.addBox("Stand1", 9F, 1F, 3F, 2, 8, 10);
        Rack.addBox("Stand2", -1F, 1F, 3F, 2, 8, 10);
        Arm1 = new ModelRenderer(this, "Arm1");
        Arm1.setRotationPoint(-4F, 15F, -8F);
        setRotation(Arm1, 0F, 0F, 0F);
        Arm1.mirror = true;
        Arm1.addBox("Arm11", -1F, 1F, 13F, 1, 8, 2);
        Arm1.addBox("Lip11", 0F, 8F, 13F, 1, 1, 2);
        Arm2 = new ModelRenderer(this, "Arm2");
        Arm2.setRotationPoint(4F, 16F, 5F);
        setRotation(Arm2, 0F, 0F, 0F);
        Arm2.mirror = true;
        Arm2.addBox("Arm21", 0F, 0F, 0F, 1, 8, 2);
        Arm2.addBox("Lip21", -1F, 7F, 0F, 1, 1, 2);
        Arm3 = new ModelRenderer(this, "Arm3");
        Arm3.setRotationPoint(-5F, 16F, -7F);
        setRotation(Arm3, 0F, 0F, 0F);
        Arm3.mirror = true;
        Arm3.addBox("Arm31", 0F, 0F, 0F, 1, 8, 2);
        Arm3.addBox("Lip31", 1F, 7F, 0F, 1, 1, 2);
        Arm4 = new ModelRenderer(this, "Arm4");
        Arm4.setRotationPoint(4F, 16F, -7F);
        setRotation(Arm4, 0F, 0F, 0F);
        Arm4.mirror = true;
        Arm4.addBox("Arm41", 0F, 0F, 0F, 1, 8, 2);
        Arm4.addBox("Lip41", -1F, 7F, 0F, 1, 1, 2);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Rack.render(f5);
        Arm1.render(f5);
        Arm2.render(f5);
        Arm3.render(f5);
        Arm4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
