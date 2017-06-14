package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.objects.HollowObject;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class EntityHollow extends HollowObject {

    public EntityHollow(EntityDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    public IntegerHollow _getIntValue() {
        int refOrdinal = delegate().getIntValueOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getIntegerHollow(refOrdinal);
    }

    public ListOfLongHollow _getListOfLongValues() {
        int refOrdinal = delegate().getListOfLongValuesOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getListOfLongHollow(refOrdinal);
    }

    public StringHollow _getStringValue() {
        int refOrdinal = delegate().getStringValueOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getStringHollow(refOrdinal);
    }

    public EntityApi api() {
        return typeApi().getAPI();
    }

    public EntityTypeAPI typeApi() {
        return delegate().getTypeAPI();
    }

    protected EntityDelegate delegate() {
        return (EntityDelegate)delegate;
    }

}