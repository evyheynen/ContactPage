package be.qnh.contact.repository;

import be.qnh.contact.Model.ContactPage;
import be.qnh.contact.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactPageRepository extends JpaRepository<ContactPage,Long> {

    ContactPage findOne(Long id);
    List<ContactPage> findBySubject(Subject subject);
}
