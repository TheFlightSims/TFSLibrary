/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ final class SimpleConfigOrigin implements ConfigOrigin {
/*     */   private final String description;
/*     */   
/*     */   private final int lineNumber;
/*     */   
/*     */   private final int endLineNumber;
/*     */   
/*     */   private final OriginType originType;
/*     */   
/*     */   private final String urlOrNull;
/*     */   
/*     */   private final List<String> commentsOrNull;
/*     */   
/*     */   static final String MERGE_OF_PREFIX = "merge of ";
/*     */   
/*     */   protected SimpleConfigOrigin(String description, int lineNumber, int endLineNumber, OriginType originType, String urlOrNull, List<String> commentsOrNull) {
/*  35 */     if (description == null)
/*  36 */       throw new ConfigException.BugOrBroken("description may not be null"); 
/*  37 */     this.description = description;
/*  38 */     this.lineNumber = lineNumber;
/*  39 */     this.endLineNumber = endLineNumber;
/*  40 */     this.originType = originType;
/*  41 */     this.urlOrNull = urlOrNull;
/*  42 */     this.commentsOrNull = commentsOrNull;
/*     */   }
/*     */   
/*     */   static SimpleConfigOrigin newSimple(String description) {
/*  46 */     return new SimpleConfigOrigin(description, -1, -1, OriginType.GENERIC, null, null);
/*     */   }
/*     */   
/*     */   static SimpleConfigOrigin newFile(String filename) {
/*     */     String str;
/*     */     try {
/*  52 */       str = (new File(filename)).toURI().toURL().toExternalForm();
/*  53 */     } catch (MalformedURLException e) {
/*  54 */       str = null;
/*     */     } 
/*  56 */     return new SimpleConfigOrigin(filename, -1, -1, OriginType.FILE, str, null);
/*     */   }
/*     */   
/*     */   static SimpleConfigOrigin newURL(URL url) {
/*  60 */     String u = url.toExternalForm();
/*  61 */     return new SimpleConfigOrigin(u, -1, -1, OriginType.URL, u, null);
/*     */   }
/*     */   
/*     */   static SimpleConfigOrigin newResource(String resource, URL url) {
/*  65 */     return new SimpleConfigOrigin(resource, -1, -1, OriginType.RESOURCE, (url != null) ? url.toExternalForm() : null, null);
/*     */   }
/*     */   
/*     */   static SimpleConfigOrigin newResource(String resource) {
/*  70 */     return newResource(resource, null);
/*     */   }
/*     */   
/*     */   SimpleConfigOrigin setLineNumber(int lineNumber) {
/*  74 */     if (lineNumber == this.lineNumber && lineNumber == this.endLineNumber)
/*  75 */       return this; 
/*  77 */     return new SimpleConfigOrigin(this.description, lineNumber, lineNumber, this.originType, this.urlOrNull, this.commentsOrNull);
/*     */   }
/*     */   
/*     */   SimpleConfigOrigin addURL(URL url) {
/*  83 */     return new SimpleConfigOrigin(this.description, this.lineNumber, this.endLineNumber, this.originType, (url != null) ? url.toExternalForm() : null, this.commentsOrNull);
/*     */   }
/*     */   
/*     */   SimpleConfigOrigin setComments(List<String> comments) {
/*  88 */     if (ConfigImplUtil.equalsHandlingNull(comments, this.commentsOrNull))
/*  89 */       return this; 
/*  91 */     return new SimpleConfigOrigin(this.description, this.lineNumber, this.endLineNumber, this.originType, this.urlOrNull, comments);
/*     */   }
/*     */   
/*     */   SimpleConfigOrigin prependComments(List<String> comments) {
/*  97 */     if (ConfigImplUtil.equalsHandlingNull(comments, this.commentsOrNull) || comments == null)
/*  98 */       return this; 
/*  99 */     if (this.commentsOrNull == null)
/* 100 */       return setComments(comments); 
/* 102 */     List<String> merged = new ArrayList<String>(comments.size() + this.commentsOrNull.size());
/* 104 */     merged.addAll(comments);
/* 105 */     merged.addAll(this.commentsOrNull);
/* 106 */     return setComments(merged);
/*     */   }
/*     */   
/*     */   SimpleConfigOrigin appendComments(List<String> comments) {
/* 111 */     if (ConfigImplUtil.equalsHandlingNull(comments, this.commentsOrNull) || comments == null)
/* 112 */       return this; 
/* 113 */     if (this.commentsOrNull == null)
/* 114 */       return setComments(comments); 
/* 116 */     List<String> merged = new ArrayList<String>(comments.size() + this.commentsOrNull.size());
/* 118 */     merged.addAll(this.commentsOrNull);
/* 119 */     merged.addAll(comments);
/* 120 */     return setComments(merged);
/*     */   }
/*     */   
/*     */   public String description() {
/* 128 */     if (this.lineNumber < 0)
/* 129 */       return this.description; 
/* 130 */     if (this.endLineNumber == this.lineNumber)
/* 131 */       return this.description + ": " + this.lineNumber; 
/* 133 */     return this.description + ": " + this.lineNumber + "-" + this.endLineNumber;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 139 */     if (other instanceof SimpleConfigOrigin) {
/* 140 */       SimpleConfigOrigin otherOrigin = (SimpleConfigOrigin)other;
/* 142 */       return (this.description.equals(otherOrigin.description) && this.lineNumber == otherOrigin.lineNumber && this.endLineNumber == otherOrigin.endLineNumber && this.originType == otherOrigin.originType && ConfigImplUtil.equalsHandlingNull(this.urlOrNull, otherOrigin.urlOrNull));
/*     */     } 
/* 148 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 154 */     int h = 41 * (41 + this.description.hashCode());
/* 155 */     h = 41 * (h + this.lineNumber);
/* 156 */     h = 41 * (h + this.endLineNumber);
/* 157 */     h = 41 * (h + this.originType.hashCode());
/* 158 */     if (this.urlOrNull != null)
/* 159 */       h = 41 * (h + this.urlOrNull.hashCode()); 
/* 160 */     return h;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 166 */     if (this.originType == OriginType.RESOURCE && this.urlOrNull != null)
/* 167 */       return "ConfigOrigin(" + this.description + "," + this.urlOrNull + ")"; 
/* 169 */     return "ConfigOrigin(" + this.description + ")";
/*     */   }
/*     */   
/*     */   public String filename() {
/* 175 */     if (this.originType == OriginType.FILE)
/* 176 */       return this.description; 
/* 177 */     if (this.urlOrNull != null) {
/*     */       URL url;
/*     */       try {
/* 180 */         url = new URL(this.urlOrNull);
/* 181 */       } catch (MalformedURLException e) {
/* 182 */         return null;
/*     */       } 
/* 184 */       if (url.getProtocol().equals("file"))
/* 185 */         return url.getFile(); 
/* 187 */       return null;
/*     */     } 
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   public URL url() {
/* 196 */     if (this.urlOrNull == null)
/* 197 */       return null; 
/*     */     try {
/* 200 */       return new URL(this.urlOrNull);
/* 201 */     } catch (MalformedURLException e) {
/* 202 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String resource() {
/* 209 */     if (this.originType == OriginType.RESOURCE)
/* 210 */       return this.description; 
/* 212 */     return null;
/*     */   }
/*     */   
/*     */   public int lineNumber() {
/* 218 */     return this.lineNumber;
/*     */   }
/*     */   
/*     */   public List<String> comments() {
/* 223 */     if (this.commentsOrNull != null)
/* 224 */       return Collections.unmodifiableList(this.commentsOrNull); 
/* 226 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   private static SimpleConfigOrigin mergeTwo(SimpleConfigOrigin a, SimpleConfigOrigin b) {
/*     */     String mergedDesc;
/*     */     int mergedStartLine, mergedEndLine;
/*     */     List<String> mergedComments;
/*     */     OriginType mergedType;
/*     */     String mergedURL;
/* 239 */     if (a.originType == b.originType) {
/* 240 */       mergedType = a.originType;
/*     */     } else {
/* 242 */       mergedType = OriginType.GENERIC;
/*     */     } 
/* 247 */     String aDesc = a.description;
/* 248 */     String bDesc = b.description;
/* 249 */     if (aDesc.startsWith("merge of "))
/* 250 */       aDesc = aDesc.substring("merge of ".length()); 
/* 251 */     if (bDesc.startsWith("merge of "))
/* 252 */       bDesc = bDesc.substring("merge of ".length()); 
/* 254 */     if (aDesc.equals(bDesc)) {
/* 255 */       mergedDesc = aDesc;
/* 257 */       if (a.lineNumber < 0) {
/* 258 */         mergedStartLine = b.lineNumber;
/* 259 */       } else if (b.lineNumber < 0) {
/* 260 */         mergedStartLine = a.lineNumber;
/*     */       } else {
/* 262 */         mergedStartLine = Math.min(a.lineNumber, b.lineNumber);
/*     */       } 
/* 264 */       mergedEndLine = Math.max(a.endLineNumber, b.endLineNumber);
/*     */     } else {
/* 272 */       String aFull = a.description();
/* 273 */       String bFull = b.description();
/* 274 */       if (aFull.startsWith("merge of "))
/* 275 */         aFull = aFull.substring("merge of ".length()); 
/* 276 */       if (bFull.startsWith("merge of "))
/* 277 */         bFull = bFull.substring("merge of ".length()); 
/* 279 */       mergedDesc = "merge of " + aFull + "," + bFull;
/* 281 */       mergedStartLine = -1;
/* 282 */       mergedEndLine = -1;
/*     */     } 
/* 286 */     if (ConfigImplUtil.equalsHandlingNull(a.urlOrNull, b.urlOrNull)) {
/* 287 */       mergedURL = a.urlOrNull;
/*     */     } else {
/* 289 */       mergedURL = null;
/*     */     } 
/* 292 */     if (ConfigImplUtil.equalsHandlingNull(a.commentsOrNull, b.commentsOrNull)) {
/* 293 */       mergedComments = a.commentsOrNull;
/*     */     } else {
/* 295 */       mergedComments = new ArrayList<String>();
/* 296 */       if (a.commentsOrNull != null)
/* 297 */         mergedComments.addAll(a.commentsOrNull); 
/* 298 */       if (b.commentsOrNull != null)
/* 299 */         mergedComments.addAll(b.commentsOrNull); 
/*     */     } 
/* 302 */     return new SimpleConfigOrigin(mergedDesc, mergedStartLine, mergedEndLine, mergedType, mergedURL, mergedComments);
/*     */   }
/*     */   
/*     */   private static int similarity(SimpleConfigOrigin a, SimpleConfigOrigin b) {
/* 307 */     int count = 0;
/* 309 */     if (a.originType == b.originType)
/* 310 */       count++; 
/* 312 */     if (a.description.equals(b.description)) {
/* 313 */       count++;
/* 317 */       if (a.lineNumber == b.lineNumber)
/* 318 */         count++; 
/* 319 */       if (a.endLineNumber == b.endLineNumber)
/* 320 */         count++; 
/* 321 */       if (ConfigImplUtil.equalsHandlingNull(a.urlOrNull, b.urlOrNull))
/* 322 */         count++; 
/*     */     } 
/* 325 */     return count;
/*     */   }
/*     */   
/*     */   private static SimpleConfigOrigin mergeThree(SimpleConfigOrigin a, SimpleConfigOrigin b, SimpleConfigOrigin c) {
/* 334 */     if (similarity(a, b) >= similarity(b, c))
/* 335 */       return mergeTwo(mergeTwo(a, b), c); 
/* 337 */     return mergeTwo(a, mergeTwo(b, c));
/*     */   }
/*     */   
/*     */   static ConfigOrigin mergeOrigins(ConfigOrigin a, ConfigOrigin b) {
/* 342 */     return mergeTwo((SimpleConfigOrigin)a, (SimpleConfigOrigin)b);
/*     */   }
/*     */   
/*     */   static ConfigOrigin mergeOrigins(List<? extends AbstractConfigValue> stack) {
/* 346 */     List<ConfigOrigin> origins = new ArrayList<ConfigOrigin>(stack.size());
/* 347 */     for (AbstractConfigValue v : stack)
/* 348 */       origins.add(v.origin()); 
/* 350 */     return mergeOrigins(origins);
/*     */   }
/*     */   
/*     */   static ConfigOrigin mergeOrigins(Collection<? extends ConfigOrigin> stack) {
/* 354 */     if (stack.isEmpty())
/* 355 */       throw new ConfigException.BugOrBroken("can't merge empty list of origins"); 
/* 356 */     if (stack.size() == 1)
/* 357 */       return stack.iterator().next(); 
/* 358 */     if (stack.size() == 2) {
/* 359 */       Iterator<? extends ConfigOrigin> i = stack.iterator();
/* 360 */       return mergeTwo((SimpleConfigOrigin)i.next(), (SimpleConfigOrigin)i.next());
/*     */     } 
/* 362 */     List<SimpleConfigOrigin> remaining = new ArrayList<SimpleConfigOrigin>();
/* 363 */     for (ConfigOrigin o : stack)
/* 364 */       remaining.add((SimpleConfigOrigin)o); 
/* 366 */     while (remaining.size() > 2) {
/* 367 */       SimpleConfigOrigin c = remaining.get(remaining.size() - 1);
/* 368 */       remaining.remove(remaining.size() - 1);
/* 369 */       SimpleConfigOrigin b = remaining.get(remaining.size() - 1);
/* 370 */       remaining.remove(remaining.size() - 1);
/* 371 */       SimpleConfigOrigin a = remaining.get(remaining.size() - 1);
/* 372 */       remaining.remove(remaining.size() - 1);
/* 374 */       SimpleConfigOrigin merged = mergeThree(a, b, c);
/* 376 */       remaining.add(merged);
/*     */     } 
/* 380 */     return mergeOrigins((Collection)remaining);
/*     */   }
/*     */   
/*     */   Map<SerializedConfigValue.SerializedField, Object> toFields() {
/* 385 */     Map<SerializedConfigValue.SerializedField, Object> m = new EnumMap<SerializedConfigValue.SerializedField, Object>(SerializedConfigValue.SerializedField.class);
/* 387 */     m.put(SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION, this.description);
/* 389 */     if (this.lineNumber >= 0)
/* 390 */       m.put(SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER, Integer.valueOf(this.lineNumber)); 
/* 391 */     if (this.endLineNumber >= 0)
/* 392 */       m.put(SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER, Integer.valueOf(this.endLineNumber)); 
/* 394 */     m.put(SerializedConfigValue.SerializedField.ORIGIN_TYPE, Integer.valueOf(this.originType.ordinal()));
/* 396 */     if (this.urlOrNull != null)
/* 397 */       m.put(SerializedConfigValue.SerializedField.ORIGIN_URL, this.urlOrNull); 
/* 398 */     if (this.commentsOrNull != null)
/* 399 */       m.put(SerializedConfigValue.SerializedField.ORIGIN_COMMENTS, this.commentsOrNull); 
/* 401 */     return m;
/*     */   }
/*     */   
/*     */   Map<SerializedConfigValue.SerializedField, Object> toFieldsDelta(SimpleConfigOrigin baseOrigin) {
/*     */     Map<SerializedConfigValue.SerializedField, Object> baseFields;
/* 406 */     if (baseOrigin != null) {
/* 407 */       baseFields = baseOrigin.toFields();
/*     */     } else {
/* 409 */       baseFields = Collections.emptyMap();
/*     */     } 
/* 410 */     return fieldsDelta(baseFields, toFields());
/*     */   }
/*     */   
/*     */   static Map<SerializedConfigValue.SerializedField, Object> fieldsDelta(Map<SerializedConfigValue.SerializedField, Object> base, Map<SerializedConfigValue.SerializedField, Object> child) {
/* 419 */     Map<SerializedConfigValue.SerializedField, Object> m = new EnumMap<SerializedConfigValue.SerializedField, Object>(child);
/* 421 */     for (Map.Entry<SerializedConfigValue.SerializedField, Object> baseEntry : base.entrySet()) {
/* 422 */       SerializedConfigValue.SerializedField f = baseEntry.getKey();
/* 423 */       if (m.containsKey(f) && ConfigImplUtil.equalsHandlingNull(baseEntry.getValue(), m.get(f))) {
/* 426 */         m.remove(f);
/*     */         continue;
/*     */       } 
/* 427 */       if (!m.containsKey(f))
/* 429 */         switch (f) {
/*     */           case ORIGIN_DESCRIPTION:
/* 431 */             throw new ConfigException.BugOrBroken("origin missing description field? " + child);
/*     */           case ORIGIN_LINE_NUMBER:
/* 434 */             m.put(SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER, Integer.valueOf(-1));
/*     */           case ORIGIN_END_LINE_NUMBER:
/* 437 */             m.put(SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER, Integer.valueOf(-1));
/*     */           case ORIGIN_TYPE:
/* 440 */             throw new ConfigException.BugOrBroken("should always be an ORIGIN_TYPE field");
/*     */           case ORIGIN_URL:
/* 442 */             m.put(SerializedConfigValue.SerializedField.ORIGIN_NULL_URL, "");
/*     */           case ORIGIN_COMMENTS:
/* 445 */             m.put(SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS, "");
/*     */           case ORIGIN_NULL_URL:
/*     */           case ORIGIN_NULL_COMMENTS:
/* 449 */             throw new ConfigException.BugOrBroken("computing delta, base object should not contain " + f + " " + base);
/*     */           case END_MARKER:
/*     */           case ROOT_VALUE:
/*     */           case ROOT_WAS_CONFIG:
/*     */           case UNKNOWN:
/*     */           case VALUE_DATA:
/*     */           case VALUE_ORIGIN:
/* 458 */             throw new ConfigException.BugOrBroken("should not appear here: " + f);
/*     */         }  
/*     */     } 
/* 465 */     return m;
/*     */   }
/*     */   
/*     */   static SimpleConfigOrigin fromFields(Map<SerializedConfigValue.SerializedField, Object> m) throws IOException {
/* 470 */     if (m.isEmpty())
/* 471 */       return null; 
/* 473 */     String description = (String)m.get(SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION);
/* 474 */     Integer lineNumber = (Integer)m.get(SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER);
/* 475 */     Integer endLineNumber = (Integer)m.get(SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER);
/* 476 */     Number originTypeOrdinal = (Number)m.get(SerializedConfigValue.SerializedField.ORIGIN_TYPE);
/* 477 */     if (originTypeOrdinal == null)
/* 478 */       throw new IOException("Missing ORIGIN_TYPE field"); 
/* 479 */     OriginType originType = OriginType.values()[originTypeOrdinal.byteValue()];
/* 480 */     String urlOrNull = (String)m.get(SerializedConfigValue.SerializedField.ORIGIN_URL);
/* 482 */     List<String> commentsOrNull = (List<String>)m.get(SerializedConfigValue.SerializedField.ORIGIN_COMMENTS);
/* 483 */     return new SimpleConfigOrigin(description, (lineNumber != null) ? lineNumber.intValue() : -1, (endLineNumber != null) ? endLineNumber.intValue() : -1, originType, urlOrNull, commentsOrNull);
/*     */   }
/*     */   
/*     */   static Map<SerializedConfigValue.SerializedField, Object> applyFieldsDelta(Map<SerializedConfigValue.SerializedField, Object> base, Map<SerializedConfigValue.SerializedField, Object> delta) throws IOException {
/* 490 */     Map<SerializedConfigValue.SerializedField, Object> m = new EnumMap<SerializedConfigValue.SerializedField, Object>(delta);
/* 492 */     for (Map.Entry<SerializedConfigValue.SerializedField, Object> baseEntry : base.entrySet()) {
/* 493 */       SerializedConfigValue.SerializedField f = baseEntry.getKey();
/* 494 */       if (delta.containsKey(f))
/*     */         continue; 
/* 500 */       switch (f) {
/*     */         case ORIGIN_DESCRIPTION:
/* 502 */           m.put(f, base.get(f));
/*     */         case ORIGIN_URL:
/* 505 */           if (delta.containsKey(SerializedConfigValue.SerializedField.ORIGIN_NULL_URL)) {
/* 506 */             m.remove(SerializedConfigValue.SerializedField.ORIGIN_NULL_URL);
/*     */             continue;
/*     */           } 
/* 508 */           m.put(f, base.get(f));
/*     */         case ORIGIN_COMMENTS:
/* 512 */           if (delta.containsKey(SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS)) {
/* 513 */             m.remove(SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS);
/*     */             continue;
/*     */           } 
/* 515 */           m.put(f, base.get(f));
/*     */         case ORIGIN_NULL_URL:
/*     */         case ORIGIN_NULL_COMMENTS:
/* 522 */           throw new ConfigException.BugOrBroken("applying fields, base object should not contain " + f + " " + base);
/*     */         case ORIGIN_LINE_NUMBER:
/*     */         case ORIGIN_END_LINE_NUMBER:
/*     */         case ORIGIN_TYPE:
/* 527 */           m.put(f, base.get(f));
/*     */         case END_MARKER:
/*     */         case ROOT_VALUE:
/*     */         case ROOT_WAS_CONFIG:
/*     */         case UNKNOWN:
/*     */         case VALUE_DATA:
/*     */         case VALUE_ORIGIN:
/* 536 */           throw new ConfigException.BugOrBroken("should not appear here: " + f);
/*     */       } 
/*     */     } 
/* 540 */     return m;
/*     */   }
/*     */   
/*     */   static SimpleConfigOrigin fromBase(SimpleConfigOrigin baseOrigin, Map<SerializedConfigValue.SerializedField, Object> delta) throws IOException {
/*     */     Map<SerializedConfigValue.SerializedField, Object> baseFields;
/* 546 */     if (baseOrigin != null) {
/* 547 */       baseFields = baseOrigin.toFields();
/*     */     } else {
/* 549 */       baseFields = Collections.emptyMap();
/*     */     } 
/* 550 */     Map<SerializedConfigValue.SerializedField, Object> fields = applyFieldsDelta(baseFields, delta);
/* 551 */     return fromFields(fields);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\SimpleConfigOrigin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */