package com.delvi511.symphogearmod;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

@SideOnly(value=Side.CLIENT)
public class AProjectileRenderer extends Render{
	/** Tessellatorのインスタンス */
	private static final Tessellator tessellator = Tessellator.instance;
	
	/** エンティティ軌跡開始時の軌跡の半径*/
	private static final double TRAIL_BEGIN_RADIUS = 0.3D;
	
	/** 軌跡半径の減衰係数*/
	private static final double TRAIL_DELAY_COEFF = TRAIL_BEGIN_RADIUS / ArmorProjectile.POSITION_RECORD_TICK;
	
	/** 軌跡の断面の頂点数（ >= 3）*/
	private static final int TRAIL_VERTICES = 4;
	
	/**
	 * 実際にレンダー処理を行う関数
	 * @param entity
	 * @param x
	 * @param y
	 * @param z
	 * @param yaw
	 * @param renderTick
	 */
	public void doRender(ArmorProjectile entity, double x, double y, double z, float yaw, float renderTick) {
		// レンダー開始処理
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		
		// 軌跡の開始位置
		Vec3 trailBegin = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);
		Vec3 prevTrailBegin = null;

		this.renderTip(trailBegin);
		
		// 軌跡を数ティックの位置情報からレンダリングする
		for(int delayTick = 0; delayTick < ArmorProjectile.POSITION_RECORD_TICK; delayTick++){
			// 軌跡終了位置を取得
			Vec3 trailEnd = entity.getCoordBefore(delayTick);
			Vec3 nxtTrailEnd = entity.getCoordBefore(delayTick + 1);
			
			this.renderTrail(trailBegin, trailEnd, prevTrailBegin, nxtTrailEnd, delayTick);
			
			// 軌跡の開始位置を更新
			prevTrailBegin = trailBegin;
			trailBegin = trailEnd;
		}
		
		// 終了処理
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}

	/**
	 * 引数をキャストしてから実際のレンダー処理関数を呼び出す関数。
	 */
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float renderTick){
		this.doRender((ArmorProjectile)entity, x, y, z, yaw, renderTick);
	}
	
	/**
	 * エンティティの先端（座標：[x, y, z]）を描画します。
	 * @param x
	 * @param y
	 * @param z
	 */
	public void renderTip(Vec3 tipVec){
		// TODO
	}
	
	/**
	 * エンティティの軌跡を描画します。 
	 * prevBeginとnextEnd引数は、それぞれ前と次の軌跡が存在する時にのみ参照されます。
	 *
	 * @param begin 軌跡開始位置
	 * @param end 軌跡終了位置
	 * @param prevBegin 前の軌跡の開始地点
	 * @param nextEnd 次の軌跡の終了地点
	 * @param trailDelay 軌跡の古さ（大きいほど軌跡が細くなる）
	 */
	public void renderTrail(Vec3 begin, Vec3 end, Vec3 prevBegin, Vec3 nextEnd, int trailDelay){
		//TODO
		tessellator.setColorRGBA_F(0.9F, 0.3F, 0.7F, 0.6F);
		
		double trailBeginRadius = this.getTrailBeginRadius(trailDelay);
		double trailEndRadius = this.getTrailBeginRadius(trailDelay + 1);
		
		tessellator.draw();
	}
	
	/**
	 * エンティティの軌跡の開始半径を軌跡の古さから算出します。
	 * @param trailDelay 軌跡の古さ
	 * @return
	 */
	private double getTrailBeginRadius(int trailDelay){
		return -trailDelay * TRAIL_DELAY_COEFF + TRAIL_BEGIN_RADIUS;
	}
}
