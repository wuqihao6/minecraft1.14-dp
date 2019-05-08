package net.minecraft.recipe.cooking;

import net.minecraft.recipe.RecipeSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemProvider;
import net.minecraft.block.Blocks;
import net.minecraft.recipe.RecipeType;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class SmeltingRecipe extends CookingRecipe
{
    public SmeltingRecipe(final Identifier identifier, final String string, final Ingredient ingredient, final ItemStack itemStack, final float float5, final int integer) {
        super(RecipeType.SMELTING, identifier, string, ingredient, itemStack, float5, integer);
    }
    
    @Environment(EnvType.CLIENT)
    @Override
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Blocks.bW);
    }
    
    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.SMELTING;
    }
}
