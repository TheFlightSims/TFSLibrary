/*    */ package org.apache.commons.configuration.tree;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public final class TreeUtils {
/*    */   public static void printTree(PrintStream stream, ConfigurationNode result) {
/* 44 */     if (stream != null)
/* 46 */       printTree(stream, "", result); 
/*    */   }
/*    */   
/*    */   private static void printTree(PrintStream stream, String indent, ConfigurationNode result) {
/* 52 */     StringBuffer buffer = (new StringBuffer(indent)).append("<").append(result.getName());
/* 53 */     Iterator iter = result.getAttributes().iterator();
/* 54 */     while (iter.hasNext()) {
/* 56 */       ConfigurationNode node = iter.next();
/* 57 */       buffer.append(" ").append(node.getName()).append("='").append(node.getValue()).append("'");
/*    */     } 
/* 59 */     buffer.append(">");
/* 60 */     stream.print(buffer.toString());
/* 61 */     if (result.getValue() != null)
/* 63 */       stream.print(result.getValue()); 
/* 65 */     boolean newline = false;
/* 66 */     if (result.getChildrenCount() > 0) {
/* 68 */       stream.print("\n");
/* 69 */       iter = result.getChildren().iterator();
/* 70 */       while (iter.hasNext())
/* 72 */         printTree(stream, indent + "  ", iter.next()); 
/* 74 */       newline = true;
/*    */     } 
/* 76 */     if (newline)
/* 78 */       stream.print(indent); 
/* 80 */     stream.println("</" + result.getName() + ">");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\TreeUtils.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */