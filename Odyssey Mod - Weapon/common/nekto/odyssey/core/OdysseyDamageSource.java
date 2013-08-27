package nekto.odyssey.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.util.DamageSource;

public class OdysseyDamageSource extends DamageSource
{
    public static OdysseyDamageSource laser = (OdysseyDamageSource) new OdysseyDamageSource("laser").setDeathMessage("%1$s was blasted by a laser!")/*
                                                                                                                                                     * .
                                                                                                                                                     * setFireDamage
                                                                                                                                                     * (
                                                                                                                                                     * )
                                                                                                                                                     * .
                                                                                                                                                     * setProjectile
                                                                                                                                                     * (
                                                                                                                                                     * )
                                                                                                                                                     */;

    public OdysseyDamageSource(String par1Str)
    {
        super(par1Str);
    }

    public OdysseyDamageSource setDeathMessage(String deathMessage)
    {
        LanguageRegistry.instance().addStringLocalization("death.attack." + this.damageType, deathMessage);
        return this;
    }
}
