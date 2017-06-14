package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.custom.HollowObjectTypeAPI;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;

@SuppressWarnings("all")
public class EntityTypeAPI extends HollowObjectTypeAPI {

    private final EntityDelegateLookupImpl delegateLookupImpl;

    EntityTypeAPI(EntityApi api, HollowObjectTypeDataAccess typeDataAccess) {
        super(api, typeDataAccess, new String[] {
            "intValue",
            "listOfLongValues",
            "stringValue"
        });
        this.delegateLookupImpl = new EntityDelegateLookupImpl(this);
    }

    public int getIntValueOrdinal(int ordinal) {
        if(fieldIndex[0] == -1)
            return missingDataHandler().handleReferencedOrdinal("Entity", ordinal, "intValue");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[0]);
    }

    public IntegerTypeAPI getIntValueTypeAPI() {
        return getAPI().getIntegerTypeAPI();
    }

    public int getListOfLongValuesOrdinal(int ordinal) {
        if(fieldIndex[1] == -1)
            return missingDataHandler().handleReferencedOrdinal("Entity", ordinal, "listOfLongValues");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[1]);
    }

    public ListOfLongTypeAPI getListOfLongValuesTypeAPI() {
        return getAPI().getListOfLongTypeAPI();
    }

    public int getStringValueOrdinal(int ordinal) {
        if(fieldIndex[2] == -1)
            return missingDataHandler().handleReferencedOrdinal("Entity", ordinal, "stringValue");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[2]);
    }

    public StringTypeAPI getStringValueTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public EntityDelegateLookupImpl getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

    @Override
    public EntityApi getAPI() {
        return (EntityApi) api;
    }

}