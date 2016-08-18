package com.delvi511.symphogearmod.core;

import com.delvi511.symphogearmod.client.render.entity.RenderArmorProjectile;
import com.delvi511.symphogearmod.entity.projectile.EntityArmorProjectile;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public int addArmor(String par1Str)	{
		return RenderingRegistry.addNewArmourRendererPrefix(par1Str);
	}

	@Override
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityArmorProjectile.class, new RenderArmorProjectile());
	}
}
