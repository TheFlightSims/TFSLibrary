/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.net.URL;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.util.IoUtil;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ final class ManifestParser {
/*  42 */   static Log log = LogFactory.getLog(ManifestParser.class);
/*     */   
/*  44 */   static final String PLUGIN_DTD_1_0 = loadPluginDtd("1_0");
/*     */   
/*     */   private final SAXParserFactory parserFactory;
/*     */   
/*     */   private final EntityResolver entityResolver;
/*     */   
/*     */   private static String loadPluginDtd(String version) {
/*     */     try {
/*  48 */       Reader in = new InputStreamReader(PluginRegistryImpl.class.getResourceAsStream("plugin_" + version + ".dtd"), "UTF-8");
/*     */       try {
/*  52 */         StringBuilder sBuf = new StringBuilder();
/*  53 */         char[] cBuf = new char[64];
/*     */         int read;
/*  55 */         while ((read = in.read(cBuf)) != -1)
/*  56 */           sBuf.append(cBuf, 0, read); 
/*  58 */         return sBuf.toString();
/*     */       } finally {
/*  60 */         in.close();
/*     */       } 
/*  62 */     } catch (IOException ioe) {
/*  63 */       log.error("can't read plug-in DTD file of version " + version, ioe);
/*  65 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static EntityResolver getDtdEntityResolver() {
/*  69 */     return new EntityResolver() {
/*     */         public InputSource resolveEntity(String publicId, String systemId) {
/*  72 */           if (publicId == null) {
/*  73 */             ManifestParser.log.debug("can't resolve entity, public ID is NULL, systemId=" + systemId);
/*  74 */             return null;
/*     */           } 
/*  76 */           if (ManifestParser.PLUGIN_DTD_1_0 == null)
/*  77 */             return null; 
/*  79 */           if (publicId.equals("-//JPF//Java Plug-in Manifest 1.0") || publicId.equals("-//JPF//Java Plug-in Manifest 0.7") || publicId.equals("-//JPF//Java Plug-in Manifest 0.6") || publicId.equals("-//JPF//Java Plug-in Manifest 0.5") || publicId.equals("-//JPF//Java Plug-in Manifest 0.4") || publicId.equals("-//JPF//Java Plug-in Manifest 0.3") || publicId.equals("-//JPF//Java Plug-in Manifest 0.2")) {
/*  86 */             if (ManifestParser.log.isDebugEnabled())
/*  87 */               ManifestParser.log.debug("entity resolved to plug-in manifest DTD, publicId=" + publicId + ", systemId=" + systemId); 
/*  90 */             return new InputSource(new StringReader(ManifestParser.PLUGIN_DTD_1_0));
/*     */           } 
/*  92 */           if (ManifestParser.log.isDebugEnabled())
/*  93 */             ManifestParser.log.debug("entity not resolved, publicId=" + publicId + ", systemId=" + systemId); 
/*  96 */           return null;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   ManifestParser(boolean isValidating) {
/* 105 */     this.parserFactory = SAXParserFactory.newInstance();
/* 106 */     this.parserFactory.setValidating(isValidating);
/* 107 */     this.entityResolver = isValidating ? getDtdEntityResolver() : null;
/* 108 */     log.info("got SAX parser factory - " + this.parserFactory);
/*     */   }
/*     */   
/*     */   ModelPluginManifest parseManifest(URL url) throws ParserConfigurationException, SAXException, IOException {
/* 113 */     ManifestHandler handler = new ManifestHandler(this.entityResolver);
/* 116 */     InputStream strm = IoUtil.getResourceInputStream(url);
/*     */     try {
/* 118 */       this.parserFactory.newSAXParser().parse(strm, handler);
/*     */     } finally {
/* 120 */       strm.close();
/*     */     } 
/* 122 */     ModelPluginManifest result = handler.getResult();
/* 123 */     result.setLocation(url);
/* 124 */     return result;
/*     */   }
/*     */   
/*     */   ModelManifestInfo parseManifestInfo(URL url) throws ParserConfigurationException, SAXException, IOException {
/* 129 */     ManifestInfoHandler handler = new ManifestInfoHandler(this.entityResolver);
/* 132 */     InputStream strm = IoUtil.getResourceInputStream(url);
/*     */     try {
/* 134 */       this.parserFactory.newSAXParser().parse(strm, handler);
/*     */     } finally {
/* 136 */       strm.close();
/*     */     } 
/* 138 */     return handler.getResult();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ManifestParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */