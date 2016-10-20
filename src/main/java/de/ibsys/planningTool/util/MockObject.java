package de.ibsys.planningTool.util;

import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.Order;
import de.ibsys.planningTool.model.xmlExportModel.WorkTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static de.ibsys.planningTool.model.Constants.*;

/**
 * Mock Object for Export
 * Created by minhnguyen on 20.07.16.
 */
public class MockObject {

    public int generatedRandomValueMode() {
        return new Random().nextInt(10);
    }

    public int generatedRandomShift() {
        return new Random().nextInt(3);
    }

    public int generatedRandomOverTime() {
        return new Random().nextInt(1200);
    }

    public int generatedRandomOrder() {
        return new Random().nextInt(22000);
    }

    public int generatedProduction() {
        return new Random().nextInt(400);
    }


    public List<Order> orderListMockData() {
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int mode = generatedRandomValueMode();
            int delivery = NORMAL_DELIVERY;
            if (mode < 4) {
                delivery = FAST_DELIVERY;
            }
            Order order = new Order(String.valueOf(22+i), generatedRandomOrder(), delivery);
            orderList.add(order);
        }
        return orderList;
    }

    public List<Item> productionListMockData() {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Item item = new Item(String.valueOf(i+1), generatedProduction());
            itemList.add(item);
        }
        return itemList;
    }

    public List<WorkTime> workTimeMockData() {
        List<WorkTime> workTimeList = new ArrayList<>();
        for (int i = 0; i < 15; i++ ) {
            WorkTime workTime = new WorkTime(i+1, generatedRandomShift(), generatedRandomOverTime());
            workTimeList.add(workTime);
        }
        return workTimeList;
    }
}
