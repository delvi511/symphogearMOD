package com.delvi511.symphogearmod;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes{
	public static void registerIgalima(Item igalima){
		// 獄鎌・イガリマのレシピを追加
		GameRegistry.addRecipe(new ItemStack(igalima, 1),
								"EEE",
								"EBI",
								"EB_",
								'B', Items.blaze_rod,
								'E', Items.emerald,
								'I', Items.iron_ingot);
	}
}
