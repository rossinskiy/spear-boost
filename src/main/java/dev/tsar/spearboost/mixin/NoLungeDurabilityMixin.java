package dev.tsar.spearboost.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.tsar.spearboost.LungeUtil;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.entity.ChangeItemDamageEnchantmentEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

/**
 * The Lunge enchantment applies its 1 durability cost via the data-driven
 * {@code minecraft:change_item_damage} effect (see data/minecraft/enchantment/lunge.json).
 * Cancel that effect when the enchanted item is a Lunge spear.
 */
@Mixin(ChangeItemDamageEnchantmentEffect.class)
public class NoLungeDurabilityMixin {

    @Inject(
        method = "apply(Lnet/minecraft/server/world/ServerWorld;ILnet/minecraft/enchantment/EnchantmentEffectContext;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Vec3d;)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private void spearboost$skipLungeDurability(ServerWorld world, int level, EnchantmentEffectContext context,
                                                Entity entity, Vec3d pos, CallbackInfo ci) {
        if (LungeUtil.hasLunge(context.stack())) {
            ci.cancel();
        }
    }
}
