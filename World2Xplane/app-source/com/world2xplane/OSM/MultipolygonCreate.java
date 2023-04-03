/*     */ package com.world2xplane.OSM;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.operation.valid.IsValidOp;
/*     */ import com.vividsolutions.jts.operation.valid.TopologyValidationError;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.Geom.CoordinateConversion;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.Geom.MultiMap;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Path2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class MultipolygonCreate {
/*  51 */   private static Logger log = LoggerFactory.getLogger(MultipolygonCreate.class);
/*     */   
/*     */   public List<JoinedPolygon> outerWays;
/*     */   
/*     */   public List<JoinedPolygon> innerWays;
/*     */   
/*     */   public enum PolygonIntersection {
/*  54 */     FIRST_INSIDE_SECOND, SECOND_INSIDE_FIRST, OUTSIDE, CROSSING;
/*     */   }
/*     */   
/*     */   public static class JoinedPolygon {
/*     */     public final List<Way> ways;
/*     */     
/*     */     public final List<Boolean> reversed;
/*     */     
/*     */     public List<Node> nodes;
/*     */     
/*     */     public OSMRelation.Member member;
/*     */     
/*     */     public JoinedPolygon(List<Way> ways, List<Boolean> reversed) {
/*  68 */       this.ways = ways;
/*  69 */       this.reversed = reversed;
/*  70 */       this.nodes = getNodes();
/*  71 */       if (ways != null && ways.size() > 0)
/*  72 */         this.member = ((Way)ways.get(0)).getOsmPolygon(); 
/*     */     }
/*     */     
/*     */     public JoinedPolygon(Way way) {
/*  82 */       this.ways = Collections.singletonList(way);
/*  83 */       this.reversed = Collections.singletonList(Boolean.FALSE);
/*  84 */       this.nodes = getNodes();
/*  85 */       this.member = way.getOsmPolygon();
/*     */     }
/*     */     
/*     */     private List<Node> getNodes() {
/*  95 */       List<Node> nodes = new ArrayList<>();
/*  97 */       for (int waypos = 0; waypos < this.ways.size(); waypos++) {
/*  98 */         Way way = this.ways.get(waypos);
/*  99 */         boolean reversed = ((Boolean)this.reversed.get(waypos)).booleanValue();
/* 101 */         if (!reversed) {
/* 102 */           for (int pos = 0; pos < way.getNodesCount() - 1; pos++)
/* 103 */             nodes.add(way.getNode(pos)); 
/*     */         } else {
/* 106 */           for (int pos = way.getNodesCount() - 1; pos > 0; pos--)
/* 107 */             nodes.add(way.getNode(pos)); 
/*     */         } 
/*     */       } 
/* 112 */       return nodes;
/*     */     }
/*     */     
/*     */     public Geometry toJTSShape() {
/* 116 */       LinearRing2D linearRing2D = new LinearRing2D();
/* 117 */       for (Node item : this.nodes)
/* 118 */         linearRing2D.addVertex(new Point2D(item.getLon(), item.getLat())); 
/* 120 */       return GeomUtils.linearRingToJTSPolygon(linearRing2D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PolygonLevel {
/*     */     public final int level;
/*     */     
/*     */     public final MultipolygonCreate.JoinedPolygon outerWay;
/*     */     
/*     */     public List<MultipolygonCreate.JoinedPolygon> innerWays;
/*     */     
/*     */     public PolygonLevel(MultipolygonCreate.JoinedPolygon _pol, int _level) {
/* 137 */       this.outerWay = _pol;
/* 138 */       this.level = _level;
/* 139 */       this.innerWays = new ArrayList<>();
/*     */     }
/*     */   }
/*     */   
/*     */   public MultipolygonCreate(List<JoinedPolygon> outerWays, List<JoinedPolygon> innerWays) {
/* 147 */     this.outerWays = outerWays;
/* 148 */     this.innerWays = innerWays;
/*     */   }
/*     */   
/*     */   public MultipolygonCreate() {
/* 152 */     this.outerWays = new ArrayList<>(0);
/* 153 */     this.innerWays = new ArrayList<>(0);
/*     */   }
/*     */   
/*     */   public boolean makeFromRings(Collection<OSMRelation.Member> linearRing2Ds) {
/* 158 */     HashMap<Integer, Node> nodes = new HashMap<>();
/* 159 */     for (OSMRelation.Member item : linearRing2Ds) {
/* 160 */       for (int x = 0; x < item.shape.vertexNumber(); x++) {
/* 161 */         Point2D point = item.shape.vertex(x);
/* 162 */         Node node = new Node(point);
/* 163 */         nodes.put(Integer.valueOf(node.hashCode()), node);
/*     */       } 
/*     */     } 
/* 167 */     Collection<Way> ways = new ArrayList<>();
/* 168 */     int id = 1;
/* 169 */     for (OSMRelation.Member item : linearRing2Ds) {
/* 170 */       Way way = new Way(item);
/* 171 */       for (int x = 0; x < item.shape.vertexNumber(); x++) {
/* 172 */         Point2D point = item.shape.vertex(x);
/* 173 */         Node n = new Node(point);
/* 174 */         Node node = nodes.get(Integer.valueOf(n.hashCode()));
/* 175 */         if (node == null)
/* 176 */           throw new RuntimeException("Can't find node"); 
/* 178 */         way.getNodes().add(node);
/*     */       } 
/* 180 */       way.setId(id);
/* 181 */       id++;
/* 182 */       ways.add(way);
/*     */     } 
/* 185 */     return makeFromWays(ways);
/*     */   }
/*     */   
/*     */   private Way makeFromOsmPolygon(OSMRelation.Member ring) {
/* 190 */     HashMap<Integer, Node> nodes = new HashMap<>();
/* 191 */     for (int x = 0; x < ring.shape.vertexNumber(); x++) {
/* 192 */       Point2D point = ring.shape.vertex(x);
/* 193 */       Node node = new Node(point);
/* 194 */       nodes.put(Integer.valueOf(node.hashCode()), node);
/*     */     } 
/* 196 */     Way way = new Way(ring);
/* 197 */     for (int i = 0; i < ring.shape.vertexNumber(); i++) {
/* 198 */       Point2D point = ring.shape.vertex(i);
/* 199 */       Node n = new Node(point);
/* 200 */       Node node = nodes.get(Integer.valueOf(n.hashCode()));
/* 201 */       if (node == null)
/* 202 */         throw new RuntimeException("Can't find node"); 
/* 204 */       way.getNodes().add(node);
/*     */     } 
/* 206 */     return way;
/*     */   }
/*     */   
/*     */   public boolean makeFromWays(Collection<Way> ways) {
/* 210 */     List<JoinedPolygon> joinedWays = new ArrayList<>();
/* 213 */     MultiMap<Node, Way> nodesWithConnectedWays = new MultiMap();
/* 214 */     Set<Way> usedWays = new HashSet<>();
/* 216 */     for (Way w : ways) {
/* 217 */       if (w.getNodesCount() < 2)
/* 218 */         return false; 
/* 221 */       if (w.isClosed()) {
/* 223 */         JoinedPolygon jw = new JoinedPolygon(w);
/* 224 */         jw.member = w.getOsmPolygon();
/* 225 */         joinedWays.add(jw);
/* 226 */         usedWays.add(w);
/*     */         continue;
/*     */       } 
/* 228 */       nodesWithConnectedWays.put(w.getLast(), w);
/* 229 */       nodesWithConnectedWays.put(w.getFirst(), w);
/*     */     } 
/* 234 */     for (Way startWay : ways) {
/* 235 */       if (usedWays.contains(startWay))
/*     */         continue; 
/* 239 */       Node startNode = startWay.getFirst();
/* 240 */       List<Way> collectedWays = new ArrayList<>();
/* 241 */       List<Boolean> collectedWaysReverse = new ArrayList<>();
/* 242 */       Way curWay = startWay;
/* 243 */       Node prevNode = startNode;
/*     */       while (true) {
/* 248 */         boolean curWayReverse = (prevNode == curWay.getLast());
/* 250 */         Node nextNode = curWayReverse ? curWay.getFirst() : curWay.getLast();
/* 253 */         collectedWays.add(curWay);
/* 254 */         collectedWaysReverse.add(Boolean.valueOf(curWayReverse));
/* 256 */         if (nextNode == startNode)
/*     */           break; 
/* 262 */         Collection<Way> adjacentWays = nodesWithConnectedWays.get(nextNode);
/* 264 */         if (adjacentWays.size() != 2)
/* 265 */           return false; 
/* 268 */         Way nextWay = null;
/* 269 */         for (Way way : adjacentWays) {
/* 270 */           if (way != curWay)
/* 271 */             nextWay = way; 
/*     */         } 
/* 276 */         curWay = nextWay;
/* 277 */         prevNode = nextNode;
/*     */       } 
/* 280 */       usedWays.addAll(collectedWays);
/* 281 */       joinedWays.add(new JoinedPolygon(collectedWays, collectedWaysReverse));
/*     */     } 
/* 287 */     for (JoinedPolygon item : joinedWays)
/* 288 */       testExtractIntersections(item); 
/* 292 */     return makeFromPolygons(joinedWays);
/*     */   }
/*     */   
/*     */   private boolean testExtractIntersections(JoinedPolygon item) {
/* 297 */     Geometry jtsShape = item.toJTSShape();
/* 298 */     IsValidOp validOp = new IsValidOp(jtsShape);
/* 299 */     TopologyValidationError err = validOp.getValidationError();
/* 300 */     if (err != null && err.getErrorType() == 6) {
/* 302 */       Geometry convexHull = jtsShape.buffer(0.0D);
/* 303 */       validOp = new IsValidOp(convexHull);
/* 304 */       err = validOp.getValidationError();
/* 306 */       if (err != null) {
/* 307 */         log.error("Found self intersecting way for http://openstreetmap.org/way/" + item.member.shape.getIdentifier() + ", attempting repair");
/* 308 */         return true;
/*     */       } 
/* 312 */       OSMPolygon hull = item.member.shape.clone();
/* 313 */       hull.setShape(GeomUtils.polygonToLinearRing2D(convexHull));
/* 314 */       item.member.shape = hull;
/* 315 */       Way way = makeFromOsmPolygon(item.member);
/* 316 */       item.nodes = way.getNodes();
/* 317 */       return true;
/*     */     } 
/* 319 */     return true;
/*     */   }
/*     */   
/*     */   private boolean makeFromPolygons(List<JoinedPolygon> polygons) {
/* 351 */     this.outerWays = new ArrayList<>(0);
/* 352 */     this.innerWays = new ArrayList<>(0);
/* 353 */     for (JoinedPolygon item : polygons) {
/* 354 */       if (item.member.shape.getRole() == WayInfo.INNER) {
/* 355 */         this.innerWays.add(item);
/*     */         continue;
/*     */       } 
/* 357 */       this.outerWays.add(item);
/*     */     } 
/* 361 */     return true;
/*     */   }
/*     */   
/*     */   private List<PolygonLevel> findOuterWaysRecursive(int level, Collection<JoinedPolygon> boundaryWays, int count) {
/* 373 */     List<PolygonLevel> result = new ArrayList<>();
/* 375 */     count++;
/* 376 */     if (count > 2000)
/* 377 */       return null; 
/* 379 */     for (JoinedPolygon outerWay : boundaryWays) {
/* 381 */       boolean outerGood = true;
/* 382 */       List<JoinedPolygon> innerCandidates = new ArrayList<>();
/* 384 */       for (JoinedPolygon innerWay : boundaryWays) {
/* 385 */         if (innerWay == outerWay)
/*     */           continue; 
/* 389 */         PolygonIntersection intersection = polygonIntersection(outerWay.nodes, innerWay.nodes);
/* 391 */         if (intersection == PolygonIntersection.FIRST_INSIDE_SECOND) {
/* 392 */           outerGood = false;
/*     */           break;
/*     */         } 
/* 394 */         if (intersection == PolygonIntersection.SECOND_INSIDE_FIRST) {
/* 395 */           innerCandidates.add(innerWay);
/*     */           continue;
/*     */         } 
/* 396 */         if (intersection == PolygonIntersection.CROSSING)
/* 400 */           innerWay.getNodes().clear(); 
/*     */       } 
/* 406 */       if (!outerGood)
/*     */         continue; 
/* 411 */       PolygonLevel pol = new PolygonLevel(outerWay, level);
/* 414 */       if (!innerCandidates.isEmpty()) {
/* 415 */         List<PolygonLevel> innerList = findOuterWaysRecursive(level + 1, innerCandidates, count);
/* 416 */         if (innerList == null)
/* 417 */           return null; 
/* 420 */         result.addAll(innerList);
/* 422 */         for (PolygonLevel pl : innerList) {
/* 423 */           if (pl.level == level + 1)
/* 424 */             pol.innerWays.add(pl.outerWay); 
/*     */         } 
/*     */       } 
/* 429 */       result.add(pol);
/*     */     } 
/* 432 */     return result;
/*     */   }
/*     */   
/*     */   public static PolygonIntersection polygonIntersection(List<Node> first, List<Node> second) {
/* 445 */     Area a1 = getArea(first);
/* 446 */     Area a2 = getArea(second);
/* 448 */     Area inter = new Area(a1);
/* 449 */     inter.intersect(a2);
/* 451 */     Rectangle bounds = inter.getBounds();
/* 453 */     if (inter.isEmpty() || bounds.getHeight() * bounds.getWidth() <= 1.0D)
/* 454 */       return PolygonIntersection.OUTSIDE; 
/* 455 */     if (inter.equals(a1))
/* 456 */       return PolygonIntersection.FIRST_INSIDE_SECOND; 
/* 457 */     if (inter.equals(a2))
/* 458 */       return PolygonIntersection.SECOND_INSIDE_FIRST; 
/* 460 */     return PolygonIntersection.CROSSING;
/*     */   }
/*     */   
/*     */   private static Area getArea(List<Node> polygon) {
/* 469 */     Path2D path = new Path2D.Double();
/* 471 */     CoordinateConversion coordinateConversion = new CoordinateConversion();
/* 472 */     boolean begin = true;
/* 473 */     for (Node n : polygon) {
/* 474 */       Point2D point = coordinateConversion.latLon2UTM(n.getLat(), n.getLon());
/* 475 */       if (begin) {
/* 476 */         path.moveTo(point.x(), point.y());
/* 477 */         begin = false;
/*     */         continue;
/*     */       } 
/* 479 */       path.lineTo(point.x(), point.y());
/*     */     } 
/* 482 */     if (!begin)
/* 483 */       path.closePath(); 
/* 486 */     return new Area(path);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\MultipolygonCreate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */