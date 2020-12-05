package com.zwx.blog.pojo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Type implements Serializable {
    private Long id;

    @NotBlank(message = "内容不能为空")
    private String name;

    private static final long serialVersionUID = 1L;

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type() {
        super();
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
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}