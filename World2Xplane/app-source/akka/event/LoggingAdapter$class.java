/*      */ package akka.event;
/*      */ 
/*      */ import scala.Array$;
/*      */ import scala.Function1;
/*      */ import scala.MatchError;
/*      */ import scala.Predef$;
/*      */ import scala.collection.Seq;
/*      */ import scala.collection.immutable.Map;
/*      */ import scala.reflect.ClassTag$;
/*      */ import scala.runtime.BoxedUnit;
/*      */ import scala.runtime.ScalaRunTime$;
/*      */ 
/*      */ public abstract class LoggingAdapter$class {
/*      */   public static void $init$(LoggingAdapter $this) {}
/*      */   
/*      */   public static Map mdc(LoggingAdapter $this) {
/*  843 */     return Logging$.MODULE$.emptyMDC();
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, Throwable cause, String message) {
/*  872 */     if ($this.isErrorEnabled())
/*  872 */       $this.notifyError(cause, message); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, Throwable cause, String template, Object arg1) {
/*  877 */     if ($this.isErrorEnabled())
/*  877 */       $this.notifyError(cause, format1($this, template, arg1)); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, Throwable cause, String template, Object arg1, Object arg2) {
/*  882 */     if ($this.isErrorEnabled())
/*  882 */       $this.notifyError(cause, $this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2 }))); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, Throwable cause, String template, Object arg1, Object arg2, Object arg3) {
/*  887 */     if ($this.isErrorEnabled())
/*  887 */       $this.notifyError(cause, $this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3 }))); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, Throwable cause, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/*  892 */     if ($this.isErrorEnabled())
/*  892 */       $this.notifyError(cause, $this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3, arg4 }))); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, String message) {
/*  898 */     if ($this.isErrorEnabled())
/*  898 */       $this.notifyError(message); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, String template, Object arg1) {
/*  903 */     if ($this.isErrorEnabled())
/*  903 */       $this.notifyError(format1($this, template, arg1)); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, String template, Object arg1, Object arg2) {
/*  908 */     if ($this.isErrorEnabled())
/*  908 */       $this.notifyError($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2 }))); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, String template, Object arg1, Object arg2, Object arg3) {
/*  913 */     if ($this.isErrorEnabled())
/*  913 */       $this.notifyError($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3 }))); 
/*      */   }
/*      */   
/*      */   public static void error(LoggingAdapter $this, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/*  918 */     if ($this.isErrorEnabled())
/*  918 */       $this.notifyError($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3, arg4 }))); 
/*      */   }
/*      */   
/*      */   public static void warning(LoggingAdapter $this, String message) {
/*  924 */     if ($this.isWarningEnabled())
/*  924 */       $this.notifyWarning(message); 
/*      */   }
/*      */   
/*      */   public static void warning(LoggingAdapter $this, String template, Object arg1) {
/*  929 */     if ($this.isWarningEnabled())
/*  929 */       $this.notifyWarning(format1($this, template, arg1)); 
/*      */   }
/*      */   
/*      */   public static void warning(LoggingAdapter $this, String template, Object arg1, Object arg2) {
/*  934 */     if ($this.isWarningEnabled())
/*  934 */       $this.notifyWarning($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2 }))); 
/*      */   }
/*      */   
/*      */   public static void warning(LoggingAdapter $this, String template, Object arg1, Object arg2, Object arg3) {
/*  939 */     if ($this.isWarningEnabled())
/*  939 */       $this.notifyWarning($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3 }))); 
/*      */   }
/*      */   
/*      */   public static void warning(LoggingAdapter $this, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/*  944 */     if ($this.isWarningEnabled())
/*  944 */       $this.notifyWarning($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3, arg4 }))); 
/*      */   }
/*      */   
/*      */   public static void info(LoggingAdapter $this, String message) {
/*  950 */     if ($this.isInfoEnabled())
/*  950 */       $this.notifyInfo(message); 
/*      */   }
/*      */   
/*      */   public static void info(LoggingAdapter $this, String template, Object arg1) {
/*  955 */     if ($this.isInfoEnabled())
/*  955 */       $this.notifyInfo(format1($this, template, arg1)); 
/*      */   }
/*      */   
/*      */   public static void info(LoggingAdapter $this, String template, Object arg1, Object arg2) {
/*  960 */     if ($this.isInfoEnabled())
/*  960 */       $this.notifyInfo($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2 }))); 
/*      */   }
/*      */   
/*      */   public static void info(LoggingAdapter $this, String template, Object arg1, Object arg2, Object arg3) {
/*  965 */     if ($this.isInfoEnabled())
/*  965 */       $this.notifyInfo($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3 }))); 
/*      */   }
/*      */   
/*      */   public static void info(LoggingAdapter $this, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/*  970 */     if ($this.isInfoEnabled())
/*  970 */       $this.notifyInfo($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3, arg4 }))); 
/*      */   }
/*      */   
/*      */   public static void debug(LoggingAdapter $this, String message) {
/*  976 */     if ($this.isDebugEnabled())
/*  976 */       $this.notifyDebug(message); 
/*      */   }
/*      */   
/*      */   public static void debug(LoggingAdapter $this, String template, Object arg1) {
/*  981 */     if ($this.isDebugEnabled())
/*  981 */       $this.notifyDebug(format1($this, template, arg1)); 
/*      */   }
/*      */   
/*      */   public static void debug(LoggingAdapter $this, String template, Object arg1, Object arg2) {
/*  986 */     if ($this.isDebugEnabled())
/*  986 */       $this.notifyDebug($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2 }))); 
/*      */   }
/*      */   
/*      */   public static void debug(LoggingAdapter $this, String template, Object arg1, Object arg2, Object arg3) {
/*  991 */     if ($this.isDebugEnabled())
/*  991 */       $this.notifyDebug($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3 }))); 
/*      */   }
/*      */   
/*      */   public static void debug(LoggingAdapter $this, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/*  996 */     if ($this.isDebugEnabled())
/*  996 */       $this.notifyDebug($this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3, arg4 }))); 
/*      */   }
/*      */   
/*      */   public static void log(LoggingAdapter $this, int level, String message) {
/* 1001 */     if ($this.isEnabled(level))
/* 1001 */       $this.notifyLog(level, message); 
/*      */   }
/*      */   
/*      */   public static void log(LoggingAdapter $this, int level, String template, Object arg1) {
/* 1005 */     if ($this.isEnabled(level))
/* 1005 */       $this.notifyLog(level, format1($this, template, arg1)); 
/*      */   }
/*      */   
/*      */   public static void log(LoggingAdapter $this, int level, String template, Object arg1, Object arg2) {
/* 1009 */     if ($this.isEnabled(level))
/* 1009 */       $this.notifyLog(level, $this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2 }))); 
/*      */   }
/*      */   
/*      */   public static void log(LoggingAdapter $this, int level, String template, Object arg1, Object arg2, Object arg3) {
/* 1013 */     if ($this.isEnabled(level))
/* 1013 */       $this.notifyLog(level, $this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3 }))); 
/*      */   }
/*      */   
/*      */   public static void log(LoggingAdapter $this, int level, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1017 */     if ($this.isEnabled(level))
/* 1017 */       $this.notifyLog(level, $this.format(template, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { arg1, arg2, arg3, arg4 }))); 
/*      */   }
/*      */   
/*      */   public static final boolean isEnabled(LoggingAdapter $this, int level) {
/* 1022 */     int i = level;
/* 1023 */     if (Logging$.MODULE$.ErrorLevel() == i) {
/* 1023 */       boolean bool = $this.isErrorEnabled();
/* 1024 */     } else if (Logging$.MODULE$.WarningLevel() == i) {
/* 1024 */       boolean bool = $this.isWarningEnabled();
/* 1025 */     } else if (Logging$.MODULE$.InfoLevel() == i) {
/* 1025 */       boolean bool = $this.isInfoEnabled();
/*      */     } else {
/* 1026 */       if (Logging$.MODULE$.DebugLevel() == i)
/* 1026 */         return $this.isDebugEnabled(); 
/*      */       throw new MatchError(new Logging.LogLevel(i));
/*      */     } 
/*      */     return SYNTHETIC_LOCAL_VARIABLE_3;
/*      */   }
/*      */   
/*      */   public static final void notifyLog(LoggingAdapter $this, int level, String message) {
/* 1029 */     int i = level;
/* 1030 */     if (Logging$.MODULE$.ErrorLevel() == i) {
/* 1030 */       $this.notifyError(message);
/* 1030 */       BoxedUnit boxedUnit = $this.isErrorEnabled() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/* 1031 */     } else if (Logging$.MODULE$.WarningLevel() == i) {
/* 1031 */       $this.notifyWarning(message);
/* 1031 */       BoxedUnit boxedUnit = $this.isWarningEnabled() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/* 1032 */     } else if (Logging$.MODULE$.InfoLevel() == i) {
/* 1032 */       $this.notifyInfo(message);
/* 1032 */       BoxedUnit boxedUnit = $this.isInfoEnabled() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*      */     } else {
/* 1033 */       if (Logging$.MODULE$.DebugLevel() == i) {
/* 1033 */         $this.notifyDebug(message);
/* 1033 */         BoxedUnit boxedUnit = $this.isDebugEnabled() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*      */         return;
/*      */       } 
/*      */       throw new MatchError(new Logging.LogLevel(i));
/*      */     } 
/*      */   }
/*      */   
/*      */   private static String format1(LoggingAdapter $this, String t, Object arg) {
/*      */     String str;
/* 1036 */     Object object = arg;
/* 1037 */     if (ScalaRunTime$.MODULE$.isArray(object, 1)) {
/* 1037 */       Object object1 = object;
/* 1037 */       if (!object1.getClass().getComponentType().isPrimitive())
/* 1037 */         return $this.format(t, (Seq<Object>)Predef$.MODULE$.genericWrapArray(object1)); 
/*      */     } 
/* 1038 */     if (ScalaRunTime$.MODULE$.isArray(object, 1)) {
/* 1038 */       Object object1 = object;
/* 1038 */       str = $this.format(t, (Seq<Object>)Predef$.MODULE$.genericWrapArray(Predef$.MODULE$.genericArrayOps(object1).map((Function1)new LoggingAdapter$$anonfun$format1$1($this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.AnyRef()))));
/*      */     } else {
/* 1039 */       str = $this.format(t, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { object }));
/*      */     } 
/*      */     return str;
/*      */   }
/*      */   
/*      */   public static String format(LoggingAdapter $this, String t, Seq arg) {
/* 1043 */     StringBuilder sb = new StringBuilder(64);
/* 1044 */     int p = 0;
/* 1045 */     String rest = t;
/* 1046 */     while (p < arg.length()) {
/* 1047 */       int index = rest.indexOf("{}");
/* 1048 */       if (index == -1) {
/* 1049 */         sb.append(rest).append(" WARNING arguments left: ").append(arg.length() - p);
/* 1050 */         rest = "";
/* 1051 */         p = arg.length();
/*      */         continue;
/*      */       } 
/* 1053 */       sb.append(rest.substring(0, index)).append(arg.apply(p));
/* 1054 */       rest = rest.substring(index + 2);
/* 1055 */       p++;
/*      */     } 
/* 1058 */     return sb.append(rest).toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LoggingAdapter$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */