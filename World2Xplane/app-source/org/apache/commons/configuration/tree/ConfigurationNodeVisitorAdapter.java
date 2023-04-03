/*    */ package org.apache.commons.configuration.tree;
/*    */ 
/*    */ public class ConfigurationNodeVisitorAdapter implements ConfigurationNodeVisitor {
/*    */   public void visitBeforeChildren(ConfigurationNode node) {}
/*    */   
/*    */   public void visitAfterChildren(ConfigurationNode node) {}
/*    */   
/*    */   public boolean terminate() {
/* 60 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\ConfigurationNodeVisitorAdapter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */