package ua.nure.ohol.SummaryTask4.db.beans;

public class Users extends Entity {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean validUser;
    private int roleId;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isValidUser() {
        return validUser;
    }

    public void setValidUser(boolean validUser) {
        this.validUser = validUser;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Users [ login = " + login +
                ", password = " + password +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", roleId = " + roleId +
                ", validUser = " + validUser +
                ", phone = " + phone +
                ", email = " + email +
                ", getId() = " + super.getId();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
