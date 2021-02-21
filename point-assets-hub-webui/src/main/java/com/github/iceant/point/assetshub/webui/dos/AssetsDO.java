package com.github.iceant.point.assetshub.webui.dos;

import java.util.Date;

public class AssetsDO {
    private Long id;
    private String type;
    private String groupId;
    private String artifactId;
    private String version;
    private String detail;
    private Date createDatetime;

    ////////////////////////////////////////////////////////////////////////////////
    ////


    public Long getId() {
        return id;
    }

    public AssetsDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public AssetsDO setType(String type) {
        this.type = type;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public AssetsDO setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public AssetsDO setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public AssetsDO setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public AssetsDO setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public AssetsDO setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
        return this;
    }
}
