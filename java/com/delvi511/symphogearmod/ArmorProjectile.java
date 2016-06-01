package com.delvi511.symphogearmod;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class ArmorProjectile extends Entity {
	/**　パージ使用者　*/
	private EntityPlayer shootingEntity;
	
	/** 1ティック当たりの移動距離 */
	private double motionPerTick;

	/** エンティティが存在したティック数 */
	private int ticksAlive;

	/** 落下加速度 (m*t^(-2)) */
	private static final double DOWNWARD_ACCEL = 0.00275D;
	/** ダメージ量 */
	private static final float DAMAGE_AMT = 15.0F;
	/** ノックバック量 */
	private static final double KNOCKBACK_STRENGTH = 1.0D;	
	
	/**
	 * アーマーパージの飛翔体エンティティを生成します。
	 * @param shooter アーマーパージ使用者
	 * @param yOffset 使用者の足元からの位置
	 * @param pitch ピッチ
	 * @param yaw ヨー
	 * @param speed ティック毎の移動距離
	 */
	public ArmorProjectile(EntityPlayer shooter, double yOffset, float pitch, float yaw, double speed){
		super(shooter.getEntityWorld());
		this.shootingEntity = shooter;

		// 位置を初期化
		this.posX = shooter.posX;
		this.posY = shooter.posY + yOffset;
		this.posZ = shooter.posZ;
		
		this.motionPerTick = speed;

		// 角度をセット
		this.rotationPitch     = pitch;
		this.prevRotationPitch = this.rotationPitch;		
		this.rotationYaw     = yaw;
		this.prevRotationYaw = this.rotationYaw;

		// 速度を初期化
		this.setMotionsFromAngle();
		
		this.ticksAlive = 0;
		this.setSize(.1F, .1F);
	}
	
	/**
	 * ティック当たりの直交軸沿いの移動距離を移動距離と角度から算出し格納します。
	 */
	private void setMotionsFromAngle(){
		float xzFactor = (float)Math.cos(this.rotationPitch / 180 * Math.PI);

		this.motionX = Math.sin(this.rotationYaw / 180 * Math.PI)   * this.motionPerTick * xzFactor;
		this.motionZ = -Math.cos(this.rotationYaw / 180 * Math.PI)  * this.motionPerTick * xzFactor;
		this.motionY = Math.sin(this.rotationPitch / 180 * Math.PI) * this.motionPerTick;
	}

	@Override
	public void onUpdate(){
		if(!this.worldObj.isRemote && !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)){
			this.setDead();
			return;
		}
		
		super.onUpdate();
		if(this.ticksAlive == 600){
			this.setDead();
			return;
		}

		++this.ticksAlive;
		
		Vec3 posVec = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 nxtVec = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(posVec, nxtVec);
		
		if (movingObjectPosition != null){
			nxtVec = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
		}
		
		/** 衝突したエンティティ */
		Entity colEntity = null;
		
		/** 飛翔体から一番近いエンティティへの距離*/
		double minDist = 0.0D;
		
		@SuppressWarnings("rawtypes")
		List entityList = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		for(int i = 0; i < entityList.size(); ++i){
			Entity entity = (Entity)entityList.get(i);

			if(entity.canBeCollidedWith() && !entity.isEntityEqual(this.shootingEntity)){
				double f = 0.1F;
				AxisAlignedBB aabb = entity.boundingBox.expand(f, f, f);
				MovingObjectPosition aabbIntercept = aabb.calculateIntercept(posVec, nxtVec);
				
				if(aabbIntercept != null){
					double dist = posVec.distanceTo(aabbIntercept.hitVec);
					
					if(dist < minDist || minDist == 0.0D){
						colEntity = entity;
						minDist = dist;
					}
				}
			}
		}
		
		if(colEntity != null){
			movingObjectPosition = new MovingObjectPosition(colEntity);
		}
		
		if (movingObjectPosition != null){
			this.onImpact(movingObjectPosition);
		}

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        
        this.motionY -= DOWNWARD_ACCEL;
        
        this.setPosition(this.posX, this.posY, this.posZ);
	}

	/**
	 * ブロックまたはエンティティに衝突した際に呼ばれる関数。
	 * @param movingObjectPosition
	 */
	private void onImpact(MovingObjectPosition movingObjectPosition){
		if(!this.worldObj.isRemote){
			if(movingObjectPosition.entityHit != null){
				movingObjectPosition.entityHit.attackEntityFrom(new EntityDamageSourceIndirect("armorProjectile", this, this.shootingEntity).setProjectile(), DAMAGE_AMT);
				this.causeKnockbackOnEntity(movingObjectPosition.entityHit);
			}
			
			this.worldObj.newExplosion((Entity) null, this.posX, this.posY, this.posZ, 0.0F, true, false);
			this.setDead();
		}
	}

	private void causeKnockbackOnEntity(Entity entityHit) {
		double v = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY);
		entityHit.addVelocity(this.motionX * KNOCKBACK_STRENGTH / v, 0.1D, this.motionY * KNOCKBACK_STRENGTH / v);
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
}
