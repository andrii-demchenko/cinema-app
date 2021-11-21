package cinema.spring.dao.impl;

import cinema.spring.dao.AbstractDao;
import cinema.spring.dao.RoleDao;
import cinema.spring.exception.DataProcessingException;
import cinema.spring.model.Role;
import cinema.spring.model.RoleName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> getByRoleName = session.createQuery(
                    "FROM Role WHERE roleName = :role", Role.class);
            getByRoleName.setParameter("role", RoleName.getValue(roleName));
            return getByRoleName.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("No role found with name: " + roleName, e);
        }
    }
}
