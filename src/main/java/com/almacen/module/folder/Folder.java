package com.almacen.module.folder;

import com.almacen.module.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Integer id;

    @NotNull
    @Column(name = "folder_name")
    private String folder_name;

    @Column(name = "parent_folder_id")
    @JsonIgnore
    private Integer parent_folder_id;

    @NotNull
    @Column(name = "physical_path")
    @JsonIgnore
    private String physical_path;

    @OneToOne
    @JoinColumn(name = "fk_owner_id")
    @JsonIgnore
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public int getParent_folder_id() {
        return parent_folder_id;
    }

    public void setParent_folder_id(int parent_folder_id) {
        this.parent_folder_id = parent_folder_id;
    }

    public String getPhysical_path() {
        return physical_path;
    }

    public void setPhysical_path(String physical_path) {
        this.physical_path = physical_path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
