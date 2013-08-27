package nekto.odyssey.network;

import java.util.Hashtable;
import java.util.Map;

import nekto.odyssey.api.IOdysseyPacket;
import nekto.odyssey.network.packet.PacketSentryHit;
import nekto.odyssey.network.packet.PacketSentryRotation;
import nekto.odyssey.network.packet.PacketSentryShot;
import nekto.odyssey.ref.GeneralRef;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
    private static Map<String, IOdysseyPacket> packetList = new Hashtable<String, IOdysseyPacket>();

    static PacketSentryShot shot = new PacketSentryShot();
    static PacketSentryRotation rotation = new PacketSentryRotation();
    static PacketSentryHit hit = new PacketSentryHit();

    static
    {
        packetList.put(GeneralRef.PACKET_CHANNELS[0], shot);
        packetList.put(GeneralRef.PACKET_CHANNELS[1], rotation);
        packetList.put(GeneralRef.PACKET_CHANNELS[2], hit);
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player par3Player)
    {
        packetList.get(packet.channel).handle(packet, (EntityPlayer) par3Player);
    }
}