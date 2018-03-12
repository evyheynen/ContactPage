package be.qnh.contact.service;

import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;

import java.util.List;

public interface ContactService {

    List<ContactPage> findAllContactPages();
    List<Person> findAllPersons();
    List<Subject> findAllSubjects();
    List<ContactPage> findBySubject(Subject subject);

    ContactPage findContactPageById(Long id);
    Person findPersonbyId(Long id);

    int addContactPage(ContactPage contactPage);
    int deleteById(Long id);
    int update(Long id, ContactPage contactPage);

}
