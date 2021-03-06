package net.minecraft.realms;

import net.minecraft.SharedConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RealmsSharedConstants
{
    public static final int TICKS_PER_SECOND = 20;
    public static final char[] ILLEGAL_FILE_CHARACTERS;
    
    static {
        ILLEGAL_FILE_CHARACTERS = SharedConstants.INVALID_CHARS_LEVEL_NAME;
    }
}
