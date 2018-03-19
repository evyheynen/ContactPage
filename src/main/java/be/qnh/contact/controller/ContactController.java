package be.qnh.contact.controller;

import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Person;
import be.qnh.contact.Model.Subject;
import be.qnh.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactpage")
public class ContactController {

    @Autowired
    private ContactService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAllContactPages(){
        return ResponseEntity.ok(service.findAllContactPages());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/person")
    public ResponseEntity findAllPersons(){
        return ResponseEntity.ok(service.findAllPersons());
    }

    @GetMapping("/subject")
    public ResponseEntity findAllSubjecs(){
        return ResponseEntity.ok(service.findAllSubjects());
    }

    @RequestMapping(value = "/subject/{subject}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ContactPage>> findPageBySubject(@PathVariable Subject subject) {
        List<ContactPage> result = this.service.findBySubject(subject);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ContactPage> findPageById(@PathVariable Long id) {
        ContactPage result = this.service.findContactPageById(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> findPersonById(@PathVariable Long id) {
        Person result = this.service.findPersonbyId(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addContactPage(@RequestBody ContactPage contactPage){
        return ResponseEntity.ok(service.addContactPage(contactPage));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, ContactPage contactPage){
        return ResponseEntity.ok(service.update(id, contactPage));
    }

}
