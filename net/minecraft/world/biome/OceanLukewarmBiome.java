package net.minecraft.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityCategory;
import net.minecraft.world.gen.feature.ShipwreckFeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.OceanRuinFeatureConfig;
import net.minecraft.world.gen.feature.OceanRuinFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class OceanLukewarmBiome extends Biome
{
    public OceanLukewarmBiome() {
        super(new Settings().<TernarySurfaceConfig>configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG).precipitation(Precipitation.RAIN).category(Category.OCEAN).depth(-1.0f).scale(0.1f).temperature(0.5f).downfall(0.5f).waterColor(4566514).waterFogColor(267827).parent(null));
        this.<OceanRuinFeatureConfig>addStructureFeature(Feature.OCEAN_RUIN, new OceanRuinFeatureConfig(OceanRuinFeature.BiomeType.WARM, 0.3f, 0.9f));
        this.<MineshaftFeatureConfig>addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL));
        this.<ShipwreckFeatureConfig>addStructureFeature(Feature.SHIPWRECK, new ShipwreckFeatureConfig(false));
        DefaultBiomeFeatures.addOceanCarvers(this);
        DefaultBiomeFeatures.addDefaultStructures(this);
        DefaultBiomeFeatures.addDefaultLakes(this);
        DefaultBiomeFeatures.addDungeons(this);
        DefaultBiomeFeatures.addMineables(this);
        DefaultBiomeFeatures.addDefaultOres(this);
        DefaultBiomeFeatures.addDefaultDisks(this);
        DefaultBiomeFeatures.addWaterBiomeOakTrees(this);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        DefaultBiomeFeatures.addDefaultGrass(this);
        DefaultBiomeFeatures.addDefaultMushrooms(this);
        DefaultBiomeFeatures.addDefaultVegetation(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addSeagrass(this);
        DefaultBiomeFeatures.addLessKelp(this);
        DefaultBiomeFeatures.addFrozenTopLayer(this);
        this.addSpawn(EntityCategory.d, new SpawnEntry(EntityType.SQUID, 10, 1, 2));
        this.addSpawn(EntityCategory.d, new SpawnEntry(EntityType.COD, 15, 3, 6));
        this.addSpawn(EntityCategory.d, new SpawnEntry(EntityType.PUFFERFISH, 5, 1, 3));
        this.addSpawn(EntityCategory.d, new SpawnEntry(EntityType.TROPICAL_FISH, 25, 8, 8));
        this.addSpawn(EntityCategory.d, new SpawnEntry(EntityType.DOLPHIN, 2, 1, 2));
        this.addSpawn(EntityCategory.c, new SpawnEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.DROWNED, 5, 1, 1));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityCategory.a, new SpawnEntry(EntityType.WITCH, 5, 1, 1));
    }
}
