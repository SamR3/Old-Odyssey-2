package nekto.space.odyssey.teleport;

import java.util.Random;

import nekto.space.odyssey.core.ITeleportType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleportSpace implements ITeleportType
{
    @Override
    public boolean useParachute()
    {
        return false;
    }

    @Override
    public Vec3 getPlayerSpawnLocation(WorldServer world, EntityPlayerMP player)
    {
        return Vec3.createVectorHelper(0.5, 65.0, 0.5);
    }

    @Override
    public Vec3 getEntitySpawnLocation(WorldServer world, Entity entity)
    {
        return Vec3.createVectorHelper(0.5, 65.0, 0.5);
    }

    @Override
    public Vec3 getParaChestSpawnLocation(WorldServer world, Entity chest, EntityPlayerMP player, Random rand)
    {
        return null;
    }

    @Override
    public void onSpaceDimensionChanged(World newWorld, EntityPlayerMP player)
    {
    }

}
