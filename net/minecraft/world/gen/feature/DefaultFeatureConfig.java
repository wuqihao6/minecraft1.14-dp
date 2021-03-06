package net.minecraft.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class DefaultFeatureConfig implements FeatureConfig
{
    @Override
    public <T> Dynamic<T> serialize(final DynamicOps<T> ops) {
        return (Dynamic<T>)new Dynamic((DynamicOps)ops, ops.emptyMap());
    }
    
    public static <T> DefaultFeatureConfig deserialize(final Dynamic<T> dynamic) {
        return FeatureConfig.DEFAULT;
    }
}
