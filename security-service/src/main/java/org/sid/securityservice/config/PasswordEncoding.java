package org.sid.securityservice.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class PasswordEncoding {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String getEncodedPassword(String password){
        return encoder.encode(password);
    }
}
