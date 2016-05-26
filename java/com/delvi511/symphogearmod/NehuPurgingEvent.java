package com.delvi511.symphogearmod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class NehuPurgingEvent{
	private EntityPlayer armorPurgeUser;
	
	private int remainingPurgeTick;
	
	private NehuPurgingArmor[] purgingNehushtan;
	
	public NehuPurgingEvent(EntityPlayer player, ArmorMaterial purgingMaterial, int maxPurgeTick){
		this.armorPurgeUser = player;
		this.remainingPurgeTick = maxPurgeTick;
		
		this.purgingNehushtan = new NehuPurgingArmor[4];
		for(int i = 0; i < 4; i++){
			this.purgingNehushtan[i] = new NehuPurgingArmor(purgingMaterial, i);
		}
	}
	
	@SubscribeEvent
	public void onUpdate(TickEvent.PlayerTickEvent event){
		if(this.remainingPurgeTick > 0){
			this.launchArmorProjectile();
			this.remainingPurgeTick--;
		}else{
			FMLCommonHandler.instance().bus().unregister(this);
			this.breakAllArmors();
		}
	}
	
	/**
	 * アーマーパージを実行出来るかどうかを返します。
	 * @return 実行可能かの真偽値
	 */
	public boolean isExecutable(){
		/*プレーヤーがネフシュタンの鎧のセットを着ていれば真*/
		boolean isNehushtanArmor = true;
		
		return isNehushtanArmor;
	}

	/**
	 * アーマーパージを実行します
	 */
	public void execute(){
		this.replacePlayerArmors();
		FMLCommonHandler.instance().bus().register(this);
	}
	
	/**
	 * 強制的にプレーヤーの防具をアーマーパージ中の一時防具に変更します。
	 */
	private void replacePlayerArmors(){
		for(int i = 0; i < 4; i++){
			this.armorPurgeUser.inventory.armorInventory[i] = new ItemStack(this.purgingNehushtan[i]);
		}
		// TODO 破壊音を鳴らす
	}
	
	/**
	 * プレーヤーの防具をすべて破壊します
	 */
	private void breakAllArmors(){
		for(int i = 0; i < 4; i++){
			this.armorPurgeUser.inventory.armorInventory[i] = null;
		}
	}
	
	/**
	 * プレーヤーから防具の破片エンティティを発射します。
	 */
	private void launchArmorProjectile(){
		// TODO
	}
}
