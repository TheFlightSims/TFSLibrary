package org.hsqldb.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import org.hsqldb.map.BitMap;

public final class ServerAcl {
  protected static final byte[] ALL_SET_4BYTES = new byte[] { -1, -1, -1, -1 };
  
  protected static final byte[] ALL_SET_16BYTES = new byte[] { 
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
      -1, -1, -1, -1, -1, -1 };
  
  private PrintWriter pw = null;
  
  private List aclEntries;
  
  private static AclEntry PROHIBIT_ALL_IPV4;
  
  private static AclEntry PROHIBIT_ALL_IPV6;
  
  private File aclFile;
  
  private long lastLoadTime = 0L;
  
  public static String dottedNotation(byte[] paramArrayOfbyte) {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramArrayOfbyte.length; b++) {
      if (b > 0)
        stringBuffer.append('.'); 
      stringBuffer.append(paramArrayOfbyte[b] & 0xFF);
    } 
    return stringBuffer.toString();
  }
  
  public static String colonNotation(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length / 2 * 2 != paramArrayOfbyte.length)
      throw new RuntimeException("At this time .colonNotation only handles even byte quantities"); 
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramArrayOfbyte.length; b += 2) {
      if (b > 0)
        stringBuffer.append(':'); 
      stringBuffer.append(Integer.toHexString((paramArrayOfbyte[b] & 0xFF) * 256 + (paramArrayOfbyte[b + 1] & 0xFF)));
    } 
    return stringBuffer.toString();
  }
  
  public void setPrintWriter(PrintWriter paramPrintWriter) {
    this.pw = paramPrintWriter;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < this.aclEntries.size(); b++) {
      if (b > 0)
        stringBuffer.append('\n'); 
      stringBuffer.append("Entry " + (b + 1) + ": " + this.aclEntries.get(b));
    } 
    return stringBuffer.toString();
  }
  
  public boolean permitAccess(String paramString) {
    try {
      return permitAccess(InetAddress.getByName(paramString).getAddress());
    } catch (UnknownHostException unknownHostException) {
      println("'" + paramString + "' denied because failed to resolve to an addr");
      return false;
    } 
  }
  
  public boolean permitAccess(byte[] paramArrayOfbyte) {
    ensureAclsUptodate();
    for (byte b = 0; b < this.aclEntries.size(); b++) {
      if (((AclEntry)this.aclEntries.get(b)).matches(paramArrayOfbyte)) {
        AclEntry aclEntry = this.aclEntries.get(b);
        println("Addr '" + dottedNotation(paramArrayOfbyte) + "' matched rule #" + (b + 1) + ":  " + aclEntry);
        return aclEntry.allow;
      } 
    } 
    throw new RuntimeException("No rule matches address '" + dottedNotation(paramArrayOfbyte) + "'");
  }
  
  private void println(String paramString) {
    if (this.pw == null)
      return; 
    this.pw.println(paramString);
    this.pw.flush();
  }
  
  public ServerAcl(File paramFile) throws IOException, AclFormatException {
    this.aclFile = paramFile;
    this.aclEntries = load();
  }
  
  protected synchronized void ensureAclsUptodate() {
    if (this.lastLoadTime > this.aclFile.lastModified())
      return; 
    try {
      this.aclEntries = load();
      println("ACLs reloaded from file");
      return;
    } catch (Exception exception) {
      println("Failed to reload ACL file.  Retaining old ACLs.  " + exception);
      return;
    } 
  }
  
  protected List load() throws IOException, AclFormatException {
    if (!this.aclFile.exists())
      throw new IOException("File '" + this.aclFile.getAbsolutePath() + "' is not present"); 
    if (!this.aclFile.isFile())
      throw new IOException("'" + this.aclFile.getAbsolutePath() + "' is not a regular file"); 
    if (!this.aclFile.canRead())
      throw new IOException("'" + this.aclFile.getAbsolutePath() + "' is not accessible"); 
    String str = null;
    byte b = 0;
    BufferedReader bufferedReader = new BufferedReader(new FileReader(this.aclFile));
    ArrayList<AclEntry> arrayList = new ArrayList();
    try {
      String str1;
      while ((str1 = bufferedReader.readLine()) != null) {
        byte[] arrayOfByte;
        boolean bool;
        int i;
        b++;
        str1 = str1.trim();
        if (str1.length() < 1 || str1.charAt(0) == '#')
          continue; 
        StringTokenizer stringTokenizer = new StringTokenizer(str1);
        try {
          if (stringTokenizer.countTokens() != 2)
            throw new InternalException(); 
          String str2 = stringTokenizer.nextToken();
          String str3 = stringTokenizer.nextToken();
          int j = str3.indexOf('/');
          if (j > -1) {
            str = str3.substring(j + 1);
            str3 = str3.substring(0, j);
          } 
          arrayOfByte = InetAddress.getByName(str3).getAddress();
          i = (str == null) ? (arrayOfByte.length * 8) : Integer.parseInt(str);
          if (str2.equalsIgnoreCase("allow")) {
            bool = true;
          } else if (str2.equalsIgnoreCase("permit")) {
            bool = true;
          } else if (str2.equalsIgnoreCase("accept")) {
            bool = true;
          } else if (str2.equalsIgnoreCase("prohibit")) {
            bool = false;
          } else if (str2.equalsIgnoreCase("deny")) {
            bool = false;
          } else if (str2.equalsIgnoreCase("reject")) {
            bool = false;
          } else {
            throw new InternalException();
          } 
        } catch (NumberFormatException numberFormatException) {
          throw new AclFormatException("Syntax error at ACL file '" + this.aclFile.getAbsolutePath() + "', line " + b);
        } catch (InternalException internalException) {
          throw new AclFormatException("Syntax error at ACL file '" + this.aclFile.getAbsolutePath() + "', line " + b);
        } 
        try {
          arrayList.add(new AclEntry(arrayOfByte, i, bool));
        } catch (AclFormatException aclFormatException) {
          throw new AclFormatException("Syntax error at ACL file '" + this.aclFile.getAbsolutePath() + "', line " + b + ": " + aclFormatException.toString());
        } 
      } 
    } finally {
      bufferedReader.close();
    } 
    arrayList.add(PROHIBIT_ALL_IPV4);
    arrayList.add(PROHIBIT_ALL_IPV6);
    this.lastLoadTime = (new Date()).getTime();
    return arrayList;
  }
  
  public static void main(String[] paramArrayOfString) throws AclFormatException, IOException {
    if (paramArrayOfString.length > 1)
      throw new RuntimeException("Try: java -cp path/to/hsqldb.jar " + ServerAcl.class.getName() + " --help"); 
    if (paramArrayOfString.length > 0 && paramArrayOfString[0].equals("--help")) {
      System.err.println("SYNTAX: java -cp path/to/hsqldb.jar " + ServerAcl.class.getName() + " [filepath.txt]");
      System.err.println("ACL file path defaults to 'acl.txt' in the current directory.");
      System.exit(0);
    } 
    ServerAcl serverAcl = new ServerAcl(new File((paramArrayOfString.length == 0) ? "acl.txt" : paramArrayOfString[0]));
    serverAcl.setPrintWriter(new PrintWriter(System.out));
    System.out.println(serverAcl.toString());
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter hostnames or IP addresses to be tested (one per line).");
    String str;
    while ((str = bufferedReader.readLine()) != null) {
      str = str.trim();
      if (str.length() < 1)
        continue; 
      System.out.println(Boolean.toString(serverAcl.permitAccess(str)));
    } 
  }
  
  static {
    try {
      PROHIBIT_ALL_IPV4 = new AclEntry(InetAddress.getByName("0.0.0.0").getAddress(), 0, false);
      PROHIBIT_ALL_IPV6 = new AclEntry(InetAddress.getByName("::").getAddress(), 0, false);
    } catch (UnknownHostException unknownHostException) {
      throw new RuntimeException("Unexpected problem in static initializer", unknownHostException);
    } catch (AclFormatException aclFormatException) {
      throw new RuntimeException("Unexpected problem in static initializer", aclFormatException);
    } 
  }
  
  private static final class InternalException extends Exception {
    private InternalException() {}
  }
  
  private static final class AclEntry {
    private byte[] value;
    
    private byte[] mask;
    
    private int bitBlockSize;
    
    public boolean allow;
    
    public AclEntry(byte[] param1ArrayOfbyte, int param1Int, boolean param1Boolean) throws ServerAcl.AclFormatException {
      byte[] arrayOfByte = null;
      switch (param1ArrayOfbyte.length) {
        case 4:
          arrayOfByte = ServerAcl.ALL_SET_4BYTES;
          break;
        case 16:
          arrayOfByte = ServerAcl.ALL_SET_16BYTES;
          break;
        default:
          throw new IllegalArgumentException("Only 4 and 16 bytes supported, not " + param1ArrayOfbyte.length);
      } 
      if (param1Int > param1ArrayOfbyte.length * 8)
        throw new IllegalArgumentException("Specified " + param1Int + " significant bits, but value only has " + (param1ArrayOfbyte.length * 8) + " bits"); 
      this.bitBlockSize = param1Int;
      this.value = param1ArrayOfbyte;
      this.mask = BitMap.leftShift(arrayOfByte, param1ArrayOfbyte.length * 8 - param1Int);
      if (this.mask.length != param1ArrayOfbyte.length)
        throw new RuntimeException("Basic program assertion failed.  Generated mask length " + this.mask.length + " (bytes) does not match given value length " + param1ArrayOfbyte.length + " (bytes)."); 
      this.allow = param1Boolean;
      validateMask();
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer("Addrs ");
      stringBuffer.append((this.value.length == 16) ? ("[" + ServerAcl.colonNotation(this.value) + ']') : ServerAcl.dottedNotation(this.value));
      stringBuffer.append("/" + this.bitBlockSize + ' ' + (this.allow ? "ALLOW" : "DENY"));
      return stringBuffer.toString();
    }
    
    public boolean matches(byte[] param1ArrayOfbyte) {
      return (this.value.length != param1ArrayOfbyte.length) ? false : (!BitMap.hasAnyBitSet(BitMap.xor(this.value, BitMap.and(param1ArrayOfbyte, this.mask))));
    }
    
    public void validateMask() throws ServerAcl.AclFormatException {
      if (BitMap.hasAnyBitSet(BitMap.and(this.value, BitMap.not(this.mask))))
        throw new ServerAcl.AclFormatException("The base address '" + ServerAcl.dottedNotation(this.value) + "' is too specific for block-size-spec /" + this.bitBlockSize); 
    }
  }
  
  public static final class AclFormatException extends Exception {
    public AclFormatException(String param1String) {
      super(param1String);
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\ServerAcl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */