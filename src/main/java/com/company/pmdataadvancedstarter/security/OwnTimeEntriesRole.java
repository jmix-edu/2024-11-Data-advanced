package com.company.pmdataadvancedstarter.security;

import com.company.pmdataadvancedstarter.entity.TimeEntry;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "Own time entries", code = OwnTimeEntriesRole.CODE)
public interface OwnTimeEntriesRole {
    String CODE = "own-time-entries";

    @JpqlRowLevelPolicy(entityClass = TimeEntry.class, where = "{E}.user.id = :current_user_id")
    void timeEntry();
}