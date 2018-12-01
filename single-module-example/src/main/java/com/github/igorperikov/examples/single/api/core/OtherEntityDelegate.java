package com.github.igorperikov.examples.single.api.core;

import com.netflix.hollow.api.objects.delegate.HollowObjectDelegate;


@SuppressWarnings("all")
public interface OtherEntityDelegate extends HollowObjectDelegate {

    public int getId(int ordinal);

    public Integer getIdBoxed(int ordinal);

    public String getValue(int ordinal);

    public boolean isValueEqual(int ordinal, String testValue);

    public int getValueOrdinal(int ordinal);

    public OtherEntityTypeAPI getTypeAPI();

}
