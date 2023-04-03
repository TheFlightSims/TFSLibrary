package org.apache.xerces.jaxp.validation;

import java.util.HashMap;
import java.util.Map;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.util.ParserConfigurationSettings;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;

final class XMLSchemaValidatorComponentManager extends ParserConfigurationSettings implements XMLComponentManager {
  private static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
  
  private static final String VALIDATION = "http://xml.org/sax/features/validation";
  
  private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
  
  protected static final String IGNORE_XSI_TYPE = "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl";
  
  protected static final String ID_IDREF_CHECKING = "http://apache.org/xml/features/validation/id-idref-checking";
  
  protected static final String UNPARSED_ENTITY_CHECKING = "http://apache.org/xml/features/validation/unparsed-entity-checking";
  
  protected static final String IDENTITY_CONSTRAINT_CHECKING = "http://apache.org/xml/features/validation/identity-constraint-checking";
  
  private static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
  
  private static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  private static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
  
  private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  private static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
  
  private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
  
  private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  private boolean fConfigUpdated = true;
  
  private boolean fUseGrammarPoolOnly;
  
  private final HashMap fComponents = new HashMap();
  
  private XMLEntityManager fEntityManager = new XMLEntityManager();
  
  private XMLErrorReporter fErrorReporter;
  
  private NamespaceContext fNamespaceContext;
  
  private XMLSchemaValidator fSchemaValidator;
  
  private ValidationManager fValidationManager;
  
  private HashMap fInitFeatures = new HashMap();
  
  private HashMap fInitProperties = new HashMap();
  
  private SecurityManager fInitSecurityManager = null;
  
  private ErrorHandler fErrorHandler = null;
  
  private LSResourceResolver fResourceResolver = null;
  
  public XMLSchemaValidatorComponentManager(XSGrammarPoolContainer paramXSGrammarPoolContainer) {
    this.fComponents.put("http://apache.org/xml/properties/internal/entity-manager", this.fEntityManager);
    this.fErrorReporter = new XMLErrorReporter();
    this.fComponents.put("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
    this.fNamespaceContext = (NamespaceContext)new NamespaceSupport();
    this.fComponents.put("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
    this.fSchemaValidator = new XMLSchemaValidator();
    this.fComponents.put("http://apache.org/xml/properties/internal/validator/schema", this.fSchemaValidator);
    this.fValidationManager = new ValidationManager();
    this.fComponents.put("http://apache.org/xml/properties/internal/validation-manager", this.fValidationManager);
    this.fComponents.put("http://apache.org/xml/properties/internal/entity-resolver", null);
    this.fComponents.put("http://apache.org/xml/properties/internal/error-handler", null);
    this.fComponents.put("http://apache.org/xml/properties/security-manager", null);
    this.fComponents.put("http://apache.org/xml/properties/internal/symbol-table", new SymbolTable());
    this.fComponents.put("http://apache.org/xml/properties/internal/grammar-pool", paramXSGrammarPoolContainer.getGrammarPool());
    this.fUseGrammarPoolOnly = paramXSGrammarPoolContainer.isFullyComposed();
    this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", (MessageFormatter)new XSMessageFormatter());
    addRecognizedParamsAndSetDefaults((XMLComponent)this.fEntityManager, paramXSGrammarPoolContainer);
    addRecognizedParamsAndSetDefaults((XMLComponent)this.fErrorReporter, paramXSGrammarPoolContainer);
    addRecognizedParamsAndSetDefaults((XMLComponent)this.fSchemaValidator, paramXSGrammarPoolContainer);
    Boolean bool = paramXSGrammarPoolContainer.getFeature("http://javax.xml.XMLConstants/feature/secure-processing");
    if (Boolean.TRUE.equals(bool))
      this.fInitSecurityManager = new SecurityManager(); 
    this.fComponents.put("http://apache.org/xml/properties/security-manager", this.fInitSecurityManager);
    this.fFeatures.put("http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl", Boolean.FALSE);
    this.fFeatures.put("http://apache.org/xml/features/validation/id-idref-checking", Boolean.TRUE);
    this.fFeatures.put("http://apache.org/xml/features/validation/identity-constraint-checking", Boolean.TRUE);
    this.fFeatures.put("http://apache.org/xml/features/validation/unparsed-entity-checking", Boolean.TRUE);
  }
  
  public boolean getFeature(String paramString) throws XMLConfigurationException {
    return "http://apache.org/xml/features/internal/parser-settings".equals(paramString) ? this.fConfigUpdated : (("http://xml.org/sax/features/validation".equals(paramString) || "http://apache.org/xml/features/validation/schema".equals(paramString)) ? true : ("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only".equals(paramString) ? this.fUseGrammarPoolOnly : ("http://javax.xml.XMLConstants/feature/secure-processing".equals(paramString) ? ((getProperty("http://apache.org/xml/properties/security-manager") != null)) : super.getFeature(paramString))));
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    if ("http://apache.org/xml/features/internal/parser-settings".equals(paramString))
      throw new XMLConfigurationException((short)1, paramString); 
    if (!paramBoolean && ("http://xml.org/sax/features/validation".equals(paramString) || "http://apache.org/xml/features/validation/schema".equals(paramString)))
      throw new XMLConfigurationException((short)1, paramString); 
    if ("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only".equals(paramString) && paramBoolean != this.fUseGrammarPoolOnly)
      throw new XMLConfigurationException((short)1, paramString); 
    if ("http://javax.xml.XMLConstants/feature/secure-processing".equals(paramString)) {
      setProperty("http://apache.org/xml/properties/security-manager", paramBoolean ? new SecurityManager() : null);
      return;
    } 
    this.fConfigUpdated = true;
    this.fEntityManager.setFeature(paramString, paramBoolean);
    this.fErrorReporter.setFeature(paramString, paramBoolean);
    this.fSchemaValidator.setFeature(paramString, paramBoolean);
    if (!this.fInitFeatures.containsKey(paramString)) {
      boolean bool = super.getFeature(paramString);
      this.fInitFeatures.put(paramString, bool ? Boolean.TRUE : Boolean.FALSE);
    } 
    super.setFeature(paramString, paramBoolean);
  }
  
  public Object getProperty(String paramString) throws XMLConfigurationException {
    Object object = this.fComponents.get(paramString);
    return (object != null) ? object : (this.fComponents.containsKey(paramString) ? null : super.getProperty(paramString));
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    if ("http://apache.org/xml/properties/internal/entity-manager".equals(paramString) || "http://apache.org/xml/properties/internal/error-reporter".equals(paramString) || "http://apache.org/xml/properties/internal/namespace-context".equals(paramString) || "http://apache.org/xml/properties/internal/validator/schema".equals(paramString) || "http://apache.org/xml/properties/internal/symbol-table".equals(paramString) || "http://apache.org/xml/properties/internal/validation-manager".equals(paramString) || "http://apache.org/xml/properties/internal/grammar-pool".equals(paramString))
      throw new XMLConfigurationException((short)1, paramString); 
    this.fConfigUpdated = true;
    this.fEntityManager.setProperty(paramString, paramObject);
    this.fErrorReporter.setProperty(paramString, paramObject);
    this.fSchemaValidator.setProperty(paramString, paramObject);
    if ("http://apache.org/xml/properties/internal/entity-resolver".equals(paramString) || "http://apache.org/xml/properties/internal/error-handler".equals(paramString) || "http://apache.org/xml/properties/security-manager".equals(paramString)) {
      this.fComponents.put(paramString, paramObject);
      return;
    } 
    if (!this.fInitProperties.containsKey(paramString))
      this.fInitProperties.put(paramString, super.getProperty(paramString)); 
    super.setProperty(paramString, paramObject);
  }
  
  public void addRecognizedParamsAndSetDefaults(XMLComponent paramXMLComponent, XSGrammarPoolContainer paramXSGrammarPoolContainer) {
    String[] arrayOfString1 = paramXMLComponent.getRecognizedFeatures();
    addRecognizedFeatures(arrayOfString1);
    String[] arrayOfString2 = paramXMLComponent.getRecognizedProperties();
    addRecognizedProperties(arrayOfString2);
    setFeatureDefaults(paramXMLComponent, arrayOfString1, paramXSGrammarPoolContainer);
    setPropertyDefaults(paramXMLComponent, arrayOfString2);
  }
  
  public void reset() throws XNIException {
    this.fNamespaceContext.reset();
    this.fValidationManager.reset();
    this.fEntityManager.reset(this);
    this.fErrorReporter.reset(this);
    this.fSchemaValidator.reset(this);
    this.fConfigUpdated = false;
  }
  
  void setErrorHandler(ErrorHandler paramErrorHandler) {
    this.fErrorHandler = paramErrorHandler;
    setProperty("http://apache.org/xml/properties/internal/error-handler", (paramErrorHandler != null) ? new ErrorHandlerWrapper(paramErrorHandler) : new ErrorHandlerWrapper(DraconianErrorHandler.getInstance()));
  }
  
  ErrorHandler getErrorHandler() {
    return this.fErrorHandler;
  }
  
  void setResourceResolver(LSResourceResolver paramLSResourceResolver) {
    this.fResourceResolver = paramLSResourceResolver;
    setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper(paramLSResourceResolver));
  }
  
  public LSResourceResolver getResourceResolver() {
    return this.fResourceResolver;
  }
  
  void restoreInitialState() {
    this.fConfigUpdated = true;
    this.fComponents.put("http://apache.org/xml/properties/internal/entity-resolver", null);
    this.fComponents.put("http://apache.org/xml/properties/internal/error-handler", null);
    this.fComponents.put("http://apache.org/xml/properties/security-manager", this.fInitSecurityManager);
    if (!this.fInitFeatures.isEmpty()) {
      for (Map.Entry entry : this.fInitFeatures.entrySet()) {
        String str = (String)entry.getKey();
        boolean bool = ((Boolean)entry.getValue()).booleanValue();
        super.setFeature(str, bool);
      } 
      this.fInitFeatures.clear();
    } 
    if (!this.fInitProperties.isEmpty()) {
      for (Map.Entry entry : this.fInitProperties.entrySet()) {
        String str = (String)entry.getKey();
        Object object = entry.getValue();
        super.setProperty(str, object);
      } 
      this.fInitProperties.clear();
    } 
  }
  
  private void setFeatureDefaults(XMLComponent paramXMLComponent, String[] paramArrayOfString, XSGrammarPoolContainer paramXSGrammarPoolContainer) {
    if (paramArrayOfString != null)
      for (byte b = 0; b < paramArrayOfString.length; b++) {
        String str = paramArrayOfString[b];
        Boolean bool = paramXSGrammarPoolContainer.getFeature(str);
        if (bool == null)
          bool = paramXMLComponent.getFeatureDefault(str); 
        if (bool != null && !this.fFeatures.containsKey(str)) {
          this.fFeatures.put(str, bool);
          this.fConfigUpdated = true;
        } 
      }  
  }
  
  private void setPropertyDefaults(XMLComponent paramXMLComponent, String[] paramArrayOfString) {
    if (paramArrayOfString != null)
      for (byte b = 0; b < paramArrayOfString.length; b++) {
        String str = paramArrayOfString[b];
        Object object = paramXMLComponent.getPropertyDefault(str);
        if (object != null && !this.fProperties.containsKey(str)) {
          this.fProperties.put(str, object);
          this.fConfigUpdated = true;
        } 
      }  
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\XMLSchemaValidatorComponentManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */