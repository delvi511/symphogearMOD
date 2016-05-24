package com.delvi511.symphogearmod;

import net.minecraft.entity.player.EntityPlayer;

public class NehuPurgingEvent{
	private EntityPlayer armorPurgeUser;
	private boolean isPurgeActive;
	
	private int remainingPurgeTick;
	
	public NehuPurgingEvent(EntityPlayer player, int maxPurgeTick){
		this.armorPurgeUser = player;
		this.remainingPurgeTick = maxPurgeTick;
		this.isPurgeActive = false;
	}
	
	/*
	 * TODO by Kory33
	 * 
	 * onUpdateがisPurgeActiveがtrueになったら毎ティック呼ばれるようにする。
	 * 各メソッドの実装
	 */
	
	public void onUpdate(){
		if(this.isPurgeActive){
			if(this.remainingPurgeTick > 0){
				this.launchArmorProjectile();
				this.remainingPurgeTick--;
			}else{
				this.breakAllArmors();
			}
		}
	}
	
	public boolean isExecutable(){
		return false;
	}

	/**
	 * アーマーパージを実行します
	 */
	public void execute(){
		this.isPurgeActive = true;
		this.replacePlayerArmors();
	}
	
	/**
	 * 強制的にプレーヤーの防具をアーマーパージ中の一時防具に変更します。
	 */
	private void replacePlayerArmors(){
		//TODO
	}
	
	/**
	 * プレーヤーの防具をすべて破壊します
	 */
	private void breakAllArmors(){
		
	}
	
	/**
	 * プレーヤーから防具の破片エンティティを発射します。
	 */
	private void launchArmorProjectile(){
		
	}
}
