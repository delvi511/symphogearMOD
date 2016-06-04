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
	private static final Tessellator t = Tessellator.instance;
	
	/**
	 * @constructor
	 */
	public AProjectileRenderer() {
		super();
		this.shadowSize = 0.0f;
	}
	
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

		this.renderTip(x, y, z);
		
		// 軌跡の開始位置
		Vec3 trailBegin = Vec3.createVectorHelper(x, y, z);

		// 軌跡を数ティックの位置情報からレンダリングする
		for(int delayTick = 0; delayTick < ArmorProjectile.POSITION_RECORD_TICK; delayTick++){
			Vec3 trailEnd = entity.getCoordBefore(delayTick);
			this.renderTrail(trailBegin, trailEnd, delayTick);
			
			// 軌跡の次の開始位置
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
	public void renderTip(double x, double y, double z){
		// TODO
	}
	
	/**
	 * エンティティの軌跡を描画します
	 * @param begin 軌跡開始位置
	 * @param end 軌跡終了位置
	 * @param trailDelay 軌跡の古さ（大きいほど軌跡が細くなる）
	 */
	public void renderTrail(Vec3 begin, Vec3 end, int trailDelay){
		// TODO
	}
}
