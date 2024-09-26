package job_applications_storage.dao;

import job_applications_storage.entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public Application findById(int id) {
        return getSession().get(Application.class, id);
    }

    @Override
    public List<Application> findAll() {
        return getSession().createQuery("from Application").list();
    }

    @Override
    public void save(Application application) {
        getSession().saveOrUpdate(application);
    }

    @Override
    public void deleteById(int id) {
        Application application = getSession().find(Application.class, id);
        getSession().delete(application);
    }
}
