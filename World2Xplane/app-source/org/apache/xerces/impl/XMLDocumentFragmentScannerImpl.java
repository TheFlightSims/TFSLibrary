package org.apache.xerces.impl;

import java.io.CharConversionException;
import java.io.EOFException;
import java.io.IOException;
import org.apache.xerces.impl.io.MalformedByteSequenceException;
import org.apache.xerces.util.AugmentationsImpl;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDocumentScanner;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDocumentFragmentScannerImpl extends XMLScanner implements XMLDocumentScanner, XMLComponent, XMLEntityHandler {
  protected static final int SCANNER_STATE_START_OF_MARKUP = 1;
  
  protected static final int SCANNER_STATE_COMMENT = 2;
  
  protected static final int SCANNER_STATE_PI = 3;
  
  protected static final int SCANNER_STATE_DOCTYPE = 4;
  
  protected static final int SCANNER_STATE_ROOT_ELEMENT = 6;
  
  protected static final int SCANNER_STATE_CONTENT = 7;
  
  protected static final int SCANNER_STATE_REFERENCE = 8;
  
  protected static final int SCANNER_STATE_END_OF_INPUT = 13;
  
  protected static final int SCANNER_STATE_TERMINATED = 14;
  
  protected static final int SCANNER_STATE_CDATA = 15;
  
  protected static final int SCANNER_STATE_TEXT_DECL = 16;
  
  protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
  
  protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
  
  protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  private static final String[] RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/namespaces", "http://xml.org/sax/features/validation", "http://apache.org/xml/features/scanner/notify-builtin-refs", "http://apache.org/xml/features/scanner/notify-char-refs" };
  
  private static final Boolean[] FEATURE_DEFAULTS = new Boolean[] { null, null, Boolean.FALSE, Boolean.FALSE };
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/entity-resolver" };
  
  private static final Object[] PROPERTY_DEFAULTS = new Object[] { null, null, null, null };
  
  private static final boolean DEBUG_SCANNER_STATE = false;
  
  private static final boolean DEBUG_DISPATCHER = false;
  
  protected static final boolean DEBUG_CONTENT_SCANNING = false;
  
  protected XMLDocumentHandler fDocumentHandler;
  
  protected int[] fEntityStack = new int[4];
  
  protected int fMarkupDepth;
  
  protected int fScannerState;
  
  protected boolean fInScanContent = false;
  
  protected boolean fHasExternalDTD;
  
  protected boolean fStandalone;
  
  protected boolean fIsEntityDeclaredVC;
  
  protected ExternalSubsetResolver fExternalSubsetResolver;
  
  protected QName fCurrentElement;
  
  protected final ElementStack fElementStack = new ElementStack();
  
  protected boolean fNotifyBuiltInRefs = false;
  
  protected Dispatcher fDispatcher;
  
  protected final Dispatcher fContentDispatcher = createContentDispatcher();
  
  protected final QName fElementQName = new QName();
  
  protected final QName fAttributeQName = new QName();
  
  protected final XMLAttributesImpl fAttributes = new XMLAttributesImpl();
  
  protected final XMLString fTempString = new XMLString();
  
  protected final XMLString fTempString2 = new XMLString();
  
  private final String[] fStrings = new String[3];
  
  private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
  
  private final XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
  
  private final QName fQName = new QName();
  
  private final char[] fSingleChar = new char[1];
  
  private boolean fSawSpace;
  
  private Augmentations fTempAugmentations = null;
  
  public void setInputSource(XMLInputSource paramXMLInputSource) throws IOException {
    this.fEntityManager.setEntityHandler(this);
    this.fEntityManager.startEntity("$fragment$", paramXMLInputSource, false, true);
  }
  
  public boolean scanDocument(boolean paramBoolean) throws IOException, XNIException {
    this.fEntityScanner = this.fEntityManager.getEntityScanner();
    this.fEntityManager.setEntityHandler(this);
    while (true) {
      if (!this.fDispatcher.dispatch(paramBoolean))
        return false; 
      if (!paramBoolean)
        return true; 
    } 
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException {
    super.reset(paramXMLComponentManager);
    this.fAttributes.setNamespaces(this.fNamespaces);
    this.fMarkupDepth = 0;
    this.fCurrentElement = null;
    this.fElementStack.clear();
    this.fHasExternalDTD = false;
    this.fStandalone = false;
    this.fIsEntityDeclaredVC = false;
    this.fInScanContent = false;
    setScannerState(7);
    setDispatcher(this.fContentDispatcher);
    if (this.fParserSettings) {
      try {
        this.fNotifyBuiltInRefs = paramXMLComponentManager.getFeature("http://apache.org/xml/features/scanner/notify-builtin-refs");
      } catch (XMLConfigurationException xMLConfigurationException) {
        this.fNotifyBuiltInRefs = false;
      } 
      try {
        Object object = paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
        this.fExternalSubsetResolver = (object instanceof ExternalSubsetResolver) ? (ExternalSubsetResolver)object : null;
      } catch (XMLConfigurationException xMLConfigurationException) {
        this.fExternalSubsetResolver = null;
      } 
    } 
  }
  
  public String[] getRecognizedFeatures() {
    return (String[])RECOGNIZED_FEATURES.clone();
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    super.setFeature(paramString, paramBoolean);
    if (paramString.startsWith("http://apache.org/xml/features/")) {
      int i = paramString.length() - "http://apache.org/xml/features/".length();
      if (i == "scanner/notify-builtin-refs".length() && paramString.endsWith("scanner/notify-builtin-refs"))
        this.fNotifyBuiltInRefs = paramBoolean; 
    } 
  }
  
  public String[] getRecognizedProperties() {
    return (String[])RECOGNIZED_PROPERTIES.clone();
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    super.setProperty(paramString, paramObject);
    if (paramString.startsWith("http://apache.org/xml/properties/")) {
      int i = paramString.length() - "http://apache.org/xml/properties/".length();
      if (i == "internal/entity-manager".length() && paramString.endsWith("internal/entity-manager")) {
        this.fEntityManager = (XMLEntityManager)paramObject;
        return;
      } 
      if (i == "internal/entity-resolver".length() && paramString.endsWith("internal/entity-resolver")) {
        this.fExternalSubsetResolver = (paramObject instanceof ExternalSubsetResolver) ? (ExternalSubsetResolver)paramObject : null;
        return;
      } 
    } 
  }
  
  public Boolean getFeatureDefault(String paramString) {
    for (byte b = 0; b < RECOGNIZED_FEATURES.length; b++) {
      if (RECOGNIZED_FEATURES[b].equals(paramString))
        return FEATURE_DEFAULTS[b]; 
    } 
    return null;
  }
  
  public Object getPropertyDefault(String paramString) {
    for (byte b = 0; b < RECOGNIZED_PROPERTIES.length; b++) {
      if (RECOGNIZED_PROPERTIES[b].equals(paramString))
        return PROPERTY_DEFAULTS[b]; 
    } 
    return null;
  }
  
  public void setDocumentHandler(XMLDocumentHandler paramXMLDocumentHandler) {
    this.fDocumentHandler = paramXMLDocumentHandler;
  }
  
  public XMLDocumentHandler getDocumentHandler() {
    return this.fDocumentHandler;
  }
  
  public void startEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fEntityDepth == this.fEntityStack.length) {
      int[] arrayOfInt = new int[this.fEntityStack.length * 2];
      System.arraycopy(this.fEntityStack, 0, arrayOfInt, 0, this.fEntityStack.length);
      this.fEntityStack = arrayOfInt;
    } 
    this.fEntityStack[this.fEntityDepth] = this.fMarkupDepth;
    super.startEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations);
    if (this.fStandalone && this.fEntityManager.isEntityDeclInExternalSubset(paramString1))
      reportFatalError("MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[] { paramString1 }); 
    if (this.fDocumentHandler != null && !this.fScanningAttribute && !paramString1.equals("[xml]"))
      this.fDocumentHandler.startGeneralEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations); 
  }
  
  public void endEntity(String paramString, Augmentations paramAugmentations) throws XNIException {
    if (this.fInScanContent && ((XMLString)this.fStringBuffer).length != 0 && this.fDocumentHandler != null) {
      this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null);
      ((XMLString)this.fStringBuffer).length = 0;
    } 
    super.endEntity(paramString, paramAugmentations);
    if (this.fMarkupDepth != this.fEntityStack[this.fEntityDepth])
      reportFatalError("MarkupEntityMismatch", null); 
    if (this.fDocumentHandler != null && !this.fScanningAttribute && !paramString.equals("[xml]"))
      this.fDocumentHandler.endGeneralEntity(paramString, paramAugmentations); 
  }
  
  protected Dispatcher createContentDispatcher() {
    return new FragmentContentDispatcher(this);
  }
  
  protected void scanXMLDeclOrTextDecl(boolean paramBoolean) throws IOException, XNIException {
    scanXMLDeclOrTextDecl(paramBoolean, this.fStrings);
    this.fMarkupDepth--;
    String str1 = this.fStrings[0];
    String str2 = this.fStrings[1];
    String str3 = this.fStrings[2];
    this.fStandalone = (str3 != null && str3.equals("yes"));
    this.fEntityManager.setStandalone(this.fStandalone);
    this.fEntityScanner.setXMLVersion(str1);
    if (this.fDocumentHandler != null)
      if (paramBoolean) {
        this.fDocumentHandler.textDecl(str1, str2, null);
      } else {
        this.fDocumentHandler.xmlDecl(str1, str2, str3, null);
      }  
    if (str2 != null && !this.fEntityScanner.fCurrentEntity.isEncodingExternallySpecified())
      this.fEntityScanner.setEncoding(str2); 
  }
  
  protected void scanPIData(String paramString, XMLString paramXMLString) throws IOException, XNIException {
    super.scanPIData(paramString, paramXMLString);
    this.fMarkupDepth--;
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.processingInstruction(paramString, paramXMLString, null); 
  }
  
  protected void scanComment() throws IOException, XNIException {
    scanComment(this.fStringBuffer);
    this.fMarkupDepth--;
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.comment((XMLString)this.fStringBuffer, null); 
  }
  
  protected boolean scanStartElement() throws IOException, XNIException {
    if (this.fNamespaces) {
      this.fEntityScanner.scanQName(this.fElementQName);
    } else {
      String str1 = this.fEntityScanner.scanName();
      this.fElementQName.setValues(null, str1, str1, null);
    } 
    String str = this.fElementQName.rawname;
    this.fCurrentElement = this.fElementStack.pushElement(this.fElementQName);
    boolean bool = false;
    this.fAttributes.removeAllAttributes();
    while (true) {
      boolean bool1 = this.fEntityScanner.skipSpaces();
      int i = this.fEntityScanner.peekChar();
      if (i == 62) {
        this.fEntityScanner.scanChar();
        break;
      } 
      if (i == 47) {
        this.fEntityScanner.scanChar();
        if (!this.fEntityScanner.skipChar(62))
          reportFatalError("ElementUnterminated", new Object[] { str }); 
        bool = true;
        break;
      } 
      if ((!isValidNameStartChar(i) || !bool1) && (!isValidNameStartHighSurrogate(i) || !bool1))
        reportFatalError("ElementUnterminated", new Object[] { str }); 
      scanAttribute((XMLAttributes)this.fAttributes);
    } 
    if (this.fDocumentHandler != null)
      if (bool) {
        this.fMarkupDepth--;
        if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1])
          reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname }); 
        this.fDocumentHandler.emptyElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
        this.fElementStack.popElement(this.fElementQName);
      } else {
        this.fDocumentHandler.startElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
      }  
    return bool;
  }
  
  protected void scanStartElementName() throws IOException, XNIException {
    if (this.fNamespaces) {
      this.fEntityScanner.scanQName(this.fElementQName);
    } else {
      String str = this.fEntityScanner.scanName();
      this.fElementQName.setValues(null, str, str, null);
    } 
    this.fSawSpace = this.fEntityScanner.skipSpaces();
  }
  
  protected boolean scanStartElementAfterName() throws IOException, XNIException {
    String str = this.fElementQName.rawname;
    this.fCurrentElement = this.fElementStack.pushElement(this.fElementQName);
    boolean bool = false;
    this.fAttributes.removeAllAttributes();
    while (true) {
      int i = this.fEntityScanner.peekChar();
      if (i == 62) {
        this.fEntityScanner.scanChar();
        break;
      } 
      if (i == 47) {
        this.fEntityScanner.scanChar();
        if (!this.fEntityScanner.skipChar(62))
          reportFatalError("ElementUnterminated", new Object[] { str }); 
        bool = true;
        break;
      } 
      if ((!isValidNameStartChar(i) || !this.fSawSpace) && (!isValidNameStartHighSurrogate(i) || !this.fSawSpace))
        reportFatalError("ElementUnterminated", new Object[] { str }); 
      scanAttribute((XMLAttributes)this.fAttributes);
      this.fSawSpace = this.fEntityScanner.skipSpaces();
    } 
    if (this.fDocumentHandler != null)
      if (bool) {
        this.fMarkupDepth--;
        if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1])
          reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname }); 
        this.fDocumentHandler.emptyElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
        this.fElementStack.popElement(this.fElementQName);
      } else {
        this.fDocumentHandler.startElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
      }  
    return bool;
  }
  
  protected void scanAttribute(XMLAttributes paramXMLAttributes) throws IOException, XNIException {
    if (this.fNamespaces) {
      this.fEntityScanner.scanQName(this.fAttributeQName);
    } else {
      String str = this.fEntityScanner.scanName();
      this.fAttributeQName.setValues(null, str, str, null);
    } 
    this.fEntityScanner.skipSpaces();
    if (!this.fEntityScanner.skipChar(61))
      reportFatalError("EqRequiredInAttribute", new Object[] { this.fCurrentElement.rawname, this.fAttributeQName.rawname }); 
    this.fEntityScanner.skipSpaces();
    int i = paramXMLAttributes.getLength();
    int j = paramXMLAttributes.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
    if (i == paramXMLAttributes.getLength())
      reportFatalError("AttributeNotUnique", new Object[] { this.fCurrentElement.rawname, this.fAttributeQName.rawname }); 
    boolean bool = scanAttributeValue(this.fTempString, this.fTempString2, this.fAttributeQName.rawname, this.fIsEntityDeclaredVC, this.fCurrentElement.rawname);
    paramXMLAttributes.setValue(j, this.fTempString.toString());
    if (!bool)
      paramXMLAttributes.setNonNormalizedValue(j, this.fTempString2.toString()); 
    paramXMLAttributes.setSpecified(j, true);
  }
  
  protected int scanContent() throws IOException, XNIException {
    XMLStringBuffer xMLStringBuffer;
    XMLString xMLString = this.fTempString;
    int i = this.fEntityScanner.scanContent(xMLString);
    if (i == 13) {
      this.fEntityScanner.scanChar();
      this.fStringBuffer.clear();
      this.fStringBuffer.append(this.fTempString);
      this.fStringBuffer.append((char)i);
      xMLStringBuffer = this.fStringBuffer;
      i = -1;
    } 
    if (this.fDocumentHandler != null && ((XMLString)xMLStringBuffer).length > 0)
      this.fDocumentHandler.characters((XMLString)xMLStringBuffer, null); 
    if (i == 93 && this.fTempString.length == 0) {
      this.fStringBuffer.clear();
      this.fStringBuffer.append((char)this.fEntityScanner.scanChar());
      this.fInScanContent = true;
      if (this.fEntityScanner.skipChar(93)) {
        this.fStringBuffer.append(']');
        while (this.fEntityScanner.skipChar(93))
          this.fStringBuffer.append(']'); 
        if (this.fEntityScanner.skipChar(62))
          reportFatalError("CDEndInContent", null); 
      } 
      if (this.fDocumentHandler != null && ((XMLString)this.fStringBuffer).length != 0)
        this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null); 
      this.fInScanContent = false;
      i = -1;
    } 
    return i;
  }
  
  protected boolean scanCDATASection(boolean paramBoolean) throws IOException, XNIException {
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.startCDATA(null); 
    while (true) {
      this.fStringBuffer.clear();
      if (!this.fEntityScanner.scanData("]]", this.fStringBuffer)) {
        if (this.fDocumentHandler != null && ((XMLString)this.fStringBuffer).length > 0)
          this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null); 
        byte b;
        for (b = 0; this.fEntityScanner.skipChar(93); b++);
        if (this.fDocumentHandler != null && b > 0) {
          this.fStringBuffer.clear();
          if (b > 'ࠀ') {
            int j = b / 2048;
            int k = b % 2048;
            for (byte b1 = 0; b1 < 'ࠀ'; b1++)
              this.fStringBuffer.append(']'); 
            for (byte b2 = 0; b2 < j; b2++)
              this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null); 
            if (k != 0) {
              ((XMLString)this.fStringBuffer).length = k;
              this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null);
            } 
          } else {
            for (byte b1 = 0; b1 < b; b1++)
              this.fStringBuffer.append(']'); 
            this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null);
          } 
        } 
        if (this.fEntityScanner.skipChar(62)) {
          this.fMarkupDepth--;
          if (this.fDocumentHandler != null)
            this.fDocumentHandler.endCDATA(null); 
          return true;
        } 
        if (this.fDocumentHandler != null) {
          this.fStringBuffer.clear();
          this.fStringBuffer.append("]]");
          this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null);
        } 
        continue;
      } 
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null); 
      int i = this.fEntityScanner.peekChar();
      if (i != -1 && isInvalidLiteral(i)) {
        if (XMLChar.isHighSurrogate(i)) {
          this.fStringBuffer.clear();
          scanSurrogates(this.fStringBuffer);
          if (this.fDocumentHandler != null)
            this.fDocumentHandler.characters((XMLString)this.fStringBuffer, null); 
          continue;
        } 
        reportFatalError("InvalidCharInCDSect", new Object[] { Integer.toString(i, 16) });
        this.fEntityScanner.scanChar();
      } 
    } 
  }
  
  protected int scanEndElement() throws IOException, XNIException {
    this.fElementStack.popElement(this.fElementQName);
    if (!this.fEntityScanner.skipString(this.fElementQName.rawname))
      reportFatalError("ETagRequired", new Object[] { this.fElementQName.rawname }); 
    this.fEntityScanner.skipSpaces();
    if (!this.fEntityScanner.skipChar(62))
      reportFatalError("ETagUnterminated", new Object[] { this.fElementQName.rawname }); 
    this.fMarkupDepth--;
    this.fMarkupDepth--;
    if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1])
      reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname }); 
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.endElement(this.fElementQName, null); 
    return this.fMarkupDepth;
  }
  
  protected void scanCharReference() throws IOException, XNIException {
    this.fStringBuffer2.clear();
    int i = scanCharReferenceValue(this.fStringBuffer2, null);
    this.fMarkupDepth--;
    if (i != -1 && this.fDocumentHandler != null) {
      if (this.fNotifyCharRefs)
        this.fDocumentHandler.startGeneralEntity(this.fCharRefLiteral, null, null, null); 
      Augmentations augmentations = null;
      if (this.fValidation && i <= 32) {
        if (this.fTempAugmentations != null) {
          this.fTempAugmentations.removeAllItems();
        } else {
          this.fTempAugmentations = (Augmentations)new AugmentationsImpl();
        } 
        augmentations = this.fTempAugmentations;
        augmentations.putItem("CHAR_REF_PROBABLE_WS", Boolean.TRUE);
      } 
      this.fDocumentHandler.characters((XMLString)this.fStringBuffer2, augmentations);
      if (this.fNotifyCharRefs)
        this.fDocumentHandler.endGeneralEntity(this.fCharRefLiteral, null); 
    } 
  }
  
  protected void scanEntityReference() throws IOException, XNIException {
    String str = this.fEntityScanner.scanName();
    if (str == null) {
      reportFatalError("NameRequiredInReference", null);
      return;
    } 
    if (!this.fEntityScanner.skipChar(59))
      reportFatalError("SemicolonRequiredInReference", new Object[] { str }); 
    this.fMarkupDepth--;
    if (str == XMLScanner.fAmpSymbol) {
      handleCharacter('&', XMLScanner.fAmpSymbol);
    } else if (str == XMLScanner.fLtSymbol) {
      handleCharacter('<', XMLScanner.fLtSymbol);
    } else if (str == XMLScanner.fGtSymbol) {
      handleCharacter('>', XMLScanner.fGtSymbol);
    } else if (str == XMLScanner.fQuotSymbol) {
      handleCharacter('"', XMLScanner.fQuotSymbol);
    } else if (str == XMLScanner.fAposSymbol) {
      handleCharacter('\'', XMLScanner.fAposSymbol);
    } else if (this.fEntityManager.isUnparsedEntity(str)) {
      reportFatalError("ReferenceToUnparsedEntity", new Object[] { str });
    } else {
      if (!this.fEntityManager.isDeclaredEntity(str))
        if (this.fIsEntityDeclaredVC) {
          if (this.fValidation)
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[] { str }, (short)1); 
        } else {
          reportFatalError("EntityNotDeclared", new Object[] { str });
        }  
      this.fEntityManager.startEntity(str, false);
    } 
  }
  
  private void handleCharacter(char paramChar, String paramString) throws XNIException {
    if (this.fDocumentHandler != null) {
      if (this.fNotifyBuiltInRefs)
        this.fDocumentHandler.startGeneralEntity(paramString, null, null, null); 
      this.fSingleChar[0] = paramChar;
      this.fTempString.setValues(this.fSingleChar, 0, 1);
      this.fDocumentHandler.characters(this.fTempString, null);
      if (this.fNotifyBuiltInRefs)
        this.fDocumentHandler.endGeneralEntity(paramString, null); 
    } 
  }
  
  protected int handleEndElement(QName paramQName, boolean paramBoolean) throws XNIException {
    this.fMarkupDepth--;
    if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1])
      reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname }); 
    QName qName = this.fQName;
    this.fElementStack.popElement(qName);
    if (paramQName.rawname != qName.rawname)
      reportFatalError("ETagRequired", new Object[] { qName.rawname }); 
    if (this.fNamespaces)
      paramQName.uri = qName.uri; 
    if (this.fDocumentHandler != null && !paramBoolean)
      this.fDocumentHandler.endElement(paramQName, null); 
    return this.fMarkupDepth;
  }
  
  protected final void setScannerState(int paramInt) {
    this.fScannerState = paramInt;
  }
  
  protected final void setDispatcher(Dispatcher paramDispatcher) {
    this.fDispatcher = paramDispatcher;
  }
  
  protected String getScannerStateName(int paramInt) {
    switch (paramInt) {
      case 4:
        return "SCANNER_STATE_DOCTYPE";
      case 6:
        return "SCANNER_STATE_ROOT_ELEMENT";
      case 1:
        return "SCANNER_STATE_START_OF_MARKUP";
      case 2:
        return "SCANNER_STATE_COMMENT";
      case 3:
        return "SCANNER_STATE_PI";
      case 7:
        return "SCANNER_STATE_CONTENT";
      case 8:
        return "SCANNER_STATE_REFERENCE";
      case 13:
        return "SCANNER_STATE_END_OF_INPUT";
      case 14:
        return "SCANNER_STATE_TERMINATED";
      case 15:
        return "SCANNER_STATE_CDATA";
      case 16:
        return "SCANNER_STATE_TEXT_DECL";
    } 
    return "??? (" + paramInt + ')';
  }
  
  public String getDispatcherName(Dispatcher paramDispatcher) {
    return "null";
  }
  
  protected class FragmentContentDispatcher implements Dispatcher {
    private final XMLDocumentFragmentScannerImpl this$0;
    
    protected FragmentContentDispatcher(XMLDocumentFragmentScannerImpl this$0) {
      this.this$0 = this$0;
    }
    
    public boolean dispatch(boolean param1Boolean) throws IOException, XNIException {
      try {
        while (true) {
          boolean bool = false;
          switch (this.this$0.fScannerState) {
            case 7:
              if (this.this$0.fEntityScanner.skipChar(60)) {
                this.this$0.setScannerState(1);
                bool = true;
                break;
              } 
              if (this.this$0.fEntityScanner.skipChar(38)) {
                this.this$0.setScannerState(8);
                bool = true;
                break;
              } 
              do {
                int i = this.this$0.scanContent();
                if (i == 60) {
                  this.this$0.fEntityScanner.scanChar();
                  this.this$0.setScannerState(1);
                  break;
                } 
                if (i == 38) {
                  this.this$0.fEntityScanner.scanChar();
                  this.this$0.setScannerState(8);
                  break;
                } 
                if (i == -1 || !this.this$0.isInvalidLiteral(i))
                  continue; 
                if (XMLChar.isHighSurrogate(i)) {
                  this.this$0.fStringBuffer.clear();
                  if (this.this$0.scanSurrogates(this.this$0.fStringBuffer) && this.this$0.fDocumentHandler != null)
                    this.this$0.fDocumentHandler.characters((XMLString)this.this$0.fStringBuffer, null); 
                } else {
                  this.this$0.reportFatalError("InvalidCharInContent", new Object[] { Integer.toString(i, 16) });
                  this.this$0.fEntityScanner.scanChar();
                } 
              } while (param1Boolean);
              break;
            case 1:
              this.this$0.fMarkupDepth++;
              if (this.this$0.fEntityScanner.skipChar(47)) {
                if (this.this$0.scanEndElement() == 0 && elementDepthIsZeroHook())
                  return true; 
                this.this$0.setScannerState(7);
                break;
              } 
              if (this.this$0.isValidNameStartChar(this.this$0.fEntityScanner.peekChar())) {
                this.this$0.scanStartElement();
                this.this$0.setScannerState(7);
                break;
              } 
              if (this.this$0.fEntityScanner.skipChar(33)) {
                if (this.this$0.fEntityScanner.skipChar(45)) {
                  if (!this.this$0.fEntityScanner.skipChar(45))
                    this.this$0.reportFatalError("InvalidCommentStart", null); 
                  this.this$0.setScannerState(2);
                  bool = true;
                  break;
                } 
                if (this.this$0.fEntityScanner.skipString("[CDATA[")) {
                  this.this$0.setScannerState(15);
                  bool = true;
                  break;
                } 
                if (!scanForDoctypeHook())
                  this.this$0.reportFatalError("MarkupNotRecognizedInContent", null); 
                break;
              } 
              if (this.this$0.fEntityScanner.skipChar(63)) {
                this.this$0.setScannerState(3);
                bool = true;
                break;
              } 
              if (this.this$0.isValidNameStartHighSurrogate(this.this$0.fEntityScanner.peekChar())) {
                this.this$0.scanStartElement();
                this.this$0.setScannerState(7);
                break;
              } 
              this.this$0.reportFatalError("MarkupNotRecognizedInContent", null);
              this.this$0.setScannerState(7);
              break;
            case 2:
              this.this$0.scanComment();
              this.this$0.setScannerState(7);
              break;
            case 3:
              this.this$0.scanPI();
              this.this$0.setScannerState(7);
              break;
            case 15:
              this.this$0.scanCDATASection(param1Boolean);
              this.this$0.setScannerState(7);
              break;
            case 8:
              this.this$0.fMarkupDepth++;
              this.this$0.setScannerState(7);
              if (this.this$0.fEntityScanner.skipChar(35)) {
                this.this$0.scanCharReference();
                break;
              } 
              this.this$0.scanEntityReference();
              break;
            case 16:
              if (this.this$0.fEntityScanner.skipString("<?xml")) {
                this.this$0.fMarkupDepth++;
                if (this.this$0.isValidNameChar(this.this$0.fEntityScanner.peekChar())) {
                  this.this$0.fStringBuffer.clear();
                  this.this$0.fStringBuffer.append("xml");
                  if (this.this$0.fNamespaces) {
                    while (this.this$0.isValidNCName(this.this$0.fEntityScanner.peekChar()))
                      this.this$0.fStringBuffer.append((char)this.this$0.fEntityScanner.scanChar()); 
                  } else {
                    while (this.this$0.isValidNameChar(this.this$0.fEntityScanner.peekChar()))
                      this.this$0.fStringBuffer.append((char)this.this$0.fEntityScanner.scanChar()); 
                  } 
                  String str = this.this$0.fSymbolTable.addSymbol(((XMLString)this.this$0.fStringBuffer).ch, ((XMLString)this.this$0.fStringBuffer).offset, ((XMLString)this.this$0.fStringBuffer).length);
                  this.this$0.scanPIData(str, this.this$0.fTempString);
                } else {
                  this.this$0.scanXMLDeclOrTextDecl(true);
                } 
              } 
              this.this$0.fEntityManager.fCurrentEntity.mayReadChunks = true;
              this.this$0.setScannerState(7);
              break;
            case 6:
              if (scanRootElementHook())
                return true; 
              this.this$0.setScannerState(7);
              break;
            case 4:
              this.this$0.reportFatalError("DoctypeIllegalInContent", null);
              this.this$0.setScannerState(7);
              break;
          } 
          if (!param1Boolean && !bool)
            return true; 
        } 
      } catch (MalformedByteSequenceException malformedByteSequenceException) {
        this.this$0.fErrorReporter.reportError(malformedByteSequenceException.getDomain(), malformedByteSequenceException.getKey(), malformedByteSequenceException.getArguments(), (short)2, (Exception)malformedByteSequenceException);
        return false;
      } catch (CharConversionException charConversionException) {
        this.this$0.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[])null, (short)2, charConversionException);
        return false;
      } catch (EOFException eOFException) {
        endOfFileHook(eOFException);
        return false;
      } 
    }
    
    protected boolean scanForDoctypeHook() throws IOException, XNIException {
      return false;
    }
    
    protected boolean elementDepthIsZeroHook() throws IOException, XNIException {
      return false;
    }
    
    protected boolean scanRootElementHook() throws IOException, XNIException {
      return false;
    }
    
    protected void endOfFileHook(EOFException param1EOFException) throws IOException, XNIException {
      if (this.this$0.fMarkupDepth != 0)
        this.this$0.reportFatalError("PrematureEOF", null); 
    }
  }
  
  protected static interface Dispatcher {
    boolean dispatch(boolean param1Boolean) throws IOException, XNIException;
  }
  
  protected static class ElementStack {
    protected QName[] fElements = new QName[10];
    
    protected int fSize;
    
    public ElementStack() {
      for (byte b = 0; b < this.fElements.length; b++)
        this.fElements[b] = new QName(); 
    }
    
    public QName pushElement(QName param1QName) {
      if (this.fSize == this.fElements.length) {
        QName[] arrayOfQName = new QName[this.fElements.length * 2];
        System.arraycopy(this.fElements, 0, arrayOfQName, 0, this.fSize);
        this.fElements = arrayOfQName;
        for (int i = this.fSize; i < this.fElements.length; i++)
          this.fElements[i] = new QName(); 
      } 
      this.fElements[this.fSize].setValues(param1QName);
      return this.fElements[this.fSize++];
    }
    
    public void popElement(QName param1QName) {
      param1QName.setValues(this.fElements[--this.fSize]);
    }
    
    public void clear() {
      this.fSize = 0;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XMLDocumentFragmentScannerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */