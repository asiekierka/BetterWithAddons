package betterwithaddons.interaction;

import betterwithaddons.BetterWithAddons;
import betterwithaddons.crafting.recipes.DisplaySawRecipe;
import betterwithaddons.handler.EggIncubationHandler;
import betterwithaddons.handler.SoapHandler;
import betterwithaddons.handler.StumpingHandler;
import betterwithaddons.item.ModItems;
import betterwithmods.common.BWMBlocks;
import betterwithmods.common.blocks.BlockAesthetic;
import betterwithmods.common.blocks.BlockMechMachines;
import betterwithmods.common.items.ItemMaterial;
import betterwithmods.common.registry.blockmeta.managers.SawManager;
import betterwithmods.common.registry.bulk.manager.StokedCauldronManager;
import betterwithmods.module.hardcore.HCBonemeal;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Arrays;
import java.util.List;

public class InteractionBTWTweak extends Interaction {
    public static boolean ENABLED = true;
    public static boolean HARD_STUMPS = true;
    public static double HARD_STUMPS_MODIFIER = 0.1;
    public static boolean SOFT_WOODS = true;
    public static boolean SAW_RECYCLING = true;
    //public static boolean KILN_DOUBLING = true;
    public static boolean EGG_INCUBATION = true;
    public static boolean SLIPPERY_WHEN_WET = true;
    public static boolean ASH_FERTILIZER = true;
    public static boolean WOOL_RECYCLING = true;
    public static boolean LOGS_SMELT_TO_ASH = true;
    public static boolean REPLACE_WRITABLE_BOOK_RECIPE = true;

    @Override
    public boolean isActive() {
        return ENABLED;
    }

    @Override
    public void setEnabled(boolean active) {
        ENABLED = active;
        super.setEnabled(active);
    }

    @Override
    public List<Interaction> getDependencies() {
        return Arrays.asList(new Interaction[]{ ModInteractions.bwm });
    }

    @Override
    public List<Interaction> getIncompatibilities() {
        return null;
    }

    @Override
    public void preInit() {
        if(SLIPPERY_WHEN_WET)
            MinecraftForge.EVENT_BUS.register(new SoapHandler(BWMBlocks.AESTHETIC.getDefaultState().withProperty(BlockAesthetic.blockType, BlockAesthetic.EnumType.SOAP)));
        if(EGG_INCUBATION)
            MinecraftForge.EVENT_BUS.register(new EggIncubationHandler());
        if(HARD_STUMPS || SOFT_WOODS)
            MinecraftForge.EVENT_BUS.register(new StumpingHandler());
    }

    @Override
    public void init() {
        if(SOFT_WOODS)
        {
            StumpingHandler.addSoftWood(Blocks.LOG,BlockPlanks.EnumType.SPRUCE.getMetadata(),1.3f);
            StumpingHandler.addSoftWood(Blocks.LOG,BlockPlanks.EnumType.JUNGLE.getMetadata(),1.0f);
            StumpingHandler.addSoftWood(Blocks.LOG2,BlockPlanks.EnumType.DARK_OAK.getMetadata() - 4,1.3f);
        }

        if(ASH_FERTILIZER) {
            HCBonemeal.registerFertilzier(ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.POTASH));
            HCBonemeal.registerFertilzier(ModItems.materialTweak.getMaterial("ash"));
        }

        GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.materialTweak.getMaterial("ink_and_quill"),new ItemStack(Items.GLASS_BOTTLE),new ItemStack(Items.DYE,1,EnumDyeColor.BLACK.getDyeDamage()),"feather"));

        if(REPLACE_WRITABLE_BOOK_RECIPE)
        {
            BetterWithAddons.removeCraftingRecipe(new ItemStack(Items.WRITABLE_BOOK));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.WRITABLE_BOOK),new ItemStack(Items.BOOK),ModItems.materialTweak.getMaterial("ink_and_quill")));
        }

        if(WOOL_RECYCLING && InteractionBWM.HARDCORE_SHEARING)
        {
            for (EnumDyeColor color: EnumDyeColor.values()) {
                StokedCauldronManager.getInstance().addRecipe(new ItemStack(BWMBlocks.AESTHETIC,1,BlockAesthetic.EnumType.WICKER.getMeta()), ModItems.wool.getByColor(color,4), new Object[]{ new ItemStack(Blocks.WOOL,1,color.getMetadata()) });
            }
        }

        if(SAW_RECYCLING)
        {
            SawManager.INSTANCE.addRecipe(Blocks.BOOKSHELF,0,new ItemStack(BWMBlocks.WOOD_SIDING, 4, BlockPlanks.EnumType.OAK.getMetadata()),new ItemStack(Items.BOOK,3));
            SawManager.INSTANCE.addRecipe(Blocks.CHEST,0,new ItemStack(BWMBlocks.WOOD_SIDING, 6, BlockPlanks.EnumType.OAK.getMetadata()));
            SawManager.INSTANCE.addRecipe(Blocks.JUKEBOX,0,new ItemStack(BWMBlocks.WOOD_SIDING, 6, BlockPlanks.EnumType.OAK.getMetadata()),new ItemStack(Items.DIAMOND,1));
            SawManager.INSTANCE.addRecipe(Blocks.LADDER,0,new ItemStack(Items.STICK,2));
            SawManager.INSTANCE.addRecipe(Blocks.NOTEBLOCK,0,new ItemStack(BWMBlocks.WOOD_SIDING, 6, BlockPlanks.EnumType.OAK.getMetadata()),new ItemStack(Items.REDSTONE,1));
            SawManager.INSTANCE.addRecipe(Blocks.TRAPDOOR,0,new ItemStack(BWMBlocks.WOOD_SIDING, 2, BlockPlanks.EnumType.OAK.getMetadata()));
            SawManager.INSTANCE.addRecipe(BWMBlocks.AXLE,0,new ItemStack(BWMBlocks.WOOD_CORNER, 2, BlockPlanks.EnumType.OAK.getMetadata()),new ItemStack(BWMBlocks.ROPE,1));
            SawManager.INSTANCE.addRecipe(BWMBlocks.BELLOWS,0,new ItemStack(BWMBlocks.WOOD_SIDING, 2, BlockPlanks.EnumType.OAK.getMetadata()), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.LEATHER_BELT), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.GEAR), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.TANNED_LEATHER_CUT,3));
            SawManager.INSTANCE.addRecipe(BWMBlocks.GEARBOX,0,new ItemStack(BWMBlocks.WOOD_SIDING, 3, BlockPlanks.EnumType.OAK.getMetadata()), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.GEAR,3), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.REDSTONE_LATCH));
            SawManager.INSTANCE.addRecipe(BWMBlocks.SINGLE_MACHINES, BlockMechMachines.EnumType.HOPPER.getMeta() << 1,new ItemStack(BWMBlocks.WOOD_MOULDING, 3, BlockPlanks.EnumType.OAK.getMetadata()), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.GEAR,1), new ItemStack(Blocks.WOODEN_PRESSURE_PLATE,1));
            SawManager.INSTANCE.addRecipe(BWMBlocks.PLATFORM,0,new ItemStack(BWMBlocks.WOOD_MOULDING, 3, BlockPlanks.EnumType.OAK.getMetadata()), new ItemStack(BWMBlocks.PANE,2,2));
            SawManager.INSTANCE.addRecipe(BWMBlocks.SINGLE_MACHINES, BlockMechMachines.EnumType.PULLEY.getMeta() << 1,new ItemStack(BWMBlocks.WOOD_SIDING, 3, BlockPlanks.EnumType.OAK.getMetadata()), new ItemStack(Items.IRON_INGOT), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.GEAR), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.REDSTONE_LATCH));
            SawManager.INSTANCE.addRecipe(BWMBlocks.SAW,0,new ItemStack(BWMBlocks.WOOD_SIDING, 1, BlockPlanks.EnumType.OAK.getMetadata()), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.LEATHER_BELT), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.GEAR), new ItemStack(Items.IRON_INGOT, 2));
            SawManager.INSTANCE.addRecipe(BWMBlocks.PUMP,0,new ItemStack(BWMBlocks.WOOD_SIDING, 3, BlockPlanks.EnumType.OAK.getMetadata()), ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.SCREW), new ItemStack(BWMBlocks.GRATE, 1, BlockPlanks.EnumType.OAK.getMetadata()));

            BlockPlanks.EnumType[] woodtypes = BlockPlanks.EnumType.values();
            int len = woodtypes.length;

            for(int i = 0; i < len; ++i) {
                BlockPlanks.EnumType woodtype = woodtypes[i];

                SawManager.INSTANCE.addRecipe(BWMBlocks.WOOD_BENCH,woodtype.getMetadata(),new ItemStack(BWMBlocks.WOOD_CORNER, 2, woodtype.getMetadata()));
                SawManager.INSTANCE.addRecipe(BWMBlocks.WOOD_TABLE,woodtype.getMetadata(),new ItemStack(BWMBlocks.WOOD_CORNER, 3, woodtype.getMetadata()));
            }

            SawManager.INSTANCE.addRecipe(new DisplaySawRecipe(Blocks.OAK_DOOR,0,Arrays.asList(new ItemStack(BWMBlocks.WOOD_SIDING, 4, BlockPlanks.EnumType.OAK.getMetadata())),new ItemStack(Items.OAK_DOOR)));
            SawManager.INSTANCE.addRecipe(new DisplaySawRecipe(Blocks.BIRCH_DOOR,0,Arrays.asList(new ItemStack(BWMBlocks.WOOD_SIDING, 4, BlockPlanks.EnumType.BIRCH.getMetadata())),new ItemStack(Items.BIRCH_DOOR)));
            SawManager.INSTANCE.addRecipe(new DisplaySawRecipe(Blocks.SPRUCE_DOOR,0,Arrays.asList(new ItemStack(BWMBlocks.WOOD_SIDING, 4, BlockPlanks.EnumType.SPRUCE.getMetadata())),new ItemStack(Items.SPRUCE_DOOR)));
            SawManager.INSTANCE.addRecipe(new DisplaySawRecipe(Blocks.JUNGLE_DOOR,0,Arrays.asList(new ItemStack(BWMBlocks.WOOD_SIDING, 4, BlockPlanks.EnumType.JUNGLE.getMetadata())),new ItemStack(Items.JUNGLE_DOOR)));
            SawManager.INSTANCE.addRecipe(new DisplaySawRecipe(Blocks.ACACIA_DOOR,0,Arrays.asList(new ItemStack(BWMBlocks.WOOD_SIDING, 4, BlockPlanks.EnumType.ACACIA.getMetadata())),new ItemStack(Items.ACACIA_DOOR)));
            SawManager.INSTANCE.addRecipe(new DisplaySawRecipe(Blocks.DARK_OAK_DOOR,0,Arrays.asList(new ItemStack(BWMBlocks.WOOD_SIDING, 4, BlockPlanks.EnumType.DARK_OAK.getMetadata())),new ItemStack(Items.DARK_OAK_DOOR)));

            SawManager.INSTANCE.addRecipe(Blocks.OAK_FENCE_GATE,0,new ItemStack(BWMBlocks.WOOD_MOULDING, 3, BlockPlanks.EnumType.OAK.getMetadata()));
            SawManager.INSTANCE.addRecipe(Blocks.BIRCH_FENCE_GATE,0,new ItemStack(BWMBlocks.WOOD_MOULDING, 3, BlockPlanks.EnumType.BIRCH.getMetadata()));
            SawManager.INSTANCE.addRecipe(Blocks.SPRUCE_FENCE_GATE,0,new ItemStack(BWMBlocks.WOOD_MOULDING, 3, BlockPlanks.EnumType.SPRUCE.getMetadata()));
            SawManager.INSTANCE.addRecipe(Blocks.JUNGLE_FENCE_GATE,0,new ItemStack(BWMBlocks.WOOD_MOULDING, 3, BlockPlanks.EnumType.JUNGLE.getMetadata()));
            SawManager.INSTANCE.addRecipe(Blocks.ACACIA_FENCE_GATE,0,new ItemStack(BWMBlocks.WOOD_MOULDING, 3, BlockPlanks.EnumType.ACACIA.getMetadata()));
            SawManager.INSTANCE.addRecipe(Blocks.DARK_OAK_FENCE_GATE,0,new ItemStack(BWMBlocks.WOOD_MOULDING, 3, BlockPlanks.EnumType.DARK_OAK.getMetadata()));
        }
    }

    @Override
    public void postInit() {
        if(LOGS_SMELT_TO_ASH) {
            for (ItemStack log : OreDictionary.getOres("logWood")) {
                ItemStack result = FurnaceRecipes.instance().getSmeltingResult(log);
                if(result.isEmpty())
                    GameRegistry.addSmelting(log,ModItems.materialTweak.getMaterial("ash"),0.1f);
            }
        }

        /*if(KILN_DOUBLING && ModuleLoader.isFeatureEnabled(KilnSmelting.class))
        {
            for (ItemStack ore : BWOreDictionary.oreNames) {
                if(ore.getItem() instanceof ItemBlock)
                {
                    BlockMetaRecipe recipe = KilnManager.INSTANCE.getRecipe(ore);
                    List<ItemStack> outputs = recipe.getOutputs();
                    if(outputs.size() > 0)
                    {
                        ItemStack output = outputs.get(0).copy();
                        output.setCount(MathHelper.clamp(output.getCount() * 2,0,output.getMaxStackSize()));
                        outputs.set(0,output);
                    }
                }
            }
        }*/
    }
}
