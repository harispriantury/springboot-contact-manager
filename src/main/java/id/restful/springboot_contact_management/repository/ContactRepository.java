package id.restful.springboot_contact_management.repository;

import id.restful.springboot_contact_management.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact , String> {
}
