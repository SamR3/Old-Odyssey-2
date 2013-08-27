package nekto.odyssey.core;

import nekto.odyssey.entity.EntityBomb;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockBombRack extends Block
{
    public EntityBomb loadedBomb = null;

    public BlockBombRack(int par1)
    {
        super(par1, Material.rock);
        this.setUnlocalizedName("rack");
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par5EntityPlayer.getCurrentEquippedItem().getItem() != null && par5EntityPlayer.getCurrentEquippedItem().getItem() instanceof ItemBomb && this.loadedBomb == null)
        {
            par5EntityPlayer.getCurrentEquippedItem().stackSize--;

            this.loadedBomb = new EntityBomb(par1World, par2, par4, par6);
            par1World.spawnEntityInWorld(this.loadedBomb);

            return true;
        }

        if (this.loadedBomb != null)
        {
            this.loadedBomb.drop();
            this.loadedBomb = null;
            return true;
        }

        return false;
    }
}
