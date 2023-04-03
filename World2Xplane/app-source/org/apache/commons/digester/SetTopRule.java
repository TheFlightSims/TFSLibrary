/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.apache.commons.beanutils.MethodUtils;
/*     */ 
/*     */ public class SetTopRule extends Rule {
/*     */   protected String methodName;
/*     */   
/*     */   protected String paramType;
/*     */   
/*     */   protected boolean useExactMatch;
/*     */   
/*     */   public SetTopRule(Digester digester, String methodName) {
/*  55 */     this(methodName);
/*     */   }
/*     */   
/*     */   public SetTopRule(Digester digester, String methodName, String paramType) {
/*  76 */     this(methodName, paramType);
/*     */   }
/*     */   
/*     */   public SetTopRule(String methodName) {
/*  89 */     this(methodName, (String)null);
/*     */   }
/*     */   
/*     */   public SetTopRule(String methodName, String paramType) {
/* 118 */     this.methodName = null;
/* 124 */     this.paramType = null;
/* 129 */     this.useExactMatch = false;
/*     */     this.methodName = methodName;
/*     */     this.paramType = paramType;
/*     */   }
/*     */   
/*     */   public boolean isExactMatch() {
/* 156 */     return this.useExactMatch;
/*     */   }
/*     */   
/*     */   public void setExactMatch(boolean useExactMatch) {
/* 169 */     this.useExactMatch = useExactMatch;
/*     */   }
/*     */   
/*     */   public void end() throws Exception {
/* 178 */     Object child = this.digester.peek(0);
/* 179 */     Object parent = this.digester.peek(1);
/* 181 */     if (this.digester.log.isDebugEnabled())
/* 182 */       if (child == null) {
/* 183 */         this.digester.log.debug("[SetTopRule]{" + this.digester.match + "} Call [NULL CHILD]." + this.methodName + "(" + parent + ")");
/*     */       } else {
/* 187 */         this.digester.log.debug("[SetTopRule]{" + this.digester.match + "} Call " + child.getClass().getName() + "." + this.methodName + "(" + parent + ")");
/*     */       }  
/* 194 */     Class[] paramTypes = new Class[1];
/* 195 */     if (this.paramType != null) {
/* 196 */       paramTypes[0] = this.digester.getClassLoader().loadClass(this.paramType);
/*     */     } else {
/* 199 */       paramTypes[0] = parent.getClass();
/*     */     } 
/* 202 */     if (this.useExactMatch) {
/* 204 */       MethodUtils.invokeExactMethod(child, this.methodName, new Object[] { parent }, paramTypes);
/*     */     } else {
/* 209 */       MethodUtils.invokeMethod(child, this.methodName, new Object[] { parent }, paramTypes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 221 */     StringBuffer sb = new StringBuffer("SetTopRule[");
/* 222 */     sb.append("methodName=");
/* 223 */     sb.append(this.methodName);
/* 224 */     sb.append(", paramType=");
/* 225 */     sb.append(this.paramType);
/* 226 */     sb.append("]");
/* 227 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\SetTopRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */