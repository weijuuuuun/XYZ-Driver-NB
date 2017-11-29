package chai.Services;

import chai.dao.MemberDAO;
import chai.dao.UserDAO;
import chai.models.Member;
import chai.models.User;

public class RegistrationService {

    MemberDAO memberDAO;
    UserDAO userDAO;

    public RegistrationService(){
        this.memberDAO = new MemberDAO();
        this.userDAO   = new UserDAO();
    }


    public User registerMember(Member member){

        DateService dateService = new DateService("ddMMYY");

        User newUser = new User();
        newUser.setStatus("APPLIED");
        newUser.setPassword(dateService.dateToString(member.getDob()));
        newUser.setId(member.getId());

        userDAO.addUser(newUser);
        memberDAO.addMember(member);

        return newUser;
    }
}
