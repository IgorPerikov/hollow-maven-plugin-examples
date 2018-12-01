package com.github.igorperikov.examples.single.api.index;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.github.igorperikov.examples.single.api.OtherEntity;
import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.api.consumer.index.HollowUniqueKeyIndex;

@SuppressWarnings("all")
public class OtherEntityUniqueKeyIndex extends AbstractHollowUniqueKeyIndex<EntityApi, OtherEntity> implements HollowUniqueKeyIndex<OtherEntity> {

    public OtherEntityUniqueKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, false, fieldPaths);
    }

    public OtherEntityUniqueKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "OtherEntity", isListenToDataRefresh, fieldPaths);
    }

    @Override
    public OtherEntity findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if (ordinal == -1)
            return null;
        return api.getOtherEntity(ordinal);
    }

}
