package betterwithaddons.interaction.minetweaker;

import betterwithaddons.block.EriottoMod.BlockNettedScreen;
import betterwithaddons.crafting.manager.CraftingManagerFireNet;
import betterwithaddons.crafting.recipes.NetRecipe;
import betterwithaddons.interaction.InteractionCraftTweaker;
import betterwithaddons.interaction.jei.category.FireNetRecipeCategory;
import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass(FireNet.clazz)
public class FireNet extends Net {
    public static final String clazz = "mods.betterwithaddons.FireNet";

    @ZenMethod
    public static void add(IItemStack[] outputs, @NotNull IIngredient input) {
        NetRecipe r = new NetRecipe(BlockNettedScreen.SifterType.FIRE, InputHelper.toObject(input),0,InputHelper.toStacks(outputs));
        InteractionCraftTweaker.LATE_ADDITIONS.add(new Add("FireNet", CraftingManagerFireNet.getInstance(),r));
    }

    @ZenMethod
    public static void remove(IItemStack input)
    {
        InteractionCraftTweaker.LATE_REMOVALS.add(new Remove("FireNet", CraftingManagerFireNet.getInstance(),InputHelper.toStack(input)));
    }
}
