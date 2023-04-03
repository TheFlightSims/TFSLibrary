package org.apache.xerces.jaxp;

import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.jaxp.validation.XSGrammarPoolContainer;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;

final class SchemaValidatorConfiguration implements XMLComponentManager {
  private static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
  
  private static final String VALIDATION = "http://xml.org/sax/features/validation";
  
  private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
  
  private static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
  
  private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  private final XMLComponentManager fParentComponentManager;
  
  private final XMLGrammarPool fGrammarPool;
  
  private final boolean fUseGrammarPoolOnly;
  
  private final ValidationManager fValidationManager;
  
  public SchemaValidatorConfiguration(XMLComponentManager paramXMLComponentManager, XSGrammarPoolContainer paramXSGrammarPoolContainer, ValidationManager paramValidationManager) {
    this.fParentComponentManager = paramXMLComponentManager;
    this.fGrammarPool = paramXSGrammarPoolContainer.getGrammarPool();
    this.fUseGrammarPoolOnly = paramXSGrammarPoolContainer.isFullyComposed();
    this.fValidationManager = paramValidationManager;
    try {
      XMLErrorReporter xMLErrorReporter = (XMLErrorReporter)this.fParentComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
      if (xMLErrorReporter != null)
        xMLErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", (MessageFormatter)new XSMessageFormatter()); 
    } catch (XMLConfigurationException xMLConfigurationException) {}
  }
  
  public boolean getFeature(String paramString) throws XMLConfigurationException {
    return "http://apache.org/xml/features/internal/parser-settings".equals(paramString) ? this.fParentComponentManager.getFeature(paramString) : (("http://xml.org/sax/features/validation".equals(paramString) || "http://apache.org/xml/features/validation/schema".equals(paramString)) ? true : ("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only".equals(paramString) ? this.fUseGrammarPoolOnly : this.fParentComponentManager.getFeature(paramString)));
  }
  
  public Object getProperty(String paramString) throws XMLConfigurationException {
    return "http://apache.org/xml/properties/internal/grammar-pool".equals(paramString) ? this.fGrammarPool : ("http://apache.org/xml/properties/internal/validation-manager".equals(paramString) ? this.fValidationManager : this.fParentComponentManager.getProperty(paramString));
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\SchemaValidatorConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */