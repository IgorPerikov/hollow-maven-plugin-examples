package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.client.HollowAPIFactory;
import com.netflix.hollow.api.custom.HollowAPI;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.core.read.dataaccess.HollowDataAccess;
import java.util.Collections;
import java.util.Set;

public class EntityApiFactory implements HollowAPIFactory {

    private final Set<String> cachedTypes;

    public EntityApiFactory() {
        this(Collections.<String>emptySet());
    }

    public EntityApiFactory(Set<String> cachedTypes) {
        this.cachedTypes = cachedTypes;
    }

    @Override
    public HollowAPI createAPI(HollowDataAccess dataAccess) {
        return new EntityApi(dataAccess, cachedTypes);
    }

    @Override
    public HollowAPI createAPI(HollowDataAccess dataAccess, HollowAPI previousCycleAPI) {
        return new EntityApi(dataAccess, cachedTypes, Collections.<String, HollowFactory<?>>emptyMap(), (EntityApi) previousCycleAPI);
    }

}