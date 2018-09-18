package com.hbm.explosion;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.grenade.EntityGrenadeTau;
import com.hbm.entity.grenade.EntityGrenadeZOMG;
import com.hbm.entity.missile.EntityMIRV;
import com.hbm.entity.missile.EntityMissileAntiBallistic;
import com.hbm.entity.missile.EntityMissileBase;
import com.hbm.entity.particle.EntityChlorineFX;
import com.hbm.entity.particle.EntityCloudFX;
import com.hbm.entity.particle.EntityDSmokeFX;
import com.hbm.entity.particle.EntityModFX;
import com.hbm.entity.particle.EntityOrangeFX;
import com.hbm.entity.particle.EntityPinkCloudFX;
import com.hbm.entity.projectile.EntityBullet;
import com.hbm.entity.projectile.EntityMiniNuke;
import com.hbm.entity.projectile.EntityRainbow;
import com.hbm.entity.projectile.EntityRocket;
import com.hbm.entity.projectile.EntityRubble;
import com.hbm.entity.projectile.EntitySchrab;
import com.hbm.interfaces.IConductor;
import com.hbm.interfaces.IConsumer;
import com.hbm.interfaces.ISource;
import com.hbm.lib.Library;
import com.hbm.lib.ModDamageSource;
import com.hbm.potion.HbmPotion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ExplosionChaos {

	private final static Random random = new Random();
	private static Random rand = new Random();

	public static void explode(World world, int x, int y, int z, int bombStartStrength) {

		int r = bombStartStrength;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						destruction(world, X, Y, Z);
					}
				}
			}
		}
	}

	public static void antiCheat(World world, int x, int y, int z, int bombStartStrength) {

		int r = bombStartStrength;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						if (rand.nextInt(15) == 0 && world.getBlock(X, Y, Z) != Blocks.air)
							world.setBlock(X, Y, Z, ModBlocks.cheater_virus);
					}
				}
			}
		}
	}

	public static void hardenVirus(World world, int x, int y, int z, int bombStartStrength) {

		int r = bombStartStrength;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						if (world.getBlock(X, Y, Z) == ModBlocks.crystal_virus)
							world.setBlock(X, Y, Z, ModBlocks.crystal_hardened);
					}
				}
			}
		}
	}

	public static void spreadVirus(World world, int x, int y, int z, int bombStartStrength) {

		int r = bombStartStrength;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						if (rand.nextInt(15) == 0 && world.getBlock(X, Y, Z) != Blocks.air)
							world.setBlock(X, Y, Z, ModBlocks.cheater_virus_seed);
					}
				}
			}
		}
	}

	public static void pulse(World world, int x, int y, int z, int bombStartStrength) {

		int r = bombStartStrength;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						pDestruction(world, X, Y, Z);
					}
				}
			}
		}
	}

	public static void explodeZOMG(World world, int x, int y, int z, int bombStartStrength) {

		int r = bombStartStrength;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						if (!(world.getBlock(X, Y, Z) == Blocks.bedrock && Y <= 0))
							world.setBlock(X, Y, Z, Blocks.air);
					}
				}
			}
		}
	}

	public static void decon(World world, int x, int y, int z, int radius) {

		int r = radius;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						decontaminate(world, X, Y, Z);
					}
				}
			}
		}
	}

	public static void spawnExplosion(World world, int x, int y, int z, int bound) {

		int randX;
		int randY;
		int randZ;

		for (int i = 0; i < 25; i++) {

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x + randX, y + randY, z + randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x + randX, y + randY, z + randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x + randX, y - randY, z + randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x - randX, y + randY, z + randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x + randX, y + randY, z - randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x + randX, y - randY, z + randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x - randX, y + randY, z + randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x + randX, y + randY, z - randZ,
			// 5);
			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x - randX, y - randY, z + randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x - randX, y - randY, z + randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x - randX, y + randY, z - randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x - randX, y + randY, z - randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x + randX, y - randY, z - randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x + randX, y - randY, z - randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x - randX, y - randY, z - randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x - randX, y - randY, z - randZ,
			// 5);
		}
	}

	/**
	 * Sets all flammable blocks on fire
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param bound
	 */
	public static void flameDeath(World world, int x, int y, int z, int bound) {

		int r = bound;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						if (world.getBlock(X, Y, Z).isFlammable(world, XX, YY, ZZ, ForgeDirection.UP)
								&& world.getBlock(X, Y + 1, Z) == Blocks.air) {
							world.setBlock(X, Y + 1, Z, Blocks.fire);
						}
					}
				}
			}
		}

	}

	/**
	 * Sets all blocks on fire
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param bound
	 */
	public static void burn(World world, int x, int y, int z, int bound) {

		int r = bound;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						if ((world.getBlock(X, Y + 1, Z) == Blocks.air
								|| world.getBlock(X, Y + 1, Z) == Blocks.snow_layer)
								&& world.getBlock(X, Y, Z) != Blocks.air) {
							world.setBlock(X, Y + 1, Z, Blocks.fire);
						}
					}
				}
			}
		}

	}

	public static void spawnChlorine(World world, double x, double y, double z, int count, double speed, int type) {
		
		for(int i = 0; i < count; i++) {
			
			EntityModFX fx = null;
			
			if(type == 0) {
				fx = new EntityChlorineFX(world, x, y, z, 0.0, 0.0, 0.0);
			} else if(type == 1) {
				fx = new EntityCloudFX(world, x, y, z, 0.0, 0.0, 0.0);
			} else if(type == 2) {
				fx = new EntityPinkCloudFX(world, x, y, z, 0.0, 0.0, 0.0);
			} else {
				fx = new EntityOrangeFX(world, x, y, z, 0.0, 0.0, 0.0);
			}
			
			fx.motionY = rand.nextGaussian() * speed;
			fx.motionX = rand.nextGaussian() * speed;
			fx.motionZ = rand.nextGaussian() * speed;
			world.spawnEntityInWorld(fx);
		}
	}

	public static void destruction(World world, int x, int y, int z) {

		if (world.getBlock(x, y, z) == Blocks.bedrock || world.getBlock(x, y, z) == ModBlocks.reinforced_brick
				|| world.getBlock(x, y, z) == ModBlocks.reinforced_sand
				|| world.getBlock(x, y, z) == ModBlocks.reinforced_glass
				|| world.getBlock(x, y, z) == ModBlocks.reinforced_lamp_on
				|| world.getBlock(x, y, z) == ModBlocks.reinforced_lamp_off) {

		} else {
			world.setBlock(x, y, z, Blocks.air);
		}

	}

	public static void pDestruction(World world, int x, int y, int z) {

		if (Blocks.air.getBlockHardness(world, x, y, z) != Float.POSITIVE_INFINITY) {
			Block b = world.getBlock(x, y, z);
			TileEntity t = world.getTileEntity(x, y, z);

			if (b == Blocks.sandstone || b == Blocks.sandstone_stairs)
				world.setBlock(x, y, z, Blocks.sand);
			else if (t != null && t instanceof ISource)
				world.setBlock(x, y, z, ModBlocks.block_electrical_scrap);
			else if (t != null && t instanceof IConductor)
				world.setBlock(x, y, z, ModBlocks.block_electrical_scrap);
			else if (t != null && t instanceof IConsumer)
				world.setBlock(x, y, z, ModBlocks.block_electrical_scrap);
			else if (b == Blocks.sand)
				world.setBlock(x, y, z, Blocks.sand);
			else if (b == Blocks.gravel)
				world.setBlock(x, y, z, Blocks.gravel);
			else if (b == ModBlocks.gravel_obsidian)
				world.setBlock(x, y, z, ModBlocks.gravel_obsidian);
			else if (b == ModBlocks.block_electrical_scrap)
				world.setBlock(x, y, z, ModBlocks.block_electrical_scrap);
			else if (b == ModBlocks.block_scrap)
				world.setBlock(x, y, z, ModBlocks.block_scrap);
			else if (b == ModBlocks.brick_obsidian)
				world.setBlock(x, y, z, ModBlocks.gravel_obsidian);
			else if (b.getMaterial() == Material.anvil)
				world.setBlock(x, y, z, Blocks.gravel);
			else if (b.getMaterial() == Material.clay)
				world.setBlock(x, y, z, Blocks.sand);
			else if (b.getMaterial() == Material.grass)
				world.setBlock(x, y, z, Blocks.sand);
			else if (b.getMaterial() == Material.ground)
				world.setBlock(x, y, z, Blocks.sand);
			else if (b.getMaterial() == Material.iron)
				world.setBlock(x, y, z, Blocks.gravel);
			else if (b.getMaterial() == Material.piston)
				world.setBlock(x, y, z, Blocks.gravel);
			else if (b.getMaterial() == Material.rock)
				world.setBlock(x, y, z, Blocks.gravel);
			else if (b.getMaterial() == Material.sand)
				world.setBlock(x, y, z, Blocks.sand);
			else if (b.getMaterial() == Material.tnt)
				world.setBlock(x, y, z, ModBlocks.block_scrap);
			else
				world.setBlock(x, y, z, Blocks.air);
		}
	}

	public static void cluster(World world, int x, int y, int z, int count, int gravity) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntityRocket fragment;

		for (int i = 0; i < count; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();

			if (rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if (rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			fragment = new EntityRocket(world, x, y, z, d1, d2, d3, 0.0125D);

			world.spawnEntityInWorld(fragment);
		}
	}

	public static void schrab(World world, int x, int y, int z, int count, int gravity) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntitySchrab fragment;

		for (int i = 0; i < count; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();

			if (rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if (rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			fragment = new EntitySchrab(world, x, y, z, d1, d2, d3, 0.0125D);

			world.spawnEntityInWorld(fragment);
		}
	}

	public static void nuke(World world, int x, int y, int z, int count) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntityTNTPrimed fragment;

		for (int i = 0; i < 5; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();

			if (rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if (rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			fragment = new EntityTNTPrimed(world);
			fragment.motionX = d1;
			fragment.motionY = d2;
			fragment.motionZ = d3;

			world.spawnEntityInWorld(fragment);
		}
	}

	public static void frag(World world, int x, int y, int z, int count, boolean flame, Entity shooter) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntityArrow fragment;

		for (int i = 0; i < count; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();

			if (rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if (rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			fragment = new EntityArrow(world, x, y, z);

			fragment.motionX = d1;
			fragment.motionY = d2;
			fragment.motionZ = d3;
			fragment.shootingEntity = shooter;

			fragment.setIsCritical(true);
			if (flame) {
				fragment.setFire(1000);
			}

			fragment.setDamage(2.5);

			world.spawnEntityInWorld(fragment);
		}
	}

	public static void anvil(World world, int x, int y, int z, int count) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntityFallingBlock fragment;

		for (int i = 0; i < count; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();
			;

			if (rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if (rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			world.setBlock(x, y, z, Blocks.anvil);
			fragment = new EntityFallingBlock(world, x + 0.5, y + 0.5, z + 0.5, Blocks.anvil);
			world.setBlock(x, y, z, Blocks.air);

			fragment.motionX = d1;
			fragment.motionY = d2;
			fragment.motionZ = d3;

			world.spawnEntityInWorld(fragment);
		}
	}

	public static void poison(World world, int x, int y, int z, int bombStartStrength) {
		float f = bombStartStrength;
		HashSet hashset = new HashSet();
		int i;
		int j;
		int k;
		double d5;
		double d6;
		double d7;
		double wat = bombStartStrength * 2;
		boolean isOccupied = false;

		bombStartStrength *= 2.0F;
		i = MathHelper.floor_double(x - wat - 1.0D);
		j = MathHelper.floor_double(x + wat + 1.0D);
		k = MathHelper.floor_double(y - wat - 1.0D);
		int i2 = MathHelper.floor_double(y + wat + 1.0D);
		int l = MathHelper.floor_double(z - wat - 1.0D);
		int j2 = MathHelper.floor_double(z + wat + 1.0D);
		List list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(i, k, l, j, i2, j2));
		Vec3 vec3 = Vec3.createVectorHelper(x, y, z);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(x, y, z) / bombStartStrength;

			if (d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				double d9 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
				if (d9 < wat) {
					if (entity instanceof EntityPlayer && Library.checkForGasMask((EntityPlayer) entity)) {
						Library.damageSuit((EntityPlayer)entity, 3, rand.nextInt(2));

					} else if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity)
								.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 5 * 20, 0));
						((EntityLivingBase) entity)
								.addPotionEffect(new PotionEffect(Potion.poison.getId(), 20 * 20, 2));
						((EntityLivingBase) entity)
								.addPotionEffect(new PotionEffect(Potion.wither.getId(), 1 * 20, 1));
						((EntityLivingBase) entity)
								.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 30 * 20, 1));
						((EntityLivingBase) entity)
								.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 30 * 20, 2));
					}
				}
			}
		}

		bombStartStrength = (int) f;
	}

	public static void pc(World world, int x, int y, int z, int bombStartStrength) {
		float f = bombStartStrength;
		HashSet hashset = new HashSet();
		int i;
		int j;
		int k;
		double d5;
		double d6;
		double d7;
		double wat = bombStartStrength * 2;
		boolean isOccupied = false;

		bombStartStrength *= 2.0F;
		i = MathHelper.floor_double(x - wat - 1.0D);
		j = MathHelper.floor_double(x + wat + 1.0D);
		k = MathHelper.floor_double(y - wat - 1.0D);
		int i2 = MathHelper.floor_double(y + wat + 1.0D);
		int l = MathHelper.floor_double(z - wat - 1.0D);
		int j2 = MathHelper.floor_double(z + wat + 1.0D);
		List list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(i, k, l, j, i2, j2));
		Vec3 vec3 = Vec3.createVectorHelper(x, y, z);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(x, y, z) / bombStartStrength;

			if (d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				double d9 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
				if (d9 < wat) {
					
					if (entity instanceof EntityPlayer) {
						
						Library.damageSuit((EntityPlayer)entity, 0, 25);
						Library.damageSuit((EntityPlayer)entity, 1, 25);
						Library.damageSuit((EntityPlayer)entity, 2, 25);
						Library.damageSuit((EntityPlayer)entity, 3, 25);
						
					}
					
					entity.attackEntityFrom(ModDamageSource.pc, 5);
				}
			}
		}

		bombStartStrength = (int) f;
	}

	public static void c(World world, int x, int y, int z, int bombStartStrength) {
		float f = bombStartStrength;
		HashSet hashset = new HashSet();
		int i;
		int j;
		int k;
		double d5;
		double d6;
		double d7;
		double wat = bombStartStrength * 2;
		boolean isOccupied = false;

		bombStartStrength *= 2.0F;
		i = MathHelper.floor_double(x - wat - 1.0D);
		j = MathHelper.floor_double(x + wat + 1.0D);
		k = MathHelper.floor_double(y - wat - 1.0D);
		int i2 = MathHelper.floor_double(y + wat + 1.0D);
		int l = MathHelper.floor_double(z - wat - 1.0D);
		int j2 = MathHelper.floor_double(z + wat + 1.0D);
		List list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(i, k, l, j, i2, j2));
		Vec3 vec3 = Vec3.createVectorHelper(x, y, z);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(x, y, z) / bombStartStrength;

			if (d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				double d9 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
				if (d9 < wat) {
					
					if (entity instanceof EntityPlayer) {
						
						Library.damageSuit((EntityPlayer)entity, 0, 5);
						Library.damageSuit((EntityPlayer)entity, 1, 5);
						Library.damageSuit((EntityPlayer)entity, 2, 5);
						Library.damageSuit((EntityPlayer)entity, 3, 5);
						
					}
					
					if (entity instanceof EntityPlayer && Library.checkForHazmat((EntityPlayer) entity)) { } else {
						
						if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isPotionActive(HbmPotion.taint.id)) {
							((EntityLivingBase)entity).removePotionEffect(HbmPotion.taint.id);
							((EntityLivingBase)entity).addPotionEffect(new PotionEffect(HbmPotion.mutation.id, 1 * 60 * 60 * 20, 0, false));
						} else {
							entity.attackEntityFrom(ModDamageSource.cloud, 3);
						}
					}
				}
			}
		}

		bombStartStrength = (int) f;
	}

	public static void floater(World world, int x, int y, int z, int radi, int height) {

		Block save;
		int meta;

		int r = radi;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22) {
						save = world.getBlock(X, Y, Z);
						meta = world.getBlockMetadata(X, Y, Z);
						world.setBlock(X, Y, Z, Blocks.air);
						if (save != Blocks.air) {
							world.setBlock(X, Y + height, Z, save);
							world.setBlockMetadataWithNotify(X, Y + height, Z, meta, 2);
						}
					}
				}
			}
		}

	}

	public static void move(World world, int x, int y, int z, int radius, int a, int b, int c) {
		float f = radius;
		HashSet hashset = new HashSet();
		int i;
		int j;
		int k;
		double d5;
		double d6;
		double d7;
		double wat = radius;
		boolean isOccupied = false;
		int rand = 0;

		radius *= 2.0F;
		i = MathHelper.floor_double(x - wat - 1.0D);
		j = MathHelper.floor_double(x + wat + 1.0D);
		k = MathHelper.floor_double(y - wat - 1.0D);
		int i2 = MathHelper.floor_double(y + wat + 1.0D);
		int l = MathHelper.floor_double(z - wat - 1.0D);
		int j2 = MathHelper.floor_double(z + wat + 1.0D);
		List list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(i, k, l, j, i2, j2));
		Vec3 vec3 = Vec3.createVectorHelper(x, y, z);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(x, y, z) / radius;

			if (d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				if (entity instanceof EntityLiving && !(entity instanceof EntitySheep)) {
					rand = random.nextInt(2);
					if (rand == 0) {
						((EntityLiving) entity).setCustomNameTag("Dinnerbone");
					} else {
						((EntityLiving) entity).setCustomNameTag("Grumm");
					}
				}

				if (entity instanceof EntitySheep) {
					((EntityLiving) entity).setCustomNameTag("jeb_");
				}

				double d9 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
				if (d9 < wat) {
					entity.setPosition(entity.posX += a, entity.posY += b, entity.posZ += c);
				}
			}
		}

		radius = (int) f;
	}

	public static Entity getHomingTarget(World world, int x, int y, int z, int radius, Entity e) {
		float f = radius;
		HashSet hashset = new HashSet();
		int i;
		int j;
		int k;
		double d5;
		double d6;
		double d7;
		double wat = radius * 2;
		boolean isOccupied = false;

		radius *= 2.0F;
		i = MathHelper.floor_double(x - wat - 1.0D);
		j = MathHelper.floor_double(x + wat + 1.0D);
		k = MathHelper.floor_double(y - wat - 1.0D);
		int i2 = MathHelper.floor_double(y + wat + 1.0D);
		int l = MathHelper.floor_double(z - wat - 1.0D);
		int j2 = MathHelper.floor_double(z + wat + 1.0D);
		List list = world.getEntitiesWithinAABBExcludingEntity(e, AxisAlignedBB.getBoundingBox(i, k, l, j, i2, j2));
		Vec3 vec3 = Vec3.createVectorHelper(x, y, z);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(x, y, z) / radius;

			if (d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				double d9 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
				if (true) {
					d5 /= d9;
					d6 /= d9;
					d7 /= d9;
					double d10 = world.getBlockDensity(vec3, entity.boundingBox);
					if (entity instanceof EntityMissileBase && !(entity instanceof EntityMissileAntiBallistic)) {
						return entity;
					}
				}
			}
		}

		radius = (int) f;
		return null;
	}

	public static void delMissiles(World world, int x, int y, int z, int radius, Entity e) {
		float f = radius;
		HashSet hashset = new HashSet();
		int i;
		int j;
		int k;
		double d5;
		double d6;
		double d7;
		double wat = radius * 2;
		boolean isOccupied = false;

		radius *= 2.0F;
		i = MathHelper.floor_double(x - wat - 1.0D);
		j = MathHelper.floor_double(x + wat + 1.0D);
		k = MathHelper.floor_double(y - wat - 1.0D);
		int i2 = MathHelper.floor_double(y + wat + 1.0D);
		int l = MathHelper.floor_double(z - wat - 1.0D);
		int j2 = MathHelper.floor_double(z + wat + 1.0D);
		List list = world.getEntitiesWithinAABBExcludingEntity(e, AxisAlignedBB.getBoundingBox(i, k, l, j, i2, j2));
		Vec3 vec3 = Vec3.createVectorHelper(x, y, z);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(x, y, z) / radius;

			if (d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				double d9 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
				if (true) {
					d5 /= d9;
					d6 /= d9;
					d7 /= d9;
					double d10 = world.getBlockDensity(vec3, entity.boundingBox);
					if (entity instanceof EntityMissileBase) {
						entity = null;
					}
				}
			}
		}

		radius = (int) f;
	}

	public static void mirv(World world, double x, double y, double z) {
		double modifier = 2.5;
		double zeta = Math.sqrt(2) / 2;
		double theta = Math.sqrt(1 - Math.pow(0.5, 2));
		EntityMIRV mirv1 = new EntityMIRV(world);
		EntityMIRV mirv2 = new EntityMIRV(world);
		EntityMIRV mirv3 = new EntityMIRV(world);
		EntityMIRV mirv4 = new EntityMIRV(world);
		// double vx1 = rand.nextDouble();
		// double vy1 = rand.nextDouble() * -1;
		// double vz1 = Math.sqrt(Math.pow(1, 2) - Math.pow(vx1, 2));
		double vx1 = 1;
		double vy1 = rand.nextDouble() * -1;
		double vz1 = 0;

		mirv1.posX = x;
		mirv1.posY = y;
		mirv1.posZ = z;
		mirv1.motionY = vy1;
		mirv2.posX = x;
		mirv2.posY = y;
		mirv2.posZ = z;
		mirv2.motionY = vy1;
		mirv3.posX = x;
		mirv3.posY = y;
		mirv3.posZ = z;
		mirv3.motionY = vy1;
		mirv4.posX = x;
		mirv4.posY = y;
		mirv4.posZ = z;
		mirv4.motionY = vy1;

		mirv1.motionX = vx1 * modifier;
		mirv1.motionZ = vz1 * modifier;
		world.spawnEntityInWorld(mirv1);

		mirv2.motionX = -vz1 * modifier;
		mirv2.motionZ = vx1 * modifier;
		world.spawnEntityInWorld(mirv2);

		mirv3.motionX = -vx1 * modifier;
		mirv3.motionZ = -vz1 * modifier;
		world.spawnEntityInWorld(mirv3);

		mirv4.motionX = vz1 * modifier;
		mirv4.motionZ = -vx1 * modifier;
		world.spawnEntityInWorld(mirv4);

		EntityMIRV mirv5 = new EntityMIRV(world);
		EntityMIRV mirv6 = new EntityMIRV(world);
		EntityMIRV mirv7 = new EntityMIRV(world);
		EntityMIRV mirv8 = new EntityMIRV(world);
		// double vx2 = vx1 < theta ? vx1 + theta : vx1 - theta;
		// double vy2 = vy1;
		// double vz2 = Math.sqrt(Math.pow(1, 2) - Math.pow(vx2, 2));
		double vx2 = zeta;
		double vy2 = vy1;
		double vz2 = zeta;

		mirv5.posX = x;
		mirv5.posY = y;
		mirv5.posZ = z;
		mirv5.motionY = vy2;
		mirv6.posX = x;
		mirv6.posY = y;
		mirv6.posZ = z;
		mirv6.motionY = vy2;
		mirv7.posX = x;
		mirv7.posY = y;
		mirv7.posZ = z;
		mirv7.motionY = vy2;
		mirv8.posX = x;
		mirv8.posY = y;
		mirv8.posZ = z;
		mirv8.motionY = vy2;

		mirv5.motionX = vx2 * modifier;
		mirv5.motionZ = vz2 * modifier;
		world.spawnEntityInWorld(mirv5);

		mirv6.motionX = -vz2 * modifier;
		mirv6.motionZ = vx2 * modifier;
		world.spawnEntityInWorld(mirv6);

		mirv7.motionX = -vx2 * modifier;
		mirv7.motionZ = -vz2 * modifier;
		world.spawnEntityInWorld(mirv7);

		mirv8.motionX = vz2 * modifier;
		mirv8.motionZ = -vx2 * modifier;
		world.spawnEntityInWorld(mirv8);
	}

	public static void miniMirv(World world, double x, double y, double z) {
		double modifier = 1.25;
		double zeta = Math.sqrt(2) / 2;
		double theta = Math.sqrt(1 - Math.pow(0.5, 2));
		EntityMiniNuke mirv1 = new EntityMiniNuke(world);
		EntityMiniNuke mirv2 = new EntityMiniNuke(world);
		EntityMiniNuke mirv3 = new EntityMiniNuke(world);
		EntityMiniNuke mirv4 = new EntityMiniNuke(world);
		double vx1 = 1;
		double vy1 = rand.nextDouble() * -1;
		double vz1 = 0;

		mirv1.posX = x;
		mirv1.posY = y;
		mirv1.posZ = z;
		mirv1.motionY = vy1;
		mirv2.posX = x;
		mirv2.posY = y;
		mirv2.posZ = z;
		mirv2.motionY = vy1;
		mirv3.posX = x;
		mirv3.posY = y;
		mirv3.posZ = z;
		mirv3.motionY = vy1;
		mirv4.posX = x;
		mirv4.posY = y;
		mirv4.posZ = z;
		mirv4.motionY = vy1;

		mirv1.motionX = vx1 * modifier;
		mirv1.motionZ = vz1 * modifier;
		world.spawnEntityInWorld(mirv1);

		mirv2.motionX = -vz1 * modifier;
		mirv2.motionZ = vx1 * modifier;
		world.spawnEntityInWorld(mirv2);

		mirv3.motionX = -vx1 * modifier;
		mirv3.motionZ = -vz1 * modifier;
		world.spawnEntityInWorld(mirv3);

		mirv4.motionX = vz1 * modifier;
		mirv4.motionZ = -vx1 * modifier;
		world.spawnEntityInWorld(mirv4);

		EntityMiniNuke mirv5 = new EntityMiniNuke(world);
		EntityMiniNuke mirv6 = new EntityMiniNuke(world);
		EntityMiniNuke mirv7 = new EntityMiniNuke(world);
		EntityMiniNuke mirv8 = new EntityMiniNuke(world);
		// double vx2 = vx1 < theta ? vx1 + theta : vx1 - theta;
		// double vy2 = vy1;
		// double vz2 = Math.sqrt(Math.pow(1, 2) - Math.pow(vx2, 2));
		double vx2 = zeta;
		double vy2 = vy1;
		double vz2 = zeta;

		mirv5.posX = x;
		mirv5.posY = y;
		mirv5.posZ = z;
		mirv5.motionY = vy2;
		mirv6.posX = x;
		mirv6.posY = y;
		mirv6.posZ = z;
		mirv6.motionY = vy2;
		mirv7.posX = x;
		mirv7.posY = y;
		mirv7.posZ = z;
		mirv7.motionY = vy2;
		mirv8.posX = x;
		mirv8.posY = y;
		mirv8.posZ = z;
		mirv8.motionY = vy2;

		mirv5.motionX = vx2 * modifier;
		mirv5.motionZ = vz2 * modifier;
		world.spawnEntityInWorld(mirv5);

		mirv6.motionX = -vz2 * modifier;
		mirv6.motionZ = vx2 * modifier;
		world.spawnEntityInWorld(mirv6);

		mirv7.motionX = -vx2 * modifier;
		mirv7.motionZ = -vz2 * modifier;
		world.spawnEntityInWorld(mirv7);

		mirv8.motionX = vz2 * modifier;
		mirv8.motionZ = -vx2 * modifier;
		world.spawnEntityInWorld(mirv8);
	}

	public static void plasma(World world, int x, int y, int z, int radius) {
		int r = radius;
		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22 + world.rand.nextInt(r22 / 2)) {
						if (world.getBlock(X, Y, Z) != Blocks.bedrock && world.getBlock(X, Y, Z) != ModBlocks.statue_elb
								&& world.getBlock(X, Y, Z) != ModBlocks.statue_elb_g
								&& world.getBlock(X, Y, Z) != ModBlocks.statue_elb_w
								&& world.getBlock(X, Y, Z) != ModBlocks.statue_elb_f)
							world.setBlock(X, Y, Z, ModBlocks.plasma);
					}
				}
			}
		}
	}

	public static void tauMeSinPi(World world, double x, double y, double z, int count, Entity shooter,
			EntityGrenadeTau tau) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntityBullet fragment;

		if (shooter != null && shooter instanceof EntityPlayer)
			for (int i = 0; i < count; i++) {
				d1 = rand.nextDouble();
				d2 = rand.nextDouble();
				d3 = rand.nextDouble();

				if (rand.nextInt(2) == 0) {
					d1 *= -1;
				}

				if (rand.nextInt(2) == 0) {
					d2 *= -1;
				}

				if (rand.nextInt(2) == 0) {
					d3 *= -1;
				}

				if (rand.nextInt(5) == 0) {
					fragment = new EntityBullet(world, (EntityPlayer) shooter, 3.0F, 35, 45, false, "tauDay", tau);
					fragment.setDamage(rand.nextInt(301) + 100);
				} else {
					fragment = new EntityBullet(world, (EntityPlayer) shooter, 3.0F, 35, 45, false, "eyyOk", tau);
					fragment.setDamage(rand.nextInt(11) + 35);
				}

				fragment.motionX = d1 * 5;
				fragment.motionY = d2 * 5;
				fragment.motionZ = d3 * 5;
				fragment.shootingEntity = shooter;

				fragment.setIsCritical(true);

				world.spawnEntityInWorld(fragment);
			}
	}

	public static void zomgMeSinPi(World world, double x, double y, double z, int count, Entity shooter,
			EntityGrenadeZOMG zomg) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntityBullet fragment;

		// if (shooter != null && shooter instanceof EntityPlayer)
		for (int i = 0; i < count; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();

			if (rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if (rand.nextInt(2) == 0) {
				d2 *= -1;
			}

			if (rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			EntityRainbow entityZomg = new EntityRainbow(world, (EntityPlayer) shooter, 1F, 10000, 100000, zomg);

			entityZomg.motionX = d1;// * 5;
			entityZomg.motionY = d2;// * 5;
			entityZomg.motionZ = d3;// * 5;
			entityZomg.shootingEntity = shooter;

			world.spawnEntityInWorld(entityZomg);
			world.playSoundAtEntity(zomg, "hbm:weapon.zomgShoot", 10.0F, 0.8F + (rand.nextFloat() * 0.4F));
		}
	}

	public static void levelDown(World world, int x, int y, int z, int radius) {

		if(!world.isRemote)
		for (int i = x - radius; i <= x + radius; i++)
			for (int j = z - radius; j <= z + radius; j++) {
				
				Block b = world.getBlock(i, y, j);
				float k = b.getBlockHardness(world, i, y, j);
						
				if(k < 60 && b != Blocks.air) {
					
					EntityRubble rubble = new EntityRubble(world);
					rubble.posX = i + 0.5F;
					rubble.posY = y;
					rubble.posZ = j + 0.5F;
					
					rubble.motionY = 0.025F * 10 + 0.15F;
					rubble.setMetaBasedOnMat(b.getMaterial());
					
					world.spawnEntityInWorld(rubble);
					
					world.setBlock(i, y, j, Blocks.air);
				}
			}
	}

	public static void decontaminate(World world, int x, int y, int z) {
		//Bridged
		//if (!world.isRemote) {
			{ int rand;
			Random random = new Random();
			
			if (world.getBlock(x, y, z) == ModBlocks.waste_earth && random.nextInt(3) != 0) {
				world.setBlock(x, y, z, Blocks.grass);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.waste_mycelium && random.nextInt(5) == 0) {
				world.setBlock(x, y, z, Blocks.mycelium);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.waste_trinitite && random.nextInt(3) == 0) {
				world.setBlock(x, y, z, Blocks.sand);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.waste_trinitite_red && random.nextInt(3) == 0) {
				world.setBlock(x, y, z, Blocks.sand, 1, 2);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.waste_log && random.nextInt(3) != 0) {
				world.setBlock(x, y, z, Blocks.log);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.waste_planks && random.nextInt(3) != 0) {
				world.setBlock(x, y, z, Blocks.planks);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.block_trinitite && random.nextInt(10) == 0) {
				world.setBlock(x, y, z, ModBlocks.block_lead);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.block_waste && random.nextInt(10) == 0) {
				world.setBlock(x, y, z, ModBlocks.block_lead);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.sellafield_core && random.nextInt(10) == 0) {
				world.setBlock(x, y, z, ModBlocks.sellafield_4);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.sellafield_4 && random.nextInt(5) == 0) {
				world.setBlock(x, y, z, ModBlocks.sellafield_3);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.sellafield_3 && random.nextInt(5) == 0) {
				world.setBlock(x, y, z, ModBlocks.sellafield_2);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.sellafield_2 && random.nextInt(5) == 0) {
				world.setBlock(x, y, z, ModBlocks.sellafield_1);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.sellafield_1 && random.nextInt(5) == 0) {
				world.setBlock(x, y, z, ModBlocks.sellafield_0);
			}

			else if (world.getBlock(x, y, z) == ModBlocks.sellafield_0 && random.nextInt(5) == 0) {
				world.setBlock(x, y, z, ModBlocks.sellafield_slaked);
			}
		}
	}

}
