package net.minecraft.world.gen.decorator;

import net.minecraft.world.Heightmap;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.IWorld;
import com.mojang.datafixers.Dynamic;
import java.util.function.Function;

public class ForestRockDecorator extends Decorator<CountDecoratorConfig>
{
    public ForestRockDecorator(final Function<Dynamic<?>, ? extends CountDecoratorConfig> configDeserializer) {
        super(configDeserializer);
    }
    
    @Override
    public Stream<BlockPos> getPositions(final IWorld world, final ChunkGenerator<? extends ChunkGeneratorConfig> generator, final Random random, final CountDecoratorConfig config, final BlockPos pos) {
        final int integer6 = random.nextInt(config.count);
        final int integer7;
        final int integer8;
        return IntStream.range(0, integer6).<BlockPos>mapToObj(integer -> {
            integer7 = random.nextInt(16);
            integer8 = random.nextInt(16);
            return world.getTopPosition(Heightmap.Type.e, pos.add(integer7, 0, integer8));
        });
    }
}
