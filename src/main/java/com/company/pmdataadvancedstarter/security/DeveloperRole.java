package com.company.pmdataadvancedstarter.security;

import com.company.pmdataadvancedstarter.entity.Project;
import com.company.pmdataadvancedstarter.entity.Task;
import com.company.pmdataadvancedstarter.entity.TimeEntry;
import com.company.pmdataadvancedstarter.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Developer", code = DeveloperRole.CODE, scope = "UI")
public interface DeveloperRole {
    String CODE = "developer";

    @MenuPolicy(menuIds = {"Task_.list", "Project.list", "TimeEntry.list", "User.list"})
    @ViewPolicy(viewIds = {"Task_.list", "Project.list", "TimeEntry.list", "Project.detail", "Task_.detail", "TimeEntry.detail", "User.list"})
    void screens();

    @EntityAttributePolicy(entityClass = Project.class, attributes = "tasks", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = Project.class, attributes = {"id", "name", "startDate", "endDate", "manager", "status", "participants", "roadmap", "projectLabels", "deletedBy", "deletedDate", "totalEstimatedEfforts", "ownerId", "owner"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Project.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void project();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();

    @EntityAttributePolicy(entityClass = TimeEntry.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = TimeEntry.class, actions = EntityPolicyAction.ALL)
    void timeEntry();

    @EntityAttributePolicy(entityClass = User.class, attributes = {"firstName", "lastName", "username"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();
}