package nekto.odyssey.space.network;

import java.util.EnumSet;

import nekto.odyssey.space.ref.GeneralRef;
import nekto.space.odyssey.provider.WorldProviderSpace;
import nekto.space.odyssey.teleport.TeleUtil;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class OdysseyTickHandlerCommon implements ITickHandler
{
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.WORLD)))
        {
            final WorldServer world = (WorldServer) tickData[0];
            final Object[] entityList = world.loadedEntityList.toArray();
            
            for (final Object o : entityList)
            {
                if (o instanceof Entity)
                {
                    final Entity e = (Entity) o;

                    if (e.posY >= 150)
                    {
                        TeleUtil.transferEntityToDimension(e, GeneralRef.SPACE_ID, world);
                    }
                }

                if (o instanceof Entity && ((Entity) o).worldObj.provider instanceof WorldProviderSpace)
                {
                    final Entity e = (Entity) o;

                    if (e.posY <= 0)
                    {
                        TeleUtil.transferEntityToDimension(e, 0, world, false);
                    }
                }
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.WORLD);
    }

    @Override
    public String getLabel()
    {
        return "Odyssey Common";
    }

}
