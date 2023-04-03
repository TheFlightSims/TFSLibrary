/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdgeSubdivision;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.Vertex;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class ConformingDelaunayTriangulationBuilder {
/*     */   private Collection siteCoords;
/*     */   
/*     */   private Geometry constraintLines;
/*     */   
/*  53 */   private double tolerance = 0.0D;
/*     */   
/*  54 */   private QuadEdgeSubdivision subdiv = null;
/*     */   
/*  56 */   private Map constraintVertexMap = new TreeMap<>();
/*     */   
/*     */   public void setSites(Geometry geom) {
/*  74 */     this.siteCoords = (Collection)DelaunayTriangulationBuilder.extractUniqueCoordinates(geom);
/*     */   }
/*     */   
/*     */   public void setConstraints(Geometry constraintLines) {
/*  88 */     this.constraintLines = constraintLines;
/*     */   }
/*     */   
/*     */   public void setTolerance(double tolerance) {
/* 100 */     this.tolerance = tolerance;
/*     */   }
/*     */   
/*     */   private void create() {
/* 106 */     if (this.subdiv != null)
/*     */       return; 
/* 108 */     Envelope siteEnv = DelaunayTriangulationBuilder.envelope(this.siteCoords);
/* 110 */     List segments = new ArrayList();
/* 111 */     if (this.constraintLines != null) {
/* 112 */       siteEnv.expandToInclude(this.constraintLines.getEnvelopeInternal());
/* 113 */       createVertices(this.constraintLines);
/* 114 */       segments = createConstraintSegments(this.constraintLines);
/*     */     } 
/* 116 */     List sites = createSiteVertices(this.siteCoords);
/* 118 */     ConformingDelaunayTriangulator cdt = new ConformingDelaunayTriangulator(sites, this.tolerance);
/* 120 */     cdt.setConstraints(segments, new ArrayList(this.constraintVertexMap.values()));
/* 122 */     cdt.formInitialDelaunay();
/* 123 */     cdt.enforceConstraints();
/* 124 */     this.subdiv = cdt.getSubdivision();
/*     */   }
/*     */   
/*     */   private List createSiteVertices(Collection coords) {
/* 129 */     List<ConstraintVertex> verts = new ArrayList();
/* 130 */     for (Iterator<Coordinate> i = coords.iterator(); i.hasNext(); ) {
/* 131 */       Coordinate coord = i.next();
/* 132 */       if (this.constraintVertexMap.containsKey(coord))
/*     */         continue; 
/* 134 */       verts.add(new ConstraintVertex(coord));
/*     */     } 
/* 136 */     return verts;
/*     */   }
/*     */   
/*     */   private void createVertices(Geometry geom) {
/* 141 */     Coordinate[] coords = geom.getCoordinates();
/* 142 */     for (int i = 0; i < coords.length; i++) {
/* 143 */       Vertex v = new ConstraintVertex(coords[i]);
/* 144 */       this.constraintVertexMap.put(coords[i], v);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static List createConstraintSegments(Geometry geom) {
/* 150 */     List lines = LinearComponentExtracter.getLines(geom);
/* 151 */     List constraintSegs = new ArrayList();
/* 152 */     for (Iterator<LineString> i = lines.iterator(); i.hasNext(); ) {
/* 153 */       LineString line = i.next();
/* 154 */       createConstraintSegments(line, constraintSegs);
/*     */     } 
/* 156 */     return constraintSegs;
/*     */   }
/*     */   
/*     */   private static void createConstraintSegments(LineString line, List<Segment> constraintSegs) {
/* 161 */     Coordinate[] coords = line.getCoordinates();
/* 162 */     for (int i = 1; i < coords.length; i++)
/* 163 */       constraintSegs.add(new Segment(coords[i - 1], coords[i])); 
/*     */   }
/*     */   
/*     */   public QuadEdgeSubdivision getSubdivision() {
/* 174 */     create();
/* 175 */     return this.subdiv;
/*     */   }
/*     */   
/*     */   public Geometry getEdges(GeometryFactory geomFact) {
/* 186 */     create();
/* 187 */     return this.subdiv.getEdges(geomFact);
/*     */   }
/*     */   
/*     */   public Geometry getTriangles(GeometryFactory geomFact) {
/* 199 */     create();
/* 200 */     return this.subdiv.getTriangles(geomFact);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\ConformingDelaunayTriangulationBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */