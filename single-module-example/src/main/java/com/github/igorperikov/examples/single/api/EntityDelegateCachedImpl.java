package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;
import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowCachedDelegate;

@SuppressWarnings("all")
public class EntityDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, EntityDelegate {

    private final int intValueOrdinal;
    private final int listOfLongValuesOrdinal;
    private final int stringValueOrdinal;
   private EntityTypeAPI typeAPI;

    public EntityDelegateCachedImpl(EntityTypeAPI typeAPI, int ordinal) {
        this.intValueOrdinal = typeAPI.getIntValueOrdinal(ordinal);
        this.listOfLongValuesOrdinal = typeAPI.getListOfLongValuesOrdinal(ordinal);
        this.stringValueOrdinal = typeAPI.getStringValueOrdinal(ordinal);
        this.typeAPI = typeAPI;
    }

    public int getIntValueOrdinal(int ordinal) {
        return intValueOrdinal;
    }

    public int getListOfLongValuesOrdinal(int ordinal) {
        return listOfLongValuesOrdinal;
    }

    public int getStringValueOrdinal(int ordinal) {
        return stringValueOrdinal;
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