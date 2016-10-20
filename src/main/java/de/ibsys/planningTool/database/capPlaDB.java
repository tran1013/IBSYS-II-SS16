package de.ibsys.planningTool.database;

import de.ibsys.planningTool.model.Constants;
import de.ibsys.planningTool.model.ProductionSteps;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duc on 07.08.16.
 */
public class capPlaDB {

    public List<ProductionSteps> findByWorkplacID(Integer id) throws SQLException {
        List<ProductionSteps> productionSteps = new ArrayList<>();

        Statement stmt = null;
        String query = "SELECT p.*, c.c_id "
                + " FROM production_step p LEFT JOIN production_step_chain c "
                + " ON p.id = c.p_id "
                + " WHERE p.workplaceId = '" + id + "'";
        try {
            stmt = DriverManager.getConnection(Constants.DATABASEPATH).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int productionStepID = rs.getInt("id");
                int workplaceID = rs.getInt("workplaceId");
                String itemConfigID = rs.getString("itemId");
                int productionTime = rs.getInt("productionTime");
                int setupTime = rs.getInt("setupTime");
                Integer preProductionStepConfigID = rs.getInt("c_id");

                if (preProductionStepConfigID == 0) {
                    productionSteps.add(new ProductionSteps(productionStepID, workplaceID, itemConfigID, productionTime, setupTime));
                } else {
                    productionSteps.add(new ProductionSteps(productionStepID, workplaceID, itemConfigID, productionTime, setupTime, preProductionStepConfigID));
                }
            }
            //System.out.println(productionSteps);
            return productionSteps;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return productionSteps;
    }

    public List<Integer> findWorkplaceID() throws SQLException {
        List<Integer> workplaceIDs = new ArrayList<>();

        Statement stmt = null;
        String query = "SELECT DISTINCT workplaceId "
                + "FROM production_step "
                + "ORDER BY workplaceId";

        try {
            stmt = DriverManager.getConnection(Constants.DATABASEPATH).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("workplaceId");

                workplaceIDs.add(id);
            }

            return workplaceIDs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return workplaceIDs;
    }
}
