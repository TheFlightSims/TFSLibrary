/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.io.StringReader;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ import org.apache.commons.beanutils.Converter;
/*     */ 
/*     */ public class ArrayConverter extends AbstractConverter {
/*     */   private Object defaultTypeInstance;
/*     */   
/*     */   private Converter elementConverter;
/*     */   
/*     */   private int defaultSize;
/*     */   
/* 132 */   private char delimiter = ',';
/*     */   
/* 133 */   private char[] allowedChars = new char[] { '.', '-' };
/*     */   
/*     */   private boolean onlyFirstToString = true;
/*     */   
/*     */   public ArrayConverter(Class defaultType, Converter elementConverter) {
/* 150 */     if (defaultType == null)
/* 151 */       throw new IllegalArgumentException("Default type is missing"); 
/* 153 */     if (!defaultType.isArray())
/* 154 */       throw new IllegalArgumentException("Default type must be an array."); 
/* 156 */     if (elementConverter == null)
/* 157 */       throw new IllegalArgumentException("Component Converter is missing."); 
/* 159 */     this.defaultTypeInstance = Array.newInstance(defaultType.getComponentType(), 0);
/* 160 */     this.elementConverter = elementConverter;
/*     */   }
/*     */   
/*     */   public ArrayConverter(Class defaultType, Converter elementConverter, int defaultSize) {
/* 176 */     this(defaultType, elementConverter);
/* 177 */     this.defaultSize = defaultSize;
/* 178 */     Object defaultValue = null;
/* 179 */     if (defaultSize >= 0)
/* 180 */       defaultValue = Array.newInstance(defaultType.getComponentType(), defaultSize); 
/* 182 */     setDefaultValue(defaultValue);
/*     */   }
/*     */   
/*     */   public void setDelimiter(char delimiter) {
/* 191 */     this.delimiter = delimiter;
/*     */   }
/*     */   
/*     */   public void setAllowedChars(char[] allowedChars) {
/* 201 */     this.allowedChars = allowedChars;
/*     */   }
/*     */   
/*     */   public void setOnlyFirstToString(boolean onlyFirstToString) {
/* 214 */     this.onlyFirstToString = onlyFirstToString;
/*     */   }
/*     */   
/*     */   protected Class getDefaultType() {
/* 223 */     return this.defaultTypeInstance.getClass();
/*     */   }
/*     */   
/*     */   protected String convertToString(Object value) throws Throwable {
/* 235 */     int size = 0;
/* 236 */     Iterator iterator = null;
/* 237 */     Class type = value.getClass();
/* 238 */     if (type.isArray()) {
/* 239 */       size = Array.getLength(value);
/*     */     } else {
/* 241 */       Collection collection = convertToCollection(type, value);
/* 242 */       size = collection.size();
/* 243 */       iterator = collection.iterator();
/*     */     } 
/* 246 */     if (size == 0)
/* 247 */       return (String)getDefault(String.class); 
/* 250 */     if (this.onlyFirstToString)
/* 251 */       size = 1; 
/* 255 */     StringBuffer buffer = new StringBuffer();
/* 256 */     for (int i = 0; i < size; i++) {
/* 257 */       if (i > 0)
/* 258 */         buffer.append(this.delimiter); 
/* 260 */       Object element = (iterator == null) ? Array.get(value, i) : iterator.next();
/* 261 */       element = this.elementConverter.convert(String.class, element);
/* 262 */       if (element != null)
/* 263 */         buffer.append(element); 
/*     */     } 
/* 267 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   protected Object convertToType(Class type, Object value) throws Throwable {
/* 281 */     if (!type.isArray())
/* 282 */       throw new ConversionException(toString(getClass()) + " cannot handle conversion to '" + toString(type) + "' (not an array)."); 
/* 288 */     int size = 0;
/* 289 */     Iterator iterator = null;
/* 290 */     if (value.getClass().isArray()) {
/* 291 */       size = Array.getLength(value);
/*     */     } else {
/* 293 */       Collection collection = convertToCollection(type, value);
/* 294 */       size = collection.size();
/* 295 */       iterator = collection.iterator();
/*     */     } 
/* 299 */     Class componentType = type.getComponentType();
/* 300 */     Object newArray = Array.newInstance(componentType, size);
/* 303 */     for (int i = 0; i < size; i++) {
/* 304 */       Object element = (iterator == null) ? Array.get(value, i) : iterator.next();
/* 307 */       element = this.elementConverter.convert(componentType, element);
/* 308 */       Array.set(newArray, i, element);
/*     */     } 
/* 311 */     return newArray;
/*     */   }
/*     */   
/*     */   protected Object convertArray(Object value) {
/* 321 */     return value;
/*     */   }
/*     */   
/*     */   protected Collection convertToCollection(Class type, Object value) {
/* 346 */     if (value instanceof Collection)
/* 347 */       return (Collection)value; 
/* 349 */     if (value instanceof Number || value instanceof Boolean || value instanceof java.util.Date) {
/* 352 */       List list = new ArrayList(1);
/* 353 */       list.add(value);
/* 354 */       return list;
/*     */     } 
/* 357 */     return parseElements(type, value.toString());
/*     */   }
/*     */   
/*     */   protected Object getDefault(Class type) {
/* 367 */     if (type.equals(String.class))
/* 368 */       return null; 
/* 371 */     Object defaultValue = super.getDefault(type);
/* 372 */     if (defaultValue == null)
/* 373 */       return null; 
/* 376 */     if (defaultValue.getClass().equals(type))
/* 377 */       return defaultValue; 
/* 379 */     return Array.newInstance(type.getComponentType(), this.defaultSize);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 390 */     StringBuffer buffer = new StringBuffer();
/* 391 */     buffer.append(toString(getClass()));
/* 392 */     buffer.append("[UseDefault=");
/* 393 */     buffer.append(isUseDefault());
/* 394 */     buffer.append(", ");
/* 395 */     buffer.append(this.elementConverter.toString());
/* 396 */     buffer.append(']');
/* 397 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private List parseElements(Class type, String value) {
/* 424 */     if (log().isDebugEnabled())
/* 425 */       log().debug("Parsing elements, delimiter=[" + this.delimiter + "], value=[" + value + "]"); 
/* 429 */     value = value.trim();
/* 430 */     if (value.startsWith("{") && value.endsWith("}"))
/* 431 */       value = value.substring(1, value.length() - 1); 
/*     */     try {
/*     */       int ttype;
/* 437 */       StreamTokenizer st = new StreamTokenizer(new StringReader(value));
/* 438 */       st.whitespaceChars(this.delimiter, this.delimiter);
/* 439 */       st.ordinaryChars(48, 57);
/* 440 */       st.wordChars(48, 57);
/* 441 */       for (int i = 0; i < this.allowedChars.length; i++) {
/* 442 */         st.ordinaryChars(this.allowedChars[i], this.allowedChars[i]);
/* 443 */         st.wordChars(this.allowedChars[i], this.allowedChars[i]);
/*     */       } 
/* 447 */       List list = null;
/*     */       while (true) {
/* 449 */         ttype = st.nextToken();
/* 450 */         if (ttype == -3 || ttype > 0) {
/* 451 */           if (st.sval != null) {
/* 452 */             if (list == null)
/* 453 */               list = new ArrayList(); 
/* 455 */             list.add(st.sval);
/*     */           } 
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 457 */       if (ttype == -1) {
/* 465 */         if (list == null)
/* 466 */           list = Collections.EMPTY_LIST; 
/* 468 */         if (log().isDebugEnabled())
/* 469 */           log().debug(list.size() + " elements parsed"); 
/* 473 */         return list;
/*     */       } 
/*     */       throw new ConversionException("Encountered token of type " + ttype + " parsing elements to '" + toString(type) + ".");
/* 475 */     } catch (IOException e) {
/* 477 */       throw new ConversionException("Error converting from String to '" + toString(type) + "': " + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\ArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */