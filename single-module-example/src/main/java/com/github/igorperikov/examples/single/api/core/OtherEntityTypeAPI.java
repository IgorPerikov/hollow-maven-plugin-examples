package com.github.igorperikov.examples.single.api.core;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.netflix.hollow.api.custom.HollowObjectTypeAPI;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.type.StringTypeAPI;

@SuppressWarnings("all")
public class OtherEntityTypeAPI extends HollowObjectTypeAPI {

    private final OtherEntityDelegateLookupImpl delegateLookupImpl;

    public OtherEntityTypeAPI(EntityApi api, HollowObjectTypeDataAccess typeDataAccess) {
        super(api, typeDataAccess, new String[]{
                "id",
                "value"
        });
        this.delegateLookupImpl = new OtherEntityDelegateLookupImpl(this);
    }

    public int getId(int ordinal) {
        if (fieldIndex[0] == -1)
            return missingDataHandler().handleInt("OtherEntity", ordinal, "id");
        return getTypeDataAccess().readInt(ordinal, fieldIndex[0]);
    }

    public Integer getIdBoxed(int ordinal) {
        int i;
        if (fieldIndex[0] == -1) {
            i = missingDataHandler().handleInt("OtherEntity", ordinal, "id");
        } else {
            boxedFieldAccessSampler.recordFieldAccess(fieldIndex[0]);
            i = getTypeDataAccess().readInt(ordinal, fieldIndex[0]);
        }
        if (i == Integer.MIN_VALUE)
            return null;
        return Integer.valueOf(i);
    }


    public int getValueOrdinal(int ordinal) {
        if (fieldIndex[1] == -1)
            return missingDataHandler().handleReferencedOrdinal("OtherEntity", ordinal, "value");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[1]);
    }

    public StringTypeAPI getValueTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public OtherEntityDelegateLookupImpl getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

    @Override
    public EntityApi getAPI() {
        return (EntityApi) api;
    }

}
