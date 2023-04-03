package org.apache.xerces.util;

import java.io.IOException;
import java.io.Serializable;

public class URI implements Serializable {
  static final long serialVersionUID = 1601921774685357214L;
  
  private static final byte[] fgLookupTable = new byte[128];
  
  private static final int RESERVED_CHARACTERS = 1;
  
  private static final int MARK_CHARACTERS = 2;
  
  private static final int SCHEME_CHARACTERS = 4;
  
  private static final int USERINFO_CHARACTERS = 8;
  
  private static final int ASCII_ALPHA_CHARACTERS = 16;
  
  private static final int ASCII_DIGIT_CHARACTERS = 32;
  
  private static final int ASCII_HEX_CHARACTERS = 64;
  
  private static final int PATH_CHARACTERS = 128;
  
  private static final int MASK_ALPHA_NUMERIC = 48;
  
  private static final int MASK_UNRESERVED_MASK = 50;
  
  private static final int MASK_URI_CHARACTER = 51;
  
  private static final int MASK_SCHEME_CHARACTER = 52;
  
  private static final int MASK_USERINFO_CHARACTER = 58;
  
  private static final int MASK_PATH_CHARACTER = 178;
  
  private String m_scheme = null;
  
  private String m_userinfo = null;
  
  private String m_host = null;
  
  private int m_port = -1;
  
  private String m_regAuthority = null;
  
  private String m_path = null;
  
  private String m_queryString = null;
  
  private String m_fragment = null;
  
  private static boolean DEBUG = false;
  
  public URI() {}
  
  public URI(URI paramURI) {
    initialize(paramURI);
  }
  
  public URI(String paramString) throws MalformedURIException {
    this((URI)null, paramString);
  }
  
  public URI(String paramString, boolean paramBoolean) throws MalformedURIException {
    this((URI)null, paramString, paramBoolean);
  }
  
  public URI(URI paramURI, String paramString) throws MalformedURIException {
    initialize(paramURI, paramString);
  }
  
  public URI(URI paramURI, String paramString, boolean paramBoolean) throws MalformedURIException {
    initialize(paramURI, paramString, paramBoolean);
  }
  
  public URI(String paramString1, String paramString2) throws MalformedURIException {
    if (paramString1 == null || paramString1.trim().length() == 0)
      throw new MalformedURIException("Cannot construct URI with null/empty scheme!"); 
    if (paramString2 == null || paramString2.trim().length() == 0)
      throw new MalformedURIException("Cannot construct URI with null/empty scheme-specific part!"); 
    setScheme(paramString1);
    setPath(paramString2);
  }
  
  public URI(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) throws MalformedURIException {
    this(paramString1, null, paramString2, -1, paramString3, paramString4, paramString5);
  }
  
  public URI(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5, String paramString6) throws MalformedURIException {
    if (paramString1 == null || paramString1.trim().length() == 0)
      throw new MalformedURIException("Scheme is required!"); 
    if (paramString3 == null) {
      if (paramString2 != null)
        throw new MalformedURIException("Userinfo may not be specified if host is not specified!"); 
      if (paramInt != -1)
        throw new MalformedURIException("Port may not be specified if host is not specified!"); 
    } 
    if (paramString4 != null) {
      if (paramString4.indexOf('?') != -1 && paramString5 != null)
        throw new MalformedURIException("Query string cannot be specified in path and query string!"); 
      if (paramString4.indexOf('#') != -1 && paramString6 != null)
        throw new MalformedURIException("Fragment cannot be specified in both the path and fragment!"); 
    } 
    setScheme(paramString1);
    setHost(paramString3);
    setPort(paramInt);
    setUserinfo(paramString2);
    setPath(paramString4);
    setQueryString(paramString5);
    setFragment(paramString6);
  }
  
  private void initialize(URI paramURI) {
    this.m_scheme = paramURI.getScheme();
    this.m_userinfo = paramURI.getUserinfo();
    this.m_host = paramURI.getHost();
    this.m_port = paramURI.getPort();
    this.m_regAuthority = paramURI.getRegBasedAuthority();
    this.m_path = paramURI.getPath();
    this.m_queryString = paramURI.getQueryString();
    this.m_fragment = paramURI.getFragment();
  }
  
  private void initialize(URI paramURI, String paramString, boolean paramBoolean) throws MalformedURIException {
    String str = paramString;
    byte b = (str != null) ? str.length() : 0;
    if (paramURI == null && !b) {
      if (paramBoolean) {
        this.m_path = "";
        return;
      } 
      throw new MalformedURIException("Cannot initialize URI with empty parameters.");
    } 
    if (!b) {
      initialize(paramURI);
      return;
    } 
    int i = 0;
    int j = str.indexOf(':');
    if (j != -1) {
      int k = j - 1;
      int m = str.lastIndexOf('/', k);
      int n = str.lastIndexOf('?', k);
      int i1 = str.lastIndexOf('#', k);
      if (j == 0 || m != -1 || n != -1 || i1 != -1) {
        if (j == 0 || (paramURI == null && i1 != 0 && !paramBoolean))
          throw new MalformedURIException("No scheme found in URI."); 
      } else {
        initializeScheme(str);
        i = this.m_scheme.length() + 1;
        if (j == b - 1 || str.charAt(j + 1) == '#')
          throw new MalformedURIException("Scheme specific part cannot be empty."); 
      } 
    } else if (paramURI == null && str.indexOf('#') != 0 && !paramBoolean) {
      throw new MalformedURIException("No scheme found in URI.");
    } 
    if (i + 1 < b && str.charAt(i) == '/' && str.charAt(i + 1) == '/') {
      i += 2;
      int k = i;
      char c = Character.MIN_VALUE;
      while (i < b) {
        c = str.charAt(i);
        if (c == '/' || c == '?' || c == '#')
          break; 
        i++;
      } 
      if (i > k) {
        if (!initializeAuthority(str.substring(k, i)))
          i = k - 2; 
      } else {
        this.m_host = "";
      } 
    } 
    initializePath(str, i);
    if (paramURI != null)
      absolutize(paramURI); 
  }
  
  private void initialize(URI paramURI, String paramString) throws MalformedURIException {
    String str = paramString;
    byte b = (str != null) ? str.length() : 0;
    if (paramURI == null && !b)
      throw new MalformedURIException("Cannot initialize URI with empty parameters."); 
    if (!b) {
      initialize(paramURI);
      return;
    } 
    int i = 0;
    int j = str.indexOf(':');
    if (j != -1) {
      int k = j - 1;
      int m = str.lastIndexOf('/', k);
      int n = str.lastIndexOf('?', k);
      int i1 = str.lastIndexOf('#', k);
      if (j == 0 || m != -1 || n != -1 || i1 != -1) {
        if (j == 0 || (paramURI == null && i1 != 0))
          throw new MalformedURIException("No scheme found in URI."); 
      } else {
        initializeScheme(str);
        i = this.m_scheme.length() + 1;
        if (j == b - 1 || str.charAt(j + 1) == '#')
          throw new MalformedURIException("Scheme specific part cannot be empty."); 
      } 
    } else if (paramURI == null && str.indexOf('#') != 0) {
      throw new MalformedURIException("No scheme found in URI.");
    } 
    if (i + 1 < b && str.charAt(i) == '/' && str.charAt(i + 1) == '/') {
      i += 2;
      int k = i;
      char c = Character.MIN_VALUE;
      while (i < b) {
        c = str.charAt(i);
        if (c == '/' || c == '?' || c == '#')
          break; 
        i++;
      } 
      if (i > k) {
        if (!initializeAuthority(str.substring(k, i)))
          i = k - 2; 
      } else {
        this.m_host = "";
      } 
    } 
    initializePath(str, i);
    if (paramURI != null)
      absolutize(paramURI); 
  }
  
  public void absolutize(URI paramURI) {
    if (this.m_path.length() == 0 && this.m_scheme == null && this.m_host == null && this.m_regAuthority == null) {
      this.m_scheme = paramURI.getScheme();
      this.m_userinfo = paramURI.getUserinfo();
      this.m_host = paramURI.getHost();
      this.m_port = paramURI.getPort();
      this.m_regAuthority = paramURI.getRegBasedAuthority();
      this.m_path = paramURI.getPath();
      if (this.m_queryString == null) {
        this.m_queryString = paramURI.getQueryString();
        if (this.m_fragment == null)
          this.m_fragment = paramURI.getFragment(); 
      } 
      return;
    } 
    if (this.m_scheme == null) {
      this.m_scheme = paramURI.getScheme();
    } else {
      return;
    } 
    if (this.m_host == null && this.m_regAuthority == null) {
      this.m_userinfo = paramURI.getUserinfo();
      this.m_host = paramURI.getHost();
      this.m_port = paramURI.getPort();
      this.m_regAuthority = paramURI.getRegBasedAuthority();
    } else {
      return;
    } 
    if (this.m_path.length() > 0 && this.m_path.startsWith("/"))
      return; 
    String str1 = "";
    String str2 = paramURI.getPath();
    if (str2 != null && str2.length() > 0) {
      int k = str2.lastIndexOf('/');
      if (k != -1)
        str1 = str2.substring(0, k + 1); 
    } else if (this.m_path.length() > 0) {
      str1 = "/";
    } 
    str1 = str1.concat(this.m_path);
    int i = -1;
    while ((i = str1.indexOf("/./")) != -1)
      str1 = str1.substring(0, i + 1).concat(str1.substring(i + 3)); 
    if (str1.endsWith("/."))
      str1 = str1.substring(0, str1.length() - 1); 
    i = 1;
    int j = -1;
    String str3 = null;
    while ((i = str1.indexOf("/../", i)) > 0) {
      str3 = str1.substring(0, str1.indexOf("/../"));
      j = str3.lastIndexOf('/');
      if (j != -1) {
        if (!str3.substring(j).equals("..")) {
          str1 = str1.substring(0, j + 1).concat(str1.substring(i + 4));
          i = j;
          continue;
        } 
        i += 4;
        continue;
      } 
      i += 4;
    } 
    if (str1.endsWith("/..")) {
      str3 = str1.substring(0, str1.length() - 3);
      j = str3.lastIndexOf('/');
      if (j != -1)
        str1 = str1.substring(0, j + 1); 
    } 
    this.m_path = str1;
  }
  
  private void initializeScheme(String paramString) throws MalformedURIException {
    int i = paramString.length();
    byte b = 0;
    String str = null;
    char c = Character.MIN_VALUE;
    while (b < i) {
      c = paramString.charAt(b);
      if (c == ':' || c == '/' || c == '?' || c == '#')
        break; 
      b++;
    } 
    str = paramString.substring(0, b);
    if (str.length() == 0)
      throw new MalformedURIException("No scheme found in URI."); 
    setScheme(str);
  }
  
  private boolean initializeAuthority(String paramString) {
    int i = 0;
    int j = 0;
    int k = paramString.length();
    char c = Character.MIN_VALUE;
    String str1 = null;
    if (paramString.indexOf('@', j) != -1) {
      while (i < k) {
        c = paramString.charAt(i);
        if (c == '@')
          break; 
        i++;
      } 
      str1 = paramString.substring(j, i);
      i++;
    } 
    String str2 = null;
    j = i;
    boolean bool = false;
    if (i < k)
      if (paramString.charAt(j) == '[') {
        int n = paramString.indexOf(']', j);
        i = (n != -1) ? n : k;
        if (i + 1 < k && paramString.charAt(i + 1) == ':') {
          i++;
          bool = true;
        } else {
          i = k;
        } 
      } else {
        int n = paramString.lastIndexOf(':', k);
        i = (n > j) ? n : k;
        bool = (i != k) ? true : false;
      }  
    str2 = paramString.substring(j, i);
    int m = -1;
    if (str2.length() > 0 && bool) {
      j = ++i;
      while (i < k)
        i++; 
      String str = paramString.substring(j, i);
      if (str.length() > 0)
        try {
          m = Integer.parseInt(str);
          if (m == -1)
            m--; 
        } catch (NumberFormatException numberFormatException) {
          m = -2;
        }  
    } 
    if (isValidServerBasedAuthority(str2, m, str1)) {
      this.m_host = str2;
      this.m_port = m;
      this.m_userinfo = str1;
      return true;
    } 
    if (isValidRegistryBasedAuthority(paramString)) {
      this.m_regAuthority = paramString;
      return true;
    } 
    return false;
  }
  
  private boolean isValidServerBasedAuthority(String paramString1, int paramInt, String paramString2) {
    if (!isWellFormedAddress(paramString1))
      return false; 
    if (paramInt < -1 || paramInt > 65535)
      return false; 
    if (paramString2 != null) {
      byte b = 0;
      int i = paramString2.length();
      char c = Character.MIN_VALUE;
      while (b < i) {
        c = paramString2.charAt(b);
        if (c == '%') {
          if (b + 2 >= i || !isHex(paramString2.charAt(b + 1)) || !isHex(paramString2.charAt(b + 2)))
            return false; 
          b += 2;
        } else if (!isUserinfoCharacter(c)) {
          return false;
        } 
        b++;
      } 
    } 
    return true;
  }
  
  private boolean isValidRegistryBasedAuthority(String paramString) {
    byte b = 0;
    int i = paramString.length();
    while (b < i) {
      char c = paramString.charAt(b);
      if (c == '%') {
        if (b + 2 >= i || !isHex(paramString.charAt(b + 1)) || !isHex(paramString.charAt(b + 2)))
          return false; 
        b += 2;
      } else if (!isPathCharacter(c)) {
        return false;
      } 
      b++;
    } 
    return true;
  }
  
  private void initializePath(String paramString, int paramInt) throws MalformedURIException {
    if (paramString == null)
      throw new MalformedURIException("Cannot initialize path from null string!"); 
    int i = paramInt;
    int j = paramInt;
    int k = paramString.length();
    char c = Character.MIN_VALUE;
    if (j < k)
      if (getScheme() == null || paramString.charAt(j) == '/') {
        while (i < k) {
          c = paramString.charAt(i);
          if (c == '%') {
            if (i + 2 >= k || !isHex(paramString.charAt(i + 1)) || !isHex(paramString.charAt(i + 2)))
              throw new MalformedURIException("Path contains invalid escape sequence!"); 
            i += 2;
          } else if (!isPathCharacter(c)) {
            if (c == '?' || c == '#')
              break; 
            throw new MalformedURIException("Path contains invalid character: " + c);
          } 
          i++;
        } 
      } else {
        while (i < k) {
          c = paramString.charAt(i);
          if (c == '?' || c == '#')
            break; 
          if (c == '%') {
            if (i + 2 >= k || !isHex(paramString.charAt(i + 1)) || !isHex(paramString.charAt(i + 2)))
              throw new MalformedURIException("Opaque part contains invalid escape sequence!"); 
            i += 2;
          } else if (!isURICharacter(c)) {
            throw new MalformedURIException("Opaque part contains invalid character: " + c);
          } 
          i++;
        } 
      }  
    this.m_path = paramString.substring(j, i);
    if (c == '?') {
      j = ++i;
      while (i < k) {
        c = paramString.charAt(i);
        if (c == '#')
          break; 
        if (c == '%') {
          if (i + 2 >= k || !isHex(paramString.charAt(i + 1)) || !isHex(paramString.charAt(i + 2)))
            throw new MalformedURIException("Query string contains invalid escape sequence!"); 
          i += 2;
        } else if (!isURICharacter(c)) {
          throw new MalformedURIException("Query string contains invalid character: " + c);
        } 
        i++;
      } 
      this.m_queryString = paramString.substring(j, i);
    } 
    if (c == '#') {
      j = ++i;
      while (i < k) {
        c = paramString.charAt(i);
        if (c == '%') {
          if (i + 2 >= k || !isHex(paramString.charAt(i + 1)) || !isHex(paramString.charAt(i + 2)))
            throw new MalformedURIException("Fragment contains invalid escape sequence!"); 
          i += 2;
        } else if (!isURICharacter(c)) {
          throw new MalformedURIException("Fragment contains invalid character: " + c);
        } 
        i++;
      } 
      this.m_fragment = paramString.substring(j, i);
    } 
  }
  
  public String getScheme() {
    return this.m_scheme;
  }
  
  public String getSchemeSpecificPart() {
    StringBuffer stringBuffer = new StringBuffer();
    if (this.m_host != null || this.m_regAuthority != null) {
      stringBuffer.append("//");
      if (this.m_host != null) {
        if (this.m_userinfo != null) {
          stringBuffer.append(this.m_userinfo);
          stringBuffer.append('@');
        } 
        stringBuffer.append(this.m_host);
        if (this.m_port != -1) {
          stringBuffer.append(':');
          stringBuffer.append(this.m_port);
        } 
      } else {
        stringBuffer.append(this.m_regAuthority);
      } 
    } 
    if (this.m_path != null)
      stringBuffer.append(this.m_path); 
    if (this.m_queryString != null) {
      stringBuffer.append('?');
      stringBuffer.append(this.m_queryString);
    } 
    if (this.m_fragment != null) {
      stringBuffer.append('#');
      stringBuffer.append(this.m_fragment);
    } 
    return stringBuffer.toString();
  }
  
  public String getUserinfo() {
    return this.m_userinfo;
  }
  
  public String getHost() {
    return this.m_host;
  }
  
  public int getPort() {
    return this.m_port;
  }
  
  public String getRegBasedAuthority() {
    return this.m_regAuthority;
  }
  
  public String getAuthority() {
    StringBuffer stringBuffer = new StringBuffer();
    if (this.m_host != null || this.m_regAuthority != null) {
      stringBuffer.append("//");
      if (this.m_host != null) {
        if (this.m_userinfo != null) {
          stringBuffer.append(this.m_userinfo);
          stringBuffer.append('@');
        } 
        stringBuffer.append(this.m_host);
        if (this.m_port != -1) {
          stringBuffer.append(':');
          stringBuffer.append(this.m_port);
        } 
      } else {
        stringBuffer.append(this.m_regAuthority);
      } 
    } 
    return stringBuffer.toString();
  }
  
  public String getPath(boolean paramBoolean1, boolean paramBoolean2) {
    StringBuffer stringBuffer = new StringBuffer(this.m_path);
    if (paramBoolean1 && this.m_queryString != null) {
      stringBuffer.append('?');
      stringBuffer.append(this.m_queryString);
    } 
    if (paramBoolean2 && this.m_fragment != null) {
      stringBuffer.append('#');
      stringBuffer.append(this.m_fragment);
    } 
    return stringBuffer.toString();
  }
  
  public String getPath() {
    return this.m_path;
  }
  
  public String getQueryString() {
    return this.m_queryString;
  }
  
  public String getFragment() {
    return this.m_fragment;
  }
  
  public void setScheme(String paramString) throws MalformedURIException {
    if (paramString == null)
      throw new MalformedURIException("Cannot set scheme from null string!"); 
    if (!isConformantSchemeName(paramString))
      throw new MalformedURIException("The scheme is not conformant."); 
    this.m_scheme = paramString.toLowerCase();
  }
  
  public void setUserinfo(String paramString) throws MalformedURIException {
    if (paramString == null) {
      this.m_userinfo = null;
      return;
    } 
    if (this.m_host == null)
      throw new MalformedURIException("Userinfo cannot be set when host is null!"); 
    byte b = 0;
    int i = paramString.length();
    char c = Character.MIN_VALUE;
    while (b < i) {
      c = paramString.charAt(b);
      if (c == '%') {
        if (b + 2 >= i || !isHex(paramString.charAt(b + 1)) || !isHex(paramString.charAt(b + 2)))
          throw new MalformedURIException("Userinfo contains invalid escape sequence!"); 
      } else if (!isUserinfoCharacter(c)) {
        throw new MalformedURIException("Userinfo contains invalid character:" + c);
      } 
      b++;
    } 
    this.m_userinfo = paramString;
  }
  
  public void setHost(String paramString) throws MalformedURIException {
    if (paramString == null || paramString.length() == 0) {
      if (paramString != null)
        this.m_regAuthority = null; 
      this.m_host = paramString;
      this.m_userinfo = null;
      this.m_port = -1;
      return;
    } 
    if (!isWellFormedAddress(paramString))
      throw new MalformedURIException("Host is not a well formed address!"); 
    this.m_host = paramString;
    this.m_regAuthority = null;
  }
  
  public void setPort(int paramInt) throws MalformedURIException {
    if (paramInt >= 0 && paramInt <= 65535) {
      if (this.m_host == null)
        throw new MalformedURIException("Port cannot be set when host is null!"); 
    } else if (paramInt != -1) {
      throw new MalformedURIException("Invalid port number!");
    } 
    this.m_port = paramInt;
  }
  
  public void setRegBasedAuthority(String paramString) throws MalformedURIException {
    if (paramString == null) {
      this.m_regAuthority = null;
      return;
    } 
    if (paramString.length() < 1 || !isValidRegistryBasedAuthority(paramString) || paramString.indexOf('/') != -1)
      throw new MalformedURIException("Registry based authority is not well formed."); 
    this.m_regAuthority = paramString;
    this.m_host = null;
    this.m_userinfo = null;
    this.m_port = -1;
  }
  
  public void setPath(String paramString) throws MalformedURIException {
    if (paramString == null) {
      this.m_path = null;
      this.m_queryString = null;
      this.m_fragment = null;
    } else {
      initializePath(paramString, 0);
    } 
  }
  
  public void appendPath(String paramString) throws MalformedURIException {
    if (paramString == null || paramString.trim().length() == 0)
      return; 
    if (!isURIString(paramString))
      throw new MalformedURIException("Path contains invalid character!"); 
    if (this.m_path == null || this.m_path.trim().length() == 0) {
      if (paramString.startsWith("/")) {
        this.m_path = paramString;
      } else {
        this.m_path = "/" + paramString;
      } 
    } else if (this.m_path.endsWith("/")) {
      if (paramString.startsWith("/")) {
        this.m_path = this.m_path.concat(paramString.substring(1));
      } else {
        this.m_path = this.m_path.concat(paramString);
      } 
    } else if (paramString.startsWith("/")) {
      this.m_path = this.m_path.concat(paramString);
    } else {
      this.m_path = this.m_path.concat("/" + paramString);
    } 
  }
  
  public void setQueryString(String paramString) throws MalformedURIException {
    if (paramString == null) {
      this.m_queryString = null;
    } else {
      if (!isGenericURI())
        throw new MalformedURIException("Query string can only be set for a generic URI!"); 
      if (getPath() == null)
        throw new MalformedURIException("Query string cannot be set when path is null!"); 
      if (!isURIString(paramString))
        throw new MalformedURIException("Query string contains invalid character!"); 
      this.m_queryString = paramString;
    } 
  }
  
  public void setFragment(String paramString) throws MalformedURIException {
    if (paramString == null) {
      this.m_fragment = null;
    } else {
      if (!isGenericURI())
        throw new MalformedURIException("Fragment can only be set for a generic URI!"); 
      if (getPath() == null)
        throw new MalformedURIException("Fragment cannot be set when path is null!"); 
      if (!isURIString(paramString))
        throw new MalformedURIException("Fragment contains invalid character!"); 
      this.m_fragment = paramString;
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof URI) {
      URI uRI = (URI)paramObject;
      if (((this.m_scheme == null && uRI.m_scheme == null) || (this.m_scheme != null && uRI.m_scheme != null && this.m_scheme.equals(uRI.m_scheme))) && ((this.m_userinfo == null && uRI.m_userinfo == null) || (this.m_userinfo != null && uRI.m_userinfo != null && this.m_userinfo.equals(uRI.m_userinfo))) && ((this.m_host == null && uRI.m_host == null) || (this.m_host != null && uRI.m_host != null && this.m_host.equals(uRI.m_host))) && this.m_port == uRI.m_port && ((this.m_path == null && uRI.m_path == null) || (this.m_path != null && uRI.m_path != null && this.m_path.equals(uRI.m_path))) && ((this.m_queryString == null && uRI.m_queryString == null) || (this.m_queryString != null && uRI.m_queryString != null && this.m_queryString.equals(uRI.m_queryString))) && ((this.m_fragment == null && uRI.m_fragment == null) || (this.m_fragment != null && uRI.m_fragment != null && this.m_fragment.equals(uRI.m_fragment))))
        return true; 
    } 
    return false;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    if (this.m_scheme != null) {
      stringBuffer.append(this.m_scheme);
      stringBuffer.append(':');
    } 
    stringBuffer.append(getSchemeSpecificPart());
    return stringBuffer.toString();
  }
  
  public boolean isGenericURI() {
    return (this.m_host != null);
  }
  
  public boolean isAbsoluteURI() {
    return (this.m_scheme != null);
  }
  
  public static boolean isConformantSchemeName(String paramString) {
    if (paramString == null || paramString.trim().length() == 0)
      return false; 
    if (!isAlpha(paramString.charAt(0)))
      return false; 
    int i = paramString.length();
    for (byte b = 1; b < i; b++) {
      char c = paramString.charAt(b);
      if (!isSchemeCharacter(c))
        return false; 
    } 
    return true;
  }
  
  public static boolean isWellFormedAddress(String paramString) {
    if (paramString == null)
      return false; 
    int i = paramString.length();
    if (i == 0)
      return false; 
    if (paramString.startsWith("["))
      return isWellFormedIPv6Reference(paramString); 
    if (paramString.startsWith(".") || paramString.startsWith("-") || paramString.endsWith("-"))
      return false; 
    int j = paramString.lastIndexOf('.');
    if (paramString.endsWith("."))
      j = paramString.substring(0, j).lastIndexOf('.'); 
    if (j + 1 < i && isDigit(paramString.charAt(j + 1)))
      return isWellFormedIPv4Address(paramString); 
    if (i > 255)
      return false; 
    byte b1 = 0;
    for (byte b2 = 0; b2 < i; b2++) {
      char c = paramString.charAt(b2);
      if (c == '.') {
        if (!isAlphanum(paramString.charAt(b2 - 1)))
          return false; 
        if (b2 + 1 < i && !isAlphanum(paramString.charAt(b2 + 1)))
          return false; 
        b1 = 0;
      } else {
        if (!isAlphanum(c) && c != '-')
          return false; 
        if (++b1 > 63)
          return false; 
      } 
    } 
    return true;
  }
  
  public static boolean isWellFormedIPv4Address(String paramString) {
    int i = paramString.length();
    byte b1 = 0;
    byte b2 = 0;
    for (byte b3 = 0; b3 < i; b3++) {
      char c = paramString.charAt(b3);
      if (c == '.') {
        if ((b3 > 0 && !isDigit(paramString.charAt(b3 - 1))) || (b3 + 1 < i && !isDigit(paramString.charAt(b3 + 1))))
          return false; 
        b2 = 0;
        if (++b1 > 3)
          return false; 
      } else {
        if (!isDigit(c))
          return false; 
        if (++b2 > 3)
          return false; 
        if (b2 == 3) {
          char c1 = paramString.charAt(b3 - 2);
          char c2 = paramString.charAt(b3 - 1);
          if (c1 >= '2' && (c1 != '2' || (c2 >= '5' && (c2 != '5' || c > '5'))))
            return false; 
        } 
      } 
    } 
    return (b1 == 3);
  }
  
  public static boolean isWellFormedIPv6Reference(String paramString) {
    int i = paramString.length();
    int j = 1;
    int k = i - 1;
    if (i <= 2 || paramString.charAt(0) != '[' || paramString.charAt(k) != ']')
      return false; 
    int[] arrayOfInt = new int[1];
    j = scanHexSequence(paramString, j, k, arrayOfInt);
    if (j == -1)
      return false; 
    if (j == k)
      return (arrayOfInt[0] == 8); 
    if (j + 1 < k && paramString.charAt(j) == ':') {
      if (paramString.charAt(j + 1) == ':') {
        arrayOfInt[0] = arrayOfInt[0] + 1;
        if (arrayOfInt[0] + 1 > 8)
          return false; 
        j += 2;
        if (j == k)
          return true; 
      } else {
        return (arrayOfInt[0] == 6 && isWellFormedIPv4Address(paramString.substring(j + 1, k)));
      } 
    } else {
      return false;
    } 
    int m = arrayOfInt[0];
    j = scanHexSequence(paramString, j, k, arrayOfInt);
    return (j == k || (j != -1 && isWellFormedIPv4Address(paramString.substring((arrayOfInt[0] > m) ? (j + 1) : j, k))));
  }
  
  private static int scanHexSequence(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfint) {
    byte b = 0;
    int i = paramInt1;
    while (paramInt1 < paramInt2) {
      char c = paramString.charAt(paramInt1);
      if (c == ':') {
        paramArrayOfint[0] = paramArrayOfint[0] + 1;
        if (b && paramArrayOfint[0] + 1 > 8)
          return -1; 
        if (!b || (paramInt1 + 1 < paramInt2 && paramString.charAt(paramInt1 + 1) == ':'))
          return paramInt1; 
        b = 0;
      } else {
        if (!isHex(c)) {
          if (c == '.' && b < 4 && b > 0 && paramArrayOfint[0] <= 6) {
            int j = paramInt1 - b - 1;
            return (j >= i) ? j : (j + 1);
          } 
          return -1;
        } 
        if (++b > 4)
          return -1; 
      } 
      paramInt1++;
    } 
    paramArrayOfint[0] = paramArrayOfint[0] + 1;
    return (b > 0 && paramArrayOfint[0] + 1 <= 8) ? paramInt2 : -1;
  }
  
  private static boolean isDigit(char paramChar) {
    return (paramChar >= '0' && paramChar <= '9');
  }
  
  private static boolean isHex(char paramChar) {
    return (paramChar <= 'f' && (fgLookupTable[paramChar] & 0x40) != 0);
  }
  
  private static boolean isAlpha(char paramChar) {
    return ((paramChar >= 'a' && paramChar <= 'z') || (paramChar >= 'A' && paramChar <= 'Z'));
  }
  
  private static boolean isAlphanum(char paramChar) {
    return (paramChar <= 'z' && (fgLookupTable[paramChar] & 0x30) != 0);
  }
  
  private static boolean isReservedCharacter(char paramChar) {
    return (paramChar <= ']' && (fgLookupTable[paramChar] & 0x1) != 0);
  }
  
  private static boolean isUnreservedCharacter(char paramChar) {
    return (paramChar <= '~' && (fgLookupTable[paramChar] & 0x32) != 0);
  }
  
  private static boolean isURICharacter(char paramChar) {
    return (paramChar <= '~' && (fgLookupTable[paramChar] & 0x33) != 0);
  }
  
  private static boolean isSchemeCharacter(char paramChar) {
    return (paramChar <= 'z' && (fgLookupTable[paramChar] & 0x34) != 0);
  }
  
  private static boolean isUserinfoCharacter(char paramChar) {
    return (paramChar <= 'z' && (fgLookupTable[paramChar] & 0x3A) != 0);
  }
  
  private static boolean isPathCharacter(char paramChar) {
    return (paramChar <= '~' && (fgLookupTable[paramChar] & 0xB2) != 0);
  }
  
  private static boolean isURIString(String paramString) {
    if (paramString == null)
      return false; 
    int i = paramString.length();
    char c = Character.MIN_VALUE;
    for (byte b = 0; b < i; b++) {
      c = paramString.charAt(b);
      if (c == '%') {
        if (b + 2 >= i || !isHex(paramString.charAt(b + 1)) || !isHex(paramString.charAt(b + 2)))
          return false; 
        b += 2;
      } else if (!isURICharacter(c)) {
        return false;
      } 
    } 
    return true;
  }
  
  static {
    for (byte b1 = 48; b1 <= 57; b1++)
      fgLookupTable[b1] = (byte)(fgLookupTable[b1] | 0x60); 
    for (byte b2 = 65; b2 <= 70; b2++) {
      fgLookupTable[b2] = (byte)(fgLookupTable[b2] | 0x50);
      fgLookupTable[b2 + 32] = (byte)(fgLookupTable[b2 + 32] | 0x50);
    } 
    for (byte b3 = 71; b3 <= 90; b3++) {
      fgLookupTable[b3] = (byte)(fgLookupTable[b3] | 0x10);
      fgLookupTable[b3 + 32] = (byte)(fgLookupTable[b3 + 32] | 0x10);
    } 
    fgLookupTable[59] = (byte)(fgLookupTable[59] | 0x1);
    fgLookupTable[47] = (byte)(fgLookupTable[47] | 0x1);
    fgLookupTable[63] = (byte)(fgLookupTable[63] | 0x1);
    fgLookupTable[58] = (byte)(fgLookupTable[58] | 0x1);
    fgLookupTable[64] = (byte)(fgLookupTable[64] | 0x1);
    fgLookupTable[38] = (byte)(fgLookupTable[38] | 0x1);
    fgLookupTable[61] = (byte)(fgLookupTable[61] | 0x1);
    fgLookupTable[43] = (byte)(fgLookupTable[43] | 0x1);
    fgLookupTable[36] = (byte)(fgLookupTable[36] | 0x1);
    fgLookupTable[44] = (byte)(fgLookupTable[44] | 0x1);
    fgLookupTable[91] = (byte)(fgLookupTable[91] | 0x1);
    fgLookupTable[93] = (byte)(fgLookupTable[93] | 0x1);
    fgLookupTable[45] = (byte)(fgLookupTable[45] | 0x2);
    fgLookupTable[95] = (byte)(fgLookupTable[95] | 0x2);
    fgLookupTable[46] = (byte)(fgLookupTable[46] | 0x2);
    fgLookupTable[33] = (byte)(fgLookupTable[33] | 0x2);
    fgLookupTable[126] = (byte)(fgLookupTable[126] | 0x2);
    fgLookupTable[42] = (byte)(fgLookupTable[42] | 0x2);
    fgLookupTable[39] = (byte)(fgLookupTable[39] | 0x2);
    fgLookupTable[40] = (byte)(fgLookupTable[40] | 0x2);
    fgLookupTable[41] = (byte)(fgLookupTable[41] | 0x2);
    fgLookupTable[43] = (byte)(fgLookupTable[43] | 0x4);
    fgLookupTable[45] = (byte)(fgLookupTable[45] | 0x4);
    fgLookupTable[46] = (byte)(fgLookupTable[46] | 0x4);
    fgLookupTable[59] = (byte)(fgLookupTable[59] | 0x8);
    fgLookupTable[58] = (byte)(fgLookupTable[58] | 0x8);
    fgLookupTable[38] = (byte)(fgLookupTable[38] | 0x8);
    fgLookupTable[61] = (byte)(fgLookupTable[61] | 0x8);
    fgLookupTable[43] = (byte)(fgLookupTable[43] | 0x8);
    fgLookupTable[36] = (byte)(fgLookupTable[36] | 0x8);
    fgLookupTable[44] = (byte)(fgLookupTable[44] | 0x8);
    fgLookupTable[59] = (byte)(fgLookupTable[59] | 0x80);
    fgLookupTable[47] = (byte)(fgLookupTable[47] | 0x80);
    fgLookupTable[58] = (byte)(fgLookupTable[58] | 0x80);
    fgLookupTable[64] = (byte)(fgLookupTable[64] | 0x80);
    fgLookupTable[38] = (byte)(fgLookupTable[38] | 0x80);
    fgLookupTable[61] = (byte)(fgLookupTable[61] | 0x80);
    fgLookupTable[43] = (byte)(fgLookupTable[43] | 0x80);
    fgLookupTable[36] = (byte)(fgLookupTable[36] | 0x80);
    fgLookupTable[44] = (byte)(fgLookupTable[44] | 0x80);
  }
  
  public static class MalformedURIException extends IOException {
    static final long serialVersionUID = -6695054834342951930L;
    
    public MalformedURIException() {}
    
    public MalformedURIException(String param1String) {
      super(param1String);
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\URI.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */