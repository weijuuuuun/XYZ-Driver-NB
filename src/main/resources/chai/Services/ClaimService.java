package chai.Services;

import chai.dao.ClaimDAO;
import chai.models.Claim;

import java.util.List;

public class ClaimService {

    private ClaimDAO claimDAO;

    public ClaimService(){
        this.claimDAO = new ClaimDAO();
    }

    public List<Claim> getAllClaims(){
        return this.claimDAO.getAll();
    }

    public List<Claim> getMemberClaimsInYear(String memberId, String year){
        return this.claimDAO.getMemberClaimsInYear(memberId, year);
    }

    public Claim addClaim(Claim claim){
        DateService dateService = new DateService("yyyy-MM-dd");
        String dateString = dateService.dateToString(claim.getDate());

        return this.claimDAO.add(claim, dateString);
    }

    public void updateClaimStatus(String status, int claimId){
        this.claimDAO.updateClaimsStatus(status, claimId);
    }
}
