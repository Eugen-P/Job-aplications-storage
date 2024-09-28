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
    public String show(Model model) {
        List<Application> applications = applicationService.getAllApplications();
        model.addAttribute("applications", applications);
        return "index";
    }

    @GetMapping("/applications/{id}")
    public String showApplicationDetails(@PathVariable("id") int id , Model model) {
        Application application = applicationService.getApplicationById(id);
        model.addAttribute("app", application);
        return "application-info";
    }

    @GetMapping("/applications/new")
    public String addNewApplication(Model model) {
        Application app = new Application();
        model.addAttribute("app", app);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "application-edit";
    }

    @PostMapping("/application/save")
    public String saveApplication(@ModelAttribute("app") Application application
            ,                        @ModelAttribute("company") Company company
            )
    {
//        if (newCompany != null && !newCompany.trim().isEmpty()) {
//            Company company = new Company();
//            company.setName(newCompany);
//            companyService.saveCompany(company);
//            application.setCompany(company); // Устанавливаем новую компанию
//        }
//        companyService.saveCompany(company);
        applicationService.saveApplication(application);

        return "redirect:/applications";
    }

    @GetMapping("applications/edit/{id}")
    public String editApplication(@PathVariable("id") int id, Model model ) {
        Application app = applicationService.getApplicationById(id);
        model.addAttribute("app", app);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "application-edit";
    }

    @PostMapping("/application/update")
    public String updateApplication(@ModelAttribute("app") Application application) {
        applicationService.saveApplication(application);
        return "redirect:/applications";
    }

}
