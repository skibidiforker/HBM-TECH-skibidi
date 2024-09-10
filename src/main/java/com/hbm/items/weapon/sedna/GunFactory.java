package com.hbm.items.weapon.sedna;

import com.hbm.items.ModItems;
import com.hbm.items.weapon.sedna.factory.GunStateDecider;
import com.hbm.items.weapon.sedna.factory.Lego;
import com.hbm.items.weapon.sedna.mags.MagazineRevolverDrum;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.render.util.RenderScreenOverlay.Crosshair;

import net.minecraft.item.Item;

public class GunFactory {

	public static void init() {
		
		ModItems.ammo_debug = new Item().setUnlocalizedName("ammo_debug").setTextureName(RefStrings.MODID + ":ammo_45");
		
		BulletConfig ammo_debug = new BulletConfig().setItem(ModItems.ammo_debug);
		
		ModItems.gun_debug = new ItemGunBase(new GunConfig()
				.dura(600).draw(15).crosshair(Crosshair.L_CLASSIC)
				.rec(new Receiver()
						.dmg(10F).delay(10).mag(new MagazineRevolverDrum(0, 6).addConfigs(ammo_debug))
						.canFire(Lego.LAMBDA_DEBUG_CAN_FIRE).fire(Lego.LAMBDA_DEBUG_FIRE))
				.pr(Lego.LAMBDA_STANDARD_RELOAD)
				.pp(Lego.LAMBDA_STANDARD_FIRE)
				.pt(Lego.LAMBDA_TOGGLE_AIM)
				.decider(GunStateDecider.LAMBDA_STANDARD_DECIDER)
				).setUnlocalizedName("gun_debug").setCreativeTab(MainRegistry.weaponTab).setTextureName(RefStrings.MODID + ":gun_darter");
	}
}