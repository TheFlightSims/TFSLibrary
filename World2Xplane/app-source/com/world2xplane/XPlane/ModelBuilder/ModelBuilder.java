/*      */ package com.world2xplane.XPlane.ModelBuilder;
/*      */ 
/*      */ import com.world2xplane.Geom.GeomUtils;
/*      */ import com.world2xplane.Geom.Vector3D;
/*      */ import com.world2xplane.OSM.NamedColour;
/*      */ import com.world2xplane.OSM.OSMPolygon;
/*      */ import com.world2xplane.OSM.OsmUtils;
/*      */ import com.world2xplane.Rules.GeneratorStore;
/*      */ import com.world2xplane.XPlane.DDSTool;
/*      */ import java.awt.Color;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.TexturePaint;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.lang.reflect.Field;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.ImageIcon;
/*      */ import math.geom2d.Point2D;
/*      */ import math.geom2d.line.LineSegment2D;
/*      */ import math.geom2d.polygon.LinearRing2D;
/*      */ import org.apache.commons.io.FileUtils;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ 
/*      */ public class ModelBuilder {
/*   61 */   private static Logger log = LoggerFactory.getLogger(ModelBuilder.class);
/*      */   
/*   64 */   Long wayId = null;
/*      */   
/*      */   private Double height;
/*      */   
/*      */   private Double roofHeight;
/*      */   
/*      */   public Double getHeight() {
/*   69 */     return this.height;
/*      */   }
/*      */   
/*      */   public double getRoofHeight() {
/*   73 */     return this.roofHeight.doubleValue();
/*      */   }
/*      */   
/*      */   public static class ObjPoint {
/*      */     Double x;
/*      */     
/*      */     Double y;
/*      */     
/*      */     Double z;
/*      */     
/*      */     Double nx;
/*      */     
/*      */     Double ny;
/*      */     
/*      */     Double nz;
/*      */     
/*      */     Double u;
/*      */     
/*      */     Double v;
/*      */     
/*      */     public ObjPoint(double x, double y, double z, double nx, double ny, double nz, double u, double v) {
/*   90 */       this.x = Double.valueOf(x);
/*   91 */       this.y = Double.valueOf(y);
/*   92 */       this.z = Double.valueOf(z);
/*   93 */       this.nx = Double.valueOf(nx);
/*   94 */       this.ny = Double.valueOf(ny);
/*   95 */       this.nz = Double.valueOf(nz);
/*   96 */       this.u = Double.valueOf(u);
/*   97 */       this.v = Double.valueOf(v);
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  101 */       return this.x.hashCode() + this.y.hashCode() + this.z.hashCode() + this.nx.hashCode() + this.nz.hashCode() + this.ny.hashCode() + this.u.hashCode() + this.v.hashCode();
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*  105 */       if (o == null || !(o instanceof ObjPoint))
/*  106 */         return false; 
/*  108 */       ObjPoint other = (ObjPoint)o;
/*  110 */       return (this.x.equals(other.x) && this.y.equals(other.y) && this.z.equals(other.z) && this.nx.equals(other.nx) && this.ny.equals(other.ny) && this.u.equals(other.u) && this.v.equals(other.v));
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ObjTriangle {
/*      */     ModelBuilder.ObjPoint a;
/*      */     
/*      */     ModelBuilder.ObjPoint b;
/*      */     
/*      */     ModelBuilder.ObjPoint c;
/*      */     
/*      */     public ObjTriangle(ModelBuilder.ObjPoint a, ModelBuilder.ObjPoint b, ModelBuilder.ObjPoint c) {
/*  124 */       this.a = a;
/*  125 */       this.b = b;
/*  126 */       this.c = c;
/*      */     }
/*      */   }
/*      */   
/*  131 */   private Set<ObjPoint> vertexes = new HashSet<>();
/*      */   
/*  132 */   private List<ObjTriangle> triangles = new ArrayList<>();
/*      */   
/*      */   public static final int ROOF_FLAT = 0;
/*      */   
/*  136 */   DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/*      */   
/*  138 */   NumberFormat numberFormat = new DecimalFormat("####0.0000000", this.formatSymbols);
/*      */   
/*      */   public boolean isPart(Long identifier) {
/*      */     try {
/*  144 */       if (!(new File(GeneratorStore.getGeneratorStore().getOutputFolder() + File.separator + "objects" + File.separator + "parts" + File.separator + "part_" + identifier + ".txt")).exists())
/*  146 */         return false; 
/*  148 */       InputStream input = null;
/*  149 */       Properties prop = new Properties();
/*  150 */       this.wayId = identifier;
/*  151 */       input = new FileInputStream(GeneratorStore.getGeneratorStore().getOutputFolder() + File.separator + "objects" + File.separator + "parts" + File.separator + "part_" + identifier + ".txt");
/*  156 */       prop.load(input);
/*  157 */       return prop.containsKey("building:part");
/*  159 */     } catch (Exception e) {
/*  160 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public String generateFromFile(OSMPolygon outer, Long identifier, int tileX, int tileY) {
/*  165 */     Properties prop = new Properties();
/*  166 */     InputStream input = null;
/*      */     try {
/*  171 */       if (!(new File(GeneratorStore.getGeneratorStore().getOutputFolder() + File.separator + "objects" + File.separator + "parts" + File.separator + "part_" + identifier + ".txt")).exists())
/*  173 */         return null; 
/*  175 */       this.wayId = identifier;
/*  176 */       input = new FileInputStream(GeneratorStore.getGeneratorStore().getOutputFolder() + File.separator + "objects" + File.separator + "parts" + File.separator + "part_" + identifier + ".txt");
/*  181 */       prop.load(input);
/*  183 */       HashMap<String, String> tags = new HashMap<>();
/*  184 */       for (Map.Entry<Object, Object> item : prop.entrySet())
/*  185 */         tags.put((String)item.getKey(), (String)item.getValue()); 
/*  188 */       File directory = new File(GeneratorStore.getGeneratorStore().getOutputFolder() + File.separator + "objects" + File.separator + tileX + File.separator + tileY + File.separator + "parts");
/*  191 */       FileUtils.forceMkdir(directory);
/*  193 */       File wallFile = new File(directory, "part_" + identifier + "w.obj");
/*  195 */       File roofFile = new File(directory, "part_" + identifier + "r.obj");
/*  198 */       Float minHeight = Float.valueOf(0.0F);
/*  199 */       Float height = Float.valueOf(10.0F);
/*  200 */       Float roofHeight = Float.valueOf(0.0F);
/*  202 */       if (tags.containsKey("building:levels"))
/*  203 */         height = parseBuildingLevels(tags.get("building:levels")); 
/*  205 */       if (tags.containsKey("roof:levels"))
/*  206 */         roofHeight = parseBuildingLevels(tags.get("roof:levels")); 
/*  209 */       if (tags.containsKey("min_height")) {
/*      */         try {
/*  211 */           minHeight = OsmUtils.parseHeight(tags.get("min_height"));
/*  212 */         } catch (Exception e) {
/*  213 */           minHeight = Float.valueOf(0.0F);
/*      */         } 
/*  215 */         if (minHeight == null)
/*  216 */           minHeight = Float.valueOf(0.0F); 
/*      */       } 
/*  219 */       if (tags.containsKey("height")) {
/*      */         try {
/*  221 */           height = OsmUtils.parseHeight(tags.get("height"));
/*  222 */         } catch (Exception e) {
/*  223 */           height = Float.valueOf(10.0F);
/*      */         } 
/*  225 */         if (height == null)
/*  226 */           height = Float.valueOf(10.0F); 
/*      */       } 
/*  229 */       if (tags.containsKey("building:height")) {
/*      */         try {
/*  231 */           height = OsmUtils.parseHeight(tags.get("building:height"));
/*  232 */         } catch (Exception e) {
/*  233 */           height = Float.valueOf(10.0F);
/*      */         } 
/*  235 */         if (height == null)
/*  236 */           height = Float.valueOf(10.0F); 
/*      */       } 
/*  240 */       if (tags.containsKey("roof:height")) {
/*      */         try {
/*  242 */           roofHeight = OsmUtils.parseHeight(tags.get("roof:height"));
/*  243 */         } catch (Exception e) {
/*  244 */           roofHeight = Float.valueOf(0.0F);
/*      */         } 
/*  246 */         if (roofHeight == null)
/*  247 */           roofHeight = Float.valueOf(0.0F); 
/*      */       } 
/*  250 */       String roofColor = "gray";
/*  251 */       if (tags.containsKey("roof:colour"))
/*  252 */         roofColor = tags.get("roof:colour"); 
/*  254 */       if (tags.containsKey("roof:color"))
/*  255 */         roofColor = tags.get("roof:color"); 
/*  257 */       String wallColor = null;
/*  258 */       if (tags.containsKey("building:colour"))
/*  259 */         wallColor = tags.get("building:colour"); 
/*  261 */       if (tags.containsKey("building:color"))
/*  262 */         wallColor = tags.get("building:color"); 
/*  264 */       String wallTexture = "none";
/*  265 */       String roofTexture = "none";
/*  266 */       if (tags.containsKey("roof:material"))
/*  267 */         roofTexture = tags.get("roof:material"); 
/*  269 */       if (tags.containsKey("building:material"))
/*  270 */         wallTexture = tags.get("building:material"); 
/*  272 */       String roofShape = "flat";
/*  273 */       if (tags.containsKey("roof:shape"))
/*  274 */         roofShape = ((String)tags.get("roof:shape")).trim().toLowerCase(); 
/*  277 */       if (!roofShape.equals("onion") && !roofShape.equals("dome") && !roofShape.equals("domed") && !roofShape.equals("pyramidal") && !roofShape.equals("round") && !roofShape.equals("mansard"))
/*  281 */         roofShape = "flat"; 
/*  285 */       if (outer.getArea().floatValue() < 4.0D && minHeight.floatValue() == 0.0F && height.floatValue() <= 4.1D)
/*  286 */         return null; 
/*  290 */       if (roofShape.equals("flat") && roofHeight.floatValue() > 0.0F) {
/*  291 */         height = Float.valueOf(height.floatValue() + roofHeight.floatValue());
/*  292 */         roofHeight = Float.valueOf(0.0F);
/*      */       } 
/*  295 */       String wContent = generateWall(outer.getShape(), minHeight.floatValue(), height.floatValue(), roofHeight.floatValue(), wallColor, wallTexture, directory);
/*  298 */       String rContent = generateRoof(outer.getShape(), height.floatValue(), roofHeight.floatValue(), roofShape, roofColor, roofTexture, directory);
/*  301 */       FileUtils.writeStringToFile(wallFile, wContent);
/*  302 */       FileUtils.writeStringToFile(roofFile, rContent);
/*  304 */       return "objects" + File.separator + tileX + File.separator + tileY + File.separator + "parts";
/*  307 */     } catch (IOException ex) {
/*  308 */       ex.printStackTrace();
/*      */     } finally {
/*  310 */       if (input != null)
/*      */         try {
/*  312 */           input.close();
/*  313 */           File file = new File(GeneratorStore.getGeneratorStore().getOutputFolder() + File.separator + "objects" + File.separator + "parts" + File.separator + "part_" + identifier + ".txt");
/*  315 */           file.delete();
/*  316 */         } catch (IOException e) {
/*  317 */           e.printStackTrace();
/*      */         }  
/*      */     } 
/*  321 */     return null;
/*      */   }
/*      */   
/*      */   private Float parseBuildingLevels(String tag) {
/*  325 */     Float levels = null;
/*      */     try {
/*  327 */       levels = Float.valueOf(this.numberFormat.parse(tag).floatValue());
/*  328 */     } catch (Exception e) {
/*  329 */       log.info("Building Levels " + tag);
/*  330 */       return Float.valueOf(1.0F);
/*      */     } 
/*  332 */     if (levels != null) {
/*  333 */       float height = levels.floatValue() * 3.0F;
/*  334 */       if (height < 1000.0F && height > 0.0F)
/*  335 */         return Float.valueOf(height); 
/*      */     } 
/*  338 */     return Float.valueOf(1.0F);
/*      */   }
/*      */   
/*      */   public String[] buildFromTags(LinearRing2D shape, HashMap<String, String> tags) throws Exception {
/*  343 */     float minHeight = 0.0F;
/*  344 */     float height = 0.0F;
/*  345 */     float roofHeight = 0.0F;
/*  346 */     if (tags.containsKey("min_height"))
/*  347 */       minHeight = OsmUtils.parseHeight(tags.get("min_height")).floatValue(); 
/*  349 */     if (tags.containsKey("height"))
/*  350 */       height = OsmUtils.parseHeight(tags.get("height")).floatValue(); 
/*  354 */     String roofColor = "gray";
/*  355 */     if (tags.containsKey("roof:colour"))
/*  356 */       roofColor = tags.get("roof:colour"); 
/*  358 */     if (tags.containsKey("roof:color"))
/*  359 */       roofColor = tags.get("roof:color"); 
/*  361 */     String wallColor = null;
/*  362 */     if (tags.containsKey("building:colour"))
/*  363 */       wallColor = tags.get("building:colour"); 
/*  365 */     if (tags.containsKey("building:color"))
/*  366 */       wallColor = tags.get("building:color"); 
/*  368 */     String wallTexture = "none";
/*  369 */     String roofTexture = "none";
/*  370 */     if (tags.containsKey("roof:material"))
/*  371 */       roofTexture = tags.get("roof:material"); 
/*  373 */     if (tags.containsKey("building:material"))
/*  374 */       wallTexture = tags.get("building:material"); 
/*  376 */     String roofShape = "flat";
/*  377 */     if (tags.containsKey("roof:shape"))
/*  378 */       roofShape = tags.get("roof:shape"); 
/*  380 */     return get3DModel(shape, minHeight, height, roofHeight, roofShape, wallColor, wallTexture, roofColor, roofTexture, "/Users/tony/Desktop/Test");
/*      */   }
/*      */   
/*      */   public String[] get3DModel(LinearRing2D building, double minHeight, double height, double roofHeight, String roofType, String wallColour, String wallTexture, String roofColour, String roofTexture, String path) {
/*  397 */     String[] objects = new String[2];
/*  398 */     objects[0] = generateWall(building, minHeight, height, roofHeight, wallColour, wallTexture, new File(path));
/*  399 */     objects[1] = generateRoof(building, height, roofHeight, roofType, roofColour, roofTexture, new File(path));
/*  400 */     return objects;
/*      */   }
/*      */   
/*      */   public String generateWall(LinearRing2D building, double minHeight, double h, double roofHeight, String wallColour, String wallTexture, File path) {
/*  412 */     double textureHeight = 9.0D;
/*  413 */     int sheetWidth = 256;
/*  414 */     int sheetHeight = 256;
/*  415 */     this.height = Double.valueOf(h - roofHeight);
/*  417 */     if ("glass".equals(wallColour)) {
/*  418 */       wallColour = null;
/*  419 */       wallTexture = "glass";
/*      */     } 
/*  426 */     ArrayList<Color> wallColor = (wallColour != null && !wallColour.equals("none") && !wallColour.equals("transparent")) ? getColors(wallColour) : null;
/*  431 */     String textName = ("w_" + wallTexture).hashCode() + "";
/*  432 */     if (wallColor != null && wallColor.size() > 1)
/*  433 */       textName = ("w_" + wallTexture + "_" + wallColour).hashCode() + ""; 
/*  436 */     if (!GeneratorStore.getGeneratorStore().isUseLightingForColours())
/*  437 */       textName = ("w_" + wallTexture + wallColour).hashCode() + ""; 
/*  440 */     boolean useDDS = GeneratorStore.getGeneratorStore().isCompressTextures();
/*  441 */     File textureFile = new File(path, textName + (useDDS ? ".dds" : ".png"));
/*  444 */     if (!textureFile.exists()) {
/*      */       TexturePaint wallPaint;
/*  446 */       BufferedImage bImg = new BufferedImage(sheetWidth, sheetHeight, (wallColour != null && wallColour.equals("transparent")) ? 2 : 1);
/*  448 */       Graphics2D graphicsContext = bImg.createGraphics();
/*  450 */       if (wallTexture.equals("glass")) {
/*  451 */         wallPaint = getTexturePaint("glass/glass4.jpg", sheetHeight);
/*  453 */       } else if (wallTexture.equals("brick")) {
/*  454 */         wallPaint = getTexturePaint("brick/brick3.jpg", sheetHeight);
/*  455 */       } else if (wallTexture.equals("stone")) {
/*  456 */         wallPaint = getTexturePaint("stone/stone4.jpg", sheetHeight);
/*  457 */       } else if (wallTexture.equals("concrete")) {
/*  458 */         wallPaint = getTexturePaint("concrete/concrete1.png", sheetHeight);
/*  459 */       } else if (wallTexture.equals("metal")) {
/*  460 */         wallPaint = getTexturePaint("stone/stone4.jpg", sheetHeight);
/*  461 */         if (wallColour == null || wallColour.length() == 0)
/*  462 */           wallColour = "white"; 
/*      */       } else {
/*  466 */         wallPaint = getTexturePaint("concrete/concrete1.png", sheetHeight);
/*      */       } 
/*  473 */       if (wallPaint != null) {
/*  474 */         graphicsContext.setPaint(wallPaint);
/*  475 */         graphicsContext.fillRect(0, 0, sheetWidth, sheetHeight);
/*      */       } 
/*  481 */       if (wallColor != null && (wallColor.size() > 1 || !GeneratorStore.getGeneratorStore().isUseLightingForColours())) {
/*  482 */         int j = wallColor.size() * 4;
/*  483 */         double rowHeight = (sheetHeight / j);
/*  484 */         int s = 0;
/*      */         double d1;
/*  485 */         for (d1 = 0.0D; d1 <= sheetHeight; d1 += rowHeight) {
/*  486 */           Color paint = new Color(((Color)wallColor.get(s)).getRed(), ((Color)wallColor.get(s)).getGreen(), ((Color)wallColor.get(s)).getBlue(), 120);
/*  487 */           graphicsContext.setPaint(paint);
/*  488 */           graphicsContext.fillRect(0, (int)d1, sheetWidth, (int)rowHeight);
/*  489 */           s++;
/*  490 */           if (s >= wallColor.size())
/*  491 */             s = 0; 
/*      */         } 
/*      */       } 
/*  496 */       final File outImage = new File(path, textName + ".png");
/*      */       try {
/*  498 */         if (ImageIO.write(bImg, "png", outImage));
/*  502 */       } catch (IOException e) {
/*  504 */         e.printStackTrace();
/*      */       } 
/*  506 */       final String ddsPath = DDSTool.getDDSToolPath();
/*  507 */       if (GeneratorStore.getGeneratorStore().isCompressTextures()) {
/*  508 */         Thread thread = new Thread() {
/*      */             public void run() {
/*  510 */               Runtime runtime = Runtime.getRuntime();
/*      */               try {
/*  512 */                 Process p = runtime.exec(new String[] { this.val$ddsPath, "--png2dxt", "arg1", "arg2", this.val$outImage.getAbsolutePath(), this.val$outImage.getAbsolutePath().replaceAll(".png", ".dds") });
/*  514 */                 p.waitFor();
/*  515 */                 outImage.delete();
/*  516 */               } catch (Exception e) {
/*  517 */                 e.printStackTrace();
/*      */               } 
/*      */             }
/*      */           };
/*  521 */         thread.start();
/*      */       } 
/*      */     } 
/*  526 */     Point2D centroid = GeomUtils.getPolygonCenter(building);
/*  528 */     this.vertexes.clear();
/*  529 */     this.triangles.clear();
/*  533 */     building = GeomUtils.setClockwise(building);
/*  536 */     for (int x = 0; x < building.vertexNumber() - 1; x++) {
/*  537 */       Point2D vertex1 = building.vertex(x);
/*  538 */       Point2D vertex2 = building.vertex(x + 1);
/*  542 */       Vector3D point1 = get3DPoint(centroid, vertex1, minHeight);
/*  543 */       Vector3D point2 = get3DPoint(centroid, vertex2, minHeight);
/*  544 */       Vector3D point3 = get3DPoint(centroid, vertex1, this.height.doubleValue());
/*  545 */       Vector3D normal = calculateNormal(point3, point2, point1);
/*  546 */       double distance = distance(vertex1, vertex2).doubleValue();
/*  548 */       Point2D uv1 = new Point2D(0.0D, 0.0D);
/*  549 */       Point2D uv2 = new Point2D(distance / textureHeight, 0.0D);
/*  550 */       Point2D uv3 = new Point2D(0.0D, this.height.doubleValue() / textureHeight);
/*  551 */       ObjPoint objPoint1 = new ObjPoint(point1.x(), point1.y(), point1.z(), normal.x(), normal.y(), normal.z(), uv1.x(), uv1.y());
/*  552 */       ObjPoint objPoint2 = new ObjPoint(point2.x(), point2.y(), point2.z(), normal.x(), normal.y(), normal.z(), uv2.x(), uv2.y());
/*  553 */       ObjPoint objPoint3 = new ObjPoint(point3.x(), point3.y(), point3.z(), normal.x(), normal.y(), normal.z(), uv3.x(), uv3.y());
/*  555 */       this.vertexes.add(objPoint1);
/*  556 */       this.vertexes.add(objPoint2);
/*  557 */       this.vertexes.add(objPoint3);
/*  558 */       ObjTriangle tri1 = new ObjTriangle(objPoint1, objPoint2, objPoint3);
/*  559 */       this.triangles.add(tri1);
/*  565 */       point1 = get3DPoint(centroid, vertex2, minHeight);
/*  566 */       point2 = get3DPoint(centroid, vertex2, this.height.doubleValue());
/*  567 */       point3 = get3DPoint(centroid, vertex1, this.height.doubleValue());
/*  568 */       normal = calculateNormal(point3, point2, point1);
/*  570 */       uv1 = new Point2D(distance / textureHeight, 0.0D);
/*  571 */       uv2 = new Point2D(distance / textureHeight, this.height.doubleValue() / textureHeight);
/*  572 */       uv3 = new Point2D(0.0D, this.height.doubleValue() / textureHeight);
/*  574 */       objPoint1 = new ObjPoint(point1.x(), point1.y(), point1.z(), normal.x(), normal.y(), normal.z(), uv1.x(), uv1.y());
/*  575 */       objPoint2 = new ObjPoint(point2.x(), point2.y(), point2.z(), normal.x(), normal.y(), normal.z(), uv2.x(), uv2.y());
/*  576 */       objPoint3 = new ObjPoint(point3.x(), point3.y(), point3.z(), normal.x(), normal.y(), normal.z(), uv3.x(), uv3.y());
/*  578 */       this.vertexes.add(objPoint1);
/*  579 */       this.vertexes.add(objPoint2);
/*  580 */       this.vertexes.add(objPoint3);
/*  581 */       tri1 = new ObjTriangle(objPoint1, objPoint2, objPoint3);
/*  582 */       this.triangles.add(tri1);
/*      */     } 
/*  587 */     StringBuilder builder = new StringBuilder();
/*  589 */     builder.append("I\n800\nOBJ\n\nTEXTURE\t\t" + textName + (useDDS ? ".dds" : ".png") + "\n");
/*  596 */     builder.append(String.format("POINT_COUNTS\t%d %d %d %d\n\n", new Object[] { Integer.valueOf(this.vertexes.size()), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(this.triangles.size() * 3) }));
/*  598 */     HashMap<ObjPoint, Integer> objPoints = new HashMap<>();
/*  600 */     int index = 0;
/*  601 */     for (ObjPoint item : this.vertexes) {
/*  602 */       objPoints.put(item, Integer.valueOf(index));
/*  603 */       index++;
/*  604 */       builder.append(String.format("VT\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", new Object[] { this.numberFormat.format(item.x), this.numberFormat.format(item.y), this.numberFormat.format(item.z), this.numberFormat.format(item.nx), this.numberFormat.format(item.ny), this.numberFormat.format(item.nz), this.numberFormat.format(item.u), this.numberFormat.format(item.v) }));
/*      */     } 
/*  616 */     builder.append("\n\n");
/*  617 */     List<Integer> triPoints = new ArrayList<>();
/*  618 */     for (ObjTriangle triangle : this.triangles) {
/*  619 */       triPoints.add(objPoints.get(triangle.a));
/*  620 */       triPoints.add(objPoints.get(triangle.b));
/*  621 */       triPoints.add(objPoints.get(triangle.c));
/*      */     } 
/*  624 */     int count = triPoints.size();
/*  625 */     int remainer = count % 10;
/*  626 */     count -= remainer;
/*  627 */     index = 0;
/*  628 */     int idx10count = count / 10;
/*      */     int i;
/*  629 */     for (i = 0; i < idx10count; i++) {
/*  630 */       builder.append("IDX10 ");
/*  631 */       for (int z = index; z < index + 10; z++)
/*  632 */         builder.append((new StringBuilder()).append(triPoints.get(z)).append(" ").toString()); 
/*  634 */       builder.append("\n");
/*  635 */       index += 10;
/*      */     } 
/*  637 */     for (i = 0; i < remainer; i++) {
/*  638 */       builder.append("IDX " + triPoints.get(index) + "\n");
/*  639 */       index++;
/*      */     } 
/*  645 */     builder.append("\n\n");
/*  646 */     int lod = GeneratorStore.getGeneratorStore().getFacadeLod();
/*  648 */     if (this.height.doubleValue() < 5.0D)
/*  649 */       lod = 5000; 
/*  651 */     if (this.height.doubleValue() > 50.0D)
/*  652 */       lod = 25000; 
/*  654 */     if (this.height.doubleValue() > 100.0D)
/*  655 */       lod = 30000; 
/*  660 */     builder.append("ATTR_LOD 0 " + lod + ".000000\n");
/*  662 */     if (wallTexture.equals("glass"))
/*  663 */       builder.append("ATTR_shiny_rat 4.0\n"); 
/*  666 */     if (wallColor != null && wallColor.size() == 1 && GeneratorStore.getGeneratorStore().isUseLightingForColours()) {
/*  667 */       Color color = getChilledColour(wallColor);
/*  668 */       builder.append("ATTR_diffuse_rgb " + this.numberFormat.format(color.getRed() / 255.0D) + " " + this.numberFormat.format(color.getGreen() / 255.0D) + " " + this.numberFormat.format(color.getBlue() / 255.0D) + "\n");
/*      */     } 
/*  674 */     builder.append("TRIS 0 " + triPoints.size() + "\n");
/*  678 */     builder.append("\n# Created by World2XPlane 0.7.4\n");
/*  680 */     return builder.toString();
/*      */   }
/*      */   
/*      */   private Color getChilledColour(ArrayList<Color> wallColor) {
/*  684 */     Color color = wallColor.get(0);
/*  685 */     if (color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0)
/*  687 */       return color; 
/*  690 */     if (color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255)
/*  692 */       return new Color(230, 230, 230); 
/*  697 */     if (color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0)
/*  699 */       return new Color(144, 51, 36); 
/*  702 */     if (color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 255)
/*  704 */       return new Color(94, 152, 211); 
/*  707 */     color = generatePastelColor(color);
/*  708 */     return color;
/*      */   }
/*      */   
/*      */   public static Color generatePastelColor(Color mix) {
/*  712 */     float correctionFactor = 0.4F;
/*  713 */     float red = (255 - mix.getRed()) * correctionFactor + mix.getRed();
/*  714 */     float green = (255 - mix.getGreen()) * correctionFactor + mix.getGreen();
/*  715 */     float blue = (255 - mix.getBlue()) * correctionFactor + mix.getBlue();
/*  716 */     return new Color((int)red, (int)green, (int)blue);
/*      */   }
/*      */   
/*      */   public String generateRoof(LinearRing2D building, double roofBase, double roofHeight, String roofType, String roofColour, String roofTexture, File path) {
/*  726 */     double textureHeight = 40.0D;
/*  727 */     int sheetWidth = 256;
/*  728 */     int sheetHeight = 256;
/*  730 */     if (roofHeight == 0.0D && (!"flat".equals(roofType) || roofType == null))
/*  731 */       roofHeight = 3.0D; 
/*  734 */     this.height = Double.valueOf(roofBase - roofHeight);
/*  735 */     this.roofHeight = Double.valueOf(roofBase);
/*  739 */     String textName = ("r_" + roofTexture).hashCode() + "";
/*  740 */     boolean useDDS = GeneratorStore.getGeneratorStore().isCompressTextures();
/*  741 */     File textureFile = new File(path, textName + (useDDS ? ".dds" : ".png"));
/*  742 */     ArrayList<Color> roofColors = (roofColour != null && !roofColour.equals("none") && !roofColour.equals("transparent")) ? getColors(roofColour) : null;
/*  745 */     if (!GeneratorStore.getGeneratorStore().isUseLightingForColours())
/*  746 */       textName = ("r_" + roofTexture + roofColour).hashCode() + ""; 
/*  748 */     if (!textureFile.exists()) {
/*      */       TexturePaint roofPaint;
/*  750 */       BufferedImage bImg = new BufferedImage(sheetWidth, sheetHeight, 1);
/*  751 */       Graphics2D graphicsContext = bImg.createGraphics();
/*  753 */       if (roofTexture.equals("glass")) {
/*  754 */         roofPaint = getTexturePaint("glass/glass4.jpg", sheetHeight);
/*  756 */       } else if (roofTexture.equals("brick")) {
/*  757 */         roofPaint = getTexturePaint("brick/brick3.jpg", sheetHeight);
/*  760 */       } else if (roofTexture.equals("stone")) {
/*  761 */         roofPaint = getTexturePaint("stone/stone4.jpg", sheetHeight);
/*  762 */       } else if (roofTexture.equals("concrete")) {
/*  763 */         roofPaint = getTexturePaint("concrete/concrete.png", sheetHeight);
/*  764 */       } else if (roofTexture.equals("metal")) {
/*  765 */         roofPaint = getTexturePaint("concrete/concrete.png", sheetHeight);
/*      */       } else {
/*  768 */         roofPaint = getTexturePaint("roof/concrete.png", sheetHeight);
/*      */       } 
/*  776 */       if (roofPaint != null) {
/*  777 */         graphicsContext.setPaint(roofPaint);
/*  778 */         graphicsContext.fillRect(0, 0, sheetWidth, sheetHeight);
/*  780 */         graphicsContext.setColor(Color.white);
/*  781 */         graphicsContext.fillRect(0, sheetHeight - 1, 1, 1);
/*      */       } 
/*  787 */       if (roofColour != null && !roofColour.equals("none") && !roofColour.equals("transparent") && !GeneratorStore.getGeneratorStore().isUseLightingForColours()) {
/*  788 */         ArrayList<Color> wallColor = getColors(roofColour);
/*  789 */         if (wallColor != null) {
/*  790 */           int i = wallColor.size() * 4;
/*  791 */           double rowHeight = (sheetHeight / i);
/*  792 */           int s = 0;
/*      */           double d1;
/*  793 */           for (d1 = 0.0D; d1 <= sheetHeight; d1 += rowHeight) {
/*  794 */             Color paint = new Color(((Color)wallColor.get(s)).getRed(), ((Color)wallColor.get(s)).getGreen(), ((Color)wallColor.get(s)).getBlue(), 120);
/*  795 */             graphicsContext.setPaint(paint);
/*  796 */             graphicsContext.fillRect(0, (int)d1, sheetWidth, (int)rowHeight);
/*  797 */             s++;
/*  798 */             if (s >= wallColor.size())
/*  799 */               s = 0; 
/*      */           } 
/*      */         } 
/*      */       } 
/*  805 */       final File outImage = new File(path, textName + ".png");
/*      */       try {
/*  807 */         if (ImageIO.write(bImg, "png", outImage));
/*  810 */       } catch (IOException e) {
/*  812 */         e.printStackTrace();
/*      */       } 
/*  814 */       final String ddsPath = DDSTool.getDDSToolPath();
/*  815 */       if (GeneratorStore.getGeneratorStore().isCompressTextures()) {
/*  816 */         Thread thread = new Thread() {
/*      */             public void run() {
/*  818 */               Runtime runtime = Runtime.getRuntime();
/*      */               try {
/*  820 */                 Process p = runtime.exec(new String[] { this.val$ddsPath, "--png2dxt", "arg1", "arg2", this.val$outImage.getAbsolutePath(), this.val$outImage.getAbsolutePath().replaceAll(".png", ".dds") });
/*  822 */                 p.waitFor();
/*  823 */                 outImage.delete();
/*  824 */               } catch (Exception e) {
/*  825 */                 e.printStackTrace();
/*      */               } 
/*      */             }
/*      */           };
/*  829 */         thread.start();
/*      */       } 
/*      */     } 
/*  834 */     Point2D centroid = GeomUtils.getPolygonCenter(building);
/*  837 */     this.vertexes.clear();
/*  838 */     this.triangles.clear();
/*  839 */     building = GeomUtils.setCounterClockwise(building);
/*  841 */     boolean flat = false;
/*  842 */     if (roofType.equals("pyramidal") || roofType.equals("round") || roofType.equals("mansard")) {
/*  843 */       PyramidRoof pyramidRoof = new PyramidRoof();
/*  844 */       pyramidRoof.makeRoof(this.vertexes, this.triangles, building, centroid, this.height.doubleValue(), roofHeight);
/*  845 */     } else if (roofType.equals("onion")) {
/*  846 */       OnionRoof onionRoof = new OnionRoof();
/*  847 */       onionRoof.makeRoof(this.vertexes, this.triangles, building, centroid, this.height.doubleValue(), roofHeight);
/*  848 */     } else if (roofType.equals("dome") || roofType.equals("domed")) {
/*  849 */       DomedRoof domedRoof = new DomedRoof();
/*  850 */       domedRoof.makeRoof(this.vertexes, this.triangles, building, centroid, this.height.doubleValue(), roofHeight);
/*      */     } else {
/*  852 */       flat = true;
/*  853 */       FlatRoof flatRoof = new FlatRoof();
/*  854 */       flatRoof.makeRoof(this.vertexes, this.triangles, building, centroid, this.height.doubleValue(), roofHeight);
/*      */     } 
/*  858 */     StringBuilder builder = new StringBuilder();
/*  860 */     builder.append("I\n800\nOBJ\n\n");
/*  865 */     if (!flat)
/*  866 */       builder.append("TEXTURE\t\t" + textName + (useDDS ? ".dds" : ".png") + "\n"); 
/*  869 */     builder.append(String.format("POINT_COUNTS\t%d %d %d %d\n\n", new Object[] { Integer.valueOf(this.vertexes.size()), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(this.triangles.size() * 3) }));
/*  871 */     HashMap<ObjPoint, Integer> objPoints = new HashMap<>();
/*  873 */     int index = 0;
/*  874 */     for (ObjPoint item : this.vertexes) {
/*  875 */       objPoints.put(item, Integer.valueOf(index));
/*  876 */       index++;
/*  877 */       builder.append(String.format("VT\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", new Object[] { this.numberFormat.format(item.x), this.numberFormat.format(item.y), this.numberFormat.format(item.z), this.numberFormat.format(item.nx), this.numberFormat.format(item.ny), this.numberFormat.format(item.nz), this.numberFormat.format(item.u), this.numberFormat.format(item.v) }));
/*      */     } 
/*  889 */     builder.append("\n\n");
/*  890 */     List<Integer> triPoints = new ArrayList<>();
/*  891 */     for (ObjTriangle triangle : this.triangles) {
/*  892 */       triPoints.add(objPoints.get(triangle.a));
/*  893 */       triPoints.add(objPoints.get(triangle.b));
/*  894 */       triPoints.add(objPoints.get(triangle.c));
/*      */     } 
/*  897 */     int count = triPoints.size();
/*  898 */     int remainer = count % 10;
/*  899 */     count -= remainer;
/*  900 */     index = 0;
/*  901 */     int idx10count = count / 10;
/*      */     int x;
/*  902 */     for (x = 0; x < idx10count; x++) {
/*  903 */       builder.append("IDX10 ");
/*  904 */       for (int z = index; z < index + 10; z++)
/*  905 */         builder.append((new StringBuilder()).append(triPoints.get(z)).append(" ").toString()); 
/*  907 */       builder.append("\n");
/*  908 */       index += 10;
/*      */     } 
/*  910 */     for (x = 0; x < remainer; x++) {
/*  911 */       builder.append("IDX " + triPoints.get(index) + "\n");
/*  912 */       index++;
/*      */     } 
/*  918 */     builder.append("\n\n");
/*  919 */     int lod = GeneratorStore.getGeneratorStore().getFacadeLod();
/*  920 */     if (roofHeight < 5.0D)
/*  921 */       lod = 5000; 
/*  923 */     if (roofHeight > 100.0D)
/*  924 */       lod = 16000; 
/*  926 */     if (roofHeight > 200.0D)
/*  927 */       lod = 20000; 
/*  930 */     builder.append("ATTR_LOD 0 " + lod + ".000000\n");
/*  932 */     if (roofTexture.equals("glass"))
/*  933 */       builder.append("ATTR_shiny_rat 3.0\n"); 
/*  935 */     if (roofColors != null && roofColors.size() == 1 && GeneratorStore.getGeneratorStore().isUseLightingForColours()) {
/*  936 */       Color color = getChilledColour(roofColors);
/*  937 */       builder.append("ATTR_diffuse_rgb " + this.numberFormat.format(color.getRed() / 255.0D) + " " + this.numberFormat.format(color.getGreen() / 255.0D) + " " + this.numberFormat.format(color.getBlue() / 255.0D) + "\n");
/*  941 */     } else if (flat) {
/*  942 */       Color color = generatePastelColor(Color.gray);
/*  943 */       builder.append("ATTR_diffuse_rgb " + this.numberFormat.format(color.getRed() / 255.0D) + " " + this.numberFormat.format(color.getGreen() / 255.0D) + " " + this.numberFormat.format(color.getBlue() / 255.0D) + "\n");
/*      */     } 
/*  948 */     builder.append("TRIS 0 " + triPoints.size() + "\n");
/*  949 */     builder.append("\n# Created by World2XPlane 0.7.4\n");
/*  950 */     return builder.toString();
/*      */   }
/*      */   
/*      */   public static Vector3D get3DPoint(Point2D centroid, Point2D point, double height) {
/*  956 */     Point2D x1 = new Point2D(GeomUtils.merc_x(centroid.x()), GeomUtils.merc_y(centroid.y()));
/*  957 */     Point2D x2 = new Point2D(GeomUtils.merc_x(point.x()), GeomUtils.merc_y(point.y()));
/*  959 */     double angle = (new LineSegment2D(x1, x2)).horizontalAngle();
/*  963 */     double distance = distance(centroid, point).doubleValue();
/*  968 */     double dx = GeomUtils.distanceInMeters(new LineSegment2D(centroid.x(), centroid.y(), point.x(), centroid.y()));
/*  969 */     double dy = GeomUtils.distanceInMeters(new LineSegment2D(centroid.x(), centroid.y(), centroid.x(), point.y()));
/*  970 */     if (Double.isNaN(dx))
/*  971 */       dx = 0.0D; 
/*  973 */     if (Double.isNaN(dy))
/*  974 */       dy = 0.0D; 
/*  976 */     if (point.x() < centroid.x())
/*  977 */       dx = -dx; 
/*  979 */     if (point.y() > centroid.y())
/*  980 */       dy = -dy; 
/*  984 */     return new Vector3D(dx, height, dy);
/*      */   }
/*      */   
/*      */   public static Vector3D get3DPoint(Point2D centroid, Point2D point, double height, double scale) {
/*  989 */     Point2D x1 = new Point2D(GeomUtils.merc_x(centroid.x()), GeomUtils.merc_y(centroid.y()));
/*  990 */     Point2D x2 = new Point2D(GeomUtils.merc_x(point.x()), GeomUtils.merc_y(point.y()));
/*  992 */     double angle = (new LineSegment2D(x1, x2)).horizontalAngle();
/*  996 */     double distance = distance(centroid, point).doubleValue();
/*  997 */     distance *= scale;
/* 1002 */     double dx = GeomUtils.distanceInMeters(new LineSegment2D(centroid.x(), centroid.y(), point.x(), centroid.y())) * scale;
/* 1003 */     double dy = GeomUtils.distanceInMeters(new LineSegment2D(centroid.x(), centroid.y(), centroid.x(), point.y())) * scale;
/* 1004 */     if (Double.isNaN(dx))
/* 1005 */       dx = 0.0D; 
/* 1007 */     if (Double.isNaN(dy))
/* 1008 */       dy = 0.0D; 
/* 1010 */     if (point.x() < centroid.x())
/* 1011 */       dx = -dx; 
/* 1013 */     if (point.y() > centroid.y())
/* 1014 */       dy = -dy; 
/* 1018 */     return new Vector3D(dx, height, dy);
/*      */   }
/*      */   
/*      */   public static Vector3D calculateNormal(Vector3D one, Vector3D two, Vector3D three) {
/* 1026 */     Vector3D edge1 = (new Vector3D(one.x(), one.y(), one.z())).sub(two);
/* 1027 */     Vector3D edge2 = (new Vector3D(two.x(), two.y(), two.z())).sub(three);
/* 1029 */     Vector3D crsProd = edge1.cross(edge2);
/* 1031 */     Vector3D normal = crsProd.normalize();
/* 1033 */     return normal;
/*      */   }
/*      */   
/*      */   public static Double distance(Point2D p1, Point2D p2) {
/* 1044 */     double a = 6378137.0D;
/* 1045 */     double b = 6356752.3142D;
/* 1046 */     double f = 0.0033528106647474805D;
/* 1048 */     double l = FastMath.toRadians(p2.x() - p1.x());
/* 1049 */     double u1 = FastMath.atan((1.0D - f) * FastMath.tan(FastMath.toRadians(p1.y())));
/* 1050 */     double u2 = FastMath.atan((1.0D - f) * FastMath.tan(FastMath.toRadians(p2.y())));
/* 1051 */     double sinU1 = FastMath.sin(u1), cosU1 = FastMath.cos(u1);
/* 1052 */     double sinU2 = FastMath.sin(u2), cosU2 = FastMath.cos(u2);
/* 1053 */     double lambda = l, lambdaP = 6.283185307179586D;
/* 1054 */     double iterLimit = 20.0D;
/* 1055 */     double cosSqAlpha = 0.0D;
/* 1056 */     double cos2SigmaM = 0.0D;
/* 1057 */     double cosSigma = 0.0D;
/* 1058 */     double sinSigma = 0.0D;
/* 1059 */     double sigma = 0.0D;
/* 1060 */     while (FastMath.abs(lambda - lambdaP) > 1.0E-12D && --iterLimit > 0.0D) {
/* 1061 */       double sinLambda = FastMath.sin(lambda), cosLambda = FastMath.cos(lambda);
/* 1062 */       sinSigma = FastMath.sqrt(cosU2 * sinLambda * cosU2 * sinLambda + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
/* 1064 */       if (sinSigma == 0.0D)
/* 1065 */         return Double.valueOf(0.0D); 
/* 1067 */       cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
/* 1068 */       sigma = FastMath.atan2(sinSigma, cosSigma);
/* 1069 */       double alpha = FastMath.asin(cosU1 * cosU2 * sinLambda / sinSigma);
/* 1070 */       cosSqAlpha = FastMath.cos(alpha) * FastMath.cos(alpha);
/* 1071 */       cos2SigmaM = cosSigma - 2.0D * sinU1 * sinU2 / cosSqAlpha;
/* 1072 */       double C = f / 16.0D * cosSqAlpha * (4.0D + f * (4.0D - 3.0D * cosSqAlpha));
/* 1073 */       lambdaP = lambda;
/* 1074 */       lambda = l + (1.0D - C) * f * FastMath.sin(alpha) * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1.0D + 2.0D * cos2SigmaM * cos2SigmaM)));
/*      */     } 
/* 1077 */     if (iterLimit == 0.0D)
/* 1078 */       return null; 
/* 1080 */     double uSq = cosSqAlpha * (a * a - b * b) / b * b;
/* 1081 */     double A = 1.0D + uSq / 16384.0D * (4096.0D + uSq * (-768.0D + uSq * (320.0D - 175.0D * uSq)));
/* 1082 */     double B = uSq / 1024.0D * (256.0D + uSq * (-128.0D + uSq * (74.0D - 47.0D * uSq)));
/* 1083 */     double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4.0D * (cosSigma * (-1.0D + 2.0D * cos2SigmaM * cos2SigmaM) - B / 6.0D * cos2SigmaM * (-3.0D + 4.0D * sinSigma * sinSigma) * (-3.0D + 4.0D * cos2SigmaM * cos2SigmaM)));
/* 1085 */     double s = b * A * (sigma - deltaSigma);
/* 1086 */     double d = s / 1000.0D;
/* 1087 */     return Double.valueOf(d * 1000.0D);
/*      */   }
/*      */   
/*      */   public TexturePaint getTexturePaint(String texture, double bandHeight) {
/* 1091 */     Image img = (new ImageIcon("./resources/textures/" + texture)).getImage();
/* 1092 */     BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), 1);
/* 1095 */     Graphics2D g2 = bImage.createGraphics();
/* 1096 */     g2.drawImage(img, (AffineTransform)null, (ImageObserver)null);
/* 1099 */     Rectangle2D tr = new Rectangle2D.Double(0.0D, 0.0D, bandHeight, bandHeight);
/* 1103 */     return new TexturePaint(bImage, tr);
/*      */   }
/*      */   
/*      */   protected Color getColor(String colorCode) {
/* 1107 */     if (colorCode == null)
/* 1108 */       return Color.white; 
/* 1113 */     if (colorCode.equals("red"))
/* 1114 */       colorCode = "#97564B"; 
/* 1116 */     if (colorCode.equals("yellow"))
/* 1117 */       colorCode = "#E5B45B"; 
/* 1119 */     if (colorCode.equals("blue"))
/* 1120 */       colorCode = "#5A8DC2"; 
/* 1123 */     if (colorCode.equals("glass"))
/* 1124 */       colorCode = "#5A8DC2"; 
/* 1128 */     colorCode = colorCode.toLowerCase().trim();
/* 1129 */     if (colorCode.equals("mustard yellow"))
/* 1130 */       colorCode = "#556b2f"; 
/* 1132 */     if (colorCode.equals("seaweed green"))
/* 1133 */       colorCode = "#197C3B"; 
/* 1135 */     if (colorCode.equals("light blue") || colorCode.equals("light_blue"))
/* 1136 */       colorCode = "#add8e6"; 
/* 1138 */     if (colorCode.equals("dark red") || colorCode.equals("dark_red"))
/* 1139 */       colorCode = "#88000E"; 
/* 1141 */     if (colorCode.equals("light orange") || colorCode.equals("light_orange"))
/* 1142 */       colorCode = "#EAA64B"; 
/* 1144 */     if (colorCode.equals("light red") || colorCode.equals("light_red"))
/* 1145 */       colorCode = "#ffc0cb"; 
/* 1147 */     if (colorCode.equals("light yellow") || colorCode.equals("light_yellow"))
/* 1148 */       colorCode = "#ffffe0"; 
/* 1150 */     if (colorCode.equals("light green") || colorCode.equals("light_green"))
/* 1151 */       colorCode = "#90ee90"; 
/* 1153 */     if (colorCode.equals("light blue") || colorCode.equals("light_blue"))
/* 1154 */       colorCode = "#add8e6"; 
/* 1156 */     if (colorCode.equals("light brown") || colorCode.equals("light_brown"))
/* 1157 */       colorCode = "#6C4E3A"; 
/* 1159 */     if (colorCode.equals("light grey") || colorCode.equals("granite"))
/* 1160 */       colorCode = "#666666"; 
/* 1162 */     if (colorCode.equals("dark_grey") || colorCode.equals("dark grey"))
/* 1163 */       colorCode = "#a9a9a9"; 
/* 1165 */     if (colorCode.equals("dark_green") || colorCode.equals("dark green"))
/* 1166 */       colorCode = "#006400"; 
/* 1168 */     if (colorCode.equals("dark_gray") || colorCode.equals("dark gray"))
/* 1169 */       colorCode = "#a9a9a9"; 
/* 1171 */     if (colorCode.equals("peach"))
/* 1172 */       colorCode = "#ffdab9"; 
/* 1174 */     if (colorCode.equals("mocassin"))
/* 1175 */       colorCode = "#deb887"; 
/* 1177 */     if (colorCode.equals("mustard"))
/* 1178 */       colorCode = "#556b2f"; 
/* 1180 */     if (colorCode.equals("iceberg"))
/* 1181 */       colorCode = "#e0ffff"; 
/* 1183 */     if (colorCode.equals("creme") || colorCode.equals("cream") || colorCode.equals("latte"))
/* 1184 */       colorCode = "#faebd7"; 
/* 1186 */     if (colorCode.equals("vanilla"))
/* 1187 */       colorCode = "#faebd7"; 
/* 1189 */     if (colorCode.equals("red carmine") || colorCode.equals("carmine"))
/* 1190 */       colorCode = "#8b0000"; 
/* 1192 */     if (colorCode.equals("maron"))
/* 1193 */       colorCode = "#800000"; 
/* 1195 */     if (colorCode.equals("buttermilk") || colorCode.equals("dark brown") || colorCode.equals("dark_brown"))
/* 1196 */       colorCode = "#d2691e"; 
/* 1200 */     Color color = null;
/* 1201 */     if (!colorCode.startsWith("#")) {
/* 1202 */       color = NamedColour.getColourByName(colorCode.trim());
/* 1203 */       if (color == null) {
/* 1204 */         color = getColorByJava(colorCode);
/* 1205 */         if (color != null)
/* 1206 */           return color; 
/*      */       } else {
/* 1209 */         return color;
/*      */       } 
/*      */     } 
/* 1213 */     if (color == null)
/*      */       try {
/* 1215 */         color = Color.decode(colorCode);
/* 1216 */         return color;
/* 1217 */       } catch (Exception e) {
/* 1220 */         if (colorCode.indexOf("#") >= 0) {
/*      */           try {
/* 1222 */             int intValue = Integer.parseInt(colorCode.substring(1), 16);
/* 1223 */             return new Color(intValue);
/* 1224 */           } catch (Exception ex) {}
/*      */         } else {
/*      */           try {
/* 1230 */             Field field = Color.class.getField(colorCode);
/* 1231 */             return (Color)field.get(null);
/* 1232 */           } catch (Exception ex) {}
/*      */         } 
/*      */       }  
/* 1241 */     if (!colorCode.startsWith("#")) {
/* 1242 */       colorCode = "#" + colorCode;
/* 1243 */       return getColor(colorCode);
/*      */     } 
/* 1247 */     log.info("Invalid colour " + colorCode + " for " + ((this.wayId != null) ? (" way http://openstreetmap.org/way/" + this.wayId) : (" http://openstreetmap.org/relation/" + this.wayId)));
/* 1249 */     return Color.gray;
/*      */   }
/*      */   
/*      */   public static Color getColorByJava(String colorName) {
/*      */     try {
/* 1255 */       Field field = Class.forName("java.awt.Color").getField(colorName);
/* 1256 */       return (Color)field.get(null);
/* 1257 */     } catch (Exception e) {
/* 1258 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected ArrayList<Color> getColors(String colorCode) {
/* 1263 */     ArrayList<Color> colors = new ArrayList<>();
/* 1264 */     List<String> colours = new ArrayList<>();
/* 1265 */     if (colorCode == null)
/* 1266 */       log.error("No colour passed in"); 
/* 1268 */     if (colorCode.contains(";")) {
/* 1269 */       for (String color : colorCode.split(";"))
/* 1270 */         colors.add(getColor(color)); 
/* 1272 */     } else if (colorCode.contains(",")) {
/* 1273 */       for (String color : colorCode.split(","))
/* 1274 */         colors.add(getColor(color)); 
/*      */     } else {
/* 1277 */       colors.add(getColor(colorCode));
/*      */     } 
/* 1279 */     return colors;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\ModelBuilder\ModelBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */