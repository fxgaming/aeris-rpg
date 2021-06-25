package by.fxg.aeris.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class RHelper {
	public static int gameColor = 10271186;
	public static int crosshairColor = 16777215;
	private static float zLevel = 0.0F;

	public static int getGameColor() {
		return gameColor;
	}

	public static void setGameColor(String par1) {
		gameColor = (int) Long.parseLong(par1.replaceFirst("0x", ""), 16);
	}

	public static void renderColor(int par1) {
		Color color = Color.decode("" + par1);
		float red = (float) color.getRed() / 255.0F;
		float green = (float) color.getGreen() / 255.0F;
		float blue = (float) color.getBlue() / 255.0F;
		GL11.glColor3f(red, green, blue);
	}

	public static void renderColor(String par1) {
		Color color = Color.decode("" + par1);
		float red = (float) color.getRed() / 255.0F;
		float green = (float) color.getGreen() / 255.0F;
		float blue = (float) color.getBlue() / 255.0F;
		GL11.glColor3f(red, green, blue);
	}

	public static void renderColor() {
		Color color = Color.decode("" + gameColor);
		float red = (float) color.getRed() / 255.0F;
		float green = (float) color.getGreen() / 255.0F;
		float blue = (float) color.getBlue() / 255.0F;
		GL11.glColor3f(red, green, blue);
	}

	public static void renderCrosshairColor() {
		Color color = Color.decode("" + crosshairColor);
		float red = (float) color.getRed() / 255.0F;
		float green = (float) color.getGreen() / 255.0F;
		float blue = (float) color.getBlue() / 255.0F;
		GL11.glColor4f(red, green, blue, 0.9F);
	}

	public static void renderIcon(double par1, double par2, int var3, int var4, int imageSize, double scale, String input) {
		int var5 = var3 * imageSize;
		int var6 = var4 * imageSize;
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		float red = 1.0F;
		float green = 1.0F;
		float blue = 1.0F;
		GL11.glColor4f(red, green, blue, 0.9F);
		GL11.glScaled(scale, scale, scale);
		GL11.glTranslated(par1 * (1.0D / scale), par2 * (1.0D / scale), 0.0D);
		Minecraft.getMinecraft().renderEngine.bindTexture(input);
		drawTexturedModalRect(imageSize / 2, imageSize / 2, var5, var6, imageSize, imageSize);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}

	public static void drawImageTransparent(double x, double y, String image, double width, double height, double alpha) {
		Minecraft.getMinecraft().renderEngine.bindTexture(image);
		GL11.glColor4d(255.0D, 255.0D, 255.0D, alpha);
		Tessellator tess = Tessellator.instance;
		GL11.glEnable(3042);
		GL11.glEnable(2832);
		tess.setColorRGBA_F(255.0F, 0.0F, 0.0F, 0.5F);
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
		tess.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
		tess.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
		tess.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
		tess.draw();
		GL11.glDisable(2832);
		GL11.glDisable(3042);
	}

	public static void drawImage(double par0, double par1, String image, double par2Width, double par3Height, float... color) {
		Minecraft.getMinecraft().renderEngine.bindTexture(image);
		Tessellator tess = new Tessellator();
		tess.startDrawingQuads();
		tess.setColorRGBA_F(color[0], color[1], color[2], color[3]);
		tess.addVertexWithUV(par0, par1 + par3Height, 0.0D, 0.0D, 1.0D);
		tess.addVertexWithUV(par0 + par2Width, par1 + par3Height, 0.0D, 1.0D, 1.0D);
		tess.addVertexWithUV(par0 + par2Width, par1, 0.0D, 1.0D, 0.0D);
		tess.addVertexWithUV(par0, par1, 0.0D, 0.0D, 0.0D);
		tess.draw();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public static void drawImage(double par0, double par1, String image, double par2Width, double par3Height, int... color) {
		Minecraft.getMinecraft().renderEngine.bindTexture(image);
		Tessellator tess = new Tessellator();
		tess.startDrawingQuads();
		tess.setColorRGBA(color[0], color[1], color[2], color[3]);
		tess.addVertexWithUV(par0, par1 + par3Height, 0.0D, 0.0D, 1.0D);
		tess.addVertexWithUV(par0 + par2Width, par1 + par3Height, 0.0D, 1.0D, 1.0D);
		tess.addVertexWithUV(par0 + par2Width, par1, 0.0D, 1.0D, 0.0D);
		tess.addVertexWithUV(par0, par1, 0.0D, 0.0D, 0.0D);
		tess.draw();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public static void drawImage(double par0, double par1, String image, double par2Width, double par3Height) {
		Minecraft.getMinecraft().renderEngine.bindTexture(image);
		Tessellator tess = new Tessellator();
		tess.startDrawingQuads();
		tess.addVertexWithUV(par0, par1 + par3Height, 0.0D, 0.0D, 1.0D);
		tess.addVertexWithUV(par0 + par2Width, par1 + par3Height, 0.0D, 1.0D, 1.0D);
		tess.addVertexWithUV(par0 + par2Width, par1, 0.0D, 1.0D, 0.0D);
		tess.addVertexWithUV(par0, par1, 0.0D, 0.0D, 0.0D);
		tess.draw();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + par6), (double) zLevel, (double) ((float) (par3 + 0) * f), (double) ((float) (par4 + par6) * f1));
		tessellator.addVertexWithUV((double) (par1 + par5), (double) (par2 + par6), (double) zLevel, (double) ((float) (par3 + par5) * f), (double) ((float) (par4 + par6) * f1));
		tessellator.addVertexWithUV((double) (par1 + par5), (double) (par2 + 0), (double) zLevel, (double) ((float) (par3 + par5) * f), (double) ((float) (par4 + 0) * f1));
		tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0), (double) zLevel, (double) ((float) (par3 + 0) * f), (double) ((float) (par4 + 0) * f1));
		tessellator.draw();
	}

	public static void drawRectWithShadow(double par0, double par1, double par2, double par3, String par4Hex, float par5Alpha) {
		drawRect(par0 - 1.0D, par1 - 1.0D, par2 + 2.0D, par3 + 2.0D, "0x000000", 0.3F);
		drawRect(par0, par1, par2, par3, par4Hex, par5Alpha);
	}

	public static void drawRectWithShadow2(int x, int y, int width, int height, Color color, int alpha) {
		drawRect2(x - 1, y - 1, width + 2, height + 2, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha / 2));
		drawRect2(x, y, width, height, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
	}

	public static void drawRect2(int xStart, int yStart, int xEnd, int yEnd, Color color) {
		float r = (float) color.getRed() / 255.0F;
		float g = (float) color.getGreen() / 255.0F;
		float b = (float) color.getBlue() / 255.0F;
		float a = (float) color.getAlpha() / 255.0F;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glColor4f(r, g, b, a);
		tessellator.startDrawingQuads();
		tessellator.addVertex((double) xStart, (double) (yStart + yEnd), 0.0D);
		tessellator.addVertex((double) (xStart + xEnd), (double) (yStart + yEnd), 0.0D);
		tessellator.addVertex((double) (xStart + xEnd), (double) yStart, 0.0D);
		tessellator.addVertex((double) xStart, (double) yStart, 0.0D);
		tessellator.draw();
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public static void drawRectAlpha(int xStart, int yStart, int xEnd, int yEnd, Color color) {
		float r = (float) color.getRed() / 255.0F;
		float g = (float) color.getGreen() / 255.0F;
		float b = (float) color.getBlue() / 255.0F;
		float a = (float) color.getAlpha() / 255.0F;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glColor4f(r, g, b, a);
		tessellator.startDrawingQuads();
		tessellator.addVertex((double) xStart, (double) (yStart + yEnd), 0.0D);
		tessellator.addVertex((double) (xStart + xEnd), (double) (yStart + yEnd), 0.0D);
		tessellator.addVertex((double) (xStart + xEnd), (double) yStart, 0.0D);
		tessellator.addVertex((double) xStart, (double) yStart, 0.0D);
		tessellator.draw();
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public static void drawRect(double par0, double par1, double par2, double par3, String par4Hex, float par5Alpha) {
		Color color = Color.decode(par4Hex);
		float red = (float) color.getRed() / 255.0F;
		float green = (float) color.getGreen() / 255.0F;
		float blue = (float) color.getBlue() / 255.0F;
		drawRect(par0, par1, par0 + par2, par1 + par3, red, green, blue, par5Alpha);
	}

	public static void drawRect(double par0, double par1, double par2, double par3, float par4Red, float par5Green, float par6Blue, float par7Alpha) {
		Tessellator var9 = new Tessellator();
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glColor4f(par4Red, par5Green, par6Blue, par7Alpha);
		var9.startDrawingQuads();
		var9.addVertex(par0, par3, 0.0D);
		var9.addVertex(par2, par3, 0.0D);
		var9.addVertex(par2, par1, 0.0D);
		var9.addVertex(par0, par1, 0.0D);
		var9.draw();
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public static int resWidth() {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		return scaledresolution.getScaledWidth();
	}

	public static int resHeight() {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		return scaledresolution.getScaledHeight();
	}

	public static void renderTextScaled(String text, int posX, int posY, double par4) {
		GL11.glPushMatrix();
		GL11.glTranslated((double) posX, (double) posY, 0.0D);
		GL11.glScaled(par4, par4, par4);
		renderText(text, 0, 0);
		GL11.glPopMatrix();
	}

	public static void renderText(String text, int posX, int posY) {
		renderText(text, posX, posY, true);
	}

	public static void renderTextNoShadow(String text, int posX, int posY) {
		renderText(text, posX, posY, false);
	}

	public static void renderText(String text, int posX, int posY, boolean par4) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.fontRenderer.drawString(text, posX, posY, gameColor, par4);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void drawTextWithOutline(String text, int x, int y, int color) {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fr = mc.fontRenderer;
		fr.drawString(text, x - 1, y + 1, 0);
		fr.drawString(text, x, y + 1, 0);
		fr.drawString(text, x + 1, y + 1, 0);
		fr.drawString(text, x - 1, y, 0);
		fr.drawString(text, x + 1, y, 0);
		fr.drawString(text, x - 1, y - 1, 0);
		fr.drawString(text, x, y - 1, 0);
		fr.drawString(text, x + 1, y - 1, 0);
		fr.drawString(text, x, y, color);
	}

	public static void renderCenteredText(String text, int posX, int posY) {
		renderCenteredText(text, posX, posY, true);
	}

	public static void renderCenteredText(String text, int posX, int posY, boolean par4) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.fontRenderer.drawString(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, gameColor, par4);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void renderTextRight(String text, int posX, int posY) {
		renderTextRight(text, posX, posY, true);
	}

	public static void renderTextRight(String text, int posX, int posY, boolean par4) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.fontRenderer.drawString(text, posX - mc.fontRenderer.getStringWidth(text), posY, gameColor, par4);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void renderTextScaled(String text, int posX, int posY, double par4, int par5) {
		GL11.glPushMatrix();
		GL11.glTranslated((double) posX, (double) posY, 0.0D);
		GL11.glScaled(par4, par4, par4);
		renderText(text, 0, 0, par5);
		GL11.glPopMatrix();
	}

	public static void renderText(String text, int posX, int posY, int par4) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.fontRenderer.drawString(text, posX, posY, par4, true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void renderCenteredText(String text, int posX, int posY, int par4) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.fontRenderer.drawString(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, par4, true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void renderCenteredTextScaled(String text, int posX, int posY, double par4) {
		Minecraft mc = Minecraft.getMinecraft();
		double width = (double) (mc.fontRenderer.getStringWidth(text) / 2) * par4;
		GL11.glPushMatrix();
		GL11.glTranslated((double) posX - width, (double) posY, 0.0D);
		GL11.glScaled(par4, par4, par4);
		renderText(text, 0, 0);
		GL11.glPopMatrix();
	}

	public static void renderCenteredTextScaledWithOutline(String text, int posX, int posY, double par4) {
		Minecraft mc = Minecraft.getMinecraft();
		double width = (double) (mc.fontRenderer.getStringWidth(text) / 2) * par4;
		GL11.glPushMatrix();
		GL11.glTranslated((double) posX - width, (double) posY, 0.0D);
		GL11.glScaled(par4, par4, par4);
		mc.fontRenderer.drawString(text, -1, -1, 0);
		mc.fontRenderer.drawString(text, 0, -1, 0);
		mc.fontRenderer.drawString(text, 1, -1, 0);
		mc.fontRenderer.drawString(text, -1, 0, 0);
		mc.fontRenderer.drawString(text, 1, 0, 0);
		mc.fontRenderer.drawString(text, -1, 1, 0);
		mc.fontRenderer.drawString(text, 0, 1, 0);
		mc.fontRenderer.drawString(text, 1, 1, 0);
		mc.fontRenderer.drawString(text, 0, 0, 16777215);
		GL11.glPopMatrix();
	}

	public static void renderStringFacingPlayer(String par1, double par2, double par3, double par4, float par5, double scale) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		GL11.glPushMatrix();
		FontRenderer fontrenderer = mc.fontRenderer;
		float f122 = 1.8F;
		float scale2 = 0.016F;
		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) par5;
		double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) par5;
		double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) par5;
		GL11.glTranslated(par2, par3, par4);
		GL11.glTranslated(-d0, -d1, -d2);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-player.rotationYaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(player.rotationPitch, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(-scale2, -scale2, scale2);
		GL11.glScaled(scale, scale, scale);
		GL11.glDisable(2896);
		GL11.glDepthMask(false);
		GL11.glDisable(2929);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		Tessellator tessellator = Tessellator.instance;
		byte b0 = 0;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		fontrenderer.drawString(par1, -fontrenderer.getStringWidth(par1) / 2, b0, 553648127);
		GL11.glDepthMask(true);
		fontrenderer.drawString(par1, -fontrenderer.getStringWidth(par1) / 2, b0, -1);
		GL11.glEnable(2896);
		GL11.glDisable(3042);
		GL11.glEnable(2929);
		GL11.glPopMatrix();
	}

	public static void drawOutlinedBox(int x, int y, int z, float par4) {
		drawOutlinedBox((double) x, (double) y, (double) z, 255.0F, 255.0F, 255.0F, 1.0D, par4);
	}

	@SideOnly(Side.CLIENT)
	public static void drawOutlinedBox(double x, double y, double z, float f1, float f2, float f3, double boxsize, float particle) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		double posX = x + boxsize / 2.0D;
		double posY = y + boxsize / 2.0D;
		double posZ = z + boxsize / 2.0D;
		double min = -(boxsize / 2.0D);
		double max = boxsize / 2.0D;
		AxisAlignedBB box = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(posX + min, posY + min, posZ + min, posX + max, posY + max, posZ + max);
		GL11.glPushMatrix();
		GL11.glDisable(3008);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(f1, f2, f3, 0.4F);
		GL11.glLineWidth(5.0F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		double par4 = 0.98D;
		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) particle;
		double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) particle;
		double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) particle;
		drawOutlinedBoundingBox(box.getOffsetBoundingBox(-d0, -d1, -d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glEnable(3008);
		GL11.glPopMatrix();
	}

	@SideOnly(Side.CLIENT)
	protected static void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(3);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
		tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
		tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
		tessellator.draw();
		tessellator.startDrawing(3);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
		tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
		tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
		tessellator.draw();
		tessellator.startDrawing(1);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
		tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
		tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
		tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
		tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
		tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
		tessellator.draw();
	}

	public static boolean isInBox(int x, int y, int width, int height, int checkX, int checkY) {
		return checkX >= x && checkY >= y && checkX <= x + width && checkY <= y + height;
	}

	public static void setSaturation(float givenSaturation) {
		int color2 = Color.HSBtoRGB(1.0F, givenSaturation, 1.0F);
		float red = (float) (color2 >> 16 & 255) / 255.0F;
		float blue = (float) (color2 >> 8 & 255) / 255.0F;
		float green = (float) (color2 & 255) / 255.0F;
		float alpha = (float) (color2 >> 24 & 255) / 255.0F;
		GL11.glColor4f(red, blue, green, alpha);
	}

	public static enum EnumShowcaseColor {
		RED(0), BLUE(1), GREEN(2), ORANGE(3), YELLOW(4), NORMAL(5), ALPHA2(6);

		public int colorIndex = 0;

		private EnumShowcaseColor(int givenID) {
			this.colorIndex = givenID;
		}

		public int getColorIndex() {
			return this.colorIndex;
		}
	}
}
