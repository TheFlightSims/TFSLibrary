package org.apache.xerces.parsers;

import org.apache.xerces.impl.XMLDocumentScannerImpl;
import org.apache.xerces.impl.XMLNSDocumentScannerImpl;
import org.apache.xerces.impl.dtd.XMLDTDValidator;
import org.apache.xerces.impl.dtd.XMLDTDValidatorFilter;
import org.apache.xerces.impl.dtd.XMLNSDTDValidator;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLDocumentScanner;
import org.apache.xerces.xni.parser.XMLDocumentSource;

public class IntegratedParserConfiguration extends StandardParserConfiguration {
  protected XMLNSDocumentScannerImpl fNamespaceScanner;
  
  protected XMLDocumentScannerImpl fNonNSScanner = new XMLDocumentScannerImpl();
  
  protected XMLDTDValidator fNonNSDTDValidator = new XMLDTDValidator();
  
  public IntegratedParserConfiguration() {
    this(null, null, null);
  }
  
  public IntegratedParserConfiguration(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, null, null);
  }
  
  public IntegratedParserConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    this(paramSymbolTable, paramXMLGrammarPool, null);
  }
  
  public IntegratedParserConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool, XMLComponentManager paramXMLComponentManager) {
    super(paramSymbolTable, paramXMLGrammarPool, paramXMLComponentManager);
    addComponent((XMLComponent)this.fNonNSScanner);
    addComponent((XMLComponent)this.fNonNSDTDValidator);
  }
  
  protected void configurePipeline() {
    setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fDatatypeValidatorFactory);
    configureDTDPipeline();
    if (this.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
      this.fProperties.put("http://apache.org/xml/properties/internal/namespace-binder", this.fNamespaceBinder);
      this.fScanner = (XMLDocumentScanner)this.fNamespaceScanner;
      this.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fNamespaceScanner);
      if (this.fDTDValidator != null) {
        this.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", this.fDTDValidator);
        this.fNamespaceScanner.setDTDValidator((XMLDTDValidatorFilter)this.fDTDValidator);
        this.fNamespaceScanner.setDocumentHandler((XMLDocumentHandler)this.fDTDValidator);
        this.fDTDValidator.setDocumentSource((XMLDocumentSource)this.fNamespaceScanner);
        this.fDTDValidator.setDocumentHandler(this.fDocumentHandler);
        if (this.fDocumentHandler != null)
          this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fDTDValidator); 
        this.fLastComponent = (XMLDocumentSource)this.fDTDValidator;
      } else {
        this.fNamespaceScanner.setDocumentHandler(this.fDocumentHandler);
        this.fNamespaceScanner.setDTDValidator(null);
        if (this.fDocumentHandler != null)
          this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fNamespaceScanner); 
        this.fLastComponent = (XMLDocumentSource)this.fNamespaceScanner;
      } 
    } else {
      this.fScanner = (XMLDocumentScanner)this.fNonNSScanner;
      this.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fNonNSScanner);
      if (this.fNonNSDTDValidator != null) {
        this.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", this.fNonNSDTDValidator);
        this.fNonNSScanner.setDocumentHandler((XMLDocumentHandler)this.fNonNSDTDValidator);
        this.fNonNSDTDValidator.setDocumentSource((XMLDocumentSource)this.fNonNSScanner);
        this.fNonNSDTDValidator.setDocumentHandler(this.fDocumentHandler);
        if (this.fDocumentHandler != null)
          this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fNonNSDTDValidator); 
        this.fLastComponent = (XMLDocumentSource)this.fNonNSDTDValidator;
      } else {
        this.fScanner.setDocumentHandler(this.fDocumentHandler);
        if (this.fDocumentHandler != null)
          this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fScanner); 
        this.fLastComponent = (XMLDocumentSource)this.fScanner;
      } 
    } 
    if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
      if (this.fSchemaValidator == null) {
        this.fSchemaValidator = new XMLSchemaValidator();
        this.fProperties.put("http://apache.org/xml/properties/internal/validator/schema", this.fSchemaValidator);
        addComponent((XMLComponent)this.fSchemaValidator);
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
          XSMessageFormatter xSMessageFormatter = new XSMessageFormatter();
          this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", (MessageFormatter)xSMessageFormatter);
        } 
      } 
      this.fLastComponent.setDocumentHandler((XMLDocumentHandler)this.fSchemaValidator);
      this.fSchemaValidator.setDocumentSource(this.fLastComponent);
      this.fSchemaValidator.setDocumentHandler(this.fDocumentHandler);
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fSchemaValidator); 
      this.fLastComponent = (XMLDocumentSource)this.fSchemaValidator;
    } 
  }
  
  protected XMLDocumentScanner createDocumentScanner() {
    this.fNamespaceScanner = new XMLNSDocumentScannerImpl();
    return (XMLDocumentScanner)this.fNamespaceScanner;
  }
  
  protected XMLDTDValidator createDTDValidator() {
    return (XMLDTDValidator)new XMLNSDTDValidator();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\IntegratedParserConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */