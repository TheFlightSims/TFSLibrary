/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.util.EventObject;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class FeatureEvent extends EventObject {
/*     */   private static final long serialVersionUID = 3154238322369916485L;
/*     */   
/*     */   public static final int FEATURES_ADDED = 1;
/*     */   
/*     */   public static final int FEATURES_CHANGED = 0;
/*     */   
/*     */   public static final int FEATURES_REMOVED = -1;
/*     */   
/*     */   protected Type type;
/*     */   
/*     */   protected ReferencedEnvelope bounds;
/*     */   
/*     */   protected FeatureSource featureSource;
/*     */   
/*     */   protected Filter filter;
/*     */   
/*     */   public enum Type {
/* 162 */     ADDED(1),
/* 171 */     CHANGED(0),
/* 178 */     REMOVED(-1),
/* 187 */     COMMIT(0),
/* 192 */     ROLLBACK(0);
/*     */     
/*     */     final int type;
/*     */     
/*     */     Type(int type) {
/* 197 */       this.type = type;
/*     */     }
/*     */     
/*     */     static Type fromValue(int value) {
/* 200 */       switch (value) {
/*     */         case 1:
/* 201 */           return ADDED;
/*     */         case 0:
/* 202 */           return CHANGED;
/*     */         case -1:
/* 203 */           return REMOVED;
/*     */       } 
/* 205 */       return CHANGED;
/*     */     }
/*     */   }
/*     */   
/*     */   public FeatureEvent(FeatureEvent origional) {
/* 245 */     super(origional.getSource());
/* 246 */     this.type = origional.type;
/* 247 */     this.bounds = new ReferencedEnvelope(origional.bounds);
/* 248 */     this.filter = origional.filter;
/* 249 */     this.featureSource = origional.getFeatureSource();
/*     */   }
/*     */   
/*     */   public FeatureEvent(Object source, Type type, ReferencedEnvelope bounds, Filter filter) {
/* 261 */     super(source);
/* 262 */     this.type = type;
/* 263 */     this.bounds = bounds;
/* 264 */     this.filter = filter;
/* 265 */     if (source instanceof FeatureSource)
/* 266 */       this.featureSource = (FeatureSource)source; 
/*     */   }
/*     */   
/*     */   public FeatureEvent(FeatureSource<? extends FeatureType, ? extends Feature> featureSource, int eventType, Envelope bounds) {
/* 281 */     super(featureSource);
/* 282 */     switch (eventType) {
/*     */       case 1:
/* 284 */         this.type = Type.ADDED;
/*     */         break;
/*     */       case 0:
/* 287 */         this.type = Type.CHANGED;
/*     */         break;
/*     */       case -1:
/* 290 */         this.type = Type.REMOVED;
/*     */         break;
/*     */       default:
/* 293 */         this.type = Type.CHANGED;
/*     */         break;
/*     */     } 
/* 295 */     this.bounds = ReferencedEnvelope.reference(bounds);
/* 296 */     this.featureSource = featureSource;
/*     */   }
/*     */   
/*     */   public FeatureSource<? extends FeatureType, ? extends Feature> getFeatureSource() {
/* 306 */     return (FeatureSource<? extends FeatureType, ? extends Feature>)this.source;
/*     */   }
/*     */   
/*     */   public void setFeatureSource(FeatureSource featureSource) {
/* 310 */     this.source = featureSource;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/* 320 */     return this.type.type;
/*     */   }
/*     */   
/*     */   public Type getType() {
/* 330 */     return this.type;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 340 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public Filter getFilter() {
/* 351 */     return this.filter;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */