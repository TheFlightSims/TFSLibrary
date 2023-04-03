/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import org.geotools.geometry.GeneralEnvelope;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class RubberSheetBuilder extends MathTransformBuilder {
/*     */   private HashMap trianglesMap;
/*     */   
/*     */   private HashMap trianglesToKeysMap;
/*     */   
/*     */   public RubberSheetBuilder(List<MappedPosition> vectors, List<DirectPosition> vertices) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException, TriangulationException {
/*     */     CoordinateReferenceSystem coordinateReferenceSystem;
/*  80 */     setMappedPositions(vectors);
/*  83 */     if (vertices.size() != 4)
/*  84 */       throw new IllegalArgumentException("The region of interest must have four vertices."); 
/*  88 */     DirectPosition[] ddpp = new DirectPosition[4];
/*  89 */     for (int i = 0; i < vertices.size(); i++)
/*  90 */       ddpp[i] = vertices.get(i); 
/*     */     try {
/*  96 */       coordinateReferenceSystem = getSourceCRS();
/*  97 */     } catch (FactoryException e) {
/*  99 */       coordinateReferenceSystem = ddpp[0].getCoordinateReferenceSystem();
/*     */     } 
/* 101 */     if (!CRS.equalsIgnoreMetadata(coordinateReferenceSystem, ddpp[0].getCoordinateReferenceSystem()) && !CRS.equalsIgnoreMetadata(coordinateReferenceSystem, ddpp[1].getCoordinateReferenceSystem()) && !CRS.equalsIgnoreMetadata(coordinateReferenceSystem, ddpp[2].getCoordinateReferenceSystem()) && !CRS.equalsIgnoreMetadata(coordinateReferenceSystem, ddpp[3].getCoordinateReferenceSystem()))
/* 107 */       throw new MismatchedReferenceSystemException("Region of interest defined by mismatched DirectPositions."); 
/* 114 */     DirectPosition[] dpa = getSourcePoints();
/* 115 */     GeneralEnvelope srcextnt = new GeneralEnvelope(2);
/* 116 */     for (int j = 0; j < dpa.length; j++)
/* 117 */       srcextnt.add(dpa[j]); 
/* 119 */     GeneralEnvelope vtxextnt = new GeneralEnvelope(2);
/* 120 */     vtxextnt.add(ddpp[0]);
/* 121 */     vtxextnt.add(ddpp[1]);
/* 122 */     vtxextnt.add(ddpp[2]);
/* 123 */     vtxextnt.add(ddpp[3]);
/* 124 */     if (!vtxextnt.contains((Envelope)srcextnt, true))
/* 125 */       throw new IllegalArgumentException("The region of interest must contain the control points"); 
/* 127 */     Quadrilateral quad = new Quadrilateral(ddpp[0], ddpp[1], ddpp[2], ddpp[3]);
/* 129 */     MapTriangulationFactory trianglemap = new MapTriangulationFactory(quad, vectors);
/* 131 */     this.trianglesMap = (HashMap)trianglemap.getTriangleMap();
/* 132 */     this.trianglesToKeysMap = mapTrianglesToKey();
/*     */   }
/*     */   
/*     */   public int getMinimumPointCount() {
/* 141 */     return 1;
/*     */   }
/*     */   
/*     */   public HashMap getMapTriangulation() {
/* 150 */     return this.trianglesMap;
/*     */   }
/*     */   
/*     */   protected MathTransform computeMathTransform() throws FactoryException {
/* 162 */     return (MathTransform)new RubberSheetTransform(this.trianglesToKeysMap);
/*     */   }
/*     */   
/*     */   private HashMap mapTrianglesToKey() {
/* 177 */     HashMap trianglesToKeysMap = (HashMap)this.trianglesMap.clone();
/* 179 */     Iterator<Map.Entry> it = trianglesToKeysMap.entrySet().iterator();
/* 181 */     while (it.hasNext()) {
/* 183 */       Map.Entry a = it.next();
/* 184 */       List<MappedPosition> pts = new ArrayList();
/* 186 */       for (int i = 1; i <= 3; i++)
/* 187 */         pts.add(new MappedPosition(((TINTriangle)a.getKey()).getPoints()[i], ((TINTriangle)a.getValue()).getPoints()[i])); 
/*     */       try {
/* 194 */         AffineTransformBuilder calculator = new AffineTransformBuilder(pts);
/* 195 */         a.setValue(calculator.getMathTransform());
/* 196 */       } catch (Exception e) {
/* 199 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 203 */     return trianglesToKeysMap;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\RubberSheetBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */