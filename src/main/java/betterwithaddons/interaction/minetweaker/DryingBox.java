package betterwithaddons.interaction.minetweaker;

import betterwithaddons.block.EriottoMod.BlockCherryBox;
import betterwithaddons.crafting.manager.CraftingManagerDryingBox;
import betterwithaddons.crafting.recipes.CherryBoxRecipe;
import betterwithaddons.interaction.InteractionCraftTweaker;
import betterwithaddons.interaction.jei.category.DryingBoxRecipeCategory;
import com.blamejared.mtlib.helpers.InputHelper;
import com.google.common.collect.Lists;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;

@ZenRegister
@ZenClass(DryingBox.clazz)
public class DryingBox extends CherryBox {
    public static final String clazz = "mods.betterwithaddons.DryingBox";

    @ZenMethod
    public static void add(IItemStack output, IItemStack input) {
        CherryBoxRecipe recipe = new CherryBoxRecipe(BlockCherryBox.CherryBoxType.DRYING,InputHelper.toObject(input),InputHelper.toStack(output));
        InteractionCraftTweaker.LATE_ADDITIONS.add(new Add("DryingBox", CraftingManagerDryingBox.instance(), Lists.newArrayList(recipe)));
    }

    @ZenMethod
    public static void remove(IItemStack input)
    {
        List<CherryBoxRecipe> recipes = CraftingManagerDryingBox.instance().findRecipeForRemoval(InputHelper.toStack(input));
        InteractionCraftTweaker.LATE_REMOVALS.add(new Remove("DryingBox", CraftingManagerDryingBox.instance(), recipes));
    }
}
