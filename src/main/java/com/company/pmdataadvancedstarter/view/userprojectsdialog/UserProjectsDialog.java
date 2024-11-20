package com.company.pmdataadvancedstarter.view.userprojectsdialog;


import com.company.pmdataadvancedstarter.entity.Project;
import com.company.pmdataadvancedstarter.entity.User;
import com.company.pmdataadvancedstarter.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

@Route(value = "user-projects-dialog", layout = MainView.class)
@ViewController(id = "UserProjectsDialog")
@ViewDescriptor(path = "user-projects-dialog.xml")
@DialogMode(width = "50em", height = "37.5em")
public class UserProjectsDialog extends StandardView {

    @ViewComponent
    private CollectionLoader<Project> projectsDl;

    private User user;

    public User getUser() {
        return user;
    }

    public UserProjectsDialog withUser(User user) {
        this.user = user;

        projectsDl.setParameter("user", user);
        projectsDl.load();

        return this;
    }
}