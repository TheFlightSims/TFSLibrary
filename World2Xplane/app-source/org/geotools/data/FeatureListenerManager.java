/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class FeatureListenerManager {
/*  50 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*     */   class WeakFeatureListener implements FeatureListener {
/*     */     WeakReference<FeatureListener> reference;
/*     */     
/*     */     public WeakFeatureListener(FeatureListener listener) {
/*  62 */       this.reference = new WeakReference<FeatureListener>(listener);
/*     */     }
/*     */     
/*     */     public void changed(FeatureEvent featureEvent) {
/*  66 */       FeatureListener listener = this.reference.get();
/*  67 */       if (listener == null) {
/*  68 */         FeatureListenerManager.this.removeFeatureListener(this);
/*     */       } else {
/*  70 */         listener.changed(featureEvent);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*  79 */   Map<FeatureSource<? extends FeatureType, ? extends Feature>, EventListenerList> listenerMap = new WeakHashMap<FeatureSource<? extends FeatureType, ? extends Feature>, EventListenerList>();
/*     */   
/*     */   public void addFeatureListener(FeatureSource<? extends FeatureType, ? extends Feature> featureSource, FeatureListener featureListener) {
/*  89 */     eventListenerList(featureSource).add(FeatureListener.class, featureListener);
/*     */   }
/*     */   
/*     */   void removeFeatureListener(WeakFeatureListener listener) {
/* 100 */     for (EventListenerList list : this.listenerMap.values())
/* 101 */       list.remove(FeatureListener.class, listener); 
/*     */   }
/*     */   
/*     */   public void removeFeatureListener(FeatureSource<? extends FeatureType, ? extends Feature> featureSource, FeatureListener featureListener) {
/* 113 */     EventListenerList list = eventListenerList(featureSource);
/* 114 */     list.remove(FeatureListener.class, featureListener);
/* 118 */     if (list.getListenerCount() == 0)
/* 119 */       cleanListenerList(featureSource); 
/*     */   }
/*     */   
/*     */   private EventListenerList eventListenerList(FeatureSource<? extends FeatureType, ? extends Feature> featureSource) {
/* 130 */     synchronized (this.listenerMap) {
/* 131 */       if (this.listenerMap.containsKey(featureSource))
/* 132 */         return this.listenerMap.get(featureSource); 
/* 134 */       EventListenerList listenerList = new EventListenerList();
/* 135 */       this.listenerMap.put(featureSource, listenerList);
/* 137 */       return listenerList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cleanListenerList(FeatureSource<? extends FeatureType, ? extends Feature> featureSource) {
/* 143 */     synchronized (this.listenerMap) {
/* 144 */       this.listenerMap.remove(featureSource);
/*     */     } 
/*     */   }
/*     */   
/*     */   Map<SimpleFeatureSource, FeatureListener[]> getListeners(String typeName, Transaction transaction) {
/* 162 */     Map<SimpleFeatureSource, FeatureListener[]> map = (Map)new HashMap<SimpleFeatureSource, FeatureListener>();
/* 168 */     synchronized (this.listenerMap) {
/* 169 */       for (Map.Entry<FeatureSource<? extends FeatureType, ? extends Feature>, EventListenerList> entry : this.listenerMap.entrySet()) {
/* 170 */         SimpleFeatureSource featureSource = (SimpleFeatureSource)entry.getKey();
/* 172 */         if (!featureSource.getName().getLocalPart().equals(typeName))
/*     */           continue; 
/* 176 */         if (transaction != Transaction.AUTO_COMMIT && hasTransaction((FeatureSource<? extends FeatureType, ? extends Feature>)featureSource))
/* 179 */           if (transaction != getTransaction((FeatureSource<? extends FeatureType, ? extends Feature>)featureSource))
/*     */             continue;  
/* 184 */         EventListenerList listenerList = (EventListenerList)entry.getValue();
/* 185 */         FeatureListener[] listeners = listenerList.<FeatureListener>getListeners(FeatureListener.class);
/* 187 */         if (listeners.length != 0)
/* 188 */           map.put(featureSource, listeners); 
/*     */       } 
/*     */     } 
/* 193 */     return map;
/*     */   }
/*     */   
/*     */   private static boolean hasTransaction(FeatureSource<? extends FeatureType, ? extends Feature> featureSource) {
/* 198 */     return (featureSource instanceof FeatureStore && ((FeatureStore)featureSource).getTransaction() != null);
/*     */   }
/*     */   
/*     */   private static Transaction getTransaction(FeatureSource<? extends FeatureType, ? extends Feature> featureSource) {
/* 205 */     if (hasTransaction(featureSource))
/* 206 */       return ((FeatureStore)featureSource).getTransaction(); 
/* 210 */     return Transaction.AUTO_COMMIT;
/*     */   }
/*     */   
/*     */   public void fireFeaturesAdded(String typeName, Transaction transaction, ReferencedEnvelope bounds, boolean commit) {
/* 248 */     if (commit) {
/* 249 */       fireCommit(typeName, transaction, 1, bounds);
/*     */     } else {
/* 251 */       fireEvent(typeName, transaction, 1, bounds);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fireEvent(String typeName, Transaction transaction, FeatureEvent event) {
/* 264 */     if (event.getType() == FeatureEvent.Type.COMMIT || event.getType() == FeatureEvent.Type.ROLLBACK) {
/* 270 */       Map<SimpleFeatureSource, FeatureListener[]> map = getListeners(typeName, Transaction.AUTO_COMMIT);
/* 271 */       for (Map.Entry<SimpleFeatureSource, FeatureListener> entry : map.entrySet()) {
/* 272 */         FeatureSource featureSource = (FeatureSource)entry.getKey();
/* 273 */         FeatureListener[] listeners = (FeatureListener[])entry.getValue();
/* 274 */         event.setFeatureSource(featureSource);
/* 275 */         for (FeatureListener listener : listeners) {
/*     */           try {
/* 277 */             listener.changed(event);
/* 279 */           } catch (Throwable t) {
/* 280 */             LOGGER.log(Level.FINE, "Could not deliver " + event + " to " + listener + ":" + t.getMessage(), t);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 289 */       Map<SimpleFeatureSource, FeatureListener[]> map = getListeners(typeName, transaction);
/* 290 */       for (Map.Entry<SimpleFeatureSource, FeatureListener> entry : map.entrySet()) {
/* 291 */         FeatureSource featureSource = (FeatureSource)entry.getKey();
/* 292 */         FeatureListener[] listeners = (FeatureListener[])entry.getValue();
/* 293 */         event.setFeatureSource(featureSource);
/* 294 */         for (FeatureListener listener : listeners) {
/*     */           try {
/* 296 */             listener.changed(event);
/* 298 */           } catch (Throwable t) {
/* 299 */             LOGGER.log(Level.FINE, "Could not deliver " + event + " to " + listener + ":" + t.getMessage(), t);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fireFeaturesChanged(String typeName, Transaction transaction, ReferencedEnvelope bounds, boolean commit) {
/* 342 */     if (commit) {
/* 343 */       fireCommit(typeName, transaction, 0, bounds);
/*     */     } else {
/* 345 */       fireEvent(typeName, transaction, 0, bounds);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fireChanged(String typeName, Transaction transaction, boolean commit) {
/* 379 */     if (commit) {
/* 380 */       fireCommit(typeName, transaction, 0, null);
/*     */     } else {
/* 382 */       fireEvent(typeName, transaction, 0, null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fireCommit(String typeName, Transaction transaction, int type, ReferencedEnvelope bounds) {
/* 396 */     Map<SimpleFeatureSource, FeatureListener[]> map = getListeners(typeName, Transaction.AUTO_COMMIT);
/* 398 */     for (Map.Entry<SimpleFeatureSource, FeatureListener> entry : map.entrySet()) {
/* 399 */       FeatureSource<? extends FeatureType, ? extends Feature> featureSource = (FeatureSource<? extends FeatureType, ? extends Feature>)entry.getKey();
/* 400 */       FeatureListener[] listeners = (FeatureListener[])entry.getValue();
/* 402 */       if (hasTransaction(featureSource) && getTransaction(featureSource) == transaction)
/*     */         continue; 
/* 407 */       FeatureEvent event = new FeatureEvent(featureSource, type, (Envelope)bounds);
/* 409 */       for (int l = 0; l < listeners.length; l++)
/* 410 */         listeners[l].changed(event); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fireEvent(String typeName, Transaction transaction, int type, ReferencedEnvelope bounds) {
/* 425 */     Map<SimpleFeatureSource, FeatureListener[]> map = getListeners(typeName, transaction);
/* 427 */     for (Map.Entry<SimpleFeatureSource, FeatureListener> entry : map.entrySet()) {
/* 428 */       FeatureSource<? extends FeatureType, ? extends Feature> featureSource = (FeatureSource<? extends FeatureType, ? extends Feature>)entry.getKey();
/* 429 */       FeatureListener[] listeners = (FeatureListener[])entry.getValue();
/* 431 */       FeatureEvent event = new FeatureEvent(featureSource, type, (Envelope)bounds);
/* 433 */       for (int l = 0; l < listeners.length; l++)
/* 434 */         listeners[l].changed(event); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fireFeaturesRemoved(String typeName, Transaction transaction, ReferencedEnvelope bounds, boolean commit) {
/* 467 */     if (commit) {
/* 468 */       fireCommit(typeName, transaction, -1, bounds);
/*     */     } else {
/* 470 */       fireEvent(typeName, transaction, -1, bounds);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureListenerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */