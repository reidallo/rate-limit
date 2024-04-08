package al.lhind.tab.claim.model;

public enum ClaimStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");
    private final String value;

    ClaimStatus(String status) {
        this.value = status;
    }

    public String getValue() {
        return value;
    }
}
