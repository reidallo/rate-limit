package al.lhind.tab.claim.model.ui;

import al.lhind.tab.claim.model.ClaimStatus;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

public class ClaimDto {
    private Long claimId;
    private ClaimStatus status;
    private String email;
    private String description;
    private LocalDate createdDate;

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
