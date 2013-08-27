package nekto.odyssey.entity;

import java.util.List;

import nekto.odyssey.core.OdysseyCore;
import nekto.odyssey.turret.tile.TileEntityTurret;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityLaser extends EntityThrowable
{
    private float explosionRadius = 1.0F;

    public EntityLaser(World par1World)
    {
        super(par1World);
        setSpeed();
    }

    public EntityLaser(World par1World, EntityLiving turret)
    {
        super(par1World, turret);
        setSpeed();
    }

    public EntityLaser(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        setSpeed();
    }

    public EntityLaser(World par1World, TileEntityTurret turret)
    {
        super(par1World);

        /*
         * TODO: BUG: Entity can be created after turret is destroyed, causing a
         * crash.
         */

        this.setSize(0.25F, 0.25F);

        float f = -MathHelper.sin(turret.getHover() / 10.0F) * 0.03F;

        this.posX = turret.xCoord + 0.5;
        this.posY = turret.yCoord + 0.72 + f;
        this.posZ = turret.zCoord + 0.5;

        double d0 = turret.target.posX - turret.xCoord;
        double d1 = turret.target.posY - turret.yCoord;
        double d2 = turret.target.posZ - turret.zCoord;

        this.setLocationAndAngles(this.posX, this.posY, this.posZ, turret.getYaw(), turret.getPitch());

        yOffset = 0.0F;

        this.setThrowableHeading(d0, d1, d2, 1.0F, 1.0F);

        // setSpeed();
    }

    protected void setSpeed()
    {
        setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0F, 1.0F);
    }

    @Override
    public float getBrightness(float par1)
    {
        return 10.0F;
    }

    private int[][] destroyedBlocks = { { 1, -1, 1 }, { 1, -1, -1 }, { 1, -1, 0 }, { 1, 0, 1 }, { 1, 0, -1 }, { 1, 0, 0 }, { 1, 1, 1 }, { 1, 1, -1 }, { 1, 1, 0 }, { -1, -1, 1 }, { -1, -1, -1 },
            { -1, -1, 0 }, { -1, 0, 1 }, { -1, 0, -1 }, { -1, 0, 0 }, { -1, 1, 1 }, { -1, 1, -1 }, { -1, 1, 0 }, { 0, -1, 1 }, { 0, -1, -1 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 }, { 0, 0, 0 },
            { 0, 1, 1 }, { 0, 1, -1 }, { 0, 1, 0 } };

    @Override
    protected void onImpact(MovingObjectPosition movingobjectposition)
    {
        if (this.worldObj.getBlockId(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) != OdysseyCore.instance.turret.blockID)
        {
            for (int i = 0; i < destroyedBlocks.length; i++)
            {
                if (this.worldObj.getBlockId(movingobjectposition.blockX + destroyedBlocks[i][0], movingobjectposition.blockY + destroyedBlocks[i][1], movingobjectposition.blockZ
                        + destroyedBlocks[i][2]) != 7)
                    this.worldObj.setBlockToAir(movingobjectposition.blockX + destroyedBlocks[i][0], movingobjectposition.blockY + destroyedBlocks[i][1], movingobjectposition.blockZ
                            + destroyedBlocks[i][2]);
            }

            Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            
            Entity entity = null;
            List<?> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            int l;
            float f1;

            for (l = 0; l < list.size(); ++l)
            {
                Entity entity1 = (Entity) list.get(l);

                if (entity1.canBeCollidedWith())
                {
                    f1 = 0.3F;
                    AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double) f1, (double) f1, (double) f1);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null)
                    {
                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            float f2;
            if (movingobjectposition != null)
            {
                if (movingobjectposition.entityHit != null)
                {
                    f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    int i1 = MathHelper.ceiling_double_int((double) f2 * this.explosionRadius);

                    DamageSource damagesource = null;

                    damagesource = DamageSource.causeThrownDamage(this, null);

                    if (movingobjectposition.entityHit.attackEntityFrom(damagesource, i1))
                    {
                        if (movingobjectposition.entityHit instanceof EntityLiving)
                        {
                            int[] data = { movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ, movingobjectposition.entityHit.entityId, this.entityId };

                            OdysseyCore.proxy.sendHitToServer(this, data);
                            this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, true, true);
                        }
                    }
                }
            }

            this.setDead();
        }
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0;
    }
}