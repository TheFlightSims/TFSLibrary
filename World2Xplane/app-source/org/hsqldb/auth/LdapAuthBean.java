package org.hsqldb.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import org.hsqldb.lib.FrameworkLogger;

public class LdapAuthBean implements AuthFunctionBean {
  private static FrameworkLogger logger = FrameworkLogger.getLog(LdapAuthBean.class);
  
  private Integer ldapPort;
  
  private String ldapHost;
  
  private String principalTemplate;
  
  private String saslRealm;
  
  private String parentDn;
  
  private Pattern roleSchemaValuePattern;
  
  private Pattern accessValuePattern;
  
  private String initialContextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
  
  private boolean tls;
  
  private String mechanism = "SIMPLE";
  
  private String rdnAttribute = "uid";
  
  private boolean initialized;
  
  private String rolesSchemaAttribute;
  
  private String accessAttribute;
  
  protected String[] attributeUnion;
  
  public void setStartTls(boolean paramBoolean) {
    this.tls = paramBoolean;
  }
  
  public void setLdapPort(int paramInt) {
    this.ldapPort = Integer.valueOf(paramInt);
  }
  
  public void init() {
    if (this.ldapHost == null)
      throw new IllegalStateException("Required property 'ldapHost' not set"); 
    if (this.parentDn == null)
      throw new IllegalStateException("Required property 'parentDn' not set"); 
    if (this.initialContextFactory == null)
      throw new IllegalStateException("Required property 'initialContextFactory' not set"); 
    if (this.mechanism == null)
      throw new IllegalStateException("Required property 'mechanism' not set"); 
    if (this.rdnAttribute == null)
      throw new IllegalStateException("Required property 'rdnAttribute' not set"); 
    if (this.rolesSchemaAttribute == null && this.accessAttribute == null)
      throw new IllegalStateException("You must set property 'rolesSchemaAttribute' and/or property 'accessAttribute'"); 
    if (this.roleSchemaValuePattern != null && this.rolesSchemaAttribute == null)
      throw new IllegalStateException("If property 'roleSchemaValuePattern' is set, then you must also set property 'rolesSchemaAttribute' to indicate which attribute to evalueate"); 
    if (this.accessValuePattern != null && this.accessAttribute == null)
      throw new IllegalStateException("If property 'accessValuePattern' is set, then you must also set property 'accessAttribute' to indicate which attribute to evalueate"); 
    if (this.rolesSchemaAttribute != null && this.accessAttribute != null) {
      this.attributeUnion = new String[] { this.rolesSchemaAttribute, this.accessAttribute };
    } else if (this.rolesSchemaAttribute != null) {
      this.attributeUnion = new String[] { this.rolesSchemaAttribute };
    } else {
      this.attributeUnion = new String[] { this.accessAttribute };
    } 
    this.initialized = true;
  }
  
  public void setAccessValuePattern(Pattern paramPattern) {
    this.accessValuePattern = paramPattern;
  }
  
  public void setAccessValuePatternString(String paramString) {
    setAccessValuePattern(Pattern.compile(paramString));
  }
  
  public void setRoleSchemaValuePattern(Pattern paramPattern) {
    this.roleSchemaValuePattern = paramPattern;
  }
  
  public void setRoleSchemaValuePatternString(String paramString) {
    setRoleSchemaValuePattern(Pattern.compile(paramString));
  }
  
  public void setSecurityMechanism(String paramString) {
    this.mechanism = paramString;
  }
  
  public void setLdapHost(String paramString) {
    this.ldapHost = paramString;
  }
  
  public void setPrincipalTemplate(String paramString) {
    this.principalTemplate = paramString;
  }
  
  public void setInitialContextFactory(String paramString) {
    this.initialContextFactory = paramString;
  }
  
  public void setSaslRealm(String paramString) {
    this.saslRealm = paramString;
  }
  
  public void setParentDn(String paramString) {
    this.parentDn = paramString;
  }
  
  public void setRdnAttribute(String paramString) {
    this.rdnAttribute = paramString;
  }
  
  public void setRolesSchemaAttribute(String paramString) {
    this.rolesSchemaAttribute = paramString;
  }
  
  public void setAccessAttribute(String paramString) {
    this.accessAttribute = paramString;
  }
  
  public String[] authenticate(String paramString1, String paramString2) throws DenyException {
    if (!this.initialized)
      throw new IllegalStateException("You must invoke the 'init' method to initialize the " + LdapAuthBean.class.getName() + " instance."); 
    Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>(5, 0.75F);
    hashtable.put("java.naming.factory.initial", this.initialContextFactory);
    hashtable.put("java.naming.provider.url", "ldap://" + this.ldapHost + ((this.ldapPort == null) ? "" : (":" + this.ldapPort)));
    StartTlsResponse startTlsResponse = null;
    InitialLdapContext initialLdapContext = null;
    try {
      initialLdapContext = new InitialLdapContext(hashtable, null);
      if (this.tls) {
        startTlsResponse = (StartTlsResponse)initialLdapContext.extendedOperation(new StartTlsRequest());
        startTlsResponse.negotiate();
      } 
      initialLdapContext.addToEnvironment("java.naming.security.authentication", this.mechanism);
      initialLdapContext.addToEnvironment("java.naming.security.principal", (this.principalTemplate == null) ? paramString1 : this.principalTemplate.replace("${username}", paramString1));
      initialLdapContext.addToEnvironment("java.naming.security.credentials", paramString2);
      if (this.saslRealm != null)
        hashtable.put("java.naming.security.sasl.realm", this.saslRealm); 
      NamingEnumeration<SearchResult> namingEnumeration = null;
      try {
        namingEnumeration = initialLdapContext.search(this.parentDn, new BasicAttributes(this.rdnAttribute, paramString1), this.attributeUnion);
      } catch (AuthenticationException authenticationException) {
        throw new DenyException();
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      } 
      if (!namingEnumeration.hasMore())
        throw new DenyException(); 
      SearchResult searchResult = namingEnumeration.next();
      if (namingEnumeration.hasMore())
        throw new RuntimeException("> 1 result"); 
      Attributes attributes = searchResult.getAttributes();
      if (this.accessAttribute != null) {
        Attribute attribute1 = attributes.get(this.accessAttribute);
        if (attribute1 == null)
          throw new DenyException(); 
        if (attribute1.size() != 1)
          throw new RuntimeException("Access attribute '" + this.accessAttribute + "' has unexpected value count: " + attribute1.size()); 
        if (this.accessValuePattern != null) {
          Object object = attribute1.get(0);
          if (object == null)
            throw new RuntimeException("Access Attr. value is null"); 
          if (!(object instanceof String))
            throw new RuntimeException("Access Attr. value not a String: " + object.getClass().getName()); 
          if (!this.accessValuePattern.matcher((String)object).matches())
            throw new DenyException(); 
        } 
      } 
      if (this.rolesSchemaAttribute == null)
        return null; 
      ArrayList<String> arrayList = new ArrayList();
      Attribute attribute = attributes.get(this.rolesSchemaAttribute);
      if (attribute != null) {
        int i = attribute.size();
        for (byte b = 0; b < i; b++) {
          Object object = attribute.get(b);
          if (object == null)
            throw new RuntimeException("R/S Attr value #" + b + " is null"); 
          if (!(object instanceof String))
            throw new RuntimeException("R/S Attr value #" + b + " not a String: " + object.getClass().getName()); 
          if (this.roleSchemaValuePattern == null) {
            arrayList.add((String)object);
          } else {
            Matcher matcher = this.roleSchemaValuePattern.matcher((String)object);
            if (matcher.matches())
              arrayList.add((matcher.groupCount() > 0) ? matcher.group(1) : (String)object); 
          } 
        } 
      } 
      if (arrayList.size() < 1) {
        if (this.accessAttribute == null)
          throw new DenyException(); 
        return new String[0];
      } 
      return arrayList.<String>toArray(new String[0]);
    } catch (DenyException denyException) {
      throw denyException;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } catch (NamingException namingException) {
      throw new RuntimeException(namingException);
    } finally {
      if (startTlsResponse != null)
        try {
          startTlsResponse.close();
        } catch (IOException iOException) {
          logger.error("Failed to close TLS Response", iOException);
        }  
      if (initialLdapContext != null)
        try {
          initialLdapContext.close();
        } catch (NamingException namingException) {
          logger.error("Failed to close LDAP Context", namingException);
        }  
    } 
  }
  
  public static void main(String[] paramArrayOfString) throws IOException {
    if (paramArrayOfString.length != 3)
      throw new IllegalArgumentException("SYNTAX:  java " + AuthBeanMultiplexer.class.getName() + " path/to/file.properties <USERNAME> <PASSWORD>"); 
    File file = new File(paramArrayOfString[0]);
    if (!file.isFile())
      throw new IllegalArgumentException("Not a file: " + file.getAbsolutePath()); 
    Properties properties = new Properties();
    properties.load(new FileInputStream(file));
    String str1 = properties.getProperty("trustStore");
    String str2 = properties.getProperty("startTls");
    String str3 = properties.getProperty("ldapPort");
    String str4 = properties.getProperty("roleSchemaValuePattern");
    String str5 = properties.getProperty("accessValuePattern");
    String str6 = properties.getProperty("securityMechanism");
    String str7 = properties.getProperty("ldapHost");
    String str8 = properties.getProperty("principalTemplate");
    String str9 = properties.getProperty("initialContextFactory");
    String str10 = properties.getProperty("saslRealm");
    String str11 = properties.getProperty("parentDn");
    String str12 = properties.getProperty("rdnAttribute");
    String str13 = properties.getProperty("rolesSchemaAttribute");
    String str14 = properties.getProperty("accessAttribute");
    if (str1 != null) {
      if (!(new File(str1)).isFile())
        throw new IllegalArgumentException("Specified trust store is not a file: " + str1); 
      System.setProperty("javax.net.ssl.trustStore", str1);
    } 
    LdapAuthBean ldapAuthBean = new LdapAuthBean();
    if (str2 != null)
      ldapAuthBean.setStartTls(Boolean.parseBoolean(str2)); 
    if (str3 != null)
      ldapAuthBean.setLdapPort(Integer.parseInt(str3)); 
    if (str4 != null)
      ldapAuthBean.setRoleSchemaValuePatternString(str4); 
    if (str5 != null)
      ldapAuthBean.setAccessValuePatternString(str5); 
    if (str6 != null)
      ldapAuthBean.setSecurityMechanism(str6); 
    if (str7 != null)
      ldapAuthBean.setLdapHost(str7); 
    if (str8 != null)
      ldapAuthBean.setPrincipalTemplate(str8); 
    if (str9 != null)
      ldapAuthBean.setInitialContextFactory(str9); 
    if (str10 != null)
      ldapAuthBean.setSaslRealm(str10); 
    if (str11 != null)
      ldapAuthBean.setParentDn(str11); 
    if (str12 != null)
      ldapAuthBean.setRdnAttribute(str12); 
    if (str13 != null)
      ldapAuthBean.setRolesSchemaAttribute(str13); 
    if (str14 != null)
      ldapAuthBean.setAccessAttribute(str14); 
    ldapAuthBean.init();
    String[] arrayOfString = null;
    try {
      arrayOfString = ldapAuthBean.authenticate(paramArrayOfString[1], paramArrayOfString[2]);
    } catch (DenyException denyException) {
      System.out.println("<DENY ACCESS>");
      return;
    } 
    if (arrayOfString == null) {
      System.out.println("<ALLOW ACCESS w/ local Roles+Schema>");
    } else {
      System.out.println(Integer.toString(arrayOfString.length) + " Roles/Schema: " + Arrays.toString((Object[])arrayOfString));
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\auth\LdapAuthBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */