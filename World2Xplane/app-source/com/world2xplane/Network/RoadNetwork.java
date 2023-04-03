/*      */ package com.world2xplane.Network;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Coordinate;
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import com.vividsolutions.jts.geom.Geometry;
/*      */ import com.vividsolutions.jts.geom.GeometryFactory;
/*      */ import com.vividsolutions.jts.geom.LineString;
/*      */ import com.vividsolutions.jts.geom.Point;
/*      */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*      */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*      */ import com.vividsolutions.jts.index.strtree.STRtree;
/*      */ import com.world2xplane.Geom.GeomUtils;
/*      */ import com.world2xplane.OSM.OSMPolygon;
/*      */ import com.world2xplane.Rules.FilterList;
/*      */ import com.world2xplane.Rules.GeneratorStore;
/*      */ import com.world2xplane.Rules.Rule;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import math.geom2d.Box2D;
/*      */ import math.geom2d.Point2D;
/*      */ import math.geom2d.line.LineSegment2D;
/*      */ import math.geom2d.polygon.LinearRing2D;
/*      */ import org.apache.commons.io.FileUtils;
/*      */ import org.geotools.geometry.jts.GeometryClipper;
/*      */ import org.mapdb.DB;
/*      */ import org.mapdb.DBMaker;
/*      */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ 
/*      */ public class RoadNetwork extends NetworkItem {
/*      */   public STRtree roadTree;
/*      */   
/*   60 */   public Set<Road> rendered = new HashSet<>();
/*      */   
/*   63 */   public static List<String> streetTypes = new ArrayList<String>() {
/*      */     
/*      */     };
/*      */   
/*   85 */   public static List<String> railTypes = new ArrayList<String>() {
/*      */     
/*      */     };
/*      */   
/*      */   Map<Long, Junction> junctions;
/*      */   
/*      */   Map<Long, Road> roads;
/*      */   
/*   95 */   long idCounter = 0L;
/*      */   
/*      */   private DB db;
/*      */   
/*   98 */   private static Logger log = LoggerFactory.getLogger(RoadNetwork.class);
/*      */   
/*   99 */   private int commitCount = 0;
/*      */   
/*  100 */   private static int COMMIT_COUNT = 50000000;
/*      */   
/*      */   public int getCount() {
/*  104 */     return this.roads.size();
/*      */   }
/*      */   
/*      */   public RoadNetwork(GeneratorStore generatorStore) {
/*  108 */     super(generatorStore);
/*  109 */     FilterList filterList = new FilterList();
/*  110 */     filterList.setFilterType("key-value");
/*  112 */     StringBuilder filters = new StringBuilder();
/*  113 */     for (String type : streetTypes)
/*  114 */       filters.append("highway=").append(type).append("\n"); 
/*  116 */     for (String type : railTypes)
/*  117 */       filters.append("railway=").append(type).append("\n"); 
/*  120 */     filters.append("man_made=cutline\naeroway=*");
/*  121 */     filterList.setFilter(filters.toString());
/*  122 */     filterList.parseFilter();
/*  123 */     setPassThrough(true);
/*  124 */     this.filterList = filterList;
/*      */   }
/*      */   
/*      */   public NetworkItem initialise() {
/*  130 */     RoadNetwork roadNetwork = new RoadNetwork(this.generatorStore);
/*  132 */     roadNetwork.junctions = new HashMap<>();
/*  133 */     roadNetwork.roads = new HashMap<>();
/*  139 */     roadNetwork.roadTree = new STRtree();
/*  140 */     return roadNetwork;
/*      */   }
/*      */   
/*      */   private void createDb() {
/*  145 */     this.commitCount = 0;
/*  146 */     File dbFile = null;
/*      */     try {
/*  149 */       if ((new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "rdb")).exists())
/*  150 */         FileUtils.deleteDirectory(new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "rdb")); 
/*  152 */       if (!(new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "rdb")).exists())
/*  153 */         FileUtils.forceMkdir(new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "rdb")); 
/*  155 */       dbFile = new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "rdb/osm.db");
/*  157 */     } catch (Exception e) {
/*  158 */       throw new RuntimeException("Could not create tmp file!", e);
/*      */     } 
/*  160 */     this.db = DBMaker.newFileDB(dbFile).closeOnJvmShutdown().deleteFilesAfterClose().cacheSize(524288).mmapFileEnablePartial().transactionDisable().make();
/*      */   }
/*      */   
/*      */   public void finished() {
/*  167 */     this.roads.clear();
/*  168 */     this.roadTree = null;
/*  169 */     this.junctions.clear();
/*      */   }
/*      */   
/*      */   public boolean acceptsShape(Byte tag, OSMPolygon shape, Set<String> possibleRegions, Rule.Delegate delegate, boolean acceptOnly) {
/*  174 */     return false;
/*      */   }
/*      */   
/*      */   public boolean wayRead(HashMap<String, String> tags, Osmformat.Way way) {
/*  181 */     this.commitCount++;
/*  182 */     if (tags != null && (tags.containsKey("highway") || tags.containsKey("railway") || tags.containsKey("aeroway") || tags.containsKey("man_made")) && (streetTypes.contains(tags.get("highway")) || "runway".equals(tags.get("aeroway")) || "cutline".equals(tags.get("man_made")) || "taxiway".equals(tags.get("aeroway")) || railTypes.contains(tags.get("railway")))) {
/*  185 */       long lastRef = 0L;
/*  186 */       Road road = new Road();
/*  187 */       road.roadType = (short)getRoadType(tags);
/*  188 */       road.identifier = way.getId();
/*  190 */       if (way.getRefsList().size() < 2)
/*  191 */         return false; 
/*  194 */       for (Long ref : way.getRefsList()) {
/*  195 */         lastRef += ref.longValue();
/*  196 */         double[] coords = this.dataStore.getNode(lastRef);
/*  198 */         if (coords == null) {
/*  199 */           log.error("Can't find coordinates for node " + lastRef + " request in tile " + this.dsfTile);
/*      */           continue;
/*      */         } 
/*  202 */         if (this.junctions.containsKey(Long.valueOf(lastRef))) {
/*  203 */           Junction junction1 = ((Junction)this.junctions.get(Long.valueOf(lastRef))).upCount();
/*  204 */           junction1.getRoadIds().add(Long.valueOf(road.identifier));
/*  205 */           road.junctions.add(((Junction)this.junctions.get(Long.valueOf(lastRef))).upCount());
/*      */           continue;
/*      */         } 
/*  207 */         Junction junction = new Junction();
/*  208 */         junction.setId(lastRef);
/*  209 */         junction.setLon(coords[0]);
/*  210 */         junction.setLat(coords[1]);
/*  211 */         junction.getRoadIds().add(Long.valueOf(road.identifier));
/*  212 */         this.idCounter++;
/*  213 */         this.junctions.put(Long.valueOf(lastRef), junction);
/*  214 */         road.junctions.add(junction);
/*      */       } 
/*  218 */       if (tags.containsKey("tunnel") && !"no".equals(tags.get("tunnel")))
/*  219 */         road.tunnel = true; 
/*  222 */       if (tags.containsKey("bridge") && !"no".equals(tags.get("bridge")))
/*  223 */         road.bridge = true; 
/*  227 */       if (road.junctions.size() < 2)
/*  228 */         return false; 
/*  230 */       this.roads.put(Long.valueOf(way.getId()), road);
/*  233 */       road.generateLineString(this.dsfTile.getArea());
/*  235 */       if (!road.tunnel)
/*  236 */         this.roadTree.insert(road.lineString.getGeometry().getEnvelopeInternal(), Long.valueOf(road.identifier)); 
/*      */     } 
/*  239 */     commit();
/*  240 */     return true;
/*      */   }
/*      */   
/*      */   public boolean wayRead(HashMap<String, String> tags, LinearRing2D linearRing2D, Long wayId) {
/*  249 */     this.commitCount++;
/*  250 */     if (tags != null && (tags.containsKey("highway") || tags.containsKey("railway") || tags.containsKey("aeroway") || tags.containsKey("man_made")) && (streetTypes.contains(tags.get("highway")) || "runway".equals(tags.get("aeroway")) || "cutline".equals(tags.get("man_made")) || "taxiway".equals(tags.get("aeroway")) || railTypes.contains(tags.get("railway")))) {
/*  253 */       long lastRef = wayId.longValue() + 1L;
/*  254 */       Road road = new Road();
/*  255 */       road.roadType = (short)getRoadType(tags);
/*  256 */       road.identifier = wayId.longValue();
/*  258 */       if (linearRing2D.vertexNumber() < 2)
/*  259 */         return false; 
/*  263 */       for (int x = 0; x < linearRing2D.vertexNumber(); x++) {
/*  265 */         Point2D vertex = linearRing2D.vertex(x);
/*  266 */         lastRef = generateId(vertex);
/*  267 */         if (this.junctions.containsKey(Long.valueOf(lastRef))) {
/*  268 */           Junction junction = ((Junction)this.junctions.get(Long.valueOf(lastRef))).upCount();
/*  269 */           junction.getRoadIds().add(Long.valueOf(road.identifier));
/*  270 */           road.junctions.add(((Junction)this.junctions.get(Long.valueOf(lastRef))).upCount());
/*      */         } else {
/*  272 */           Junction junction = new Junction();
/*  273 */           junction.setId(lastRef);
/*  274 */           junction.setLon(vertex.x());
/*  275 */           junction.setLat(vertex.y());
/*  276 */           junction.getRoadIds().add(Long.valueOf(road.identifier));
/*  277 */           this.idCounter++;
/*  278 */           this.junctions.put(Long.valueOf(lastRef), junction);
/*  279 */           road.junctions.add(junction);
/*      */         } 
/*      */       } 
/*  284 */       if (tags.containsKey("tunnel") && !"no".equals(tags.get("tunnel")))
/*  285 */         road.tunnel = true; 
/*  288 */       if (tags.containsKey("bridge") && !"no".equals(tags.get("bridge")))
/*  289 */         road.bridge = true; 
/*  293 */       if (road.junctions.size() < 2)
/*  294 */         return false; 
/*  296 */       this.roads.put(wayId, road);
/*  299 */       road.generateLineString(this.dsfTile.getArea());
/*  301 */       if (!road.tunnel)
/*  302 */         this.roadTree.insert(road.lineString.getGeometry().getEnvelopeInternal(), Long.valueOf(road.identifier)); 
/*      */     } 
/*  305 */     commit();
/*  306 */     return true;
/*      */   }
/*      */   
/*      */   private long generateId(Point2D vertex) {
/*  312 */     return (vertex.getX() + ":" + vertex.getY()).hashCode();
/*      */   }
/*      */   
/*      */   private void commit() {
/*  316 */     this.commitCount++;
/*  318 */     if (this.db != null && 
/*  319 */       this.commitCount > 8000) {
/*  320 */       this.db.commit();
/*  321 */       this.commitCount = 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int getRoadType(HashMap<String, String> tags) {
/*  327 */     String highway = tags.containsKey("highway") ? ((String)tags.get("highway")).toLowerCase().trim() : "";
/*  328 */     String rail = tags.containsKey("railway") ? ((String)tags.get("railway")).toLowerCase().trim() : "";
/*  329 */     int lanes = 1;
/*      */     try {
/*  331 */       lanes = (new Integer(tags.containsKey("lanes") ? ((String)tags.get("lanes")).toLowerCase().trim() : "1")).intValue();
/*  332 */     } catch (Exception e) {}
/*  335 */     String sidewalk = tags.get("sidewalk");
/*  336 */     boolean oneway = ("yes".equals(tags.get("oneway")) || "1".equals(tags.get("oneway")));
/*  339 */     if (highway.length() > 0) {
/*  341 */       if (highway.equals("residential") || highway.equals("living_street") || highway.equals("unclassified"))
/*  342 */         return roadSubtype(oneway ? Road.RESIDENTIAL_ONE_WAY : Road.RESIDENTIAL, sidewalk); 
/*  345 */       if (highway.equals("trunk")) {
/*  346 */         if (lanes >= 2)
/*  347 */           return roadSubtype(oneway ? Road.PRIMARY_ONE_WAY : Road.PRIMARY, sidewalk); 
/*  349 */         return roadSubtype(oneway ? Road.SECONDARY_ONE_WAY : Road.SECONDARY, sidewalk);
/*      */       } 
/*  352 */       if (highway.equals("primary"))
/*  353 */         return roadSubtype(oneway ? Road.PRIMARY_ONE_WAY : Road.SECONDARY, sidewalk); 
/*  356 */       if (highway.equals("secondary"))
/*  357 */         return roadSubtype(oneway ? Road.SECONDARY_ONE_WAY : Road.SECONDARY, sidewalk); 
/*  360 */       if (highway.equals("tertiary"))
/*  361 */         return roadSubtype(oneway ? Road.TERTIARY_ONE_WAY : Road.TERTIARY, sidewalk); 
/*  364 */       if (highway.equals("motorway")) {
/*  365 */         if (lanes == 2)
/*  366 */           return Road.HIGHWAY_2_LIT; 
/*  368 */         return Road.HIGHWAY_3_LIT;
/*      */       } 
/*  371 */       if (highway.equals("service") || highway.equals("unclassified") || highway.equals("road")) {
/*  372 */         if (!tags.containsKey("service"))
/*  373 */           return oneway ? Road.SERVICE_ONE_WAY : Road.SERVICE; 
/*  375 */         return 0;
/*      */       } 
/*  379 */       if (highway.equals("secondary_link") || highway.equals("tertiary_link") || highway.equals("primary_link") || highway.equals("trunk_link"))
/*  380 */         return Road.SECONDARY_LINK; 
/*  382 */       if (highway.equals("motorway_link"))
/*  383 */         return Road.PRIMARY_LINK; 
/*  386 */       return 0;
/*      */     } 
/*  389 */     if (rail.length() > 0) {
/*  390 */       if (rail.equals("rail"))
/*  391 */         return Road.RAIL_PRIMARY; 
/*  393 */       if (rail.equals("narrow_gauge"))
/*  394 */         return Road.RAIL_TERTIARY; 
/*  396 */       return Road.RAIL_SECONDARY;
/*      */     } 
/*  399 */     return 0;
/*      */   }
/*      */   
/*      */   private int roadSubtype(int roadType, String sidewalk) {
/*  405 */     if ("none".equals(sidewalk) || "no".equals(sidewalk))
/*  406 */       return roadType; 
/*  409 */     if ("left".equals(sidewalk))
/*  410 */       return roadType + 1; 
/*  413 */     if ("right".equals(sidewalk))
/*  414 */       return roadType + 3; 
/*  417 */     if ("both".equals(sidewalk))
/*  418 */       return roadType + 4; 
/*  420 */     return roadType;
/*      */   }
/*      */   
/*      */   public boolean nodeRead(HashMap<String, String> tags, long nodeId, double lon, double lat) {
/*  436 */     return true;
/*      */   }
/*      */   
/*      */   public void createBridges() {
/*  443 */     for (Road road : this.roads.values()) {
/*  444 */       if (road.bridge && road.junctions.size() > 1) {
/*  446 */         double length = GeomUtils.distanceInMeters(new LineSegment2D(((Junction)road.junctions.get(0)).getLon(), ((Junction)road.junctions.get(0)).getLat(), ((Junction)road.junctions.get(road.junctions.size() - 1)).getLon(), ((Junction)road.junctions.get(road.junctions.size() - 1)).getLat()));
/*  449 */         if (length < 10.0D)
/*      */           continue; 
/*  452 */         boolean done = false;
/*  454 */         Junction p1 = road.junctions.get(0);
/*  455 */         Junction p2 = road.junctions.get(road.junctions.size() - 1);
/*  457 */         if (p1.getRoadIds().size() == 2 && p2.getRoadIds().size() == 2) {
/*  460 */           Road r1 = this.roads.get(((Long)p1.getRoadIds().get(0)).equals(Long.valueOf(road.identifier)) ? p1.getRoadIds().get(1) : p1.getRoadIds().get(0));
/*  461 */           Road r2 = this.roads.get(((Long)p2.getRoadIds().get(0)).equals(Long.valueOf(road.identifier)) ? p2.getRoadIds().get(1) : p2.getRoadIds().get(0));
/*  463 */           if (r1 != null && r2 != null) {
/*  465 */             int sp1 = r1.junctions.indexOf(p1);
/*  466 */             int sp2 = r2.junctions.indexOf(p2);
/*  468 */             double r1Length = 0.0D;
/*  469 */             double r2Length = 0.0D;
/*  470 */             Junction p2a = null;
/*  472 */             double angle1 = 0.0D;
/*  473 */             if (sp1 == 0) {
/*  474 */               angle1 = GeomUtils.getBearing(((Junction)r1.junctions.get(0)).getPoint(), ((Junction)r1.junctions.get(1)).getPoint());
/*  475 */               r1Length = GeomUtils.distanceInMeters(new LineSegment2D(((Junction)r1.junctions.get(0)).getLon(), ((Junction)r1.junctions.get(0)).getLat(), ((Junction)r1.junctions.get(1)).getLon(), ((Junction)r1.junctions.get(1)).getLat()));
/*      */             } else {
/*  480 */               angle1 = GeomUtils.getBearing(((Junction)r1.junctions.get(sp1)).getPoint(), ((Junction)r1.junctions.get(sp1 - 1)).getPoint());
/*  481 */               r1Length = GeomUtils.distanceInMeters(new LineSegment2D(((Junction)r1.junctions.get(sp1)).getLon(), ((Junction)r1.junctions.get(sp1)).getLat(), ((Junction)r1.junctions.get(sp1 - 1)).getLon(), ((Junction)r1.junctions.get(sp1 - 1)).getLat()));
/*      */             } 
/*  485 */             double angle2 = 0.0D;
/*  486 */             if (sp2 == 0) {
/*  487 */               angle2 = GeomUtils.getBearing(((Junction)r2.junctions.get(0)).getPoint(), ((Junction)r2.junctions.get(1)).getPoint());
/*  488 */               r2Length = GeomUtils.distanceInMeters(new LineSegment2D(((Junction)r2.junctions.get(0)).getLon(), ((Junction)r2.junctions.get(0)).getLat(), ((Junction)r2.junctions.get(1)).getLon(), ((Junction)r2.junctions.get(1)).getLat()));
/*      */             } else {
/*  492 */               angle2 = GeomUtils.getBearing(((Junction)r2.junctions.get(sp2)).getPoint(), ((Junction)r2.junctions.get(sp2 - 1)).getPoint());
/*  493 */               r2Length = GeomUtils.distanceInMeters(new LineSegment2D(((Junction)r2.junctions.get(sp2)).getLon(), ((Junction)r2.junctions.get(sp2)).getLat(), ((Junction)r2.junctions.get(sp2 - 1)).getLon(), ((Junction)r2.junctions.get(sp2 - 1)).getLat()));
/*      */             } 
/*  498 */             if (length < 100.0D) {
/*  500 */               double shortage1 = (100.0D - length) / 2.0D;
/*  501 */               double shortage2 = (100.0D - length) / 2.0D;
/*  505 */               if (r1Length < shortage1)
/*  506 */                 shortage1 = r1Length; 
/*  508 */               if (r2Length < shortage2)
/*  509 */                 shortage2 = r2Length; 
/*  513 */               Point2D np1 = GeomUtils.translateFastLatLon(p1.getPoint(), angle1, shortage1);
/*  514 */               Point2D np2 = GeomUtils.translateFastLatLon(p2.getPoint(), angle2, shortage2);
/*  516 */               p1.setLon(np1.x());
/*  517 */               p1.setLat(np1.y());
/*  519 */               p2.setLon(np2.x());
/*  520 */               p2.setLat(np2.y());
/*      */             } 
/*  524 */             if (road.junctions.size() < 4)
/*  525 */               insertBridgePoint(road); 
/*  529 */             setLineString(r1);
/*  530 */             setLineString(r2);
/*  531 */             setLineString(road);
/*  533 */             commit();
/*  536 */             done = true;
/*      */           } 
/*      */         } 
/*  541 */         if (!done);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void insertBridgePoint(Road road) {
/*  550 */     Junction first = road.junctions.get(0);
/*  551 */     Junction last = road.junctions.get(road.junctions.size() - 1);
/*  554 */     Junction p1 = road.junctions.get(1);
/*  555 */     double angle = GeomUtils.getBearing(first.getLat(), first.getLon(), p1.getLat(), p1.getLon());
/*  556 */     this.idCounter++;
/*  557 */     Junction n1 = new Junction();
/*  558 */     Point2D n1p = GeomUtils.translateFastLatLon(first.getPoint(), angle, 10.0D);
/*  559 */     n1.setLon(n1p.x());
/*  560 */     n1.setLat(n1p.y());
/*  561 */     n1.setId(-this.idCounter);
/*  562 */     road.junctions.add(1, n1);
/*  565 */     Junction p2 = road.junctions.get(road.junctions.size() - 2);
/*  566 */     angle = GeomUtils.getBearing(last.getLat(), last.getLon(), p2.getLat(), p2.getLon());
/*  567 */     this.idCounter++;
/*  568 */     Junction n2 = new Junction();
/*  569 */     Point2D n2p = GeomUtils.translateFastLatLon(last.getPoint(), angle, 10.0D);
/*  570 */     n2.setLon(n2p.x());
/*  571 */     n2.setLat(n2p.y());
/*  572 */     n2.setId(-this.idCounter);
/*  573 */     road.junctions.add(road.junctions.size() - 1, n2);
/*      */   }
/*      */   
/*      */   private void setLineString(Road road) {
/*  577 */     GeometryFactory geometryFactory = new GeometryFactory();
/*  579 */     Coordinate[] b_coordinates = new Coordinate[road.junctions.size()];
/*  580 */     for (int x = 0; x < road.junctions.size(); x++) {
/*  581 */       Coordinate coord = new Coordinate(((Junction)road.junctions.get(x)).getLon(), ((Junction)road.junctions.get(x)).getLat());
/*  583 */       b_coordinates[x] = coord;
/*      */     } 
/*  585 */     LineString line = geometryFactory.createLineString(b_coordinates);
/*  586 */     road.lineString = PreparedGeometryFactory.prepare((Geometry)line);
/*      */   }
/*      */   
/*      */   public long writeToDSF(FileOutputStream fileOutputStream, long junctionId, NetworkDelegate delegate) throws IOException {
/*  593 */     log.info("Adding Road Network");
/*  596 */     GeometryFactory geometryFactory = new GeometryFactory();
/*  597 */     Box2D box = this.dsfTile.getArea();
/*  601 */     for (Road road : this.roads.values()) {
/*  602 */       if (road.bridge)
/*  603 */         scanForBridges(road); 
/*      */     } 
/*  608 */     createBridges();
/*  612 */     for (Road road : this.roads.values()) {
/*  613 */       if (road.tunnel)
/*      */         continue; 
/*      */       try {
/*  619 */         Coordinate[] coordinates = new Coordinate[road.junctions.size()];
/*  620 */         Map<Coordinate, Junction> junctionIds = new HashMap<>();
/*  621 */         for (int x = 0; x < road.junctions.size(); x++) {
/*  622 */           Coordinate coord = new Coordinate(((Junction)road.junctions.get(x)).getLon(), ((Junction)road.junctions.get(x)).getLat());
/*  624 */           coordinates[x] = coord;
/*  625 */           junctionIds.put(coord, road.junctions.get(x));
/*      */         } 
/*  628 */         LineString line = geometryFactory.createLineString(coordinates);
/*  629 */         LinearRing2D boundary = new LinearRing2D();
/*  630 */         boundary.addVertex(new Point2D(box.getMinX(), box.getMinY()));
/*  631 */         boundary.addVertex(new Point2D(box.getMaxX(), box.getMinY()));
/*  632 */         boundary.addVertex(new Point2D(box.getMaxX(), box.getMaxY()));
/*  633 */         boundary.addVertex(new Point2D(box.getMinX(), box.getMaxY()));
/*  634 */         boundary.addVertex(new Point2D(box.getMinX(), box.getMinY()));
/*  635 */         Geometry clipper = GeomUtils.linearRingToJTSPolygon(boundary);
/*  637 */         GeometryClipper geometryClipper = new GeometryClipper(clipper.getEnvelopeInternal());
/*  638 */         Geometry output = geometryClipper.clip((Geometry)line, true);
/*  640 */         if (output == null || output.getNumPoints() < 2) {
/*  641 */           road.junctions.clear();
/*  642 */           road.junctionIds.clear();
/*      */           continue;
/*      */         } 
/*  646 */         road.junctions.clear();
/*  647 */         if (output instanceof LineString) {
/*  648 */           for (Coordinate item : output.getCoordinates()) {
/*  649 */             if (junctionIds.containsKey(item)) {
/*  650 */               Junction junc = junctionIds.get(item);
/*  652 */               road.junctions.add(junctionIds.get(item));
/*      */             } else {
/*  654 */               this.idCounter++;
/*  655 */               Junction junction = new Junction();
/*  656 */               junction.setLon(item.x);
/*  657 */               junction.setLat(item.y);
/*  658 */               junction.setId(-9999L - this.idCounter);
/*  660 */               junctionIds.put(item, junction);
/*  661 */               road.junctions.add(junction);
/*      */             } 
/*      */           } 
/*  665 */         } else if (output instanceof com.vividsolutions.jts.geom.MultiLineString) {
/*  667 */           for (int i = 0; i < output.getNumGeometries(); i++) {
/*  669 */             LineString lineString = (LineString)output.getGeometryN(i);
/*  670 */             for (Coordinate item : lineString.getCoordinates()) {
/*  671 */               if (junctionIds.containsKey(item)) {
/*  672 */                 Junction junc = junctionIds.get(item);
/*  674 */                 road.junctions.add(junctionIds.get(item));
/*      */               } else {
/*  676 */                 this.idCounter++;
/*  677 */                 Junction junction = new Junction();
/*  678 */                 junction.setLon(item.x);
/*  679 */                 junction.setLat(item.y);
/*  680 */                 junction.setId(-9999L - this.idCounter);
/*  683 */                 junctionIds.put(item, junction);
/*  684 */                 road.junctions.add(junction);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*  691 */         junctionIds.clear();
/*  694 */       } catch (Exception e) {
/*  695 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/*  703 */     for (Road road : this.roads.values()) {
/*  704 */       if (road.tunnel || road.roadType == 0 || road.junctions.size() < 2)
/*      */         continue; 
/*  707 */       for (Junction junction : road.junctions) {
/*  708 */         if (junction.getJunctionId().longValue() == 0L) {
/*  709 */           junction.setJunctionId(Long.valueOf(junctionId));
/*  710 */           junctionId++;
/*      */         } 
/*      */       } 
/*      */     } 
/*  716 */     for (Road road : this.roads.values())
/*  717 */       plotRoad(fileOutputStream, delegate, road); 
/*  720 */     log.info("Added Road Network");
/*  721 */     return junctionId;
/*      */   }
/*      */   
/*      */   private void plotRoad(FileOutputStream fileOutputStream, NetworkDelegate delegate, Road road) throws IOException {
/*  727 */     if (road.roadType == 0 || road.tunnel || road.junctions.size() < 2 || road.rendered)
/*      */       return; 
/*  731 */     if (this.rendered.contains(road))
/*      */       return; 
/*  734 */     this.rendered.add(road);
/*  736 */     road.rendered = true;
/*  738 */     float height = 0.0F;
/*  739 */     if (road.bridge)
/*  740 */       height = 1.0F; 
/*  743 */     int junctionType = 0;
/*  746 */     Iterator<Junction> it = road.junctions.iterator();
/*  747 */     while (it.hasNext()) {
/*  748 */       Junction item = it.next();
/*  749 */       if (!this.dsfTile.containsPoint(new Point2D(item.getLon(), item.getLat())))
/*      */         return; 
/*      */     } 
/*  755 */     Junction firstJunction = road.junctions.get(0);
/*  756 */     Junction lastJunction = road.junctions.get(road.junctions.size() - 1);
/*  759 */     int roadType = road.roadType;
/*      */     try {
/*  761 */       roadType = updateRoadType(delegate.getAreaType(new Point2D(firstJunction.getLon(), firstJunction.getLat())), roadType);
/*  762 */     } catch (Exception e) {
/*  763 */       e.printStackTrace();
/*      */       return;
/*      */     } 
/*  767 */     boolean newRoadType = false;
/*  768 */     int previousRoadType = roadType;
/*  770 */     String begin_segment = String.format("BEGIN_SEGMENT %d %d %d %s %s %s\n", new Object[] { Integer.valueOf(junctionType), Integer.valueOf(roadType), firstJunction.getJunctionId(), this.numberFormat.format(firstJunction.getLon()), this.numberFormat.format(firstJunction.getLat()), Float.valueOf(0.0F) });
/*  773 */     fileOutputStream.write(begin_segment.getBytes());
/*  775 */     for (int x = 1; x < road.junctions.size() - 1; x++) {
/*  776 */       Junction junction = road.junctions.get(x);
/*  777 */       roadType = updateRoadType(delegate.getAreaType(new Point2D(junction.getLon(), junction.getLat())), roadType);
/*  778 */       if (previousRoadType != roadType)
/*  779 */         newRoadType = true; 
/*  781 */       previousRoadType = roadType;
/*  782 */       if (junction.getJunctionCount() > 1 || newRoadType || (road.bridge && x == 1) || (road.bridge && x == road.junctions.size() - 2)) {
/*  783 */         String segment = String.format("END_SEGMENT %d %s %s %s\n", new Object[] { junction.getJunctionId(), this.numberFormat.format(junction.getLon()), this.numberFormat.format(junction.getLat()), this.numberFormat.format(height) });
/*  786 */         fileOutputStream.write(segment.getBytes());
/*  787 */         segment = String.format("BEGIN_SEGMENT %d %d %d %s %s %s\n", new Object[] { Integer.valueOf(junctionType), Integer.valueOf(roadType), junction.getJunctionId(), this.numberFormat.format(junction.getLon()), this.numberFormat.format(junction.getLat()), this.numberFormat.format(height) });
/*  790 */         fileOutputStream.write(segment.getBytes());
/*      */       } else {
/*  792 */         String segment = String.format("SHAPE_POINT %s %s %s\n", new Object[] { this.numberFormat.format(junction.getLon()), this.numberFormat.format(junction.getLat()), Integer.valueOf(0) });
/*  795 */         fileOutputStream.write(segment.getBytes());
/*      */       } 
/*      */     } 
/*  799 */     String end_segment = String.format("END_SEGMENT %d %s %s %s\n", new Object[] { lastJunction.getJunctionId(), this.numberFormat.format(lastJunction.getLon()), this.numberFormat.format(lastJunction.getLat()), Integer.valueOf(0) });
/*  802 */     fileOutputStream.write(end_segment.getBytes());
/*  803 */     fileOutputStream.write("\n".getBytes());
/*  804 */     road.rendered = true;
/*      */   }
/*      */   
/*      */   public List<Road> getLinks(Road road) {
/*  816 */     Junction p2 = road.junctions.get(road.junctions.size() - 1);
/*  818 */     List<Road> linkedRoads = new ArrayList<>();
/*  819 */     if (p2.getRoadIds() != null && p2.getRoadIds().size() > 1)
/*  820 */       for (Long item : p2.getRoadIds()) {
/*  821 */         if (item.longValue() != road.identifier) {
/*  823 */           Road joinedRoad = this.roads.get(item);
/*  824 */           if (joinedRoad != null && !joinedRoad.rendered) {
/*  826 */             if (joinedRoad.junctions.indexOf(p2) == joinedRoad.junctions.size() - 1) {
/*  827 */               joinedRoad.reverse();
/*  828 */               linkedRoads.add(joinedRoad);
/*  829 */               return linkedRoads;
/*      */             } 
/*  831 */             if (joinedRoad.junctions.indexOf(p2) == 0) {
/*  832 */               linkedRoads.add(joinedRoad);
/*  833 */               return linkedRoads;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }  
/*  841 */     return new ArrayList<>();
/*      */   }
/*      */   
/*      */   private void scanForBridges(Road bridge) {
/*      */     Geometry line;
/*  848 */     GeometryFactory geometryFactory = new GeometryFactory();
/*  850 */     if (bridge.lineString == null) {
/*  851 */       Coordinate[] arrayOfCoordinate = new Coordinate[bridge.junctions.size()];
/*  852 */       for (int x = 0; x < bridge.junctions.size(); x++) {
/*  853 */         Coordinate coord = new Coordinate(((Junction)bridge.junctions.get(x)).getLon(), ((Junction)bridge.junctions.get(x)).getLat());
/*  855 */         arrayOfCoordinate[x] = coord;
/*      */       } 
/*  857 */       LineString lineString = geometryFactory.createLineString(arrayOfCoordinate);
/*  858 */       bridge.lineString = PreparedGeometryFactory.prepare((Geometry)lineString);
/*  859 */       commit();
/*      */     } else {
/*  862 */       line = bridge.lineString.getGeometry();
/*      */     } 
/*  865 */     Coordinate[] b_coordinates = line.getCoordinates();
/*  867 */     List<Long> roadIds = this.roadTree.query(line.getEnvelopeInternal());
/*  868 */     if (roadIds != null && roadIds.size() > 0)
/*  870 */       for (Long roadId : roadIds) {
/*      */         LineString lineString;
/*  871 */         Road road = this.roads.get(roadId);
/*  872 */         if (road == null || road == bridge || road.tunnel || road.junctions.size() < 2)
/*      */           continue; 
/*  877 */         if (road.lineString != null) {
/*  878 */           Geometry line2 = road.lineString.getGeometry();
/*      */         } else {
/*  880 */           Coordinate[] arrayOfCoordinate = new Coordinate[road.junctions.size()];
/*  881 */           for (int x = 0; x < road.junctions.size(); x++) {
/*  882 */             Coordinate coord = new Coordinate(((Junction)road.junctions.get(x)).getLon(), ((Junction)road.junctions.get(x)).getLat());
/*  884 */             arrayOfCoordinate[x] = coord;
/*      */           } 
/*  887 */           lineString = geometryFactory.createLineString(arrayOfCoordinate);
/*  888 */           commit();
/*      */         } 
/*  891 */         Coordinate[] coordinates = lineString.getCoordinates();
/*  892 */         if (line.crosses((Geometry)lineString))
/*  898 */           for (int x = 0; x < (line.getCoordinates()).length - 1; x++) {
/*  900 */             LineString section1 = geometryFactory.createLineString(new Coordinate[] { new Coordinate((b_coordinates[x]).x, (b_coordinates[x]).y), new Coordinate((b_coordinates[x + 1]).x, (b_coordinates[x + 1]).y) });
/*  902 */             for (int y = 0; y < (lineString.getCoordinates()).length - 1; y++) {
/*  903 */               LineString section2 = geometryFactory.createLineString(new Coordinate[] { new Coordinate((coordinates[y]).x, (coordinates[y]).y), new Coordinate((coordinates[y + 1]).x, (coordinates[y + 1]).y) });
/*  906 */               if (section1.crosses((Geometry)section2))
/*  907 */                 splitWays(section1, section2, bridge, road); 
/*      */             } 
/*      */           }  
/*      */       }  
/*      */   }
/*      */   
/*      */   private void splitWays(LineString section1, LineString section2, Road bridge, Road road) {
/*  927 */     Point intersection = (Point)section1.intersection((Geometry)section2);
/*  928 */     if (intersection != null) {
/*  931 */       int bindex = 0;
/*  932 */       boolean bfound = false;
/*  933 */       int findex = 0;
/*  934 */       boolean ffound = false;
/*      */       int x;
/*  936 */       for (x = 0; x < bridge.junctions.size() - 1; x++) {
/*  937 */         Coordinate p1 = new Coordinate(((Junction)bridge.junctions.get(x)).getLon(), ((Junction)bridge.junctions.get(x)).getLat());
/*  938 */         Coordinate p2 = new Coordinate(((Junction)bridge.junctions.get(x + 1)).getLon(), ((Junction)bridge.junctions.get(x + 1)).getLat());
/*  939 */         if (section1.getCoordinates()[0].equals(p1) && section1.getCoordinates()[1].equals(p2)) {
/*  940 */           bindex = x;
/*  941 */           bfound = true;
/*      */           break;
/*      */         } 
/*      */       } 
/*  946 */       for (x = 0; x < road.junctions.size() - 1; x++) {
/*  947 */         Coordinate p1 = new Coordinate(((Junction)road.junctions.get(x)).getLon(), ((Junction)road.junctions.get(x)).getLat());
/*  948 */         Coordinate p2 = new Coordinate(((Junction)road.junctions.get(x + 1)).getLon(), ((Junction)road.junctions.get(x + 1)).getLat());
/*  949 */         if (section2.getCoordinates()[0].equals(p1) && section2.getCoordinates()[1].equals(p2)) {
/*  950 */           findex = x;
/*  951 */           ffound = true;
/*      */           break;
/*      */         } 
/*      */       } 
/*  956 */       if (ffound && bfound) {
/*  958 */         this.idCounter++;
/*  959 */         Junction junction = new Junction();
/*  960 */         junction.setId(-9999L - this.idCounter);
/*  961 */         junction.setLon(intersection.getX());
/*  962 */         junction.setLat(intersection.getY());
/*  963 */         junction.upCount();
/*  964 */         junction.upCount();
/*  965 */         bridge.junctions.add(bindex + 1, junction);
/*  966 */         road.junctions.add(findex + 1, junction);
/*      */       } 
/*  969 */       bridge.lineString = null;
/*  970 */       road.lineString = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int updateRoadType(String areaType, int roadType) {
/*  980 */     boolean isResidentialZone = "residential".equals(areaType);
/*  981 */     boolean isCommercialZone = "commercial".equals(areaType);
/*  982 */     boolean isIndustrialZone = "industrial".equals(areaType);
/*  985 */     if (roadType == Road.RESIDENTIAL && isResidentialZone) {
/*  986 */       int random = getRandomNumber(0, 4);
/*  988 */       if (random == 0)
/*  989 */         return roadType + 4; 
/*  991 */       if (random == 1)
/*  992 */         return roadType + 5; 
/*  994 */       if (random == 2)
/*  995 */         return roadType + 7; 
/*  997 */       if (random == 3)
/*  998 */         return roadType + 8; 
/*      */     } 
/* 1010 */     if (roadType == Road.PRIMARY && isResidentialZone) {
/* 1011 */       int random = getRandomNumber(0, 4);
/* 1013 */       if (random == 0)
/* 1014 */         return roadType + 4; 
/* 1016 */       if (random == 1)
/* 1017 */         return roadType + 5; 
/* 1019 */       if (random == 2)
/* 1020 */         return roadType + 7; 
/* 1022 */       if (random == 3)
/* 1023 */         return roadType + 8; 
/*      */     } 
/* 1027 */     if (roadType == Road.SECONDARY && isResidentialZone) {
/* 1028 */       int random = getRandomNumber(0, 4);
/* 1030 */       if (random == 0)
/* 1031 */         return roadType + 4; 
/* 1033 */       if (random == 1)
/* 1034 */         return roadType + 5; 
/* 1036 */       if (random == 2)
/* 1037 */         return roadType + 7; 
/* 1039 */       if (random == 3)
/* 1040 */         return roadType + 8; 
/*      */     } 
/* 1044 */     if (roadType == Road.TERTIARY && isResidentialZone) {
/* 1045 */       int random = getRandomNumber(0, 4);
/* 1046 */       if (random == 0)
/* 1047 */         return roadType + 4; 
/* 1049 */       if (random == 1)
/* 1050 */         return roadType + 5; 
/* 1052 */       if (random == 2)
/* 1053 */         return roadType + 7; 
/* 1055 */       if (random == 3)
/* 1056 */         return roadType + 8; 
/*      */     } 
/* 1060 */     return roadType;
/*      */   }
/*      */   
/*      */   public boolean collides(Envelope searchEnvelope) {
/* 1065 */     return collides(searchEnvelope, (Long)null);
/*      */   }
/*      */   
/*      */   public boolean collides(Envelope searchEnvelope, Long identifier) {
/* 1078 */     GeometryFactory geometryFactory = new GeometryFactory();
/* 1079 */     Geometry shape = geometryFactory.toGeometry(searchEnvelope);
/* 1081 */     List<Long> roadIds = this.roadTree.query(searchEnvelope);
/* 1082 */     if (roadIds != null && roadIds.size() > 0)
/* 1084 */       for (Long roadId : roadIds) {
/* 1085 */         if (roadId.equals(identifier))
/*      */           continue; 
/* 1088 */         Road road = this.roads.get(roadId);
/* 1089 */         if (road == null || road.tunnel || road.junctions.size() < 2)
/*      */           continue; 
/* 1092 */         PreparedGeometry line = road.lineString;
/* 1094 */         if (line != null && shape != null && line.intersects(shape))
/* 1095 */           return true; 
/*      */       }  
/* 1101 */     return false;
/*      */   }
/*      */   
/*      */   public List<Road> getCollidingRoads(Geometry geometry, RoadCollisionCheck roadCollisionCheck) {
/* 1110 */     List<Road> collidingRoads = new ArrayList<>();
/* 1113 */     List<Long> roadIds = this.roadTree.query(geometry.getEnvelopeInternal());
/* 1114 */     if (roadIds != null && roadIds.size() > 0)
/* 1116 */       for (Long roadId : roadIds) {
/* 1117 */         Road road = this.roads.get(roadId);
/* 1118 */         if (road == null || road.tunnel || road.junctions.size() < 2)
/*      */           continue; 
/* 1121 */         if (roadCollisionCheck != null && 
/* 1122 */           !roadCollisionCheck.ignoreRoad(road))
/*      */           continue; 
/* 1126 */         PreparedGeometry line = road.lineString;
/* 1127 */         if (line == null)
/* 1128 */           line = road.generateLineString(this.dsfTile.getArea()); 
/* 1130 */         if (line.intersects(geometry))
/* 1131 */           collidingRoads.add(road); 
/*      */       }  
/* 1137 */     return collidingRoads;
/*      */   }
/*      */   
/*      */   public Double getAngleFromNearestRoad(Geometry geometry) {
/* 1141 */     List<Road> collidingRoads = getCollidingRoads(geometry, (RoadCollisionCheck)null);
/* 1143 */     if (collidingRoads.size() > 0) {
/* 1144 */       Road firstRoad = collidingRoads.get(0);
/* 1146 */       LineString line = (LineString)firstRoad.getLineString().getGeometry().clone();
/* 1147 */       Geometry crossingArea = line.intersection(geometry);
/* 1148 */       if (crossingArea != null && crossingArea instanceof LineString) {
/* 1149 */         LineString ls = (LineString)crossingArea;
/* 1150 */         if (ls.getNumPoints() > 1) {
/* 1151 */           Coordinate p1 = ls.getCoordinateN(0);
/* 1152 */           Coordinate p2 = ls.getCoordinateN(1);
/* 1153 */           return Double.valueOf(GeomUtils.getBearing(p1.y, p1.x, p2.y, p2.x));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1159 */     return null;
/*      */   }
/*      */   
/*      */   public boolean isPassThrough() {
/* 1164 */     return true;
/*      */   }
/*      */   
/*      */   public Map<Long, Junction> getJunctions() {
/* 1169 */     return this.junctions;
/*      */   }
/*      */   
/*      */   public Map<Long, Road> getRoads() {
/* 1173 */     return this.roads;
/*      */   }
/*      */   
/*      */   public Set<Road> getRendered() {
/* 1177 */     return this.rendered;
/*      */   }
/*      */   
/*      */   public static interface RoadCollisionCheck {
/*      */     boolean ignoreRoad(Road param1Road);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Network\RoadNetwork.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */