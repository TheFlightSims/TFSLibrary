/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigValueType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ final class DefaultTransformer {
/*     */   static AbstractConfigValue transform(AbstractConfigValue value, ConfigValueType requested) {
/*  21 */     if (value.valueType() == ConfigValueType.STRING) {
/*  22 */       String s = (String)value.unwrapped();
/*  23 */       switch (requested) {
/*     */         case NUMBER:
/*     */           try {
/*  26 */             Long v = Long.valueOf(Long.parseLong(s));
/*  27 */             return new ConfigLong(value.origin(), v.longValue(), s);
/*  28 */           } catch (NumberFormatException e) {
/*     */             try {
/*  32 */               Double v = Double.valueOf(Double.parseDouble(s));
/*  33 */               return new ConfigDouble(value.origin(), v.doubleValue(), s);
/*  34 */             } catch (NumberFormatException numberFormatException) {
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         case NULL:
/*  39 */           if (s.equals("null"))
/*  40 */             return new ConfigNull(value.origin()); 
/*     */           break;
/*     */         case BOOLEAN:
/*  43 */           if (s.equals("true") || s.equals("yes") || s.equals("on"))
/*  44 */             return new ConfigBoolean(value.origin(), true); 
/*  45 */           if (s.equals("false") || s.equals("no") || s.equals("off"))
/*  47 */             return new ConfigBoolean(value.origin(), false); 
/*     */           break;
/*     */       } 
/*  60 */     } else if (requested == ConfigValueType.STRING) {
/*  64 */       switch (value.valueType()) {
/*     */         case NUMBER:
/*     */         case BOOLEAN:
/*  67 */           return new ConfigString(value.origin(), value.transformToString());
/*     */       } 
/*  83 */     } else if (requested == ConfigValueType.LIST && value.valueType() == ConfigValueType.OBJECT) {
/*  91 */       AbstractConfigObject o = (AbstractConfigObject)value;
/*  92 */       Map<Integer, AbstractConfigValue> values = new HashMap<Integer, AbstractConfigValue>();
/*  93 */       for (String key : o.keySet()) {
/*     */         try {
/*  96 */           int i = Integer.parseInt(key, 10);
/*  97 */           if (i < 0)
/*     */             continue; 
/*  99 */           values.put(Integer.valueOf(i), o.get(key));
/* 100 */         } catch (NumberFormatException e) {}
/*     */       } 
/* 104 */       if (!values.isEmpty()) {
/* 105 */         ArrayList<Map.Entry<Integer, AbstractConfigValue>> entryList = new ArrayList<Map.Entry<Integer, AbstractConfigValue>>(values.entrySet());
/* 108 */         Collections.sort(entryList, new Comparator<Map.Entry<Integer, AbstractConfigValue>>() {
/*     */               public int compare(Map.Entry<Integer, AbstractConfigValue> a, Map.Entry<Integer, AbstractConfigValue> b) {
/* 115 */                 return Integer.valueOf(((Integer)a.getKey()).intValue()).compareTo(b.getKey());
/*     */               }
/*     */             });
/* 120 */         ArrayList<AbstractConfigValue> list = new ArrayList<AbstractConfigValue>();
/* 121 */         for (Map.Entry<Integer, AbstractConfigValue> entry : entryList)
/* 122 */           list.add(entry.getValue()); 
/* 124 */         return new SimpleConfigList(value.origin(), list);
/*     */       } 
/*     */     } 
/* 128 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\DefaultTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */