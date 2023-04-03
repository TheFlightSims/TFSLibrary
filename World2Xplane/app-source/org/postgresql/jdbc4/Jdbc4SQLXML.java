/*     */ package org.postgresql.jdbc4;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLXML;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.stream.XMLInputFactory;
/*     */ import javax.xml.stream.XMLOutputFactory;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMResult;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.sax.SAXResult;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import javax.xml.transform.sax.SAXTransformerFactory;
/*     */ import javax.xml.transform.sax.TransformerHandler;
/*     */ import javax.xml.transform.stax.StAXResult;
/*     */ import javax.xml.transform.stax.StAXSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ public class Jdbc4SQLXML implements SQLXML {
/*     */   private final BaseConnection _conn;
/*     */   
/*     */   private String _data;
/*     */   
/*     */   private boolean _initialized;
/*     */   
/*     */   private boolean _active;
/*     */   
/*     */   private boolean _freed;
/*     */   
/*     */   private ByteArrayOutputStream _byteArrayOutputStream;
/*     */   
/*     */   private StringWriter _stringWriter;
/*     */   
/*     */   private DOMResult _domResult;
/*     */   
/*     */   public Jdbc4SQLXML(BaseConnection conn) {
/*  55 */     this(conn, null, false);
/*     */   }
/*     */   
/*     */   public Jdbc4SQLXML(BaseConnection conn, String data) {
/*  60 */     this(conn, data, true);
/*     */   }
/*     */   
/*     */   private Jdbc4SQLXML(BaseConnection conn, String data, boolean initialized) {
/*  65 */     this._conn = conn;
/*  66 */     this._data = data;
/*  67 */     this._initialized = initialized;
/*  68 */     this._active = false;
/*  69 */     this._freed = false;
/*     */   }
/*     */   
/*     */   public synchronized void free() {
/*  74 */     this._freed = true;
/*  75 */     this._data = null;
/*     */   }
/*     */   
/*     */   public synchronized InputStream getBinaryStream() throws SQLException {
/*  80 */     checkFreed();
/*  81 */     ensureInitialized();
/*  83 */     if (this._data == null)
/*  84 */       return null; 
/*     */     try {
/*  87 */       return new ByteArrayInputStream(this._conn.getEncoding().encode(this._data));
/*  88 */     } catch (IOException ioe) {
/*  93 */       throw new PSQLException("Failed to re-encode xml data.", PSQLState.DATA_ERROR, ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Reader getCharacterStream() throws SQLException {
/*  99 */     checkFreed();
/* 100 */     ensureInitialized();
/* 102 */     if (this._data == null)
/* 103 */       return null; 
/* 105 */     return new StringReader(this._data);
/*     */   }
/*     */   
/*     */   public synchronized Source getSource(Class sourceClass) throws SQLException {
/* 116 */     checkFreed();
/* 117 */     ensureInitialized();
/* 119 */     if (this._data == null)
/* 120 */       return null; 
/*     */     try {
/* 123 */       if (sourceClass == null || DOMSource.class.equals(sourceClass)) {
/* 125 */         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 126 */         DocumentBuilder builder = factory.newDocumentBuilder();
/* 127 */         builder.setErrorHandler(new NonPrintingErrorHandler());
/* 128 */         InputSource input = new InputSource(new StringReader(this._data));
/* 129 */         return new DOMSource(builder.parse(input));
/*     */       } 
/* 131 */       if (SAXSource.class.equals(sourceClass)) {
/* 133 */         InputSource is = new InputSource(new StringReader(this._data));
/* 134 */         return new SAXSource(is);
/*     */       } 
/* 136 */       if (StreamSource.class.equals(sourceClass))
/* 138 */         return new StreamSource(new StringReader(this._data)); 
/* 140 */       if (StAXSource.class.equals(sourceClass)) {
/* 142 */         XMLInputFactory xif = XMLInputFactory.newInstance();
/* 143 */         XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(this._data));
/* 144 */         return new StAXSource(xsr);
/*     */       } 
/* 146 */     } catch (Exception e) {
/* 147 */       throw new PSQLException(GT.tr("Unable to decode xml data."), PSQLState.DATA_ERROR, e);
/*     */     } 
/* 150 */     throw new PSQLException(GT.tr("Unknown XML Source class: {0}", sourceClass), PSQLState.INVALID_PARAMETER_TYPE);
/*     */   }
/*     */   
/*     */   public synchronized String getString() throws SQLException {
/* 155 */     checkFreed();
/* 156 */     ensureInitialized();
/* 157 */     return this._data;
/*     */   }
/*     */   
/*     */   public synchronized OutputStream setBinaryStream() throws SQLException {
/* 162 */     checkFreed();
/* 163 */     initialize();
/* 164 */     this._active = true;
/* 165 */     this._byteArrayOutputStream = new ByteArrayOutputStream();
/* 166 */     return this._byteArrayOutputStream;
/*     */   }
/*     */   
/*     */   public synchronized Writer setCharacterStream() throws SQLException {
/* 171 */     checkFreed();
/* 172 */     initialize();
/* 173 */     this._stringWriter = new StringWriter();
/* 174 */     return this._stringWriter;
/*     */   }
/*     */   
/*     */   public synchronized Result setResult(Class resultClass) throws SQLException {
/* 179 */     checkFreed();
/* 180 */     initialize();
/* 182 */     if (resultClass == null || DOMResult.class.equals(resultClass)) {
/* 183 */       this._domResult = new DOMResult();
/* 184 */       this._active = true;
/* 185 */       return this._domResult;
/*     */     } 
/* 186 */     if (SAXResult.class.equals(resultClass))
/*     */       try {
/* 188 */         SAXTransformerFactory transformerFactory = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
/* 189 */         TransformerHandler transformerHandler = transformerFactory.newTransformerHandler();
/* 190 */         this._stringWriter = new StringWriter();
/* 191 */         transformerHandler.setResult(new StreamResult(this._stringWriter));
/* 192 */         this._active = true;
/* 193 */         return new SAXResult(transformerHandler);
/* 194 */       } catch (TransformerException te) {
/* 195 */         throw new PSQLException(GT.tr("Unable to create SAXResult for SQLXML."), PSQLState.UNEXPECTED_ERROR, te);
/*     */       }  
/* 197 */     if (StreamResult.class.equals(resultClass)) {
/* 198 */       this._stringWriter = new StringWriter();
/* 199 */       this._active = true;
/* 200 */       return new StreamResult(this._stringWriter);
/*     */     } 
/* 201 */     if (StAXResult.class.equals(resultClass)) {
/* 202 */       this._stringWriter = new StringWriter();
/*     */       try {
/* 204 */         XMLOutputFactory xof = XMLOutputFactory.newInstance();
/* 205 */         XMLStreamWriter xsw = xof.createXMLStreamWriter(this._stringWriter);
/* 206 */         this._active = true;
/* 207 */         return new StAXResult(xsw);
/* 208 */       } catch (XMLStreamException xse) {
/* 209 */         throw new PSQLException(GT.tr("Unable to create StAXResult for SQLXML"), PSQLState.UNEXPECTED_ERROR, xse);
/*     */       } 
/*     */     } 
/* 213 */     throw new PSQLException(GT.tr("Unknown XML Result class: {0}", resultClass), PSQLState.INVALID_PARAMETER_TYPE);
/*     */   }
/*     */   
/*     */   public synchronized void setString(String value) throws SQLException {
/* 218 */     checkFreed();
/* 219 */     initialize();
/* 220 */     this._data = value;
/*     */   }
/*     */   
/*     */   private void checkFreed() throws SQLException {
/* 225 */     if (this._freed)
/* 226 */       throw new PSQLException(GT.tr("This SQLXML object has already been freed."), PSQLState.OBJECT_NOT_IN_STATE); 
/*     */   }
/*     */   
/*     */   private void ensureInitialized() throws SQLException {
/* 232 */     if (!this._initialized)
/* 233 */       throw new PSQLException(GT.tr("This SQLXML object has not been initialized, so you cannot retrieve data from it."), PSQLState.OBJECT_NOT_IN_STATE); 
/* 237 */     if (!this._active)
/*     */       return; 
/* 240 */     if (this._byteArrayOutputStream != null) {
/*     */       try {
/* 242 */         this._data = this._conn.getEncoding().decode(this._byteArrayOutputStream.toByteArray());
/* 243 */       } catch (IOException ioe) {
/* 244 */         throw new PSQLException(GT.tr("Failed to convert binary xml data to encoding: {0}.", this._conn.getEncoding().name()), PSQLState.DATA_ERROR, ioe);
/*     */       } finally {
/* 246 */         this._byteArrayOutputStream = null;
/* 247 */         this._active = false;
/*     */       } 
/* 249 */     } else if (this._stringWriter != null) {
/* 253 */       this._data = this._stringWriter.toString();
/* 254 */       this._stringWriter = null;
/* 255 */       this._active = false;
/* 256 */     } else if (this._domResult != null) {
/*     */       try {
/* 261 */         TransformerFactory factory = TransformerFactory.newInstance();
/* 262 */         Transformer transformer = factory.newTransformer();
/* 263 */         DOMSource domSource = new DOMSource(this._domResult.getNode());
/* 264 */         StringWriter stringWriter = new StringWriter();
/* 265 */         StreamResult streamResult = new StreamResult(stringWriter);
/* 266 */         transformer.transform(domSource, streamResult);
/* 267 */         this._data = stringWriter.toString();
/* 268 */       } catch (TransformerException te) {
/* 269 */         throw new PSQLException(GT.tr("Unable to convert DOMResult SQLXML data to a string."), PSQLState.DATA_ERROR, te);
/*     */       } finally {
/* 272 */         this._domResult = null;
/* 273 */         this._active = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initialize() throws SQLException {
/* 281 */     if (this._initialized)
/* 282 */       throw new PSQLException(GT.tr("This SQLXML object has already been initialized, so you cannot manipulate it further."), PSQLState.OBJECT_NOT_IN_STATE); 
/* 284 */     this._initialized = true;
/*     */   }
/*     */   
/*     */   static class NonPrintingErrorHandler implements ErrorHandler {
/*     */     public void error(SAXParseException e) {}
/*     */     
/*     */     public void fatalError(SAXParseException e) {}
/*     */     
/*     */     public void warning(SAXParseException e) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4SQLXML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */