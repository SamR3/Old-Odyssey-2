package nekto.odyssey.turret.systems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nekto.odyssey.api.ISystem;
import nekto.odyssey.turret.tile.TileEntityTurret;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TargetingSystem implements ISystem
{
    private TileEntityTurret turret;
    private Entity target;

    public TargetingSystem(TileEntityTurret tile)
    {
        this.turret = tile;
    }

    @SuppressWarnings("unchecked")
    public Entity doFindTarget()
    {
        double range = this.turret.getDetectionRange();
        List<Entity> entityList = this.turret.worldObj.getLoadedEntityList();
        Map<Double, Entity> targetList = new HashMap<Double, Entity>();
        double shortDist = range;

        for (Entity ent : entityList)
        {
            if (ent instanceof EntityLiving && !(ent instanceof EntityPlayer || ent instanceof EntityPlayerMP) && !ent.isDead)
            {
                targetList.put(ent.getDistance(this.turret.xCoord, this.turret.yCoord, this.turret.zCoord), ent);
            }
        }

        for (Double dist : targetList.keySet())
        {
            if (dist < shortDist)
            {
                shortDist = dist;
            }
        }

        this.target = targetList.get(shortDist);

        return this.target;
    }

    @Override
    public String getIdentifier()
    {
        return "Targeting System";
    }
}