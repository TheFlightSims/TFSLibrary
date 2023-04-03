/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ViewNode extends DefaultConfigurationNode {
/*     */   public void addAttribute(ConfigurationNode attr) {
/*  51 */     ConfigurationNode parent = null;
/*  53 */     if (attr != null) {
/*  55 */       parent = attr.getParentNode();
/*  56 */       super.addAttribute(attr);
/*  57 */       attr.setParentNode(parent);
/*     */     } else {
/*  61 */       throw new IllegalArgumentException("Attribute node must not be null!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addChild(ConfigurationNode child) {
/*  73 */     ConfigurationNode parent = null;
/*  75 */     if (child != null) {
/*  77 */       parent = child.getParentNode();
/*  78 */       super.addChild(child);
/*  79 */       child.setParentNode(parent);
/*     */     } else {
/*  83 */       throw new IllegalArgumentException("Child node must not be null!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void appendAttributes(ConfigurationNode source) {
/*  94 */     if (source != null)
/*  96 */       for (Iterator it = source.getAttributes().iterator(); it.hasNext();)
/*  98 */         addAttribute(it.next());  
/*     */   }
/*     */   
/*     */   public void appendChildren(ConfigurationNode source) {
/* 110 */     if (source != null)
/* 112 */       for (Iterator it = source.getChildren().iterator(); it.hasNext();)
/* 114 */         addChild(it.next());  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\ViewNode.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */