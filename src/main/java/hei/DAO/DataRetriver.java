package hei.DAO;

import hei.entity.VoteType;
import hei.entity.VoteTypeCount;
import hei.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DataRetriver {
    public long countAllVotes() {
        String sql = """
                SELECT COUNT(id) FROM vote
                """;
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<VoteTypeCount> countVotesByType() {
        String sql = """
                SELECT vote_type, COUNT(id) as count FROM vote
                GROUP BY vote_type
                """;
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet resultSet = ps.executeQuery();
            List<VoteTypeCount> voteTypeCounts = new java.util.ArrayList<>();
            while (resultSet.next()) {
                String voteTypeStr = resultSet.getString("vote_type");
                long count = resultSet.getLong("count");
                VoteTypeCount voteTypeCount = new VoteTypeCount(VoteType.valueOf(voteTypeStr), count);
                voteTypeCounts.add(voteTypeCount);
            }
            return voteTypeCounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
