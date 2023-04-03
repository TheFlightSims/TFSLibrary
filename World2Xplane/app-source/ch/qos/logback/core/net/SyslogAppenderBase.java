/*     */ package ch.qos.logback.core.net;
/*     */ 
/*     */ import ch.qos.logback.core.AppenderBase;
/*     */ import ch.qos.logback.core.Layout;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.net.SocketException;
/*     */ import java.net.UnknownHostException;
/*     */ 
/*     */ public abstract class SyslogAppenderBase<E> extends AppenderBase<E> {
/*     */   static final String SYSLOG_LAYOUT_URL = "http://logback.qos.ch/codes.html#syslog_layout";
/*     */   
/*     */   Layout<E> layout;
/*     */   
/*     */   String facilityStr;
/*     */   
/*     */   String syslogHost;
/*     */   
/*     */   protected String suffixPattern;
/*     */   
/*     */   SyslogOutputStream sos;
/*     */   
/*  42 */   int port = 514;
/*     */   
/*  43 */   int maxMessageSize = 65000;
/*     */   
/*     */   public void start() {
/*  46 */     int errorCount = 0;
/*  47 */     if (this.facilityStr == null) {
/*  48 */       addError("The Facility option is mandatory");
/*  49 */       errorCount++;
/*     */     } 
/*     */     try {
/*  53 */       this.sos = new SyslogOutputStream(this.syslogHost, this.port);
/*  54 */     } catch (UnknownHostException e) {
/*  55 */       addError("Could not create SyslogWriter", e);
/*  56 */       errorCount++;
/*  57 */     } catch (SocketException e) {
/*  58 */       addWarn("Failed to bind to a random datagram socket. Will try to reconnect later.", e);
/*     */     } 
/*  63 */     if (this.layout == null)
/*  64 */       this.layout = buildLayout(); 
/*  67 */     if (errorCount == 0)
/*  68 */       super.start(); 
/*     */   }
/*     */   
/*     */   public abstract Layout<E> buildLayout();
/*     */   
/*     */   public abstract int getSeverityForEvent(Object paramObject);
/*     */   
/*     */   protected void append(E eventObject) {
/*  78 */     if (!isStarted())
/*     */       return; 
/*     */     try {
/*  83 */       String msg = this.layout.doLayout(eventObject);
/*  84 */       if (msg == null)
/*     */         return; 
/*  87 */       if (msg.length() > this.maxMessageSize)
/*  88 */         msg = msg.substring(0, this.maxMessageSize); 
/*  90 */       this.sos.write(msg.getBytes());
/*  91 */       this.sos.flush();
/*  92 */       postProcess(eventObject, this.sos);
/*  93 */     } catch (IOException ioe) {
/*  94 */       addError("Failed to send diagram to " + this.syslogHost, ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void postProcess(Object event, OutputStream sw) {}
/*     */   
/*     */   public static int facilityStringToint(String facilityStr) {
/* 109 */     if ("KERN".equalsIgnoreCase(facilityStr))
/* 110 */       return 0; 
/* 111 */     if ("USER".equalsIgnoreCase(facilityStr))
/* 112 */       return 8; 
/* 113 */     if ("MAIL".equalsIgnoreCase(facilityStr))
/* 114 */       return 16; 
/* 115 */     if ("DAEMON".equalsIgnoreCase(facilityStr))
/* 116 */       return 24; 
/* 117 */     if ("AUTH".equalsIgnoreCase(facilityStr))
/* 118 */       return 32; 
/* 119 */     if ("SYSLOG".equalsIgnoreCase(facilityStr))
/* 120 */       return 40; 
/* 121 */     if ("LPR".equalsIgnoreCase(facilityStr))
/* 122 */       return 48; 
/* 123 */     if ("NEWS".equalsIgnoreCase(facilityStr))
/* 124 */       return 56; 
/* 125 */     if ("UUCP".equalsIgnoreCase(facilityStr))
/* 126 */       return 64; 
/* 127 */     if ("CRON".equalsIgnoreCase(facilityStr))
/* 128 */       return 72; 
/* 129 */     if ("AUTHPRIV".equalsIgnoreCase(facilityStr))
/* 130 */       return 80; 
/* 131 */     if ("FTP".equalsIgnoreCase(facilityStr))
/* 132 */       return 88; 
/* 133 */     if ("LOCAL0".equalsIgnoreCase(facilityStr))
/* 134 */       return 128; 
/* 135 */     if ("LOCAL1".equalsIgnoreCase(facilityStr))
/* 136 */       return 136; 
/* 137 */     if ("LOCAL2".equalsIgnoreCase(facilityStr))
/* 138 */       return 144; 
/* 139 */     if ("LOCAL3".equalsIgnoreCase(facilityStr))
/* 140 */       return 152; 
/* 141 */     if ("LOCAL4".equalsIgnoreCase(facilityStr))
/* 142 */       return 160; 
/* 143 */     if ("LOCAL5".equalsIgnoreCase(facilityStr))
/* 144 */       return 168; 
/* 145 */     if ("LOCAL6".equalsIgnoreCase(facilityStr))
/* 146 */       return 176; 
/* 147 */     if ("LOCAL7".equalsIgnoreCase(facilityStr))
/* 148 */       return 184; 
/* 150 */     throw new IllegalArgumentException(facilityStr + " is not a valid syslog facility string");
/*     */   }
/*     */   
/*     */   public String getSyslogHost() {
/* 159 */     return this.syslogHost;
/*     */   }
/*     */   
/*     */   public void setSyslogHost(String syslogHost) {
/* 169 */     this.syslogHost = syslogHost;
/*     */   }
/*     */   
/*     */   public String getFacility() {
/* 178 */     return this.facilityStr;
/*     */   }
/*     */   
/*     */   public void setFacility(String facilityStr) {
/* 192 */     if (facilityStr != null)
/* 193 */       facilityStr = facilityStr.trim(); 
/* 195 */     this.facilityStr = facilityStr;
/*     */   }
/*     */   
/*     */   public int getPort() {
/* 203 */     return this.port;
/*     */   }
/*     */   
/*     */   public void setPort(int port) {
/* 211 */     this.port = port;
/*     */   }
/*     */   
/*     */   public int getMaxMessageSize() {
/* 219 */     return this.maxMessageSize;
/*     */   }
/*     */   
/*     */   public void setMaxMessageSize(int maxMessageSize) {
/* 230 */     this.maxMessageSize = maxMessageSize;
/*     */   }
/*     */   
/*     */   public Layout<E> getLayout() {
/* 234 */     return this.layout;
/*     */   }
/*     */   
/*     */   public void setLayout(Layout<E> layout) {
/* 238 */     addWarn("The layout of a SyslogAppender cannot be set directly. See also http://logback.qos.ch/codes.html#syslog_layout");
/*     */   }
/*     */   
/*     */   public void stop() {
/* 244 */     this.sos.close();
/* 245 */     super.stop();
/*     */   }
/*     */   
/*     */   public String getSuffixPattern() {
/* 254 */     return this.suffixPattern;
/*     */   }
/*     */   
/*     */   public void setSuffixPattern(String suffixPattern) {
/* 264 */     this.suffixPattern = suffixPattern;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\SyslogAppenderBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */