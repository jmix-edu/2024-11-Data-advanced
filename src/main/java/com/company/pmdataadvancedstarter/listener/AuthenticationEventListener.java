package com.company.pmdataadvancedstarter.listener;

import com.company.pmdataadvancedstarter.security.FullAccessRole;
import io.jmix.core.session.SessionData;
import io.jmix.security.role.assignment.RoleAssignment;
import io.jmix.security.role.assignment.RoleAssignmentRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component
public class AuthenticationEventListener {

    private final ObjectProvider<SessionData> sessionDataProvider;
    private final RoleAssignmentRepository roleAssignmentRepository;

    public AuthenticationEventListener(ObjectProvider<SessionData> sessionDataProvider, RoleAssignmentRepository roleAssignmentRepository) {
        this.sessionDataProvider = sessionDataProvider;
        this.roleAssignmentRepository = roleAssignmentRepository;
    }

    @EventListener
    public void onInteractiveAuthenticationSuccess(final InteractiveAuthenticationSuccessEvent event) {
        SessionData sessionData = sessionDataProvider.getIfAvailable();
        if (sessionData != null) {
            UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
            RoleAssignment fullAccessAssignment = roleAssignmentRepository.getAssignmentsByUsername(userDetails.getUsername())
                    .stream()
                    .filter(roleAssignment -> roleAssignment.getRoleCode().equals(FullAccessRole.CODE))
                    .findFirst()
                    .orElse(null);

            sessionData.setAttribute("isManager", fullAccessAssignment != null);
        }
    }
}