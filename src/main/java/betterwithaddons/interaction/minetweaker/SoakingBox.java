package betterwithaddons.interaction.minetweaker;

import betterwithaddons.block.EriottoMod.BlockCherryBox;
import betterwithaddons.crafting.manager.CraftingManagerSoakingBox;
import betterwithaddons.crafting.recipes.CherryBoxRecipe;
import betterwithaddons.interaction.jei.category.SoakingBoxRecipeCategory;
import com.blamejared.mtlib.helpers.InputHelper;
import com.google.common.collect.Lists;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;

@ZenClass(SoakingBox.clazz)
public class SoakingBox extends CherryBox {
    public static final String clazz = "mods.betterwithaddons.SoakingBox";

    @ZenMethod
    public static void add(IItemStack output, IItemStack input) {
        CherryBoxRecipe recipe = new CherryBoxRecipe(BlockCherryBox.CherryBoxType.SOAKING,InputHelper.toObject(input),InputHelper.toStack(output));
        MineTweakerAPI.apply(new Add("SoakingBox", SoakingBoxRecipeCategory.UID, CraftingManagerSoakingBox.instance(), Lists.newArrayList(recipe)));
    }

    @ZenMethod
    public static void remove(IItemStack input)
    {
        List<CherryBoxRecipe> recipes = CraftingManagerSoakingBox.instance().findRecipeForRemoval(InputHelper.toStack(input));
        MineTweakerAPI.apply(new Remove("SoakingBox", SoakingBoxRecipeCategory.UID, CraftingManagerSoakingBox.instance(), recipes));
    }
}