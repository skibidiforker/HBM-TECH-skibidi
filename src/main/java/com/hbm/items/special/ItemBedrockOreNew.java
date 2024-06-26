package com.hbm.items.special;

import static com.hbm.inventory.OreDictManager.*;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.util.EnumUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.hbm.items.ItemEnums.EnumChunkType;
import com.hbm.lib.RefStrings;
import com.hbm.render.icon.RGBMutatorInterpolatedComponentRemap;
import com.hbm.render.icon.TextureAtlasSpriteMutatable;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBedrockOreNew extends Item {
	
	public IIcon[] icons = new IIcon[BedrockOreType.values().length * BedrockOreGrade.values().length];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {

		
		if(reg instanceof TextureMap) {
			TextureMap map = (TextureMap) reg;
			
			for(int i = 0; i < BedrockOreGrade.values().length; i++) { BedrockOreGrade grade = BedrockOreGrade.values()[i];
				for(int j = 0; j < BedrockOreType.values().length; j++) { BedrockOreType type = BedrockOreType.values()[j];
					String placeholderName = RefStrings.MODID + ":bedrock_ore_" + grade.prefix + "_" + type.suffix + "-" + (i * BedrockOreType.values().length + j);
					TextureAtlasSpriteMutatable mutableIcon = new TextureAtlasSpriteMutatable(placeholderName, new RGBMutatorInterpolatedComponentRemap(0xFFFFFF, 0x505050, type.light, type.dark));
					map.setTextureEntry(placeholderName, mutableIcon);
					this.icons[i * BedrockOreType.values().length + j] = mutableIcon;
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		
		for(int j = 0; j < BedrockOreType.values().length; j++) { BedrockOreType type = BedrockOreType.values()[j];
			for(int i = 0; i < BedrockOreGrade.values().length; i++) { BedrockOreGrade grade = BedrockOreGrade.values()[i];
				list.add(this.make(grade, type));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		int icon = this.getGrade(meta).ordinal() * BedrockOreType.values().length + this.getType(meta).ordinal();
		return icons[Math.abs(icon % icons.length)];
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		int meta = stack.getItemDamage();
		return this.getGrade(meta).name() + " " + this.getType(meta).suffix;
	}

	public static enum BedrockOreType {
		//												primary															sulfuric						solvent							radsolvent
		LIGHT_METAL(	0xFFFFFF, 0x353535, "light",	IRON, CU,														TI, AL, AL,						CHLOROCALCITE, LI, NA,			CHLOROCALCITE, LI, NA),
		HEAVY_METAL(	0x868686, 0x000000, "heavy",	W, PB,															GOLD, GOLD, BE,					W, PB, GOLD,					BI, BI, GOLD),
		RARE_EARTH(		0xE6E6B6, 0x1C1C00, "rare",		CO, DictFrame.fromOne(ModItems.chunk_ore, EnumChunkType.RARE),	B, LA, NB,						ND, B, ZR,						CO, ND, ZR),
		ACTINIDE(		0xC1C7BD, 0x2B3227, "actinide",	U, TH232,														RA226, RA226, PO210,			RA226, RA226, PO210,			TC99, TC99, U238),
		NON_METAL(		0xAFAFAF, 0x0F0F0F, "nonmetal",	COAL, S,														LIGNITE, KNO, F,				P_RED, F, S,					CHLOROCALCITE, SI, SI),
		CRYSTALLINE(	0xE2FFFA, 0x1E8A77, "crystal",	DIAMOND, SODALITE,												CINNABAR, ASBESTOS, REDSTONE,	CINNABAR, ASBESTOS, EMERALD,	BORAX, MOLYSITE, SODALITE);

		public int light;
		public int dark;
		public String suffix;
		public Object primary1, primary2;
		public Object byproductAcid1, byproductAcid2, byproductAcid3;
		public Object byproductSolvent1, byproductSolvent2, byproductSolvent3;
		public Object byproductRad1, byproductRad2, byproductRad3;
		
		private BedrockOreType(int light, int dark, String suffix, Object p1, Object p2, Object bA1, Object bA2, Object bA3, Object bS1, Object bS2, Object bS3, Object bR1, Object bR2, Object bR3) {
			this.light = light;
			this.dark = dark;
			this.suffix = suffix;
			this.primary1 = p1; this.primary2 = p2;
			this.byproductAcid1 = bA1; this.byproductAcid2 = bA2; this.byproductAcid3 = bA3;
			this.byproductSolvent1 = bS1; this.byproductSolvent2 = bS2; this.byproductSolvent3 = bS3;
			this.byproductRad1 = bR1; this.byproductRad2 = bR2; this.byproductRad3 = bR3;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		BedrockOreGrade grade = this.getGrade(stack.getItemDamage());
		return grade.tint;
	}

	public static final int none = 0xFFFFFF;
	public static final int roasted = 0xCFCFCF;
	public static final int arc = 0xC3A2A2;
	public static final int washed = 0xDBE2CB;
	
	public static enum BedrockOreGrade {
		BASE(none, "base"),						//from the slopper
		BASE_ROASTED(roasted, "base"),			//optional combination oven step, yields vitriol
		BASE_WASHED(washed, "base"),			//primitive-ass acidizer with water
		PRIMARY(none, "primary"),				//centrifuging for more primary
		PRIMARY_ROASTED(roasted, "primary"),	//optional comboven
		PRIMARY_SULFURIC(0xFFFFD3, "primary"),	//sulfuric acid
		PRIMARY_NOSULFURIC(0xD3D4FF, "primary"),//from centrifuging, sulfuric byproduct removed
		PRIMARY_SOLVENT(0xD3F0FF, "primary"),	//solvent
		PRIMARY_NOSOLVENT(0xFFDED3, "primary"),	//solvent byproduct removed
		PRIMARY_RAD(0xECFFD3, "primary"),		//radsolvent
		PRIMARY_NORAD(0xEBD3FF, "primary"),		//radsolvent byproduct removed
		PRIMARY_FIRST(0xFFD3D4, "primary"),		//higher first material yield
		PRIMARY_SECOND(0xD3FFEB, "primary"),	//higher second material yield
		CRUMBS(none, "crumbs"),					//endpoint for primary, recycling
		
		SULFURIC_BYPRODUCT(none, "sulfuric"),	//from centrifuging
		SULFURIC_ROASTED(roasted, "sulfuric"),	//comboven again
		SULFURIC_ARC(arc, "sulfuric"),			//alternate step
		SULFURIC_WASHED(washed, "sulfuric"),	//sulfuric endpoint
		
		SOLVENT_BYPRODUCT(none, "solvent"),		//from centrifuging
		SOLVENT_ROASTED(roasted, "solvent"),	//comboven again
		SOLVENT_ARC(arc, "solvent"),			//alternate step
		SOLVENT_WASHED(washed, "solvent"),		//solvent endpoint
		
		RAD_BYPRODUCT(none, "rad"),				//from centrifuging
		RAD_ROASTED(roasted, "rad"),			//comboven again
		RAD_ARC(arc, "rad"),					//alternate step
		RAD_WASHED(washed, "rad");				//rad endpoint
		
		public int tint;
		public String prefix;
		
		private BedrockOreGrade(int tint, String prefix) {
			this.tint = tint;
			this.prefix = prefix;
		}
	}
	
	public static ItemStack make(BedrockOreGrade grade, BedrockOreType type) {
		return new ItemStack(ModItems.bedrock_ore, 1, grade.ordinal() << 4 | type.ordinal());
	}
	
	public BedrockOreGrade getGrade(int meta) {
		return EnumUtil.grabEnumSafely(BedrockOreGrade.class, meta >> 4);
	}
	
	public BedrockOreType getType(int meta) {
		return EnumUtil.grabEnumSafely(BedrockOreType.class, meta & 15);
	}
}