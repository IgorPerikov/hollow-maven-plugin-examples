package com.github.igorperikov.examples.single.api.index;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.github.igorperikov.examples.single.api.OtherEntity;
import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class OtherEntityPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<EntityApi, OtherEntity> {

    public OtherEntityPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, false);
    }

    public OtherEntityPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema) consumer.getStateEngine().getNonNullSchema("OtherEntity")).getPrimaryKey().getFieldPaths());
    }

    private OtherEntityPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, false, fieldPaths);
    }

    private OtherEntityPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "OtherEntity", isListenToDataRefresh, fieldPaths);
    }

    public OtherEntity findMatch(int id) {
        int ordinal = idx.getMatchingOrdinal(id);
        if (ordinal == -1)
            return null;
        return api.getOtherEntity(ordinal);
    }

}
