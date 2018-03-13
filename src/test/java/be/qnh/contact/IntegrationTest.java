package be.qnh.contact;


import be.qnh.contact.Model.Address;
import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;
import be.qnh.contact.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactApplication.class)
public class IntegrationTest {

    @Autowired
    public ContactService service;

    @Test
    public void testFindAllContactPages(){
        List<ContactPage> allPages= service.findAllContactPages();
        assertThat(allPages).isNotEmpty();
    }
    @Test
    public void testFindAllPersons(){
        List<Person> allPersons= service.findAllPersons();
        assertThat(allPersons).isNotEmpty();
    }
    @Test
    public void testFindAllSubjects(){
        List<Subject> allSubjects= service.findAllSubjects();
        assertThat(allSubjects).isNotEmpty();
    }
    @Test
    public void testFindBySubject(){
        List<ContactPage> foundPage= service.findBySubject(Subject.JAVA);
        assertThat(foundPage).isNotEmpty();
    }
    @Test
    public void testFindContactPageById(){
        ContactPage page=service.findContactPageById(1L);
        assertThat(page).isNotNull();
    }
//    @Test
//    public void testFindPersonbyId(){
//        Person foundPerson=service.findPersonbyId(1L);
//        assertThat(foundPerson).isNotNull();
//    }
    @Test
   public void testAddContactPage(){
        ContactPage contactPage=new ContactPage();
        Person personTest=new Person();
        Address addressTest=new Address();
        Subject subject=Subject.HIBERNATE;

        List<Address> addressList =new ArrayList<>();
        addressList.add(addressTest);

        addressTest.setStreet("Strawinskylaan");
        addressTest.setNumber("7");
        addressTest.setPostalCode("2960");
        addressTest.setTown("Brecht");

        personTest.setFirstName("Carina");
        personTest.setLastName("Aertgeerts");
        personTest.setAddress(addressList);
        personTest.setMail("carina.aertgeerts@telenet.be");

        contactPage.setPerson(personTest);
        contactPage.setSubject(subject);
        contactPage.setQuestion("Why use Hibernate?");

        int i = service.addContactPage(contactPage);
        assertThat(i).isEqualTo(1);
    }
    @Test
    public void testDeleteById(){
        int i= service.deleteById(1L);
        //assertNull(service.findContactPageById(4L));
        assertThat(i).isEqualTo(1);
}
    @Test
    public void update(){
        ContactPage contactPage=new ContactPage();
        Person personTest=new Person();
        Address addressTest=new Address();
        Subject subject=Subject.HIBERNATE;

        List<Address> addressList =new ArrayList<>();
        addressList.add(addressTest);

        addressTest.setStreet("Strawinskylaan");
        addressTest.setNumber("7");
        addressTest.setPostalCode("2960");
        addressTest.setTown("Brecht");

        personTest.setFirstName("Carina");
        personTest.setLastName("Aertgeerts");
        personTest.setAddress(addressList);
        personTest.setMail("carina.aertgeerts@telenet.be");

        contactPage.setPerson(personTest);
        contactPage.setSubject(subject);
        contactPage.setQuestion("Why use Hibernate?");

        int i=service.update(1L, contactPage);
        assertThat(i).isEqualTo(1);
    }
}
