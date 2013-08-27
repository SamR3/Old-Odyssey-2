package nekto.odyssey.network.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import nekto.odyssey.api.IOdysseyPacket;
import nekto.odyssey.core.OdysseyDamageSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketSentryHit extends Packet250CustomPayload implements IOdysseyPacket
{
    @Override
    public void handle(Packet250CustomPayload packet, EntityPlayer player)
    {
        DataInputStream inStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        int[] data = new int[6];

        try
        {
            data[0] = inStream.readInt();
            data[1] = inStream.readInt();
            data[2] = inStream.readInt();
            data[3] = inStream.readInt();
            data[4] = inStream.readInt();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        if (data[3] != -1)
        {
            player.worldObj.getEntityByID(data[3]).attackEntityFrom(OdysseyDamageSource.laser, 1000);
        }
    }
}
