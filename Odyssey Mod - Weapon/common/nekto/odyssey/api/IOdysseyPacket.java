package nekto.odyssey.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

public interface IOdysseyPacket
{

    public abstract void handle(Packet250CustomPayload packet, EntityPlayer player);

}
