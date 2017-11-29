package chai.daoMappers;

import chai.Services.DateService;
import chai.models.Member;
import chai.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberMapper {

    public List<Member> mapMembers(ResultSet resultSet) throws SQLException, ParseException {
        if(!resultSet.isBeforeFirst()){
            return null;
        }

        List<Member> members = new ArrayList<Member>();
        DateService dateService = new DateService("yyyy-MM-dd");

        while(resultSet.next()){
            Member member = new Member();
            member.setId(resultSet.getString("id"));
            String nameTokens[] = resultSet.getString("name").split(" ");

            member.setFirstName(nameTokens[0]);
            member.setLastName(nameTokens[1]);

            member.setAddress(resultSet.getString("address"));
            member.setDob(dateService.stringToDate(resultSet.getString("dob")));
            member.setStatus(resultSet.getString("status"));
            member.setBalance(resultSet.getFloat("balance"));

            members.add(member);
        }


        return members;
    }


    public Member mapMember(ResultSet resultSet) throws SQLException, ParseException {
        if(!resultSet.isBeforeFirst()){
            return null;
        }


        DateService dateService = new DateService("yyyy-MM-dd");

        Member member = new Member();

        resultSet.next();

        member.setId(resultSet.getString("id"));
        String nameTokens[] = resultSet.getString("name").split(" ");

        member.setFirstName(nameTokens[0]);
        member.setLastName(nameTokens[1]);

        member.setAddress(resultSet.getString("address"));
        member.setDob(dateService.stringToDate(resultSet.getString("dob")));
        member.setDor(dateService.stringToDate(resultSet.getString("dor")));
        member.setStatus(resultSet.getString("status"));
        member.setBalance(resultSet.getFloat("balance"));



        return member;
    }

}
