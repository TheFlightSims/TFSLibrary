/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.parameter.Parameter;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class Parameter<T> implements Parameter<T> {
/*     */   public final String key;
/*     */   
/*     */   public final InternationalString title;
/*     */   
/*     */   public final InternationalString description;
/*     */   
/*     */   public final Class<T> type;
/*     */   
/*     */   public final boolean required;
/*     */   
/*     */   public final int minOccurs;
/*     */   
/*     */   public final int maxOccurs;
/*     */   
/*     */   public final Object sample;
/*     */   
/*     */   public static final String FEATURE_TYPE = "featureType";
/*     */   
/*     */   public static final String IS_PASSWORD = "isPassword";
/*     */   
/*     */   public static final String IS_LARGE_TEXT = "isLargeText";
/*     */   
/*     */   public static final String LENGTH = "length";
/*     */   
/*     */   public static final String CRS = "crs";
/*     */   
/*     */   public static final String ELEMENT = "element";
/*     */   
/*     */   public static final String MIN = "min";
/*     */   
/*     */   public static final String MAX = "max";
/*     */   
/*     */   public static final String OPTIONS = "options";
/*     */   
/*     */   public static final String EXT = "ext";
/*     */   
/*     */   public static final String LEVEL = "level";
/*     */   
/*     */   public final Map<String, Object> metadata;
/*     */   
/*     */   public Parameter(String key, Class<T> type, String title, String description) {
/* 190 */     this(key, type, (InternationalString)new SimpleInternationalString(title), (InternationalString)new SimpleInternationalString(description));
/*     */   }
/*     */   
/*     */   public Parameter(String key, Class<T> type, String title, String description, Map<String, Object> metadata) {
/* 204 */     this(key, type, (InternationalString)new SimpleInternationalString(title), (InternationalString)new SimpleInternationalString(description), metadata);
/*     */   }
/*     */   
/*     */   public Parameter(String key, Class<T> type, InternationalString title, InternationalString description) {
/* 215 */     this(key, type, title, description, true, 1, 1, null, null);
/*     */   }
/*     */   
/*     */   public Parameter(String key, Class<T> type, InternationalString title, InternationalString description, Map<String, Object> metadata) {
/* 236 */     this(key, type, title, description, true, 1, 1, null, metadata);
/*     */   }
/*     */   
/*     */   public Parameter(String key, Class<T> type, InternationalString title, InternationalString description, boolean required, int min, int max, Object sample, Map<String, Object> metadata) {
/* 263 */     this.key = key;
/* 264 */     this.title = title;
/* 265 */     this.type = type;
/* 266 */     this.description = description;
/* 267 */     this.required = required;
/* 268 */     this.minOccurs = min;
/* 269 */     this.maxOccurs = max;
/* 270 */     this.sample = sample;
/* 271 */     this.metadata = (metadata == null) ? Collections.EMPTY_MAP : Collections.<String, Object>unmodifiableMap(metadata);
/*     */   }
/*     */   
/*     */   public Parameter(String key, Class<T> type) {
/* 281 */     this(key, type, null, null, true, 1, 1, null, null);
/*     */   }
/*     */   
/*     */   public Parameter(String key, Class<T> type, int min, int max) {
/* 293 */     this(key, type, null, null, (min > 0), min, max, null, null);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 297 */     return this.key;
/*     */   }
/*     */   
/*     */   public InternationalString getTitle() {
/* 301 */     return this.title;
/*     */   }
/*     */   
/*     */   public InternationalString getDescription() {
/* 305 */     return this.description;
/*     */   }
/*     */   
/*     */   public Class<T> getType() {
/* 309 */     return this.type;
/*     */   }
/*     */   
/*     */   public Boolean isRequired() {
/* 313 */     return Boolean.valueOf(this.required);
/*     */   }
/*     */   
/*     */   public int getMinOccurs() {
/* 317 */     return this.minOccurs;
/*     */   }
/*     */   
/*     */   public int getMaxOccurs() {
/* 321 */     return this.maxOccurs;
/*     */   }
/*     */   
/*     */   public T getDefaultValue() {
/* 325 */     return (T)this.sample;
/*     */   }
/*     */   
/*     */   public boolean isPassword() {
/* 333 */     return (this.metadata != null && Boolean.TRUE.equals(this.metadata.get("isPassword")));
/*     */   }
/*     */   
/*     */   public String getLevel() {
/* 341 */     if (this.metadata == null)
/* 342 */       return "user"; 
/* 344 */     String level = (String)this.metadata.get("level");
/* 345 */     if (level == null)
/* 346 */       return "user"; 
/* 348 */     return level;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 352 */     StringBuilder build = new StringBuilder();
/* 353 */     build.append(this.key);
/* 354 */     if (this.type != Object.class) {
/* 355 */       build.append(":");
/* 356 */       build.append(this.type.getSimpleName());
/*     */     } 
/* 358 */     if (!this.required)
/* 359 */       build.append("{optional}"); 
/* 361 */     if (this.minOccurs != 1 || this.maxOccurs != 1) {
/* 362 */       build.append("{");
/* 363 */       build.append(this.minOccurs);
/* 364 */       build.append(":");
/* 365 */       build.append(this.maxOccurs);
/* 366 */       build.append("}");
/*     */     } 
/* 368 */     return build.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\Parameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */