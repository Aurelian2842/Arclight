package io.izzel.arclight.neoforge.mixin.bukkit;

import org.bukkit.permissions.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Map;

@Mixin(value = PermissibleBase.class, remap = false)
public interface PermissibleBaseAccessor {
    @Accessor("parent")
    Permissible getParent();

    @Accessor("permissions")
    Map<String, PermissionAttachmentInfo> getPermissions();

    @Accessor("attachments")
    List<PermissionAttachment> getAttachments();

    @Invoker("calculateChildPermissions")
    void invokeCalculateChildPermissions(Map<String, Boolean> children, boolean invert, PermissionAttachment attachment);
}
