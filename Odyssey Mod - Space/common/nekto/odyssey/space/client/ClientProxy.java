package nekto.odyssey.space.client;

import nekto.odyssey.space.network.CommonProxy;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
    @Override
    public void init()
    {
        TickRegistry.registerTickHandler(new OdysseyTickHandlerClient(), Side.CLIENT);
    }

    @Override
    public void registerRenderers()
    {
    }
}
