package by.fxg.aeris.gui.intro;

import org.lwjgl.opengl.GL11;

import by.fxg.aeris.gui.RHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class GuiIntroButton extends GuiButton {
    protected int width;
    protected int height;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled;
    public boolean drawButton;
    public float rotateRound = 0.0F;
    public float[] colors;
    public float alpha = 0.0F;

    public GuiIntroButton(int par1, int par2, int par3, String par6Str, float... colors) {
    	super(par1, par2, par3, 64, 64, par6Str);
        this.width = 64;
        this.height = 64;
        this.enabled = true;
        this.drawButton = true;
        this.id = par1;
        this.xPosition = par2;
        this.yPosition = par3;
        this.displayString = par6Str;
        this.colors = colors;
    }
    
    protected int getHoverState(boolean par1) {
        byte var2 = 1;
        if (!this.enabled) {
            var2 = 0;
        }
        else if (par1) {
            var2 = 2;
        }
        return var2;
    }

    public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
        if (this.drawButton) {
            FontRenderer var4 = par1Minecraft.fontRenderer;
            //GL11.glBindTexture(GL11.GL_TEXTURE_2D, par1Minecraft.renderEngine.getTexture("/gui/gui.png"));
            //GL11.glColor4f(1.0F, 1.0F, 1.0F, this.alpha + 1F);
            boolean a = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int var5 = this.getHoverState(a);
            if (var5 == 1 && this.rotateRound > 0.0F) this.rotateRound -= 0.1F;
            else if (var5 == 1 && this.rotateRound < 0.0F) this.rotateRound = 0.0F;
            else if (var5 == 2 && this.rotateRound < 180.0F) this.rotateRound += 0.1F;
            else if (var5 == 2 && this.rotateRound > 180.0F) this.rotateRound = 180.0F;
            RHelper.drawImage(this.xPosition, this.yPosition, "/mods/aeris/misc/bt0.png", 64, 64, this.colors[var5 == 1 ? 0 : 3], this.colors[var5 == 1 ? 1 : 4], this.colors[var5 == 1 ? 2 : 5], this.alpha);
            double x = this.xPosition + 32;
            double y = this.yPosition + 32;
            GL11.glTranslated(x, y + (double)this.rotateRound / 45.0F, 0.0F);
            GL11.glRotatef(this.rotateRound, 0.0F, 0.0F, 1.0F);
            RHelper.drawImage(-38, -38, "/mods/aeris/misc/bt1.png", 76, 76, this.colors[var5 == 1 ? 6 : 9], this.colors[var5 == 1 ? 7 : 10], this.colors[var5 == 1 ? 8 : 11], this.alpha);
            GL11.glTranslated(-x, -y, 0.0F);
            this.mouseDragged(par1Minecraft, par2, par3);

            //this.drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, var6);
        }
    }

    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {}

    public void mouseReleased(int par1, int par2) {}

    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
        return this.enabled && this.drawButton && par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
    }
}
