package job_applications_storage.dao;

import job_applications_storage.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Company findById(int id) {
        return getSession().get(Company.class, id);
    }

    @Override
    public List<Company> findAll() {
        return getSession().createQuery("from Company", Company.class).list();
    }

    @Override
    public void save(Company company) {
        getSession().saveOrUpdate(company);
    }

    @Override
    public void deleteById(int id) {
        Company company = getSession().find(Company.class, id);
        getSession().delete(company);
    }

    @Override
    public Company findByName(String name) {
        try {
            return getSession()
                    .createQuery("FROM Company WHERE name = :name", Company.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
