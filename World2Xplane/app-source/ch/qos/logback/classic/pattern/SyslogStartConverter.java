/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import ch.qos.logback.classic.util.LevelToSyslogSeverity;
/*    */ import ch.qos.logback.core.net.SyslogAppenderBase;
/*    */ import java.net.InetAddress;
/*    */ import java.net.UnknownHostException;
/*    */ import java.text.DateFormatSymbols;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
/*    */ 
/*    */ public class SyslogStartConverter extends ClassicConverter {
/* 29 */   long lastTimestamp = -1L;
/*    */   
/* 30 */   String timesmapStr = null;
/*    */   
/*    */   SimpleDateFormat simpleFormat;
/*    */   
/*    */   String localHostName;
/*    */   
/*    */   int facility;
/*    */   
/*    */   public void start() {
/* 36 */     int errorCount = 0;
/* 38 */     String facilityStr = getFirstOption();
/* 39 */     if (facilityStr == null) {
/* 40 */       addError("was expecting a facility string as an option");
/*    */       return;
/*    */     } 
/* 44 */     this.facility = SyslogAppenderBase.facilityStringToint(facilityStr);
/* 46 */     this.localHostName = getLocalHostname();
/*    */     try {
/* 49 */       this.simpleFormat = new SimpleDateFormat("MMM dd HH:mm:ss", new DateFormatSymbols(Locale.US));
/* 50 */     } catch (IllegalArgumentException e) {
/* 51 */       addError("Could not instantiate SimpleDateFormat", e);
/* 52 */       errorCount++;
/*    */     } 
/* 55 */     if (errorCount == 0)
/* 56 */       super.start(); 
/*    */   }
/*    */   
/*    */   public String convert(ILoggingEvent event) {
/* 61 */     StringBuilder sb = new StringBuilder();
/* 63 */     int pri = this.facility + LevelToSyslogSeverity.convert(event);
/* 65 */     sb.append("<");
/* 66 */     sb.append(pri);
/* 67 */     sb.append(">");
/* 68 */     sb.append(computeTimeStampString(event.getTimeStamp()));
/* 69 */     sb.append(' ');
/* 70 */     sb.append(this.localHostName);
/* 71 */     sb.append(' ');
/* 73 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public String getLocalHostname() {
/*    */     try {
/* 84 */       InetAddress addr = InetAddress.getLocalHost();
/* 85 */       return addr.getHostName();
/* 86 */     } catch (UnknownHostException uhe) {
/* 87 */       addError("Could not determine local host name", uhe);
/* 88 */       return "UNKNOWN_LOCALHOST";
/*    */     } 
/*    */   }
/*    */   
/*    */   String computeTimeStampString(long now) {
/* 93 */     synchronized (this) {
/* 94 */       if (now != this.lastTimestamp) {
/* 95 */         this.lastTimestamp = now;
/* 96 */         this.timesmapStr = this.simpleFormat.format(new Date(now));
/*    */       } 
/* 98 */       return this.timesmapStr;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\SyslogStartConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */