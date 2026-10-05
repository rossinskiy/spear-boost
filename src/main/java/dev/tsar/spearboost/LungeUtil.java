package dev.tsar.spearboost;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

/**
 * Helper for detecting the vanilla {@code minecraft:lunge} enchantment.
 * Lunge only exists on spears, so checking for the enchantment is enough --
 * we never need to hard-code the individual spear item ids.
 */
public final class LungeUtil {

    private static final Identifier LUNGE = Identifier.ofVanilla("lunge");

    private LungeUtil() {
    }

    public static boolean hasLunge(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        for (RegistryEntry<Enchantment> entry : stack.getEnchantments().getEnchantments()) {
            if (entry.getKey().map(key -> key.getValue().equals(LUNGE)).orElse(false)) {
                return true;
            }
        }
        return false;
    }
}
