# LdapSample
Java/Spring Application to check Micro Soft ID belongs to AD Group

DEMO AD Group
 "CN=focus_user_stage,CN=Users,DC=ms,DC=ds,DC=org,DC=com",
 "CN=focus_user_prod,CN=Users,DC=ms,DC=ds,DC=org,DC=com"

# To run the standalone program
1. Change AD group in code
2. Replace the User DN (system uid and pwd)
3. Go to src/main/java/com/org/DEMO/standalone folder, make sure u r replacing the following
   ##env.put(Context.SECURITY_PRINCIPAL, "MS\\msid"); // replace with user DN and pwd
   ##env.put(Context.SECURITY_CREDENTIALS, "secret");

4. javac UserGroupCheckMain.java

5. java UserGroupCheckMain
# Output
Search MSID: cn=bjanard
Name: CN=bjanard,CN=Users
CN=focus_user_stage,CN=Users,DC=ms,DC=ds,DC=org,DC=com
User belongs to AD
SUCCESS




# Spring Example
1. Modify the springldap.xml
2. Go to src/main/java/com/org/DEMO
3. javac LDAPClientSpring.java
4. java LDAPClientSpring

