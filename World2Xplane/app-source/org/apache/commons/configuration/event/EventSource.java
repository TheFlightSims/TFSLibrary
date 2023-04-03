/*     */ package org.apache.commons.configuration.event;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class EventSource {
/*     */   private Collection listeners;
/*     */   
/*     */   private Collection errorListeners;
/*     */   
/*     */   private int detailEvents;
/*     */   
/*     */   public EventSource() {
/*  86 */     initListeners();
/*     */   }
/*     */   
/*     */   public void addConfigurationListener(ConfigurationListener l) {
/*  96 */     doAddListener(this.listeners, l);
/*     */   }
/*     */   
/*     */   public boolean removeConfigurationListener(ConfigurationListener l) {
/* 108 */     return doRemoveListener(this.listeners, l);
/*     */   }
/*     */   
/*     */   public Collection getConfigurationListeners() {
/* 122 */     return doGetListeners(this.listeners);
/*     */   }
/*     */   
/*     */   public void clearConfigurationListeners() {
/* 130 */     doClearListeners(this.listeners);
/*     */   }
/*     */   
/*     */   public boolean isDetailEvents() {
/* 140 */     synchronized (this.listeners) {
/* 142 */       return (this.detailEvents > 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDetailEvents(boolean enable) {
/* 157 */     synchronized (this.listeners) {
/* 159 */       if (enable) {
/* 161 */         this.detailEvents++;
/*     */       } else {
/* 165 */         this.detailEvents--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addErrorListener(ConfigurationErrorListener l) {
/* 179 */     doAddListener(this.errorListeners, l);
/*     */   }
/*     */   
/*     */   public boolean removeErrorListener(ConfigurationErrorListener l) {
/* 192 */     return doRemoveListener(this.errorListeners, l);
/*     */   }
/*     */   
/*     */   public void clearErrorListeners() {
/* 202 */     doClearListeners(this.errorListeners);
/*     */   }
/*     */   
/*     */   public Collection getErrorListeners() {
/* 216 */     return doGetListeners(this.errorListeners);
/*     */   }
/*     */   
/*     */   protected void fireEvent(int type, String propName, Object propValue, boolean before) {
/* 232 */     Collection listenersToCall = null;
/* 234 */     synchronized (this.listeners) {
/* 236 */       if (this.detailEvents >= 0 && this.listeners.size() > 0)
/* 240 */         listenersToCall = new ArrayList(this.listeners); 
/*     */     } 
/* 244 */     if (listenersToCall != null) {
/* 246 */       ConfigurationEvent event = createEvent(type, propName, propValue, before);
/* 247 */       for (Iterator it = listenersToCall.iterator(); it.hasNext();)
/* 249 */         ((ConfigurationListener)it.next()).configurationChanged(event); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ConfigurationEvent createEvent(int type, String propName, Object propValue, boolean before) {
/* 267 */     return new ConfigurationEvent(this, type, propName, propValue, before);
/*     */   }
/*     */   
/*     */   protected void fireError(int type, String propName, Object propValue, Throwable ex) {
/* 282 */     Collection listenersToCall = null;
/* 284 */     synchronized (this.errorListeners) {
/* 286 */       if (this.errorListeners.size() > 0)
/* 290 */         listenersToCall = new ArrayList(this.errorListeners); 
/*     */     } 
/* 294 */     if (listenersToCall != null) {
/* 296 */       ConfigurationErrorEvent event = createErrorEvent(type, propName, propValue, ex);
/* 297 */       for (Iterator it = listenersToCall.iterator(); it.hasNext();)
/* 299 */         ((ConfigurationErrorListener)it.next()).configurationError(event); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ConfigurationErrorEvent createErrorEvent(int type, String propName, Object propValue, Throwable ex) {
/* 319 */     return new ConfigurationErrorEvent(this, type, propName, propValue, ex);
/*     */   }
/*     */   
/*     */   protected Object clone() throws CloneNotSupportedException {
/* 334 */     EventSource copy = (EventSource)super.clone();
/* 335 */     copy.initListeners();
/* 336 */     return copy;
/*     */   }
/*     */   
/*     */   private static void doAddListener(Collection listeners, Object l) {
/* 348 */     if (l == null)
/* 350 */       throw new IllegalArgumentException("Listener must not be null!"); 
/* 352 */     synchronized (listeners) {
/* 354 */       listeners.add(l);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean doRemoveListener(Collection listeners, Object l) {
/* 368 */     synchronized (listeners) {
/* 370 */       return listeners.remove(l);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void doClearListeners(Collection listeners) {
/* 381 */     synchronized (listeners) {
/* 383 */       listeners.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Collection doGetListeners(Collection listeners) {
/* 395 */     synchronized (listeners) {
/* 397 */       return Collections.unmodifiableCollection(new ArrayList(listeners));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initListeners() {
/* 406 */     this.listeners = new LinkedList();
/* 407 */     this.errorListeners = new LinkedList();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\event\EventSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */