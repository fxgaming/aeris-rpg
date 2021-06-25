package by.fxg.aeris.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

public class Characters implements IExtendedEntityProperties {
	public static final String CHAR_PROP_NAME = "character";
	public EntityPlayer player;
	public float renderSneakAngle;
	public float renderRideAngle;
	public float renderBowAngle;
	
	public Characters(EntityPlayer par1) {
		this.player = par1;
		this.renderSneakAngle = 0.0F;
		this.renderRideAngle = 0.0F;
		this.renderBowAngle = 0.0F;
	}
	
	@Deprecated
	public Characters() {
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(CHAR_PROP_NAME, new Characters(player));
	}

	public static final Characters get(EntityPlayer player) {
		return (Characters)player.getExtendedProperties(CHAR_PROP_NAME);
	}
	
	public void saveNBTData(NBTTagCompound tagCompound) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("renderSneakAngle", this.renderSneakAngle);
		tag.setFloat("renderRideAngle", this.renderRideAngle);
		tag.setFloat("renderBowAngle", this.renderBowAngle);
		tagCompound.setCompoundTag(CHAR_PROP_NAME, tag);
	}

	public void loadNBTData(NBTTagCompound tagCompound) {
		NBTTagCompound tag = tagCompound.getCompoundTag(CHAR_PROP_NAME);
		this.renderSneakAngle = tag.getFloat("renderSneakAngle");
		this.renderRideAngle = tag.getFloat("renderRideAngle");
		this.renderBowAngle = tag.getFloat("renderBowAngle");
	}

	public void init(Entity entity, World world) {
	}
}
