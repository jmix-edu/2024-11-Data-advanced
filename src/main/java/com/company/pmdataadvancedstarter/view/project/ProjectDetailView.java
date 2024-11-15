package com.company.pmdataadvancedstarter.view.project;

import com.company.pmdataadvancedstarter.app.ProjectsService;
import com.company.pmdataadvancedstarter.datatype.ProjectLabels;
import com.company.pmdataadvancedstarter.entity.Project;
import com.company.pmdataadvancedstarter.entity.Roadmap;
import com.company.pmdataadvancedstarter.view.main.MainView;
import com.company.pmdataadvancedstarter.view.user.UserListView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Route(value = "projects/:id", layout = MainView.class)
@ViewController(id = "Project.detail")
@ViewDescriptor(path = "project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {

    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private TypedTextField<ProjectLabels> projectLabelsField;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Validator validator;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Project> event) {
        Roadmap roadmap = dataContext.create(Roadmap.class);
        event.getEntity().setRoadmap(roadmap);

        projectLabelsField.setReadOnly(false);

        event.getEntity().setProjectLabels(new ProjectLabels(List.of("bug", "task", "enhancement")));
    }

    @Subscribe(id = "commitWithBeanValidationBtn", subject = "clickListener")
    public void onCommitWithBeanValidationBtnClick(final ClickEvent<JmixButton> event) {
        try {
            projectsService.save(getEditedEntity());
            close(StandardOutcome.CLOSE);
        } catch (ConstraintViolationException e) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                sb.append(constraintViolation.getMessage()).append("\n");
            }

            notifications.create(sb.toString())
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }
    }

    @Subscribe(id = "performBeanValidationBtn", subject = "clickListener")
    public void onPerformBeanValidationBtnClick(final ClickEvent<JmixButton> event) {
        Set<ConstraintViolation<Project>> violations = validator.validate(getEditedEntity(),
                Default.class, UiComponentChecks.class);

        showBeanValidationExceptions(violations);
    }

    private void showBeanValidationExceptions(Set<ConstraintViolation<Project>> violations) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : violations) {
            sb.append(constraintViolation.getMessage()).append("\n");
        }
        notifications.create(sb.toString())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Install(to = "participantsDataGrid.add", subject = "viewConfigurer")
    private void participantsDataGridAddViewConfigurer(final UserListView view) {
        view.setFilterProject(getEditedEntity());
    }
}