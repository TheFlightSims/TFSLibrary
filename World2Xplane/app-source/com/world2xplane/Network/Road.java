/*     */ package com.world2xplane.Network;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.Externalizable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.geotools.geometry.jts.GeometryClipper;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class Road implements Externalizable {
/*  47 */   private static Logger log = LoggerFactory.getLogger(Road.class);
/*     */   
/*  49 */   public static short HIGHWAY_3 = 101;
/*     */   
/*  50 */   public static short HIGHWAY_2 = 111;
/*     */   
/*  52 */   public static short HIGHWAY_3_LIT = 100;
/*     */   
/*  53 */   public static short HIGHWAY_2_LIT = 110;
/*     */   
/*  55 */   public static short PRIMARY = 10;
/*     */   
/*  56 */   public static short PRIMARY_ONE_WAY = 20;
/*     */   
/*  58 */   public static short SECONDARY = 30;
/*     */   
/*  59 */   public static short SECONDARY_ONE_WAY = 40;
/*     */   
/*  61 */   public static short TERTIARY = 30;
/*     */   
/*  62 */   public static short TERTIARY_ONE_WAY = 40;
/*     */   
/*  64 */   public static short RESIDENTIAL = 51;
/*     */   
/*  65 */   public static short RESIDENTIAL_ONE_WAY = 60;
/*     */   
/*  67 */   public static short SERVICE = 70;
/*     */   
/*  68 */   public static short SERVICE_ONE_WAY = 71;
/*     */   
/*  70 */   public static short RAIL_PRIMARY = 151;
/*     */   
/*  71 */   public static short RAIL_SECONDARY = 152;
/*     */   
/*  72 */   public static short RAIL_TERTIARY = 153;
/*     */   
/*  74 */   public static short PRIMARY_LINK = 120;
/*     */   
/*  75 */   public static short SECONDARY_LINK = 120;
/*     */   
/*  77 */   public static short RUNWAY = 0;
/*     */   
/*  79 */   public List<Junction> junctions = new ArrayList<>();
/*     */   
/*  80 */   List<Long> junctionIds = new ArrayList<>();
/*     */   
/*  81 */   short roadType = 0;
/*     */   
/*     */   boolean tunnel = false;
/*     */   
/*     */   boolean bridge = false;
/*     */   
/*     */   long identifier;
/*     */   
/*     */   boolean rendered = false;
/*     */   
/*     */   public PreparedGeometry lineString;
/*     */   
/*     */   private static HashMap<Short, Float> roadSizes;
/*     */   
/*     */   public boolean equals(Object other) {
/*  92 */     if (!(other instanceof Road) || other == null)
/*  93 */       return false; 
/*  95 */     return (((Road)other).identifier == this.identifier);
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 100 */     out.writeInt(this.junctions.size());
/* 101 */     for (Junction item : this.junctions)
/* 102 */       out.writeObject(item); 
/* 105 */     out.writeInt(this.junctionIds.size());
/* 106 */     for (Long item : this.junctionIds)
/* 107 */       out.writeLong(item.longValue()); 
/* 110 */     out.writeShort(this.roadType);
/* 112 */     out.writeBoolean(this.tunnel);
/* 113 */     out.writeBoolean(this.bridge);
/* 115 */     out.writeLong(this.identifier);
/* 116 */     if (this.lineString != null) {
/* 117 */       out.writeBoolean(true);
/* 118 */       out.writeObject(this.lineString);
/*     */     } else {
/* 120 */       out.writeBoolean(false);
/*     */     } 
/* 123 */     out.writeBoolean(this.rendered);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 128 */     int nJunctions = in.readInt();
/*     */     int x;
/* 129 */     for (x = 0; x < nJunctions; x++)
/* 130 */       this.junctions.add((Junction)in.readObject()); 
/* 133 */     nJunctions = in.readInt();
/* 134 */     for (x = 0; x < nJunctions; x++)
/* 135 */       this.junctionIds.add(Long.valueOf(in.readLong())); 
/* 138 */     this.roadType = in.readShort();
/* 140 */     this.tunnel = in.readBoolean();
/* 141 */     this.bridge = in.readBoolean();
/* 142 */     this.identifier = in.readLong();
/* 144 */     boolean hasLine = in.readBoolean();
/* 145 */     if (hasLine)
/* 146 */       this.lineString = (PreparedGeometry)in.readObject(); 
/* 149 */     this.rendered = in.readBoolean();
/*     */   }
/*     */   
/*     */   public void setNorthSouth() {
/* 156 */     if (this.junctions.size() < 2)
/*     */       return; 
/* 160 */     Junction j1 = this.junctions.get(0);
/* 161 */     Junction j2 = this.junctions.get(this.junctions.size() - 1);
/* 163 */     if (j1.getLat() > j2.getLat())
/* 164 */       Collections.reverse(this.junctions); 
/*     */   }
/*     */   
/*     */   public void reverse() {
/* 170 */     Collections.reverse(this.junctions);
/*     */   }
/*     */   
/*     */   public double getSize() {
/* 176 */     if (roadSizes == null)
/*     */       try {
/* 178 */         fetchRoadSizes();
/* 179 */       } catch (Exception e) {
/* 180 */         roadSizes = new HashMap<>();
/*     */       }  
/* 185 */     if (roadSizes.size() > 0)
/* 186 */       return (((Float)roadSizes.get(Short.valueOf(this.roadType))).floatValue() / 2.0F); 
/* 190 */     if (this.roadType == HIGHWAY_2)
/* 191 */       return 6.25D; 
/* 194 */     if (this.roadType == HIGHWAY_3)
/* 195 */       return 8.625D; 
/* 198 */     if (this.roadType == PRIMARY)
/* 199 */       return 11.31D; 
/* 202 */     if (this.roadType == PRIMARY_ONE_WAY)
/* 203 */       return 7.935D; 
/* 206 */     if (this.roadType == SECONDARY)
/* 207 */       return 9.65D; 
/* 210 */     if (this.roadType == SECONDARY_ONE_WAY)
/* 211 */       return 9.65D; 
/* 214 */     if (this.roadType == TERTIARY)
/* 215 */       return 9.65D; 
/* 218 */     if (this.roadType == TERTIARY_ONE_WAY)
/* 219 */       return 9.65D; 
/* 222 */     if (this.roadType == RESIDENTIAL)
/* 223 */       return 7.625D; 
/* 226 */     if (this.roadType == RESIDENTIAL_ONE_WAY)
/* 227 */       return 7.625D; 
/* 230 */     if (this.roadType == SERVICE || this.roadType == SERVICE_ONE_WAY)
/* 231 */       return 3.375D; 
/* 234 */     return 8.0D;
/*     */   }
/*     */   
/*     */   private static void fetchRoadSizes() throws Exception {
/* 242 */     File xplaneDirectory = new File(GeneratorStore.getGeneratorStore().getXplaneSceneryFolder());
/* 243 */     if (!xplaneDirectory.exists()) {
/* 244 */       log.error("Can't determine size of roads for buffering as X-Plane directory is not found. Scenery clipping will be less accurate");
/*     */       return;
/*     */     } 
/* 247 */     String path = "Resources" + File.separator + "default scenery" + File.separator + "1000 roads" + File.separator + "roads.net";
/* 249 */     File roadFile = new File(xplaneDirectory, path);
/* 250 */     if (!roadFile.exists()) {
/* 251 */       log.error("Can't determine size of roads for buffering as X-Plane road.net file is not found. Scenery clipping will be less accurate");
/*     */       return;
/*     */     } 
/* 254 */     roadSizes = new HashMap<>();
/* 255 */     String contents = FileUtils.readFileToString(roadFile);
/* 256 */     Float cLength = null;
/* 257 */     String[] lines = contents.split("\n");
/* 258 */     for (String line : lines) {
/* 259 */       if (line.startsWith("#VROAD ")) {
/* 260 */         String[] items = line.split(" ");
/* 261 */         String wString = items[2];
/* 262 */         wString = wString.replace("(", "").replace(")", "");
/* 263 */         cLength = new Float(wString);
/*     */       } 
/* 265 */       if (line.startsWith("ROAD_DRAPED ") && cLength != null) {
/* 266 */         String[] items = line.split(" ");
/* 267 */         String wString = items[2];
/* 268 */         Short roadType = new Short(wString);
/* 269 */         roadSizes.put(roadType, cLength);
/* 270 */         cLength = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public PreparedGeometry generateLineString(Box2D box) {
/* 279 */     GeometryFactory geometryFactory = new GeometryFactory();
/* 281 */     Coordinate[] b_coordinates = new Coordinate[this.junctions.size()];
/* 282 */     for (int x = 0; x < this.junctions.size(); x++) {
/* 283 */       Coordinate coord = new Coordinate(((Junction)this.junctions.get(x)).getLon(), ((Junction)this.junctions.get(x)).getLat());
/* 285 */       b_coordinates[x] = coord;
/*     */     } 
/* 288 */     LineString ls = geometryFactory.createLineString(b_coordinates);
/* 290 */     LinearRing2D boundary = new LinearRing2D();
/* 291 */     boundary.addVertex(new Point2D(box.getMinX(), box.getMinY()));
/* 292 */     boundary.addVertex(new Point2D(box.getMaxX(), box.getMinY()));
/* 293 */     boundary.addVertex(new Point2D(box.getMaxX(), box.getMaxY()));
/* 294 */     boundary.addVertex(new Point2D(box.getMinX(), box.getMaxY()));
/* 295 */     boundary.addVertex(new Point2D(box.getMinX(), box.getMinY()));
/* 296 */     Geometry clipper = GeomUtils.linearRingToJTSPolygon(boundary);
/* 298 */     GeometryClipper geometryClipper = new GeometryClipper(clipper.getEnvelopeInternal());
/* 299 */     Geometry output = geometryClipper.clip((Geometry)ls, true);
/* 301 */     if (output == null || output.getNumPoints() < 2) {
/* 302 */       this.lineString = PreparedGeometryFactory.prepare((Geometry)ls);
/* 303 */       return this.lineString;
/*     */     } 
/* 306 */     if (output instanceof LineString) {
/* 307 */       this.lineString = PreparedGeometryFactory.prepare(output);
/* 308 */       return this.lineString;
/*     */     } 
/* 312 */     this.lineString = PreparedGeometryFactory.prepare((Geometry)ls);
/* 313 */     return this.lineString;
/*     */   }
/*     */   
/*     */   public PreparedGeometry getLineString() {
/* 317 */     return this.lineString;
/*     */   }
/*     */   
/*     */   public short getRoadType() {
/* 321 */     return this.roadType;
/*     */   }
/*     */   
/*     */   public boolean isTunnel() {
/* 325 */     return this.tunnel;
/*     */   }
/*     */   
/*     */   public boolean isBridge() {
/* 329 */     return this.bridge;
/*     */   }
/*     */   
/*     */   public long getIdentifier() {
/* 333 */     return this.identifier;
/*     */   }
/*     */   
/*     */   public boolean isRendered() {
/* 337 */     return this.rendered;
/*     */   }
/*     */   
/*     */   public Geometry buffer(double additionalSize) {
/* 341 */     return GeomUtils.preciseBuffer(getLineString().getGeometry(), additionalSize + getSize() / 2.0D);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Network\Road.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */