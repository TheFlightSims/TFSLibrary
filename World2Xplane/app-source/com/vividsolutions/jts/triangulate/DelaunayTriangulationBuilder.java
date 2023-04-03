/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdgeSubdivision;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.Vertex;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DelaunayTriangulationBuilder {
/*     */   private Collection siteCoords;
/*     */   
/*     */   public static CoordinateList extractUniqueCoordinates(Geometry geom) {
/*  57 */     if (geom == null)
/*  58 */       return new CoordinateList(); 
/*  60 */     Coordinate[] coords = geom.getCoordinates();
/*  61 */     return unique(coords);
/*     */   }
/*     */   
/*     */   public static CoordinateList unique(Coordinate[] coords) {
/*  66 */     Coordinate[] coordsCopy = CoordinateArrays.copyDeep(coords);
/*  67 */     Arrays.sort((Object[])coordsCopy);
/*  68 */     CoordinateList coordList = new CoordinateList(coordsCopy, false);
/*  69 */     return coordList;
/*     */   }
/*     */   
/*     */   public static List toVertices(Collection coords) {
/*  79 */     List<Vertex> verts = new ArrayList();
/*  80 */     for (Iterator<Coordinate> i = coords.iterator(); i.hasNext(); ) {
/*  81 */       Coordinate coord = i.next();
/*  82 */       verts.add(new Vertex(coord));
/*     */     } 
/*  84 */     return verts;
/*     */   }
/*     */   
/*     */   public static Envelope envelope(Collection coords) {
/*  95 */     Envelope env = new Envelope();
/*  96 */     for (Iterator<Coordinate> i = coords.iterator(); i.hasNext(); ) {
/*  97 */       Coordinate coord = i.next();
/*  98 */       env.expandToInclude(coord);
/*     */     } 
/* 100 */     return env;
/*     */   }
/*     */   
/* 104 */   private double tolerance = 0.0D;
/*     */   
/* 105 */   private QuadEdgeSubdivision subdiv = null;
/*     */   
/*     */   public void setSites(Geometry geom) {
/* 124 */     this.siteCoords = (Collection)extractUniqueCoordinates(geom);
/*     */   }
/*     */   
/*     */   public void setSites(Collection coords) {
/* 136 */     this.siteCoords = (Collection)unique(CoordinateArrays.toCoordinateArray(coords));
/*     */   }
/*     */   
/*     */   public void setTolerance(double tolerance) {
/* 148 */     this.tolerance = tolerance;
/*     */   }
/*     */   
/*     */   private void create() {
/* 153 */     if (this.subdiv != null)
/*     */       return; 
/* 155 */     Envelope siteEnv = envelope(this.siteCoords);
/* 156 */     List vertices = toVertices(this.siteCoords);
/* 157 */     this.subdiv = new QuadEdgeSubdivision(siteEnv, this.tolerance);
/* 158 */     IncrementalDelaunayTriangulator triangulator = new IncrementalDelaunayTriangulator(this.subdiv);
/* 159 */     triangulator.insertSites(vertices);
/*     */   }
/*     */   
/*     */   public QuadEdgeSubdivision getSubdivision() {
/* 169 */     create();
/* 170 */     return this.subdiv;
/*     */   }
/*     */   
/*     */   public Geometry getEdges(GeometryFactory geomFact) {
/* 181 */     create();
/* 182 */     return this.subdiv.getEdges(geomFact);
/*     */   }
/*     */   
/*     */   public Geometry getTriangles(GeometryFactory geomFact) {
/* 194 */     create();
/* 195 */     return this.subdiv.getTriangles(geomFact);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\DelaunayTriangulationBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */