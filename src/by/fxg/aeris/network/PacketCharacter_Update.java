package by.fxg.aeris.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import by.fxg.aeris.player.CharStats;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
@Deprecated
public class PacketCharacter_Update extends PacketBase {
	private int entityID;
	private int coins;
	private int arises;
	private int healthMax;
	private int health;
	private int manaMax;
	private int mana;
	private int levelMax;
	private int level;
	private long exp;
	private long deathTimerNext;
	private long deathTimerSet;
	
	public PacketCharacter_Update() {}
	public PacketCharacter_Update(int entityID, CharStats charStats) {
		this.entityID = entityID;
		this.coins = charStats.coins;
		this.arises = charStats.arises;
		this.healthMax = charStats.health.getMax();
		this.health = charStats.health.getCurrent();
		this.manaMax = charStats.mana.getMax();
		this.mana = charStats.mana.getCurrent();
		this.levelMax = charStats.level.getMax();
		this.level = charStats.level.getCurrent();
		this.exp = charStats.exp;
		this.deathTimerNext = charStats.deathTimer.next;
		this.deathTimerSet = charStats.deathTimer.set;
	}

	protected void write(ByteArrayDataOutput out) {
		out.writeInt(this.entityID);
		out.writeInt(this.coins);
		out.writeInt(this.arises);
		out.writeInt(this.healthMax);
		out.writeInt(this.health);
		out.writeInt(this.manaMax);
		out.writeInt(this.mana);
		out.writeInt(this.levelMax);
		out.writeInt(this.level);
		out.writeLong(this.exp);
		out.writeLong(this.deathTimerNext);
		out.writeLong(this.deathTimerSet);
	}

	protected void read(ByteArrayDataInput in){
		this.entityID = in.readInt();
		this.coins = in.readInt();
		this.arises = in.readInt();
		this.healthMax = in.readInt();
		this.health = in.readInt();
		this.manaMax = in.readInt();
		this.mana = in.readInt();
		this.levelMax = in.readInt();
		this.level = in.readInt();
		this.exp = in.readLong();
		this.deathTimerNext = in.readLong();
		this.deathTimerSet = in.readLong();
		
	}

	protected void execute(EntityPlayer player, Side side) {
		if (side.isClient()) {
			try {
				Entity e = player.worldObj.getEntityByID(this.entityID);
				if (e != null && e instanceof EntityPlayer) {
					CharStats cs = CharStats.get((EntityPlayer)e);
					cs.coins = this.coins;
					cs.arises = this.arises;
					cs.health.setMax(this.healthMax).setCurrent(this.health);
					cs.mana.setMax(this.manaMax).setCurrent(this.mana);
					cs.level.setMax(this.levelMax).setCurrent(this.level);
					cs.exp = this.exp;
					cs.deathTimer.next = this.deathTimerNext;
					cs.deathTimer.set = this.deathTimerSet;
				}
			} catch (Exception var5) {
			}
		}
	}
}
