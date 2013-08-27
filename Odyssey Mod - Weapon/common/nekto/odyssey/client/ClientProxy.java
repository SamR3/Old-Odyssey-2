package nekto.odyssey.client;

import nekto.odyssey.client.tick.OdysseyTickHandlerClient;
import nekto.odyssey.entity.EntityBomb;
import nekto.odyssey.entity.EntityLaser;
import nekto.odyssey.network.CommonProxy;
import nekto.odyssey.ref.EnumLaserColor;
import nekto.odyssey.ref.EnumLaserType;
import nekto.odyssey.render.RenderBomb;
import nekto.odyssey.render.RenderLaser;
import nekto.odyssey.render.RenderTurret;
import nekto.odyssey.turret.tile.TileEntityTurret;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
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
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurret.class, new RenderTurret());

        EntityRegistry.registerGlobalEntityID(EntityLaser.class, "BlasterBolt", EntityRegistry.findGlobalUniqueEntityId());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser(EnumLaserType.STANDARD_SMALL, EnumLaserColor.PURPLE));

        EntityRegistry.registerGlobalEntityID(EntityBomb.class, "Bomb", EntityRegistry.findGlobalUniqueEntityId());
        RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new RenderBomb());
    }
}
