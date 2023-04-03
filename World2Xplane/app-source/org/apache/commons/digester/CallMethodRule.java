/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.apache.commons.beanutils.ConvertUtils;
/*     */ import org.apache.commons.beanutils.MethodUtils;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class CallMethodRule extends Rule {
/*     */   protected String bodyText;
/*     */   
/*     */   protected int targetOffset;
/*     */   
/*     */   protected String methodName;
/*     */   
/*     */   protected int paramCount;
/*     */   
/*     */   protected Class[] paramTypes;
/*     */   
/*     */   private String[] paramClassNames;
/*     */   
/*     */   protected boolean useExactMatch;
/*     */   
/*     */   public CallMethodRule(Digester digester, String methodName, int paramCount) {
/* 115 */     this(methodName, paramCount);
/*     */   }
/*     */   
/*     */   public CallMethodRule(Digester digester, String methodName, int paramCount, String[] paramTypes) {
/* 138 */     this(methodName, paramCount, paramTypes);
/*     */   }
/*     */   
/*     */   public CallMethodRule(Digester digester, String methodName, int paramCount, Class[] paramTypes) {
/* 162 */     this(methodName, paramCount, paramTypes);
/*     */   }
/*     */   
/*     */   public CallMethodRule(String methodName, int paramCount) {
/* 176 */     this(0, methodName, paramCount);
/*     */   }
/*     */   
/*     */   public CallMethodRule(int targetOffset, String methodName, int paramCount) {
/* 377 */     this.bodyText = null;
/* 385 */     this.targetOffset = 0;
/* 390 */     this.methodName = null;
/* 398 */     this.paramCount = 0;
/* 404 */     this.paramTypes = null;
/* 410 */     this.paramClassNames = null;
/* 415 */     this.useExactMatch = false;
/*     */     this.targetOffset = targetOffset;
/*     */     this.methodName = methodName;
/*     */     this.paramCount = paramCount;
/*     */     if (paramCount == 0) {
/*     */       this.paramTypes = new Class[] { String.class };
/*     */     } else {
/*     */       this.paramTypes = new Class[paramCount];
/*     */       for (int i = 0; i < this.paramTypes.length; i++)
/*     */         this.paramTypes[i] = String.class; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public CallMethodRule(String methodName) {
/*     */     this(0, methodName, 0, (Class[])null);
/*     */   }
/*     */   
/*     */   public CallMethodRule(int targetOffset, String methodName) {
/*     */     this(targetOffset, methodName, 0, (Class[])null);
/*     */   }
/*     */   
/*     */   public CallMethodRule(String methodName, int paramCount, String[] paramTypes) {
/*     */     this(0, methodName, paramCount, paramTypes);
/*     */   }
/*     */   
/*     */   public CallMethodRule(int targetOffset, String methodName, int paramCount, String[] paramTypes) {
/*     */     this.bodyText = null;
/*     */     this.targetOffset = 0;
/*     */     this.methodName = null;
/*     */     this.paramCount = 0;
/*     */     this.paramTypes = null;
/*     */     this.paramClassNames = null;
/* 415 */     this.useExactMatch = false;
/*     */     this.targetOffset = targetOffset;
/*     */     this.methodName = methodName;
/*     */     this.paramCount = paramCount;
/*     */     if (paramTypes == null) {
/*     */       this.paramTypes = new Class[paramCount];
/*     */       for (int i = 0; i < this.paramTypes.length; i++)
/*     */         this.paramTypes[i] = String.class; 
/*     */     } else {
/*     */       this.paramClassNames = new String[paramTypes.length];
/*     */       for (int i = 0; i < this.paramClassNames.length; i++)
/*     */         this.paramClassNames[i] = paramTypes[i]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public CallMethodRule(String methodName, int paramCount, Class[] paramTypes) {
/*     */     this(0, methodName, paramCount, paramTypes);
/*     */   }
/*     */   
/*     */   public CallMethodRule(int targetOffset, String methodName, int paramCount, Class[] paramTypes) {
/*     */     this.bodyText = null;
/*     */     this.targetOffset = 0;
/*     */     this.methodName = null;
/*     */     this.paramCount = 0;
/*     */     this.paramTypes = null;
/*     */     this.paramClassNames = null;
/* 415 */     this.useExactMatch = false;
/*     */     this.targetOffset = targetOffset;
/*     */     this.methodName = methodName;
/*     */     this.paramCount = paramCount;
/*     */     if (paramTypes == null) {
/*     */       this.paramTypes = new Class[paramCount];
/*     */       for (int i = 0; i < this.paramTypes.length; i++)
/*     */         this.paramTypes[i] = String.class; 
/*     */     } else {
/*     */       this.paramTypes = new Class[paramTypes.length];
/*     */       for (int i = 0; i < this.paramTypes.length; i++)
/*     */         this.paramTypes[i] = paramTypes[i]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getUseExactMatch() {
/* 424 */     return this.useExactMatch;
/*     */   }
/*     */   
/*     */   public void setUseExactMatch(boolean useExactMatch) {
/* 433 */     this.useExactMatch = useExactMatch;
/*     */   }
/*     */   
/*     */   public void setDigester(Digester digester) {
/* 443 */     super.setDigester(digester);
/* 445 */     if (this.paramClassNames != null) {
/* 446 */       this.paramTypes = new Class[this.paramClassNames.length];
/* 447 */       for (int i = 0; i < this.paramClassNames.length; i++) {
/*     */         try {
/* 449 */           this.paramTypes[i] = digester.getClassLoader().loadClass(this.paramClassNames[i]);
/* 451 */         } catch (ClassNotFoundException e) {
/* 453 */           digester.getLogger().error("(CallMethodRule) Cannot load class " + this.paramClassNames[i], e);
/* 454 */           this.paramTypes[i] = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void begin(Attributes attributes) throws Exception {
/* 468 */     if (this.paramCount > 0) {
/* 469 */       Object[] parameters = new Object[this.paramCount];
/* 470 */       for (int i = 0; i < parameters.length; i++)
/* 471 */         parameters[i] = null; 
/* 473 */       this.digester.pushParams(parameters);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void body(String bodyText) throws Exception {
/* 486 */     if (this.paramCount == 0)
/* 487 */       this.bodyText = bodyText.trim(); 
/*     */   }
/*     */   
/*     */   public void end() throws Exception {
/* 499 */     Object target, parameters[] = null;
/* 500 */     if (this.paramCount > 0) {
/* 502 */       parameters = (Object[])this.digester.popParams();
/* 504 */       if (this.digester.log.isTraceEnabled())
/* 505 */         for (int j = 0, size = parameters.length; j < size; j++)
/* 506 */           this.digester.log.trace("[CallMethodRule](" + j + ")" + parameters[j]);  
/* 520 */       if (this.paramCount == 1 && parameters[0] == null)
/*     */         return; 
/* 524 */     } else if (this.paramTypes != null && this.paramTypes.length != 0) {
/* 531 */       if (this.bodyText == null)
/*     */         return; 
/* 535 */       parameters = new Object[1];
/* 536 */       parameters[0] = this.bodyText;
/* 537 */       if (this.paramTypes.length == 0) {
/* 538 */         this.paramTypes = new Class[1];
/* 539 */         this.paramTypes[0] = String.class;
/*     */       } 
/*     */     } 
/* 552 */     Object[] paramValues = new Object[this.paramTypes.length];
/* 553 */     for (int i = 0; i < this.paramTypes.length; i++) {
/* 556 */       if (parameters[i] == null || (parameters[i] instanceof String && !String.class.isAssignableFrom(this.paramTypes[i]))) {
/* 561 */         paramValues[i] = ConvertUtils.convert((String)parameters[i], this.paramTypes[i]);
/*     */       } else {
/* 564 */         paramValues[i] = parameters[i];
/*     */       } 
/*     */     } 
/* 570 */     if (this.targetOffset >= 0) {
/* 571 */       target = this.digester.peek(this.targetOffset);
/*     */     } else {
/* 573 */       target = this.digester.peek(this.digester.getCount() + this.targetOffset);
/*     */     } 
/* 576 */     if (target == null) {
/* 577 */       StringBuffer sb = new StringBuffer();
/* 578 */       sb.append("[CallMethodRule]{");
/* 579 */       sb.append(this.digester.match);
/* 580 */       sb.append("} Call target is null (");
/* 581 */       sb.append("targetOffset=");
/* 582 */       sb.append(this.targetOffset);
/* 583 */       sb.append(",stackdepth=");
/* 584 */       sb.append(this.digester.getCount());
/* 585 */       sb.append(")");
/* 586 */       throw new SAXException(sb.toString());
/*     */     } 
/* 590 */     if (this.digester.log.isDebugEnabled()) {
/* 591 */       StringBuffer sb = new StringBuffer("[CallMethodRule]{");
/* 592 */       sb.append(this.digester.match);
/* 593 */       sb.append("} Call ");
/* 594 */       sb.append(target.getClass().getName());
/* 595 */       sb.append(".");
/* 596 */       sb.append(this.methodName);
/* 597 */       sb.append("(");
/* 598 */       for (int j = 0; j < paramValues.length; j++) {
/* 599 */         if (j > 0)
/* 600 */           sb.append(","); 
/* 602 */         if (paramValues[j] == null) {
/* 603 */           sb.append("null");
/*     */         } else {
/* 605 */           sb.append(paramValues[j].toString());
/*     */         } 
/* 607 */         sb.append("/");
/* 608 */         if (this.paramTypes[j] == null) {
/* 609 */           sb.append("null");
/*     */         } else {
/* 611 */           sb.append(this.paramTypes[j].getName());
/*     */         } 
/*     */       } 
/* 614 */       sb.append(")");
/* 615 */       this.digester.log.debug(sb.toString());
/*     */     } 
/* 618 */     Object result = null;
/* 619 */     if (this.useExactMatch) {
/* 621 */       result = MethodUtils.invokeExactMethod(target, this.methodName, paramValues, this.paramTypes);
/*     */     } else {
/* 626 */       result = MethodUtils.invokeMethod(target, this.methodName, paramValues, this.paramTypes);
/*     */     } 
/* 630 */     processMethodCallResult(result);
/*     */   }
/*     */   
/*     */   public void finish() throws Exception {
/* 639 */     this.bodyText = null;
/*     */   }
/*     */   
/*     */   protected void processMethodCallResult(Object result) {}
/*     */   
/*     */   public String toString() {
/* 658 */     StringBuffer sb = new StringBuffer("CallMethodRule[");
/* 659 */     sb.append("methodName=");
/* 660 */     sb.append(this.methodName);
/* 661 */     sb.append(", paramCount=");
/* 662 */     sb.append(this.paramCount);
/* 663 */     sb.append(", paramTypes={");
/* 664 */     if (this.paramTypes != null)
/* 665 */       for (int i = 0; i < this.paramTypes.length; i++) {
/* 666 */         if (i > 0)
/* 667 */           sb.append(", "); 
/* 669 */         sb.append(this.paramTypes[i].getName());
/*     */       }  
/* 672 */     sb.append("}");
/* 673 */     sb.append("]");
/* 674 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\CallMethodRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */