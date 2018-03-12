package be.qnh.contact.service;

import be.qnh.contact.Model.Address;
import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;
import be.qnh.contact.repository.ContactPageRepository;
import be.qnh.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private final ContactPageRepository contactPageRepository;
    @Autowired
    private final ContactRepository repo;

    public ContactServiceImpl(ContactPageRepository contactRepository, ContactRepository repo) {
        this.contactPageRepository = contactRepository;
        this.repo = repo;
    }

    @Override
    public List<ContactPage> findAllContactPages() {
        return repo.findAllContactPages();
    }

    @Override
    public List<Person> findAllPersons() {
        return repo.findAllPersons();
    }

    @Override
    public List<Subject> findAllSubjects() {
        return repo.findAllSubjects();
    }

    @Override
    public List<ContactPage> findBySubject(Subject subject) {
        return repo.findBySubject(subject);
    }

    @Override
    public ContactPage findContactPageById(Long id) {
        return repo.findContactPageById(id);
    }

    @Override
    public Person findPersonbyId(Long id) {
        return repo.findPersonbyId(id);
    }

    @Override
    public int addContactPage(ContactPage contactPage) {
        return repo.addContactPage(contactPage);
    }

    @Override
    public int deleteById(Long id) {
        return repo.deleteById(id);
    }

    @Override
    public int update(Long id, ContactPage contactPage) {
        return repo.update(id,contactPage);
    }

    @PostConstruct
    public void init(){
        ContactPage contactPage=new ContactPage();
        Person person1=new Person();
        Address addressEvy=new Address();
        Subject subject=Subject.JAVA;

        List<Address> addressList =new ArrayList<>();
        addressList.add(addressEvy);

        addressEvy.setStreet("Heidestraat");
        addressEvy.setNumber("52D");
        addressEvy.setPostalCode("3945");
        addressEvy.setTown("Ham");

        person1.setFirstName("Evy");
        person1.setLastName("Heynen");
        person1.setAddress(addressList);
        person1.setMail("evy.heynen@telenet.be");

        contactPage.setPerson(person1);
        contactPage.setSubject(subject);
        contactPage.setQuestion("How to download JAVA?");

        contactPageRepository.save(contactPage);
        }
    }

