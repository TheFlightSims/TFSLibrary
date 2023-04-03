/*     */ package com.ctc.wstx.stax;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import com.ctc.wstx.cfg.OutputConfigFlags;
/*     */ import com.ctc.wstx.dom.WstxDOMWrappingWriter;
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import com.ctc.wstx.io.CharsetNames;
/*     */ import com.ctc.wstx.io.UTF8Writer;
/*     */ import com.ctc.wstx.sw.AsciiXmlWriter;
/*     */ import com.ctc.wstx.sw.BufferingXmlWriter;
/*     */ import com.ctc.wstx.sw.ISOLatin1XmlWriter;
/*     */ import com.ctc.wstx.sw.NonNsStreamWriter;
/*     */ import com.ctc.wstx.sw.RepairingNsStreamWriter;
/*     */ import com.ctc.wstx.sw.SimpleNsStreamWriter;
/*     */ import com.ctc.wstx.sw.XmlWriter;
/*     */ import com.ctc.wstx.util.URLUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.XMLEventWriter;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.dom.DOMResult;
/*     */ import javax.xml.transform.sax.SAXResult;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.codehaus.stax2.XMLOutputFactory2;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.io.Stax2Result;
/*     */ import org.codehaus.stax2.ri.Stax2EventWriterImpl;
/*     */ import org.codehaus.stax2.ri.Stax2WriterAdapter;
/*     */ 
/*     */ public class WstxOutputFactory extends XMLOutputFactory2 implements OutputConfigFlags {
/*  83 */   protected final WriterConfig mConfig = WriterConfig.createFullDefaults();
/*     */   
/*     */   public XMLEventWriter createXMLEventWriter(OutputStream out) throws XMLStreamException {
/*  95 */     return createXMLEventWriter(out, (String)null);
/*     */   }
/*     */   
/*     */   public XMLEventWriter createXMLEventWriter(OutputStream out, String enc) throws XMLStreamException {
/* 101 */     if (out == null)
/* 102 */       throw new IllegalArgumentException("Null OutputStream is not a valid argument"); 
/* 104 */     return (XMLEventWriter)new Stax2EventWriterImpl(createSW(out, null, enc, false));
/*     */   }
/*     */   
/*     */   public XMLEventWriter createXMLEventWriter(Result result) throws XMLStreamException {
/* 110 */     return (XMLEventWriter)new Stax2EventWriterImpl(createSW(result));
/*     */   }
/*     */   
/*     */   public XMLEventWriter createXMLEventWriter(Writer w) throws XMLStreamException {
/* 116 */     if (w == null)
/* 117 */       throw new IllegalArgumentException("Null Writer is not a valid argument"); 
/* 119 */     return (XMLEventWriter)new Stax2EventWriterImpl(createSW(null, w, null, false));
/*     */   }
/*     */   
/*     */   public XMLStreamWriter createXMLStreamWriter(OutputStream out) throws XMLStreamException {
/* 125 */     return createXMLStreamWriter(out, (String)null);
/*     */   }
/*     */   
/*     */   public XMLStreamWriter createXMLStreamWriter(OutputStream out, String enc) throws XMLStreamException {
/* 131 */     if (out == null)
/* 132 */       throw new IllegalArgumentException("Null OutputStream is not a valid argument"); 
/* 134 */     return (XMLStreamWriter)createSW(out, null, enc, false);
/*     */   }
/*     */   
/*     */   public XMLStreamWriter createXMLStreamWriter(Result result) throws XMLStreamException {
/* 140 */     return (XMLStreamWriter)createSW(result);
/*     */   }
/*     */   
/*     */   public XMLStreamWriter createXMLStreamWriter(Writer w) throws XMLStreamException {
/* 146 */     if (w == null)
/* 147 */       throw new IllegalArgumentException("Null Writer is not a valid argument"); 
/* 149 */     return (XMLStreamWriter)createSW(null, w, null, false);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 154 */     return this.mConfig.getProperty(name);
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/* 158 */     return this.mConfig.isPropertySupported(name);
/*     */   }
/*     */   
/*     */   public void setProperty(String name, Object value) {
/* 163 */     this.mConfig.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public XMLEventWriter createXMLEventWriter(Writer w, String enc) throws XMLStreamException {
/* 177 */     return (XMLEventWriter)new Stax2EventWriterImpl(createSW(null, w, enc, false));
/*     */   }
/*     */   
/*     */   public XMLEventWriter createXMLEventWriter(XMLStreamWriter sw) throws XMLStreamException {
/* 183 */     XMLStreamWriter2 sw2 = Stax2WriterAdapter.wrapIfNecessary(sw);
/* 184 */     return (XMLEventWriter)new Stax2EventWriterImpl(sw2);
/*     */   }
/*     */   
/*     */   public XMLStreamWriter2 createXMLStreamWriter(Writer w, String enc) throws XMLStreamException {
/* 190 */     return createSW(null, w, enc, false);
/*     */   }
/*     */   
/*     */   public void configureForXmlConformance() {
/* 197 */     this.mConfig.configureForXmlConformance();
/*     */   }
/*     */   
/*     */   public void configureForRobustness() {
/* 202 */     this.mConfig.configureForRobustness();
/*     */   }
/*     */   
/*     */   public void configureForSpeed() {
/* 207 */     this.mConfig.configureForSpeed();
/*     */   }
/*     */   
/*     */   public WriterConfig getConfig() {
/* 217 */     return this.mConfig;
/*     */   }
/*     */   
/*     */   private XMLStreamWriter2 createSW(OutputStream out, Writer w, String enc, boolean requireAutoClose) throws XMLStreamException {
/*     */     BufferingXmlWriter bufferingXmlWriter;
/* 242 */     WriterConfig cfg = this.mConfig.createNonShared();
/* 245 */     boolean autoCloseOutput = (requireAutoClose || this.mConfig.willAutoCloseOutput());
/* 247 */     if (w == null) {
/* 248 */       if (enc == null) {
/* 249 */         enc = "UTF-8";
/* 254 */       } else if (enc != "UTF-8" && enc != "ISO-8859-1" && enc != "US-ASCII") {
/* 257 */         enc = CharsetNames.normalize(enc);
/*     */       } 
/*     */       try {
/* 262 */         if (enc == "UTF-8") {
/* 263 */           UTF8Writer uTF8Writer = new UTF8Writer(cfg, out, autoCloseOutput);
/* 264 */           bufferingXmlWriter = new BufferingXmlWriter((Writer)uTF8Writer, cfg, enc, autoCloseOutput, out, 16);
/* 265 */         } else if (enc == "ISO-8859-1") {
/* 266 */           ISOLatin1XmlWriter iSOLatin1XmlWriter = new ISOLatin1XmlWriter(out, cfg, autoCloseOutput);
/* 267 */         } else if (enc == "US-ASCII") {
/* 268 */           AsciiXmlWriter asciiXmlWriter = new AsciiXmlWriter(out, cfg, autoCloseOutput);
/*     */         } else {
/* 270 */           w = new OutputStreamWriter(out, enc);
/* 271 */           bufferingXmlWriter = new BufferingXmlWriter(w, cfg, enc, autoCloseOutput, out, -1);
/*     */         } 
/* 273 */       } catch (IOException ex) {
/* 274 */         throw new XMLStreamException(ex);
/*     */       } 
/*     */     } else {
/* 278 */       if (enc == null)
/* 279 */         enc = CharsetNames.findEncodingFor(w); 
/*     */       try {
/* 282 */         bufferingXmlWriter = new BufferingXmlWriter(w, cfg, enc, autoCloseOutput, null, -1);
/* 283 */       } catch (IOException ex) {
/* 284 */         throw new XMLStreamException(ex);
/*     */       } 
/*     */     } 
/* 288 */     return createSW(enc, cfg, (XmlWriter)bufferingXmlWriter);
/*     */   }
/*     */   
/*     */   protected XMLStreamWriter2 createSW(String enc, WriterConfig cfg, XmlWriter xw) {
/* 296 */     if (cfg.willSupportNamespaces()) {
/* 297 */       if (cfg.automaticNamespacesEnabled())
/* 298 */         return (XMLStreamWriter2)new RepairingNsStreamWriter(xw, enc, cfg); 
/* 300 */       return (XMLStreamWriter2)new SimpleNsStreamWriter(xw, enc, cfg);
/*     */     } 
/* 302 */     return (XMLStreamWriter2)new NonNsStreamWriter(xw, enc, cfg);
/*     */   }
/*     */   
/*     */   private XMLStreamWriter2 createSW(Result res) throws XMLStreamException {
/*     */     boolean requireAutoClose;
/* 308 */     OutputStream out = null;
/* 309 */     Writer w = null;
/* 310 */     String encoding = null;
/* 312 */     String sysId = null;
/* 314 */     if (res instanceof Stax2Result) {
/* 315 */       Stax2Result sr = (Stax2Result)res;
/*     */       try {
/* 317 */         out = sr.constructOutputStream();
/* 318 */         if (out == null)
/* 319 */           w = sr.constructWriter(); 
/* 321 */       } catch (IOException ioe) {
/* 322 */         throw new WstxIOException(ioe);
/*     */       } 
/* 325 */       requireAutoClose = true;
/* 326 */     } else if (res instanceof StreamResult) {
/* 327 */       StreamResult sr = (StreamResult)res;
/* 328 */       out = sr.getOutputStream();
/* 329 */       sysId = sr.getSystemId();
/* 330 */       if (out == null)
/* 331 */         w = sr.getWriter(); 
/* 337 */       requireAutoClose = false;
/* 338 */     } else if (res instanceof SAXResult) {
/* 339 */       SAXResult sr = (SAXResult)res;
/* 340 */       sysId = sr.getSystemId();
/* 341 */       if (sysId == null || sysId.length() == 0)
/* 342 */         throw new XMLStreamException("Can not create a stream writer for a SAXResult that does not have System Id (support for using SAX input source not implemented)"); 
/* 344 */       requireAutoClose = true;
/*     */     } else {
/* 345 */       if (res instanceof DOMResult)
/* 346 */         return (XMLStreamWriter2)WstxDOMWrappingWriter.createFrom(this.mConfig.createNonShared(), (DOMResult)res); 
/* 348 */       throw new IllegalArgumentException("Can not instantiate a writer for XML result type " + res.getClass() + " (unrecognized type)");
/*     */     } 
/* 351 */     if (out != null)
/* 352 */       return createSW(out, null, encoding, requireAutoClose); 
/* 354 */     if (w != null)
/* 355 */       return createSW(null, w, encoding, requireAutoClose); 
/* 357 */     if (sysId != null && sysId.length() > 0) {
/* 362 */       requireAutoClose = true;
/*     */       try {
/* 364 */         out = URLUtil.outputStreamFromURL(URLUtil.urlFromSystemId(sysId));
/* 365 */       } catch (IOException ioe) {
/* 366 */         throw new WstxIOException(ioe);
/*     */       } 
/* 368 */       return createSW(out, null, encoding, requireAutoClose);
/*     */     } 
/* 370 */     throw new XMLStreamException("Can not create Stax writer for passed-in Result -- neither writer, output stream or system id was accessible");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\stax\WstxOutputFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */