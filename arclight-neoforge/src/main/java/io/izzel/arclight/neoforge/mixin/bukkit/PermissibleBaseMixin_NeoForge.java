package io.izzel.arclight.neoforge.mixin.bukkit;

import io.izzel.arclight.mixin.Decorate;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.plugin.PluginManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = PermissibleBase.class, remap = false)
public class PermissibleBaseMixin_NeoForge {
    @Decorate(
            method = "recalculatePermissions",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/bukkit/plugin/PluginManager;subscribeToPermission(Ljava/lang/String;Lorg/bukkit/permissions/Permissible;)V"
            )
    )
    private void arclight$skipSubscribe(PluginManager instance, String permission, Permissible permissible) {
        if (!(permissible instanceof net.neoforged.neoforge.common.util.FakePlayer)) {
            instance.subscribeToPermission(permission, permissible);
        }
    }

    @Decorate(
            method = "recalculatePermissions",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/bukkit/plugin/PluginManager;subscribeToDefaultPerms(ZLorg/bukkit/permissions/Permissible;)V"
            )
    )
    private void arclight$skipDefaultSubscribe(PluginManager instance, boolean op, Permissible permissible) {
        if (!(permissible instanceof net.neoforged.neoforge.common.util.FakePlayer)) {
            instance.subscribeToDefaultPerms(op, permissible);
        }
    }
}
