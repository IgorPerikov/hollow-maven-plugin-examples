package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.objects.HollowList;
import com.netflix.hollow.core.schema.HollowListSchema;
import com.netflix.hollow.api.objects.delegate.HollowListDelegate;
import com.netflix.hollow.api.objects.generic.GenericHollowRecordHelper;

@SuppressWarnings("all")
public class ListOfLongHollow extends HollowList<LongHollow> {

    public ListOfLongHollow(HollowListDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    @Override
    public LongHollow instantiateElement(int ordinal) {
        return (LongHollow) api().getLongHollow(ordinal);
    }

    @Override
    public boolean equalsElement(int elementOrdinal, Object testObject) {
        return GenericHollowRecordHelper.equalObject(getSchema().getElementType(), elementOrdinal, testObject);
    }

    public EntityApi api() {
        return typeApi().getAPI();
    }

    public ListOfLongTypeAPI typeApi() {
        return (ListOfLongTypeAPI) delegate.getTypeAPI();
    }

}