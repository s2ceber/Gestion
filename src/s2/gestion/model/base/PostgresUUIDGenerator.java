package s2.gestion.model.base;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PostgresUUIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
	UUID result = null;
	try (Connection cn = session.connection()) {
	    CallableStatement cs = cn.prepareCall("{call public.uuid_generate_v1()}");
	    cs.executeQuery();
	    result = (UUID) cs.getObject(1);

	} catch (SQLException e) {
	    throw new HibernateException(e);
	}
	return result;
    }

}
