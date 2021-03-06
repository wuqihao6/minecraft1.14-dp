package net.minecraft.world.gen.decorator;

import net.minecraft.world.Heightmap;
import java.util.stream.Stream;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.IWorld;
import com.mojang.datafixers.Dynamic;
import java.util.function.Function;

public class ChanceHeightmapDecorator extends Decorator<ChanceDecoratorConfig>
{
    public ChanceHeightmapDecorator(final Function<Dynamic<?>, ? extends ChanceDecoratorConfig> configDeserializer) {
        super(configDeserializer);
    }
    
    @Override
    public Stream<BlockPos> getPositions(final IWorld world, final ChunkGenerator<? extends ChunkGeneratorConfig> generator, final Random random, final ChanceDecoratorConfig config, final BlockPos pos) {
        if (random.nextFloat() < 1.0f / config.chance) {
            final int integer6 = random.nextInt(16);
            final int integer7 = random.nextInt(16);
            final BlockPos blockPos8 = world.getTopPosition(Heightmap.Type.e, pos.add(integer6, 0, integer7));
            return Stream.<BlockPos>of(blockPos8);
        }
        return Stream.<BlockPos>empty();
    }
}
