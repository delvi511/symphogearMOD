package com.delvi511.symphogearmod.nehushtan;

import org.lwjgl.input.Keyboard;

import com.delvi511.symphogearmod.Config;
import com.delvi511.symphogearmod.nehushtan.ArmorPurge.NehushtanArmorPurgeEvent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NehushtanArmor extends ItemArmor{
	// 防具のテクスチャ
	public static final String HELMET_TEXTURE = "symphogear:neh_layer_1";
	public static final String CHESTPLATE_TEXTURE = "symphogear:neh_layer_1";
	public static final String LEGGINGS_TEXTURE = "symphogear:neh_layer_2";
	public static final String BOOTS_TEXTURE = "symphogear:neh_layer_1";
	
	private ArmorMaterial purgingMaterial;

	/**
	 * @constructor
	 * @param type
	 */
	public NehushtanArmor(ArmorMaterial nehushtanMaterial, ArmorMaterial purgingMaterial, int type){
		super(nehushtanMaterial, 0, type);
		
		this.purgingMaterial = purgingMaterial;
		
		// クリエイティブのタブを「戦闘」に指定
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type){
		if (this.armorType == 2){
			return "symphogear:textures/models/armor/neh_layer_2.png";
		}else{
			return "symphogear:textures/models/armor/neh_layer_1.png";
		}
	}
	
	/**
	 * 毎ティック鎧のインスタンスについて実行されます。
	 */
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
		if (!world.isRemote && player.isSneaking() && Keyboard.isKeyDown(Config.getPurgeKey()) && this.armorType == 1){
			NehushtanArmorPurgeEvent purge = new NehushtanArmorPurgeEvent(player);
			if(purge.isExecutable()){
				purge.execute();
			}
		}
	}
}
