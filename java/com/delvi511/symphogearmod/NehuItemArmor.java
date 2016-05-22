package com.delvi511.symphogearmod;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NehuItemArmor extends ItemArmor{
	// 防具のテクスチャ
	public static final String HELMET_TEXTURE = "symphogear:neh_layer_1";
	public static final String CHESTPLATE_TEXTURE = "symphogear:neh_layer_1";
	public static final String LEGGINGS_TEXTURE = "symphogear:neh_layer_2";
	public static final String BOOTS_TEXTURE = "symphogear:neh_layer_1";
	
	private Config config;

	/**
	 * @constructor
	 * @param type
	 */
	public NehuItemArmor(int type, ArmorMaterial nehushtanMaterial, Config config){
		super(nehushtanMaterial, 0, type);
		
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
			executeArmorPurge(world, player, itemStack);
		}
	}
	
	/**
	 * アーマーパージを指定AABB内の敵性mobに対して実行します。
	 * @param world
	 * @param player
	 * @param itemStack
	 */
	@SuppressWarnings("unchecked")
	private void executeArmorPurge(World world, EntityPlayer player, ItemStack itemStack){
		List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(50, 10, 50));

		for(Entity entity : entityList){
			boolean isEntityEnemy = entity instanceof EntityMob || entity instanceof EntitySlime;

			if(entity.isEntityAlive() && !entity.isDead && isEntityEnemy){
				// mobにダメージを与え、防具を破壊
				entity.attackEntityFrom(DamageSource.causeMobDamage((EntityPlayer)player), 500);
				itemStack.damageItem(this.getMaxItemUseDuration(itemStack), (EntityPlayer)player);
			}
		}
	}
}