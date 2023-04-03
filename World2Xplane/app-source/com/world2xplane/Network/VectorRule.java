/*     */ package com.world2xplane.Network;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.operation.polygonize.Polygonizer;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.Rules.FilterList;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class VectorRule extends NetworkItem {
/*     */   private int defNumber;
/*     */   
/*     */   private boolean nodesAsPoints = false;
/*     */   
/*     */   private String vectorFile;
/*     */   
/*     */   private FilterList nodeFilterList;
/*     */   
/*  84 */   HashMap<Long, Junction> junctions = new HashMap<>();
/*     */   
/*  85 */   HashMap<Long, VectorWay> vectorWays = new HashMap<>();
/*     */   
/*     */   public static class VectorWay {
/*  88 */     List<Junction> junctions = new ArrayList<>();
/*     */   }
/*     */   
/*  91 */   private static Logger log = LoggerFactory.getLogger(VectorRule.class);
/*     */   
/*     */   public VectorRule(GeneratorStore generatorStore) {
/*  95 */     super(generatorStore);
/*  96 */     setPassThrough(true);
/*     */   }
/*     */   
/*     */   public NetworkItem initialise() {
/* 101 */     VectorRule vectorRule = new VectorRule(this.generatorStore);
/* 102 */     vectorRule.setRegions(getRegions());
/* 103 */     vectorRule.setNodesAsPoints(this.nodesAsPoints);
/* 104 */     vectorRule.setDefNumber(this.defNumber);
/* 105 */     vectorRule.setVectorFile(this.vectorFile);
/* 106 */     vectorRule.setCircular(this.circular);
/* 107 */     vectorRule.setNetworkNumber(getNetworkNumber());
/* 108 */     vectorRule.setFilter(getFilter());
/* 109 */     vectorRule.setFilterList(getFilterList());
/* 110 */     vectorRule.setNotRegions(getNotRegions());
/* 111 */     vectorRule.setMaxSeed(this.maxSeed);
/* 112 */     vectorRule.setMinSeed(this.minSeed);
/* 113 */     vectorRule.setPassThrough(isPassThrough());
/* 114 */     vectorRule.setRandomSeedIdentifier(this.randomSeedIdentifier);
/* 115 */     vectorRule.junctions = new HashMap<>();
/* 116 */     vectorRule.vectorWays = new HashMap<>();
/* 117 */     vectorRule.setNodeFilterList(getNodeFilterList());
/* 118 */     return vectorRule;
/*     */   }
/*     */   
/*     */   public void finished() {
/* 123 */     this.junctions.clear();
/* 124 */     this.vectorWays.clear();
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 128 */     return this.vectorWays.size();
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(Byte tag, OSMPolygon shape, Set<String> possibleRegions, Rule.Delegate delegate, boolean acceptOnly) {
/* 134 */     return false;
/*     */   }
/*     */   
/*     */   public boolean wayRead(HashMap<String, String> tags, Osmformat.Way way) {
/* 141 */     if (this.filterList.acceptsTag(tags) != null) {
/* 142 */       long lastRef = 0L;
/* 143 */       VectorWay aerialWay = new VectorWay();
/* 145 */       int count = 0;
/* 146 */       for (Long ref : way.getRefsList()) {
/* 147 */         lastRef += ref.longValue();
/* 150 */         if (this.nodesAsPoints) {
/* 151 */           if (this.junctions.containsKey(Long.valueOf(lastRef))) {
/* 152 */             aerialWay.junctions.add(this.junctions.get(Long.valueOf(lastRef)));
/* 153 */             count++;
/*     */             continue;
/*     */           } 
/* 154 */           if (count == 0 || count == way.getRefsList().size() - 1) {
/* 155 */             double[] arrayOfDouble = this.dataStore.getNode(lastRef);
/* 157 */             if (arrayOfDouble == null) {
/* 158 */               log.error("Can't find coordinates for node " + lastRef + " request in tile " + this.dsfTile);
/*     */               continue;
/*     */             } 
/* 161 */             Junction junction1 = new Junction();
/* 162 */             junction1.setId(lastRef);
/* 163 */             junction1.setLon(arrayOfDouble[0]);
/* 164 */             junction1.setLat(arrayOfDouble[1]);
/* 165 */             junction1.setJunctionCount((short)1);
/* 166 */             this.junctions.put(Long.valueOf(lastRef), junction1);
/* 167 */             aerialWay.junctions.add(this.junctions.get(Long.valueOf(lastRef)));
/* 168 */             count++;
/*     */           } 
/*     */           continue;
/*     */         } 
/* 171 */         if (this.junctions.containsKey(Long.valueOf(lastRef))) {
/* 172 */           aerialWay.junctions.add(this.junctions.get(Long.valueOf(lastRef)));
/* 173 */           count++;
/*     */           continue;
/*     */         } 
/* 175 */         double[] coords = this.dataStore.getNode(lastRef);
/* 177 */         if (coords == null) {
/* 178 */           log.error("Can't find coordinates for node " + lastRef + " request in tile " + this.dsfTile);
/*     */           continue;
/*     */         } 
/* 181 */         Junction junction = new Junction();
/* 182 */         junction.setId(lastRef);
/* 183 */         junction.setLon(coords[0]);
/* 184 */         junction.setLat(coords[1]);
/* 185 */         junction.setJunctionCount((short)1);
/* 186 */         this.junctions.put(Long.valueOf(lastRef), junction);
/* 187 */         aerialWay.junctions.add(this.junctions.get(Long.valueOf(lastRef)));
/* 188 */         count++;
/*     */       } 
/* 195 */       this.vectorWays.put(Long.valueOf(way.getId()), aerialWay);
/* 196 */       return true;
/*     */     } 
/* 198 */     return false;
/*     */   }
/*     */   
/*     */   public boolean nodeRead(HashMap<String, String> tags, long nodeId, double lon, double lat) {
/* 207 */     if (this.dsfTile.containsPoint(new Point2D(lon, lat)) && this.nodeFilterList != null && this.nodeFilterList.acceptsTag(tags) != null) {
/* 209 */       if (this.junctions.containsKey(Long.valueOf(nodeId))) {
/* 210 */         ((Junction)this.junctions.get(Long.valueOf(nodeId))).setJunctionCount((short)1);
/*     */       } else {
/* 212 */         Junction junction = new Junction();
/* 213 */         junction.setId(nodeId);
/* 214 */         junction.setLon(lon);
/* 215 */         junction.setLat(lat);
/* 216 */         junction.setJunctionCount((short)1);
/* 218 */         this.junctions.put(Long.valueOf(nodeId), junction);
/*     */       } 
/* 220 */       return true;
/*     */     } 
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   public long writeToDSF(FileOutputStream fileOutputStream, long junctionId, NetworkDelegate delegate) throws IOException {
/* 231 */     GeometryFactory geometryFactory = new GeometryFactory();
/* 232 */     Box2D box = this.dsfTile.getArea();
/* 234 */     for (VectorWay vectorWay : this.vectorWays.values()) {
/*     */       try {
/* 237 */         if (vectorWay.junctions.size() < 2)
/*     */           continue; 
/* 240 */         List<Coordinate> coordinates = new ArrayList<>();
/* 241 */         for (int x = 0; x < vectorWay.junctions.size(); x++) {
/* 242 */           Junction junction = vectorWay.junctions.get(x);
/* 243 */           if (junction.getJunctionCount() != 0) {
/* 246 */             Coordinate coord = new Coordinate(junction.getLon(), junction.getLat());
/* 248 */             coordinates.add(coord);
/*     */           } 
/*     */         } 
/* 250 */         if (coordinates.size() < 2)
/*     */           continue; 
/* 254 */         LineString line = geometryFactory.createLineString(coordinates.<Coordinate>toArray(new Coordinate[coordinates.size()]));
/* 255 */         Polygonizer polygonizer = new Polygonizer();
/* 256 */         polygonizer.add((Geometry)line);
/* 257 */         LinearRing2D boundary = new LinearRing2D();
/* 258 */         boundary.addVertex(new Point2D(box.getMinX(), box.getMinY()));
/* 259 */         boundary.addVertex(new Point2D(box.getMaxX(), box.getMinY()));
/* 260 */         boundary.addVertex(new Point2D(box.getMaxX(), box.getMaxY()));
/* 261 */         boundary.addVertex(new Point2D(box.getMinX(), box.getMaxY()));
/* 262 */         boundary.addVertex(new Point2D(box.getMinX(), box.getMinY()));
/* 263 */         Geometry clipper = GeomUtils.linearRingToJTSPolygon(boundary);
/* 265 */         Geometry output = line.intersection(clipper);
/* 267 */         if (output instanceof LineString) {
/* 269 */           if (output.getNumPoints() < 2)
/*     */             continue; 
/* 274 */           StringBuilder item = new StringBuilder();
/* 276 */           if (output.getNumPoints() >= 2) {
/* 277 */             Coordinate p1 = ((LineString)output).getCoordinateN(0);
/* 278 */             Coordinate p2 = ((LineString)output).getCoordinateN(output.getNumPoints() - 1);
/* 280 */             String begin_segment = String.format("BEGIN_SEGMENT %d %d %d %s %s %d\n", new Object[] { getNetworkNumber(), Integer.valueOf(this.defNumber), Long.valueOf(junctionId), this.numberFormat.format(p1.x), this.numberFormat.format(p1.y), Integer.valueOf(0) });
/* 283 */             junctionId++;
/* 284 */             item.append(begin_segment);
/* 285 */             for (int i = 1; i < output.getNumPoints() - 1; i++) {
/* 286 */               Coordinate p = ((LineString)output).getCoordinateN(i);
/* 287 */               String segment = String.format("SHAPE_POINT %s %s 0\n", new Object[] { this.numberFormat.format(p.x), this.numberFormat.format(p.y) });
/* 290 */               item.append(segment);
/*     */             } 
/* 294 */             String end_segment = String.format("END_SEGMENT %d %s %s %d\n", new Object[] { Long.valueOf(junctionId), this.numberFormat.format(p2.x), this.numberFormat.format(p2.y), Integer.valueOf(0) });
/* 297 */             item.append(end_segment);
/* 298 */             item.append("\n");
/* 299 */             junctionId++;
/* 300 */             fileOutputStream.write(item.toString().getBytes());
/*     */           } 
/*     */         } 
/* 304 */       } catch (Exception e) {
/* 305 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 308 */     return junctionId;
/*     */   }
/*     */   
/*     */   public int getDefNumber() {
/* 313 */     return this.defNumber;
/*     */   }
/*     */   
/*     */   public void setDefNumber(int defNumber) {
/* 317 */     this.defNumber = defNumber;
/*     */   }
/*     */   
/*     */   public String getVectorFile() {
/* 321 */     return this.vectorFile;
/*     */   }
/*     */   
/*     */   public void setVectorFile(String vectorFile) {
/* 325 */     this.vectorFile = vectorFile;
/*     */   }
/*     */   
/*     */   public boolean isNodesAsPoints() {
/* 329 */     return this.nodesAsPoints;
/*     */   }
/*     */   
/*     */   public void setNodesAsPoints(boolean nodesAsPoints) {
/* 333 */     this.nodesAsPoints = nodesAsPoints;
/*     */   }
/*     */   
/*     */   public FilterList getNodeFilterList() {
/* 337 */     return this.nodeFilterList;
/*     */   }
/*     */   
/*     */   public void setNodeFilterList(FilterList nodeFilterList) {
/* 341 */     this.nodeFilterList = nodeFilterList;
/*     */   }
/*     */   
/*     */   public List<Byte> acceptsTag(HashMap<String, String> tags) {
/* 346 */     List<Byte> value = this.filterList.acceptsTag(tags);
/* 347 */     if (this.nodeFilterList != null) {
/* 348 */       List<Byte> nodes = this.nodeFilterList.acceptsTag(tags);
/* 349 */       if (value != null && nodes != null) {
/* 350 */         value.addAll(nodes);
/* 351 */       } else if (nodes != null) {
/* 352 */         return nodes;
/*     */       } 
/*     */     } 
/* 355 */     return value;
/*     */   }
/*     */   
/*     */   public boolean requiresAreaTracking(Byte tagList) {
/* 361 */     return false;
/*     */   }
/*     */   
/*     */   public List<Byte> acceptsNodeTag(HashMap<String, String> tags) {
/* 365 */     if (this.nodeFilterList != null) {
/* 366 */       List<Byte> nodes = this.nodeFilterList.acceptsTag(tags);
/* 367 */       return nodes;
/*     */     } 
/* 369 */     return null;
/*     */   }
/*     */   
/*     */   public List<Byte> acceptsWayTags(HashMap<String, String> tags) {
/* 374 */     if (this.filterList != null) {
/* 375 */       List<Byte> nodes = this.filterList.acceptsTag(tags);
/* 376 */       return nodes;
/*     */     } 
/* 378 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Network\VectorRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */