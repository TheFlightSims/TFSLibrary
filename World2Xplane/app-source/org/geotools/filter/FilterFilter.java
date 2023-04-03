/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.gml.GMLHandlerJTS;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.XMLFilterImpl;
/*     */ 
/*     */ public class FilterFilter extends XMLFilterImpl implements GMLHandlerJTS {
/*  49 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*     */   private LogicSAXParser logicFactory;
/*     */   
/*     */   private FilterSAXParser filterFactory;
/*     */   
/*     */   private ExpressionSAXParser expressionFactory;
/*     */   
/*     */   private FilterHandler parent;
/*     */   
/*     */   private SimpleFeatureType schema;
/*     */   
/*     */   private boolean isLogicFilter = false;
/*     */   
/*     */   private boolean isFidFilter = false;
/*     */   
/*     */   protected boolean insideFilter = false;
/*     */   
/*     */   private boolean insideDistance = false;
/*     */   
/*     */   private String units;
/*     */   
/*     */   private StringBuffer characters;
/*     */   
/*     */   private boolean convertLiteralToNumber = true;
/*     */   
/*     */   public FilterFilter(FilterHandler parent, SimpleFeatureType schema) {
/*  95 */     this.parent = parent;
/*  96 */     this.schema = schema;
/*  97 */     this.expressionFactory = new ExpressionSAXParser(schema);
/*  98 */     this.filterFactory = new FilterSAXParser();
/*  99 */     this.logicFactory = new LogicSAXParser();
/* 100 */     this.characters = new StringBuffer();
/*     */   }
/*     */   
/*     */   public FilterFilter(FilterHandler parent, SimpleFeatureType schema, boolean convertLiteralToNumber) {
/* 110 */     this(parent, schema);
/* 111 */     this.convertLiteralToNumber = convertLiteralToNumber;
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 128 */     LOGGER.finer("found start element: " + localName);
/* 130 */     this.characters.setLength(0);
/* 132 */     if (localName.equals("Filter")) {
/* 135 */       this.insideFilter = true;
/* 136 */       this.expressionFactory = new ExpressionSAXParser(this.schema);
/* 137 */     } else if (this.insideFilter) {
/* 138 */       short filterType = convertType(localName);
/* 139 */       LOGGER.finest("types: (xml): " + localName + "; " + "(internal): " + filterType);
/* 152 */       if (filterType == -1 && !localName.equals("UpperBoundary") && !localName.equals("LowerBoundary") && !localName.equals("Distance"))
/* 154 */         if (!localName.endsWith("Member"))
/* 155 */           throw new SAXException("Attempted to construct illegal filter - I dont understand the tag: " + qName + ".  HINT: tags are case-sensitive!");  
/*     */       try {
/* 159 */         if (this.isFidFilter)
/* 160 */           if (filterType == 22) {
/* 161 */             LOGGER.finer("sending attributes to existing FID filter");
/* 163 */             this.filterFactory.setAttributes(atts);
/*     */           } else {
/* 165 */             this.isFidFilter = false;
/* 166 */             LOGGER.finer("is fid (1): " + this.isFidFilter);
/* 169 */             if (this.isLogicFilter) {
/* 170 */               addFilterToLogicFactory();
/*     */             } else {
/* 172 */               addFilterToParent();
/*     */             } 
/*     */           }  
/* 177 */         if (!this.isFidFilter) {
/* 179 */           LOGGER.finest("is logic?");
/* 181 */           if (AbstractFilter.isLogicFilter(filterType)) {
/* 182 */             LOGGER.finer("found a logic filter start");
/* 183 */             this.isLogicFilter = true;
/* 184 */             this.logicFactory.start(filterType);
/* 185 */           } else if (AbstractFilter.isSimpleFilter(filterType)) {
/* 187 */             LOGGER.finer("found a simple filter start");
/* 188 */             this.filterFactory.start(filterType);
/* 190 */             if (filterType == 20) {
/* 191 */               LOGGER.finer("sending attributes for like filter");
/* 192 */               this.filterFactory.setAttributes(atts);
/* 193 */             } else if (filterType == 22) {
/* 194 */               LOGGER.finer("sending attributes to new FID filter");
/* 195 */               this.filterFactory.setAttributes(atts);
/* 196 */               this.isFidFilter = true;
/* 197 */               LOGGER.finer("is fid (3): " + this.isFidFilter);
/*     */             } 
/* 199 */           } else if (DefaultExpression.isExpression(filterType)) {
/* 201 */             LOGGER.finest("found an expression filter start");
/* 202 */             this.expressionFactory.start(localName, atts);
/* 203 */           } else if (localName.equals("Distance")) {
/* 204 */             LOGGER.finest("inside distance");
/* 210 */             if ("units".equals(atts.getLocalName(0))) {
/* 211 */               this.units = atts.getValue(0);
/* 212 */               LOGGER.finest("units = " + this.units);
/*     */             } 
/* 215 */             this.insideDistance = true;
/*     */           } 
/*     */         } 
/* 218 */       } catch (IllegalFilterException ife) {
/* 219 */         throw new SAXException("Attempted to construct illegal filter: " + ife.getMessage(), ife);
/*     */       } 
/*     */     } else {
/* 223 */       this.parent.startElement(namespaceURI, localName, qName, atts);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addFilterToParent() throws IllegalFilterException {
/* 228 */     this.parent.filter(this.filterFactory.create());
/* 229 */     this.expressionFactory = new ExpressionSAXParser(this.schema);
/*     */   }
/*     */   
/*     */   private void addFilterToLogicFactory() throws IllegalFilterException {
/* 233 */     this.logicFactory.add(this.filterFactory.create());
/* 234 */     this.expressionFactory = new ExpressionSAXParser(this.schema);
/*     */   }
/*     */   
/*     */   public void characters(char[] chars, int start, int length) throws SAXException {
/* 254 */     this.characters.append(chars, start, length);
/*     */   }
/*     */   
/*     */   private void processCharacters() throws SAXException {
/* 266 */     if (this.insideFilter) {
/* 267 */       String message = this.characters.toString();
/*     */       try {
/* 269 */         if (this.insideDistance) {
/* 270 */           LOGGER.finest("calling set distance on " + message + ", " + this.units);
/* 272 */           this.filterFactory.setDistance(message, this.units);
/*     */         } else {
/* 274 */           LOGGER.finest("sending to expression factory: " + message);
/* 275 */           this.expressionFactory.message(message, this.convertLiteralToNumber);
/*     */         } 
/* 277 */       } catch (IllegalFilterException ife) {
/* 278 */         throw new SAXException(ife);
/*     */       } 
/* 280 */     } else if (this.characters.length() > 0) {
/* 281 */       LOGGER.finer("delegating characters to parent: " + this.characters.toString());
/* 282 */       int len = this.characters.length();
/* 283 */       char[] chars = new char[this.characters.length()];
/* 284 */       this.characters.getChars(0, len, chars, 0);
/* 285 */       this.parent.characters(chars, 0, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 301 */     LOGGER.finer("found end element: " + localName);
/* 303 */     processCharacters();
/* 305 */     if (localName.equals("Filter")) {
/* 307 */       if (this.isFidFilter && !localName.equals("FeatureId")) {
/* 308 */         this.isFidFilter = false;
/* 309 */         LOGGER.finer("is fid (2): " + this.isFidFilter);
/*     */         try {
/* 313 */           if (this.isLogicFilter) {
/* 314 */             addFilterToLogicFactory();
/*     */           } else {
/* 316 */             addFilterToParent();
/*     */           } 
/* 318 */         } catch (IllegalFilterException e) {
/* 319 */           throw new SAXException("Attempted to construct illegal filter: " + e.getMessage());
/*     */         } 
/*     */       } 
/* 325 */       this.insideFilter = false;
/* 326 */     } else if (this.insideFilter) {
/* 327 */       short filterType = convertType(localName);
/*     */       try {
/* 332 */         if (AbstractFilter.isLogicFilter(filterType)) {
/* 333 */           LOGGER.finest("found a logic filter end");
/* 335 */           if (this.isFidFilter) {
/* 336 */             addFilterToLogicFactory();
/* 337 */             this.isFidFilter = false;
/*     */           } 
/* 340 */           this.logicFactory.end(filterType);
/* 343 */           if (this.logicFactory.isComplete()) {
/* 344 */             LOGGER.finer("creating logic factory");
/* 345 */             this.parent.filter(this.logicFactory.create());
/*     */           } 
/* 349 */         } else if (AbstractFilter.isSimpleFilter(filterType) && !this.isFidFilter) {
/* 353 */           LOGGER.finest("found a simple filter end");
/* 356 */           if (this.isLogicFilter) {
/* 357 */             addFilterToLogicFactory();
/*     */           } else {
/* 359 */             addFilterToParent();
/*     */           } 
/* 367 */         } else if (DefaultExpression.isExpression(filterType)) {
/* 368 */           LOGGER.finer("found an expression filter end");
/* 369 */           this.expressionFactory.end(localName);
/* 371 */           if (this.expressionFactory.isReady()) {
/* 372 */             LOGGER.finer("expression factory is ready");
/* 373 */             this.filterFactory.expression(this.expressionFactory.create());
/*     */           } 
/* 375 */         } else if (localName.equals("Distance")) {
/* 376 */           this.insideDistance = false;
/*     */         } 
/* 378 */       } catch (IllegalFilterException e) {
/* 379 */         throw new SAXException("Attempted to construct illegal filter: " + e.getMessage());
/*     */       } 
/*     */     } else {
/* 383 */       this.parent.endElement(namespaceURI, localName, qName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void geometry(Geometry geometry) throws RuntimeException {
/*     */     try {
/* 399 */       LOGGER.finer("got geometry: " + geometry);
/* 400 */       this.expressionFactory.geometry(geometry);
/* 402 */       if (this.expressionFactory.isReady()) {
/* 403 */         LOGGER.finer("expression factory made expression and sent to filter factory");
/* 405 */         this.filterFactory.expression(this.expressionFactory.create());
/*     */       } 
/* 407 */     } catch (IllegalFilterException ife) {
/* 408 */       LOGGER.finer("Had problems adding geometry: " + geometry.toString());
/* 409 */       throw new RuntimeException("problem adding geometry to filter ", ife);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static short convertType(String filterType) {
/* 423 */     if (filterType.equals("Or"))
/* 424 */       return 1; 
/* 425 */     if (filterType.equals("And"))
/* 426 */       return 2; 
/* 427 */     if (filterType.equals("Not"))
/* 428 */       return 3; 
/* 429 */     if (filterType.equals("Equals"))
/* 430 */       return 5; 
/* 431 */     if (filterType.equals("Disjoint"))
/* 432 */       return 6; 
/* 433 */     if (filterType.equals("DWithin"))
/* 434 */       return 24; 
/* 435 */     if (filterType.equals("Intersects"))
/* 436 */       return 7; 
/* 437 */     if (filterType.equals("Touches"))
/* 438 */       return 8; 
/* 439 */     if (filterType.equals("Crosses"))
/* 440 */       return 9; 
/* 441 */     if (filterType.equals("Within"))
/* 442 */       return 10; 
/* 443 */     if (filterType.equals("Contains"))
/* 444 */       return 11; 
/* 445 */     if (filterType.equals("Overlaps"))
/* 446 */       return 12; 
/* 447 */     if (filterType.equals("Beyond"))
/* 448 */       return 13; 
/* 449 */     if (filterType.equals("BBOX"))
/* 450 */       return 4; 
/* 451 */     if (filterType.equals("PropertyIsEqualTo"))
/* 452 */       return 14; 
/* 453 */     if (filterType.equals("PropertyIsNotEqualTo"))
/* 454 */       return 23; 
/* 455 */     if (filterType.equals("PropertyIsLessThan"))
/* 456 */       return 15; 
/* 457 */     if (filterType.equals("PropertyIsGreaterThan"))
/* 458 */       return 16; 
/* 459 */     if (filterType.equals("PropertyIsLessThanOrEqualTo"))
/* 460 */       return 17; 
/* 461 */     if (filterType.equals("PropertyIsGreaterThanOrEqualTo"))
/* 462 */       return 18; 
/* 463 */     if (filterType.equals("PropertyIsBetween"))
/* 464 */       return 19; 
/* 465 */     if (filterType.equals("PropertyIsLike"))
/* 466 */       return 20; 
/* 467 */     if (filterType.equals("PropertyIsNull"))
/* 468 */       return 21; 
/* 469 */     if (filterType.equals("FeatureId"))
/* 470 */       return 22; 
/* 471 */     if (filterType.equals("Add"))
/* 472 */       return 105; 
/* 473 */     if (filterType.equals("Sub"))
/* 474 */       return 106; 
/* 475 */     if (filterType.equals("Mul"))
/* 476 */       return 107; 
/* 477 */     if (filterType.equals("Div"))
/* 478 */       return 108; 
/* 479 */     if (filterType.equals("PropertyName"))
/* 480 */       return 101; 
/* 481 */     if (filterType.equals("Literal"))
/* 482 */       return 109; 
/* 483 */     if (filterType.equals("Function"))
/* 484 */       return 114; 
/* 486 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */