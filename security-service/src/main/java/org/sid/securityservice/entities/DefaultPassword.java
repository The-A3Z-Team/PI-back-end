package org.sid.securityservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@Data
public class DefaultPassword {

    private String password;
    private BCryptPasswordEncoder encoder;
    public String getEncodedPassword(){
        return encoder.encode(this.password);
    }
}
