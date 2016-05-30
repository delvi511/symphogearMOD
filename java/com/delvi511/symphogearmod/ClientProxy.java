package com.delvi511.symphogearmod;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public int addArmor(String par1Str)	{
		return RenderingRegistry.addNewArmourRendererPrefix(par1Str);
	}

	@Override
	public void registerRenderers(){
//		RenderingRegistry.registerEntityRenderingHandler(ArmorProjectile.class, new RenderSnowball());
	}
}
