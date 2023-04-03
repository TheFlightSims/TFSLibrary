/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ public class HierarchicalConfigurationXMLReader extends ConfigurationXMLReader {
/*     */   private HierarchicalConfiguration configuration;
/*     */   
/*     */   public HierarchicalConfigurationXMLReader() {}
/*     */   
/*     */   public HierarchicalConfigurationXMLReader(HierarchicalConfiguration config) {
/*  64 */     this();
/*  65 */     setConfiguration(config);
/*     */   }
/*     */   
/*     */   public HierarchicalConfiguration getConfiguration() {
/*  75 */     return this.configuration;
/*     */   }
/*     */   
/*     */   public void setConfiguration(HierarchicalConfiguration config) {
/*  85 */     this.configuration = config;
/*     */   }
/*     */   
/*     */   public Configuration getParsedConfiguration() {
/*  95 */     return getConfiguration();
/*     */   }
/*     */   
/*     */   protected void processKeys() {
/* 103 */     getConfiguration().getRoot().visit(new SAXVisitor(), (ConfigurationKey)null);
/*     */   }
/*     */   
/*     */   class SAXVisitor extends HierarchicalConfiguration.NodeVisitor {
/*     */     private static final String ATTR_TYPE = "CDATA";
/*     */     
/*     */     private final HierarchicalConfigurationXMLReader this$0;
/*     */     
/*     */     public void visitAfterChildren(HierarchicalConfiguration.Node node, ConfigurationKey key) {
/* 124 */       if (!isAttributeNode(node))
/* 126 */         HierarchicalConfigurationXMLReader.this.fireElementEnd(nodeName(node)); 
/*     */     }
/*     */     
/*     */     public void visitBeforeChildren(HierarchicalConfiguration.Node node, ConfigurationKey key) {
/* 138 */       if (!isAttributeNode(node)) {
/* 140 */         HierarchicalConfigurationXMLReader.this.fireElementStart(nodeName(node), fetchAttributes(node));
/* 142 */         if (node.getValue() != null)
/* 144 */           HierarchicalConfigurationXMLReader.this.fireCharacters(node.getValue().toString()); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean terminate() {
/* 157 */       return (HierarchicalConfigurationXMLReader.this.getException() != null);
/*     */     }
/*     */     
/*     */     protected Attributes fetchAttributes(HierarchicalConfiguration.Node node) {
/* 168 */       AttributesImpl attrs = new AttributesImpl();
/* 170 */       for (Iterator it = node.getAttributes().iterator(); it.hasNext(); ) {
/* 172 */         HierarchicalConfiguration.Node child = it.next();
/* 173 */         if (child.getValue() != null) {
/* 175 */           String attr = child.getName();
/* 176 */           attrs.addAttribute("", attr, attr, "CDATA", child.getValue().toString());
/*     */         } 
/*     */       } 
/* 180 */       return attrs;
/*     */     }
/*     */     
/*     */     private String nodeName(HierarchicalConfiguration.Node node) {
/* 193 */       return (node.getName() == null) ? HierarchicalConfigurationXMLReader.this.getRootName() : node.getName();
/*     */     }
/*     */     
/*     */     private boolean isAttributeNode(HierarchicalConfiguration.Node node) {
/* 206 */       return node.isAttribute();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\HierarchicalConfigurationXMLReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */