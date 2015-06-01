package com.yeahmobi.loadbalance_manager.model;

import org.hibernate.validator.constraints.NotBlank;

import com.google.code.morphia.annotations.Id;

public class Region extends BaseEntity {

    @Id
    @NotBlank
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String desc;

    public Region(String name, String desc) {
        super();
        this.name = name;
        this.desc = desc;
    }

    public Region(String name) {
        super();
        this.name = name;
    }

    public Region() {
        super();
    }

    // @Override
    // @JSONField(name = "name")
    // public String getId() {
    // return super.getId();
    // }
    //
    // @Override
    // @JSONField(name = "name")
    // public void setId(String id) {
    // super.setId(id);
    // }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Region other = (Region) obj;
        if (this.name == null) {
            if (other.name != null) return false;
        } else if (!this.name.equals(other.name)) return false;
        return true;
    }

}
