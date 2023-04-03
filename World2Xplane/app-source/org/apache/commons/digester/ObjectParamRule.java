/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class ObjectParamRule extends Rule {
/*     */   protected String attributeName;
/*     */   
/*     */   protected int paramIndex;
/*     */   
/*     */   protected Object param;
/*     */   
/*     */   public ObjectParamRule(int paramIndex, Object param) {
/*  52 */     this(paramIndex, null, param);
/*     */   }
/*     */   
/*     */   public ObjectParamRule(int paramIndex, String attributeName, Object param) {
/*  76 */     this.attributeName = null;
/*  81 */     this.paramIndex = 0;
/*  86 */     this.param = null;
/*     */     this.paramIndex = paramIndex;
/*     */     this.attributeName = attributeName;
/*     */     this.param = param;
/*     */   }
/*     */   
/*     */   public void begin(String namespace, String name, Attributes attributes) throws Exception {
/*  98 */     Object anAttribute = null;
/*  99 */     Object[] parameters = (Object[])this.digester.peekParams();
/* 101 */     if (this.attributeName != null) {
/* 102 */       anAttribute = attributes.getValue(this.attributeName);
/* 103 */       if (anAttribute != null)
/* 104 */         parameters[this.paramIndex] = this.param; 
/*     */     } else {
/* 109 */       parameters[this.paramIndex] = this.param;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 117 */     StringBuffer sb = new StringBuffer("ObjectParamRule[");
/* 118 */     sb.append("paramIndex=");
/* 119 */     sb.append(this.paramIndex);
/* 120 */     sb.append(", attributeName=");
/* 121 */     sb.append(this.attributeName);
/* 122 */     sb.append(", param=");
/* 123 */     sb.append(this.param);
/* 124 */     sb.append("]");
/* 125 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\ObjectParamRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */