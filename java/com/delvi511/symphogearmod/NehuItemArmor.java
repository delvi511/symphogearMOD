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

public class NehuItemArmor extends ItemArmor
{

	public NehuItemArmor(int type)
	{
		super(SymphogearModCore.NEHUSHTAN, 0, type);
		this.setCreativeTab(CreativeTabs.tabCombat);//クリエイティブのタブを「戦闘」に指定
	}
	public static final String HELMET_TEXTURE = "symphogear:neh_layer_1";//ヘルメットのテクスチャ
	public static final String CHESTPLATE_TEXTURE = "symphogear:neh_layer_1";//胴当てのテクスチャ
	public static final String LEGGINGS_TEXTURE = "symphogear:neh_layer_2";//レギンスのテクスチャ
	public static final String BOOTS_TEXTURE = "symphogear:neh_layer_1";//ブーツのテクスチャ
    //renderIndexは以下でArmorTextureを指定する場合はどんな値でも良い。

    //ItemStack:防具のItemStack, Entity:装備してるEntity, Slot:装備してるスロット, Layer:オーバーレイかどうか
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type)
	{
	if (this.armorType == 2) {
		return "symphogear:textures/models/armor/neh_layer_2.png";
							 }
		return "symphogear:textures/models/armor/neh_layer_1.png";
	}
@SuppressWarnings("unchecked")
public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
{
	if (!world.isRemote)
	{
		if(player.isSneaking())
		{
			if(Keyboard.isKeyDown(SymphogearModCore.keyBinding))
			{
				try
				{
					List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(50, 10, 50));

					for(Entity entity : list)
					{
						if(entity.isEntityAlive() && !entity.isDead && (entity instanceof EntityMob || entity instanceof EntitySlime))
						{
							entity.attackEntityFrom(DamageSource.causeMobDamage((EntityPlayer)player), 500);
							itemStack.damageItem(this.getMaxItemUseDuration(itemStack), (EntityPlayer)player);
						}
					}
				}
				catch(Throwable e)
				{
				}
			}
		}
	}
}

}