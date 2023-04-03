/*     */ package com.world2xplane.Mesh;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*     */ import com.world2xplane.Geom.Geom3D.DelaunayTriangulator;
/*     */ import com.world2xplane.Geom.Geom3D.NotEnoughPointsException;
/*     */ import com.world2xplane.Geom.Geom3D.Triangle;
/*     */ import com.world2xplane.Geom.Geom3D.Vector2D;
/*     */ import com.world2xplane.XPlane.DSFTool;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import math.geom2d.Point2D;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.DataStoreFinder;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.poly2tri.Poly2Tri;
/*     */ import org.poly2tri.geometry.polygon.Polygon;
/*     */ import org.poly2tri.geometry.polygon.PolygonPoint;
/*     */ import org.poly2tri.triangulation.TriangulationPoint;
/*     */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class MeshGenerator {
/*     */   private static final double RESOLUTION = 300.0D;
/*     */   
/*  58 */   private static Logger log = LoggerFactory.getLogger(MeshGenerator.class);
/*     */   
/*     */   private Coordinate tileCoordinate;
/*     */   
/*     */   private String outputDirectory;
/*     */   
/*     */   private String directory;
/*     */   
/*     */   private String dsfTxtPath;
/*     */   
/*  65 */   private HashMap<String, HgtReader> hgtMap = new HashMap<>();
/*     */   
/*     */   private Geometry bounds;
/*     */   
/*     */   private Envelope boundary;
/*     */   
/*     */   private double west;
/*     */   
/*     */   private double east;
/*     */   
/*     */   private double north;
/*     */   
/*     */   private double south;
/*     */   
/*     */   private MultiPolygon waterMesh;
/*     */   
/*     */   private MultiPolygon landMeshSquare;
/*     */   
/*     */   public void setTileCoordinate(Coordinate tileCoordinate) {
/*  75 */     this.tileCoordinate = tileCoordinate;
/*     */   }
/*     */   
/*     */   public void setOutputDirectory(String outputDirectory) {
/*  79 */     this.outputDirectory = outputDirectory;
/*     */   }
/*     */   
/*     */   public void generateMesh() throws Exception {
/*  86 */     setupDirectoryStructure();
/*  89 */     this.boundary = new Envelope(this.tileCoordinate.x + 1.0D, this.tileCoordinate.x, this.tileCoordinate.y, this.tileCoordinate.y + 1.0D);
/*  91 */     GeometryFactory geometryFactory = new GeometryFactory();
/*  92 */     this.bounds = geometryFactory.toGeometry(this.boundary);
/*  94 */     this.west = this.tileCoordinate.x;
/*  95 */     this.east = this.tileCoordinate.x + 1.0D;
/*  96 */     this.north = this.tileCoordinate.y + 1.0D;
/*  97 */     this.south = this.tileCoordinate.y;
/* 100 */     createWaterMesh();
/* 103 */     FileOutputStream fileOutputStream = new FileOutputStream(new File(this.dsfTxtPath));
/* 105 */     StringBuilder sb = new StringBuilder();
/* 106 */     int latitude = (int)this.tileCoordinate.y;
/* 107 */     int longitude = (int)this.tileCoordinate.x;
/* 109 */     sb.append("I\n");
/* 110 */     sb.append("800\n");
/* 111 */     sb.append("DSF2TEXT\n\n");
/* 112 */     sb.append("DIVISIONS 32\n\n");
/* 113 */     sb.append("PROPERTY sim/planet earth\n");
/* 114 */     sb.append("PROPERTY sim/creation_agent World2XPlane 0.7.4\n");
/* 116 */     fileOutputStream.write(sb.toString().getBytes());
/* 117 */     fileOutputStream.flush();
/* 119 */     sb = new StringBuilder();
/* 122 */     sb.append("\nPROPERTY sim/west " + longitude + "\n");
/* 123 */     sb.append("PROPERTY sim/east " + (longitude + 1) + "\n");
/* 124 */     sb.append("PROPERTY sim/north " + (latitude + 1) + "\n");
/* 125 */     sb.append("PROPERTY sim/south " + latitude + "\n\n");
/* 126 */     sb.append("TERRAIN_DEF terrain_Water\n");
/* 127 */     sb.append("TERRAIN_DEF terrain/shrub.ter\n");
/* 129 */     fileOutputStream.write(sb.toString().getBytes());
/* 130 */     fileOutputStream.flush();
/* 133 */     writeLandMesh(fileOutputStream);
/* 136 */     fileOutputStream.flush();
/* 137 */     fileOutputStream.close();
/* 141 */     File file = new File(this.dsfTxtPath);
/* 142 */     String inputFile = file.getAbsolutePath();
/* 143 */     String outputFile = file.getAbsolutePath().replaceFirst(".txt", ".dsf");
/* 145 */     Runtime runtime = Runtime.getRuntime();
/* 146 */     Process p = runtime.exec(new String[] { DSFTool.getDSFToolPath(), "--text2dsf", inputFile, outputFile });
/*     */     try {
/* 148 */       p.waitFor();
/* 149 */     } catch (InterruptedException e) {
/* 150 */       e.printStackTrace();
/*     */     } 
/* 154 */     if (!(new File(outputFile)).exists()) {
/* 155 */       log.error("Tile " + this + " was not created, DSFTool failed with an error.");
/* 156 */       throw new RuntimeException("Tile " + this + " was not created, DSFTool failed with an error. Generation has stopped");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeLandMesh(FileOutputStream fileOutputStream) throws Exception {
/* 163 */     List<Geometry> land = new ArrayList<>();
/*     */     try {
/* 167 */       Map<Object, Object> connect = new HashMap<>();
/* 168 */       connect.put("url", (new File("./test/land-polygons-complete-4326/land_polygons.shp")).toURL());
/* 170 */       DataStore dataStore = DataStoreFinder.getDataStore(connect);
/* 171 */       if (dataStore == null) {
/* 172 */         log.error("Failed to read coast shapefile");
/* 173 */         System.exit(1);
/*     */       } 
/* 175 */       String[] typeNames = dataStore.getTypeNames();
/* 176 */       String typeName = typeNames[0];
/* 178 */       SimpleFeatureSource simpleFeatureSource = dataStore.getFeatureSource(typeName);
/* 179 */       FeatureCollection collection = simpleFeatureSource.getFeatures();
/* 180 */       FeatureIterator iterator = collection.features();
/*     */       try {
/* 184 */         while (iterator.hasNext()) {
/* 185 */           Feature feature = iterator.next();
/* 186 */           Geometry sourceGeometry = (Geometry)feature.getDefaultGeometryProperty().getValue();
/* 187 */           if (sourceGeometry.intersects(this.bounds))
/* 188 */             land.add(sourceGeometry); 
/*     */         } 
/*     */       } finally {
/* 194 */         iterator.close();
/*     */       } 
/* 196 */       dataStore.dispose();
/* 198 */     } catch (Throwable e) {
/* 199 */       e.printStackTrace();
/*     */     } 
/* 202 */     List<Geometry> landTriangles = new ArrayList<>();
/* 203 */     List<Geometry> landPolys = new ArrayList<>();
/* 206 */     log.info("Creating boundary from land shapefile for " + land.size() + " shapes");
/* 208 */     int count = 0;
/* 209 */     for (Geometry island : land) {
/* 210 */       Geometry clipped = island.intersection(this.bounds);
/* 211 */       if (clipped != null && clipped.getNumPoints() > 0) {
/* 212 */         landPolys.add(island);
/* 213 */         clipped = split(clipped, this.north, this.east, this.south, this.west);
/* 215 */         if (clipped.getNumPoints() > 0)
/* 216 */           landTriangles.add(clipped); 
/*     */       } 
/* 219 */       log.info(count + "");
/* 220 */       count++;
/*     */     } 
/* 222 */     log.info("Number of triangles " + landTriangles.size());
/* 225 */     log.info("Creating water mesh");
/* 226 */     List<Polygon> waterTiles = triangulate((Geometry)this.waterMesh);
/* 227 */     GeometryCollection geometryCollection = geometryFactory.createGeometryCollection(landPolys.<Geometry>toArray(new Geometry[landPolys.size()]));
/* 229 */     Geometry landArea = geometryCollection.union();
/* 263 */     log.info("Creating Land Mesh");
/* 265 */     int index = 0;
/* 266 */     for (Geometry island : landTriangles) {
/* 267 */       log.info("Island " + index + " " + landTriangles.size());
/* 268 */       index++;
/* 270 */       PreparedGeometry preparedGeometry = PreparedGeometryFactory.prepare(island);
/* 271 */       Geometry clipped = island;
/* 272 */       List<Polygon> landTri = triangulate(clipped);
/* 273 */       log.info("Triangulated");
/* 275 */       if (landTri.size() > 0) {
/* 276 */         log.info("Number of triangles " + landTri.size());
/* 278 */         fileOutputStream.write("BEGIN_PATCH 1   0.0 -1.0    1   5\n".getBytes());
/* 280 */         fileOutputStream.write("BEGIN_PRIMITIVE 0\n".getBytes());
/* 281 */         for (int x = 0; x < landTri.size(); x++) {
/* 282 */           Polygon triangle = landTri.get(x);
/* 285 */           if (isClockwise(triangle.getBoundary().getCoordinates()))
/* 286 */             triangle = (Polygon)triangle.reverse(); 
/* 289 */           for (int y = 0; y < triangle.getExteriorRing().getNumPoints() - 1; y++) {
/* 291 */             Point point = triangle.getExteriorRing().getPointN(y);
/* 292 */             double height = 0.0D;
/* 293 */             if (preparedGeometry.covers((Geometry)point))
/* 294 */               height = getElevationFromHgt(new Point2D(point.getX(), point.getY())); 
/* 296 */             fileOutputStream.write(String.format(Locale.UK, "PATCH_VERTEX %f %f %f 0 0\n", new Object[] { Double.valueOf(point.getX()), Double.valueOf(point.getY()), Double.valueOf(height) }).getBytes());
/*     */           } 
/*     */         } 
/* 300 */         fileOutputStream.write("END_PRIMITIVE\n".getBytes());
/* 301 */         fileOutputStream.write("END_PATCH\n".getBytes());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isClockwise(Coordinate[] vertices) {
/* 310 */     return CGAlgorithms.isCCW(vertices);
/*     */   }
/*     */   
/*     */   private Geometry split(Geometry clipped, double north, double east, double south, double west) {
/* 316 */     double xDivisions = 0.0033333333333333335D;
/* 317 */     double yDivisions = 0.0033333333333333335D;
/* 318 */     GeometryFactory geometryFactory = new GeometryFactory();
/* 319 */     List<Polygon> meshGrid = new ArrayList<>();
/*     */     double x;
/* 320 */     for (x = west; x <= east; x += xDivisions) {
/*     */       double y;
/* 321 */       for (y = south; y <= north; y += yDivisions) {
/* 322 */         Polygon meshSquare = geometryFactory.createPolygon(new Coordinate[] { new Coordinate(x, y), new Coordinate(x + xDivisions, y), new Coordinate(x + xDivisions, y + yDivisions), new Coordinate(x, y + yDivisions), new Coordinate(x, y) });
/* 326 */         Geometry item = clipped.intersection((Geometry)meshSquare);
/* 327 */         if (item.getNumPoints() > 0 && item instanceof Polygon) {
/* 328 */           meshGrid.add((Polygon)item);
/* 329 */         } else if (item.getNumPoints() > 0 && item instanceof MultiPolygon) {
/* 330 */           MultiPolygon multiPolygon = (MultiPolygon)item;
/* 331 */           for (int z = 0; z < multiPolygon.getNumGeometries(); z++)
/* 332 */             meshGrid.add((Polygon)multiPolygon.getGeometryN(z)); 
/* 334 */         } else if (item.getNumPoints() > 0) {
/* 335 */           throw new RuntimeException("Here " + item.getClass() + "");
/*     */         } 
/*     */       } 
/*     */     } 
/* 340 */     return (Geometry)geometryFactory.createMultiPolygon(meshGrid.<Polygon>toArray(new Polygon[meshGrid.size()]));
/*     */   }
/*     */   
/*     */   private void writePoly(Polygon field, StringBuilder dsfOut, int i, int i1) {}
/*     */   
/*     */   private List<Polygon> triangulate(Geometry clipped) throws NotEnoughPointsException {
/* 374 */     List<Polygon> triangulationPoints = new ArrayList<>();
/* 375 */     for (int z = 0; z < clipped.getNumGeometries(); z++) {
/* 376 */       Polygon shape = (Polygon)clipped.getGeometryN(z);
/* 378 */       List<PolygonPoint> points = new ArrayList<>();
/* 380 */       for (int p = 0; p < shape.getExteriorRing().getNumPoints(); p++) {
/* 381 */         Point point = shape.getExteriorRing().getPointN(p);
/* 382 */         points.add(new PolygonPoint(point.getX(), point.getY()));
/*     */       } 
/* 385 */       if (points.size() != 0) {
/* 388 */         Polygon polygon = new Polygon(points);
/*     */         try {
/* 390 */           Poly2Tri.triangulate(polygon);
/* 391 */           List<DelaunayTriangle> triangles = polygon.getTriangles();
/* 393 */           for (DelaunayTriangle triangle : triangles) {
/* 394 */             TriangulationPoint[] tp = triangle.points;
/* 395 */             Polygon polygon1 = (new GeometryFactory()).createPolygon(new Coordinate[] { new Coordinate(tp[0].getX(), tp[0].getY()), new Coordinate(tp[1].getX(), tp[1].getY()), new Coordinate(tp[2].getX(), tp[2].getY()), new Coordinate(tp[0].getX(), tp[0].getY()) });
/* 398 */             triangulationPoints.add(polygon1);
/*     */           } 
/* 400 */         } catch (Exception e) {
/*     */           try {
/* 403 */             e.printStackTrace();
/* 405 */             Vector<Vector2D> vector2Ds = new Vector<>();
/* 407 */             for (int j = 0; j < shape.getExteriorRing().getNumPoints(); j++) {
/* 408 */               Point point = shape.getExteriorRing().getPointN(j);
/* 409 */               vector2Ds.add(new Vector2D(point.getX(), point.getY()));
/*     */             } 
/* 412 */             DelaunayTriangulator delaunayTriangulator = new DelaunayTriangulator(vector2Ds);
/* 413 */             delaunayTriangulator.compute();
/* 414 */             for (int i = 0; i < delaunayTriangulator.getTriangleSet().size(); i++) {
/* 415 */               Triangle triangle = delaunayTriangulator.getTriangleSet().get(i);
/* 416 */               Vector2D a = triangle.a;
/* 417 */               Vector2D b = triangle.b;
/* 418 */               Vector2D c = triangle.c;
/* 420 */               Polygon polygon1 = (new GeometryFactory()).createPolygon(new Coordinate[] { new Coordinate(a.x, a.y), new Coordinate(b.x, b.y), new Coordinate(c.x, c.y), new Coordinate(a.x, a.y) });
/* 423 */               triangulationPoints.add(polygon1);
/*     */             } 
/* 426 */           } catch (Exception e1) {
/* 427 */             e1.printStackTrace();
/*     */           } 
/* 431 */         } catch (StackOverflowError stackOverflowError) {
/* 432 */           log.error("Stack Overflow");
/*     */           try {
/* 437 */             Vector<Vector2D> vector2Ds = new Vector<>();
/* 439 */             for (int j = 0; j < shape.getExteriorRing().getNumPoints(); j++) {
/* 440 */               Point point = shape.getExteriorRing().getPointN(j);
/* 441 */               vector2Ds.add(new Vector2D(point.getX(), point.getY()));
/*     */             } 
/* 444 */             DelaunayTriangulator delaunayTriangulator = new DelaunayTriangulator(vector2Ds);
/* 445 */             delaunayTriangulator.compute();
/* 446 */             for (int i = 0; i < delaunayTriangulator.getTriangleSet().size(); i++) {
/* 447 */               Triangle triangle = delaunayTriangulator.getTriangleSet().get(i);
/* 448 */               Vector2D a = triangle.a;
/* 449 */               Vector2D b = triangle.b;
/* 450 */               Vector2D c = triangle.c;
/* 452 */               Polygon polygon1 = (new GeometryFactory()).createPolygon(new Coordinate[] { new Coordinate(a.x, a.y), new Coordinate(b.x, b.y), new Coordinate(c.x, c.y), new Coordinate(a.x, a.y) });
/* 455 */               triangulationPoints.add(polygon1);
/*     */             } 
/* 459 */           } catch (Exception e1) {
/* 460 */             e1.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 466 */     return triangulationPoints;
/*     */   }
/*     */   
/*     */   private double getElevationFromHgt(Point2D point2D) throws Exception {
/* 471 */     String filename = HgtReader.getHgtFileName(point2D);
/* 472 */     if (!this.hgtMap.containsKey(filename)) {
/* 473 */       log.info("Reading Elevation " + filename);
/* 474 */       HgtReader hgtReader = new HgtReader(new File("./test/hgt/" + filename));
/* 475 */       this.hgtMap.put(filename, hgtReader);
/* 476 */       return hgtReader.getElevationFromHgt(point2D);
/*     */     } 
/* 478 */     return ((HgtReader)this.hgtMap.get(filename)).getElevationFromHgt(point2D);
/*     */   }
/*     */   
/* 482 */   private static GeometryFactory geometryFactory = new GeometryFactory();
/*     */   
/*     */   private void createWaterMesh() {
/* 485 */     double xDivisions = 0.02D;
/* 486 */     double yDivisions = 0.02D;
/* 488 */     List<Polygon> meshGrid = new ArrayList<>();
/*     */     double x;
/* 489 */     for (x = this.west; x <= this.east; x += xDivisions) {
/*     */       double y;
/* 490 */       for (y = this.south; y <= this.north; y += yDivisions) {
/* 491 */         Polygon meshSquare = geometryFactory.createPolygon(new Coordinate[] { new Coordinate(x, y), new Coordinate(x + xDivisions, y), new Coordinate(x + xDivisions, y + yDivisions), new Coordinate(x, y + yDivisions), new Coordinate(x, y) });
/* 495 */         if (y + yDivisions < this.north && x + xDivisions < this.east)
/* 496 */           meshGrid.add(meshSquare); 
/*     */       } 
/*     */     } 
/* 499 */     this.waterMesh = geometryFactory.createMultiPolygon(meshGrid.<Polygon>toArray(new Polygon[meshGrid.size()]));
/*     */   }
/*     */   
/*     */   private void setupDirectoryStructure() {
/* 503 */     int latitude = (int)this.tileCoordinate.y;
/* 504 */     int longitude = (int)this.tileCoordinate.x;
/* 505 */     String[] folderAndFileNames = getFolderAndFileNames(longitude, latitude);
/* 506 */     this.directory = this.outputDirectory + File.separatorChar + "Earth nav data" + File.separatorChar + folderAndFileNames[0];
/* 508 */     this.dsfTxtPath = this.directory + File.separatorChar + folderAndFileNames[1] + ".txt";
/* 510 */     File file = new File(this.dsfTxtPath);
/* 511 */     File parent = file.getParentFile();
/* 512 */     if (parent != null && !parent.exists() && 
/* 513 */       !parent.mkdirs())
/* 514 */       throw new RuntimeException("File '" + file + "' could not be created"); 
/*     */   }
/*     */   
/*     */   private String dsfFilePath(int x, int y) {
/* 521 */     String[] folderAndFileNames = getFolderAndFileNames(x, y);
/* 522 */     String folderName = folderAndFileNames[1];
/* 523 */     String pathOne = folderAndFileNames[0];
/* 524 */     String pathTwo = this.outputDirectory + File.separatorChar + "Earth nav data" + File.separatorChar + pathOne;
/* 526 */     return pathTwo + File.separatorChar + folderName + ".dsf.txt";
/*     */   }
/*     */   
/*     */   public String[] getFolderAndFileNames(int longitude, int latitude) {
/* 533 */     Double folderFirst = new Double(latitude);
/* 534 */     Double folderEnd = new Double(longitude);
/* 535 */     while (folderFirst.doubleValue() % 10.0D != 0.0D)
/* 536 */       Double double_1 = folderFirst, double_2 = folderFirst = Double.valueOf(folderFirst.doubleValue() - 1.0D); 
/* 538 */     while (folderEnd.doubleValue() % 10.0D != 0.0D)
/* 539 */       Double double_1 = folderEnd, double_2 = folderEnd = Double.valueOf(folderEnd.doubleValue() - 1.0D); 
/* 542 */     DecimalFormat dfLat = (DecimalFormat)DecimalFormat.getNumberInstance();
/* 543 */     dfLat.applyPattern("00");
/* 544 */     DecimalFormat dfLong = (DecimalFormat)DecimalFormat.getNumberInstance();
/* 546 */     dfLong.applyPattern("000");
/* 547 */     StringBuffer f1 = new StringBuffer();
/* 548 */     StringBuffer f2 = new StringBuffer();
/* 549 */     if (latitude >= 0) {
/* 550 */       f1.append("+");
/* 551 */       f2.append("+");
/*     */     } 
/* 553 */     f1.append(dfLat.format(latitude));
/* 554 */     f2.append(dfLat.format(folderFirst));
/* 555 */     if (longitude >= 0) {
/* 556 */       f1.append("+");
/* 557 */       f2.append("+");
/*     */     } 
/* 559 */     f1.append(dfLong.format(longitude));
/* 560 */     f2.append(dfLong.format(folderEnd));
/* 561 */     String filename = f1.toString();
/* 562 */     String foName = f2.toString();
/* 563 */     String[] result = { foName, filename };
/* 564 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Mesh\MeshGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */