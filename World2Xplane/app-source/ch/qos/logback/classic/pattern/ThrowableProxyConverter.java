/*     */ package ch.qos.logback.classic.pattern;
/*     */ 
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.classic.spi.IThrowableProxy;
/*     */ import ch.qos.logback.classic.spi.StackTraceElementProxy;
/*     */ import ch.qos.logback.classic.spi.ThrowableProxyUtil;
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
/*     */ public class ThrowableProxyConverter extends ThrowableHandlingConverter {
/*     */   int lengthOption;
/*     */   
/*  38 */   List<EventEvaluator<ILoggingEvent>> evaluatorList = null;
/*     */   
/*  40 */   int errorCount = 0;
/*     */   
/*     */   public void start() {
/*  45 */     String lengthStr = getFirstOption();
/*  47 */     if (lengthStr == null) {
/*  48 */       this.lengthOption = Integer.MAX_VALUE;
/*     */     } else {
/*  50 */       lengthStr = lengthStr.toLowerCase();
/*  51 */       if ("full".equals(lengthStr)) {
/*  52 */         this.lengthOption = Integer.MAX_VALUE;
/*  53 */       } else if ("short".equals(lengthStr)) {
/*  54 */         this.lengthOption = 2;
/*     */       } else {
/*     */         try {
/*  58 */           this.lengthOption = Integer.parseInt(lengthStr) + 1;
/*  59 */         } catch (NumberFormatException nfe) {
/*  60 */           addError("Could not parse [" + lengthStr + "] as an integer");
/*  61 */           this.lengthOption = Integer.MAX_VALUE;
/*     */         } 
/*     */       } 
/*     */     } 
/*  66 */     List<String> optionList = getOptionList();
/*  68 */     if (optionList != null && optionList.size() > 1) {
/*  69 */       int optionListSize = optionList.size();
/*  70 */       for (int i = 1; i < optionListSize; i++) {
/*  71 */         String evaluatorStr = optionList.get(i);
/*  72 */         Context context = getContext();
/*  73 */         Map evaluatorMap = (Map)context.getObject("EVALUATOR_MAP");
/*  74 */         EventEvaluator<ILoggingEvent> ee = (EventEvaluator<ILoggingEvent>)evaluatorMap.get(evaluatorStr);
/*  76 */         addEvaluator(ee);
/*     */       } 
/*     */     } 
/*  79 */     super.start();
/*     */   }
/*     */   
/*     */   private void addEvaluator(EventEvaluator<ILoggingEvent> ee) {
/*  83 */     if (this.evaluatorList == null)
/*  84 */       this.evaluatorList = new ArrayList<EventEvaluator<ILoggingEvent>>(); 
/*  86 */     this.evaluatorList.add(ee);
/*     */   }
/*     */   
/*     */   public void stop() {
/*  90 */     this.evaluatorList = null;
/*  91 */     super.stop();
/*     */   }
/*     */   
/*     */   protected void extraData(StringBuilder builder, StackTraceElementProxy step) {}
/*     */   
/*     */   public String convert(ILoggingEvent event) {
/* 100 */     IThrowableProxy tp = event.getThrowableProxy();
/* 101 */     if (tp == null)
/* 102 */       return ""; 
/* 106 */     if (this.evaluatorList != null) {
/* 107 */       boolean printStack = true;
/* 108 */       for (int i = 0; i < this.evaluatorList.size(); i++) {
/* 109 */         EventEvaluator<ILoggingEvent> ee = this.evaluatorList.get(i);
/*     */         try {
/* 111 */           if (ee.evaluate(event)) {
/* 112 */             printStack = false;
/*     */             break;
/*     */           } 
/* 115 */         } catch (EvaluationException eex) {
/* 116 */           this.errorCount++;
/* 117 */           if (this.errorCount < 4) {
/* 118 */             addError("Exception thrown for evaluator named [" + ee.getName() + "]", (Throwable)eex);
/* 120 */           } else if (this.errorCount == 4) {
/* 121 */             ErrorStatus errorStatus = new ErrorStatus("Exception thrown for evaluator named [" + ee.getName() + "].", this, (Throwable)eex);
/* 124 */             errorStatus.add((Status)new ErrorStatus("This was the last warning about this evaluator's errors.We don't want the StatusManager to get flooded.", this));
/* 127 */             addStatus((Status)errorStatus);
/*     */           } 
/*     */         } 
/*     */       } 
/* 132 */       if (!printStack)
/* 133 */         return ""; 
/*     */     } 
/* 137 */     return throwableProxyToString(tp);
/*     */   }
/*     */   
/*     */   protected String throwableProxyToString(IThrowableProxy tp) {
/* 141 */     StringBuilder buf = new StringBuilder(32);
/* 142 */     IThrowableProxy currentThrowable = tp;
/* 143 */     while (currentThrowable != null) {
/* 144 */       subjoinThrowableProxy(buf, currentThrowable);
/* 145 */       currentThrowable = currentThrowable.getCause();
/*     */     } 
/* 147 */     return buf.toString();
/*     */   }
/*     */   
/*     */   void subjoinThrowableProxy(StringBuilder buf, IThrowableProxy tp) {
/* 151 */     ThrowableProxyUtil.subjoinFirstLine(buf, tp);
/* 152 */     buf.append(CoreConstants.LINE_SEPARATOR);
/* 153 */     StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
/* 154 */     int commonFrames = tp.getCommonFrames();
/* 156 */     boolean unrestrictedPrinting = (this.lengthOption > stepArray.length);
/* 159 */     int maxIndex = unrestrictedPrinting ? stepArray.length : this.lengthOption;
/* 160 */     if (commonFrames > 0 && unrestrictedPrinting)
/* 161 */       maxIndex -= commonFrames; 
/* 164 */     for (int i = 0; i < maxIndex; i++) {
/* 165 */       String string = stepArray[i].toString();
/* 166 */       buf.append('\t');
/* 167 */       buf.append(string);
/* 168 */       extraData(buf, stepArray[i]);
/* 169 */       buf.append(CoreConstants.LINE_SEPARATOR);
/*     */     } 
/* 172 */     if (commonFrames > 0 && unrestrictedPrinting)
/* 173 */       buf.append("\t... ").append(tp.getCommonFrames()).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\ThrowableProxyConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */