package com.zwx.blog.pojo;

import java.io.Serializable;

public class BlogTags implements Serializable {
    private Long blogsId;

    private Long tagsId;

    private static final long serialVersionUID = 1L;

    public BlogTags(Long blogsId, Long tagsId) {
        this.blogsId = blogsId;
        this.tagsId = tagsId;
    }

    public BlogTags() {
        super();
    }

    public Long getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(Long blogsId) {
        this.blogsId = blogsId;
    }

    public Long getTagsId() {
        return tagsId;
    }

    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", blogsId=").append(blogsId);
        sb.append(", tagsId=").append(tagsId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}