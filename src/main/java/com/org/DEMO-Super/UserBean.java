package com.org.DEMO;

import java.util.List;

public class UserBean {

    String       commonName;

    String       firstName;

    String       lastName;

    String       description;

    String       cn;

    List<String> memberOf;

    String       emailId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        StringBuffer contactDTOStr = new StringBuffer("User Details=[");
        contactDTOStr.append(" Common Name = " + commonName);
        contactDTOStr.append(" First Name = " + firstName);
        contactDTOStr.append(" Last Name = " + lastName);

        contactDTOStr.append(", Email = " + emailId);
        contactDTOStr.append(", Description = " + description);
        contactDTOStr.append(" ]");
        return contactDTOStr.toString();
    }

    public List<String> getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(List<String> memberOf) {
        this.memberOf = memberOf;
    }

}
