/*     */ package org.geotools.data.crs;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.DelegatingFeatureReader;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.GeometryCoordinateSequenceTransformer;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.OperationNotFoundException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ReprojectFeatureReader implements DelegatingFeatureReader<SimpleFeatureType, SimpleFeature> {
/*     */   FeatureReader<SimpleFeatureType, SimpleFeature> reader;
/*     */   
/*     */   SimpleFeatureType schema;
/*     */   
/*  82 */   GeometryCoordinateSequenceTransformer transformer = new GeometryCoordinateSequenceTransformer();
/*     */   
/*     */   public ReprojectFeatureReader(FeatureReader<SimpleFeatureType, SimpleFeature> reader, SimpleFeatureType schema, MathTransform transform) {
/*  95 */     this.reader = reader;
/*  96 */     this.schema = schema;
/*  97 */     this.transformer.setMathTransform(transform);
/*     */   }
/*     */   
/*     */   public ReprojectFeatureReader(FeatureReader<SimpleFeatureType, SimpleFeature> reader, CoordinateReferenceSystem cs) throws SchemaException, OperationNotFoundException, NoSuchElementException, FactoryException {
/* 108 */     if (cs == null)
/* 109 */       throw new NullPointerException("CoordinateSystem required"); 
/* 112 */     SimpleFeatureType type = (SimpleFeatureType)reader.getFeatureType();
/* 113 */     CoordinateReferenceSystem original = type.getGeometryDescriptor().getCoordinateReferenceSystem();
/* 116 */     if (cs.equals(original))
/* 117 */       throw new IllegalArgumentException("CoordinateSystem " + cs + " already used (check before using wrapper)"); 
/* 121 */     this.schema = FeatureTypes.transform(type, cs);
/* 122 */     this.reader = reader;
/* 123 */     this.transformer.setMathTransform(CRS.findMathTransform(original, cs, true));
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> getDelegate() {
/* 127 */     return this.reader;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 143 */     if (this.schema == null)
/* 144 */       throw new IllegalStateException("Reader has already been closed"); 
/* 147 */     return this.schema;
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 168 */     if (this.reader == null)
/* 169 */       throw new IllegalStateException("Reader has already been closed"); 
/* 172 */     SimpleFeature next = (SimpleFeature)this.reader.next();
/* 173 */     Object[] attributes = next.getAttributes().toArray();
/*     */     try {
/* 176 */       for (int i = 0; i < attributes.length; i++) {
/* 177 */         if (attributes[i] instanceof Geometry)
/* 178 */           attributes[i] = this.transformer.transform((Geometry)attributes[i]); 
/*     */       } 
/* 181 */     } catch (TransformException e) {
/* 182 */       throw new DataSourceException("A transformation exception occurred while reprojecting data on the fly", e);
/*     */     } 
/* 186 */     return SimpleFeatureBuilder.build(this.schema, attributes, next.getID());
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 203 */     if (this.reader == null)
/* 204 */       throw new IllegalStateException("Reader has already been closed"); 
/* 207 */     return this.reader.hasNext();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 223 */     if (this.reader == null)
/* 224 */       throw new IllegalStateException("Reader has already been closed"); 
/* 227 */     this.reader.close();
/* 228 */     this.reader = null;
/* 229 */     this.schema = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\crs\ReprojectFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */