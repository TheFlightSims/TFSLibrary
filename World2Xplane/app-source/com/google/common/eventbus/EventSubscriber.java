/*     */ package com.google.common.eventbus;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ class EventSubscriber {
/*     */   private final Object target;
/*     */   
/*     */   private final Method method;
/*     */   
/*     */   EventSubscriber(Object target, Method method) {
/*  54 */     Preconditions.checkNotNull(target, "EventSubscriber target cannot be null.");
/*  56 */     Preconditions.checkNotNull(method, "EventSubscriber method cannot be null.");
/*  58 */     this.target = target;
/*  59 */     this.method = method;
/*  60 */     method.setAccessible(true);
/*     */   }
/*     */   
/*     */   public void handleEvent(Object event) throws InvocationTargetException {
/*  72 */     Preconditions.checkNotNull(event);
/*     */     try {
/*  74 */       this.method.invoke(this.target, new Object[] { event });
/*  75 */     } catch (IllegalArgumentException e) {
/*  76 */       throw new Error("Method rejected target/argument: " + event, e);
/*  77 */     } catch (IllegalAccessException e) {
/*  78 */       throw new Error("Method became inaccessible: " + event, e);
/*  79 */     } catch (InvocationTargetException e) {
/*  80 */       if (e.getCause() instanceof Error)
/*  81 */         throw (Error)e.getCause(); 
/*  83 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/*  88 */     return "[wrapper " + this.method + "]";
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  92 */     int PRIME = 31;
/*  93 */     return (31 + this.method.hashCode()) * 31 + System.identityHashCode(this.target);
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object obj) {
/*  98 */     if (obj instanceof EventSubscriber) {
/*  99 */       EventSubscriber that = (EventSubscriber)obj;
/* 103 */       return (this.target == that.target && this.method.equals(that.method));
/*     */     } 
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public Object getSubscriber() {
/* 109 */     return this.target;
/*     */   }
/*     */   
/*     */   public Method getMethod() {
/* 113 */     return this.method;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\eventbus\EventSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */