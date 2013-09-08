package nekto.odyssey.mobs.core;

import nekto.odyssey.mobs.network.CommonProxy;
import nekto.odyssey.mobs.ref.GeneralRef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = GeneralRef.MOD_ID, name = GeneralRef.MOD_NAME, version = GeneralRef.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class OdysseyMobs
{
    private static int startEntityId = 300;
    
    @Instance(GeneralRef.MOD_ID)
    public static OdysseyMobs instance;

    @SidedProxy(clientSide = GeneralRef.CLIENT_PROXY, serverSide = GeneralRef.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        proxy.registerRenderers();

        EntityRegistry.registerModEntity(EntityReaver.class, "Reaver", 1, this, 80, 1, true);
        registerEntityEgg(EntityReaver.class, 0xffffff, 0x000000);
        
        registerBlocks();
        registerItems();
        registerTileEntities();
        registerLanguage();
    }

    private void registerBlocks()
    {

    }

    private void registerItems()
    {
        
    }

    private void registerTileEntities()
    {
        
    }

    private void registerLanguage()
    {
        LanguageRegistry.instance().addStringLocalization("entity.OdysseyMobs.Reaver.name", "Reaver");
    }
    
    public static int getUniqueEntityId() 
    {
     do 
     {
      startEntityId++;
     } 
     while (EntityList.getStringFromID(startEntityId) != null);

      return startEntityId;
    }
    
    @SuppressWarnings("unchecked")
    public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
    {
     int id = getUniqueEntityId();
     EntityList.IDtoClassMapping.put(id, entity);
     EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
    }
}
