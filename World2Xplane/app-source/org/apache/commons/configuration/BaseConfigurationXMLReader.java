/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ public class BaseConfigurationXMLReader extends ConfigurationXMLReader {
/*     */   private Configuration config;
/*     */   
/*     */   public BaseConfigurationXMLReader() {}
/*     */   
/*     */   public BaseConfigurationXMLReader(Configuration conf) {
/*  52 */     this();
/*  53 */     setConfiguration(conf);
/*     */   }
/*     */   
/*     */   public Configuration getConfiguration() {
/*  63 */     return this.config;
/*     */   }
/*     */   
/*     */   public void setConfiguration(Configuration conf) {
/*  73 */     this.config = conf;
/*     */   }
/*     */   
/*     */   public Configuration getParsedConfiguration() {
/*  83 */     return getConfiguration();
/*     */   }
/*     */   
/*     */   protected void processKeys() {
/*  94 */     fireElementStart(getRootName(), null);
/*  95 */     (new SAXConverter()).process(getConfiguration());
/*  96 */     fireElementEnd(getRootName());
/*     */   }
/*     */   
/*     */   class SAXConverter extends HierarchicalConfigurationConverter {
/*     */     private final BaseConfigurationXMLReader this$0;
/*     */     
/*     */     protected void elementStart(String name, Object value) {
/* 114 */       BaseConfigurationXMLReader.this.fireElementStart(name, null);
/* 115 */       if (value != null)
/* 117 */         BaseConfigurationXMLReader.this.fireCharacters(value.toString()); 
/*     */     }
/*     */     
/*     */     protected void elementEnd(String name) {
/* 128 */       BaseConfigurationXMLReader.this.fireElementEnd(name);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\BaseConfigurationXMLReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */