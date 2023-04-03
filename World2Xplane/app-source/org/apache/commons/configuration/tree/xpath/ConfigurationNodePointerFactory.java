/*    */ package org.apache.commons.configuration.tree.xpath;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*    */ import org.apache.commons.jxpath.ri.QName;
/*    */ import org.apache.commons.jxpath.ri.model.NodePointer;
/*    */ import org.apache.commons.jxpath.ri.model.NodePointerFactory;
/*    */ 
/*    */ public class ConfigurationNodePointerFactory implements NodePointerFactory {
/*    */   public static final int CONFIGURATION_NODE_POINTER_FACTORY_ORDER = 200;
/*    */   
/*    */   public int getOrder() {
/* 46 */     return 200;
/*    */   }
/*    */   
/*    */   public NodePointer createNodePointer(QName name, Object bean, Locale locale) {
/* 60 */     if (bean instanceof ConfigurationNode)
/* 62 */       return new ConfigurationNodePointer((ConfigurationNode)bean, locale); 
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public NodePointer createNodePointer(NodePointer parent, QName name, Object bean) {
/* 80 */     if (bean instanceof ConfigurationNode)
/* 82 */       return new ConfigurationNodePointer(parent, (ConfigurationNode)bean); 
/* 85 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\xpath\ConfigurationNodePointerFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */