package com.andrsam.response;

public class OpenAccountResponse {
    boolean success;
    String description;
    String password;

    public OpenAccountResponse(boolean success, String description, String password) {
        this.success = success;
        this.description = description;
        this.password = password;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
