/*     */ package com.world2xplane.Rules.Facade;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.OSM.OSMShape;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.Rules.ObjectDefinitionStore;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ public class FacadeItemList {
/*     */   private GeneratorStore generatorStore;
/*     */   
/*  45 */   List<FacadeItem> facadeItemList = new ArrayList<>();
/*     */   
/*     */   public FacadeItemList(GeneratorStore generatorStore) {
/*  50 */     this.generatorStore = generatorStore;
/*     */   }
/*     */   
/*     */   public boolean parseCsv(String filename, String worldModels) throws Exception {
/*  56 */     File file = new File(worldModels, filename);
/*  57 */     DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/*  59 */     NumberFormat numberFormat = new DecimalFormat("#####.###########", formatSymbols);
/*  63 */     if (!file.exists())
/*  64 */       throw new IOException("File " + file.getAbsolutePath() + " does not exist"); 
/*  67 */     String contents = FileUtils.readFileToString(file);
/*  68 */     contents = contents.replaceAll("\r", "\n");
/*  69 */     String[] lines = contents.split("\n");
/*  70 */     for (int x = 1; x < lines.length; x++) {
/*  71 */       String[] columns = lines[x].split(",");
/*  73 */       if (columns.length > 5)
/*     */         try {
/*  75 */           String minHeightString = columns[0];
/*  76 */           String maxHeightString = columns[1];
/*  77 */           String minAreaString = columns[2];
/*  78 */           String maxAreaString = columns[3];
/*  79 */           String[] regionList = columns[4].split(";");
/*  80 */           String facade = columns[5];
/*  81 */           String roofHeightString = columns[6];
/*  83 */           FacadeItem facadeItem = new FacadeItem();
/*  84 */           if (minHeightString != null && !minHeightString.isEmpty())
/*  85 */             facadeItem.setMinHeight(Float.valueOf(numberFormat.parse(minHeightString).floatValue())); 
/*  87 */           if (maxHeightString != null && !maxHeightString.isEmpty())
/*  88 */             facadeItem.setMaxHeight(Float.valueOf(numberFormat.parse(maxHeightString).floatValue())); 
/*  90 */           if (minAreaString != null && !minAreaString.isEmpty())
/*  91 */             facadeItem.setMinArea(Double.valueOf(numberFormat.parse(minAreaString).doubleValue())); 
/*  93 */           if (maxAreaString != null && !maxAreaString.isEmpty())
/*  94 */             facadeItem.setMaxArea(Double.valueOf(numberFormat.parse(maxAreaString).doubleValue())); 
/*  96 */           if (roofHeightString != null && !roofHeightString.isEmpty() && !roofHeightString.equals("0"))
/*  97 */             facadeItem.setRoofHeight(Float.valueOf(numberFormat.parse(roofHeightString).floatValue())); 
/*  99 */           facadeItem.setFacade(facade);
/* 108 */           if (regionList != null)
/* 109 */             for (String item : regionList)
/* 110 */               facadeItem.getRegions().add(item);  
/* 115 */           facadeItem.setObjectDefinition(Integer.valueOf(this.generatorStore.getObjectDefinitionStore().pushObject(ObjectDefinitionStore.ObjectType.FACADE, facade, null)));
/* 120 */           this.facadeItemList.add(facadeItem);
/* 124 */         } catch (Exception e) {
/* 125 */           e.printStackTrace();
/* 126 */           throw new ParseException("Line " + lines[x] + " is invalid", 0);
/*     */         }  
/*     */     } 
/* 131 */     if (this.facadeItemList.size() == 0)
/* 132 */       return false; 
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(OSMPolygon shape) {
/* 142 */     Float height = shape.getHeight();
/* 145 */     for (FacadeItem item : this.facadeItemList) {
/* 146 */       if (item.getMinHeight() == null && item.getMaxHeight() == null)
/* 147 */         return true; 
/* 149 */       if (item.getMinHeight() != null && height == null)
/*     */         continue; 
/* 152 */       if (item.getMaxHeight() != null && height == null)
/*     */         continue; 
/* 155 */       if (item.getMinHeight() != null && height.floatValue() < item.getMinHeight().floatValue())
/*     */         continue; 
/* 158 */       if (item.getMaxHeight() != null && height.floatValue() > item.getMaxHeight().floatValue())
/*     */         continue; 
/* 161 */       return true;
/*     */     } 
/* 164 */     return false;
/*     */   }
/*     */   
/*     */   public Integer getObjectDefinition(OSMShape shape) {
/* 169 */     if (GeneratorStore.getGeneratorStore().isEnabledRegions() && shape.outer.getRegions() == null && GeneratorStore.getGeneratorStore().getRegionProviders().size() > 0) {
/* 171 */       Set<String> regions = GeneratorStore.getGeneratorStore().getRegionsAtPoint(shape.outer.getCentroid(), new HashSet());
/* 174 */       shape.outer.setRegions(regions);
/*     */     } else {
/* 176 */       shape.outer.setRegions(this.generatorStore.getEmptyRegions());
/*     */     } 
/* 179 */     Float height = shape.outer.getHeight();
/* 181 */     HashMap<Integer, ArrayList<FacadeItem>> fits = new HashMap<>();
/* 182 */     for (FacadeItem item : this.facadeItemList) {
/* 183 */       if (item.getMinHeight() != null && height != null && 
/* 184 */         height.floatValue() < item.getMinHeight().floatValue())
/*     */         continue; 
/* 188 */       if (item.getMaxHeight() != null && height != null && 
/* 189 */         height.floatValue() > item.getMaxHeight().floatValue())
/*     */         continue; 
/* 194 */       int score = 0;
/* 195 */       if (height != null) {
/* 196 */         if (item.getMinHeight() == null && item.getMaxHeight() == null)
/* 197 */           score = 99; 
/* 199 */         if (item.getMinHeight() != null && item.getMaxHeight() == null)
/* 200 */           score = 1; 
/* 202 */         if (item.getMaxHeight() != null && item.getMinHeight() == null)
/* 203 */           score = 1; 
/*     */       } 
/* 206 */       if (!fits.containsKey(Integer.valueOf(score)))
/* 207 */         fits.put(Integer.valueOf(score), new ArrayList<>()); 
/* 209 */       ((ArrayList<FacadeItem>)fits.get(Integer.valueOf(score))).add(item);
/*     */     } 
/* 212 */     if (fits.isEmpty())
/* 213 */       return null; 
/* 217 */     ArrayList<Integer> points = new ArrayList<>();
/* 218 */     for (Iterator<Integer> i$ = fits.keySet().iterator(); i$.hasNext(); ) {
/* 218 */       int item = ((Integer)i$.next()).intValue();
/* 219 */       if (!points.contains(Integer.valueOf(item)))
/* 220 */         points.add(Integer.valueOf(item)); 
/*     */     } 
/* 223 */     Collections.sort(points);
/* 225 */     ArrayList<FacadeItem> highestScore = fits.get(points.get(0));
/* 226 */     if (highestScore.size() == 1)
/* 227 */       return ((FacadeItem)highestScore.get(0)).getObjectDefinition(); 
/* 229 */     FacadeItem highest = highestScore.get(Rule.getRandomNumber(0, highestScore.size()));
/* 232 */     if (highest.getRoofHeight() != null && shape.outer.getHeight() != null)
/* 233 */       shape.outer.setHeight(Float.valueOf(shape.outer.getHeight().floatValue() + highest.getRoofHeight().floatValue())); 
/* 236 */     return highest.getObjectDefinition();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Facade\FacadeItemList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */