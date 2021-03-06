package net.minecraft.advancement.criterion;

import java.util.Iterator;
import java.util.List;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Set;
import com.google.gson.JsonElement;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.predicate.item.ItemPredicate;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.common.collect.Maps;
import net.minecraft.advancement.PlayerAdvancementTracker;
import java.util.Map;
import net.minecraft.util.Identifier;

public class ShotCrossbowCriterion implements Criterion<Conditions>
{
    private static final Identifier ID;
    private final Map<PlayerAdvancementTracker, Handler> handlers;
    
    public ShotCrossbowCriterion() {
        this.handlers = Maps.newHashMap();
    }
    
    @Override
    public Identifier getId() {
        return ShotCrossbowCriterion.ID;
    }
    
    @Override
    public void beginTrackingCondition(final PlayerAdvancementTracker manager, final ConditionsContainer<Conditions> conditionsContainer) {
        Handler handler3 = this.handlers.get(manager);
        if (handler3 == null) {
            handler3 = new Handler(manager);
            this.handlers.put(manager, handler3);
        }
        handler3.add(conditionsContainer);
    }
    
    @Override
    public void endTrackingCondition(final PlayerAdvancementTracker manager, final ConditionsContainer<Conditions> conditionsContainer) {
        final Handler handler3 = this.handlers.get(manager);
        if (handler3 != null) {
            handler3.remove(conditionsContainer);
            if (handler3.isEmpty()) {
                this.handlers.remove(manager);
            }
        }
    }
    
    @Override
    public void endTracking(final PlayerAdvancementTracker playerAdvancementTracker) {
        this.handlers.remove(playerAdvancementTracker);
    }
    
    @Override
    public Conditions conditionsFromJson(final JsonObject obj, final JsonDeserializationContext jsonDeserializationContext) {
        final ItemPredicate itemPredicate3 = ItemPredicate.deserialize(obj.get("item"));
        return new Conditions(itemPredicate3);
    }
    
    public void trigger(final ServerPlayerEntity serverPlayerEntity, final ItemStack itemStack) {
        final Handler handler3 = this.handlers.get(serverPlayerEntity.getAdvancementManager());
        if (handler3 != null) {
            handler3.trigger(itemStack);
        }
    }
    
    static {
        ID = new Identifier("shot_crossbow");
    }
    
    public static class Conditions extends AbstractCriterionConditions
    {
        private final ItemPredicate item;
        
        public Conditions(final ItemPredicate itemPredicate) {
            super(ShotCrossbowCriterion.ID);
            this.item = itemPredicate;
        }
        
        public static Conditions create(final ItemProvider itemProvider) {
            return new Conditions(ItemPredicate.Builder.create().item(itemProvider).build());
        }
        
        public boolean matches(final ItemStack itemStack) {
            return this.item.test(itemStack);
        }
        
        @Override
        public JsonElement toJson() {
            final JsonObject jsonObject1 = new JsonObject();
            jsonObject1.add("item", this.item.serialize());
            return jsonObject1;
        }
    }
    
    static class Handler
    {
        private final PlayerAdvancementTracker tracker;
        private final Set<ConditionsContainer<Conditions>> conditions;
        
        public Handler(final PlayerAdvancementTracker playerAdvancementTracker) {
            this.conditions = Sets.newHashSet();
            this.tracker = playerAdvancementTracker;
        }
        
        public boolean isEmpty() {
            return this.conditions.isEmpty();
        }
        
        public void add(final ConditionsContainer<Conditions> conditionsContainer) {
            this.conditions.add(conditionsContainer);
        }
        
        public void remove(final ConditionsContainer<Conditions> conditionsContainer) {
            this.conditions.remove(conditionsContainer);
        }
        
        public void trigger(final ItemStack itemStack) {
            List<ConditionsContainer<Conditions>> list2 = null;
            for (final ConditionsContainer<Conditions> conditionsContainer4 : this.conditions) {
                if (conditionsContainer4.getConditions().matches(itemStack)) {
                    if (list2 == null) {
                        list2 = Lists.newArrayList();
                    }
                    list2.add(conditionsContainer4);
                }
            }
            if (list2 != null) {
                for (final ConditionsContainer<Conditions> conditionsContainer4 : list2) {
                    conditionsContainer4.apply(this.tracker);
                }
            }
        }
    }
}
