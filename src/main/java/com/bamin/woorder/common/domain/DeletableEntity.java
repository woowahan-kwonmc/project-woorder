package com.bamin.woorder.common.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class DeletableEntity extends ModifiableEntity {

    @CreatedDate
    @Column(name = "delete_time")
    private LocalDateTime deleteTime;

    protected void softDelete() {
        checkDeletable();
        this.deleteTime = LocalDateTime.now();
    }

    private void checkDeletable() {
        if (deleteTime != null) {
            throw new NotDeletableException();
        }
    }

    @Override
    public LocalDateTime getCreateTime() {
        return super.getCreateTime();
    }

    @Override
    public LocalDateTime getModifiedTime() {
        return super.getModifiedTime();
    }

    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }
}
