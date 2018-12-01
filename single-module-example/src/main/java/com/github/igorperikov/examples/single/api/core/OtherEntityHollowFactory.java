package com.github.igorperikov.examples.single.api.core;

import com.github.igorperikov.examples.single.api.OtherEntity;
import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.core.read.dataaccess.HollowTypeDataAccess;

@SuppressWarnings("all")
public class OtherEntityHollowFactory<T extends OtherEntity> extends HollowFactory<T> {

    @Override
    public T newHollowObject(HollowTypeDataAccess dataAccess, HollowTypeAPI typeAPI, int ordinal) {
        return (T) new OtherEntity(((OtherEntityTypeAPI) typeAPI).getDelegateLookupImpl(), ordinal);
    }

    @Override
    public T newCachedHollowObject(HollowTypeDataAccess dataAccess, HollowTypeAPI typeAPI, int ordinal) {
        return (T) new OtherEntity(new OtherEntityDelegateCachedImpl((OtherEntityTypeAPI) typeAPI, ordinal), ordinal);
    }

}
