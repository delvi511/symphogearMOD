package com.delvi511.symphogearmod;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class AProjectileRenderer extends Render{	
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
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glColor4f(2.0F, .5F, 1.5F, .9F);

		// 終了処理
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}

	/**
	 * 引数をキャストしてから実際のレンダー処理関数を呼び出す関数。
	 */
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float renderTick){
		this.doRender((ArmorProjectile)entity, x, y, z, yaw, renderTick);
	}
}
