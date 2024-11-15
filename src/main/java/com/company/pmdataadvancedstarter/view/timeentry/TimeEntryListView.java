package com.company.pmdataadvancedstarter.view.timeentry;

import com.company.pmdataadvancedstarter.entity.TimeEntry;
import com.company.pmdataadvancedstarter.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route(value = "timeEntries", layout = MainView.class)
@ViewController(id = "TimeEntry.list")
@ViewDescriptor(path = "time-entry-list-view.xml")
@LookupComponent("timeEntriesDataGrid")
@DialogMode(width = "64em")
public class TimeEntryListView extends StandardListView<TimeEntry> {

    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;

    @Install(to = "timeEntriesDl", target = Target.DATA_LOADER)
    private List<TimeEntry> timeEntriesDlLoadDelegate(final LoadContext<TimeEntry> loadContext) {
        return unconstrainedDataManager.loadList(loadContext);
    }
}