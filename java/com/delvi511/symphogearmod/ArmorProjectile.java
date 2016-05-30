package com.delvi511.symphogearmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;

public class ArmorProjectile extends EntityArrow {
	private double motionPerTick;
	
	public ArmorProjectile(EntityPlayer shooter, double yOffset, float pitch, float yaw, double speed){
		super(shooter.getEntityWorld(), shooter.posX, shooter.posY + yOffset, shooter.posZ);
		this.shootingEntity = shooter;
		
		this.motionPerTick = speed;
		this.prevRotationPitch = this.rotationPitch = pitch;
		this.prevRotationYaw   = this.rotationYaw   = yaw;

		this.setMotionsFromAngle();
	}
	
	private void setMotionsFromAngle(){
		float xzFactor = (float)Math.sin(this.rotationPitch / 180 * Math.PI);
		this.prevPosX = this.motionX = Math.sin(this.rotationYaw / 180 * Math.PI)   * this.motionPerTick * xzFactor;
		this.prevPosZ = this.motionZ = -Math.cos(this.rotationYaw / 180 * Math.PI)  * this.motionPerTick * xzFactor;
		this.prevPosY = this.motionY = Math.cos(this.rotationPitch / 180 * Math.PI) * this.motionPerTick;
	}
}
