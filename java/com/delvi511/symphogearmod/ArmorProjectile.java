package com.delvi511.symphogearmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;

public class ArmorProjectile extends EntityArrow {
	private double motionPerTick;
	
	public ArmorProjectile(EntityPlayer shooter, double yOffset, float pitch, float yaw, double speed){
		super(shooter.getEntityWorld(), shooter.posX, shooter.posY + yOffset, shooter.posZ);
		this.shootingEntity = shooter;
		
		this.motionPerTick = speed;
		this.rotationPitch = pitch;
		this.rotationYaw = yaw;
		
		this.setMotionsFromAngle();
	}
	
	private void setMotionsFromAngle(){
		float xzFactor = (float)Math.cos(this.rotationPitch / 180.0F * (float)Math.PI);
		this.motionX = Math.sin(this.rotationYaw   / 180.0F * (float)Math.PI) * this.motionPerTick * xzFactor;
		this.motionZ = -Math.cos(this.rotationYaw  / 180.0F * (float)Math.PI) * this.motionPerTick * xzFactor;
		this.motionY = Math.sin(this.rotationPitch / 180.0F * (float)Math.PI) * this.motionPerTick;
	}
}
