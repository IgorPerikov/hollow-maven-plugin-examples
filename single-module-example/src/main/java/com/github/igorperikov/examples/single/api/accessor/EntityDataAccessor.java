package com.github.igorperikov.examples.single.api.accessor;

import com.github.igorperikov.examples.single.api.Entity;
import com.github.igorperikov.examples.single.api.EntityApi;
import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.data.AbstractHollowDataAccessor;
import com.netflix.hollow.core.index.key.PrimaryKey;
import com.netflix.hollow.core.read.engine.HollowReadStateEngine;

@SuppressWarnings("all")
public class EntityDataAccessor extends AbstractHollowDataAccessor<Entity> {

    public static final String TYPE = "Entity";
    private EntityApi api;

    public EntityDataAccessor(HollowConsumer consumer) {
        super(consumer, TYPE);
        this.api = (EntityApi) consumer.getAPI();
    }

    public EntityDataAccessor(HollowReadStateEngine rStateEngine, EntityApi api) {
        super(rStateEngine, TYPE);
        this.api = api;
    }

    public EntityDataAccessor(HollowReadStateEngine rStateEngine, EntityApi api, String... fieldPaths) {
        super(rStateEngine, TYPE, fieldPaths);
        this.api = api;
    }

    public EntityDataAccessor(HollowReadStateEngine rStateEngine, EntityApi api, PrimaryKey primaryKey) {
        super(rStateEngine, TYPE, primaryKey);
        this.api = api;
    }

    @Override
    public Entity getRecord(int ordinal) {
        return api.getEntity(ordinal);
    }

}
