package be.qnh.contact;


import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactApplication.class)
public class IntegrationTest {

    @Autowired
    public ContactService service;

    @Test
    public void testfindAllContactPages(){
        List<ContactPage> allPages= service.findAllContactPages();
        assertThat(allPages).isNotEmpty();
        //assertFalse(allPages.size()==0);
    }
//    @Test
//    public void findAllPersons(){
//
//    }
//    @Test
//    public void findAllSubjects(){
//
//    }
//    @Test
//    public void findBySubject(){
//
//    }
//    @Test
//    public void findContactPageById(){
//
//    }
//    @Test
//    public void findPersonbyId(){
//
//    }
//    @Test
//   public void addContactPage(){
//
//    }
//    @Test
//    public void deleteById(){
//
//    }
//    @Test
//    public void update(){
//
//    }
}
