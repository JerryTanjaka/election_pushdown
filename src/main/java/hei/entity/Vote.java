package hei.entity;

public class Vote {
    private int id;
    private Integer candidateId; // peut être null
    private int voterId;
    private VoteType voteType;

    public Vote() {}

    public Vote(int id, Integer candidateId, int voterId, VoteType voteType) {
        this.id = id;
        this.candidateId = candidateId;
        this.voterId = voterId;
        this.voteType = voteType;
    }

    public int getId() {
        return id;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public int getVoterId() {
        return voterId;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }
}
