package net.avidbiscuit.tutorialmod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent TOMATO = new FoodComponent.Builder().hunger(2)
            .saturationModifier(0.55f).statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200), 0.5f).build();
}
