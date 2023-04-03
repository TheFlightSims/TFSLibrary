/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public final class ExpressionDOMParser {
/*  59 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*     */   private FilterFactory2 ff;
/*     */   
/*  65 */   private static GeometryFactory gfac = new GeometryFactory();
/*     */   
/*     */   private static final int GML_BOX = 1;
/*     */   
/*     */   private static final int GML_POLYGON = 2;
/*     */   
/*     */   private static final int GML_LINESTRING = 3;
/*     */   
/*     */   private static final int GML_POINT = 4;
/*     */   
/*     */   private static final int NUM_BOX_COORDS = 5;
/*     */   
/*     */   private ExpressionDOMParser() {
/*  86 */     this(CommonFactoryFinder.getFilterFactory2(null));
/*  87 */     LOGGER.finer("made new logic factory");
/*     */   }
/*     */   
/*     */   public ExpressionDOMParser(FilterFactory2 factory) {
/*  91 */     this.ff = (factory != null) ? factory : CommonFactoryFinder.getFilterFactory2(null);
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory2 factory) {
/*  95 */     this.ff = factory;
/*     */   }
/*     */   
/*     */   private static NamespaceSupport getNameSpaces(Node node) {
/* 101 */     NamespaceSupport namespaces = new NamespaceSupport();
/* 102 */     while (node != null) {
/* 104 */       NamedNodeMap atts = node.getAttributes();
/* 106 */       if (atts != null)
/* 107 */         for (int i = 0; i < atts.getLength(); i++) {
/* 108 */           Node att = atts.item(i);
/* 110 */           if (att.getNamespaceURI() != null && att.getNamespaceURI().equals("http://www.w3.org/2000/xmlns/") && namespaces.getURI(att.getLocalName()) == null)
/* 113 */             namespaces.declarePrefix(att.getLocalName(), att.getNodeValue()); 
/*     */         }  
/* 118 */       node = node.getParentNode();
/*     */     } 
/* 121 */     return namespaces;
/*     */   }
/*     */   
/*     */   public static Expression parseExpression(Node root) {
/* 130 */     ExpressionDOMParser parser = new ExpressionDOMParser();
/* 131 */     return parser.expression(root);
/*     */   }
/*     */   
/*     */   public Expression expression(Node root) {
/* 141 */     LOGGER.finer("parsingExpression " + root.getLocalName());
/* 145 */     if (root == null || root.getNodeType() != 1) {
/* 146 */       LOGGER.finer("bad node input ");
/* 148 */       return null;
/*     */     } 
/* 151 */     LOGGER.finer("processing root " + root.getLocalName());
/* 153 */     Node child = root;
/* 155 */     String childName = (child.getLocalName() != null) ? child.getLocalName() : child.getNodeName();
/* 158 */     if (childName.indexOf(':') != -1)
/* 161 */       childName = childName.substring(childName.indexOf(':') + 1); 
/* 164 */     if (childName.equalsIgnoreCase("Literal")) {
/* 165 */       LOGGER.finer("processing literal " + child);
/* 167 */       NodeList kidList = child.getChildNodes();
/* 168 */       LOGGER.finest("literal elements (" + kidList.getLength() + ") " + kidList.toString());
/* 171 */       for (int i = 0; i < kidList.getLength(); i++) {
/* 172 */         Node kid = kidList.item(i);
/* 173 */         LOGGER.finest("kid " + i + " " + kid);
/* 175 */         if (kid == null) {
/* 176 */           LOGGER.finest("Skipping ");
/*     */         } else {
/* 181 */           if (kid.getNodeValue() == null) {
/* 186 */             LOGGER.finer("node " + kid.getNodeValue() + " namespace " + kid.getNamespaceURI());
/* 188 */             LOGGER.fine("a literal gml string?");
/*     */             try {
/* 191 */               Geometry geom = parseGML(kid);
/* 193 */               if (geom != null) {
/* 194 */                 LOGGER.finer("built a " + geom.getGeometryType() + " from gml");
/* 196 */                 LOGGER.finer("\tpoints: " + geom.getNumPoints());
/*     */               } else {
/* 198 */                 LOGGER.finer("got a null geometry back from gml parser");
/*     */               } 
/* 202 */               return (Expression)this.ff.literal(geom);
/* 203 */             } catch (IllegalFilterException ife) {
/* 204 */               LOGGER.warning("Problem building GML/JTS object: " + ife);
/* 208 */               return null;
/*     */             } 
/*     */           } 
/* 212 */           if (kid.getNodeType() != 4 && kid.getNodeValue().trim().length() == 0) {
/* 213 */             LOGGER.finest("empty text element");
/*     */           } else {
/* 237 */             String nodeValue = kid.getNodeValue();
/* 238 */             LOGGER.finer("processing " + nodeValue);
/*     */             try {
/* 242 */               return (Expression)this.ff.literal(nodeValue);
/* 243 */             } catch (IllegalFilterException ife) {
/* 244 */               LOGGER.finer("Unable to build expression " + ife);
/* 245 */               return null;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 249 */       return (Expression)this.ff.literal("");
/*     */     } 
/* 252 */     if (childName.equalsIgnoreCase("add"))
/*     */       try {
/* 254 */         LOGGER.fine("processing an Add");
/* 258 */         Node value = child.getFirstChild();
/* 260 */         while (value.getNodeType() != 1)
/* 261 */           value = value.getNextSibling(); 
/* 264 */         LOGGER.finer("add left value -> " + value + "<-");
/* 265 */         Expression left = parseExpression(value);
/* 266 */         value = value.getNextSibling();
/* 268 */         while (value.getNodeType() != 1)
/* 269 */           value = value.getNextSibling(); 
/* 272 */         LOGGER.finer("add right value -> " + value + "<-");
/* 273 */         Expression right = parseExpression(value);
/* 275 */         return (Expression)this.ff.add(left, right);
/* 276 */       } catch (IllegalFilterException ife) {
/* 277 */         LOGGER.warning("Unable to build expression " + ife);
/* 278 */         return null;
/*     */       }  
/* 282 */     if (childName.equalsIgnoreCase("sub"))
/*     */       try {
/* 285 */         Node value = child.getFirstChild();
/* 287 */         while (value.getNodeType() != 1)
/* 288 */           value = value.getNextSibling(); 
/* 290 */         LOGGER.finer("add left value -> " + value + "<-");
/* 291 */         Expression left = parseExpression(value);
/* 292 */         value = value.getNextSibling();
/* 294 */         while (value.getNodeType() != 1)
/* 295 */           value = value.getNextSibling(); 
/* 298 */         LOGGER.finer("add right value -> " + value + "<-");
/* 299 */         Expression right = parseExpression(value);
/* 301 */         return (Expression)this.ff.subtract(left, right);
/* 302 */       } catch (IllegalFilterException ife) {
/* 303 */         LOGGER.warning("Unable to build expression " + ife);
/* 305 */         return null;
/*     */       }  
/* 309 */     if (childName.equalsIgnoreCase("mul"))
/*     */       try {
/* 312 */         Node value = child.getFirstChild();
/* 314 */         while (value.getNodeType() != 1)
/* 315 */           value = value.getNextSibling(); 
/* 318 */         LOGGER.finer("add left value -> " + value + "<-");
/* 319 */         Expression left = parseExpression(value);
/* 320 */         value = value.getNextSibling();
/* 322 */         while (value.getNodeType() != 1)
/* 323 */           value = value.getNextSibling(); 
/* 326 */         LOGGER.finer("add right value -> " + value + "<-");
/* 327 */         Expression right = parseExpression(value);
/* 329 */         return (Expression)this.ff.multiply(left, right);
/* 330 */       } catch (IllegalFilterException ife) {
/* 331 */         LOGGER.warning("Unable to build expression " + ife);
/* 333 */         return null;
/*     */       }  
/* 337 */     if (childName.equalsIgnoreCase("div"))
/*     */       try {
/* 339 */         Node value = child.getFirstChild();
/* 341 */         while (value.getNodeType() != 1)
/* 342 */           value = value.getNextSibling(); 
/* 345 */         LOGGER.finer("add left value -> " + value + "<-");
/* 346 */         Expression left = parseExpression(value);
/* 347 */         value = value.getNextSibling();
/* 349 */         while (value.getNodeType() != 1)
/* 350 */           value = value.getNextSibling(); 
/* 353 */         LOGGER.finer("add right value -> " + value + "<-");
/* 354 */         Expression right = parseExpression(value);
/* 356 */         return (Expression)this.ff.divide(left, right);
/* 357 */       } catch (IllegalFilterException ife) {
/* 358 */         LOGGER.warning("Unable to build expression " + ife);
/* 360 */         return null;
/*     */       }  
/* 364 */     if (childName.equalsIgnoreCase("PropertyName"))
/*     */       try {
/* 367 */         String value = child.getFirstChild().getNodeValue();
/* 368 */         value = (value != null) ? value.trim() : value;
/* 369 */         PropertyName attribute = this.ff.property(value, getNameSpaces(root));
/* 372 */         return (Expression)attribute;
/* 373 */       } catch (IllegalFilterException ife) {
/* 374 */         LOGGER.warning("Unable to build expression: " + ife);
/* 376 */         return null;
/*     */       }  
/* 380 */     if (childName.equalsIgnoreCase("Function")) {
/* 381 */       Element param = (Element)child;
/* 383 */       NamedNodeMap map = param.getAttributes();
/* 384 */       String funcName = null;
/* 386 */       for (int k = 0; k < map.getLength(); k++) {
/* 387 */         String res = map.item(k).getNodeValue();
/* 388 */         String name = map.item(k).getLocalName();
/* 390 */         if (name == null)
/* 391 */           name = map.item(k).getNodeName(); 
/* 393 */         if (name.indexOf(':') != -1)
/* 396 */           name = name.substring(name.indexOf(':') + 1); 
/* 399 */         LOGGER.fine("attribute " + name + " with value of " + res);
/* 401 */         if (name.equalsIgnoreCase("name"))
/* 402 */           funcName = res; 
/*     */       } 
/* 406 */       if (funcName == null) {
/* 407 */         LOGGER.severe("failed to find a function name in " + child);
/* 408 */         return null;
/*     */       } 
/* 411 */       ArrayList<Expression> args = new ArrayList<Expression>();
/* 412 */       Node value = child.getFirstChild();
/* 414 */       label140: while (value != null) {
/* 415 */         while (value.getNodeType() != 1) {
/* 416 */           value = value.getNextSibling();
/* 417 */           if (value == null)
/*     */             break label140; 
/*     */         } 
/* 420 */         args.add(parseExpression(value));
/* 421 */         value = value.getNextSibling();
/*     */       } 
/* 423 */       Expression[] array = args.<Expression>toArray(new Expression[0]);
/* 424 */       return (Expression)this.ff.function(funcName, array);
/*     */     } 
/* 427 */     if (child.getNodeType() == 3) {
/* 428 */       LOGGER.finer("processing a text node " + root.getNodeValue());
/* 430 */       String nodeValue = root.getNodeValue();
/* 431 */       LOGGER.finer("Text name " + nodeValue);
/*     */       try {
/* 436 */         Integer intLiteral = new Integer(nodeValue);
/* 438 */         return (Expression)this.ff.literal(intLiteral);
/* 439 */       } catch (NumberFormatException e) {
/*     */         try {
/* 444 */           Double doubleLit = new Double(nodeValue);
/* 446 */           return (Expression)this.ff.literal(doubleLit);
/* 447 */         } catch (NumberFormatException numberFormatException) {
/* 451 */           return (Expression)this.ff.literal(nodeValue);
/*     */         } 
/* 452 */       } catch (IllegalFilterException ife) {
/* 453 */         LOGGER.finer("Unable to build expression " + ife);
/*     */       } 
/*     */     } 
/* 457 */     return null;
/*     */   }
/*     */   
/*     */   public static Geometry parseGML(Node root) {
/* 466 */     ExpressionDOMParser parser = new ExpressionDOMParser();
/* 467 */     return parser.gml(root);
/*     */   }
/*     */   
/*     */   public Geometry gml(Node root) {
/* 473 */     Node srsNameNode = root.getAttributes().getNamedItem("srsName");
/* 474 */     CoordinateReferenceSystem crs = null;
/* 475 */     if (srsNameNode != null) {
/* 476 */       String srs = srsNameNode.getTextContent();
/*     */       try {
/* 478 */         crs = CRS.decode(srs);
/* 479 */       } catch (Exception e) {
/* 480 */         LOGGER.warning("Failed to parse the specified SRS " + srs);
/*     */       } 
/*     */     } 
/* 485 */     Geometry g = _gml(root);
/* 488 */     if (crs != null)
/* 489 */       g.setUserData(crs); 
/* 492 */     return g;
/*     */   }
/*     */   
/*     */   private Geometry _gml(Node root) {
/* 502 */     LOGGER.finer("processing gml " + root);
/* 505 */     int type = 0;
/* 506 */     Node child = root;
/* 513 */     String childName = child.getNodeName();
/* 514 */     if (childName == null)
/* 516 */       childName = child.getLocalName(); 
/* 518 */     if (!childName.startsWith("gml:"))
/* 520 */       childName = "gml:" + childName; 
/* 523 */     if (childName.equalsIgnoreCase("gml:box")) {
/* 524 */       type = 1;
/* 525 */       List<Coordinate> coordList = parseCoords(child);
/* 527 */       Envelope env = new Envelope();
/* 529 */       for (int i = 0; i < coordList.size(); i++)
/* 530 */         env.expandToInclude(coordList.get(i)); 
/* 532 */       Coordinate[] coords = new Coordinate[5];
/* 533 */       coords[0] = new Coordinate(env.getMinX(), env.getMinY());
/* 534 */       coords[1] = new Coordinate(env.getMinX(), env.getMaxY());
/* 535 */       coords[2] = new Coordinate(env.getMaxX(), env.getMaxY());
/* 536 */       coords[3] = new Coordinate(env.getMaxX(), env.getMinY());
/* 537 */       coords[4] = new Coordinate(env.getMinX(), env.getMinY());
/* 540 */       LinearRing ring = null;
/*     */       try {
/* 543 */         ring = gfac.createLinearRing(coords);
/* 544 */       } catch (TopologyException tope) {
/* 545 */         LOGGER.fine("Topology Exception in GMLBox" + tope);
/* 547 */         return null;
/*     */       } 
/* 549 */       return (Geometry)gfac.createPolygon(ring, null);
/*     */     } 
/* 553 */     if (childName.equalsIgnoreCase("gml:polygonmember") || childName.equalsIgnoreCase("gml:pointmember") || childName.equalsIgnoreCase("gml:linestringmember") || childName.equalsIgnoreCase("gml:linearringmember"))
/* 558 */       for (int i = 0; i < child.getChildNodes().getLength(); i++) {
/* 559 */         Node newChild = child.getChildNodes().item(i);
/* 560 */         if (newChild.getNodeType() == 1) {
/* 561 */           childName = newChild.getNodeName();
/* 562 */           if (!childName.startsWith("gml:"))
/* 563 */             childName = "gml:" + childName; 
/* 565 */           root = newChild;
/* 566 */           child = newChild;
/*     */           break;
/*     */         } 
/*     */       }  
/* 572 */     if (childName.equalsIgnoreCase("gml:polygon")) {
/* 573 */       LOGGER.finer("polygon");
/* 574 */       type = 2;
/* 576 */       LinearRing outer = null;
/* 577 */       List<LinearRing> inner = new ArrayList();
/* 578 */       NodeList kids = root.getChildNodes();
/* 580 */       for (int i = 0; i < kids.getLength(); i++) {
/* 581 */         Node kid = kids.item(i);
/* 582 */         LOGGER.finer("doing " + kid);
/* 584 */         String kidName = kid.getNodeName();
/* 585 */         if (kidName == null)
/* 587 */           kidName = child.getLocalName(); 
/* 589 */         if (!kidName.startsWith("gml:"))
/* 591 */           kidName = "gml:" + kidName; 
/* 595 */         if (kidName.equalsIgnoreCase("gml:outerBoundaryIs"))
/* 596 */           outer = (LinearRing)parseGML(kid); 
/* 599 */         if (kidName.equalsIgnoreCase("gml:innerBoundaryIs"))
/* 600 */           inner.add((LinearRing)parseGML(kid)); 
/*     */       } 
/* 604 */       if (inner.size() > 0)
/* 605 */         return (Geometry)gfac.createPolygon(outer, inner.<LinearRing>toArray(new LinearRing[0])); 
/* 608 */       return (Geometry)gfac.createPolygon(outer, null);
/*     */     } 
/* 612 */     if (childName.equalsIgnoreCase("gml:outerBoundaryIs") || childName.equalsIgnoreCase("gml:innerBoundaryIs")) {
/* 614 */       LOGGER.finer("Boundary layer");
/* 616 */       NodeList kids = ((Element)child).getElementsByTagName("gml:LinearRing");
/* 618 */       if (kids.getLength() == 0)
/* 619 */         kids = ((Element)child).getElementsByTagName("LinearRing"); 
/* 620 */       return parseGML(kids.item(0));
/*     */     } 
/* 623 */     if (childName.equalsIgnoreCase("gml:linearRing")) {
/* 624 */       LOGGER.finer("LinearRing");
/* 625 */       List coordList = parseCoords(child);
/* 627 */       LinearRing ring = null;
/*     */       try {
/* 630 */         ring = gfac.createLinearRing((Coordinate[])coordList.toArray((Object[])new Coordinate[0]));
/* 632 */       } catch (TopologyException te) {
/* 633 */         LOGGER.finer("Topology Exception build linear ring: " + te);
/* 635 */         return null;
/*     */       } 
/* 638 */       return (Geometry)ring;
/*     */     } 
/* 641 */     if (childName.equalsIgnoreCase("gml:linestring")) {
/* 642 */       LOGGER.finer("linestring");
/* 643 */       type = 3;
/* 644 */       List coordList = parseCoords(child);
/* 646 */       LineString line = null;
/* 647 */       line = gfac.createLineString((Coordinate[])coordList.toArray((Object[])new Coordinate[0]));
/* 650 */       return (Geometry)line;
/*     */     } 
/* 653 */     if (childName.equalsIgnoreCase("gml:point")) {
/* 654 */       LOGGER.finer("point");
/* 655 */       type = 4;
/* 656 */       List<Coordinate> coordList = parseCoords(child);
/* 658 */       Point point = null;
/* 659 */       point = gfac.createPoint(coordList.get(0));
/* 661 */       return (Geometry)point;
/*     */     } 
/* 664 */     if (childName.toLowerCase().startsWith("gml:multipolygon") || childName.toLowerCase().startsWith("gml:multilinestring") || childName.toLowerCase().startsWith("gml:multipoint")) {
/* 668 */       List<Geometry> multi = new ArrayList();
/* 671 */       NodeList kids = child.getChildNodes();
/* 673 */       for (int i = 0; i < kids.getLength(); i++) {
/* 674 */         if (kids.item(i).getNodeType() == 1)
/* 675 */           multi.add(parseGML(kids.item(i))); 
/*     */       } 
/* 679 */       if (childName.toLowerCase().startsWith("gml:multipolygon")) {
/* 680 */         LOGGER.finer("MultiPolygon");
/* 681 */         return (Geometry)gfac.createMultiPolygon(multi.<Polygon>toArray(new Polygon[0]));
/*     */       } 
/* 684 */       if (childName.toLowerCase().startsWith("gml:multilinestring")) {
/* 685 */         LOGGER.finer("MultiLineString");
/* 686 */         return (Geometry)gfac.createMultiLineString(multi.<LineString>toArray(new LineString[0]));
/*     */       } 
/* 690 */       LOGGER.finer("MultiPoint");
/* 691 */       return (Geometry)gfac.createMultiPoint(multi.<Point>toArray(new Point[0]));
/*     */     } 
/* 697 */     return null;
/*     */   }
/*     */   
/*     */   public static List parseCoords(Node root) {
/* 708 */     ExpressionDOMParser parser = new ExpressionDOMParser();
/* 709 */     return parser.coords(root);
/*     */   }
/*     */   
/*     */   public List coords(Node root) {
/* 718 */     LOGGER.finer("parsing coordinate(s) " + root);
/* 720 */     List<Coordinate> clist = new ArrayList();
/* 721 */     NodeList kids = root.getChildNodes();
/* 723 */     for (int i = 0; i < kids.getLength(); i++) {
/* 724 */       Node child = kids.item(i);
/* 725 */       LOGGER.finer("doing " + child);
/* 730 */       String childName = child.getNodeName();
/* 731 */       if (childName == null)
/* 733 */         childName = child.getLocalName(); 
/* 735 */       if (!childName.startsWith("gml:"))
/* 737 */         childName = "gml:" + childName; 
/* 739 */       if (childName.equalsIgnoreCase("gml:coord")) {
/* 744 */         Coordinate c = new Coordinate();
/* 745 */         NodeList grandChildren = child.getChildNodes();
/* 747 */         for (int t = 0; t < grandChildren.getLength(); t++) {
/* 749 */           Node grandChild = grandChildren.item(t);
/* 750 */           String grandChildName = grandChild.getNodeName();
/* 751 */           if (grandChildName == null)
/* 752 */             grandChildName = grandChild.getLocalName(); 
/* 753 */           if (!grandChildName.startsWith("gml:"))
/* 754 */             grandChildName = "gml:" + grandChildName; 
/* 756 */           if (grandChildName.equalsIgnoreCase("gml:x")) {
/* 758 */             c.x = Double.parseDouble(grandChild.getChildNodes().item(0).getNodeValue().trim());
/* 760 */           } else if (grandChildName.equalsIgnoreCase("gml:y")) {
/* 762 */             c.y = Double.parseDouble(grandChild.getChildNodes().item(0).getNodeValue().trim());
/* 764 */           } else if (grandChildName.equalsIgnoreCase("gml:z")) {
/* 766 */             c.z = Double.parseDouble(grandChild.getChildNodes().item(0).getNodeValue().trim());
/*     */           } 
/*     */         } 
/* 769 */         clist.add(c);
/*     */       } 
/* 773 */       if (childName.equalsIgnoreCase("gml:coordinates")) {
/* 774 */         LOGGER.finer("coordinates " + child.getFirstChild().getNodeValue());
/* 777 */         NodeList grandKids = child.getChildNodes();
/* 779 */         for (int k = 0; k < grandKids.getLength(); k++) {
/* 780 */           Node grandKid = grandKids.item(k);
/* 782 */           if (grandKid.getNodeValue() != null)
/* 786 */             if (grandKid.getNodeValue().trim().length() != 0) {
/* 790 */               String outer = grandKid.getNodeValue().trim();
/* 792 */               StringTokenizer ost = new StringTokenizer(outer, " \n\t");
/* 794 */               while (ost.hasMoreTokens()) {
/* 795 */                 String internal = ost.nextToken();
/* 796 */                 StringTokenizer ist = new StringTokenizer(internal, ",");
/* 797 */                 double xCoord = Double.parseDouble(ist.nextToken());
/* 798 */                 double yCoord = Double.parseDouble(ist.nextToken());
/* 799 */                 double zCoord = Double.NaN;
/* 801 */                 if (ist.hasMoreTokens())
/* 802 */                   zCoord = Double.parseDouble(ist.nextToken()); 
/* 805 */                 clist.add(new Coordinate(xCoord, yCoord, zCoord));
/*     */               } 
/*     */             }  
/*     */         } 
/*     */       } 
/*     */     } 
/* 811 */     return clist;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\ExpressionDOMParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */