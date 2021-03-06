package com.tt.handsomeman.model;

import java.io.Serializable;
import java.util.Objects;

public class Skill implements Serializable {

    private Integer id;
    private Integer handyman_id;
    private Integer category_id;
    private String name;

    public Skill(Integer category_id,
                 String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHandyman_id() {
        return handyman_id;
    }

    public void setHandyman_id(Integer handyman_id) {
        this.handyman_id = handyman_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(category_id, skill.category_id) &&
                Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, name);
    }
}
