package com.delvi511.symphogearmod;

import com.delvi511.symphogearmod.nehushtan.NehushtanArmor;
import com.delvi511.symphogearmod.nehushtan.ArmorPurge.NehushtanPurgingArmor;

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
	public ArmorMaterial purgingMaterial;
	public ToolMaterial redSaberMaterial;
	
	public Item igalima;
	public Item[] nehushtan;
	public Item[] purgingNehushtan;
	public Item redSaber;
	
	/**
	 * 追加分のアイテムを初期化します。
	 */
	public void initItems(Config config){
		this.initIgalimaMaterial();
		this.initIgalima();
		
		this.initPurgingMaterial();

		this.initNehushtanMaterial();
		this.initNehushtan();
				
		this.initRedSaberMaterial();
		this.initRedSaber();
	}
	
	/**
	 * レシピを追加します。
	 */
	public void addRecipes(){
		Recipes.registerIgalima(this.igalima);
		
		return;
	}
	
	/**
	 * 獄鎌・イガリマのマテリアルを初期化します。
	 */
	private void initIgalimaMaterial(){
		this.igalimaMaterial = EnumHelper.addToolMaterial("IGALIMA", 0, 1590, 0.0F, 999995.9F, 35);
		
		return;
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
		
		return;
	}

	/**
	 * アーマーパージ中に使用されるマテリアルを初期化します。
	 */
	private void initPurgingMaterial() {
		this.purgingMaterial = EnumHelper.addArmorMaterial("PURGING_ARMOR", -1 & 0x7fffffff, new int [] { 0, 0, 0, 0 }, 0);
		
		return;
	}
	
	/**
	 *  ネフシュタンの鎧のマテリアルを初期化します。
	 */
	private void initNehushtanMaterial(){
		this.nehushtanMaterial = EnumHelper.addArmorMaterial("NEHUSHTAN", 42, new int [] { 3, 8, 6, 3 }, 35);
		
		//金床修復時の要求素材
		this.nehushtanMaterial.customCraftingMaterial = Items.diamond_chestplate;
		
		return;
	}

	/**
	 *  ネフシュタンの鎧を初期化します。
	 */
	private void initNehushtan(){
		// 詳細設定のリスト
		// 頭、胴、脚、足の順かつ各要素は
		// {言語が非対応の時に表示される名前, テクスチャの指定, ゲームレジストリへの登録名}
		String armorSettings[][] = {
			{"NehushtanHelmet",	"symphogear:neh_helmet",	"NehushtanHelmet"	},
			{"NehushtanChest",		"symphogear:neh_chestplate","NehushtanChest"	},
			{"NehushtanLeggins",	"symphogear:neh_leggins",	"NehushtanLeggins"	},
			{"NehushtanBoots",		"symphogear:neh_boots",		"NehushtanBoots"	}
		};

		String purgingArmorSettings[][] = {
			{"NehushtanPurgingHelmet",	"symphogear:neh_pg_helmet",		"NehushtanPurgingHelmet"	},
			{"NehushtanPurgingChest",		"symphogear:neh_pg_chestplate",	"NehushtanPurgingChest"	},
			{"NehushtanPurgingLeggins",	"symphogear:neh_pg_leggins",	"NehushtanPurgingLeggins"	},
			{"NehushtanPurgingBoots",		"symphogear:neh_pg_boots",		"NehushtanPurgingBoots"	}
		};

		// 装備数は4つ（定数）とみなし、配列に防具として定義
		this.nehushtan = new Item[4];
		this.purgingNehushtan = new Item[4];
		
		for(int i = 0; i < 4; i++){
			// 詳細設定
			Item nehuArmor = new NehushtanArmor(this.nehushtanMaterial, this.nehushtanMaterial, i)
				.setUnlocalizedName(armorSettings[i][0])
				.setTextureName(armorSettings[i][1])
				.setMaxStackSize(1);

			Item purgingArmor = new NehushtanPurgingArmor(this.purgingMaterial, i)
				.setUnlocalizedName(purgingArmorSettings[i][0])
				.setTextureName(purgingArmorSettings[i][1])
				.setMaxStackSize(1);
			
			this.nehushtan[i] = nehuArmor;
			this.purgingNehushtan[i] = purgingArmor;
			
			// ゲームに登録
			GameRegistry.registerItem(this.nehushtan[i], armorSettings[i][2]);
			GameRegistry.registerItem(this.purgingNehushtan[i], purgingArmorSettings[i][2]);
		}
		
		return;
	}
	
	/**
	 * Red Saberのマテリアルを初期化します。
	 */
	private void initRedSaberMaterial(){
		this.redSaberMaterial = EnumHelper.addToolMaterial("SABER", 0, 730, 0.0F, 5.0F, 14);

		return;
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
		return;
	}
}
