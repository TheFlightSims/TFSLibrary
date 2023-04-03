/*     */ package org.geotools.data.crs;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.collection.AbstractFeatureCollection;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.OperationNotFoundException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ReprojectFeatureResults extends AbstractFeatureCollection {
/*     */   FeatureCollection<SimpleFeatureType, SimpleFeature> results;
/*     */   
/*     */   MathTransform transform;
/*     */   
/*     */   public ReprojectFeatureResults(FeatureCollection<SimpleFeatureType, SimpleFeature> results, CoordinateReferenceSystem destinationCS) throws IOException, SchemaException, TransformException, OperationNotFoundException, NoSuchElementException, FactoryException {
/* 100 */     super(forceType(origionalType(results), destinationCS));
/* 101 */     this.results = origionalCollection(results);
/* 103 */     CoordinateReferenceSystem originalCs = null;
/* 104 */     if (results instanceof ForceCoordinateSystemFeatureResults) {
/* 105 */       originalCs = ((SimpleFeatureType)results.getSchema()).getGeometryDescriptor().getCoordinateReferenceSystem();
/*     */     } else {
/* 107 */       originalCs = ((SimpleFeatureType)this.results.getSchema()).getGeometryDescriptor().getCoordinateReferenceSystem();
/*     */     } 
/* 108 */     this.transform = CRS.findMathTransform(originalCs, destinationCS, true);
/*     */   }
/*     */   
/*     */   public Iterator openIterator() {
/* 112 */     return new ReprojectFeatureIterator(this.results.features(), getSchema(), this.transform);
/*     */   }
/*     */   
/*     */   public void closeIterator(Iterator close) {
/* 116 */     if (close == null)
/*     */       return; 
/* 117 */     if (close instanceof ReprojectFeatureIterator) {
/* 118 */       ReprojectFeatureIterator iterator = (ReprojectFeatureIterator)close;
/* 119 */       iterator.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 124 */     return this.results.size();
/*     */   }
/*     */   
/*     */   private static FeatureCollection<SimpleFeatureType, SimpleFeature> origionalCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> results) {
/* 129 */     if (results instanceof ReprojectFeatureResults)
/* 130 */       results = ((ReprojectFeatureResults)results).getOrigin(); 
/* 132 */     if (results instanceof ForceCoordinateSystemFeatureResults)
/* 133 */       results = ((ForceCoordinateSystemFeatureResults)results).getOrigin(); 
/* 137 */     return results;
/*     */   }
/*     */   
/*     */   private static SimpleFeatureType origionalType(FeatureCollection<SimpleFeatureType, SimpleFeature> results) {
/* 141 */     if (results instanceof ReprojectFeatureResults)
/* 142 */       results = ((ReprojectFeatureResults)results).getOrigin(); 
/* 144 */     if (results instanceof ForceCoordinateSystemFeatureResults)
/* 145 */       results = ((ForceCoordinateSystemFeatureResults)results).getOrigin(); 
/* 149 */     return (SimpleFeatureType)results.getSchema();
/*     */   }
/*     */   
/*     */   private static SimpleFeatureType forceType(SimpleFeatureType startingType, CoordinateReferenceSystem forcedCS) throws SchemaException {
/* 153 */     if (forcedCS == null)
/* 154 */       throw new NullPointerException("CoordinateSystem required"); 
/* 156 */     CoordinateReferenceSystem originalCs = startingType.getGeometryDescriptor().getCoordinateReferenceSystem();
/* 158 */     if (forcedCS.equals(originalCs))
/* 159 */       return startingType; 
/* 162 */     return FeatureTypes.transform(startingType, forcedCS);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 180 */     SimpleFeatureIterator r = features();
/*     */     try {
/* 182 */       Envelope newBBox = new Envelope();
/* 186 */       while (r.hasNext()) {
/* 187 */         SimpleFeature feature = (SimpleFeature)r.next();
/* 188 */         Geometry geometry = (Geometry)feature.getDefaultGeometry();
/* 189 */         if (geometry != null) {
/* 190 */           Envelope internal = geometry.getEnvelopeInternal();
/* 191 */           newBBox.expandToInclude(internal);
/*     */         } 
/*     */       } 
/* 194 */       return ReferencedEnvelope.reference(newBBox);
/* 195 */     } catch (Exception e) {
/* 196 */       throw new RuntimeException("Exception occurred while computing reprojected bounds", e);
/*     */     } finally {
/* 200 */       r.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public FeatureCollection<SimpleFeatureType, SimpleFeature> getOrigin() {
/* 210 */     return this.results;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\crs\ReprojectFeatureResults.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */