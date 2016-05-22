package com.delvi511.symphogearmod;import org.apache.logging.log4j.Level;import org.apache.logging.log4j.Logger;import org.lwjgl.input.Keyboard;import cpw.mods.fml.common.FMLLog;import cpw.mods.fml.common.Mod;import cpw.mods.fml.common.Mod.EventHandler;import cpw.mods.fml.common.SidedProxy;import cpw.mods.fml.common.event.FMLInitializationEvent;import cpw.mods.fml.common.event.FMLPreInitializationEvent;import net.minecraft.item.Item;import net.minecraft.item.Item.ToolMaterial;import net.minecraft.item.ItemArmor.ArmorMaterial;import net.minecraftforge.common.config.Configuration;@Mod( modid = SymphogearModCore.MODID, version = SymphogearModCore.VERSION )public class SymphogearModCore{	public static Logger log = FMLLog.getLogger();		@Mod.Instance( "SymphogearMOD" )	public static final String MODID = "Symphogear-MOD";	public static final String VERSION = "0.1";	// 追加アイテム	public static Item igalima;	public static Item[] nehushtan;	public static Item redsaber;		// マテリアル	public static ArmorMaterial NEHUSHTAN;	public static ToolMaterial IGALIMA;	public static ToolMaterial SABER;	@SidedProxy(clientSide = "com.delvi511.symphogearmod.ClientProxy", serverSide = "com.delvi511.symphogearmod.CommonProxy")	public static CommonProxy proxy;	// キーバインディング	private static int purgeKeyBinding;	@EventHandler	public void preInit(FMLPreInitializationEvent event){		// 追加アイテムの初期化				IGALIMA = ItemInit.igalimaMaterial();		igalima = ItemInit.igalima();				NEHUSHTAN = ItemInit.nehushtanMaterial();		nehushtan = ItemInit.nehushtan();		SABER = ItemInit.redSaberMaterial();		redsaber = ItemInit.redSaber();				// 設定系の初期化		init_config(event);	}	@EventHandler	public void Init(FMLInitializationEvent e){		Recipes.registerIgalima();	}		private void init_config(FMLPreInitializationEvent event){		Configuration config = new Configuration(event.getSuggestedConfigurationFile());		try{			// Bキーをデフォルトでアーマーパージのキーとして設定			purgeKeyBinding = config.get(Configuration.CATEGORY_GENERAL, "PurgeKeyBinding", Keyboard.KEY_B).getInt();		}catch (Exception e){			FMLLog.log(Level.FATAL, e, "Symphogear-MOD ERROR: failed to configure keybind");		}finally{			config.save();		}		return;	}		public static boolean isPurgeKeyPressed(){		return Keyboard.isKeyDown(purgeKeyBinding);	}}