package be.qnh.contact;

import be.qnh.contact.controller.ContactController;
import be.qnh.contact.service.ContactService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class Webtest {

    private MockMvc mockMvc;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @Before
    public void intit(){
        mockMvc=MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void findAllContactPagesTest() throws Exception{
        mockMvc.perform(get("/contactpage")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0)));

    }

//    ContactPage contactPage=new ContactPage();
//    Person person1=new Person();
//    Address addressEvy=new Address();
//    Subject subject=Subject.JAVA;
//
//    List<Address> addressList =new ArrayList<>();
//        addressList.add(addressEvy);
//
//        addressEvy.setStreet("Heidestraat");
//        addressEvy.setNumber("52D");
//        addressEvy.setPostalCode("3945");
//        addressEvy.setTown("Ham");
//
//        person1.setFirstName("Evy");
//        person1.setLastName("Heynen");
//        person1.setAddress(addressList);
//        person1.setMail("evy.heynen@telenet.be");
//
//        contactPage.setPerson(person1);
//        contactPage.setSubject(subject);
//        contactPage.setQuestion("How to download JAVA?");
}
