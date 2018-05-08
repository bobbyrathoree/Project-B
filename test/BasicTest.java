import models.Donor;
import models.Login;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Date;
import java.util.List;

public class BasicTest extends UnitTest {
    @Test
    public void DonorTest(){
        /*Donor donor=new Donor("Kishan","Bhatt","B+","Nadiad",20,"male","8866664190","kishan@bhatt.com",2,0,new Date(),new Date()).save();
        Login login=new Login();
        login.setDonor(donor);
        login.setD_email(donor.getD_email());
        login.setD_pass("password");
        login.save();
        donor=new Donor("Parth","Shukla","O+","Jamnagar",20,"male","8735863713","parth@shukla.com&",2,0,new Date(),new Date()).save();
        new Login(donor,donor.getD_email(),"password").save();*/
    List<Donor> dn= Donor.find("d_id","D1703102").fetch();
        for (Donor d:dn){
            d.delete();
        }
    }



    /*@Test
    public void modelLoginTest() {
        Login login=new Login();
        String success=login.validate("kishan@bhatt.com","password");
        System.out.println(success);
        assertEquals(success,"D1703101");
        assertNotEquals(success,false);
        success=login.validate("kishanbhatt@xyz.com","password");
        System.out.println(success);
        assertEquals(success,null);
    }
*/




}
