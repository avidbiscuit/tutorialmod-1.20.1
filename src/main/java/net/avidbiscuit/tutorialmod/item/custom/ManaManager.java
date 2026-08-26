package net.avidbiscuit.tutorialmod.item.custom;

public class ManaManager {
    public static final int MAX_MANA = 100;
    public static final int REGEN_TIME = 50; // 5 seconds

    private static int mana = MAX_MANA;
    private static int regenTimer = 0;

    public static int getMana() {
        return mana;
    }

    public static boolean hasMana(int amount) {
        return mana >= amount;
    }

    public static void useMana(int amount) {
        if (mana >= amount) {
            mana -= amount;
        }
    }

    public static void tick() {
        if (mana < MAX_MANA) {
            regenTimer++;

            if (regenTimer >= REGEN_TIME) {
                mana++;
                regenTimer = 0;
            }
        } else {
            regenTimer = 0;
        }
    }
}
