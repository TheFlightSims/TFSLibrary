/*     */ package org.geotools.util.logging;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Filter;
/*     */ import java.util.logging.Handler;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public abstract class LoggerAdapter extends Logger {
/* 100 */   private static final Pattern MESSAGE_FORMAT = Pattern.compile("\\{\\d+\\}");
/*     */   
/*     */   protected LoggerAdapter(String name) {
/* 108 */     super(name, null);
/* 112 */     super.setUseParentHandlers(false);
/* 119 */     super.setLevel(Level.ALL);
/*     */   }
/*     */   
/*     */   public abstract void setLevel(Level paramLevel);
/*     */   
/*     */   public abstract Level getLevel();
/*     */   
/*     */   protected Level getDebugLevel() {
/* 143 */     return Level.FINER;
/*     */   }
/*     */   
/*     */   public abstract boolean isLoggable(Level paramLevel);
/*     */   
/*     */   public abstract void severe(String paramString);
/*     */   
/*     */   public abstract void warning(String paramString);
/*     */   
/*     */   public abstract void info(String paramString);
/*     */   
/*     */   public abstract void config(String paramString);
/*     */   
/*     */   public abstract void fine(String paramString);
/*     */   
/*     */   public abstract void finer(String paramString);
/*     */   
/*     */   public abstract void finest(String paramString);
/*     */   
/*     */   public void entering(String sourceClass, String sourceMethod) {
/* 239 */     logp(getDebugLevel(), sourceClass, sourceMethod, "ENTRY");
/*     */   }
/*     */   
/*     */   public void entering(String sourceClass, String sourceMethod, Object param) {
/* 249 */     logp(getDebugLevel(), sourceClass, sourceMethod, "ENTRY {0}", param);
/*     */   }
/*     */   
/*     */   public void entering(String sourceClass, String sourceMethod, Object[] params) {
/*     */     String message;
/* 260 */     if (params == null) {
/* 261 */       message = "ENTRY";
/*     */     } else {
/*     */       StringBuilder builder;
/*     */       int i;
/* 262 */       switch (params.length) {
/*     */         case 0:
/* 263 */           message = "ENTRY";
/*     */           break;
/*     */         case 1:
/* 264 */           message = "ENTRY {0}";
/*     */           break;
/*     */         case 2:
/* 265 */           message = "ENTRY {0} {1}";
/*     */           break;
/*     */         default:
/* 267 */           builder = new StringBuilder("ENTRY");
/* 268 */           for (i = 0; i < params.length; i++)
/* 269 */             builder.append(" {").append(i).append('}'); 
/* 271 */           message = builder.toString();
/*     */           break;
/*     */       } 
/*     */     } 
/* 275 */     logp(getDebugLevel(), sourceClass, sourceMethod, message, params);
/*     */   }
/*     */   
/*     */   public void exiting(String sourceClass, String sourceMethod) {
/* 285 */     logp(getDebugLevel(), sourceClass, sourceMethod, "RETURN");
/*     */   }
/*     */   
/*     */   public void exiting(String sourceClass, String sourceMethod, Object result) {
/* 295 */     logp(getDebugLevel(), sourceClass, sourceMethod, "RETURN {0}", result);
/*     */   }
/*     */   
/*     */   public void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
/* 305 */     logp(getDebugLevel(), sourceClass, sourceMethod, "THROW", thrown);
/*     */   }
/*     */   
/*     */   public void log(LogRecord record) {
/* 320 */     Filter filter = getFilter();
/* 321 */     if (filter != null && !filter.isLoggable(record))
/*     */       return; 
/* 324 */     Level level = record.getLevel();
/* 325 */     String sourceClass = record.getSourceClassName();
/* 326 */     String sourceMethod = record.getSourceMethodName();
/* 327 */     String bundleName = record.getResourceBundleName();
/* 328 */     String message = record.getMessage();
/* 329 */     Object[] params = record.getParameters();
/* 330 */     Throwable thrown = record.getThrown();
/* 331 */     ResourceBundle bundle = record.getResourceBundle();
/* 332 */     boolean localized = false;
/* 333 */     if (bundle != null)
/*     */       try {
/* 334 */         message = bundle.getString(message);
/* 335 */         localized = true;
/* 336 */       } catch (MissingResourceException e) {} 
/* 340 */     boolean useThrown = (thrown != null && (params == null || params.length == 0));
/* 341 */     if (localized) {
/* 343 */       if (useThrown) {
/* 344 */         logp(level, sourceClass, sourceMethod, message, thrown);
/*     */       } else {
/* 346 */         logp(level, sourceClass, sourceMethod, message, params);
/*     */       } 
/* 351 */     } else if (useThrown) {
/* 352 */       logrb(level, sourceClass, sourceMethod, bundleName, message, thrown);
/*     */     } else {
/* 354 */       logrb(level, sourceClass, sourceMethod, bundleName, message, params);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void log(Level level, String message) {
/* 368 */     int n = level.intValue();
/* 369 */     switch (n / 100) {
/*     */       default:
/* 371 */         if (n < 0 || n == Integer.MAX_VALUE)
/*     */           break; 
/*     */       case 10:
/* 375 */         severe(message);
/*     */         break;
/*     */       case 9:
/* 376 */         warning(message);
/*     */         break;
/*     */       case 8:
/* 377 */         info(message);
/*     */         break;
/*     */       case 7:
/* 378 */         config(message);
/*     */         break;
/*     */       case 5:
/*     */       case 6:
/* 380 */         fine(message);
/*     */         break;
/*     */       case 4:
/* 381 */         finer(message);
/*     */         break;
/*     */       case 3:
/* 382 */         finest(message);
/*     */         break;
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void log(Level level, String message, Throwable thrown) {
/* 395 */     log(level, message);
/*     */   }
/*     */   
/*     */   public void log(Level level, String message, Object param) {
/* 405 */     if (isLoggable(level))
/* 406 */       log(level, message, asArray(param)); 
/*     */   }
/*     */   
/*     */   public void log(Level level, String message, Object[] params) {
/* 417 */     if (isLoggable(level))
/* 418 */       log(level, format(message, params)); 
/*     */   }
/*     */   
/*     */   public void logp(Level level, String sourceClass, String sourceMethod, String message) {
/* 431 */     log(level, message);
/*     */   }
/*     */   
/*     */   public void logp(Level level, String sourceClass, String sourceMethod, String message, Throwable thrown) {
/* 443 */     log(level, message, thrown);
/*     */   }
/*     */   
/*     */   public void logp(Level level, String sourceClass, String sourceMethod, String message, Object param) {
/* 459 */     if (isLoggable(level))
/* 460 */       logp(level, sourceClass, sourceMethod, message, asArray(param)); 
/*     */   }
/*     */   
/*     */   public void logp(Level level, String sourceClass, String sourceMethod, String message, Object[] params) {
/* 476 */     if (isLoggable(level))
/* 477 */       logp(level, sourceClass, sourceMethod, format(message, params)); 
/*     */   }
/*     */   
/*     */   public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String message) {
/* 490 */     if (isLoggable(level))
/* 491 */       logp(level, sourceClass, sourceMethod, localize(bundleName, message)); 
/*     */   }
/*     */   
/*     */   public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String message, Throwable thrown) {
/* 504 */     if (isLoggable(level))
/* 505 */       logp(level, sourceClass, sourceMethod, localize(bundleName, message), thrown); 
/*     */   }
/*     */   
/*     */   public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String message, Object param) {
/* 518 */     if (isLoggable(level))
/* 519 */       logp(level, sourceClass, sourceMethod, localize(bundleName, message), param); 
/*     */   }
/*     */   
/*     */   public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String message, Object[] params) {
/* 532 */     if (isLoggable(level))
/* 533 */       logp(level, sourceClass, sourceMethod, localize(bundleName, message), params); 
/*     */   }
/*     */   
/*     */   public void addHandler(Handler handler) {}
/*     */   
/*     */   public void removeHandler(Handler handler) {}
/*     */   
/*     */   public void setUseParentHandlers(boolean useParentHandlers) {}
/*     */   
/*     */   public void setParent(Logger parent) {}
/*     */   
/*     */   public void setFilter(Filter filter) {}
/*     */   
/*     */   private static Object[] asArray(Object param) {
/* 588 */     (new Object[1])[0] = param;
/* 588 */     return (param != null) ? new Object[1] : null;
/*     */   }
/*     */   
/*     */   private static String format(String message, Object[] params) {
/* 596 */     if (params != null && params.length != 0 && 
/* 597 */       MESSAGE_FORMAT.matcher(message).find())
/*     */       try {
/* 598 */         message = MessageFormat.format(message, params);
/* 599 */       } catch (IllegalArgumentException e) {} 
/* 604 */     return message;
/*     */   }
/*     */   
/*     */   private static String localize(String bundleName, String message) {
/* 612 */     if (bundleName != null)
/*     */       try {
/* 613 */         message = ResourceBundle.getBundle(bundleName).getString(message);
/* 614 */       } catch (MissingResourceException e) {} 
/* 618 */     return message;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\LoggerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */