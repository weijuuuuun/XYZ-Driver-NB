package chai.Services;

import java.util.UUID;

public class CredentialsGeneratorService {



    private static String generateRandomString(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 5);
    }

    public static String generateRandomPassword(){
        return generateRandomString();
    }

    public static String generateUserId(String name){
        return name + "-" +(generateRandomString());
    }

}
