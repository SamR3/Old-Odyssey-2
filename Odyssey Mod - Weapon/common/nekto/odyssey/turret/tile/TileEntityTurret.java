package nekto.odyssey.turret.tile;

import nekto.odyssey.api.ITurret;
import nekto.odyssey.core.OdysseyCore;
import nekto.odyssey.turret.systems.RotationSystem;
import nekto.odyssey.turret.systems.TargetingSystem;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTurret extends TileEntity implements ITurret
{
    private final int RANGE = 10;

    private TargetingSystem targetingSystem = new TargetingSystem(this);
    private RotationSystem turnSystem = new RotationSystem(this);

    private float pitch = 0.0F;
    private float yaw = 0.0F;
    
    private boolean isLooking;

    public Entity target;

    private final float COOLDOWN_SECONDS = 1;
    private int cooldownCounter = 0;

    private int counter = 0;

    @Override
    public void aim()
    {
        while (this.target == null || this.target.isDead)
        {
            this.target = this.targetingSystem.doFindTarget();
        }
    }

    @Override
    public void updateEntity()
    {
        if (this.target != null && !this.target.isDead && this.getDistanceFrom(this.target.posX, this.target.posY, this.target.posZ) <= this.getDetectionRange())
        {
            this.turnSystem.doTurn();

            if (this.cooldownCounter > 0)
            {
                this.cooldownCounter--;
            } else
            {
                if(this.isLooking)
                {
                    this.fire();
                }
            }
        } else
        {
            this.target = null;
            this.aim();
        }

        counter++;
    }

    public int getHover()
    {
        return this.counter;
    }

    public void fire()
    {
        int data[] = { this.xCoord, this.yCoord, this.zCoord };

        OdysseyCore.proxy.sendShotToServer(this, data);
        this.worldObj.playSound((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "mods.odyssey.sounds.laser", 100, 0, true);
        
        this.cooldownCounter = (int) this.COOLDOWN_SECONDS * 20;
    }

    @Override
    public Entity getTarget()
    {
        return this.target;
    }

    @Override
    public int getDetectionRange()
    {
        return RANGE * 10;
    }

    public void setYaw(float par1)
    {
        this.yaw = par1;
        this.isLooking = true;
    }

    public void setPitch(float par1)
    {
        if (par1 <= 30 && par1 >= -30)
        {
            this.pitch = par1;
            this.isLooking = true;
        } else {
            this.isLooking = false;
        }
    }

    public float getYaw()
    {
        return this.yaw;
    }

    public float getPitch()
    {
        return this.pitch;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setFloat("yaw", this.getYaw());

        if (this.target != null)
        {
            nbt.setInteger("target", this.target.entityId);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.yaw = nbt.getFloat("yaw");
        this.target = this.worldObj.getEntityByID(nbt.getInteger("target"));
    }
}
