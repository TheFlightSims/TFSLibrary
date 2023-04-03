/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.configuration.ConfigurationRuntimeException;
/*     */ 
/*     */ public class DefaultConfigurationNode implements ConfigurationNode, Cloneable {
/*     */   private SubNodes children;
/*     */   
/*     */   private SubNodes attributes;
/*     */   
/*     */   private ConfigurationNode parent;
/*     */   
/*     */   private Object value;
/*     */   
/*     */   private Object reference;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private boolean attribute;
/*     */   
/*     */   public DefaultConfigurationNode() {
/*  67 */     this(null);
/*     */   }
/*     */   
/*     */   public DefaultConfigurationNode(String name) {
/*  78 */     this(name, null);
/*     */   }
/*     */   
/*     */   public DefaultConfigurationNode(String name, Object value) {
/*  90 */     setName(name);
/*  91 */     setValue(value);
/*  92 */     initSubNodes();
/*     */   }
/*     */   
/*     */   public String getName() {
/* 102 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 112 */     checkState();
/* 113 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Object getValue() {
/* 123 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Object val) {
/* 133 */     this.value = val;
/*     */   }
/*     */   
/*     */   public Object getReference() {
/* 143 */     return this.reference;
/*     */   }
/*     */   
/*     */   public void setReference(Object reference) {
/* 153 */     this.reference = reference;
/*     */   }
/*     */   
/*     */   public ConfigurationNode getParentNode() {
/* 163 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void setParentNode(ConfigurationNode parent) {
/* 173 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public void addChild(ConfigurationNode child) {
/* 183 */     this.children.addNode(child);
/* 184 */     child.setAttribute(false);
/* 185 */     child.setParentNode(this);
/*     */   }
/*     */   
/*     */   public List getChildren() {
/* 195 */     return this.children.getSubNodes();
/*     */   }
/*     */   
/*     */   public int getChildrenCount() {
/* 205 */     return this.children.getSubNodes().size();
/*     */   }
/*     */   
/*     */   public List getChildren(String name) {
/* 216 */     return this.children.getSubNodes(name);
/*     */   }
/*     */   
/*     */   public int getChildrenCount(String name) {
/* 228 */     return this.children.getSubNodes(name).size();
/*     */   }
/*     */   
/*     */   public ConfigurationNode getChild(int index) {
/* 239 */     return this.children.getNode(index);
/*     */   }
/*     */   
/*     */   public boolean removeChild(ConfigurationNode child) {
/* 250 */     return this.children.removeNode(child);
/*     */   }
/*     */   
/*     */   public boolean removeChild(String childName) {
/* 261 */     return this.children.removeNodes(childName);
/*     */   }
/*     */   
/*     */   public void removeChildren() {
/* 269 */     this.children.clear();
/*     */   }
/*     */   
/*     */   public boolean isAttribute() {
/* 279 */     return this.attribute;
/*     */   }
/*     */   
/*     */   public void setAttribute(boolean f) {
/* 290 */     checkState();
/* 291 */     this.attribute = f;
/*     */   }
/*     */   
/*     */   public void addAttribute(ConfigurationNode attr) {
/* 301 */     this.attributes.addNode(attr);
/* 302 */     attr.setAttribute(true);
/* 303 */     attr.setParentNode(this);
/*     */   }
/*     */   
/*     */   public List getAttributes() {
/* 314 */     return this.attributes.getSubNodes();
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/* 324 */     return this.attributes.getSubNodes().size();
/*     */   }
/*     */   
/*     */   public List getAttributes(String name) {
/* 335 */     return this.attributes.getSubNodes(name);
/*     */   }
/*     */   
/*     */   public int getAttributeCount(String name) {
/* 346 */     return getAttributes(name).size();
/*     */   }
/*     */   
/*     */   public boolean removeAttribute(ConfigurationNode node) {
/* 357 */     return this.attributes.removeNode(node);
/*     */   }
/*     */   
/*     */   public boolean removeAttribute(String name) {
/* 368 */     return this.attributes.removeNodes(name);
/*     */   }
/*     */   
/*     */   public ConfigurationNode getAttribute(int index) {
/* 379 */     return this.attributes.getNode(index);
/*     */   }
/*     */   
/*     */   public void removeAttributes() {
/* 387 */     this.attributes.clear();
/*     */   }
/*     */   
/*     */   public boolean isDefined() {
/* 398 */     return (getValue() != null || getChildrenCount() > 0 || getAttributeCount() > 0);
/*     */   }
/*     */   
/*     */   public void visit(ConfigurationNodeVisitor visitor) {
/* 409 */     if (visitor == null)
/* 411 */       throw new IllegalArgumentException("Visitor must not be null!"); 
/* 414 */     if (!visitor.terminate()) {
/* 416 */       visitor.visitBeforeChildren(this);
/* 417 */       this.children.visit(visitor);
/* 418 */       this.attributes.visit(visitor);
/* 419 */       visitor.visitAfterChildren(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 433 */       DefaultConfigurationNode copy = (DefaultConfigurationNode)super.clone();
/* 435 */       copy.initSubNodes();
/* 436 */       return copy;
/* 438 */     } catch (CloneNotSupportedException cex) {
/* 441 */       throw new ConfigurationRuntimeException("Cannot clone " + getClass());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void checkState() {
/* 452 */     if (getParentNode() != null)
/* 454 */       throw new IllegalStateException("Node cannot be modified when added to a parent!"); 
/*     */   }
/*     */   
/*     */   protected SubNodes createSubNodes(boolean attributes) {
/* 469 */     return new SubNodes();
/*     */   }
/*     */   
/*     */   protected void removeReference() {}
/*     */   
/*     */   private void initSubNodes() {
/* 488 */     this.children = createSubNodes(false);
/* 489 */     this.attributes = createSubNodes(true);
/*     */   }
/*     */   
/*     */   protected static class SubNodes {
/*     */     private List nodes;
/*     */     
/*     */     private Map namedNodes;
/*     */     
/*     */     public void addNode(ConfigurationNode node) {
/* 510 */       if (node == null || node.getName() == null)
/* 512 */         throw new IllegalArgumentException("Node to add must have a defined name!"); 
/* 515 */       node.setParentNode(null);
/* 517 */       if (this.nodes == null) {
/* 519 */         this.nodes = new ArrayList();
/* 520 */         this.namedNodes = new HashMap();
/*     */       } 
/* 523 */       this.nodes.add(node);
/* 524 */       List lst = (List)this.namedNodes.get(node.getName());
/* 525 */       if (lst == null) {
/* 527 */         lst = new LinkedList();
/* 528 */         this.namedNodes.put(node.getName(), lst);
/*     */       } 
/* 530 */       lst.add(node);
/*     */     }
/*     */     
/*     */     public boolean removeNode(ConfigurationNode node) {
/* 541 */       if (this.nodes != null && node != null && this.nodes.contains(node)) {
/* 543 */         detachNode(node);
/* 544 */         this.nodes.remove(node);
/* 546 */         List lst = (List)this.namedNodes.get(node.getName());
/* 547 */         if (lst != null) {
/* 549 */           lst.remove(node);
/* 550 */           if (lst.isEmpty())
/* 552 */             this.namedNodes.remove(node.getName()); 
/*     */         } 
/* 555 */         return true;
/*     */       } 
/* 560 */       return false;
/*     */     }
/*     */     
/*     */     public boolean removeNodes(String name) {
/* 572 */       if (this.nodes != null && name != null) {
/* 574 */         List lst = (List)this.namedNodes.remove(name);
/* 575 */         if (lst != null) {
/* 577 */           detachNodes(lst);
/* 578 */           this.nodes.removeAll(lst);
/* 579 */           return true;
/*     */         } 
/*     */       } 
/* 582 */       return false;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 590 */       if (this.nodes != null) {
/* 592 */         detachNodes(this.nodes);
/* 593 */         this.nodes = null;
/* 594 */         this.namedNodes = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ConfigurationNode getNode(int index) {
/* 607 */       if (this.nodes == null)
/* 609 */         throw new IndexOutOfBoundsException("No sub nodes available!"); 
/* 611 */       return this.nodes.get(index);
/*     */     }
/*     */     
/*     */     public List getSubNodes() {
/* 622 */       return (this.nodes == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(this.nodes);
/*     */     }
/*     */     
/*     */     public List getSubNodes(String name) {
/*     */       List result;
/* 636 */       if (name == null)
/* 638 */         return getSubNodes(); 
/* 642 */       if (this.nodes == null) {
/* 644 */         result = null;
/*     */       } else {
/* 648 */         result = (List)this.namedNodes.get(name);
/*     */       } 
/* 651 */       return (result == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(result);
/*     */     }
/*     */     
/*     */     public void visit(ConfigurationNodeVisitor visitor) {
/* 662 */       if (this.nodes != null) {
/* 664 */         Iterator it = this.nodes.iterator();
/* 665 */         while (it.hasNext() && !visitor.terminate())
/* 667 */           ((ConfigurationNode)it.next()).visit(visitor); 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void detachNode(ConfigurationNode subNode) {
/* 681 */       subNode.setParentNode(null);
/* 682 */       if (subNode instanceof DefaultConfigurationNode)
/* 684 */         ((DefaultConfigurationNode)subNode).removeReference(); 
/*     */     }
/*     */     
/*     */     protected void detachNodes(Collection subNodes) {
/* 696 */       for (Iterator it = subNodes.iterator(); it.hasNext();)
/* 698 */         detachNode(it.next()); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\DefaultConfigurationNode.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */