package id.restful.springboot_contact_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String usernameUser;
}
