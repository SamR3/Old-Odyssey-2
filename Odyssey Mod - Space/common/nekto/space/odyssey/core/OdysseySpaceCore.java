package nekto.space.odyssey.core;

import nekto.odyssey.space.network.CommonProxy;
import nekto.odyssey.space.network.OdysseyTickHandlerCommon;
import nekto.odyssey.space.ref.GeneralRef;
import nekto.space.odyssey.provider.WorldProviderSpace;
import net.minecraft.item.Item;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = GeneralRef.CORE_MOD_ID, name = GeneralRef.CORE_MOD_NAME, version = GeneralRef.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class OdysseySpaceCore
{
    public final Item tele = new ItemSpaceTeleporter(5002);

    @Instance(GeneralRef.CORE_MOD_ID)
    public static OdysseySpaceCore instance;

    @SidedProxy(clientSide = GeneralRef.CLIENT_PROXY, serverSide = GeneralRef.SERVER_PROXY)
    public static CommonProxy proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @Init
    public void load(FMLInitializationEvent event)
    {
        proxy.registerRenderers();

        proxy.init();

        TickRegistry.registerTickHandler(new OdysseyTickHandlerCommon(), Side.SERVER);

        registerBlocks();
        registerItems();
        registerTileEntities();
        registerLanguage();

        DimensionManager.registerProviderType(GeneralRef.SPACE_ID, WorldProviderSpace.class, false);
        DimensionManager.registerDimension(GeneralRef.SPACE_ID, GeneralRef.SPACE_ID);
    }

    @ServerStarted
    public void serverStarted()
    {
    }

    private void registerBlocks()
    {
    }

    private void registerItems()
    {
        GameRegistry.registerItem(tele, "tele");
    }

    private void registerTileEntities()
    {
    }

    private void registerLanguage()
    {
    }
}
