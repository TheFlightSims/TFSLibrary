/*     */ package org.geotools.util.logging;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class LoggedFormat<T> extends Format {
/*     */   private static final long serialVersionUID = 4578880360344271325L;
/*     */   
/*     */   private final Format format;
/*     */   
/*     */   private final Class<T> type;
/*     */   
/*     */   private Level level;
/*     */   
/*     */   private String logger;
/*     */   
/*     */   private String className;
/*     */   
/*     */   private String methodName;
/*     */   
/*     */   protected LoggedFormat(Format format, Class<T> type) {
/* 102 */     this.format = format;
/* 103 */     this.type = type;
/* 104 */     this.level = Level.WARNING;
/*     */   }
/*     */   
/*     */   public static <T> LoggedFormat<T> getInstance(Format format, Class<T> type) {
/* 114 */     return new LoggedFormat<T>(format, type);
/*     */   }
/*     */   
/*     */   public void setLogger(String logger) {
/* 123 */     this.logger = logger;
/*     */   }
/*     */   
/*     */   public void setLevel(Level level) {
/* 135 */     if (level != null)
/* 136 */       this.level = level; 
/*     */   }
/*     */   
/*     */   public void setCaller(Class<?> caller, String method) {
/* 149 */     this.className = (caller != null) ? caller.getName() : null;
/* 150 */     this.methodName = method;
/*     */   }
/*     */   
/*     */   public T parse(String text) {
/* 163 */     if (text == null || (text = text.trim()).length() == 0)
/* 164 */       return null; 
/* 166 */     ParsePosition position = new ParsePosition(0);
/* 167 */     Object value = parseObject(text, position);
/* 168 */     int index = position.getIndex();
/* 169 */     int error = position.getErrorIndex();
/* 170 */     if (error >= 0 && error < index)
/* 171 */       index = error; 
/* 173 */     if (index < text.length()) {
/* 174 */       doLogWarning(formatUnparsable(text, 0, index, getWarningLocale(), this.level));
/* 175 */     } else if (value != null && !this.type.isInstance(value)) {
/* 176 */       doLogWarning(Errors.getResources(getWarningLocale()).getLogRecord(this.level, 61, value.getClass(), this.type));
/* 178 */       return null;
/*     */     } 
/* 180 */     return this.type.cast(value);
/*     */   }
/*     */   
/*     */   public Object parseObject(String text) throws ParseException {
/* 194 */     return this.format.parseObject(text);
/*     */   }
/*     */   
/*     */   public Object parseObject(String text, ParsePosition position) {
/* 207 */     return this.format.parseObject(text, position);
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object value, StringBuffer toAppendTo, FieldPosition position) {
/* 222 */     return this.format.format(value, toAppendTo, position);
/*     */   }
/*     */   
/*     */   public AttributedCharacterIterator formatToCharacterIterator(Object value) {
/* 234 */     return this.format.formatToCharacterIterator(value);
/*     */   }
/*     */   
/*     */   private void doLogWarning(LogRecord warning) {
/* 241 */     if (this.className != null)
/* 242 */       warning.setSourceClassName(this.className); 
/* 244 */     if (this.methodName != null)
/* 245 */       warning.setSourceMethodName(this.methodName); 
/* 247 */     logWarning(warning);
/*     */   }
/*     */   
/*     */   protected void logWarning(LogRecord warning) {
/* 259 */     if (this.logger != null) {
/* 260 */       Logger logger = Logging.getLogger(this.logger);
/* 261 */       warning.setLoggerName(logger.getName());
/* 262 */       logger.log(warning);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Locale getWarningLocale() {
/* 271 */     return Locale.getDefault();
/*     */   }
/*     */   
/*     */   public static String formatUnparsable(String text, int index, int errorIndex, Locale locale) {
/* 293 */     return (String)doFormatUnparsable(text, index, errorIndex, locale, null);
/*     */   }
/*     */   
/*     */   public static LogRecord formatUnparsable(String text, int index, int errorIndex, Locale locale, Level level) {
/* 315 */     if (level == null)
/* 318 */       level = Level.WARNING; 
/* 320 */     return (LogRecord)doFormatUnparsable(text, index, errorIndex, locale, level);
/*     */   }
/*     */   
/*     */   private static Object doFormatUnparsable(String text, int index, int errorIndex, Locale locale, Level level) {
/* 330 */     Errors resources = Errors.getResources(locale);
/* 331 */     int length = text.length();
/* 332 */     if (errorIndex < index)
/* 333 */       errorIndex = index; 
/* 335 */     if (errorIndex == length) {
/* 336 */       if (level != null)
/* 337 */         return resources.getLogRecord(level, 174); 
/* 339 */       return resources.getString(174);
/*     */     } 
/* 341 */     int upper = errorIndex;
/* 342 */     if (upper < length) {
/* 343 */       int type = Character.getType(text.charAt(upper));
/*     */       do {
/*     */       
/* 344 */       } while (++upper < length && 
/* 345 */         Character.getType(text.charAt(upper)) == type);
/*     */     } 
/* 350 */     String error = text.substring(errorIndex, upper);
/* 351 */     text = text.substring(index);
/* 352 */     if (level != null)
/* 353 */       return resources.getLogRecord(level, 192, text, error); 
/* 355 */     return resources.getString(192, text, error);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 363 */     StringBuilder buffer = (new StringBuilder(Classes.getShortClassName(this))).append('[').append(Classes.getShortClassName(this.format));
/* 365 */     if (this.logger != null)
/* 366 */       buffer.append(", logger=").append(this.logger); 
/* 368 */     return buffer.append(']').toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\LoggedFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */