package com.engsoft.atdd.domain.models.valueobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TaxId {
    
    private static final String TAXID_REGEX = "^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$";
    private static final Pattern TAXID_PATTERN = Pattern.compile(TAXID_REGEX);

    @Column(name = "taxId")
    private String value;

    private TaxId(String value) {
        this.value = value;
    }
    
    public TaxId() { }

    public static TaxId fromString(String TaxId) {
    	validate(TaxId);
        return new TaxId(TaxId);
    }

    public String getValue() {
        return value;
    }

    private static void validate(String TaxId) {
        Matcher matcher = TAXID_PATTERN.matcher(TaxId);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid taxid format");
        }
    }

}
