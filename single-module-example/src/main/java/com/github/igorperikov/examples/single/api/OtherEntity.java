package com.github.igorperikov.examples.single.api;

import com.github.igorperikov.examples.single.api.core.OtherEntityDelegate;
import com.github.igorperikov.examples.single.api.core.OtherEntityTypeAPI;
import com.netflix.hollow.api.objects.HollowObject;
import com.netflix.hollow.core.type.HString;
import com.netflix.hollow.tools.stringifier.HollowRecordStringifier;

@SuppressWarnings("all")
public class OtherEntity extends HollowObject {

    public OtherEntity(OtherEntityDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    public int getId() {
        return delegate().getId(ordinal);
    }


    public String getValue() {
        return delegate().getValue(ordinal);
    }

    public boolean isValueEqual(String testValue) {
        return delegate().isValueEqual(ordinal, testValue);
    }

    public HString getValueHollowReference() {
        int refOrdinal = delegate().getValueOrdinal(ordinal);
        if (refOrdinal == -1)
            return null;
        return api().getHString(refOrdinal);
    }

    public EntityApi api() {
        return typeApi().getAPI();
    }

    public OtherEntityTypeAPI typeApi() {
        return delegate().getTypeAPI();
    }

    protected OtherEntityDelegate delegate() {
        return (OtherEntityDelegate) delegate;
    }

    public String toString() {
        return new HollowRecordStringifier().stringify(this);
    }

}
