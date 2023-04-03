package org.apache.xerces.impl;

import java.io.IOException;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDScanner;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDTDScannerImpl extends XMLScanner implements XMLDTDScanner, XMLComponent, XMLEntityHandler {
  protected static final int SCANNER_STATE_END_OF_INPUT = 0;
  
  protected static final int SCANNER_STATE_TEXT_DECL = 1;
  
  protected static final int SCANNER_STATE_MARKUP_DECL = 2;
  
  private static final String[] RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://apache.org/xml/features/scanner/notify-char-refs" };
  
  private static final Boolean[] FEATURE_DEFAULTS = new Boolean[] { null, Boolean.FALSE };
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager" };
  
  private static final Object[] PROPERTY_DEFAULTS = new Object[] { null, null, null };
  
  private static final boolean DEBUG_SCANNER_STATE = false;
  
  protected XMLDTDHandler fDTDHandler;
  
  protected XMLDTDContentModelHandler fDTDContentModelHandler;
  
  protected int fScannerState;
  
  protected boolean fStandalone;
  
  protected boolean fSeenExternalDTD;
  
  protected boolean fSeenPEReferences;
  
  private boolean fStartDTDCalled;
  
  private int[] fContentStack = new int[5];
  
  private int fContentDepth;
  
  private int[] fPEStack = new int[5];
  
  private boolean[] fPEReport = new boolean[5];
  
  private int fPEDepth;
  
  private int fMarkUpDepth;
  
  private int fExtEntityDepth;
  
  private int fIncludeSectDepth;
  
  private final String[] fStrings = new String[3];
  
  private final XMLString fString = new XMLString();
  
  private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
  
  private final XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
  
  private final XMLString fLiteral = new XMLString();
  
  private final XMLString fLiteral2 = new XMLString();
  
  private String[] fEnumeration = new String[5];
  
  private int fEnumerationCount;
  
  private final XMLStringBuffer fIgnoreConditionalBuffer = new XMLStringBuffer(128);
  
  public XMLDTDScannerImpl() {}
  
  public XMLDTDScannerImpl(SymbolTable paramSymbolTable, XMLErrorReporter paramXMLErrorReporter, XMLEntityManager paramXMLEntityManager) {
    this.fSymbolTable = paramSymbolTable;
    this.fErrorReporter = paramXMLErrorReporter;
    this.fEntityManager = paramXMLEntityManager;
    paramXMLEntityManager.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
  }
  
  public void setInputSource(XMLInputSource paramXMLInputSource) throws IOException {
    if (paramXMLInputSource == null) {
      if (this.fDTDHandler != null) {
        this.fDTDHandler.startDTD(null, null);
        this.fDTDHandler.endDTD(null);
      } 
      return;
    } 
    this.fEntityManager.setEntityHandler(this);
    this.fEntityManager.startDTDEntity(paramXMLInputSource);
  }
  
  public boolean scanDTDExternalSubset(boolean paramBoolean) throws IOException, XNIException {
    this.fEntityManager.setEntityHandler(this);
    if (this.fScannerState == 1) {
      this.fSeenExternalDTD = true;
      boolean bool = scanTextDecl();
      if (this.fScannerState == 0)
        return false; 
      setScannerState(2);
      if (bool && !paramBoolean)
        return true; 
    } 
    while (true) {
      if (!scanDecls(paramBoolean))
        return false; 
      if (!paramBoolean)
        return true; 
    } 
  }
  
  public boolean scanDTDInternalSubset(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) throws IOException, XNIException {
    this.fEntityScanner = this.fEntityManager.getEntityScanner();
    this.fEntityManager.setEntityHandler(this);
    this.fStandalone = paramBoolean2;
    if (this.fScannerState == 1) {
      if (this.fDTDHandler != null) {
        this.fDTDHandler.startDTD(this.fEntityScanner, null);
        this.fStartDTDCalled = true;
      } 
      setScannerState(2);
    } 
    while (true) {
      if (!scanDecls(paramBoolean1)) {
        if (this.fDTDHandler != null && !paramBoolean3)
          this.fDTDHandler.endDTD(null); 
        setScannerState(1);
        return false;
      } 
      if (!paramBoolean1)
        return true; 
    } 
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException {
    super.reset(paramXMLComponentManager);
    init();
  }
  
  public void reset() {
    super.reset();
    init();
  }
  
  public String[] getRecognizedFeatures() {
    return (String[])RECOGNIZED_FEATURES.clone();
  }
  
  public String[] getRecognizedProperties() {
    return (String[])RECOGNIZED_PROPERTIES.clone();
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
  
  public void setDTDHandler(XMLDTDHandler paramXMLDTDHandler) {
    this.fDTDHandler = paramXMLDTDHandler;
  }
  
  public XMLDTDHandler getDTDHandler() {
    return this.fDTDHandler;
  }
  
  public void setDTDContentModelHandler(XMLDTDContentModelHandler paramXMLDTDContentModelHandler) {
    this.fDTDContentModelHandler = paramXMLDTDContentModelHandler;
  }
  
  public XMLDTDContentModelHandler getDTDContentModelHandler() {
    return this.fDTDContentModelHandler;
  }
  
  public void startEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    super.startEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations);
    boolean bool = paramString1.equals("[dtd]");
    if (bool) {
      if (this.fDTDHandler != null && !this.fStartDTDCalled)
        this.fDTDHandler.startDTD(this.fEntityScanner, null); 
      if (this.fDTDHandler != null)
        this.fDTDHandler.startExternalSubset(paramXMLResourceIdentifier, null); 
      this.fEntityManager.startExternalSubset();
      this.fExtEntityDepth++;
    } else if (paramString1.charAt(0) == '%') {
      pushPEStack(this.fMarkUpDepth, this.fReportEntity);
      if (this.fEntityScanner.isExternal())
        this.fExtEntityDepth++; 
    } 
    if (this.fDTDHandler != null && !bool && this.fReportEntity)
      this.fDTDHandler.startParameterEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations); 
  }
  
  public void endEntity(String paramString, Augmentations paramAugmentations) throws XNIException {
    super.endEntity(paramString, paramAugmentations);
    if (this.fScannerState == 0)
      return; 
    boolean bool = this.fReportEntity;
    if (paramString.startsWith("%")) {
      bool = peekReportEntity();
      int i = popPEStack();
      if (i == 0 && i < this.fMarkUpDepth)
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ILL_FORMED_PARAMETER_ENTITY_WHEN_USED_IN_DECL", new Object[] { this.fEntityManager.fCurrentEntity.name }, (short)2); 
      if (i != this.fMarkUpDepth) {
        bool = false;
        if (this.fValidation)
          this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ImproperDeclarationNesting", new Object[] { paramString }, (short)1); 
      } 
      if (this.fEntityScanner.isExternal())
        this.fExtEntityDepth--; 
      if (this.fDTDHandler != null && bool)
        this.fDTDHandler.endParameterEntity(paramString, paramAugmentations); 
    } else if (paramString.equals("[dtd]")) {
      if (this.fIncludeSectDepth != 0)
        reportFatalError("IncludeSectUnterminated", null); 
      this.fScannerState = 0;
      this.fEntityManager.endExternalSubset();
      if (this.fDTDHandler != null) {
        this.fDTDHandler.endExternalSubset(null);
        this.fDTDHandler.endDTD(null);
      } 
      this.fExtEntityDepth--;
    } 
  }
  
  protected final void setScannerState(int paramInt) {
    this.fScannerState = paramInt;
  }
  
  private static String getScannerStateName(int paramInt) {
    return "??? (" + paramInt + ')';
  }
  
  protected final boolean scanningInternalSubset() {
    return (this.fExtEntityDepth == 0);
  }
  
  protected void startPE(String paramString, boolean paramBoolean) throws IOException, XNIException {
    int i = this.fPEDepth;
    String str = "%" + paramString;
    if (!this.fSeenPEReferences) {
      this.fSeenPEReferences = true;
      this.fEntityManager.notifyHasPEReferences();
    } 
    if (this.fValidation && !this.fEntityManager.isDeclaredEntity(str))
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[] { paramString }, (short)1); 
    this.fEntityManager.startEntity(this.fSymbolTable.addSymbol(str), paramBoolean);
    if (i != this.fPEDepth && this.fEntityScanner.isExternal())
      scanTextDecl(); 
  }
  
  protected final boolean scanTextDecl() throws IOException, XNIException {
    boolean bool = false;
    if (this.fEntityScanner.skipString("<?xml")) {
      this.fMarkUpDepth++;
      if (isValidNameChar(this.fEntityScanner.peekChar())) {
        this.fStringBuffer.clear();
        this.fStringBuffer.append("xml");
        if (this.fNamespaces) {
          while (isValidNCName(this.fEntityScanner.peekChar()))
            this.fStringBuffer.append((char)this.fEntityScanner.scanChar()); 
        } else {
          while (isValidNameChar(this.fEntityScanner.peekChar()))
            this.fStringBuffer.append((char)this.fEntityScanner.scanChar()); 
        } 
        String str = this.fSymbolTable.addSymbol(((XMLString)this.fStringBuffer).ch, ((XMLString)this.fStringBuffer).offset, ((XMLString)this.fStringBuffer).length);
        scanPIData(str, this.fString);
      } else {
        String str1 = null;
        String str2 = null;
        scanXMLDeclOrTextDecl(true, this.fStrings);
        bool = true;
        this.fMarkUpDepth--;
        str1 = this.fStrings[0];
        str2 = this.fStrings[1];
        this.fEntityScanner.setXMLVersion(str1);
        if (!this.fEntityScanner.fCurrentEntity.isEncodingExternallySpecified())
          this.fEntityScanner.setEncoding(str2); 
        if (this.fDTDHandler != null)
          this.fDTDHandler.textDecl(str1, str2, null); 
      } 
    } 
    this.fEntityManager.fCurrentEntity.mayReadChunks = true;
    return bool;
  }
  
  protected final void scanPIData(String paramString, XMLString paramXMLString) throws IOException, XNIException {
    super.scanPIData(paramString, paramXMLString);
    this.fMarkUpDepth--;
    if (this.fDTDHandler != null)
      this.fDTDHandler.processingInstruction(paramString, paramXMLString, null); 
  }
  
  protected final void scanComment() throws IOException, XNIException {
    this.fReportEntity = false;
    scanComment(this.fStringBuffer);
    this.fMarkUpDepth--;
    if (this.fDTDHandler != null)
      this.fDTDHandler.comment((XMLString)this.fStringBuffer, null); 
    this.fReportEntity = true;
  }
  
  protected final void scanElementDecl() throws IOException, XNIException {
    this.fReportEntity = false;
    if (!skipSeparator(true, !scanningInternalSubset()))
      reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL", null); 
    String str1 = this.fEntityScanner.scanName();
    if (str1 == null)
      reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL", null); 
    if (!skipSeparator(true, !scanningInternalSubset()))
      reportFatalError("MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL", new Object[] { str1 }); 
    if (this.fDTDContentModelHandler != null)
      this.fDTDContentModelHandler.startContentModel(str1, null); 
    String str2 = null;
    this.fReportEntity = true;
    if (this.fEntityScanner.skipString("EMPTY")) {
      str2 = "EMPTY";
      if (this.fDTDContentModelHandler != null)
        this.fDTDContentModelHandler.empty(null); 
    } else if (this.fEntityScanner.skipString("ANY")) {
      str2 = "ANY";
      if (this.fDTDContentModelHandler != null)
        this.fDTDContentModelHandler.any(null); 
    } else {
      if (!this.fEntityScanner.skipChar(40))
        reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[] { str1 }); 
      if (this.fDTDContentModelHandler != null)
        this.fDTDContentModelHandler.startGroup(null); 
      this.fStringBuffer.clear();
      this.fStringBuffer.append('(');
      this.fMarkUpDepth++;
      skipSeparator(false, !scanningInternalSubset());
      if (this.fEntityScanner.skipString("#PCDATA")) {
        scanMixed(str1);
      } else {
        scanChildren(str1);
      } 
      str2 = this.fStringBuffer.toString();
    } 
    if (this.fDTDContentModelHandler != null)
      this.fDTDContentModelHandler.endContentModel(null); 
    this.fReportEntity = false;
    skipSeparator(false, !scanningInternalSubset());
    if (!this.fEntityScanner.skipChar(62))
      reportFatalError("ElementDeclUnterminated", new Object[] { str1 }); 
    this.fReportEntity = true;
    this.fMarkUpDepth--;
    if (this.fDTDHandler != null)
      this.fDTDHandler.elementDecl(str1, str2, null); 
  }
  
  private final void scanMixed(String paramString) throws IOException, XNIException {
    String str = null;
    this.fStringBuffer.append("#PCDATA");
    if (this.fDTDContentModelHandler != null)
      this.fDTDContentModelHandler.pcdata(null); 
    skipSeparator(false, !scanningInternalSubset());
    while (this.fEntityScanner.skipChar(124)) {
      this.fStringBuffer.append('|');
      if (this.fDTDContentModelHandler != null)
        this.fDTDContentModelHandler.separator((short)0, null); 
      skipSeparator(false, !scanningInternalSubset());
      str = this.fEntityScanner.scanName();
      if (str == null)
        reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT", new Object[] { paramString }); 
      this.fStringBuffer.append(str);
      if (this.fDTDContentModelHandler != null)
        this.fDTDContentModelHandler.element(str, null); 
      skipSeparator(false, !scanningInternalSubset());
    } 
    if (this.fEntityScanner.skipString(")*")) {
      this.fStringBuffer.append(")*");
      if (this.fDTDContentModelHandler != null) {
        this.fDTDContentModelHandler.endGroup(null);
        this.fDTDContentModelHandler.occurrence((short)3, null);
      } 
    } else if (str != null) {
      reportFatalError("MixedContentUnterminated", new Object[] { paramString });
    } else if (this.fEntityScanner.skipChar(41)) {
      this.fStringBuffer.append(')');
      if (this.fDTDContentModelHandler != null)
        this.fDTDContentModelHandler.endGroup(null); 
    } else {
      reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[] { paramString });
    } 
    this.fMarkUpDepth--;
  }
  
  private final void scanChildren(String paramString) throws IOException, XNIException {
    this.fContentDepth = 0;
    pushContentStack(0);
    int i = 0;
    while (true) {
      while (this.fEntityScanner.skipChar(40)) {
        this.fMarkUpDepth++;
        this.fStringBuffer.append('(');
        if (this.fDTDContentModelHandler != null)
          this.fDTDContentModelHandler.startGroup(null); 
        pushContentStack(i);
        i = 0;
        skipSeparator(false, !scanningInternalSubset());
      } 
      skipSeparator(false, !scanningInternalSubset());
      String str = this.fEntityScanner.scanName();
      if (str == null) {
        reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[] { paramString });
        return;
      } 
      if (this.fDTDContentModelHandler != null)
        this.fDTDContentModelHandler.element(str, null); 
      this.fStringBuffer.append(str);
      int j = this.fEntityScanner.peekChar();
      if (j == 63 || j == 42 || j == 43) {
        if (this.fDTDContentModelHandler != null) {
          byte b;
          if (j == 63) {
            b = 2;
          } else if (j == 42) {
            b = 3;
          } else {
            b = 4;
          } 
          this.fDTDContentModelHandler.occurrence(b, null);
        } 
        this.fEntityScanner.scanChar();
        this.fStringBuffer.append((char)j);
      } 
      while (true) {
        skipSeparator(false, !scanningInternalSubset());
        j = this.fEntityScanner.peekChar();
        if (j == 44 && i != 124) {
          i = j;
          if (this.fDTDContentModelHandler != null)
            this.fDTDContentModelHandler.separator((short)1, null); 
          this.fEntityScanner.scanChar();
          this.fStringBuffer.append(',');
          break;
        } 
        if (j == 124 && i != 44) {
          i = j;
          if (this.fDTDContentModelHandler != null)
            this.fDTDContentModelHandler.separator((short)0, null); 
          this.fEntityScanner.scanChar();
          this.fStringBuffer.append('|');
          break;
        } 
        if (j != 41)
          reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[] { paramString }); 
        if (this.fDTDContentModelHandler != null)
          this.fDTDContentModelHandler.endGroup(null); 
        i = popContentStack();
        if (this.fEntityScanner.skipString(")?")) {
          this.fStringBuffer.append(")?");
          if (this.fDTDContentModelHandler != null) {
            byte b = 2;
            this.fDTDContentModelHandler.occurrence(b, null);
          } 
        } else if (this.fEntityScanner.skipString(")+")) {
          this.fStringBuffer.append(")+");
          if (this.fDTDContentModelHandler != null) {
            byte b = 4;
            this.fDTDContentModelHandler.occurrence(b, null);
          } 
        } else if (this.fEntityScanner.skipString(")*")) {
          this.fStringBuffer.append(")*");
          if (this.fDTDContentModelHandler != null) {
            byte b = 3;
            this.fDTDContentModelHandler.occurrence(b, null);
          } 
        } else {
          this.fEntityScanner.scanChar();
          this.fStringBuffer.append(')');
        } 
        this.fMarkUpDepth--;
        if (this.fContentDepth == 0)
          return; 
      } 
      skipSeparator(false, !scanningInternalSubset());
    } 
  }
  
  protected final void scanAttlistDecl() throws IOException, XNIException {
    this.fReportEntity = false;
    if (!skipSeparator(true, !scanningInternalSubset()))
      reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL", null); 
    String str = this.fEntityScanner.scanName();
    if (str == null)
      reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL", null); 
    if (this.fDTDHandler != null)
      this.fDTDHandler.startAttlist(str, null); 
    if (!skipSeparator(true, !scanningInternalSubset())) {
      if (this.fEntityScanner.skipChar(62)) {
        if (this.fDTDHandler != null)
          this.fDTDHandler.endAttlist(null); 
        this.fMarkUpDepth--;
        return;
      } 
      reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF", new Object[] { str });
    } 
    while (!this.fEntityScanner.skipChar(62)) {
      String str1 = this.fEntityScanner.scanName();
      if (str1 == null)
        reportFatalError("AttNameRequiredInAttDef", new Object[] { str }); 
      if (!skipSeparator(true, !scanningInternalSubset()))
        reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF", new Object[] { str, str1 }); 
      String str2 = scanAttType(str, str1);
      if (!skipSeparator(true, !scanningInternalSubset()))
        reportFatalError("MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF", new Object[] { str, str1 }); 
      String str3 = scanAttDefaultDecl(str, str1, str2, this.fLiteral, this.fLiteral2);
      if (this.fDTDHandler != null) {
        String[] arrayOfString = null;
        if (this.fEnumerationCount != 0) {
          arrayOfString = new String[this.fEnumerationCount];
          System.arraycopy(this.fEnumeration, 0, arrayOfString, 0, this.fEnumerationCount);
        } 
        if (str3 != null && (str3.equals("#REQUIRED") || str3.equals("#IMPLIED"))) {
          this.fDTDHandler.attributeDecl(str, str1, str2, arrayOfString, str3, null, null, null);
        } else {
          this.fDTDHandler.attributeDecl(str, str1, str2, arrayOfString, str3, this.fLiteral, this.fLiteral2, null);
        } 
      } 
      skipSeparator(false, !scanningInternalSubset());
    } 
    if (this.fDTDHandler != null)
      this.fDTDHandler.endAttlist(null); 
    this.fMarkUpDepth--;
    this.fReportEntity = true;
  }
  
  private final String scanAttType(String paramString1, String paramString2) throws IOException, XNIException {
    String str = null;
    this.fEnumerationCount = 0;
    if (this.fEntityScanner.skipString("CDATA")) {
      str = "CDATA";
    } else if (this.fEntityScanner.skipString("IDREFS")) {
      str = "IDREFS";
    } else if (this.fEntityScanner.skipString("IDREF")) {
      str = "IDREF";
    } else if (this.fEntityScanner.skipString("ID")) {
      str = "ID";
    } else if (this.fEntityScanner.skipString("ENTITY")) {
      str = "ENTITY";
    } else if (this.fEntityScanner.skipString("ENTITIES")) {
      str = "ENTITIES";
    } else if (this.fEntityScanner.skipString("NMTOKENS")) {
      str = "NMTOKENS";
    } else if (this.fEntityScanner.skipString("NMTOKEN")) {
      str = "NMTOKEN";
    } else if (this.fEntityScanner.skipString("NOTATION")) {
      str = "NOTATION";
      if (!skipSeparator(true, !scanningInternalSubset()))
        reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE", new Object[] { paramString1, paramString2 }); 
      int i = this.fEntityScanner.scanChar();
      if (i != 40)
        reportFatalError("MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE", new Object[] { paramString1, paramString2 }); 
      this.fMarkUpDepth++;
      while (true) {
        skipSeparator(false, !scanningInternalSubset());
        String str1 = this.fEntityScanner.scanName();
        if (str1 == null) {
          reportFatalError("MSG_NAME_REQUIRED_IN_NOTATIONTYPE", new Object[] { paramString1, paramString2 });
          i = skipInvalidEnumerationValue();
          if (i == 124)
            continue; 
          break;
        } 
        ensureEnumerationSize(this.fEnumerationCount + 1);
        this.fEnumeration[this.fEnumerationCount++] = str1;
        skipSeparator(false, !scanningInternalSubset());
        i = this.fEntityScanner.scanChar();
        continue;
        if (i != 124)
          break; 
      } 
      if (i != 41)
        reportFatalError("NotationTypeUnterminated", new Object[] { paramString1, paramString2 }); 
      this.fMarkUpDepth--;
    } else {
      str = "ENUMERATION";
      int i = this.fEntityScanner.scanChar();
      if (i != 40)
        reportFatalError("AttTypeRequiredInAttDef", new Object[] { paramString1, paramString2 }); 
      this.fMarkUpDepth++;
      while (true) {
        skipSeparator(false, !scanningInternalSubset());
        String str1 = this.fEntityScanner.scanNmtoken();
        if (str1 == null) {
          reportFatalError("MSG_NMTOKEN_REQUIRED_IN_ENUMERATION", new Object[] { paramString1, paramString2 });
          i = skipInvalidEnumerationValue();
          if (i == 124)
            continue; 
          break;
        } 
        ensureEnumerationSize(this.fEnumerationCount + 1);
        this.fEnumeration[this.fEnumerationCount++] = str1;
        skipSeparator(false, !scanningInternalSubset());
        i = this.fEntityScanner.scanChar();
        continue;
        if (i != 124)
          break; 
      } 
      if (i != 41)
        reportFatalError("EnumerationUnterminated", new Object[] { paramString1, paramString2 }); 
      this.fMarkUpDepth--;
    } 
    return str;
  }
  
  protected final String scanAttDefaultDecl(String paramString1, String paramString2, String paramString3, XMLString paramXMLString1, XMLString paramXMLString2) throws IOException, XNIException {
    String str = null;
    this.fString.clear();
    paramXMLString1.clear();
    if (this.fEntityScanner.skipString("#REQUIRED")) {
      str = "#REQUIRED";
    } else if (this.fEntityScanner.skipString("#IMPLIED")) {
      str = "#IMPLIED";
    } else {
      if (this.fEntityScanner.skipString("#FIXED")) {
        str = "#FIXED";
        if (!skipSeparator(true, !scanningInternalSubset()))
          reportFatalError("MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL", new Object[] { paramString1, paramString2 }); 
      } 
      boolean bool = (!this.fStandalone && (this.fSeenExternalDTD || this.fSeenPEReferences)) ? true : false;
      scanAttributeValue(paramXMLString1, paramXMLString2, paramString2, bool, paramString1);
    } 
    return str;
  }
  
  private final void scanEntityDecl() throws IOException, XNIException {
    boolean bool1 = false;
    boolean bool = false;
    this.fReportEntity = false;
    if (this.fEntityScanner.skipSpaces()) {
      if (!this.fEntityScanner.skipChar(37)) {
        bool1 = false;
      } else if (skipSeparator(true, !scanningInternalSubset())) {
        bool1 = true;
      } else if (scanningInternalSubset()) {
        reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
        bool1 = true;
      } else if (this.fEntityScanner.peekChar() == 37) {
        skipSeparator(false, !scanningInternalSubset());
        bool1 = true;
      } else {
        bool = true;
      } 
    } else if (scanningInternalSubset() || !this.fEntityScanner.skipChar(37)) {
      reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
      bool1 = false;
    } else if (this.fEntityScanner.skipSpaces()) {
      reportFatalError("MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL", null);
      bool1 = false;
    } else {
      bool = true;
    } 
    if (bool)
      while (true) {
        String str = this.fEntityScanner.scanName();
        if (str == null) {
          reportFatalError("NameRequiredInPEReference", null);
        } else if (!this.fEntityScanner.skipChar(59)) {
          reportFatalError("SemicolonRequiredInPEReference", new Object[] { str });
        } else {
          startPE(str, false);
        } 
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(37))
          break; 
        if (!bool1) {
          if (skipSeparator(true, !scanningInternalSubset())) {
            bool1 = true;
            break;
          } 
          bool1 = this.fEntityScanner.skipChar(37);
        } 
      }  
    String str1 = null;
    if (this.fNamespaces) {
      str1 = this.fEntityScanner.scanNCName();
    } else {
      str1 = this.fEntityScanner.scanName();
    } 
    if (str1 == null)
      reportFatalError("MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL", null); 
    if (!skipSeparator(true, !scanningInternalSubset()))
      if (this.fNamespaces && this.fEntityScanner.peekChar() == 58) {
        this.fEntityScanner.scanChar();
        XMLStringBuffer xMLStringBuffer = new XMLStringBuffer(str1);
        xMLStringBuffer.append(":");
        String str = this.fEntityScanner.scanName();
        if (str != null)
          xMLStringBuffer.append(str); 
        reportFatalError("ColonNotLegalWithNS", new Object[] { xMLStringBuffer.toString() });
        if (!skipSeparator(true, !scanningInternalSubset()))
          reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", new Object[] { str1 }); 
      } else {
        reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", new Object[] { str1 });
      }  
    scanExternalID(this.fStrings, false);
    String str2 = this.fStrings[0];
    String str3 = this.fStrings[1];
    String str4 = null;
    boolean bool2 = skipSeparator(true, !scanningInternalSubset());
    if (!bool1 && this.fEntityScanner.skipString("NDATA")) {
      if (!bool2)
        reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL", new Object[] { str1 }); 
      if (!skipSeparator(true, !scanningInternalSubset()))
        reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL", new Object[] { str1 }); 
      str4 = this.fEntityScanner.scanName();
      if (str4 == null)
        reportFatalError("MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL", new Object[] { str1 }); 
    } 
    if (str2 == null) {
      scanEntityValue(this.fLiteral, this.fLiteral2);
      this.fStringBuffer.clear();
      this.fStringBuffer2.clear();
      this.fStringBuffer.append(this.fLiteral.ch, this.fLiteral.offset, this.fLiteral.length);
      this.fStringBuffer2.append(this.fLiteral2.ch, this.fLiteral2.offset, this.fLiteral2.length);
    } 
    skipSeparator(false, !scanningInternalSubset());
    if (!this.fEntityScanner.skipChar(62))
      reportFatalError("EntityDeclUnterminated", new Object[] { str1 }); 
    this.fMarkUpDepth--;
    if (bool1)
      str1 = "%" + str1; 
    if (str2 != null) {
      String str = this.fEntityScanner.getBaseSystemId();
      if (str4 != null) {
        this.fEntityManager.addUnparsedEntity(str1, str3, str2, str, str4);
      } else {
        this.fEntityManager.addExternalEntity(str1, str3, str2, str);
      } 
      if (this.fDTDHandler != null) {
        this.fResourceIdentifier.setValues(str3, str2, str, XMLEntityManager.expandSystemId(str2, str, false));
        if (str4 != null) {
          this.fDTDHandler.unparsedEntityDecl(str1, (XMLResourceIdentifier)this.fResourceIdentifier, str4, null);
        } else {
          this.fDTDHandler.externalEntityDecl(str1, (XMLResourceIdentifier)this.fResourceIdentifier, null);
        } 
      } 
    } else {
      this.fEntityManager.addInternalEntity(str1, this.fStringBuffer.toString());
      if (this.fDTDHandler != null)
        this.fDTDHandler.internalEntityDecl(str1, (XMLString)this.fStringBuffer, (XMLString)this.fStringBuffer2, null); 
    } 
    this.fReportEntity = true;
  }
  
  protected final void scanEntityValue(XMLString paramXMLString1, XMLString paramXMLString2) throws IOException, XNIException {
    XMLStringBuffer xMLStringBuffer1;
    XMLStringBuffer xMLStringBuffer2;
    int i = this.fEntityScanner.scanChar();
    if (i != 39 && i != 34)
      reportFatalError("OpenQuoteMissingInDecl", null); 
    int j = this.fEntityDepth;
    XMLString xMLString1 = this.fString;
    XMLString xMLString2 = this.fString;
    if (this.fEntityScanner.scanLiteral(i, this.fString) != i) {
      this.fStringBuffer.clear();
      this.fStringBuffer2.clear();
      while (true) {
        this.fStringBuffer.append(this.fString);
        this.fStringBuffer2.append(this.fString);
        if (this.fEntityScanner.skipChar(38)) {
          if (this.fEntityScanner.skipChar(35)) {
            this.fStringBuffer2.append("&#");
            scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
          } else {
            this.fStringBuffer.append('&');
            this.fStringBuffer2.append('&');
            String str = this.fEntityScanner.scanName();
            if (str == null) {
              reportFatalError("NameRequiredInReference", null);
            } else {
              this.fStringBuffer.append(str);
              this.fStringBuffer2.append(str);
            } 
            if (!this.fEntityScanner.skipChar(59)) {
              reportFatalError("SemicolonRequiredInReference", new Object[] { str });
            } else {
              this.fStringBuffer.append(';');
              this.fStringBuffer2.append(';');
            } 
          } 
        } else if (this.fEntityScanner.skipChar(37)) {
          do {
            this.fStringBuffer2.append('%');
            String str = this.fEntityScanner.scanName();
            if (str == null) {
              reportFatalError("NameRequiredInPEReference", null);
            } else if (!this.fEntityScanner.skipChar(59)) {
              reportFatalError("SemicolonRequiredInPEReference", new Object[] { str });
            } else {
              if (scanningInternalSubset())
                reportFatalError("PEReferenceWithinMarkup", new Object[] { str }); 
              this.fStringBuffer2.append(str);
              this.fStringBuffer2.append(';');
            } 
            startPE(str, true);
            this.fEntityScanner.skipSpaces();
          } while (this.fEntityScanner.skipChar(37));
        } else {
          int k = this.fEntityScanner.peekChar();
          if (XMLChar.isHighSurrogate(k)) {
            scanSurrogates(this.fStringBuffer2);
          } else if (isInvalidLiteral(k)) {
            reportFatalError("InvalidCharInLiteral", new Object[] { Integer.toHexString(k) });
            this.fEntityScanner.scanChar();
          } else if (k != i || j != this.fEntityDepth) {
            this.fStringBuffer.append((char)k);
            this.fStringBuffer2.append((char)k);
            this.fEntityScanner.scanChar();
          } 
        } 
        if (this.fEntityScanner.scanLiteral(i, this.fString) == i) {
          this.fStringBuffer.append(this.fString);
          this.fStringBuffer2.append(this.fString);
          xMLStringBuffer1 = this.fStringBuffer;
          xMLStringBuffer2 = this.fStringBuffer2;
          break;
        } 
      } 
    } 
    paramXMLString1.setValues((XMLString)xMLStringBuffer1);
    paramXMLString2.setValues((XMLString)xMLStringBuffer2);
    if (!this.fEntityScanner.skipChar(i))
      reportFatalError("CloseQuoteMissingInDecl", null); 
  }
  
  private final void scanNotationDecl() throws IOException, XNIException {
    this.fReportEntity = false;
    if (!skipSeparator(true, !scanningInternalSubset()))
      reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL", null); 
    String str1 = null;
    if (this.fNamespaces) {
      str1 = this.fEntityScanner.scanNCName();
    } else {
      str1 = this.fEntityScanner.scanName();
    } 
    if (str1 == null)
      reportFatalError("MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL", null); 
    if (!skipSeparator(true, !scanningInternalSubset()))
      if (this.fNamespaces && this.fEntityScanner.peekChar() == 58) {
        this.fEntityScanner.scanChar();
        XMLStringBuffer xMLStringBuffer = new XMLStringBuffer(str1);
        xMLStringBuffer.append(":");
        xMLStringBuffer.append(this.fEntityScanner.scanName());
        reportFatalError("ColonNotLegalWithNS", new Object[] { xMLStringBuffer.toString() });
        skipSeparator(true, !scanningInternalSubset());
      } else {
        reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL", new Object[] { str1 });
      }  
    scanExternalID(this.fStrings, true);
    String str2 = this.fStrings[0];
    String str3 = this.fStrings[1];
    String str4 = this.fEntityScanner.getBaseSystemId();
    if (str2 == null && str3 == null)
      reportFatalError("ExternalIDorPublicIDRequired", new Object[] { str1 }); 
    skipSeparator(false, !scanningInternalSubset());
    if (!this.fEntityScanner.skipChar(62))
      reportFatalError("NotationDeclUnterminated", new Object[] { str1 }); 
    this.fMarkUpDepth--;
    if (this.fDTDHandler != null) {
      this.fResourceIdentifier.setValues(str3, str2, str4, XMLEntityManager.expandSystemId(str2, str4, false));
      this.fDTDHandler.notationDecl(str1, (XMLResourceIdentifier)this.fResourceIdentifier, null);
    } 
    this.fReportEntity = true;
  }
  
  private final void scanConditionalSect(int paramInt) throws IOException, XNIException {
    this.fReportEntity = false;
    skipSeparator(false, !scanningInternalSubset());
    if (this.fEntityScanner.skipString("INCLUDE")) {
      skipSeparator(false, !scanningInternalSubset());
      if (paramInt != this.fPEDepth && this.fValidation)
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[] { this.fEntityManager.fCurrentEntity.name }, (short)1); 
      if (!this.fEntityScanner.skipChar(91))
        reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null); 
      if (this.fDTDHandler != null)
        this.fDTDHandler.startConditional((short)0, null); 
      this.fIncludeSectDepth++;
      this.fReportEntity = true;
    } else {
      if (this.fEntityScanner.skipString("IGNORE")) {
        skipSeparator(false, !scanningInternalSubset());
        if (paramInt != this.fPEDepth && this.fValidation)
          this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[] { this.fEntityManager.fCurrentEntity.name }, (short)1); 
        if (this.fDTDHandler != null)
          this.fDTDHandler.startConditional((short)1, null); 
        if (!this.fEntityScanner.skipChar(91))
          reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null); 
        this.fReportEntity = true;
        int i = ++this.fIncludeSectDepth;
        if (this.fDTDHandler != null)
          this.fIgnoreConditionalBuffer.clear(); 
        while (true) {
          while (this.fEntityScanner.skipChar(60)) {
            if (this.fDTDHandler != null)
              this.fIgnoreConditionalBuffer.append('<'); 
            if (this.fEntityScanner.skipChar(33)) {
              if (this.fEntityScanner.skipChar(91)) {
                if (this.fDTDHandler != null)
                  this.fIgnoreConditionalBuffer.append("!["); 
                this.fIncludeSectDepth++;
                continue;
              } 
              if (this.fDTDHandler != null)
                this.fIgnoreConditionalBuffer.append("!"); 
            } 
          } 
          if (this.fEntityScanner.skipChar(93)) {
            if (this.fDTDHandler != null)
              this.fIgnoreConditionalBuffer.append(']'); 
            if (this.fEntityScanner.skipChar(93)) {
              if (this.fDTDHandler != null)
                this.fIgnoreConditionalBuffer.append(']'); 
              while (this.fEntityScanner.skipChar(93)) {
                if (this.fDTDHandler != null)
                  this.fIgnoreConditionalBuffer.append(']'); 
              } 
              if (this.fEntityScanner.skipChar(62)) {
                if (this.fIncludeSectDepth-- == i) {
                  this.fMarkUpDepth--;
                  if (this.fDTDHandler != null) {
                    this.fLiteral.setValues(((XMLString)this.fIgnoreConditionalBuffer).ch, 0, ((XMLString)this.fIgnoreConditionalBuffer).length - 2);
                    this.fDTDHandler.ignoredCharacters(this.fLiteral, null);
                    this.fDTDHandler.endConditional(null);
                  } 
                  return;
                } 
                if (this.fDTDHandler != null)
                  this.fIgnoreConditionalBuffer.append('>'); 
              } 
            } 
            continue;
          } 
          int j = this.fEntityScanner.scanChar();
          if (this.fScannerState == 0) {
            reportFatalError("IgnoreSectUnterminated", null);
            return;
          } 
          if (this.fDTDHandler != null)
            this.fIgnoreConditionalBuffer.append((char)j); 
        } 
      } 
      reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
    } 
  }
  
  protected final boolean scanDecls(boolean paramBoolean) throws IOException, XNIException {
    skipSeparator(false, true);
    boolean bool = true;
    while (bool && this.fScannerState == 2) {
      bool = paramBoolean;
      if (this.fEntityScanner.skipChar(60)) {
        this.fMarkUpDepth++;
        if (this.fEntityScanner.skipChar(63)) {
          scanPI();
        } else if (this.fEntityScanner.skipChar(33)) {
          if (this.fEntityScanner.skipChar(45)) {
            if (!this.fEntityScanner.skipChar(45)) {
              reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            } else {
              scanComment();
            } 
          } else if (this.fEntityScanner.skipString("ELEMENT")) {
            scanElementDecl();
          } else if (this.fEntityScanner.skipString("ATTLIST")) {
            scanAttlistDecl();
          } else if (this.fEntityScanner.skipString("ENTITY")) {
            scanEntityDecl();
          } else if (this.fEntityScanner.skipString("NOTATION")) {
            scanNotationDecl();
          } else if (this.fEntityScanner.skipChar(91) && !scanningInternalSubset()) {
            scanConditionalSect(this.fPEDepth);
          } else {
            this.fMarkUpDepth--;
            reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
          } 
        } else {
          this.fMarkUpDepth--;
          reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
        } 
      } else if (this.fIncludeSectDepth > 0 && this.fEntityScanner.skipChar(93)) {
        if (!this.fEntityScanner.skipChar(93) || !this.fEntityScanner.skipChar(62))
          reportFatalError("IncludeSectUnterminated", null); 
        if (this.fDTDHandler != null)
          this.fDTDHandler.endConditional(null); 
        this.fIncludeSectDepth--;
        this.fMarkUpDepth--;
      } else {
        if (scanningInternalSubset() && this.fEntityScanner.peekChar() == 93)
          return false; 
        if (!this.fEntityScanner.skipSpaces()) {
          int i;
          reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
          do {
            this.fEntityScanner.scanChar();
            skipSeparator(false, true);
            i = this.fEntityScanner.peekChar();
          } while (i != 60 && i != 93 && !XMLChar.isSpace(i));
        } 
      } 
      skipSeparator(false, true);
    } 
    return (this.fScannerState != 0);
  }
  
  private boolean skipSeparator(boolean paramBoolean1, boolean paramBoolean2) throws IOException, XNIException {
    int i = this.fPEDepth;
    boolean bool = this.fEntityScanner.skipSpaces();
    if (!paramBoolean2 || !this.fEntityScanner.skipChar(37))
      return (!paramBoolean1 || bool || i != this.fPEDepth); 
    while (true) {
      String str = this.fEntityScanner.scanName();
      if (str == null) {
        reportFatalError("NameRequiredInPEReference", null);
      } else if (!this.fEntityScanner.skipChar(59)) {
        reportFatalError("SemicolonRequiredInPEReference", new Object[] { str });
      } 
      startPE(str, false);
      this.fEntityScanner.skipSpaces();
      if (!this.fEntityScanner.skipChar(37))
        return true; 
    } 
  }
  
  private final void pushContentStack(int paramInt) {
    if (this.fContentStack.length == this.fContentDepth) {
      int[] arrayOfInt = new int[this.fContentDepth * 2];
      System.arraycopy(this.fContentStack, 0, arrayOfInt, 0, this.fContentDepth);
      this.fContentStack = arrayOfInt;
    } 
    this.fContentStack[this.fContentDepth++] = paramInt;
  }
  
  private final int popContentStack() {
    return this.fContentStack[--this.fContentDepth];
  }
  
  private final void pushPEStack(int paramInt, boolean paramBoolean) {
    if (this.fPEStack.length == this.fPEDepth) {
      int[] arrayOfInt = new int[this.fPEDepth * 2];
      System.arraycopy(this.fPEStack, 0, arrayOfInt, 0, this.fPEDepth);
      this.fPEStack = arrayOfInt;
      boolean[] arrayOfBoolean = new boolean[this.fPEDepth * 2];
      System.arraycopy(this.fPEReport, 0, arrayOfBoolean, 0, this.fPEDepth);
      this.fPEReport = arrayOfBoolean;
    } 
    this.fPEReport[this.fPEDepth] = paramBoolean;
    this.fPEStack[this.fPEDepth++] = paramInt;
  }
  
  private final int popPEStack() {
    return this.fPEStack[--this.fPEDepth];
  }
  
  private final boolean peekReportEntity() {
    return this.fPEReport[this.fPEDepth - 1];
  }
  
  private final void ensureEnumerationSize(int paramInt) {
    if (this.fEnumeration.length == paramInt) {
      String[] arrayOfString = new String[paramInt * 2];
      System.arraycopy(this.fEnumeration, 0, arrayOfString, 0, paramInt);
      this.fEnumeration = arrayOfString;
    } 
  }
  
  private void init() {
    this.fStartDTDCalled = false;
    this.fExtEntityDepth = 0;
    this.fIncludeSectDepth = 0;
    this.fMarkUpDepth = 0;
    this.fPEDepth = 0;
    this.fStandalone = false;
    this.fSeenExternalDTD = false;
    this.fSeenPEReferences = false;
    setScannerState(1);
  }
  
  private int skipInvalidEnumerationValue() throws IOException {
    int i;
    do {
      i = this.fEntityScanner.scanChar();
    } while (i != 124 && i != 41);
    ensureEnumerationSize(this.fEnumerationCount + 1);
    this.fEnumeration[this.fEnumerationCount++] = XMLSymbols.EMPTY_STRING;
    return i;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XMLDTDScannerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */