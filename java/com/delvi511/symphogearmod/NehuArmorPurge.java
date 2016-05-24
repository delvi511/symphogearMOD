package com.delvi511.symphogearmod;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class NehuArmorPurge{
	/**
	 * アーマーパージを指定AABB内の敵性mobに対して実行します。
	 * @param world
	 * @param player
	 * @param itemStack
	 */
	@SuppressWarnings("unchecked")
	public static void executeArmorPurge(World world, EntityPlayer player, ItemStack itemStack){
		  List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(50, 10, 50));

		  boolean hostileMobExist = false;

		  for(Entity entity : entityList){
		   boolean isEntityEnemy = entity instanceof EntityMob || entity instanceof EntitySlime;

		   if(entity.isEntityAlive() && !entity.isDead && isEntityEnemy){
		    // mobにダメージを与える
		    entity.attackEntityFrom(DamageSource.causeMobDamage((EntityPlayer)player), 500);

		    // フラグを変更
		    hostileMobExist = true;
		   }
		  }

		  if(hostileMobExist){
		   // 爆発を起こす
		   Vec3 playerPos = player.getPosition(1.0F);
		   player.getEntityWorld().newExplosion(player, playerPos.xCoord, playerPos.yCoord + 1.0, playerPos.zCoord, 100.F, true, true);

		   // 防具を破壊
		   itemStack.damageItem(itemStack.getMaxItemUseDuration(), (EntityPlayer)player);
		  }
		 }

}
