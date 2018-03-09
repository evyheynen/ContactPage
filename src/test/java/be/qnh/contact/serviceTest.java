package be.qnh.contact;

import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ContactApplicationTests.class)
public class serviceTest {

    @Autowired
    private ContactService service;

    @Test
    public void testFindAllContactPages(){
        List<ContactPage> list=service.findAllContactPages();
        assertThat(list).isNotNull();
    }
}
