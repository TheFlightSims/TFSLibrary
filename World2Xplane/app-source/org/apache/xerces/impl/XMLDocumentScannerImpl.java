package org.apache.xerces.impl;

import java.io.CharConversionException;
import java.io.EOFException;
import java.io.IOException;
import org.apache.xerces.impl.dtd.XMLDTDDescription;
import org.apache.xerces.impl.io.MalformedByteSequenceException;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.XMLDTDDescription;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDScanner;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDocumentScannerImpl extends XMLDocumentFragmentScannerImpl {
  protected static final int SCANNER_STATE_XML_DECL = 0;
  
  protected static final int SCANNER_STATE_PROLOG = 5;
  
  protected static final int SCANNER_STATE_TRAILING_MISC = 12;
  
  protected static final int SCANNER_STATE_DTD_INTERNAL_DECLS = 17;
  
  protected static final int SCANNER_STATE_DTD_EXTERNAL = 18;
  
  protected static final int SCANNER_STATE_DTD_EXTERNAL_DECLS = 19;
  
  protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
  
  protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
  
  protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
  
  protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  protected static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
  
  private static final String[] RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/nonvalidating/load-external-dtd", "http://apache.org/xml/features/disallow-doctype-decl" };
  
  private static final Boolean[] FEATURE_DEFAULTS = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/dtd-scanner", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/properties/internal/namespace-context" };
  
  private static final Object[] PROPERTY_DEFAULTS = new Object[] { null, null, null };
  
  protected XMLDTDScanner fDTDScanner;
  
  protected ValidationManager fValidationManager;
  
  protected boolean fScanningDTD;
  
  protected String fDoctypeName;
  
  protected String fDoctypePublicId;
  
  protected String fDoctypeSystemId;
  
  protected NamespaceContext fNamespaceContext = (NamespaceContext)new NamespaceSupport();
  
  protected boolean fLoadExternalDTD = true;
  
  protected boolean fDisallowDoctype = false;
  
  protected boolean fSeenDoctypeDecl;
  
  protected final XMLDocumentFragmentScannerImpl.Dispatcher fXMLDeclDispatcher = new XMLDeclDispatcher(this);
  
  protected final XMLDocumentFragmentScannerImpl.Dispatcher fPrologDispatcher = new PrologDispatcher(this);
  
  protected final XMLDocumentFragmentScannerImpl.Dispatcher fDTDDispatcher = new DTDDispatcher(this);
  
  protected final XMLDocumentFragmentScannerImpl.Dispatcher fTrailingMiscDispatcher = new TrailingMiscDispatcher(this);
  
  private final String[] fStrings = new String[3];
  
  private final XMLString fString = new XMLString();
  
  private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
  
  private XMLInputSource fExternalSubsetSource = null;
  
  private final XMLDTDDescription fDTDDescription = new XMLDTDDescription(null, null, null, null, null);
  
  public void setInputSource(XMLInputSource paramXMLInputSource) throws IOException {
    this.fEntityManager.setEntityHandler(this);
    this.fEntityManager.startDocumentEntity(paramXMLInputSource);
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException {
    super.reset(paramXMLComponentManager);
    this.fDoctypeName = null;
    this.fDoctypePublicId = null;
    this.fDoctypeSystemId = null;
    this.fSeenDoctypeDecl = false;
    this.fScanningDTD = false;
    this.fExternalSubsetSource = null;
    if (!this.fParserSettings) {
      this.fNamespaceContext.reset();
      setScannerState(0);
      setDispatcher(this.fXMLDeclDispatcher);
      return;
    } 
    try {
      this.fLoadExternalDTD = paramXMLComponentManager.getFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fLoadExternalDTD = true;
    } 
    try {
      this.fDisallowDoctype = paramXMLComponentManager.getFeature("http://apache.org/xml/features/disallow-doctype-decl");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fDisallowDoctype = false;
    } 
    this.fDTDScanner = (XMLDTDScanner)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/dtd-scanner");
    try {
      this.fValidationManager = (ValidationManager)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fValidationManager = null;
    } 
    try {
      this.fNamespaceContext = (NamespaceContext)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/namespace-context");
    } catch (XMLConfigurationException xMLConfigurationException) {}
    if (this.fNamespaceContext == null)
      this.fNamespaceContext = (NamespaceContext)new NamespaceSupport(); 
    this.fNamespaceContext.reset();
    setScannerState(0);
    setDispatcher(this.fXMLDeclDispatcher);
  }
  
  public String[] getRecognizedFeatures() {
    String[] arrayOfString1 = super.getRecognizedFeatures();
    byte b = (arrayOfString1 != null) ? arrayOfString1.length : 0;
    String[] arrayOfString2 = new String[b + RECOGNIZED_FEATURES.length];
    if (arrayOfString1 != null)
      System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, arrayOfString1.length); 
    System.arraycopy(RECOGNIZED_FEATURES, 0, arrayOfString2, b, RECOGNIZED_FEATURES.length);
    return arrayOfString2;
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    super.setFeature(paramString, paramBoolean);
    if (paramString.startsWith("http://apache.org/xml/features/")) {
      int i = paramString.length() - "http://apache.org/xml/features/".length();
      if (i == "nonvalidating/load-external-dtd".length() && paramString.endsWith("nonvalidating/load-external-dtd")) {
        this.fLoadExternalDTD = paramBoolean;
        return;
      } 
      if (i == "disallow-doctype-decl".length() && paramString.endsWith("disallow-doctype-decl")) {
        this.fDisallowDoctype = paramBoolean;
        return;
      } 
    } 
  }
  
  public String[] getRecognizedProperties() {
    String[] arrayOfString1 = super.getRecognizedProperties();
    byte b = (arrayOfString1 != null) ? arrayOfString1.length : 0;
    String[] arrayOfString2 = new String[b + RECOGNIZED_PROPERTIES.length];
    if (arrayOfString1 != null)
      System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, arrayOfString1.length); 
    System.arraycopy(RECOGNIZED_PROPERTIES, 0, arrayOfString2, b, RECOGNIZED_PROPERTIES.length);
    return arrayOfString2;
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    super.setProperty(paramString, paramObject);
    if (paramString.startsWith("http://apache.org/xml/properties/")) {
      int i = paramString.length() - "http://apache.org/xml/properties/".length();
      if (i == "internal/dtd-scanner".length() && paramString.endsWith("internal/dtd-scanner"))
        this.fDTDScanner = (XMLDTDScanner)paramObject; 
      if (i == "internal/namespace-context".length() && paramString.endsWith("internal/namespace-context") && paramObject != null)
        this.fNamespaceContext = (NamespaceContext)paramObject; 
      return;
    } 
  }
  
  public Boolean getFeatureDefault(String paramString) {
    for (byte b = 0; b < RECOGNIZED_FEATURES.length; b++) {
      if (RECOGNIZED_FEATURES[b].equals(paramString))
        return FEATURE_DEFAULTS[b]; 
    } 
    return super.getFeatureDefault(paramString);
  }
  
  public Object getPropertyDefault(String paramString) {
    for (byte b = 0; b < RECOGNIZED_PROPERTIES.length; b++) {
      if (RECOGNIZED_PROPERTIES[b].equals(paramString))
        return PROPERTY_DEFAULTS[b]; 
    } 
    return super.getPropertyDefault(paramString);
  }
  
  public void startEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    super.startEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations);
    if (!paramString1.equals("[xml]") && this.fEntityScanner.isExternal())
      setScannerState(16); 
    if (this.fDocumentHandler != null && paramString1.equals("[xml]"))
      this.fDocumentHandler.startDocument(this.fEntityScanner, paramString2, this.fNamespaceContext, null); 
  }
  
  public void endEntity(String paramString, Augmentations paramAugmentations) throws XNIException {
    super.endEntity(paramString, paramAugmentations);
    if (this.fDocumentHandler != null && paramString.equals("[xml]"))
      this.fDocumentHandler.endDocument(null); 
  }
  
  protected XMLDocumentFragmentScannerImpl.Dispatcher createContentDispatcher() {
    return new ContentDispatcher(this);
  }
  
  protected boolean scanDoctypeDecl() throws IOException, XNIException {
    if (!this.fEntityScanner.skipSpaces())
      reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL", null); 
    this.fDoctypeName = this.fEntityScanner.scanName();
    if (this.fDoctypeName == null)
      reportFatalError("MSG_ROOT_ELEMENT_TYPE_REQUIRED", null); 
    if (this.fEntityScanner.skipSpaces()) {
      scanExternalID(this.fStrings, false);
      this.fDoctypeSystemId = this.fStrings[0];
      this.fDoctypePublicId = this.fStrings[1];
      this.fEntityScanner.skipSpaces();
    } 
    this.fHasExternalDTD = (this.fDoctypeSystemId != null);
    if (!this.fHasExternalDTD && this.fExternalSubsetResolver != null) {
      this.fDTDDescription.setValues(null, null, this.fEntityManager.getCurrentResourceIdentifier().getExpandedSystemId(), null);
      this.fDTDDescription.setRootName(this.fDoctypeName);
      this.fExternalSubsetSource = this.fExternalSubsetResolver.getExternalSubset((XMLDTDDescription)this.fDTDDescription);
      this.fHasExternalDTD = (this.fExternalSubsetSource != null);
    } 
    if (this.fDocumentHandler != null)
      if (this.fExternalSubsetSource == null) {
        this.fDocumentHandler.doctypeDecl(this.fDoctypeName, this.fDoctypePublicId, this.fDoctypeSystemId, null);
      } else {
        this.fDocumentHandler.doctypeDecl(this.fDoctypeName, this.fExternalSubsetSource.getPublicId(), this.fExternalSubsetSource.getSystemId(), null);
      }  
    boolean bool = true;
    if (!this.fEntityScanner.skipChar(91)) {
      bool = false;
      this.fEntityScanner.skipSpaces();
      if (!this.fEntityScanner.skipChar(62))
        reportFatalError("DoctypedeclUnterminated", new Object[] { this.fDoctypeName }); 
      this.fMarkupDepth--;
    } 
    return bool;
  }
  
  protected String getScannerStateName(int paramInt) {
    switch (paramInt) {
      case 0:
        return "SCANNER_STATE_XML_DECL";
      case 5:
        return "SCANNER_STATE_PROLOG";
      case 12:
        return "SCANNER_STATE_TRAILING_MISC";
      case 17:
        return "SCANNER_STATE_DTD_INTERNAL_DECLS";
      case 18:
        return "SCANNER_STATE_DTD_EXTERNAL";
      case 19:
        return "SCANNER_STATE_DTD_EXTERNAL_DECLS";
    } 
    return super.getScannerStateName(paramInt);
  }
  
  protected final class TrailingMiscDispatcher implements XMLDocumentFragmentScannerImpl.Dispatcher {
    private final XMLDocumentScannerImpl this$0;
    
    protected TrailingMiscDispatcher(XMLDocumentScannerImpl this$0) {
      this.this$0 = this$0;
    }
    
    public boolean dispatch(boolean param1Boolean) throws IOException, XNIException {
      try {
        while (true) {
          int i;
          boolean bool = false;
          switch (this.this$0.fScannerState) {
            case 12:
              this.this$0.fEntityScanner.skipSpaces();
              if (this.this$0.fEntityScanner.skipChar(60)) {
                this.this$0.setScannerState(1);
                bool = true;
                break;
              } 
              this.this$0.setScannerState(7);
              bool = true;
              break;
            case 1:
              this.this$0.fMarkupDepth++;
              if (this.this$0.fEntityScanner.skipChar(63)) {
                this.this$0.setScannerState(3);
                bool = true;
                break;
              } 
              if (this.this$0.fEntityScanner.skipChar(33)) {
                this.this$0.setScannerState(2);
                bool = true;
                break;
              } 
              if (this.this$0.fEntityScanner.skipChar(47)) {
                this.this$0.reportFatalError("MarkupNotRecognizedInMisc", null);
                bool = true;
                break;
              } 
              if (this.this$0.isValidNameStartChar(this.this$0.fEntityScanner.peekChar())) {
                this.this$0.reportFatalError("MarkupNotRecognizedInMisc", null);
                this.this$0.scanStartElement();
                this.this$0.setScannerState(7);
                break;
              } 
              if (this.this$0.isValidNameStartHighSurrogate(this.this$0.fEntityScanner.peekChar())) {
                this.this$0.reportFatalError("MarkupNotRecognizedInMisc", null);
                this.this$0.scanStartElement();
                this.this$0.setScannerState(7);
                break;
              } 
              this.this$0.reportFatalError("MarkupNotRecognizedInMisc", null);
              break;
            case 3:
              this.this$0.scanPI();
              this.this$0.setScannerState(12);
              break;
            case 2:
              if (!this.this$0.fEntityScanner.skipString("--"))
                this.this$0.reportFatalError("InvalidCommentStart", null); 
              this.this$0.scanComment();
              this.this$0.setScannerState(12);
              break;
            case 7:
              i = this.this$0.fEntityScanner.peekChar();
              if (i == -1) {
                this.this$0.setScannerState(14);
                return false;
              } 
              this.this$0.reportFatalError("ContentIllegalInTrailingMisc", null);
              this.this$0.fEntityScanner.scanChar();
              this.this$0.setScannerState(12);
              break;
            case 8:
              this.this$0.reportFatalError("ReferenceIllegalInTrailingMisc", null);
              this.this$0.setScannerState(12);
              break;
            case 14:
              return false;
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
        if (this.this$0.fMarkupDepth != 0) {
          this.this$0.reportFatalError("PrematureEOF", null);
          return false;
        } 
        this.this$0.setScannerState(14);
        return false;
      } 
    }
  }
  
  protected class ContentDispatcher extends XMLDocumentFragmentScannerImpl.FragmentContentDispatcher {
    private final XMLDocumentScannerImpl this$0;
    
    protected ContentDispatcher(XMLDocumentScannerImpl this$0) {
      super(this$0);
      this.this$0 = this$0;
    }
    
    protected boolean scanForDoctypeHook() throws IOException, XNIException {
      if (this.this$0.fEntityScanner.skipString("DOCTYPE")) {
        this.this$0.setScannerState(4);
        return true;
      } 
      return false;
    }
    
    protected boolean elementDepthIsZeroHook() throws IOException, XNIException {
      this.this$0.setScannerState(12);
      this.this$0.setDispatcher(this.this$0.fTrailingMiscDispatcher);
      return true;
    }
    
    protected boolean scanRootElementHook() throws IOException, XNIException {
      if (this.this$0.fExternalSubsetResolver != null && !this.this$0.fSeenDoctypeDecl && !this.this$0.fDisallowDoctype && (this.this$0.fValidation || this.this$0.fLoadExternalDTD)) {
        this.this$0.scanStartElementName();
        resolveExternalSubsetAndRead();
        if (this.this$0.scanStartElementAfterName()) {
          this.this$0.setScannerState(12);
          this.this$0.setDispatcher(this.this$0.fTrailingMiscDispatcher);
          return true;
        } 
      } else if (this.this$0.scanStartElement()) {
        this.this$0.setScannerState(12);
        this.this$0.setDispatcher(this.this$0.fTrailingMiscDispatcher);
        return true;
      } 
      return false;
    }
    
    protected void endOfFileHook(EOFException param1EOFException) throws IOException, XNIException {
      this.this$0.reportFatalError("PrematureEOF", null);
    }
    
    protected void resolveExternalSubsetAndRead() throws IOException, XNIException {
      this.this$0.fDTDDescription.setValues(null, null, this.this$0.fEntityManager.getCurrentResourceIdentifier().getExpandedSystemId(), null);
      this.this$0.fDTDDescription.setRootName(this.this$0.fElementQName.rawname);
      XMLInputSource xMLInputSource = this.this$0.fExternalSubsetResolver.getExternalSubset((XMLDTDDescription)this.this$0.fDTDDescription);
      if (xMLInputSource != null) {
        this.this$0.fDoctypeName = this.this$0.fElementQName.rawname;
        this.this$0.fDoctypePublicId = xMLInputSource.getPublicId();
        this.this$0.fDoctypeSystemId = xMLInputSource.getSystemId();
        if (this.this$0.fDocumentHandler != null)
          this.this$0.fDocumentHandler.doctypeDecl(this.this$0.fDoctypeName, this.this$0.fDoctypePublicId, this.this$0.fDoctypeSystemId, null); 
        try {
          if (this.this$0.fValidationManager == null || !this.this$0.fValidationManager.isCachedDTD()) {
            this.this$0.fDTDScanner.setInputSource(xMLInputSource);
            do {
            
            } while (this.this$0.fDTDScanner.scanDTDExternalSubset(true));
          } else {
            this.this$0.fDTDScanner.setInputSource(null);
          } 
        } finally {
          this.this$0.fEntityManager.setEntityHandler(this.this$0);
        } 
      } 
    }
  }
  
  protected final class DTDDispatcher implements XMLDocumentFragmentScannerImpl.Dispatcher {
    private final XMLDocumentScannerImpl this$0;
    
    protected DTDDispatcher(XMLDocumentScannerImpl this$0) {
      this.this$0 = this$0;
    }
    
    public boolean dispatch(boolean param1Boolean) throws IOException, XNIException {
      this.this$0.fEntityManager.setEntityHandler(null);
      try {
        while (true) {
          try {
            boolean bool3;
            XMLInputSource xMLInputSource;
            boolean bool2;
            boolean bool4;
            boolean bool5;
            boolean bool1 = false;
            switch (this.this$0.fScannerState) {
              case 17:
                bool3 = true;
                bool4 = ((this.this$0.fValidation || this.this$0.fLoadExternalDTD) && (this.this$0.fValidationManager == null || !this.this$0.fValidationManager.isCachedDTD()));
                bool5 = this.this$0.fDTDScanner.scanDTDInternalSubset(bool3, this.this$0.fStandalone, (this.this$0.fHasExternalDTD && bool4));
                if (!bool5) {
                  if (!this.this$0.fEntityScanner.skipChar(93))
                    this.this$0.reportFatalError("EXPECTED_SQUARE_BRACKET_TO_CLOSE_INTERNAL_SUBSET", null); 
                  this.this$0.fEntityScanner.skipSpaces();
                  if (!this.this$0.fEntityScanner.skipChar(62))
                    this.this$0.reportFatalError("DoctypedeclUnterminated", new Object[] { this.this$0.fDoctypeName }); 
                  this.this$0.fMarkupDepth--;
                  if (this.this$0.fDoctypeSystemId != null) {
                    this.this$0.fIsEntityDeclaredVC = !this.this$0.fStandalone;
                    if (bool4) {
                      this.this$0.setScannerState(18);
                      break;
                    } 
                  } else if (this.this$0.fExternalSubsetSource != null) {
                    this.this$0.fIsEntityDeclaredVC = !this.this$0.fStandalone;
                    if (bool4) {
                      this.this$0.fDTDScanner.setInputSource(this.this$0.fExternalSubsetSource);
                      this.this$0.fExternalSubsetSource = null;
                      this.this$0.setScannerState(19);
                      break;
                    } 
                  } else {
                    this.this$0.fIsEntityDeclaredVC = (this.this$0.fEntityManager.hasPEReferences() && !this.this$0.fStandalone);
                  } 
                  this.this$0.setScannerState(5);
                  this.this$0.setDispatcher(this.this$0.fPrologDispatcher);
                  this.this$0.fEntityManager.setEntityHandler(this.this$0);
                  return true;
                } 
                break;
              case 18:
                this.this$0.fDTDDescription.setValues(this.this$0.fDoctypePublicId, this.this$0.fDoctypeSystemId, null, null);
                this.this$0.fDTDDescription.setRootName(this.this$0.fDoctypeName);
                xMLInputSource = this.this$0.fEntityManager.resolveEntity((XMLResourceIdentifier)this.this$0.fDTDDescription);
                this.this$0.fDTDScanner.setInputSource(xMLInputSource);
                this.this$0.setScannerState(19);
                bool1 = true;
                break;
              case 19:
                bool2 = true;
                bool4 = this.this$0.fDTDScanner.scanDTDExternalSubset(bool2);
                if (!bool4) {
                  this.this$0.setScannerState(5);
                  this.this$0.setDispatcher(this.this$0.fPrologDispatcher);
                  this.this$0.fEntityManager.setEntityHandler(this.this$0);
                  return true;
                } 
                break;
              default:
                throw new XNIException("DTDDispatcher#dispatch: scanner state=" + this.this$0.fScannerState + " (" + this.this$0.getScannerStateName(this.this$0.fScannerState) + ')');
            } 
            if (!param1Boolean && !bool1)
              return true; 
          } catch (MalformedByteSequenceException malformedByteSequenceException) {
            this.this$0.fErrorReporter.reportError(malformedByteSequenceException.getDomain(), malformedByteSequenceException.getKey(), malformedByteSequenceException.getArguments(), (short)2, (Exception)malformedByteSequenceException);
            return false;
          } catch (CharConversionException charConversionException) {
            this.this$0.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[])null, (short)2, charConversionException);
            return false;
          } catch (EOFException eOFException) {
            this.this$0.reportFatalError("PrematureEOF", null);
            return false;
          } 
        } 
      } finally {
        this.this$0.fEntityManager.setEntityHandler(this.this$0);
      } 
    }
  }
  
  protected final class PrologDispatcher implements XMLDocumentFragmentScannerImpl.Dispatcher {
    private final XMLDocumentScannerImpl this$0;
    
    protected PrologDispatcher(XMLDocumentScannerImpl this$0) {
      this.this$0 = this$0;
    }
    
    public boolean dispatch(boolean param1Boolean) throws IOException, XNIException {
      try {
        while (true) {
          boolean bool = false;
          switch (this.this$0.fScannerState) {
            case 5:
              this.this$0.fEntityScanner.skipSpaces();
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
              this.this$0.setScannerState(7);
              bool = true;
              break;
            case 1:
              this.this$0.fMarkupDepth++;
              if (this.this$0.fEntityScanner.skipChar(33)) {
                if (this.this$0.fEntityScanner.skipChar(45)) {
                  if (!this.this$0.fEntityScanner.skipChar(45))
                    this.this$0.reportFatalError("InvalidCommentStart", null); 
                  this.this$0.setScannerState(2);
                  bool = true;
                  break;
                } 
                if (this.this$0.fEntityScanner.skipString("DOCTYPE")) {
                  this.this$0.setScannerState(4);
                  bool = true;
                  break;
                } 
                this.this$0.reportFatalError("MarkupNotRecognizedInProlog", null);
                break;
              } 
              if (this.this$0.isValidNameStartChar(this.this$0.fEntityScanner.peekChar())) {
                this.this$0.setScannerState(6);
                this.this$0.setDispatcher(this.this$0.fContentDispatcher);
                return true;
              } 
              if (this.this$0.fEntityScanner.skipChar(63)) {
                this.this$0.setScannerState(3);
                bool = true;
                break;
              } 
              if (this.this$0.isValidNameStartHighSurrogate(this.this$0.fEntityScanner.peekChar())) {
                this.this$0.setScannerState(6);
                this.this$0.setDispatcher(this.this$0.fContentDispatcher);
                return true;
              } 
              this.this$0.reportFatalError("MarkupNotRecognizedInProlog", null);
              break;
            case 2:
              this.this$0.scanComment();
              this.this$0.setScannerState(5);
              break;
            case 3:
              this.this$0.scanPI();
              this.this$0.setScannerState(5);
              break;
            case 4:
              if (this.this$0.fDisallowDoctype)
                this.this$0.reportFatalError("DoctypeNotAllowed", null); 
              if (this.this$0.fSeenDoctypeDecl)
                this.this$0.reportFatalError("AlreadySeenDoctype", null); 
              this.this$0.fSeenDoctypeDecl = true;
              if (this.this$0.scanDoctypeDecl()) {
                this.this$0.setScannerState(17);
                this.this$0.setDispatcher(this.this$0.fDTDDispatcher);
                return true;
              } 
              if (this.this$0.fDoctypeSystemId != null) {
                this.this$0.fIsEntityDeclaredVC = !this.this$0.fStandalone;
                if ((this.this$0.fValidation || this.this$0.fLoadExternalDTD) && (this.this$0.fValidationManager == null || !this.this$0.fValidationManager.isCachedDTD())) {
                  this.this$0.setScannerState(18);
                  this.this$0.setDispatcher(this.this$0.fDTDDispatcher);
                  return true;
                } 
              } else if (this.this$0.fExternalSubsetSource != null) {
                this.this$0.fIsEntityDeclaredVC = !this.this$0.fStandalone;
                if ((this.this$0.fValidation || this.this$0.fLoadExternalDTD) && (this.this$0.fValidationManager == null || !this.this$0.fValidationManager.isCachedDTD())) {
                  this.this$0.fDTDScanner.setInputSource(this.this$0.fExternalSubsetSource);
                  this.this$0.fExternalSubsetSource = null;
                  this.this$0.setScannerState(19);
                  this.this$0.setDispatcher(this.this$0.fDTDDispatcher);
                  return true;
                } 
              } 
              this.this$0.fDTDScanner.setInputSource(null);
              this.this$0.setScannerState(5);
              break;
            case 7:
              this.this$0.reportFatalError("ContentIllegalInProlog", null);
              this.this$0.fEntityScanner.scanChar();
            case 8:
              this.this$0.reportFatalError("ReferenceIllegalInProlog", null);
              break;
          } 
          if (!param1Boolean && !bool) {
            if (param1Boolean) {
              if (this.this$0.fEntityScanner.scanChar() != 60)
                this.this$0.reportFatalError("RootElementRequired", null); 
              this.this$0.setScannerState(6);
              this.this$0.setDispatcher(this.this$0.fContentDispatcher);
            } 
            return true;
          } 
        } 
      } catch (MalformedByteSequenceException malformedByteSequenceException) {
        this.this$0.fErrorReporter.reportError(malformedByteSequenceException.getDomain(), malformedByteSequenceException.getKey(), malformedByteSequenceException.getArguments(), (short)2, (Exception)malformedByteSequenceException);
        return false;
      } catch (CharConversionException charConversionException) {
        this.this$0.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[])null, (short)2, charConversionException);
        return false;
      } catch (EOFException eOFException) {
        this.this$0.reportFatalError("PrematureEOF", null);
        return false;
      } 
    }
  }
  
  protected final class XMLDeclDispatcher implements XMLDocumentFragmentScannerImpl.Dispatcher {
    private final XMLDocumentScannerImpl this$0;
    
    protected XMLDeclDispatcher(XMLDocumentScannerImpl this$0) {
      this.this$0 = this$0;
    }
    
    public boolean dispatch(boolean param1Boolean) throws IOException, XNIException {
      this.this$0.setScannerState(5);
      this.this$0.setDispatcher(this.this$0.fPrologDispatcher);
      try {
        if (this.this$0.fEntityScanner.skipString("<?xml")) {
          this.this$0.fMarkupDepth++;
          if (XMLChar.isName(this.this$0.fEntityScanner.peekChar())) {
            this.this$0.fStringBuffer.clear();
            this.this$0.fStringBuffer.append("xml");
            if (this.this$0.fNamespaces) {
              while (XMLChar.isNCName(this.this$0.fEntityScanner.peekChar()))
                this.this$0.fStringBuffer.append((char)this.this$0.fEntityScanner.scanChar()); 
            } else {
              while (XMLChar.isName(this.this$0.fEntityScanner.peekChar()))
                this.this$0.fStringBuffer.append((char)this.this$0.fEntityScanner.scanChar()); 
            } 
            String str = this.this$0.fSymbolTable.addSymbol(((XMLString)this.this$0.fStringBuffer).ch, ((XMLString)this.this$0.fStringBuffer).offset, ((XMLString)this.this$0.fStringBuffer).length);
            this.this$0.scanPIData(str, this.this$0.fString);
          } else {
            this.this$0.scanXMLDeclOrTextDecl(false);
          } 
        } 
        this.this$0.fEntityManager.fCurrentEntity.mayReadChunks = true;
        return true;
      } catch (MalformedByteSequenceException malformedByteSequenceException) {
        this.this$0.fErrorReporter.reportError(malformedByteSequenceException.getDomain(), malformedByteSequenceException.getKey(), malformedByteSequenceException.getArguments(), (short)2, (Exception)malformedByteSequenceException);
        return false;
      } catch (CharConversionException charConversionException) {
        this.this$0.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[])null, (short)2, charConversionException);
        return false;
      } catch (EOFException eOFException) {
        this.this$0.reportFatalError("PrematureEOF", null);
        return false;
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XMLDocumentScannerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */