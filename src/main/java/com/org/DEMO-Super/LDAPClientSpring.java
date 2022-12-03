package com.org.DEMO;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class LDAPClientSpring {

    private static final String[] groupIds = new String[] {
            "CN=focus_user_stage,CN=Users,DC=ms,DC=ds,DC=org,DC=com",
            "CN=focus_user_prod,CN=Users,DC=ms,DC=ds,DC=org,DC=com"
    };
    
    private static String msid="mchindam";

    public static void main(String[] args) {
        try {
            // LdapContextSource contextSource = new LdapContextSource();
            // contextSource.setUrl("ldap://ad-ldap-prod.org.com:389");
            // contextSource.setBase("dc=ms,dc=ds,dc=org,dc=com");
            // contextSource.setUserDn("msid@ms.ds.org.com");
            // contextSource.setPassword("password");
            // contextSource.afterPropertiesSet();
            // LdapTemplate ldapTemplate = new LdapTemplate(contextSource);
            // ldapTemplate.afterPropertiesSet();

            Resource resource = new ClassPathResource("springldap.xml");
            BeanFactory factory = new XmlBeanFactory(resource);

            LDAPContactService ldapContact = (LDAPContactService) factory.getBean("ldapContact");
            List<UserBean> userList = ldapContact.getUserDetails(msid);
            checkUserAccesstoGroup(userList);

        } catch (Exception e) {
            System.out.println("Error occured " + e.getCause());
        }
    }

    private static boolean checkUserAccesstoGroup(List<UserBean> contactList) {
        for (UserBean contactDto : contactList) {
            System.out.println(contactDto);
            List<String> memberOfList = contactDto.getMemberOf();

            if (memberOfList.containsAll(Arrays.asList(groupIds))) {
                System.out.println("User belongs to the group");
                return true;
            }

        }
        System.out.println("User doesnt belongs to the group");
        return false;
    }
}
