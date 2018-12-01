package com.github.igorperikov.examples.single.api.core;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class EntityDelegateLookupImpl extends HollowObjectAbstractDelegate implements EntityDelegate {

    private final EntityTypeAPI typeAPI;

    public EntityDelegateLookupImpl(EntityTypeAPI typeAPI) {
        this.typeAPI = typeAPI;
    }

    public int getId(int ordinal) {
        ordinal = typeAPI.getIdOrdinal(ordinal);
        return ordinal == -1 ? Integer.MIN_VALUE : typeAPI.getAPI().getIntegerTypeAPI().getValue(ordinal);
    }

    public Integer getIdBoxed(int ordinal) {
        ordinal = typeAPI.getIdOrdinal(ordinal);
        return ordinal == -1 ? null : typeAPI.getAPI().getIntegerTypeAPI().getValueBoxed(ordinal);
    }

    public int getIdOrdinal(int ordinal) {
        return typeAPI.getIdOrdinal(ordinal);
    }

    public int getListOfLongValuesOrdinal(int ordinal) {
        return typeAPI.getListOfLongValuesOrdinal(ordinal);
    }

    public String getStringValue(int ordinal) {
        ordinal = typeAPI.getStringValueOrdinal(ordinal);
        return ordinal == -1 ? null : typeAPI.getAPI().getStringTypeAPI().getValue(ordinal);
    }

    public boolean isStringValueEqual(int ordinal, String testValue) {
        ordinal = typeAPI.getStringValueOrdinal(ordinal);
        return ordinal == -1 ? testValue == null : typeAPI.getAPI().getStringTypeAPI().isValueEqual(ordinal, testValue);
    }

    public int getStringValueOrdinal(int ordinal) {
        return typeAPI.getStringValueOrdinal(ordinal);
    }

    public int getPrimitive(int ordinal) {
        return typeAPI.getPrimitive(ordinal);
    }

    public Integer getPrimitiveBoxed(int ordinal) {
        return typeAPI.getPrimitiveBoxed(ordinal);
    }

    public int getSetOfOtherEntitiesOrdinal(int ordinal) {
        return typeAPI.getSetOfOtherEntitiesOrdinal(ordinal);
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
