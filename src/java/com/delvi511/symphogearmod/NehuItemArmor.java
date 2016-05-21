package com.delvi511.symphogearmod;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NehuItemArmor extends ItemArmor{
	// コンストラクタ
	public NehuItemArmor(int type){
		super(SymphogearModCore.NEHUSHTAN, 0, type);
		
		//クリエイティブのタブを「戦闘」に指定
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	//防具のテクスチャ
	public static final String HELMET_TEXTURE = "symphogear:neh_layer_1";
	public static final String CHESTPLATE_TEXTURE = "symphogear:neh_layer_1";
	public static final String LEGGINGS_TEXTURE = "symphogear:neh_layer_2";
	public static final String BOOTS_TEXTURE = "symphogear:neh_layer_1";

    //renderIndexは以下でArmorTextureを指定する場合はどんな値でも良い。

	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type){
		if (this.armorType == 2){
			return "symphogear:textures/models/armor/neh_layer_2.png";
		}else{
			return "symphogear:textures/models/armor/neh_layer_1.png";
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){		
		// もしプレーヤーがスニーク中かつ設定されたキーを押下しているなら
		if (!world.isRemote && player.isSneaking() && Keyboard.isKeyDown(SymphogearModCore.keyBinding)){
			try{
				List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(50, 10, 50));
				for(Entity entity : entityList){
					if(entity.isEntityAlive() && !entity.isDead && (entity instanceof EntityMob || entity instanceof EntitySlime)){
						entity.attackEntityFrom(DamageSource.causeMobDamage((EntityPlayer)player), 500);
						itemStack.damageItem(this.getMaxItemUseDuration(itemStack), (EntityPlayer)player	);
					}
				}
			}catch(Throwable e){

			}
		}
	}
}