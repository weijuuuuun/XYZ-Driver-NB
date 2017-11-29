package chai.Services;

import chai.dao.MemberDAO;
import chai.models.Claim;
import chai.models.Member;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MemberService {

    private MemberDAO memberDAO;

    public MemberService(){
        this.memberDAO = new MemberDAO();
    }

    public List<Member> getAllMembers(){
        return this.memberDAO.getAll();
    }

    public Member get(String memberId){
        return this.memberDAO.get(memberId);
    }

    public void updateMemberStatus(String newStatus, String memberId){
        this.memberDAO.updateStatus(newStatus, memberId);
    }

    public boolean getClaimEligibility(String memberId){

        boolean eligibility = true;

        ClaimService claimService = new ClaimService();
        DateService dateService = new DateService("yyyy");


        Member member = this.memberDAO.get(memberId);

        List<Claim> claims = claimService.getMemberClaimsInYear(memberId, dateService.dateToString(new Date()));

        System.out.println("================CLAIMS SIZE: " + claims.size() + " ================================");

        if(member == null){
            return false;
        }

        if(DateUtils.addMonths(member.getDor(), 6).after(new Date())){
            System.out.println("REGISTERED 6 months!!");
            eligibility = false;
        }

        if(claims != null && claims.size() >= 2){
            eligibility = false;
        }

        return eligibility;

    }


}
