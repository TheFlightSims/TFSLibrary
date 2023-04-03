/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ 
/*     */ class TriangulationFactory {
/*     */   private List<TINTriangle> triangles;
/*     */   
/*     */   protected TriangulationFactory(Quadrilateral quad, DirectPosition[] pt) throws TriangulationException {
/*  48 */     List<DirectPosition> vertices = new ArrayList<DirectPosition>();
/*  50 */     for (int j = 0; j < pt.length; j++)
/*  51 */       vertices.add(pt[j]); 
/*  54 */     if (!quad.containsAll(vertices))
/*  55 */       throw new TriangulationException("Point is outside triangles"); 
/*  58 */     this.triangles = quad.getTriangles();
/*  60 */     for (Iterator<DirectPosition> i = vertices.iterator(); i.hasNext(); ) {
/*  61 */       DirectPosition vertex = i.next();
/*  62 */       insertPoint(vertex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<TINTriangle> getTriangulation() {
/*  72 */     return this.triangles;
/*     */   }
/*     */   
/*     */   protected void recursiveDelaunayTest(List<?> ChangedTriangles) throws TriangulationException {
/*  85 */     int i = ChangedTriangles.size();
/*  87 */     while (i != 0) {
/*  88 */       this.triangles.removeAll(ChangedTriangles);
/*  89 */       ChangedTriangles = insertTriangles(ChangedTriangles);
/*  90 */       i = ChangedTriangles.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected List insertTriangles(List trian) throws TriangulationException {
/* 105 */     List ChangedTriangles = new ArrayList();
/* 107 */     for (Iterator<TINTriangle> i = trian.iterator(); i.hasNext(); ) {
/* 108 */       TINTriangle trig = i.next();
/* 110 */       if (trig.getAdjacentTriangles().size() <= 2) {
/* 112 */         this.triangles.add(trig);
/*     */         continue;
/*     */       } 
/* 114 */       ChangedTriangles.addAll(delaunayCircleTest(trig));
/*     */     } 
/* 118 */     return ChangedTriangles;
/*     */   }
/*     */   
/*     */   private List delaunayCircleTest(TINTriangle triangle) throws TriangulationException {
/* 134 */     List<? extends TINTriangle> changedTriangles = new ArrayList();
/* 136 */     Iterator<TINTriangle> j = triangle.getAdjacentTriangles().iterator();
/* 137 */     int ct = changedTriangles.size();
/* 139 */     while (j.hasNext() && changedTriangles.size() == ct) {
/* 140 */       TINTriangle adjacent = j.next();
/* 142 */       List<? extends TINTriangle> NewTriangles = new ArrayList();
/* 145 */       if (triangle.getCircumCicle().contains(adjacent.p1) || triangle.getCircumCicle().contains(adjacent.p0) || triangle.getCircumCicle().contains(adjacent.p2)) {
/* 148 */         this.triangles.remove(triangle);
/* 149 */         this.triangles.remove(adjacent);
/* 151 */         NewTriangles.addAll(alternativeTriangles(triangle, adjacent));
/* 153 */         this.triangles.addAll(NewTriangles);
/* 154 */         changedTriangles = NewTriangles;
/*     */         continue;
/*     */       } 
/* 155 */       if (!this.triangles.contains(triangle))
/* 156 */         this.triangles.add(triangle); 
/*     */     } 
/* 160 */     return changedTriangles;
/*     */   }
/*     */   
/*     */   public void insertPoint(DirectPosition newVertex) throws TriangulationException {
/* 172 */     TINTriangle triangleContainingNewVertex = triangleContains(newVertex);
/* 174 */     if (triangleContainingNewVertex == null)
/* 175 */       throw new TriangulationException("Point is outside triangles"); 
/* 178 */     this.triangles.remove(triangleContainingNewVertex);
/* 179 */     recursiveDelaunayTest(triangleContainingNewVertex.subTriangles(newVertex));
/*     */   }
/*     */   
/*     */   private List alternativeTriangles(TINTriangle ABC, TINTriangle BCD) throws TriangulationException {
/* 196 */     ArrayList<DirectPosition> ABCvertices = new ArrayList();
/* 197 */     ArrayList<DirectPosition> BCDvertices = new ArrayList();
/* 199 */     ABCvertices.add(ABC.p0);
/* 200 */     ABCvertices.add(ABC.p1);
/* 201 */     ABCvertices.add(ABC.p2);
/* 202 */     BCDvertices.add(BCD.p0);
/* 203 */     BCDvertices.add(BCD.p1);
/* 204 */     BCDvertices.add(BCD.p2);
/* 206 */     ArrayList<DirectPosition> sharedVertices = new ArrayList();
/* 207 */     ArrayList<DirectPosition> unsharedVertices = new ArrayList();
/* 210 */     for (Iterator<DirectPosition> i = ABCvertices.iterator(); i.hasNext(); ) {
/* 211 */       DirectPosition vertex = i.next();
/* 213 */       if (!BCDvertices.contains(vertex)) {
/* 214 */         unsharedVertices.add(vertex);
/*     */         continue;
/*     */       } 
/* 215 */       if (BCDvertices.contains(vertex)) {
/* 216 */         sharedVertices.add(vertex);
/* 217 */         BCDvertices.remove(vertex);
/*     */         continue;
/*     */       } 
/* 219 */       throw new TriangulationException("should never reach here");
/*     */     } 
/* 223 */     unsharedVertices.addAll(BCDvertices);
/* 225 */     if (sharedVertices.size() < 2)
/* 226 */       throw new TriangulationException("Unable to make alternative triangles"); 
/* 231 */     ABC.removeAdjacent(BCD);
/* 232 */     BCD.removeAdjacent(ABC);
/* 235 */     TINTriangle trigA = new TINTriangle(sharedVertices.get(0), unsharedVertices.get(0), unsharedVertices.get(1));
/* 238 */     TINTriangle trigB = new TINTriangle(unsharedVertices.get(0), unsharedVertices.get(1), sharedVertices.get(1));
/* 243 */     trigA.addAdjacentTriangle(trigB);
/* 244 */     trigB.addAdjacentTriangle(trigA);
/* 245 */     trigA.tryToAddAdjacent(BCD.getAdjacentTriangles());
/* 246 */     trigA.tryToAddAdjacent(ABC.getAdjacentTriangles());
/* 247 */     trigB.tryToAddAdjacent(BCD.getAdjacentTriangles());
/* 248 */     trigB.tryToAddAdjacent(ABC.getAdjacentTriangles());
/* 250 */     List<TINTriangle> list = new ArrayList();
/* 251 */     list.add(trigA);
/* 252 */     list.add(trigB);
/* 255 */     for (Iterator<TINTriangle> iterator2 = ABC.getAdjacentTriangles().iterator(); iterator2.hasNext(); ) {
/* 256 */       TINTriangle trig = iterator2.next();
/* 257 */       trig.removeAdjacent(ABC);
/* 258 */       trig.tryToAddAdjacent(list);
/*     */     } 
/* 261 */     for (Iterator<TINTriangle> iterator1 = BCD.getAdjacentTriangles().iterator(); iterator1.hasNext(); ) {
/* 262 */       TINTriangle trig = iterator1.next();
/* 263 */       trig.removeAdjacent(BCD);
/* 264 */       trig.tryToAddAdjacent(list);
/*     */     } 
/* 267 */     return list;
/*     */   }
/*     */   
/*     */   private TINTriangle triangleContains(DirectPosition p) {
/* 279 */     for (Iterator<TINTriangle> i = this.triangles.iterator(); i.hasNext(); ) {
/* 280 */       TINTriangle triangle = i.next();
/* 282 */       if (triangle.containsOrIsVertex(p))
/* 283 */         return triangle; 
/*     */     } 
/* 287 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\TriangulationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */