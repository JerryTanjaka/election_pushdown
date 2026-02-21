package hei.DAO;

import hei.entity.CandidateVoteCount;
import hei.entity.VoteSummary;
import hei.entity.VoteType;
import hei.entity.VoteTypeCount;
import hei.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<CandidateVoteCount> countValidVotesByCandidate(){
        String sql= """
                select c.name as candidate_name, COUNT(v.id) as count
                from candidate c
                left join vote v
                on c.id = v.candidate_id
                and v.vote_type = 'VALID'
                group by c.name
                order by c.name;
                """;
        try (Connection connection = new DBConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        ResultSet resultSet = preparedStatement.executeQuery();
        List<CandidateVoteCount> candidateVoteCounts = new ArrayList<>();
        while (resultSet.next()){
            CandidateVoteCount candidateVoteCount = new CandidateVoteCount();
            candidateVoteCount.setCandidateName(resultSet.getString("candidate_name"));
            candidateVoteCount.setValidVoteCount(resultSet.getLong("count"));
            candidateVoteCounts.add(candidateVoteCount);
        }
        return candidateVoteCounts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public VoteSummary computeVoteSummary() {
        String sql = """
                SELECT
                  count(CASE WHEN vote_type = 'VALID' THEN 1 END) AS valid_count,
                  count(CASE WHEN vote_type = 'BLANK' THEN 1 END) AS blank_count,
                  count(CASE WHEN vote_type = 'NULL' THEN 1 END) AS null_count
                FROM vote;
        """;
        try(Connection connection = new DBConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            VoteSummary result = new VoteSummary();
            if(resultSet.next()){
                result.setValidCount(resultSet.getLong("valid_count"));
                result.setBlankCount(resultSet.getLong("blank_count"));
                result.setNullCount(resultSet.getLong("null_count"));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
