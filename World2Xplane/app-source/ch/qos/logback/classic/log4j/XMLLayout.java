/*     */ package ch.qos.logback.classic.log4j;
/*     */ 
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.classic.spi.IThrowableProxy;
/*     */ import ch.qos.logback.classic.spi.StackTraceElementProxy;
/*     */ import ch.qos.logback.core.LayoutBase;
/*     */ import ch.qos.logback.core.helpers.Transform;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class XMLLayout extends LayoutBase<ILoggingEvent> {
/*  40 */   private final int DEFAULT_SIZE = 256;
/*     */   
/*  41 */   private final int UPPER_LIMIT = 2048;
/*     */   
/*  43 */   private StringBuilder buf = new StringBuilder(256);
/*     */   
/*     */   private boolean locationInfo = false;
/*     */   
/*     */   private boolean properties = false;
/*     */   
/*     */   public void start() {
/*  49 */     super.start();
/*     */   }
/*     */   
/*     */   public void setLocationInfo(boolean flag) {
/*  63 */     this.locationInfo = flag;
/*     */   }
/*     */   
/*     */   public boolean getLocationInfo() {
/*  70 */     return this.locationInfo;
/*     */   }
/*     */   
/*     */   public void setProperties(boolean flag) {
/*  81 */     this.properties = flag;
/*     */   }
/*     */   
/*     */   public boolean getProperties() {
/*  91 */     return this.properties;
/*     */   }
/*     */   
/*     */   public String doLayout(ILoggingEvent event) {
/* 101 */     if (this.buf.capacity() > 2048) {
/* 102 */       this.buf = new StringBuilder(256);
/*     */     } else {
/* 104 */       this.buf.setLength(0);
/*     */     } 
/* 109 */     this.buf.append("<log4j:event logger=\"");
/* 110 */     this.buf.append(event.getLoggerName());
/* 111 */     this.buf.append("\"\r\n");
/* 112 */     this.buf.append("             timestamp=\"");
/* 113 */     this.buf.append(event.getTimeStamp());
/* 114 */     this.buf.append("\" level=\"");
/* 115 */     this.buf.append(event.getLevel());
/* 116 */     this.buf.append("\" thread=\"");
/* 117 */     this.buf.append(event.getThreadName());
/* 118 */     this.buf.append("\">\r\n");
/* 120 */     this.buf.append("  <log4j:message><![CDATA[");
/* 123 */     Transform.appendEscapingCDATA(this.buf, event.getFormattedMessage());
/* 124 */     this.buf.append("]]></log4j:message>\r\n");
/* 130 */     IThrowableProxy tp = event.getThrowableProxy();
/* 131 */     if (tp != null) {
/* 132 */       StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
/* 133 */       this.buf.append("  <log4j:throwable><![CDATA[");
/* 134 */       for (StackTraceElementProxy step : stepArray) {
/* 135 */         this.buf.append('\t');
/* 136 */         this.buf.append(step.toString());
/* 137 */         this.buf.append("\r\n");
/*     */       } 
/* 139 */       this.buf.append("]]></log4j:throwable>\r\n");
/*     */     } 
/* 142 */     if (this.locationInfo) {
/* 143 */       StackTraceElement[] callerDataArray = event.getCallerData();
/* 144 */       if (callerDataArray != null && callerDataArray.length > 0) {
/* 145 */         StackTraceElement immediateCallerData = callerDataArray[0];
/* 146 */         this.buf.append("  <log4j:locationInfo class=\"");
/* 147 */         this.buf.append(immediateCallerData.getClassName());
/* 148 */         this.buf.append("\"\r\n");
/* 149 */         this.buf.append("                      method=\"");
/* 150 */         this.buf.append(Transform.escapeTags(immediateCallerData.getMethodName()));
/* 151 */         this.buf.append("\" file=\"");
/* 152 */         this.buf.append(immediateCallerData.getFileName());
/* 153 */         this.buf.append("\" line=\"");
/* 154 */         this.buf.append(immediateCallerData.getLineNumber());
/* 155 */         this.buf.append("\"/>\r\n");
/*     */       } 
/*     */     } 
/* 163 */     if (getProperties()) {
/* 164 */       Map<String, String> propertyMap = event.getMDCPropertyMap();
/* 166 */       if (propertyMap != null && propertyMap.size() != 0) {
/* 167 */         Set<Map.Entry<String, String>> entrySet = propertyMap.entrySet();
/* 168 */         this.buf.append("  <log4j:properties>");
/* 169 */         for (Map.Entry<String, String> entry : entrySet) {
/* 170 */           this.buf.append("\r\n    <log4j:data");
/* 171 */           this.buf.append(" name='" + Transform.escapeTags(entry.getKey()) + "'");
/* 172 */           this.buf.append(" value='" + Transform.escapeTags(entry.getValue()) + "'");
/* 173 */           this.buf.append(" />");
/*     */         } 
/* 175 */         this.buf.append("\r\n  </log4j:properties>");
/*     */       } 
/*     */     } 
/* 179 */     this.buf.append("\r\n</log4j:event>\r\n\r\n");
/* 181 */     return this.buf.toString();
/*     */   }
/*     */   
/*     */   public String getContentType() {
/* 186 */     return "text/xml";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\log4j\XMLLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */