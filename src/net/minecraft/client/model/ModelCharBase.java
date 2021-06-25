package net.minecraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelCharBase extends ModelBiped {
	protected List parts;

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
	}

	public void renderEars(float par1) {
	}

	public void renderCloak(float par1) {
	}

	protected void setInitPose() {
		for (int i = 0; i < this.parts.size(); ++i) {
			((ModelCharRenderer) this.parts.get(i)).setInitValuesToCurrentPose();
		}

	}

	public void setToInitPose() {
		for (int i = 0; i < this.parts.size(); ++i) {
			((ModelCharRenderer) this.parts.get(i)).setCurrentPoseToInitValues();
		}

	}

	protected void addChildTo(ModelRenderer child, ModelRenderer parent) {
		float distance = (float) Math.sqrt(Math.pow((double) (child.rotationPointZ - parent.rotationPointZ), 2.0D) + Math.pow((double) (child.rotationPointY - parent.rotationPointY), 2.0D));
		float oldRotateAngleX = parent.rotateAngleX;
		float parentToChildAngle = (float) Math.atan((double) ((child.rotationPointZ - parent.rotationPointZ) / (child.rotationPointY - parent.rotationPointY)));
		float childRelativeRotation = parentToChildAngle - parent.rotateAngleX;
		float newRotationPointY = (float) ((double) distance * Math.cos((double) childRelativeRotation));
		float newRotationPointZ = (float) ((double) distance * Math.sin((double) childRelativeRotation));
		parent.rotateAngleX = 0.0F;
		child.setRotationPoint(child.rotationPointX - parent.rotationPointX, newRotationPointY, newRotationPointZ);
		parent.addChild(child);
		parent.rotateAngleX = oldRotateAngleX;
		child.rotateAngleX -= parent.rotateAngleX;
		child.rotateAngleY -= parent.rotateAngleY;
		child.rotateAngleZ -= parent.rotateAngleZ;
	}

	public void faceTarget(ModelCharRenderer box, float f, float f3, float f4) {
		box.rotateAngleY += f3 / 57.295776F / f;
		box.rotateAngleX += f4 / 57.295776F / f;
	}

	public float rotateBox(float speed, float degree, boolean invert, float offset, float weight, float f, float f1) {
		return invert ? -MathHelper.cos(f * speed + offset) * degree * f1 + weight * f1 : MathHelper.cos(f * speed + offset) * degree * f1 + weight * f1;
	}

	public float moveBox(float speed, float degree, boolean bounce, float f, float f1) {
		return bounce ? -MathHelper.abs(MathHelper.sin(f * speed) * f1 * degree) : MathHelper.sin(f * speed) * f1 * degree - f1 * degree;
	}

	public void walk(ModelCharRenderer box, float speed, float degree, boolean invert, float offset, float weight, float f, float f1) {
		int inverted = 1;
		if (invert) {
			inverted = -1;
		}

		box.rotateAngleX += MathHelper.cos(f * speed + offset) * degree * (float) inverted * f1 + weight * f1;
	}

	public void flap(ModelCharRenderer box, float speed, float degree, boolean invert, float offset, float weight, float f, float f1) {
		int inverted = 1;
		if (invert) {
			inverted = -1;
		}

		box.rotateAngleZ += MathHelper.cos(f * speed + offset) * degree * (float) inverted * f1 + weight * f1;
	}

	public void swing(ModelCharRenderer box, float speed, float degree, boolean invert, float offset, float weight, float f, float f1) {
		int inverted = 1;
		if (invert) {
			inverted = -1;
		}

		box.rotateAngleY += MathHelper.cos(f * speed + offset) * degree * (float) inverted * f1 + weight * f1;
	}

	public void bob(ModelCharRenderer box, float speed, float degree, boolean bounce, float f, float f1) {
		float bob = (float) (Math.sin((double) (f * speed)) * (double) f1 * (double) degree - (double) (f1 * degree));
		if (bounce) {
			bob = (float) (-Math.abs(Math.sin((double) (f * speed)) * (double) f1 * (double) degree));
		}

		box.rotationPointY += bob;
	}
	
	public void bobX(ModelCharRenderer box, float speed, float degree, boolean bounce, float f, float f1) {
		float bob = (float) (Math.sin((double) (f * speed)) * (double) f1 * (double) degree - (double) (f1 * degree));
		if (bounce) {
			bob = (float) (-Math.abs(Math.sin((double) (f * speed)) * (double) f1 * (double) degree));
		}

		box.rotationPointX += bob;
	}
	
	public void swing(ModelCharRenderer box, float speed, float degree, boolean bounce, float offset, float f, float f1) {
		float bob = (float) (Math.sin((double) (f * speed)) * (double) f1 * (double) degree - (double) (f1 * degree));
		if (bounce) {
			bob = (float) (-Math.abs(Math.sin((double) (f * speed)) * (double) f1 * (double) degree));
		}

		box.rotateAngleX += MathHelper.cos(f * speed + offset) * f1 * degree;
	}

	public void chainSwing(ModelCharRenderer[] boxes, float speed, float degree, double rootOffset, float f, float f1) {
		int numberOfSegments = boxes.length;
		float offset = (float) (rootOffset * 3.141592653589793D / (double) (2 * numberOfSegments));

		for (int i = 0; i < numberOfSegments; ++i) {
			boxes[i].rotateAngleY += MathHelper.cos(f * speed + offset * (float) i) * f1 * degree;
		}

	}

	public void chainWave(ModelCharRenderer[] boxes, float speed, float degree, double rootOffset, float f, float f1) {
		int numberOfSegments = boxes.length;
		float offset = (float) (rootOffset * 3.141592653589793D / (double) (2 * numberOfSegments));

		for (int i = 0; i < numberOfSegments; ++i) {
			boxes[i].rotateAngleX += MathHelper.cos(f * speed + offset * (float) i) * f1 * degree;
		}

	}

	public void chainFlap(ModelCharRenderer[] boxes, float speed, float degree, double rootOffset, float f, float f1) {
		int numberOfSegments = boxes.length;
		float offset = (float) (rootOffset * 3.141592653589793D / (double) (2 * numberOfSegments));

		for (int i = 0; i < numberOfSegments; ++i) {
			boxes[i].rotateAngleZ += MathHelper.cos(f * speed + offset * (float) i) * f1 * degree;
		}

	}

	protected void rotateTo(ModelRenderer rotating, ModelRenderer to, float t) {
		float rotXDif = to.rotateAngleX - rotating.rotateAngleX;
		float rotYDif = to.rotateAngleY - rotating.rotateAngleY;
		float rotZDif = to.rotateAngleZ - rotating.rotateAngleZ;
		float posXDif = to.rotationPointX - rotating.rotationPointX;
		float posYDif = to.rotationPointY - rotating.rotationPointY;
		float posZDif = to.rotationPointZ - rotating.rotationPointZ;
		float offsetXDif = to.field_82906_o - rotating.field_82906_o;
		float offsetYDif = to.field_82907_q - rotating.field_82907_q;
		float offsetZDif = to.field_82908_p - rotating.field_82908_p;
		rotating.rotateAngleX += rotXDif / 20.0F * t;
		rotating.rotateAngleY += rotYDif / 20.0F * t;
		rotating.rotateAngleZ += rotZDif / 20.0F * t;
		rotating.field_82906_o += offsetXDif / 20.0F * t;
		rotating.field_82907_q += offsetYDif / 20.0F * t;
		rotating.field_82908_p += offsetZDif / 20.0F * t;
		rotating.rotationPointX += posXDif / 20.0F * t;
		rotating.rotationPointY += posYDif / 20.0F * t;
		rotating.rotationPointZ += posZDif / 20.0F * t;
	}

	public void addPart(ModelCharRenderer mowzieModelRenderer) {
		if (this.parts == null) {
			this.parts = new ArrayList();
		}

		this.parts.add(mowzieModelRenderer);
	}
}
