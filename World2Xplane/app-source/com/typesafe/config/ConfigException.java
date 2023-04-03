/*     */ package com.typesafe.config;
/*     */ 
/*     */ import com.typesafe.config.impl.ConfigImplUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ public abstract class ConfigException extends RuntimeException implements Serializable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private final transient ConfigOrigin origin;
/*     */   
/*     */   protected ConfigException(ConfigOrigin origin, String message, Throwable cause) {
/*  23 */     super(origin.description() + ": " + message, cause);
/*  24 */     this.origin = origin;
/*     */   }
/*     */   
/*     */   protected ConfigException(ConfigOrigin origin, String message) {
/*  28 */     this(origin.description() + ": " + message, (Throwable)null);
/*     */   }
/*     */   
/*     */   protected ConfigException(String message, Throwable cause) {
/*  32 */     super(message, cause);
/*  33 */     this.origin = null;
/*     */   }
/*     */   
/*     */   protected ConfigException(String message) {
/*  37 */     this(message, (Throwable)null);
/*     */   }
/*     */   
/*     */   public ConfigOrigin origin() {
/*  50 */     return this.origin;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  57 */     out.defaultWriteObject();
/*  58 */     ConfigImplUtil.writeOrigin(out, this.origin);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*     */     Field f;
/*  63 */     in.defaultReadObject();
/*  64 */     ConfigOrigin origin = ConfigImplUtil.readOrigin(in);
/*     */     try {
/*  68 */       f = ConfigException.class.getDeclaredField("origin");
/*  69 */     } catch (NoSuchFieldException e) {
/*  70 */       throw new IOException("ConfigException has no origin field?", e);
/*  71 */     } catch (SecurityException e) {
/*  72 */       throw new IOException("unable to fill out origin field in ConfigException", e);
/*     */     } 
/*  74 */     f.setAccessible(true);
/*     */     try {
/*  76 */       f.set(this, origin);
/*  77 */     } catch (IllegalArgumentException e) {
/*  78 */       throw new IOException("unable to set origin field", e);
/*  79 */     } catch (IllegalAccessException e) {
/*  80 */       throw new IOException("unable to set origin field", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class WrongType extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public WrongType(ConfigOrigin origin, String path, String expected, String actual, Throwable cause) {
/*  94 */       super(origin, path + " has type " + actual + " rather than " + expected, cause);
/*     */     }
/*     */     
/*     */     public WrongType(ConfigOrigin origin, String path, String expected, String actual) {
/*  98 */       this(origin, path, expected, actual, null);
/*     */     }
/*     */     
/*     */     public WrongType(ConfigOrigin origin, String message, Throwable cause) {
/* 102 */       super(origin, message, cause);
/*     */     }
/*     */     
/*     */     public WrongType(ConfigOrigin origin, String message) {
/* 106 */       super(origin, message, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Missing extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Missing(String path, Throwable cause) {
/* 118 */       super("No configuration setting found for key '" + path + "'", cause);
/*     */     }
/*     */     
/*     */     public Missing(String path) {
/* 123 */       this(path, (Throwable)null);
/*     */     }
/*     */     
/*     */     protected Missing(ConfigOrigin origin, String message, Throwable cause) {
/* 127 */       super(origin, message, cause);
/*     */     }
/*     */     
/*     */     protected Missing(ConfigOrigin origin, String message) {
/* 131 */       this(origin, message, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Null extends Missing {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private static String makeMessage(String path, String expected) {
/* 143 */       if (expected != null)
/* 144 */         return "Configuration key '" + path + "' is set to null but expected " + expected; 
/* 147 */       return "Configuration key '" + path + "' is null";
/*     */     }
/*     */     
/*     */     public Null(ConfigOrigin origin, String path, String expected, Throwable cause) {
/* 153 */       super(origin, makeMessage(path, expected), cause);
/*     */     }
/*     */     
/*     */     public Null(ConfigOrigin origin, String path, String expected) {
/* 157 */       this(origin, path, expected, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BadValue extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public BadValue(ConfigOrigin origin, String path, String message, Throwable cause) {
/* 172 */       super(origin, "Invalid value at '" + path + "': " + message, cause);
/*     */     }
/*     */     
/*     */     public BadValue(ConfigOrigin origin, String path, String message) {
/* 176 */       this(origin, path, message, null);
/*     */     }
/*     */     
/*     */     public BadValue(String path, String message, Throwable cause) {
/* 180 */       super("Invalid value at '" + path + "': " + message, cause);
/*     */     }
/*     */     
/*     */     public BadValue(String path, String message) {
/* 184 */       this(path, message, (Throwable)null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BadPath extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public BadPath(ConfigOrigin origin, String path, String message, Throwable cause) {
/* 198 */       super(origin, (path != null) ? ("Invalid path '" + path + "': " + message) : message, cause);
/*     */     }
/*     */     
/*     */     public BadPath(ConfigOrigin origin, String path, String message) {
/* 204 */       this(origin, path, message, null);
/*     */     }
/*     */     
/*     */     public BadPath(String path, String message, Throwable cause) {
/* 208 */       super((path != null) ? ("Invalid path '" + path + "': " + message) : message, cause);
/*     */     }
/*     */     
/*     */     public BadPath(String path, String message) {
/* 213 */       this(path, message, (Throwable)null);
/*     */     }
/*     */     
/*     */     public BadPath(ConfigOrigin origin, String message) {
/* 217 */       this(origin, (String)null, message);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BugOrBroken extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public BugOrBroken(String message, Throwable cause) {
/* 232 */       super(message, cause);
/*     */     }
/*     */     
/*     */     public BugOrBroken(String message) {
/* 236 */       this(message, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class IO extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public IO(ConfigOrigin origin, String message, Throwable cause) {
/* 248 */       super(origin, message, cause);
/*     */     }
/*     */     
/*     */     public IO(ConfigOrigin origin, String message) {
/* 252 */       this(origin, message, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Parse extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Parse(ConfigOrigin origin, String message, Throwable cause) {
/* 264 */       super(origin, message, cause);
/*     */     }
/*     */     
/*     */     public Parse(ConfigOrigin origin, String message) {
/* 268 */       this(origin, message, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class UnresolvedSubstitution extends Parse {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public UnresolvedSubstitution(ConfigOrigin origin, String detail, Throwable cause) {
/* 280 */       super(origin, "Could not resolve substitution to a value: " + detail, cause);
/*     */     }
/*     */     
/*     */     public UnresolvedSubstitution(ConfigOrigin origin, String detail) {
/* 284 */       this(origin, detail, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class NotResolved extends BugOrBroken {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public NotResolved(String message, Throwable cause) {
/* 300 */       super(message, cause);
/*     */     }
/*     */     
/*     */     public NotResolved(String message) {
/* 304 */       this(message, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ValidationProblem {
/*     */     private final String path;
/*     */     
/*     */     private final ConfigOrigin origin;
/*     */     
/*     */     private final String problem;
/*     */     
/*     */     public ValidationProblem(String path, ConfigOrigin origin, String problem) {
/* 320 */       this.path = path;
/* 321 */       this.origin = origin;
/* 322 */       this.problem = problem;
/*     */     }
/*     */     
/*     */     public String path() {
/* 327 */       return this.path;
/*     */     }
/*     */     
/*     */     public ConfigOrigin origin() {
/* 335 */       return this.origin;
/*     */     }
/*     */     
/*     */     public String problem() {
/* 340 */       return this.problem;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 345 */       return "ValidationProblem(" + this.path + "," + this.origin + "," + this.problem + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ValidationFailed extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private final Iterable<ConfigException.ValidationProblem> problems;
/*     */     
/*     */     public ValidationFailed(Iterable<ConfigException.ValidationProblem> problems) {
/* 361 */       super(makeMessage(problems), (Throwable)null);
/* 362 */       this.problems = problems;
/*     */     }
/*     */     
/*     */     public Iterable<ConfigException.ValidationProblem> problems() {
/* 366 */       return this.problems;
/*     */     }
/*     */     
/*     */     private static String makeMessage(Iterable<ConfigException.ValidationProblem> problems) {
/* 370 */       StringBuilder sb = new StringBuilder();
/* 371 */       for (ConfigException.ValidationProblem p : problems) {
/* 372 */         sb.append(p.origin().description());
/* 373 */         sb.append(": ");
/* 374 */         sb.append(p.path());
/* 375 */         sb.append(": ");
/* 376 */         sb.append(p.problem());
/* 377 */         sb.append(", ");
/*     */       } 
/* 379 */       if (sb.length() == 0)
/* 380 */         throw new ConfigException.BugOrBroken("ValidationFailed must have a non-empty list of problems"); 
/* 382 */       sb.setLength(sb.length() - 2);
/* 384 */       return sb.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Generic extends ConfigException {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Generic(String message, Throwable cause) {
/* 395 */       super(message, cause);
/*     */     }
/*     */     
/*     */     public Generic(String message) {
/* 399 */       this(message, null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */