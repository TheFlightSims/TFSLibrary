/*     */ package com.ctc.wstx.msv;
/*     */ 
/*     */ import com.ctc.wstx.api.ValidatorConfig;
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import com.ctc.wstx.util.URLUtil;
/*     */ import com.sun.msv.reader.util.IgnoreController;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchemaFactory;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.Locator;
/*     */ 
/*     */ public abstract class BaseSchemaFactory extends XMLValidationSchemaFactory {
/*     */   protected static SAXParserFactory sSaxFactory;
/*     */   
/*     */   protected final ValidatorConfig mConfig;
/*     */   
/*     */   protected BaseSchemaFactory(String schemaType) {
/*  48 */     super(schemaType);
/*  49 */     this.mConfig = ValidatorConfig.createDefaults();
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String propName) {
/*  60 */     return this.mConfig.isPropertySupported(propName);
/*     */   }
/*     */   
/*     */   public boolean setProperty(String propName, Object value) {
/*  65 */     return this.mConfig.setProperty(propName, value);
/*     */   }
/*     */   
/*     */   public Object getProperty(String propName) {
/*  70 */     return this.mConfig.getProperty(propName);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(InputStream in, String encoding, String publicId, String systemId) throws XMLStreamException {
/*  83 */     InputSource src = new InputSource(in);
/*  84 */     src.setEncoding(encoding);
/*  85 */     src.setPublicId(publicId);
/*  86 */     src.setSystemId(systemId);
/*  87 */     return loadSchema(src, systemId);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(Reader r, String publicId, String systemId) throws XMLStreamException {
/*  94 */     InputSource src = new InputSource(r);
/*  95 */     src.setPublicId(publicId);
/*  96 */     src.setSystemId(systemId);
/*  97 */     return loadSchema(src, systemId);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(URL url) throws XMLStreamException {
/*     */     try {
/* 104 */       InputStream in = URLUtil.inputStreamFromURL(url);
/* 105 */       InputSource src = new InputSource(in);
/* 106 */       src.setSystemId(url.toExternalForm());
/* 107 */       return loadSchema(src, url);
/* 108 */     } catch (IOException ioe) {
/* 109 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(File f) throws XMLStreamException {
/*     */     try {
/* 117 */       return createSchema(f.toURL());
/* 118 */     } catch (IOException ioe) {
/* 119 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract XMLValidationSchema loadSchema(InputSource paramInputSource, Object paramObject) throws XMLStreamException;
/*     */   
/*     */   protected static synchronized SAXParserFactory getSaxFactory() {
/* 145 */     if (sSaxFactory == null) {
/* 146 */       sSaxFactory = SAXParserFactory.newInstance();
/* 147 */       sSaxFactory.setNamespaceAware(true);
/*     */     } 
/* 149 */     return sSaxFactory;
/*     */   }
/*     */   
/*     */   static final class MyGrammarController extends IgnoreController {
/* 161 */     public String mErrorMsg = null;
/*     */     
/*     */     public void error(Locator[] locs, String msg, Exception nestedException) {
/* 169 */       if (this.mErrorMsg == null) {
/* 170 */         this.mErrorMsg = msg;
/*     */       } else {
/* 172 */         this.mErrorMsg += "; " + msg;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\msv\BaseSchemaFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */