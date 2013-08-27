package nekto.odyssey.mobs.client;

import nekto.odyssey.mobs.core.EntityReaver;
import nekto.odyssey.mobs.network.CommonProxy;
import nekto.odyssey.mobs.render.RenderReaver;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityReaver.class, new RenderReaver());
    }
}
