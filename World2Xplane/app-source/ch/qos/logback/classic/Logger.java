/*     */ package ch.qos.logback.classic;
/*     */ 
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.classic.spi.LoggingEvent;
/*     */ import ch.qos.logback.classic.util.LoggerNameUtil;
/*     */ import ch.qos.logback.core.Appender;
/*     */ import ch.qos.logback.core.spi.AppenderAttachable;
/*     */ import ch.qos.logback.core.spi.AppenderAttachableImpl;
/*     */ import ch.qos.logback.core.spi.FilterReply;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.slf4j.Marker;
/*     */ import org.slf4j.spi.LocationAwareLogger;
/*     */ 
/*     */ public final class Logger implements Logger, LocationAwareLogger, AppenderAttachable<ILoggingEvent>, Serializable {
/*     */   private static final long serialVersionUID = 5454405123156820674L;
/*     */   
/*  45 */   public static final String FQCN = Logger.class.getName();
/*     */   
/*     */   private String name;
/*     */   
/*     */   private transient Level level;
/*     */   
/*     */   private transient int effectiveLevelInt;
/*     */   
/*     */   private transient Logger parent;
/*     */   
/*     */   private transient List<Logger> childrenList;
/*     */   
/*     */   private transient AppenderAttachableImpl<ILoggingEvent> aai;
/*     */   
/*     */   private transient boolean additive = true;
/*     */   
/*     */   final transient LoggerContext loggerContext;
/*     */   
/*     */   private static final int DEFAULT_CHILD_ARRAY_SIZE = 5;
/*     */   
/*     */   Logger(String name, Logger parent, LoggerContext loggerContext) {
/* 105 */     this.name = name;
/* 106 */     this.parent = parent;
/* 107 */     this.loggerContext = loggerContext;
/*     */   }
/*     */   
/*     */   public Level getEffectiveLevel() {
/* 111 */     return Level.toLevel(this.effectiveLevelInt);
/*     */   }
/*     */   
/*     */   int getEffectiveLevelInt() {
/* 115 */     return this.effectiveLevelInt;
/*     */   }
/*     */   
/*     */   public Level getLevel() {
/* 119 */     return this.level;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 123 */     return this.name;
/*     */   }
/*     */   
/*     */   private boolean isRootLogger() {
/* 128 */     return (this.parent == null);
/*     */   }
/*     */   
/*     */   Logger getChildByName(String childName) {
/* 132 */     if (this.childrenList == null)
/* 133 */       return null; 
/* 135 */     int len = this.childrenList.size();
/* 136 */     for (int i = 0; i < len; i++) {
/* 137 */       Logger childLogger_i = this.childrenList.get(i);
/* 138 */       String childName_i = childLogger_i.getName();
/* 140 */       if (childName.equals(childName_i))
/* 141 */         return childLogger_i; 
/*     */     } 
/* 145 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized void setLevel(Level newLevel) {
/* 150 */     if (this.level == newLevel)
/*     */       return; 
/* 154 */     if (newLevel == null && isRootLogger())
/* 155 */       throw new IllegalArgumentException("The level of the root logger cannot be set to null"); 
/* 159 */     this.level = newLevel;
/* 160 */     if (newLevel == null) {
/* 161 */       this.effectiveLevelInt = this.parent.effectiveLevelInt;
/*     */     } else {
/* 163 */       this.effectiveLevelInt = newLevel.levelInt;
/*     */     } 
/* 166 */     if (this.childrenList != null) {
/* 167 */       int len = this.childrenList.size();
/* 168 */       for (int i = 0; i < len; i++) {
/* 169 */         Logger child = this.childrenList.get(i);
/* 171 */         child.handleParentLevelChange(this.effectiveLevelInt);
/*     */       } 
/*     */     } 
/* 175 */     this.loggerContext.fireOnLevelChange(this, newLevel);
/*     */   }
/*     */   
/*     */   private synchronized void handleParentLevelChange(int newParentLevelInt) {
/* 187 */     if (this.level == null) {
/* 188 */       this.effectiveLevelInt = newParentLevelInt;
/* 191 */       if (this.childrenList != null) {
/* 192 */         int len = this.childrenList.size();
/* 193 */         for (int i = 0; i < len; i++) {
/* 194 */           Logger child = this.childrenList.get(i);
/* 195 */           child.handleParentLevelChange(newParentLevelInt);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void detachAndStopAllAppenders() {
/* 207 */     if (this.aai != null)
/* 208 */       this.aai.detachAndStopAllAppenders(); 
/*     */   }
/*     */   
/*     */   public boolean detachAppender(String name) {
/* 213 */     if (this.aai == null)
/* 214 */       return false; 
/* 216 */     return this.aai.detachAppender(name);
/*     */   }
/*     */   
/*     */   public synchronized void addAppender(Appender<ILoggingEvent> newAppender) {
/* 222 */     if (this.aai == null)
/* 223 */       this.aai = new AppenderAttachableImpl(); 
/* 225 */     this.aai.addAppender(newAppender);
/*     */   }
/*     */   
/*     */   public boolean isAttached(Appender<ILoggingEvent> appender) {
/* 229 */     if (this.aai == null)
/* 230 */       return false; 
/* 232 */     return this.aai.isAttached(appender);
/*     */   }
/*     */   
/*     */   public Iterator<Appender<ILoggingEvent>> iteratorForAppenders() {
/* 237 */     if (this.aai == null)
/* 238 */       return Collections.EMPTY_LIST.iterator(); 
/* 240 */     return this.aai.iteratorForAppenders();
/*     */   }
/*     */   
/*     */   public Appender<ILoggingEvent> getAppender(String name) {
/* 244 */     if (this.aai == null)
/* 245 */       return null; 
/* 247 */     return this.aai.getAppender(name);
/*     */   }
/*     */   
/*     */   public void callAppenders(ILoggingEvent event) {
/* 257 */     int writes = 0;
/* 258 */     for (Logger l = this; l != null; l = l.parent) {
/* 259 */       writes += l.appendLoopOnAppenders(event);
/* 260 */       if (!l.additive)
/*     */         break; 
/*     */     } 
/* 265 */     if (writes == 0)
/* 266 */       this.loggerContext.noAppenderDefinedWarning(this); 
/*     */   }
/*     */   
/*     */   private int appendLoopOnAppenders(ILoggingEvent event) {
/* 271 */     if (this.aai != null)
/* 272 */       return this.aai.appendLoopOnAppenders(event); 
/* 274 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean detachAppender(Appender<ILoggingEvent> appender) {
/* 282 */     if (this.aai == null)
/* 283 */       return false; 
/* 285 */     return this.aai.detachAppender(appender);
/*     */   }
/*     */   
/*     */   Logger createChildByLastNamePart(String lastPart) {
/*     */     Logger childLogger;
/* 306 */     int i_index = LoggerNameUtil.getFirstSeparatorIndexOf(lastPart);
/* 307 */     if (i_index != -1)
/* 308 */       throw new IllegalArgumentException("Child name [" + lastPart + " passed as parameter, may not include [" + '.' + "]"); 
/* 312 */     if (this.childrenList == null)
/* 313 */       this.childrenList = new ArrayList<Logger>(); 
/* 316 */     if (isRootLogger()) {
/* 317 */       childLogger = new Logger(lastPart, this, this.loggerContext);
/*     */     } else {
/* 319 */       childLogger = new Logger(this.name + '.' + lastPart, this, this.loggerContext);
/*     */     } 
/* 322 */     this.childrenList.add(childLogger);
/* 323 */     childLogger.effectiveLevelInt = this.effectiveLevelInt;
/* 324 */     return childLogger;
/*     */   }
/*     */   
/*     */   private void localLevelReset() {
/* 328 */     this.effectiveLevelInt = 10000;
/* 329 */     if (isRootLogger()) {
/* 330 */       this.level = Level.DEBUG;
/*     */     } else {
/* 332 */       this.level = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   void recursiveReset() {
/* 337 */     detachAndStopAllAppenders();
/* 338 */     localLevelReset();
/* 339 */     this.additive = true;
/* 340 */     if (this.childrenList == null)
/*     */       return; 
/* 343 */     for (Logger childLogger : this.childrenList)
/* 344 */       childLogger.recursiveReset(); 
/*     */   }
/*     */   
/*     */   Logger createChildByName(String childName) {
/* 355 */     int i_index = LoggerNameUtil.getSeparatorIndexOf(childName, this.name.length() + 1);
/* 356 */     if (i_index != -1)
/* 357 */       throw new IllegalArgumentException("For logger [" + this.name + "] child name [" + childName + " passed as parameter, may not include '.' after index" + (this.name.length() + 1)); 
/* 363 */     if (this.childrenList == null)
/* 364 */       this.childrenList = new ArrayList<Logger>(5); 
/* 367 */     Logger childLogger = new Logger(childName, this, this.loggerContext);
/* 368 */     this.childrenList.add(childLogger);
/* 369 */     childLogger.effectiveLevelInt = this.effectiveLevelInt;
/* 370 */     return childLogger;
/*     */   }
/*     */   
/*     */   private void filterAndLog_0_Or3Plus(String localFQCN, Marker marker, Level level, String msg, Object[] params, Throwable t) {
/* 383 */     FilterReply decision = this.loggerContext.getTurboFilterChainDecision_0_3OrMore(marker, this, level, msg, params, t);
/* 387 */     if (decision == FilterReply.NEUTRAL) {
/* 388 */       if (this.effectiveLevelInt > level.levelInt)
/*     */         return; 
/* 391 */     } else if (decision == FilterReply.DENY) {
/*     */       return;
/*     */     } 
/* 395 */     buildLoggingEventAndAppend(localFQCN, marker, level, msg, params, t);
/*     */   }
/*     */   
/*     */   private void filterAndLog_1(String localFQCN, Marker marker, Level level, String msg, Object param, Throwable t) {
/* 402 */     FilterReply decision = this.loggerContext.getTurboFilterChainDecision_1(marker, this, level, msg, param, t);
/* 405 */     if (decision == FilterReply.NEUTRAL) {
/* 406 */       if (this.effectiveLevelInt > level.levelInt)
/*     */         return; 
/* 409 */     } else if (decision == FilterReply.DENY) {
/*     */       return;
/*     */     } 
/* 413 */     buildLoggingEventAndAppend(localFQCN, marker, level, msg, new Object[] { param }, t);
/*     */   }
/*     */   
/*     */   private void filterAndLog_2(String localFQCN, Marker marker, Level level, String msg, Object param1, Object param2, Throwable t) {
/* 421 */     FilterReply decision = this.loggerContext.getTurboFilterChainDecision_2(marker, this, level, msg, param1, param2, t);
/* 424 */     if (decision == FilterReply.NEUTRAL) {
/* 425 */       if (this.effectiveLevelInt > level.levelInt)
/*     */         return; 
/* 428 */     } else if (decision == FilterReply.DENY) {
/*     */       return;
/*     */     } 
/* 432 */     buildLoggingEventAndAppend(localFQCN, marker, level, msg, new Object[] { param1, param2 }, t);
/*     */   }
/*     */   
/*     */   private void buildLoggingEventAndAppend(String localFQCN, Marker marker, Level level, String msg, Object[] params, Throwable t) {
/* 439 */     LoggingEvent le = new LoggingEvent(localFQCN, this, level, msg, t, params);
/* 440 */     le.setMarker(marker);
/* 441 */     callAppenders((ILoggingEvent)le);
/*     */   }
/*     */   
/*     */   public void trace(String msg) {
/* 445 */     filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, msg, null, null);
/*     */   }
/*     */   
/*     */   public void trace(String format, Object arg) {
/* 449 */     filterAndLog_1(FQCN, null, Level.TRACE, format, arg, null);
/*     */   }
/*     */   
/*     */   public void trace(String format, Object arg1, Object arg2) {
/* 453 */     filterAndLog_2(FQCN, null, Level.TRACE, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void trace(String format, Object[] argArray) {
/* 457 */     filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void trace(String msg, Throwable t) {
/* 461 */     filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, msg, null, t);
/*     */   }
/*     */   
/*     */   public void trace(Marker marker, String msg) {
/* 465 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, msg, null, null);
/*     */   }
/*     */   
/*     */   public void trace(Marker marker, String format, Object arg) {
/* 469 */     filterAndLog_1(FQCN, marker, Level.TRACE, format, arg, null);
/*     */   }
/*     */   
/*     */   public void trace(Marker marker, String format, Object arg1, Object arg2) {
/* 473 */     filterAndLog_2(FQCN, marker, Level.TRACE, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void trace(Marker marker, String format, Object[] argArray) {
/* 477 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void trace(Marker marker, String msg, Throwable t) {
/* 481 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, msg, null, t);
/*     */   }
/*     */   
/*     */   public boolean isDebugEnabled() {
/* 485 */     return isDebugEnabled(null);
/*     */   }
/*     */   
/*     */   public boolean isDebugEnabled(Marker marker) {
/* 489 */     FilterReply decision = callTurboFilters(marker, Level.DEBUG);
/* 490 */     if (decision == FilterReply.NEUTRAL)
/* 491 */       return (this.effectiveLevelInt <= 10000); 
/* 492 */     if (decision == FilterReply.DENY)
/* 493 */       return false; 
/* 494 */     if (decision == FilterReply.ACCEPT)
/* 495 */       return true; 
/* 497 */     throw new IllegalStateException("Unknown FilterReply value: " + decision);
/*     */   }
/*     */   
/*     */   public void debug(String msg) {
/* 502 */     filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, msg, null, null);
/*     */   }
/*     */   
/*     */   public void debug(String format, Object arg) {
/* 506 */     filterAndLog_1(FQCN, null, Level.DEBUG, format, arg, null);
/*     */   }
/*     */   
/*     */   public void debug(String format, Object arg1, Object arg2) {
/* 510 */     filterAndLog_2(FQCN, null, Level.DEBUG, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void debug(String format, Object[] argArray) {
/* 514 */     filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void debug(String msg, Throwable t) {
/* 518 */     filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, msg, null, t);
/*     */   }
/*     */   
/*     */   public void debug(Marker marker, String msg) {
/* 522 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, msg, null, null);
/*     */   }
/*     */   
/*     */   public void debug(Marker marker, String format, Object arg) {
/* 526 */     filterAndLog_1(FQCN, marker, Level.DEBUG, format, arg, null);
/*     */   }
/*     */   
/*     */   public void debug(Marker marker, String format, Object arg1, Object arg2) {
/* 530 */     filterAndLog_2(FQCN, marker, Level.DEBUG, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void debug(Marker marker, String format, Object[] argArray) {
/* 534 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void debug(Marker marker, String msg, Throwable t) {
/* 538 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, msg, null, t);
/*     */   }
/*     */   
/*     */   public void error(String msg) {
/* 542 */     filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, msg, null, null);
/*     */   }
/*     */   
/*     */   public void error(String format, Object arg) {
/* 546 */     filterAndLog_1(FQCN, null, Level.ERROR, format, arg, null);
/*     */   }
/*     */   
/*     */   public void error(String format, Object arg1, Object arg2) {
/* 550 */     filterAndLog_2(FQCN, null, Level.ERROR, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void error(String format, Object[] argArray) {
/* 554 */     filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void error(String msg, Throwable t) {
/* 558 */     filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, msg, null, t);
/*     */   }
/*     */   
/*     */   public void error(Marker marker, String msg) {
/* 562 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, msg, null, null);
/*     */   }
/*     */   
/*     */   public void error(Marker marker, String format, Object arg) {
/* 566 */     filterAndLog_1(FQCN, marker, Level.ERROR, format, arg, null);
/*     */   }
/*     */   
/*     */   public void error(Marker marker, String format, Object arg1, Object arg2) {
/* 570 */     filterAndLog_2(FQCN, marker, Level.ERROR, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void error(Marker marker, String format, Object[] argArray) {
/* 574 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void error(Marker marker, String msg, Throwable t) {
/* 578 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, msg, null, t);
/*     */   }
/*     */   
/*     */   public boolean isInfoEnabled() {
/* 582 */     return isInfoEnabled(null);
/*     */   }
/*     */   
/*     */   public boolean isInfoEnabled(Marker marker) {
/* 586 */     FilterReply decision = callTurboFilters(marker, Level.INFO);
/* 587 */     if (decision == FilterReply.NEUTRAL)
/* 588 */       return (this.effectiveLevelInt <= 20000); 
/* 589 */     if (decision == FilterReply.DENY)
/* 590 */       return false; 
/* 591 */     if (decision == FilterReply.ACCEPT)
/* 592 */       return true; 
/* 594 */     throw new IllegalStateException("Unknown FilterReply value: " + decision);
/*     */   }
/*     */   
/*     */   public void info(String msg) {
/* 599 */     filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, msg, null, null);
/*     */   }
/*     */   
/*     */   public void info(String format, Object arg) {
/* 603 */     filterAndLog_1(FQCN, null, Level.INFO, format, arg, null);
/*     */   }
/*     */   
/*     */   public void info(String format, Object arg1, Object arg2) {
/* 607 */     filterAndLog_2(FQCN, null, Level.INFO, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void info(String format, Object[] argArray) {
/* 611 */     filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void info(String msg, Throwable t) {
/* 615 */     filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, msg, null, t);
/*     */   }
/*     */   
/*     */   public void info(Marker marker, String msg) {
/* 619 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, msg, null, null);
/*     */   }
/*     */   
/*     */   public void info(Marker marker, String format, Object arg) {
/* 623 */     filterAndLog_1(FQCN, marker, Level.INFO, format, arg, null);
/*     */   }
/*     */   
/*     */   public void info(Marker marker, String format, Object arg1, Object arg2) {
/* 627 */     filterAndLog_2(FQCN, marker, Level.INFO, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void info(Marker marker, String format, Object[] argArray) {
/* 631 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void info(Marker marker, String msg, Throwable t) {
/* 635 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, msg, null, t);
/*     */   }
/*     */   
/*     */   public boolean isTraceEnabled() {
/* 639 */     return isTraceEnabled(null);
/*     */   }
/*     */   
/*     */   public boolean isTraceEnabled(Marker marker) {
/* 643 */     FilterReply decision = callTurboFilters(marker, Level.TRACE);
/* 644 */     if (decision == FilterReply.NEUTRAL)
/* 645 */       return (this.effectiveLevelInt <= 5000); 
/* 646 */     if (decision == FilterReply.DENY)
/* 647 */       return false; 
/* 648 */     if (decision == FilterReply.ACCEPT)
/* 649 */       return true; 
/* 651 */     throw new IllegalStateException("Unknown FilterReply value: " + decision);
/*     */   }
/*     */   
/*     */   public boolean isErrorEnabled() {
/* 656 */     return isErrorEnabled(null);
/*     */   }
/*     */   
/*     */   public boolean isErrorEnabled(Marker marker) {
/* 660 */     FilterReply decision = callTurboFilters(marker, Level.ERROR);
/* 661 */     if (decision == FilterReply.NEUTRAL)
/* 662 */       return (this.effectiveLevelInt <= 40000); 
/* 663 */     if (decision == FilterReply.DENY)
/* 664 */       return false; 
/* 665 */     if (decision == FilterReply.ACCEPT)
/* 666 */       return true; 
/* 668 */     throw new IllegalStateException("Unknown FilterReply value: " + decision);
/*     */   }
/*     */   
/*     */   public boolean isWarnEnabled() {
/* 673 */     return isWarnEnabled(null);
/*     */   }
/*     */   
/*     */   public boolean isWarnEnabled(Marker marker) {
/* 677 */     FilterReply decision = callTurboFilters(marker, Level.WARN);
/* 678 */     if (decision == FilterReply.NEUTRAL)
/* 679 */       return (this.effectiveLevelInt <= 30000); 
/* 680 */     if (decision == FilterReply.DENY)
/* 681 */       return false; 
/* 682 */     if (decision == FilterReply.ACCEPT)
/* 683 */       return true; 
/* 685 */     throw new IllegalStateException("Unknown FilterReply value: " + decision);
/*     */   }
/*     */   
/*     */   public boolean isEnabledFor(Marker marker, Level level) {
/* 691 */     FilterReply decision = callTurboFilters(marker, level);
/* 692 */     if (decision == FilterReply.NEUTRAL)
/* 693 */       return (this.effectiveLevelInt <= level.levelInt); 
/* 694 */     if (decision == FilterReply.DENY)
/* 695 */       return false; 
/* 696 */     if (decision == FilterReply.ACCEPT)
/* 697 */       return true; 
/* 699 */     throw new IllegalStateException("Unknown FilterReply value: " + decision);
/*     */   }
/*     */   
/*     */   public boolean isEnabledFor(Level level) {
/* 704 */     return isEnabledFor(null, level);
/*     */   }
/*     */   
/*     */   public void warn(String msg) {
/* 708 */     filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, msg, null, null);
/*     */   }
/*     */   
/*     */   public void warn(String msg, Throwable t) {
/* 712 */     filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, msg, null, t);
/*     */   }
/*     */   
/*     */   public void warn(String format, Object arg) {
/* 716 */     filterAndLog_1(FQCN, null, Level.WARN, format, arg, null);
/*     */   }
/*     */   
/*     */   public void warn(String format, Object arg1, Object arg2) {
/* 720 */     filterAndLog_2(FQCN, null, Level.WARN, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void warn(String format, Object[] argArray) {
/* 724 */     filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void warn(Marker marker, String msg) {
/* 728 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, msg, null, null);
/*     */   }
/*     */   
/*     */   public void warn(Marker marker, String format, Object arg) {
/* 732 */     filterAndLog_1(FQCN, marker, Level.WARN, format, arg, null);
/*     */   }
/*     */   
/*     */   public void warn(Marker marker, String format, Object[] argArray) {
/* 736 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, format, argArray, null);
/*     */   }
/*     */   
/*     */   public void warn(Marker marker, String format, Object arg1, Object arg2) {
/* 740 */     filterAndLog_2(FQCN, marker, Level.WARN, format, arg1, arg2, null);
/*     */   }
/*     */   
/*     */   public void warn(Marker marker, String msg, Throwable t) {
/* 744 */     filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, msg, null, t);
/*     */   }
/*     */   
/*     */   public boolean isAdditive() {
/* 748 */     return this.additive;
/*     */   }
/*     */   
/*     */   public void setAdditive(boolean additive) {
/* 752 */     this.additive = additive;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 756 */     return "Logger[" + this.name + "]";
/*     */   }
/*     */   
/*     */   private FilterReply callTurboFilters(Marker marker, Level level) {
/* 771 */     return this.loggerContext.getTurboFilterChainDecision_0_3OrMore(marker, this, level, null, null, null);
/*     */   }
/*     */   
/*     */   public LoggerContext getLoggerContext() {
/* 781 */     return this.loggerContext;
/*     */   }
/*     */   
/*     */   public void log(Marker marker, String fqcn, int levelInt, String message, Object[] argArray, Throwable t) {
/* 786 */     Level level = Level.fromLocationAwareLoggerInteger(levelInt);
/* 787 */     filterAndLog_0_Or3Plus(fqcn, marker, level, message, argArray, t);
/*     */   }
/*     */   
/*     */   protected Object readResolve() throws ObjectStreamException {
/* 799 */     return LoggerFactory.getLogger(getName());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\Logger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */