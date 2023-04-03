/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.io.ParseException;
/*     */ import com.vividsolutions.jts.io.WKTReader;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class WKTReader2 extends WKTReader {
/*     */   private static final String EMPTY = "EMPTY";
/*     */   
/*     */   private static final String COMMA = ",";
/*     */   
/*     */   private static final String L_PAREN = "(";
/*     */   
/*     */   private static final String R_PAREN = ")";
/*     */   
/*     */   private static final String NAN_SYMBOL = "NaN";
/*     */   
/*     */   private static final double EPSILON_SQLMM = 1.0E-8D;
/*     */   
/*     */   private static final double M_PI = 3.141592653589793D;
/*     */   
/*     */   private static final double M_PI_2 = 1.5707963267948966D;
/*     */   
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*     */   private PrecisionModel precisionModel;
/*     */   
/*     */   private StreamTokenizer tokenizer;
/*     */   
/*     */   public WKTReader2() {
/*  81 */     this(JTSFactoryFinder.getGeometryFactory(null));
/*     */   }
/*     */   
/*     */   public WKTReader2(GeometryFactory geometryFactory) {
/*  91 */     this.geometryFactory = geometryFactory;
/*  92 */     this.precisionModel = geometryFactory.getPrecisionModel();
/*     */   }
/*     */   
/*     */   public Geometry read(String wellKnownText) throws ParseException {
/* 106 */     StringReader reader = new StringReader(wellKnownText);
/*     */     try {
/* 108 */       return read(reader);
/*     */     } finally {
/* 110 */       reader.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Geometry read(Reader reader) throws ParseException {
/* 125 */     this.tokenizer = new StreamTokenizer(reader);
/* 127 */     this.tokenizer.resetSyntax();
/* 128 */     this.tokenizer.wordChars(97, 122);
/* 129 */     this.tokenizer.wordChars(65, 90);
/* 130 */     this.tokenizer.wordChars(160, 255);
/* 131 */     this.tokenizer.wordChars(48, 57);
/* 132 */     this.tokenizer.wordChars(45, 45);
/* 133 */     this.tokenizer.wordChars(43, 43);
/* 134 */     this.tokenizer.wordChars(46, 46);
/* 135 */     this.tokenizer.whitespaceChars(0, 32);
/* 136 */     this.tokenizer.commentChar(35);
/*     */     try {
/* 139 */       return readGeometryTaggedText();
/* 140 */     } catch (IOException e) {
/* 141 */       throw new ParseException(e.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private Coordinate[] getCoordinates() throws IOException, ParseException {
/* 156 */     String nextToken = getNextEmptyOrOpener();
/* 157 */     if (nextToken.equals("EMPTY"))
/* 158 */       return new Coordinate[0]; 
/* 160 */     ArrayList<Coordinate> coordinates = new ArrayList();
/* 161 */     coordinates.add(getPreciseCoordinate());
/* 162 */     nextToken = getNextCloserOrComma();
/* 163 */     while (nextToken.equals(",")) {
/* 164 */       coordinates.add(getPreciseCoordinate());
/* 165 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 167 */     Coordinate[] array = new Coordinate[coordinates.size()];
/* 168 */     return coordinates.<Coordinate>toArray(array);
/*     */   }
/*     */   
/*     */   private List<Coordinate> getCoordinateList(boolean openExpected) throws IOException, ParseException {
/* 173 */     if (openExpected) {
/* 174 */       String str = getNextEmptyOrOpener();
/* 175 */       if (str.equals("EMPTY"))
/* 176 */         return Collections.emptyList(); 
/*     */     } 
/* 179 */     ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
/* 180 */     coordinates.add(getPreciseCoordinate());
/* 181 */     String nextToken = getNextCloserOrComma();
/* 182 */     while (nextToken.equals(",")) {
/* 183 */       coordinates.add(getPreciseCoordinate());
/* 184 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 186 */     Coordinate[] array = new Coordinate[coordinates.size()];
/* 187 */     return coordinates;
/*     */   }
/*     */   
/*     */   private Coordinate getPreciseCoordinate() throws IOException, ParseException {
/* 191 */     Coordinate coord = new Coordinate();
/* 192 */     coord.x = getNextNumber();
/* 193 */     coord.y = getNextNumber();
/* 194 */     if (isNumberNext())
/* 195 */       coord.z = getNextNumber(); 
/* 197 */     this.precisionModel.makePrecise(coord);
/* 198 */     return coord;
/*     */   }
/*     */   
/*     */   private boolean isNumberNext() throws IOException {
/* 202 */     int type = this.tokenizer.nextToken();
/* 203 */     this.tokenizer.pushBack();
/* 204 */     return (type == -3);
/*     */   }
/*     */   
/*     */   private double getNextNumber() throws IOException, ParseException {
/* 221 */     int type = this.tokenizer.nextToken();
/* 222 */     switch (type) {
/*     */       case -3:
/* 224 */         if (this.tokenizer.sval.equalsIgnoreCase("NaN"))
/* 225 */           return Double.NaN; 
/*     */         try {
/* 228 */           return Double.parseDouble(this.tokenizer.sval);
/* 229 */         } catch (NumberFormatException ex) {
/* 230 */           throw new ParseException("Invalid number: " + this.tokenizer.sval);
/*     */         } 
/*     */     } 
/* 235 */     parseError("number");
/* 236 */     return 0.0D;
/*     */   }
/*     */   
/*     */   private String getNextEmptyOrOpener() throws IOException, ParseException {
/* 249 */     String nextWord = getNextWord();
/* 250 */     if (nextWord.equals("EMPTY") || nextWord.equals("("))
/* 251 */       return nextWord; 
/* 253 */     parseError("EMPTY or (");
/* 254 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextCloserOrComma() throws IOException, ParseException {
/* 267 */     String nextWord = getNextWord();
/* 268 */     if (nextWord.equals(",") || nextWord.equals(")"))
/* 269 */       return nextWord; 
/* 271 */     parseError(", or )");
/* 272 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextCloser() throws IOException, ParseException {
/* 285 */     String nextWord = getNextWord();
/* 286 */     if (nextWord.equals(")"))
/* 287 */       return nextWord; 
/* 289 */     parseError(")");
/* 290 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextWord() throws IOException, ParseException {
/*     */     String word;
/* 303 */     int type = this.tokenizer.nextToken();
/* 304 */     switch (type) {
/*     */       case -3:
/* 307 */         word = this.tokenizer.sval;
/* 308 */         if (word.equalsIgnoreCase("EMPTY"))
/* 309 */           return "EMPTY"; 
/* 310 */         return word;
/*     */       case 40:
/* 313 */         return "(";
/*     */       case 41:
/* 315 */         return ")";
/*     */       case 44:
/* 317 */         return ",";
/*     */     } 
/* 319 */     parseError("word");
/* 320 */     return null;
/*     */   }
/*     */   
/*     */   private void parseError(String expected) throws ParseException {
/* 334 */     if (this.tokenizer.ttype == -2)
/* 335 */       Assert.shouldNeverReachHere("Unexpected NUMBER token"); 
/* 336 */     if (this.tokenizer.ttype == 10)
/* 337 */       Assert.shouldNeverReachHere("Unexpected EOL token"); 
/* 339 */     String tokenStr = tokenString();
/* 340 */     throw new ParseException("Expected " + expected + " but found " + tokenStr);
/*     */   }
/*     */   
/*     */   private String tokenString() {
/* 349 */     switch (this.tokenizer.ttype) {
/*     */       case -2:
/* 351 */         return "<NUMBER>";
/*     */       case 10:
/* 353 */         return "End-of-Line";
/*     */       case -1:
/* 355 */         return "End-of-Stream";
/*     */       case -3:
/* 357 */         return "'" + this.tokenizer.sval + "'";
/*     */     } 
/* 359 */     return "'" + (char)this.tokenizer.ttype + "'";
/*     */   }
/*     */   
/*     */   private Geometry readGeometryTaggedText() throws IOException, ParseException {
/* 373 */     String type = null;
/*     */     try {
/* 376 */       type = getNextWord();
/* 377 */     } catch (IOException e) {
/* 378 */       return null;
/* 379 */     } catch (ParseException e) {
/* 380 */       return null;
/*     */     } 
/* 383 */     if (type.equals("POINT"))
/* 384 */       return (Geometry)readPointText(); 
/* 385 */     if (type.equalsIgnoreCase("LINESTRING"))
/* 386 */       return (Geometry)readLineStringText(); 
/* 387 */     if (type.equalsIgnoreCase("LINEARRING"))
/* 388 */       return (Geometry)readLinearRingText(); 
/* 389 */     if (type.equalsIgnoreCase("POLYGON"))
/* 390 */       return (Geometry)readPolygonText(); 
/* 391 */     if (type.equalsIgnoreCase("MULTIPOINT"))
/* 392 */       return (Geometry)readMultiPointText(); 
/* 393 */     if (type.equalsIgnoreCase("MULTILINESTRING"))
/* 394 */       return (Geometry)readMultiLineStringText(); 
/* 395 */     if (type.equalsIgnoreCase("MULTIPOLYGON"))
/* 396 */       return (Geometry)readMultiPolygonText(); 
/* 397 */     if (type.equalsIgnoreCase("GEOMETRYCOLLECTION"))
/* 398 */       return (Geometry)readGeometryCollectionText(); 
/* 399 */     if (type.equalsIgnoreCase("CIRCULARSTRING"))
/* 400 */       return (Geometry)readCircularStringText(); 
/* 401 */     if (type.equalsIgnoreCase("COMPOUNDCURVE"))
/* 402 */       return (Geometry)readCompoundCurveText(); 
/* 403 */     if (type.equalsIgnoreCase("CURVEPOLYGON"))
/* 404 */       return (Geometry)readCurvePolygonText(); 
/* 406 */     throw new ParseException("Unknown geometry type: " + type);
/*     */   }
/*     */   
/*     */   private Point readPointText() throws IOException, ParseException {
/* 419 */     String nextToken = getNextEmptyOrOpener();
/* 420 */     if (nextToken.equals("EMPTY"))
/* 421 */       return this.geometryFactory.createPoint((Coordinate)null); 
/* 423 */     Point point = this.geometryFactory.createPoint(getPreciseCoordinate());
/* 424 */     getNextCloser();
/* 425 */     return point;
/*     */   }
/*     */   
/*     */   private LineString readLineStringText() throws IOException, ParseException {
/* 438 */     return this.geometryFactory.createLineString(getCoordinates());
/*     */   }
/*     */   
/*     */   private LineString readCircularStringText() throws IOException, ParseException {
/* 449 */     List<Coordinate> segmentized, coordinates = getCoordinateList(true);
/* 451 */     if (coordinates.size() < 3) {
/* 452 */       segmentized = coordinates;
/*     */     } else {
/* 454 */       segmentized = new ArrayList<Coordinate>();
/* 455 */       for (int i = 0; i < coordinates.size() - 1; i += 2) {
/* 456 */         Coordinate p1 = coordinates.get(i);
/* 457 */         Coordinate p2 = coordinates.get(i + 1);
/* 458 */         Coordinate p3 = coordinates.get(i + 2);
/* 460 */         List<Coordinate> segments = circularSegmentize(p1, p2, p3);
/* 461 */         segmentized.addAll(segments.subList(0, segments.size() - 1));
/*     */       } 
/* 463 */       segmentized.add(coordinates.get(coordinates.size() - 1));
/*     */     } 
/* 466 */     Coordinate[] array = segmentized.<Coordinate>toArray(new Coordinate[segmentized.size()]);
/* 468 */     return this.geometryFactory.createLineString(array);
/*     */   }
/*     */   
/*     */   private List<Coordinate> circularSegmentize(Coordinate p1, Coordinate p2, Coordinate p3) {
/*     */     Coordinate center;
/*     */     double radius;
/* 499 */     if (Math.abs(p1.x - p3.x) < 1.0E-8D && Math.abs(p1.y - p3.y) < 1.0E-8D) {
/* 500 */       double centerX = p1.x + (p2.x - p1.x) / 2.0D;
/* 501 */       double centerY = p1.y + (p2.y - p1.y) / 2.0D;
/* 502 */       center = new Coordinate();
/* 503 */       center.x = centerX;
/* 504 */       center.y = centerY;
/* 506 */       radius = Math.sqrt((centerX - p1.x) * (centerX - p1.x) + (centerY - p1.y) * (centerY - p1.y));
/*     */     } else {
/* 508 */       double temp = p2.x * p2.x + p2.y * p2.y;
/* 509 */       double bc = (p1.x * p1.x + p1.y * p1.y - temp) / 2.0D;
/* 510 */       double cd = (temp - p3.x * p3.x - p3.y * p3.y) / 2.0D;
/* 511 */       double determinate = (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p2.y);
/* 514 */       if (Math.abs(determinate) < 1.0E-8D) {
/* 515 */         List<Coordinate> curve = new ArrayList<Coordinate>();
/* 516 */         curve.add(p1);
/* 518 */         curve.add(p3);
/* 520 */         return curve;
/*     */       } 
/* 522 */       determinate = 1.0D / determinate;
/* 523 */       double centerX = (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * determinate;
/* 524 */       double centerY = ((p1.x - p2.x) * cd - (p2.x - p3.x) * bc) * determinate;
/* 525 */       center = new Coordinate();
/* 526 */       center.x = centerX;
/* 527 */       center.y = centerY;
/* 529 */       radius = Math.sqrt((centerX - p1.x) * (centerX - p1.x) + (centerY - p1.y) * (centerY - p1.y));
/*     */     } 
/* 531 */     return circularSegmentize(p1, p2, p3, center, radius, 32);
/*     */   }
/*     */   
/*     */   private List<Coordinate> circularSegmentize(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate center, double radius, int perQuad) {
/* 538 */     Coordinate pbuf = new Coordinate();
/* 543 */     double sweep = 0.0D;
/* 544 */     double angle = 0.0D;
/* 545 */     double increment = 0.0D;
/* 548 */     if (radius < 0.0D) {
/* 551 */       List<Coordinate> list = new ArrayList<Coordinate>();
/* 552 */       list.add(p1);
/* 553 */       list.add(p2);
/* 554 */       return list;
/*     */     } 
/* 557 */     double a1 = Math.atan2(p1.y - center.y, p1.x - center.x);
/* 558 */     double a2 = Math.atan2(p2.y - center.y, p2.x - center.x);
/* 559 */     double a3 = Math.atan2(p3.y - center.y, p3.x - center.x);
/* 561 */     if (Math.abs(p1.x - p3.x) < 1.0E-8D && Math.abs(p1.y - p3.y) < 1.0E-8D) {
/* 564 */       sweep = 6.283185307179586D;
/* 567 */     } else if (a1 > a2 && a2 > a3) {
/* 569 */       sweep = a3 - a1;
/* 572 */     } else if (a1 < a2 && a2 < a3) {
/* 574 */       sweep = a3 - a1;
/* 577 */     } else if ((a1 < a2 && a1 > a3) || (a2 < a3 && a1 > a3)) {
/* 579 */       sweep = a3 - a1 + 6.283185307179586D;
/* 582 */     } else if ((a1 > a2 && a1 < a3) || (a2 > a3 && a1 < a3)) {
/* 584 */       sweep = a3 - a1 - 6.283185307179586D;
/*     */     } else {
/* 588 */       sweep = 0.0D;
/*     */     } 
/* 591 */     int ptcount = (int)Math.ceil(Math.abs(perQuad * sweep / 1.5707963267948966D));
/* 593 */     List<Coordinate> result = new ArrayList<Coordinate>(ptcount);
/* 595 */     increment = 1.5707963267948966D / perQuad;
/* 596 */     if (sweep < 0.0D)
/* 596 */       increment *= -1.0D; 
/* 597 */     angle = a1;
/* 599 */     result.add(p1);
/*     */     double i;
/* 601 */     for (i = 0.0D; i < (ptcount - 1); i++) {
/* 603 */       Coordinate pt = new Coordinate();
/* 604 */       result.add(pt);
/* 606 */       angle += increment;
/* 607 */       if (increment > 0.0D && angle > Math.PI)
/* 607 */         angle -= 6.283185307179586D; 
/* 608 */       if (increment < 0.0D && angle < -3.141592653589793D)
/* 608 */         angle -= 6.283185307179586D; 
/* 610 */       center.x += radius * Math.cos(angle);
/* 611 */       center.y += radius * Math.sin(angle);
/*     */     } 
/* 660 */     result.add(p3);
/* 662 */     return result;
/*     */   }
/*     */   
/*     */   private LineString readCompoundCurveText() throws IOException, ParseException {
/* 667 */     List<LineString> lineStrings = getLineStrings();
/* 669 */     if (lineStrings.isEmpty())
/* 671 */       return this.geometryFactory.createLineString(new Coordinate[0]); 
/* 673 */     if (lineStrings.size() == 1)
/* 674 */       return lineStrings.get(0); 
/* 677 */     List<Coordinate> coords = new ArrayList<Coordinate>();
/* 678 */     for (LineString segment : lineStrings) {
/* 679 */       List<Coordinate> segmentCoordinates = Arrays.asList(segment.getCoordinates());
/* 680 */       coords.addAll(segmentCoordinates.subList(0, segmentCoordinates.size() - 1));
/*     */     } 
/* 682 */     LineString last = lineStrings.get(lineStrings.size() - 1);
/* 683 */     Coordinate end = last.getCoordinateN(last.getNumPoints() - 1);
/* 684 */     coords.add(end);
/* 686 */     return this.geometryFactory.createLineString(coords.<Coordinate>toArray(new Coordinate[coords.size()]));
/*     */   }
/*     */   
/*     */   List<LineString> getLineStrings() throws IOException, ParseException {
/* 698 */     ArrayList<LineString> lineStrings = new ArrayList<LineString>();
/* 699 */     String nextWord = getNextEmptyOrOpener();
/* 700 */     if (nextWord.equals("EMPTY"))
/* 701 */       return lineStrings; 
/* 704 */     nextWord = ",";
/* 705 */     while (nextWord.equals(",")) {
/* 706 */       nextWord = getNextWord();
/* 707 */       if (nextWord.equals("(")) {
/* 708 */         List<Coordinate> coords = getCoordinateList(false);
/* 709 */         LineString lineString = this.geometryFactory.createLineString(coords.<Coordinate>toArray(new Coordinate[coords.size()]));
/* 710 */         lineStrings.add(lineString);
/* 712 */       } else if (nextWord.equalsIgnoreCase("CIRCULARSTRING")) {
/* 713 */         LineString circularString = readCircularStringText();
/* 714 */         lineStrings.add(circularString);
/*     */       } 
/* 716 */       nextWord = getNextCloserOrComma();
/*     */     } 
/* 718 */     return lineStrings;
/*     */   }
/*     */   
/*     */   private LinearRing readCurvedLinearRingText() throws IOException, ParseException {
/* 730 */     Coordinate[] ring = null;
/* 732 */     String nextWord = getNextWord();
/* 733 */     if (nextWord.equals("(")) {
/* 734 */       List<Coordinate> coords = getCoordinateList(false);
/* 735 */       ring = coords.<Coordinate>toArray(new Coordinate[coords.size()]);
/* 737 */     } else if (nextWord.equalsIgnoreCase("CIRCULARSTRING")) {
/* 738 */       LineString circularString = readCircularStringText();
/* 739 */       ring = circularString.getCoordinates();
/* 741 */     } else if (nextWord.equalsIgnoreCase("COMPOUNDCURVE")) {
/* 742 */       LineString circularString = readCompoundCurveText();
/* 743 */       ring = circularString.getCoordinates();
/*     */     } else {
/* 746 */       parseError("(, CIRCULARSTRING or COMPOUNDCURVE");
/*     */     } 
/* 748 */     return this.geometryFactory.createLinearRing(ring);
/*     */   }
/*     */   
/*     */   private LinearRing readLinearRingText() throws IOException, ParseException {
/* 762 */     return this.geometryFactory.createLinearRing(getCoordinates());
/*     */   }
/*     */   
/*     */   private MultiPoint readMultiPointText() throws IOException, ParseException {
/* 775 */     return this.geometryFactory.createMultiPoint(toPoints(getCoordinates()));
/*     */   }
/*     */   
/*     */   private Point[] toPoints(Coordinate[] coordinates) {
/* 787 */     ArrayList<Point> points = new ArrayList();
/* 788 */     for (int i = 0; i < coordinates.length; i++)
/* 789 */       points.add(this.geometryFactory.createPoint(coordinates[i])); 
/* 791 */     return points.<Point>toArray(new Point[0]);
/*     */   }
/*     */   
/*     */   private Polygon readPolygonText() throws IOException, ParseException {
/* 805 */     String nextToken = getNextEmptyOrOpener();
/* 806 */     if (nextToken.equals("EMPTY"))
/* 807 */       return this.geometryFactory.createPolygon(this.geometryFactory.createLinearRing(new Coordinate[0]), new LinearRing[0]); 
/* 810 */     ArrayList<LinearRing> holes = new ArrayList();
/* 811 */     LinearRing shell = readLinearRingText();
/* 812 */     nextToken = getNextCloserOrComma();
/* 813 */     while (nextToken.equals(",")) {
/* 814 */       LinearRing hole = readLinearRingText();
/* 815 */       holes.add(hole);
/* 816 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 818 */     LinearRing[] array = new LinearRing[holes.size()];
/* 819 */     return this.geometryFactory.createPolygon(shell, holes.<LinearRing>toArray(array));
/*     */   }
/*     */   
/*     */   private Polygon readCurvePolygonText() throws IOException, ParseException {
/* 823 */     String nextToken = getNextEmptyOrOpener();
/* 824 */     if (nextToken.equals("EMPTY"))
/* 825 */       return this.geometryFactory.createPolygon(this.geometryFactory.createLinearRing(new Coordinate[0]), new LinearRing[0]); 
/* 828 */     if (!nextToken.equals("("))
/* 829 */       parseError("Ring expected"); 
/* 831 */     LinearRing shell = readCurvedLinearRingText();
/* 832 */     ArrayList<LinearRing> holes = new ArrayList();
/* 833 */     nextToken = getNextCloserOrComma();
/* 834 */     while (nextToken.equals(",")) {
/* 835 */       LinearRing hole = readCurvedLinearRingText();
/* 836 */       holes.add(hole);
/* 837 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 839 */     LinearRing[] array = new LinearRing[holes.size()];
/* 840 */     return this.geometryFactory.createPolygon(shell, holes.<LinearRing>toArray(array));
/*     */   }
/*     */   
/*     */   private MultiLineString readMultiLineStringText() throws IOException, ParseException {
/* 853 */     String nextToken = getNextEmptyOrOpener();
/* 854 */     if (nextToken.equals("EMPTY"))
/* 855 */       return this.geometryFactory.createMultiLineString(new LineString[0]); 
/* 857 */     ArrayList<LineString> lineStrings = new ArrayList();
/* 858 */     LineString lineString = readLineStringText();
/* 859 */     lineStrings.add(lineString);
/* 860 */     nextToken = getNextCloserOrComma();
/* 861 */     while (nextToken.equals(",")) {
/* 862 */       lineString = readLineStringText();
/* 863 */       lineStrings.add(lineString);
/* 864 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 866 */     LineString[] array = new LineString[lineStrings.size()];
/* 867 */     return this.geometryFactory.createMultiLineString(lineStrings.<LineString>toArray(array));
/*     */   }
/*     */   
/*     */   private MultiPolygon readMultiPolygonText() throws IOException, ParseException {
/* 882 */     String nextToken = getNextEmptyOrOpener();
/* 883 */     if (nextToken.equals("EMPTY"))
/* 884 */       return this.geometryFactory.createMultiPolygon(new Polygon[0]); 
/* 886 */     ArrayList<Polygon> polygons = new ArrayList();
/* 887 */     Polygon polygon = readPolygonText();
/* 888 */     polygons.add(polygon);
/* 889 */     nextToken = getNextCloserOrComma();
/* 890 */     while (nextToken.equals(",")) {
/* 891 */       polygon = readPolygonText();
/* 892 */       polygons.add(polygon);
/* 893 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 895 */     Polygon[] array = new Polygon[polygons.size()];
/* 896 */     return this.geometryFactory.createMultiPolygon(polygons.<Polygon>toArray(array));
/*     */   }
/*     */   
/*     */   private GeometryCollection readGeometryCollectionText() throws IOException, ParseException {
/* 910 */     String nextToken = getNextEmptyOrOpener();
/* 911 */     if (nextToken.equals("EMPTY"))
/* 912 */       return this.geometryFactory.createGeometryCollection(new Geometry[0]); 
/* 914 */     ArrayList<Geometry> geometries = new ArrayList();
/* 915 */     Geometry geometry = readGeometryTaggedText();
/* 916 */     geometries.add(geometry);
/* 917 */     nextToken = getNextCloserOrComma();
/* 918 */     while (nextToken.equals(",")) {
/* 919 */       geometry = readGeometryTaggedText();
/* 920 */       geometries.add(geometry);
/* 921 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 923 */     Geometry[] array = new Geometry[geometries.size()];
/* 924 */     return this.geometryFactory.createGeometryCollection(geometries.<Geometry>toArray(array));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\WKTReader2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */