/*     */ package ch.qos.logback.classic.pattern;
/*     */ 
/*     */ import ch.qos.logback.classic.spi.CallerData;
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.CoreConstants;
/*     */ import ch.qos.logback.core.boolex.EvaluationException;
/*     */ import ch.qos.logback.core.boolex.EventEvaluator;
/*     */ import ch.qos.logback.core.status.ErrorStatus;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CallerDataConverter extends ClassicConverter {
/*     */   public static final String DEFAULT_CALLER_LINE_PREFIX = "Caller+";
/*     */   
/*  37 */   int depth = 5;
/*     */   
/*  38 */   List<EventEvaluator<ILoggingEvent>> evaluatorList = null;
/*     */   
/*  41 */   final int MAX_ERROR_COUNT = 4;
/*     */   
/*  42 */   int errorCount = 0;
/*     */   
/*     */   public void start() {
/*  46 */     String depthStr = getFirstOption();
/*  47 */     if (depthStr == null)
/*     */       return; 
/*     */     try {
/*  52 */       this.depth = Integer.parseInt(depthStr);
/*  53 */     } catch (NumberFormatException nfe) {
/*  54 */       addError("Failed to parse depth option [" + depthStr + "]", nfe);
/*     */     } 
/*  57 */     List<String> optionList = getOptionList();
/*  59 */     if (optionList != null && optionList.size() > 1) {
/*  60 */       int optionListSize = optionList.size();
/*  61 */       for (int i = 1; i < optionListSize; i++) {
/*  62 */         String evaluatorStr = optionList.get(i);
/*  63 */         Context context = getContext();
/*  64 */         if (context != null) {
/*  65 */           Map evaluatorMap = (Map)context.getObject("EVALUATOR_MAP");
/*  67 */           EventEvaluator<ILoggingEvent> ee = (EventEvaluator<ILoggingEvent>)evaluatorMap.get(evaluatorStr);
/*  69 */           if (ee != null)
/*  70 */             addEvaluator(ee); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addEvaluator(EventEvaluator<ILoggingEvent> ee) {
/*  78 */     if (this.evaluatorList == null)
/*  79 */       this.evaluatorList = new ArrayList<EventEvaluator<ILoggingEvent>>(); 
/*  81 */     this.evaluatorList.add(ee);
/*     */   }
/*     */   
/*     */   public String convert(ILoggingEvent le) {
/*  85 */     StringBuilder buf = new StringBuilder();
/*  87 */     if (this.evaluatorList != null) {
/*  88 */       boolean printCallerData = false;
/*  89 */       for (int i = 0; i < this.evaluatorList.size(); i++) {
/*  90 */         EventEvaluator<ILoggingEvent> ee = this.evaluatorList.get(i);
/*     */         try {
/*  92 */           if (ee.evaluate(le)) {
/*  93 */             printCallerData = true;
/*     */             break;
/*     */           } 
/*  96 */         } catch (EvaluationException eex) {
/*  97 */           this.errorCount++;
/*  98 */           if (this.errorCount < 4) {
/*  99 */             addError("Exception thrown for evaluator named [" + ee.getName() + "]", (Throwable)eex);
/* 101 */           } else if (this.errorCount == 4) {
/* 102 */             ErrorStatus errorStatus = new ErrorStatus("Exception thrown for evaluator named [" + ee.getName() + "].", this, (Throwable)eex);
/* 105 */             errorStatus.add((Status)new ErrorStatus("This was the last warning about this evaluator's errors.We don't want the StatusManager to get flooded.", this));
/* 108 */             addStatus((Status)errorStatus);
/*     */           } 
/*     */         } 
/*     */       } 
/* 114 */       if (!printCallerData)
/* 115 */         return ""; 
/*     */     } 
/* 119 */     StackTraceElement[] cda = le.getCallerData();
/* 120 */     if (cda != null && cda.length > 0) {
/* 121 */       int limit = (this.depth < cda.length) ? this.depth : cda.length;
/* 123 */       for (int i = 0; i < limit; i++) {
/* 124 */         buf.append(getCallerLinePrefix());
/* 125 */         buf.append(i);
/* 126 */         buf.append("\t at ");
/* 127 */         buf.append(cda[i]);
/* 128 */         buf.append(CoreConstants.LINE_SEPARATOR);
/*     */       } 
/* 130 */       return buf.toString();
/*     */     } 
/* 132 */     return CallerData.CALLER_DATA_NA;
/*     */   }
/*     */   
/*     */   protected String getCallerLinePrefix() {
/* 137 */     return "Caller+";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\CallerDataConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */