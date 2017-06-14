package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class EntityDelegateLookupImpl extends HollowObjectAbstractDelegate implements EntityDelegate {

    private final EntityTypeAPI typeAPI;

    public EntityDelegateLookupImpl(EntityTypeAPI typeAPI) {
        this.typeAPI = typeAPI;
    }

    public int getIntValueOrdinal(int ordinal) {
        return typeAPI.getIntValueOrdinal(ordinal);
    }

    public int getListOfLongValuesOrdinal(int ordinal) {
        return typeAPI.getListOfLongValuesOrdinal(ordinal);
    }

    public int getStringValueOrdinal(int ordinal) {
        return typeAPI.getStringValueOrdinal(ordinal);
    }

    public EntityTypeAPI getTypeAPI() {
        return typeAPI;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

}