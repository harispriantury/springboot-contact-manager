package id.restful.springboot_contact_management.service;

import id.restful.springboot_contact_management.dto.request.ContactCreateRequest;
import id.restful.springboot_contact_management.dto.request.ContactUpdateRequest;
import id.restful.springboot_contact_management.dto.response.ContactResponse;
import id.restful.springboot_contact_management.entity.Contact;
import id.restful.springboot_contact_management.entity.User;
import id.restful.springboot_contact_management.repository.ContactRepository;
import id.restful.springboot_contact_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    public List<ContactResponse> getAll() {
        List<Contact> all = contactRepository.findAll();
        List<ContactResponse> contactResponses = all.stream().map(i -> {
            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setId(i.getId());
            contactResponse.setFirstName(i.getFirstName());
            contactResponse.setLastName(i.getLastName());
            contactResponse.setEmail(i.getEmail());
            contactResponse.setPhoneNumber(i.getPhoneNumber());

            return contactResponse;
        }).collect(Collectors.toList());
        return contactResponses;
    }

    @Transactional(readOnly = true)
    public Contact save(ContactCreateRequest request, String token) {
        validationService.validate(request);

        //find user
        User user = userRepository.findFirstByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized"));

        Contact contact = new Contact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setUser(user);
        contactRepository.save(contact);
        return contact;
    }

    @Transactional
    public ContactResponse update(ContactUpdateRequest request, String id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "id not found"));
        if (Objects.nonNull(request.getFirstName())) {
            contact.setFirstName(request.getFirstName());
        }
        if (Objects.nonNull(request.getLastName())) {
            contact.setLastName(request.getLastName());
        }
        if (Objects.nonNull(request.getEmail())) {
            contact.setEmail(request.getEmail());
        }
        if (Objects.nonNull(request.getPhoneNumber())) {
            contact.setEmail(request.getPhoneNumber());
        }
        contactRepository.save(contact);
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(contact.getId());
        contactResponse.setFirstName(contact.getFirstName());
        contactResponse.setLastName(contact.getLastName());
        contactResponse.setEmail(contact.getEmail());
        contactResponse.setPhoneNumber(contact.getPhoneNumber());
        contactResponse.setUsernameUser(contact.getUser().getUsername());
        return contactResponse;
    }
}
