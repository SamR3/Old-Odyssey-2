package nekto.odyssey.network.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import nekto.odyssey.api.IOdysseyPacket;
import nekto.odyssey.entity.EntityLaser;
import nekto.odyssey.turret.tile.TileEntityTurret;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketSentryShot extends Packet250CustomPayload implements IOdysseyPacket
{
    @Override
    public void handle(Packet250CustomPayload packet, EntityPlayer player)
    {
        DataInputStream inStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        int[] data = new int[packet.data.length / 4];

        try
        {
            for (int id = 0; id < data.length; id++)
                data[id] = inStream.readInt();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        TileEntityTurret tile = (TileEntityTurret) player.worldObj.getBlockTileEntity(data[0], data[1], data[2]);

        if (tile != null && tile.target != null && !tile.target.isDead)
        {
            /*
             * if(tile.target instanceof EntitySheep) {
             * tile.getTarget().setFire(15); } else { tile.getTarget().motionY =
             * 5.0F; }
             */
            player.worldObj.spawnEntityInWorld(new EntityLaser(player.worldObj, tile));
        }
    }
}
