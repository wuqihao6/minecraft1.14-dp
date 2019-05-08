package net.minecraft.container;

import net.minecraft.text.TextComponent;

public interface NameableContainerProvider extends ContainerProvider
{
    TextComponent getDisplayName();
}
