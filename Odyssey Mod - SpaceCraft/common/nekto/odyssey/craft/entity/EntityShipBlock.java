package nekto.odyssey.craft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityShipBlock extends Entity
{
    public int blockID = 139;
    public int meta;

    public EntityShipBlock(World par1World)
    {
        super(par1World);

        setSize(1.0F, 1.0F);
        entityCollisionReduction = 1;
    }

    public EntityShipBlock(World par1World, int x, int y, int z)
    {
        super(par1World);

        setSize(1.0F, 1.0F);
        entityCollisionReduction = 1;

        this.setLocationAndAngles(x, y, z, 0, 0);
    }

    public boolean canBeCollidedWith()
    {
        return true;
    }

    public void onEntityUpdate()
    {
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }

    @Override
    public boolean func_130002_c(EntityPlayer entityplayer)
    {
        this.rejoinWorld();
        return true;
    }

    public void rejoinWorld()
    {
        worldObj.setBlock((int) Math.round(posX - 0.5), (int) Math.round(posY - 0.5), (int) Math.round(posZ - 0.5), this.blockID, this.meta, 2);

        this.setDead();
    }
}
