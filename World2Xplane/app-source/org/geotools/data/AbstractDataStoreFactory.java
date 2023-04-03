/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.DefaultParameterDescriptorGroup;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ 
/*     */ public abstract class AbstractDataStoreFactory implements DataStoreFactorySpi {
/*     */   public String getDisplayName() {
/*  73 */     String name = getClass().getName();
/*  75 */     name = name.substring(name.lastIndexOf('.'));
/*  76 */     if (name.endsWith("Factory")) {
/*  77 */       name = name.substring(0, name.length() - 7);
/*  78 */     } else if (name.endsWith("FactorySpi")) {
/*  79 */       name = name.substring(0, name.length() - 10);
/*     */     } 
/*  81 */     return name;
/*     */   }
/*     */   
/*     */   public boolean canProcess(Map<String, ?> params) {
/* 115 */     if (params == null)
/* 116 */       return false; 
/* 118 */     DataAccessFactory.Param[] arrayParameters = getParametersInfo();
/* 119 */     for (int i = 0; i < arrayParameters.length; i++) {
/* 120 */       DataAccessFactory.Param param = arrayParameters[i];
/* 122 */       if (!params.containsKey(param.key)) {
/* 123 */         if (param.required)
/* 124 */           return false; 
/*     */       } else {
/*     */         Object value;
/*     */         try {
/* 130 */           value = param.lookUp(params);
/* 131 */         } catch (IOException e) {
/* 136 */           return false;
/*     */         } 
/* 138 */         if (value == null) {
/* 139 */           if (param.required)
/* 140 */             return false; 
/*     */         } else {
/* 143 */           if (!param.type.isInstance(value))
/* 144 */             return false; 
/* 146 */           if (param.metadata != null)
/* 148 */             if (param.metadata.containsKey("options")) {
/* 149 */               List<Object> options = (List<Object>)param.metadata.get("options");
/* 150 */               if (options != null && !options.contains(value))
/* 151 */                 return false; 
/*     */             }  
/*     */         } 
/*     */       } 
/*     */     } 
/* 157 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isAvailable() {
/* 166 */     return true;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameters() {
/* 170 */     DataAccessFactory.Param[] params = getParametersInfo();
/* 171 */     DefaultParameterDescriptor[] parameters = new DefaultParameterDescriptor[params.length];
/* 172 */     for (int i = 0; i < params.length; i++) {
/* 173 */       DataAccessFactory.Param param = params[i];
/* 174 */       parameters[i] = new ParamDescriptor(params[i]);
/*     */     } 
/* 176 */     Map<Object, Object> properties = new HashMap<Object, Object>();
/* 177 */     properties.put("name", getDisplayName());
/* 178 */     properties.put("remarks", getDescription());
/* 179 */     return (ParameterDescriptorGroup)new DefaultParameterDescriptorGroup(properties, (GeneralParameterDescriptor[])parameters);
/*     */   }
/*     */   
/*     */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/* 186 */     return Collections.EMPTY_MAP;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AbstractDataStoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */