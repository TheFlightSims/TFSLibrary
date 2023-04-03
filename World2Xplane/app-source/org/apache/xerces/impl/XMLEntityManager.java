package org.apache.xerces.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import org.apache.xerces.impl.io.ASCIIReader;
import org.apache.xerces.impl.io.Latin1Reader;
import org.apache.xerces.impl.io.UCSReader;
import org.apache.xerces.impl.io.UTF8Reader;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.util.AugmentationsImpl;
import org.apache.xerces.util.EncodingMap;
import org.apache.xerces.util.HTTPInputSource;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.URI;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLEntityDescriptionImpl;
import org.apache.xerces.util.XMLResourceIdentifierImpl;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XMLEntityManager implements XMLComponent, XMLEntityResolver {
  public static final int DEFAULT_BUFFER_SIZE = 2048;
  
  public static final int DEFAULT_XMLDECL_BUFFER_SIZE = 64;
  
  public static final int DEFAULT_INTERNAL_BUFFER_SIZE = 512;
  
  protected static final String VALIDATION = "http://xml.org/sax/features/validation";
  
  protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
  
  protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
  
  protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
  
  protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
  
  protected static final String STANDARD_URI_CONFORMANT = "http://apache.org/xml/features/standard-uri-conformant";
  
  protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
  
  protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  protected static final String BUFFER_SIZE = "http://apache.org/xml/properties/input-buffer-size";
  
  protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  private static final String[] RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://xml.org/sax/features/external-general-entities", "http://xml.org/sax/features/external-parameter-entities", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/warn-on-duplicate-entitydef", "http://apache.org/xml/features/standard-uri-conformant" };
  
  private static final Boolean[] FEATURE_DEFAULTS = new Boolean[] { null, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE };
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/properties/input-buffer-size", "http://apache.org/xml/properties/security-manager" };
  
  private static final Object[] PROPERTY_DEFAULTS = new Object[] { null, null, null, null, new Integer(2048), null };
  
  private static final String XMLEntity = "[xml]".intern();
  
  private static final String DTDEntity = "[dtd]".intern();
  
  private static final boolean DEBUG_BUFFER = false;
  
  private static final boolean DEBUG_ENTITIES = false;
  
  private static final boolean DEBUG_ENCODINGS = false;
  
  private static final boolean DEBUG_RESOLVER = false;
  
  protected boolean fValidation;
  
  protected boolean fExternalGeneralEntities = true;
  
  protected boolean fExternalParameterEntities = true;
  
  protected boolean fAllowJavaEncodings;
  
  protected boolean fWarnDuplicateEntityDef;
  
  protected boolean fStrictURI;
  
  protected SymbolTable fSymbolTable;
  
  protected XMLErrorReporter fErrorReporter;
  
  protected XMLEntityResolver fEntityResolver;
  
  protected ValidationManager fValidationManager;
  
  protected int fBufferSize = 2048;
  
  protected SecurityManager fSecurityManager = null;
  
  protected boolean fStandalone;
  
  protected boolean fHasPEReferences;
  
  protected boolean fInExternalSubset = false;
  
  protected XMLEntityHandler fEntityHandler;
  
  protected XMLEntityScanner fEntityScanner;
  
  protected XMLEntityScanner fXML10EntityScanner;
  
  protected XMLEntityScanner fXML11EntityScanner;
  
  protected int fEntityExpansionLimit = 0;
  
  protected int fEntityExpansionCount = 0;
  
  protected final Hashtable fEntities = new Hashtable();
  
  protected final Stack fEntityStack = new Stack();
  
  protected ScannedEntity fCurrentEntity;
  
  protected Hashtable fDeclaredEntities;
  
  private final XMLResourceIdentifierImpl fResourceIdentifier = new XMLResourceIdentifierImpl();
  
  private final Augmentations fEntityAugs = (Augmentations)new AugmentationsImpl();
  
  private final ByteBufferPool fByteBufferPool = new ByteBufferPool(this.fBufferSize);
  
  private byte[] fTempByteBuffer = null;
  
  private final CharacterBufferPool fCharacterBufferPool = new CharacterBufferPool(this.fBufferSize, 512);
  
  protected Stack fReaderStack = new Stack();
  
  private static String gUserDir;
  
  private static URI gUserDirURI;
  
  private static final boolean[] gNeedEscaping = new boolean[128];
  
  private static final char[] gAfterEscaping1 = new char[128];
  
  private static final char[] gAfterEscaping2 = new char[128];
  
  private static final char[] gHexChs = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'A', 'B', 'C', 'D', 'E', 'F' };
  
  private static PrivilegedAction GET_USER_DIR_SYSTEM_PROPERTY = new PrivilegedAction() {
      public Object run() {
        return System.getProperty("user.dir");
      }
    };
  
  public XMLEntityManager() {
    this(null);
  }
  
  public XMLEntityManager(XMLEntityManager paramXMLEntityManager) {
    this.fDeclaredEntities = (paramXMLEntityManager != null) ? paramXMLEntityManager.getDeclaredEntities() : null;
    setScannerVersion((short)1);
  }
  
  public void setStandalone(boolean paramBoolean) {
    this.fStandalone = paramBoolean;
  }
  
  public boolean isStandalone() {
    return this.fStandalone;
  }
  
  final void notifyHasPEReferences() {
    this.fHasPEReferences = true;
  }
  
  final boolean hasPEReferences() {
    return this.fHasPEReferences;
  }
  
  public void setEntityHandler(XMLEntityHandler paramXMLEntityHandler) {
    this.fEntityHandler = paramXMLEntityHandler;
  }
  
  public XMLResourceIdentifier getCurrentResourceIdentifier() {
    return (XMLResourceIdentifier)this.fResourceIdentifier;
  }
  
  public ScannedEntity getCurrentEntity() {
    return this.fCurrentEntity;
  }
  
  public void addInternalEntity(String paramString1, String paramString2) {
    if (!this.fEntities.containsKey(paramString1)) {
      InternalEntity internalEntity = new InternalEntity(paramString1, paramString2, this.fInExternalSubset);
      this.fEntities.put(paramString1, internalEntity);
    } else if (this.fWarnDuplicateEntityDef) {
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[] { paramString1 }, (short)0);
    } 
  }
  
  public void addExternalEntity(String paramString1, String paramString2, String paramString3, String paramString4) throws IOException {
    if (!this.fEntities.containsKey(paramString1)) {
      if (paramString4 == null) {
        int i = this.fEntityStack.size();
        if (i == 0 && this.fCurrentEntity != null && this.fCurrentEntity.entityLocation != null)
          paramString4 = this.fCurrentEntity.entityLocation.getExpandedSystemId(); 
        for (int j = i - 1; j >= 0; j--) {
          ScannedEntity scannedEntity = this.fEntityStack.elementAt(j);
          if (scannedEntity.entityLocation != null && scannedEntity.entityLocation.getExpandedSystemId() != null) {
            paramString4 = scannedEntity.entityLocation.getExpandedSystemId();
            break;
          } 
        } 
      } 
      ExternalEntity externalEntity = new ExternalEntity(paramString1, (XMLResourceIdentifier)new XMLEntityDescriptionImpl(paramString1, paramString2, paramString3, paramString4, expandSystemId(paramString3, paramString4, false)), null, this.fInExternalSubset);
      this.fEntities.put(paramString1, externalEntity);
    } else if (this.fWarnDuplicateEntityDef) {
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[] { paramString1 }, (short)0);
    } 
  }
  
  public boolean isExternalEntity(String paramString) {
    Entity entity = (Entity)this.fEntities.get(paramString);
    return (entity == null) ? false : entity.isExternal();
  }
  
  public boolean isEntityDeclInExternalSubset(String paramString) {
    Entity entity = (Entity)this.fEntities.get(paramString);
    return (entity == null) ? false : entity.isEntityDeclInExternalSubset();
  }
  
  public void addUnparsedEntity(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    if (!this.fEntities.containsKey(paramString1)) {
      ExternalEntity externalEntity = new ExternalEntity(paramString1, (XMLResourceIdentifier)new XMLEntityDescriptionImpl(paramString1, paramString2, paramString3, paramString4, null), paramString5, this.fInExternalSubset);
      this.fEntities.put(paramString1, externalEntity);
    } else if (this.fWarnDuplicateEntityDef) {
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[] { paramString1 }, (short)0);
    } 
  }
  
  public boolean isUnparsedEntity(String paramString) {
    Entity entity = (Entity)this.fEntities.get(paramString);
    return (entity == null) ? false : entity.isUnparsed();
  }
  
  public boolean isDeclaredEntity(String paramString) {
    Entity entity = (Entity)this.fEntities.get(paramString);
    return (entity != null);
  }
  
  public XMLInputSource resolveEntity(XMLResourceIdentifier paramXMLResourceIdentifier) throws IOException, XNIException {
    if (paramXMLResourceIdentifier == null)
      return null; 
    String str1 = paramXMLResourceIdentifier.getPublicId();
    String str2 = paramXMLResourceIdentifier.getLiteralSystemId();
    String str3 = paramXMLResourceIdentifier.getBaseSystemId();
    String str4 = paramXMLResourceIdentifier.getExpandedSystemId();
    boolean bool = (str4 == null) ? true : false;
    if (str3 == null && this.fCurrentEntity != null && this.fCurrentEntity.entityLocation != null) {
      str3 = this.fCurrentEntity.entityLocation.getExpandedSystemId();
      if (str3 != null)
        bool = true; 
    } 
    if (bool)
      str4 = expandSystemId(str2, str3, false); 
    XMLInputSource xMLInputSource = null;
    if (this.fEntityResolver != null) {
      paramXMLResourceIdentifier.setBaseSystemId(str3);
      paramXMLResourceIdentifier.setExpandedSystemId(str4);
      xMLInputSource = this.fEntityResolver.resolveEntity(paramXMLResourceIdentifier);
    } 
    if (xMLInputSource == null)
      xMLInputSource = new XMLInputSource(str1, str2, str3); 
    return xMLInputSource;
  }
  
  public void startEntity(String paramString, boolean paramBoolean) throws IOException, XNIException {
    Entity entity = (Entity)this.fEntities.get(paramString);
    if (entity == null) {
      if (this.fEntityHandler != null) {
        String str = null;
        this.fResourceIdentifier.clear();
        this.fEntityAugs.removeAllItems();
        this.fEntityAugs.putItem("ENTITY_SKIPPED", Boolean.TRUE);
        this.fEntityHandler.startEntity(paramString, (XMLResourceIdentifier)this.fResourceIdentifier, str, this.fEntityAugs);
        this.fEntityAugs.removeAllItems();
        this.fEntityAugs.putItem("ENTITY_SKIPPED", Boolean.TRUE);
        this.fEntityHandler.endEntity(paramString, this.fEntityAugs);
      } 
      return;
    } 
    boolean bool = entity.isExternal();
    if (bool && (this.fValidationManager == null || !this.fValidationManager.isCachedDTD())) {
      boolean bool1 = entity.isUnparsed();
      boolean bool2 = paramString.startsWith("%");
      boolean bool3 = !bool2 ? true : false;
      if (bool1 || (bool3 && !this.fExternalGeneralEntities) || (bool2 && !this.fExternalParameterEntities)) {
        if (this.fEntityHandler != null) {
          this.fResourceIdentifier.clear();
          String str1 = null;
          ExternalEntity externalEntity = (ExternalEntity)entity;
          String str2 = (externalEntity.entityLocation != null) ? externalEntity.entityLocation.getLiteralSystemId() : null;
          String str3 = (externalEntity.entityLocation != null) ? externalEntity.entityLocation.getBaseSystemId() : null;
          String str4 = expandSystemId(str2, str3, false);
          this.fResourceIdentifier.setValues((externalEntity.entityLocation != null) ? externalEntity.entityLocation.getPublicId() : null, str2, str3, str4);
          this.fEntityAugs.removeAllItems();
          this.fEntityAugs.putItem("ENTITY_SKIPPED", Boolean.TRUE);
          this.fEntityHandler.startEntity(paramString, (XMLResourceIdentifier)this.fResourceIdentifier, str1, this.fEntityAugs);
          this.fEntityAugs.removeAllItems();
          this.fEntityAugs.putItem("ENTITY_SKIPPED", Boolean.TRUE);
          this.fEntityHandler.endEntity(paramString, this.fEntityAugs);
        } 
        return;
      } 
    } 
    int i = this.fEntityStack.size();
    for (int j = i; j >= 0; j--) {
      Entity entity1 = (j == i) ? this.fCurrentEntity : this.fEntityStack.elementAt(j);
      if (entity1.name == paramString) {
        StringBuffer stringBuffer = new StringBuffer(paramString);
        for (int k = j + 1; k < i; k++) {
          entity1 = this.fEntityStack.elementAt(k);
          stringBuffer.append(" -> ");
          stringBuffer.append(entity1.name);
        } 
        stringBuffer.append(" -> ");
        stringBuffer.append(this.fCurrentEntity.name);
        stringBuffer.append(" -> ");
        stringBuffer.append(paramString);
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RecursiveReference", new Object[] { paramString, stringBuffer.toString() }, (short)2);
        if (this.fEntityHandler != null) {
          this.fResourceIdentifier.clear();
          String str = null;
          if (bool) {
            ExternalEntity externalEntity = (ExternalEntity)entity;
            String str1 = (externalEntity.entityLocation != null) ? externalEntity.entityLocation.getLiteralSystemId() : null;
            String str2 = (externalEntity.entityLocation != null) ? externalEntity.entityLocation.getBaseSystemId() : null;
            String str3 = expandSystemId(str1, str2, false);
            this.fResourceIdentifier.setValues((externalEntity.entityLocation != null) ? externalEntity.entityLocation.getPublicId() : null, str1, str2, str3);
          } 
          this.fEntityAugs.removeAllItems();
          this.fEntityAugs.putItem("ENTITY_SKIPPED", Boolean.TRUE);
          this.fEntityHandler.startEntity(paramString, (XMLResourceIdentifier)this.fResourceIdentifier, str, this.fEntityAugs);
          this.fEntityAugs.removeAllItems();
          this.fEntityAugs.putItem("ENTITY_SKIPPED", Boolean.TRUE);
          this.fEntityHandler.endEntity(paramString, this.fEntityAugs);
        } 
        return;
      } 
    } 
    XMLInputSource xMLInputSource = null;
    if (bool) {
      ExternalEntity externalEntity = (ExternalEntity)entity;
      xMLInputSource = resolveEntity(externalEntity.entityLocation);
    } else {
      InternalEntity internalEntity = (InternalEntity)entity;
      StringReader stringReader = new StringReader(internalEntity.text);
      xMLInputSource = new XMLInputSource(null, null, null, stringReader, null);
    } 
    startEntity(paramString, xMLInputSource, paramBoolean, bool);
  }
  
  public void startDocumentEntity(XMLInputSource paramXMLInputSource) throws IOException, XNIException {
    startEntity(XMLEntity, paramXMLInputSource, false, true);
  }
  
  public void startDTDEntity(XMLInputSource paramXMLInputSource) throws IOException, XNIException {
    startEntity(DTDEntity, paramXMLInputSource, false, true);
  }
  
  public void startExternalSubset() {
    this.fInExternalSubset = true;
  }
  
  public void endExternalSubset() {
    this.fInExternalSubset = false;
  }
  
  public void startEntity(String paramString, XMLInputSource paramXMLInputSource, boolean paramBoolean1, boolean paramBoolean2) throws IOException, XNIException {
    String str = setupCurrentEntity(paramString, paramXMLInputSource, paramBoolean1, paramBoolean2);
    if (this.fSecurityManager != null && this.fEntityExpansionCount++ > this.fEntityExpansionLimit) {
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityExpansionLimitExceeded", new Object[] { new Integer(this.fEntityExpansionLimit) }, (short)2);
      this.fEntityExpansionCount = 0;
    } 
    if (this.fEntityHandler != null)
      this.fEntityHandler.startEntity(paramString, (XMLResourceIdentifier)this.fResourceIdentifier, str, null); 
  }
  
  public String setupCurrentEntity(String paramString, XMLInputSource paramXMLInputSource, boolean paramBoolean1, boolean paramBoolean2) throws IOException, XNIException {
    String str1 = paramXMLInputSource.getPublicId();
    String str2 = paramXMLInputSource.getSystemId();
    String str3 = paramXMLInputSource.getBaseSystemId();
    String str4 = paramXMLInputSource.getEncoding();
    boolean bool = (str4 != null) ? true : false;
    Boolean bool1 = null;
    this.fTempByteBuffer = null;
    InputStream inputStream = null;
    Reader reader = paramXMLInputSource.getCharacterStream();
    String str5 = expandSystemId(str2, str3, this.fStrictURI);
    if (str3 == null)
      str3 = str5; 
    if (reader == null) {
      inputStream = paramXMLInputSource.getByteStream();
      if (inputStream == null) {
        URL uRL = new URL(str5);
        URLConnection uRLConnection = uRL.openConnection();
        if (!(uRLConnection instanceof HttpURLConnection)) {
          inputStream = uRLConnection.getInputStream();
        } else {
          boolean bool2 = true;
          if (paramXMLInputSource instanceof HTTPInputSource) {
            HttpURLConnection httpURLConnection = (HttpURLConnection)uRLConnection;
            HTTPInputSource hTTPInputSource = (HTTPInputSource)paramXMLInputSource;
            Iterator iterator = hTTPInputSource.getHTTPRequestProperties();
            while (iterator.hasNext()) {
              Map.Entry entry = iterator.next();
              httpURLConnection.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
            } 
            bool2 = hTTPInputSource.getFollowHTTPRedirects();
            if (!bool2)
              setInstanceFollowRedirects(httpURLConnection, bool2); 
          } 
          inputStream = uRLConnection.getInputStream();
          if (bool2) {
            String str = uRLConnection.getURL().toString();
            if (!str.equals(str5)) {
              str2 = str;
              str5 = str;
            } 
          } 
        } 
      } 
      inputStream = new RewindableInputStream(this, inputStream);
      if (str4 == null) {
        byte[] arrayOfByte = new byte[4];
        byte b;
        for (b = 0; b < 4; b++)
          arrayOfByte[b] = (byte)inputStream.read(); 
        if (b == 4) {
          Object[] arrayOfObject = getEncodingName(arrayOfByte, b);
          str4 = (String)arrayOfObject[0];
          bool1 = (Boolean)arrayOfObject[1];
          inputStream.reset();
          if (b > 2 && str4.equals("UTF-8")) {
            int i = arrayOfByte[0] & 0xFF;
            int j = arrayOfByte[1] & 0xFF;
            int k = arrayOfByte[2] & 0xFF;
            if (i == 239 && j == 187 && k == 191)
              inputStream.skip(3L); 
          } 
          reader = createReader(inputStream, str4, bool1);
        } else {
          reader = createReader(inputStream, str4, bool1);
        } 
      } else {
        str4 = str4.toUpperCase(Locale.ENGLISH);
        if (str4.equals("UTF-8")) {
          int[] arrayOfInt = new int[3];
          byte b;
          for (b = 0; b < 3; b++) {
            arrayOfInt[b] = inputStream.read();
            if (arrayOfInt[b] == -1)
              break; 
          } 
          if (b == 3) {
            if (arrayOfInt[0] != 239 || arrayOfInt[1] != 187 || arrayOfInt[2] != 191)
              inputStream.reset(); 
          } else {
            inputStream.reset();
          } 
          reader = createReader(inputStream, str4, bool1);
        } else if (str4.equals("UTF-16")) {
          int[] arrayOfInt = new int[4];
          byte b;
          for (b = 0; b < 4; b++) {
            arrayOfInt[b] = inputStream.read();
            if (arrayOfInt[b] == -1)
              break; 
          } 
          inputStream.reset();
          String str = "UTF-16";
          if (b >= 2) {
            int i = arrayOfInt[0];
            int j = arrayOfInt[1];
            if (i == 254 && j == 255) {
              str = "UTF-16BE";
              bool1 = Boolean.TRUE;
            } else if (i == 255 && j == 254) {
              str = "UTF-16LE";
              bool1 = Boolean.FALSE;
            } else if (b == 4) {
              int k = arrayOfInt[2];
              int m = arrayOfInt[3];
              if (i == 0 && j == 60 && k == 0 && m == 63) {
                str = "UTF-16BE";
                bool1 = Boolean.TRUE;
              } 
              if (i == 60 && j == 0 && k == 63 && m == 0) {
                str = "UTF-16LE";
                bool1 = Boolean.FALSE;
              } 
            } 
          } 
          reader = createReader(inputStream, str, bool1);
        } else if (str4.equals("ISO-10646-UCS-4")) {
          int[] arrayOfInt = new int[4];
          byte b;
          for (b = 0; b < 4; b++) {
            arrayOfInt[b] = inputStream.read();
            if (arrayOfInt[b] == -1)
              break; 
          } 
          inputStream.reset();
          if (b == 4)
            if (arrayOfInt[0] == 0 && arrayOfInt[1] == 0 && arrayOfInt[2] == 0 && arrayOfInt[3] == 60) {
              bool1 = Boolean.TRUE;
            } else if (arrayOfInt[0] == 60 && arrayOfInt[1] == 0 && arrayOfInt[2] == 0 && arrayOfInt[3] == 0) {
              bool1 = Boolean.FALSE;
            }  
          reader = createReader(inputStream, str4, bool1);
        } else if (str4.equals("ISO-10646-UCS-2")) {
          int[] arrayOfInt = new int[4];
          byte b;
          for (b = 0; b < 4; b++) {
            arrayOfInt[b] = inputStream.read();
            if (arrayOfInt[b] == -1)
              break; 
          } 
          inputStream.reset();
          if (b == 4)
            if (arrayOfInt[0] == 0 && arrayOfInt[1] == 60 && arrayOfInt[2] == 0 && arrayOfInt[3] == 63) {
              bool1 = Boolean.TRUE;
            } else if (arrayOfInt[0] == 60 && arrayOfInt[1] == 0 && arrayOfInt[2] == 63 && arrayOfInt[3] == 0) {
              bool1 = Boolean.FALSE;
            }  
          reader = createReader(inputStream, str4, bool1);
        } else {
          reader = createReader(inputStream, str4, bool1);
        } 
      } 
    } 
    this.fReaderStack.push(reader);
    if (this.fCurrentEntity != null)
      this.fEntityStack.push(this.fCurrentEntity); 
    this.fCurrentEntity = new ScannedEntity(this, paramString, (XMLResourceIdentifier)new XMLResourceIdentifierImpl(str1, str2, str3, str5), inputStream, reader, this.fTempByteBuffer, str4, paramBoolean1, false, paramBoolean2);
    this.fCurrentEntity.setEncodingExternallySpecified(bool);
    this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
    this.fResourceIdentifier.setValues(str1, str2, str3, str5);
    return str4;
  }
  
  public void setScannerVersion(short paramShort) {
    if (paramShort == 1) {
      if (this.fXML10EntityScanner == null)
        this.fXML10EntityScanner = new XMLEntityScanner(); 
      this.fXML10EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
      this.fEntityScanner = this.fXML10EntityScanner;
      this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
    } else {
      if (this.fXML11EntityScanner == null)
        this.fXML11EntityScanner = new XML11EntityScanner(); 
      this.fXML11EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
      this.fEntityScanner = this.fXML11EntityScanner;
      this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
    } 
  }
  
  public XMLEntityScanner getEntityScanner() {
    if (this.fEntityScanner == null) {
      if (this.fXML10EntityScanner == null)
        this.fXML10EntityScanner = new XMLEntityScanner(); 
      this.fXML10EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
      this.fEntityScanner = this.fXML10EntityScanner;
    } 
    return this.fEntityScanner;
  }
  
  public void closeReaders() {
    for (int i = this.fReaderStack.size() - 1; i >= 0; i--) {
      try {
        ((Reader)this.fReaderStack.pop()).close();
      } catch (IOException iOException) {}
    } 
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException {
    boolean bool;
    try {
      bool = paramXMLComponentManager.getFeature("http://apache.org/xml/features/internal/parser-settings");
    } catch (XMLConfigurationException xMLConfigurationException) {
      bool = true;
    } 
    if (!bool) {
      reset();
      return;
    } 
    try {
      this.fValidation = paramXMLComponentManager.getFeature("http://xml.org/sax/features/validation");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fValidation = false;
    } 
    try {
      this.fExternalGeneralEntities = paramXMLComponentManager.getFeature("http://xml.org/sax/features/external-general-entities");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fExternalGeneralEntities = true;
    } 
    try {
      this.fExternalParameterEntities = paramXMLComponentManager.getFeature("http://xml.org/sax/features/external-parameter-entities");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fExternalParameterEntities = true;
    } 
    try {
      this.fAllowJavaEncodings = paramXMLComponentManager.getFeature("http://apache.org/xml/features/allow-java-encodings");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fAllowJavaEncodings = false;
    } 
    try {
      this.fWarnDuplicateEntityDef = paramXMLComponentManager.getFeature("http://apache.org/xml/features/warn-on-duplicate-entitydef");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fWarnDuplicateEntityDef = false;
    } 
    try {
      this.fStrictURI = paramXMLComponentManager.getFeature("http://apache.org/xml/features/standard-uri-conformant");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fStrictURI = false;
    } 
    this.fSymbolTable = (SymbolTable)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    this.fErrorReporter = (XMLErrorReporter)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    try {
      this.fEntityResolver = (XMLEntityResolver)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fEntityResolver = null;
    } 
    try {
      this.fValidationManager = (ValidationManager)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fValidationManager = null;
    } 
    try {
      this.fSecurityManager = (SecurityManager)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fSecurityManager = null;
    } 
    reset();
  }
  
  public void reset() {
    this.fEntityExpansionLimit = (this.fSecurityManager != null) ? this.fSecurityManager.getEntityExpansionLimit() : 0;
    this.fStandalone = false;
    this.fHasPEReferences = false;
    this.fEntities.clear();
    this.fEntityStack.removeAllElements();
    this.fEntityExpansionCount = 0;
    this.fCurrentEntity = null;
    if (this.fXML10EntityScanner != null)
      this.fXML10EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter); 
    if (this.fXML11EntityScanner != null)
      this.fXML11EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter); 
    if (this.fDeclaredEntities != null) {
      Enumeration enumeration = this.fDeclaredEntities.keys();
      while (enumeration.hasMoreElements()) {
        Object object = enumeration.nextElement();
        Object object1 = this.fDeclaredEntities.get(object);
        this.fEntities.put(object, object1);
      } 
    } 
    this.fEntityHandler = null;
  }
  
  public String[] getRecognizedFeatures() {
    return (String[])RECOGNIZED_FEATURES.clone();
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/features/")) {
      int i = paramString.length() - "http://apache.org/xml/features/".length();
      if (i == "allow-java-encodings".length() && paramString.endsWith("allow-java-encodings"))
        this.fAllowJavaEncodings = paramBoolean; 
    } 
  }
  
  public String[] getRecognizedProperties() {
    return (String[])RECOGNIZED_PROPERTIES.clone();
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/properties/")) {
      int i = paramString.length() - "http://apache.org/xml/properties/".length();
      if (i == "internal/symbol-table".length() && paramString.endsWith("internal/symbol-table")) {
        this.fSymbolTable = (SymbolTable)paramObject;
        return;
      } 
      if (i == "internal/error-reporter".length() && paramString.endsWith("internal/error-reporter")) {
        this.fErrorReporter = (XMLErrorReporter)paramObject;
        return;
      } 
      if (i == "internal/entity-resolver".length() && paramString.endsWith("internal/entity-resolver")) {
        this.fEntityResolver = (XMLEntityResolver)paramObject;
        return;
      } 
      if (i == "input-buffer-size".length() && paramString.endsWith("input-buffer-size")) {
        Integer integer = (Integer)paramObject;
        if (integer != null && integer.intValue() > 64) {
          this.fBufferSize = integer.intValue();
          this.fEntityScanner.setBufferSize(this.fBufferSize);
          this.fByteBufferPool.setBufferSize(this.fBufferSize);
          this.fCharacterBufferPool.setExternalBufferSize(this.fBufferSize);
        } 
      } 
      if (i == "security-manager".length() && paramString.endsWith("security-manager")) {
        this.fSecurityManager = (SecurityManager)paramObject;
        this.fEntityExpansionLimit = (this.fSecurityManager != null) ? this.fSecurityManager.getEntityExpansionLimit() : 0;
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
  
  private static synchronized URI getUserDir() throws URI.MalformedURIException {
    String str = "";
    try {
      str = AccessController.<String>doPrivileged(GET_USER_DIR_SYSTEM_PROPERTY);
    } catch (SecurityException securityException) {}
    if (str.length() == 0)
      return new URI("file", "", "", null, null); 
    if (gUserDirURI != null && str.equals(gUserDir))
      return gUserDirURI; 
    gUserDir = str;
    char c = File.separatorChar;
    str = str.replace(c, '/');
    int i = str.length();
    StringBuffer stringBuffer = new StringBuffer(i * 3);
    if (i >= 2 && str.charAt(1) == ':') {
      char c1 = Character.toUpperCase(str.charAt(0));
      if (c1 >= 'A' && c1 <= 'Z')
        stringBuffer.append('/'); 
    } 
    byte b;
    for (b = 0; b < i; b++) {
      char c1 = str.charAt(b);
      if (c1 >= '')
        break; 
      if (gNeedEscaping[c1]) {
        stringBuffer.append('%');
        stringBuffer.append(gAfterEscaping1[c1]);
        stringBuffer.append(gAfterEscaping2[c1]);
      } else {
        stringBuffer.append((char)c1);
      } 
    } 
    if (b < i) {
      byte[] arrayOfByte = null;
      try {
        arrayOfByte = str.substring(b).getBytes("UTF-8");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        return new URI("file", "", str, null, null);
      } 
      i = arrayOfByte.length;
      for (b = 0; b < i; b++) {
        byte b1 = arrayOfByte[b];
        if (b1 < 0) {
          int j = b1 + 256;
          stringBuffer.append('%');
          stringBuffer.append(gHexChs[j >> 4]);
          stringBuffer.append(gHexChs[j & 0xF]);
        } else if (gNeedEscaping[b1]) {
          stringBuffer.append('%');
          stringBuffer.append(gAfterEscaping1[b1]);
          stringBuffer.append(gAfterEscaping2[b1]);
        } else {
          stringBuffer.append((char)b1);
        } 
      } 
    } 
    if (!str.endsWith("/"))
      stringBuffer.append('/'); 
    gUserDirURI = new URI("file", "", stringBuffer.toString(), null, null);
    return gUserDirURI;
  }
  
  public static void absolutizeAgainstUserDir(URI paramURI) throws URI.MalformedURIException {
    paramURI.absolutize(getUserDir());
  }
  
  public static String expandSystemId(String paramString1, String paramString2, boolean paramBoolean) throws URI.MalformedURIException {
    if (paramString1 == null)
      return null; 
    if (paramBoolean)
      return expandSystemIdStrictOn(paramString1, paramString2); 
    try {
      return expandSystemIdStrictOff(paramString1, paramString2);
    } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
      if (paramString1.length() == 0)
        return paramString1; 
      String str = fixURI(paramString1);
      URI uRI1 = null;
      URI uRI2 = null;
      try {
        if (paramString2 == null || paramString2.length() == 0 || paramString2.equals(paramString1)) {
          uRI1 = getUserDir();
        } else {
          try {
            uRI1 = new URI(fixURI(paramString2).trim());
          } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException1) {
            if (paramString2.indexOf(':') != -1) {
              uRI1 = new URI("file", "", fixURI(paramString2).trim(), null, null);
            } else {
              uRI1 = new URI(getUserDir(), fixURI(paramString2));
            } 
          } 
        } 
        uRI2 = new URI(uRI1, str.trim());
      } catch (Exception exception) {}
      return (uRI2 == null) ? paramString1 : uRI2.toString();
    } 
  }
  
  private static String expandSystemIdStrictOn(String paramString1, String paramString2) throws URI.MalformedURIException {
    URI uRI1 = new URI(paramString1, true);
    if (uRI1.isAbsoluteURI())
      return paramString1; 
    URI uRI2 = null;
    if (paramString2 == null || paramString2.length() == 0) {
      uRI2 = getUserDir();
    } else {
      uRI2 = new URI(paramString2, true);
      if (!uRI2.isAbsoluteURI())
        uRI2.absolutize(getUserDir()); 
    } 
    uRI1.absolutize(uRI2);
    return uRI1.toString();
  }
  
  private static String expandSystemIdStrictOff(String paramString1, String paramString2) throws URI.MalformedURIException {
    URI uRI1 = new URI(paramString1, true);
    if (uRI1.isAbsoluteURI()) {
      if (uRI1.getScheme().length() > 1)
        return paramString1; 
      throw new URI.MalformedURIException();
    } 
    URI uRI2 = null;
    if (paramString2 == null || paramString2.length() == 0) {
      uRI2 = getUserDir();
    } else {
      uRI2 = new URI(paramString2, true);
      if (!uRI2.isAbsoluteURI())
        uRI2.absolutize(getUserDir()); 
    } 
    uRI1.absolutize(uRI2);
    return uRI1.toString();
  }
  
  public static void setInstanceFollowRedirects(HttpURLConnection paramHttpURLConnection, boolean paramBoolean) {
    try {
      Method method = HttpURLConnection.class.getMethod("setInstanceFollowRedirects", new Class[] { boolean.class });
      method.invoke(paramHttpURLConnection, new Object[] { paramBoolean ? Boolean.TRUE : Boolean.FALSE });
    } catch (Exception exception) {}
  }
  
  public static OutputStream createOutputStream(String paramString) throws IOException {
    String str1 = expandSystemId(paramString, null, true);
    URL uRL = new URL((str1 != null) ? str1 : paramString);
    OutputStream outputStream = null;
    String str2 = uRL.getProtocol();
    String str3 = uRL.getHost();
    if (str2.equals("file") && (str3 == null || str3.length() == 0 || str3.equals("localhost"))) {
      outputStream = new FileOutputStream(getPathWithoutEscapes(uRL.getPath()));
    } else {
      URLConnection uRLConnection = uRL.openConnection();
      uRLConnection.setDoInput(false);
      uRLConnection.setDoOutput(true);
      uRLConnection.setUseCaches(false);
      if (uRLConnection instanceof HttpURLConnection) {
        HttpURLConnection httpURLConnection = (HttpURLConnection)uRLConnection;
        httpURLConnection.setRequestMethod("PUT");
      } 
      outputStream = uRLConnection.getOutputStream();
    } 
    return outputStream;
  }
  
  private static String getPathWithoutEscapes(String paramString) {
    if (paramString != null && paramString.length() != 0 && paramString.indexOf('%') != -1) {
      StringTokenizer stringTokenizer = new StringTokenizer(paramString, "%");
      StringBuffer stringBuffer = new StringBuffer(paramString.length());
      int i = stringTokenizer.countTokens();
      stringBuffer.append(stringTokenizer.nextToken());
      for (byte b = 1; b < i; b++) {
        String str = stringTokenizer.nextToken();
        stringBuffer.append((char)Integer.valueOf(str.substring(0, 2), 16).intValue());
        stringBuffer.append(str.substring(2));
      } 
      return stringBuffer.toString();
    } 
    return paramString;
  }
  
  void endEntity() throws XNIException {
    if (this.fEntityHandler != null)
      this.fEntityHandler.endEntity(this.fCurrentEntity.name, null); 
    try {
      this.fCurrentEntity.reader.close();
    } catch (IOException iOException) {}
    if (!this.fReaderStack.isEmpty())
      this.fReaderStack.pop(); 
    this.fCharacterBufferPool.returnBuffer(this.fCurrentEntity.fCharacterBuffer);
    if (this.fCurrentEntity.fByteBuffer != null)
      this.fByteBufferPool.returnBuffer(this.fCurrentEntity.fByteBuffer); 
    this.fCurrentEntity = (this.fEntityStack.size() > 0) ? this.fEntityStack.pop() : null;
    this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
  }
  
  protected Object[] getEncodingName(byte[] paramArrayOfbyte, int paramInt) {
    if (paramInt < 2)
      return new Object[] { "UTF-8", null }; 
    int i = paramArrayOfbyte[0] & 0xFF;
    int j = paramArrayOfbyte[1] & 0xFF;
    if (i == 254 && j == 255)
      return new Object[] { "UTF-16BE", Boolean.TRUE }; 
    if (i == 255 && j == 254)
      return new Object[] { "UTF-16LE", Boolean.FALSE }; 
    if (paramInt < 3)
      return new Object[] { "UTF-8", null }; 
    int k = paramArrayOfbyte[2] & 0xFF;
    if (i == 239 && j == 187 && k == 191)
      return new Object[] { "UTF-8", null }; 
    if (paramInt < 4)
      return new Object[] { "UTF-8", null }; 
    int m = paramArrayOfbyte[3] & 0xFF;
    return (i == 0 && j == 0 && k == 0 && m == 60) ? new Object[] { "ISO-10646-UCS-4", Boolean.TRUE } : ((i == 60 && j == 0 && k == 0 && m == 0) ? new Object[] { "ISO-10646-UCS-4", Boolean.FALSE } : ((i == 0 && j == 0 && k == 60 && m == 0) ? new Object[] { "ISO-10646-UCS-4", null } : ((i == 0 && j == 60 && k == 0 && m == 0) ? new Object[] { "ISO-10646-UCS-4", null } : ((i == 0 && j == 60 && k == 0 && m == 63) ? new Object[] { "UTF-16BE", Boolean.TRUE } : ((i == 60 && j == 0 && k == 63 && m == 0) ? new Object[] { "UTF-16LE", Boolean.FALSE } : ((i == 76 && j == 111 && k == 167 && m == 148) ? new Object[] { "CP037", null } : new Object[] { "UTF-8", null }))))));
  }
  
  protected Reader createReader(InputStream paramInputStream, String paramString, Boolean paramBoolean) throws IOException {
    if (paramString == "UTF-8" || paramString == null) {
      if (this.fTempByteBuffer == null)
        this.fTempByteBuffer = this.fByteBufferPool.getBuffer(); 
      return (Reader)new UTF8Reader(paramInputStream, this.fTempByteBuffer, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    } 
    String str1 = paramString.toUpperCase(Locale.ENGLISH);
    if (str1.equals("UTF-8")) {
      if (this.fTempByteBuffer == null)
        this.fTempByteBuffer = this.fByteBufferPool.getBuffer(); 
      return (Reader)new UTF8Reader(paramInputStream, this.fTempByteBuffer, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    } 
    if (str1.equals("ISO-10646-UCS-4")) {
      if (paramBoolean != null) {
        boolean bool = paramBoolean.booleanValue();
        return (Reader)(bool ? new UCSReader(paramInputStream, (short)8) : new UCSReader(paramInputStream, (short)4));
      } 
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[] { paramString }, (short)2);
    } 
    if (str1.equals("ISO-10646-UCS-2")) {
      if (paramBoolean != null) {
        boolean bool = paramBoolean.booleanValue();
        return (Reader)(bool ? new UCSReader(paramInputStream, (short)2) : new UCSReader(paramInputStream, (short)1));
      } 
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[] { paramString }, (short)2);
    } 
    boolean bool1 = XMLChar.isValidIANAEncoding(paramString);
    boolean bool2 = XMLChar.isValidJavaEncoding(paramString);
    if (!bool1 || (this.fAllowJavaEncodings && !bool2)) {
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[] { paramString }, (short)2);
      return (Reader)new Latin1Reader(paramInputStream, this.fBufferSize);
    } 
    String str2 = EncodingMap.getIANA2JavaMapping(str1);
    if (str2 == null) {
      if (this.fAllowJavaEncodings) {
        str2 = paramString;
      } else {
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[] { paramString }, (short)2);
        if (this.fTempByteBuffer == null)
          this.fTempByteBuffer = this.fByteBufferPool.getBuffer(); 
        return (Reader)new Latin1Reader(paramInputStream, this.fTempByteBuffer);
      } 
    } else {
      if (str2.equals("ASCII")) {
        if (this.fTempByteBuffer == null)
          this.fTempByteBuffer = this.fByteBufferPool.getBuffer(); 
        return (Reader)new ASCIIReader(paramInputStream, this.fTempByteBuffer, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
      } 
      if (str2.equals("ISO8859_1")) {
        if (this.fTempByteBuffer == null)
          this.fTempByteBuffer = this.fByteBufferPool.getBuffer(); 
        return (Reader)new Latin1Reader(paramInputStream, this.fTempByteBuffer);
      } 
    } 
    return new InputStreamReader(paramInputStream, str2);
  }
  
  protected static String fixURI(String paramString) {
    paramString = paramString.replace(File.separatorChar, '/');
    StringBuffer stringBuffer = null;
    if (paramString.length() >= 2) {
      char c = paramString.charAt(1);
      if (c == ':') {
        char c1 = Character.toUpperCase(paramString.charAt(0));
        if (c1 >= 'A' && c1 <= 'Z') {
          stringBuffer = new StringBuffer(paramString.length() + 8);
          stringBuffer.append("file:///");
        } 
      } else if (c == '/' && paramString.charAt(0) == '/') {
        stringBuffer = new StringBuffer(paramString.length() + 5);
        stringBuffer.append("file:");
      } 
    } 
    int i = paramString.indexOf(' ');
    if (i < 0) {
      if (stringBuffer != null) {
        stringBuffer.append(paramString);
        paramString = stringBuffer.toString();
      } 
    } else {
      if (stringBuffer == null)
        stringBuffer = new StringBuffer(paramString.length()); 
      for (byte b = 0; b < i; b++)
        stringBuffer.append(paramString.charAt(b)); 
      stringBuffer.append("%20");
      for (int j = i + 1; j < paramString.length(); j++) {
        if (paramString.charAt(j) == ' ') {
          stringBuffer.append("%20");
        } else {
          stringBuffer.append(paramString.charAt(j));
        } 
      } 
      paramString = stringBuffer.toString();
    } 
    return paramString;
  }
  
  Hashtable getDeclaredEntities() {
    return this.fEntities;
  }
  
  static final void print(ScannedEntity paramScannedEntity) {}
  
  static {
    for (byte b = 0; b <= 31; b++) {
      gNeedEscaping[b] = true;
      gAfterEscaping1[b] = gHexChs[b >> 4];
      gAfterEscaping2[b] = gHexChs[b & 0xF];
    } 
    gNeedEscaping[127] = true;
    gAfterEscaping1[127] = '7';
    gAfterEscaping2[127] = 'F';
    for (char c : new char[] { 
        ' ', '<', '>', '#', '%', '"', '{', '}', '|', '\\', 
        '^', '~', '[', ']', '`' }) {
      gNeedEscaping[c] = true;
      gAfterEscaping1[c] = gHexChs[c >> 4];
      gAfterEscaping2[c] = gHexChs[c & 0xF];
    } 
  }
  
  protected final class RewindableInputStream extends InputStream {
    private InputStream fInputStream;
    
    private byte[] fData;
    
    private int fStartOffset;
    
    private int fEndOffset;
    
    private int fOffset;
    
    private int fLength;
    
    private int fMark;
    
    private final XMLEntityManager this$0;
    
    public RewindableInputStream(XMLEntityManager this$0, InputStream param1InputStream) {
      this.this$0 = this$0;
      this.fData = new byte[64];
      this.fInputStream = param1InputStream;
      this.fStartOffset = 0;
      this.fEndOffset = -1;
      this.fOffset = 0;
      this.fLength = 0;
      this.fMark = 0;
    }
    
    public void setStartOffset(int param1Int) {
      this.fStartOffset = param1Int;
    }
    
    public void rewind() {
      this.fOffset = this.fStartOffset;
    }
    
    public int read() throws IOException {
      int i = 0;
      if (this.fOffset < this.fLength)
        return this.fData[this.fOffset++] & 0xFF; 
      if (this.fOffset == this.fEndOffset)
        return -1; 
      if (this.fOffset == this.fData.length) {
        byte[] arrayOfByte = new byte[this.fOffset << 1];
        System.arraycopy(this.fData, 0, arrayOfByte, 0, this.fOffset);
        this.fData = arrayOfByte;
      } 
      i = this.fInputStream.read();
      if (i == -1) {
        this.fEndOffset = this.fOffset;
        return -1;
      } 
      this.fData[this.fLength++] = (byte)i;
      this.fOffset++;
      return i & 0xFF;
    }
    
    public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      int i = this.fLength - this.fOffset;
      if (i == 0) {
        if (this.fOffset == this.fEndOffset)
          return -1; 
        if (this.this$0.fCurrentEntity.mayReadChunks)
          return this.fInputStream.read(param1ArrayOfbyte, param1Int1, param1Int2); 
        int j = read();
        if (j == -1) {
          this.fEndOffset = this.fOffset;
          return -1;
        } 
        param1ArrayOfbyte[param1Int1] = (byte)j;
        return 1;
      } 
      if (param1Int2 < i) {
        if (param1Int2 <= 0)
          return 0; 
      } else {
        param1Int2 = i;
      } 
      if (param1ArrayOfbyte != null)
        System.arraycopy(this.fData, this.fOffset, param1ArrayOfbyte, param1Int1, param1Int2); 
      this.fOffset += param1Int2;
      return param1Int2;
    }
    
    public long skip(long param1Long) throws IOException {
      if (param1Long <= 0L)
        return 0L; 
      int i = this.fLength - this.fOffset;
      if (i == 0)
        return (this.fOffset == this.fEndOffset) ? 0L : this.fInputStream.skip(param1Long); 
      if (param1Long <= i) {
        this.fOffset = (int)(this.fOffset + param1Long);
        return param1Long;
      } 
      this.fOffset += i;
      if (this.fOffset == this.fEndOffset)
        return i; 
      param1Long -= i;
      return this.fInputStream.skip(param1Long) + i;
    }
    
    public int available() throws IOException {
      int i = this.fLength - this.fOffset;
      return (i == 0) ? ((this.fOffset == this.fEndOffset) ? -1 : (this.this$0.fCurrentEntity.mayReadChunks ? this.fInputStream.available() : 0)) : i;
    }
    
    public void mark(int param1Int) {
      this.fMark = this.fOffset;
    }
    
    public void reset() {
      this.fOffset = this.fMark;
    }
    
    public boolean markSupported() {
      return true;
    }
    
    public void close() throws IOException {
      if (this.fInputStream != null) {
        this.fInputStream.close();
        this.fInputStream = null;
      } 
    }
  }
  
  private static final class CharacterBufferPool {
    private static final int DEFAULT_POOL_SIZE = 3;
    
    private XMLEntityManager.CharacterBuffer[] fInternalBufferPool;
    
    private XMLEntityManager.CharacterBuffer[] fExternalBufferPool;
    
    private int fExternalBufferSize;
    
    private int fInternalBufferSize;
    
    private int fPoolSize;
    
    private int fInternalTop;
    
    private int fExternalTop;
    
    public CharacterBufferPool(int param1Int1, int param1Int2) {
      this(3, param1Int1, param1Int2);
    }
    
    public CharacterBufferPool(int param1Int1, int param1Int2, int param1Int3) {
      this.fExternalBufferSize = param1Int2;
      this.fInternalBufferSize = param1Int3;
      this.fPoolSize = param1Int1;
      init();
    }
    
    private void init() {
      this.fInternalBufferPool = new XMLEntityManager.CharacterBuffer[this.fPoolSize];
      this.fExternalBufferPool = new XMLEntityManager.CharacterBuffer[this.fPoolSize];
      this.fInternalTop = -1;
      this.fExternalTop = -1;
    }
    
    public XMLEntityManager.CharacterBuffer getBuffer(boolean param1Boolean) {
      return param1Boolean ? ((this.fExternalTop > -1) ? this.fExternalBufferPool[this.fExternalTop--] : new XMLEntityManager.CharacterBuffer(true, this.fExternalBufferSize)) : ((this.fInternalTop > -1) ? this.fInternalBufferPool[this.fInternalTop--] : new XMLEntityManager.CharacterBuffer(false, this.fInternalBufferSize));
    }
    
    public void returnBuffer(XMLEntityManager.CharacterBuffer param1CharacterBuffer) {
      if (param1CharacterBuffer.isExternal) {
        if (this.fExternalTop < this.fExternalBufferPool.length - 1)
          this.fExternalBufferPool[++this.fExternalTop] = param1CharacterBuffer; 
      } else if (this.fInternalTop < this.fInternalBufferPool.length - 1) {
        this.fInternalBufferPool[++this.fInternalTop] = param1CharacterBuffer;
      } 
    }
    
    public void setExternalBufferSize(int param1Int) {
      this.fExternalBufferSize = param1Int;
      this.fExternalBufferPool = new XMLEntityManager.CharacterBuffer[this.fPoolSize];
      this.fExternalTop = -1;
    }
  }
  
  private static final class CharacterBuffer {
    private final char[] ch;
    
    private final boolean isExternal;
    
    public CharacterBuffer(boolean param1Boolean, int param1Int) {
      this.isExternal = param1Boolean;
      this.ch = new char[param1Int];
    }
  }
  
  private static final class ByteBufferPool {
    private static final int DEFAULT_POOL_SIZE = 3;
    
    private int fPoolSize;
    
    private int fBufferSize;
    
    private byte[][] fByteBufferPool;
    
    private int fDepth;
    
    public ByteBufferPool(int param1Int) {
      this(3, param1Int);
    }
    
    public ByteBufferPool(int param1Int1, int param1Int2) {
      this.fPoolSize = param1Int1;
      this.fBufferSize = param1Int2;
      this.fByteBufferPool = new byte[this.fPoolSize][];
      this.fDepth = 0;
    }
    
    public byte[] getBuffer() {
      return (this.fDepth > 0) ? this.fByteBufferPool[--this.fDepth] : new byte[this.fBufferSize];
    }
    
    public void returnBuffer(byte[] param1ArrayOfbyte) {
      if (this.fDepth < this.fByteBufferPool.length)
        this.fByteBufferPool[this.fDepth++] = param1ArrayOfbyte; 
    }
    
    public void setBufferSize(int param1Int) {
      this.fBufferSize = param1Int;
      this.fByteBufferPool = new byte[this.fPoolSize][];
      this.fDepth = 0;
    }
  }
  
  public class ScannedEntity extends Entity {
    public InputStream stream;
    
    public Reader reader;
    
    public XMLResourceIdentifier entityLocation;
    
    public int lineNumber;
    
    public int columnNumber;
    
    public String encoding;
    
    boolean externallySpecifiedEncoding;
    
    public String xmlVersion;
    
    public boolean literal;
    
    public boolean isExternal;
    
    public char[] ch;
    
    public int position;
    
    public int baseCharOffset;
    
    public int startPosition;
    
    public int count;
    
    public boolean mayReadChunks;
    
    private XMLEntityManager.CharacterBuffer fCharacterBuffer;
    
    private byte[] fByteBuffer;
    
    private final XMLEntityManager this$0;
    
    public ScannedEntity(XMLEntityManager this$0, String param1String1, XMLResourceIdentifier param1XMLResourceIdentifier, InputStream param1InputStream, Reader param1Reader, byte[] param1ArrayOfbyte, String param1String2, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3) {
      super(param1String1, this$0.fInExternalSubset);
      this.this$0 = this$0;
      this.lineNumber = 1;
      this.columnNumber = 1;
      this.externallySpecifiedEncoding = false;
      this.xmlVersion = "1.0";
      this.ch = null;
      this.entityLocation = param1XMLResourceIdentifier;
      this.stream = param1InputStream;
      this.reader = param1Reader;
      this.encoding = param1String2;
      this.literal = param1Boolean1;
      this.mayReadChunks = param1Boolean2;
      this.isExternal = param1Boolean3;
      this.fCharacterBuffer = this$0.fCharacterBufferPool.getBuffer(param1Boolean3);
      this.ch = this.fCharacterBuffer.ch;
      this.fByteBuffer = param1ArrayOfbyte;
    }
    
    public final boolean isExternal() {
      return this.isExternal;
    }
    
    public final boolean isUnparsed() {
      return false;
    }
    
    public void setReader(InputStream param1InputStream, String param1String, Boolean param1Boolean) throws IOException {
      this.this$0.fTempByteBuffer = this.fByteBuffer;
      this.reader = this.this$0.createReader(param1InputStream, param1String, param1Boolean);
      this.fByteBuffer = this.this$0.fTempByteBuffer;
    }
    
    public String getExpandedSystemId() {
      int i = this.this$0.fEntityStack.size();
      for (int j = i - 1; j >= 0; j--) {
        ScannedEntity scannedEntity = this.this$0.fEntityStack.elementAt(j);
        if (scannedEntity.entityLocation != null && scannedEntity.entityLocation.getExpandedSystemId() != null)
          return scannedEntity.entityLocation.getExpandedSystemId(); 
      } 
      return null;
    }
    
    public String getLiteralSystemId() {
      int i = this.this$0.fEntityStack.size();
      for (int j = i - 1; j >= 0; j--) {
        ScannedEntity scannedEntity = this.this$0.fEntityStack.elementAt(j);
        if (scannedEntity.entityLocation != null && scannedEntity.entityLocation.getLiteralSystemId() != null)
          return scannedEntity.entityLocation.getLiteralSystemId(); 
      } 
      return null;
    }
    
    public int getLineNumber() {
      int i = this.this$0.fEntityStack.size();
      for (int j = i - 1; j >= 0; j--) {
        ScannedEntity scannedEntity = this.this$0.fEntityStack.elementAt(j);
        if (scannedEntity.isExternal())
          return scannedEntity.lineNumber; 
      } 
      return -1;
    }
    
    public int getColumnNumber() {
      int i = this.this$0.fEntityStack.size();
      for (int j = i - 1; j >= 0; j--) {
        ScannedEntity scannedEntity = this.this$0.fEntityStack.elementAt(j);
        if (scannedEntity.isExternal())
          return scannedEntity.columnNumber; 
      } 
      return -1;
    }
    
    public int getCharacterOffset() {
      int i = this.this$0.fEntityStack.size();
      for (int j = i - 1; j >= 0; j--) {
        ScannedEntity scannedEntity = this.this$0.fEntityStack.elementAt(j);
        if (scannedEntity.isExternal())
          return scannedEntity.baseCharOffset + scannedEntity.position - scannedEntity.startPosition; 
      } 
      return -1;
    }
    
    public String getEncoding() {
      int i = this.this$0.fEntityStack.size();
      for (int j = i - 1; j >= 0; j--) {
        ScannedEntity scannedEntity = this.this$0.fEntityStack.elementAt(j);
        if (scannedEntity.isExternal())
          return scannedEntity.encoding; 
      } 
      return null;
    }
    
    public String getXMLVersion() {
      int i = this.this$0.fEntityStack.size();
      for (int j = i - 1; j >= 0; j--) {
        ScannedEntity scannedEntity = this.this$0.fEntityStack.elementAt(j);
        if (scannedEntity.isExternal())
          return scannedEntity.xmlVersion; 
      } 
      return null;
    }
    
    public boolean isEncodingExternallySpecified() {
      return this.externallySpecifiedEncoding;
    }
    
    public void setEncodingExternallySpecified(boolean param1Boolean) {
      this.externallySpecifiedEncoding = param1Boolean;
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("name=\"").append(this.name).append('"');
      stringBuffer.append(",ch=");
      stringBuffer.append(this.ch);
      stringBuffer.append(",position=").append(this.position);
      stringBuffer.append(",count=").append(this.count);
      stringBuffer.append(",baseCharOffset=").append(this.baseCharOffset);
      stringBuffer.append(",startPosition=").append(this.startPosition);
      return stringBuffer.toString();
    }
  }
  
  protected static class ExternalEntity extends Entity {
    public XMLResourceIdentifier entityLocation;
    
    public String notation;
    
    public ExternalEntity() {
      clear();
    }
    
    public ExternalEntity(String param1String1, XMLResourceIdentifier param1XMLResourceIdentifier, String param1String2, boolean param1Boolean) {
      super(param1String1, param1Boolean);
      this.entityLocation = param1XMLResourceIdentifier;
      this.notation = param1String2;
    }
    
    public final boolean isExternal() {
      return true;
    }
    
    public final boolean isUnparsed() {
      return (this.notation != null);
    }
    
    public void clear() {
      super.clear();
      this.entityLocation = null;
      this.notation = null;
    }
    
    public void setValues(XMLEntityManager.Entity param1Entity) {
      super.setValues(param1Entity);
      this.entityLocation = null;
      this.notation = null;
    }
    
    public void setValues(ExternalEntity param1ExternalEntity) {
      super.setValues(param1ExternalEntity);
      this.entityLocation = param1ExternalEntity.entityLocation;
      this.notation = param1ExternalEntity.notation;
    }
  }
  
  protected static class InternalEntity extends Entity {
    public String text;
    
    public InternalEntity() {
      clear();
    }
    
    public InternalEntity(String param1String1, String param1String2, boolean param1Boolean) {
      super(param1String1, param1Boolean);
      this.text = param1String2;
    }
    
    public final boolean isExternal() {
      return false;
    }
    
    public final boolean isUnparsed() {
      return false;
    }
    
    public void clear() {
      super.clear();
      this.text = null;
    }
    
    public void setValues(XMLEntityManager.Entity param1Entity) {
      super.setValues(param1Entity);
      this.text = null;
    }
    
    public void setValues(InternalEntity param1InternalEntity) {
      super.setValues(param1InternalEntity);
      this.text = param1InternalEntity.text;
    }
  }
  
  public static abstract class Entity {
    public String name;
    
    public boolean inExternalSubset;
    
    public Entity() {
      clear();
    }
    
    public Entity(String param1String, boolean param1Boolean) {
      this.name = param1String;
      this.inExternalSubset = param1Boolean;
    }
    
    public boolean isEntityDeclInExternalSubset() {
      return this.inExternalSubset;
    }
    
    public abstract boolean isExternal();
    
    public abstract boolean isUnparsed();
    
    public void clear() {
      this.name = null;
      this.inExternalSubset = false;
    }
    
    public void setValues(Entity param1Entity) {
      this.name = param1Entity.name;
      this.inExternalSubset = param1Entity.inExternalSubset;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XMLEntityManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */