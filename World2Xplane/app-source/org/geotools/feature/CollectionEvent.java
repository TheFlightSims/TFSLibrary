/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.EventObject;
/*     */ import org.geotools.data.FeatureEvent;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class CollectionEvent extends EventObject {
/*     */   private static final long serialVersionUID = -1864190177730929948L;
/*     */   
/*     */   public static final int FEATURES_ADDED = 0;
/*     */   
/*     */   public static final int FEATURES_REMOVED = 1;
/*     */   
/*     */   public static final int FEATURES_CHANGED = 2;
/*     */   
/*     */   private int type;
/*     */   
/*     */   private SimpleFeature[] features;
/*     */   
/*     */   public CollectionEvent(FeatureCollection<? extends FeatureType, ? extends Feature> collection, FeatureEvent event) {
/*  61 */     super(collection);
/*  63 */     switch (event.getType()) {
/*     */       case ADDED:
/*  65 */         this.type = 0;
/*     */         break;
/*     */       case CHANGED:
/*  69 */         this.type = 2;
/*     */         break;
/*     */       case REMOVED:
/*  73 */         this.type = 1;
/*     */         break;
/*     */       default:
/*  77 */         this.type = 1;
/*     */         break;
/*     */     } 
/*  79 */     this.features = null;
/*     */   }
/*     */   
/*     */   public CollectionEvent(FeatureCollection<? extends FeatureType, ? extends Feature> source, SimpleFeature[] involvedFeatures, int type) {
/*  90 */     super(source);
/*  91 */     this.type = type;
/*  92 */     this.features = involvedFeatures;
/*     */   }
/*     */   
/*     */   public FeatureCollection<? extends FeatureType, ? extends Feature> getCollection() {
/* 102 */     return (FeatureCollection<? extends FeatureType, ? extends Feature>)this.source;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/* 113 */     return this.type;
/*     */   }
/*     */   
/*     */   public SimpleFeature[] getFeatures() {
/* 122 */     return this.features;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\CollectionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */