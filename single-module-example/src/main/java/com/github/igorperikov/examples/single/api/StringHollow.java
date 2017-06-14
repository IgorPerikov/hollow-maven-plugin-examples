package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.objects.HollowObject;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class StringHollow extends HollowObject {

    public StringHollow(StringDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    public String _getValue() {
        return delegate().getValue(ordinal);
    }

    public boolean _isValueEqual(String testValue) {
        return delegate().isValueEqual(ordinal, testValue);
    }

    public EntityApi api() {
        return typeApi().getAPI();
    }

    public StringTypeAPI typeApi() {
        return delegate().getTypeAPI();
    }

    protected StringDelegate delegate() {
        return (StringDelegate)delegate;
    }

}