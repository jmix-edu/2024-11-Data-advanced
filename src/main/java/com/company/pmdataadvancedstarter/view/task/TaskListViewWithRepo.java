package com.company.pmdataadvancedstarter.view.task;

import com.company.pmdataadvancedstarter.entity.Task;
import com.company.pmdataadvancedstarter.repository.TaskRepository;
import com.company.pmdataadvancedstarter.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

import static io.jmix.core.repository.JmixDataRepositoryUtils.buildPageRequest;
import static io.jmix.core.repository.JmixDataRepositoryUtils.buildRepositoryContext;

@Route(value = "tasks-with-repo", layout = MainView.class)
@ViewController(id = "Task_repo.list")
@ViewDescriptor(path = "task-list-view-with-repo.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListViewWithRepo extends StandardListView<Task> {
    private static final Logger log = LoggerFactory.getLogger(TaskListViewWithRepo.class);

    @Autowired
    private TaskRepository repository;

    @Install(to = "tasksDl", target = Target.DATA_LOADER)
    private List<Task> loadDelegate(LoadContext<Task> context) {
        LoadContext.Query query = context.getQuery();

        log.info("Query: " + query);

        return repository.findAll(buildPageRequest(context), buildRepositoryContext(context)).getContent();
    }

    @Install(to = "tasksDataGrid.remove", subject = "delegate")
    private void tasksDataGridRemoveDelegate(final Collection<Task> collection) {
        repository.deleteAll(collection);
    }
}