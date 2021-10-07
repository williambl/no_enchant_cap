package amymialee.noenchantcap.mixin;

import amymialee.noenchantcap.NoEnchantCap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
    @Shadow @Final public EnchantmentType type;

    @Shadow public abstract String getName();

    @Inject(method = "canApply", at = @At(value = "HEAD"), cancellable = true)
    public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (NoEnchantCap.CONFIG.allowAnyEnchantOnAnyItem) {
            cir.setReturnValue(true);
        } else {
            if (this.getName().equals("enchantment.minecraft.looting") && stack.getItem() instanceof AxeItem) {
                cir.setReturnValue(true);
            } else {
                cir.setReturnValue(this.type.canEnchantItem(stack.getItem()));
            }
        }
    }
}
