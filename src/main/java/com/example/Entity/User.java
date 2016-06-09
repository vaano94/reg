package com.example.Entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User entity.
 */
@Entity
public class User implements Serializable {
    /** Minimum password length.
     */
    private static final int MIN_PASS_LENGTH = 6;
    /** Maximum password length.
     */
    private static final int MAX_PASS_LENGTH = 12;
    /**
     * User email.
     */
    @NotNull
    @NotBlank
    @Email
    @Column
    private String email;
    /**
     * User id.
     */
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * User password. Must match the given regex
     */
    @NotNull
    @NotBlank
    @Size(min = MIN_PASS_LENGTH, max = MAX_PASS_LENGTH)
    @Pattern(regexp = "^(?=.*[!])(?=(.*\\d){2}).*$", message = "Password code validation failed.")
    @Column
    private String password;

    /**
     * User confirmation status.
     */
    private boolean isConfirmed;

    /**
     * Return user email.
     * @return String email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user email.
     * @param email String email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Return user password.
     * @return String password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password.
     * @param password String password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return user confirmation status.
     * @return boolean isConfirmed
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Set user confirmation status.
     * @param confirmed boolean isConfirmed
     */
    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
    /**
     * Return user email.
     * @return String email.
     */
    public int getId() {
        return id;
    }

    /**
     * Set user id. Not recommended because of auto generation.
     * @param id int id
     */
    public void setId(int id) {
        this.id = id;
    }
}
