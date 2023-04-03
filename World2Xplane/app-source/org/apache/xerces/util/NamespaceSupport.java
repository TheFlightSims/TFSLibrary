package org.apache.xerces.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.apache.xerces.xni.NamespaceContext;

public class NamespaceSupport implements NamespaceContext {
  protected String[] fNamespace = new String[32];
  
  protected int fNamespaceSize;
  
  protected int[] fContext = new int[8];
  
  protected int fCurrentContext;
  
  protected String[] fPrefixes = new String[16];
  
  public NamespaceSupport() {}
  
  public NamespaceSupport(NamespaceContext paramNamespaceContext) {
    pushContext();
    Enumeration enumeration = paramNamespaceContext.getAllPrefixes();
    while (enumeration.hasMoreElements()) {
      String str1 = enumeration.nextElement();
      String str2 = paramNamespaceContext.getURI(str1);
      declarePrefix(str1, str2);
    } 
  }
  
  public void reset() {
    this.fNamespaceSize = 0;
    this.fCurrentContext = 0;
    this.fContext[this.fCurrentContext] = this.fNamespaceSize;
    this.fNamespace[this.fNamespaceSize++] = XMLSymbols.PREFIX_XML;
    this.fNamespace[this.fNamespaceSize++] = NamespaceContext.XML_URI;
    this.fNamespace[this.fNamespaceSize++] = XMLSymbols.PREFIX_XMLNS;
    this.fNamespace[this.fNamespaceSize++] = NamespaceContext.XMLNS_URI;
    this.fCurrentContext++;
  }
  
  public void pushContext() {
    if (this.fCurrentContext + 1 == this.fContext.length) {
      int[] arrayOfInt = new int[this.fContext.length * 2];
      System.arraycopy(this.fContext, 0, arrayOfInt, 0, this.fContext.length);
      this.fContext = arrayOfInt;
    } 
    this.fContext[++this.fCurrentContext] = this.fNamespaceSize;
  }
  
  public void popContext() {
    this.fNamespaceSize = this.fContext[this.fCurrentContext--];
  }
  
  public boolean declarePrefix(String paramString1, String paramString2) {
    if (paramString1 == XMLSymbols.PREFIX_XML || paramString1 == XMLSymbols.PREFIX_XMLNS)
      return false; 
    for (int i = this.fNamespaceSize; i > this.fContext[this.fCurrentContext]; i -= 2) {
      if (this.fNamespace[i - 2] == paramString1) {
        this.fNamespace[i - 1] = paramString2;
        return true;
      } 
    } 
    if (this.fNamespaceSize == this.fNamespace.length) {
      String[] arrayOfString = new String[this.fNamespaceSize * 2];
      System.arraycopy(this.fNamespace, 0, arrayOfString, 0, this.fNamespaceSize);
      this.fNamespace = arrayOfString;
    } 
    this.fNamespace[this.fNamespaceSize++] = paramString1;
    this.fNamespace[this.fNamespaceSize++] = paramString2;
    return true;
  }
  
  public String getURI(String paramString) {
    for (int i = this.fNamespaceSize; i > 0; i -= 2) {
      if (this.fNamespace[i - 2] == paramString)
        return this.fNamespace[i - 1]; 
    } 
    return null;
  }
  
  public String getPrefix(String paramString) {
    for (int i = this.fNamespaceSize; i > 0; i -= 2) {
      if (this.fNamespace[i - 1] == paramString && getURI(this.fNamespace[i - 2]) == paramString)
        return this.fNamespace[i - 2]; 
    } 
    return null;
  }
  
  public int getDeclaredPrefixCount() {
    return (this.fNamespaceSize - this.fContext[this.fCurrentContext]) / 2;
  }
  
  public String getDeclaredPrefixAt(int paramInt) {
    return this.fNamespace[this.fContext[this.fCurrentContext] + paramInt * 2];
  }
  
  public Enumeration getAllPrefixes() {
    byte b1 = 0;
    if (this.fPrefixes.length < this.fNamespace.length / 2) {
      String[] arrayOfString = new String[this.fNamespaceSize];
      this.fPrefixes = arrayOfString;
    } 
    String str = null;
    boolean bool = true;
    for (byte b2 = 2; b2 < this.fNamespaceSize - 2; b2 += 2) {
      str = this.fNamespace[b2 + 2];
      for (byte b = 0; b < b1; b++) {
        if (this.fPrefixes[b] == str) {
          bool = false;
          break;
        } 
      } 
      if (bool)
        this.fPrefixes[b1++] = str; 
      bool = true;
    } 
    return new Prefixes(this, this.fPrefixes, b1);
  }
  
  public boolean containsPrefix(String paramString) {
    for (int i = this.fNamespaceSize; i > 0; i -= 2) {
      if (this.fNamespace[i - 2] == paramString)
        return true; 
    } 
    return false;
  }
  
  protected final class Prefixes implements Enumeration {
    private String[] prefixes;
    
    private int counter;
    
    private int size;
    
    private final NamespaceSupport this$0;
    
    public Prefixes(NamespaceSupport this$0, String[] param1ArrayOfString, int param1Int) {
      this.this$0 = this$0;
      this.counter = 0;
      this.size = 0;
      this.prefixes = param1ArrayOfString;
      this.size = param1Int;
    }
    
    public boolean hasMoreElements() {
      return (this.counter < this.size);
    }
    
    public Object nextElement() {
      if (this.counter < this.size)
        return this.this$0.fPrefixes[this.counter++]; 
      throw new NoSuchElementException("Illegal access to Namespace prefixes enumeration.");
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer();
      for (byte b = 0; b < this.size; b++) {
        stringBuffer.append(this.prefixes[b]);
        stringBuffer.append(" ");
      } 
      return stringBuffer.toString();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\NamespaceSupport.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */