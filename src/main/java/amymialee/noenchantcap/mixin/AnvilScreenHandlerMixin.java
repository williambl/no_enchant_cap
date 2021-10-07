package amymialee.noenchantcap.mixin;

import amymialee.noenchantcap.NoEnchantCap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.container.RepairContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RepairContainer.class)
public class AnvilScreenHandlerMixin {
    @Redirect(method = "updateRepairOutput", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int redirectGetMaxLevel(Enchantment enchantment) {
        if (enchantment.getMaxLevel() == 1) {
            return 1;
        } else {
            if (NoEnchantCap.CONFIG.useGlobalEnchantCap) {
                return NoEnchantCap.CONFIG.globalEnchantCap;
            } else {
                switch (enchantment.getName()) {
                    case "enchantment.minecraft.sharpness":
                        return NoEnchantCap.CONFIG.sharpnessCap;
                    case "enchantment.minecraft.smite":
                        return NoEnchantCap.CONFIG.smiteCap;
                    case "enchantment.minecraft.bane_of_arthropods":
                        return NoEnchantCap.CONFIG.baneOfArthropodsCap;
                    case "enchantment.minecraft.knockback":
                        return NoEnchantCap.CONFIG.knockbackCap;
                    case "enchantment.minecraft.fire_aspect":
                        return NoEnchantCap.CONFIG.fireAspectCap;
                    case "enchantment.minecraft.sweeping":
                        return NoEnchantCap.CONFIG.sweepingCap;
                    case "enchantment.minecraft.protection":
                        return NoEnchantCap.CONFIG.protectionCap;
                    case "enchantment.minecraft.fire_protection":
                        return NoEnchantCap.CONFIG.protectionFireCap;
                    case "enchantment.minecraft.feather_falling":
                        return NoEnchantCap.CONFIG.featherFallingCap;
                    case "enchantment.minecraft.blast_protection":
                        return NoEnchantCap.CONFIG.protectionBlastCap;
                    case "enchantment.minecraft.projectile_protection":
                        return NoEnchantCap.CONFIG.protectionProjectileCap;
                    case "enchantment.minecraft.respiration":
                        return NoEnchantCap.CONFIG.respirationCap;
                    case "enchantment.minecraft.depth_strider":
                        return NoEnchantCap.CONFIG.depthStriderCap;
                    case "enchantment.minecraft.frost_walker":
                        return NoEnchantCap.CONFIG.frostWalkerCap;
                    case "enchantment.minecraft.soul_speed":
                        return NoEnchantCap.CONFIG.soulSpeedCap;
                    case "enchantment.minecraft.efficiency":
                        return NoEnchantCap.CONFIG.efficiencyCap;
                    case "enchantment.minecraft.unbreaking":
                        return NoEnchantCap.CONFIG.unbreakingCap;
                    case "enchantment.minecraft.looting":
                        return NoEnchantCap.CONFIG.lootingCap;
                    case "enchantment.minecraft.fortune":
                        return NoEnchantCap.CONFIG.fortuneCap;
                    case "enchantment.minecraft.luck_of_the_sea":
                        return NoEnchantCap.CONFIG.luckOfTheSeaCap;
                    case "enchantment.minecraft.lure":
                        return NoEnchantCap.CONFIG.lureCap;
                    case "enchantment.minecraft.power":
                        return NoEnchantCap.CONFIG.powerCap;
                    case "enchantment.minecraft.punch":
                        return NoEnchantCap.CONFIG.punchCap;
                    case "enchantment.minecraft.thorns":
                        return NoEnchantCap.CONFIG.thornsCap;
                    case "enchantment.minecraft.loyalty":
                        return NoEnchantCap.CONFIG.loyaltyCap;
                    case "enchantment.minecraft.impaling":
                        return NoEnchantCap.CONFIG.impalingCap;
                    case "enchantment.minecraft.quick_charge":
                        return NoEnchantCap.CONFIG.quickChargeCap;
                    case "enchantment.minecraft.piercing":
                        return NoEnchantCap.CONFIG.piercingCap;
                    default:
                        return NoEnchantCap.CONFIG.globalEnchantCap;
                }
            }
        }
    }

    @Redirect(method = "updateRepairOutput", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/enchantment/Enchantment;isCompatibleWith(Lnet/minecraft/enchantment/Enchantment;)Z"))
    private boolean redirectCanCombine(Enchantment enchantment, Enchantment enchantment2) {
        if (NoEnchantCap.CONFIG.allowAllEnchantmentCombinations) {
            return (!enchantment.getName().equals("enchantment.minecraft.silk_touch") ||
                    !enchantment2.getName().equals("enchantment.minecraft.fortune")) &&
                    (!enchantment.getName().equals("enchantment.minecraft.riptide") ||
                            !enchantment2.getName().equals("enchantment.minecraft.loyalty")) &&
                    (!enchantment.getName().equals("enchantment.minecraft.fortune") ||
                            !enchantment2.getName().equals("enchantment.minecraft.silk_touch")) &&
                    (!enchantment.getName().equals("enchantment.minecraft.loyalty") ||
                            !enchantment2.getName().equals("enchantment.minecraft.riptide"));
        }
        return enchantment.isCompatibleWith(enchantment2);
    }

    @ModifyConstant(method = "updateRepairOutput", constant = @Constant(intValue = 40, ordinal = 2))
    private int modifyMaxCost(int original) {
        if (NoEnchantCap.CONFIG.removeAnvilLimit) {
            return 50000;
        } else {
            return original;
        }
    }
}
