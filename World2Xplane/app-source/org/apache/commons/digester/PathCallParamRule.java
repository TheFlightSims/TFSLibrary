/*    */ package org.apache.commons.digester;
/*    */ 
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public class PathCallParamRule extends Rule {
/*    */   protected int paramIndex;
/*    */   
/*    */   public PathCallParamRule(int paramIndex) {
/* 55 */     this.paramIndex = 0;
/*    */     this.paramIndex = paramIndex;
/*    */   }
/*    */   
/*    */   public void begin(String namespace, String name, Attributes attributes) throws Exception {
/* 73 */     String param = getDigester().getMatch();
/* 75 */     if (param != null) {
/* 76 */       Object[] parameters = (Object[])this.digester.peekParams();
/* 77 */       parameters[this.paramIndex] = param;
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 87 */     StringBuffer sb = new StringBuffer("PathCallParamRule[");
/* 88 */     sb.append("paramIndex=");
/* 89 */     sb.append(this.paramIndex);
/* 90 */     sb.append("]");
/* 91 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\PathCallParamRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */