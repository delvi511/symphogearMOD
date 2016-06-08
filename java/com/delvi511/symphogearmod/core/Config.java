package com.delvi511.symphogearmod.core;

import org.apache.logging.log4j.Level;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
	private static int purgeKeyBinding = Keyboard.KEY_B;

	public static void init(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try{
			config.load();
			purgeKeyBinding = config.get(Configuration.CATEGORY_GENERAL, "PurgeKeyBinding", Keyboard.KEY_B).getInt();
		}catch (Exception e){
			FMLLog.log(Level.FATAL, e, "Symphogear-MOD ERROR: failed to load configuration");
		}finally{
			config.save();
		}

	}

	/**
	 * アーマーパージの設定キーを取得します。
	 * @return キーを表す整数
	 */
	public static int getPurgeKey(){
		return purgeKeyBinding;
	}
}
