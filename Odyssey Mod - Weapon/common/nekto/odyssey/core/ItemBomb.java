package nekto.odyssey.core;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBomb extends Item
{
    public ItemBomb(int par1)
    {
        super(par1);
        this.setUnlocalizedName("bomb");
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("dfksajd");
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        // par3World.spawnEntityInWorld(new EntityBomb(par3World, par4, par5 +
        // 1, par6));
        return false;
    }
}
