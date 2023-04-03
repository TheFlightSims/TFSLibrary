package org.apache.xerces.xni.parser;

import java.io.InputStream;
import java.io.Reader;
import org.apache.xerces.xni.XMLResourceIdentifier;

public class XMLInputSource {
  protected String fPublicId;
  
  protected String fSystemId;
  
  protected String fBaseSystemId;
  
  protected InputStream fByteStream;
  
  protected Reader fCharStream;
  
  protected String fEncoding;
  
  public XMLInputSource(String paramString1, String paramString2, String paramString3) {
    this.fPublicId = paramString1;
    this.fSystemId = paramString2;
    this.fBaseSystemId = paramString3;
  }
  
  public XMLInputSource(XMLResourceIdentifier paramXMLResourceIdentifier) {
    this.fPublicId = paramXMLResourceIdentifier.getPublicId();
    this.fSystemId = paramXMLResourceIdentifier.getLiteralSystemId();
    this.fBaseSystemId = paramXMLResourceIdentifier.getBaseSystemId();
  }
  
  public XMLInputSource(String paramString1, String paramString2, String paramString3, InputStream paramInputStream, String paramString4) {
    this.fPublicId = paramString1;
    this.fSystemId = paramString2;
    this.fBaseSystemId = paramString3;
    this.fByteStream = paramInputStream;
    this.fEncoding = paramString4;
  }
  
  public XMLInputSource(String paramString1, String paramString2, String paramString3, Reader paramReader, String paramString4) {
    this.fPublicId = paramString1;
    this.fSystemId = paramString2;
    this.fBaseSystemId = paramString3;
    this.fCharStream = paramReader;
    this.fEncoding = paramString4;
  }
  
  public void setPublicId(String paramString) {
    this.fPublicId = paramString;
  }
  
  public String getPublicId() {
    return this.fPublicId;
  }
  
  public void setSystemId(String paramString) {
    this.fSystemId = paramString;
  }
  
  public String getSystemId() {
    return this.fSystemId;
  }
  
  public void setBaseSystemId(String paramString) {
    this.fBaseSystemId = paramString;
  }
  
  public String getBaseSystemId() {
    return this.fBaseSystemId;
  }
  
  public void setByteStream(InputStream paramInputStream) {
    this.fByteStream = paramInputStream;
  }
  
  public InputStream getByteStream() {
    return this.fByteStream;
  }
  
  public void setCharacterStream(Reader paramReader) {
    this.fCharStream = paramReader;
  }
  
  public Reader getCharacterStream() {
    return this.fCharStream;
  }
  
  public void setEncoding(String paramString) {
    this.fEncoding = paramString;
  }
  
  public String getEncoding() {
    return this.fEncoding;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\parser\XMLInputSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */