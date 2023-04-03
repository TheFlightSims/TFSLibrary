/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class OverrideCombiner extends NodeCombiner {
/*     */   public ConfigurationNode combine(ConfigurationNode node1, ConfigurationNode node2) {
/*  69 */     ViewNode result = createViewNode();
/*  70 */     result.setName(node1.getName());
/*  73 */     for (Iterator iterator1 = node1.getChildren().iterator(); iterator1.hasNext(); ) {
/*  75 */       ConfigurationNode child = iterator1.next();
/*  76 */       ConfigurationNode child2 = canCombine(node1, node2, child);
/*  77 */       if (child2 != null) {
/*  79 */         result.addChild(combine(child, child2));
/*     */         continue;
/*     */       } 
/*  83 */       result.addChild(child);
/*     */     } 
/*  89 */     for (Iterator it = node2.getChildren().iterator(); it.hasNext(); ) {
/*  91 */       ConfigurationNode child = it.next();
/*  92 */       if (node1.getChildrenCount(child.getName()) < 1)
/*  94 */         result.addChild(child); 
/*     */     } 
/*  99 */     addAttributes(result, node1, node2);
/* 100 */     result.setValue((node1.getValue() != null) ? node1.getValue() : node2.getValue());
/* 103 */     return result;
/*     */   }
/*     */   
/*     */   protected void addAttributes(ViewNode result, ConfigurationNode node1, ConfigurationNode node2) {
/* 119 */     result.appendAttributes(node1);
/* 120 */     for (Iterator it = node2.getAttributes().iterator(); it.hasNext(); ) {
/* 122 */       ConfigurationNode attr = it.next();
/* 123 */       if (node1.getAttributeCount(attr.getName()) == 0)
/* 125 */         result.addAttribute(attr); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ConfigurationNode canCombine(ConfigurationNode node1, ConfigurationNode node2, ConfigurationNode child) {
/* 145 */     if (node2.getChildrenCount(child.getName()) == 1 && node1.getChildrenCount(child.getName()) == 1 && !isListNode(child))
/* 149 */       return node2.getChildren(child.getName()).get(0); 
/* 154 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\OverrideCombiner.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */