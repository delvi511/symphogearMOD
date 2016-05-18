package com.delvi511.symphogearmod;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

	@Mod( modid = SymphogearModCore.MODID, version = SymphogearModCore.VERSION )
	public class SymphogearModCore
	{
		public static Logger log = FMLLog.getLogger();
	    @Mod.Instance( "SymphogearMOD" )
	    public static final String MODID = "Symphogear-MOD";
	    public static final String VERSION = "0.1";
	    public static Item nehushtan_helmet;//アイテムを登録してる
	    public static Item nehushtan_chest;
	    public static Item nehushtan_leggins;
	    public static Item nehushtan_boots;
	    public static Item igalima;
	    public static Item redsaber;


	    public static ArmorMaterial NEHUSHTAN;//アーマーマテリアルの名前を登録してる
	    public static ToolMaterial IGALIMA;//ツールマテリアルの名前を登録してる
	    public static ToolMaterial SABER;//ツールマテリアルの名前を登録してる

		@SidedProxy(clientSide = "com.delvi511.symphogearmod.ClientProxy", serverSide = "com.delvi511.symphogearmod.CommonProxy")
		public static CommonProxy proxy;
		public static Configuration config;
		public static int keyBinding;


	    @EventHandler
	    /**
    	 * @param addToolMaterial (名前,回収レベル,耐久値,採掘速度,ダメージ倍率,エンチャのつきやすさ)
    	 * @see preInit
    	 */
	    public void preInit( FMLPreInitializationEvent event )
	    {
	    	//獄鎌・イガリマの設定ここから
	    	IGALIMA = EnumHelper.addToolMaterial("IGALIMA", 0, 1590, 0.0F, 999995.9F, 35);//IGALIMAというツールマテリアルの詳細な設定
	    	/*addToolMaterial(名前,回収レベル,耐久値,採掘速度,ダメージ倍率,エンチャのつきやすさ)*/
	    	igalima = new ItemSword(IGALIMA)//剣として登録
	    	.setCreativeTab(CreativeTabs.tabCombat)//クリエイティブ時、「戦闘」タブにアイテムを追加
	    	.setUnlocalizedName("igalima")//言語が非対応の場合に表示される名前
	    	.setTextureName("symphogear:igalima")//読みに行くテクスチャの指定
	    	.setMaxStackSize(1);//スタックできる最大サイズ
	    	GameRegistry.registerItem( igalima, "igalimaScythe");//最後にこれをやることで登録される
	    	//獄鎌・イガリマの設定ここまで
	    	//ネフシュタンの鎧の設定ここから
	    	NEHUSHTAN = EnumHelper.addArmorMaterial("NEHUSHTAN", 42, new int [] { 3, 8, 6, 3 }, 35);
	    	NEHUSHTAN.customCraftingMaterial = Items.diamond_chestplate;
	    	//頭
	    	nehushtan_helmet = new NehuItemArmor(0)
	    	.setUnlocalizedName("NehushtanHelmet")//言語が非対応の時に表示される名前
	    	.setTextureName("symphogear:neh_helmet")//テクスチャの指定
	    	.setMaxStackSize(1);//スタックできる量。デフォルト64
	    	//アイテムの登録。登録文字列はMOD内で被らなければ何でも良い。
	    	GameRegistry.registerItem(nehushtan_helmet, "nehushtanHelmet");
	    	//胴
	    	nehushtan_chest = new NehuItemArmor(1)
	    	.setUnlocalizedName("NehushtanChest")//言語が非対応の時に表示される名前
	    	.setTextureName("symphogear:neh_chestplate")//テクスチャの指定
	    	.setMaxStackSize(1);//スタックできる量。デフォルト64
	    	//アイテムの登録。登録文字列はMOD内で被らなければ何でも良い。
	    	GameRegistry.registerItem(nehushtan_chest, "nehushtanArmor");
	    	//脚
	    	nehushtan_leggins = new NehuItemArmor(2)
	    	.setUnlocalizedName("NehushtanLeggins")//言語が非対応の時に表示される名前
	    	.setTextureName("symphogear:neh_leggins")//テクスチャの指定
	    	.setMaxStackSize(1);//スタックできる量。デフォルト64
	    	//アイテムの登録。登録文字列はMOD内で被らなければ何でも良い。
	    	GameRegistry.registerItem(nehushtan_leggins, "nehushtanLeggins");
	    	//足
	    	nehushtan_boots = new NehuItemArmor(3)
	    	.setUnlocalizedName("NehushtanBoots")//言語が非対応の時に表示される名前
	    	.setTextureName("symphogear:neh_boots")//テクスチャの指定
	    	.setMaxStackSize(1);//スタックできる量。デフォルト64
	    	//アイテムの登録。登録文字列はMOD内で被らなければ何でも良い。
	    	GameRegistry.registerItem(nehushtan_boots, "nehushtanBoots");
	    	//ネフシュタンの鎧の設定ここまで
	    	//赤のセイバーの設定ここから
	    	SABER = EnumHelper.addToolMaterial("SABER", 0, 730, 0.0F, 5.0F, 14);//SABERというツールマテリアルの詳細な設定
	    	redsaber = new ItemSword(SABER)
	    	.setCreativeTab(CreativeTabs.tabCombat)//クリエイティブ時、「戦闘」タブにアイテムを追加
	    	.setUnlocalizedName("red_saber")//言語が非対応の場合に表示される名前
	    	.setTextureName("symphogear:red_saber")//読みに行くテクスチャの指定
	    	.setMaxStackSize(1);//スタックできる最大サイズ
	    	GameRegistry.registerItem(redsaber, "red_saber");
	    	//赤のセイバーの設定ここまで
	    	//ネフシュタンパージ追加分
	    	config = new Configuration(event.getSuggestedConfigurationFile());

			try
			{
				keyBinding = config.get(Configuration.CATEGORY_GENERAL, "KeyBinding", 48).getInt(48);
			}
			catch (Exception e)
			{
			//	FMLLog.log(Level.SEVERE, e, "Symphogear-MOD ERROR");
			}
			finally
			{
				config.save();
			}

	    }

	    @EventHandler
	    public void Init( FMLInitializationEvent e )
	    {
	    	Recipes.registry( );
	    }
	}
