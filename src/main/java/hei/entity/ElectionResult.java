package hei.entity;

public class ElectionResult {
    private String candidateName;
    private long validVoteCount;

    public ElectionResult() {}

    public ElectionResult(String candidateName, long validVoteCount) {
        this.candidateName = candidateName;
        this.validVoteCount = validVoteCount;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public long getValidVoteCount() {
        return validVoteCount;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public void setValidVoteCount(long validVoteCount) {
        this.validVoteCount = validVoteCount;
    }

    @Override
    public String toString() {
        return "ElectionResult(candidateName=" + candidateName +
                ", validVoteCount=" + validVoteCount + ")";
    }
}
