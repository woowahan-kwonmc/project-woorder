package com.bamin.woorder.common.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class ModifiableEntity {

    @CreationTimestamp
    @Column(name = "create_time",
            updatable = false,
            nullable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "modified_time",
            nullable = false)
    private LocalDateTime modifiedTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }
}
