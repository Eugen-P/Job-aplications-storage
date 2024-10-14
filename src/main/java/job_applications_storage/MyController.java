package job_applications_storage;


import job_applications_storage.entity.Application;
import job_applications_storage.entity.Company;
import job_applications_storage.services.ApplicationService;
import job_applications_storage.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class MyController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/applications")
    public String showApplications(Model model) {
        List<Application> applications = applicationService.getAllApplications();
        applications.sort((app1, app2) -> Boolean.compare(!app1.isApplied(), !app2.isApplied()));
        model.addAttribute("applications", applications);
        return "index";
    }

    @GetMapping("/companies")
    public String showCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "companies";
    }

    @GetMapping("/applications/{id}")
    public String showApplicationDetails(@PathVariable("id") int id , Model model) {
        Application application = applicationService.getApplicationById(id);
        model.addAttribute("app", application);
        return "application-info";
    }

    @GetMapping("companies/{id}")
    public String showCompanyDetails(@PathVariable("id") int id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "company-info";
    }

    @GetMapping("/applications/new")
    public String addNewApplication(Model model) {
        Application app = new Application();
        model.addAttribute("app", app);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "application-edit";
    }

    @PostMapping("/applications/save")
    public String saveApplication(@ModelAttribute("app") Application application) {
        Company existingCompany = companyService.getCompanyByName(application.getCompany().getName());
        if (existingCompany != null) {
            application.setCompany(existingCompany);
        } else {
            companyService.saveCompany(application.getCompany());
        }
        applicationService.saveApplication(application);
        return "redirect:/applications/" + application.getId();
    }

    @PostMapping("companies/save")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies/" + company.getId();
    }


    @GetMapping("applications/edit/{id}")
    public String editApplication(@PathVariable("id") int id, Model model ) {
        Application app = applicationService.getApplicationById(id);
        model.addAttribute("app", app);
//        model.addAttribute("companies", companyService.getAllCompanies());
        return "application-edit";
    }

    @GetMapping("companies/edit/{id}")
    public String editCompany(@PathVariable("id") int id, Model model ) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "company-edit";
    }

    @PostMapping("/applications/{id}/delete")
    public String deleteApplication(@PathVariable("id") int id) {
        applicationService.deleteApplication(id);
        return "redirect:/applications";
    }

    @PostMapping("/companies/{id}/delete")
    public String deleteCompany(@PathVariable("id") int id) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }
}
