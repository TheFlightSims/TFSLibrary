/*     */ package com.world2xplane.Parser;
/*     */ 
/*     */ import com.world2xplane.DataStore.DataStore;
/*     */ import com.world2xplane.OSM.OsmUtils;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.openstreetmap.osmosis.osmbinary.BinaryParser;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public abstract class OSMBaseParser extends BinaryParser {
/*  46 */   private static Logger log = LoggerFactory.getLogger(OSMBaseParser.class);
/*     */   
/*  47 */   private static final NumberFormat format = NumberFormat.getInstance(Locale.UK);
/*     */   
/*     */   protected DataStore dataStore;
/*     */   
/*     */   protected GeneratorStore generatorStore;
/*     */   
/*     */   public OSMBaseParser(DataStore dataStore, GeneratorStore generatorStore) throws IOException {
/*  54 */     this.dataStore = dataStore;
/*  55 */     this.generatorStore = generatorStore;
/*     */   }
/*     */   
/*     */   public Float getHeightFromTags(HashMap<String, String> tags, Long wayId, Long relationId) {
/*  60 */     if (tags.containsKey("height") || tags.containsKey("building:height") || tags.containsKey("building:levels"))
/*  61 */       for (Map.Entry<String, String> tag : tags.entrySet()) {
/*  63 */         if (!((String)tag.getKey()).toLowerCase().contains("max") && !((String)tag.getKey()).toLowerCase().contains("min")) {
/*  64 */           if (((String)tag.getKey()).trim().toLowerCase().equals("height"))
/*     */             try {
/*  67 */               float value = OsmUtils.parseHeight(tag.getValue()).floatValue();
/*  68 */               if (value > 800.0F) {
/*  69 */                 log.error("Invalid Height Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/*     */               } else {
/*  72 */                 return Float.valueOf(value);
/*     */               } 
/*  74 */             } catch (Exception e) {
/*  75 */               log.error("Invalid Height Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/*     */             }  
/*  80 */           if (((String)tag.getKey()).trim().toLowerCase().equals("building:height"))
/*     */             try {
/*  82 */               float value = OsmUtils.parseHeight(tag.getValue()).floatValue();
/*  83 */               if (value > 800.0F) {
/*  84 */                 log.error("Invalid Height Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/*     */               } else {
/*  87 */                 return Float.valueOf(value);
/*     */               } 
/*  89 */             } catch (Exception e) {
/*  90 */               log.error("Invalid Height Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/*     */             }  
/*  95 */           if (((String)tag.getKey()).toLowerCase().contains("building:levels") && !((String)tag.getValue()).contains("-1") && ((String)tag.getValue()).length() < 5)
/*     */             try {
/*  97 */               Float levels = Float.valueOf(format.parse(tag.getValue()).floatValue());
/*  98 */               if (levels != null) {
/*  99 */                 if (levels.floatValue() > 100.0F) {
/* 100 */                   log.error("Invalid building:levels Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/* 102 */                   return null;
/*     */                 } 
/* 104 */                 float height = levels.floatValue() * 3.0F;
/* 105 */                 if (height < 1000.0F && height > 0.0F)
/* 107 */                   return Float.valueOf(height); 
/*     */               } 
/* 110 */             } catch (Exception e) {
/* 111 */               e.printStackTrace();
/* 112 */               log.error("Invalid Building:Levels Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/* 114 */               return null;
/*     */             }  
/*     */         } 
/*     */       }  
/* 123 */     return null;
/*     */   }
/*     */   
/*     */   public static Float getMinHeightFromTags(HashMap<String, String> tags, Long wayId, Long relationId) {
/* 127 */     for (Map.Entry<String, String> tag : tags.entrySet()) {
/* 129 */       if (((String)tag.getKey()).toLowerCase().contains("min_height") && ((String)tag.getValue()).length() < 11)
/*     */         try {
/* 132 */           float value = OsmUtils.parseHeight(tag.getValue()).floatValue();
/* 133 */           if (value > 1000.0F) {
/* 134 */             log.error("Invalid Height Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/*     */           } else {
/* 137 */             return Float.valueOf(value);
/*     */           } 
/* 139 */         } catch (Exception e) {
/* 140 */           log.error("Invalid Height Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/*     */         }  
/* 145 */       if (((String)tag.getKey()).toLowerCase().contains("building:min_levels") && !((String)tag.getValue()).contains("-1") && ((String)tag.getValue()).length() < 5)
/*     */         try {
/* 147 */           Float levels = Float.valueOf(format.parse(tag.getValue()).floatValue());
/* 148 */           if (levels != null) {
/* 149 */             float height = levels.floatValue() * 3.0F;
/* 150 */             if (height < 1000.0F && height > 0.0F)
/* 152 */               return Float.valueOf(height); 
/*     */           } 
/* 155 */         } catch (Exception e) {
/* 156 */           log.error("Invalid Building:Levels Tag " + (String)tag.getValue() + " for " + ((wayId != null) ? (" way http://openstreetmap.org/way/" + wayId) : ("http://openstreetmap.org/relation/" + relationId)));
/* 158 */           return null;
/*     */         }  
/*     */     } 
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   protected Integer writeBuildingTags(HashMap<String, String> tags, long id, Float height) {
/* 171 */     boolean process = shouldCreateBuildingPart(tags, height);
/* 172 */     if (!process)
/* 173 */       return null; 
/*     */     try {
/* 177 */       FileUtils.forceMkdir(new File(this.generatorStore.getOutputFolder() + File.separator + "objects" + File.separator + "parts"));
/* 178 */       File file = new File(this.generatorStore.getOutputFolder() + File.separator + "objects" + File.separator + "parts" + File.separator + "part_" + id + ".txt");
/* 180 */       Properties properties = new Properties();
/* 181 */       properties.putAll(tags);
/* 182 */       FileOutputStream fileOutputStream = new FileOutputStream(file);
/* 183 */       properties.store(fileOutputStream, (String)null);
/* 184 */       fileOutputStream.flush();
/* 185 */       fileOutputStream.close();
/* 186 */       return Integer.valueOf(1);
/* 187 */     } catch (Exception e) {
/* 188 */       e.printStackTrace();
/* 190 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean shouldCreateBuildingPart(HashMap<String, String> tags, Float height) {
/* 195 */     boolean process = true;
/* 196 */     if (height != null && height.floatValue() > this.generatorStore.getFacadeHeightLimit())
/* 197 */       return true; 
/* 199 */     if (tags.containsKey("building:part"))
/* 200 */       return true; 
/* 202 */     if (!tags.containsKey("min_height")) {
/* 204 */       process = false;
/* 207 */       if (tags.containsKey("roof:colour"))
/* 208 */         return true; 
/* 209 */       if (tags.containsKey("roof:color"))
/* 210 */         return true; 
/* 211 */       if (tags.containsKey("roof:height"))
/* 212 */         return true; 
/* 215 */       if (tags.containsKey("building:min_level"))
/* 216 */         return true; 
/* 217 */       if (tags.containsKey("building:min_levels"))
/* 218 */         return true; 
/* 219 */       if (tags.containsKey("building:color"))
/* 220 */         return true; 
/* 221 */       if (tags.containsKey("building:colour"))
/* 222 */         return true; 
/* 223 */       if (tags.containsKey("building:material"))
/* 224 */         return true; 
/* 226 */       if (tags.containsKey("roof:shape")) {
/* 227 */         String shape = tags.get("roof:shape");
/* 228 */         if (shape.equals("flat") || shape.equals("gabled"))
/* 229 */           return false; 
/*     */       } 
/*     */     } 
/* 233 */     return process;
/*     */   }
/*     */   
/*     */   protected Integer generateColoredFacade(HashMap<String, String> tags, Float height, Long wayId, Long relationId) {
/* 270 */     if (wayId == null)
/* 271 */       return null; 
/* 273 */     if (writeBuildingTags(tags, wayId.longValue(), height) != null)
/* 274 */       return Integer.valueOf(-2); 
/* 276 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\OSMBaseParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */