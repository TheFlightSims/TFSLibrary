/*     */ package org.apache.commons.configuration.tree.xpath;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.jxpath.ri.QName;
/*     */ import org.apache.commons.jxpath.ri.compiler.NodeTest;
/*     */ import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
/*     */ import org.apache.commons.jxpath.ri.model.NodeIterator;
/*     */ import org.apache.commons.jxpath.ri.model.NodePointer;
/*     */ 
/*     */ class ConfigurationNodePointer extends NodePointer {
/*     */   private static final long serialVersionUID = -1087475639680007713L;
/*     */   
/*     */   private ConfigurationNode node;
/*     */   
/*     */   public ConfigurationNodePointer(ConfigurationNode node, Locale locale) {
/*  61 */     super(null, locale);
/*  62 */     this.node = node;
/*     */   }
/*     */   
/*     */   public ConfigurationNodePointer(NodePointer parent, ConfigurationNode node) {
/*  74 */     super(parent);
/*  75 */     this.node = node;
/*     */   }
/*     */   
/*     */   public boolean isLeaf() {
/*  86 */     return (this.node.getChildrenCount() < 1);
/*     */   }
/*     */   
/*     */   public boolean isCollection() {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public int getLength() {
/* 106 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isAttribute() {
/* 117 */     return this.node.isAttribute();
/*     */   }
/*     */   
/*     */   public QName getName() {
/* 127 */     return new QName(null, this.node.getName());
/*     */   }
/*     */   
/*     */   public Object getBaseValue() {
/* 138 */     return this.node;
/*     */   }
/*     */   
/*     */   public Object getImmediateNode() {
/* 148 */     return this.node;
/*     */   }
/*     */   
/*     */   public Object getValue() {
/* 158 */     return this.node.getValue();
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/* 168 */     this.node.setValue(value);
/*     */   }
/*     */   
/*     */   public int compareChildNodePointers(NodePointer pointer1, NodePointer pointer2) {
/* 181 */     ConfigurationNode node1 = (ConfigurationNode)pointer1.getBaseValue();
/* 182 */     ConfigurationNode node2 = (ConfigurationNode)pointer2.getBaseValue();
/* 185 */     if (node1.isAttribute() && !node2.isAttribute())
/* 187 */       return -1; 
/* 189 */     if (node2.isAttribute() && !node1.isAttribute())
/* 191 */       return 1; 
/* 197 */     List subNodes = node1.isAttribute() ? this.node.getAttributes() : this.node.getChildren();
/* 199 */     for (Iterator it = subNodes.iterator(); it.hasNext(); ) {
/* 201 */       ConfigurationNode child = it.next();
/* 202 */       if (child == node1)
/* 204 */         return -1; 
/* 206 */       if (child == node2)
/* 208 */         return 1; 
/*     */     } 
/* 211 */     return 0;
/*     */   }
/*     */   
/*     */   public NodeIterator attributeIterator(QName name) {
/* 223 */     return new ConfigurationNodeIteratorAttribute(this, name);
/*     */   }
/*     */   
/*     */   public NodeIterator childIterator(NodeTest test, boolean reverse, NodePointer startWith) {
/* 237 */     return new ConfigurationNodeIteratorChildren(this, test, reverse, startWith);
/*     */   }
/*     */   
/*     */   public boolean testNode(NodeTest test) {
/* 250 */     if (test instanceof NodeTypeTest && ((NodeTypeTest)test).getNodeType() == 2)
/* 253 */       return true; 
/* 255 */     return super.testNode(test);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\xpath\ConfigurationNodePointer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */