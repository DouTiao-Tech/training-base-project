package com.darcytech.training.core.node.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.google.common.base.Objects;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<WangwangInfo> wangwangInfos;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WangwangInfo> getWangwangInfos() {
        return wangwangInfos;
    }

    public void setWangwangInfos(Set<WangwangInfo> wangwangInfos) {
        this.wangwangInfos = wangwangInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tag tag = (Tag) o;
        return Objects.equal(getName(), tag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    public void addWangwangInfo(WangwangInfo wangwangInfo) {
        if (wangwangInfos == null) {
            wangwangInfos = new HashSet<>();
        }
        wangwangInfos.add(wangwangInfo);
    }
}
