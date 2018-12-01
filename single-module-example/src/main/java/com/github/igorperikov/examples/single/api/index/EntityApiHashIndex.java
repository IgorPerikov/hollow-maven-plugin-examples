package com.github.igorperikov.examples.single.api.index;

import com.github.igorperikov.examples.single.api.Entity;
import com.github.igorperikov.examples.single.api.EntityApi;
import com.github.igorperikov.examples.single.api.OtherEntity;
import com.github.igorperikov.examples.single.api.collections.ListOfLong;
import com.github.igorperikov.examples.single.api.collections.SetOfOtherEntity;
import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.data.AbstractHollowOrdinalIterable;
import com.netflix.hollow.api.consumer.index.AbstractHollowHashIndex;
import com.netflix.hollow.core.index.HollowHashIndexResult;
import com.netflix.hollow.core.type.HInteger;
import com.netflix.hollow.core.type.HLong;
import com.netflix.hollow.core.type.HString;

import java.util.Collections;


@SuppressWarnings("all")
public class EntityApiHashIndex extends AbstractHollowHashIndex<EntityApi> {

    public EntityApiHashIndex(HollowConsumer consumer, String queryType, String selectFieldPath, String... matchFieldPaths) {
        super(consumer, false, queryType, selectFieldPath, matchFieldPaths);
    }

    public EntityApiHashIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String queryType, String selectFieldPath, String... matchFieldPaths) {
        super(consumer, isListenToDataRefresh, queryType, selectFieldPath, matchFieldPaths);
    }

    public Iterable<HInteger> findIntegerMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if (matches == null) return Collections.emptySet();

        return new AbstractHollowOrdinalIterable<HInteger>(matches.iterator()) {
            public HInteger getData(int ordinal) {
                return api.getHInteger(ordinal);
            }
        };
    }

    public Iterable<HLong> findLongMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if (matches == null) return Collections.emptySet();

        return new AbstractHollowOrdinalIterable<HLong>(matches.iterator()) {
            public HLong getData(int ordinal) {
                return api.getHLong(ordinal);
            }
        };
    }

    public Iterable<ListOfLong> findListOfLongMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if (matches == null) return Collections.emptySet();

        return new AbstractHollowOrdinalIterable<ListOfLong>(matches.iterator()) {
            public ListOfLong getData(int ordinal) {
                return api.getListOfLong(ordinal);
            }
        };
    }

    public Iterable<HString> findStringMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if (matches == null) return Collections.emptySet();

        return new AbstractHollowOrdinalIterable<HString>(matches.iterator()) {
            public HString getData(int ordinal) {
                return api.getHString(ordinal);
            }
        };
    }

    public Iterable<OtherEntity> findOtherEntityMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if (matches == null) return Collections.emptySet();

        return new AbstractHollowOrdinalIterable<OtherEntity>(matches.iterator()) {
            public OtherEntity getData(int ordinal) {
                return api.getOtherEntity(ordinal);
            }
        };
    }

    public Iterable<SetOfOtherEntity> findSetOfOtherEntityMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if (matches == null) return Collections.emptySet();

        return new AbstractHollowOrdinalIterable<SetOfOtherEntity>(matches.iterator()) {
            public SetOfOtherEntity getData(int ordinal) {
                return api.getSetOfOtherEntity(ordinal);
            }
        };
    }

    public Iterable<Entity> findEntityMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if (matches == null) return Collections.emptySet();

        return new AbstractHollowOrdinalIterable<Entity>(matches.iterator()) {
            public Entity getData(int ordinal) {
                return api.getEntity(ordinal);
            }
        };
    }

}
