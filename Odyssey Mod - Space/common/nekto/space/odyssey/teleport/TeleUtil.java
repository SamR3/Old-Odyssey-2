package nekto.space.odyssey.teleport;

import nekto.space.odyssey.core.ITeleportType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet43Experience;
import net.minecraft.network.packet.Packet9Respawn;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class TeleUtil
{
    private static void removeEntityFromWorld(World var0, Entity var1)
    {
        if (var1 instanceof EntityPlayer)
        {
            final EntityPlayer var2 = (EntityPlayer) var1;
            var2.closeScreen();
            var0.playerEntities.remove(var2);
            var0.updateAllPlayersSleepingFlag();
            final int var3 = var1.chunkCoordX;
            final int var4 = var1.chunkCoordZ;

            if (var1.addedToChunk && var0.getChunkProvider().chunkExists(var3, var4))
            {
                var0.getChunkFromChunkCoords(var3, var4).removeEntity(var1);
                var0.getChunkFromChunkCoords(var3, var4).isModified = true;
            }

            var0.loadedEntityList.remove(var1);
            var0.releaseEntitySkin(var1);
        }

        var1.isDead = false;
    }

    public static void transferEntityToDimension(Entity entity, int dimensionID, WorldServer world)
    {
        TeleUtil.transferEntityToDimension(entity, dimensionID, world, true);
    }

    public static void transferEntityToDimension(Entity entity, int dimensionID, WorldServer world, boolean transferInv)
    {
        if (!world.isRemote)
        {
            MinecraftServer mcServer = FMLCommonHandler.instance().getMinecraftServerInstance();

            if (mcServer != null)
            {
                final WorldServer var6 = mcServer.worldServerForDimension(dimensionID);
                
                final ITeleportType type = new TeleportSpace();

                if (type != null)
                {
                    TeleUtil.teleportEntity(var6, entity, dimensionID, type, transferInv);
                }
            }
        }
    }

    private static Entity teleportEntity(World var0, Entity var1, int var2, ITeleportType type, boolean transferInv)
    {
        final boolean var7 = var1.worldObj != var0;
        var1.worldObj.updateEntityWithOptionalForce(var1, false);
        EntityPlayerMP var8 = null;

        if (var1 instanceof EntityPlayerMP)
        {
            var8 = (EntityPlayerMP) var1;
            var8.closeScreen();

            if (var7)
            {
                var8.dimension = var2;
                var8.playerNetServerHandler.sendPacketToPlayer(new Packet9Respawn(var8.dimension, (byte) var8.worldObj.difficultySetting, var0.getWorldInfo().getTerrainType(), var0.getHeight(), var8.theItemInWorldManager.getGameType()));

                ((WorldServer) var1.worldObj).getPlayerManager().removePlayer(var8);
            }
        }

        if (var7)
        {
            TeleUtil.removeEntityFromWorld(var1.worldObj, var1);
        }

        if (var7)
        {
            if (var1 instanceof EntityPlayerMP)
            {
                var1.setLocationAndAngles(type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).xCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).yCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).zCoord, var1.rotationYaw, var1.rotationPitch);
                ((WorldServer) var0).theChunkProviderServer.loadChunk(var0.getChunkFromChunkCoords((int)type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).xCoord, (int)type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).zCoord).getChunkCoordIntPair().chunkXPos, var0.getChunkFromChunkCoords((int)type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).xCoord, (int)type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).zCoord).getChunkCoordIntPair().chunkZPos);
            }
        }

        if (var7)
        {
            if (var1 instanceof EntityPlayer)
            {
                var1.setPosition(type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).xCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).yCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).zCoord);
            }
        }

        if (var7)
        {
            if (!(var1 instanceof EntityPlayer))
            {
                final NBTTagCompound var11 = new NBTTagCompound();
                var1.isDead = false;
                var1.addEntityID(var11);
                var1.isDead = true;
                var1 = EntityList.createEntityFromNBT(var11, var0);

                if (var1 == null)
                {
                    return null;
                }
            }

            var0.spawnEntityInWorld(var1);
            var1.setWorld(var0);
        }

        if (var7)
        {
            if (var1 instanceof EntityPlayer)
            {
                var1.setLocationAndAngles(type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).xCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).yCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).zCoord, var1.rotationYaw, var1.rotationPitch);
            }
        }

        var0.updateEntityWithOptionalForce(var1, false);

        if (var7)
        {
            if (var1 instanceof EntityPlayer)
            {
                var1.setLocationAndAngles(type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).xCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).yCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).zCoord, var1.rotationYaw, var1.rotationPitch);
            }
        }

        if (var1 instanceof EntityPlayerMP)
        {
            var8 = (EntityPlayerMP) var1;

            if (var7)
            {
                var8.mcServer.getConfigurationManager().func_72375_a(var8, (WorldServer) var0);
            }

            var8.playerNetServerHandler.setPlayerLocation(type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).xCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).yCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).zCoord, var1.rotationYaw, var1.rotationPitch);
        }

        var0.updateEntityWithOptionalForce(var1, false);

        if (var1 instanceof EntityPlayerMP && var7)
        {
            var8 = (EntityPlayerMP) var1;
            var8.theItemInWorldManager.setWorld((WorldServer) var0);
            var8.mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(var8, (WorldServer) var0);
            var8.mcServer.getConfigurationManager().syncPlayerInventory(var8);

            var8.playerNetServerHandler.sendPacketToPlayer(new Packet43Experience(var8.experience, var8.experienceTotal, var8.experienceLevel));
        }

        if (var8 != null)
        {
            var1.setLocationAndAngles(type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).xCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).yCoord, type.getPlayerSpawnLocation((WorldServer) var1.worldObj, (EntityPlayerMP) var1).zCoord, var1.rotationYaw, var1.rotationPitch);
        }
        else
        {
            var1.setLocationAndAngles(type.getEntitySpawnLocation((WorldServer) var1.worldObj, var1).xCoord, type.getEntitySpawnLocation((WorldServer) var1.worldObj, var1).yCoord, type.getEntitySpawnLocation((WorldServer) var1.worldObj, var1).zCoord, var1.rotationYaw, var1.rotationPitch);
        }

        if (var1 instanceof EntityPlayerMP)
        {
            GameRegistry.onPlayerChangedDimension((EntityPlayerMP) var1);

            type.onSpaceDimensionChanged(var0, (EntityPlayerMP) var1);
        }
        
        return var1;
    }
}
