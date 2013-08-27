package nekto.space.odyssey.core;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public interface ITeleportType
{
    /**
     * This method is used to determine if a player will open parachute upon
     * entering the dimension
     * 
     * @return whether player will set parachute open upon entering this
     *         dimension
     */
    public boolean useParachute();

    /**
     * Gets the player spawn location when entering this dimension
     * 
     * @param world
     *            The world to be spawned into
     * @param player
     *            The player to be teleported
     * @return a Vec3 object containing the coordinates to be spawned into the
     *         world with
     */
    public Vec3 getPlayerSpawnLocation(WorldServer world, EntityPlayerMP player);

    /**
     * Gets the entity (non-player) spawn location when entering this dimension
     * 
     * @param world
     *            The world to be spawned into
     * @param entity
     *            the non-player entity to be teleported
     * @return a Vec3 object containing the coordinates to be spawned into the
     *         world with
     */
    public Vec3 getEntitySpawnLocation(WorldServer world, Entity entity);

    /**
     * Gets the parachest spawn location when entering this dimension. Return
     * null for no parachest spawn
     * 
     * @param world
     *            The world to be spawned into
     * @param chest
     *            the parachest to be teleported
     * @return a Vec3 object containing the coordinates to be spawned into the
     *         world with. Return null for no spawn
     */
    public Vec3 getParaChestSpawnLocation(WorldServer world, Entity chest, EntityPlayerMP player, Random rand);

    public void onSpaceDimensionChanged(World newWorld, EntityPlayerMP player);
}
