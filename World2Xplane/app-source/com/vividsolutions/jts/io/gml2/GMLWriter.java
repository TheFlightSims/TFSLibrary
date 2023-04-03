/*     */ package com.vividsolutions.jts.io.gml2;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ 
/*     */ public class GMLWriter {
/*  66 */   private final String INDENT = "  ";
/*     */   
/*  68 */   private int startingIndentIndex = 0;
/*     */   
/*  70 */   private int maxCoordinatesPerLine = 10;
/*     */   
/*     */   private boolean emitNamespace = false;
/*     */   
/*     */   private boolean isRootTag = false;
/*     */   
/*  76 */   private String prefix = "gml";
/*     */   
/*  77 */   private String namespace = "http://www.opengis.net/gml";
/*     */   
/*  78 */   private String srsName = null;
/*     */   
/*  80 */   private String[] customElements = null;
/*     */   
/*     */   private static final String coordinateSeparator = ",";
/*     */   
/*     */   private static final String tupleSeparator = " ";
/*     */   
/*     */   public GMLWriter() {}
/*     */   
/*     */   public GMLWriter(boolean emitNamespace) {
/* 102 */     setNamespace(emitNamespace);
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix) {
/* 114 */     this.prefix = prefix;
/*     */   }
/*     */   
/*     */   public void setSrsName(String srsName) {
/* 129 */     this.srsName = srsName;
/*     */   }
/*     */   
/*     */   public void setNamespace(boolean emitNamespace) {
/* 141 */     this.emitNamespace = emitNamespace;
/*     */   }
/*     */   
/*     */   public void setCustomElements(String[] customElements) {
/* 158 */     this.customElements = customElements;
/*     */   }
/*     */   
/*     */   public void setStartingIndentIndex(int indent) {
/* 167 */     if (indent < 0)
/* 168 */       indent = 0; 
/* 169 */     this.startingIndentIndex = indent;
/*     */   }
/*     */   
/*     */   public void setMaxCoordinatesPerLine(int num) {
/* 178 */     if (num < 1)
/* 179 */       throw new IndexOutOfBoundsException("Invalid coordinate count per line, must be > 0"); 
/* 181 */     this.maxCoordinatesPerLine = num;
/*     */   }
/*     */   
/*     */   public String write(Geometry geom) {
/* 192 */     StringWriter writer = new StringWriter();
/*     */     try {
/* 194 */       write(geom, writer);
/* 196 */     } catch (IOException ex) {
/* 197 */       Assert.shouldNeverReachHere();
/*     */     } 
/* 199 */     return writer.toString();
/*     */   }
/*     */   
/*     */   public void write(Geometry geom, Writer writer) throws IOException {
/* 210 */     write(geom, writer, this.startingIndentIndex);
/*     */   }
/*     */   
/*     */   private void write(Geometry geom, Writer writer, int level) throws IOException {
/* 216 */     this.isRootTag = true;
/* 217 */     if (geom instanceof Point) {
/* 218 */       writePoint((Point)geom, writer, level);
/* 219 */     } else if (geom instanceof LineString) {
/* 220 */       writeLineString((LineString)geom, writer, level);
/* 221 */     } else if (geom instanceof Polygon) {
/* 222 */       writePolygon((Polygon)geom, writer, level);
/* 223 */     } else if (geom instanceof MultiPoint) {
/* 224 */       writeMultiPoint((MultiPoint)geom, writer, level);
/* 225 */     } else if (geom instanceof MultiLineString) {
/* 226 */       writeMultiLineString((MultiLineString)geom, writer, level);
/* 227 */     } else if (geom instanceof MultiPolygon) {
/* 228 */       writeMultiPolygon((MultiPolygon)geom, writer, level);
/* 229 */     } else if (geom instanceof GeometryCollection) {
/* 230 */       writeGeometryCollection((GeometryCollection)geom, writer, this.startingIndentIndex);
/*     */     } else {
/* 233 */       throw new IllegalArgumentException("Unhandled geometry type: " + geom.getGeometryType());
/*     */     } 
/* 236 */     writer.flush();
/*     */   }
/*     */   
/*     */   private void writePoint(Point p, Writer writer, int level) throws IOException {
/* 241 */     startLine(level, writer);
/* 242 */     startGeomTag("Point", (Geometry)p, writer);
/* 244 */     write(new Coordinate[] { p.getCoordinate() }, writer, level + 1);
/* 246 */     startLine(level, writer);
/* 247 */     endGeomTag("Point", writer);
/*     */   }
/*     */   
/*     */   private void writeLineString(LineString ls, Writer writer, int level) throws IOException {
/* 253 */     startLine(level, writer);
/* 254 */     startGeomTag("LineString", (Geometry)ls, writer);
/* 256 */     write(ls.getCoordinates(), writer, level + 1);
/* 258 */     startLine(level, writer);
/* 259 */     endGeomTag("LineString", writer);
/*     */   }
/*     */   
/*     */   private void writeLinearRing(LinearRing lr, Writer writer, int level) throws IOException {
/* 265 */     startLine(level, writer);
/* 266 */     startGeomTag("LinearRing", (Geometry)lr, writer);
/* 268 */     write(lr.getCoordinates(), writer, level + 1);
/* 270 */     startLine(level, writer);
/* 271 */     endGeomTag("LinearRing", writer);
/*     */   }
/*     */   
/*     */   private void writePolygon(Polygon p, Writer writer, int level) throws IOException {
/* 276 */     startLine(level, writer);
/* 277 */     startGeomTag("Polygon", (Geometry)p, writer);
/* 279 */     startLine(level + 1, writer);
/* 280 */     startGeomTag("outerBoundaryIs", null, writer);
/* 282 */     writeLinearRing((LinearRing)p.getExteriorRing(), writer, level + 2);
/* 284 */     startLine(level + 1, writer);
/* 285 */     endGeomTag("outerBoundaryIs", writer);
/* 287 */     for (int t = 0; t < p.getNumInteriorRing(); t++) {
/* 288 */       startLine(level + 1, writer);
/* 289 */       startGeomTag("innerBoundaryIs", null, writer);
/* 291 */       writeLinearRing((LinearRing)p.getInteriorRingN(t), writer, level + 2);
/* 293 */       startLine(level + 1, writer);
/* 294 */       endGeomTag("innerBoundaryIs", writer);
/*     */     } 
/* 297 */     startLine(level, writer);
/* 298 */     endGeomTag("Polygon", writer);
/*     */   }
/*     */   
/*     */   private void writeMultiPoint(MultiPoint mp, Writer writer, int level) throws IOException {
/* 303 */     startLine(level, writer);
/* 304 */     startGeomTag("MultiPoint", (Geometry)mp, writer);
/* 306 */     for (int t = 0; t < mp.getNumGeometries(); t++) {
/* 307 */       startLine(level + 1, writer);
/* 308 */       startGeomTag("pointMember", null, writer);
/* 310 */       writePoint((Point)mp.getGeometryN(t), writer, level + 2);
/* 312 */       startLine(level + 1, writer);
/* 313 */       endGeomTag("pointMember", writer);
/*     */     } 
/* 315 */     startLine(level, writer);
/* 316 */     endGeomTag("MultiPoint", writer);
/*     */   }
/*     */   
/*     */   private void writeMultiLineString(MultiLineString mls, Writer writer, int level) throws IOException {
/* 321 */     startLine(level, writer);
/* 322 */     startGeomTag("MultiLineString", (Geometry)mls, writer);
/* 324 */     for (int t = 0; t < mls.getNumGeometries(); t++) {
/* 325 */       startLine(level + 1, writer);
/* 326 */       startGeomTag("lineStringMember", null, writer);
/* 328 */       writeLineString((LineString)mls.getGeometryN(t), writer, level + 2);
/* 330 */       startLine(level + 1, writer);
/* 331 */       endGeomTag("lineStringMember", writer);
/*     */     } 
/* 333 */     startLine(level, writer);
/* 334 */     endGeomTag("MultiLineString", writer);
/*     */   }
/*     */   
/*     */   private void writeMultiPolygon(MultiPolygon mp, Writer writer, int level) throws IOException {
/* 339 */     startLine(level, writer);
/* 340 */     startGeomTag("MultiPolygon", (Geometry)mp, writer);
/* 342 */     for (int t = 0; t < mp.getNumGeometries(); t++) {
/* 343 */       startLine(level + 1, writer);
/* 344 */       startGeomTag("polygonMember", null, writer);
/* 346 */       writePolygon((Polygon)mp.getGeometryN(t), writer, level + 2);
/* 348 */       startLine(level + 1, writer);
/* 349 */       endGeomTag("polygonMember", writer);
/*     */     } 
/* 351 */     startLine(level, writer);
/* 352 */     endGeomTag("MultiPolygon", writer);
/*     */   }
/*     */   
/*     */   private void writeGeometryCollection(GeometryCollection gc, Writer writer, int level) throws IOException {
/* 357 */     startLine(level, writer);
/* 358 */     startGeomTag("MultiGeometry", (Geometry)gc, writer);
/* 360 */     for (int t = 0; t < gc.getNumGeometries(); t++) {
/* 361 */       startLine(level + 1, writer);
/* 362 */       startGeomTag("geometryMember", null, writer);
/* 364 */       write(gc.getGeometryN(t), writer, level + 2);
/* 366 */       startLine(level + 1, writer);
/* 367 */       endGeomTag("geometryMember", writer);
/*     */     } 
/* 369 */     startLine(level, writer);
/* 370 */     endGeomTag("MultiGeometry", writer);
/*     */   }
/*     */   
/*     */   private void write(Coordinate[] coords, Writer writer, int level) throws IOException {
/* 386 */     startLine(level, writer);
/* 387 */     startGeomTag("coordinates", null, writer);
/* 389 */     int dim = 2;
/* 391 */     if (coords.length > 0 && 
/* 392 */       !Double.isNaN((coords[0]).z))
/* 393 */       dim = 3; 
/* 396 */     boolean isNewLine = true;
/* 397 */     for (int i = 0; i < coords.length; i++) {
/* 398 */       if (isNewLine) {
/* 399 */         startLine(level + 1, writer);
/* 400 */         isNewLine = false;
/*     */       } 
/* 402 */       if (dim == 2) {
/* 403 */         writer.write("" + (coords[i]).x);
/* 404 */         writer.write(",");
/* 405 */         writer.write("" + (coords[i]).y);
/* 406 */       } else if (dim == 3) {
/* 407 */         writer.write("" + (coords[i]).x);
/* 408 */         writer.write(",");
/* 409 */         writer.write("" + (coords[i]).y);
/* 410 */         writer.write(",");
/* 411 */         writer.write("" + (coords[i]).z);
/*     */       } 
/* 413 */       writer.write(" ");
/* 416 */       if ((i + 1) % this.maxCoordinatesPerLine == 0 && i < coords.length - 1) {
/* 417 */         writer.write("\n");
/* 418 */         isNewLine = true;
/*     */       } 
/*     */     } 
/* 421 */     if (!isNewLine)
/* 422 */       writer.write("\n"); 
/* 424 */     startLine(level, writer);
/* 425 */     endGeomTag("coordinates", writer);
/*     */   }
/*     */   
/*     */   private void startLine(int level, Writer writer) throws IOException {
/* 429 */     for (int i = 0; i < level; i++)
/* 430 */       writer.write("  "); 
/*     */   }
/*     */   
/*     */   private void startGeomTag(String geometryName, Geometry g, Writer writer) throws IOException {
/* 435 */     writer.write("<" + ((this.prefix == null || "".equals(this.prefix)) ? "" : (this.prefix + ":")));
/* 437 */     writer.write(geometryName);
/* 438 */     writeAttributes(g, writer);
/* 439 */     writer.write(">\n");
/* 440 */     writeCustomElements(g, writer);
/* 441 */     this.isRootTag = false;
/*     */   }
/*     */   
/*     */   private void writeAttributes(Geometry geom, Writer writer) throws IOException {
/* 445 */     if (geom == null)
/*     */       return; 
/* 447 */     if (!this.isRootTag)
/*     */       return; 
/* 450 */     if (this.emitNamespace)
/* 451 */       writer.write(" xmlns" + ((this.prefix == null || "".equals(this.prefix)) ? "" : (":" + this.prefix)) + "='" + this.namespace + "'"); 
/* 455 */     if (this.srsName != null && this.srsName.length() > 0)
/* 456 */       writer.write(" srsName='" + this.srsName + "'"); 
/*     */   }
/*     */   
/*     */   private void writeCustomElements(Geometry geom, Writer writer) throws IOException {
/* 463 */     if (geom == null)
/*     */       return; 
/* 464 */     if (!this.isRootTag)
/*     */       return; 
/* 465 */     if (this.customElements == null)
/*     */       return; 
/* 467 */     for (int i = 0; i < this.customElements.length; i++) {
/* 468 */       writer.write(this.customElements[i]);
/* 469 */       writer.write("\n");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endGeomTag(String geometryName, Writer writer) throws IOException {
/* 475 */     writer.write("</" + prefix());
/* 476 */     writer.write(geometryName);
/* 477 */     writer.write(">\n");
/*     */   }
/*     */   
/*     */   private String prefix() {
/* 482 */     if (this.prefix == null || this.prefix.length() == 0)
/* 483 */       return ""; 
/* 484 */     return this.prefix + ":";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\gml2\GMLWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */