package be.qnh.contact.repository;

import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private final ContactPageRepository contactPageRepository;
    @Autowired
    private final PersonRepository personRepository;

    private ContactPage contactPage=new ContactPage();

    public ContactRepositoryImpl(ContactPageRepository contactPageRepository, PersonRepository personRepository) {
        this.contactPageRepository = contactPageRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<ContactPage> findAllContactPages() {
        List<ContactPage> contactPages= contactPageRepository.findAll();
        return contactPages;
    }

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public List<Subject> findAllSubjects() {
        Query query= entityManager.createQuery("select i.subject from ContactPage as i");
        return query.getResultList();
    }
    @Override
    public List<ContactPage> findBySubject(Subject subject) {
        return contactPageRepository.findBySubject(subject);
    }

    @Override
    public ContactPage findContactPageById(Long id) {
        return contactPageRepository.findOne(id);
    }

    @Override
    public Person findPersonbyId(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public int addContactPage(ContactPage contactPage) {
        ContactPage page= contactPageRepository.save(contactPage);
        return page!=null?1:0;
    }

    @Override
    public int deleteById(Long id) {
        contactPageRepository.deleteById(id);
        return 1;
    }

    @Override
    public int update(Long id, ContactPage contactPage) {
        ContactPage contactPage1=contactPageRepository.getOne(id);
        if(contactPage1!=null) {
            contactPage1.setSubject(contactPage.getSubject());
            contactPage1.setQuestion(contactPage.getQuestion());
            contactPage1.setPerson(contactPage.getPerson());
            contactPageRepository.save(contactPage1);
            return 1;
        }
        else{
            return 0;
        }
    }
}
