package com.delvi511.symphogearmod;

import com.delvi511.symphogearmod.nehushtan.NehushtanArmor;
import com.delvi511.symphogearmod.nehushtan.ArmorPurge.NehushtanPurgingArmor;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class SymphogearItems {
	public static final ToolMaterial igalimaMaterial = EnumHelper.addToolMaterial("IGALIMA", 0, 1590, 0.0F, 999995.9F, 35);
	public static final ArmorMaterial nehushtanMaterial = EnumHelper.addArmorMaterial("NEHUSHTAN", 42, new int [] { 3, 8, 6, 3 }, 35);
	public static final ArmorMaterial purgingMaterial = EnumHelper.addArmorMaterial("PURGING_ARMOR", -1 & 0x7fffffff, new int [] { 0, 0, 0, 0 }, 0);;
	public static final ToolMaterial redSaberMaterial = EnumHelper.addToolMaterial("SABER", 0, 730, 0.0F, 5.0F, 14);

	static{
		nehushtanMaterial.customCraftingMaterial = Items.diamond_chestplate;
	}

	/**
	 * 追加分のアイテムを初期化します。
	 */
	public static void initItems(){
		initIgalima();		
		initNehushtan();
		initRedSaber();
	}

	private static void initIgalima() {
		GameRegistry.registerItem(new ItemSword(igalimaMaterial)
				.setCreativeTab(CreativeTabs.tabCombat)
				.setUnlocalizedName("igalima")
				.setTextureName("symphogear:igalima")
				.setMaxStackSize(1),
				"igalimaScythe");
	}

	private static void initNehushtan(){
		String nehuArmorSettings[][] = {
			{"NehushtanHelmet",		"symphogear:neh_helmet",	"NehushtanHelmet"	},
			{"NehushtanChest",		"symphogear:neh_chestplate","NehushtanChest"	},
			{"NehushtanLeggins",	"symphogear:neh_leggins",	"NehushtanLeggins"	},
			{"NehushtanBoots",		"symphogear:neh_boots",		"NehushtanBoots"	}
		};

		String nehuPurgingArmorSettings[][] = {
			{"NehushtanPurgingHelmet",	"symphogear:neh_pg_helmet",		"NehushtanPurgingHelmet"	},
			{"NehushtanPurgingChest",	"symphogear:neh_pg_chestplate",	"NehushtanPurgingChest"	},
			{"NehushtanPurgingLeggins",	"symphogear:neh_pg_leggins",	"NehushtanPurgingLeggins"	},
			{"NehushtanPurgingBoots",	"symphogear:neh_pg_boots",		"NehushtanPurgingBoots"	}
		};

		for(int i = 0; i < 4; i++){
			GameRegistry.registerItem(new NehushtanArmor(nehushtanMaterial, nehushtanMaterial, i)
					.setUnlocalizedName(nehuArmorSettings[i][0])
					.setTextureName(nehuArmorSettings[i][1])
					.setMaxStackSize(1),
					nehuArmorSettings[i][2]);
			
			GameRegistry.registerItem(new NehushtanPurgingArmor(purgingMaterial, i)
					.setUnlocalizedName(nehuPurgingArmorSettings[i][0])
					.setTextureName(nehuPurgingArmorSettings[i][1])
					.setMaxStackSize(1),
					nehuPurgingArmorSettings[i][2]);
		}
	}

	private static void initRedSaber() {
		GameRegistry.registerItem(new ItemSword(redSaberMaterial)
				.setCreativeTab(CreativeTabs.tabCombat)
				.setUnlocalizedName("red_saber")
				.setTextureName("symphogear:red_saber")
				.setMaxStackSize(1),
				"red_saber");
	}
}
