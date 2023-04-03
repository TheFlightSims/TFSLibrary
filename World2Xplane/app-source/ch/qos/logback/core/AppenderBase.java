/*     */ package ch.qos.logback.core;
/*     */ 
/*     */ import ch.qos.logback.core.filter.Filter;
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import ch.qos.logback.core.spi.FilterAttachableImpl;
/*     */ import ch.qos.logback.core.spi.FilterReply;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import ch.qos.logback.core.status.WarnStatus;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class AppenderBase<E> extends ContextAwareBase implements Appender<E> {
/*     */   protected boolean started = false;
/*     */   
/*     */   private boolean guard = false;
/*     */   
/*     */   protected String name;
/*     */   
/*  48 */   private FilterAttachableImpl<E> fai = new FilterAttachableImpl();
/*     */   
/*     */   public String getName() {
/*  51 */     return this.name;
/*     */   }
/*     */   
/*  54 */   private int statusRepeatCount = 0;
/*     */   
/*  55 */   private int exceptionCount = 0;
/*     */   
/*     */   static final int ALLOWED_REPEATS = 5;
/*     */   
/*     */   public synchronized void doAppend(E eventObject) {
/*  64 */     if (this.guard)
/*     */       return; 
/*     */     try {
/*  69 */       this.guard = true;
/*  71 */       if (!this.started) {
/*  72 */         if (this.statusRepeatCount++ < 5)
/*  73 */           addStatus((Status)new WarnStatus("Attempted to append to non started appender [" + this.name + "].", this)); 
/*     */         return;
/*     */       } 
/*  80 */       if (getFilterChainDecision(eventObject) == FilterReply.DENY)
/*     */         return; 
/*  85 */       append(eventObject);
/*  87 */     } catch (Exception e) {
/*  88 */       if (this.exceptionCount++ < 5)
/*  89 */         addError("Appender [" + this.name + "] failed to append.", e); 
/*     */     } finally {
/*  92 */       this.guard = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void append(E paramE);
/*     */   
/*     */   public void setName(String name) {
/* 102 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void start() {
/* 106 */     this.started = true;
/*     */   }
/*     */   
/*     */   public void stop() {
/* 110 */     this.started = false;
/*     */   }
/*     */   
/*     */   public boolean isStarted() {
/* 114 */     return this.started;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 118 */     return getClass().getName() + "[" + this.name + "]";
/*     */   }
/*     */   
/*     */   public void addFilter(Filter<E> newFilter) {
/* 122 */     this.fai.addFilter(newFilter);
/*     */   }
/*     */   
/*     */   public void clearAllFilters() {
/* 126 */     this.fai.clearAllFilters();
/*     */   }
/*     */   
/*     */   public List<Filter<E>> getCopyOfAttachedFiltersList() {
/* 130 */     return this.fai.getCopyOfAttachedFiltersList();
/*     */   }
/*     */   
/*     */   public FilterReply getFilterChainDecision(E event) {
/* 134 */     return this.fai.getFilterChainDecision(event);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\AppenderBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */