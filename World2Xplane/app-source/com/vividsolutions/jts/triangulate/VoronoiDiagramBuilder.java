/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdgeSubdivision;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class VoronoiDiagramBuilder {
/*     */   private Collection siteCoords;
/*     */   
/*  53 */   private double tolerance = 0.0D;
/*     */   
/*  54 */   private QuadEdgeSubdivision subdiv = null;
/*     */   
/*  55 */   private Envelope clipEnv = null;
/*     */   
/*  56 */   private Envelope diagramEnv = null;
/*     */   
/*     */   public void setSites(Geometry geom) {
/*  75 */     this.siteCoords = (Collection)DelaunayTriangulationBuilder.extractUniqueCoordinates(geom);
/*     */   }
/*     */   
/*     */   public void setSites(Collection coords) {
/*  87 */     this.siteCoords = (Collection)DelaunayTriangulationBuilder.unique(CoordinateArrays.toCoordinateArray(coords));
/*     */   }
/*     */   
/*     */   public void setClipEnvelope(Envelope clipEnv) {
/*  99 */     this.clipEnv = clipEnv;
/*     */   }
/*     */   
/*     */   public void setTolerance(double tolerance) {
/* 110 */     this.tolerance = tolerance;
/*     */   }
/*     */   
/*     */   private void create() {
/* 115 */     if (this.subdiv != null)
/*     */       return; 
/* 117 */     Envelope siteEnv = DelaunayTriangulationBuilder.envelope(this.siteCoords);
/* 118 */     this.diagramEnv = siteEnv;
/* 120 */     double expandBy = Math.max(this.diagramEnv.getWidth(), this.diagramEnv.getHeight());
/* 121 */     this.diagramEnv.expandBy(expandBy);
/* 122 */     if (this.clipEnv != null)
/* 123 */       this.diagramEnv.expandToInclude(this.clipEnv); 
/* 125 */     List vertices = DelaunayTriangulationBuilder.toVertices(this.siteCoords);
/* 126 */     this.subdiv = new QuadEdgeSubdivision(siteEnv, this.tolerance);
/* 127 */     IncrementalDelaunayTriangulator triangulator = new IncrementalDelaunayTriangulator(this.subdiv);
/* 128 */     triangulator.insertSites(vertices);
/*     */   }
/*     */   
/*     */   public QuadEdgeSubdivision getSubdivision() {
/* 138 */     create();
/* 139 */     return this.subdiv;
/*     */   }
/*     */   
/*     */   public Geometry getDiagram(GeometryFactory geomFact) {
/* 151 */     create();
/* 152 */     Geometry polys = this.subdiv.getVoronoiDiagram(geomFact);
/* 155 */     return clipGeometryCollection(polys, this.diagramEnv);
/*     */   }
/*     */   
/*     */   private static Geometry clipGeometryCollection(Geometry geom, Envelope clipEnv) {
/* 160 */     Geometry clipPoly = geom.getFactory().toGeometry(clipEnv);
/* 161 */     List<Geometry> clipped = new ArrayList();
/* 162 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 163 */       Geometry g = geom.getGeometryN(i);
/* 164 */       Geometry result = null;
/* 166 */       if (clipEnv.contains(g.getEnvelopeInternal())) {
/* 167 */         result = g;
/* 168 */       } else if (clipEnv.intersects(g.getEnvelopeInternal())) {
/* 169 */         result = clipPoly.intersection(g);
/* 171 */         result.setUserData(g.getUserData());
/*     */       } 
/* 174 */       if (result != null && !result.isEmpty())
/* 175 */         clipped.add(result); 
/*     */     } 
/* 178 */     return (Geometry)geom.getFactory().createGeometryCollection(GeometryFactory.toGeometryArray(clipped));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\VoronoiDiagramBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */