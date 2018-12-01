package com.github.igorperikov.examples.single.data;

import com.netflix.hollow.core.write.objectmapper.HollowPrimaryKey;

@HollowPrimaryKey(fields = "id")
public class OtherEntity {
    private int id;
    private String value;
}
