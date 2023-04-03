/*     */ package org.geotools.util.logging;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import java.util.logging.ConsoleHandler;
/*     */ import java.util.logging.Formatter;
/*     */ import java.util.logging.Handler;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogManager;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.logging.SimpleFormatter;
/*     */ import org.geotools.io.LineWriter;
/*     */ import org.geotools.util.Utilities;
/*     */ 
/*     */ public class MonolineFormatter extends Formatter {
/*     */   private static final String PREFIX = "";
/*     */   
/*     */   private static final String SUFFIX = " - ";
/*     */   
/*     */   private static final int DEFAULT_WIDTH = 9;
/*     */   
/*     */   private static final int NO_SOURCE = 0;
/*     */   
/*     */   private static final int NO_SOURCE_EX = 1;
/*     */   
/*     */   private static final int LOGGER_SHORT = 2;
/*     */   
/*     */   private static final int LOGGER_LONG = 3;
/*     */   
/*     */   private static final int CLASS_SHORT = 4;
/*     */   
/*     */   private static final int CLASS_LONG = 5;
/*     */   
/* 117 */   private static String[] FORMAT_LABELS = new String[6];
/*     */   
/*     */   static {
/* 119 */     FORMAT_LABELS[1] = "none";
/* 120 */     FORMAT_LABELS[2] = "logger:short";
/* 121 */     FORMAT_LABELS[3] = "logger:long";
/* 122 */     FORMAT_LABELS[4] = "class:short";
/* 123 */     FORMAT_LABELS[5] = "class:long";
/*     */   }
/*     */   
/* 130 */   private final String lineSeparator = System.getProperty("line.separator", "\n");
/*     */   
/* 137 */   private String bodyLineSeparator = this.lineSeparator;
/*     */   
/*     */   private final int margin;
/*     */   
/*     */   private final long startMillis;
/*     */   
/* 158 */   private SimpleDateFormat timeFormat = null;
/*     */   
/* 165 */   private int sourceFormat = 0;
/*     */   
/*     */   private final StringBuffer buffer;
/*     */   
/*     */   private final LineWriter writer;
/*     */   
/*     */   public MonolineFormatter() {
/* 184 */     this.startMillis = System.currentTimeMillis();
/* 185 */     this.margin = 9;
/* 186 */     StringWriter str = new StringWriter();
/* 187 */     this.writer = new LineWriter(str);
/* 188 */     this.buffer = str.getBuffer();
/* 189 */     this.buffer.append("");
/* 192 */     LogManager manager = LogManager.getLogManager();
/* 193 */     String classname = MonolineFormatter.class.getName();
/*     */     try {
/* 195 */       setTimeFormat(manager.getProperty(classname + ".time"));
/* 196 */     } catch (IllegalArgumentException exception) {
/* 199 */       System.err.println(exception);
/*     */     } 
/*     */     try {
/* 202 */       setSourceFormat(manager.getProperty(classname + ".source"));
/* 203 */     } catch (IllegalArgumentException exception) {
/* 204 */       System.err.println(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void setTimeFormat(String pattern) {
/* 217 */     if (pattern == null) {
/* 218 */       this.timeFormat = null;
/* 219 */     } else if (this.timeFormat == null) {
/* 220 */       this.timeFormat = new SimpleDateFormat(pattern);
/* 221 */       this.timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
/*     */     } else {
/* 223 */       this.timeFormat.applyPattern(pattern);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized String getTimeFormat() {
/* 236 */     return (this.timeFormat != null) ? this.timeFormat.toPattern() : null;
/*     */   }
/*     */   
/*     */   public synchronized void setSourceFormat(String format) {
/* 253 */     if (format != null)
/* 254 */       format = format.trim().toLowerCase(); 
/* 256 */     for (int i = 0; i < FORMAT_LABELS.length; i++) {
/* 257 */       if (Utilities.equals(FORMAT_LABELS[i], format)) {
/* 258 */         this.sourceFormat = i;
/*     */         return;
/*     */       } 
/*     */     } 
/* 262 */     throw new IllegalArgumentException(format);
/*     */   }
/*     */   
/*     */   public String getSourceFormat() {
/* 274 */     return FORMAT_LABELS[this.sourceFormat];
/*     */   }
/*     */   
/*     */   public synchronized String format(LogRecord record) {
/*     */     int pos, dot;
/* 285 */     this.buffer.setLength("".length());
/* 292 */     if (this.timeFormat != null) {
/* 293 */       Date time = new Date(Math.max(0L, record.getMillis() - this.startMillis));
/* 294 */       this.timeFormat.format(time, this.buffer, new FieldPosition(0));
/* 295 */       this.buffer.append(' ');
/*     */     } 
/* 302 */     int offset = this.buffer.length();
/* 303 */     this.buffer.append(record.getLevel().getLocalizedName());
/* 304 */     offset = this.buffer.length() - offset;
/* 305 */     this.buffer.append(Utilities.spaces(this.margin - offset));
/* 310 */     String logger = record.getLoggerName();
/* 311 */     String classname = record.getSourceClassName();
/* 312 */     switch (this.sourceFormat) {
/*     */       case 2:
/* 314 */         pos = logger.lastIndexOf('.');
/* 315 */         if (pos >= 0)
/* 316 */           logger = logger.substring(pos); 
/*     */       case 3:
/* 321 */         this.buffer.append(' ');
/* 322 */         this.buffer.append(logger);
/*     */         break;
/*     */       case 4:
/* 326 */         dot = classname.lastIndexOf('.');
/* 327 */         if (dot >= 0)
/* 328 */           classname = classname.substring(dot + 1); 
/* 330 */         classname = classname.replace('$', '.');
/*     */       case 5:
/* 334 */         this.buffer.append(' ');
/* 335 */         this.buffer.append(classname);
/*     */         break;
/*     */     } 
/* 339 */     this.buffer.append(" - ");
/* 345 */     int margin = this.buffer.length();
/* 346 */     assert margin >= this.margin;
/* 347 */     if (this.bodyLineSeparator.length() != this.lineSeparator.length() + margin)
/* 348 */       this.bodyLineSeparator = this.lineSeparator + Utilities.spaces(margin); 
/*     */     try {
/* 351 */       this.writer.setLineSeparator(this.bodyLineSeparator);
/* 352 */       this.writer.write(String.valueOf(formatMessage(record)));
/* 353 */       this.writer.setLineSeparator(this.lineSeparator);
/* 354 */       this.writer.write(10);
/* 355 */       this.writer.flush();
/* 356 */     } catch (IOException exception) {
/* 358 */       throw new AssertionError(exception);
/*     */     } 
/* 360 */     return this.buffer.toString();
/*     */   }
/*     */   
/*     */   public static MonolineFormatter configureConsoleHandler(Logger logger, Level level) {
/* 383 */     MonolineFormatter monoline = null;
/* 384 */     boolean foundConsoleHandler = false;
/* 385 */     Handler[] handlers = logger.getHandlers();
/* 386 */     for (int i = 0; i < handlers.length; i++) {
/* 387 */       Handler handler = handlers[i];
/* 388 */       if (handler.getClass().equals(ConsoleHandler.class)) {
/* 389 */         foundConsoleHandler = true;
/* 390 */         Formatter formatter = handler.getFormatter();
/* 391 */         if (formatter instanceof MonolineFormatter) {
/* 398 */           if (monoline == null) {
/* 399 */             monoline = (MonolineFormatter)formatter;
/* 400 */             if (level != null)
/* 401 */               handler.setLevel(level); 
/*     */           } 
/* 404 */         } else if (formatter.getClass().equals(SimpleFormatter.class)) {
/* 411 */           if (monoline == null)
/* 412 */             monoline = new MonolineFormatter(); 
/*     */           try {
/* 415 */             handler.setFormatter(monoline);
/* 416 */             if (level != null)
/* 417 */               handler.setLevel(level); 
/* 419 */           } catch (SecurityException exception) {
/* 420 */             unexpectedException(exception);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 429 */     for (Logger parent = logger; parent.getUseParentHandlers(); ) {
/* 430 */       parent = parent.getParent();
/* 431 */       if (parent == null)
/*     */         break; 
/* 434 */       handlers = parent.getHandlers();
/* 435 */       for (int j = 0; j < handlers.length; j++) {
/* 436 */         Handler handler = handlers[j];
/* 437 */         if (handler.getClass().equals(ConsoleHandler.class)) {
/* 438 */           if (!foundConsoleHandler)
/*     */             continue; 
/* 442 */           foundConsoleHandler = true;
/* 443 */           Formatter formatter = handler.getFormatter();
/* 444 */           if (formatter.getClass().equals(SimpleFormatter.class)) {
/* 445 */             monoline = addHandler(logger, level);
/*     */             continue;
/*     */           } 
/*     */         } 
/* 449 */         logger.addHandler(handler);
/*     */         continue;
/*     */       } 
/*     */     } 
/* 452 */     logger.setUseParentHandlers(false);
/* 453 */     if (!foundConsoleHandler)
/* 454 */       monoline = addHandler(logger, level); 
/* 456 */     return monoline;
/*     */   }
/*     */   
/*     */   private static MonolineFormatter addHandler(Logger logger, Level level) {
/* 464 */     MonolineFormatter monoline = new MonolineFormatter();
/*     */     try {
/* 466 */       Handler handler = new ConsoleHandler();
/* 467 */       handler.setFormatter(monoline);
/* 468 */       if (level != null)
/* 469 */         handler.setLevel(level); 
/* 471 */       logger.addHandler(handler);
/* 472 */     } catch (SecurityException exception) {
/* 473 */       unexpectedException(exception);
/*     */     } 
/* 480 */     return monoline;
/*     */   }
/*     */   
/*     */   private static void unexpectedException(Exception exception) {
/* 487 */     Logging.unexpectedException(MonolineFormatter.class, "configureConsoleHandler", exception);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\MonolineFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */