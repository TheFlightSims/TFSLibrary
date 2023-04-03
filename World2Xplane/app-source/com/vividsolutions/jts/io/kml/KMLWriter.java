/*     */ package com.vividsolutions.jts.io.kml;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.util.StringUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ 
/*     */ public class KMLWriter {
/*  37 */   public static String ALTITUDE_MODE_CLAMPTOGROUND = "clampToGround ";
/*     */   
/*  41 */   public static String ALTITUDE_MODE_RELATIVETOGROUND = "relativeToGround  ";
/*     */   
/*  45 */   public static String ALTITUDE_MODE_ABSOLUTE = "absolute";
/*     */   
/*     */   public static String writeGeometry(Geometry geometry, double z) {
/*  56 */     KMLWriter writer = new KMLWriter();
/*  57 */     writer.setZ(z);
/*  58 */     return writer.write(geometry);
/*     */   }
/*     */   
/*     */   public static String writeGeometry(Geometry geometry, double z, int precision, boolean extrude, String altitudeMode) {
/*  75 */     KMLWriter writer = new KMLWriter();
/*  76 */     writer.setZ(z);
/*  77 */     writer.setPrecision(precision);
/*  78 */     writer.setExtrude(extrude);
/*  79 */     writer.setAltitudeMode(altitudeMode);
/*  80 */     return writer.write(geometry);
/*     */   }
/*     */   
/*  83 */   private final int INDENT_SIZE = 2;
/*     */   
/*     */   private static final String COORDINATE_SEPARATOR = ",";
/*     */   
/*     */   private static final String TUPLE_SEPARATOR = " ";
/*     */   
/*  87 */   private String linePrefix = null;
/*     */   
/*  88 */   private int maxCoordinatesPerLine = 5;
/*     */   
/*  89 */   private double zVal = Double.NaN;
/*     */   
/*     */   private boolean extrude = false;
/*     */   
/*     */   private boolean tesselate;
/*     */   
/*  92 */   private String altitudeMode = null;
/*     */   
/*  93 */   private DecimalFormat numberFormatter = null;
/*     */   
/*     */   public void setLinePrefix(String linePrefix) {
/* 108 */     this.linePrefix = linePrefix;
/*     */   }
/*     */   
/*     */   public void setMaximumCoordinatesPerLine(int maxCoordinatesPerLine) {
/* 117 */     if (maxCoordinatesPerLine <= 0) {
/* 118 */       maxCoordinatesPerLine = 1;
/*     */       return;
/*     */     } 
/* 121 */     this.maxCoordinatesPerLine = maxCoordinatesPerLine;
/*     */   }
/*     */   
/*     */   public void setZ(double zVal) {
/* 131 */     this.zVal = zVal;
/*     */   }
/*     */   
/*     */   public void setExtrude(boolean extrude) {
/* 140 */     this.extrude = extrude;
/*     */   }
/*     */   
/*     */   public void setTesselate(boolean tesselate) {
/* 149 */     this.tesselate = tesselate;
/*     */   }
/*     */   
/*     */   public void setAltitudeMode(String altitudeMode) {
/* 158 */     this.altitudeMode = altitudeMode;
/*     */   }
/*     */   
/*     */   public void setPrecision(int precision) {
/* 169 */     if (precision >= 0)
/* 170 */       this.numberFormatter = createFormatter(precision); 
/*     */   }
/*     */   
/*     */   public String write(Geometry geom) {
/* 180 */     StringBuffer buf = new StringBuffer();
/* 181 */     write(geom, buf);
/* 182 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void write(Geometry geometry, Writer writer) throws IOException {
/* 193 */     writer.write(write(geometry));
/*     */   }
/*     */   
/*     */   public void write(Geometry geometry, StringBuffer buf) {
/* 203 */     writeGeometry(geometry, 0, buf);
/*     */   }
/*     */   
/*     */   private void writeGeometry(Geometry g, int level, StringBuffer buf) {
/* 207 */     String attributes = "";
/* 208 */     if (g instanceof Point) {
/* 209 */       writePoint((Point)g, attributes, level, buf);
/* 210 */     } else if (g instanceof LinearRing) {
/* 211 */       writeLinearRing((LinearRing)g, attributes, true, level, buf);
/* 212 */     } else if (g instanceof LineString) {
/* 213 */       writeLineString((LineString)g, attributes, level, buf);
/* 214 */     } else if (g instanceof Polygon) {
/* 215 */       writePolygon((Polygon)g, attributes, level, buf);
/* 216 */     } else if (g instanceof GeometryCollection) {
/* 217 */       writeGeometryCollection((GeometryCollection)g, attributes, level, buf);
/*     */     } else {
/* 220 */       throw new IllegalArgumentException("Geometry type not supported: " + g.getGeometryType());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startLine(String text, int level, StringBuffer buf) {
/* 224 */     if (this.linePrefix != null)
/* 225 */       buf.append(this.linePrefix); 
/* 226 */     buf.append(StringUtil.spaces(2 * level));
/* 227 */     buf.append(text);
/*     */   }
/*     */   
/*     */   private String geometryTag(String geometryName, String attributes) {
/* 231 */     StringBuffer buf = new StringBuffer();
/* 232 */     buf.append("<");
/* 233 */     buf.append(geometryName);
/* 234 */     if (attributes != null && attributes.length() > 0) {
/* 235 */       buf.append(" ");
/* 236 */       buf.append(attributes);
/*     */     } 
/* 238 */     buf.append(">");
/* 239 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private void writeModifiers(int level, StringBuffer buf) {
/* 244 */     if (this.extrude)
/* 245 */       startLine("<extrude>1</extrude>\n", level, buf); 
/* 247 */     if (this.tesselate)
/* 248 */       startLine("<tesselate>1</tesselate>\n", level, buf); 
/* 250 */     if (this.altitudeMode != null)
/* 251 */       startLine("<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n", level, buf); 
/*     */   }
/*     */   
/*     */   private void writePoint(Point p, String attributes, int level, StringBuffer buf) {
/* 258 */     startLine(geometryTag("Point", attributes) + "\n", level, buf);
/* 259 */     writeModifiers(level, buf);
/* 260 */     write(new Coordinate[] { p.getCoordinate() }, level + 1, buf);
/* 261 */     startLine("</Point>\n", level, buf);
/*     */   }
/*     */   
/*     */   private void writeLineString(LineString ls, String attributes, int level, StringBuffer buf) {
/* 267 */     startLine(geometryTag("LineString", attributes) + "\n", level, buf);
/* 268 */     writeModifiers(level, buf);
/* 269 */     write(ls.getCoordinates(), level + 1, buf);
/* 270 */     startLine("</LineString>\n", level, buf);
/*     */   }
/*     */   
/*     */   private void writeLinearRing(LinearRing lr, String attributes, boolean writeModifiers, int level, StringBuffer buf) {
/* 277 */     startLine(geometryTag("LinearRing", attributes) + "\n", level, buf);
/* 278 */     if (writeModifiers)
/* 278 */       writeModifiers(level, buf); 
/* 279 */     write(lr.getCoordinates(), level + 1, buf);
/* 280 */     startLine("</LinearRing>\n", level, buf);
/*     */   }
/*     */   
/*     */   private void writePolygon(Polygon p, String attributes, int level, StringBuffer buf) {
/* 285 */     startLine(geometryTag("Polygon", attributes) + "\n", level, buf);
/* 286 */     writeModifiers(level, buf);
/* 288 */     startLine("  <outerBoundaryIs>\n", level, buf);
/* 289 */     writeLinearRing((LinearRing)p.getExteriorRing(), null, false, level + 1, buf);
/* 290 */     startLine("  </outerBoundaryIs>\n", level, buf);
/* 292 */     for (int t = 0; t < p.getNumInteriorRing(); t++) {
/* 293 */       startLine("  <innerBoundaryIs>\n", level, buf);
/* 294 */       writeLinearRing((LinearRing)p.getInteriorRingN(t), null, false, level + 1, buf);
/* 295 */       startLine("  </innerBoundaryIs>\n", level, buf);
/*     */     } 
/* 298 */     startLine("</Polygon>\n", level, buf);
/*     */   }
/*     */   
/*     */   private void writeGeometryCollection(GeometryCollection gc, String attributes, int level, StringBuffer buf) {
/* 303 */     startLine("<MultiGeometry>\n", level, buf);
/* 304 */     for (int t = 0; t < gc.getNumGeometries(); t++)
/* 305 */       writeGeometry(gc.getGeometryN(t), level + 1, buf); 
/* 307 */     startLine("</MultiGeometry>\n", level, buf);
/*     */   }
/*     */   
/*     */   private void write(Coordinate[] coords, int level, StringBuffer buf) {
/* 317 */     startLine("<coordinates>", level, buf);
/* 319 */     boolean isNewLine = false;
/* 320 */     for (int i = 0; i < coords.length; i++) {
/* 321 */       if (i > 0)
/* 322 */         buf.append(" "); 
/* 325 */       if (isNewLine) {
/* 326 */         startLine("  ", level, buf);
/* 327 */         isNewLine = false;
/*     */       } 
/* 330 */       write(coords[i], buf);
/* 333 */       if ((i + 1) % this.maxCoordinatesPerLine == 0 && i < coords.length - 1) {
/* 334 */         buf.append("\n");
/* 335 */         isNewLine = true;
/*     */       } 
/*     */     } 
/* 338 */     buf.append("</coordinates>\n");
/*     */   }
/*     */   
/*     */   private void write(Coordinate p, StringBuffer buf) {
/* 342 */     write(p.x, buf);
/* 343 */     buf.append(",");
/* 344 */     write(p.y, buf);
/* 346 */     double z = p.z;
/* 348 */     if (!Double.isNaN(this.zVal))
/* 349 */       z = this.zVal; 
/* 353 */     if (!Double.isNaN(z)) {
/* 354 */       buf.append(",");
/* 355 */       write(z, buf);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void write(double num, StringBuffer buf) {
/* 360 */     if (this.numberFormatter != null) {
/* 361 */       buf.append(this.numberFormatter.format(num));
/*     */     } else {
/* 363 */       buf.append(num);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static DecimalFormat createFormatter(int precision) {
/* 378 */     DecimalFormatSymbols symbols = new DecimalFormatSymbols();
/* 379 */     symbols.setDecimalSeparator('.');
/* 380 */     DecimalFormat format = new DecimalFormat("0." + StringUtil.chars('#', precision), symbols);
/* 382 */     format.setDecimalSeparatorAlwaysShown(false);
/* 383 */     return format;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\kml\KMLWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */