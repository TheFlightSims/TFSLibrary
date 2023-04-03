/*     */ package com.world2xplane.Rules.ObjectRules;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.Rules.ObjectDefinitionStore;
/*     */ import com.world2xplane.Rules.ObjectRule;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.lang.math.FloatRange;
/*     */ import org.apache.commons.lang.math.Range;
/*     */ 
/*     */ public class ObjectList {
/*  49 */   Random randomGenerator = new Random();
/*     */   
/*  50 */   final BestFitList bestFitList = new BestFitList();
/*     */   
/*     */   private final GeneratorStore generatorStore;
/*     */   
/*  53 */   private double tolerance = 1.0D;
/*     */   
/*     */   private boolean restrictHeights = false;
/*     */   
/*     */   private FloatRange widthRange;
/*     */   
/*     */   private FloatRange lengthRange;
/*     */   
/*  58 */   private volatile List<String> regions = new ArrayList<>();
/*     */   
/*  59 */   private float defaultHeight = 6.0F;
/*     */   
/*  61 */   private volatile ConcurrentHashMap<Integer, Set<ObjectListEntry>> cache = new ConcurrentHashMap<>();
/*     */   
/*     */   final List<ObjectListEntry> objects;
/*     */   
/*     */   public List<ObjectListEntry> getObjects() {
/*  64 */     return this.objects;
/*     */   }
/*     */   
/*     */   public ObjectListEntry getObjectEntry(String path) {
/*  68 */     for (ObjectListEntry item : this.objects) {
/*  69 */       if (item.model.trim().equals(path.trim()))
/*  70 */         return item; 
/*     */     } 
/*  73 */     return null;
/*     */   }
/*     */   
/*     */   public class ObjectListEntry {
/*     */     float width;
/*     */     
/*     */     float length;
/*     */     
/*     */     Range widthRange;
/*     */     
/*     */     Range lengthRange;
/*     */     
/*     */     String type;
/*     */     
/*     */     List<String> regions;
/*     */     
/*     */     String model;
/*     */     
/*     */     Float minheight;
/*     */     
/*     */     Float maxheight;
/*     */     
/*     */     int objectDefinitionNumber;
/*     */     
/*     */     int levels;
/*     */     
/*     */     private String shape;
/*     */     
/*     */     public Set<String> tags;
/*     */     
/*     */     private ObjectListEntry(float width, float length, String type, String[] regionList, String model, float minheight, float maxheight, int levels, String shape, Set<String> tags) {
/* 103 */       this.width = width;
/* 104 */       this.length = length;
/* 105 */       this.type = type;
/* 106 */       if (regionList != null) {
/* 107 */         this.regions = new ArrayList<>();
/* 108 */         for (String item : regionList) {
/* 109 */           if (!this.regions.contains(item) && !item.isEmpty())
/* 110 */             this.regions.add(item); 
/*     */         } 
/*     */       } 
/* 113 */       this.model = model;
/* 114 */       this.minheight = Float.valueOf(minheight);
/* 115 */       this.maxheight = Float.valueOf(maxheight);
/* 116 */       this.levels = levels;
/* 117 */       this.shape = shape;
/* 118 */       this.tags = tags;
/* 120 */       this.widthRange = (Range)new FloatRange(Double.valueOf(this.width - ObjectList.this.tolerance), Double.valueOf(this.width + ObjectList.this.tolerance));
/* 121 */       this.lengthRange = (Range)new FloatRange(Double.valueOf(this.length - ObjectList.this.tolerance), Double.valueOf(this.length + ObjectList.this.tolerance));
/* 122 */       this.objectDefinitionNumber = ObjectList.this.generatorStore.getObjectDefinitionStore().pushObject(ObjectDefinitionStore.ObjectType.OBJECT, model, null);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 128 */       return String.format("%s: %f %f, %f %f %d", new Object[] { this.model, Float.valueOf(this.width), Float.valueOf(this.length), this.minheight, this.maxheight, Integer.valueOf(this.levels) });
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 133 */       return this.model.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 138 */       if (other == null || !(other instanceof ObjectListEntry))
/* 139 */         return false; 
/* 141 */       return this.model.equals(((ObjectListEntry)other).model);
/*     */     }
/*     */     
/*     */     public double getMinAreaY() {
/* 145 */       return this.lengthRange.getMinimumFloat();
/*     */     }
/*     */     
/*     */     public double getMaxAreaY() {
/* 149 */       return this.lengthRange.getMaximumFloat();
/*     */     }
/*     */     
/*     */     public double getMinAreaX() {
/* 153 */       return this.widthRange.getMinimumFloat();
/*     */     }
/*     */     
/*     */     public double getMaxAreaX() {
/* 157 */       return this.widthRange.getMaximumFloat();
/*     */     }
/*     */     
/*     */     public List<String> getRegions() {
/* 161 */       return this.regions;
/*     */     }
/*     */     
/*     */     public void setRegions(List<String> regions) {
/* 165 */       this.regions = regions;
/*     */     }
/*     */     
/*     */     public String getShape() {
/* 169 */       return this.shape;
/*     */     }
/*     */     
/*     */     public void setShape(String shape) {
/* 173 */       this.shape = shape;
/*     */     }
/*     */   }
/*     */   
/*     */   public ObjectList(GeneratorStore generatorStore, String regionList, boolean restrictHeights) {
/* 193 */     this.objects = new ArrayList<>();
/*     */     this.generatorStore = generatorStore;
/*     */     this.restrictHeights = restrictHeights;
/*     */     if (regionList != null && !regionList.isEmpty())
/*     */       for (String item : regionList.split(",")) {
/*     */         if (!item.isEmpty())
/*     */           this.regions.add(item); 
/*     */       }  
/*     */   }
/*     */   
/*     */   public boolean parseCsv(String filename, String worldModels) throws Exception {
/* 198 */     File file = new File(worldModels, filename);
/* 199 */     DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/* 201 */     NumberFormat numberFormat = new DecimalFormat("#####.###########", formatSymbols);
/* 205 */     if (!file.exists())
/* 206 */       throw new IOException("File " + file.getAbsolutePath() + " does not exist"); 
/* 209 */     String contents = FileUtils.readFileToString(file);
/* 210 */     contents = contents.replaceAll("\r", "\n");
/* 211 */     String[] lines = contents.split("\n");
/* 212 */     for (int x = 1; x < lines.length; x++) {
/* 213 */       String[] columns = lines[x].split(",");
/* 215 */       if (columns.length > 2)
/*     */         try {
/* 217 */           float width = numberFormat.parse(columns[0]).floatValue();
/* 218 */           float length = numberFormat.parse(columns[1]).floatValue();
/* 219 */           String type = columns[2];
/* 220 */           String[] regionList = columns[3].split(";");
/* 221 */           String model = columns[4];
/* 222 */           float minheight = 0.0F;
/* 223 */           String shape = "";
/* 224 */           if (columns.length > 5 && !columns[5].isEmpty())
/* 225 */             minheight = numberFormat.parse(columns[5]).floatValue(); 
/* 227 */           float maxheight = 0.0F;
/* 228 */           if (columns.length > 6 && !columns[6].isEmpty())
/* 229 */             maxheight = numberFormat.parse(columns[6]).floatValue(); 
/* 231 */           int levels = 0;
/* 232 */           if (columns.length > 7 && !columns[7].isEmpty())
/* 233 */             levels = numberFormat.parse(columns[7]).intValue(); 
/* 236 */           if (columns.length > 8 && !columns[8].isEmpty())
/* 237 */             shape = columns[8].trim(); 
/* 240 */           String lib = null;
/* 241 */           if (columns.length > 9 && !columns[9].isEmpty())
/* 242 */             lib = columns[9].trim(); 
/* 244 */           if (lib == null || lib.length() <= 0 || 
/* 245 */             this.generatorStore.hasIncludeLib(lib)) {
/* 250 */             Set<String> tags = new HashSet<>();
/* 251 */             if (columns.length > 10 && !columns[10].isEmpty()) {
/* 252 */               String text = columns[10].trim().toLowerCase();
/* 253 */               String[] tagList = text.split(";");
/* 254 */               for (String item : tagList)
/* 255 */                 tags.add(item); 
/*     */             } 
/* 261 */             if (regionList.length > 0)
/* 262 */               addEntryToList(new ObjectListEntry(width, length, type, regionList, model, minheight, maxheight, levels, shape, tags)); 
/*     */           } 
/* 267 */         } catch (Exception e) {
/* 268 */           e.printStackTrace();
/* 269 */           throw new ParseException("Line " + lines[x] + " is invalid", 0);
/*     */         }  
/*     */     } 
/* 274 */     if (this.objects.size() == 0)
/* 275 */       return false; 
/* 287 */     float minX = ((ObjectListEntry)Collections.min((Collection)this.objects, (Comparator)new Comparator<ObjectListEntry>() {
/*     */           public int compare(ObjectList.ObjectListEntry objectListEntry, ObjectList.ObjectListEntry objectListEntry2) {
/* 289 */             return ObjectList.this.compareFloat(objectListEntry.widthRange.getMinimumFloat(), objectListEntry2.widthRange.getMinimumFloat());
/*     */           }
/*     */         })).widthRange.getMinimumFloat();
/* 293 */     float maxX = ((ObjectListEntry)Collections.min((Collection)this.objects, (Comparator)new Comparator<ObjectListEntry>() {
/*     */           public int compare(ObjectList.ObjectListEntry objectListEntry, ObjectList.ObjectListEntry objectListEntry2) {
/* 295 */             return ObjectList.this.compareFloat(objectListEntry2.widthRange.getMaximumFloat(), objectListEntry.widthRange.getMaximumFloat());
/*     */           }
/*     */         })).widthRange.getMaximumFloat();
/* 299 */     float minY = ((ObjectListEntry)Collections.min((Collection)this.objects, (Comparator)new Comparator<ObjectListEntry>() {
/*     */           public int compare(ObjectList.ObjectListEntry objectListEntry, ObjectList.ObjectListEntry objectListEntry2) {
/* 301 */             return ObjectList.this.compareFloat(objectListEntry.lengthRange.getMinimumFloat(), objectListEntry2.lengthRange.getMinimumFloat());
/*     */           }
/*     */         })).lengthRange.getMinimumFloat();
/* 305 */     float maxY = ((ObjectListEntry)Collections.min((Collection)this.objects, (Comparator)new Comparator<ObjectListEntry>() {
/*     */           public int compare(ObjectList.ObjectListEntry objectListEntry, ObjectList.ObjectListEntry objectListEntry2) {
/* 307 */             return ObjectList.this.compareFloat(objectListEntry2.lengthRange.getMaximumFloat(), objectListEntry.lengthRange.getMaximumFloat());
/*     */           }
/*     */         })).lengthRange.getMaximumFloat();
/* 312 */     this.widthRange = new FloatRange(minX, maxX);
/* 313 */     this.lengthRange = new FloatRange(minY, maxY);
/* 315 */     return true;
/*     */   }
/*     */   
/*     */   private int compareFloat(float minimumFloat, float minimumFloat1) {
/* 320 */     return (minimumFloat < minimumFloat1) ? -1 : 1;
/*     */   }
/*     */   
/*     */   private void addEntryToList(ObjectListEntry objectListEntry) {
/* 324 */     this.objects.add(objectListEntry);
/*     */   }
/*     */   
/*     */   public Set<ObjectListEntry> getItems(Set<String> regions, float width, float length) {
/* 338 */     if (this.widthRange == null)
/* 339 */       throw new RuntimeException("Object List has not been initialised"); 
/* 342 */     boolean enabledRegions = GeneratorStore.getGeneratorStore().isEnabledRegions();
/* 343 */     Set<ObjectListEntry> objectDefinitions = new HashSet<>();
/* 346 */     float f1 = Math.round(width * 4.0F) / 4.0F;
/* 347 */     float f2 = Math.round(length * 4.0F) / 4.0F;
/* 348 */     int hashCode = ("" + f1 + "_" + f2 + "_" + regions.hashCode() + "").hashCode();
/*     */     try {
/* 351 */       hashCode = ("" + f1 + "_" + f2 + "_" + regions.hashCode() + "").hashCode();
/* 352 */       if (this.cache.containsKey(Integer.valueOf(hashCode)))
/* 353 */         return this.cache.get(Integer.valueOf(hashCode)); 
/* 355 */     } catch (Exception e) {
/* 356 */       e.printStackTrace();
/*     */     } 
/* 360 */     if (this.widthRange.containsFloat(width) && this.lengthRange.containsFloat(length))
/* 362 */       for (ObjectListEntry item : this.objects) {
/* 363 */         if (item.widthRange.containsFloat(width) && item.lengthRange.containsFloat(length)) {
/* 365 */           boolean pass = true;
/* 366 */           if (enabledRegions && !item.regions.isEmpty()) {
/* 367 */             boolean exists = false;
/* 368 */             for (String objectRegion : regions) {
/* 369 */               for (String region : item.regions) {
/* 370 */                 if (objectRegion.equals(region))
/* 371 */                   exists = true; 
/*     */               } 
/*     */             } 
/* 376 */             pass = exists;
/*     */           } 
/* 380 */           if (pass && !objectDefinitions.contains(item))
/* 381 */             objectDefinitions.add(item); 
/*     */         } 
/*     */       }  
/* 387 */     if (this.widthRange.containsFloat(length) && this.lengthRange.containsFloat(width))
/* 389 */       for (ObjectListEntry item : this.objects) {
/* 390 */         if (item.widthRange.containsFloat(length) && item.lengthRange.containsFloat(width)) {
/* 392 */           boolean pass = true;
/* 393 */           if (enabledRegions && !item.regions.isEmpty()) {
/* 394 */             boolean exists = false;
/* 395 */             for (String objectRegion : regions) {
/* 396 */               for (String region : item.regions) {
/* 397 */                 if (objectRegion.equals(region))
/* 398 */                   exists = true; 
/*     */               } 
/*     */             } 
/* 403 */             pass = exists;
/*     */           } 
/* 406 */           if (pass && !objectDefinitions.contains(item))
/* 407 */             objectDefinitions.add(item); 
/*     */         } 
/*     */       }  
/* 412 */     this.cache.putIfAbsent(Integer.valueOf(hashCode), objectDefinitions);
/* 413 */     return objectDefinitions;
/*     */   }
/*     */   
/*     */   public ObjectListEntry getBestFitObject(OSMPolygon shape, Set<String> regions, float width, float length, boolean restrictShape, Rule.Delegate delegate, ObjectRule rule) {
/* 426 */     Set<ObjectListEntry> objects = getItems(regions, width, length);
/* 427 */     if (objects.size() == 0)
/* 428 */       return null; 
/* 431 */     Float height = shape.getHeight();
/* 432 */     if (height == null && shape.getRule() != null) {
/* 433 */       height = Float.valueOf(((ObjectRule)shape.getRule()).getRandomHeight());
/* 434 */     } else if (height == null) {
/* 435 */       height = Float.valueOf(this.defaultHeight);
/*     */     } 
/* 440 */     ObjectListEntry item = this.bestFitList.getBestFit(rule, shape, objects, regions, width, length, height, this.restrictHeights, restrictShape, delegate);
/* 443 */     return item;
/*     */   }
/*     */   
/*     */   public Integer getBestFit(OSMPolygon shape, Set<String> regionsFromDsf, boolean restrictShape, Rule.Delegate delegate, ObjectRule rule) {
/* 455 */     if (GeneratorStore.getGeneratorStore().isEnabledRegions() && shape.getRegions() == null && GeneratorStore.getGeneratorStore().getRegionProviders().size() > 0) {
/* 457 */       Set<String> regions = GeneratorStore.getGeneratorStore().getRegionsAtPoint(shape.getCentroid(), regionsFromDsf);
/* 459 */       shape.setRegions(regions);
/*     */     } 
/* 462 */     ObjectListEntry bestFit = getBestFitObject(shape, shape.getRegions(), shape.getLongestSide().floatValue(), shape.getShortestSide().floatValue(), restrictShape, delegate, rule);
/* 466 */     if (bestFit != null) {
/* 467 */       int number = bestFit.objectDefinitionNumber;
/* 468 */       bestFit = null;
/* 469 */       return Integer.valueOf(number);
/*     */     } 
/* 471 */     return null;
/*     */   }
/*     */   
/*     */   private int getRandomNumber(int min, int max) {
/* 478 */     if (min == max)
/* 479 */       return max; 
/* 481 */     return this.randomGenerator.nextInt(max - min) + min;
/*     */   }
/*     */   
/*     */   public List<String> getRegions() {
/* 487 */     return this.regions;
/*     */   }
/*     */   
/*     */   public void setTolerance(double tolerance) {
/* 491 */     this.tolerance = tolerance;
/*     */   }
/*     */   
/*     */   public void setDefaultHeight(float defaultHeight) {
/* 495 */     this.defaultHeight = defaultHeight;
/*     */   }
/*     */   
/*     */   public Float getDefaultHeight() {
/* 499 */     return Float.valueOf(this.defaultHeight);
/*     */   }
/*     */   
/*     */   public void setDefaultHeight(Float defaultHeight) {
/* 503 */     this.defaultHeight = defaultHeight.floatValue();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\ObjectRules\ObjectList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */