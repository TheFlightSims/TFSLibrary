package org.hsqldb.jdbc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.xml.bind.util.JAXBResult;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.hsqldb.lib.ClosableByteArrayOutputStream;
import org.hsqldb.lib.StringConverter;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class JDBCSQLXML implements SQLXML {
  private static String domFeatures = "XML 3.0 Traversal +Events 2.0";
  
  private static DOMImplementation domImplementation;
  
  private static DOMImplementationRegistry domImplementationRegistry;
  
  private static ThreadPoolExecutor executorService;
  
  private static Transformer identityTransformer;
  
  private static TransformerFactory transformerFactory;
  
  private static final Charset utf8Charset;
  
  private static ArrayBlockingQueue<Runnable> workQueue;
  
  private SAX2DOMBuilder builder;
  
  private boolean closed;
  
  private volatile byte[] gzdata;
  
  private InputStream inputStream;
  
  private ClosableByteArrayOutputStream outputStream;
  
  private DOMResult domResult;
  
  private String publicId;
  
  private boolean readable;
  
  private String systemId;
  
  private boolean writable;
  
  protected JDBCSQLXML() {
    setReadable(false);
    setWritable(true);
  }
  
  protected JDBCSQLXML(byte[] paramArrayOfbyte) throws SQLException {
    this(paramArrayOfbyte, (String)null);
  }
  
  protected JDBCSQLXML(char[] paramArrayOfchar) throws SQLException {
    this(paramArrayOfchar, 0, paramArrayOfchar.length, (String)null);
  }
  
  protected JDBCSQLXML(Document paramDocument) throws SQLException {
    this(new DOMSource(paramDocument));
  }
  
  protected JDBCSQLXML(InputStream paramInputStream) throws SQLException {
    this(paramInputStream, (String)null);
  }
  
  protected JDBCSQLXML(Reader paramReader) throws SQLException {
    this(paramReader, (String)null);
  }
  
  public JDBCSQLXML(Source paramSource) throws SQLException {
    init(paramSource);
  }
  
  protected JDBCSQLXML(String paramString) throws SQLException {
    this(new StreamSource(new StringReader(paramString)));
  }
  
  protected JDBCSQLXML(byte[] paramArrayOfbyte, String paramString) throws SQLException {
    this(new StreamSource(new ByteArrayInputStream(paramArrayOfbyte), paramString));
  }
  
  protected JDBCSQLXML(char[] paramArrayOfchar, String paramString) throws SQLException {
    this(paramArrayOfchar, 0, paramArrayOfchar.length, paramString);
  }
  
  protected JDBCSQLXML(InputStream paramInputStream, String paramString) throws SQLException {
    this(new StreamSource(paramInputStream, paramString));
  }
  
  protected JDBCSQLXML(Reader paramReader, String paramString) throws SQLException {
    this(new StreamSource(paramReader, paramString));
  }
  
  protected JDBCSQLXML(String paramString1, String paramString2) throws SQLException {
    this(new StreamSource(new StringReader(paramString1), paramString2));
  }
  
  protected JDBCSQLXML(byte[] paramArrayOfbyte, boolean paramBoolean, String paramString1, String paramString2) throws SQLException {
    setGZipData(paramBoolean ? (byte[])paramArrayOfbyte.clone() : paramArrayOfbyte);
    this.systemId = paramString1;
    this.publicId = paramString2;
  }
  
  protected JDBCSQLXML(char[] paramArrayOfchar, int paramInt1, int paramInt2, String paramString) throws SQLException {
    this(new StreamSource(new CharArrayReader(paramArrayOfchar, paramInt1, paramInt2), paramString));
  }
  
  public void free() throws SQLException {
    close();
  }
  
  public synchronized InputStream getBinaryStream() throws SQLException {
    checkClosed();
    checkReadable();
    InputStream inputStream = getBinaryStreamImpl();
    setReadable(false);
    setWritable(false);
    return inputStream;
  }
  
  public synchronized OutputStream setBinaryStream() throws SQLException {
    checkClosed();
    checkWritable();
    OutputStream outputStream = setBinaryStreamImpl();
    setWritable(false);
    setReadable(true);
    return outputStream;
  }
  
  public synchronized Reader getCharacterStream() throws SQLException {
    checkClosed();
    checkReadable();
    Reader reader = getCharacterStreamImpl();
    setReadable(false);
    setWritable(false);
    return reader;
  }
  
  public synchronized Writer setCharacterStream() throws SQLException {
    checkClosed();
    checkWritable();
    Writer writer = setCharacterStreamImpl();
    setReadable(true);
    setWritable(false);
    return writer;
  }
  
  public synchronized String getString() throws SQLException {
    checkClosed();
    checkReadable();
    String str = getStringImpl();
    setReadable(false);
    setWritable(false);
    return str;
  }
  
  public synchronized void setString(String paramString) throws SQLException {
    if (paramString == null)
      throw JDBCUtil.nullArgument("value"); 
    checkWritable();
    setStringImpl(paramString);
    setReadable(true);
    setWritable(false);
  }
  
  public synchronized <T extends Source> T getSource(Class<T> paramClass) throws SQLException {
    checkClosed();
    checkReadable();
    T t = (T)getSourceImpl((Class)paramClass);
    setReadable(false);
    setWritable(false);
    return t;
  }
  
  public synchronized <T extends Result> T setResult(Class<T> paramClass) throws SQLException {
    checkClosed();
    checkWritable();
    T t = (T)createResult((Class)paramClass);
    setReadable(true);
    setWritable(false);
    return t;
  }
  
  protected static ExecutorService getExecutorService() {
    if (executorService == null) {
      boolean bool = true;
      byte b = 10;
      long l = 1L;
      TimeUnit timeUnit = TimeUnit.SECONDS;
      workQueue = new ArrayBlockingQueue<Runnable>(10);
      executorService = new ThreadPoolExecutor(bool, b, l, timeUnit, workQueue);
    } 
    return executorService;
  }
  
  protected static TransformerFactory getTransformerFactory() throws SQLException {
    if (transformerFactory == null)
      try {
        transformerFactory = TransformerFactory.newInstance();
      } catch (TransformerFactoryConfigurationError transformerFactoryConfigurationError) {
        throw Exceptions.transformFailed(transformerFactoryConfigurationError);
      }  
    return transformerFactory;
  }
  
  protected static Transformer getIdentityTransformer() throws SQLException {
    if (identityTransformer == null)
      try {
        identityTransformer = getTransformerFactory().newTransformer();
      } catch (TransformerConfigurationException transformerConfigurationException) {
        throw Exceptions.transformFailed(transformerConfigurationException);
      }  
    return identityTransformer;
  }
  
  protected static DOMImplementationRegistry getDOMImplementationRegistry() throws SQLException {
    if (domImplementationRegistry == null)
      try {
        domImplementationRegistry = DOMImplementationRegistry.newInstance();
      } catch (ClassCastException classCastException) {
        throw Exceptions.domInstantiation(classCastException);
      } catch (InstantiationException instantiationException) {
        throw Exceptions.domInstantiation(instantiationException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw Exceptions.domInstantiation(classNotFoundException);
      } catch (IllegalAccessException illegalAccessException) {
        throw Exceptions.domInstantiation(illegalAccessException);
      }  
    return domImplementationRegistry;
  }
  
  protected static DOMImplementation getDOMImplementation() throws SQLException {
    if (domImplementation == null)
      domImplementation = getDOMImplementationRegistry().getDOMImplementation(domFeatures); 
    if (domImplementation == null) {
      RuntimeException runtimeException = new RuntimeException("Not supported: " + domFeatures);
      throw Exceptions.domInstantiation(runtimeException);
    } 
    return domImplementation;
  }
  
  protected static Document createDocument(String paramString1, String paramString2, DocumentType paramDocumentType) throws SQLException {
    try {
      return getDOMImplementation().createDocument(paramString1, paramString2, paramDocumentType);
    } catch (DOMException dOMException) {
      throw Exceptions.domInstantiation(dOMException);
    } 
  }
  
  protected static Document createDocument() throws SQLException {
    return createDocument(null, null, null);
  }
  
  protected void init(Source paramSource) throws SQLException {
    GZIPOutputStream gZIPOutputStream;
    if (paramSource == null)
      throw JDBCUtil.nullArgument("source"); 
    Transformer transformer = getIdentityTransformer();
    StreamResult streamResult = new StreamResult();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
    } catch (IOException iOException) {
      throw Exceptions.transformFailed(iOException);
    } 
    streamResult.setOutputStream(gZIPOutputStream);
    try {
      transformer.transform(paramSource, streamResult);
    } catch (TransformerException transformerException) {
      throw Exceptions.transformFailed(transformerException);
    } 
    try {
      gZIPOutputStream.close();
    } catch (IOException iOException) {
      throw Exceptions.transformFailed(iOException);
    } 
    byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
    setGZipData(arrayOfByte);
    setReadable(true);
    setWritable(false);
  }
  
  protected void setGZipData(byte[] paramArrayOfbyte) throws SQLException {
    if (paramArrayOfbyte == null)
      throw JDBCUtil.nullArgument("data"); 
    this.gzdata = paramArrayOfbyte;
  }
  
  protected byte[] gZipData() {
    return this.gzdata;
  }
  
  protected byte[] getGZipData() throws SQLException {
    byte[] arrayOfByte = gZipData();
    if (arrayOfByte != null)
      return arrayOfByte; 
    if (this.domResult != null) {
      DOMSource dOMSource = new DOMSource(this.domResult.getNode(), this.domResult.getSystemId());
      OutputStream outputStream = setBinaryStreamImpl();
      StreamResult streamResult = new StreamResult(outputStream);
      try {
        identityTransformer.transform(dOMSource, streamResult);
      } catch (TransformerException transformerException) {
        throw Exceptions.transformFailed(transformerException);
      } 
      try {
        outputStream.close();
      } catch (IOException iOException) {
        throw Exceptions.transformFailed(iOException);
      } 
    } 
    if (this.outputStream == null)
      throw Exceptions.notReadable("No Data."); 
    if (!this.outputStream.isClosed())
      throw Exceptions.notReadable("Stream used for writing must be closed but is still open."); 
    if (this.outputStream.isFreed())
      throw Exceptions.notReadable("Stream used for writing was freed and is no longer valid."); 
    try {
      setGZipData(this.outputStream.toByteArray());
      return gZipData();
    } catch (IOException iOException) {
      throw Exceptions.notReadable();
    } finally {
      freeOutputStream();
    } 
  }
  
  protected synchronized void close() {
    this.closed = true;
    setReadable(false);
    setWritable(false);
    freeOutputStream();
    freeInputStream();
    freeDomResult();
    this.gzdata = null;
  }
  
  protected void freeInputStream() {
    if (this.inputStream != null)
      try {
        this.inputStream.close();
      } catch (IOException iOException) {
      
      } finally {
        this.inputStream = null;
      }  
  }
  
  protected void freeOutputStream() {
    if (this.outputStream != null) {
      try {
        this.outputStream.free();
      } catch (IOException iOException) {}
      this.outputStream = null;
    } 
  }
  
  protected synchronized void checkClosed() throws SQLException {
    if (this.closed)
      throw Exceptions.inFreedState(); 
  }
  
  protected synchronized void checkReadable() throws SQLException {
    if (!isReadable())
      throw Exceptions.notReadable(); 
  }
  
  protected synchronized void setReadable(boolean paramBoolean) {
    this.readable = paramBoolean;
  }
  
  protected synchronized void checkWritable() throws SQLException {
    if (!isWritable())
      throw Exceptions.notWritable(); 
  }
  
  protected synchronized void setWritable(boolean paramBoolean) {
    this.writable = paramBoolean;
  }
  
  public synchronized boolean isReadable() {
    return this.readable;
  }
  
  public synchronized boolean isWritable() {
    return this.writable;
  }
  
  protected InputStream getBinaryStreamImpl() throws SQLException {
    try {
      byte[] arrayOfByte = getGZipData();
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
      return new GZIPInputStream(byteArrayInputStream);
    } catch (IOException iOException) {
      throw Exceptions.transformFailed(iOException);
    } 
  }
  
  protected Reader getCharacterStreamImpl() throws SQLException {
    return new InputStreamReader(getBinaryStreamImpl());
  }
  
  protected String getStringImpl() throws SQLException {
    try {
      return StringConverter.inputStreamToString(getBinaryStreamImpl(), "US-ASCII");
    } catch (IOException iOException) {
      throw Exceptions.transformFailed(iOException);
    } 
  }
  
  protected OutputStream setBinaryStreamImpl() throws SQLException {
    this.outputStream = new ClosableByteArrayOutputStream();
    try {
      return new GZIPOutputStream((OutputStream)this.outputStream);
    } catch (IOException iOException) {
      this.outputStream = null;
      throw Exceptions.resultInstantiation(iOException);
    } 
  }
  
  protected Writer setCharacterStreamImpl() throws SQLException {
    return new OutputStreamWriter(setBinaryStreamImpl());
  }
  
  protected void setStringImpl(String paramString) throws SQLException {
    init(new StreamSource(new StringReader(paramString)));
  }
  
  protected <T extends Source> T getSourceImpl(Class<T> paramClass) throws SQLException {
    if (!JAXBSource.class.isAssignableFrom(paramClass)) {
      if (StreamSource.class.isAssignableFrom(paramClass))
        return createStreamSource(paramClass); 
      if (paramClass == null || DOMSource.class.isAssignableFrom(paramClass))
        return createDOMSource(paramClass); 
      if (SAXSource.class.isAssignableFrom(paramClass))
        return createSAXSource(paramClass); 
      if (StAXSource.class.isAssignableFrom(paramClass))
        return createStAXSource(paramClass); 
    } 
    throw JDBCUtil.invalidArgument("sourceClass: " + paramClass);
  }
  
  protected <T extends Source> T createStreamSource(Class<T> paramClass) throws SQLException {
    StreamSource streamSource = null;
    try {
      streamSource = (paramClass == null) ? new StreamSource() : (StreamSource)paramClass.newInstance();
    } catch (SecurityException securityException) {
      throw Exceptions.sourceInstantiation(securityException);
    } catch (InstantiationException instantiationException) {
      throw Exceptions.sourceInstantiation(instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      throw Exceptions.sourceInstantiation(illegalAccessException);
    } catch (ClassCastException classCastException) {
      throw Exceptions.sourceInstantiation(classCastException);
    } 
    Reader reader = getCharacterStreamImpl();
    streamSource.setReader(reader);
    return (T)streamSource;
  }
  
  protected <T extends Source> T createDOMSource(Class<T> paramClass) throws SQLException {
    DOMSource dOMSource = null;
    try {
      dOMSource = (paramClass == null) ? new DOMSource() : (DOMSource)paramClass.newInstance();
    } catch (SecurityException securityException) {
      throw Exceptions.sourceInstantiation(securityException);
    } catch (IllegalAccessException illegalAccessException) {
      throw Exceptions.sourceInstantiation(illegalAccessException);
    } catch (InstantiationException instantiationException) {
      throw Exceptions.sourceInstantiation(instantiationException);
    } catch (ClassCastException classCastException) {
      throw Exceptions.sourceInstantiation(classCastException);
    } 
    Transformer transformer = getIdentityTransformer();
    InputStream inputStream = getBinaryStreamImpl();
    StreamSource streamSource = new StreamSource();
    DOMResult dOMResult = new DOMResult();
    streamSource.setInputStream(inputStream);
    try {
      transformer.transform(streamSource, dOMResult);
    } catch (TransformerException transformerException) {
      throw Exceptions.transformFailed(transformerException);
    } 
    dOMSource.setNode(dOMResult.getNode());
    dOMSource.setSystemId(dOMResult.getSystemId());
    return (T)dOMSource;
  }
  
  protected <T extends Source> T createSAXSource(Class<T> paramClass) throws SQLException {
    SAXSource sAXSource = null;
    try {
      sAXSource = (paramClass == null) ? new SAXSource() : (SAXSource)paramClass.newInstance();
    } catch (SecurityException securityException) {
      throw Exceptions.sourceInstantiation(securityException);
    } catch (InstantiationException instantiationException) {
      throw Exceptions.sourceInstantiation(instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      throw Exceptions.sourceInstantiation(illegalAccessException);
    } catch (ClassCastException classCastException) {
      throw Exceptions.sourceInstantiation(classCastException);
    } 
    Reader reader = getCharacterStreamImpl();
    InputSource inputSource = new InputSource(reader);
    sAXSource.setInputSource(inputSource);
    return (T)sAXSource;
  }
  
  protected <T extends Source> T createStAXSource(Class<T> paramClass) throws SQLException {
    StAXSource stAXSource = null;
    Constructor<T> constructor = null;
    Reader reader = null;
    XMLInputFactory xMLInputFactory = null;
    XMLEventReader xMLEventReader = null;
    try {
      xMLInputFactory = XMLInputFactory.newInstance();
    } catch (FactoryConfigurationError factoryConfigurationError) {
      throw Exceptions.sourceInstantiation(factoryConfigurationError);
    } 
    try {
      constructor = (paramClass == null) ? (Constructor)StAXSource.class.getConstructor(new Class[] { XMLEventReader.class }) : paramClass.getConstructor(new Class[] { XMLEventReader.class });
    } catch (SecurityException securityException) {
      throw Exceptions.sourceInstantiation(securityException);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw Exceptions.sourceInstantiation(noSuchMethodException);
    } 
    reader = getCharacterStreamImpl();
    try {
      xMLEventReader = xMLInputFactory.createXMLEventReader(reader);
    } catch (XMLStreamException xMLStreamException) {
      throw Exceptions.sourceInstantiation(xMLStreamException);
    } 
    try {
      stAXSource = (StAXSource)constructor.newInstance(new Object[] { xMLEventReader });
    } catch (SecurityException securityException) {
      throw Exceptions.sourceInstantiation(securityException);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw Exceptions.sourceInstantiation(illegalArgumentException);
    } catch (IllegalAccessException illegalAccessException) {
      throw Exceptions.sourceInstantiation(illegalAccessException);
    } catch (InstantiationException instantiationException) {
      throw Exceptions.sourceInstantiation(instantiationException);
    } catch (InvocationTargetException invocationTargetException) {
      throw Exceptions.sourceInstantiation(invocationTargetException.getTargetException());
    } catch (ClassCastException classCastException) {
      throw Exceptions.sourceInstantiation(classCastException);
    } 
    return (T)stAXSource;
  }
  
  protected <T extends Result> T createResult(Class<T> paramClass) throws SQLException {
    checkWritable();
    setWritable(false);
    setReadable(true);
    if (!JAXBResult.class.isAssignableFrom(paramClass)) {
      if (paramClass == null || StreamResult.class.isAssignableFrom(paramClass))
        return createStreamResult(paramClass); 
      if (DOMResult.class.isAssignableFrom(paramClass))
        return createDOMResult(paramClass); 
      if (SAXResult.class.isAssignableFrom(paramClass))
        return createSAXResult(paramClass); 
      if (StAXResult.class.isAssignableFrom(paramClass))
        return createStAXResult(paramClass); 
    } 
    throw JDBCUtil.invalidArgument("resultClass: " + paramClass);
  }
  
  protected <T extends Result> T createStreamResult(Class<T> paramClass) throws SQLException {
    StreamResult streamResult = null;
    try {
      streamResult = (paramClass == null) ? new StreamResult() : (StreamResult)paramClass.newInstance();
    } catch (SecurityException securityException) {
      throw Exceptions.resultInstantiation(securityException);
    } catch (InstantiationException instantiationException) {
      throw Exceptions.resultInstantiation(instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      throw Exceptions.resultInstantiation(illegalAccessException);
    } catch (ClassCastException classCastException) {
      throw Exceptions.resultInstantiation(classCastException);
    } 
    OutputStream outputStream = setBinaryStreamImpl();
    streamResult.setOutputStream(outputStream);
    return (T)streamResult;
  }
  
  protected <T extends Result> T createDOMResult(Class<T> paramClass) throws SQLException {
    try {
      Result result = (paramClass == null) ? new DOMResult() : (Result)paramClass.newInstance();
      this.domResult = (DOMResult)result;
      return (T)result;
    } catch (SecurityException securityException) {
      throw Exceptions.resultInstantiation(securityException);
    } catch (InstantiationException instantiationException) {
      throw Exceptions.resultInstantiation(instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      throw Exceptions.resultInstantiation(illegalAccessException);
    } catch (ClassCastException classCastException) {
      throw Exceptions.resultInstantiation(classCastException);
    } 
  }
  
  protected <T extends Result> T createSAXResult(Class<T> paramClass) throws SQLException {
    SAXResult sAXResult = null;
    try {
      sAXResult = (paramClass == null) ? new SAXResult() : (SAXResult)paramClass.newInstance();
    } catch (SecurityException securityException) {
      throw Exceptions.resultInstantiation(securityException);
    } catch (InstantiationException instantiationException) {
      throw Exceptions.resultInstantiation(instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      throw Exceptions.resultInstantiation(illegalAccessException);
    } catch (ClassCastException classCastException) {
      throw Exceptions.resultInstantiation(classCastException);
    } 
    SAX2DOMBuilder sAX2DOMBuilder = null;
    try {
      sAX2DOMBuilder = new SAX2DOMBuilder();
    } catch (ParserConfigurationException parserConfigurationException) {
      throw Exceptions.resultInstantiation(parserConfigurationException);
    } 
    this.domResult = new DOMResult();
    sAXResult.setHandler(sAX2DOMBuilder);
    this.domResult.setNode(sAX2DOMBuilder.getDocument());
    return (T)sAXResult;
  }
  
  protected <T extends Result> T createStAXResult(Class<T> paramClass) throws SQLException {
    StAXResult stAXResult = null;
    try {
      this.domResult = new DOMResult((new SAX2DOMBuilder()).getDocument());
      XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
      XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(this.domResult);
      if (paramClass == null || paramClass == StAXResult.class) {
        stAXResult = new StAXResult(xMLStreamWriter);
      } else {
        Constructor<T> constructor = paramClass.getConstructor(new Class[] { XMLStreamWriter.class });
        stAXResult = (StAXResult)constructor.newInstance(new Object[] { xMLStreamWriter });
      } 
    } catch (ParserConfigurationException parserConfigurationException) {
      throw Exceptions.resultInstantiation(parserConfigurationException);
    } catch (SecurityException securityException) {
      throw Exceptions.resultInstantiation(securityException);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw Exceptions.resultInstantiation(illegalArgumentException);
    } catch (IllegalAccessException illegalAccessException) {
      throw Exceptions.resultInstantiation(illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      throw Exceptions.resultInstantiation(invocationTargetException.getTargetException());
    } catch (FactoryConfigurationError factoryConfigurationError) {
      throw Exceptions.resultInstantiation(factoryConfigurationError);
    } catch (InstantiationException instantiationException) {
      throw Exceptions.resultInstantiation(instantiationException);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw Exceptions.resultInstantiation(noSuchMethodException);
    } catch (XMLStreamException xMLStreamException) {
      throw Exceptions.resultInstantiation(xMLStreamException);
    } 
    return (T)stAXResult;
  }
  
  protected void freeDomResult() {
    this.domResult = null;
  }
  
  static {
    Charset charset = null;
    try {
      charset = Charset.forName("UTF8");
    } catch (Exception exception) {}
    utf8Charset = charset;
  }
  
  public static class SAX2XMLStreamWriter implements ContentHandler, Closeable {
    private List<QualifiedName> namespaces = new ArrayList<QualifiedName>();
    
    private boolean closed;
    
    private Locator locator;
    
    private XMLStreamWriter writer;
    
    public SAX2XMLStreamWriter(XMLStreamWriter param1XMLStreamWriter) {
      if (param1XMLStreamWriter == null)
        throw new NullPointerException("writer"); 
      this.writer = param1XMLStreamWriter;
    }
    
    public void startDocument() throws SAXException {
      checkClosed();
      try {
        this.writer.writeStartDocument();
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } 
    }
    
    public void endDocument() throws SAXException {
      checkClosed();
      try {
        this.writer.writeEndDocument();
        this.writer.flush();
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } 
    }
    
    public void characters(char[] param1ArrayOfchar, int param1Int1, int param1Int2) throws SAXException {
      checkClosed();
      try {
        this.writer.writeCharacters(param1ArrayOfchar, param1Int1, param1Int2);
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } 
    }
    
    public void startElement(String param1String1, String param1String2, String param1String3, Attributes param1Attributes) throws SAXException {
      checkClosed();
      try {
        int i = param1String3.indexOf(':');
        String str = (i > 0) ? param1String3.substring(0, i) : "";
        this.writer.writeStartElement(str, param1String2, param1String1);
        int j = this.namespaces.size();
        byte b;
        for (b = 0; b < j; b++) {
          QualifiedName qualifiedName = this.namespaces.get(b);
          this.writer.writeNamespace(qualifiedName.prefix, qualifiedName.namespaceName);
        } 
        this.namespaces.clear();
        j = param1Attributes.getLength();
        for (b = 0; b < j; b++)
          this.writer.writeAttribute(param1Attributes.getURI(b), param1Attributes.getLocalName(b), param1Attributes.getValue(b)); 
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } 
    }
    
    public void endElement(String param1String1, String param1String2, String param1String3) throws SAXException {
      checkClosed();
      try {
        this.writer.writeEndElement();
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } 
    }
    
    public void startPrefixMapping(String param1String1, String param1String2) throws SAXException {
      checkClosed();
      try {
        this.writer.setPrefix(param1String1, param1String2);
        this.namespaces.add(new QualifiedName(param1String1, param1String2));
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } 
    }
    
    public void endPrefixMapping(String param1String) throws SAXException {
      checkClosed();
    }
    
    public void ignorableWhitespace(char[] param1ArrayOfchar, int param1Int1, int param1Int2) throws SAXException {
      characters(param1ArrayOfchar, param1Int1, param1Int2);
    }
    
    public void processingInstruction(String param1String1, String param1String2) throws SAXException {
      checkClosed();
      try {
        this.writer.writeProcessingInstruction(param1String1, param1String2);
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } 
    }
    
    public void setDocumentLocator(Locator param1Locator) {
      this.locator = param1Locator;
    }
    
    public Locator getDocumentLocator() {
      return this.locator;
    }
    
    public void skippedEntity(String param1String) throws SAXException {
      checkClosed();
    }
    
    public void comment(char[] param1ArrayOfchar, int param1Int1, int param1Int2) throws SAXException {
      checkClosed();
      try {
        this.writer.writeComment(new String(param1ArrayOfchar, param1Int1, param1Int2));
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } 
    }
    
    public XMLStreamWriter getWriter() {
      return this.writer;
    }
    
    protected List<QualifiedName> getNamespaces() {
      return this.namespaces;
    }
    
    public void close() throws IOException {
      if (!this.closed) {
        this.closed = true;
        try {
          this.writer.close();
        } catch (XMLStreamException xMLStreamException) {
          throw new IOException(xMLStreamException);
        } finally {
          this.writer = null;
          this.locator = null;
          this.namespaces = null;
        } 
      } 
    }
    
    public boolean isClosed() {
      return this.closed;
    }
    
    protected void checkClosed() throws SAXException {
      if (isClosed())
        throw new SAXException("content handler is closed."); 
    }
    
    protected class QualifiedName {
      public final String namespaceName;
      
      public final String prefix;
      
      public QualifiedName(String param2String1, String param2String2) {
        this.prefix = param2String1;
        this.namespaceName = param2String2;
      }
    }
  }
  
  protected static class SAX2DOMBuilder implements ContentHandler, Closeable {
    private boolean closed;
    
    private Element currentElement;
    
    private Node currentNode;
    
    private Document document;
    
    private Locator locator;
    
    public SAX2DOMBuilder() throws ParserConfigurationException {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setValidating(false);
      documentBuilderFactory.setNamespaceAware(true);
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      this.document = documentBuilder.newDocument();
      this.currentNode = this.document;
    }
    
    public void setDocumentLocator(Locator param1Locator) {
      this.locator = param1Locator;
    }
    
    public Locator getDocumentLocator() {
      return this.locator;
    }
    
    public void startDocument() throws SAXException {
      checkClosed();
    }
    
    public void endDocument() throws SAXException {
      checkClosed();
      close();
    }
    
    public void startPrefixMapping(String param1String1, String param1String2) throws SAXException {
      checkClosed();
    }
    
    public void endPrefixMapping(String param1String) throws SAXException {
      checkClosed();
    }
    
    public void startElement(String param1String1, String param1String2, String param1String3, Attributes param1Attributes) throws SAXException {
      Element element;
      checkClosed();
      if (param1String1 == null || param1String1.length() == 0) {
        element = getDocument().createElement(param1String3);
      } else {
        element = getDocument().createElementNS(param1String1, param1String3);
      } 
      if (param1Attributes != null)
        for (byte b = 0; b < param1Attributes.getLength(); b++) {
          String str1 = param1Attributes.getURI(b);
          String str2 = param1Attributes.getQName(b);
          String str3 = param1Attributes.getValue(b);
          if (str1 == null || str1.length() == 0) {
            element.setAttribute(str2, str3);
          } else {
            element.setAttributeNS(str1, str2, str3);
          } 
        }  
      getCurrentNode().appendChild(element);
      setCurrentNode(element);
      if (getCurrentElement() == null)
        setCurrentElement(element); 
    }
    
    public void endElement(String param1String1, String param1String2, String param1String3) throws SAXException {
      checkClosed();
      setCurrentNode(getCurrentNode().getParentNode());
    }
    
    public void characters(char[] param1ArrayOfchar, int param1Int1, int param1Int2) throws SAXException {
      checkClosed();
      Node node = getCurrentNode().getLastChild();
      String str = new String(param1ArrayOfchar, param1Int1, param1Int2);
      if (node != null && node.getNodeType() == 3) {
        ((Text)node).appendData(str);
      } else {
        Text text = getDocument().createTextNode(str);
        getCurrentNode().appendChild(text);
      } 
    }
    
    public void ignorableWhitespace(char[] param1ArrayOfchar, int param1Int1, int param1Int2) throws SAXException {
      characters(param1ArrayOfchar, param1Int1, param1Int2);
    }
    
    public void processingInstruction(String param1String1, String param1String2) throws SAXException {
      checkClosed();
      ProcessingInstruction processingInstruction = getDocument().createProcessingInstruction(param1String1, param1String2);
      getCurrentNode().appendChild(processingInstruction);
    }
    
    public void skippedEntity(String param1String) throws SAXException {
      checkClosed();
      EntityReference entityReference = getDocument().createEntityReference(param1String);
      getCurrentNode().appendChild(entityReference);
    }
    
    public void close() {
      this.closed = true;
    }
    
    public void free() {
      close();
      this.document = null;
      this.currentElement = null;
      this.currentNode = null;
      this.locator = null;
    }
    
    public boolean isClosed() {
      return this.closed;
    }
    
    protected void checkClosed() throws SAXException {
      if (isClosed())
        throw new SAXException("content handler is closed."); 
    }
    
    public Document getDocument() {
      return this.document;
    }
    
    protected Element getCurrentElement() {
      return this.currentElement;
    }
    
    protected void setCurrentElement(Element param1Element) {
      this.currentElement = param1Element;
    }
    
    protected Node getCurrentNode() {
      return this.currentNode;
    }
    
    protected void setCurrentNode(Node param1Node) {
      this.currentNode = param1Node;
    }
  }
  
  protected static class Exceptions {
    static SQLException domInstantiation(Throwable param1Throwable) {
      Exception exception = (param1Throwable instanceof Exception) ? (Exception)param1Throwable : new Exception(param1Throwable);
      return JDBCUtil.sqlException(458, "SQLXML DOM instantiation failed: " + param1Throwable, exception);
    }
    
    static SQLException sourceInstantiation(Throwable param1Throwable) {
      Exception exception = (param1Throwable instanceof Exception) ? (Exception)param1Throwable : new Exception(param1Throwable);
      return JDBCUtil.sqlException(458, "SQLXML Source instantiation failed: " + param1Throwable, exception);
    }
    
    static SQLException resultInstantiation(Throwable param1Throwable) {
      Exception exception = (param1Throwable instanceof Exception) ? (Exception)param1Throwable : new Exception(param1Throwable);
      return JDBCUtil.sqlException(458, "SQLXML Result instantiation failed: " + param1Throwable, exception);
    }
    
    static SQLException parseFailed(Throwable param1Throwable) {
      Exception exception = (param1Throwable instanceof Exception) ? (Exception)param1Throwable : new Exception(param1Throwable);
      return JDBCUtil.sqlException(458, "parse failed: " + param1Throwable, exception);
    }
    
    static SQLException transformFailed(Throwable param1Throwable) {
      Exception exception = (param1Throwable instanceof Exception) ? (Exception)param1Throwable : new Exception(param1Throwable);
      return JDBCUtil.sqlException(458, "transform failed: " + param1Throwable, exception);
    }
    
    static SQLException notReadable() {
      return JDBCUtil.sqlException(467, "SQLXML in not readable state");
    }
    
    static SQLException notReadable(String param1String) {
      return JDBCUtil.sqlException(467, "SQLXML in not readable state: " + param1String);
    }
    
    static SQLException notWritable() {
      return JDBCUtil.sqlException(467, "SQLXML in not writable state");
    }
    
    static SQLException directUpdateByLocatorNotSupported() {
      return JDBCUtil.sqlException(1500, "SQLXML direct update by locator");
    }
    
    static SQLException inFreedState() {
      return JDBCUtil.sqlException(458, "SQLXML in freed state");
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCSQLXML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */