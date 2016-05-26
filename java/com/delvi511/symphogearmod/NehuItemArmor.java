package com.delvi511.symphogearmod;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NehuItemArmor extends ItemArmor{
	// 防具のテクスチャ
	public static final String HELMET_TEXTURE = "symphogear:neh_layer_1";
	public static final String CHESTPLATE_TEXTURE = "symphogear:neh_layer_1";
	public static final String LEGGINGS_TEXTURE = "symphogear:neh_layer_2";
	public static final String BOOTS_TEXTURE = "symphogear:neh_layer_1";
	
	private Config config;
	private ArmorMaterial purgingMaterial;

	/**
	 * @constructor
	 * @param type
	 */
	public NehuItemArmor(ArmorMaterial nehushtanMaterial, ArmorMaterial purgingMaterial, int type, Config config){
		super(nehushtanMaterial, 0, type);
		
		this.purgingMaterial = purgingMaterial;
		
		// クリエイティブのタブを「戦闘」に指定
		this.setCreativeTab(CreativeTabs.tabCombat);
		
		this.config = config;
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
		if (!world.isRemote && player.isSneaking() && Keyboard.isKeyDown(this.config.getPurgeKey())){
			NehuPurgingEvent purge = new NehuPurgingEvent(player, purgingMaterial, 200);
			if(purge.isExecutable()){
				purge.execute();
			}
		}
	}
}
