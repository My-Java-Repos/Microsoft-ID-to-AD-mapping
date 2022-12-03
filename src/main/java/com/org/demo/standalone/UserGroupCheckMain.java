package com.org.DEMO.standalone;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.*;

/**
 * List all attributes for a single user AND List all the groups the user is a
 * member of
 */

public class UserGroupCheckMain {

	String searchBase = "dc=ms,dc=ds,dc=org,dc=com";
	DirContext ctx;

	public static void main(String[] args) {

		Hashtable<String, String> env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.REFERRAL, "follow");

		env.put(Context.PROVIDER_URL, "ldap://ad-ldap-prod.org.com:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "MS\\msid"); // replace with user DN
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		String user = "bjanard";
		new UserGroupCheckMain(user, env);
	}

	public UserGroupCheckMain(String user, Hashtable<String, String> env) {
		try {
			ctx = new InitialDirContext(env);
			processUser(user, ctx);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void processUser(String uid, DirContext ctx) {

		String filter;
		String member;
		filter = "cn=" + uid;
		System.out.println("Search filter: " + filter);
		SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

		try {
			NamingEnumeration<?> results = ctx.search(searchBase, filter, constraints);

			// *** Really only one expected
			while (results.hasMore()) {
				SearchResult si = (SearchResult) results.next();
				member = si.getName();
				System.out.println("Name: " + member);

				// Show members of group
				Attributes attrs = si.getAttributes();
				if (attrs == null) {
					System.out.println("No attributes for group");
					continue;
				}

				for (NamingEnumeration<?> ae = attrs.getAll(); ae.hasMoreElements();) {
					Attribute attr = (Attribute) ae.next();
					String id = attr.getID();
					
					for (Enumeration vals = attrs.get("memberOf").getAll(); vals.hasMoreElements();) {
						Object valueEntry = ((String) vals.nextElement());

						if (valueEntry.equals("CN=focus_user_stage,CN=Users,DC=ms,DC=ds,DC=org,DC=com")) {
							System.out.println(valueEntry);

							System.out.println("SUCCESS");
							return;
						}
					}

				}
			}
		} catch (NamingException e) {
			System.out.println("Search Failed " + e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();

		}
	}

}
