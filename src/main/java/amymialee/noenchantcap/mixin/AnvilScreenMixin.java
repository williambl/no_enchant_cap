package amymialee.noenchantcap.mixin;

import amymialee.noenchantcap.NoEnchantCap;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer", constant = @Constant(intValue = 40, ordinal = 0))
    private int modifyMaxCost(int original) {
        if (NoEnchantCap.CONFIG.removeAnvilLimit) {
            return Integer.MAX_VALUE;
        } else {
            return original;
        }
    }
}