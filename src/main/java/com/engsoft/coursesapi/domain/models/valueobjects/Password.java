package com.engsoft.coursesapi.domain.models.valueobjects;

import com.engsoft.coursesapi.infraestructure.utils.PasswordEncoderUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Password {
    
    @Column(name = "password")
    private String value;

    private Password(String value) {
        this.value = value;
    }
    
    public Password() { }

    public static Password fromString(String password) {
        String encryptedPassword = PasswordEncoderUtil.encryptPassword(password);
        return new Password(encryptedPassword);
    }

    public String getValue() {
        return value;
    }

}
