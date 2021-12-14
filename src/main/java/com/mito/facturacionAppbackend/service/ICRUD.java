package com.mito.facturacionAppbackend.service;

import java.util.List;

public interface ICRUD <T, ID>{
    T toRegister(T d)throws Exception;
    T toModify(T d)throws Exception;
    List<T> toList()throws Exception;
    T toListForId(ID id)throws Exception;
    void toDelete(ID id)throws Exception;
}
