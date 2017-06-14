package com.github.igorperikov.examples.single.api;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Map;
import com.netflix.hollow.api.custom.HollowAPI;
import com.netflix.hollow.core.read.dataaccess.HollowDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowListTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowSetTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowMapTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowObjectMissingDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowListMissingDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowSetMissingDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowMapMissingDataAccess;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.api.objects.provider.HollowObjectProvider;
import com.netflix.hollow.api.objects.provider.HollowObjectCacheProvider;
import com.netflix.hollow.api.objects.provider.HollowObjectFactoryProvider;
import com.netflix.hollow.api.sampling.HollowObjectCreationSampler;
import com.netflix.hollow.api.sampling.HollowSamplingDirector;
import com.netflix.hollow.api.sampling.SampleResult;
import com.netflix.hollow.core.util.AllHollowRecordCollection;

@SuppressWarnings("all")
public class EntityApi extends HollowAPI {

    private final HollowObjectCreationSampler objectCreationSampler;

    private final IntegerTypeAPI integerTypeAPI;
    private final LongTypeAPI longTypeAPI;
    private final ListOfLongTypeAPI listOfLongTypeAPI;
    private final StringTypeAPI stringTypeAPI;
    private final EntityTypeAPI entityTypeAPI;

    private final HollowObjectProvider integerProvider;
    private final HollowObjectProvider longProvider;
    private final HollowObjectProvider listOfLongProvider;
    private final HollowObjectProvider stringProvider;
    private final HollowObjectProvider entityProvider;

    public EntityApi(HollowDataAccess dataAccess) {
        this(dataAccess, Collections.<String>emptySet());
    }

    public EntityApi(HollowDataAccess dataAccess, Set<String> cachedTypes) {
        this(dataAccess, cachedTypes, Collections.<String, HollowFactory<?>>emptyMap());
    }

    public EntityApi(HollowDataAccess dataAccess, Set<String> cachedTypes, Map<String, HollowFactory<?>> factoryOverrides) {
        this(dataAccess, cachedTypes, factoryOverrides, null);
    }

    public EntityApi(HollowDataAccess dataAccess, Set<String> cachedTypes, Map<String, HollowFactory<?>> factoryOverrides, EntityApi previousCycleAPI) {
        super(dataAccess);
        HollowTypeDataAccess typeDataAccess;
        HollowFactory factory;

        objectCreationSampler = new HollowObjectCreationSampler("Integer","Long","ListOfLong","String","Entity");

        typeDataAccess = dataAccess.getTypeDataAccess("Integer");
        if(typeDataAccess != null) {
            integerTypeAPI = new IntegerTypeAPI(this, (HollowObjectTypeDataAccess)typeDataAccess);
        } else {
            integerTypeAPI = new IntegerTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "Integer"));
        }
        addTypeAPI(integerTypeAPI);
        factory = factoryOverrides.get("Integer");
        if(factory == null)
            factory = new IntegerHollowFactory();
        if(cachedTypes.contains("Integer")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.integerProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.integerProvider;
            integerProvider = new HollowObjectCacheProvider(typeDataAccess, integerTypeAPI, factory, previousCacheProvider);
        } else {
            integerProvider = new HollowObjectFactoryProvider(typeDataAccess, integerTypeAPI, factory);
        }

        typeDataAccess = dataAccess.getTypeDataAccess("Long");
        if(typeDataAccess != null) {
            longTypeAPI = new LongTypeAPI(this, (HollowObjectTypeDataAccess)typeDataAccess);
        } else {
            longTypeAPI = new LongTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "Long"));
        }
        addTypeAPI(longTypeAPI);
        factory = factoryOverrides.get("Long");
        if(factory == null)
            factory = new LongHollowFactory();
        if(cachedTypes.contains("Long")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.longProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.longProvider;
            longProvider = new HollowObjectCacheProvider(typeDataAccess, longTypeAPI, factory, previousCacheProvider);
        } else {
            longProvider = new HollowObjectFactoryProvider(typeDataAccess, longTypeAPI, factory);
        }

        typeDataAccess = dataAccess.getTypeDataAccess("ListOfLong");
        if(typeDataAccess != null) {
            listOfLongTypeAPI = new ListOfLongTypeAPI(this, (HollowListTypeDataAccess)typeDataAccess);
        } else {
            listOfLongTypeAPI = new ListOfLongTypeAPI(this, new HollowListMissingDataAccess(dataAccess, "ListOfLong"));
        }
        addTypeAPI(listOfLongTypeAPI);
        factory = factoryOverrides.get("ListOfLong");
        if(factory == null)
            factory = new ListOfLongHollowFactory();
        if(cachedTypes.contains("ListOfLong")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.listOfLongProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.listOfLongProvider;
            listOfLongProvider = new HollowObjectCacheProvider(typeDataAccess, listOfLongTypeAPI, factory, previousCacheProvider);
        } else {
            listOfLongProvider = new HollowObjectFactoryProvider(typeDataAccess, listOfLongTypeAPI, factory);
        }

        typeDataAccess = dataAccess.getTypeDataAccess("String");
        if(typeDataAccess != null) {
            stringTypeAPI = new StringTypeAPI(this, (HollowObjectTypeDataAccess)typeDataAccess);
        } else {
            stringTypeAPI = new StringTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "String"));
        }
        addTypeAPI(stringTypeAPI);
        factory = factoryOverrides.get("String");
        if(factory == null)
            factory = new StringHollowFactory();
        if(cachedTypes.contains("String")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.stringProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.stringProvider;
            stringProvider = new HollowObjectCacheProvider(typeDataAccess, stringTypeAPI, factory, previousCacheProvider);
        } else {
            stringProvider = new HollowObjectFactoryProvider(typeDataAccess, stringTypeAPI, factory);
        }

        typeDataAccess = dataAccess.getTypeDataAccess("Entity");
        if(typeDataAccess != null) {
            entityTypeAPI = new EntityTypeAPI(this, (HollowObjectTypeDataAccess)typeDataAccess);
        } else {
            entityTypeAPI = new EntityTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "Entity"));
        }
        addTypeAPI(entityTypeAPI);
        factory = factoryOverrides.get("Entity");
        if(factory == null)
            factory = new EntityHollowFactory();
        if(cachedTypes.contains("Entity")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.entityProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.entityProvider;
            entityProvider = new HollowObjectCacheProvider(typeDataAccess, entityTypeAPI, factory, previousCacheProvider);
        } else {
            entityProvider = new HollowObjectFactoryProvider(typeDataAccess, entityTypeAPI, factory);
        }

    }

    public void detachCaches() {
        if(integerProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)integerProvider).detach();
        if(longProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)longProvider).detach();
        if(listOfLongProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)listOfLongProvider).detach();
        if(stringProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)stringProvider).detach();
        if(entityProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)entityProvider).detach();
    }

    public IntegerTypeAPI getIntegerTypeAPI() {
        return integerTypeAPI;
    }
    public LongTypeAPI getLongTypeAPI() {
        return longTypeAPI;
    }
    public ListOfLongTypeAPI getListOfLongTypeAPI() {
        return listOfLongTypeAPI;
    }
    public StringTypeAPI getStringTypeAPI() {
        return stringTypeAPI;
    }
    public EntityTypeAPI getEntityTypeAPI() {
        return entityTypeAPI;
    }
    public Collection<IntegerHollow> getAllIntegerHollow() {
        return new AllHollowRecordCollection<IntegerHollow>(getDataAccess().getTypeDataAccess("Integer").getTypeState()) {
            protected IntegerHollow getForOrdinal(int ordinal) {
                return getIntegerHollow(ordinal);
            }
        };
    }
    public IntegerHollow getIntegerHollow(int ordinal) {
        objectCreationSampler.recordCreation(0);
        return (IntegerHollow)integerProvider.getHollowObject(ordinal);
    }
    public Collection<LongHollow> getAllLongHollow() {
        return new AllHollowRecordCollection<LongHollow>(getDataAccess().getTypeDataAccess("Long").getTypeState()) {
            protected LongHollow getForOrdinal(int ordinal) {
                return getLongHollow(ordinal);
            }
        };
    }
    public LongHollow getLongHollow(int ordinal) {
        objectCreationSampler.recordCreation(1);
        return (LongHollow)longProvider.getHollowObject(ordinal);
    }
    public Collection<ListOfLongHollow> getAllListOfLongHollow() {
        return new AllHollowRecordCollection<ListOfLongHollow>(getDataAccess().getTypeDataAccess("ListOfLong").getTypeState()) {
            protected ListOfLongHollow getForOrdinal(int ordinal) {
                return getListOfLongHollow(ordinal);
            }
        };
    }
    public ListOfLongHollow getListOfLongHollow(int ordinal) {
        objectCreationSampler.recordCreation(2);
        return (ListOfLongHollow)listOfLongProvider.getHollowObject(ordinal);
    }
    public Collection<StringHollow> getAllStringHollow() {
        return new AllHollowRecordCollection<StringHollow>(getDataAccess().getTypeDataAccess("String").getTypeState()) {
            protected StringHollow getForOrdinal(int ordinal) {
                return getStringHollow(ordinal);
            }
        };
    }
    public StringHollow getStringHollow(int ordinal) {
        objectCreationSampler.recordCreation(3);
        return (StringHollow)stringProvider.getHollowObject(ordinal);
    }
    public Collection<EntityHollow> getAllEntityHollow() {
        return new AllHollowRecordCollection<EntityHollow>(getDataAccess().getTypeDataAccess("Entity").getTypeState()) {
            protected EntityHollow getForOrdinal(int ordinal) {
                return getEntityHollow(ordinal);
            }
        };
    }
    public EntityHollow getEntityHollow(int ordinal) {
        objectCreationSampler.recordCreation(4);
        return (EntityHollow)entityProvider.getHollowObject(ordinal);
    }
    public void setSamplingDirector(HollowSamplingDirector director) {
        super.setSamplingDirector(director);
        objectCreationSampler.setSamplingDirector(director);
    }

    public Collection<SampleResult> getObjectCreationSamplingResults() {
        return objectCreationSampler.getSampleResults();
    }

}
