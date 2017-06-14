package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.objects.HollowObject;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class IntegerHollow extends HollowObject {

    public IntegerHollow(IntegerDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    public int _getValue() {
        return delegate().getValue(ordinal);
    }

    public Integer _getValueBoxed() {
        return delegate().getValueBoxed(ordinal);
    }

    public EntityApi api() {
        return typeApi().getAPI();
    }

    public IntegerTypeAPI typeApi() {
        return delegate().getTypeAPI();
    }

    protected IntegerDelegate delegate() {
        return (IntegerDelegate)delegate;
    }

}