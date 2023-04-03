/*     */ package org.opensphere.geometry.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
/*     */ import com.vividsolutions.jts.operation.linemerge.LineMerger;
/*     */ import com.vividsolutions.jts.triangulate.ConformingDelaunayTriangulationBuilder;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdge;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdgeSubdivision;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdgeTriangle;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.Vertex;
/*     */ import com.vividsolutions.jts.util.UniqueCoordinateArrayFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import org.opensphere.geometry.triangulation.DoubleComparator;
/*     */ import org.opensphere.geometry.triangulation.model.Edge;
/*     */ import org.opensphere.geometry.triangulation.model.Triangle;
/*     */ import org.opensphere.geometry.triangulation.model.Vertex;
/*     */ 
/*     */ public class ConcaveHull {
/*     */   private GeometryFactory geomFactory;
/*     */   
/*     */   private GeometryCollection geometries;
/*     */   
/*     */   private double threshold;
/*     */   
/*  74 */   public HashMap<LineSegment, Integer> segments = new HashMap<>();
/*     */   
/*  75 */   public HashMap<Integer, Edge> edges = new HashMap<>();
/*     */   
/*  76 */   public HashMap<Integer, Triangle> triangles = new HashMap<>();
/*     */   
/*  77 */   public TreeMap<Integer, Edge> lengths = new TreeMap<>();
/*     */   
/*  79 */   public HashMap<Integer, Edge> shortLengths = new HashMap<>();
/*     */   
/*  81 */   public HashMap<Coordinate, Integer> coordinates = new HashMap<>();
/*     */   
/*  82 */   public HashMap<Integer, Vertex> vertices = new HashMap<>();
/*     */   
/*     */   public ConcaveHull(Geometry geometry, double threshold) {
/*  92 */     this.geometries = transformIntoPointGeometryCollection(geometry);
/*  93 */     this.threshold = threshold;
/*  94 */     this.geomFactory = geometry.getFactory();
/*     */   }
/*     */   
/*     */   public ConcaveHull(GeometryCollection geometries, double threshold) {
/* 104 */     this.geometries = transformIntoPointGeometryCollection(geometries);
/* 105 */     this.threshold = threshold;
/* 106 */     this.geomFactory = geometries.getFactory();
/*     */   }
/*     */   
/*     */   private static GeometryCollection transformIntoPointGeometryCollection(Geometry geom) {
/* 116 */     UniqueCoordinateArrayFilter filter = new UniqueCoordinateArrayFilter();
/* 117 */     geom.apply((CoordinateFilter)filter);
/* 118 */     Coordinate[] coord = filter.getCoordinates();
/* 120 */     Geometry[] geometries = new Geometry[coord.length];
/* 121 */     for (int i = 0; i < coord.length; i++) {
/* 122 */       Coordinate[] c = { coord[i] };
/* 123 */       CoordinateArraySequence cs = new CoordinateArraySequence(c);
/* 124 */       geometries[i] = (Geometry)new Point((CoordinateSequence)cs, geom.getFactory());
/*     */     } 
/* 127 */     return new GeometryCollection(geometries, geom.getFactory());
/*     */   }
/*     */   
/*     */   private static GeometryCollection transformIntoPointGeometryCollection(GeometryCollection gc) {
/* 138 */     UniqueCoordinateArrayFilter filter = new UniqueCoordinateArrayFilter();
/* 139 */     gc.apply((CoordinateFilter)filter);
/* 140 */     Coordinate[] coord = filter.getCoordinates();
/* 142 */     Geometry[] geometries = new Geometry[coord.length];
/* 143 */     for (int i = 0; i < coord.length; i++) {
/* 144 */       Coordinate[] c = { coord[i] };
/* 145 */       CoordinateArraySequence cs = new CoordinateArraySequence(c);
/* 146 */       geometries[i] = (Geometry)new Point((CoordinateSequence)cs, gc.getFactory());
/*     */     } 
/* 149 */     return new GeometryCollection(geometries, gc.getFactory());
/*     */   }
/*     */   
/*     */   public Geometry getConcaveHull() {
/* 166 */     if (this.geometries.getNumGeometries() == 0)
/* 167 */       return (Geometry)this.geomFactory.createGeometryCollection(null); 
/* 169 */     if (this.geometries.getNumGeometries() == 1)
/* 170 */       return this.geometries.getGeometryN(0); 
/* 172 */     if (this.geometries.getNumGeometries() == 2)
/* 173 */       return (Geometry)this.geomFactory.createLineString(this.geometries.getCoordinates()); 
/* 176 */     return concaveHull();
/*     */   }
/*     */   
/*     */   private Geometry concaveHull() {
/* 189 */     ConformingDelaunayTriangulationBuilder cdtb = new ConformingDelaunayTriangulationBuilder();
/* 192 */     cdtb.setSites((Geometry)this.geometries);
/* 194 */     QuadEdgeSubdivision qes = cdtb.getSubdivision();
/* 196 */     Collection<QuadEdge> quadEdges = qes.getEdges();
/* 197 */     List<QuadEdgeTriangle> qeTriangles = QuadEdgeTriangle.createOn(qes);
/* 198 */     Collection<Vertex> qeVertices = qes.getVertices(false);
/* 201 */     int iV = 0;
/* 202 */     for (Vertex v : qeVertices) {
/* 203 */       this.coordinates.put(v.getCoordinate(), Integer.valueOf(iV));
/* 204 */       this.vertices.put(Integer.valueOf(iV), new Vertex(iV, v.getCoordinate()));
/* 205 */       iV++;
/*     */     } 
/* 209 */     List<QuadEdge> qeFrameBorder = new ArrayList<>();
/* 210 */     List<QuadEdge> qeFrame = new ArrayList<>();
/* 211 */     List<QuadEdge> qeBorder = new ArrayList<>();
/* 213 */     for (QuadEdge qe : quadEdges) {
/* 214 */       if (qes.isFrameBorderEdge(qe))
/* 215 */         qeFrameBorder.add(qe); 
/* 217 */       if (qes.isFrameEdge(qe))
/* 218 */         qeFrame.add(qe); 
/*     */     } 
/* 223 */     for (int j = 0; j < qeFrameBorder.size(); j++) {
/* 224 */       QuadEdge q = qeFrameBorder.get(j);
/* 225 */       if (!qeFrame.contains(q))
/* 226 */         qeBorder.add(q); 
/*     */     } 
/* 231 */     for (QuadEdge qe : qeFrame)
/* 232 */       qes.delete(qe); 
/* 235 */     HashMap<QuadEdge, Double> qeDistances = new HashMap<>();
/* 236 */     for (QuadEdge qe : quadEdges)
/* 237 */       qeDistances.put(qe, Double.valueOf(qe.toLineSegment().getLength())); 
/* 240 */     DoubleComparator dc = new DoubleComparator(qeDistances);
/* 241 */     TreeMap<QuadEdge, Double> qeSorted = new TreeMap<>((Comparator<? super QuadEdge>)dc);
/* 242 */     qeSorted.putAll(qeDistances);
/* 245 */     int i = 0;
/* 246 */     for (QuadEdge qe : qeSorted.keySet()) {
/*     */       Edge edge;
/* 247 */       LineSegment s = qe.toLineSegment();
/* 248 */       s.normalize();
/* 250 */       Integer idS = this.coordinates.get(s.p0);
/* 251 */       Integer idD = this.coordinates.get(s.p1);
/* 252 */       Vertex oV = this.vertices.get(idS);
/* 253 */       Vertex eV = this.vertices.get(idD);
/* 256 */       if (qeBorder.contains(qe)) {
/* 257 */         oV.setBorder(true);
/* 258 */         eV.setBorder(true);
/* 259 */         edge = new Edge(i, s, oV, eV, true);
/* 260 */         if (s.getLength() < this.threshold) {
/* 261 */           this.shortLengths.put(Integer.valueOf(i), edge);
/*     */         } else {
/* 263 */           this.lengths.put(Integer.valueOf(i), edge);
/*     */         } 
/*     */       } else {
/* 266 */         edge = new Edge(i, s, oV, eV, false);
/*     */       } 
/* 268 */       this.edges.put(Integer.valueOf(i), edge);
/* 269 */       this.segments.put(s, Integer.valueOf(i));
/* 270 */       i++;
/*     */     } 
/* 276 */     i = 0;
/* 277 */     for (QuadEdgeTriangle qet : qeTriangles) {
/* 278 */       LineSegment sA = qet.getEdge(0).toLineSegment();
/* 279 */       LineSegment sB = qet.getEdge(1).toLineSegment();
/* 280 */       LineSegment sC = qet.getEdge(2).toLineSegment();
/* 281 */       sA.normalize();
/* 282 */       sB.normalize();
/* 283 */       sC.normalize();
/* 285 */       Edge edgeA = this.edges.get(this.segments.get(sA));
/* 286 */       Edge edgeB = this.edges.get(this.segments.get(sB));
/* 287 */       Edge edgeC = this.edges.get(this.segments.get(sC));
/* 289 */       Triangle triangle = new Triangle(i, qet.isBorder());
/* 290 */       triangle.addEdge(edgeA);
/* 291 */       triangle.addEdge(edgeB);
/* 292 */       triangle.addEdge(edgeC);
/* 294 */       edgeA.addTriangle(triangle);
/* 295 */       edgeB.addTriangle(triangle);
/* 296 */       edgeC.addTriangle(triangle);
/* 298 */       this.triangles.put(Integer.valueOf(i), triangle);
/* 299 */       i++;
/*     */     } 
/* 303 */     for (Edge edge : this.edges.values()) {
/* 304 */       if (edge.getTriangles().size() != 1) {
/* 305 */         Triangle tA = edge.getTriangles().get(0);
/* 306 */         Triangle tB = edge.getTriangles().get(1);
/* 307 */         tA.addNeighbour(tB);
/* 308 */         tB.addNeighbour(tA);
/*     */       } 
/*     */     } 
/* 314 */     int index = 0;
/* 315 */     while (index != -1) {
/* 316 */       index = -1;
/* 318 */       Edge e = null;
/* 321 */       int si = this.lengths.size();
/* 323 */       if (si != 0) {
/* 324 */         Map.Entry<Integer, Edge> entry = this.lengths.firstEntry();
/* 325 */         int ind = ((Integer)entry.getKey()).intValue();
/* 326 */         if (((Edge)entry.getValue()).getGeometry().getLength() > this.threshold) {
/* 327 */           index = ind;
/* 328 */           e = entry.getValue();
/*     */         } 
/*     */       } 
/* 332 */       if (index != -1) {
/* 333 */         Triangle triangle = e.getTriangles().get(0);
/* 334 */         List<Triangle> neighbours = triangle.getNeighbours();
/* 336 */         if (neighbours.size() == 1) {
/* 337 */           this.shortLengths.put(Integer.valueOf(e.getId()), e);
/* 338 */           this.lengths.remove(Integer.valueOf(e.getId()));
/*     */           continue;
/*     */         } 
/* 340 */         Edge e0 = triangle.getEdges().get(0);
/* 341 */         Edge e1 = triangle.getEdges().get(1);
/* 343 */         if (e0.getOV().isBorder() && e0.getEV().isBorder() && e1.getOV().isBorder() && e1.getEV().isBorder()) {
/* 345 */           this.shortLengths.put(Integer.valueOf(e.getId()), e);
/* 346 */           this.lengths.remove(Integer.valueOf(e.getId()));
/*     */           continue;
/*     */         } 
/* 349 */         Triangle tA = neighbours.get(0);
/* 350 */         Triangle tB = neighbours.get(1);
/* 351 */         tA.setBorder(true);
/* 352 */         tB.setBorder(true);
/* 353 */         this.triangles.remove(Integer.valueOf(triangle.getId()));
/* 354 */         tA.removeNeighbour(triangle);
/* 355 */         tB.removeNeighbour(triangle);
/* 358 */         List<Edge> ee = triangle.getEdges();
/* 359 */         Edge eA = ee.get(0);
/* 360 */         Edge eB = ee.get(1);
/* 361 */         Edge eC = ee.get(2);
/* 363 */         if (eA.isBorder()) {
/* 364 */           this.edges.remove(Integer.valueOf(eA.getId()));
/* 365 */           eB.setBorder(true);
/* 366 */           eB.getOV().setBorder(true);
/* 367 */           eB.getEV().setBorder(true);
/* 368 */           eC.setBorder(true);
/* 369 */           eC.getOV().setBorder(true);
/* 370 */           eC.getEV().setBorder(true);
/* 373 */           eB.removeTriangle(triangle);
/* 374 */           eC.removeTriangle(triangle);
/* 376 */           if (eB.getGeometry().getLength() < this.threshold) {
/* 377 */             this.shortLengths.put(Integer.valueOf(eB.getId()), eB);
/*     */           } else {
/* 379 */             this.lengths.put(Integer.valueOf(eB.getId()), eB);
/*     */           } 
/* 381 */           if (eC.getGeometry().getLength() < this.threshold) {
/* 382 */             this.shortLengths.put(Integer.valueOf(eC.getId()), eC);
/*     */           } else {
/* 384 */             this.lengths.put(Integer.valueOf(eC.getId()), eC);
/*     */           } 
/* 386 */           this.lengths.remove(Integer.valueOf(eA.getId()));
/*     */           continue;
/*     */         } 
/* 387 */         if (eB.isBorder()) {
/* 388 */           this.edges.remove(Integer.valueOf(eB.getId()));
/* 389 */           eA.setBorder(true);
/* 390 */           eA.getOV().setBorder(true);
/* 391 */           eA.getEV().setBorder(true);
/* 392 */           eC.setBorder(true);
/* 393 */           eC.getOV().setBorder(true);
/* 394 */           eC.getEV().setBorder(true);
/* 397 */           eA.removeTriangle(triangle);
/* 398 */           eC.removeTriangle(triangle);
/* 400 */           if (eA.getGeometry().getLength() < this.threshold) {
/* 401 */             this.shortLengths.put(Integer.valueOf(eA.getId()), eA);
/*     */           } else {
/* 403 */             this.lengths.put(Integer.valueOf(eA.getId()), eA);
/*     */           } 
/* 405 */           if (eC.getGeometry().getLength() < this.threshold) {
/* 406 */             this.shortLengths.put(Integer.valueOf(eC.getId()), eC);
/*     */           } else {
/* 408 */             this.lengths.put(Integer.valueOf(eC.getId()), eC);
/*     */           } 
/* 410 */           this.lengths.remove(Integer.valueOf(eB.getId()));
/*     */           continue;
/*     */         } 
/* 412 */         this.edges.remove(Integer.valueOf(eC.getId()));
/* 413 */         eA.setBorder(true);
/* 414 */         eA.getOV().setBorder(true);
/* 415 */         eA.getEV().setBorder(true);
/* 416 */         eB.setBorder(true);
/* 417 */         eB.getOV().setBorder(true);
/* 418 */         eB.getEV().setBorder(true);
/* 420 */         eA.removeTriangle(triangle);
/* 421 */         eB.removeTriangle(triangle);
/* 423 */         if (eA.getGeometry().getLength() < this.threshold) {
/* 424 */           this.shortLengths.put(Integer.valueOf(eA.getId()), eA);
/*     */         } else {
/* 426 */           this.lengths.put(Integer.valueOf(eA.getId()), eA);
/*     */         } 
/* 428 */         if (eB.getGeometry().getLength() < this.threshold) {
/* 429 */           this.shortLengths.put(Integer.valueOf(eB.getId()), eB);
/*     */         } else {
/* 431 */           this.lengths.put(Integer.valueOf(eB.getId()), eB);
/*     */         } 
/* 433 */         this.lengths.remove(Integer.valueOf(eC.getId()));
/*     */       } 
/*     */     } 
/* 441 */     List<LineString> edges = new ArrayList<>();
/* 442 */     for (Edge e : this.lengths.values()) {
/* 443 */       LineString l = e.getGeometry().toGeometry(this.geomFactory);
/* 444 */       edges.add(l);
/*     */     } 
/* 447 */     for (Edge e : this.shortLengths.values()) {
/* 448 */       LineString l = e.getGeometry().toGeometry(this.geomFactory);
/* 449 */       edges.add(l);
/*     */     } 
/* 453 */     LineMerger lineMerger = new LineMerger();
/* 454 */     lineMerger.add(edges);
/* 455 */     LineString merge = lineMerger.getMergedLineStrings().iterator().next();
/* 457 */     if (merge.isRing()) {
/* 458 */       LinearRing lr = new LinearRing(merge.getCoordinateSequence(), this.geomFactory);
/* 459 */       Polygon concaveHull = new Polygon(lr, null, this.geomFactory);
/* 460 */       return (Geometry)concaveHull;
/*     */     } 
/* 463 */     return (Geometry)merge;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opensphere\geometry\algorithm\ConcaveHull.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */