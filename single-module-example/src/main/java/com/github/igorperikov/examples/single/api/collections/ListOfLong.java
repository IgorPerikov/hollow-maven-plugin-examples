package com.github.igorperikov.examples.single.api.collections;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.github.igorperikov.examples.single.api.core.ListOfLongTypeAPI;
import com.netflix.hollow.api.objects.HollowList;
import com.netflix.hollow.api.objects.delegate.HollowListDelegate;
import com.netflix.hollow.api.objects.generic.GenericHollowRecordHelper;
import com.netflix.hollow.core.type.HLong;

@SuppressWarnings("all")
public class ListOfLong extends HollowList<HLong> {

    public ListOfLong(HollowListDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    @Override
    public HLong instantiateElement(int ordinal) {
        return (HLong) api().getHLong(ordinal);
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
