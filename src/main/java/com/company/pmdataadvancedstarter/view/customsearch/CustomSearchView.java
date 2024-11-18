package com.company.pmdataadvancedstarter.view.customsearch;


import com.company.pmdataadvancedstarter.entity.Customer;
import com.company.pmdataadvancedstarter.entity.Order;
import com.company.pmdataadvancedstarter.view.main.MainView;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Route(value = "custom-search-view", layout = MainView.class)
@ViewController(id = "CustomSearchView")
@ViewDescriptor(path = "custom-search-view.xml")
public class CustomSearchView extends StandardView {
    private static final Logger log = LoggerFactory.getLogger(CustomSearchView.class);
    @ViewComponent
    private CollectionContainer<Customer> customersDc;
    @ViewComponent
    private CollectionLoader<Order> ordersDl;

    @Subscribe
    public void onInit(final InitEvent event) {
        log.info("InitEvent - customers size: " + customersDc.getItems().size());
    }

    @Subscribe("customersTable")
    public void onCustomersTableSelection(final SelectionEvent<DataGrid<Customer>, Customer> event) {
        Collection<Customer> customers = event.getAllSelectedItems();

        ordersDl.setParameter("customers", customers);
        ordersDl.load();

    }


}