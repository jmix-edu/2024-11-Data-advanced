package com.company.pmdataadvancedstarter.view.order;

import com.company.pmdataadvancedstarter.entity.Order;
import com.company.pmdataadvancedstarter.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "orders", layout = MainView.class)
@ViewController(id = "Order_.list")
@ViewDescriptor(path = "order-list-view.xml")
@LookupComponent("ordersDataGrid")
@DialogMode(width = "64em")
public class OrderListView extends StandardListView<Order> {
}