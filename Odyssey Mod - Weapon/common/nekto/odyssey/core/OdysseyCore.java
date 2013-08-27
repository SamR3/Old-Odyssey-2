package nekto.odyssey.core;

import nekto.odyssey.network.CommonProxy;
import nekto.odyssey.network.PacketHandler;
import nekto.odyssey.ref.GeneralRef;
import nekto.odyssey.turret.tile.TileEntityTurret;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = GeneralRef.CORE_MOD_ID, name = GeneralRef.CORE_MOD_NAME, version = GeneralRef.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "Sentry.Shot", "Sentry.Turn" }, packetHandler = PacketHandler.class)
public class OdysseyCore
{
    public final Block turret = new BlockTurret(500);
    public final Block rack = new BlockBombRack(501).setCreativeTab(CreativeTabs.tabBlock);

    public final Item bomb = new ItemBomb(5000).setCreativeTab(CreativeTabs.tabBlock);

    public static boolean shouldRenderGUI = false;

    @Instance(GeneralRef.CORE_MOD_ID)
    public static OdysseyCore instance;

    @SidedProxy(clientSide = GeneralRef.CLIENT_PROXY, serverSide = GeneralRef.SERVER_PROXY)
    public static CommonProxy proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        OdysseyCore.proxy.init();
    }

    @Init
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
        GameRegistry.registerBlock(turret, "turret");
        GameRegistry.registerBlock(rack, "rack");
    }

    private void registerItems()
    {
        GameRegistry.registerItem(bomb, "bomb");
    }

    private void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityTurret.class, "turretEntity");
    }

    private void registerLanguage()
    {
        LanguageRegistry.addName(turret, "Turret");
    }
}
