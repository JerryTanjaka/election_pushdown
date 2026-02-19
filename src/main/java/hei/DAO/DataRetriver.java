package hei.DAO;

import hei.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRetriver {
    public long countAllVotes(){
    String sql= """
        SELECT COUNT(id) FROM vote
        """;
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);){
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return resultSet.getLong(1);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
  return 0;
    }
}
