package be.qnh.contact.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String mail;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL)
    private List<ContactPage> contactPage=new ArrayList<>();

    //controllers
    public Person() {
    }
    public Person(String firstName, String lastName, String mail, List<Address> address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.address = address;
    }

    //getters and setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public List<Address> getAddress() {
        return address;
    }
    public void setAddress(List<Address> address) {
        this.address = address;
    }
    public List<ContactPage> getContactPage() {
        return contactPage;
    }
    public void setContactPage(List<ContactPage> contactPage) {
        this.contactPage = contactPage;
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", address=" + address +
                '}';
    }
}

