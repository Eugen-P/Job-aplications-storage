package job_applications_storage.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import java.util.Date;
import java.time.LocalDate;
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "applied")
    private boolean applied;

    @Column(name = "answered")
    private boolean answered;

    @Column(name = "active")
    private boolean active;

    @Column(name = "correspondence")
    private String correspondence;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "application_date")
    private LocalDate applicationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    public Application() {
    }

    public Application(String name, String description,
                       boolean applied, boolean answered,
                       boolean active, String correspondence,
                       LocalDate applicationDate, Company company) {
        this.name = name;
        this.description = description;
        this.applied = applied;
        this.answered = answered;
        this.active = active;
        this.correspondence = correspondence;
        this.applicationDate = applicationDate;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCorrespondence() {
        return correspondence;
    }

    public void setCorrespondence(String correspondence) {
        this.correspondence = correspondence;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
