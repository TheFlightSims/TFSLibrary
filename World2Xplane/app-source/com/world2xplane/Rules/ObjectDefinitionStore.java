/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.Rules.Facade.osm2xp.Facade;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ObjectDefinitionStore {
/*     */   private final GeneratorStore generatorStore;
/*     */   
/*     */   private HashMap<String, List<Integer>> definedFacades;
/*     */   
/*     */   public synchronized Integer getFacade(String s) {
/*  42 */     int count = 0;
/*  43 */     for (ObjectDefinition item : this.polygonDefinitions) {
/*  45 */       if (item.getPath().equals(s))
/*  46 */         return Integer.valueOf(count); 
/*  48 */       count++;
/*     */     } 
/*  50 */     return null;
/*     */   }
/*     */   
/*     */   public enum ObjectType {
/*  54 */     FOREST, FACADE, OBJECT;
/*     */   }
/*     */   
/*     */   public enum RotationPoint {
/*  60 */     CENTER, BOTTOM_RIGHT, BOTTOM_CENTER, BOTTOM_LEFT, RIGHT_MIDDLE;
/*     */   }
/*     */   
/*     */   public class ObjectDefinition {
/*     */     private ObjectDefinitionStore.ObjectType objectType;
/*     */     
/*     */     private String path;
/*     */     
/*     */     private Facade facade;
/*     */     
/*     */     private Integer identifier;
/*     */     
/*     */     private ObjectDefinitionStore.RotationPoint rotationPoint;
/*     */     
/*     */     public boolean equals(Object o) {
/*  76 */       if (o == null || !(o instanceof ObjectDefinition))
/*  77 */         return false; 
/*  79 */       return ((ObjectDefinition)o).path.equals(this.path);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  84 */       if (this.path != null)
/*  85 */         return this.path.hashCode(); 
/*  87 */       return super.hashCode();
/*     */     }
/*     */     
/*     */     public ObjectDefinitionStore.ObjectType getObjectType() {
/*  91 */       return this.objectType;
/*     */     }
/*     */     
/*     */     public void setObjectType(ObjectDefinitionStore.ObjectType objectType) {
/*  95 */       this.objectType = objectType;
/*     */     }
/*     */     
/*     */     public String getPath() {
/*  99 */       return this.path;
/*     */     }
/*     */     
/*     */     public void setPath(String path) {
/* 103 */       this.path = path;
/*     */     }
/*     */     
/*     */     public Facade getFacade() {
/* 108 */       return this.facade;
/*     */     }
/*     */     
/*     */     public void setFacade(Facade facade) {
/* 112 */       this.facade = facade;
/*     */     }
/*     */     
/*     */     public void setRotationPoint(ObjectDefinitionStore.RotationPoint rotationPoint) {
/* 117 */       this.rotationPoint = rotationPoint;
/*     */     }
/*     */     
/*     */     public ObjectDefinitionStore.RotationPoint getRotationPoint() {
/* 121 */       return this.rotationPoint;
/*     */     }
/*     */   }
/*     */   
/* 126 */   private volatile List<ObjectDefinition> polygonDefinitions = new ArrayList<>();
/*     */   
/* 127 */   private volatile List<ObjectDefinition> objectDefinitions = new ArrayList<>();
/*     */   
/*     */   private Map<String, List<Integer>> residentialFacades;
/*     */   
/*     */   private Map<String, List<Integer>> commercialFacades;
/*     */   
/*     */   private Map<String, List<Integer>> industrialFacades;
/*     */   
/*     */   private Map<String, List<Integer>> slopedFacades;
/*     */   
/*     */   public synchronized Map<String, List<Integer>> getResidentialFacades() {
/* 137 */     if (this.residentialFacades == null) {
/* 138 */       this.residentialFacades = new HashMap<>();
/* 141 */       int pos = 0;
/* 142 */       for (ObjectDefinition item : this.polygonDefinitions) {
/* 143 */         if (item.getFacade() != null && item.getFacade().isResidential() && !item.getFacade().isSloped()) {
/* 144 */           if (!this.residentialFacades.containsKey(item.getFacade().getIdentifier()))
/* 145 */             this.residentialFacades.put(item.getFacade().getIdentifier(), new ArrayList<>()); 
/* 147 */           ((List<Integer>)this.residentialFacades.get(item.getFacade().getIdentifier())).add(Integer.valueOf(pos));
/*     */         } 
/* 149 */         pos++;
/*     */       } 
/*     */     } 
/* 152 */     return this.residentialFacades;
/*     */   }
/*     */   
/*     */   public synchronized Map<String, List<Integer>> getDefinedFacades() {
/* 156 */     if (this.definedFacades == null) {
/* 157 */       this.definedFacades = new HashMap<>();
/* 160 */       int pos = 0;
/* 161 */       for (ObjectDefinition item : this.polygonDefinitions) {
/* 162 */         if (item.getFacade() != null) {
/* 163 */           if (!this.definedFacades.containsKey(item.getFacade().getIdentifier()))
/* 164 */             this.definedFacades.put(item.getFacade().getIdentifier(), new ArrayList<>()); 
/* 166 */           ((List<Integer>)this.definedFacades.get(item.getFacade().getIdentifier())).add(Integer.valueOf(pos));
/*     */         } 
/* 168 */         pos++;
/*     */       } 
/*     */     } 
/* 171 */     return this.definedFacades;
/*     */   }
/*     */   
/*     */   public synchronized Map<String, List<Integer>> getCommercialFacades() {
/* 175 */     if (this.commercialFacades == null) {
/* 176 */       this.commercialFacades = new HashMap<>();
/* 179 */       int pos = 0;
/* 180 */       for (ObjectDefinition item : this.polygonDefinitions) {
/* 181 */         if (item.getFacade() != null && item.getFacade().isCommercial()) {
/* 182 */           if (!this.commercialFacades.containsKey(item.getFacade().getIdentifier()))
/* 183 */             this.commercialFacades.put(item.getFacade().getIdentifier(), new ArrayList<>()); 
/* 185 */           ((List<Integer>)this.commercialFacades.get(item.getFacade().getIdentifier())).add(Integer.valueOf(pos));
/*     */         } 
/* 187 */         pos++;
/*     */       } 
/*     */     } 
/* 190 */     return this.commercialFacades;
/*     */   }
/*     */   
/*     */   public synchronized Map<String, List<Integer>> getIndustrialFacades() {
/* 194 */     if (this.industrialFacades == null) {
/* 195 */       this.industrialFacades = new HashMap<>();
/* 198 */       int pos = 0;
/* 199 */       for (ObjectDefinition item : this.polygonDefinitions) {
/* 200 */         if (item.getFacade() != null && item.getFacade().isIndustrial()) {
/* 201 */           if (!this.industrialFacades.containsKey(item.getFacade().getIdentifier()))
/* 202 */             this.industrialFacades.put(item.getFacade().getIdentifier(), new ArrayList<>()); 
/* 204 */           ((List<Integer>)this.industrialFacades.get(item.getFacade().getIdentifier())).add(Integer.valueOf(pos));
/*     */         } 
/* 206 */         pos++;
/*     */       } 
/*     */     } 
/* 209 */     return this.industrialFacades;
/*     */   }
/*     */   
/*     */   public synchronized Map<String, List<Integer>> getSlopedFacades() {
/* 213 */     if (this.slopedFacades == null) {
/* 214 */       this.slopedFacades = new HashMap<>();
/* 217 */       int pos = 0;
/* 218 */       for (ObjectDefinition item : this.polygonDefinitions) {
/* 219 */         if (item.getFacade() != null && item.getFacade().isSloped()) {
/* 220 */           if (!this.slopedFacades.containsKey(item.getFacade().getIdentifier()))
/* 221 */             this.slopedFacades.put(item.getFacade().getIdentifier(), new ArrayList<>()); 
/* 223 */           ((List<Integer>)this.slopedFacades.get(item.getFacade().getIdentifier())).add(Integer.valueOf(pos));
/*     */         } 
/* 225 */         pos++;
/*     */       } 
/*     */     } 
/* 228 */     return this.slopedFacades;
/*     */   }
/*     */   
/*     */   public ObjectDefinitionStore(GeneratorStore generatorStore) {
/* 233 */     this.generatorStore = generatorStore;
/*     */   }
/*     */   
/*     */   public synchronized int pushObject(ObjectType objectType, String path, Facade facade) {
/* 248 */     if (objectType == ObjectType.FOREST || objectType == ObjectType.FACADE) {
/* 249 */       ObjectDefinition polygonDefinition = new ObjectDefinition();
/* 250 */       polygonDefinition.path = path;
/* 251 */       polygonDefinition.objectType = objectType;
/* 252 */       polygonDefinition.facade = facade;
/* 253 */       if (this.polygonDefinitions.contains(polygonDefinition))
/* 254 */         return (short)this.polygonDefinitions.indexOf(polygonDefinition); 
/* 256 */       this.polygonDefinitions.add(polygonDefinition);
/* 258 */       return (short)this.polygonDefinitions.indexOf(polygonDefinition);
/*     */     } 
/* 261 */     ObjectDefinition objectDefinition = new ObjectDefinition();
/* 262 */     objectDefinition.path = path;
/* 263 */     objectDefinition.objectType = objectType;
/* 265 */     if (this.objectDefinitions.contains(objectDefinition))
/* 266 */       return (short)this.objectDefinitions.indexOf(objectDefinition); 
/* 268 */     this.objectDefinitions.add(objectDefinition);
/* 270 */     return this.objectDefinitions.indexOf(objectDefinition);
/*     */   }
/*     */   
/*     */   public synchronized List<ObjectDefinition> getPolygonDefinitions() {
/* 275 */     return this.polygonDefinitions;
/*     */   }
/*     */   
/*     */   public synchronized List<ObjectDefinition> getObjectDefinitions() {
/* 279 */     return this.objectDefinitions;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\ObjectDefinitionStore.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */