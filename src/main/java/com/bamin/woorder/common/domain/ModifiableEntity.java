package com.bamin.woorder.common.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class ModifiableEntity {

    @Column(name = "create_time",
            updatable = false,
            nullable = false)
    private LocalDateTime createTime;

    @Column(name = "modified_time",
            nullable = false)
    private LocalDateTime modifiedTime;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createTime = now;
        this.modifiedTime = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedTime = LocalDateTime.now();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }
}
