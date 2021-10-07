package amymialee.noenchantcap;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("noenchantcap")
public class NoEnchantCap {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final EnchantModConfig CONFIG = new EnchantModConfig();

    public NoEnchantCap() {
    }
}
