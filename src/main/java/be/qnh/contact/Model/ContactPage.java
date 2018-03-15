package be.qnh.contact.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ContactPage  implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne (cascade=CascadeType.ALL)
    private Person person;

    private String question;
    @Enumerated(EnumType.STRING)
    private Subject subject;


    //constructors
    public ContactPage(){ }
    public ContactPage(Person person,Subject subject,String question) {
        this.question = question;
        this.subject = subject;
        this.person = person;
    }

    //getters and setters
    public Long getId() {
        return id; }
    public void setId(Long id) {
        this.id = id; }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "ContactPage{" +
                ", person=" + person +
                ", subject=" + subject +
                "question='" + question + '\'' +
                '}';
    }
}
