package net.minecraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelCharRenderer extends ModelRenderer {
	public float initRotateAngleX;
	public float initRotateAngleY;
	public float initRotateAngleZ;
	public float initOffsetX;
	public float initOffsetY;
	public float initOffsetZ;
	public float initRotationPointX;
	public float initRotationPointY;
	public float initRotationPointZ;
	public float scaleX = 1.0F;
	public float scaleY = 1.0F;
	public float scaleZ = 1.0F;
	private boolean compiled;
	private int displayList;
	public ModelRenderer parent;
	public boolean hasInitPose;

	public ModelCharRenderer(ModelBase modelBase, String name) {
		super(modelBase, name);
	}

	public ModelCharRenderer(ModelBase modelBase, int x, int y) {
		super(modelBase, x, y);
		if (modelBase instanceof ModelCharBase) {
			ModelCharBase modelCharBase = (ModelCharBase) modelBase;
			modelCharBase.addPart(this);
		}
	}

	public ModelCharRenderer(ModelBase modelBase) {
		super(modelBase);
	}

	public void addChild(ModelRenderer renderer) {
		super.addChild(renderer);
		if (renderer instanceof ModelCharRenderer) {
			((ModelCharRenderer) renderer).setParent(this);
		}
	}

	public void postRenderParentChain(float par1) {
		if (this.parent instanceof ModelCharRenderer) {
			((ModelCharRenderer) this.parent).postRenderParentChain(par1);
		} else if (this.parent != null) {
			this.parent.postRender(par1);
		}

		this.postRender(par1);
	}

	public ModelRenderer getParent() {
		return this.parent;
	}

	private void setParent(ModelRenderer modelRenderer) {
		this.parent = modelRenderer;
	}

	public void setInitValuesToCurrentPose() {
		this.initRotateAngleX = super.rotateAngleX;
		this.initRotateAngleY = super.rotateAngleY;
		this.initRotateAngleZ = super.rotateAngleZ;
		this.initRotationPointX = super.rotationPointX;
		this.initRotationPointY = super.rotationPointY;
		this.initRotationPointZ = super.rotationPointZ;
		this.initOffsetX = super.field_82906_o;
		this.initOffsetY = super.field_82907_q;
		this.initOffsetZ = super.field_82908_p;
		this.hasInitPose = true;
	}

	public void setCurrentPoseToInitValues() {
		if (this.hasInitPose) {
			super.rotateAngleX = this.initRotateAngleX;
			super.rotateAngleY = this.initRotateAngleY;
			super.rotateAngleZ = this.initRotateAngleZ;
			super.rotationPointX = this.initRotationPointX;
			super.rotationPointY = this.initRotationPointY;
			super.rotationPointZ = this.initRotationPointZ;
			super.field_82906_o = this.initOffsetX;
			super.field_82907_q = this.initOffsetY;
			super.field_82908_p = this.initOffsetZ;
		}

	}

	public void setRotationAngles(float x, float y, float z) {
		super.rotateAngleX = x;
		super.rotateAngleY = y;
		super.rotateAngleZ = z;
	}

	public void resetAllRotationPoints() {
		super.rotationPointX = this.initRotationPointX;
		super.rotationPointY = this.initRotationPointY;
		super.rotationPointZ = this.initRotationPointZ;
	}

	public void resetXRotationPoints() {
		super.rotationPointX = this.initRotationPointX;
	}

	public void resetYRotationPoints() {
		super.rotationPointY = this.initRotationPointY;
	}

	public void resetZRotationPoints() {
		super.rotationPointZ = this.initRotationPointZ;
	}

	public void resetAllRotations() {
		super.rotateAngleX = this.initRotateAngleX;
		super.rotateAngleY = this.initRotateAngleY;
		super.rotateAngleZ = this.initRotateAngleZ;
	}

	public void resetXRotations() {
		super.rotateAngleX = this.initRotateAngleX;
	}

	public void resetYRotations() {
		super.rotateAngleY = this.initRotateAngleY;
	}

	public void resetZRotations() {
		super.rotateAngleZ = this.initRotateAngleZ;
	}

	public void copyAllRotationPoints(ModelCharRenderer target) {
		super.rotationPointX = target.rotationPointX;
		super.rotationPointY = target.rotationPointY;
		super.rotationPointZ = target.rotationPointZ;
	}

	public void copyXRotationPoint(ModelCharRenderer target) {
		super.rotationPointX = target.rotationPointX;
	}

	public void copyYRotationPoint(ModelCharRenderer target) {
		super.rotationPointY = target.rotationPointY;
	}

	public void copyZRotationPoint(ModelCharRenderer target) {
		super.rotationPointZ = target.rotationPointZ;
	}

	public void renderWithParents(float partialTicks) {
		if (this.parent instanceof ModelCharRenderer) {
			((ModelCharRenderer) this.parent).renderWithParents(partialTicks);
		} else if (this.parent != null) {
			this.parent.render(partialTicks);
		}

		this.render(partialTicks);
	}

	public void setScale(float x, float y, float z) {
		this.scaleX = x;
		this.scaleY = y;
		this.scaleZ = z;
	}

	@SideOnly(Side.CLIENT)
	public void render(float p_78785_1_) {
		GL11.glPushMatrix();
		if (!super.isHidden && super.showModel) {
			if (!this.compiled) {
				this.compileDisplayList(p_78785_1_);
			}

			float f5 = 0.0625F;
			GL11.glTranslatef(super.rotationPointX * f5, super.rotationPointY * f5, super.rotationPointZ * f5);
			GL11.glTranslatef(super.field_82906_o, super.field_82907_q, super.field_82908_p);
			GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
			GL11.glTranslatef(-super.rotationPointX * f5, -super.rotationPointY * f5, -super.rotationPointZ * f5);
			int i;
			if (super.rotateAngleX == 0.0F && super.rotateAngleY == 0.0F && super.rotateAngleZ == 0.0F) {
				if (super.rotationPointX == 0.0F && super.rotationPointY == 0.0F && super.rotationPointZ == 0.0F) {
					GL11.glCallList(this.displayList);
					if (super.childModels != null) {
						for (i = 0; i < super.childModels.size(); ++i) {
							((ModelCharRenderer) super.childModels.get(i)).render(p_78785_1_);
						}
					}
				} else {
					GL11.glTranslatef(super.rotationPointX * p_78785_1_, super.rotationPointY * p_78785_1_, super.rotationPointZ * p_78785_1_);
					GL11.glCallList(this.displayList);
					if (super.childModels != null) {
						for (i = 0; i < super.childModels.size(); ++i) {
							((ModelCharRenderer) super.childModels.get(i)).render(p_78785_1_);
						}
					}

					GL11.glTranslatef(-super.rotationPointX * p_78785_1_, -super.rotationPointY * p_78785_1_, -super.rotationPointZ * p_78785_1_);
				}
			} else {
				GL11.glPushMatrix();
				GL11.glTranslatef(super.rotationPointX * p_78785_1_, super.rotationPointY * p_78785_1_, super.rotationPointZ * p_78785_1_);
				if (super.rotateAngleZ != 0.0F) {
					GL11.glRotatef(super.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
				}

				if (super.rotateAngleY != 0.0F) {
					GL11.glRotatef(super.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
				}

				if (super.rotateAngleX != 0.0F) {
					GL11.glRotatef(super.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
				}

				GL11.glCallList(this.displayList);
				if (super.childModels != null) {
					for (i = 0; i < super.childModels.size(); ++i) {
						((ModelCharRenderer) super.childModels.get(i)).render(p_78785_1_);
					}
				}

				GL11.glPopMatrix();
			}

			GL11.glTranslatef(-super.field_82906_o, -super.field_82907_q, -super.field_82908_p);
			GL11.glScalef(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
		}

		GL11.glPopMatrix();
	}

	@SideOnly(Side.CLIENT)
	private void compileDisplayList(float p_78788_1_) {
		this.displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(this.displayList, 4864);
		Tessellator tessellator = Tessellator.instance;

		for (int i = 0; i < super.cubeList.size(); ++i) {
			((ModelBox) super.cubeList.get(i)).render(tessellator, p_78788_1_);
		}

		GL11.glEndList();
		this.compiled = true;
	}
}
