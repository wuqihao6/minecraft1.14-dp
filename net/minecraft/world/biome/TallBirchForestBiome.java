package net.minecraft.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityCategory;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public final class TallBirchForestBiome extends Biome
{
    public TallBirchForestBiome() {
        super(new Settings().<TernarySurfaceConfig>configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG).precipitation(Precipitation.RAIN).category(Category.FOREST).depth(0.2f).scale(0.4f).temperature(0.6f).downfall(0.6f).waterColor(4159204).waterFogColor(329011).parent("birch_forest"));
        this.<MineshaftFeatureConfig>addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL));
        this.<DefaultFeatureConfig>addStructureFeature(Feature.STRONGHOLD, FeatureConfig.DEFAULT);
        DefaultBiomeFeatures.addLandCarvers(this);
        DefaultBiomeFeatures.addDefaultStructures(this);
        DefaultBiomeFeatures.addDefaultLakes(this);
        DefaultBiomeFeatures.addDungeons(this);
        DefaultBiomeFeatures.addForestFlowers(this);
        DefaultBiomeFeatures.addMineables(this);
        DefaultBiomeFeatures.addDefaultOres(this);
        DefaultBiomeFeatures.addDefaultDisks(this);
        DefaultBiomeFeatures.addTallBirchTrees(this);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        DefaultBiomeFeatures.addForestGrass(this);
        DefaultBiomeFeatures.addDefaultMushrooms(this);
        DefaultBiomeFeatures.addDefaultVegetation(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFrozenTopLayer(this);
        this.addSpawn(EntityCategory.b, new SpawnEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityCategory.b, new SpawnEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityCategory.b, new SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityCategory.b, new SpawnEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityCategory.c, new SpawnEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.WITCH, 5, 1, 1));
    }
}
