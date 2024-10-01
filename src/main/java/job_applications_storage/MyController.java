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

    @PostMapping("/application/save")
    public String saveApplication(@ModelAttribute("app") Application application
            , @ModelAttribute("comp") Company comp
            ,                        @ModelAttribute("newCompany") Company company)
    {
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println(comp);
//        System.out.println("***************************************************************************" +
//                "******************************************************* comp" );
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println(company);
//        System.out.println("***************************************************************************" +
//                "******************************************************* company");
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println(application.getCompany());
//        System.out.println("***************************************************************************" +
//                "******************************************************* application.getCompany()");

        if (company.getName().trim().isEmpty()) {
            application.setCompany(comp);
        }

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

    @PostMapping("/companies/update")
    public String updateCompany(@ModelAttribute("company") Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies";
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
