package com.github.igorperikov.examples.single.data;

import com.netflix.hollow.core.write.objectmapper.HollowPrimaryKey;

import java.util.List;
import java.util.Set;

@HollowPrimaryKey(fields = "id")
public class Entity {
    private Integer id;
    private List<Long> listOfLongValues;
    private String stringValue;
    private int primitive;
    private Set<OtherEntity> setOfOtherEntities;
}
