/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.io.Writer;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchIdentifierException;
/*     */ 
/*     */ public class Preprocessor extends Format {
/*     */   protected final Format parser;
/*     */   
/*  75 */   private final Map definitions = new TreeMap<Object, Object>();
/*     */   
/*     */   private transient Set names;
/*     */   
/*     */   private transient Replacement replacements;
/*     */   
/*  96 */   transient int offset = 0;
/*     */   
/*     */   public Preprocessor(Format parser) {
/* 104 */     this.parser = parser;
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object object, StringBuffer toAppendTo, FieldPosition position) {
/* 121 */     return this.parser.format(object, toAppendTo, position);
/*     */   }
/*     */   
/*     */   public Object parseObject(String wkt, ParsePosition position) {
/* 140 */     int start = position.getIndex();
/*     */     try {
/* 142 */       return parseObject(wkt.substring(start));
/* 143 */     } catch (ParseException exception) {
/* 144 */       position.setIndex(start);
/* 145 */       position.setErrorIndex(exception.getErrorOffset() + start);
/* 146 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object parseObject(String wkt) throws ParseException {
/*     */     try {
/* 162 */       return parseObject(wkt, Object.class);
/* 163 */     } catch (FactoryException cause) {
/* 164 */       ParseException e = new ParseException(cause.getLocalizedMessage(), 0);
/* 165 */       e.initCause((Throwable)cause);
/* 166 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object parseObject(String text, Class type) throws ParseException, FactoryException {
/* 196 */     Definition def = (Definition)this.definitions.get(text);
/* 197 */     if (def != null) {
/* 198 */       Object value = def.asObject;
/* 199 */       if (type.isAssignableFrom(value.getClass()))
/* 200 */         return value; 
/* 202 */     } else if (!isIdentifier(text)) {
/* 210 */       text = substitute(text);
/* 211 */       Object value = forwardParse(text);
/* 212 */       Class<?> actualType = value.getClass();
/* 213 */       if (type.isAssignableFrom(actualType))
/* 214 */         return value; 
/* 216 */       throw new FactoryException(Errors.format(61, actualType, type));
/*     */     } 
/* 219 */     throw new NoSuchIdentifierException(Errors.format(137, type, text), text);
/*     */   }
/*     */   
/*     */   private Object forwardParse(String text) throws ParseException {
/*     */     try {
/* 235 */       return this.parser.parseObject(text);
/* 236 */     } catch (ParseException exception) {
/* 237 */       int shift = 0;
/* 238 */       int errorOffset = exception.getErrorOffset();
/* 239 */       for (Replacement r = this.replacements; r != null && 
/* 240 */         errorOffset >= r.lower; r = r.next) {
/* 243 */         if (errorOffset < r.upper) {
/* 244 */           errorOffset = r.lower;
/*     */           break;
/*     */         } 
/* 247 */         shift += r.shift;
/*     */       } 
/* 249 */       ParseException adjusted = new ParseException(exception.getLocalizedMessage(), errorOffset - shift);
/* 251 */       adjusted.setStackTrace(exception.getStackTrace());
/* 252 */       adjusted.initCause(exception.getCause());
/* 253 */       throw adjusted;
/*     */     } 
/*     */   }
/*     */   
/*     */   private String substitute(String text) {
/* 267 */     Replacement last = new Replacement(0, 0, this.offset);
/* 268 */     StringBuilder buffer = null;
/* 269 */     for (Iterator<Map.Entry> it = this.definitions.entrySet().iterator(); it.hasNext(); ) {
/* 270 */       Map.Entry entry = it.next();
/* 271 */       String name = (String)entry.getKey();
/* 272 */       Definition def = (Definition)entry.getValue();
/* 273 */       int index = (buffer != null) ? buffer.indexOf(name) : text.indexOf(name);
/* 274 */       while (index >= 0) {
/* 280 */         int upper = index + name.length();
/* 281 */         CharSequence cs = (buffer != null) ? buffer : text;
/* 282 */         if ((index == 0 || !Character.isJavaIdentifierPart(cs.charAt(index - 1))) && (upper == cs.length() || !Character.isJavaIdentifierPart(cs.charAt(upper)))) {
/* 290 */           int count = 0;
/* 291 */           for (int scan = index; --scan >= 0; ) {
/* 292 */             scan = (buffer != null) ? buffer.lastIndexOf("\"", scan) : text.lastIndexOf('"', scan);
/* 294 */             if (scan < 0)
/*     */               break; 
/* 297 */             count++;
/*     */           } 
/* 299 */           if ((count & 0x1) == 0) {
/* 305 */             if (buffer == null) {
/* 306 */               buffer = new StringBuilder(text);
/* 307 */               assert buffer.indexOf(name, index) == index;
/*     */             } 
/* 309 */             String value = def.asString;
/* 310 */             buffer.replace(index, upper, value);
/* 311 */             int change = value.length() - name.length();
/* 312 */             last = last.next = new Replacement(index, index + value.length(), change);
/* 313 */             index = buffer.indexOf(name, index + change);
/*     */             continue;
/*     */           } 
/*     */         } 
/* 323 */         index += name.length();
/* 324 */         index = (buffer != null) ? buffer.indexOf(name, index) : text.indexOf(name, index);
/*     */       } 
/*     */     } 
/* 328 */     return (buffer != null) ? buffer.toString() : text;
/*     */   }
/*     */   
/*     */   public void addDefinition(String name, String value) throws ParseException {
/* 341 */     if (value == null || value.trim().length() == 0)
/* 342 */       throw new IllegalArgumentException(Errors.format(101)); 
/* 344 */     if (!isIdentifier(name))
/* 345 */       throw new IllegalArgumentException(Errors.format(67, name)); 
/* 347 */     value = substitute(value);
/* 348 */     Definition newDef = new Definition(value, forwardParse(value));
/* 349 */     Definition oldDef = this.definitions.put(name, newDef);
/*     */   }
/*     */   
/*     */   public void removeDefinition(String name) {
/* 359 */     this.definitions.remove(name);
/*     */   }
/*     */   
/*     */   public Set getDefinitionNames() {
/* 368 */     if (this.names == null)
/* 369 */       this.names = Collections.unmodifiableSet(this.definitions.keySet()); 
/* 371 */     return this.names;
/*     */   }
/*     */   
/*     */   public void printDefinitions(Writer out) throws IOException {
/* 383 */     Locale locale = null;
/* 384 */     Vocabulary resources = Vocabulary.getResources(locale);
/* 385 */     TableWriter table = new TableWriter(out, " │ ");
/* 386 */     table.setMultiLinesCells(true);
/* 387 */     table.writeHorizontalSeparator();
/* 388 */     table.write(resources.getString(146));
/* 389 */     table.nextColumn();
/* 390 */     table.write(resources.getString(228));
/* 391 */     table.nextColumn();
/* 392 */     table.write(resources.getString(46));
/* 393 */     table.nextLine();
/* 394 */     table.writeHorizontalSeparator();
/* 395 */     for (Iterator<Map.Entry> it = this.definitions.entrySet().iterator(); it.hasNext(); ) {
/* 396 */       Map.Entry entry = it.next();
/* 397 */       Object object = ((Definition)entry.getValue()).asObject;
/* 398 */       table.write(String.valueOf(entry.getKey()));
/* 399 */       table.nextColumn();
/* 400 */       table.write(Classes.getShortClassName(object));
/* 401 */       table.nextColumn();
/* 402 */       if (object instanceof IdentifiedObject)
/* 403 */         table.write(((IdentifiedObject)object).getName().getCode()); 
/* 405 */       table.nextLine();
/*     */     } 
/* 407 */     table.writeHorizontalSeparator();
/* 408 */     table.flush();
/*     */   }
/*     */   
/*     */   private static boolean isIdentifier(String text) {
/* 415 */     for (int i = text.length(); --i >= 0; ) {
/* 416 */       char c = text.charAt(i);
/* 417 */       if (!Character.isJavaIdentifierPart(c) && c != ':')
/* 418 */         return false; 
/*     */     } 
/* 421 */     return true;
/*     */   }
/*     */   
/*     */   private static final class Definition implements Serializable {
/*     */     public final String asString;
/*     */     
/*     */     public final Object asObject;
/*     */     
/*     */     public Definition(String asString, Object asObject) {
/* 447 */       this.asString = asString;
/* 448 */       this.asObject = asObject;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Replacement {
/*     */     public final int lower;
/*     */     
/*     */     public final int upper;
/*     */     
/*     */     public final int shift;
/*     */     
/*     */     public Replacement next;
/*     */     
/*     */     public Replacement(int lower, int upper, int shift) {
/* 467 */       this.lower = lower;
/* 468 */       this.upper = upper;
/* 469 */       this.shift = shift;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 477 */       StringBuilder buffer = new StringBuilder();
/* 478 */       for (Replacement r = this; r != null; r = r.next) {
/* 479 */         if (r != this)
/* 480 */           buffer.append(", "); 
/* 482 */         buffer.append('[').append(r.lower).append("..").append(r.upper).append("] → ").append(r.shift);
/*     */       } 
/* 489 */       return buffer.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\Preprocessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */