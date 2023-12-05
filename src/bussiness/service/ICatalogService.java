package bussiness.service;

import bussiness.entity.Catalog;

import java.util.List;

public interface ICatalogService extends IGeneric<Catalog, Long> {
    Long getNewId();

    List<Catalog> findByName(String input);
}
