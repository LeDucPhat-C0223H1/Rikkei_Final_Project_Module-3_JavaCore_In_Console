package bussiness.entity;

import java.io.Serializable;

public class Catalog implements Serializable {
    private long catalogtId;
    private String catalogName;
    private String description;
    private boolean status;

    public Catalog() {
    }

    public Catalog(long catalogtId, String catalogName, String description) {
        this.catalogtId = catalogtId;
        this.catalogName = catalogName;
        this.description = description;
        this.status = true;
    }

    public long getCatalogtId() {
        return catalogtId;
    }

    public void setCatalogtId(long catalogtId) {
        this.catalogtId = catalogtId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "* " +
                "id=" + catalogtId +
                " __ Tên danh mục: " + catalogName +
                " , Trạng thái: " + (status ? "Hiển thị" : "Bị ẩn");
    }
}
