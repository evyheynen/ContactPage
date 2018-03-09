package be.qnh.contact.repository;

import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;

import java.util.List;

public interface ContactRepository {

    List<ContactPage> findAllContactPages();
    List<Person> findAllPersons();
    List<Subject> findAllSubjects();

    ContactPage findContactPageById(Long id);
    Person findPersonbyId(Long id);
    Subject findSubject(Subject subject);

    int addContactPage(ContactPage contactPage);
    int delete(ContactPage contactPage);
    int update(Long id, ContactPage contactPage);

}

