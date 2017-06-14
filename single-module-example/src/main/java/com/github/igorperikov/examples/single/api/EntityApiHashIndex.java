package com.github.igorperikov.examples.single.api;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.custom.HollowAPI;
import com.netflix.hollow.core.index.HollowHashIndex;
import com.netflix.hollow.core.index.HollowHashIndexResult;
import com.netflix.hollow.core.read.engine.HollowReadStateEngine;
import com.netflix.hollow.core.read.iterator.HollowOrdinalIterator;
import java.util.Collections;
import java.lang.Iterable;
import java.util.Iterator;

public class EntityApiHashIndex implements HollowConsumer.RefreshListener {

    private HollowHashIndex idx;
    private EntityApi api;
    private final String queryType;    private final String selectFieldPath;
    private final String matchFieldPaths[];

    public EntityApiHashIndex(HollowConsumer consumer, String queryType, String selectFieldPath, String... matchFieldPaths) {
        this.queryType = queryType;        this.selectFieldPath = selectFieldPath;
        this.matchFieldPaths = matchFieldPaths;
        consumer.getRefreshLock().lock();
        try {
            this.api = (EntityApi)consumer.getAPI();
            this.idx = new HollowHashIndex(consumer.getStateEngine(), queryType, selectFieldPath, matchFieldPaths);
            consumer.addRefreshListener(this);
        } catch(ClassCastException cce) {
            throw new ClassCastException("The HollowConsumer provided was not created with the EntityApi generated API class.");
        } finally {
            consumer.getRefreshLock().unlock();
        }
    }

    public Iterable<IntegerHollow> findIntegerMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if(matches == null)
            return Collections.emptySet();

        final HollowOrdinalIterator iter = matches.iterator();

        return new Iterable<IntegerHollow>() {
            public Iterator<IntegerHollow> iterator() {
                return new Iterator<IntegerHollow>() {

                    private int next = iter.next();

                    public boolean hasNext() {
                        return next != HollowOrdinalIterator.NO_MORE_ORDINALS;
                    }

                    public IntegerHollow next() {
                        IntegerHollow obj = api.getIntegerHollow(next);
                        next = iter.next();
                        return obj;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<LongHollow> findLongMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if(matches == null)
            return Collections.emptySet();

        final HollowOrdinalIterator iter = matches.iterator();

        return new Iterable<LongHollow>() {
            public Iterator<LongHollow> iterator() {
                return new Iterator<LongHollow>() {

                    private int next = iter.next();

                    public boolean hasNext() {
                        return next != HollowOrdinalIterator.NO_MORE_ORDINALS;
                    }

                    public LongHollow next() {
                        LongHollow obj = api.getLongHollow(next);
                        next = iter.next();
                        return obj;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<ListOfLongHollow> findListOfLongMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if(matches == null)
            return Collections.emptySet();

        final HollowOrdinalIterator iter = matches.iterator();

        return new Iterable<ListOfLongHollow>() {
            public Iterator<ListOfLongHollow> iterator() {
                return new Iterator<ListOfLongHollow>() {

                    private int next = iter.next();

                    public boolean hasNext() {
                        return next != HollowOrdinalIterator.NO_MORE_ORDINALS;
                    }

                    public ListOfLongHollow next() {
                        ListOfLongHollow obj = api.getListOfLongHollow(next);
                        next = iter.next();
                        return obj;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<StringHollow> findStringMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if(matches == null)
            return Collections.emptySet();

        final HollowOrdinalIterator iter = matches.iterator();

        return new Iterable<StringHollow>() {
            public Iterator<StringHollow> iterator() {
                return new Iterator<StringHollow>() {

                    private int next = iter.next();

                    public boolean hasNext() {
                        return next != HollowOrdinalIterator.NO_MORE_ORDINALS;
                    }

                    public StringHollow next() {
                        StringHollow obj = api.getStringHollow(next);
                        next = iter.next();
                        return obj;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<EntityHollow> findEntityMatches(Object... keys) {
        HollowHashIndexResult matches = idx.findMatches(keys);
        if(matches == null)
            return Collections.emptySet();

        final HollowOrdinalIterator iter = matches.iterator();

        return new Iterable<EntityHollow>() {
            public Iterator<EntityHollow> iterator() {
                return new Iterator<EntityHollow>() {

                    private int next = iter.next();

                    public boolean hasNext() {
                        return next != HollowOrdinalIterator.NO_MORE_ORDINALS;
                    }

                    public EntityHollow next() {
                        EntityHollow obj = api.getEntityHollow(next);
                        next = iter.next();
                        return obj;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    @Override public void deltaUpdateOccurred(HollowAPI api, HollowReadStateEngine stateEngine, long version) throws Exception {
        reindex(stateEngine, api);
    }

    @Override public void snapshotUpdateOccurred(HollowAPI api, HollowReadStateEngine stateEngine, long version) throws Exception {
        reindex(stateEngine, api);
    }

    private void reindex(HollowReadStateEngine stateEngine, HollowAPI api) {
        this.idx = new HollowHashIndex(stateEngine, queryType, selectFieldPath, matchFieldPaths);
        this.api = (EntityApi) api;
    }

    @Override public void refreshStarted(long currentVersion, long requestedVersion) { }
    @Override public void blobLoaded(HollowConsumer.Blob transition) { }
    @Override public void refreshSuccessful(long beforeVersion, long afterVersion, long requestedVersion) { }
    @Override public void refreshFailed(long beforeVersion, long afterVersion, long requestedVersion, Throwable failureCause) { }

}