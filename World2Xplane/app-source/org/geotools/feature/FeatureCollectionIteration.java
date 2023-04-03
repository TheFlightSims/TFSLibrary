/*     */ package org.geotools.feature;
/*     */ 
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ 
/*     */ public class FeatureCollectionIteration {
/*     */   protected final Handler handler;
/*     */   
/*     */   private final FeatureCollection<?, ?> collection;
/*     */   
/*     */   public FeatureCollectionIteration(Handler handler, FeatureCollection<?, ?> collection) throws NullPointerException {
/*  74 */     if (handler == null)
/*  75 */       throw new NullPointerException("handler"); 
/*  78 */     if (collection == null)
/*  79 */       throw new NullPointerException("collection"); 
/*  82 */     this.handler = handler;
/*  83 */     this.collection = collection;
/*     */   }
/*     */   
/*     */   public static void iteration(Handler handler, FeatureCollection<?, ?> collection) {
/*  93 */     FeatureCollectionIteration iteration = new FeatureCollectionIteration(handler, collection);
/*  95 */     iteration.iterate();
/*     */   }
/*     */   
/*     */   public void iterate() {
/* 102 */     walker(this.collection);
/*     */   }
/*     */   
/*     */   protected void walker(FeatureCollection<?, ?> collection) {
/* 114 */     this.handler.handleFeatureCollection(collection);
/* 116 */     iterate(collection.features());
/* 118 */     this.handler.endFeatureCollection(collection);
/*     */   }
/*     */   
/*     */   protected void iterate(FeatureIterator<?> iterator) {
/* 127 */     while (iterator.hasNext())
/* 128 */       walker((Feature)iterator.next()); 
/* 130 */     iterator.close();
/*     */   }
/*     */   
/*     */   protected void walker(Feature feature) {
/* 139 */     FeatureType schema = feature.getType();
/* 142 */     this.handler.handleFeature(feature);
/* 144 */     for (Property property : feature.getProperties()) {
/* 145 */       Class<?> binding = property.getType().getBinding();
/* 147 */       if (FeatureCollection.class.isAssignableFrom(binding)) {
/* 148 */         walker((FeatureCollection<?, ?>)property.getValue());
/*     */         continue;
/*     */       } 
/* 150 */       if (Feature.class.isAssignableFrom(binding)) {
/* 152 */         walker((Feature)property.getValue());
/*     */         continue;
/*     */       } 
/* 155 */       this.handler.handleAttribute(property.getDescriptor(), property.getValue());
/*     */     } 
/* 159 */     this.handler.endFeature(feature);
/*     */   }
/*     */   
/*     */   public static interface Handler {
/*     */     void handleFeatureCollection(FeatureCollection<?, ?> param1FeatureCollection);
/*     */     
/*     */     void endFeatureCollection(FeatureCollection<?, ?> param1FeatureCollection);
/*     */     
/*     */     void handleFeature(Feature param1Feature);
/*     */     
/*     */     void endFeature(Feature param1Feature);
/*     */     
/*     */     void handleAttribute(PropertyDescriptor param1PropertyDescriptor, Object param1Object);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureCollectionIteration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */