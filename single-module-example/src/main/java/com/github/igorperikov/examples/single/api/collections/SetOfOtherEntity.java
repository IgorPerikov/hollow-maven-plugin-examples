package com.github.igorperikov.examples.single.api.collections;

import com.github.igorperikov.examples.single.api.EntityApi;
import com.github.igorperikov.examples.single.api.OtherEntity;
import com.github.igorperikov.examples.single.api.core.SetOfOtherEntityTypeAPI;
import com.netflix.hollow.api.objects.HollowSet;
import com.netflix.hollow.api.objects.delegate.HollowSetDelegate;
import com.netflix.hollow.api.objects.generic.GenericHollowRecordHelper;

@SuppressWarnings("all")
public class SetOfOtherEntity extends HollowSet<OtherEntity> {

    public SetOfOtherEntity(HollowSetDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    @Override
    public OtherEntity instantiateElement(int ordinal) {
        return (OtherEntity) api().getOtherEntity(ordinal);
    }

    @Override
    public boolean equalsElement(int elementOrdinal, Object testObject) {
        return GenericHollowRecordHelper.equalObject(getSchema().getElementType(), elementOrdinal, testObject);
    }

    public EntityApi api() {
        return typeApi().getAPI();
    }

    public SetOfOtherEntityTypeAPI typeApi() {
        return (SetOfOtherEntityTypeAPI) delegate.getTypeAPI();
    }

}
