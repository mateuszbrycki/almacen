package com.almacen.module.folder.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


public class FolderDTO {

    @NotNull
    @Length(min = 3)
    private String folder_name;

    private Integer parent_folder_id;

    @NotNull
    private String physical_path;


    public FolderDTO() {
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public Integer getParent_folder_id() {
        return parent_folder_id;
    }

    public void setParent_folder_id(Integer parent_folder_id) {
        this.parent_folder_id = parent_folder_id;
    }

    public String getPhysical_path() {
        return physical_path;
    }

    public void setPhysical_path(String physical_path) {
        this.physical_path = physical_path;
    }

    @Override
    public String toString() {
        return "Folder name: " + this.folder_name + ", parent id: " + this.parent_folder_id + ", physical path: " + this.physical_path;
    }
}
