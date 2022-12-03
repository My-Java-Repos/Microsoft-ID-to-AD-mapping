package com.org.DEMO;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

public class UserAttributeMapper implements AttributesMapper {

    public Object mapFromAttributes(Attributes attributes) throws NamingException {

        UserBean user = new UserBean();
        user.setCommonName((String) attributes.get("cn").get());
        List<String> memberOf = new ArrayList<String>();

        for (Enumeration<?> vals = attributes.get("memberOf").getAll(); vals.hasMoreElements();) {
            memberOf.add((String) vals.nextElement());
        }
        user.setMemberOf(memberOf);

        String email = (String) attributes.get("mail").get();
        user.setEmailId(email);
        Attribute description = attributes.get("description");
        if (description != null)
            user.setDescription((String) description.get());
        user.setLastName((String) attributes.get("sn").get());

        user.setFirstName((String) attributes.get("givenname").get());

        System.out.println(email);

        return user;

    }

}
