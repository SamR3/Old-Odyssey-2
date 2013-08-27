package nekto.space.odyssey.core;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpaceTeleporter extends Item
{
    public ItemSpaceTeleporter(int par1)
    {
        super(par1);
        this.setUnlocalizedName("spacetele");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage("" + par3EntityPlayer.posY);

        return par1ItemStack;
    }
}
