package nekto.odyssey.core;

import nekto.odyssey.turret.tile.TileEntityTurret;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTurret extends BlockContainer
{

    public BlockTurret(int id)
    {
        super(id, Material.rock);
        setUnlocalizedName("smallTurret");
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return false;
    }

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public TileEntityTurret createNewTileEntity(World world)
    {
        return new TileEntityTurret();
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        TileEntityTurret tile = (TileEntityTurret) par1World.getBlockTileEntity(par2, par3, par4);

        tile.fire();

        return true;
    }
}
