/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.identity.FeatureIdImpl;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.WeakHashSet;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ 
/*     */ public class BatchFeatureEvent extends FeatureEvent {
/*     */   private static final long serialVersionUID = 3154238322369916486L;
/*     */   
/*     */   protected WeakHashSet<Identifier> fids;
/*     */   
/*     */   public BatchFeatureEvent(FeatureSource<? extends FeatureType, ? extends Feature> featureSource) {
/*  49 */     this(featureSource, new ReferencedEnvelope(), (Filter)Filter.EXCLUDE);
/*     */   }
/*     */   
/*     */   public BatchFeatureEvent(FeatureSource<? extends FeatureType, ? extends Feature> featureSource, ReferencedEnvelope bounds, Filter filter) {
/*  54 */     super(featureSource, FeatureEvent.Type.COMMIT, bounds, filter);
/*  62 */     this.fids = new WeakHashSet(Identifier.class);
/*     */   }
/*     */   
/*     */   public void setType(FeatureEvent.Type type) {
/*  70 */     this.type = type;
/*     */   }
/*     */   
/*     */   public void add(FeatureEvent change) {
/*  81 */     if (change.getType() == FeatureEvent.Type.ADDED && 
/*  82 */       change.getFilter() instanceof Id) {
/*  84 */       Id newFeatureIds = (Id)change.getFilter();
/*  85 */       this.fids.addAll(newFeatureIds.getIdentifiers());
/*     */     } 
/*  92 */     if (this.filter == Filter.INCLUDE || this.bounds == ReferencedEnvelope.EVERYTHING)
/*     */       return; 
/*  97 */     if (change.getFilter() == Filter.INCLUDE || change.getBounds() == ReferencedEnvelope.EVERYTHING) {
/* 100 */       this.filter = (Filter)Filter.INCLUDE;
/* 101 */       this.bounds = ReferencedEnvelope.EVERYTHING;
/*     */       return;
/*     */     } 
/* 104 */     this.bounds.expandToInclude((Envelope)change.getBounds());
/* 106 */     FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/* 108 */     if (this.filter == Filter.EXCLUDE) {
/* 110 */       this.filter = change.getFilter();
/* 111 */     } else if (this.filter instanceof And) {
/* 112 */       And and = (And)this.filter;
/* 113 */       List<Filter> children = new ArrayList<Filter>(and.getChildren());
/* 114 */       children.add(change.getFilter());
/* 115 */       this.filter = (Filter)ff.and(children);
/*     */     } else {
/* 117 */       this.filter = (Filter)ff.and(this.filter, change.getFilter());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void replaceFid(String tempFid, String actualFid) {
/* 125 */     for (Identifier id : this.fids) {
/* 126 */       if (tempFid.equals(id.getID()))
/* 128 */         if (id instanceof FeatureIdImpl) {
/* 129 */           FeatureIdImpl featureId = (FeatureIdImpl)id;
/* 131 */           featureId.setID(actualFid);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public WeakHashSet<Identifier> getCreatedFeatureIds() {
/* 149 */     return this.fids;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\BatchFeatureEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */