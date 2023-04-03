/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ import org.java.plugin.registry.Extension;
/*     */ import org.java.plugin.registry.ExtensionPoint;
/*     */ import org.java.plugin.registry.ParameterType;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ 
/*     */ class ParameterValueParser {
/*     */   private Object value;
/*     */   
/*     */   private final boolean isParsingSucceeds;
/*     */   
/*     */   private String parsingMessage;
/*     */   
/*     */   private static ExtensionPoint getExtensionPoint(PluginRegistry registry, String uniqueId) {
/*  41 */     String pluginId = registry.extractPluginId(uniqueId);
/*  42 */     if (!registry.isPluginDescriptorAvailable(pluginId))
/*  43 */       return null; 
/*  45 */     String pointId = registry.extractId(uniqueId);
/*  46 */     for (ExtensionPoint point : registry.getPluginDescriptor(pluginId).getExtensionPoints()) {
/*  48 */       if (point.getId().equals(pointId))
/*  49 */         return point; 
/*     */     } 
/*  52 */     return null;
/*     */   }
/*     */   
/*     */   ParameterValueParser(PluginRegistry registry, ExtensionPoint.ParameterDefinition definition, String rawValue) {
/*     */     DateFormat fmt;
/*     */     String extId;
/*     */     StringTokenizer st;
/*  61 */     if (definition == null) {
/*  62 */       this.parsingMessage = "parameter definition is NULL";
/*  63 */       this.isParsingSucceeds = false;
/*     */       return;
/*     */     } 
/*  66 */     if (rawValue == null) {
/*  67 */       this.isParsingSucceeds = true;
/*     */       return;
/*     */     } 
/*  70 */     if (ParameterType.ANY == definition.getType() || ParameterType.NULL == definition.getType()) {
/*  72 */       this.isParsingSucceeds = true;
/*     */       return;
/*     */     } 
/*  74 */     if (ParameterType.STRING == definition.getType()) {
/*  75 */       this.value = rawValue;
/*  76 */       this.isParsingSucceeds = true;
/*     */       return;
/*     */     } 
/*  79 */     String val = rawValue.trim();
/*  80 */     if (val.length() == 0) {
/*  81 */       this.isParsingSucceeds = true;
/*     */       return;
/*     */     } 
/*  84 */     switch (definition.getType()) {
/*     */       case BOOLEAN:
/*  86 */         if ("true".equals(val)) {
/*  87 */           this.value = Boolean.TRUE;
/*     */           break;
/*     */         } 
/*  88 */         if ("false".equals(val)) {
/*  89 */           this.value = Boolean.FALSE;
/*     */           break;
/*     */         } 
/*  91 */         this.isParsingSucceeds = false;
/*     */         return;
/*     */       case NUMBER:
/*     */         try {
/*  97 */           this.value = NumberFormat.getInstance(Locale.ENGLISH).parse(val);
/*  99 */         } catch (ParseException nfe) {
/* 100 */           this.isParsingSucceeds = false;
/*     */           return;
/*     */         } 
/*     */         break;
/*     */       case DATE:
/* 105 */         fmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
/*     */         try {
/* 108 */           this.value = fmt.parse(val);
/* 109 */         } catch (ParseException pe) {
/* 110 */           this.isParsingSucceeds = false;
/*     */           return;
/*     */         } 
/*     */         break;
/*     */       case TIME:
/* 116 */         fmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
/*     */         try {
/* 119 */           this.value = fmt.parse(val);
/* 120 */         } catch (ParseException pe) {
/* 121 */           this.isParsingSucceeds = false;
/*     */           return;
/*     */         } 
/*     */         break;
/*     */       case DATE_TIME:
/* 127 */         fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
/*     */         try {
/* 130 */           this.value = fmt.parse(val);
/* 131 */         } catch (ParseException pe) {
/* 132 */           this.isParsingSucceeds = false;
/*     */           return;
/*     */         } 
/*     */         break;
/*     */       case PLUGIN_ID:
/*     */         try {
/* 139 */           this.value = registry.getPluginDescriptor(val);
/* 140 */         } catch (IllegalArgumentException iae) {
/* 141 */           this.parsingMessage = "unknown plug-in ID " + val;
/* 142 */           this.isParsingSucceeds = false;
/*     */           return;
/*     */         } 
/*     */         break;
/*     */       case EXTENSION_POINT_ID:
/* 147 */         this.value = getExtensionPoint(registry, val);
/* 148 */         if (this.value == null) {
/* 149 */           this.parsingMessage = "unknown extension point UID " + val;
/* 150 */           this.isParsingSucceeds = false;
/*     */           return;
/*     */         } 
/* 153 */         if (definition.getCustomData() != null) {
/* 154 */           ExtensionPoint customExtPoint = getExtensionPoint(registry, definition.getCustomData());
/* 156 */           if (customExtPoint == null) {
/* 157 */             this.parsingMessage = "unknown extension point UID " + definition.getCustomData() + " provided as custom data";
/* 160 */             this.isParsingSucceeds = false;
/*     */             return;
/*     */           } 
/* 163 */           if (!((ExtensionPoint)this.value).isSuccessorOf(customExtPoint)) {
/* 165 */             this.parsingMessage = "extension point with UID " + val + " doesn't \"inherit\" point that is defined" + " according to custom data in parameter" + " definition - " + definition.getCustomData();
/* 170 */             this.isParsingSucceeds = false;
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case EXTENSION_ID:
/* 176 */         extId = registry.extractId(val);
/* 177 */         for (Extension ext : registry.getPluginDescriptor(registry.extractPluginId(val)).getExtensions()) {
/* 179 */           if (ext.getId().equals(extId)) {
/* 180 */             this.value = ext;
/*     */             break;
/*     */           } 
/*     */         } 
/* 184 */         if (this.value == null) {
/* 185 */           this.parsingMessage = "unknown extension UID " + val;
/* 186 */           this.isParsingSucceeds = false;
/*     */           return;
/*     */         } 
/* 189 */         if (definition.getCustomData() != null) {
/* 190 */           ExtensionPoint customExtPoint = getExtensionPoint(registry, definition.getCustomData());
/* 192 */           if (customExtPoint == null) {
/* 193 */             this.parsingMessage = "unknown extension point UID " + definition.getCustomData() + " provided as custom data in parameter definition " + definition;
/* 197 */             this.isParsingSucceeds = false;
/*     */             return;
/*     */           } 
/* 200 */           String extPointUid = registry.makeUniqueId(((Extension)this.value).getExtendedPluginId(), ((Extension)this.value).getExtendedPointId());
/* 203 */           ExtensionPoint extPoint = getExtensionPoint(registry, extPointUid);
/* 205 */           if (extPoint == null) {
/* 206 */             this.parsingMessage = "extension point " + extPointUid + " is unknown for extension " + ((Extension)this.value).getUniqueId();
/* 209 */             this.isParsingSucceeds = false;
/*     */             return;
/*     */           } 
/* 212 */           if (!extPoint.equals(customExtPoint) && !extPoint.isSuccessorOf(customExtPoint)) {
/* 214 */             this.parsingMessage = "extension with UID " + val + " extends point that not allowed according" + " to custom data defined in parameter" + " definition - " + definition.getCustomData();
/* 219 */             this.isParsingSucceeds = false;
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case FIXED:
/* 225 */         st = new StringTokenizer(definition.getCustomData(), "|", false);
/* 227 */         while (st.hasMoreTokens()) {
/* 228 */           if (val.equals(st.nextToken().trim())) {
/* 229 */             this.value = val;
/* 230 */             this.isParsingSucceeds = true;
/*     */             return;
/*     */           } 
/*     */         } 
/* 234 */         this.parsingMessage = "not allowed value " + val;
/* 235 */         this.isParsingSucceeds = false;
/*     */         return;
/*     */       case RESOURCE:
/*     */         try {
/* 239 */           this.value = new URL(val);
/* 240 */         } catch (MalformedURLException mue) {
/* 241 */           this.parsingMessage = "can't parse value " + val + " as an absolute URL, will treat it as relative URL";
/* 244 */           this.value = null;
/*     */         } 
/* 246 */         this.isParsingSucceeds = true;
/*     */         return;
/*     */     } 
/* 258 */     this.isParsingSucceeds = true;
/*     */   }
/*     */   
/*     */   Object getValue() {
/* 262 */     return this.value;
/*     */   }
/*     */   
/*     */   String getParsingMessage() {
/* 266 */     return this.parsingMessage;
/*     */   }
/*     */   
/*     */   boolean isParsingSucceeds() {
/* 270 */     return this.isParsingSucceeds;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ParameterValueParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */