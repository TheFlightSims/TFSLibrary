/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.apache.commons.beanutils.MethodUtils;
/*     */ 
/*     */ public class SetNextRule extends Rule {
/*     */   protected String methodName;
/*     */   
/*     */   protected String paramType;
/*     */   
/*     */   protected boolean useExactMatch;
/*     */   
/*     */   public SetNextRule(Digester digester, String methodName) {
/*  64 */     this(methodName);
/*     */   }
/*     */   
/*     */   public SetNextRule(Digester digester, String methodName, String paramType) {
/*  85 */     this(methodName, paramType);
/*     */   }
/*     */   
/*     */   public SetNextRule(String methodName) {
/*  98 */     this(methodName, (String)null);
/*     */   }
/*     */   
/*     */   public SetNextRule(String methodName, String paramType) {
/* 127 */     this.methodName = null;
/* 133 */     this.paramType = null;
/* 138 */     this.useExactMatch = false;
/*     */     this.methodName = methodName;
/*     */     this.paramType = paramType;
/*     */   }
/*     */   
/*     */   public boolean isExactMatch() {
/* 165 */     return this.useExactMatch;
/*     */   }
/*     */   
/*     */   public void setExactMatch(boolean useExactMatch) {
/* 178 */     this.useExactMatch = useExactMatch;
/*     */   }
/*     */   
/*     */   public void end() throws Exception {
/* 187 */     Object child = this.digester.peek(0);
/* 188 */     Object parent = this.digester.peek(1);
/* 189 */     if (this.digester.log.isDebugEnabled())
/* 190 */       if (parent == null) {
/* 191 */         this.digester.log.debug("[SetNextRule]{" + this.digester.match + "} Call [NULL PARENT]." + this.methodName + "(" + child + ")");
/*     */       } else {
/* 195 */         this.digester.log.debug("[SetNextRule]{" + this.digester.match + "} Call " + parent.getClass().getName() + "." + this.methodName + "(" + child + ")");
/*     */       }  
/* 202 */     Class[] paramTypes = new Class[1];
/* 203 */     if (this.paramType != null) {
/* 204 */       paramTypes[0] = this.digester.getClassLoader().loadClass(this.paramType);
/*     */     } else {
/* 207 */       paramTypes[0] = child.getClass();
/*     */     } 
/* 210 */     if (this.useExactMatch) {
/* 212 */       MethodUtils.invokeExactMethod(parent, this.methodName, new Object[] { child }, paramTypes);
/*     */     } else {
/* 217 */       MethodUtils.invokeMethod(parent, this.methodName, new Object[] { child }, paramTypes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 229 */     StringBuffer sb = new StringBuffer("SetNextRule[");
/* 230 */     sb.append("methodName=");
/* 231 */     sb.append(this.methodName);
/* 232 */     sb.append(", paramType=");
/* 233 */     sb.append(this.paramType);
/* 234 */     sb.append("]");
/* 235 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\SetNextRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */