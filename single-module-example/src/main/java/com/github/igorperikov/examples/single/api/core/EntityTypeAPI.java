package com.github.igorperikov.examples.single.api.core;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.netflix.hollow.api.custom.HollowObjectTypeAPI;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.type.IntegerTypeAPI;
import com.netflix.hollow.core.type.StringTypeAPI;

@SuppressWarnings("all")
public class EntityTypeAPI extends HollowObjectTypeAPI {

    private final EntityDelegateLookupImpl delegateLookupImpl;

    public EntityTypeAPI(EntityApi api, HollowObjectTypeDataAccess typeDataAccess) {
        super(api, typeDataAccess, new String[]{
                "id",
                "listOfLongValues",
                "stringValue",
                "primitive",
                "setOfOtherEntities"
        });
        this.delegateLookupImpl = new EntityDelegateLookupImpl(this);
    }

    public int getIdOrdinal(int ordinal) {
        if (fieldIndex[0] == -1)
            return missingDataHandler().handleReferencedOrdinal("Entity", ordinal, "id");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[0]);
    }

    public IntegerTypeAPI getIdTypeAPI() {
        return getAPI().getIntegerTypeAPI();
    }

    public int getListOfLongValuesOrdinal(int ordinal) {
        if (fieldIndex[1] == -1)
            return missingDataHandler().handleReferencedOrdinal("Entity", ordinal, "listOfLongValues");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[1]);
    }

    public ListOfLongTypeAPI getListOfLongValuesTypeAPI() {
        return getAPI().getListOfLongTypeAPI();
    }

    public int getStringValueOrdinal(int ordinal) {
        if (fieldIndex[2] == -1)
            return missingDataHandler().handleReferencedOrdinal("Entity", ordinal, "stringValue");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[2]);
    }

    public StringTypeAPI getStringValueTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getPrimitive(int ordinal) {
        if (fieldIndex[3] == -1)
            return missingDataHandler().handleInt("Entity", ordinal, "primitive");
        return getTypeDataAccess().readInt(ordinal, fieldIndex[3]);
    }

    public Integer getPrimitiveBoxed(int ordinal) {
        int i;
        if (fieldIndex[3] == -1) {
            i = missingDataHandler().handleInt("Entity", ordinal, "primitive");
        } else {
            boxedFieldAccessSampler.recordFieldAccess(fieldIndex[3]);
            i = getTypeDataAccess().readInt(ordinal, fieldIndex[3]);
        }
        if (i == Integer.MIN_VALUE)
            return null;
        return Integer.valueOf(i);
    }


    public int getSetOfOtherEntitiesOrdinal(int ordinal) {
        if (fieldIndex[4] == -1)
            return missingDataHandler().handleReferencedOrdinal("Entity", ordinal, "setOfOtherEntities");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[4]);
    }

    public SetOfOtherEntityTypeAPI getSetOfOtherEntitiesTypeAPI() {
        return getAPI().getSetOfOtherEntityTypeAPI();
    }

    public EntityDelegateLookupImpl getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

    @Override
    public EntityApi getAPI() {
        return (EntityApi) api;
    }

}
