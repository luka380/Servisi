package com.project.prjx.Data.Mappers;

import java.util.List;

public interface Mapper <T, R> {
    R map(T object);

    default List<R> map(List<T> objects){
        return objects.stream().map(this::map).toList();
    }

    T reverseMap(R object);

    default List<T> reverseMap(List<R> objects){
        return objects.stream().map(this::reverseMap).toList();
    }
}
