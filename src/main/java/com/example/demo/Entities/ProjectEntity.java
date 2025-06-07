package com.example.demo.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity image;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    @NotNull
    private UserEntity owner;

    @ManyToMany
    @JoinTable(name = "user_project", joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<UserEntity> users = new HashSet<UserEntity>();

    private Boolean isDraft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", image=" + image +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity projectEntity = (ProjectEntity) o;

        return Objects.equals(id, projectEntity.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
