package net.minecraft.enchantment;

import net.minecraft.entity.EquipmentSlot;

public class FlameEnchantment extends Enchantment
{
    public FlameEnchantment(final Weight weight, final EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.BOW, slotTypes);
    }
    
    @Override
    public int getMinimumPower(final int integer) {
        return 20;
    }
    
    @Override
    public int getMaximumLevel() {
        return 1;
    }
}
