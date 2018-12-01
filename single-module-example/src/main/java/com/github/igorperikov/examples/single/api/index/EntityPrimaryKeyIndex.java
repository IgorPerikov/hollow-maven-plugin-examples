package com.github.igorperikov.examples.single.api.index;

import com.github.igorperikov.examples.single.api.Entity;
import com.github.igorperikov.examples.single.api.EntityApi;
import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class EntityPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<EntityApi, Entity> {

    public EntityPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, false);
    }

    public EntityPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema) consumer.getStateEngine().getNonNullSchema("Entity")).getPrimaryKey().getFieldPaths());
    }

    private EntityPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, false, fieldPaths);
    }

    private EntityPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "Entity", isListenToDataRefresh, fieldPaths);
    }

    public Entity findMatch(int id) {
        int ordinal = idx.getMatchingOrdinal(id);
        if (ordinal == -1)
            return null;
        return api.getEntity(ordinal);
    }

}
