package com.engsoft.atdd.domain.models.valueobjects;

import com.engsoft.atdd.infraestructure.utils.PasswordEncoderUtil;

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
