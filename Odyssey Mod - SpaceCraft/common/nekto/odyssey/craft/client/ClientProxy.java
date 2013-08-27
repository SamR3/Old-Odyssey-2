package nekto.odyssey.craft.client;

import nekto.odyssey.craft.entity.EntityShipBlock;
import nekto.odyssey.craft.network.CommonProxy;
import nekto.odyssey.craft.render.RenderShipBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        EntityRegistry.registerGlobalEntityID(EntityShipBlock.class, "ShipBlock", EntityRegistry.findGlobalUniqueEntityId());
        RenderingRegistry.registerEntityRenderingHandler(EntityShipBlock.class, new RenderShipBlock());
    }
}
