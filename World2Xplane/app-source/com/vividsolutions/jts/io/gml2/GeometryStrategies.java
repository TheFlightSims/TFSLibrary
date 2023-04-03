/*     */ package com.vividsolutions.jts.io.gml2;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
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
/*     */ import com.vividsolutions.jts.util.StringUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.regex.Pattern;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class GeometryStrategies {
/*  71 */   private static HashMap strategies = loadStrategies();
/*     */   
/*     */   private static HashMap loadStrategies() {
/*  73 */     HashMap<Object, Object> strats = new HashMap<>();
/*  76 */     strats.put("Point".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/*  82 */             if (arg.children.size() != 1)
/*  83 */               throw new SAXException("Cannot create a point without exactly one coordinate"); 
/*  85 */             int srid = GeometryStrategies.getSrid(arg.attrs, gf.getSRID());
/*  87 */             Object c = arg.children.get(0);
/*  88 */             Point p = null;
/*  89 */             if (c instanceof Coordinate) {
/*  90 */               p = gf.createPoint((Coordinate)c);
/*     */             } else {
/*  92 */               p = gf.createPoint((CoordinateSequence)c);
/*     */             } 
/*  94 */             if (p.getSRID() != srid)
/*  95 */               p.setSRID(srid); 
/*  97 */             return p;
/*     */           }
/*     */         });
/* 102 */     strats.put("LineString".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 108 */             if (arg.children.size() < 1)
/* 109 */               throw new SAXException("Cannot create a linestring without atleast two coordinates or one coordinate sequence"); 
/* 111 */             int srid = GeometryStrategies.getSrid(arg.attrs, gf.getSRID());
/* 113 */             LineString ls = null;
/* 114 */             if (arg.children.size() == 1) {
/*     */               try {
/* 117 */                 CoordinateSequence cs = arg.children.get(0);
/* 118 */                 ls = gf.createLineString(cs);
/* 119 */               } catch (ClassCastException e) {
/* 120 */                 throw new SAXException("Cannot create a linestring without atleast two coordinates or one coordinate sequence", e);
/*     */               } 
/*     */             } else {
/*     */               try {
/* 124 */                 Coordinate[] coords = (Coordinate[])arg.children.toArray((Object[])new Coordinate[arg.children.size()]);
/* 125 */                 ls = gf.createLineString(coords);
/* 126 */               } catch (ClassCastException e) {
/* 127 */                 throw new SAXException("Cannot create a linestring without atleast two coordinates or one coordinate sequence", e);
/*     */               } 
/*     */             } 
/* 131 */             if (ls.getSRID() != srid)
/* 132 */               ls.setSRID(srid); 
/* 134 */             return ls;
/*     */           }
/*     */         });
/* 139 */     strats.put("LinearRing".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 145 */             if (arg.children.size() != 1 && arg.children.size() < 4)
/* 146 */               throw new SAXException("Cannot create a linear ring without atleast four coordinates or one coordinate sequence"); 
/* 148 */             int srid = GeometryStrategies.getSrid(arg.attrs, gf.getSRID());
/* 150 */             LinearRing ls = null;
/* 151 */             if (arg.children.size() == 1) {
/*     */               try {
/* 154 */                 CoordinateSequence cs = arg.children.get(0);
/* 155 */                 ls = gf.createLinearRing(cs);
/* 156 */               } catch (ClassCastException e) {
/* 157 */                 throw new SAXException("Cannot create a linear ring without atleast four coordinates or one coordinate sequence", e);
/*     */               } 
/*     */             } else {
/*     */               try {
/* 161 */                 Coordinate[] coords = (Coordinate[])arg.children.toArray((Object[])new Coordinate[arg.children.size()]);
/* 162 */                 ls = gf.createLinearRing(coords);
/* 163 */               } catch (ClassCastException e) {
/* 164 */                 throw new SAXException("Cannot create a linear ring without atleast four coordinates or one coordinate sequence", e);
/*     */               } 
/*     */             } 
/* 168 */             if (ls.getSRID() != srid)
/* 169 */               ls.setSRID(srid); 
/* 171 */             return ls;
/*     */           }
/*     */         });
/* 176 */     strats.put("Polygon".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 182 */             if (arg.children.size() < 1)
/* 183 */               throw new SAXException("Cannot create a polygon without atleast one linear ring"); 
/* 185 */             int srid = GeometryStrategies.getSrid(arg.attrs, gf.getSRID());
/* 187 */             LinearRing outer = arg.children.get(0);
/* 188 */             List<E> t = (arg.children.size() > 1) ? arg.children.subList(1, arg.children.size()) : null;
/* 189 */             LinearRing[] inner = (t == null) ? null : t.<LinearRing>toArray(new LinearRing[t.size()]);
/* 191 */             Polygon p = gf.createPolygon(outer, inner);
/* 193 */             if (p.getSRID() != srid)
/* 194 */               p.setSRID(srid); 
/* 196 */             return p;
/*     */           }
/*     */         });
/* 201 */     strats.put("Box".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 207 */             if (arg.children.size() < 1 || arg.children.size() > 2)
/* 208 */               throw new SAXException("Cannot create a box without either two coords or one coordinate sequence"); 
/* 212 */             Envelope box = null;
/* 213 */             if (arg.children.size() == 1) {
/* 214 */               CoordinateSequence cs = arg.children.get(0);
/* 215 */               box = cs.expandEnvelope(new Envelope());
/*     */             } else {
/* 217 */               box = new Envelope(arg.children.get(0), arg.children.get(1));
/*     */             } 
/* 220 */             return box;
/*     */           }
/*     */         });
/* 225 */     strats.put("MultiPoint".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 231 */             if (arg.children.size() < 1)
/* 232 */               throw new SAXException("Cannot create a multi-point without atleast one point"); 
/* 234 */             int srid = GeometryStrategies.getSrid(arg.attrs, gf.getSRID());
/* 236 */             Point[] pts = (Point[])arg.children.toArray((Object[])new Point[arg.children.size()]);
/* 238 */             MultiPoint mp = gf.createMultiPoint(pts);
/* 240 */             if (mp.getSRID() != srid)
/* 241 */               mp.setSRID(srid); 
/* 243 */             return mp;
/*     */           }
/*     */         });
/* 248 */     strats.put("MultiLineString".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 254 */             if (arg.children.size() < 1)
/* 255 */               throw new SAXException("Cannot create a multi-linestring without atleast one linestring"); 
/* 257 */             int srid = GeometryStrategies.getSrid(arg.attrs, gf.getSRID());
/* 259 */             LineString[] lns = (LineString[])arg.children.toArray((Object[])new LineString[arg.children.size()]);
/* 261 */             MultiLineString mp = gf.createMultiLineString(lns);
/* 263 */             if (mp.getSRID() != srid)
/* 264 */               mp.setSRID(srid); 
/* 266 */             return mp;
/*     */           }
/*     */         });
/* 271 */     strats.put("MultiPolygon".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 277 */             if (arg.children.size() < 1)
/* 278 */               throw new SAXException("Cannot create a multi-polygon without atleast one polygon"); 
/* 280 */             int srid = GeometryStrategies.getSrid(arg.attrs, gf.getSRID());
/* 282 */             Polygon[] plys = (Polygon[])arg.children.toArray((Object[])new Polygon[arg.children.size()]);
/* 284 */             MultiPolygon mp = gf.createMultiPolygon(plys);
/* 286 */             if (mp.getSRID() != srid)
/* 287 */               mp.setSRID(srid); 
/* 289 */             return mp;
/*     */           }
/*     */         });
/* 294 */     strats.put("MultiGeometry".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 300 */             if (arg.children.size() < 1)
/* 301 */               throw new SAXException("Cannot create a multi-polygon without atleast one geometry"); 
/* 303 */             Geometry[] geoms = (Geometry[])arg.children.toArray((Object[])new Geometry[arg.children.size()]);
/* 305 */             GeometryCollection gc = gf.createGeometryCollection(geoms);
/* 307 */             return gc;
/*     */           }
/*     */         });
/* 312 */     strats.put("coordinates".toLowerCase(), new ParseStrategy() {
/* 314 */           private WeakHashMap patterns = new WeakHashMap<>();
/*     */           
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 320 */             if (arg.text == null || "".equals(arg.text))
/* 321 */               throw new SAXException("Cannot create a coordinate sequence without text to parse"); 
/* 323 */             String decimal = ".";
/* 324 */             String coordSeperator = ",";
/* 325 */             String toupleSeperator = " ";
/* 328 */             if (arg.attrs.getIndex("decimal") >= 0) {
/* 329 */               decimal = arg.attrs.getValue("decimal");
/* 330 */             } else if (arg.attrs.getIndex("http://www.opengis.net/gml", "decimal") >= 0) {
/* 331 */               decimal = arg.attrs.getValue("http://www.opengis.net/gml", "decimal");
/*     */             } 
/* 333 */             if (arg.attrs.getIndex("cs") >= 0) {
/* 334 */               coordSeperator = arg.attrs.getValue("cs");
/* 335 */             } else if (arg.attrs.getIndex("http://www.opengis.net/gml", "cs") >= 0) {
/* 336 */               coordSeperator = arg.attrs.getValue("http://www.opengis.net/gml", "cs");
/*     */             } 
/* 338 */             if (arg.attrs.getIndex("ts") >= 0) {
/* 339 */               toupleSeperator = arg.attrs.getValue("ts");
/* 340 */             } else if (arg.attrs.getIndex("http://www.opengis.net/gml", "ts") >= 0) {
/* 341 */               toupleSeperator = arg.attrs.getValue("http://www.opengis.net/gml", "ts");
/*     */             } 
/* 344 */             String t = arg.text.toString();
/* 345 */             t = t.replaceAll("\\s", " ");
/* 347 */             Pattern ptn = (Pattern)this.patterns.get(toupleSeperator);
/* 348 */             if (ptn == null) {
/* 349 */               String ts = new String(toupleSeperator);
/* 350 */               if (ts.indexOf('\\') > -1)
/* 352 */                 ts = ts.replaceAll("\\", "\\\\"); 
/* 354 */               if (ts.indexOf('.') > -1)
/* 356 */                 ts = ts.replaceAll("\\.", "\\\\."); 
/* 358 */               ptn = Pattern.compile(ts);
/* 359 */               this.patterns.put(toupleSeperator, ptn);
/*     */             } 
/* 361 */             String[] touples = ptn.split(t.trim());
/* 363 */             if (touples.length == 0)
/* 364 */               throw new SAXException("Cannot create a coordinate sequence without a touple to parse"); 
/* 367 */             int numNonNullTouples = 0;
/*     */             int i;
/* 368 */             for (i = 0; i < touples.length; i++) {
/* 369 */               if (touples[i] != null && !"".equals(touples[i].trim())) {
/* 370 */                 if (i != numNonNullTouples)
/* 371 */                   touples[numNonNullTouples] = touples[i]; 
/* 373 */                 numNonNullTouples++;
/*     */               } 
/*     */             } 
/* 376 */             for (i = numNonNullTouples; i < touples.length; i++)
/* 377 */               touples[i] = null; 
/* 380 */             if (numNonNullTouples == 0)
/* 381 */               throw new SAXException("Cannot create a coordinate sequence without a non-null touple to parse"); 
/* 383 */             int dim = (StringUtil.split(touples[0], coordSeperator)).length;
/* 384 */             CoordinateSequence cs = gf.getCoordinateSequenceFactory().create(numNonNullTouples, dim);
/* 385 */             dim = cs.getDimension();
/* 387 */             boolean replaceDec = !".".equals(decimal);
/* 389 */             for (int j = 0; j < numNonNullTouples; j++) {
/* 392 */               ptn = (Pattern)this.patterns.get(coordSeperator);
/* 393 */               if (ptn == null) {
/* 394 */                 String ts = new String(coordSeperator);
/* 395 */                 if (ts.indexOf('\\') > -1)
/* 397 */                   ts = ts.replaceAll("\\", "\\\\"); 
/* 399 */                 if (ts.indexOf('.') > -1)
/* 401 */                   ts = ts.replaceAll("\\.", "\\\\."); 
/* 403 */                 ptn = Pattern.compile(ts);
/* 404 */                 this.patterns.put(coordSeperator, ptn);
/*     */               } 
/* 406 */               String[] coords = ptn.split(touples[j]);
/* 408 */               int dimIndex = 0;
/* 409 */               for (int k = 0; k < coords.length && k < dim; k++) {
/* 410 */                 if (coords[k] != null && !"".equals(coords[k].trim())) {
/* 411 */                   double ordinate = Double.parseDouble(replaceDec ? coords[k].replaceAll(decimal, ".") : coords[k]);
/* 412 */                   cs.setOrdinate(j, dimIndex++, ordinate);
/*     */                 } 
/*     */               } 
/* 416 */               for (; dimIndex < dim; cs.setOrdinate(j, dimIndex++, Double.NaN));
/*     */             } 
/* 419 */             return cs;
/*     */           }
/*     */         });
/* 424 */     strats.put("coord".toLowerCase(), new ParseStrategy() {
/*     */           public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 430 */             if (arg.children.size() < 1)
/* 431 */               throw new SAXException("Cannot create a coordinate without atleast one axis"); 
/* 432 */             if (arg.children.size() > 3)
/* 433 */               throw new SAXException("Cannot create a coordinate with more than 3 axis"); 
/* 435 */             Double[] axis = (Double[])arg.children.toArray((Object[])new Double[arg.children.size()]);
/* 436 */             Coordinate c = new Coordinate();
/* 437 */             c.x = axis[0].doubleValue();
/* 438 */             if (axis.length > 1)
/* 439 */               c.y = axis[1].doubleValue(); 
/* 440 */             if (axis.length > 2)
/* 441 */               c.z = axis[2].doubleValue(); 
/* 443 */             return c;
/*     */           }
/*     */         });
/* 447 */     ParseStrategy coord_child = new ParseStrategy() {
/*     */         public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 450 */           if (arg.text == null)
/* 451 */             return null; 
/* 452 */           return new Double(arg.text.toString());
/*     */         }
/*     */       };
/* 457 */     strats.put("X".toLowerCase(), coord_child);
/* 460 */     strats.put("Y".toLowerCase(), coord_child);
/* 463 */     strats.put("Z".toLowerCase(), coord_child);
/* 465 */     ParseStrategy member = new ParseStrategy() {
/*     */         public Object parse(GMLHandler.Handler arg, GeometryFactory gf) throws SAXException {
/* 468 */           if (arg.children.size() != 1)
/* 469 */             throw new SAXException("Geometry Members may only contain one geometry."); 
/* 474 */           return arg.children.get(0);
/*     */         }
/*     */       };
/* 478 */     strats.put("outerBoundaryIs".toLowerCase(), member);
/* 481 */     strats.put("innerBoundaryIs".toLowerCase(), member);
/* 484 */     strats.put("pointMember".toLowerCase(), member);
/* 487 */     strats.put("lineStringMember".toLowerCase(), member);
/* 490 */     strats.put("polygonMember".toLowerCase(), member);
/* 492 */     return strats;
/*     */   }
/*     */   
/*     */   static int getSrid(Attributes attrs, int defaultValue) {
/* 496 */     String srs = null;
/* 497 */     if (attrs.getIndex("srsName") >= 0) {
/* 498 */       srs = attrs.getValue("srsName");
/* 499 */     } else if (attrs.getIndex("http://www.opengis.net/gml", "srsName") >= 0) {
/* 500 */       srs = attrs.getValue("http://www.opengis.net/gml", "srsName");
/*     */     } 
/* 502 */     if (srs != null) {
/* 503 */       srs = srs.trim();
/* 504 */       if (srs != null && !"".equals(srs))
/*     */         try {
/* 506 */           return Integer.parseInt(srs);
/* 507 */         } catch (NumberFormatException e) {
/* 509 */           int index = srs.lastIndexOf('#');
/* 510 */           if (index > -1)
/* 511 */             srs = srs.substring(index); 
/*     */           try {
/* 513 */             return Integer.parseInt(srs);
/* 514 */           } catch (NumberFormatException e2) {}
/*     */         }  
/*     */     } 
/* 521 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public static ParseStrategy findStrategy(String uri, String localName) {
/* 532 */     return (localName == null) ? null : (ParseStrategy)strategies.get(localName.toLowerCase());
/*     */   }
/*     */   
/*     */   static interface ParseStrategy {
/*     */     Object parse(GMLHandler.Handler param1Handler, GeometryFactory param1GeometryFactory) throws SAXException;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\gml2\GeometryStrategies.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */