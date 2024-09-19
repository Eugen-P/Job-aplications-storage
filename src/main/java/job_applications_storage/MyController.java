package job_applications_storage;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private SessionFactory sessionFactory;
    @Transactional
    @RequestMapping("/")
    public String show(Model model) {
        Session session = sessionFactory.getCurrentSession();

        List<test> list = session.createQuery("from test").getResultList();
        model.addAttribute("list", list);

        return "index";
    }
}
