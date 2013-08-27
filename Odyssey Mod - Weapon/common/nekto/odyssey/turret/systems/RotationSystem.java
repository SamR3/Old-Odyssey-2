package nekto.odyssey.turret.systems;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import nekto.odyssey.api.ISystem;
import nekto.odyssey.network.packet.PacketSentryRotation;
import nekto.odyssey.ref.GeneralRef;
import nekto.odyssey.turret.tile.TileEntityTurret;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import cpw.mods.fml.common.network.PacketDispatcher;

public class RotationSystem implements ISystem
{
    private TileEntityTurret turret;

    public RotationSystem(TileEntityTurret tile)
    {
        this.turret = tile;
    }

    public void doTurn()
    {
        Vec3 turretVec = Vec3.createVectorHelper(this.turret.xCoord, this.turret.yCoord, this.turret.zCoord);
        Vec3 difference = Vec3.createVectorHelper(this.turret.getTarget().posX, this.turret.getTarget().posY, this.turret.getTarget().posZ);

        float yaw = this.findAngleYaw(turretVec, difference);
        float pitch = this.findAnglePitch(turretVec, difference);

        Packet packet = createPacket(yaw, pitch);
        PacketDispatcher.sendPacketToAllAround(this.turret.xCoord, this.turret.yCoord, this.turret.zCoord, 50, this.turret.worldObj.provider.dimensionId, packet);
    }

    private Packet createPacket(float yaw, float pitch)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try
        {
            dos.writeInt(this.turret.xCoord);
            dos.writeInt(this.turret.yCoord);
            dos.writeInt(this.turret.zCoord);

            dos.writeFloat(yaw);
            dos.writeFloat(pitch);

            PacketSentryRotation pack = new PacketSentryRotation();
            pack.channel = GeneralRef.PACKET_CHANNELS[1];
            pack.data = bos.toByteArray();
            pack.length = pack.data.length;

            return pack;
        } catch (IOException e)
        {
            System.out.println("Packet creation failed.");
            e.printStackTrace();
        }

        return null;
    }

    private float findAngleYaw(Vec3 tile, Vec3 target)
    {
        Vec3 difference = target.subtract(tile);

        return MathHelper.wrapAngleTo180_float((float) (Math.atan2(difference.zCoord, difference.xCoord) * 180.0D / 3.14159265358979323846));
    }

    private float findAnglePitch(Vec3 tile, Vec3 target)
    {
        Vec3 difference = target.subtract(tile);

        double distance = MathHelper.sqrt_double(difference.xCoord * difference.xCoord + difference.zCoord * difference.zCoord);
        return -MathHelper.wrapAngleTo180_float((float) (Math.atan2(difference.yCoord, distance) * 180.0D / 3.1415926535897932384 + /* 1 */0.0F));
    }

    @Override
    public String getIdentifier()
    {
        return "Rotation System";
    }
}
