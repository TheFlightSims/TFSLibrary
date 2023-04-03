/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import org.java.plugin.registry.MatchingRule;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ final class ManifestInfoHandler extends BaseHandler {
/*  30 */   private ModelManifestInfo manifest = null;
/*     */   
/*     */   ManifestInfoHandler(EntityResolver anEntityResolver) {
/*  33 */     super(anEntityResolver);
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/*  44 */     if (this.log.isDebugEnabled())
/*  45 */       this.log.debug("startElement - [" + uri + "]/[" + localName + "]/[" + qName + "]"); 
/*  48 */     String name = qName;
/*  49 */     if ("plugin".equals(name)) {
/*  50 */       if (this.manifest != null)
/*  51 */         throw new SAXException("unexpected [" + name + "] element (manifest already defined)"); 
/*  54 */       this.manifest = new ModelManifestInfo();
/*  55 */       this.manifest.setId(attributes.getValue("id"));
/*  56 */       this.manifest.setVersion(attributes.getValue("version"));
/*  57 */       this.manifest.setVendor(attributes.getValue("vendor"));
/*  58 */     } else if ("plugin-fragment".equals(name)) {
/*  59 */       if (this.manifest != null)
/*  60 */         throw new SAXException("unexpected [" + name + "] element (manifest already defined)"); 
/*  63 */       this.manifest = new ModelManifestInfo();
/*  64 */       this.manifest.setId(attributes.getValue("id"));
/*  65 */       this.manifest.setVersion(attributes.getValue("version"));
/*  66 */       this.manifest.setVendor(attributes.getValue("vendor"));
/*  67 */       this.manifest.setPluginId(attributes.getValue("plugin-id"));
/*  68 */       if (attributes.getValue("plugin-version") != null)
/*  69 */         this.manifest.setPluginVersion(attributes.getValue("plugin-version")); 
/*  72 */       if (attributes.getValue("match") != null) {
/*  73 */         this.manifest.setMatchingRule(MatchingRule.fromCode(attributes.getValue("match")));
/*     */       } else {
/*  76 */         this.manifest.setMatchingRule(MatchingRule.COMPATIBLE);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String localName, String qName) {
/*  90 */     if (this.log.isDebugEnabled())
/*  91 */       this.log.debug("endElement - [" + uri + "]/[" + localName + "]/[" + qName + "]"); 
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) {}
/*     */   
/*     */   ModelManifestInfo getResult() {
/* 106 */     return this.manifest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ManifestInfoHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */