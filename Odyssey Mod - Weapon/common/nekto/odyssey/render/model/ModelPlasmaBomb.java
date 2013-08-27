package nekto.odyssey.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPlasmaBomb extends ModelBase
{
    // fields
    ModelRenderer Body;
    ModelRenderer Nose;
    ModelRenderer Tail;

    public ModelPlasmaBomb()
    {
        textureWidth = 64;
        textureHeight = 64;
        setTextureOffset("Body.Body1", 0, 0);
        setTextureOffset("Body.Body2", 0, 0);
        setTextureOffset("Nose.Nose1", 0, 0);
        setTextureOffset("Nose.Nose2", 0, 0);
        setTextureOffset("Tail.Baseplate", 0, 0);
        setTextureOffset("Tail.Nozzle1", 0, 0);
        setTextureOffset("Tail.Nozzle2", 0, 0);

        Body = new ModelRenderer(this, "Body");
        Body.setRotationPoint(-3F, 16F, -10F);
        setRotation(Body, 0F, 0F, 0F);
        Body.mirror = true;
        Body.addBox("Body1", 0F, 0F, 0F, 6, 8, 16);
        Body.addBox("Body2", -1F, 1F, 0F, 8, 6, 16);
        Nose = new ModelRenderer(this, "Nose");
        Nose.setRotationPoint(-3F, 17F, 6F);
        setRotation(Nose, 0F, 0F, 0F);
        Nose.mirror = true;
        Nose.addBox("Nose1", 0F, 0F, 0F, 6, 6, 1);
        Nose.addBox("Nose2", 0.5F, 0.5F, 1F, 5, 5, 1);
        Tail = new ModelRenderer(this, "Tail");
        Tail.setRotationPoint(-1F, 17F, -13F);
        setRotation(Tail, 0F, 0F, 0F);
        Tail.mirror = true;
        Tail.addBox("Baseplate", -2F, 0F, 2F, 6, 6, 1);
        Tail.addBox("Nozzle1", -1F, 1F, 0F, 4, 4, 2);
        Tail.addBox("Nozzle2", 0F, 2F, -1F, 2, 2, 1);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        Nose.render(f5);
        Tail.render(f5);
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
