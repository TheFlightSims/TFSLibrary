/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Writer;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class IndexedResourceBundle extends ResourceBundle {
/*     */   private static final int MAX_STRING_LENGTH = 200;
/*     */   
/*     */   private final String filename;
/*     */   
/*     */   private String[] values;
/*     */   
/*     */   private transient Locale locale;
/*     */   
/*     */   private transient MessageFormat format;
/*     */   
/*     */   private transient int lastKey;
/*     */   
/*     */   protected IndexedResourceBundle() {
/* 108 */     this.filename = getClass().getSimpleName() + ".utf";
/*     */   }
/*     */   
/*     */   protected IndexedResourceBundle(String filename) {
/* 118 */     this.filename = filename;
/*     */   }
/*     */   
/*     */   private Locale getFormatLocale() {
/* 125 */     if (this.locale == null) {
/* 126 */       this.locale = Locale.getDefault();
/* 127 */       Locale resourceLocale = getLocale();
/* 128 */       if (!this.locale.getLanguage().equalsIgnoreCase(resourceLocale.getLanguage()))
/* 129 */         this.locale = resourceLocale; 
/*     */     } 
/* 132 */     return this.locale;
/*     */   }
/*     */   
/*     */   private String getPackageName() {
/* 139 */     String name = getClass().getName();
/* 140 */     int index = name.lastIndexOf('.');
/* 141 */     return (index >= 0) ? name.substring(0, index) : "org.geotools";
/*     */   }
/*     */   
/*     */   public final void list(Writer out) throws IOException {
/* 153 */     list(out, ensureLoaded((String)null));
/*     */   }
/*     */   
/*     */   private static void list(Writer out, String[] values) throws IOException {
/* 165 */     String lineSeparator = System.getProperty("line.separator", "\n");
/* 166 */     for (int i = 0; i < values.length; i++) {
/* 167 */       String value = values[i];
/* 168 */       if (value != null) {
/* 171 */         int indexCR = value.indexOf('\r');
/* 171 */         if (indexCR < 0)
/* 171 */           indexCR = value.length(); 
/* 172 */         int indexLF = value.indexOf('\n');
/* 172 */         if (indexLF < 0)
/* 172 */           indexLF = value.length(); 
/* 173 */         String number = String.valueOf(i);
/* 174 */         out.write(Utilities.spaces(5 - number.length()));
/* 175 */         out.write(number);
/* 176 */         out.write(":\t");
/* 177 */         out.write(value.substring(0, Math.min(indexCR, indexLF)));
/* 178 */         out.write(lineSeparator);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String[] ensureLoaded(String key) throws MissingResourceException {
/* 191 */     LogRecord record = null;
/*     */     try {
/*     */       String[] values;
/* 194 */       synchronized (this) {
/* 195 */         values = this.values;
/* 196 */         if (values != null)
/* 197 */           return values; 
/* 205 */         record = new LogRecord(Level.FINER, "Loaded resources for {0} from bundle \"{1}\".");
/* 206 */         record.setSourceClassName(getClass().getName());
/* 207 */         record.setSourceMethodName((key != null) ? "getObject" : "getKeys");
/* 212 */         String name = this.filename;
/*     */         InputStream in;
/* 213 */         while ((in = getClass().getResourceAsStream(name)) == null) {
/* 214 */           int ext = name.lastIndexOf('.');
/* 215 */           int lang = name.lastIndexOf('_', ext - 1);
/* 216 */           if (lang <= 0)
/* 217 */             throw new FileNotFoundException(this.filename); 
/* 219 */           name = name.substring(0, lang) + name.substring(ext);
/*     */         } 
/* 221 */         DataInputStream input = new DataInputStream(new BufferedInputStream(in));
/* 222 */         this.values = values = new String[input.readInt()];
/* 223 */         for (int i = 0; i < values.length; i++) {
/* 224 */           values[i] = input.readUTF();
/* 225 */           if (values[i].length() == 0)
/* 226 */             values[i] = null; 
/*     */         } 
/* 228 */         input.close();
/* 232 */         String language = getLocale().getDisplayName(Locale.US);
/* 233 */         if (language == null || language.length() == 0)
/* 234 */           language = "<default>"; 
/* 236 */         record.setParameters((Object[])new String[] { language, getPackageName() });
/*     */       } 
/* 238 */       Logger logger = Logging.getLogger(IndexedResourceBundle.class);
/* 239 */       record.setLoggerName(logger.getName());
/* 240 */       logger.log(record);
/* 241 */       return values;
/* 242 */     } catch (IOException exception) {
/* 243 */       record.setLevel(Level.WARNING);
/* 244 */       record.setMessage(exception.getLocalizedMessage());
/* 245 */       record.setThrown(exception);
/* 246 */       Logger logger = Logging.getLogger(IndexedResourceBundle.class);
/* 247 */       record.setLoggerName(logger.getName());
/* 248 */       logger.log(record);
/* 249 */       MissingResourceException error = new MissingResourceException(exception.getLocalizedMessage(), getClass().getName(), key);
/* 251 */       error.initCause(exception);
/* 252 */       throw error;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Enumeration<String> getKeys() {
/* 261 */     final String[] values = ensureLoaded((String)null);
/* 262 */     return new Enumeration<String>() {
/* 263 */         private int i = 0;
/*     */         
/*     */         public boolean hasMoreElements() {
/*     */           while (true) {
/* 267 */             if (this.i >= values.length)
/* 267 */               return false; 
/* 268 */             if (values[this.i] != null)
/* 268 */               return true; 
/* 269 */             this.i++;
/*     */           } 
/*     */         }
/*     */         
/*     */         public String nextElement() {
/*     */           while (true) {
/* 275 */             if (this.i >= values.length)
/* 275 */               throw new NoSuchElementException(); 
/* 276 */             if (values[this.i] != null)
/* 276 */               return String.valueOf(this.i++); 
/* 277 */             this.i++;
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   protected final Object handleGetObject(String key) {
/*     */     int keyID;
/* 294 */     String[] values = ensureLoaded(key);
/*     */     try {
/* 297 */       keyID = Integer.parseInt(key);
/* 298 */     } catch (NumberFormatException exception) {
/* 299 */       return null;
/*     */     } 
/* 301 */     return (keyID >= 0 && keyID < values.length) ? values[keyID] : null;
/*     */   }
/*     */   
/*     */   private static String summarize(String text, int maxLength) {
/* 328 */     text = text.trim();
/* 329 */     int length = text.length();
/* 330 */     if (length <= maxLength)
/* 331 */       return text; 
/* 337 */     maxLength = maxLength - 7 >> 1;
/* 338 */     if (maxLength <= 0)
/* 339 */       return text; 
/* 350 */     int break1 = maxLength;
/* 351 */     int break2 = length - maxLength;
/* 352 */     for (int lower = maxLength >> 1; break1 >= lower; break1--) {
/* 353 */       if (!Character.isUnicodeIdentifierPart(text.charAt(break1))) {
/* 354 */         while (--break1 >= lower && !Character.isUnicodeIdentifierPart(text.charAt(break1)));
/*     */         break;
/*     */       } 
/*     */     } 
/* 358 */     for (int upper = length - (maxLength >> 1); break2 < upper; break2++) {
/* 359 */       if (!Character.isUnicodeIdentifierPart(text.charAt(break2))) {
/* 360 */         while (++break2 < upper && !Character.isUnicodeIdentifierPart(text.charAt(break2)));
/*     */         break;
/*     */       } 
/*     */     } 
/* 364 */     return (text.substring(0, break1 + 1) + " (...) " + text.substring(break2)).trim();
/*     */   }
/*     */   
/*     */   private Object[] toArray(Object arguments) {
/*     */     Object[] array;
/* 379 */     if (arguments instanceof Object[]) {
/* 380 */       array = (Object[])arguments;
/*     */     } else {
/* 382 */       array = new Object[] { arguments };
/*     */     } 
/* 384 */     for (int i = 0; i < array.length; i++) {
/* 385 */       Object element = array[i];
/* 386 */       if (element instanceof CharSequence) {
/*     */         String s0;
/* 388 */         if (element instanceof InternationalString) {
/* 389 */           s0 = ((InternationalString)element).toString(getFormatLocale());
/*     */         } else {
/* 391 */           s0 = element.toString();
/*     */         } 
/* 393 */         String s1 = summarize(s0, 200);
/* 394 */         if (s0 != s1 && !s0.equals(s1)) {
/* 395 */           if (array == arguments) {
/* 396 */             array = new Object[array.length];
/* 397 */             System.arraycopy(arguments, 0, array, 0, array.length);
/*     */           } 
/* 399 */           array[i] = s1;
/*     */         } 
/* 401 */       } else if (element instanceof Throwable) {
/* 402 */         String message = ((Throwable)element).getLocalizedMessage();
/* 403 */         if (message == null)
/* 404 */           message = Classes.getShortClassName(element); 
/* 406 */         array[i] = message;
/* 407 */       } else if (element instanceof Class) {
/* 408 */         array[i] = Classes.getShortName((Class)element);
/*     */       } 
/*     */     } 
/* 411 */     return array;
/*     */   }
/*     */   
/*     */   public final String getMenuLabel(int key) throws MissingResourceException {
/* 423 */     return getString(key) + "...";
/*     */   }
/*     */   
/*     */   public final String getLabel(int key) throws MissingResourceException {
/* 435 */     return getString(key) + ": ";
/*     */   }
/*     */   
/*     */   public final String getString(int key) throws MissingResourceException {
/* 446 */     return getString(String.valueOf(key));
/*     */   }
/*     */   
/*     */   public final String getString(int key, Object arg0) throws MissingResourceException {
/* 475 */     String pattern = getString(key);
/* 476 */     Object[] arguments = toArray(arg0);
/* 477 */     synchronized (this) {
/* 478 */       if (this.format == null) {
/* 482 */         this.format = new MessageFormat(pattern, getFormatLocale());
/* 483 */       } else if (key != this.lastKey) {
/* 488 */         this.format.applyPattern(pattern);
/* 489 */         this.lastKey = key;
/*     */       } 
/* 491 */       return this.format.format(arguments);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final String getString(int key, Object arg0, Object arg1) throws MissingResourceException {
/* 509 */     return getString(key, new Object[] { arg0, arg1 });
/*     */   }
/*     */   
/*     */   public final String getString(int key, Object arg0, Object arg1, Object arg2) throws MissingResourceException {
/* 528 */     return getString(key, new Object[] { arg0, arg1, arg2 });
/*     */   }
/*     */   
/*     */   public final String getString(int key, Object arg0, Object arg1, Object arg2, Object arg3) throws MissingResourceException {
/* 549 */     return getString(key, new Object[] { arg0, arg1, arg2, arg3 });
/*     */   }
/*     */   
/*     */   public final String getString(int key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4) throws MissingResourceException {
/* 572 */     return getString(key, new Object[] { arg0, arg1, arg2, arg3, arg4 });
/*     */   }
/*     */   
/*     */   public LogRecord getLogRecord(Level level, int key) {
/* 583 */     return getLogRecord(level, key, (Object)null);
/*     */   }
/*     */   
/*     */   public LogRecord getLogRecord(Level level, int key, Object arg0) {
/* 597 */     LogRecord record = new LogRecord(level, String.valueOf(key));
/* 598 */     record.setResourceBundle(this);
/* 599 */     if (arg0 != null)
/* 600 */       record.setParameters(toArray(arg0)); 
/* 602 */     return record;
/*     */   }
/*     */   
/*     */   public LogRecord getLogRecord(Level level, int key, Object arg0, Object arg1) {
/* 618 */     return getLogRecord(level, key, new Object[] { arg0, arg1 });
/*     */   }
/*     */   
/*     */   public LogRecord getLogRecord(Level level, int key, Object arg0, Object arg1, Object arg2) {
/* 636 */     return getLogRecord(level, key, new Object[] { arg0, arg1, arg2 });
/*     */   }
/*     */   
/*     */   public LogRecord getLogRecord(Level level, int key, Object arg0, Object arg1, Object arg2, Object arg3) {
/* 656 */     return getLogRecord(level, key, new Object[] { arg0, arg1, arg2, arg3 });
/*     */   }
/*     */   
/*     */   public static String format(LogRecord record) {
/* 669 */     String message = record.getMessage();
/* 670 */     ResourceBundle resources = record.getResourceBundle();
/* 671 */     if (resources instanceof IndexedResourceBundle) {
/* 672 */       int key = -1;
/*     */       try {
/* 674 */         key = Integer.parseInt(message);
/* 675 */       } catch (NumberFormatException e) {
/* 676 */         unexpectedException(e);
/*     */       } 
/* 678 */       if (key >= 0) {
/* 679 */         Object[] parameters = record.getParameters();
/* 680 */         return ((IndexedResourceBundle)resources).getString(key, parameters);
/*     */       } 
/*     */     } 
/* 683 */     if (resources != null) {
/*     */       try {
/* 685 */         message = resources.getString(message);
/* 686 */       } catch (MissingResourceException e) {
/* 687 */         unexpectedException(e);
/*     */       } 
/* 689 */       Object[] parameters = record.getParameters();
/* 690 */       if (parameters != null && parameters.length != 0) {
/* 691 */         int offset = message.indexOf('{');
/* 692 */         if (offset >= 0 && offset < message.length() - 1) {
/* 694 */           char c = message.charAt(offset);
/* 695 */           if (c >= '0' && c <= '9')
/*     */             try {
/* 696 */               return MessageFormat.format(message, parameters);
/* 697 */             } catch (IllegalArgumentException e) {
/* 698 */               unexpectedException(e);
/*     */             }  
/*     */         } 
/*     */       } 
/*     */     } 
/* 703 */     return message;
/*     */   }
/*     */   
/*     */   private static void unexpectedException(RuntimeException exception) {
/* 710 */     Logging.unexpectedException(IndexedResourceBundle.class, "format", exception);
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/* 719 */     StringBuilder buffer = new StringBuilder(Classes.getShortClassName(this));
/* 720 */     buffer.append('[');
/* 721 */     if (this.values != null) {
/* 722 */       int count = 0;
/* 723 */       for (int i = 0; i < this.values.length; i++) {
/* 724 */         if (this.values[i] != null)
/* 724 */           count++; 
/*     */       } 
/* 726 */       buffer.append(count);
/* 727 */       buffer.append(" values");
/*     */     } 
/* 729 */     buffer.append(']');
/* 730 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\IndexedResourceBundle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */