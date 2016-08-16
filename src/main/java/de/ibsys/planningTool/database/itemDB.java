package de.ibsys.planningTool.database;

import de.ibsys.planningTool.model.Constants;
import de.ibsys.planningTool.model.ItemComponents;

import java.util.HashMap;
import java.util.Map;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Che on 16.08.2016.
 */
public class itemDB {

    public ItemComponents findById(String id) throws  SQLException {
        ItemComponents itemComponents = new ItemComponents();

        Statement stmt = null;
        String query = "SELECT i.id, i.name, c.c_id, c.quantity "
                + "FROM item i LEFT JOIN combination c "
                + "ON i.id = c.p_id "
                + "WHERE i.id = '" + id + "'";
        try {
            stmt = DriverManager.getConnection(Constants.DATABASEPATH).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String itemConfigId = rs.getString("id");
                String name = rs.getString("name");

                Map<String, Integer> components = new HashMap<>();
                do {
                    String childComponentId = rs.getString("c_id");
                    int childComponentQuantity = rs.getInt("quantity");
                    if (childComponentId != null && childComponentQuantity != 0) {
                        components.put(childComponentId, childComponentQuantity);
                    }
                }
                    while (rs.next()) ;
                    return new ItemComponents(itemConfigId, name, components);

            } else {
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            itemComponents = null;
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return itemComponents;
    }
}
