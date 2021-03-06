package betterwithaddons.interaction.minetweaker;

import betterwithaddons.block.EriottoMod.BlockNettedScreen;
import betterwithaddons.crafting.manager.CraftingManagerFireNet;
import betterwithaddons.crafting.recipes.NetRecipe;
import betterwithaddons.interaction.jei.category.FireNetRecipeCategory;
import com.blamejared.mtlib.helpers.InputHelper;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass(FireNet.clazz)
public class FireNet extends Net {
    public static final String clazz = "mods.betterwithaddons.FireNet";

    @ZenMethod
    public static void add(IItemStack[] outputs, @NotNull IIngredient input) {
        NetRecipe r = new NetRecipe(BlockNettedScreen.SifterType.FIRE, InputHelper.toObject(input),0,InputHelper.toStacks(outputs));
        MineTweakerAPI.apply(new Add("FireNet", FireNetRecipeCategory.UID, CraftingManagerFireNet.getInstance(),r));
    }

    @ZenMethod
    public static void remove(IItemStack input)
    {
        MineTweakerAPI.apply(new Remove("FireNet", FireNetRecipeCategory.UID, CraftingManagerFireNet.getInstance(),InputHelper.toStack(input)));
    }
}
