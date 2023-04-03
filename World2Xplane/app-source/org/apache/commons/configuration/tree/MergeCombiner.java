/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MergeCombiner extends NodeCombiner {
/*     */   public ConfigurationNode combine(ConfigurationNode node1, ConfigurationNode node2) {
/*  57 */     ViewNode result = createViewNode();
/*  58 */     result.setName(node1.getName());
/*  59 */     result.setValue(node1.getValue());
/*  60 */     addAttributes(result, node1, node2);
/*  63 */     List children2 = new LinkedList(node2.getChildren());
/*  64 */     for (Iterator iterator1 = node1.getChildren().iterator(); iterator1.hasNext(); ) {
/*  66 */       ConfigurationNode child1 = iterator1.next();
/*  67 */       ConfigurationNode child2 = canCombine(node1, node2, child1, children2);
/*  68 */       if (child2 != null) {
/*  70 */         result.addChild(combine(child1, child2));
/*  71 */         children2.remove(child2);
/*     */         continue;
/*     */       } 
/*  75 */       result.addChild(child1);
/*     */     } 
/*  80 */     for (Iterator it = children2.iterator(); it.hasNext();)
/*  82 */       result.addChild(it.next()); 
/*  84 */     return result;
/*     */   }
/*     */   
/*     */   protected void addAttributes(ViewNode result, ConfigurationNode node1, ConfigurationNode node2) {
/* 100 */     result.appendAttributes(node1);
/* 101 */     for (Iterator it = node2.getAttributes().iterator(); it.hasNext(); ) {
/* 103 */       ConfigurationNode attr = it.next();
/* 104 */       if (node1.getAttributeCount(attr.getName()) == 0)
/* 106 */         result.addAttribute(attr); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ConfigurationNode canCombine(ConfigurationNode node1, ConfigurationNode node2, ConfigurationNode child, List children2) {
/* 124 */     List attrs1 = child.getAttributes();
/* 125 */     List nodes = new ArrayList();
/* 127 */     List children = node2.getChildren(child.getName());
/* 128 */     Iterator it = children.iterator();
/* 129 */     while (it.hasNext()) {
/* 131 */       ConfigurationNode node = it.next();
/* 132 */       Iterator iter = attrs1.iterator();
/* 133 */       while (iter.hasNext()) {
/* 135 */         ConfigurationNode attr1 = iter.next();
/* 136 */         List list2 = node.getAttributes(attr1.getName());
/* 137 */         if (list2.size() == 1 && !attr1.getValue().equals(((ConfigurationNode)list2.get(0)).getValue())) {
/* 140 */           node = null;
/*     */           break;
/*     */         } 
/*     */       } 
/* 144 */       if (node != null)
/* 146 */         nodes.add(node); 
/*     */     } 
/* 150 */     if (nodes.size() == 1)
/* 152 */       return nodes.get(0); 
/* 154 */     if (nodes.size() > 1 && !isListNode(child)) {
/* 156 */       Iterator iter = nodes.iterator();
/* 157 */       while (iter.hasNext())
/* 159 */         children2.remove(iter.next()); 
/*     */     } 
/* 163 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\MergeCombiner.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */