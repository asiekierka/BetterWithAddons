package betterwithaddons.item;

import betterwithaddons.entity.EntityYa;
import betterwithaddons.util.IDisableable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemYa extends Item implements IDisableable {
    private boolean disabled;

    public ItemYa() {
        super();
    }

    public EntityYa createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        EntityYa entityarrow = new EntityYa(worldIn, shooter);
        return entityarrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, EntityPlayer player) {
        return false;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(!disabled)
            super.getSubItems(itemIn, tab, subItems);
    }
}