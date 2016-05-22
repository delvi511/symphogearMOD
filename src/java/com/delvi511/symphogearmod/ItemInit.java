package com.delvi511.symphogearmod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {
	// 獄鎌・イガリマのマテリアル設定
	public static ToolMaterial igalimaMaterial(){
		ToolMaterial igalima = EnumHelper.addToolMaterial("IGALIMA", 0, 1590, 0.0F, 999995.9F, 35);
		return igalima;
	}
	
	// 獄鎌・イガリマの設定
	public static Item igalima(){
		// ツールマテリアルの詳細な設定

		// 剣として詳細設定を行う
		Item igalima = new ItemSword(SymphogearModCore.IGALIMA)
			.setCreativeTab(CreativeTabs.tabCombat)
			.setUnlocalizedName("igalima")
			.setTextureName("symphogear:igalima")
			.setMaxStackSize(1);

		// 登録
		GameRegistry.registerItem(igalima, "igalimaScythe");

		return igalima;
	}

	// ネフシュタンの鎧のマテリアル設定
	public static ArmorMaterial nehushtanMaterial(){
		ArmorMaterial nehuMaterial = EnumHelper.addArmorMaterial("NEHUSHTAN", 42, new int [] { 3, 8, 6, 3 }, 35);
		
		//金床修復時の要求素材
		nehuMaterial.customCraftingMaterial = Items.diamond_chestplate;

		return nehuMaterial;
	}

	// ネフシュタンの鎧の設定
	public static Item[] nehushtan(){
		// 詳細設定のリスト
		// 頭、胴、脚、足の順かつ各要素は
		// {言語が非対応の時に表示される名前, テクスチャの指定, ゲームレジストリへの登録名}
		String armorSettings[][] = {
			{"NehushtanHelmet",		"symphogear:neh_helmet",	"NehushtanHelmet"	},
			{"NehushtanChest",		"symphogear:neh_chestplate","NehushtanChest"	},
			{"NehushtanLeggins",	"symphogear:neh_leggins",	"NehushtanLeggins"	},
			{"NehushtanBoots",		"symphogear:neh_boots",		"NehushtanBoots"	}
		};

		// 装備数は4つ（定数）とみなし、配列に防具として定義
		Item[] nehushtan = new Item[4];
		
		for(int i = 0; i < 4; i++){
			// 詳細設定
			Item armorItem = new NehuItemArmor(i)
				.setUnlocalizedName(armorSettings[i][0])
				.setTextureName(armorSettings[i][1])
				.setMaxStackSize(1);

			nehushtan[i] = armorItem;
			
			// ゲームに登録
			GameRegistry.registerItem(armorItem, armorSettings[i][2]);
		}

		return nehushtan;
	}

	public static ToolMaterial redSaberMaterial(){
		return EnumHelper.addToolMaterial("SABER", 0, 730, 0.0F, 5.0F, 14);
	}
	
	public static Item redSaber(){
		// 剣として詳細設定を行う
		Item redsaber = new ItemSword(SymphogearModCore.SABER)
			.setCreativeTab(CreativeTabs.tabCombat)
			.setUnlocalizedName("red_saber")
			.setTextureName("symphogear:red_saber")
			.setMaxStackSize(1);

		// 登録
		GameRegistry.registerItem(redsaber, "red_saber");

		return redsaber;
	}
}
