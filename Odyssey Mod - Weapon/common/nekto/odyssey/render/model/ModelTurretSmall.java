package nekto.odyssey.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTurretSmall extends ModelBase
{
    // fields
    ModelRenderer Base;
    ModelRenderer Stand;
    ModelRenderer Cannon;

    public ModelTurretSmall()
    {
        textureWidth = 64;
        textureHeight = 64;
        setTextureOffset("Base.Base1", 0, 0);
        setTextureOffset("Base.Base2", 0, 18);
        setTextureOffset("Base.Base3", 0, 35);
        setTextureOffset("Base.Base4", 0, 35);
        setTextureOffset("Base.Base5", 0, 35);
        setTextureOffset("Stand.Stand1", 0, 37);
        setTextureOffset("Stand.Stand2", 0, 50);
        setTextureOffset("Stand.Side1", 34, 21);
        setTextureOffset("Stand.Side2", 34, 21);
        setTextureOffset("Stand.Side3", 34, 21);
        setTextureOffset("Stand.Side4", 34, 18);
        setTextureOffset("Cannon.Barrel1", 0, 0);
        setTextureOffset("Cannon.Barrel2", 0, 0);
        setTextureOffset("Cannon.Back1", 0, 0);
        setTextureOffset("Cannon.Back2", 0, 0);
        setTextureOffset("Cannon.Back3", 0, 0);
        setTextureOffset("Cannon.Back4", 0, 0);

        Base = new ModelRenderer(this, "Base");
        Base.setRotationPoint(0F, 0F, 0F);
        setRotation(Base, 0F, 0.0174533F, 0F);
        Base.mirror = true;
        Base.addBox("Base1", -8F, 22F, -8F, 16, 2, 16);
        Base.addBox("Base2", -8F, 21F, -8F, 1, 1, 16);
        Base.addBox("Base3", -8F, 21F, -8F, 16, 1, 1);
        Base.addBox("Base4", 7F, 21F, -8F, 1, 1, 16);
        Base.addBox("Base5", -8F, 21F, 7F, 16, 1, 1);
        Stand = new ModelRenderer(this, "Stand");
        Stand.setRotationPoint(0F, 0F, 0F);
        setRotation(Stand, 0F, 0F, 0F);
        Stand.mirror = true;
        Stand.addBox("Stand1", -5F, 19F, -5F, 10, 3, 10);
        Stand.addBox("Stand2", -4F, 18F, -4F, 8, 1, 8);
        Stand.addBox("Side1", 5F, 20F, -2F, 1, 2, 4);
        Stand.addBox("Side2", -2F, 20F, 5F, 4, 2, 1);
        Stand.addBox("Side3", -6F, 20F, -2F, 1, 2, 4);
        Stand.addBox("Side4", -2F, 20F, -6F, 4, 2, 1);
        Cannon = new ModelRenderer(this, "Cannon");
        Cannon.setRotationPoint(-8F, 11F, -1F);
        setRotation(Cannon, 0F, 0F, 0F);
        Cannon.mirror = true;
        Cannon.addBox("Barrel1", 0F, 0F, 0F, 18, 2, 2);
        Cannon.addBox("Barrel2", 2F, -1F, -1F, 12, 4, 4);
        Cannon.addBox("Back1", 1F, -1F, -1F, 1, 1, 1);
        Cannon.addBox("Back2", 1F, -1F, 2F, 1, 1, 1);
        Cannon.addBox("Back3", 1F, 2F, -1F, 1, 1, 1);
        Cannon.addBox("Back4", 1F, 2F, 2F, 1, 1, 1);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Base.render(f5);
        Stand.render(f5);
    }

    public void renderCannon(float f, float rotation)
    {
        Cannon.render(f);
        setRotation(Cannon, 0.0F, 0.0F, rotation);
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
