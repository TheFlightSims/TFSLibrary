/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public class AutogenStringRule extends Rule {
/*     */   private boolean clipToArea;
/*     */   
/*     */   public void setClipToArea(boolean clipToArea) {
/*  39 */     this.clipToArea = clipToArea;
/*     */   }
/*     */   
/*     */   public boolean isClipToArea() {
/*  43 */     return this.clipToArea;
/*     */   }
/*     */   
/*     */   public static class AutogenString {
/*     */     public Integer identifier;
/*     */     
/*     */     public Integer minDistance;
/*     */     
/*     */     public Integer maxDistance;
/*     */     
/*     */     public Double sizeX;
/*     */     
/*     */     public Double sizeY;
/*     */     
/*     */     public HashSet<String> hotspot;
/*     */     
/*     */     public HashSet<String> regions;
/*     */   }
/*     */   
/*  60 */   private final List<AutogenString> autogenStrings = new ArrayList<>();
/*     */   
/*  62 */   private double lineWidth = 0.0D;
/*     */   
/*     */   private boolean collisionTest = false;
/*     */   
/*     */   public AutogenStringRule(GeneratorStore generatorStore) {
/*  66 */     super(generatorStore);
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumber(Object object) {
/*  72 */     if (object instanceof HashSet) {
/*  73 */       HashSet<String> regions = (HashSet<String>)object;
/*  74 */       for (AutogenString item : this.autogenStrings) {
/*  75 */         if (item.regions != null && item.regions.size() > 0)
/*  76 */           for (String region : regions) {
/*  77 */             if (item.regions.contains(region))
/*  78 */               return item.identifier; 
/*     */           }  
/*     */       } 
/*     */     } 
/*  85 */     for (AutogenString item : this.autogenStrings) {
/*  86 */       if (item.regions == null || item.regions.size() == 0)
/*  87 */         return item.identifier; 
/*     */     } 
/*  90 */     return ((AutogenString)this.autogenStrings.get(0)).identifier;
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumberFromHotspot(List<GeneratorStore.HotspotHit> hotspotList, Set<String> regions) {
/*  98 */     List<AutogenString> fit = new ArrayList<>();
/*  99 */     for (GeneratorStore.HotspotHit hotspotHit : hotspotList) {
/* 100 */       for (AutogenString item : this.autogenStrings) {
/* 101 */         if (item.hotspot.contains(hotspotHit.hotspotEntry.getIdentifier())) {
/* 103 */           boolean pass = true;
/* 104 */           if (item.minDistance != null && 
/* 105 */             hotspotHit.distance < item.minDistance.intValue())
/* 106 */             pass = false; 
/* 109 */           if (item.maxDistance != null && 
/* 110 */             hotspotHit.distance > item.maxDistance.intValue())
/* 111 */             pass = false; 
/* 114 */           if (pass && item.regions != null && item.regions.size() > 0) {
/* 115 */             pass = false;
/* 116 */             for (String region : regions) {
/* 117 */               if (item.regions.contains(region))
/* 118 */                 return item.identifier; 
/*     */             } 
/*     */           } 
/* 122 */           if (pass && !fit.contains(item)) {
/* 123 */             fit.add(item);
/* 124 */             return item.identifier;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 131 */     if (fit.size() == 0)
/* 133 */       return getDefinitionNumber(regions); 
/* 135 */     int indexNumber = getRandomNumber(0, fit.size());
/* 136 */     return ((AutogenString)fit.get(0)).identifier;
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(Byte tagList, OSMPolygon shape, Set<String> regionsFromDsf, Rule.Delegate delegate, boolean acceptOnly) {
/* 146 */     if (shape == null)
/* 147 */       return false; 
/* 150 */     if (!validateShape(tagList, shape, regionsFromDsf, delegate))
/* 151 */       return false; 
/* 154 */     return true;
/*     */   }
/*     */   
/*     */   public double getLineWidth() {
/* 158 */     return this.lineWidth;
/*     */   }
/*     */   
/*     */   public void addString(int index, Node hotspot, Node region, Node min, Node max, Node sizeX, Node sizeY) {
/* 162 */     String hotspotName = null;
/* 163 */     if (hotspot != null)
/* 164 */       hotspotName = hotspot.getNodeValue(); 
/* 168 */     AutogenString autogenString = new AutogenString();
/* 169 */     autogenString.identifier = Integer.valueOf(index);
/* 170 */     autogenString.minDistance = (min != null) ? new Integer(min.getNodeValue()) : null;
/* 171 */     autogenString.maxDistance = (max != null) ? new Integer(max.getNodeValue()) : null;
/* 172 */     if (sizeX != null)
/* 173 */       autogenString.sizeX = new Double(sizeX.getNodeValue()); 
/* 175 */     if (sizeY != null)
/* 176 */       autogenString.sizeY = new Double(sizeY.getNodeValue()); 
/* 178 */     autogenString.hotspot = new HashSet<>();
/* 179 */     if (hotspot != null) {
/* 180 */       autogenString.hotspot = new HashSet<>();
/* 181 */       String[] spots = hotspotName.split(",");
/* 182 */       for (String spot : spots)
/* 183 */         autogenString.hotspot.add(spot); 
/*     */     } 
/* 186 */     autogenString.regions = new HashSet<>();
/* 187 */     if (region != null) {
/* 188 */       String[] regions = region.getNodeValue().split(",");
/* 189 */       for (String item : regions)
/* 190 */         autogenString.regions.add(item); 
/*     */     } 
/* 193 */     this.autogenStrings.add(autogenString);
/*     */   }
/*     */   
/*     */   public void setLineWidth(double lineWidth) {
/* 198 */     this.lineWidth = lineWidth;
/*     */   }
/*     */   
/*     */   public double getAutogenOffset(int definitionNumber) {
/* 202 */     for (AutogenString item : this.autogenStrings) {
/* 203 */       if (item.identifier.intValue() == definitionNumber)
/* 204 */         return item.sizeY.doubleValue(); 
/*     */     } 
/* 207 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public boolean isCollisionTest() {
/* 211 */     return this.collisionTest;
/*     */   }
/*     */   
/*     */   public void setCollisionTest(boolean collisionTest) {
/* 215 */     this.collisionTest = collisionTest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\AutogenStringRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */