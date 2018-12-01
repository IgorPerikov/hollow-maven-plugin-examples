package com.github.igorperikov.examples.single.api.core;

import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowCachedDelegate;
import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class OtherEntityDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, OtherEntityDelegate {

    private final Integer id;
    private final String value;
    private final int valueOrdinal;
    private OtherEntityTypeAPI typeAPI;

    public OtherEntityDelegateCachedImpl(OtherEntityTypeAPI typeAPI, int ordinal) {
        this.id = typeAPI.getIdBoxed(ordinal);
        this.valueOrdinal = typeAPI.getValueOrdinal(ordinal);
        int valueTempOrdinal = valueOrdinal;
        this.value = valueTempOrdinal == -1 ? null : typeAPI.getAPI().getStringTypeAPI().getValue(valueTempOrdinal);
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

    public String getValue(int ordinal) {
        return value;
    }

    public boolean isValueEqual(int ordinal, String testValue) {
        if (testValue == null)
            return value == null;
        return testValue.equals(value);
    }

    public int getValueOrdinal(int ordinal) {
        return valueOrdinal;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

    public OtherEntityTypeAPI getTypeAPI() {
        return typeAPI;
    }

    public void updateTypeAPI(HollowTypeAPI typeAPI) {
        this.typeAPI = (OtherEntityTypeAPI) typeAPI;
    }

}
