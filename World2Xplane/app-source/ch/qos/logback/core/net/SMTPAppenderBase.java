/*     */ package ch.qos.logback.core.net;
/*     */ 
/*     */ import ch.qos.logback.core.AppenderBase;
/*     */ import ch.qos.logback.core.Layout;
/*     */ import ch.qos.logback.core.boolex.EvaluationException;
/*     */ import ch.qos.logback.core.boolex.EventEvaluator;
/*     */ import ch.qos.logback.core.helpers.CyclicBuffer;
/*     */ import ch.qos.logback.core.pattern.PatternLayoutBase;
/*     */ import ch.qos.logback.core.sift.DefaultDiscriminator;
/*     */ import ch.qos.logback.core.sift.Discriminator;
/*     */ import ch.qos.logback.core.spi.CyclicBufferTracker;
/*     */ import ch.qos.logback.core.util.ContentTypeUtil;
/*     */ import ch.qos.logback.core.util.OptionHelper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import javax.mail.Address;
/*     */ import javax.mail.BodyPart;
/*     */ import javax.mail.Message;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.mail.Multipart;
/*     */ import javax.mail.Session;
/*     */ import javax.mail.Transport;
/*     */ import javax.mail.internet.AddressException;
/*     */ import javax.mail.internet.InternetAddress;
/*     */ import javax.mail.internet.MimeBodyPart;
/*     */ import javax.mail.internet.MimeMessage;
/*     */ import javax.mail.internet.MimeMultipart;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.InitialContext;
/*     */ 
/*     */ public abstract class SMTPAppenderBase<E> extends AppenderBase<E> {
/*  64 */   static InternetAddress[] EMPTY_IA_ARRAY = new InternetAddress[0];
/*     */   
/*     */   static final int MAX_DELAY_BETWEEN_STATUS_MESSAGES = 1228800000;
/*     */   
/*  68 */   long lastTrackerStatusPrint = 0L;
/*     */   
/*  69 */   int delayBetweenStatusMessages = 300000;
/*     */   
/*     */   protected Layout<E> subjectLayout;
/*     */   
/*     */   protected Layout<E> layout;
/*     */   
/*  74 */   private List<PatternLayoutBase<E>> toPatternLayoutList = new ArrayList<PatternLayoutBase<E>>();
/*     */   
/*     */   private String from;
/*     */   
/*  76 */   private String subjectStr = null;
/*     */   
/*     */   private String smtpHost;
/*     */   
/*  78 */   private int smtpPort = 25;
/*     */   
/*     */   private boolean starttls = false;
/*     */   
/*     */   private boolean ssl = false;
/*     */   
/*     */   private boolean sessionViaJNDI = false;
/*     */   
/*  82 */   private String jndiLocation = "java:comp/env/mail/Session";
/*     */   
/*     */   String username;
/*     */   
/*     */   String password;
/*     */   
/*     */   String localhost;
/*     */   
/*     */   boolean asynchronousSending = true;
/*     */   
/*  91 */   private String charsetEncoding = "UTF-8";
/*     */   
/*     */   protected MimeMessage mimeMsg;
/*     */   
/*     */   protected EventEvaluator<E> eventEvaluator;
/*     */   
/*  97 */   protected Discriminator<E> discriminator = (Discriminator<E>)new DefaultDiscriminator();
/*     */   
/*     */   protected CyclicBufferTracker<E> cbTracker;
/*     */   
/* 100 */   private int errorCount = 0;
/*     */   
/*     */   protected abstract Layout<E> makeSubjectLayout(String paramString);
/*     */   
/*     */   public void start() {
/* 117 */     if (this.cbTracker == null)
/* 118 */       this.cbTracker = new CyclicBufferTracker(); 
/* 121 */     Session session = null;
/* 122 */     if (this.sessionViaJNDI) {
/* 123 */       session = lookupSessionInJNDI();
/*     */     } else {
/* 125 */       session = buildSessionFromProperties();
/*     */     } 
/* 127 */     if (session == null) {
/* 128 */       addError("Failed to obtain javax.mail.Session. Cannot start.");
/*     */       return;
/*     */     } 
/* 131 */     this.mimeMsg = new MimeMessage(session);
/*     */     try {
/* 134 */       if (this.from != null) {
/* 135 */         this.mimeMsg.setFrom((Address)getAddress(this.from));
/*     */       } else {
/* 137 */         this.mimeMsg.setFrom();
/*     */       } 
/* 140 */       this.subjectLayout = makeSubjectLayout(this.subjectStr);
/* 142 */       this.started = true;
/* 144 */     } catch (MessagingException e) {
/* 145 */       addError("Could not activate SMTPAppender options.", (Throwable)e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Session lookupSessionInJNDI() {
/* 150 */     addInfo("Looking up javax.mail.Session at JNDI location [" + this.jndiLocation + "]");
/*     */     try {
/* 152 */       Context initialContext = new InitialContext();
/* 153 */       Object obj = initialContext.lookup(this.jndiLocation);
/* 154 */       return (Session)obj;
/* 155 */     } catch (Exception e) {
/* 156 */       addError("Failed to obtain javax.mail.Session from JNDI location [" + this.jndiLocation + "]");
/* 157 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Session buildSessionFromProperties() {
/* 162 */     Properties props = new Properties(OptionHelper.getSystemProperties());
/* 163 */     if (this.smtpHost != null)
/* 164 */       props.put("mail.smtp.host", this.smtpHost); 
/* 166 */     props.put("mail.smtp.port", Integer.toString(this.smtpPort));
/* 168 */     if (this.localhost != null)
/* 169 */       props.put("mail.smtp.localhost", this.localhost); 
/* 172 */     LoginAuthenticator loginAuthenticator = null;
/* 174 */     if (this.username != null) {
/* 175 */       loginAuthenticator = new LoginAuthenticator(this.username, this.password);
/* 176 */       props.put("mail.smtp.auth", "true");
/*     */     } 
/* 179 */     if (isSTARTTLS() && isSSL()) {
/* 180 */       addError("Both SSL and StartTLS cannot be enabled simultaneously");
/*     */     } else {
/* 182 */       if (isSTARTTLS())
/* 184 */         props.put("mail.smtp.starttls.enable", "true"); 
/* 186 */       if (isSSL()) {
/* 187 */         String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
/* 188 */         props.put("mail.smtp.socketFactory.port", Integer.toString(this.smtpPort));
/* 189 */         props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
/* 190 */         props.put("mail.smtp.socketFactory.fallback", "true");
/*     */       } 
/*     */     } 
/* 196 */     return Session.getInstance(props, loginAuthenticator);
/*     */   }
/*     */   
/*     */   protected void append(E eventObject) {
/* 205 */     if (!checkEntryConditions())
/*     */       return; 
/* 209 */     String key = this.discriminator.getDiscriminatingValue(eventObject);
/* 210 */     long now = System.currentTimeMillis();
/* 211 */     CyclicBuffer<E> cb = (CyclicBuffer<E>)this.cbTracker.getOrCreate(key, now);
/* 212 */     subAppend(cb, eventObject);
/*     */     try {
/* 215 */       if (this.eventEvaluator.evaluate(eventObject)) {
/* 217 */         CyclicBuffer<E> cbClone = new CyclicBuffer(cb);
/* 219 */         cb.clear();
/* 221 */         if (this.asynchronousSending) {
/* 223 */           SenderRunnable senderRunnable = new SenderRunnable(cbClone, eventObject);
/* 224 */           this.context.getExecutorService().execute(senderRunnable);
/*     */         } else {
/* 227 */           sendBuffer(cbClone, eventObject);
/*     */         } 
/*     */       } 
/* 230 */     } catch (EvaluationException ex) {
/* 231 */       this.errorCount++;
/* 232 */       if (this.errorCount < 4)
/* 233 */         addError("SMTPAppender's EventEvaluator threw an Exception-", (Throwable)ex); 
/*     */     } 
/* 238 */     if (eventMarksEndOfLife(eventObject))
/* 239 */       this.cbTracker.endOfLife(key); 
/* 242 */     this.cbTracker.removeStaleComponents(now);
/* 244 */     if (this.lastTrackerStatusPrint + this.delayBetweenStatusMessages < now) {
/* 245 */       addInfo("SMTPAppender [" + this.name + "] is tracking [" + this.cbTracker.getComponentCount() + "] buffers");
/* 246 */       this.lastTrackerStatusPrint = now;
/* 248 */       if (this.delayBetweenStatusMessages < 1228800000)
/* 249 */         this.delayBetweenStatusMessages *= 4; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract boolean eventMarksEndOfLife(E paramE);
/*     */   
/*     */   protected abstract void subAppend(CyclicBuffer<E> paramCyclicBuffer, E paramE);
/*     */   
/*     */   public boolean checkEntryConditions() {
/* 267 */     if (!this.started) {
/* 268 */       addError("Attempting to append to a non-started appender: " + getName());
/* 270 */       return false;
/*     */     } 
/* 273 */     if (this.mimeMsg == null) {
/* 274 */       addError("Message object not configured.");
/* 275 */       return false;
/*     */     } 
/* 278 */     if (this.eventEvaluator == null) {
/* 279 */       addError("No EventEvaluator is set for appender [" + this.name + "].");
/* 280 */       return false;
/*     */     } 
/* 283 */     if (this.layout == null) {
/* 284 */       addError("No layout set for appender named [" + this.name + "]. For more information, please visit http://logback.qos.ch/codes.html#smtp_no_layout");
/* 287 */       return false;
/*     */     } 
/* 289 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized void stop() {
/* 293 */     this.started = false;
/*     */   }
/*     */   
/*     */   InternetAddress getAddress(String addressStr) {
/*     */     try {
/* 298 */       return new InternetAddress(addressStr);
/* 299 */     } catch (AddressException e) {
/* 300 */       addError("Could not parse address [" + addressStr + "].", (Throwable)e);
/* 301 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<InternetAddress> parseAddress(E event) {
/* 306 */     int len = this.toPatternLayoutList.size();
/* 308 */     List<InternetAddress> iaList = new ArrayList<InternetAddress>();
/* 310 */     for (int i = 0; i < len; i++) {
/*     */       try {
/* 312 */         PatternLayoutBase<E> emailPL = this.toPatternLayoutList.get(i);
/* 313 */         String emailAdrr = emailPL.doLayout(event);
/* 314 */         if (emailAdrr != null && emailAdrr.length() != 0) {
/* 317 */           InternetAddress[] tmp = InternetAddress.parse(emailAdrr, true);
/* 318 */           iaList.addAll(Arrays.asList(tmp));
/*     */         } 
/* 319 */       } catch (AddressException e) {
/* 320 */         addError("Could not parse email address for [" + this.toPatternLayoutList.get(i) + "] for event [" + event + "]", (Throwable)e);
/* 321 */         return iaList;
/*     */       } 
/*     */     } 
/* 325 */     return iaList;
/*     */   }
/*     */   
/*     */   public List<PatternLayoutBase<E>> getToList() {
/* 332 */     return this.toPatternLayoutList;
/*     */   }
/*     */   
/*     */   protected void sendBuffer(CyclicBuffer<E> cb, E lastEventObject) {
/*     */     try {
/* 343 */       MimeBodyPart part = new MimeBodyPart();
/* 345 */       StringBuffer sbuf = new StringBuffer();
/* 347 */       String header = this.layout.getFileHeader();
/* 348 */       if (header != null)
/* 349 */         sbuf.append(header); 
/* 351 */       String presentationHeader = this.layout.getPresentationHeader();
/* 352 */       if (presentationHeader != null)
/* 353 */         sbuf.append(presentationHeader); 
/* 355 */       fillBuffer(cb, sbuf);
/* 356 */       String presentationFooter = this.layout.getPresentationFooter();
/* 357 */       if (presentationFooter != null)
/* 358 */         sbuf.append(presentationFooter); 
/* 360 */       String footer = this.layout.getFileFooter();
/* 361 */       if (footer != null)
/* 362 */         sbuf.append(footer); 
/* 365 */       String subjectStr = "Undefined subject";
/* 366 */       if (this.subjectLayout != null)
/* 367 */         subjectStr = this.subjectLayout.doLayout(lastEventObject); 
/* 369 */       this.mimeMsg.setSubject(subjectStr, this.charsetEncoding);
/* 371 */       List<InternetAddress> destinationAddresses = parseAddress(lastEventObject);
/* 372 */       if (destinationAddresses.isEmpty()) {
/* 373 */         addInfo("Empty destination address. Aborting email transmission");
/*     */         return;
/*     */       } 
/* 377 */       InternetAddress[] toAddressArray = destinationAddresses.<InternetAddress>toArray(EMPTY_IA_ARRAY);
/* 378 */       this.mimeMsg.setRecipients(Message.RecipientType.TO, (Address[])toAddressArray);
/* 380 */       String contentType = this.layout.getContentType();
/* 382 */       if (ContentTypeUtil.isTextual(contentType)) {
/* 383 */         part.setText(sbuf.toString(), this.charsetEncoding, ContentTypeUtil.getSubType(contentType));
/*     */       } else {
/* 386 */         part.setContent(sbuf.toString(), this.layout.getContentType());
/*     */       } 
/* 389 */       MimeMultipart mimeMultipart = new MimeMultipart();
/* 390 */       mimeMultipart.addBodyPart((BodyPart)part);
/* 391 */       this.mimeMsg.setContent((Multipart)mimeMultipart);
/* 393 */       this.mimeMsg.setSentDate(new Date());
/* 394 */       addInfo("About to send out SMTP message \"" + subjectStr + "\" to " + Arrays.toString((Object[])toAddressArray));
/* 395 */       Transport.send((Message)this.mimeMsg);
/* 396 */     } catch (Exception e) {
/* 397 */       addError("Error occurred while sending e-mail notification.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void fillBuffer(CyclicBuffer<E> paramCyclicBuffer, StringBuffer paramStringBuffer);
/*     */   
/*     */   public String getFrom() {
/* 407 */     return this.from;
/*     */   }
/*     */   
/*     */   public String getSubject() {
/* 414 */     return this.subjectStr;
/*     */   }
/*     */   
/*     */   public void setFrom(String from) {
/* 422 */     this.from = from;
/*     */   }
/*     */   
/*     */   public void setSubject(String subject) {
/* 430 */     this.subjectStr = subject;
/*     */   }
/*     */   
/*     */   public void setSMTPHost(String smtpHost) {
/* 439 */     setSmtpHost(smtpHost);
/*     */   }
/*     */   
/*     */   public void setSmtpHost(String smtpHost) {
/* 447 */     this.smtpHost = smtpHost;
/*     */   }
/*     */   
/*     */   public String getSMTPHost() {
/* 454 */     return getSmtpHost();
/*     */   }
/*     */   
/*     */   public String getSmtpHost() {
/* 461 */     return this.smtpHost;
/*     */   }
/*     */   
/*     */   public void setSMTPPort(int port) {
/* 470 */     setSmtpPort(port);
/*     */   }
/*     */   
/*     */   public void setSmtpPort(int port) {
/* 479 */     this.smtpPort = port;
/*     */   }
/*     */   
/*     */   public int getSMTPPort() {
/* 488 */     return getSmtpPort();
/*     */   }
/*     */   
/*     */   public int getSmtpPort() {
/* 497 */     return this.smtpPort;
/*     */   }
/*     */   
/*     */   public String getLocalhost() {
/* 501 */     return this.localhost;
/*     */   }
/*     */   
/*     */   public void setLocalhost(String localhost) {
/* 515 */     this.localhost = localhost;
/*     */   }
/*     */   
/*     */   public CyclicBufferTracker<E> getCyclicBufferTracker() {
/* 519 */     return this.cbTracker;
/*     */   }
/*     */   
/*     */   public void setCyclicBufferTracker(CyclicBufferTracker<E> cbTracker) {
/* 523 */     this.cbTracker = cbTracker;
/*     */   }
/*     */   
/*     */   public Discriminator<E> getDiscriminator() {
/* 527 */     return this.discriminator;
/*     */   }
/*     */   
/*     */   public void setDiscriminator(Discriminator<E> discriminator) {
/* 531 */     this.discriminator = discriminator;
/*     */   }
/*     */   
/*     */   public boolean isAsynchronousSending() {
/* 535 */     return this.asynchronousSending;
/*     */   }
/*     */   
/*     */   public void setAsynchronousSending(boolean asynchronousSending) {
/* 546 */     this.asynchronousSending = asynchronousSending;
/*     */   }
/*     */   
/*     */   public void addTo(String to) {
/* 550 */     if (to == null || to.length() == 0)
/* 551 */       throw new IllegalArgumentException("Null or empty <to> property"); 
/* 553 */     PatternLayoutBase<E> plb = makeNewToPatternLayout(to.trim());
/* 554 */     plb.setContext(this.context);
/* 555 */     plb.start();
/* 556 */     this.toPatternLayoutList.add(plb);
/*     */   }
/*     */   
/*     */   protected abstract PatternLayoutBase<E> makeNewToPatternLayout(String paramString);
/*     */   
/*     */   public List<String> getToAsListOfString() {
/* 562 */     List<String> toList = new ArrayList<String>();
/* 563 */     for (PatternLayoutBase<E> plb : this.toPatternLayoutList)
/* 564 */       toList.add(plb.getPattern()); 
/* 566 */     return toList;
/*     */   }
/*     */   
/*     */   public Message getMessage() {
/* 571 */     return (Message)this.mimeMsg;
/*     */   }
/*     */   
/*     */   public void setMessage(MimeMessage msg) {
/* 577 */     this.mimeMsg = msg;
/*     */   }
/*     */   
/*     */   public boolean isSTARTTLS() {
/* 581 */     return this.starttls;
/*     */   }
/*     */   
/*     */   public void setSTARTTLS(boolean startTLS) {
/* 585 */     this.starttls = startTLS;
/*     */   }
/*     */   
/*     */   public boolean isSSL() {
/* 589 */     return this.ssl;
/*     */   }
/*     */   
/*     */   public void setSSL(boolean ssl) {
/* 593 */     this.ssl = ssl;
/*     */   }
/*     */   
/*     */   public void setEvaluator(EventEvaluator<E> eventEvaluator) {
/* 603 */     this.eventEvaluator = eventEvaluator;
/*     */   }
/*     */   
/*     */   public String getUsername() {
/* 607 */     return this.username;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/* 611 */     this.username = username;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/* 615 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 619 */     this.password = password;
/*     */   }
/*     */   
/*     */   public String getCharsetEncoding() {
/* 627 */     return this.charsetEncoding;
/*     */   }
/*     */   
/*     */   public String getJndiLocation() {
/* 632 */     return this.jndiLocation;
/*     */   }
/*     */   
/*     */   public void setJndiLocation(String jndiLocation) {
/* 643 */     this.jndiLocation = jndiLocation;
/*     */   }
/*     */   
/*     */   public boolean isSessionViaJNDI() {
/* 647 */     return this.sessionViaJNDI;
/*     */   }
/*     */   
/*     */   public void setSessionViaJNDI(boolean sessionViaJNDI) {
/* 657 */     this.sessionViaJNDI = sessionViaJNDI;
/*     */   }
/*     */   
/*     */   public void setCharsetEncoding(String charsetEncoding) {
/* 667 */     this.charsetEncoding = charsetEncoding;
/*     */   }
/*     */   
/*     */   public Layout<E> getLayout() {
/* 671 */     return this.layout;
/*     */   }
/*     */   
/*     */   public void setLayout(Layout<E> layout) {
/* 675 */     this.layout = layout;
/*     */   }
/*     */   
/*     */   class SenderRunnable implements Runnable {
/*     */     final CyclicBuffer<E> cyclicBuffer;
/*     */     
/*     */     final E e;
/*     */     
/*     */     SenderRunnable(CyclicBuffer<E> cyclicBuffer, E e) {
/* 684 */       this.cyclicBuffer = cyclicBuffer;
/* 685 */       this.e = e;
/*     */     }
/*     */     
/*     */     public void run() {
/* 689 */       SMTPAppenderBase.this.sendBuffer(this.cyclicBuffer, this.e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\SMTPAppenderBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */