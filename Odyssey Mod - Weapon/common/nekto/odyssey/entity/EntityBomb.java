package nekto.odyssey.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBomb extends Entity
{
    private boolean isDropped;

    private static final float EXPLOSION_MAGNITUDE = 3.0F;

    public EntityBomb(World par1World)
    {
        super(par1World);
        this.isDropped = false;
        this.setVelocity(0.0F, 0.0F, 0.0F);
    }

    public EntityBomb(World par1World, double par2, double par4, double par6)
    {
        super(par1World);
        this.setPosition(par2, par4, par6);
        this.isDropped = false;
        this.setVelocity(0.0F, 0.0F, 0.0F);
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    public void onEntityUpdate()
    {
        if (this.motionY > -9.8)
        {
            this.addVelocity(0, -0.49D, 0);
        }

        if (this.isCollided || this.isInWater())
        {
            this.explode();
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        this.isDropped = nbttagcompound.getBoolean("dropped");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setBoolean("dropped", this.isDropped);
    }

    public void drop()
    {
        this.isDropped = true;
    }

    private void explode()
    {
        this.setDead();
        this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, EXPLOSION_MAGNITUDE, true, true);
    }
}
