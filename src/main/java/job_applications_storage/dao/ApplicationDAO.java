package job_applications_storage.dao;

import job_applications_storage.entity.Application;

import java.util.List;

public interface ApplicationDAO {
    Application findById(int id);
    List<Application> findAll();
    void save(Application application);
    void deleteById(int id);

    void deleteByCompanyId(int id);
}
