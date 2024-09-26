package job_applications_storage.services;

import job_applications_storage.dao.CompanyDAOImpl;
import job_applications_storage.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDAOImpl companyDAO;
    @Transactional
    public List<Company> getAllCompanies() {
        return companyDAO.findAll();
    }
    @Transactional
    public void saveCompany(Company company) {
        companyDAO.save(company);
    }
    @Transactional
    public Company getCompanyById(int id) {
        return companyDAO.findById(id);
    }
    @Transactional
    public void deleteCompany(int id) {
        companyDAO.deleteById(id);
    }
}
