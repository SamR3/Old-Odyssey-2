package nekto.odyssey.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import nekto.odyssey.network.packet.PacketSentryHit;
import nekto.odyssey.network.packet.PacketSentryShot;
import nekto.odyssey.ref.GeneralRef;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommonProxy
{
    public void init()
    {
    }

    public void registerRenderers()
    {
    }

    public void sendShotToServer(TileEntity ent, int... data)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(4 * data.length);
        DataOutputStream outputStream = new DataOutputStream(bos);

        try
        {
            for (int d : data)
                outputStream.writeInt(d);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        PacketSentryShot packet = new PacketSentryShot();
        packet.channel = GeneralRef.PACKET_CHANNELS[0];
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        /*
         * if (player instanceof EntityClientPlayerMP) { ((EntityClientPlayerMP)
         * player).sendQueue.addToSendQueue(packet); } else if (player
         * instanceof EntityPlayerMP) { ((EntityPlayerMP)
         * player).playerNetServerHandler.sendPacketToPlayer(packet); }
         */

        if (!ent.worldObj.isRemote)
        {
            PacketDispatcher.sendPacketToServer(packet);
        } else
        {
            PacketDispatcher.sendPacketToAllPlayers(packet);
        }
    }

    public void sendHitToServer(Entity ent, int... data)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(4 * data.length);
        DataOutputStream outputStream = new DataOutputStream(bos);

        try
        {
            for (int d : data)
                outputStream.writeInt(d);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        PacketSentryHit packet = new PacketSentryHit();
        packet.channel = GeneralRef.PACKET_CHANNELS[0];
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        /*
         * if (player instanceof EntityClientPlayerMP) { ((EntityClientPlayerMP)
         * player).sendQueue.addToSendQueue(packet); } else if (player
         * instanceof EntityPlayerMP) { ((EntityPlayerMP)
         * player).playerNetServerHandler.sendPacketToPlayer(packet); }
         */

        if (!ent.worldObj.isRemote)
        {
            PacketDispatcher.sendPacketToServer(packet);
        } else
        {
            PacketDispatcher.sendPacketToAllPlayers(packet);
        }
    }
}
