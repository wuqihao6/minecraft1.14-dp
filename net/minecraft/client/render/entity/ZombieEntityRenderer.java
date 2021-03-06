package net.minecraft.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.ZombieEntity;

@Environment(EnvType.CLIENT)
public class ZombieEntityRenderer extends ZombieBaseEntityRenderer<ZombieEntity, ZombieEntityModel<ZombieEntity>>
{
    public ZombieEntityRenderer(final EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new ZombieEntityModel(), new ZombieEntityModel(0.5f, true), new ZombieEntityModel(1.0f, true));
    }
}
