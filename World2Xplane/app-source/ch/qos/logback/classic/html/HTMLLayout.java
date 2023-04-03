/*     */ package ch.qos.logback.classic.html;
/*     */ 
/*     */ import ch.qos.logback.classic.PatternLayout;
/*     */ import ch.qos.logback.classic.pattern.MDCConverter;
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.core.CoreConstants;
/*     */ import ch.qos.logback.core.html.HTMLLayoutBase;
/*     */ import ch.qos.logback.core.html.IThrowableRenderer;
/*     */ import ch.qos.logback.core.pattern.Converter;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class HTMLLayout extends HTMLLayoutBase<ILoggingEvent> {
/*     */   static final String DEFAULT_CONVERSION_PATTERN = "%date%thread%level%logger%mdc%msg";
/*     */   
/*  55 */   IThrowableRenderer<ILoggingEvent> throwableRenderer = new DefaultThrowableRenderer();
/*     */   
/*     */   public void start() {
/*  61 */     int errorCount = 0;
/*  62 */     if (this.throwableRenderer == null) {
/*  63 */       addError("ThrowableRender cannot be null.");
/*  64 */       errorCount++;
/*     */     } 
/*  66 */     if (errorCount == 0)
/*  67 */       super.start(); 
/*     */   }
/*     */   
/*     */   protected Map<String, String> getDefaultConverterMap() {
/*  72 */     return PatternLayout.defaultConverterMap;
/*     */   }
/*     */   
/*     */   public String doLayout(ILoggingEvent event) {
/*  76 */     StringBuilder buf = new StringBuilder();
/*  77 */     startNewTableIfLimitReached(buf);
/*  79 */     boolean odd = true;
/*  80 */     if ((this.counter++ & 0x1L) == 0L)
/*  81 */       odd = false; 
/*  84 */     String level = event.getLevel().toString().toLowerCase();
/*  86 */     buf.append(CoreConstants.LINE_SEPARATOR);
/*  87 */     buf.append("<tr class=\"");
/*  88 */     buf.append(level);
/*  89 */     if (odd) {
/*  90 */       buf.append(" odd\">");
/*     */     } else {
/*  92 */       buf.append(" even\">");
/*     */     } 
/*  94 */     buf.append(CoreConstants.LINE_SEPARATOR);
/*  96 */     Converter<ILoggingEvent> c = this.head;
/*  97 */     while (c != null) {
/*  98 */       appendEventToBuffer(buf, c, event);
/*  99 */       c = c.getNext();
/*     */     } 
/* 101 */     buf.append("</tr>");
/* 102 */     buf.append(CoreConstants.LINE_SEPARATOR);
/* 104 */     if (event.getThrowableProxy() != null)
/* 105 */       this.throwableRenderer.render(buf, event); 
/* 107 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private void appendEventToBuffer(StringBuilder buf, Converter<ILoggingEvent> c, ILoggingEvent event) {
/* 112 */     buf.append("<td class=\"");
/* 113 */     buf.append(computeConverterName(c));
/* 114 */     buf.append("\">");
/* 115 */     c.write(buf, event);
/* 116 */     buf.append("</td>");
/* 117 */     buf.append(CoreConstants.LINE_SEPARATOR);
/*     */   }
/*     */   
/*     */   public IThrowableRenderer getThrowableRenderer() {
/* 121 */     return this.throwableRenderer;
/*     */   }
/*     */   
/*     */   public void setThrowableRenderer(IThrowableRenderer<ILoggingEvent> throwableRenderer) {
/* 125 */     this.throwableRenderer = throwableRenderer;
/*     */   }
/*     */   
/*     */   protected String computeConverterName(Converter c) {
/* 130 */     if (c instanceof MDCConverter) {
/* 131 */       MDCConverter mc = (MDCConverter)c;
/* 132 */       String key = mc.getFirstOption();
/* 133 */       if (key != null)
/* 134 */         return key; 
/* 136 */       return "MDC";
/*     */     } 
/* 139 */     return super.computeConverterName(c);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\html\HTMLLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */