package com.engsoft.atdd.domain.models;

import com.engsoft.atdd.domain.models.valueobjects.Email;
import com.engsoft.atdd.domain.models.valueobjects.TaxId;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

	@Embedded
    @Column(unique = true)
    private Email email;

    @Embedded
    @Column(unique = true)
    private TaxId taxId;

	private String password; // TODO: encrypt password before persist in databse.

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
	
    public User(Long id, String name, Email email, TaxId taxId, String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.taxId = taxId;
        this.password = password;
    }

    public User(String name, Email email, TaxId taxId, String password) {
        super();
        this.name = name;
        this.email = email;
        this.taxId = taxId;
        this.password = password;
    }

    public User() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public TaxId getTaxId() {
        return taxId;
    }

    public void setTaxId(TaxId taxId) {
        this.taxId = taxId;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((plan == null) ? 0 : plan.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (plan == null) {
            if (other.plan != null)
                return false;
        } else if (!plan.equals(other.plan))
            return false;
        return true;
    }
    
}
