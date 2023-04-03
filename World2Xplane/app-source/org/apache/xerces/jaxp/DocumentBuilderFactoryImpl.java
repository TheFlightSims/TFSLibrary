package org.apache.xerces.jaxp;

import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.util.SAXMessageFormatter;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class DocumentBuilderFactoryImpl extends DocumentBuilderFactory {
  private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
  
  private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
  
  private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
  
  private static final String INCLUDE_IGNORABLE_WHITESPACE = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
  
  private static final String CREATE_ENTITY_REF_NODES_FEATURE = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
  
  private static final String INCLUDE_COMMENTS_FEATURE = "http://apache.org/xml/features/include-comments";
  
  private static final String CREATE_CDATA_NODES_FEATURE = "http://apache.org/xml/features/create-cdata-nodes";
  
  private Hashtable attributes;
  
  private Hashtable features;
  
  private Schema grammar;
  
  private boolean isXIncludeAware;
  
  private boolean fSecureProcess = false;
  
  public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
    if (this.grammar != null && this.attributes != null) {
      if (this.attributes.containsKey("http://java.sun.com/xml/jaxp/properties/schemaLanguage"))
        throw new ParserConfigurationException(SAXMessageFormatter.formatMessage(null, "schema-already-specified", new Object[] { "http://java.sun.com/xml/jaxp/properties/schemaLanguage" })); 
      if (this.attributes.containsKey("http://java.sun.com/xml/jaxp/properties/schemaSource"))
        throw new ParserConfigurationException(SAXMessageFormatter.formatMessage(null, "schema-already-specified", new Object[] { "http://java.sun.com/xml/jaxp/properties/schemaSource" })); 
    } 
    try {
      return new DocumentBuilderImpl(this, this.attributes, this.features, this.fSecureProcess);
    } catch (SAXException sAXException) {
      throw new ParserConfigurationException(sAXException.getMessage());
    } 
  }
  
  public void setAttribute(String paramString, Object paramObject) throws IllegalArgumentException {
    if (paramObject == null) {
      if (this.attributes != null)
        this.attributes.remove(paramString); 
      return;
    } 
    if (this.attributes == null)
      this.attributes = new Hashtable(); 
    this.attributes.put(paramString, paramObject);
    try {
      new DocumentBuilderImpl(this, this.attributes, this.features);
    } catch (Exception exception) {
      this.attributes.remove(paramString);
      throw new IllegalArgumentException(exception.getMessage());
    } 
  }
  
  public Object getAttribute(String paramString) throws IllegalArgumentException {
    if (this.attributes != null) {
      Object object = this.attributes.get(paramString);
      if (object != null)
        return object; 
    } 
    DOMParser dOMParser = null;
    try {
      dOMParser = (new DocumentBuilderImpl(this, this.attributes, this.features)).getDOMParser();
      return dOMParser.getProperty(paramString);
    } catch (SAXException sAXException) {
      try {
        boolean bool = dOMParser.getFeature(paramString);
        return bool ? Boolean.TRUE : Boolean.FALSE;
      } catch (SAXException sAXException1) {
        throw new IllegalArgumentException(sAXException.getMessage());
      } 
    } 
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
  
  public boolean getFeature(String paramString) throws ParserConfigurationException {
    if (paramString.equals("http://javax.xml.XMLConstants/feature/secure-processing"))
      return this.fSecureProcess; 
    if (paramString.equals("http://xml.org/sax/features/namespaces"))
      return isNamespaceAware(); 
    if (paramString.equals("http://xml.org/sax/features/validation"))
      return isValidating(); 
    if (paramString.equals("http://apache.org/xml/features/xinclude"))
      return isXIncludeAware(); 
    if (paramString.equals("http://apache.org/xml/features/dom/include-ignorable-whitespace"))
      return !isIgnoringElementContentWhitespace(); 
    if (paramString.equals("http://apache.org/xml/features/dom/create-entity-ref-nodes"))
      return !isExpandEntityReferences(); 
    if (paramString.equals("http://apache.org/xml/features/include-comments"))
      return !isIgnoringComments(); 
    if (paramString.equals("http://apache.org/xml/features/create-cdata-nodes"))
      return !isCoalescing(); 
    if (this.features != null) {
      Object object = this.features.get(paramString);
      if (object != null)
        return ((Boolean)object).booleanValue(); 
    } 
    try {
      DOMParser dOMParser = (new DocumentBuilderImpl(this, this.attributes, this.features)).getDOMParser();
      return dOMParser.getFeature(paramString);
    } catch (SAXException sAXException) {
      throw new ParserConfigurationException(sAXException.getMessage());
    } 
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws ParserConfigurationException {
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
    if (paramString.equals("http://apache.org/xml/features/dom/include-ignorable-whitespace")) {
      setIgnoringElementContentWhitespace(!paramBoolean);
      return;
    } 
    if (paramString.equals("http://apache.org/xml/features/dom/create-entity-ref-nodes")) {
      setExpandEntityReferences(!paramBoolean);
      return;
    } 
    if (paramString.equals("http://apache.org/xml/features/include-comments")) {
      setIgnoringComments(!paramBoolean);
      return;
    } 
    if (paramString.equals("http://apache.org/xml/features/create-cdata-nodes")) {
      setCoalescing(!paramBoolean);
      return;
    } 
    if (this.features == null)
      this.features = new Hashtable(); 
    this.features.put(paramString, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
    try {
      new DocumentBuilderImpl(this, this.attributes, this.features);
    } catch (SAXNotSupportedException sAXNotSupportedException) {
      this.features.remove(paramString);
      throw new ParserConfigurationException(sAXNotSupportedException.getMessage());
    } catch (SAXNotRecognizedException sAXNotRecognizedException) {
      this.features.remove(paramString);
      throw new ParserConfigurationException(sAXNotRecognizedException.getMessage());
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\DocumentBuilderFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */