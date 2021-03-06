package net.minecraft.world.gen.chunk;

public class OverworldChunkGeneratorConfig extends ChunkGeneratorConfig
{
    private final int t = 4;
    private final int u = 4;
    private final int v = -1;
    private final int w = 63;
    
    public int getBiomeSize() {
        return 4;
    }
    
    public int getRiverSize() {
        return 4;
    }
    
    public int getForcedBiome() {
        return -1;
    }
    
    @Override
    public int getMinY() {
        return 0;
    }
}
