package com.darcytech.training.core.node.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.darcytech.training.core.base.BaseJpaModel;

@Entity
public class UserFile extends BaseJpaModel<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    private Long userId;

    private String fileName;

    private String fileContentType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    public UserFile() {
    }

    public UserFile(Long userId, String fileName, String fileContentType, byte[] content) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileContentType = fileContentType;
        this.content = content;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
