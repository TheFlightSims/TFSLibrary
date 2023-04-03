/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.apache.commons.beanutils.MethodUtils;
/*     */ 
/*     */ public class SetRootRule extends Rule {
/*     */   protected String methodName;
/*     */   
/*     */   protected String paramType;
/*     */   
/*     */   protected boolean useExactMatch;
/*     */   
/*     */   public SetRootRule(Digester digester, String methodName) {
/*  56 */     this(methodName);
/*     */   }
/*     */   
/*     */   public SetRootRule(Digester digester, String methodName, String paramType) {
/*  77 */     this(methodName, paramType);
/*     */   }
/*     */   
/*     */   public SetRootRule(String methodName) {
/*  90 */     this(methodName, (String)null);
/*     */   }
/*     */   
/*     */   public SetRootRule(String methodName, String paramType) {
/* 118 */     this.methodName = null;
/* 124 */     this.paramType = null;
/* 129 */     this.useExactMatch = false;
/*     */     this.methodName = methodName;
/*     */     this.paramType = paramType;
/*     */   }
/*     */   
/*     */   public boolean isExactMatch() {
/* 157 */     return this.useExactMatch;
/*     */   }
/*     */   
/*     */   public void setExactMatch(boolean useExactMatch) {
/* 171 */     this.useExactMatch = useExactMatch;
/*     */   }
/*     */   
/*     */   public void end() throws Exception {
/* 180 */     Object child = this.digester.peek(0);
/* 181 */     Object parent = this.digester.root;
/* 182 */     if (this.digester.log.isDebugEnabled())
/* 183 */       if (parent == null) {
/* 184 */         this.digester.log.debug("[SetRootRule]{" + this.digester.match + "} Call [NULL ROOT]." + this.methodName + "(" + child + ")");
/*     */       } else {
/* 188 */         this.digester.log.debug("[SetRootRule]{" + this.digester.match + "} Call " + parent.getClass().getName() + "." + this.methodName + "(" + child + ")");
/*     */       }  
/* 195 */     Class[] paramTypes = new Class[1];
/* 196 */     if (this.paramType != null) {
/* 197 */       paramTypes[0] = this.digester.getClassLoader().loadClass(this.paramType);
/*     */     } else {
/* 200 */       paramTypes[0] = child.getClass();
/*     */     } 
/* 203 */     if (this.useExactMatch) {
/* 205 */       MethodUtils.invokeExactMethod(parent, this.methodName, new Object[] { child }, paramTypes);
/*     */     } else {
/* 210 */       MethodUtils.invokeMethod(parent, this.methodName, new Object[] { child }, paramTypes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 222 */     StringBuffer sb = new StringBuffer("SetRootRule[");
/* 223 */     sb.append("methodName=");
/* 224 */     sb.append(this.methodName);
/* 225 */     sb.append(", paramType=");
/* 226 */     sb.append(this.paramType);
/* 227 */     sb.append("]");
/* 228 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\SetRootRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */