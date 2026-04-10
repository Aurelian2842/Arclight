package io.izzel.arclight.neoforge.mod.permission;

import io.izzel.arclight.neoforge.mixin.bukkit.PermissibleBaseAccessor;
import org.bukkit.Bukkit;
import org.bukkit.permissions.*;

import java.util.Locale;
import java.util.Set;

public class SilentPermissibleBase extends PermissibleBase {

    public SilentPermissibleBase(ServerOperator opable) {
        super(opable);
    }

    @Override
    public void recalculatePermissions() {
        PermissibleBaseAccessor accessor = (PermissibleBaseAccessor) this;
        clearPermissions();
        Set<Permission> defaults = Bukkit.getServer().getPluginManager().getDefaultPermissions(isOp());

        for (Permission perm : defaults) {
            String name = perm.getName().toLowerCase(Locale.ROOT);
            accessor.getPermissions().put(name, new PermissionAttachmentInfo(accessor.getParent(), name, null, true));
            accessor.invokeCalculateChildPermissions(perm.getChildren(), false, null);
        }

        for (PermissionAttachment attachment : accessor.getAttachments()) {
            accessor.invokeCalculateChildPermissions(attachment.getPermissions(), false, attachment);
        }
    }
}