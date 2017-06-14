package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.objects.delegate.HollowObjectDelegate;


@SuppressWarnings("all")
public interface EntityDelegate extends HollowObjectDelegate {

    public int getIntValueOrdinal(int ordinal);

    public int getListOfLongValuesOrdinal(int ordinal);

    public int getStringValueOrdinal(int ordinal);

    public EntityTypeAPI getTypeAPI();

}