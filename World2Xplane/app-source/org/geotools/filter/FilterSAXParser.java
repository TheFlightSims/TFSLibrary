/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class FilterSAXParser {
/*  39 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*     */   private static final int NUM_LIKE_ATTS = 3;
/*     */   
/*  45 */   private Filter curFilter = null;
/*     */   
/*  48 */   private String curState = "uninitialized";
/*     */   
/*     */   private short filterType;
/*     */   
/*     */   private FilterFactory ff;
/*     */   
/*  59 */   private Map attributes = new HashMap<Object, Object>();
/*     */   
/*     */   public FilterSAXParser() {
/*  65 */     this(FilterFactoryFinder.createFilterFactory());
/*     */   }
/*     */   
/*     */   public FilterSAXParser(FilterFactory factory) {
/*  69 */     this.ff = factory;
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory factory) {
/*  74 */     this.ff = factory;
/*     */   }
/*     */   
/*     */   public void start(short filterType) throws IllegalFilterException {
/*  86 */     LOGGER.finest("starting filter type " + filterType);
/*  88 */     if (filterType == 22 && !this.curState.equals("fid")) {
/*  89 */       LOGGER.finer("creating the FID filter");
/*  90 */       this.curFilter = this.ff.createFidFilter();
/*  91 */     } else if (AbstractFilter.isGeometryDistanceFilter(filterType)) {
/*  92 */       this.curFilter = this.ff.createGeometryDistanceFilter(filterType);
/*  93 */     } else if (AbstractFilter.isGeometryFilter(filterType)) {
/*  94 */       this.curFilter = this.ff.createGeometryFilter(filterType);
/*  95 */     } else if (filterType == 19) {
/*  96 */       this.curFilter = this.ff.createBetweenFilter();
/*  97 */     } else if (filterType == 21) {
/*  98 */       this.curFilter = this.ff.createNullFilter();
/*  99 */     } else if (filterType == 20) {
/* 100 */       this.curFilter = this.ff.createLikeFilter();
/* 101 */     } else if (AbstractFilter.isCompareFilter(filterType)) {
/* 102 */       this.curFilter = this.ff.createCompareFilter(filterType);
/*     */     } else {
/* 104 */       throw new IllegalFilterException("Attempted to start a new filter with invalid type: " + filterType);
/*     */     } 
/* 109 */     this.curState = setInitialState(filterType);
/* 110 */     this.filterType = filterType;
/* 112 */     this.attributes = new HashMap<Object, Object>();
/*     */   }
/*     */   
/*     */   public void value(String message) throws IllegalFilterException {}
/*     */   
/*     */   public void expression(Expression expression) throws IllegalFilterException {
/* 140 */     if (this.filterType == 19) {
/* 141 */       if (this.curState.equals("attribute")) {
/* 142 */         ((BetweenFilter)this.curFilter).addMiddleValue(expression);
/* 143 */         this.curState = "LowerBoundary";
/* 144 */       } else if (this.curState.equals("LowerBoundary")) {
/* 145 */         ((BetweenFilter)this.curFilter).addLeftValue(expression);
/* 146 */         this.curState = "UpperBoundary";
/* 147 */       } else if (this.curState.equals("UpperBoundary")) {
/* 148 */         ((BetweenFilter)this.curFilter).addRightValue(expression);
/* 149 */         this.curState = "complete";
/*     */       } else {
/* 151 */         throw new IllegalFilterException("Got expression for Between Filter in illegal state: " + this.curState);
/*     */       } 
/* 155 */     } else if (AbstractFilter.isCompareFilter(this.filterType)) {
/* 156 */       if (this.curState.equals("leftValue")) {
/* 157 */         ((CompareFilter)this.curFilter).addLeftValue(expression);
/* 158 */         this.curState = "rightValue";
/* 159 */       } else if (this.curState.equals("rightValue")) {
/* 160 */         ((CompareFilter)this.curFilter).addRightValue(expression);
/* 161 */         this.curState = "complete";
/*     */       } else {
/* 163 */         throw new IllegalFilterException("Got expression for Compare Filter in illegal state: " + this.curState);
/*     */       } 
/* 167 */     } else if (this.filterType == 21) {
/* 168 */       if (this.curState.equals("attribute")) {
/* 169 */         ((NullFilter)this.curFilter).nullCheckValue(expression);
/* 170 */         this.curState = "complete";
/*     */       } else {
/* 172 */         throw new IllegalFilterException("Got expression for Null Filter in illegal state: " + this.curState);
/*     */       } 
/* 176 */     } else if (AbstractFilter.isGeometryFilter(this.filterType)) {
/* 177 */       if (this.curState.equals("leftValue")) {
/* 178 */         ((GeometryFilter)this.curFilter).addLeftGeometry(expression);
/* 179 */         this.curState = "rightValue";
/* 180 */       } else if (this.curState.equals("rightValue")) {
/* 181 */         ((GeometryFilter)this.curFilter).addRightGeometry(expression);
/* 183 */         if (AbstractFilter.isGeometryDistanceFilter(this.filterType)) {
/* 184 */           this.curState = "distance";
/*     */         } else {
/* 186 */           this.curState = "complete";
/*     */         } 
/* 189 */         LOGGER.finer("expression called on geometry, curState = " + this.curState);
/*     */       } else {
/* 192 */         throw new IllegalFilterException("Got expression for Geometry Filter in illegal state: " + this.curState);
/*     */       } 
/* 196 */     } else if (this.filterType == 20) {
/* 197 */       if (this.curState.equals("attribute")) {
/* 198 */         ((LikeFilter)this.curFilter).setValue(expression);
/* 199 */         this.curState = "pattern";
/* 200 */       } else if (this.curState.equals("pattern")) {
/* 201 */         if (this.attributes.size() != 3)
/* 202 */           throw new IllegalFilterException("Got wrong number of attributes (expecting 3): " + this.attributes.size() + "\n" + this.attributes); 
/* 207 */         String wildcard = (String)this.attributes.get("wildCard");
/* 209 */         if (wildcard == null || wildcard.length() != 1)
/* 211 */           throw new IllegalFilterException("like filter -- required attribute 'wildCard' missing or not exactly 1 char long.  Capitalization?"); 
/* 213 */         String singleChar = (String)this.attributes.get("singleChar");
/* 215 */         if (singleChar == null || singleChar.length() != 1)
/* 217 */           throw new IllegalFilterException("like filter -- required attribute 'singleChar' missing  or not exactly 1 char long.  Capitalization?"); 
/* 220 */         String escapeChar = (String)this.attributes.get("escape");
/* 221 */         if (escapeChar == null)
/* 222 */           escapeChar = (String)this.attributes.get("escapeChar"); 
/* 224 */         if (escapeChar == null || escapeChar.length() != 1)
/* 226 */           throw new IllegalFilterException("like filter -- required attribute 'escape' missing  or not exactly 1 char long.  Capitalization?"); 
/* 229 */         LOGGER.fine("escape char is " + escapeChar);
/* 232 */         ((LikeFilter)this.curFilter).setPattern(expression, wildcard, singleChar, escapeChar);
/* 234 */         this.curState = "complete";
/*     */       } else {
/* 236 */         throw new IllegalFilterException("Got expression for Like Filter in illegal state: " + this.curState);
/*     */       } 
/*     */     } 
/* 242 */     LOGGER.finer("current state (end): " + this.curState);
/*     */   }
/*     */   
/*     */   public Filter create() throws IllegalFilterException {
/* 254 */     if (isComplete()) {
/* 255 */       LOGGER.finer("complete called, state = " + this.curState);
/* 256 */       this.curState = "complete";
/* 258 */       return this.curFilter;
/*     */     } 
/* 260 */     throw new IllegalFilterException("Got to the end state of an incomplete filter, current state is " + this.curState);
/*     */   }
/*     */   
/*     */   private static String setInitialState(short filterType) throws IllegalFilterException {
/* 280 */     if (filterType == 19 || filterType == 21 || filterType == 20)
/* 283 */       return "attribute"; 
/* 284 */     if (filterType == 22)
/* 285 */       return "fid"; 
/* 286 */     if (AbstractFilter.isCompareFilter(filterType) || AbstractFilter.isGeometryFilter(filterType))
/* 288 */       return "leftValue"; 
/* 290 */     throw new IllegalFilterException("Filter type: " + filterType + " is not recognized");
/*     */   }
/*     */   
/*     */   public void setDistance(String distance, String units) throws IllegalFilterException {
/* 312 */     LOGGER.finer("set distance called, current state is " + this.curState);
/* 314 */     if (this.curState.equals("distance")) {
/*     */       try {
/* 316 */         double distDouble = Double.parseDouble(distance);
/* 317 */         ((GeometryDistanceFilter)this.curFilter).setDistance(distDouble);
/* 318 */         this.curState = "complete";
/* 319 */       } catch (NumberFormatException nfe) {
/* 320 */         throw new IllegalFilterException("could not parse distance: " + distance + " to a double");
/*     */       } 
/*     */     } else {
/* 324 */       throw new IllegalFilterException("Got distance for Geometry Distance Filter in illegal state: " + this.curState + ", geometry and property should be set first");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAttributes(Attributes atts) {
/* 337 */     LOGGER.finer("got attribute: " + atts.getLocalName(0) + ", " + atts.getValue(0));
/* 339 */     LOGGER.finer("current state: " + this.curState);
/* 341 */     if (this.curState.equals("fid")) {
/* 342 */       LOGGER.finer("is a fid");
/* 343 */       ((FidFilter)this.curFilter).addFid(atts.getValue(0));
/* 344 */       LOGGER.finer("added fid");
/*     */     } else {
/* 346 */       for (int i = 0; i < atts.getLength(); i++)
/* 347 */         this.attributes.put(atts.getLocalName(i), atts.getValue(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isComplete() {
/* 358 */     return (this.curState.equals("complete") || this.curState.equals("fid"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterSAXParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */