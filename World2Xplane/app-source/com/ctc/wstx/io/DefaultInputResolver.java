/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.util.URLUtil;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.XMLResolver;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ 
/*     */ public final class DefaultInputResolver {
/*     */   public static WstxInputSource resolveEntity(WstxInputSource parent, URL pathCtxt, String entityName, String publicId, String systemId, XMLResolver customResolver, ReaderConfig cfg, int xmlVersion) throws IOException, XMLStreamException {
/*  70 */     if (pathCtxt == null) {
/*  71 */       pathCtxt = parent.getSource();
/*  72 */       if (pathCtxt == null)
/*  73 */         pathCtxt = URLUtil.urlFromCurrentDir(); 
/*     */     } 
/*  78 */     if (customResolver != null) {
/*  79 */       Object source = customResolver.resolveEntity(publicId, systemId, pathCtxt.toExternalForm(), entityName);
/*  80 */       if (source != null)
/*  81 */         return sourceFrom(parent, cfg, entityName, xmlVersion, source); 
/*     */     } 
/*  86 */     if (systemId == null)
/*  87 */       throw new XMLStreamException("Can not resolve " + ((entityName == null) ? "[External DTD subset]" : ("entity '" + entityName + "'")) + " without a system id (public id '" + publicId + "')"); 
/*  91 */     URL url = URLUtil.urlFromSystemId(systemId, pathCtxt);
/*  92 */     return sourceFromURL(parent, cfg, entityName, xmlVersion, url, publicId);
/*     */   }
/*     */   
/*     */   public static WstxInputSource resolveEntityUsing(WstxInputSource refCtxt, String entityName, String publicId, String systemId, XMLResolver resolver, ReaderConfig cfg, int xmlVersion) throws IOException, XMLStreamException {
/* 106 */     URL ctxt = (refCtxt == null) ? null : refCtxt.getSource();
/* 107 */     if (ctxt == null)
/* 108 */       ctxt = URLUtil.urlFromCurrentDir(); 
/* 110 */     Object source = resolver.resolveEntity(publicId, systemId, ctxt.toExternalForm(), entityName);
/* 111 */     return (source == null) ? null : sourceFrom(refCtxt, cfg, entityName, xmlVersion, source);
/*     */   }
/*     */   
/*     */   protected static WstxInputSource sourceFrom(WstxInputSource parent, ReaderConfig cfg, String refName, int xmlVersion, Object o) throws IllegalArgumentException, IOException, XMLStreamException {
/* 133 */     if (o instanceof javax.xml.transform.Source) {
/* 134 */       if (o instanceof StreamSource)
/* 135 */         return sourceFromSS(parent, cfg, refName, xmlVersion, (StreamSource)o); 
/* 140 */       throw new IllegalArgumentException("Can not use other Source objects than StreamSource: got " + o.getClass());
/*     */     } 
/* 142 */     if (o instanceof URL)
/* 143 */       return sourceFromURL(parent, cfg, refName, xmlVersion, (URL)o, null); 
/* 145 */     if (o instanceof InputStream)
/* 146 */       return sourceFromIS(parent, cfg, refName, xmlVersion, (InputStream)o, null, null); 
/* 148 */     if (o instanceof Reader)
/* 149 */       return sourceFromR(parent, cfg, refName, xmlVersion, (Reader)o, null, null); 
/* 151 */     if (o instanceof String)
/* 152 */       return sourceFromString(parent, cfg, refName, xmlVersion, (String)o); 
/* 154 */     if (o instanceof File) {
/* 155 */       URL u = ((File)o).toURL();
/* 156 */       return sourceFromURL(parent, cfg, refName, xmlVersion, u, null);
/*     */     } 
/* 159 */     throw new IllegalArgumentException("Unrecognized input argument type for sourceFrom(): " + o.getClass());
/*     */   }
/*     */   
/*     */   public static Reader constructOptimizedReader(ReaderConfig cfg, InputStream in, boolean isXml11, String encoding) throws XMLStreamException {
/*     */     BaseReader r;
/* 174 */     int inputBufLen = cfg.getInputBufferLength();
/* 175 */     String normEnc = CharsetNames.normalize(encoding);
/* 179 */     boolean recycleBuffer = true;
/* 180 */     if (normEnc == "UTF-8") {
/* 181 */       r = new UTF8Reader(cfg, in, cfg.allocFullBBuffer(inputBufLen), 0, 0, recycleBuffer);
/* 182 */     } else if (normEnc == "ISO-8859-1") {
/* 183 */       r = new ISOLatinReader(cfg, in, cfg.allocFullBBuffer(inputBufLen), 0, 0, recycleBuffer);
/* 184 */     } else if (normEnc == "US-ASCII") {
/* 185 */       r = new AsciiReader(cfg, in, cfg.allocFullBBuffer(inputBufLen), 0, 0, recycleBuffer);
/* 186 */     } else if (normEnc.startsWith("UTF-32")) {
/* 187 */       boolean isBE = (normEnc == "UTF-32BE");
/* 188 */       r = new UTF32Reader(cfg, in, cfg.allocFullBBuffer(inputBufLen), 0, 0, recycleBuffer, isBE);
/*     */     } else {
/*     */       try {
/* 191 */         return new InputStreamReader(in, encoding);
/* 192 */       } catch (UnsupportedEncodingException ex) {
/* 193 */         throw new XMLStreamException("[unsupported encoding]: " + ex);
/*     */       } 
/*     */     } 
/* 197 */     if (isXml11)
/* 198 */       r.setXmlCompliancy(272); 
/* 201 */     return r;
/*     */   }
/*     */   
/*     */   private static WstxInputSource sourceFromSS(WstxInputSource parent, ReaderConfig cfg, String refName, int xmlVersion, StreamSource ssrc) throws IOException, XMLStreamException {
/*     */     InputBootstrapper bs;
/* 216 */     Reader r = ssrc.getReader();
/* 217 */     String pubId = ssrc.getPublicId();
/* 218 */     String sysId = ssrc.getSystemId();
/* 219 */     URL ctxt = (parent == null) ? null : parent.getSource();
/* 220 */     URL url = (sysId == null || sysId.length() == 0) ? null : URLUtil.urlFromSystemId(sysId, ctxt);
/* 223 */     if (r == null) {
/* 224 */       InputStream in = ssrc.getInputStream();
/* 225 */       if (in == null) {
/* 226 */         if (url == null)
/* 227 */           throw new IllegalArgumentException("Can not create Stax reader for a StreamSource -- neither reader, input stream nor system id was set."); 
/* 229 */         in = URLUtil.inputStreamFromURL(url);
/*     */       } 
/* 231 */       bs = StreamBootstrapper.getInstance(pubId, sysId, in);
/*     */     } else {
/* 233 */       bs = ReaderBootstrapper.getInstance(pubId, sysId, r, null);
/*     */     } 
/* 236 */     Reader r2 = bs.bootstrapInput(cfg, false, xmlVersion);
/* 237 */     return InputSourceFactory.constructEntitySource(cfg, parent, refName, bs, pubId, sysId, xmlVersion, (url == null) ? ctxt : url, r2);
/*     */   }
/*     */   
/*     */   private static WstxInputSource sourceFromURL(WstxInputSource parent, ReaderConfig cfg, String refName, int xmlVersion, URL url, String pubId) throws IOException, XMLStreamException {
/* 254 */     InputStream in = URLUtil.inputStreamFromURL(url);
/* 255 */     String sysId = url.toExternalForm();
/* 256 */     StreamBootstrapper bs = StreamBootstrapper.getInstance(pubId, sysId, in);
/* 257 */     Reader r = bs.bootstrapInput(cfg, false, xmlVersion);
/* 258 */     return InputSourceFactory.constructEntitySource(cfg, parent, refName, bs, pubId, sysId, xmlVersion, url, r);
/*     */   }
/*     */   
/*     */   public static WstxInputSource sourceFromString(WstxInputSource parent, ReaderConfig cfg, String refName, int xmlVersion, String refContent) throws IOException, XMLStreamException {
/* 280 */     return sourceFromR(parent, cfg, refName, xmlVersion, new StringReader(refContent), null, refName);
/*     */   }
/*     */   
/*     */   private static WstxInputSource sourceFromIS(WstxInputSource parent, ReaderConfig cfg, String refName, int xmlVersion, InputStream is, String pubId, String sysId) throws IOException, XMLStreamException {
/* 292 */     StreamBootstrapper bs = StreamBootstrapper.getInstance(pubId, sysId, is);
/* 293 */     Reader r = bs.bootstrapInput(cfg, false, xmlVersion);
/* 294 */     URL ctxt = parent.getSource();
/* 297 */     if (sysId != null && sysId.length() > 0)
/* 298 */       ctxt = URLUtil.urlFromSystemId(sysId, ctxt); 
/* 300 */     return InputSourceFactory.constructEntitySource(cfg, parent, refName, bs, pubId, sysId, xmlVersion, ctxt, r);
/*     */   }
/*     */   
/*     */   private static WstxInputSource sourceFromR(WstxInputSource parent, ReaderConfig cfg, String refName, int xmlVersion, Reader r, String pubId, String sysId) throws IOException, XMLStreamException {
/* 313 */     ReaderBootstrapper rbs = ReaderBootstrapper.getInstance(pubId, sysId, r, null);
/* 315 */     Reader r2 = rbs.bootstrapInput(cfg, false, xmlVersion);
/* 316 */     URL ctxt = (parent == null) ? null : parent.getSource();
/* 317 */     if (sysId != null && sysId.length() > 0)
/* 318 */       ctxt = URLUtil.urlFromSystemId(sysId, ctxt); 
/* 320 */     return InputSourceFactory.constructEntitySource(cfg, parent, refName, rbs, pubId, sysId, xmlVersion, ctxt, r2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\DefaultInputResolver.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */