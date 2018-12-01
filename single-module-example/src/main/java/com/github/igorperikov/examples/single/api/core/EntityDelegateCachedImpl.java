package com.github.igorperikov.examples.single.api.core;

import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowCachedDelegate;
import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class EntityDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, EntityDelegate {

    private final Integer id;
    private final int idOrdinal;
    private final int listOfLongValuesOrdinal;
    private final String stringValue;
    private final int stringValueOrdinal;
    private final Integer primitive;
    private final int setOfOtherEntitiesOrdinal;
    private EntityTypeAPI typeAPI;

    public EntityDelegateCachedImpl(EntityTypeAPI typeAPI, int ordinal) {
        this.idOrdinal = typeAPI.getIdOrdinal(ordinal);
        int idTempOrdinal = idOrdinal;
        this.id = idTempOrdinal == -1 ? null : typeAPI.getAPI().getIntegerTypeAPI().getValue(idTempOrdinal);
        this.listOfLongValuesOrdinal = typeAPI.getListOfLongValuesOrdinal(ordinal);
        this.stringValueOrdinal = typeAPI.getStringValueOrdinal(ordinal);
        int stringValueTempOrdinal = stringValueOrdinal;
        this.stringValue = stringValueTempOrdinal == -1 ? null : typeAPI.getAPI().getStringTypeAPI().getValue(stringValueTempOrdinal);
        this.primitive = typeAPI.getPrimitiveBoxed(ordinal);
        this.setOfOtherEntitiesOrdinal = typeAPI.getSetOfOtherEntitiesOrdinal(ordinal);
        this.typeAPI = typeAPI;
    }

    public int getId(int ordinal) {
        if (id == null)
            return Integer.MIN_VALUE;
        return id.intValue();
    }

    public Integer getIdBoxed(int ordinal) {
        return id;
    }

    public int getIdOrdinal(int ordinal) {
        return idOrdinal;
    }

    public int getListOfLongValuesOrdinal(int ordinal) {
        return listOfLongValuesOrdinal;
    }

    public String getStringValue(int ordinal) {
        return stringValue;
    }

    public boolean isStringValueEqual(int ordinal, String testValue) {
        if (testValue == null)
            return stringValue == null;
        return testValue.equals(stringValue);
    }

    public int getStringValueOrdinal(int ordinal) {
        return stringValueOrdinal;
    }

    public int getPrimitive(int ordinal) {
        if (primitive == null)
            return Integer.MIN_VALUE;
        return primitive.intValue();
    }

    public Integer getPrimitiveBoxed(int ordinal) {
        return primitive;
    }

    public int getSetOfOtherEntitiesOrdinal(int ordinal) {
        return setOfOtherEntitiesOrdinal;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

    public EntityTypeAPI getTypeAPI() {
        return typeAPI;
    }

    public void updateTypeAPI(HollowTypeAPI typeAPI) {
        this.typeAPI = (EntityTypeAPI) typeAPI;
    }

}
