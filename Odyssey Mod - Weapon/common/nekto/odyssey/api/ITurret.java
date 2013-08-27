package nekto.odyssey.api;

import net.minecraft.entity.Entity;

public interface ITurret
{
    public Entity getTarget();

    public int getDetectionRange();

    public void aim();
}
