package mods.tesseract.bettereffects;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.tclproject.mysteriumlib.asm.annotations.EnumReturnSetting;
import net.tclproject.mysteriumlib.asm.annotations.Fix;

public class FixesEffects {
    @Fix(returnSetting = EnumReturnSetting.ALWAYS)
    public static float getNightVisionBrightness(EntityRenderer c, EntityPlayer p, float t) {
        int i = p.getActivePotionEffect(Potion.nightVision).getDuration();
        return i > 20 ? 1.0F : (float) i / 20;
    }

    @Fix(returnSetting = EnumReturnSetting.ON_TRUE, anotherMethodReturned = "isPoisonReady")
    public static boolean isReady(Potion c, int duration, int amplifier) {
        return c.id == Potion.poison.id;
    }

    public static boolean isPoisonReady(Potion c, int duration, int amplifier) {
        int k = 75 >> amplifier;
        return k == 0 || duration % k == 0;
    }

    @Fix
    public static void performEffect(Potion c, EntityLivingBase e, int a) {
        if (c.id == Potion.poison.id && e.getHealth() <= 1)
            e.attackEntityFrom(DamageSource.magic, 1);
    }
}
