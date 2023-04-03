package org.apache.xerces.jaxp;

import java.util.Hashtable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class SAXParserFactoryImpl extends SAXParserFactory {
  private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
  
  private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
  
  private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
  
  private Hashtable features;
  
  private Schema grammar;
  
  private boolean isXIncludeAware;
  
  private boolean fSecureProcess = false;
  
  public SAXParser newSAXParser() throws ParserConfigurationException {
    SAXParserImpl sAXParserImpl;
    try {
      sAXParserImpl = new SAXParserImpl(this, this.features, this.fSecureProcess);
    } catch (SAXException sAXException) {
      throw new ParserConfigurationException(sAXException.getMessage());
    } 
    return sAXParserImpl;
  }
  
  private SAXParserImpl newSAXParserImpl() throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
    SAXParserImpl sAXParserImpl;
    try {
      sAXParserImpl = new SAXParserImpl(this, this.features);
    } catch (SAXNotSupportedException sAXNotSupportedException) {
      throw sAXNotSupportedException;
    } catch (SAXNotRecognizedException sAXNotRecognizedException) {
      throw sAXNotRecognizedException;
    } catch (SAXException sAXException) {
      throw new ParserConfigurationException(sAXException.getMessage());
    } 
    return sAXParserImpl;
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    if (paramString.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
      this.fSecureProcess = paramBoolean;
      return;
    } 
    if (paramString.equals("http://xml.org/sax/features/namespaces")) {
      setNamespaceAware(paramBoolean);
      return;
    } 
    if (paramString.equals("http://xml.org/sax/features/validation")) {
      setValidating(paramBoolean);
      return;
    } 
    if (paramString.equals("http://apache.org/xml/features/xinclude")) {
      setXIncludeAware(paramBoolean);
      return;
    } 
    if (this.features == null)
      this.features = new Hashtable(); 
    this.features.put(paramString, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
    try {
      newSAXParserImpl();
    } catch (SAXNotSupportedException sAXNotSupportedException) {
      this.features.remove(paramString);
      throw sAXNotSupportedException;
    } catch (SAXNotRecognizedException sAXNotRecognizedException) {
      this.features.remove(paramString);
      throw sAXNotRecognizedException;
    } 
  }
  
  public boolean getFeature(String paramString) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    return paramString.equals("http://javax.xml.XMLConstants/feature/secure-processing") ? this.fSecureProcess : (paramString.equals("http://xml.org/sax/features/namespaces") ? isNamespaceAware() : (paramString.equals("http://xml.org/sax/features/validation") ? isValidating() : (paramString.equals("http://apache.org/xml/features/xinclude") ? isXIncludeAware() : newSAXParserImpl().getXMLReader().getFeature(paramString))));
  }
  
  public Schema getSchema() {
    return this.grammar;
  }
  
  public void setSchema(Schema paramSchema) {
    this.grammar = paramSchema;
  }
  
  public boolean isXIncludeAware() {
    return this.isXIncludeAware;
  }
  
  public void setXIncludeAware(boolean paramBoolean) {
    this.isXIncludeAware = paramBoolean;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\SAXParserFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */