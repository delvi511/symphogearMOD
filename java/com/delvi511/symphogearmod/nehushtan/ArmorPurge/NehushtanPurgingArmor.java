package com.delvi511.symphogearmod.nehushtan.ArmorPurge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class NehushtanPurgingArmor extends ItemArmor{
	public static final String HELMET_TEXTURE = "symphogear:neh_pg_layer_1";
	public static final String CHESTPLATE_TEXTURE = "symphogear:neh_pg_layer_1";
	public static final String LEGGINGS_TEXTURE = "symphogear:neh_pg_layer_2";
	public static final String BOOTS_TEXTURE = "symphogear:neh_pg_layer_1";

	public NehushtanPurgingArmor(ArmorMaterial material, int type){
		super(material, 0, type);
		this.setCreativeTab(null);
	}
	
	/*
	 * TODO by Kory33
	 * 
	 * 防具をインベントリから外せないようにする。
	 * 防具がダメージを受けないようにする。
	 */
	
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type){
		if (this.armorType == 2){
			return "symphogear:textures/models/armor/neh_pg_layer_2.png";
		}else{
			return "symphogear:textures/models/armor/neh_pg_layer_1.png";
		}
	}

}
