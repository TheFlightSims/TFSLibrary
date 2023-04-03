package org.apache.xerces.parsers;

import java.util.Vector;
import org.apache.xerces.dom.ASModelImpl;
import org.apache.xerces.dom3.as.ASModel;
import org.apache.xerces.dom3.as.DOMASBuilder;
import org.apache.xerces.dom3.as.DOMASException;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.XSGrammarBucket;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLGrammarPoolImpl;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.w3c.dom.ls.LSInput;

public class DOMASBuilderImpl extends DOMParserImpl implements DOMASBuilder {
  protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
  
  protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
  
  protected XSGrammarBucket fGrammarBucket;
  
  protected ASModelImpl fAbstractSchema;
  
  public DOMASBuilderImpl() {
    super((XMLParserConfiguration)new XMLGrammarCachingConfiguration());
  }
  
  public DOMASBuilderImpl(XMLGrammarCachingConfiguration paramXMLGrammarCachingConfiguration) {
    super((XMLParserConfiguration)paramXMLGrammarCachingConfiguration);
  }
  
  public DOMASBuilderImpl(SymbolTable paramSymbolTable) {
    super((XMLParserConfiguration)new XMLGrammarCachingConfiguration(paramSymbolTable));
  }
  
  public DOMASBuilderImpl(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    super((XMLParserConfiguration)new XMLGrammarCachingConfiguration(paramSymbolTable, paramXMLGrammarPool));
  }
  
  public ASModel getAbstractSchema() {
    return (ASModel)this.fAbstractSchema;
  }
  
  public void setAbstractSchema(ASModel paramASModel) {
    XMLGrammarPoolImpl xMLGrammarPoolImpl;
    this.fAbstractSchema = (ASModelImpl)paramASModel;
    XMLGrammarPool xMLGrammarPool = (XMLGrammarPool)this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
    if (xMLGrammarPool == null) {
      xMLGrammarPoolImpl = new XMLGrammarPoolImpl();
      this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPoolImpl);
    } 
    if (this.fAbstractSchema != null)
      initGrammarPool(this.fAbstractSchema, (XMLGrammarPool)xMLGrammarPoolImpl); 
  }
  
  public ASModel parseASURI(String paramString) throws DOMASException, Exception {
    XMLInputSource xMLInputSource = new XMLInputSource(null, paramString, null);
    return parseASInputSource(xMLInputSource);
  }
  
  public ASModel parseASInputSource(LSInput paramLSInput) throws DOMASException, Exception {
    XMLInputSource xMLInputSource = dom2xmlInputSource(paramLSInput);
    try {
      return parseASInputSource(xMLInputSource);
    } catch (XNIException xNIException) {
      Exception exception = xNIException.getException();
      throw exception;
    } 
  }
  
  ASModel parseASInputSource(XMLInputSource paramXMLInputSource) throws Exception {
    if (this.fGrammarBucket == null)
      this.fGrammarBucket = new XSGrammarBucket(); 
    initGrammarBucket();
    XMLGrammarCachingConfiguration xMLGrammarCachingConfiguration = (XMLGrammarCachingConfiguration)this.fConfiguration;
    xMLGrammarCachingConfiguration.lockGrammarPool();
    SchemaGrammar schemaGrammar = xMLGrammarCachingConfiguration.parseXMLSchema(paramXMLInputSource);
    xMLGrammarCachingConfiguration.unlockGrammarPool();
    ASModelImpl aSModelImpl = null;
    if (schemaGrammar != null) {
      aSModelImpl = new ASModelImpl();
      this.fGrammarBucket.putGrammar(schemaGrammar, true);
      addGrammars(aSModelImpl, this.fGrammarBucket);
    } 
    return (ASModel)aSModelImpl;
  }
  
  private void initGrammarBucket() {
    this.fGrammarBucket.reset();
    if (this.fAbstractSchema != null)
      initGrammarBucketRecurse(this.fAbstractSchema); 
  }
  
  private void initGrammarBucketRecurse(ASModelImpl paramASModelImpl) {
    if (paramASModelImpl.getGrammar() != null)
      this.fGrammarBucket.putGrammar(paramASModelImpl.getGrammar()); 
    for (byte b = 0; b < paramASModelImpl.getInternalASModels().size(); b++) {
      ASModelImpl aSModelImpl = paramASModelImpl.getInternalASModels().elementAt(b);
      initGrammarBucketRecurse(aSModelImpl);
    } 
  }
  
  private void addGrammars(ASModelImpl paramASModelImpl, XSGrammarBucket paramXSGrammarBucket) {
    SchemaGrammar[] arrayOfSchemaGrammar = paramXSGrammarBucket.getGrammars();
    for (byte b = 0; b < arrayOfSchemaGrammar.length; b++) {
      ASModelImpl aSModelImpl = new ASModelImpl();
      aSModelImpl.setGrammar(arrayOfSchemaGrammar[b]);
      paramASModelImpl.addASModel((ASModel)aSModelImpl);
    } 
  }
  
  private void initGrammarPool(ASModelImpl paramASModelImpl, XMLGrammarPool paramXMLGrammarPool) {
    Grammar[] arrayOfGrammar = new Grammar[1];
    arrayOfGrammar[0] = (Grammar)paramASModelImpl.getGrammar();
    if (paramASModelImpl.getGrammar() != null)
      paramXMLGrammarPool.cacheGrammars(arrayOfGrammar[0].getGrammarDescription().getGrammarType(), arrayOfGrammar); 
    Vector vector = paramASModelImpl.getInternalASModels();
    for (byte b = 0; b < vector.size(); b++)
      initGrammarPool(vector.elementAt(b), paramXMLGrammarPool); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\DOMASBuilderImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */