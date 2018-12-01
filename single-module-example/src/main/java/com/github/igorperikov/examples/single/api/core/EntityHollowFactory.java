package com.github.igorperikov.examples.single.api.core;

import com.github.igorperikov.examples.single.api.Entity;
import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.core.read.dataaccess.HollowTypeDataAccess;

@SuppressWarnings("all")
public class EntityHollowFactory<T extends Entity> extends HollowFactory<T> {

    @Override
    public T newHollowObject(HollowTypeDataAccess dataAccess, HollowTypeAPI typeAPI, int ordinal) {
        return (T) new Entity(((EntityTypeAPI) typeAPI).getDelegateLookupImpl(), ordinal);
    }

    @Override
    public T newCachedHollowObject(HollowTypeDataAccess dataAccess, HollowTypeAPI typeAPI, int ordinal) {
        return (T) new Entity(new EntityDelegateCachedImpl((EntityTypeAPI) typeAPI, ordinal), ordinal);
    }

}
