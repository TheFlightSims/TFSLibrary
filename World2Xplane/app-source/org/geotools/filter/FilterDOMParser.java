/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.geometry.jts.JTS;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public final class FilterDOMParser {
/*  58 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*  61 */   private static final FilterFactory2 FILTER_FACT = CommonFactoryFinder.getFilterFactory2(null);
/*     */   
/*     */   private static final int NUM_BETWEEN_CHILDREN = 3;
/*     */   
/*  67 */   private static Map comparisions = new HashMap<Object, Object>();
/*     */   
/*  70 */   private static Map spatial = new HashMap<Object, Object>();
/*     */   
/*  73 */   private static Map logical = new HashMap<Object, Object>();
/*     */   
/*     */   static {
/*  76 */     comparisions.put("PropertyIsEqualTo", new Integer(14));
/*  78 */     comparisions.put("PropertyIsNotEqualTo", new Integer(23));
/*  80 */     comparisions.put("PropertyIsGreaterThan", new Integer(16));
/*  82 */     comparisions.put("PropertyIsGreaterThanOrEqualTo", new Integer(18));
/*  84 */     comparisions.put("PropertyIsLessThan", new Integer(15));
/*  86 */     comparisions.put("PropertyIsLessThanOrEqualTo", new Integer(17));
/*  88 */     comparisions.put("PropertyIsLike", new Integer(20));
/*  89 */     comparisions.put("PropertyIsNull", new Integer(21));
/*  90 */     comparisions.put("PropertyIsBetween", new Integer(19));
/*  92 */     comparisions.put("FeatureId", new Integer(22));
/*  94 */     spatial.put("Equals", new Integer(5));
/*  95 */     spatial.put("Disjoint", new Integer(6));
/*  96 */     spatial.put("Intersects", new Integer(7));
/*  98 */     spatial.put("Touches", new Integer(8));
/*  99 */     spatial.put("Crosses", new Integer(9));
/* 100 */     spatial.put("Within", new Integer(10));
/* 101 */     spatial.put("Contains", new Integer(11));
/* 102 */     spatial.put("Overlaps", new Integer(12));
/* 103 */     spatial.put("BBOX", new Integer(4));
/* 106 */     spatial.put("Beyond", new Integer(13));
/* 107 */     spatial.put("DWithin", new Integer(24));
/* 109 */     logical.put("And", new Integer(2));
/* 110 */     logical.put("Or", new Integer(1));
/* 111 */     logical.put("Not", new Integer(3));
/*     */   }
/*     */   
/*     */   public static Filter parseFilter(Node root) {
/* 131 */     ExpressionDOMParser expressionDOMParser = new ExpressionDOMParser(FILTER_FACT);
/* 134 */     LOGGER.finer("parsingFilter " + root.getLocalName());
/* 138 */     if (root == null || root.getNodeType() != 1) {
/* 139 */       LOGGER.finest("bad node input ");
/* 141 */       return null;
/*     */     } 
/* 144 */     LOGGER.finest("processing root " + root.getLocalName() + " " + root.getNodeName());
/* 146 */     Node child = root;
/* 147 */     String childName = child.getLocalName();
/* 148 */     if (childName == null)
/* 149 */       childName = child.getNodeName(); 
/* 151 */     if (childName.indexOf(':') != -1)
/* 154 */       childName = childName.substring(childName.indexOf(':') + 1); 
/* 157 */     LOGGER.finest("looking up " + childName);
/* 159 */     if (comparisions.containsKey(childName)) {
/* 160 */       LOGGER.finer("a comparision filter " + childName);
/*     */       try {
/* 165 */         short type = ((Integer)comparisions.get(childName)).shortValue();
/* 167 */         LOGGER.finer("type is " + type);
/* 169 */         if (type == 22) {
/* 170 */           Set<FeatureId> ids = new HashSet<FeatureId>();
/* 171 */           Element fidElement = (Element)child;
/* 172 */           ids.add(FILTER_FACT.featureId(fidElement.getAttribute("fid")));
/* 174 */           Node sibling = fidElement.getNextSibling();
/* 176 */           while (sibling != null) {
/* 177 */             LOGGER.finer("Parsing another FidFilter");
/* 179 */             if (sibling.getNodeType() == 1) {
/* 180 */               fidElement = (Element)sibling;
/* 182 */               String fidElementName = fidElement.getLocalName();
/* 183 */               if (fidElementName == null)
/* 184 */                 fidElementName = fidElement.getNodeName(); 
/* 186 */               if (fidElementName.indexOf(':') != -1)
/* 189 */                 fidElementName = fidElementName.substring(fidElementName.indexOf(':') + 1); 
/* 191 */               if ("FeatureId".equals(fidElementName))
/* 192 */                 ids.add(FILTER_FACT.featureId(fidElement.getAttribute("fid"))); 
/*     */             } 
/* 196 */             sibling = sibling.getNextSibling();
/*     */           } 
/* 199 */           return (Filter)FILTER_FACT.id(ids);
/*     */         } 
/* 200 */         if (type == 19) {
/* 203 */           NodeList kids = child.getChildNodes();
/* 205 */           if (kids.getLength() < 3)
/* 206 */             throw new IllegalFilterException("wrong number of children in Between filter: expected 3 got " + kids.getLength()); 
/* 211 */           Node node = child.getFirstChild();
/* 213 */           while (node.getNodeType() != 1)
/* 214 */             node = node.getNextSibling(); 
/* 221 */           LOGGER.finer("add middle value -> " + node + "<-");
/* 222 */           Expression middle = expressionDOMParser.expression(node);
/* 223 */           Expression lower = null;
/* 224 */           Expression upper = null;
/* 226 */           for (int i = 0; i < kids.getLength(); i++) {
/* 227 */             Node kid = kids.item(i);
/* 229 */             String kidName = (kid.getLocalName() != null) ? kid.getLocalName() : kid.getNodeName();
/* 230 */             if (kidName.indexOf(':') != -1)
/* 233 */               kidName = kidName.substring(kidName.indexOf(':') + 1); 
/* 235 */             if (kidName.equalsIgnoreCase("LowerBoundary")) {
/* 236 */               node = kid.getFirstChild();
/* 238 */               while (node.getNodeType() != 1)
/* 239 */                 node = node.getNextSibling(); 
/* 242 */               LOGGER.finer("add left value -> " + node + "<-");
/* 243 */               lower = expressionDOMParser.expression(node);
/*     */             } 
/* 246 */             if (kidName.equalsIgnoreCase("UpperBoundary")) {
/* 247 */               node = kid.getFirstChild();
/* 249 */               while (node.getNodeType() != 1)
/* 250 */                 node = node.getNextSibling(); 
/* 253 */               LOGGER.finer("add right value -> " + node + "<-");
/* 254 */               upper = expressionDOMParser.expression(node);
/*     */             } 
/*     */           } 
/* 258 */           return (Filter)FILTER_FACT.between(middle, lower, upper);
/*     */         } 
/* 259 */         if (type == 20) {
/* 260 */           String wildcard = null;
/* 261 */           String single = null;
/* 262 */           String escape = null;
/* 263 */           String pattern = null;
/* 264 */           Expression expression = null;
/* 265 */           NodeList map = child.getChildNodes();
/* 267 */           for (int i = 0; i < map.getLength(); i++) {
/* 268 */             Node kid = map.item(i);
/* 270 */             if (kid != null && kid.getNodeType() == 1) {
/* 275 */               String res = (kid.getLocalName() != null) ? kid.getLocalName() : kid.getNodeName();
/* 276 */               if (res.indexOf(':') != -1)
/* 279 */                 res = res.substring(res.indexOf(':') + 1); 
/* 282 */               if (res.equalsIgnoreCase("PropertyName"))
/* 283 */                 expression = expressionDOMParser.expression(kid); 
/* 286 */               if (res.equalsIgnoreCase("Literal"))
/* 287 */                 pattern = expressionDOMParser.expression(kid).toString(); 
/*     */             } 
/*     */           } 
/* 292 */           NamedNodeMap kids = child.getAttributes();
/* 294 */           for (int j = 0; j < kids.getLength(); j++) {
/* 295 */             Node kid = kids.item(j);
/* 298 */             String res = (kid.getLocalName() != null) ? kid.getLocalName() : kid.getNodeName();
/* 299 */             if (res.indexOf(':') != -1)
/* 302 */               res = res.substring(res.indexOf(':') + 1); 
/* 304 */             if (res.equalsIgnoreCase("wildCard"))
/* 305 */               wildcard = kid.getNodeValue(); 
/* 308 */             if (res.equalsIgnoreCase("singleChar"))
/* 309 */               single = kid.getNodeValue(); 
/* 312 */             if (res.equalsIgnoreCase("escapeChar") || res.equalsIgnoreCase("escape"))
/* 314 */               escape = kid.getNodeValue(); 
/*     */           } 
/* 318 */           if (wildcard != null && single != null && escape != null && pattern != null) {
/* 321 */             LOGGER.finer("Building like filter " + expression.toString() + "\n" + pattern + " " + wildcard + " " + single + " " + escape);
/* 327 */             return (Filter)FILTER_FACT.like(expression, pattern, wildcard, single, escape);
/*     */           } 
/* 330 */           LOGGER.finer("Problem building like filter\n" + pattern + " " + wildcard + " " + single + " " + escape);
/* 333 */           return null;
/*     */         } 
/* 334 */         if (type == 21)
/* 335 */           return (Filter)parseNullFilter(child); 
/* 339 */         Node value = child.getFirstChild();
/* 341 */         while (value.getNodeType() != 1)
/* 342 */           value = value.getNextSibling(); 
/* 345 */         LOGGER.finest("add left value -> " + value + "<-");
/* 346 */         Expression left = expressionDOMParser.expression(value);
/* 347 */         value = value.getNextSibling();
/* 349 */         while (value.getNodeType() != 1)
/* 350 */           value = value.getNextSibling(); 
/* 353 */         LOGGER.finest("add right value -> " + value + "<-");
/* 354 */         Expression right = expressionDOMParser.expression(value);
/* 356 */         switch (type) {
/*     */           case 14:
/* 358 */             return (Filter)FILTER_FACT.equals(left, right);
/*     */           case 16:
/* 361 */             return (Filter)FILTER_FACT.greater(left, right);
/*     */           case 18:
/* 364 */             return (Filter)FILTER_FACT.greaterOrEqual(left, right);
/*     */           case 15:
/* 367 */             return (Filter)FILTER_FACT.less(left, right);
/*     */           case 17:
/* 370 */             return (Filter)FILTER_FACT.lessOrEqual(left, right);
/*     */           case 23:
/* 373 */             return (Filter)FILTER_FACT.notEqual(left, right, true);
/*     */         } 
/* 376 */         LOGGER.warning("Unable to build filter for " + childName);
/* 377 */         return null;
/* 379 */       } catch (IllegalFilterException ife) {
/* 380 */         LOGGER.warning("Unable to build filter: " + ife);
/* 381 */         return null;
/*     */       } 
/*     */     } 
/* 383 */     if (spatial.containsKey(childName)) {
/* 384 */       LOGGER.finest("a spatial filter " + childName);
/*     */       try {
/*     */         Expression right;
/*     */         double distance;
/*     */         Literal literal;
/*     */         Object obj;
/*     */         ReferencedEnvelope bbox;
/* 387 */         short type = ((Integer)spatial.get(childName)).shortValue();
/* 389 */         Node value = child.getFirstChild();
/* 391 */         while (value.getNodeType() != 1)
/* 392 */           value = value.getNextSibling(); 
/* 395 */         LOGGER.finest("add left value -> " + value + "<-");
/* 396 */         Expression left = expressionDOMParser.expression(value);
/* 397 */         value = value.getNextSibling();
/* 399 */         while (value.getNodeType() != 1)
/* 400 */           value = value.getNextSibling(); 
/* 403 */         LOGGER.finest("add right value -> " + value + "<-");
/* 405 */         String valueName = (value.getLocalName() != null) ? value.getLocalName() : value.getNodeName();
/* 406 */         if (valueName.indexOf(':') != -1)
/* 409 */           valueName = valueName.substring(valueName.indexOf(':') + 1); 
/* 414 */         Node nextNode = value.getNextSibling();
/* 416 */         if (!valueName.equalsIgnoreCase("Literal") && !valueName.equalsIgnoreCase("propertyname")) {
/* 418 */           Element element = value.getOwnerDocument().createElement("literal");
/* 420 */           element.appendChild(value);
/* 421 */           LOGGER.finest("Built new literal " + element);
/* 422 */           right = expressionDOMParser.expression(element);
/*     */         } else {
/* 424 */           right = expressionDOMParser.expression(value);
/*     */         } 
/* 429 */         String units = null;
/* 430 */         String nodeName = null;
/* 431 */         switch (type) {
/*     */           case 5:
/* 433 */             return (Filter)FILTER_FACT.equal(left, right);
/*     */           case 6:
/* 436 */             return (Filter)FILTER_FACT.disjoint(left, right);
/*     */           case 7:
/* 439 */             return (Filter)FILTER_FACT.intersects(left, right);
/*     */           case 8:
/* 442 */             return (Filter)FILTER_FACT.touches(left, right);
/*     */           case 9:
/* 445 */             return (Filter)FILTER_FACT.crosses(left, right);
/*     */           case 10:
/* 448 */             return (Filter)FILTER_FACT.within(left, right);
/*     */           case 11:
/* 451 */             return (Filter)FILTER_FACT.contains(left, right);
/*     */           case 12:
/* 454 */             return (Filter)FILTER_FACT.overlaps(left, right);
/*     */           case 24:
/* 457 */             value = nextNode;
/* 458 */             while (value != null && value.getNodeType() != 1)
/* 459 */               value = value.getNextSibling(); 
/* 461 */             if (value == null)
/* 462 */               throw new IllegalFilterException("DWithin is missing the Distance element"); 
/* 465 */             nodeName = value.getNodeName();
/* 466 */             if (nodeName.indexOf(':') > 0)
/* 467 */               nodeName = nodeName.substring(nodeName.indexOf(":") + 1); 
/* 469 */             if (!"Distance".equals(nodeName))
/* 470 */               throw new IllegalFilterException("Parsing DWithin, was expecting to find Distance but found " + value.getLocalName()); 
/* 472 */             distance = Double.parseDouble(value.getTextContent());
/* 473 */             if (value.getAttributes().getNamedItem("units") != null)
/* 474 */               units = value.getAttributes().getNamedItem("units").getTextContent(); 
/* 475 */             return (Filter)FILTER_FACT.dwithin(left, right, distance, units);
/*     */           case 13:
/* 478 */             value = nextNode;
/* 479 */             while (value != null && value.getNodeType() != 1)
/* 480 */               value = value.getNextSibling(); 
/* 482 */             if (value == null)
/* 483 */               throw new IllegalFilterException("Beyond is missing the Distance element"); 
/* 485 */             nodeName = value.getNodeName();
/* 486 */             if (nodeName.indexOf(':') > 0)
/* 487 */               nodeName = nodeName.substring(nodeName.indexOf(":") + 1); 
/* 489 */             if (!"Distance".equals(nodeName))
/* 490 */               throw new IllegalFilterException("Parsing Beyond, was expecting to find Distance but found " + value.getLocalName()); 
/* 492 */             distance = Double.parseDouble(value.getTextContent());
/* 493 */             if (value.getAttributes().getNamedItem("units") != null)
/* 494 */               units = value.getAttributes().getNamedItem("units").getTextContent(); 
/* 495 */             return (Filter)FILTER_FACT.beyond(left, right, distance, units);
/*     */           case 4:
/* 500 */             literal = (Literal)right;
/* 501 */             obj = literal.getValue();
/* 502 */             bbox = null;
/* 503 */             if (obj instanceof Geometry) {
/* 504 */               bbox = JTS.toEnvelope((Geometry)obj);
/* 506 */             } else if (obj instanceof ReferencedEnvelope) {
/* 507 */               bbox = (ReferencedEnvelope)obj;
/* 509 */             } else if (obj instanceof Envelope) {
/* 511 */               bbox = new ReferencedEnvelope((Envelope)obj, null);
/*     */             } 
/* 513 */             return (Filter)FILTER_FACT.bbox(left, (BoundingBox)bbox);
/*     */         } 
/* 516 */         LOGGER.warning("Unable to build filter: " + childName);
/* 517 */         return null;
/* 519 */       } catch (IllegalFilterException ife) {
/* 520 */         LOGGER.warning("Unable to build filter: " + ife);
/* 522 */         return null;
/*     */       } 
/*     */     } 
/* 524 */     if (logical.containsKey(childName)) {
/* 525 */       LOGGER.finest("a logical filter " + childName);
/*     */       try {
/* 528 */         List<Filter> children = new ArrayList<Filter>();
/* 529 */         NodeList map = child.getChildNodes();
/* 531 */         for (int i = 0; i < map.getLength(); i++) {
/* 532 */           Node kid = map.item(i);
/* 534 */           if (kid != null && kid.getNodeType() == 1) {
/* 539 */             LOGGER.finest("adding to logic filter " + kid.getLocalName());
/* 540 */             children.add(parseFilter(kid));
/*     */           } 
/*     */         } 
/* 543 */         if (childName.equals("And"))
/* 544 */           return (Filter)FILTER_FACT.and(children); 
/* 545 */         if (childName.equals("Or"))
/* 546 */           return (Filter)FILTER_FACT.or(children); 
/* 547 */         if (childName.equals("Not")) {
/* 548 */           if (children.size() != 1)
/* 549 */             throw new IllegalFilterException("Filter negation can be applied to one and only one child filter"); 
/* 551 */           return (Filter)FILTER_FACT.not(children.get(0));
/*     */         } 
/* 553 */         throw new RuntimeException("Logical filter, but not And, Or, Not? This should not happen");
/* 556 */       } catch (IllegalFilterException ife) {
/* 557 */         LOGGER.warning("Unable to build filter: " + ife);
/* 559 */         return null;
/*     */       } 
/*     */     } 
/* 563 */     LOGGER.warning("unknown filter " + root);
/* 565 */     return null;
/*     */   }
/*     */   
/*     */   private static PropertyIsNull parseNullFilter(Node nullNode) throws IllegalFilterException {
/* 580 */     ExpressionDOMParser expressionDOMParser = new ExpressionDOMParser(FILTER_FACT);
/* 582 */     LOGGER.finest("parsing null node: " + nullNode);
/* 584 */     Node value = nullNode.getFirstChild();
/* 586 */     while (value.getNodeType() != 1)
/* 587 */       value = value.getNextSibling(); 
/* 590 */     LOGGER.finest("add null value -> " + value + "<-");
/* 591 */     Expression expr = expressionDOMParser.expression(value);
/* 593 */     return FILTER_FACT.isNull(expr);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterDOMParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */