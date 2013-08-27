package nekto.odyssey.space.client;

import java.util.EnumSet;

import nekto.space.odyssey.provider.SkyProviderOverworld;
import nekto.space.odyssey.provider.SkyProviderSpace;
import nekto.space.odyssey.provider.WorldProviderSpace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldProviderSurface;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class OdysseyTickHandlerClient implements ITickHandler
{

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        final Minecraft minecraft = FMLClientHandler.instance().getClient();

        final WorldClient world = minecraft.theWorld;

        final EntityClientPlayerMP player = minecraft.thePlayer;

        if (type.equals(EnumSet.of(TickType.CLIENT)))
        {
            if (world != null && world.provider instanceof WorldProviderSurface)
            {
                if (/* world.provider.getSkyRenderer() == null && */player.posY >= 100)
                {
                    world.provider.setSkyRenderer(new SkyProviderOverworld());
                } else if (/* world.provider.getSkyRenderer() != null && */world.provider.getSkyRenderer() instanceof SkyProviderOverworld && player.posY < 100)
                {
                    world.provider.setSkyRenderer(null);
                }
            }

            if (world != null && world.provider instanceof WorldProviderSpace)
            {
                if (world.provider.getSkyRenderer() == null)
                {
                    world.provider.setSkyRenderer(new SkyProviderSpace("/mods/odyssey/textures/gui/overworld.png", true, true));
                }
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public String getLabel()
    {
        return "Odyssey Client";
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
    }
}
