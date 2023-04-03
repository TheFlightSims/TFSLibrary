/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.api.ValidatorConfig;
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import com.ctc.wstx.io.InputBootstrapper;
/*     */ import com.ctc.wstx.io.InputSourceFactory;
/*     */ import com.ctc.wstx.io.ReaderBootstrapper;
/*     */ import com.ctc.wstx.io.ReaderSource;
/*     */ import com.ctc.wstx.io.StreamBootstrapper;
/*     */ import com.ctc.wstx.io.WstxInputSource;
/*     */ import com.ctc.wstx.util.DefaultXmlSymbolTable;
/*     */ import com.ctc.wstx.util.SymbolTable;
/*     */ import com.ctc.wstx.util.URLUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchemaFactory;
/*     */ 
/*     */ public class DTDSchemaFactory extends XMLValidationSchemaFactory {
/*  56 */   static final SymbolTable mRootSymbols = DefaultXmlSymbolTable.getInstance();
/*     */   
/*     */   protected final ValidatorConfig mSchemaConfig;
/*     */   
/*     */   protected final ReaderConfig mReaderConfig;
/*     */   
/*     */   static {
/*  58 */     mRootSymbols.setInternStrings(true);
/*     */   }
/*     */   
/*     */   public DTDSchemaFactory() {
/*  74 */     super("http://www.w3.org/XML/1998/namespace");
/*  75 */     this.mReaderConfig = ReaderConfig.createFullDefaults();
/*  76 */     this.mSchemaConfig = ValidatorConfig.createDefaults();
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String propName) {
/*  87 */     return this.mSchemaConfig.isPropertySupported(propName);
/*     */   }
/*     */   
/*     */   public boolean setProperty(String propName, Object value) {
/*  92 */     return this.mSchemaConfig.setProperty(propName, value);
/*     */   }
/*     */   
/*     */   public Object getProperty(String propName) {
/*  97 */     return this.mSchemaConfig.getProperty(propName);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(InputStream in, String encoding, String publicId, String systemId) throws XMLStreamException {
/* 110 */     ReaderConfig rcfg = createPrivateReaderConfig();
/* 111 */     return doCreateSchema(rcfg, (InputBootstrapper)StreamBootstrapper.getInstance(publicId, systemId, in), publicId, systemId, null);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(Reader r, String publicId, String systemId) throws XMLStreamException {
/* 119 */     ReaderConfig rcfg = createPrivateReaderConfig();
/* 120 */     return doCreateSchema(rcfg, (InputBootstrapper)ReaderBootstrapper.getInstance(publicId, systemId, r, null), publicId, systemId, null);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(URL url) throws XMLStreamException {
/* 127 */     ReaderConfig rcfg = createPrivateReaderConfig();
/*     */     try {
/* 129 */       InputStream in = URLUtil.inputStreamFromURL(url);
/* 130 */       return doCreateSchema(rcfg, (InputBootstrapper)StreamBootstrapper.getInstance(null, null, in), null, url.toExternalForm(), url);
/* 133 */     } catch (IOException ioe) {
/* 134 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(File f) throws XMLStreamException {
/* 141 */     ReaderConfig rcfg = createPrivateReaderConfig();
/*     */     try {
/* 143 */       URL url = f.toURL();
/* 144 */       return doCreateSchema(rcfg, (InputBootstrapper)StreamBootstrapper.getInstance(null, null, new FileInputStream(f)), null, url.toExternalForm(), url);
/* 147 */     } catch (IOException ioe) {
/* 148 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected XMLValidationSchema doCreateSchema(ReaderConfig rcfg, InputBootstrapper bs, String publicId, String systemId, URL ctxt) throws XMLStreamException {
/*     */     try {
/* 173 */       Reader r = bs.bootstrapInput(rcfg, false, 0);
/* 174 */       if (bs.declaredXml11())
/* 175 */         rcfg.enableXml11(true); 
/* 177 */       if (ctxt == null)
/* 178 */         ctxt = URLUtil.urlFromCurrentDir(); 
/* 185 */       ReaderSource readerSource = InputSourceFactory.constructEntitySource(rcfg, null, null, bs, publicId, systemId, 0, ctxt, r);
/* 192 */       return (XMLValidationSchema)FullDTDReader.readExternalSubset((WstxInputSource)readerSource, rcfg, null, true, bs.getDeclaredVersion());
/* 193 */     } catch (IOException ioe) {
/* 194 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ReaderConfig createPrivateReaderConfig() {
/* 200 */     return this.mReaderConfig.createNonShared(mRootSymbols.makeChild());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDSchemaFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */