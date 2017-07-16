package com.github.igorperikov.examples.single.data;

import com.netflix.hollow.core.write.objectmapper.HollowPrimaryKey;

import java.util.List;

@HollowPrimaryKey(fields = {"intValue"})
public class Entity {
    private Integer intValue;
    private List<Long> listOfLongValues;
    private String stringValue;
}
