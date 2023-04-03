/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class UnionCombiner extends NodeCombiner {
/*     */   public ConfigurationNode combine(ConfigurationNode node1, ConfigurationNode node2) {
/* 131 */     ViewNode result = createViewNode();
/* 132 */     result.setName(node1.getName());
/* 133 */     result.appendAttributes(node1);
/* 134 */     result.appendAttributes(node2);
/* 137 */     List children2 = new LinkedList(node2.getChildren());
/* 138 */     for (Iterator iterator1 = node1.getChildren().iterator(); iterator1.hasNext(); ) {
/* 140 */       ConfigurationNode child1 = iterator1.next();
/* 141 */       ConfigurationNode child2 = findCombineNode(node1, node2, child1, children2);
/* 143 */       if (child2 != null) {
/* 145 */         result.addChild(combine(child1, child2));
/* 146 */         children2.remove(child2);
/*     */         continue;
/*     */       } 
/* 150 */       result.addChild(child1);
/*     */     } 
/* 155 */     for (Iterator it = children2.iterator(); it.hasNext();)
/* 157 */       result.addChild(it.next()); 
/* 160 */     return result;
/*     */   }
/*     */   
/*     */   protected ConfigurationNode findCombineNode(ConfigurationNode node1, ConfigurationNode node2, ConfigurationNode child, List children) {
/* 199 */     if (child.getValue() == null && !isListNode(child) && node1.getChildrenCount(child.getName()) == 1 && node2.getChildrenCount(child.getName()) == 1) {
/* 203 */       ConfigurationNode child2 = node2.getChildren(child.getName()).iterator().next();
/* 205 */       if (child2.getValue() == null)
/* 207 */         return child2; 
/*     */     } 
/* 210 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\UnionCombiner.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */