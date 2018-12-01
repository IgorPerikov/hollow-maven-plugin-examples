package com.github.igorperikov.examples.single.api.accessor;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.github.igorperikov.examples.single.api.OtherEntity;
import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.data.AbstractHollowDataAccessor;
import com.netflix.hollow.core.index.key.PrimaryKey;
import com.netflix.hollow.core.read.engine.HollowReadStateEngine;

@SuppressWarnings("all")
public class OtherEntityDataAccessor extends AbstractHollowDataAccessor<OtherEntity> {

    public static final String TYPE = "OtherEntity";
    private EntityApi api;

    public OtherEntityDataAccessor(HollowConsumer consumer) {
        super(consumer, TYPE);
        this.api = (EntityApi) consumer.getAPI();
    }

    public OtherEntityDataAccessor(HollowReadStateEngine rStateEngine, EntityApi api) {
        super(rStateEngine, TYPE);
        this.api = api;
    }

    public OtherEntityDataAccessor(HollowReadStateEngine rStateEngine, EntityApi api, String... fieldPaths) {
        super(rStateEngine, TYPE, fieldPaths);
        this.api = api;
    }

    public OtherEntityDataAccessor(HollowReadStateEngine rStateEngine, EntityApi api, PrimaryKey primaryKey) {
        super(rStateEngine, TYPE, primaryKey);
        this.api = api;
    }

    @Override
    public OtherEntity getRecord(int ordinal) {
        return api.getOtherEntity(ordinal);
    }

}
