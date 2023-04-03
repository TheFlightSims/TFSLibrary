/*     */ package org.apache.commons.configuration.tree.xpath;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.jxpath.ri.QName;
/*     */ import org.apache.commons.jxpath.ri.compiler.NodeNameTest;
/*     */ import org.apache.commons.jxpath.ri.compiler.NodeTest;
/*     */ import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
/*     */ import org.apache.commons.jxpath.ri.model.NodePointer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ class ConfigurationNodeIteratorChildren extends ConfigurationNodeIteratorBase {
/*     */   public ConfigurationNodeIteratorChildren(NodePointer parent, NodeTest nodeTest, boolean reverse, NodePointer startsWith) {
/*  55 */     super(parent, reverse);
/*  56 */     ConfigurationNode root = (ConfigurationNode)parent.getNode();
/*  57 */     List childNodes = createSubNodeList(root, nodeTest);
/*  58 */     initSubNodeList(childNodes);
/*  59 */     if (startsWith != null)
/*  61 */       setStartOffset(findStartIndex(root, (ConfigurationNode)startsWith.getNode())); 
/*     */   }
/*     */   
/*     */   protected List createSubNodeList(ConfigurationNode node, NodeTest test) {
/*  77 */     List children = node.getChildren();
/*  79 */     if (test == null)
/*  81 */       return children; 
/*  85 */     if (test instanceof NodeNameTest) {
/*  87 */       NodeNameTest nameTest = (NodeNameTest)test;
/*  88 */       QName name = nameTest.getNodeName();
/*  89 */       if (name.getPrefix() == null) {
/*  91 */         if (nameTest.isWildcard())
/*  93 */           return children; 
/*  96 */         List result = new ArrayList();
/*  97 */         for (Iterator it = children.iterator(); it.hasNext(); ) {
/*  99 */           ConfigurationNode child = it.next();
/* 100 */           if (StringUtils.equals(name.getName(), child.getName()))
/* 102 */             result.add(child); 
/*     */         } 
/* 105 */         return result;
/*     */       } 
/* 109 */     } else if (test instanceof NodeTypeTest) {
/* 111 */       NodeTypeTest typeTest = (NodeTypeTest)test;
/* 112 */       if (typeTest.getNodeType() == 1 || typeTest.getNodeType() == 2)
/* 115 */         return children; 
/*     */     } 
/* 120 */     return Collections.EMPTY_LIST;
/*     */   }
/*     */   
/*     */   protected int findStartIndex(ConfigurationNode node, ConfigurationNode startNode) {
/* 134 */     for (int index = 0; index < node.getChildrenCount(); index++) {
/* 136 */       if (node.getChild(index) == startNode)
/* 138 */         return index; 
/*     */     } 
/* 142 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\xpath\ConfigurationNodeIteratorChildren.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */