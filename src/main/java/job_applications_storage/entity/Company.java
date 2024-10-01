package job_applications_storage.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Application> applications;

    public Company() {
    }

    public Company(String name, String logo_url, String notes, List<Application> applications) {
        this.name = name;
        this.logoUrl = logo_url;
        this.notes = notes;
        this.applications = applications;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo_url='" + logoUrl + '\'' +
                ", notes='" + notes + '\'' +
                ", applications=" + applications +
                '}';
    }
}
