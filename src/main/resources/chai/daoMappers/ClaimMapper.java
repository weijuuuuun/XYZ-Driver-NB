package chai.daoMappers;

import chai.Services.DateService;
import chai.models.Claim;
import chai.models.Member;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ClaimMapper {

    public List<Claim> mapClaims(ResultSet resultSet) throws SQLException, ParseException {
        if(!resultSet.isBeforeFirst()){
            return null;
        }

        List<Claim> claims = new ArrayList<Claim>();
        DateService dateService = new DateService("yyyy-MM-dd");

        while(resultSet.next()){
            Claim claim = new Claim();
            Member member = new Member();

            member.setId(resultSet.getString("member_id"));
            String nameTokens[] = resultSet.getString("name").split(" ");

            member.setFirstName(nameTokens[0]);
            member.setLastName(nameTokens[1]);

            claim.setId(resultSet.getInt("claim_id"));
            claim.setAmount(resultSet.getFloat("amount"));
            claim.setDate(dateService.stringToDate(resultSet.getString("date")));
            claim.setStatus(resultSet.getString("status"));
            claim.setRationale(resultSet.getString("rationale"));
            claim.setMember(member);


            claims.add(claim);
        }


        return claims;
    }

}
