/*    */ package org.apache.commons.configuration.tree.xpath;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*    */ import org.apache.commons.jxpath.ri.QName;
/*    */ import org.apache.commons.jxpath.ri.model.NodePointer;
/*    */ 
/*    */ class ConfigurationNodeIteratorAttribute extends ConfigurationNodeIteratorBase {
/*    */   private static final String WILDCARD = "*";
/*    */   
/*    */   public ConfigurationNodeIteratorAttribute(NodePointer parent, QName name) {
/* 46 */     super(parent, false);
/* 47 */     initSubNodeList(createSubNodeList((ConfigurationNode)parent.getNode(), name));
/*    */   }
/*    */   
/*    */   protected List createSubNodeList(ConfigurationNode node, QName name) {
/* 60 */     if (name.getPrefix() != null)
/* 63 */       return Collections.EMPTY_LIST; 
/* 66 */     List result = new ArrayList();
/* 67 */     if (!"*".equals(name.getName())) {
/* 69 */       result.addAll(node.getAttributes(name.getName()));
/*    */     } else {
/* 73 */       result.addAll(node.getAttributes());
/*    */     } 
/* 76 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\xpath\ConfigurationNodeIteratorAttribute.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */