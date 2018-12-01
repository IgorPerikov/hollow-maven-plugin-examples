package com.github.igorperikov.examples.single.api.core;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.netflix.hollow.api.custom.HollowSetTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowSetLookupDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowSetTypeDataAccess;

@SuppressWarnings("all")
public class SetOfOtherEntityTypeAPI extends HollowSetTypeAPI {

    private final HollowSetLookupDelegate delegateLookupImpl;

    public SetOfOtherEntityTypeAPI(EntityApi api, HollowSetTypeDataAccess dataAccess) {
        super(api, dataAccess);
        this.delegateLookupImpl = new HollowSetLookupDelegate(this);
    }

    public OtherEntityTypeAPI getElementAPI() {
        return getAPI().getOtherEntityTypeAPI();
    }

    public EntityApi getAPI() {
        return (EntityApi) api;
    }

    public HollowSetLookupDelegate getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

}
