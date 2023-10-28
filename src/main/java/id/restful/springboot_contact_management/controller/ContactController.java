package id.restful.springboot_contact_management.controller;

import id.restful.springboot_contact_management.dto.request.ContactCreateRequest;
import id.restful.springboot_contact_management.dto.request.ContactUpdateRequest;
import id.restful.springboot_contact_management.dto.response.ContactResponse;
import id.restful.springboot_contact_management.dto.response.WebResponse;
import id.restful.springboot_contact_management.entity.Contact;
import id.restful.springboot_contact_management.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping(
            path = "/contacts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Object> getAll() {
        List<ContactResponse> contacts = contactService.getAll();
        return WebResponse.builder()
                .data(contacts)
                .build();
    }

    @PostMapping(
            path = "/contacts",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Object> save(@RequestBody ContactCreateRequest request, @RequestHeader(name = "X-API-TOKEN") String token) {
        Contact contact = contactService.save(request, token);
        return WebResponse.builder()
                .data(contact.getFirstName() + " success created")
                .build();
    }

    @PatchMapping(
            path = "/contacts/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Object> update(@RequestBody ContactUpdateRequest request, @PathVariable String id) {
        ContactResponse contact = contactService.update(request, id);
        return WebResponse.builder()
                .data(contact)
                .build();
    }
}
