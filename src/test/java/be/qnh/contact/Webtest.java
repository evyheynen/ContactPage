package be.qnh.contact;

import be.qnh.contact.Model.Address;
import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;
import be.qnh.contact.controller.ContactController;
import be.qnh.contact.service.ContactService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class Webtest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Test
    public void findAllContactPagesTest() throws Exception{
        List<ContactPage> contactPageList = new ArrayList<>();
        ContactPage contactPage = new ContactPage();
        Person person1 = new Person();
        Address addressEvy = new Address();
        Subject subject = Subject.JAVA;

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

        contactPage.setPerson(person1);
        contactPage.setSubject(subject);
        contactPage.setQuestion("How to download JAVA?");

        contactPageList.add(contactPage);

        given(contactService.findAllContactPages()).
                willReturn(contactPageList);

        mockMvc.perform(get("/contactpage")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].question", Matchers.is("How to download JAVA?")))
                .andExpect(jsonPath("$[0].subject",Matchers.is("JAVA")));
//                .andExpect(jsonPath("$[0].person[0].firstName",Matchers.is("Evy")));



    }

}
