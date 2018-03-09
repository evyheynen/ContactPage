package be.qnh.contact.repository;

import be.qnh.contact.Model.ContactPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPageRepository extends JpaRepository<ContactPage,Long> {

}
