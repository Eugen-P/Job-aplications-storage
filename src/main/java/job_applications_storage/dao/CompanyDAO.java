package job_applications_storage.dao;




import job_applications_storage.entity.Company;

import java.util.List;

public interface CompanyDAO {
    Company findById(int id);
    List<Company> findAll();
    void save(Company company);
    void deleteById(int id);
    Company findByName(String name);
}
