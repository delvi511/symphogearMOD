package com.delvi511.symphogearmod.items;

import com.delvi511.symphogearmod.items.nehushtan.NehushtanArmor;
import com.delvi511.symphogearmod.items.nehushtan.ArmorPurge.NehushtanPurgingArmor;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class SymphogearItems {
	/**
	 * 追加分のアイテムを初期化します。
	 */
	public static void initItems(){
		initIgalima();		
		initNehushtan();
		initRedSaber();
	}

	private static void initIgalima() {
		GameRegistry.registerItem(new ItemSword(ToolMaterial.IGALIMA)
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
			GameRegistry.registerItem(new NehushtanArmor(ArmorMaterial.NEHUSHTAN, i)
					.setUnlocalizedName(nehuArmorSettings[i][0])
					.setTextureName(nehuArmorSettings[i][1])
					.setMaxStackSize(1),
					nehuArmorSettings[i][2]);
			
			GameRegistry.registerItem(new NehushtanPurgingArmor(ArmorMaterial.NEHUSHTAN_PURGE, i)
					.setUnlocalizedName(nehuPurgingArmorSettings[i][0])
					.setTextureName(nehuPurgingArmorSettings[i][1])
					.setMaxStackSize(1),
					nehuPurgingArmorSettings[i][2]);
		}
	}

	private static void initRedSaber() {
		GameRegistry.registerItem(new ItemSword(ToolMaterial.RED_SABER)
				.setCreativeTab(CreativeTabs.tabCombat)
				.setUnlocalizedName("red_saber")
				.setTextureName("symphogear:red_saber")
				.setMaxStackSize(1),
				"red_saber");
	}
	
	public static class ToolMaterial{
		public static Item.ToolMaterial IGALIMA = EnumHelper.addToolMaterial("IGALIMA", 0, 1590, 0.0F, 999995.9F, 35);
		public static Item.ToolMaterial RED_SABER = EnumHelper.addToolMaterial("SABER", 0, 730, 0.0F, 5.0F, 14);
	}
	
	public static class ArmorMaterial{
		public static ItemArmor.ArmorMaterial NEHUSHTAN = EnumHelper.addArmorMaterial("NEHUSHTAN", 42, new int [] { 3, 8, 6, 3 }, 35);
		static{NEHUSHTAN.customCraftingMaterial = Items.diamond_chestplate;}

		public static ItemArmor.ArmorMaterial NEHUSHTAN_PURGE = EnumHelper.addArmorMaterial("PURGING_ARMOR", -1 & 0x7fffffff, new int [] { 0, 0, 0, 0 }, 0);;
	}
}
