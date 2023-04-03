/*     */ package ch.qos.logback.classic.spi;
/*     */ 
/*     */ import ch.qos.logback.core.CoreConstants;
/*     */ 
/*     */ public class ThrowableProxyUtil {
/*     */   public static final int REGULAR_EXCEPTION_INDENT = 1;
/*     */   
/*     */   public static final int SUPPRESSED_EXCEPTION_INDENT = 2;
/*     */   
/*     */   public static void build(ThrowableProxy nestedTP, Throwable nestedThrowable, ThrowableProxy parentTP) {
/*  32 */     StackTraceElement[] nestedSTE = nestedThrowable.getStackTrace();
/*  34 */     int commonFramesCount = -1;
/*  35 */     if (parentTP != null)
/*  36 */       commonFramesCount = findNumberOfCommonFrames(nestedSTE, parentTP.getStackTraceElementProxyArray()); 
/*  40 */     nestedTP.commonFrames = commonFramesCount;
/*  41 */     nestedTP.stackTraceElementProxyArray = steArrayToStepArray(nestedSTE);
/*     */   }
/*     */   
/*     */   static StackTraceElementProxy[] steArrayToStepArray(StackTraceElement[] stea) {
/*  45 */     if (stea == null)
/*  46 */       return new StackTraceElementProxy[0]; 
/*  48 */     StackTraceElementProxy[] stepa = new StackTraceElementProxy[stea.length];
/*  49 */     for (int i = 0; i < stepa.length; i++)
/*  50 */       stepa[i] = new StackTraceElementProxy(stea[i]); 
/*  52 */     return stepa;
/*     */   }
/*     */   
/*     */   static int findNumberOfCommonFrames(StackTraceElement[] steArray, StackTraceElementProxy[] parentSTEPArray) {
/*  57 */     if (parentSTEPArray == null || steArray == null)
/*  58 */       return 0; 
/*  61 */     int steIndex = steArray.length - 1;
/*  62 */     int parentIndex = parentSTEPArray.length - 1;
/*  63 */     int count = 0;
/*  64 */     while (steIndex >= 0 && parentIndex >= 0) {
/*  65 */       StackTraceElement ste = steArray[steIndex];
/*  66 */       StackTraceElement otherSte = (parentSTEPArray[parentIndex]).ste;
/*  67 */       if (ste.equals(otherSte)) {
/*  68 */         count++;
/*  72 */         steIndex--;
/*  73 */         parentIndex--;
/*     */       } 
/*     */     } 
/*  75 */     return count;
/*     */   }
/*     */   
/*     */   public static String asString(IThrowableProxy tp) {
/*  79 */     StringBuilder sb = new StringBuilder();
/*  81 */     recursiveAppend(sb, null, 1, tp);
/*  83 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private static void recursiveAppend(StringBuilder sb, String prefix, int indent, IThrowableProxy tp) {
/*  87 */     if (tp == null)
/*     */       return; 
/*  89 */     subjoinFirstLine(sb, prefix, tp);
/*  90 */     sb.append(CoreConstants.LINE_SEPARATOR);
/*  91 */     subjoinSTEPArray(sb, indent, tp);
/*  92 */     IThrowableProxy[] suppressed = tp.getSuppressed();
/*  93 */     if (suppressed != null)
/*  94 */       for (IThrowableProxy current : suppressed)
/*  95 */         recursiveAppend(sb, "\tSuppressed: ", 2, current);  
/*  98 */     recursiveAppend(sb, "Caused by: ", 1, tp.getCause());
/*     */   }
/*     */   
/*     */   private static void subjoinFirstLine(StringBuilder buf, String prefix, IThrowableProxy tp) {
/* 102 */     if (prefix != null)
/* 103 */       buf.append(prefix); 
/* 105 */     subjoinExceptionMessage(buf, tp);
/*     */   }
/*     */   
/*     */   public static void subjoinPackagingData(StringBuilder builder, StackTraceElementProxy step) {
/* 109 */     if (step != null) {
/* 110 */       ClassPackagingData cpd = step.getClassPackagingData();
/* 111 */       if (cpd != null) {
/* 112 */         if (!cpd.isExact()) {
/* 113 */           builder.append(" ~[");
/*     */         } else {
/* 115 */           builder.append(" [");
/*     */         } 
/* 118 */         builder.append(cpd.getCodeLocation()).append(':').append(cpd.getVersion()).append(']');
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void subjoinSTEP(StringBuilder sb, StackTraceElementProxy step) {
/* 125 */     sb.append(step.toString());
/* 126 */     subjoinPackagingData(sb, step);
/*     */   }
/*     */   
/*     */   public static void subjoinSTEPArray(StringBuilder sb, IThrowableProxy tp) {
/* 136 */     subjoinSTEPArray(sb, 1, tp);
/*     */   }
/*     */   
/*     */   public static void subjoinSTEPArray(StringBuilder sb, int indentLevel, IThrowableProxy tp) {
/* 145 */     StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
/* 146 */     int commonFrames = tp.getCommonFrames();
/* 148 */     for (int i = 0; i < stepArray.length - commonFrames; i++) {
/* 149 */       StackTraceElementProxy step = stepArray[i];
/* 150 */       for (int j = 0; j < indentLevel; j++)
/* 151 */         sb.append('\t'); 
/* 153 */       subjoinSTEP(sb, step);
/* 154 */       sb.append(CoreConstants.LINE_SEPARATOR);
/*     */     } 
/* 157 */     if (commonFrames > 0) {
/* 158 */       for (int j = 0; j < indentLevel; j++)
/* 159 */         sb.append('\t'); 
/* 161 */       sb.append("... ").append(commonFrames).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void subjoinFirstLine(StringBuilder buf, IThrowableProxy tp) {
/* 168 */     int commonFrames = tp.getCommonFrames();
/* 169 */     if (commonFrames > 0)
/* 170 */       buf.append("Caused by: "); 
/* 172 */     subjoinExceptionMessage(buf, tp);
/*     */   }
/*     */   
/*     */   public static void subjoinFirstLineRootCauseFirst(StringBuilder buf, IThrowableProxy tp) {
/* 176 */     if (tp.getCause() != null)
/* 177 */       buf.append("Wrapped by: "); 
/* 179 */     subjoinExceptionMessage(buf, tp);
/*     */   }
/*     */   
/*     */   private static void subjoinExceptionMessage(StringBuilder buf, IThrowableProxy tp) {
/* 183 */     buf.append(tp.getClassName()).append(": ").append(tp.getMessage());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\spi\ThrowableProxyUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */