package de.ibsys.planningTool.mock;

import de.ibsys.planningTool.model.xmlExportModel.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Che on 04.09.2016.
 */
public class ForeCastMockData {

    Map<String, Item> forecastmap = new HashMap<String, Item>();

    public Map<String, Item> getForecastmap() {
        return forecastmap;
    }

    public void setForecastmap() {
        forecastmap.put("p1n", new Item("1", 100));
        forecastmap.put("p1n1", new Item("1", 100));
        forecastmap.put("p1n2", new Item("1", 100));
        forecastmap.put("p1n3", new Item("1", 100));

        forecastmap.put("p2n", new Item("2", 110));
        forecastmap.put("p2n1", new Item("2", 210));
        forecastmap.put("p2n2", new Item("2", 310));
        forecastmap.put("p2n3", new Item("2", 410));

        forecastmap.put("p3n", new Item("3", 200));
        forecastmap.put("p3n1", new Item("3", 210));
        forecastmap.put("p3n2", new Item("3", 220));
        forecastmap.put("p3n3", new Item("3", 230));
    }


}
