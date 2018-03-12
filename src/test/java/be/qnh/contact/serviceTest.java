package be.qnh.contact;

import be.qnh.contact.Model.Address;
import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;
import be.qnh.contact.repository.ContactRepository;
import be.qnh.contact.service.ContactServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class serviceTest {

    @InjectMocks
    private ContactServiceImpl service;

    @Mock
    private ContactRepository repo;

    List<ContactPage> contactpages = new ArrayList<>();
    List<Person> persons = new ArrayList<>();
    List<Subject> subjects = new ArrayList<>();

    ContactPage contactPage = new ContactPage();
    Person person1 = new Person();
    Address addressEvy = new Address();


    @Before
    public void init() {
        Subject subject = Subject.JAVA;
        subjects.add(subject);

        List<Address> addressList = new ArrayList<>();
        addressList.add(addressEvy);

        addressEvy.setStreet("Heidestraat");
        addressEvy.setNumber("52D");
        addressEvy.setPostalCode("3945");
        addressEvy.setTown("Ham");

        person1.setFirstName("Evy");
        person1.setLastName("Heynen");
        person1.setAddress(addressList);
        person1.setMail("evy.heynen@telenet.be");

        persons.add(person1);

        contactPage.setPerson(person1);
        contactPage.setSubject(subject);
        contactPage.setQuestion("How to download JAVA?");

        contactpages.add(contactPage);
    }

    @Test
    public void testFindAllContactPages() {
        when(this.repo.findAllContactPages()).thenReturn(contactpages);
        Iterable<ContactPage> pageFromService = service.findAllContactPages();
        assertThat((List<ContactPage>) pageFromService).size().isEqualTo(1);
        //test op onderwerp van eerste contactpage in de List customers
        assertThat(((List<ContactPage>) pageFromService).get(0).getSubject()).isEqualTo(Subject.JAVA);
        assertThat(((List<ContactPage>) pageFromService).get(0).getPerson().equals(person1));
        assertThat(((List<ContactPage>) pageFromService).get(0).getQuestion()).isEqualToIgnoringCase("How to download JAVA?");
        verify(repo, times(1)).findAllContactPages();
    }

    @Test
    public void findAllPersons() {
        when(this.repo.findAllPersons()).thenReturn(persons);
        Iterable<Person> personsFromService = service.findAllPersons();
        assertThat((List<Person>) personsFromService).size().isEqualTo(1);
        assertThat(((List<Person>) personsFromService).get(0).getFirstName()).isEqualToIgnoringCase("Evy");
        assertThat(((List<Person>) personsFromService).get(0).getLastName()).isEqualToIgnoringCase("Heynen");
        assertThat(((List<Person>) personsFromService).get(0).getMail()).isEqualToIgnoringCase("evy.heynen@telenet.be");
        assertThat(((List<Person>) personsFromService).get(0).getAddress().equals(addressEvy));
    }

    @Test
    public void findAllSubjects() {
        when(this.repo.findAllSubjects()).thenReturn(subjects);
        Iterable<Subject> subjectFromService = service.findAllSubjects();
        assertThat(((List<Subject>) subjectFromService).size()).isEqualTo(1);
    }

    @Test
    public void findBySubject() {
        when(this.repo.findBySubject(Subject.JAVA)).thenReturn(contactpages);
        Iterable<ContactPage> pageFromService = service.findBySubject(Subject.JAVA);
        assertThat(pageFromService).size().isEqualTo(1);
        assertThat(((List<ContactPage>) pageFromService).get(0).getPerson()).isEqualTo(person1);
        assertThat(((List<ContactPage>) pageFromService).get(0).getQuestion()).isEqualToIgnoringCase("How to download JAVA?");
    }

    @Test
    public void findContactPageById() {
        when(this.repo.findContactPageById(1L)).thenReturn(contactPage);
        ContactPage pageFromService = service.findContactPageById(1L);
        assertThat(((ContactPage) pageFromService).getSubject().equals(Subject.JAVA));
        assertThat(((ContactPage) pageFromService).getQuestion()).isEqualToIgnoringCase("How to download JAVA?");
        assertThat(((ContactPage) pageFromService).getPerson()).isEqualTo(person1);
    }

    @Test
    public void findPersonbyId() {
        when(this.repo.findPersonbyId(1L)).thenReturn(person1);
        Person personFromService = service.findPersonbyId(1L);
        assertThat(((Person) personFromService).getFirstName()).isEqualToIgnoringCase("Evy");
        assertThat(((Person) personFromService).getLastName()).isEqualToIgnoringCase("Heynen");
        assertThat(((Person) personFromService).getMail()).isEqualToIgnoringCase("evy.heynen@telenet.be");
        assertThat(((Person) personFromService).getAddress().get(0)).isEqualTo(addressEvy);
    }

    @Test
    public void addContactPage() {
        when(this.repo.addContactPage(contactPage)).thenReturn(1);
        int addedPage = service.addContactPage(contactPage);
        assertThat(addedPage).isNotNull();
        verify(repo, times(1)).addContactPage(contactPage);
    }

    @Test
    public void delete() {
        when(this.repo.deleteById(1L)).thenReturn(1);
        int deletedPage = service.deleteById(1L);
        assertThat(deletedPage).isEqualTo(1);
    }

//    @Test
//    public void update() {
//        when(repo.findContactPageById(1L)).thenReturn(contactPage);
//        when(repo.addContactPage(contactPage)).thenReturn(1);
//        int updatedPage = service.update(1L, contactPage);
//        System.out.println("Updated page = " + updatedPage);
//        assertThat(updatedPage).isEqualTo(1);
//        verify(repo, times(1)).findContactPageById(1L);
//        verify(repo, times(1)).addContactPage(contactPage);
//    }
}


