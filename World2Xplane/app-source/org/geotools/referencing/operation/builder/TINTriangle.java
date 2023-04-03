/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.geometry.DirectPosition2D;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ class TINTriangle extends Polygon {
/*     */   public DirectPosition p0;
/*     */   
/*     */   public DirectPosition p1;
/*     */   
/*     */   public DirectPosition p2;
/*     */   
/*  45 */   private final List<TINTriangle> adjacentTriangles = new ArrayList<TINTriangle>();
/*     */   
/*     */   protected TINTriangle(DirectPosition p0, DirectPosition p1, DirectPosition p2) {
/*  55 */     super(new DirectPosition[] { p0, p1, p2, p0 });
/*  56 */     this.p0 = p0;
/*  57 */     this.p1 = p1;
/*  58 */     this.p2 = p2;
/*     */   }
/*     */   
/*     */   protected Circle getCircumCicle() {
/*  68 */     List<DirectPosition> reducedVertices = reduce();
/*  70 */     CoordinateReferenceSystem crs = ((DirectPosition)reducedVertices.get(1)).getCoordinateReferenceSystem();
/*  73 */     double x1 = ((DirectPosition)reducedVertices.get(1)).getCoordinate()[0];
/*  74 */     double y1 = ((DirectPosition)reducedVertices.get(1)).getCoordinate()[1];
/*  76 */     double x2 = ((DirectPosition)reducedVertices.get(2)).getCoordinate()[0];
/*  77 */     double y2 = ((DirectPosition)reducedVertices.get(2)).getCoordinate()[1];
/*  80 */     double t = 0.5D * (x1 * x1 + y1 * y1 - x1 * x2 - y1 * y2) / (y1 * x2 - x1 * y2);
/*  84 */     DirectPosition2D center = new DirectPosition2D(crs, x2 / 2.0D - t * y2 + this.p0.getCoordinate()[0], y2 / 2.0D + t * x2 + this.p0.getCoordinate()[1]);
/*  88 */     return new Circle(center.getDirectPosition(), center.distance((Point2D)new DirectPosition2D(this.p0)));
/*     */   }
/*     */   
/*     */   public List<TINTriangle> subTriangles(DirectPosition newVertex) {
/* 102 */     ArrayList<TINTriangle> triangles = new ArrayList<TINTriangle>();
/* 103 */     TINTriangle trigA = new TINTriangle(this.p0, this.p1, newVertex);
/* 104 */     TINTriangle trigB = new TINTriangle(this.p1, this.p2, newVertex);
/* 105 */     TINTriangle trigC = new TINTriangle(this.p2, this.p0, newVertex);
/*     */     try {
/* 109 */       trigA.addAdjacentTriangle(trigB);
/* 110 */       trigA.addAdjacentTriangle(trigC);
/* 112 */       trigB.addAdjacentTriangle(trigA);
/* 113 */       trigB.addAdjacentTriangle(trigC);
/* 115 */       trigC.addAdjacentTriangle(trigA);
/* 116 */       trigC.addAdjacentTriangle(trigB);
/* 117 */     } catch (TriangulationException e) {}
/* 122 */     trigA.tryToAddAdjacent(getAdjacentTriangles());
/* 123 */     trigB.tryToAddAdjacent(getAdjacentTriangles());
/* 124 */     trigC.tryToAddAdjacent(getAdjacentTriangles());
/* 126 */     triangles.add(trigA);
/* 127 */     triangles.add(trigB);
/* 128 */     triangles.add(trigC);
/* 130 */     Iterator<TINTriangle> i = getAdjacentTriangles().iterator();
/* 132 */     while (i.hasNext()) {
/* 133 */       TINTriangle trig = i.next();
/* 134 */       trig.removeAdjacent(this);
/*     */     } 
/* 137 */     return triangles;
/*     */   }
/*     */   
/*     */   protected int tryToAddAdjacent(List<TINTriangle> adjacents) {
/* 147 */     Iterator<TINTriangle> i = adjacents.iterator();
/* 148 */     int count = 0;
/* 150 */     while (i.hasNext()) {
/*     */       try {
/* 152 */         TINTriangle candidate = i.next();
/* 154 */         if (candidate.isAdjacent(this) && !this.adjacentTriangles.contains(candidate))
/* 156 */           addAdjacentTriangle(candidate); 
/* 159 */         if (candidate.isAdjacent(this) && !candidate.adjacentTriangles.contains(this)) {
/* 162 */           candidate.addAdjacentTriangle(this);
/* 163 */           count++;
/*     */         } 
/* 165 */       } catch (TriangulationException e) {}
/*     */     } 
/* 170 */     return count;
/*     */   }
/*     */   
/*     */   protected boolean addAdjacentTriangle(TINTriangle adjacent) throws TriangulationException {
/* 181 */     if (isAdjacent(adjacent)) {
/* 182 */       this.adjacentTriangles.add(adjacent);
/* 184 */       return true;
/*     */     } 
/* 187 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isAdjacent(TINTriangle adjacent) throws TriangulationException {
/* 198 */     int identicalVertices = 0;
/* 200 */     if (adjacent.hasVertex(this.p0))
/* 201 */       identicalVertices++; 
/* 204 */     if (adjacent.hasVertex(this.p1))
/* 205 */       identicalVertices++; 
/* 208 */     if (adjacent.hasVertex(this.p2))
/* 209 */       identicalVertices++; 
/* 212 */     if (identicalVertices == 3)
/* 213 */       throw new TriangulationException("Same triangle"); 
/* 216 */     if (identicalVertices == 2)
/* 217 */       return true; 
/* 220 */     return false;
/*     */   }
/*     */   
/*     */   public List<TINTriangle> getAdjacentTriangles() {
/* 228 */     return this.adjacentTriangles;
/*     */   }
/*     */   
/*     */   protected void removeAdjacent(TINTriangle remAdjacent) {
/* 236 */     this.adjacentTriangles.remove(remAdjacent);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\TINTriangle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */