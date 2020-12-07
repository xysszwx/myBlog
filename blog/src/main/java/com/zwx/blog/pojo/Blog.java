package com.zwx.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Blog implements Serializable {
    private Long id;

    private Boolean appreciation;

    private Boolean commentabled;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String firstPicture;

    private String flag;

    private Boolean published;

    private Boolean recommend;

    private Boolean shareStatement;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer views;

    private Long typeId;

    private Long userId;

    private String description;

    private String name;

    private String tagIds;

    private List<Tag> tagss;

    public List<Tag> getTagss() {
        return tagss;
    }

    public void setTagss(List<Tag> tagss) {
        this.tagss = tagss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    private static final long serialVersionUID = 1L;

    public Blog(Long id, Boolean appreciation, Boolean commentabled, String content, Date createTime, String firstPicture, String flag, Boolean published, Boolean recommend, Boolean shareStatement, String title, Date updateTime, Integer views, Long typeId, Long userId, String description) {
        this.id = id;
        this.appreciation = appreciation;
        this.commentabled = commentabled;
        this.content = content;
        this.createTime = createTime;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.published = published;
        this.recommend = recommend;
        this.shareStatement = shareStatement;
        this.title = title;
        this.updateTime = updateTime;
        this.views = views;
        this.typeId = typeId;
        this.userId = userId;
        this.description = description;
    }

    public Blog() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(Boolean commentabled) {
        this.commentabled = commentabled;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture == null ? null : firstPicture.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(Boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appreciation=").append(appreciation);
        sb.append(", commentabled=").append(commentabled);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", firstPicture=").append(firstPicture);
        sb.append(", flag=").append(flag);
        sb.append(", published=").append(published);
        sb.append(", recommend=").append(recommend);
        sb.append(", shareStatement=").append(shareStatement);
        sb.append(", title=").append(title);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", views=").append(views);
        sb.append(", typeId=").append(typeId);
        sb.append(", userId=").append(userId);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}