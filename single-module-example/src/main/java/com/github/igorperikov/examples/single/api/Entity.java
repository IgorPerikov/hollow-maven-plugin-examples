package com.github.igorperikov.examples.single.api;

import com.github.igorperikov.examples.single.api.collections.ListOfLong;
import com.github.igorperikov.examples.single.api.collections.SetOfOtherEntity;
import com.github.igorperikov.examples.single.api.core.EntityDelegate;
import com.github.igorperikov.examples.single.api.core.EntityTypeAPI;
import com.netflix.hollow.api.objects.HollowObject;
import com.netflix.hollow.core.type.HInteger;
import com.netflix.hollow.core.type.HString;
import com.netflix.hollow.tools.stringifier.HollowRecordStringifier;

@SuppressWarnings("all")
public class Entity extends HollowObject {

    public Entity(EntityDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    public Integer getId() {
        return delegate().getIdBoxed(ordinal);
    }

    public HInteger getIdHollowReference() {
        int refOrdinal = delegate().getIdOrdinal(ordinal);
        if (refOrdinal == -1)
            return null;
        return api().getHInteger(refOrdinal);
    }

    public ListOfLong getListOfLongValues() {
        int refOrdinal = delegate().getListOfLongValuesOrdinal(ordinal);
        if (refOrdinal == -1)
            return null;
        return api().getListOfLong(refOrdinal);
    }

    public String getStringValue() {
        return delegate().getStringValue(ordinal);
    }

    public boolean isStringValueEqual(String testValue) {
        return delegate().isStringValueEqual(ordinal, testValue);
    }

    public HString getStringValueHollowReference() {
        int refOrdinal = delegate().getStringValueOrdinal(ordinal);
        if (refOrdinal == -1)
            return null;
        return api().getHString(refOrdinal);
    }

    public int getPrimitive() {
        return delegate().getPrimitive(ordinal);
    }


    public SetOfOtherEntity getSetOfOtherEntities() {
        int refOrdinal = delegate().getSetOfOtherEntitiesOrdinal(ordinal);
        if (refOrdinal == -1)
            return null;
        return api().getSetOfOtherEntity(refOrdinal);
    }

    public EntityApi api() {
        return typeApi().getAPI();
    }

    public EntityTypeAPI typeApi() {
        return delegate().getTypeAPI();
    }

    protected EntityDelegate delegate() {
        return (EntityDelegate) delegate;
    }

    public String toString() {
        return new HollowRecordStringifier().stringify(this);
    }

}
