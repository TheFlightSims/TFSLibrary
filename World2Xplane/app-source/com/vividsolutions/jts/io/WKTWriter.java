/*     */ package com.vividsolutions.jts.io;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ 
/*     */ public class WKTWriter {
/*     */   private static final int INDENT = 2;
/*     */   
/*     */   public static String toPoint(Coordinate p0) {
/*  75 */     return "POINT ( " + p0.x + " " + p0.y + " )";
/*     */   }
/*     */   
/*     */   public static String toLineString(CoordinateSequence seq) {
/*  88 */     StringBuffer buf = new StringBuffer();
/*  89 */     buf.append("LINESTRING ");
/*  90 */     if (seq.size() == 0) {
/*  91 */       buf.append(" EMPTY");
/*     */     } else {
/*  93 */       buf.append("(");
/*  94 */       for (int i = 0; i < seq.size(); i++) {
/*  95 */         if (i > 0)
/*  96 */           buf.append(", "); 
/*  97 */         buf.append(seq.getX(i) + " " + seq.getY(i));
/*     */       } 
/*  99 */       buf.append(")");
/*     */     } 
/* 101 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static String toLineString(Coordinate p0, Coordinate p1) {
/* 115 */     return "LINESTRING ( " + p0.x + " " + p0.y + ", " + p1.x + " " + p1.y + " )";
/*     */   }
/*     */   
/*     */   private static DecimalFormat createFormatter(PrecisionModel precisionModel) {
/* 132 */     int decimalPlaces = precisionModel.getMaximumSignificantDigits();
/* 134 */     DecimalFormatSymbols symbols = new DecimalFormatSymbols();
/* 135 */     symbols.setDecimalSeparator('.');
/* 136 */     String fmtString = "0" + ((decimalPlaces > 0) ? "." : "") + stringOfChar('#', decimalPlaces);
/* 138 */     return new DecimalFormat(fmtString, symbols);
/*     */   }
/*     */   
/*     */   public static String stringOfChar(char ch, int count) {
/* 149 */     StringBuffer buf = new StringBuffer();
/* 150 */     for (int i = 0; i < count; i++)
/* 151 */       buf.append(ch); 
/* 153 */     return buf.toString();
/*     */   }
/*     */   
/* 156 */   private int outputDimension = 2;
/*     */   
/*     */   private DecimalFormat formatter;
/*     */   
/*     */   private boolean isFormatted = false;
/*     */   
/*     */   private boolean useFormatting = false;
/*     */   
/* 160 */   private int level = 0;
/*     */   
/* 161 */   private int coordsPerLine = -1;
/*     */   
/* 162 */   private String indentTabStr = "  ";
/*     */   
/*     */   public WKTWriter() {}
/*     */   
/*     */   public WKTWriter(int outputDimension) {
/* 181 */     this.outputDimension = outputDimension;
/* 183 */     if (outputDimension < 2 || outputDimension > 3)
/* 184 */       throw new IllegalArgumentException("Invalid output dimension (must be 2 or 3)"); 
/*     */   }
/*     */   
/*     */   public void setFormatted(boolean isFormatted) {
/* 194 */     this.isFormatted = isFormatted;
/*     */   }
/*     */   
/*     */   public void setMaxCoordinatesPerLine(int coordsPerLine) {
/* 207 */     this.coordsPerLine = coordsPerLine;
/*     */   }
/*     */   
/*     */   public void setTab(int size) {
/* 218 */     if (size <= 0)
/* 219 */       throw new IllegalArgumentException("Tab count must be positive"); 
/* 220 */     this.indentTabStr = stringOfChar(' ', size);
/*     */   }
/*     */   
/*     */   public String write(Geometry geometry) {
/* 232 */     Writer sw = new StringWriter();
/*     */     try {
/* 234 */       writeFormatted(geometry, this.isFormatted, sw);
/* 236 */     } catch (IOException ex) {
/* 237 */       Assert.shouldNeverReachHere();
/*     */     } 
/* 239 */     return sw.toString();
/*     */   }
/*     */   
/*     */   public void write(Geometry geometry, Writer writer) throws IOException {
/* 250 */     writeFormatted(geometry, false, writer);
/*     */   }
/*     */   
/*     */   public String writeFormatted(Geometry geometry) {
/* 263 */     Writer sw = new StringWriter();
/*     */     try {
/* 265 */       writeFormatted(geometry, true, sw);
/* 267 */     } catch (IOException ex) {
/* 268 */       Assert.shouldNeverReachHere();
/*     */     } 
/* 270 */     return sw.toString();
/*     */   }
/*     */   
/*     */   public void writeFormatted(Geometry geometry, Writer writer) throws IOException {
/* 281 */     writeFormatted(geometry, true, writer);
/*     */   }
/*     */   
/*     */   private void writeFormatted(Geometry geometry, boolean useFormatting, Writer writer) throws IOException {
/* 291 */     this.useFormatting = useFormatting;
/* 292 */     this.formatter = createFormatter(geometry.getPrecisionModel());
/* 293 */     appendGeometryTaggedText(geometry, 0, writer);
/*     */   }
/*     */   
/*     */   private void appendGeometryTaggedText(Geometry geometry, int level, Writer writer) throws IOException {
/* 307 */     indent(level, writer);
/* 309 */     if (geometry instanceof Point) {
/* 310 */       Point point = (Point)geometry;
/* 311 */       appendPointTaggedText(point.getCoordinate(), level, writer, point.getPrecisionModel());
/* 313 */     } else if (geometry instanceof LinearRing) {
/* 314 */       appendLinearRingTaggedText((LinearRing)geometry, level, writer);
/* 316 */     } else if (geometry instanceof LineString) {
/* 317 */       appendLineStringTaggedText((LineString)geometry, level, writer);
/* 319 */     } else if (geometry instanceof Polygon) {
/* 320 */       appendPolygonTaggedText((Polygon)geometry, level, writer);
/* 322 */     } else if (geometry instanceof MultiPoint) {
/* 323 */       appendMultiPointTaggedText((MultiPoint)geometry, level, writer);
/* 325 */     } else if (geometry instanceof MultiLineString) {
/* 326 */       appendMultiLineStringTaggedText((MultiLineString)geometry, level, writer);
/* 328 */     } else if (geometry instanceof MultiPolygon) {
/* 329 */       appendMultiPolygonTaggedText((MultiPolygon)geometry, level, writer);
/* 331 */     } else if (geometry instanceof GeometryCollection) {
/* 332 */       appendGeometryCollectionTaggedText((GeometryCollection)geometry, level, writer);
/*     */     } else {
/* 335 */       Assert.shouldNeverReachHere("Unsupported Geometry implementation:" + geometry.getClass());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendPointTaggedText(Coordinate coordinate, int level, Writer writer, PrecisionModel precisionModel) throws IOException {
/* 353 */     writer.write("POINT ");
/* 354 */     appendPointText(coordinate, level, writer, precisionModel);
/*     */   }
/*     */   
/*     */   private void appendLineStringTaggedText(LineString lineString, int level, Writer writer) throws IOException {
/* 367 */     writer.write("LINESTRING ");
/* 368 */     appendLineStringText(lineString, level, false, writer);
/*     */   }
/*     */   
/*     */   private void appendLinearRingTaggedText(LinearRing linearRing, int level, Writer writer) throws IOException {
/* 381 */     writer.write("LINEARRING ");
/* 382 */     appendLineStringText((LineString)linearRing, level, false, writer);
/*     */   }
/*     */   
/*     */   private void appendPolygonTaggedText(Polygon polygon, int level, Writer writer) throws IOException {
/* 395 */     writer.write("POLYGON ");
/* 396 */     appendPolygonText(polygon, level, false, writer);
/*     */   }
/*     */   
/*     */   private void appendMultiPointTaggedText(MultiPoint multipoint, int level, Writer writer) throws IOException {
/* 409 */     writer.write("MULTIPOINT ");
/* 410 */     appendMultiPointText(multipoint, level, writer);
/*     */   }
/*     */   
/*     */   private void appendMultiLineStringTaggedText(MultiLineString multiLineString, int level, Writer writer) throws IOException {
/* 424 */     writer.write("MULTILINESTRING ");
/* 425 */     appendMultiLineStringText(multiLineString, level, false, writer);
/*     */   }
/*     */   
/*     */   private void appendMultiPolygonTaggedText(MultiPolygon multiPolygon, int level, Writer writer) throws IOException {
/* 438 */     writer.write("MULTIPOLYGON ");
/* 439 */     appendMultiPolygonText(multiPolygon, level, writer);
/*     */   }
/*     */   
/*     */   private void appendGeometryCollectionTaggedText(GeometryCollection geometryCollection, int level, Writer writer) throws IOException {
/* 453 */     writer.write("GEOMETRYCOLLECTION ");
/* 454 */     appendGeometryCollectionText(geometryCollection, level, writer);
/*     */   }
/*     */   
/*     */   private void appendPointText(Coordinate coordinate, int level, Writer writer, PrecisionModel precisionModel) throws IOException {
/* 470 */     if (coordinate == null) {
/* 471 */       writer.write("EMPTY");
/*     */     } else {
/* 474 */       writer.write("(");
/* 475 */       appendCoordinate(coordinate, writer);
/* 476 */       writer.write(")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendCoordinate(CoordinateSequence seq, int i, Writer writer) throws IOException {
/* 490 */     writer.write(writeNumber(seq.getX(i)) + " " + writeNumber(seq.getY(i)));
/* 491 */     if (this.outputDimension >= 3 && seq.getDimension() >= 3) {
/* 492 */       double z = seq.getOrdinate(i, 3);
/* 493 */       if (!Double.isNaN(z)) {
/* 494 */         writer.write(" ");
/* 495 */         writer.write(writeNumber(z));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendCoordinate(Coordinate coordinate, Writer writer) throws IOException {
/* 510 */     writer.write(writeNumber(coordinate.x) + " " + writeNumber(coordinate.y));
/* 511 */     if (this.outputDimension >= 3 && !Double.isNaN(coordinate.z)) {
/* 512 */       writer.write(" ");
/* 513 */       writer.write(writeNumber(coordinate.z));
/*     */     } 
/*     */   }
/*     */   
/*     */   private String writeNumber(double d) {
/* 526 */     return this.formatter.format(d);
/*     */   }
/*     */   
/*     */   private void appendSequenceText(CoordinateSequence seq, int level, boolean doIndent, Writer writer) throws IOException {
/* 539 */     if (seq.size() == 0) {
/* 540 */       writer.write("EMPTY");
/*     */     } else {
/* 543 */       if (doIndent)
/* 543 */         indent(level, writer); 
/* 544 */       writer.write("(");
/* 545 */       for (int i = 0; i < seq.size(); i++) {
/* 546 */         if (i > 0) {
/* 547 */           writer.write(", ");
/* 548 */           if (this.coordsPerLine > 0 && i % this.coordsPerLine == 0)
/* 550 */             indent(level + 1, writer); 
/*     */         } 
/* 553 */         appendCoordinate(seq, i, writer);
/*     */       } 
/* 555 */       writer.write(")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendLineStringText(LineString lineString, int level, boolean doIndent, Writer writer) throws IOException {
/* 569 */     if (lineString.isEmpty()) {
/* 570 */       writer.write("EMPTY");
/*     */     } else {
/* 573 */       if (doIndent)
/* 573 */         indent(level, writer); 
/* 574 */       writer.write("(");
/* 575 */       for (int i = 0; i < lineString.getNumPoints(); i++) {
/* 576 */         if (i > 0) {
/* 577 */           writer.write(", ");
/* 578 */           if (this.coordsPerLine > 0 && i % this.coordsPerLine == 0)
/* 580 */             indent(level + 1, writer); 
/*     */         } 
/* 583 */         appendCoordinate(lineString.getCoordinateN(i), writer);
/*     */       } 
/* 585 */       writer.write(")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendPolygonText(Polygon polygon, int level, boolean indentFirst, Writer writer) throws IOException {
/* 599 */     if (polygon.isEmpty()) {
/* 600 */       writer.write("EMPTY");
/*     */     } else {
/* 603 */       if (indentFirst)
/* 603 */         indent(level, writer); 
/* 604 */       writer.write("(");
/* 605 */       appendLineStringText(polygon.getExteriorRing(), level, false, writer);
/* 606 */       for (int i = 0; i < polygon.getNumInteriorRing(); i++) {
/* 607 */         writer.write(", ");
/* 608 */         appendLineStringText(polygon.getInteriorRingN(i), level + 1, true, writer);
/*     */       } 
/* 610 */       writer.write(")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendMultiPointText(MultiPoint multiPoint, int level, Writer writer) throws IOException {
/* 624 */     if (multiPoint.isEmpty()) {
/* 625 */       writer.write("EMPTY");
/*     */     } else {
/* 628 */       writer.write("(");
/* 629 */       for (int i = 0; i < multiPoint.getNumGeometries(); i++) {
/* 630 */         if (i > 0) {
/* 631 */           writer.write(", ");
/* 632 */           indentCoords(i, level + 1, writer);
/*     */         } 
/* 634 */         writer.write("(");
/* 635 */         appendCoordinate(((Point)multiPoint.getGeometryN(i)).getCoordinate(), writer);
/* 636 */         writer.write(")");
/*     */       } 
/* 638 */       writer.write(")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendMultiLineStringText(MultiLineString multiLineString, int level, boolean indentFirst, Writer writer) throws IOException {
/* 653 */     if (multiLineString.isEmpty()) {
/* 654 */       writer.write("EMPTY");
/*     */     } else {
/* 657 */       int level2 = level;
/* 658 */       boolean doIndent = indentFirst;
/* 659 */       writer.write("(");
/* 660 */       for (int i = 0; i < multiLineString.getNumGeometries(); i++) {
/* 661 */         if (i > 0) {
/* 662 */           writer.write(", ");
/* 663 */           level2 = level + 1;
/* 664 */           doIndent = true;
/*     */         } 
/* 666 */         appendLineStringText((LineString)multiLineString.getGeometryN(i), level2, doIndent, writer);
/*     */       } 
/* 668 */       writer.write(")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendMultiPolygonText(MultiPolygon multiPolygon, int level, Writer writer) throws IOException {
/* 682 */     if (multiPolygon.isEmpty()) {
/* 683 */       writer.write("EMPTY");
/*     */     } else {
/* 686 */       int level2 = level;
/* 687 */       boolean doIndent = false;
/* 688 */       writer.write("(");
/* 689 */       for (int i = 0; i < multiPolygon.getNumGeometries(); i++) {
/* 690 */         if (i > 0) {
/* 691 */           writer.write(", ");
/* 692 */           level2 = level + 1;
/* 693 */           doIndent = true;
/*     */         } 
/* 695 */         appendPolygonText((Polygon)multiPolygon.getGeometryN(i), level2, doIndent, writer);
/*     */       } 
/* 697 */       writer.write(")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendGeometryCollectionText(GeometryCollection geometryCollection, int level, Writer writer) throws IOException {
/* 712 */     if (geometryCollection.isEmpty()) {
/* 713 */       writer.write("EMPTY");
/*     */     } else {
/* 716 */       int level2 = level;
/* 717 */       writer.write("(");
/* 718 */       for (int i = 0; i < geometryCollection.getNumGeometries(); i++) {
/* 719 */         if (i > 0) {
/* 720 */           writer.write(", ");
/* 721 */           level2 = level + 1;
/*     */         } 
/* 723 */         appendGeometryTaggedText(geometryCollection.getGeometryN(i), level2, writer);
/*     */       } 
/* 725 */       writer.write(")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void indentCoords(int coordIndex, int level, Writer writer) throws IOException {
/* 732 */     if (this.coordsPerLine <= 0 || coordIndex % this.coordsPerLine != 0)
/*     */       return; 
/* 735 */     indent(level, writer);
/*     */   }
/*     */   
/*     */   private void indent(int level, Writer writer) throws IOException {
/* 741 */     if (!this.useFormatting || level <= 0)
/*     */       return; 
/* 743 */     writer.write("\n");
/* 744 */     for (int i = 0; i < level; i++)
/* 745 */       writer.write(this.indentTabStr); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\WKTWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */