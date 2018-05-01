package IDGenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static play.libs.Mail.session;

/**
 * Created by Aazad on 3/19/2017.
 */
public class UserIDGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SessionImplementor session, Object o) throws HibernateException {
        String prefix = "D1703";
        Connection connection = session.connection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select count(d_id) from Donor_TB");
            if (rs.next()) {
                int id = rs.getInt(1) + 101;
                String genID = prefix + new Integer(id).toString();
                System.out.println("Generated Id:" + genID);
                return genID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
