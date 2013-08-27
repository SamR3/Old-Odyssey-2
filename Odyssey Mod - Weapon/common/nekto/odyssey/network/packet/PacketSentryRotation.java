package nekto.odyssey.network.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import nekto.odyssey.api.IOdysseyPacket;
import nekto.odyssey.turret.tile.TileEntityTurret;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketSentryRotation extends Packet250CustomPayload implements IOdysseyPacket
{
    @Override
    public void handle(Packet250CustomPayload packet, EntityPlayer player)
    {
        DataInputStream inStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        int[] data = new int[3];
        float yaw;
        float pitch;

        try
        {
            data[0] = inStream.readInt();
            data[1] = inStream.readInt();
            data[2] = inStream.readInt();

            yaw = inStream.readFloat();
            pitch = inStream.readFloat();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        World world = player.worldObj;

        if (world != null)
        {
            TileEntity tile = world.getBlockTileEntity(data[0], data[1], data[2]);

            if (tile != null)
            {
                if (tile instanceof TileEntityTurret)
                {
                    ((TileEntityTurret) tile).setYaw(yaw);
                    ((TileEntityTurret) tile).setPitch(pitch);
                }
            }
        }
    }
}
