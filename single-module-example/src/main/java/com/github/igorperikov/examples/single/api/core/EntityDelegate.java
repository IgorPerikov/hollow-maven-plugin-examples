package com.github.igorperikov.examples.single.api.core;

import com.netflix.hollow.api.objects.delegate.HollowObjectDelegate;


@SuppressWarnings("all")
public interface EntityDelegate extends HollowObjectDelegate {

    public int getId(int ordinal);

    public Integer getIdBoxed(int ordinal);

    public int getIdOrdinal(int ordinal);

    public int getListOfLongValuesOrdinal(int ordinal);

    public String getStringValue(int ordinal);

    public boolean isStringValueEqual(int ordinal, String testValue);

    public int getStringValueOrdinal(int ordinal);

    public int getPrimitive(int ordinal);

    public Integer getPrimitiveBoxed(int ordinal);

    public int getSetOfOtherEntitiesOrdinal(int ordinal);

    public EntityTypeAPI getTypeAPI();

}
