package com.delvi511.symphogearmod.core;import org.apache.logging.log4j.Logger;import com.delvi511.symphogearmod.Config;import com.delvi511.symphogearmod.Recipes;import com.delvi511.symphogearmod.SymphogearItems;import cpw.mods.fml.common.FMLLog;import cpw.mods.fml.common.Mod;import cpw.mods.fml.common.Mod.EventHandler;import cpw.mods.fml.common.SidedProxy;import cpw.mods.fml.common.event.FMLInitializationEvent;import cpw.mods.fml.common.event.FMLPreInitializationEvent;@Mod( modid = SymphogearModCore.MODID, version = SymphogearModCore.VERSION )public class SymphogearModCore{	public static Logger log = FMLLog.getLogger();		@Mod.Instance( "SymphogearMOD" )	public static final String MODID = "Symphogear-MOD";	public static final String VERSION = "0.1";	@SidedProxy(clientSide = "com.delvi511.symphogearmod.core.ClientProxy", serverSide = "com.delvi511.symphogearmod.core.CommonProxy")	public static CommonProxy proxy;	@EventHandler	public void preInit(FMLPreInitializationEvent event){		// 設定系の初期化		Config config = new Config(event);		config.init();		// 追加アイテムの初期化			SymphogearItems.initItems();	}	@EventHandler	public void Init(FMLInitializationEvent e){		// レシピの追加		Recipes.registerIgalima();	}}