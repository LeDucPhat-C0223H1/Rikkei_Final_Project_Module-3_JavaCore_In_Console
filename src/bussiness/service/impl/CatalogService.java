package bussiness.service.impl;

import bussiness.config.IOFile;
import bussiness.entity.Catalog;
import bussiness.service.ICatalogService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogService implements ICatalogService {
    private List<Catalog> catalogs;

    public CatalogService() {
        this.catalogs = IOFile.readFromFile(IOFile.CATALOGS_PATH);
    }

    @Override
    public List<Catalog> findAll() {
        return catalogs;
    }

    @Override
    public Long getNewId() {
        long idMax = catalogs.stream()
                .map(c -> c.getCatalogtId())
                .max(Comparator.comparingLong(m -> m))
                .orElse(0L);
        return idMax + 1;
    }

    @Override
    public Catalog findById(Long id) {
        return catalogs.stream()
                .filter(c -> c.getCatalogtId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<Catalog> findByName(String input) {
        return catalogs.stream()
                .filter(c -> c.getCatalogName().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Catalog catalog) {
        if (findById(catalog.getCatalogtId()) != null) {
            // đã tồn tại -> chỉnh sửa
            catalogs.set(catalogs.indexOf(findById(catalog.getCatalogtId())), catalog);
        } else {
            // thêm mới
            catalogs.add(catalog);
        }
        IOFile.writeToFile(IOFile.CATALOGS_PATH, catalogs);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        catalogs.remove(findById(id));
        IOFile.writeToFile(IOFile.CATALOGS_PATH, catalogs);
    }
}
