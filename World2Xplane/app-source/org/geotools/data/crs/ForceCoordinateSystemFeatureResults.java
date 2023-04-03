/*     */ package org.geotools.data.crs;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.collection.AbstractFeatureCollection;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class ForceCoordinateSystemFeatureResults extends AbstractFeatureCollection {
/*     */   FeatureCollection<SimpleFeatureType, SimpleFeature> results;
/*     */   
/*     */   public ForceCoordinateSystemFeatureResults(FeatureCollection<SimpleFeatureType, SimpleFeature> results, CoordinateReferenceSystem forcedCS) throws IOException, SchemaException {
/*  71 */     this(results, forcedCS, false);
/*     */   }
/*     */   
/*     */   public ForceCoordinateSystemFeatureResults(FeatureCollection<SimpleFeatureType, SimpleFeature> results, CoordinateReferenceSystem forcedCS, boolean forceOnlyMissing) throws IOException, SchemaException {
/*  76 */     super(forceType(origionalType(results), forcedCS, forceOnlyMissing));
/*  78 */     this.results = results;
/*     */   }
/*     */   
/*     */   private static SimpleFeatureType origionalType(FeatureCollection<SimpleFeatureType, SimpleFeature> results) {
/*  83 */     if (results instanceof ReprojectFeatureResults)
/*  84 */       results = ((ReprojectFeatureResults)results).getOrigin(); 
/*  86 */     if (results instanceof ForceCoordinateSystemFeatureResults)
/*  87 */       results = ((ForceCoordinateSystemFeatureResults)results).getOrigin(); 
/*  91 */     return (SimpleFeatureType)results.getSchema();
/*     */   }
/*     */   
/*     */   public Iterator<SimpleFeature> openIterator() {
/*  95 */     return new ForceCoordinateSystemIterator(this.results.features(), getSchema());
/*     */   }
/*     */   
/*     */   public void closeIterator(Iterator close) {
/*  98 */     if (close == null)
/*     */       return; 
/*  99 */     if (close instanceof ForceCoordinateSystemIterator) {
/* 100 */       ForceCoordinateSystemIterator iterator = (ForceCoordinateSystemIterator)close;
/* 101 */       iterator.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 106 */     return this.results.size();
/*     */   }
/*     */   
/*     */   private static SimpleFeatureType forceType(SimpleFeatureType startingType, CoordinateReferenceSystem forcedCS, boolean forceOnlyMissing) throws SchemaException {
/* 110 */     if (forcedCS == null)
/* 111 */       throw new NullPointerException("CoordinateSystem required"); 
/* 113 */     CoordinateReferenceSystem originalCs = (startingType.getGeometryDescriptor() != null) ? startingType.getGeometryDescriptor().getCoordinateReferenceSystem() : null;
/* 116 */     if (forcedCS.equals(originalCs))
/* 117 */       return startingType; 
/* 120 */     return FeatureTypes.transform(startingType, forcedCS, forceOnlyMissing);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 130 */     ReferencedEnvelope env = this.results.getBounds();
/* 131 */     if (env == null)
/* 132 */       return null; 
/* 134 */     env = new ReferencedEnvelope((Envelope)env, getSchema().getCoordinateReferenceSystem());
/* 135 */     return env;
/*     */   }
/*     */   
/*     */   public FeatureCollection<SimpleFeatureType, SimpleFeature> getOrigin() {
/* 166 */     return this.results;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\crs\ForceCoordinateSystemFeatureResults.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */