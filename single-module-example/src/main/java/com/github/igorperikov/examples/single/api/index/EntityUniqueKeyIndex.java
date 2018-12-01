package com.github.igorperikov.examples.single.api.index;

import com.github.igorperikov.examples.single.api.Entity;
import com.github.igorperikov.examples.single.api.EntityApi;
import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.api.consumer.index.HollowUniqueKeyIndex;

@SuppressWarnings("all")
public class EntityUniqueKeyIndex extends AbstractHollowUniqueKeyIndex<EntityApi, Entity> implements HollowUniqueKeyIndex<Entity> {

    public EntityUniqueKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, false, fieldPaths);
    }

    public EntityUniqueKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "Entity", isListenToDataRefresh, fieldPaths);
    }

    @Override
    public Entity findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if (ordinal == -1)
            return null;
        return api.getEntity(ordinal);
    }

}
