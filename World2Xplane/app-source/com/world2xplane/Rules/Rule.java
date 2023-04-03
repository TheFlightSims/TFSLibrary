/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.Network.RoadNetwork;
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.Rules.Filter.AreaFilter;
/*     */ import com.world2xplane.Rules.Filter.Filter;
/*     */ import com.world2xplane.Rules.Filter.NodeFilter;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class Rule {
/*  36 */   private static short currentRule = 1;
/*     */   
/*     */   private static Random randomGenerator;
/*     */   
/*     */   protected final GeneratorStore generatorStore;
/*     */   
/*     */   protected FilterList filterList;
/*     */   
/*     */   protected String randomSeedIdentifier;
/*     */   
/*  42 */   protected int minSeed = -1;
/*     */   
/*  43 */   protected int maxSeed = -1;
/*     */   
/*  44 */   protected float buffer = -1.0F;
/*     */   
/*  45 */   protected float bufferRandomise = -1.0F;
/*     */   
/*  46 */   protected float simplify = -1.0F;
/*     */   
/*     */   private String defaultHeight;
/*     */   
/*     */   private AdaptiveHeight adaptiveHeightPolicy;
/*     */   
/*     */   private boolean passThrough = false;
/*     */   
/*     */   public boolean circular = false;
/*     */   
/*  53 */   public double minRadius = -1.0D;
/*     */   
/*  54 */   public double maxRadius = -1.0D;
/*     */   
/*  57 */   public int requiredLevel = 6;
/*     */   
/*     */   public short ruleNumber;
/*     */   
/*     */   public float parameter;
/*     */   
/*  61 */   public Boolean closed = null;
/*     */   
/*     */   private Float minHeight;
/*     */   
/*     */   private Float maxHeight;
/*     */   
/*     */   private String set;
/*     */   
/*     */   private boolean noClip = false;
/*     */   
/*     */   private String notset;
/*     */   
/*     */   private boolean skipAirport = false;
/*     */   
/*     */   private double density;
/*     */   
/*  76 */   private Set<String> regions = new HashSet<>();
/*     */   
/*  77 */   private Set<String> notRegions = new HashSet<>();
/*     */   
/*  79 */   HashSet<String> areas = null;
/*     */   
/*     */   public static interface OnAccept {
/*     */     boolean addItem(Rule param1Rule);
/*     */   }
/*     */   
/*     */   public static abstract class Delegate {
/*     */     public abstract boolean containsNode(String param1String);
/*     */     
/*     */     public RoadNetwork getRoadNetwork() {
/*  87 */       return null;
/*     */     }
/*     */     
/*     */     public double getMetreSize() {
/*  91 */       return 0.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public HashSet<String> getClipAreas() {
/*  97 */     if (this.areas != null)
/*  98 */       return this.areas; 
/* 100 */     this.areas = new HashSet<>();
/* 101 */     for (Map<String, List<Filter>> i : this.filterList.getTagSets()) {
/* 102 */       for (Map.Entry<String, List<Filter>> item : i.entrySet()) {
/* 103 */         for (Filter filter : item.getValue()) {
/* 104 */           if (filter instanceof AreaFilter) {
/* 105 */             AreaFilter areaFilter = (AreaFilter)filter;
/* 106 */             this.areas.add(areaFilter.getFilter());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 112 */     return this.areas;
/*     */   }
/*     */   
/*     */   public Rule(GeneratorStore generatorStore) {
/* 121 */     this.ruleNumber = currentRule;
/* 122 */     this.generatorStore = generatorStore;
/* 123 */     currentRule = (short)(currentRule + 1);
/*     */   }
/*     */   
/*     */   public void setFilter(FilterList filterList) {
/* 127 */     this.filterList = filterList;
/*     */   }
/*     */   
/*     */   public FilterList getFilter() {
/* 131 */     return this.filterList;
/*     */   }
/*     */   
/*     */   public short getRuleNumber() {
/* 135 */     return this.ruleNumber;
/*     */   }
/*     */   
/*     */   public abstract Integer getDefinitionNumber(Object paramObject);
/*     */   
/*     */   public float getParameter() {
/* 141 */     return this.parameter;
/*     */   }
/*     */   
/*     */   public void setParameter(float parameter) {
/* 145 */     this.parameter = parameter;
/*     */   }
/*     */   
/*     */   public static int getRandomNumber(int min, int max) {
/* 150 */     if (min == max)
/* 151 */       return max; 
/* 153 */     if (randomGenerator == null)
/* 154 */       randomGenerator = new Random(); 
/* 156 */     int number = randomGenerator.nextInt(max - min) + min;
/* 157 */     if (number > max)
/* 158 */       throw new RuntimeException("Random number " + number + " generated higher than allowed range. Please report bug"); 
/* 160 */     return number;
/*     */   }
/*     */   
/*     */   protected boolean validateShape(Byte tagList, OSMPolygon shape, Set<String> regionsFromDsf, Delegate delegate) {
/* 170 */     if (getNotset() != null && !getNotset().isEmpty() && 
/* 171 */       this.generatorStore.variableIsSet(getNotset()))
/* 172 */       return false; 
/* 175 */     if (this.randomSeedIdentifier != null) {
/* 176 */       RandomSeed randomSeed = GeneratorStore.getGeneratorStore().getRandomSeed(this.randomSeedIdentifier);
/* 177 */       if (randomSeed != null) {
/* 178 */         int seed = randomSeed.getCurrentSeed();
/* 180 */         if (seed < this.minSeed || seed > this.maxSeed)
/* 181 */           return false; 
/*     */       } 
/*     */     } 
/* 186 */     if (this.minHeight != null && shape.getHeight() != null && 
/* 187 */       shape.getHeight().floatValue() < this.minHeight.floatValue())
/* 188 */       return false; 
/* 192 */     if (this.maxHeight != null && shape.getHeight() != null && 
/* 193 */       shape.getHeight().floatValue() > this.maxHeight.floatValue())
/* 194 */       return false; 
/* 198 */     if (getClosed() != null && shape != null) {
/* 200 */       if (getClosed().booleanValue() && 
/* 201 */         !shape.isClosed())
/* 202 */         return false; 
/* 205 */       if (!getClosed().booleanValue() && 
/* 206 */         shape.isClosed())
/* 207 */         return false; 
/*     */     } 
/* 211 */     if (this.regions != null && this.regions.size() > 0) {
/* 213 */       boolean contains = false;
/* 215 */       if (shape != null) {
/* 216 */         Set<String> set = this.generatorStore.getRegionsAtPoint(shape.getCentroid(), regionsFromDsf);
/* 217 */         for (String item : this.regions) {
/* 218 */           if (set.contains(item))
/* 219 */             contains = true; 
/*     */         } 
/*     */       } 
/* 223 */       if (!contains)
/* 224 */         return false; 
/*     */     } 
/* 227 */     if (this.notRegions != null && this.notRegions.size() > 0)
/* 229 */       if (shape != null) {
/* 230 */         Set<String> set = this.generatorStore.getRegionsAtPoint(shape.getCentroid(), regionsFromDsf);
/* 231 */         for (String item : this.notRegions) {
/* 232 */           if (set.contains(item))
/* 233 */             return false; 
/*     */         } 
/*     */       }  
/* 240 */     Map<String, List<Filter>> acceptingTags = this.filterList.getTagSets().get(tagList.byteValue());
/* 241 */     for (Map.Entry<String, List<Filter>> item : acceptingTags.entrySet()) {
/* 242 */       for (Filter filter : item.getValue()) {
/* 243 */         if (filter instanceof AreaFilter) {
/* 244 */           AreaFilter areaFilter = (AreaFilter)filter;
/* 246 */           if (shape.getAreaIdentifiers() != null && !shape.getAreaIdentifiers().contains(areaFilter.getFilter()))
/* 247 */             return false; 
/* 250 */           if ((shape.getAreaIdentifiers() == null || shape.getAreaIdentifiers().size() == 0) && 
/* 251 */             !areaFilter.getFilter().equals("none"))
/* 252 */             return false; 
/*     */         } 
/* 257 */         if (filter instanceof NodeFilter) {
/* 258 */           NodeFilter nodeFilter = (NodeFilter)filter;
/* 259 */           if (delegate != null && 
/* 260 */             !delegate.containsNode(nodeFilter.getFilter()))
/* 261 */             return false; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 268 */     if (isCircular()) {
/* 269 */       if (shape.isRound()) {
/* 272 */         if (getMinRadius() != -1.0D || getMaxRadius() != -1.0D) {
/* 273 */           Double radius = shape.getRadius();
/* 274 */           if (radius == null)
/* 275 */             return false; 
/* 277 */           boolean accept = true;
/* 278 */           if (radius.doubleValue() < getMinRadius())
/* 279 */             accept = false; 
/* 281 */           if (radius.doubleValue() > getMaxRadius())
/* 282 */             accept = false; 
/* 285 */           return accept;
/*     */         } 
/* 287 */         return true;
/*     */       } 
/* 291 */       return false;
/*     */     } 
/* 295 */     return true;
/*     */   }
/*     */   
/*     */   public static double getRandomDouble(double min, double max) {
/* 300 */     if (min == max)
/* 301 */       return max; 
/* 303 */     double diff = max - min;
/* 304 */     return min + FastMath.random() * diff;
/*     */   }
/*     */   
/*     */   public static double getRandomFloat(float min, float max) {
/* 309 */     if (min == max)
/* 310 */       return max; 
/* 312 */     double diff = (max - min);
/* 313 */     return min + FastMath.random() * diff;
/*     */   }
/*     */   
/*     */   public static double getRandomDouble() {
/* 317 */     if (randomGenerator == null)
/* 318 */       randomGenerator = new Random(); 
/* 320 */     return randomGenerator.nextDouble();
/*     */   }
/*     */   
/*     */   public List<Byte> acceptsTag(HashMap<String, String> tags) {
/* 327 */     return this.filterList.acceptsTag(tags);
/*     */   }
/*     */   
/*     */   public boolean isCircular() {
/* 334 */     return this.circular;
/*     */   }
/*     */   
/*     */   public void setCircular(boolean circular) {
/* 338 */     this.circular = circular;
/*     */   }
/*     */   
/*     */   public double getMinRadius() {
/* 342 */     return this.minRadius;
/*     */   }
/*     */   
/*     */   public void setMinRadius(double minRadius) {
/* 346 */     this.minRadius = minRadius;
/*     */   }
/*     */   
/*     */   public double getMaxRadius() {
/* 350 */     return this.maxRadius;
/*     */   }
/*     */   
/*     */   public void setMaxRadius(double maxRadius) {
/* 354 */     this.maxRadius = maxRadius;
/*     */   }
/*     */   
/*     */   public double getDensity() {
/* 358 */     return this.density;
/*     */   }
/*     */   
/*     */   public boolean isPassThrough() {
/* 362 */     return this.passThrough;
/*     */   }
/*     */   
/*     */   public boolean canBeSplit() {
/* 366 */     return true;
/*     */   }
/*     */   
/*     */   public void setPassThrough(boolean passThrough) {
/* 370 */     this.passThrough = passThrough;
/*     */   }
/*     */   
/*     */   public FilterList getFilterList() {
/* 375 */     return this.filterList;
/*     */   }
/*     */   
/*     */   public void setFilterList(FilterList filterList) {
/* 379 */     this.filterList = filterList;
/*     */   }
/*     */   
/*     */   public abstract boolean acceptsShape(Byte paramByte, OSMPolygon paramOSMPolygon, Set<String> paramSet, Delegate paramDelegate, boolean paramBoolean);
/*     */   
/*     */   public boolean requiresAreaTracking(Byte tagList) {
/*     */     try {
/* 397 */       Map<String, List<Filter>> acceptingTags = this.filterList.getTagSets().get(tagList.byteValue());
/* 398 */       for (Map.Entry<String, List<Filter>> item : acceptingTags.entrySet()) {
/* 399 */         for (Filter filter : item.getValue()) {
/* 400 */           if (filter instanceof AreaFilter)
/* 401 */             return true; 
/*     */         } 
/*     */       } 
/* 405 */       return false;
/* 406 */     } catch (Exception e) {
/* 407 */       e.printStackTrace();
/* 408 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<String> getRegions() {
/* 413 */     return this.regions;
/*     */   }
/*     */   
/*     */   public void setRegions(Set<String> regions) {
/* 417 */     this.regions = regions;
/*     */   }
/*     */   
/*     */   public Set<String> getNotRegions() {
/* 421 */     return this.notRegions;
/*     */   }
/*     */   
/*     */   public void setNotRegions(Set<String> notRegions) {
/* 425 */     this.notRegions = notRegions;
/*     */   }
/*     */   
/*     */   public int getMinSeed() {
/* 429 */     return this.minSeed;
/*     */   }
/*     */   
/*     */   public void setMinSeed(int minSeed) {
/* 433 */     this.minSeed = minSeed;
/*     */   }
/*     */   
/*     */   public int getMaxSeed() {
/* 437 */     return this.maxSeed;
/*     */   }
/*     */   
/*     */   public void setMaxSeed(int maxSeed) {
/* 441 */     this.maxSeed = maxSeed;
/*     */   }
/*     */   
/*     */   public String getRandomSeedIdentifier() {
/* 445 */     return this.randomSeedIdentifier;
/*     */   }
/*     */   
/*     */   public void setRandomSeedIdentifier(String randomSeedIdentifier) {
/* 449 */     this.randomSeedIdentifier = randomSeedIdentifier;
/*     */   }
/*     */   
/*     */   public float getBuffer() {
/* 453 */     return this.buffer;
/*     */   }
/*     */   
/*     */   public void setBuffer(float buffer) {
/* 457 */     this.buffer = buffer;
/*     */   }
/*     */   
/*     */   public float getSimplify() {
/* 461 */     return this.simplify;
/*     */   }
/*     */   
/*     */   public void setSimplify(float simplify) {
/* 465 */     this.simplify = simplify;
/*     */   }
/*     */   
/*     */   public AdaptiveHeight getAdaptiveHeightPolicy() {
/* 469 */     return this.adaptiveHeightPolicy;
/*     */   }
/*     */   
/*     */   public void setAdaptiveHeightPolicy(AdaptiveHeight adaptiveHeightPolicy) {
/* 473 */     this.adaptiveHeightPolicy = adaptiveHeightPolicy;
/*     */   }
/*     */   
/*     */   public Boolean getClosed() {
/* 477 */     return this.closed;
/*     */   }
/*     */   
/*     */   public void setClosed(Boolean closed) {
/* 481 */     this.closed = closed;
/*     */   }
/*     */   
/*     */   public int getRequiredLevel() {
/* 485 */     return this.requiredLevel;
/*     */   }
/*     */   
/*     */   public void setRequiredLevel(int requiredLevel) {
/* 489 */     this.requiredLevel = requiredLevel;
/*     */   }
/*     */   
/*     */   public Float getMinHeight() {
/* 493 */     return this.minHeight;
/*     */   }
/*     */   
/*     */   public void setMinHeight(Float minHeight) {
/* 497 */     this.minHeight = minHeight;
/*     */   }
/*     */   
/*     */   public Float getMaxHeight() {
/* 501 */     return this.maxHeight;
/*     */   }
/*     */   
/*     */   public void setMaxHeight(Float maxHeight) {
/* 505 */     this.maxHeight = maxHeight;
/*     */   }
/*     */   
/*     */   public String getSet() {
/* 509 */     return this.set;
/*     */   }
/*     */   
/*     */   public void setSet(String set) {
/* 513 */     this.set = set;
/*     */   }
/*     */   
/*     */   public String getNotset() {
/* 517 */     return this.notset;
/*     */   }
/*     */   
/*     */   public void setNotset(String notset) {
/* 521 */     this.notset = notset;
/*     */   }
/*     */   
/*     */   public boolean isNoClip() {
/* 525 */     return this.noClip;
/*     */   }
/*     */   
/*     */   public void setNoClip(boolean noClip) {
/* 529 */     this.noClip = noClip;
/*     */   }
/*     */   
/*     */   public boolean isSkipAirport() {
/* 533 */     return this.skipAirport;
/*     */   }
/*     */   
/*     */   public void setSkipAirport(boolean skipAirport) {
/* 537 */     this.skipAirport = skipAirport;
/*     */   }
/*     */   
/*     */   public float getBufferRandomise() {
/* 541 */     return this.bufferRandomise;
/*     */   }
/*     */   
/*     */   public void setBufferRandomise(float bufferRandomise) {
/* 545 */     this.bufferRandomise = bufferRandomise;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Rule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */