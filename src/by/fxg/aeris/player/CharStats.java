package by.fxg.aeris.player;

import by.fxg.aeris.inventory.CharInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class CharStats implements IExtendedEntityProperties {
	public static final String STAT_PROP_NAME = "stats";
	public EntityPlayer player;
	public int coins;
	public int arises;
	public Stat<Integer> health;
	public Stat<Integer> mana;
	public Stat<Integer> level;
	public Long exp;
	public Long kills;
	public Long pvpkills;
	public Long deaths;
	public TimerStat deathTimer;
	public Skills skills;
	public CharInventory inventory;
	public EnumCharacter character;
	
	public CharStats(EntityPlayer par1) {
		this.player = par1;
		this.coins = 0;
		this.arises = 0;
		this.health = new Stat<Integer>(100, 0).setCurrent(100);
		this.mana = new Stat<Integer>(100, 0).setCurrent(100);
		this.level = new Stat<Integer>(100, 0).setCurrent(1);
		this.exp = 0L;
		this.kills = 0L;
		this.pvpkills = 0L;
		this.deaths = 0L;
		this.deathTimer = new TimerStat();
		this.skills = new Skills(par1);
		this.skills.skillPoints = 1;
		this.inventory = new CharInventory(par1);
		this.character = EnumCharacter.WARRIOR;
	}
	
	@Deprecated
	public CharStats() {
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(STAT_PROP_NAME, new CharStats(player));
	}

	public static final CharStats get(EntityPlayer player) {
		return (CharStats)player.getExtendedProperties(STAT_PROP_NAME);
	}
	
	public String buildString(Object... par1) {
		String build = "";
		for (Object str : par1) build += str + ":";
		return build.substring(0, build.length() - 1);
	}
	
	public void saveNBTData(NBTTagCompound tagCompound) {
		NBTTagCompound tag = new NBTTagCompound();
		String stats = this.buildString(this.coins, this.arises, this.health.getMax(), this.health.getMin(), this.health.getCurrent(), this.mana.getMax(), this.mana.getMin(), this.mana.getCurrent(), this.level.getMax(), this.level.getMin(), this.level.getCurrent(), this.exp, this.kills, this.pvpkills, this.deaths, this.deathTimer.next, this.deathTimer.set, this.character.ordinal());
		tag.setString("data", stats);
		this.skills.saveNBTData(tag);
		this.inventory.saveToNBT(tag);
		tagCompound.setCompoundTag(STAT_PROP_NAME, tag);
	}

	public void loadNBTData(NBTTagCompound tagCompound) {
		NBTTagCompound tag = tagCompound.getCompoundTag(STAT_PROP_NAME);
		String stats = tag.getString("data");
		this.coins = Integer.valueOf(stats.split(":")[0]);
		this.arises = Integer.valueOf(stats.split(":")[1]);
		this.health.setMax(Integer.valueOf(stats.split(":")[2])).setMin(Integer.valueOf(stats.split(":")[3])).setCurrent(Integer.valueOf(stats.split(":")[4]));
		this.mana.setMax(Integer.valueOf(stats.split(":")[5])).setMin(Integer.valueOf(stats.split(":")[6])).setCurrent(Integer.valueOf(stats.split(":")[7]));
		this.level.setMax(Integer.valueOf(stats.split(":")[8])).setMin(Integer.valueOf(stats.split(":")[9])).setCurrent(Integer.valueOf(stats.split(":")[10]));
		this.exp = Long.valueOf(stats.split(":")[11]);
		this.kills = Long.valueOf(stats.split(":")[12]);
		this.pvpkills = Long.valueOf(stats.split(":")[13]);
		this.deaths = Long.valueOf(stats.split(":")[14]);
		this.deathTimer.next = Long.valueOf(stats.split(":")[15]);
		this.deathTimer.set = Long.valueOf(stats.split(":")[16]);
		this.character = EnumCharacter.getFromOrdinal(Integer.valueOf(stats.split(":")[17]));
		this.skills.loadNBTData(tag);
		this.inventory.loadNBTData(tag);
	}

	public void init(Entity entity, World world) {
	}
	
	public static class Skills {
		public EntityPlayer player;
		public int skillPoints;
		public int warriorSkill;
		public int archerSkill;
		public int magicianSkill;
		public int medicSkill;
		public int mechanicSkill;
		public int heroLevel;
		public int specialSkill;
		public static int defaultMaxLevel = 50;
		//total defaults(250) + ability(50) + hero(200) = 500 levels
		//Ability s Char
		
		public Skills(EntityPlayer player) {
			this.player = player;
			this.skillPoints = 0;
			this.warriorSkill = 0;
			this.archerSkill = 0;
			this.magicianSkill = 0;
			this.medicSkill = 0;
			this.mechanicSkill = 0;
			this.heroLevel = 0;
			this.specialSkill = 0;
		}
		
		public boolean incrementSkill(int id) {
			if (this.skillPoints >= 1) {
				switch(id) {
					case 0:
						if (this.warriorSkill < defaultMaxLevel) {
							this.skillPoints--;
							this.warriorSkill++;
							return true;
						}
						break;
					case 1:
						if (this.archerSkill < defaultMaxLevel) {
							this.skillPoints--;
							this.archerSkill++;
							return true;
						}
						break;
					case 2:
						if (this.magicianSkill < defaultMaxLevel) {
							this.skillPoints--;
							this.magicianSkill++;
							return true;
						}
						break;
					case 3:
						if (this.medicSkill < defaultMaxLevel) {
							this.skillPoints--;
							this.medicSkill++;
							return true;
						}
						break;
					case 4:
						if (this.mechanicSkill < defaultMaxLevel) {
							this.skillPoints--;
							this.mechanicSkill++;
							return true;
						}
						break;
					case 5:
						if (this.heroLevel < 200 && this.warriorSkill == defaultMaxLevel && this.archerSkill == defaultMaxLevel && this.magicianSkill == defaultMaxLevel && this.medicSkill == defaultMaxLevel && this.mechanicSkill == defaultMaxLevel) {
							this.skillPoints--;
							this.heroLevel++;
							return true;
						}
						break;
					case 6:
						if (this.specialSkill < 50 && CharStats.get(this.player).character.isUnusual()) {
							this.skillPoints--;
							this.specialSkill++;
							return true;
						}
						break;
				}
			} 
			return false;
		}
		
		public void saveNBTData(NBTTagCompound tagCompound) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("skillPoints", this.skillPoints);
			tag.setInteger("warriorSkill", this.warriorSkill);
			tag.setInteger("archerSkill", this.archerSkill);
			tag.setInteger("magicianSkill", this.magicianSkill);
			tag.setInteger("medicSkill", this.medicSkill);
			tag.setInteger("mechanicSkill", this.mechanicSkill);
			tag.setInteger("heroLevel", this.heroLevel);
			tag.setInteger("specialSkill", this.specialSkill);
			tagCompound.setCompoundTag("skill", tag);
		}
		
		public void loadNBTData(NBTTagCompound tagCompound) {
			NBTTagCompound tag = tagCompound.getCompoundTag("skill");
			this.skillPoints = tag.getInteger("skillPoints");
			this.warriorSkill = tag.getInteger("warriorSkill");
			this.archerSkill = tag.getInteger("archerSkill");
			this.magicianSkill = tag.getInteger("magicianSkill");
			this.medicSkill = tag.getInteger("medicSkill");
			this.mechanicSkill = tag.getInteger("mechanicSkill");
			this.heroLevel = tag.getInteger("heroLevel");
			this.specialSkill = tag.getInteger("specialSkill");
		}
	}
	
	public static class Stat<T> {
		public T max;
		public T current;
		public T min;
		
		public Stat(T max, T min) {
			this.max = max;
			this.current = this.min = min;
		}
		
		public Stat setMax(T par1) {
			this.max = par1;
			return this;
		}
		
		public Stat setCurrent(T par1) {
			this.current = par1;
			return this;
		}
		
		public Stat setMin(T par1) {
			this.min = par1;
			return this;
		}
		
		public T getMax() {
			return this.max;
		}
		
		public T getCurrent() {
			return this.current;
		}
		
		public T getMin() {
			return this.min;
		}
	}
	
	public static class TimerStat {
		public Long next;
		public Long set;
		public TimerStat() {
			this.next = this.set = 0L;
		}
		
		public TimerStat update(Long par1) {
			this.next = System.currentTimeMillis() + (this.set = par1);
			return this;
		}
		
		public boolean pass() {
			return this.next < System.currentTimeMillis();
		}
	}
	
	public enum EnumCharacter {
		WARRIOR,
		ARCHER,
		MEDIC,
		MAGICIAN,
		MECHANIC,
		ZELDA;
		
		public boolean isUnusual() {
			return this.ordinal() > 4;
		}
		
		public static EnumCharacter getFromOrdinal(int par1) {
			for (EnumCharacter enums : EnumCharacter.values()) {
				if (enums.ordinal() == par1) {
					return enums;
				}
			}
			return null;
		}
	}
}
