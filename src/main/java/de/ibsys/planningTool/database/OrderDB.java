package de.ibsys.planningTool.database;

import de.ibsys.planningTool.model.Constants;
import de.ibsys.planningTool.model.TermsOfSaleData;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Che on 16.08.2016.
 */
public class OrderDB {

    public List<TermsOfSaleData> findAll() throws  SQLException {
        List<TermsOfSaleData> termsOfSaleList = new ArrayList<>();

        Statement stmt = null;
        String query = "SELECT * FROM terms_of_sale";

        try {
            stmt = DriverManager.getConnection(Constants.DATABASEPATH).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                termsOfSaleList.add(new TermsOfSaleData(rs.getString("itemId"),
                        rs.getDouble("deliveryTime"),
                        rs.getDouble("variance"),
                        rs.getInt("orderingCosts"),
                        rs.getInt("discountQuantity"),
                        rs.getDouble("part_value")
                ));
            }
        }

        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
        return termsOfSaleList;
    }

}
