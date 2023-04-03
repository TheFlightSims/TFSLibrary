package org.hsqldb.auth;

import java.security.Principal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import org.hsqldb.lib.FrameworkLogger;

public class JaasAuthBean implements AuthFunctionBean {
  private static FrameworkLogger logger = FrameworkLogger.getLog(JaasAuthBean.class);
  
  private boolean initialized;
  
  private String applicationKey;
  
  private Pattern roleSchemaValuePattern;
  
  private boolean roleSchemaViaCredential;
  
  public void setRoleSchemaViaCredential(boolean paramBoolean) {
    this.roleSchemaViaCredential = paramBoolean;
  }
  
  public void init() {
    if (this.applicationKey == null)
      throw new IllegalStateException("Required property 'applicationKey' not set"); 
    if (this.roleSchemaViaCredential && this.roleSchemaValuePattern == null)
      throw new IllegalStateException("Properties 'roleSchemaViaCredential' and 'roleSchemaValuePattern' are mutually exclusive.  If you want JaasAuthBean to manage roles or schemas, you must set property 'roleSchemaValuePattern'."); 
    this.initialized = true;
  }
  
  public void setApplicationKey(String paramString) {
    this.applicationKey = paramString;
  }
  
  public void setRoleSchemaValuePattern(Pattern paramPattern) {
    this.roleSchemaValuePattern = paramPattern;
  }
  
  public void setRoleSchemaValuePatternString(String paramString) {
    setRoleSchemaValuePattern(Pattern.compile(paramString));
  }
  
  public String[] authenticate(String paramString1, String paramString2) throws DenyException {
    if (!this.initialized)
      throw new IllegalStateException("You must invoke the 'init' method to initialize the " + JaasAuthBean.class.getName() + " instance."); 
    try {
      LoginContext loginContext = new LoginContext(this.applicationKey, new UPCallbackHandler(paramString1, paramString2));
      try {
        loginContext.login();
      } catch (LoginException loginException) {
        logger.finer("JSSE backend denying access:  " + loginException);
        throw new DenyException();
      } 
      try {
        if (this.roleSchemaValuePattern == null)
          return null; 
        byte b = 0;
        Matcher matcher = null;
        ArrayList<String> arrayList1 = new ArrayList();
        ArrayList<String> arrayList2 = new ArrayList();
        Subject subject = loginContext.getSubject();
        if (this.roleSchemaViaCredential) {
          for (Object object : new ArrayList(subject.getPublicCredentials()))
            arrayList1.add(object.toString()); 
        } else {
          for (Principal principal : new ArrayList(subject.getPrincipals()))
            arrayList1.add(principal.getName()); 
        } 
        logger.finer(Integer.toString(arrayList1.size()) + " candidate " + (this.roleSchemaViaCredential ? "Credentials" : "Principals"));
        for (String str : arrayList1) {
          matcher = this.roleSchemaValuePattern.matcher(str);
          if (matcher.matches()) {
            logger.finer("    +" + ++b + ": " + ((matcher.groupCount() > 0) ? matcher.group(1) : str));
            arrayList2.add((matcher.groupCount() > 0) ? matcher.group(1) : str);
            continue;
          } 
          logger.finer("    -" + ++b + ": " + str);
        } 
        return arrayList2.<String>toArray(new String[0]);
      } finally {
        loginContext.logout();
      } 
    } catch (LoginException loginException) {
      logger.severe("System JaasAuthBean failure", loginException);
      throw new RuntimeException(loginException);
    } catch (RuntimeException runtimeException) {
      logger.severe("System JaasAuthBean failure", runtimeException);
      throw runtimeException;
    } 
  }
  
  public static class UPCallbackHandler implements CallbackHandler {
    private String u;
    
    private char[] p;
    
    public UPCallbackHandler(String param1String1, String param1String2) {
      this.u = param1String1;
      this.p = param1String2.toCharArray();
    }
    
    public void handle(Callback[] param1ArrayOfCallback) throws UnsupportedCallbackException {
      boolean bool1 = false;
      boolean bool2 = false;
      for (Callback callback : param1ArrayOfCallback) {
        if (callback instanceof NameCallback) {
          ((NameCallback)callback).setName(this.u);
          bool1 = true;
        } else if (callback instanceof PasswordCallback) {
          ((PasswordCallback)callback).setPassword(this.p);
          bool2 = true;
        } else {
          throw new UnsupportedCallbackException(callback, "Unsupported Callback type: " + callback.getClass().getName());
        } 
      } 
      if (!bool1)
        throw new IllegalStateException("Supplied Callbacks does not include a NameCallback"); 
      if (!bool2)
        throw new IllegalStateException("Supplied Callbacks does not include a PasswordCallback"); 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\auth\JaasAuthBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */