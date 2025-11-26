package com.inolia_zaicek.more_mod_tetra.ModelProvider;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class ZeroingModRecipesGen extends RecipeProvider {

    public ZeroingModRecipesGen(PackOutput pOutput) {
        super(pOutput);
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

    }
}
