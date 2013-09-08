package nekto.odyssey.craft.core;

import nekto.odyssey.craft.network.CommonProxy;
import nekto.odyssey.craft.ref.GeneralRef;
import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = GeneralRef.MOD_ID, name = GeneralRef.MOD_NAME, version = GeneralRef.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class OdysseyCore
{

    public static final Block blo = new BlockShip(600);

    @Instance(GeneralRef.MOD_ID)
    public static OdysseyCore instance;

    @SidedProxy(clientSide = GeneralRef.CLIENT_PROXY, serverSide = GeneralRef.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        proxy.registerRenderers();

        registerBlocks();
        registerItems();
        registerTileEntities();
        registerLanguage();
    }

    private void registerBlocks()
    {
        GameRegistry.registerBlock(blo, "blo");
    }

    private void registerItems()
    {
    }

    private void registerTileEntities()
    {
    }

    private void registerLanguage()
    {
        LanguageRegistry.addName(blo, "Block");
    }
}
