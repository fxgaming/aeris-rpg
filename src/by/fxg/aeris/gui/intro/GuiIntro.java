package by.fxg.aeris.gui.intro;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import by.fxg.aeris.gui.RHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.texturepacks.GuiTexturePacks;

public class GuiIntro extends GuiScreen {
	public static final Random rand = new Random();
    public double timer1 = 0, timer2 = 0, timer3 = 0, timer4 = 0, timer5 = 0, timer6 = 0, timer7 = 0, timer8 = 0;
    public int tvar = 0;
    public boolean bool1 = false, bool2 = false, bool3 = false, bool4 = false;
    public GuiIntroButton btn0;
    public GuiIntro() {
    }

    public void updateScreen() {
    	ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
    	//this.btn0.alpha = (float)(timer4 / 10.0D);
    	if (timer1 < 4.75D) timer1 += (5.0D - timer1) / 8;
    	else {
    		timer1 = 4.75D;
    		if (timer2 < 9.0D) timer2 += 0.725D;
        	else {
        		timer2 = 9.0D;
        		if (timer3 < 15.0D) timer3 += 3.0D;
        		else if (timer3 < 10.0D) {
        			if (timer4 < 10.0D) timer4 += 0.75D;
            		else timer4 = 10.0D;
        		} else {
        			timer3 = 15.0D;
        			if (timer4 < 10.0D) timer4 += 0.5D;
            		else timer4 = 10.0D;
        			if (timer5 < 35.0D) timer5 += (35.0D - timer5) / 10;
            		else timer5 = 35.0D;
        			if (!bool1) {
        				if (this.timer6 < 230.0D) this.timer6 += (255.0D - timer6) / 2.0D;
        				else bool1 = true;
        			} else {
        				if (this.timer6 > 0.0D) this.timer6 -= (255.0D - timer6) / 2.0D;
        				else this.timer6 = 0.0D;
        			}
        		}
        	}
    	}
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    protected void keyTyped(char par1, int par2) {
    	if (par2 == 1) {
    		timer1 = timer2 = timer3 = timer4 = timer5 = timer6 = timer7 = timer8 = 0;
    		bool1 = bool2 = bool3 = bool4 = false;
    	}
    }

    public void initGui() {
    	//this.controlList.add(new GuiButton(1, this.width / 2 - 100, par1, par3StringTranslate.translateKey("menu.singleplayer")));
    	ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
    	this.controlList.add(new GuiButton(0, 10, 10, "kis"));
//    	this.controlList.add(this.btn0 = new GuiIntroButton(0, sr.getScaledWidth() / 4, sr.getScaledHeight() / 3 * 2, "keki", 
//    			0.75F, 0.1F, 0.25F, 
//    			0.25F, 0.75F, 0.1F,
//    			0.25F, 0.75F, 0.1F,
//    			0.75F, 0.1F, 0.25F
//    	));
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.id == 0 && !GuiScreen.isCtrlKeyDown()) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        } else {
        	this.mc.displayGuiScreen(new GuiTexturePacks(this));
        }
    }
    
    public void drawScreen(int par1, int par2, float par3) {
        ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        RHelper.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledWidth(), "0xFFFFFF", 1.0F);
        double time3 = timer3 > 5 ? timer3 - 5 : 0;
        double time4 = timer4 + timer4 / 2.5D;
        String str = "A E R I S".substring(0, (int)timer2);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        RHelper.drawImage(
        		sr.getScaledWidth() / 2 - (32 * timer1) / 2 - (time3 / 2.0D) + (time4 / 1.25D), 
        		sr.getScaledHeight() / 2 - (32 * timer1) / 2 - 20 - (time3 * 2.0D) - (time4 / 2.0D), 
        		"/mods/aeris/misc/emerald.png", 
        		(32 * timer1) + (time3) - (time4 * 1.5D), 
        		(32 * timer1) + (time3) - (time4 * 1.5D)
        );
        RHelper.renderText(
        		str, 
        		sr.getScaledWidth() / 2 - this.mc.fontRenderer.getStringWidth(str) / 2, 
        		sr.getScaledHeight() / 2 + 50 - (int)(time3 * 1.25D) - (int)(time4 * 2.0D),
        		0x0EE43C
        );
        int reed = 0;
        for (int i = 0; i < sr.getScaledWidth();) {
        	i += sr.getScaledWidth() / (sr.getScaledWidth() / 24);
        	RHelper.drawImage(i - 24, sr.getScaledHeight() - 8 - timer5, "/mods/aeris/misc/reed" + reed + ".png", 24, 48);
        	if (reed > 3) reed = 0;
        }
        RHelper.drawImage(sr.getScaledWidth() / 2 - 100, sr.getScaledHeight() / 2 - 156 - (time3 * 2.0D) - (time4 / 2.0D), "/mods/aeris/misc/flare.png", 256, 256, 255, 255, 255, (int)this.timer6);
        super.drawScreen(par1, par2, par3);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
    }
}
