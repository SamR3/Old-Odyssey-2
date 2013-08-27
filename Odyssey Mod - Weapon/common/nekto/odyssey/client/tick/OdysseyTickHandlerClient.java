package nekto.odyssey.client.tick;

import java.util.EnumSet;

import nekto.odyssey.radar.RadarGui;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class OdysseyTickHandlerClient implements ITickHandler
{

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (FMLClientHandler.instance().getClient().currentScreen == null)
        {
            RadarGui.doRender();
        }
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
