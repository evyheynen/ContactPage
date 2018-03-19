package be.qnh.contact;

import be.qnh.contact.Model.Address;
import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;
import be.qnh.contact.controller.ContactController;
import be.qnh.contact.service.ContactService;
import org.hamcrest.Matchers;
import org.junit.Before;
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

    @Before
    public void init(){
        List<ContactPage> contactPageList = new ArrayList<>();
        List<Person> persons=new ArrayList<>();
        List<Subject> subjects=new ArrayList<>();

        ContactPage contactPage = new ContactPage();
        Person person1 = new Person();
        Address addressEvy = new Address();

        Subject java = Subject.JAVA;
        subjects.add(java);

        List<Address> addressList = new ArrayList<>();
        addressList.add(addressEvy);

        addressEvy.setStreet("Heidestraat");
        addressEvy.setNumber("52D");
        addressEvy.setPostalCode("3945");
        addressEvy.setTown("Ham");

        person1.setFirstName("Valerie");
        person1.setLastName("Heynen");
        person1.setAddress(addressList);
        person1.setMail("evy.heynen@telenet.be");
        persons.add(person1);

        contactPage.setPerson(person1);
        contactPage.setSubject(java);
        contactPage.setQuestion("How to download JAVA?");

        contactPageList.add(contactPage);

        given(contactService.findAllContactPages()).
                willReturn(contactPageList);
    }

    @Test
    public void findAllContactPagesTest() throws Exception{
        mockMvc.perform(get("/contactpage")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].question", Matchers.is("How to download JAVA?")))
                .andExpect(jsonPath("$[0].subject",Matchers.is("JAVA")))
                .andExpect(jsonPath("$[0].person.firstName",Matchers.is("Valerie")));
    }

    @Test
    public void findAllPersonsTest() throws Exception{
        mockMvc.perform(get("/contactpage/person")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*",Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName",Matchers.is("Valerie")))
                .andExpect(jsonPath("$[0].lastName",Matchers.is("Heynen")))
                .andExpect(jsonPath("$[0].mail",Matchers.is("evy.heynen@telenet.be")))
                .andExpect(jsonPath("$[0].address[0].street",Matchers.is("Heidestraat")))
                .andExpect(jsonPath("$[0].address[0].number",Matchers.is("52D")));
    }

    @Test
    public void findAllSubjectsTest() throws Exception{
        mockMvc.perform(get("/contactpage/subject")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*",Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0]",Matchers.is("JAVA")));
    }

    @Test
    public void findPageBySubject()throws Exception{
        mockMvc.perform(get("/contactpage/subject/JAVA")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*",Matchers.hasSize(1)))
                .andExpect(jsonPath("$.[0].id",Matchers.is("1")));
    }

    @Test
    public void findPageByIdTest() throws Exception{
        mockMvc.perform(get("/contactpage/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*",Matchers.hasSize(1)))
                .andExpect(jsonPath("$.subject", Matchers.is("JAVA")))
                .andExpect(jsonPath("$.question",Matchers.is("How to download JAVA")))
                .andExpect(jsonPath("$.person.firstName", Matchers.is("Valerie")));
                }

    @Test
    public void findPersonByIdTest() throws Exception{
        mockMvc.perform(get("/contactpage/person/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*",Matchers.hasSize(1)))
                .andExpect(jsonPath("$.firstName",Matchers.is("Valerie")))
                .andExpect(jsonPath("$.lastName",Matchers.is("Heynen")))
                .andExpect(jsonPath("$.mail", Matchers.is("evy.heynen@telenet.be")))
                .andExpect(jsonPath("$.address[0].street",Matchers.is("Heidestraat")))
                .andExpect(jsonPath("$.address[0].number",Matchers.is("52D")));
                    }
//     @Test
//    public void addContactPageTest() throws Exception{
////    String jsonadd= "{"id":1,"person":{"firstName":"Evy","lastName":"Heynen",
////                 "mail":"evy.heynen@telenet.be","address":[{"street":"Heidestraat",
////                 "number":"52D","postalCode":"3945","town":"Ham"}],
////                 "question":"How to download JAVA?","subject":"JAVA"}";
//
//        mockMvc.perform(post("/contactpage")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(jsonadd)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.subject",Matchers.is("JAVA")))
//                .andExpect(jsonPath("$.question",Matchers.is("How to download JAVA?")));
//    }

//    @Test
//    public void deleteByIdTest() throws Exception{
//        mockMvc.perform(delete("/contactpage/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void updateTest() throws Exception{
//        mockMvc.perform(put("/contactpage/1",contactPage)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}
