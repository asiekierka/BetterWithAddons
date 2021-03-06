package betterwithaddons.potion.effects;

import betterwithaddons.potion.PotionBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class EffectCannonball extends PotionBase
{
	ResourceLocation icon = new ResourceLocation("betterwithaddons:textures/gui/effects/cannonball.png");

	public EffectCannonball()
	{
		super("cannonball", false, Color.pink.getRGB());
		
		this.setPotionName("Cannonball");
		this.setIconIndex(0,0);
	}
}
