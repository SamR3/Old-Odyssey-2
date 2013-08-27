package nekto.space.odyssey.chunk;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class ChunkProviderSpace extends ChunkProviderGenerate
{
    private final Random rand;

    private final World worldObj;

    public ChunkProviderSpace(World par1World, long par2, boolean par4)
    {
        super(par1World, par2, par4);
        this.rand = new Random(par2);
        this.worldObj = par1World;
    }

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    @Override
    public boolean saveChunks(boolean var1, IProgressUpdate var2)
    {
        return true;
    }

    @Override
    public boolean canSave()
    {
        return true;
    }

    @Override
    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        final short[] ids = new short[32768];
        final byte[] meta = new byte[32768];

        final Chunk var4 = new Chunk(this.worldObj, ids, meta, par1, par2);

        var4.generateSkylightMap();
        return var4;
    }

    @Override
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockSand.fallInstantly = true;
        this.rand.setSeed(this.worldObj.getSeed());
        final long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        final long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(par2 * i1 + par3 * j1 ^ this.worldObj.getSeed());
        BlockSand.fallInstantly = false;
    }

    @Override
    public String makeString()
    {
        return "OrbitLevelSource";
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int i, int j, int k)
    {
        return null;
    }
}
