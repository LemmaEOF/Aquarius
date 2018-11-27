package space.bbkr.aquarius;

import net.minecraft.potion.Potion;

public class AquariusPotion extends Potion {
    public AquariusPotion(boolean isBadEffect, int color) {
        super(isBadEffect, color);
    }

    public AquariusPotion setIcon(int x, int y) {
        return (AquariusPotion)super.setIconIndex(x, y);
    }
}
