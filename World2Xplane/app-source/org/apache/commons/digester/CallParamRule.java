/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.apache.commons.collections.ArrayStack;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class CallParamRule extends Rule {
/*     */   protected String attributeName;
/*     */   
/*     */   protected int paramIndex;
/*     */   
/*     */   protected boolean fromStack;
/*     */   
/*     */   protected int stackIndex;
/*     */   
/*     */   protected ArrayStack bodyTextStack;
/*     */   
/*     */   public CallParamRule(Digester digester, int paramIndex) {
/*  69 */     this(paramIndex);
/*     */   }
/*     */   
/*     */   public CallParamRule(Digester digester, int paramIndex, String attributeName) {
/*  88 */     this(paramIndex, attributeName);
/*     */   }
/*     */   
/*     */   public CallParamRule(int paramIndex) {
/* 106 */     this(paramIndex, (String)null);
/*     */   }
/*     */   
/*     */   public CallParamRule(int paramIndex, String attributeName) {
/* 161 */     this.attributeName = null;
/* 167 */     this.paramIndex = 0;
/* 173 */     this.fromStack = false;
/* 178 */     this.stackIndex = 0;
/*     */     this.paramIndex = paramIndex;
/*     */     this.attributeName = attributeName;
/*     */   }
/*     */   
/*     */   public CallParamRule(int paramIndex, boolean fromStack) {
/*     */     this.attributeName = null;
/*     */     this.paramIndex = 0;
/*     */     this.fromStack = false;
/* 178 */     this.stackIndex = 0;
/*     */     this.paramIndex = paramIndex;
/*     */     this.fromStack = fromStack;
/*     */   }
/*     */   
/*     */   public CallParamRule(int paramIndex, int stackIndex) {
/*     */     this.attributeName = null;
/*     */     this.paramIndex = 0;
/*     */     this.fromStack = false;
/* 178 */     this.stackIndex = 0;
/*     */     this.paramIndex = paramIndex;
/*     */     this.fromStack = true;
/*     */     this.stackIndex = stackIndex;
/*     */   }
/*     */   
/*     */   public void begin(Attributes attributes) throws Exception {
/* 196 */     Object param = null;
/* 198 */     if (this.attributeName != null) {
/* 200 */       param = attributes.getValue(this.attributeName);
/* 202 */     } else if (this.fromStack) {
/* 204 */       param = this.digester.peek(this.stackIndex);
/* 206 */       if (this.digester.log.isDebugEnabled()) {
/* 208 */         StringBuffer sb = new StringBuffer("[CallParamRule]{");
/* 209 */         sb.append(this.digester.match);
/* 210 */         sb.append("} Save from stack; from stack?").append(this.fromStack);
/* 211 */         sb.append("; object=").append(param);
/* 212 */         this.digester.log.debug(sb.toString());
/*     */       } 
/*     */     } 
/* 222 */     if (param != null) {
/* 223 */       Object[] parameters = (Object[])this.digester.peekParams();
/* 224 */       parameters[this.paramIndex] = param;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void body(String bodyText) throws Exception {
/* 236 */     if (this.attributeName == null && !this.fromStack) {
/* 240 */       if (this.bodyTextStack == null)
/* 241 */         this.bodyTextStack = new ArrayStack(); 
/* 243 */       this.bodyTextStack.push(bodyText.trim());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void end(String namespace, String name) {
/* 252 */     if (this.bodyTextStack != null && !this.bodyTextStack.empty()) {
/* 254 */       Object[] parameters = (Object[])this.digester.peekParams();
/* 255 */       parameters[this.paramIndex] = this.bodyTextStack.pop();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 264 */     StringBuffer sb = new StringBuffer("CallParamRule[");
/* 265 */     sb.append("paramIndex=");
/* 266 */     sb.append(this.paramIndex);
/* 267 */     sb.append(", attributeName=");
/* 268 */     sb.append(this.attributeName);
/* 269 */     sb.append(", from stack=");
/* 270 */     sb.append(this.fromStack);
/* 271 */     sb.append("]");
/* 272 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\CallParamRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */