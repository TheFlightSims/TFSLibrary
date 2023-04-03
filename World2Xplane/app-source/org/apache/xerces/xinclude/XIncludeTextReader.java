package org.apache.xerces.xinclude;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.io.ASCIIReader;
import org.apache.xerces.impl.io.Latin1Reader;
import org.apache.xerces.impl.io.UTF8Reader;
import org.apache.xerces.util.EncodingMap;
import org.apache.xerces.util.HTTPInputSource;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XIncludeTextReader {
  private Reader fReader;
  
  private final XIncludeHandler fHandler;
  
  private XMLInputSource fSource;
  
  private XMLErrorReporter fErrorReporter;
  
  private XMLString fTempString = new XMLString();
  
  public XIncludeTextReader(XMLInputSource paramXMLInputSource, XIncludeHandler paramXIncludeHandler, int paramInt) throws IOException {
    this.fHandler = paramXIncludeHandler;
    this.fSource = paramXMLInputSource;
    this.fTempString = new XMLString(new char[paramInt + 1], 0, 0);
  }
  
  public void setErrorReporter(XMLErrorReporter paramXMLErrorReporter) {
    this.fErrorReporter = paramXMLErrorReporter;
  }
  
  protected Reader getReader(XMLInputSource paramXMLInputSource) throws IOException {
    if (paramXMLInputSource.getCharacterStream() != null)
      return paramXMLInputSource.getCharacterStream(); 
    InputStream inputStream = null;
    String str1 = paramXMLInputSource.getEncoding();
    if (str1 == null)
      str1 = "UTF-8"; 
    if (paramXMLInputSource.getByteStream() != null) {
      inputStream = paramXMLInputSource.getByteStream();
      if (!(inputStream instanceof BufferedInputStream))
        inputStream = new BufferedInputStream(inputStream, this.fTempString.ch.length); 
    } else {
      String str3 = XMLEntityManager.expandSystemId(paramXMLInputSource.getSystemId(), paramXMLInputSource.getBaseSystemId(), false);
      URL uRL = new URL(str3);
      URLConnection uRLConnection = uRL.openConnection();
      if (uRLConnection instanceof HttpURLConnection && paramXMLInputSource instanceof HTTPInputSource) {
        HttpURLConnection httpURLConnection = (HttpURLConnection)uRLConnection;
        HTTPInputSource hTTPInputSource = (HTTPInputSource)paramXMLInputSource;
        Iterator iterator = hTTPInputSource.getHTTPRequestProperties();
        while (iterator.hasNext()) {
          Map.Entry entry = iterator.next();
          httpURLConnection.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
        } 
        boolean bool = hTTPInputSource.getFollowHTTPRedirects();
        if (!bool)
          XMLEntityManager.setInstanceFollowRedirects(httpURLConnection, bool); 
      } 
      inputStream = new BufferedInputStream(uRLConnection.getInputStream());
      String str4 = uRLConnection.getContentType();
      byte b = (str4 != null) ? str4.indexOf(';') : -1;
      String str5 = null;
      String str6 = null;
      if (b != -1) {
        str5 = str4.substring(0, b).trim();
        str6 = str4.substring(b + 1).trim();
        if (str6.startsWith("charset=")) {
          str6 = str6.substring(8).trim();
          if ((str6.charAt(0) == '"' && str6.charAt(str6.length() - 1) == '"') || (str6.charAt(0) == '\'' && str6.charAt(str6.length() - 1) == '\''))
            str6 = str6.substring(1, str6.length() - 1); 
        } else {
          str6 = null;
        } 
      } else {
        str5 = str4.trim();
      } 
      String str7 = null;
      if (str5.equals("text/xml")) {
        if (str6 != null) {
          str7 = str6;
        } else {
          str7 = "US-ASCII";
        } 
      } else if (str5.equals("application/xml")) {
        if (str6 != null) {
          str7 = str6;
        } else {
          str7 = getEncodingName(inputStream);
        } 
      } else if (str5.endsWith("+xml")) {
        str7 = getEncodingName(inputStream);
      } 
      if (str7 != null)
        str1 = str7; 
    } 
    str1 = str1.toUpperCase(Locale.ENGLISH);
    str1 = consumeBOM(inputStream, str1);
    if (str1.equals("UTF-8"))
      return (Reader)new UTF8Reader(inputStream, this.fTempString.ch.length, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale()); 
    String str2 = EncodingMap.getIANA2JavaMapping(str1);
    if (str2 == null) {
      MessageFormatter messageFormatter = this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210");
      Locale locale = this.fErrorReporter.getLocale();
      throw new IOException(messageFormatter.formatMessage(locale, "EncodingDeclInvalid", new Object[] { str1 }));
    } 
    return (Reader)(str2.equals("ASCII") ? new ASCIIReader(inputStream, this.fTempString.ch.length, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale()) : (str2.equals("ISO8859_1") ? new Latin1Reader(inputStream, this.fTempString.ch.length) : new InputStreamReader(inputStream, str2)));
  }
  
  protected String getEncodingName(InputStream paramInputStream) throws IOException {
    byte[] arrayOfByte = new byte[4];
    String str = null;
    paramInputStream.mark(4);
    int i = paramInputStream.read(arrayOfByte, 0, 4);
    paramInputStream.reset();
    if (i == 4)
      str = getEncodingName(arrayOfByte); 
    return str;
  }
  
  protected String consumeBOM(InputStream paramInputStream, String paramString) throws IOException {
    byte[] arrayOfByte = new byte[3];
    int i = 0;
    paramInputStream.mark(3);
    if (paramString.equals("UTF-8")) {
      i = paramInputStream.read(arrayOfByte, 0, 3);
      if (i == 3) {
        int j = arrayOfByte[0] & 0xFF;
        int k = arrayOfByte[1] & 0xFF;
        int m = arrayOfByte[2] & 0xFF;
        if (j != 239 || k != 187 || m != 191)
          paramInputStream.reset(); 
      } else {
        paramInputStream.reset();
      } 
    } else if (paramString.startsWith("UTF-16")) {
      i = paramInputStream.read(arrayOfByte, 0, 2);
      if (i == 2) {
        int j = arrayOfByte[0] & 0xFF;
        int k = arrayOfByte[1] & 0xFF;
        if (j == 254 && k == 255)
          return "UTF-16BE"; 
        if (j == 255 && k == 254)
          return "UTF-16LE"; 
      } 
      paramInputStream.reset();
    } 
    return paramString;
  }
  
  protected String getEncodingName(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte[0] & 0xFF;
    int j = paramArrayOfbyte[1] & 0xFF;
    if (i == 254 && j == 255)
      return "UTF-16BE"; 
    if (i == 255 && j == 254)
      return "UTF-16LE"; 
    int k = paramArrayOfbyte[2] & 0xFF;
    if (i == 239 && j == 187 && k == 191)
      return "UTF-8"; 
    int m = paramArrayOfbyte[3] & 0xFF;
    return (i == 0 && j == 0 && k == 0 && m == 60) ? "ISO-10646-UCS-4" : ((i == 60 && j == 0 && k == 0 && m == 0) ? "ISO-10646-UCS-4" : ((i == 0 && j == 0 && k == 60 && m == 0) ? "ISO-10646-UCS-4" : ((i == 0 && j == 60 && k == 0 && m == 0) ? "ISO-10646-UCS-4" : ((i == 0 && j == 60 && k == 0 && m == 63) ? "UTF-16BE" : ((i == 60 && j == 0 && k == 63 && m == 0) ? "UTF-16LE" : ((i == 76 && j == 111 && k == 167 && m == 148) ? "CP037" : null))))));
  }
  
  public void parse() throws IOException {
    this.fReader = getReader(this.fSource);
    this.fSource = null;
    int i = this.fReader.read(this.fTempString.ch, 0, this.fTempString.ch.length - 1);
    this.fHandler.fHasIncludeReportedContent = true;
    while (i != -1) {
      for (byte b = 0; b < i; b++) {
        char c = this.fTempString.ch[b];
        if (!isValid(c))
          if (XMLChar.isHighSurrogate(c)) {
            int j;
            if (++b < i) {
              j = this.fTempString.ch[b];
            } else {
              j = this.fReader.read();
              if (j != -1)
                this.fTempString.ch[i++] = (char)j; 
            } 
            if (XMLChar.isLowSurrogate(j)) {
              int k = XMLChar.supplemental(c, (char)j);
              if (!isValid(k))
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[] { Integer.toString(k, 16) }(short)2); 
            } else {
              this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[] { Integer.toString(j, 16) }(short)2);
            } 
          } else {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[] { Integer.toString(c, 16) }(short)2);
          }  
      } 
      if (this.fHandler != null && i > 0) {
        this.fTempString.offset = 0;
        this.fTempString.length = i;
        this.fHandler.characters(this.fTempString, this.fHandler.modifyAugmentations(null, true));
      } 
      i = this.fReader.read(this.fTempString.ch, 0, this.fTempString.ch.length - 1);
    } 
  }
  
  public void setInputSource(XMLInputSource paramXMLInputSource) {
    this.fSource = paramXMLInputSource;
  }
  
  public void close() throws IOException {
    if (this.fReader != null) {
      this.fReader.close();
      this.fReader = null;
    } 
  }
  
  protected boolean isValid(int paramInt) {
    return XMLChar.isValid(paramInt);
  }
  
  protected void setBufferSize(int paramInt) {
    if (this.fTempString.ch.length != ++paramInt)
      this.fTempString.ch = new char[paramInt]; 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xinclude\XIncludeTextReader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */