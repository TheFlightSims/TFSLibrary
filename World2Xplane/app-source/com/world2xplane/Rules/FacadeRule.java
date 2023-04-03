/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.OSM.OSMShape;
/*     */ import com.world2xplane.Rules.Facade.FacadeItemList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class FacadeRule extends Rule {
/*     */   public static class FacadeRuleDistribution {
/*     */     private String identifier;
/*     */     
/*     */     private int min;
/*     */     
/*     */     private int max;
/*     */     
/*     */     private Integer percentage;
/*     */     
/*     */     public String getIdentifier() {
/*  42 */       return this.identifier;
/*     */     }
/*     */     
/*     */     public void setIdentifier(String identifier) {
/*  46 */       this.identifier = identifier;
/*     */     }
/*     */     
/*     */     public int getMin() {
/*  50 */       return this.min;
/*     */     }
/*     */     
/*     */     public void setMin(int min) {
/*  54 */       this.min = min;
/*     */     }
/*     */     
/*     */     public int getMax() {
/*  58 */       return this.max;
/*     */     }
/*     */     
/*     */     public void setMax(int max) {
/*  62 */       this.max = max;
/*     */     }
/*     */     
/*     */     public void setPercentage(Integer percentage) {
/*  66 */       this.percentage = percentage;
/*     */     }
/*     */     
/*     */     public Integer getPercentage() {
/*  70 */       return this.percentage;
/*     */     }
/*     */   }
/*     */   
/*  74 */   private int minRandomHeight = 3;
/*     */   
/*  75 */   private int maxRandomHeight = 8;
/*     */   
/*  77 */   private int minArea = -1;
/*     */   
/*  78 */   private int maxArea = -1;
/*     */   
/*  79 */   private Boolean skipLastPoint = Boolean.valueOf(true);
/*     */   
/*     */   private Boolean building;
/*     */   
/*     */   private boolean simple;
/*     */   
/*     */   private String areaType;
/*     */   
/*     */   private boolean allowUnclosed = false;
/*     */   
/*  86 */   private float roofHeight = 0.0F;
/*     */   
/*     */   private FacadeItemList facadeItemList;
/*     */   
/*  90 */   private List<FacadeRuleDistribution> facadeRuleDistributions = new ArrayList<>();
/*     */   
/*  92 */   private final List<Integer> facadeFiles = new ArrayList<>();
/*     */   
/*     */   public FacadeRule(GeneratorStore generatorStore) {
/*  95 */     super(generatorStore);
/*     */   }
/*     */   
/*     */   public int getMinRandomHeight() {
/*  99 */     return this.minRandomHeight;
/*     */   }
/*     */   
/*     */   public void setMinRandomHeight(int minRandomHeight) {
/* 103 */     this.minRandomHeight = minRandomHeight;
/*     */   }
/*     */   
/*     */   public int getMaxRandomHeight() {
/* 107 */     return this.maxRandomHeight;
/*     */   }
/*     */   
/*     */   public void setMaxRandomHeight(int maxRandomHeight) {
/* 111 */     this.maxRandomHeight = maxRandomHeight;
/*     */   }
/*     */   
/*     */   public List<Integer> getFacadeFiles() {
/* 115 */     return this.facadeFiles;
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumber(Object object) {
/* 119 */     if (this.facadeItemList != null && 
/* 120 */       object != null && object instanceof OSMShape)
/* 121 */       return this.facadeItemList.getObjectDefinition((OSMShape)object); 
/* 124 */     if (this.facadeFiles.size() == 0) {
/* 125 */       if (getAreaType().equals("residential"))
/* 126 */         return Integer.valueOf(getRandomResidential()); 
/* 128 */       if (getAreaType().equals("industrial"))
/* 129 */         return Integer.valueOf(getRandomIndustrial()); 
/* 131 */       if (getAreaType().equals("commercial"))
/* 132 */         return Integer.valueOf(getRandomCommercial()); 
/* 134 */       if (getAreaType().equals("sloped-residential")) {
/* 135 */         if (object != null && object instanceof OSMShape && 
/* 136 */           ((OSMShape)object).multiPolygonPart)
/* 137 */           return Integer.valueOf(getRandomResidential()); 
/* 140 */         return Integer.valueOf(getRandomSloped());
/*     */       } 
/* 142 */       if (getAreaType().equals("random"))
/* 143 */         return Integer.valueOf(getRandomFacade()); 
/* 145 */       throw new RuntimeException("No facade or type found for object.");
/*     */     } 
/* 147 */     int facadeNumber = getRandomNumber(0, this.facadeFiles.size());
/* 148 */     return this.facadeFiles.get(facadeNumber);
/*     */   }
/*     */   
/*     */   private int getRandomFacade() {
/* 152 */     Map<String, List<Integer>> items = this.generatorStore.getObjectDefinitionStore().getDefinedFacades();
/* 153 */     return getDistribution(items);
/*     */   }
/*     */   
/*     */   private int getRandomResidential() {
/* 157 */     Map<String, List<Integer>> items = this.generatorStore.getObjectDefinitionStore().getResidentialFacades();
/* 158 */     return getDistribution(items);
/*     */   }
/*     */   
/*     */   private int getDistribution(Map<String, List<Integer>> items) {
/* 162 */     if (this.facadeRuleDistributions.size() == 0) {
/* 163 */       int i = getRandomNumber(0, items.size());
/* 164 */       String[] arrayOfString = (String[])items.keySet().toArray((Object[])new String[items.keySet().size()]);
/* 165 */       return ((Integer)((List<Integer>)items.get(arrayOfString[i])).get(getRandomNumber(0, ((List)items.get(arrayOfString[i])).size()))).intValue();
/*     */     } 
/* 167 */     int randomSet = getRandomNumber(0, 100);
/* 168 */     for (FacadeRuleDistribution item : this.facadeRuleDistributions) {
/* 169 */       if (items.containsKey(item.getIdentifier()) && randomSet > item.getMin() && randomSet < item.getMax())
/* 170 */         return ((Integer)((List<Integer>)items.get(item.getIdentifier())).get(getRandomNumber(0, ((List)items.get(item.getIdentifier())).size()))).intValue(); 
/*     */     } 
/* 174 */     randomSet = getRandomNumber(0, items.size());
/* 175 */     String[] keys = (String[])items.keySet().toArray((Object[])new String[items.keySet().size()]);
/* 176 */     return ((Integer)((List<Integer>)items.get(keys[randomSet])).get(getRandomNumber(0, ((List)items.get(keys[randomSet])).size()))).intValue();
/*     */   }
/*     */   
/*     */   private int getRandomIndustrial() {
/* 180 */     Map<String, List<Integer>> items = this.generatorStore.getObjectDefinitionStore().getIndustrialFacades();
/* 181 */     return getDistribution(items);
/*     */   }
/*     */   
/*     */   private int getRandomCommercial() {
/* 185 */     Map<String, List<Integer>> items = this.generatorStore.getObjectDefinitionStore().getCommercialFacades();
/* 186 */     return getDistribution(items);
/*     */   }
/*     */   
/*     */   private int getRandomSloped() {
/* 190 */     Map<String, List<Integer>> items = this.generatorStore.getObjectDefinitionStore().getSlopedFacades();
/* 191 */     return getDistribution(items);
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 195 */     return getRandomNumber(this.minRandomHeight, this.maxRandomHeight + 1);
/*     */   }
/*     */   
/*     */   public boolean isSimple() {
/* 199 */     return this.simple;
/*     */   }
/*     */   
/*     */   public void setSimple(boolean simple) {
/* 203 */     this.simple = simple;
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(Byte tagList, OSMPolygon shape, Set<String> regionsFromDsf, Rule.Delegate delegate, boolean acceptOnly) {
/* 214 */     if (shape == null)
/* 215 */       return false; 
/* 218 */     if (getFacadeItemList() != null && 
/* 219 */       !getFacadeItemList().acceptsShape(shape))
/* 220 */       return false; 
/* 225 */     if (!validateShape(tagList, shape, regionsFromDsf, delegate))
/* 226 */       return false; 
/* 229 */     boolean accept = true;
/* 232 */     if (this.minArea != -1 || this.maxArea != -1) {
/* 233 */       double area = shape.getArea().floatValue();
/* 234 */       if (area != 0.0D) {
/* 235 */         if (this.minArea != -1 && area < (this.minArea * this.minArea))
/* 236 */           accept = false; 
/* 238 */         if (this.maxArea != -1 && area > (this.maxArea * this.maxArea))
/* 239 */           accept = false; 
/*     */       } 
/*     */     } 
/* 245 */     return accept;
/*     */   }
/*     */   
/*     */   public int getMinArea() {
/* 249 */     return this.minArea;
/*     */   }
/*     */   
/*     */   public void setMinArea(int minArea) {
/* 253 */     this.minArea = minArea;
/*     */   }
/*     */   
/*     */   public int getMaxArea() {
/* 257 */     return this.maxArea;
/*     */   }
/*     */   
/*     */   public void setMaxArea(int maxArea) {
/* 261 */     this.maxArea = maxArea;
/*     */   }
/*     */   
/*     */   public String getAreaType() {
/* 265 */     return this.areaType;
/*     */   }
/*     */   
/*     */   public void setAreaType(String areaType) {
/* 269 */     this.areaType = areaType;
/*     */   }
/*     */   
/*     */   public List<FacadeRuleDistribution> getFacadeRuleDistributions() {
/* 273 */     return this.facadeRuleDistributions;
/*     */   }
/*     */   
/*     */   public void sortDistributions() {
/* 277 */     Collections.sort(this.facadeRuleDistributions, new Comparator<FacadeRuleDistribution>() {
/*     */           public int compare(FacadeRule.FacadeRuleDistribution o1, FacadeRule.FacadeRuleDistribution o2) {
/* 280 */             return (new Integer(o1.percentage.intValue())).compareTo(new Integer(o2.percentage.intValue()));
/*     */           }
/*     */         });
/* 284 */     int runningCount = 0;
/* 285 */     for (FacadeRuleDistribution item : this.facadeRuleDistributions) {
/* 286 */       item.setMin(runningCount);
/* 287 */       item.setMax(runningCount + item.getPercentage().intValue());
/* 288 */       runningCount = item.getPercentage().intValue();
/*     */     } 
/* 291 */     int x = 23;
/*     */   }
/*     */   
/*     */   public boolean isAllowUnclosed() {
/* 295 */     return this.allowUnclosed;
/*     */   }
/*     */   
/*     */   public void setAllowUnclosed(boolean allowUnclosed) {
/* 299 */     this.allowUnclosed = allowUnclosed;
/*     */   }
/*     */   
/*     */   public float getRoofHeight() {
/* 303 */     return this.roofHeight;
/*     */   }
/*     */   
/*     */   public void setRoofHeight(float roofHeight) {
/* 307 */     this.roofHeight = roofHeight;
/*     */   }
/*     */   
/*     */   public Boolean isSkipLastPoint() {
/* 311 */     return this.skipLastPoint;
/*     */   }
/*     */   
/*     */   public void setSkipLastPoint(Boolean skipLastPoint) {
/* 315 */     this.skipLastPoint = skipLastPoint;
/*     */   }
/*     */   
/*     */   public Boolean getBuilding() {
/* 319 */     return this.building;
/*     */   }
/*     */   
/*     */   public void setBuilding(Boolean building) {
/* 323 */     this.building = building;
/*     */   }
/*     */   
/*     */   public FacadeItemList getFacadeItemList() {
/* 327 */     return this.facadeItemList;
/*     */   }
/*     */   
/*     */   public void setFacadeItemList(FacadeItemList facadeItemList) {
/* 331 */     this.facadeItemList = facadeItemList;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\FacadeRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */