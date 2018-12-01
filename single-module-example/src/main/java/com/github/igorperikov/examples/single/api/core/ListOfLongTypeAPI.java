package com.github.igorperikov.examples.single.api.core;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.netflix.hollow.api.custom.HollowListTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowListLookupDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowListTypeDataAccess;
import com.netflix.hollow.core.type.LongTypeAPI;

@SuppressWarnings("all")
public class ListOfLongTypeAPI extends HollowListTypeAPI {

    private final HollowListLookupDelegate delegateLookupImpl;

    public ListOfLongTypeAPI(EntityApi api, HollowListTypeDataAccess dataAccess) {
        super(api, dataAccess);
        this.delegateLookupImpl = new HollowListLookupDelegate(this);
    }

    public LongTypeAPI getElementAPI() {
        return getAPI().getLongTypeAPI();
    }

    public HollowListLookupDelegate getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

    public EntityApi getAPI() {
        return (EntityApi)api;
    }

}
