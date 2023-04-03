/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.MinimumBoundingCircle;
/*     */ import com.vividsolutions.jts.algorithm.MinimumDiameter;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.OctagonalEnvelope;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.io.WKTReader;
/*     */ 
/*     */ public class StaticGeometry {
/*     */   public static Geometry geomFromWKT(String wkt) {
/*  52 */     WKTReader wktreader = new WKTReader();
/*     */     try {
/*  55 */       return wktreader.read(wkt);
/*  57 */     } catch (Exception e) {
/*  59 */       throw new IllegalArgumentException("bad wkt");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String toWKT(Geometry arg0) {
/*  66 */     if (arg0 == null)
/*  66 */       return null; 
/*  67 */     Geometry _this = arg0;
/*  69 */     return _this.toString();
/*     */   }
/*     */   
/*     */   public static boolean contains(Geometry arg0, Geometry arg1) {
/*  74 */     if (arg0 == null || arg1 == null)
/*  74 */       return false; 
/*  75 */     Geometry _this = arg0;
/*  77 */     return _this.contains(arg1);
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(Geometry arg0) {
/*  82 */     if (arg0 == null)
/*  82 */       return false; 
/*  83 */     Geometry _this = arg0;
/*  85 */     return _this.isEmpty();
/*     */   }
/*     */   
/*     */   public static double geomLength(Geometry arg0) {
/*  90 */     if (arg0 == null)
/*  90 */       return 0.0D; 
/*  91 */     Geometry _this = arg0;
/*  93 */     return _this.getLength();
/*     */   }
/*     */   
/*     */   public static boolean intersects(Geometry arg0, Geometry arg1) {
/*  98 */     if (arg0 == null || arg1 == null)
/*  98 */       return false; 
/*  99 */     Geometry _this = arg0;
/* 101 */     return _this.intersects(arg1);
/*     */   }
/*     */   
/*     */   public static boolean isValid(Geometry arg0) {
/* 106 */     if (arg0 == null)
/* 106 */       return false; 
/* 107 */     Geometry _this = arg0;
/* 109 */     return _this.isValid();
/*     */   }
/*     */   
/*     */   public static String geometryType(Geometry arg0) {
/* 114 */     if (arg0 == null)
/* 114 */       return null; 
/* 115 */     Geometry _this = arg0;
/* 117 */     return _this.getGeometryType();
/*     */   }
/*     */   
/*     */   public static int numPoints(Geometry arg0) {
/* 124 */     if (arg0 == null)
/* 124 */       return 0; 
/* 125 */     Geometry _this = arg0;
/* 127 */     return _this.getNumPoints();
/*     */   }
/*     */   
/*     */   public static boolean isSimple(Geometry arg0) {
/* 132 */     if (arg0 == null)
/* 132 */       return false; 
/* 133 */     Geometry _this = arg0;
/* 135 */     return _this.isSimple();
/*     */   }
/*     */   
/*     */   public static double distance(Geometry arg0, Geometry arg1) {
/* 140 */     if (arg0 == null || arg1 == null)
/* 140 */       return -1.0D; 
/* 141 */     Geometry _this = arg0;
/* 143 */     return _this.distance(arg1);
/*     */   }
/*     */   
/*     */   public static boolean isWithinDistance(Geometry arg0, Geometry arg1, Double arg2) {
/* 148 */     if (arg0 == null || arg1 == null || arg2 == null)
/* 148 */       return false; 
/* 149 */     Geometry _this = arg0;
/* 151 */     return _this.isWithinDistance(arg1, arg2.doubleValue());
/*     */   }
/*     */   
/*     */   public static double area(Geometry arg0) {
/* 156 */     if (arg0 == null)
/* 156 */       return -1.0D; 
/* 157 */     Geometry _this = arg0;
/* 159 */     return _this.getArea();
/*     */   }
/*     */   
/*     */   public static Geometry centroid(Geometry arg0) {
/* 164 */     if (arg0 == null)
/* 164 */       return null; 
/* 165 */     Geometry _this = arg0;
/* 167 */     return (Geometry)_this.getCentroid();
/*     */   }
/*     */   
/*     */   public static Geometry interiorPoint(Geometry arg0) {
/* 172 */     if (arg0 == null)
/* 172 */       return null; 
/* 173 */     Geometry _this = arg0;
/* 175 */     return (Geometry)_this.getInteriorPoint();
/*     */   }
/*     */   
/*     */   public static int dimension(Geometry arg0) {
/* 180 */     if (arg0 == null)
/* 180 */       return -1; 
/* 181 */     Geometry _this = arg0;
/* 183 */     return _this.getDimension();
/*     */   }
/*     */   
/*     */   public static Geometry boundary(Geometry arg0) {
/* 188 */     if (arg0 == null)
/* 188 */       return null; 
/* 189 */     Geometry _this = arg0;
/* 191 */     return _this.getBoundary();
/*     */   }
/*     */   
/*     */   public static int boundaryDimension(Geometry arg0) {
/* 196 */     if (arg0 == null)
/* 196 */       return -1; 
/* 197 */     Geometry _this = arg0;
/* 199 */     return _this.getBoundaryDimension();
/*     */   }
/*     */   
/*     */   public static Geometry envelope(Geometry arg0) {
/* 204 */     if (arg0 == null)
/* 204 */       return null; 
/* 205 */     Geometry _this = arg0;
/* 207 */     return _this.getEnvelope();
/*     */   }
/*     */   
/*     */   public static boolean disjoint(Geometry arg0, Geometry arg1) {
/* 212 */     if (arg0 == null || arg1 == null)
/* 212 */       return false; 
/* 213 */     Geometry _this = arg0;
/* 215 */     return _this.disjoint(arg1);
/*     */   }
/*     */   
/*     */   public static boolean touches(Geometry arg0, Geometry arg1) {
/* 220 */     if (arg0 == null || arg1 == null)
/* 220 */       return false; 
/* 221 */     Geometry _this = arg0;
/* 223 */     return _this.touches(arg1);
/*     */   }
/*     */   
/*     */   public static boolean crosses(Geometry arg0, Geometry arg1) {
/* 228 */     if (arg0 == null || arg1 == null)
/* 228 */       return false; 
/* 229 */     Geometry _this = arg0;
/* 231 */     return _this.crosses(arg1);
/*     */   }
/*     */   
/*     */   public static boolean within(Geometry arg0, Geometry arg1) {
/* 236 */     if (arg0 == null || arg1 == null)
/* 236 */       return false; 
/* 237 */     Geometry _this = arg0;
/* 239 */     return _this.within(arg1);
/*     */   }
/*     */   
/*     */   public static boolean overlaps(Geometry arg0, Geometry arg1) {
/* 244 */     if (arg0 == null || arg1 == null)
/* 244 */       return false; 
/* 245 */     Geometry _this = arg0;
/* 247 */     return _this.overlaps(arg1);
/*     */   }
/*     */   
/*     */   public static boolean relatePattern(Geometry arg0, Geometry arg1, String arg2) {
/* 252 */     if (arg0 == null || arg1 == null || arg2 == null)
/* 252 */       return false; 
/* 253 */     Geometry _this = arg0;
/* 255 */     return _this.relate(arg1, arg2);
/*     */   }
/*     */   
/*     */   public static String relate(Geometry arg0, Geometry arg1) {
/* 260 */     if (arg0 == null || arg1 == null)
/* 260 */       return null; 
/* 261 */     Geometry _this = arg0;
/* 263 */     return _this.relate(arg1).toString();
/*     */   }
/*     */   
/*     */   public static Geometry bufferWithSegments(Geometry arg0, Double arg1, Integer arg2) {
/* 270 */     if (arg0 == null || arg1 == null || arg2 == null)
/* 270 */       return null; 
/* 271 */     Geometry _this = arg0;
/* 273 */     return _this.buffer(arg1.doubleValue(), arg2.intValue());
/*     */   }
/*     */   
/*     */   public static Geometry buffer(Geometry arg0, Double arg1) {
/* 278 */     if (arg0 == null || arg1 == null)
/* 278 */       return null; 
/* 279 */     Geometry _this = arg0;
/* 281 */     return _this.buffer(arg1.doubleValue());
/*     */   }
/*     */   
/*     */   public static Geometry convexHull(Geometry arg0) {
/* 286 */     if (arg0 == null)
/* 286 */       return null; 
/* 287 */     Geometry _this = arg0;
/* 289 */     return _this.convexHull();
/*     */   }
/*     */   
/*     */   public static Geometry intersection(Geometry arg0, Geometry arg1) {
/* 294 */     if (arg0 == null || arg1 == null)
/* 294 */       return null; 
/* 295 */     Geometry _this = arg0;
/* 297 */     return _this.intersection(arg1);
/*     */   }
/*     */   
/*     */   public static Geometry union(Geometry arg0, Geometry arg1) {
/* 302 */     if (arg0 == null || arg1 == null)
/* 302 */       return null; 
/* 303 */     Geometry _this = arg0;
/* 305 */     return _this.union(arg1);
/*     */   }
/*     */   
/*     */   public static Geometry difference(Geometry arg0, Geometry arg1) {
/* 310 */     if (arg0 == null || arg1 == null)
/* 310 */       return null; 
/* 311 */     Geometry _this = arg0;
/* 313 */     return _this.difference(arg1);
/*     */   }
/*     */   
/*     */   public static Geometry symDifference(Geometry arg0, Geometry arg1) {
/* 318 */     if (arg0 == null || arg1 == null)
/* 318 */       return null; 
/* 319 */     Geometry _this = arg0;
/* 321 */     return _this.symDifference(arg1);
/*     */   }
/*     */   
/*     */   public static boolean equalsExactTolerance(Geometry arg0, Geometry arg1, Double arg2) {
/* 326 */     if (arg0 == null || arg1 == null || arg2 == null)
/* 326 */       return false; 
/* 327 */     Geometry _this = arg0;
/* 329 */     return _this.equalsExact(arg1, arg2.doubleValue());
/*     */   }
/*     */   
/*     */   public static boolean equalsExact(Geometry arg0, Geometry arg1) {
/* 334 */     if (arg0 == null || arg1 == null)
/* 334 */       return false; 
/* 335 */     Geometry _this = arg0;
/* 337 */     return _this.equalsExact(arg1);
/*     */   }
/*     */   
/*     */   public static int numGeometries(Geometry arg0) {
/* 342 */     if (!(arg0 instanceof GeometryCollection))
/* 342 */       return 0; 
/* 343 */     GeometryCollection _this = (GeometryCollection)arg0;
/* 345 */     return _this.getNumGeometries();
/*     */   }
/*     */   
/*     */   public static Geometry getGeometryN(Geometry arg0, Integer arg1) {
/* 350 */     if (!(arg0 instanceof GeometryCollection) || arg1 == null)
/* 350 */       return null; 
/* 352 */     GeometryCollection _this = (GeometryCollection)arg0;
/* 354 */     if (arg1.intValue() < 0 || arg1.intValue() >= _this.getNumGeometries())
/* 354 */       return null; 
/* 356 */     return _this.getGeometryN(arg1.intValue());
/*     */   }
/*     */   
/*     */   public static double getX(Geometry arg0) {
/* 361 */     if (!(arg0 instanceof Point))
/* 361 */       return 0.0D; 
/* 362 */     Point _this = (Point)arg0;
/* 364 */     return _this.getX();
/*     */   }
/*     */   
/*     */   public static double getY(Geometry arg0) {
/* 369 */     if (!(arg0 instanceof Point))
/* 369 */       return 0.0D; 
/* 370 */     Point _this = (Point)arg0;
/* 372 */     return _this.getY();
/*     */   }
/*     */   
/*     */   public static boolean isClosed(Geometry arg0) {
/* 377 */     if (!(arg0 instanceof LineString))
/* 377 */       return false; 
/* 378 */     LineString _this = (LineString)arg0;
/* 380 */     return _this.isClosed();
/*     */   }
/*     */   
/*     */   public static Geometry pointN(Geometry arg0, Integer arg1) {
/* 385 */     if (!(arg0 instanceof LineString) || arg1 == null)
/* 385 */       return null; 
/* 386 */     LineString _this = (LineString)arg0;
/* 388 */     if (arg1.intValue() < 0 || arg1.intValue() >= _this.getNumPoints())
/* 388 */       return null; 
/* 389 */     return (Geometry)_this.getPointN(arg1.intValue());
/*     */   }
/*     */   
/*     */   public static Point startPoint(Geometry arg0) {
/* 394 */     if (!(arg0 instanceof LineString))
/* 394 */       return null; 
/* 395 */     LineString _this = (LineString)arg0;
/* 397 */     return _this.getStartPoint();
/*     */   }
/*     */   
/*     */   public static Geometry endPoint(Geometry arg0) {
/* 402 */     if (!(arg0 instanceof LineString))
/* 402 */       return null; 
/* 403 */     LineString _this = (LineString)arg0;
/* 405 */     return (Geometry)_this.getEndPoint();
/*     */   }
/*     */   
/*     */   public static boolean isRing(Geometry arg0) {
/* 410 */     if (!(arg0 instanceof LineString))
/* 410 */       return false; 
/* 411 */     LineString _this = (LineString)arg0;
/* 413 */     return _this.isRing();
/*     */   }
/*     */   
/*     */   public static Geometry exteriorRing(Geometry arg0) {
/* 418 */     if (!(arg0 instanceof Polygon))
/* 418 */       return null; 
/* 419 */     Polygon _this = (Polygon)arg0;
/* 421 */     return (Geometry)_this.getExteriorRing();
/*     */   }
/*     */   
/*     */   public static int numInteriorRing(Geometry arg0) {
/* 426 */     if (!(arg0 instanceof Polygon))
/* 426 */       return 0; 
/* 427 */     Polygon _this = (Polygon)arg0;
/* 429 */     return _this.getNumInteriorRing();
/*     */   }
/*     */   
/*     */   public static Geometry interiorRingN(Geometry arg0, Integer arg1) {
/* 434 */     if (!(arg0 instanceof Polygon) || arg1 == null)
/* 434 */       return null; 
/* 435 */     Polygon _this = (Polygon)arg0;
/* 437 */     if (arg1.intValue() < 0 || arg1.intValue() >= _this.getNumInteriorRing())
/* 437 */       return null; 
/* 439 */     return (Geometry)_this.getInteriorRingN(arg1.intValue());
/*     */   }
/*     */   
/*     */   public static Geometry minimumCircle(Geometry g) {
/* 443 */     if (g == null)
/* 443 */       return null; 
/* 444 */     MinimumBoundingCircle circle = new MinimumBoundingCircle(g);
/* 445 */     return circle.getCircle();
/*     */   }
/*     */   
/*     */   public static Geometry minimumRectangle(Geometry g) {
/* 449 */     if (g == null)
/* 449 */       return null; 
/* 450 */     MinimumDiameter min = new MinimumDiameter(g);
/* 451 */     return min.getMinimumRectangle();
/*     */   }
/*     */   
/*     */   public static Geometry octagonalEnvelope(Geometry arg0) {
/* 455 */     if (arg0 == null)
/* 455 */       return null; 
/* 456 */     OctagonalEnvelope env = new OctagonalEnvelope(arg0);
/* 457 */     return env.toGeometry(arg0.getFactory());
/*     */   }
/*     */   
/*     */   public static Geometry minimumDiameter(Geometry arg0) {
/* 461 */     if (arg0 == null)
/* 461 */       return null; 
/* 462 */     MinimumDiameter minDiameter = new MinimumDiameter(arg0);
/* 463 */     return (Geometry)minDiameter.getDiameter();
/*     */   }
/*     */   
/*     */   public static String strConcat(String s1, String s2) {
/* 470 */     if (s1 == null)
/* 471 */       return s2; 
/* 472 */     if (s2 == null)
/* 473 */       return s1; 
/* 475 */     return s1 + s2;
/*     */   }
/*     */   
/*     */   public static boolean strEndsWith(String s1, String s2) {
/* 480 */     if (s1 == null || s2 == null)
/* 480 */       return false; 
/* 481 */     return s1.endsWith(s2);
/*     */   }
/*     */   
/*     */   public static boolean strStartsWith(String s1, String s2) {
/* 486 */     if (s1 == null || s2 == null)
/* 486 */       return false; 
/* 487 */     return s1.startsWith(s2);
/*     */   }
/*     */   
/*     */   public static boolean strEqualsIgnoreCase(String s1, String s2) {
/* 492 */     if (s1 == null || s2 == null)
/* 492 */       return false; 
/* 493 */     return s1.equalsIgnoreCase(s2);
/*     */   }
/*     */   
/*     */   public static int strIndexOf(String s1, String s2) {
/* 498 */     if (s1 == null || s2 == null)
/* 498 */       return -1; 
/* 499 */     return s1.indexOf(s2);
/*     */   }
/*     */   
/*     */   public static int strLastIndexOf(String s1, String s2) {
/* 504 */     if (s1 == null || s2 == null)
/* 504 */       return -1; 
/* 505 */     return s1.lastIndexOf(s2);
/*     */   }
/*     */   
/*     */   public static int strLength(String s1) {
/* 510 */     if (s1 == null)
/* 510 */       return 0; 
/* 511 */     return s1.length();
/*     */   }
/*     */   
/*     */   public static String strToLowerCase(String s1) {
/* 516 */     if (s1 == null)
/* 516 */       return null; 
/* 517 */     return s1.toLowerCase();
/*     */   }
/*     */   
/*     */   public static String strToUpperCase(String s1) {
/* 522 */     if (s1 == null)
/* 522 */       return null; 
/* 523 */     return s1.toUpperCase();
/*     */   }
/*     */   
/*     */   public static String strCapitalize(String s) {
/* 528 */     if (s == null)
/* 528 */       return null; 
/* 529 */     int strLength = s.length();
/* 530 */     StringBuilder sb = new StringBuilder(strLength);
/* 531 */     boolean titleCaseNext = true;
/* 532 */     for (int i = 0; i < strLength; i++) {
/* 533 */       char ch = s.charAt(i);
/* 534 */       if (Character.isWhitespace(ch)) {
/* 535 */         sb.append(ch);
/* 536 */         titleCaseNext = true;
/* 537 */       } else if (titleCaseNext) {
/* 538 */         sb.append(Character.toTitleCase(ch));
/* 539 */         titleCaseNext = false;
/*     */       } else {
/* 541 */         sb.append(Character.toLowerCase(ch));
/*     */       } 
/*     */     } 
/* 544 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static boolean strMatches(String s1, String s2) {
/* 549 */     if (s1 == null || s2 == null)
/* 549 */       return false; 
/* 550 */     return s1.matches(s2);
/*     */   }
/*     */   
/*     */   public static String strReplace(String s1, String s2, String s3, Boolean bAll) {
/* 555 */     if (s1 == null || s2 == null || s3 == null)
/* 555 */       return null; 
/* 556 */     if (bAll != null && bAll.booleanValue())
/* 557 */       return s1.replaceAll(s2, s3); 
/* 560 */     return s1.replaceFirst(s2, s3);
/*     */   }
/*     */   
/*     */   public static String strSubstring(String s1, Integer beg, Integer end) {
/* 566 */     if (s1 == null || beg == null || end == null)
/* 566 */       return null; 
/* 567 */     if (beg.intValue() < 0 || end.intValue() > s1.length() || beg.intValue() > end.intValue())
/* 567 */       return null; 
/* 568 */     return s1.substring(beg.intValue(), end.intValue());
/*     */   }
/*     */   
/*     */   public static String strSubstringStart(String s1, Integer beg) {
/* 573 */     if (s1 == null || beg == null)
/* 573 */       return null; 
/* 574 */     if (beg.intValue() < 0 || beg.intValue() > s1.length())
/* 574 */       return null; 
/* 575 */     return s1.substring(beg.intValue());
/*     */   }
/*     */   
/*     */   public static String strTrim(String s1) {
/* 580 */     if (s1 == null)
/* 580 */       return null; 
/* 581 */     return s1.trim();
/*     */   }
/*     */   
/*     */   public static double parseDouble(String s) {
/* 592 */     if (s == null)
/* 592 */       return 0.0D; 
/*     */     try {
/* 594 */       return Double.parseDouble(s);
/* 595 */     } catch (NumberFormatException e) {
/* 596 */       return 0.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int parseInt(String s) {
/* 602 */     if (s == null)
/* 602 */       return 0; 
/*     */     try {
/* 604 */       return Integer.parseInt(s);
/* 606 */     } catch (NumberFormatException e) {
/* 608 */       return (int)Math.round(parseDouble(s));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static long parseLong(String s) {
/* 614 */     if (s == null)
/* 614 */       return 0L; 
/*     */     try {
/* 616 */       return Long.parseLong(s);
/* 618 */     } catch (NumberFormatException e) {
/* 620 */       return Math.round(parseDouble(s));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean parseBoolean(String s) {
/* 626 */     if (s == null || s.equalsIgnoreCase("") || s.equalsIgnoreCase("f") || s.equalsIgnoreCase("false") || s.equalsIgnoreCase("0") || s.equalsIgnoreCase("0.0"))
/* 629 */       return false; 
/* 632 */     return true;
/*     */   }
/*     */   
/*     */   public static int roundDouble(Double d) {
/* 637 */     if (d == null)
/* 637 */       return 0; 
/* 638 */     return (int)Math.round(d.doubleValue());
/*     */   }
/*     */   
/*     */   public static double int2ddouble(Integer i) {
/* 643 */     if (i == null)
/* 643 */       return Double.NaN; 
/* 644 */     return i.intValue();
/*     */   }
/*     */   
/*     */   public static boolean int2bbool(Integer i) {
/* 649 */     if (i == null)
/* 649 */       return false; 
/* 650 */     return (i.intValue() == 0);
/*     */   }
/*     */   
/*     */   public static boolean double2bool(Double d) {
/* 655 */     if (d == null)
/* 655 */       return false; 
/* 656 */     return (d.doubleValue() == 0.0D);
/*     */   }
/*     */   
/*     */   public static Object if_then_else(Boolean p, Object a, Object b) {
/* 661 */     if (p != null && p.booleanValue())
/* 662 */       return a; 
/* 664 */     return b;
/*     */   }
/*     */   
/*     */   public static boolean equalTo(Object o1, Object o2) {
/* 674 */     if (o1 == null || o2 == null)
/* 674 */       return false; 
/* 675 */     if (o1.getClass() == o2.getClass())
/* 676 */       return o1.equals(o2); 
/* 677 */     if (o1 instanceof Number && o2 instanceof Number)
/* 679 */       return (((Number)o1).doubleValue() == ((Number)o2).doubleValue()); 
/* 681 */     return o1.toString().equals(o2.toString());
/*     */   }
/*     */   
/*     */   public static boolean notEqualTo(Object o1, Object o2) {
/* 686 */     if (o1 == null || o2 == null)
/* 686 */       return false; 
/* 687 */     return !equalTo(o1, o2);
/*     */   }
/*     */   
/*     */   public static boolean lessThan(Object o1, Object o2) {
/* 692 */     if (o1 == null || o2 == null)
/* 692 */       return false; 
/* 693 */     if (o1 instanceof Integer && o2 instanceof Integer)
/* 695 */       return (((Integer)o1).intValue() < ((Integer)o2).intValue()); 
/* 697 */     if (o1 instanceof Number && o2 instanceof Number)
/* 699 */       return (((Number)o1).doubleValue() < ((Number)o2).doubleValue()); 
/* 701 */     return (o1.toString().compareTo(o2.toString()) == 0);
/*     */   }
/*     */   
/*     */   public static boolean greaterThan(Object o1, Object o2) {
/* 706 */     if (o1 == null || o2 == null)
/* 706 */       return false; 
/* 707 */     if (o1 instanceof Integer && o2 instanceof Integer)
/* 709 */       return (((Integer)o1).intValue() > ((Integer)o2).intValue()); 
/* 711 */     if (o1 instanceof Number && o2 instanceof Number)
/* 713 */       return (((Number)o1).doubleValue() > ((Number)o2).doubleValue()); 
/* 715 */     return (o1.toString().compareTo(o2.toString()) == 2);
/*     */   }
/*     */   
/*     */   public static boolean greaterEqualThan(Object o1, Object o2) {
/* 720 */     if (o1 == null || o2 == null)
/* 720 */       return false; 
/* 721 */     if (o1 instanceof Integer && o2 instanceof Integer)
/* 723 */       return (((Integer)o1).intValue() >= ((Integer)o2).intValue()); 
/* 725 */     if (o1 instanceof Number && o2 instanceof Number)
/* 727 */       return (((Number)o1).doubleValue() >= ((Number)o2).doubleValue()); 
/* 729 */     return (o1.toString().compareTo(o2.toString()) == 2 || o1.toString().compareTo(o2.toString()) == 1);
/*     */   }
/*     */   
/*     */   public static boolean lessEqualThan(Object o1, Object o2) {
/* 737 */     if (o1 == null || o2 == null)
/* 737 */       return false; 
/* 738 */     if (o1 instanceof Integer && o2 instanceof Integer)
/* 740 */       return (((Integer)o1).intValue() <= ((Integer)o2).intValue()); 
/* 742 */     if (o1 instanceof Number && o2 instanceof Number)
/* 744 */       return (((Number)o1).doubleValue() <= ((Number)o2).doubleValue()); 
/* 746 */     return (o1.toString().compareTo(o2.toString()) == 0 || o1.toString().compareTo(o2.toString()) == 1);
/*     */   }
/*     */   
/*     */   public static boolean isLike(String s1, String s2) {
/* 754 */     if (s1 == null || s2 == null)
/* 754 */       return false; 
/* 755 */     return s1.matches(s2);
/*     */   }
/*     */   
/*     */   public static boolean isNull(Object o) {
/* 760 */     return (o == null);
/*     */   }
/*     */   
/*     */   public static boolean between(Object o, Object o_low, Object o_high) {
/* 772 */     return (greaterEqualThan(o, o_low) && lessEqualThan(o, o_high));
/*     */   }
/*     */   
/*     */   public static boolean not(Boolean b) {
/* 778 */     if (b == null)
/* 778 */       return true; 
/* 779 */     return !b.booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean in2(Object s, Object s1, Object s2) {
/* 789 */     return (equalTo(s, s1) || equalTo(s, s2));
/*     */   }
/*     */   
/*     */   public static boolean in3(Object s, Object s1, Object s2, Object s3) {
/* 794 */     return (equalTo(s, s1) || equalTo(s, s2) || equalTo(s, s3));
/*     */   }
/*     */   
/*     */   public static boolean in4(Object s, Object s1, Object s2, Object s3, Object s4) {
/* 799 */     return (equalTo(s, s1) || equalTo(s, s2) || equalTo(s, s3) || equalTo(s, s4));
/*     */   }
/*     */   
/*     */   public static boolean in5(Object s, Object s1, Object s2, Object s3, Object s4, Object s5) {
/* 804 */     return (equalTo(s, s1) || equalTo(s, s2) || equalTo(s, s3) || equalTo(s, s4) || equalTo(s, s5));
/*     */   }
/*     */   
/*     */   public static boolean in6(Object s, Object s1, Object s2, Object s3, Object s4, Object s5, Object s6) {
/* 809 */     return (equalTo(s, s1) || equalTo(s, s2) || equalTo(s, s3) || equalTo(s, s4) || equalTo(s, s5) || equalTo(s, s6));
/*     */   }
/*     */   
/*     */   public static boolean in7(Object s, Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7) {
/* 814 */     return (equalTo(s, s1) || equalTo(s, s2) || equalTo(s, s3) || equalTo(s, s4) || equalTo(s, s5) || equalTo(s, s6) || equalTo(s, s7));
/*     */   }
/*     */   
/*     */   public static boolean in8(Object s, Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8) {
/* 819 */     return (equalTo(s, s1) || equalTo(s, s2) || equalTo(s, s3) || equalTo(s, s4) || equalTo(s, s5) || equalTo(s, s6) || equalTo(s, s7) || equalTo(s, s8));
/*     */   }
/*     */   
/*     */   public static boolean in9(Object s, Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9) {
/* 824 */     return (equalTo(s, s1) || equalTo(s, s2) || equalTo(s, s3) || equalTo(s, s4) || equalTo(s, s5) || equalTo(s, s6) || equalTo(s, s7) || equalTo(s, s8) || equalTo(s, s9));
/*     */   }
/*     */   
/*     */   public static boolean in10(Object s, Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9, Object s10) {
/* 829 */     return (equalTo(s, s1) || equalTo(s, s2) || equalTo(s, s3) || equalTo(s, s4) || equalTo(s, s5) || equalTo(s, s6) || equalTo(s, s7) || equalTo(s, s8) || equalTo(s, s9) || equalTo(s, s10));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\StaticGeometry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */