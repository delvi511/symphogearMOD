package com.delvi511.symphogearmod;

import org.apache.logging.log4j.Level;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
	private static int purgeKeyBinding;
	private FMLPreInitializationEvent event;
	
	public Config(FMLPreInitializationEvent event){
		this.event = event;
	}
	
	public void init(){
		Configuration config = new Configuration(this.event.getSuggestedConfigurationFile());

		try{
			// Bキーをデフォルトでアーマーパージのキーとして設定
			purgeKeyBinding = config.get(Configuration.CATEGORY_GENERAL, "PurgeKeyBinding", Keyboard.KEY_B).getInt();
		}catch (Exception e){
			FMLLog.log(Level.FATAL, e, "Symphogear-MOD ERROR: failed to configure keybind");
		}finally{
			config.save();
		}

		return;

	}

	/**
	 * アーマーパージの設定キーを取得します。
	 * @return キーを表す整数
	 */
	public int getPurgeKey(){
		return purgeKeyBinding;
	}
}