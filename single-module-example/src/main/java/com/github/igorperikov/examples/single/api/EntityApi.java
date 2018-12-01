package com.github.igorperikov.examples.single.api;

import com.github.igorperikov.examples.single.api.collections.ListOfLong;
import com.github.igorperikov.examples.single.api.collections.SetOfOtherEntity;
import com.github.igorperikov.examples.single.api.core.*;
import com.netflix.hollow.api.consumer.HollowConsumerAPI;
import com.netflix.hollow.api.custom.HollowAPI;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.api.objects.provider.HollowObjectCacheProvider;
import com.netflix.hollow.api.objects.provider.HollowObjectFactoryProvider;
import com.netflix.hollow.api.objects.provider.HollowObjectProvider;
import com.netflix.hollow.api.sampling.HollowObjectCreationSampler;
import com.netflix.hollow.api.sampling.HollowSamplingDirector;
import com.netflix.hollow.api.sampling.SampleResult;
import com.netflix.hollow.core.read.dataaccess.*;
import com.netflix.hollow.core.read.dataaccess.missing.HollowListMissingDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowObjectMissingDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowSetMissingDataAccess;
import com.netflix.hollow.core.type.*;
import com.netflix.hollow.core.util.AllHollowRecordCollection;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class EntityApi extends HollowAPI implements HollowConsumerAPI.IntegerRetriever, HollowConsumerAPI.LongRetriever, HollowConsumerAPI.StringRetriever {

    private final HollowObjectCreationSampler objectCreationSampler;

    private final IntegerTypeAPI integerTypeAPI;
    private final LongTypeAPI longTypeAPI;
    private final ListOfLongTypeAPI listOfLongTypeAPI;
    private final StringTypeAPI stringTypeAPI;
    private final OtherEntityTypeAPI otherEntityTypeAPI;
    private final SetOfOtherEntityTypeAPI setOfOtherEntityTypeAPI;
    private final EntityTypeAPI entityTypeAPI;

    private final HollowObjectProvider integerProvider;
    private final HollowObjectProvider longProvider;
    private final HollowObjectProvider listOfLongProvider;
    private final HollowObjectProvider stringProvider;
    private final HollowObjectProvider otherEntityProvider;
    private final HollowObjectProvider setOfOtherEntityProvider;
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

        objectCreationSampler = new HollowObjectCreationSampler("Integer", "Long", "ListOfLong", "String", "OtherEntity", "SetOfOtherEntity", "Entity");

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

        typeDataAccess = dataAccess.getTypeDataAccess("OtherEntity");
        if (typeDataAccess != null) {
            otherEntityTypeAPI = new OtherEntityTypeAPI(this, (HollowObjectTypeDataAccess) typeDataAccess);
        } else {
            otherEntityTypeAPI = new OtherEntityTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "OtherEntity"));
        }
        addTypeAPI(otherEntityTypeAPI);
        factory = factoryOverrides.get("OtherEntity");
        if (factory == null)
            factory = new OtherEntityHollowFactory();
        if (cachedTypes.contains("OtherEntity")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if (previousCycleAPI != null && (previousCycleAPI.otherEntityProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.otherEntityProvider;
            otherEntityProvider = new HollowObjectCacheProvider(typeDataAccess, otherEntityTypeAPI, factory, previousCacheProvider);
        } else {
            otherEntityProvider = new HollowObjectFactoryProvider(typeDataAccess, otherEntityTypeAPI, factory);
        }

        typeDataAccess = dataAccess.getTypeDataAccess("SetOfOtherEntity");
        if (typeDataAccess != null) {
            setOfOtherEntityTypeAPI = new SetOfOtherEntityTypeAPI(this, (HollowSetTypeDataAccess) typeDataAccess);
        } else {
            setOfOtherEntityTypeAPI = new SetOfOtherEntityTypeAPI(this, new HollowSetMissingDataAccess(dataAccess, "SetOfOtherEntity"));
        }
        addTypeAPI(setOfOtherEntityTypeAPI);
        factory = factoryOverrides.get("SetOfOtherEntity");
        if (factory == null)
            factory = new SetOfOtherEntityHollowFactory();
        if (cachedTypes.contains("SetOfOtherEntity")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if (previousCycleAPI != null && (previousCycleAPI.setOfOtherEntityProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.setOfOtherEntityProvider;
            setOfOtherEntityProvider = new HollowObjectCacheProvider(typeDataAccess, setOfOtherEntityTypeAPI, factory, previousCacheProvider);
        } else {
            setOfOtherEntityProvider = new HollowObjectFactoryProvider(typeDataAccess, setOfOtherEntityTypeAPI, factory);
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
        if (otherEntityProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider) otherEntityProvider).detach();
        if (setOfOtherEntityProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider) setOfOtherEntityProvider).detach();
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

    public OtherEntityTypeAPI getOtherEntityTypeAPI() {
        return otherEntityTypeAPI;
    }

    public SetOfOtherEntityTypeAPI getSetOfOtherEntityTypeAPI() {
        return setOfOtherEntityTypeAPI;
    }
    public EntityTypeAPI getEntityTypeAPI() {
        return entityTypeAPI;
    }

    public Collection<HInteger> getAllHInteger() {
        return new AllHollowRecordCollection<HInteger>(getDataAccess().getTypeDataAccess("Integer").getTypeState()) {
            protected HInteger getForOrdinal(int ordinal) {
                return getHInteger(ordinal);
            }
        };
    }

    public HInteger getHInteger(int ordinal) {
        objectCreationSampler.recordCreation(0);
        return (HInteger) integerProvider.getHollowObject(ordinal);
    }

    public Collection<HLong> getAllHLong() {
        return new AllHollowRecordCollection<HLong>(getDataAccess().getTypeDataAccess("Long").getTypeState()) {
            protected HLong getForOrdinal(int ordinal) {
                return getHLong(ordinal);
            }
        };
    }

    public HLong getHLong(int ordinal) {
        objectCreationSampler.recordCreation(1);
        return (HLong) longProvider.getHollowObject(ordinal);
    }

    public Collection<ListOfLong> getAllListOfLong() {
        return new AllHollowRecordCollection<ListOfLong>(getDataAccess().getTypeDataAccess("ListOfLong").getTypeState()) {
            protected ListOfLong getForOrdinal(int ordinal) {
                return getListOfLong(ordinal);
            }
        };
    }

    public ListOfLong getListOfLong(int ordinal) {
        objectCreationSampler.recordCreation(2);
        return (ListOfLong) listOfLongProvider.getHollowObject(ordinal);
    }

    public Collection<HString> getAllHString() {
        return new AllHollowRecordCollection<HString>(getDataAccess().getTypeDataAccess("String").getTypeState()) {
            protected HString getForOrdinal(int ordinal) {
                return getHString(ordinal);
            }
        };
    }

    public HString getHString(int ordinal) {
        objectCreationSampler.recordCreation(3);
        return (HString) stringProvider.getHollowObject(ordinal);
    }

    public Collection<OtherEntity> getAllOtherEntity() {
        return new AllHollowRecordCollection<OtherEntity>(getDataAccess().getTypeDataAccess("OtherEntity").getTypeState()) {
            protected OtherEntity getForOrdinal(int ordinal) {
                return getOtherEntity(ordinal);
            }
        };
    }

    public OtherEntity getOtherEntity(int ordinal) {
        objectCreationSampler.recordCreation(4);
        return (OtherEntity) otherEntityProvider.getHollowObject(ordinal);
    }

    public Collection<SetOfOtherEntity> getAllSetOfOtherEntity() {
        return new AllHollowRecordCollection<SetOfOtherEntity>(getDataAccess().getTypeDataAccess("SetOfOtherEntity").getTypeState()) {
            protected SetOfOtherEntity getForOrdinal(int ordinal) {
                return getSetOfOtherEntity(ordinal);
            }
        };
    }

    public SetOfOtherEntity getSetOfOtherEntity(int ordinal) {
        objectCreationSampler.recordCreation(5);
        return (SetOfOtherEntity) setOfOtherEntityProvider.getHollowObject(ordinal);
    }

    public Collection<Entity> getAllEntity() {
        return new AllHollowRecordCollection<Entity>(getDataAccess().getTypeDataAccess("Entity").getTypeState()) {
            protected Entity getForOrdinal(int ordinal) {
                return getEntity(ordinal);
            }
        };
    }

    public Entity getEntity(int ordinal) {
        objectCreationSampler.recordCreation(6);
        return (Entity) entityProvider.getHollowObject(ordinal);
    }
    public void setSamplingDirector(HollowSamplingDirector director) {
        super.setSamplingDirector(director);
        objectCreationSampler.setSamplingDirector(director);
    }

    public Collection<SampleResult> getObjectCreationSamplingResults() {
        return objectCreationSampler.getSampleResults();
    }

}
