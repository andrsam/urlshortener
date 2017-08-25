package com.andrsam.response;

/**
 * a response that return in case of opening account
 */
public class OpenAccountResponse {
    /**
     * true, if account successfully registered
     */
    boolean success;

    /**
     * description of status: Your account is opened,
     * if registration success else, Account with that ID already exists
     */
    String description;

    /**
     * The user password
     * Returns only if the account was successfully created.
     * Automatically generated password length of 8 alphanumeric characters
     */
    String password;

    public OpenAccountResponse(boolean success, String description, String password) {
        this.success = success;
        this.description = description;
        this.password = password;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "OpenAccountResponse{" +
                "success=" + success +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
