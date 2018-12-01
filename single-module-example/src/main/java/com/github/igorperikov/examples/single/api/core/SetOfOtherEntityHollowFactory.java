package com.github.igorperikov.examples.single.api.core;

import com.github.igorperikov.examples.single.api.collections.SetOfOtherEntity;
import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowSetCachedDelegate;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.core.read.dataaccess.HollowTypeDataAccess;

@SuppressWarnings("all")
public class SetOfOtherEntityHollowFactory<T extends SetOfOtherEntity> extends HollowFactory<T> {

    @Override
    public T newHollowObject(HollowTypeDataAccess dataAccess, HollowTypeAPI typeAPI, int ordinal) {
        return (T) new SetOfOtherEntity(((SetOfOtherEntityTypeAPI) typeAPI).getDelegateLookupImpl(), ordinal);
    }

    @Override
    public T newCachedHollowObject(HollowTypeDataAccess dataAccess, HollowTypeAPI typeAPI, int ordinal) {
        return (T) new SetOfOtherEntity(new HollowSetCachedDelegate((SetOfOtherEntityTypeAPI) typeAPI, ordinal), ordinal);
    }

}
