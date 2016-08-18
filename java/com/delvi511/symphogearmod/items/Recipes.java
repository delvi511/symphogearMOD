package com.delvi511.symphogearmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes{
	public static void registerSymphogearItems(){
		registerIgalima();
	}
	
	private static void registerIgalima(){
		// 獄鎌・イガリマのレシピを追加
		GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem("Symphogear-MOD", "igalimaScythe"), 1),
								"EEE",
								"EBI",
								"EB_",
								'B', Items.blaze_rod,
								'E', Items.emerald,
								'I', Items.iron_ingot);
	}
}
