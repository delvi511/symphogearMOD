package com.delvi511.symphogearmod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class SymphogearItems {
	public ToolMaterial igalimaMaterial;
	public ArmorMaterial nehushtanMaterial;
	public ToolMaterial redSaberMaterial;
	
	public Item igalima;
	public Item[] nehushtan;
	public Item redSaber;
	
	/**
	 * 追加分のアイテムを初期化します。
	 */
	public void initItems(Config config){
		this.initIgalimaMaterial();
		this.initIgalima();
		
		this.initNehushtanMaterial();
		this.initNehushtan(config);
		
		this.initRedSaberMaterial();
		this.initRedSaber();
	}
	
	/**
	 * レシピを追加します
	 */
	public void addRecipes(){
		Recipes.registerIgalima(this.igalima);
	}
	
	/**
	 * 獄鎌・イガリマのマテリアルを初期化します。
	 */
	private void initIgalimaMaterial(){
		this.igalimaMaterial = EnumHelper.addToolMaterial("IGALIMA", 0, 1590, 0.0F, 999995.9F, 35);
	}
	
	/**
	 *  獄鎌・イガリマを初期化します。
	 */
	private void initIgalima(){
		// 剣として詳細設定を行う
		this.igalima = new ItemSword(this.igalimaMaterial)
			.setCreativeTab(CreativeTabs.tabCombat)
			.setUnlocalizedName("igalima")
			.setTextureName("symphogear:igalima")
			.setMaxStackSize(1);

		// 登録
		GameRegistry.registerItem(igalima, "igalimaScythe");
	}

	/**
	 *  ネフシュタンの鎧のマテリアルを初期化します。
	 */
	private void initNehushtanMaterial(){
		nehushtanMaterial = EnumHelper.addArmorMaterial("NEHUSHTAN", 42, new int [] { 3, 8, 6, 3 }, 35);
		
		//金床修復時の要求素材
		nehushtanMaterial.customCraftingMaterial = Items.diamond_chestplate;
	}

	/**
	 *  ネフシュタンの鎧を初期化します。
	 */
	private void initNehushtan(Config config){
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
		this.nehushtan = new Item[4];
		
		for(int i = 0; i < 4; i++){
			// 詳細設定
			Item armorItem = new NehuItemArmor(i, this.nehushtanMaterial, config)
				.setUnlocalizedName(armorSettings[i][0])
				.setTextureName(armorSettings[i][1])
				.setMaxStackSize(1);

			this.nehushtan[i] = armorItem;
			
			// ゲームに登録
			GameRegistry.registerItem(armorItem, armorSettings[i][2]);
		}
	}

	/**
	 * Red Saberのマテリアルを初期化します。
	 */
	private void initRedSaberMaterial(){
		this.redSaberMaterial = EnumHelper.addToolMaterial("SABER", 0, 730, 0.0F, 5.0F, 14);
	}

	/**
	 * Red Saberを初期化します。
	 */
	private void initRedSaber(){
		// 剣として詳細設定を行う
		this.redSaber = new ItemSword(this.redSaberMaterial)
			.setCreativeTab(CreativeTabs.tabCombat)
			.setUnlocalizedName("red_saber")
			.setTextureName("symphogear:red_saber")
			.setMaxStackSize(1);

		// 登録
		GameRegistry.registerItem(this.redSaber, "red_saber");
	}
}
