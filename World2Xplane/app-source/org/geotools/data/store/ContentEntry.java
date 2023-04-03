/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.data.FeatureEvent;
/*     */ import org.geotools.data.FeatureListener;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.opengis.feature.type.Name;
/*     */ 
/*     */ public final class ContentEntry {
/*     */   Name typeName;
/*     */   
/*     */   Map<Transaction, ContentState> state;
/*     */   
/*     */   ContentDataStore dataStore;
/*     */   
/*     */   public ContentEntry(ContentDataStore dataStore, Name typeName) {
/*  84 */     this.typeName = typeName;
/*  85 */     this.dataStore = dataStore;
/*  87 */     this.state = new ConcurrentHashMap<Transaction, ContentState>();
/*  90 */     ContentState autoState = dataStore.createContentState(this);
/*  91 */     autoState.setTransaction(Transaction.AUTO_COMMIT);
/*  92 */     this.state.put(Transaction.AUTO_COMMIT, autoState);
/*     */   }
/*     */   
/*     */   public Name getName() {
/*  99 */     return this.typeName;
/*     */   }
/*     */   
/*     */   public String getTypeName() {
/* 109 */     return this.typeName.getLocalPart();
/*     */   }
/*     */   
/*     */   public ContentDataStore getDataStore() {
/* 116 */     return this.dataStore;
/*     */   }
/*     */   
/*     */   public ContentState getState(Transaction transaction) {
/* 130 */     if (transaction != null && this.state.containsKey(transaction))
/* 131 */       return this.state.get(transaction); 
/* 133 */     ContentState auto = this.state.get(Transaction.AUTO_COMMIT);
/* 134 */     ContentState copy = auto.copy();
/* 135 */     Transaction t = (transaction != null) ? transaction : Transaction.AUTO_COMMIT;
/* 136 */     copy.setTransaction(t);
/* 137 */     this.state.put(t, copy);
/* 139 */     return copy;
/*     */   }
/*     */   
/*     */   void notifiyFeatureEvent(ContentState source, FeatureEvent notification) {
/* 151 */     for (ContentState entry : this.state.values()) {
/* 152 */       if (entry == source)
/*     */         continue; 
/* 155 */       for (FeatureListener listener : source.listeners) {
/*     */         try {
/* 157 */           listener.changed(notification);
/* 159 */         } catch (Throwable t) {
/* 161 */           this.dataStore.LOGGER.log(Level.WARNING, "Problem issuing feature event " + notification, t);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 172 */     for (ContentState s : this.state.values())
/* 173 */       s.close(); 
/*     */   }
/*     */   
/*     */   public void clearTransaction(Transaction transaction) {
/* 182 */     if (this.state.containsKey(transaction))
/* 183 */       this.state.remove(transaction); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 188 */     return getTypeName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ContentEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */