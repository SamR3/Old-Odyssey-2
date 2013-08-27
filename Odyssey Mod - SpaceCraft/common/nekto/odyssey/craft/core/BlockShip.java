package nekto.odyssey.craft.core;

import nekto.odyssey.craft.entity.EntityShipBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockShip extends Block
{

    public BlockShip(int par1)
    {
        super(par1, Material.rock);
        setUnlocalizedName("blo");
        setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        par1World.spawnEntityInWorld(new EntityShipBlock(par1World, par2, par3, par4));
        
        par1World.setBlockToAir(par2, par3, par4);
        return true;
    }
}
