package job_applications_storage.services;

import job_applications_storage.dao.ApplicationDAOImpl;
import job_applications_storage.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationDAOImpl applicationDAO;
    @Transactional
    public List<Application> getAllApplications() {
        return applicationDAO.findAll();
    }
    @Transactional
    public Application getApplicationById(int id) {
        return applicationDAO.findById(id);
    }
    @Transactional
    public void saveApplication(Application application) {
        applicationDAO.save(application);
    }
    @Transactional
    public void deleteApplication(int id) {
        applicationDAO.deleteById(id);
    }
}
