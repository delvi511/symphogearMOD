package com.delvi511.symphogearmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class ArmorProjectile extends Entity implements IProjectile {
	private double motionPerTick;
	private EntityPlayer shootingEntity;
	
	public ArmorProjectile(EntityPlayer shooter, double yOffset, float pitch, float yaw, double speed){
		super(shooter.getEntityWorld());
		this.shootingEntity = shooter;
		
		this.posX = shooter.posX;
		this.posY = shooter.posY + yOffset;
		this.posZ = shooter.posZ;
		
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

	@Override
	public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	protected void entityInit() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
