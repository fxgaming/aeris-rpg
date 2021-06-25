package net.minecraft.client.model;

import by.fxg.aeris.player.Characters;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.aeris.AerisRenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelCharacter extends ModelCharBase {
	public ModelCharRenderer head;
	public ModelCharRenderer neck;
	public ModelCharRenderer base;
	public ModelCharRenderer body1;
	public ModelCharRenderer body2;
	public ModelCharRenderer larm1;
	public ModelCharRenderer rarm1;
	public ModelCharRenderer larm2;
	public ModelCharRenderer rarm2;
	public ModelCharRenderer larm3;
	public ModelCharRenderer rarm3;
	public ModelCharRenderer lleg1;
	public ModelCharRenderer rleg1;
	public ModelCharRenderer lleg2;
	public ModelCharRenderer rleg2;
	public ModelCharRenderer lfeet;
	public ModelCharRenderer rfeet;

	public ModelCharacter() {
		this.textureWidth = 128;
		this.textureHeight = 128;

		this.head = new ModelCharRenderer(this, 16, 0);
		this.head.addBox(-4F, -9F, -4F, 8, 8, 8);
		this.head.setRotationPoint(0F, 2F, 0F);
		this.head.setTextureSize(64, 32);
		this.head.mirror = true;
		this.setRotation(this.head, 0F, 0F, 0F);
		this.neck = new ModelCharRenderer(this, 23, 16);
		this.neck.addBox(-2F, -1F, -2F, 4, 1, 4);
		this.neck.setRotationPoint(0F, 2F, 0F);
		this.neck.setTextureSize(64, 32);
		this.neck.mirror = true;
		this.setRotation(this.neck, 0F, 0F, 0F);
		this.base = new ModelCharRenderer(this, 0, 0);
		this.base.addBox(0F, 0F, 0F, 1, 1, 1);
		this.base.setRotationPoint(0F, -4F, 0F);
		this.base.setTextureSize(64, 32);
		this.base.mirror = true;
		this.setRotation(this.base, 0F, 0F, 0F);
		this.body1 = new ModelCharRenderer(this, 17, 21);
		this.body1.addBox(-5F, 0F, -2F, 10, 7, 5);
		this.body1.setRotationPoint(0F, -2F, -0.5F);
		this.body1.setTextureSize(64, 32);
		this.body1.mirror = true;
		this.setRotation(this.body1, 0F, 0F, 0F);
		this.body2 = new ModelCharRenderer(this, 18, 33);
		this.body2.addBox(-5F, 0F, -2F, 10, 7, 4);
		this.body2.setRotationPoint(0F, 5F, 0F);
		this.body2.setTextureSize(64, 32);
		this.body2.mirror = true;
		this.setRotation(this.body2, 0F, 0F, 0F);
		this.larm1 = new ModelCharRenderer(this, 47, 21);
		this.larm1.addBox(0F, -2F, -1.5F, 4, 6, 4);
		this.larm1.setRotationPoint(5F, 0F, 0F);
		this.larm1.setTextureSize(64, 32);
		this.larm1.mirror = true;
		this.setRotation(this.larm1, 0F, 0F, 0F);
		this.rarm1 = new ModelCharRenderer(this, 1, 21);
		this.rarm1.addBox(-4F, -2F, -1.5F, 4, 6, 4);
		this.rarm1.setRotationPoint(-5F, 0F, 0F);
		this.rarm1.setTextureSize(64, 32);
		this.rarm1.mirror = true;
		this.setRotation(this.rarm1, 0F, 0F, 0F);
		this.larm2 = new ModelCharRenderer(this, 48, 31);
		this.larm2.addBox(-1.5F, 0F, -1.5F, 3, 5, 4);
		this.larm2.setRotationPoint(7F, 4F, 0F);
		this.larm2.setTextureSize(64, 32);
		this.larm2.mirror = true;
		this.setRotation(this.larm2, 0F, 0F, 0F);
		this.rarm2 = new ModelCharRenderer(this, 2, 31);
		this.rarm2.addBox(-1.5F, 0F, -1.5F, 3, 5, 4);
		this.rarm2.setRotationPoint(-7F, 4F, 0F);
		this.rarm2.setTextureSize(64, 32);
		this.rarm2.mirror = true;
		this.setRotation(this.rarm2, 0F, 0F, 0F);
		this.larm3 = new ModelCharRenderer(this, 46, 40);
		this.larm3.addBox(-2F, 0F, -2F, 4, 3, 5);
		this.larm3.setRotationPoint(7F, 9F, 0F);
		this.larm3.setTextureSize(64, 32);
		this.larm3.mirror = true;
		this.setRotation(this.larm3, 0F, 0F, 0F);
		this.rarm3 = new ModelCharRenderer(this, 0, 40);
		this.rarm3.addBox(-2F, 0F, -2F, 4, 3, 5);
		this.rarm3.setRotationPoint(-7F, 9F, 0F);
		this.rarm3.setTextureSize(64, 32);
		this.rarm3.mirror = true;
		this.setRotation(this.rarm3, 0F, 0F, 0F);
		this.lleg1 = new ModelCharRenderer(this, 34, 50);
		this.lleg1.addBox(-2F, 0F, -2F, 4, 6, 4);
		this.lleg1.setRotationPoint(2.7F, 11.1F, 0F);
		this.lleg1.setTextureSize(64, 32);
		this.lleg1.mirror = true;
		this.setRotation(this.lleg1, 0F, 0F, -0.0523599F);
		this.rleg1 = new ModelCharRenderer(this, 16, 50);
		this.rleg1.addBox(-2F, 0F, -2F, 4, 6, 4);
		this.rleg1.setRotationPoint(-2.7F, 11.1F, 0F);
		this.rleg1.setTextureSize(64, 32);
		this.rleg1.mirror = true;
		this.setRotation(this.rleg1, 0F, 0F, 0.0523599F);
		this.lleg2 = new ModelCharRenderer(this, 35, 60);
		this.lleg2.addBox(-2F, 0F, -1.5F, 4, 5, 3);
		this.lleg2.setRotationPoint(3F, 17F, 0F);
		this.lleg2.setTextureSize(64, 32);
		this.lleg2.mirror = true;
		this.setRotation(this.lleg2, 0F, 0F, 0F);
		this.rleg2 = new ModelCharRenderer(this, 17, 60);
		this.rleg2.addBox(-2F, 0F, -1.5F, 4, 5, 3);
		this.rleg2.setRotationPoint(-3F, 17F, 0F);
		this.rleg2.setTextureSize(64, 32);
		this.rleg2.mirror = true;
		this.setRotation(this.rleg2, 0F, 0F, 0F);
		this.lfeet = new ModelCharRenderer(this, 34, 68);
		this.lfeet.addBox(-2F, 0F, -4F, 4, 2, 6);
		this.lfeet.setRotationPoint(3F, 22F, 0F);
		this.lfeet.setTextureSize(64, 32);
		this.lfeet.mirror = true;
		this.setRotation(this.lfeet, 0F, 0F, 0F);
		this.rfeet = new ModelCharRenderer(this, 13, 68);
		this.rfeet.addBox(-2F, 0F, -4F, 4, 2, 6);
		this.rfeet.setRotationPoint(-3F, 22F, 0F);
		this.rfeet.setTextureSize(64, 32);
		this.rfeet.mirror = true;
		this.setRotation(this.rfeet, 0F, 0F, 0F);
		
		
		this.addChildTo(this.lfeet, this.lleg2);
		this.addChildTo(this.lleg2, this.lleg1);
		this.addChildTo(this.rfeet, this.rleg2);
		this.addChildTo(this.rleg2, this.rleg1);
		this.addChildTo(this.lleg1, this.body2);
		this.addChildTo(this.rleg1, this.body2);
		this.addChildTo(this.body2, this.body1);
		this.addChildTo(this.body1, this.base);
		this.addChildTo(this.rarm3, this.rarm2);
		this.addChildTo(this.rarm2, this.rarm1);
		this.addChildTo(this.larm3, this.larm2);
		this.addChildTo(this.larm2, this.larm1);
		this.addChildTo(this.rarm1, this.body1);
		this.addChildTo(this.larm1, this.body1);
		this.addChildTo(this.neck, this.body1);
		this.addChildTo(this.head, this.body1);
		
		this.setInitPose();
	}

	
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.base.render(f5);
		//this.rleg1.render(f5);
		//this.lleg1.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.setToInitPose();
		Characters $char = Characters.get((EntityPlayer)entity);
		if ($char != null && !AerisRenderHelper.hasCustomPlayerRendering(entity, this, f, f1, f2, f3, f4, f5)) {
			float posDiff = (float)((entity.posX - entity.lastTickPosX) + (entity.posY - entity.lastTickPosY) + (entity.posZ - entity.lastTickPosZ));
			//head rotation
			this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
	        this.head.rotateAngleX = f4 / (220F / (float)Math.PI);
	        this.bob(this.body1, 1.0F, 10.1F, true, 0.0F, f1);
	        
	        //attack animation
	        if (this.onGround > -9990.0F && AerisRenderHelper.renderCharacterAttack(entity, this, this.onGround, f, f1, f2, f3, f4, f5)) {
	            float var1 = this.onGround;
	            float var2;
	            this.body1.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(var1) * (float)Math.PI * 2.0F) * 0.2F;
	            this.body2.rotateAngleY = -MathHelper.sin(MathHelper.sqrt_float(var1) * (float)Math.PI * 2.0F) * 0.2F;
	            this.rarm1.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
	            this.rarm1.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
	            this.larm1.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
	            this.larm1.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
	            this.rarm1.rotateAngleY += this.bipedBody.rotateAngleY;
	            this.larm1.rotateAngleY += this.bipedBody.rotateAngleY;
	            this.larm1.rotateAngleX += this.bipedBody.rotateAngleY;
	            var1 = 1.0F - this.onGround;
	            var1 *= var1;
	            var1 *= var1;
	            var1 = 1.0F - var1;
	            var2 = MathHelper.sin(var1 * (float)Math.PI);
	            float var10 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 1.5F) * 0.75F;
	            this.rarm1.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)var2 * 1.2D + (double)var10));
	            this.rarm1.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
	            this.rarm1.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
	        }
	        
	        
			//      box         spd  degre  inver  offs weight f  f1
			//sneak >
			this.walk(this.lleg1, 1.0F, 1.25F, true, 1.0F, 0.0F, 0.0F, $char.renderSneakAngle);
			this.walk(this.lleg2, 1.0F, 1.25F, false, 1.0F, 0.0F, 0.0F, $char.renderSneakAngle);
			this.walk(this.rleg1, 1.0F, 1.25F, true, 1.0F, 0.0F, 0.0F, $char.renderSneakAngle);
			this.walk(this.rleg2, 1.0F, 1.25F, false, 1.0F, 0.0F, 0.0F, $char.renderSneakAngle);
			this.walk(this.lfeet, 1.0F, 0.5F, true, 1.0F, 0.0F, 0.0F, $char.renderSneakAngle);
			this.walk(this.rfeet, 1.0F, 0.5F, true, 1.0F, 0.0F, 0.0F, $char.renderSneakAngle);
			this.walk(this.body1, 1.0F, 0.5F, false, 1.0F, 0.0F, 0.0F, $char.renderSneakAngle);
			//< ride >
			this.bob(this.body1, 1.0F, 1.0F, false, 0.0F, $char.renderRideAngle);
			this.walk(this.body1, 0.5F, 0.25F, true, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.walk(this.body2, 0.5F, -0.25F, true, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.flap(this.lleg1, 1.0F, 1.0F, true, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.walk(this.lleg1, 0.5F, 1.0F, true, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.walk(this.lleg2, 0.5F, 1.5F, false, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.flap(this.rleg1, 1.0F, 1.0F, false, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.walk(this.rleg1, 0.5F, 1.0F, true, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.walk(this.rleg2, 0.5F, 1.5F, false, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.walk(this.larm1, 0.5F, 0.75F, true, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.walk(this.rarm1, 0.5F, 0.75F, true, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.flap(this.larm1, 0.5F, 0.5F, false, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			this.flap(this.rarm1, 0.5F, 0.5F, true, 1.0F, 0.0F, 0.0F, $char.renderRideAngle);
			//< bow >
			if (entity instanceof EntityPlayer && ((EntityPlayer)entity).getHeldItem() != null) {
				if (((EntityPlayer)entity).getHeldItem().getItemUseAction() == EnumAction.bow) {
					if (((EntityPlayer)entity).getItemInUseDuration() > 0) {
						this.walk(this.rarm1, 1.0F, 3.0F, true, 1.0F, 0.0F, 0.0F, 1.0F);
						this.walk(this.larm1, 1.0F, 3.0F, true, 1.0F, 0.0F, 0.0F, 1.0F);
						this.swing(this.larm1, 1.0F, 0.75F, false, 1.0F, 0.0F, 0.0F, 1.0F);
						this.flap(this.larm2, 1.0F, 2.0F, false, 1.0F, 0.0F, 0.0F, $char.renderBowAngle);
					}
				}
			}
			//<
			
			//      box         spd  degre  inver  offs weight f  f1
			if (entity.isSprinting()) {
				if (((EntityPlayer)entity).getHeldItem() != null) {
					if (!AerisRenderHelper.renderCharacterHands(entity, this, true, f, f1, f2, f3, f4, f5)) {
						this.walk(this.larm1, 0.5F, 0.9F, false, 1.0F, 0.0F, f, f1);
						this.walk(this.larm2, 0.5F, 0.5F, false, 1.0F, 0.0F, f, f1);
						this.walk(this.rarm1, 0.5F, 0.9F, true, 1.0F, 0.0F, f, f1);
						this.walk(this.rarm2, 0.5F, 0.5F, true, 1.0F, 0.0F, f, f1);
						this.walk(this.larm3, 0.5F, 0.2F, false, 1.0F, 0.0F, f, f1);
						this.walk(this.rarm3, 0.5F, 0.2F, true, 1.0F, 0.0F, f, f1);
					}
				} else {
					if (!AerisRenderHelper.renderCharacterHands(entity, this, false, f, f1, f2, f3, f4, f5)) {
						this.walk(this.larm1, 0.5F, 0.9F, false, 1.0F, 0.0F, f, f1);
						this.walk(this.larm2, 0.5F, 0.4F, false, 1.0F, 0.0F, f, f1);
						this.walk(this.rarm1, 0.5F, 0.9F, true, 1.0F, 0.0F, f, f1);
						this.walk(this.rarm2, 0.5F, 0.4F, true, 1.0F, 0.0F, f, f1);
					}
				}
				this.walk(this.lleg1, 0.5F, 0.65F, true, 1.0F, 0.0F, f, f1);
				this.walk(this.lleg2, 0.5F, 0.65F, true, 1.0F, 0.0F, f, f1);
				this.walk(this.lfeet, 0.5F, 0.45F, false, 2.0F, 0.0F, f, f1);
				this.walk(this.rleg1, 0.5F, 0.65F, false, 1.0F, 0.0F, f, f1);
				this.walk(this.rleg2, 0.5F, 0.65F, false, 1.0F, 0.0F, f, f1);
				this.walk(this.rfeet, 0.5F, 0.45F, true, 2.0F, 0.0F, f, f1);
				this.flap(this.body1, 0.5F, 0.05F, false, 1.0F, 0.0F, f, f1);
			} else {
				this.walk(this.larm1, 0.5F, 0.6F, false, 1.0F, 0.0F, f, f1);
				this.walk(this.larm2, 0.5F, 0.2F, false, 1.0F, 0.0F, f, f1);
				this.walk(this.larm3, 0.5F, 0.1F, false, 1.0F, 0.0F, f, f1);
				this.walk(this.rarm1, 0.5F, 0.6F, true, 1.0F, 0.0F, f, f1);
				this.walk(this.rarm2, 0.5F, 0.2F, true, 1.0F, 0.0F, f, f1);
				this.walk(this.rarm3, 0.5F, 0.1F, true, 1.0F, 0.0F, f, f1);
				this.walk(this.lleg1, 0.5F, 0.5F, true, 1.0F, 0.0F, f, f1);
				this.walk(this.lleg2, 0.5F, 0.5F, true, 1.0F, 0.0F, f, f1);
				this.walk(this.lfeet, 0.5F, 0.3F, false, 2.0F, 0.0F, f, f1);
				this.walk(this.rleg1, 0.5F, 0.5F, false, 1.0F, 0.0F, f, f1);
				this.walk(this.rleg2, 0.5F, 0.5F, false, 1.0F, 0.0F, f, f1);
				this.walk(this.rfeet, 0.5F, 0.3F, true, 2.0F, 0.0F, f, f1);
				this.flap(this.body1, 0.5F, 0.015F, false, 1.0F, 0.0F, f, f1);
			}
			
			//this.swing(this.body1, 0.5F, 0.075F, false, 1.0F, 0.0F, f, f1);
			
			//this.bob(this.body2, 1.0F, 1.85F, false, f, f1);
			//this.bob(this.larm1, 1.0F, 0.3F, false, f, f1);
			//this.flap(this.larm1, 0.5F, 0.08F, true, 1.0F, 0.0F, f, f1);
			//this.swing(this.larm1, 0.5F, 0.08F, false, 1.0F, 0.0F, f, f1);
			
			
			//walk - 
			//bob - 
			//flap =
			//swing - 
		}
	}
	
	private void setRotation(ModelCharRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
