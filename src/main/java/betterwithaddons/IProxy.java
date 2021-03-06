package betterwithaddons;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public interface IProxy
{
	void preInit();

	void init();

	void postInit();

	void makeLeafFX(double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz, float maxAgeMul);

	void overrideItemModel(Item item, int meta, ModelResourceLocation location);
}
