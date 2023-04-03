/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.apache.commons.collections.ArrayStack;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class FactoryCreateRule extends Rule {
/*     */   private boolean ignoreCreateExceptions;
/*     */   
/*     */   private ArrayStack exceptionIgnoredStack;
/*     */   
/*     */   protected String attributeName;
/*     */   
/*     */   protected String className;
/*     */   
/*     */   protected ObjectCreationFactory creationFactory;
/*     */   
/*     */   public FactoryCreateRule(Digester digester, String className) {
/*  63 */     this(className);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(Digester digester, Class clazz) {
/*  81 */     this(clazz);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(Digester digester, String className, String attributeName) {
/* 103 */     this(className, attributeName);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(Digester digester, Class clazz, String attributeName) {
/* 125 */     this(clazz, attributeName);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(Digester digester, ObjectCreationFactory creationFactory) {
/* 143 */     this(creationFactory);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(String className) {
/* 158 */     this(className, false);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(Class clazz) {
/* 174 */     this(clazz, false);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(String className, String attributeName) {
/* 193 */     this(className, attributeName, false);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(Class clazz, String attributeName) {
/* 212 */     this(clazz, attributeName, false);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(ObjectCreationFactory creationFactory) {
/* 227 */     this(creationFactory, false);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(String className, boolean ignoreCreateExceptions) {
/* 243 */     this(className, (String)null, ignoreCreateExceptions);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(Class clazz, boolean ignoreCreateExceptions) {
/* 260 */     this(clazz, (String)null, ignoreCreateExceptions);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(String className, String attributeName, boolean ignoreCreateExceptions) {
/* 333 */     this.attributeName = null;
/* 340 */     this.className = null;
/* 348 */     this.creationFactory = null;
/*     */     this.className = className;
/*     */     this.attributeName = attributeName;
/*     */     this.ignoreCreateExceptions = ignoreCreateExceptions;
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(Class clazz, String attributeName, boolean ignoreCreateExceptions) {
/*     */     this(clazz.getName(), attributeName, ignoreCreateExceptions);
/*     */   }
/*     */   
/*     */   public FactoryCreateRule(ObjectCreationFactory creationFactory, boolean ignoreCreateExceptions) {
/*     */     this.attributeName = null;
/*     */     this.className = null;
/* 348 */     this.creationFactory = null;
/*     */     this.creationFactory = creationFactory;
/*     */     this.ignoreCreateExceptions = ignoreCreateExceptions;
/*     */   }
/*     */   
/*     */   public void begin(String namespace, String name, Attributes attributes) throws Exception {
/* 361 */     if (this.ignoreCreateExceptions) {
/* 363 */       if (this.exceptionIgnoredStack == null)
/* 364 */         this.exceptionIgnoredStack = new ArrayStack(); 
/*     */       try {
/* 368 */         Object instance = getFactory(attributes).createObject(attributes);
/* 370 */         if (this.digester.log.isDebugEnabled())
/* 371 */           this.digester.log.debug("[FactoryCreateRule]{" + this.digester.match + "} New " + ((instance == null) ? "null object" : instance.getClass().getName())); 
/* 375 */         this.digester.push(instance);
/* 376 */         this.exceptionIgnoredStack.push(Boolean.FALSE);
/* 378 */       } catch (Exception e) {
/* 380 */         if (this.digester.log.isInfoEnabled()) {
/* 381 */           this.digester.log.info("[FactoryCreateRule] Create exception ignored: " + ((e.getMessage() == null) ? e.getClass().getName() : e.getMessage()));
/* 383 */           if (this.digester.log.isDebugEnabled())
/* 384 */             this.digester.log.debug("[FactoryCreateRule] Ignored exception:", e); 
/*     */         } 
/* 387 */         this.exceptionIgnoredStack.push(Boolean.TRUE);
/*     */       } 
/*     */     } else {
/* 391 */       Object instance = getFactory(attributes).createObject(attributes);
/* 393 */       if (this.digester.log.isDebugEnabled())
/* 394 */         this.digester.log.debug("[FactoryCreateRule]{" + this.digester.match + "} New " + ((instance == null) ? "null object" : instance.getClass().getName())); 
/* 398 */       this.digester.push(instance);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void end(String namespace, String name) throws Exception {
/* 410 */     if (this.ignoreCreateExceptions && this.exceptionIgnoredStack != null && !this.exceptionIgnoredStack.empty())
/* 415 */       if (((Boolean)this.exceptionIgnoredStack.pop()).booleanValue()) {
/* 418 */         if (this.digester.log.isTraceEnabled())
/* 419 */           this.digester.log.trace("[FactoryCreateRule] No creation so no push so no pop"); 
/*     */         return;
/*     */       }  
/* 425 */     Object top = this.digester.pop();
/* 426 */     if (this.digester.log.isDebugEnabled())
/* 427 */       this.digester.log.debug("[FactoryCreateRule]{" + this.digester.match + "} Pop " + top.getClass().getName()); 
/*     */   }
/*     */   
/*     */   public void finish() throws Exception {
/* 439 */     if (this.attributeName != null)
/* 440 */       this.creationFactory = null; 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 451 */     StringBuffer sb = new StringBuffer("FactoryCreateRule[");
/* 452 */     sb.append("className=");
/* 453 */     sb.append(this.className);
/* 454 */     sb.append(", attributeName=");
/* 455 */     sb.append(this.attributeName);
/* 456 */     if (this.creationFactory != null) {
/* 457 */       sb.append(", creationFactory=");
/* 458 */       sb.append(this.creationFactory);
/*     */     } 
/* 460 */     sb.append("]");
/* 461 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected ObjectCreationFactory getFactory(Attributes attributes) throws Exception {
/* 480 */     if (this.creationFactory == null) {
/* 481 */       String realClassName = this.className;
/* 482 */       if (this.attributeName != null) {
/* 483 */         String value = attributes.getValue(this.attributeName);
/* 484 */         if (value != null)
/* 485 */           realClassName = value; 
/*     */       } 
/* 488 */       if (this.digester.log.isDebugEnabled())
/* 489 */         this.digester.log.debug("[FactoryCreateRule]{" + this.digester.match + "} New factory " + realClassName); 
/* 492 */       Class clazz = this.digester.getClassLoader().loadClass(realClassName);
/* 493 */       this.creationFactory = (ObjectCreationFactory)clazz.newInstance();
/* 495 */       this.creationFactory.setDigester(this.digester);
/*     */     } 
/* 497 */     return this.creationFactory;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\FactoryCreateRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */