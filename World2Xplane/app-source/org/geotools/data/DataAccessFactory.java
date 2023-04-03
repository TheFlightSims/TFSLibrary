/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.geotools.factory.Factory;
/*     */ import org.geotools.util.KVP;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public interface DataAccessFactory extends Factory {
/*     */   DataAccess<? extends FeatureType, ? extends Feature> createDataStore(Map<String, Serializable> paramMap) throws IOException;
/*     */   
/*     */   String getDisplayName();
/*     */   
/*     */   String getDescription();
/*     */   
/*     */   Param[] getParametersInfo();
/*     */   
/*     */   boolean canProcess(Map<String, Serializable> paramMap);
/*     */   
/*     */   boolean isAvailable();
/*     */   
/*     */   public static class Param extends Parameter {
/*     */     public Param(String key) {
/* 262 */       this(key, String.class, (String)null);
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type) {
/* 277 */       this(key, type, (String)null);
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type, String description) {
/* 289 */       this(key, type, description, true);
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type, String description, boolean required) {
/* 302 */       this(key, type, description, required, (Object)null);
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type, String description, boolean required, Object sample) {
/* 316 */       this(key, type, (description == null) ? null : (InternationalString)new SimpleInternationalString(description), required, sample, (Map<String, ?>)null);
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type, InternationalString description, boolean required, Object sample) {
/* 335 */       super(key, (Class)type, (InternationalString)new SimpleInternationalString(key), description, required, 1, 1, sample, null);
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type, String description, boolean required, Object sample, Map<String, ?> metadata) {
/* 356 */       this(key, type, (InternationalString)new SimpleInternationalString(description), required, sample, metadata);
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type, String description, boolean required, Object sample, Object... metadata) {
/* 364 */       this(key, type, description, required, sample, (Map<String, ?>)new KVP(metadata));
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type, InternationalString description, boolean required, Object sample, Map<String, ?> metadata) {
/* 385 */       super(key, (Class)type, (InternationalString)new SimpleInternationalString(key), description, required, 1, 1, sample, (Map)metadata);
/*     */     }
/*     */     
/*     */     public Param(String key, Class<?> type, InternationalString title, InternationalString description, boolean required, int min, int max, Object sample, Map<String, ?> metadata) {
/* 410 */       super(key, (Class)type, title, description, required, min, max, sample, (Map)metadata);
/*     */     }
/*     */     
/*     */     public Object lookUp(Map<String, ?> map) throws IOException {
/* 433 */       if (!map.containsKey(this.key)) {
/* 434 */         if (this.required)
/* 435 */           throw new IOException("Parameter " + this.key + " is required:" + this.description); 
/* 437 */         return null;
/*     */       } 
/* 441 */       Object value = map.get(this.key);
/* 443 */       if (value == null)
/* 444 */         return null; 
/* 447 */       if (value instanceof String && this.type != String.class)
/* 448 */         value = handle((String)value); 
/* 451 */       if (value == null)
/* 452 */         return null; 
/* 455 */       if (!this.type.isInstance(value))
/* 456 */         throw new IOException(this.type.getName() + " required for parameter " + this.key + ": not " + value.getClass().getName()); 
/* 460 */       return value;
/*     */     }
/*     */     
/*     */     public String text(Object value) {
/* 471 */       return value.toString();
/*     */     }
/*     */     
/*     */     public Object handle(String text) throws IOException {
/* 504 */       if (text == null)
/* 505 */         return null; 
/* 508 */       if (this.type == String.class)
/* 509 */         return text; 
/* 512 */       if (text.length() == 0)
/* 513 */         return null; 
/* 518 */       if (this.type.isArray()) {
/* 519 */         StringTokenizer tokenizer = new StringTokenizer(text, " ");
/* 520 */         List<Object> result = new ArrayList();
/* 522 */         while (tokenizer.hasMoreTokens()) {
/*     */           Object element;
/* 523 */           String token = tokenizer.nextToken();
/*     */           try {
/* 527 */             if (this.type.getComponentType() == String.class) {
/* 528 */               element = token;
/*     */             } else {
/* 530 */               element = parse(token);
/*     */             } 
/* 532 */           } catch (IOException ioException) {
/* 533 */             throw ioException;
/* 534 */           } catch (Throwable throwable) {
/* 535 */             throw new DataSourceException("Problem creating " + this.type.getName() + " from '" + text + "'", throwable);
/*     */           } 
/* 539 */           result.add(element);
/*     */         } 
/* 542 */         Object array = Array.newInstance(this.type.getComponentType(), result.size());
/* 544 */         for (int i = 0; i < result.size(); i++)
/* 545 */           Array.set(array, i, result.get(i)); 
/* 548 */         return array;
/*     */       } 
/*     */       try {
/* 552 */         return parse(text);
/* 553 */       } catch (IOException ioException) {
/* 554 */         throw ioException;
/* 555 */       } catch (Throwable throwable) {
/* 556 */         throw new DataSourceException("Problem creating " + this.type.getName() + " from '" + text + "'", throwable);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object parse(String text) throws Throwable {
/*     */       Constructor<?> constructor;
/*     */       try {
/* 585 */         constructor = this.type.getConstructor(new Class[] { String.class });
/* 586 */       } catch (SecurityException e) {
/* 588 */         throw new IOException("Could not create " + this.type.getName() + " from text");
/* 589 */       } catch (NoSuchMethodException e) {
/* 591 */         throw new IOException("Could not create " + this.type.getName() + " from text");
/*     */       } 
/*     */       try {
/* 595 */         return constructor.newInstance(new Object[] { text });
/* 596 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 597 */         throw new DataSourceException("Could not create " + this.type.getName() + ": from '" + text + "'", illegalArgumentException);
/* 599 */       } catch (InstantiationException instantiaionException) {
/* 600 */         throw new DataSourceException("Could not create " + this.type.getName() + ": from '" + text + "'", instantiaionException);
/* 602 */       } catch (IllegalAccessException illegalAccessException) {
/* 603 */         throw new DataSourceException("Could not create " + this.type.getName() + ": from '" + text + "'", illegalAccessException);
/* 605 */       } catch (InvocationTargetException targetException) {
/* 606 */         throw targetException.getCause();
/*     */       } 
/*     */     }
/*     */     
/*     */     public String toString() {
/* 614 */       StringBuffer buf = new StringBuffer();
/* 615 */       buf.append(this.key);
/* 616 */       buf.append('=');
/* 617 */       buf.append(this.type.getName());
/* 618 */       buf.append(' ');
/* 620 */       if (this.required)
/* 621 */         buf.append("REQUIRED "); 
/* 624 */       buf.append((CharSequence)this.description);
/* 626 */       return buf.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataAccessFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */