package com.company.pmdataadvancedstarter.view.order;

import com.company.pmdataadvancedstarter.entity.Order;
import com.company.pmdataadvancedstarter.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "orders/:id", layout = MainView.class)
@ViewController(id = "Order_.detail")
@ViewDescriptor(path = "order-detail-view.xml")
@EditedEntityContainer("orderDc")
public class OrderDetailView extends StandardDetailView<Order> {
}