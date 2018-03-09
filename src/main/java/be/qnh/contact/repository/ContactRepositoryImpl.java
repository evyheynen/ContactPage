package be.qnh.contact.repository;

import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private final ContactPageRepository contactPageRepository;
    @Autowired
    private final PersonRepository personRepository;

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
        return null;
    }

    @Override
    public List<Subject> findAllSubjects() {
        return null;
    }

    @Override
    public ContactPage findContactPageById(Long id) {
        return null;
    }

    @Override
    public Person findPersonbyId(Long id) {
        return null;
    }

    @Override
    public Subject findSubject(Subject subject) {
        return null;
    }

    @Override
    public int addContactPage(ContactPage contactPage) {
        return 0;
    }

    @Override
    public int delete(ContactPage contactPage) {
        return 0;
    }

    @Override
    public int update(Long id, ContactPage contactPage) {
        return 0;
    }
}
