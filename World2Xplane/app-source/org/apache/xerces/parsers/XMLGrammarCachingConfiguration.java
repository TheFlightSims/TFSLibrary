package org.apache.xerces.parsers;

import java.io.IOException;
import org.apache.xerces.impl.dtd.DTDGrammar;
import org.apache.xerces.impl.dtd.XMLDTDLoader;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.apache.xerces.util.XMLGrammarPoolImpl;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XMLGrammarCachingConfiguration extends XIncludeAwareParserConfiguration {
  public static final int BIG_PRIME = 2039;
  
  protected static final SynchronizedSymbolTable fStaticSymbolTable = new SynchronizedSymbolTable(2039);
  
  protected static final XMLGrammarPoolImpl fStaticGrammarPool = new XMLGrammarPoolImpl();
  
  protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
  
  protected XMLSchemaLoader fSchemaLoader = new XMLSchemaLoader(this.fSymbolTable);
  
  protected XMLDTDLoader fDTDLoader;
  
  public XMLGrammarCachingConfiguration() {
    this((SymbolTable)fStaticSymbolTable, (XMLGrammarPool)fStaticGrammarPool, null);
  }
  
  public XMLGrammarCachingConfiguration(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, (XMLGrammarPool)fStaticGrammarPool, null);
  }
  
  public XMLGrammarCachingConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    this(paramSymbolTable, paramXMLGrammarPool, null);
  }
  
  public XMLGrammarCachingConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool, XMLComponentManager paramXMLComponentManager) {
    super(paramSymbolTable, paramXMLGrammarPool, paramXMLComponentManager);
    this.fSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
    this.fDTDLoader = new XMLDTDLoader(this.fSymbolTable, this.fGrammarPool);
  }
  
  public void lockGrammarPool() {
    this.fGrammarPool.lockPool();
  }
  
  public void clearGrammarPool() {
    this.fGrammarPool.clear();
  }
  
  public void unlockGrammarPool() {
    this.fGrammarPool.unlockPool();
  }
  
  public Grammar parseGrammar(String paramString1, String paramString2) throws XNIException, IOException {
    XMLInputSource xMLInputSource = new XMLInputSource(null, paramString2, null);
    return parseGrammar(paramString1, xMLInputSource);
  }
  
  public Grammar parseGrammar(String paramString, XMLInputSource paramXMLInputSource) throws XNIException, IOException {
    return (Grammar)(paramString.equals("http://www.w3.org/2001/XMLSchema") ? parseXMLSchema(paramXMLInputSource) : (paramString.equals("http://www.w3.org/TR/REC-xml") ? parseDTD(paramXMLInputSource) : null));
  }
  
  protected void checkFeature(String paramString) throws XMLConfigurationException {
    super.checkFeature(paramString);
  }
  
  protected void checkProperty(String paramString) throws XMLConfigurationException {
    super.checkProperty(paramString);
  }
  
  SchemaGrammar parseXMLSchema(XMLInputSource paramXMLInputSource) throws IOException {
    XMLEntityResolver xMLEntityResolver = getEntityResolver();
    if (xMLEntityResolver != null)
      this.fSchemaLoader.setEntityResolver(xMLEntityResolver); 
    if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null)
      this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", (MessageFormatter)new XSMessageFormatter()); 
    this.fSchemaLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
    String str1 = "http://apache.org/xml/properties/";
    String str2 = str1 + "schema/external-schemaLocation";
    this.fSchemaLoader.setProperty(str2, getProperty(str2));
    str2 = str1 + "schema/external-noNamespaceSchemaLocation";
    this.fSchemaLoader.setProperty(str2, getProperty(str2));
    str2 = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    this.fSchemaLoader.setProperty(str2, getProperty(str2));
    this.fSchemaLoader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", getFeature("http://apache.org/xml/features/validation/schema-full-checking"));
    SchemaGrammar schemaGrammar = (SchemaGrammar)this.fSchemaLoader.loadGrammar(paramXMLInputSource);
    if (schemaGrammar != null)
      this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", new Grammar[] { (Grammar)schemaGrammar }); 
    return schemaGrammar;
  }
  
  DTDGrammar parseDTD(XMLInputSource paramXMLInputSource) throws IOException {
    XMLEntityResolver xMLEntityResolver = getEntityResolver();
    if (xMLEntityResolver != null)
      this.fDTDLoader.setEntityResolver(xMLEntityResolver); 
    this.fDTDLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
    DTDGrammar dTDGrammar = (DTDGrammar)this.fDTDLoader.loadGrammar(paramXMLInputSource);
    if (dTDGrammar != null)
      this.fGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[] { (Grammar)dTDGrammar }); 
    return dTDGrammar;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\XMLGrammarCachingConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */