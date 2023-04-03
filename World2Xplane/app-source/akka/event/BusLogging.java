/*      */ package akka.event;
/*      */ 
/*      */ import scala.collection.Seq;
/*      */ import scala.collection.immutable.Map;
/*      */ import scala.reflect.ScalaSignature;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001i4A!\001\002\001\017\tQ!)^:M_\036<\027N\\4\013\005\r!\021!B3wK:$(\"A\003\002\t\005\\7.Y\002\001'\r\001\001B\004\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!A\004'pO\036LgnZ!eCB$XM\035\005\t'\001\021)\031!C\001)\005\031!-^:\026\003U\001\"a\004\f\n\005]\021!A\003'pO\036Lgn\032\"vg\"A\021\004\001B\001B\003%Q#\001\003ckN\004\003\002C\016\001\005\013\007I\021\001\017\002\0231|wmU8ve\016,W#A\017\021\005y\tcBA\005 \023\t\001#\"\001\004Qe\026$WMZ\005\003E\r\022aa\025;sS:<'B\001\021\013\021!)\003A!A!\002\023i\022A\0037pON{WO]2fA!Aq\005\001BC\002\023\005\001&\001\005m_\036\034E.Y:t+\005I\003G\001\0260!\rq2&L\005\003Y\r\022Qa\0217bgN\004\"AL\030\r\001\021I\001'MA\001\002\003\025\ta\016\002\005?\022\032D\007\003\0053\001\t\005\t\025!\0034\003%awnZ\"mCN\034\b\005\r\0025mA\031adK\033\021\00592D!\003\0312\003\003\005\tQ!\0018#\tA4\b\005\002\ns%\021!H\003\002\b\035>$\b.\0338h!\tIA(\003\002>\025\t\031\021I\\=\t\013}\002A\021\001!\002\rqJg.\033;?)\021\t%i\021#\021\005=\001\001\"B\n?\001\004)\002\"B\016?\001\004i\002\"B\024?\001\004)\005G\001$I!\rq2f\022\t\003]!#\021\002\r#\002\002\003\005)\021A\034\t\013)\003A\021A&\002\035%\034XI\035:pe\026s\027M\0317fIV\tA\n\005\002\n\033&\021aJ\003\002\b\005>|G.Z1o\021\025\001\006\001\"\001L\003AI7oV1s]&tw-\0228bE2,G\rC\003S\001\021\0051*A\007jg&sgm\\#oC\ndW\r\032\005\006)\002!\taS\001\017SN$UMY;h\013:\f'\r\\3e\021\0251\006\001\"\005X\003-qw\016^5gs\026\023(o\034:\025\005a[\006CA\005Z\023\tQ&B\001\003V]&$\b\"\002/V\001\004i\022aB7fgN\fw-\032\005\006-\002!\tB\030\013\0041~k\007\"\0021^\001\004\t\027!B2bkN,\007C\0012k\035\t\031\007N\004\002eO6\tQM\003\002g\r\0051AH]8pizJ\021aC\005\003S*\tq\001]1dW\006<W-\003\002lY\nIA\013\033:po\006\024G.\032\006\003S*AQ\001X/A\002uAQa\034\001\005\022A\fQB\\8uS\032Lx+\031:oS:<GC\001-r\021\025af\0161\001\036\021\025\031\b\001\"\005u\003)qw\016^5gs&sgm\034\013\0031VDQ\001\030:A\002uAQa\036\001\005\022a\f1B\\8uS\032LH)\0322vOR\021\001,\037\005\0069Z\004\r!\b")
/*      */ public class BusLogging implements LoggingAdapter {
/*      */   private final LoggingBus bus;
/*      */   
/*      */   private final String logSource;
/*      */   
/*      */   private final Class<?> logClass;
/*      */   
/*      */   public Map<String, Object> mdc() {
/* 1128 */     return LoggingAdapter$class.mdc(this);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String message) {
/* 1128 */     LoggingAdapter$class.error(this, cause, message);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String template, Object arg1) {
/* 1128 */     LoggingAdapter$class.error(this, cause, template, arg1);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String template, Object arg1, Object arg2) {
/* 1128 */     LoggingAdapter$class.error(this, cause, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String template, Object arg1, Object arg2, Object arg3) {
/* 1128 */     LoggingAdapter$class.error(this, cause, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1128 */     LoggingAdapter$class.error(this, cause, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void error(String message) {
/* 1128 */     LoggingAdapter$class.error(this, message);
/*      */   }
/*      */   
/*      */   public void error(String template, Object arg1) {
/* 1128 */     LoggingAdapter$class.error(this, template, arg1);
/*      */   }
/*      */   
/*      */   public void error(String template, Object arg1, Object arg2) {
/* 1128 */     LoggingAdapter$class.error(this, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void error(String template, Object arg1, Object arg2, Object arg3) {
/* 1128 */     LoggingAdapter$class.error(this, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void error(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1128 */     LoggingAdapter$class.error(this, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void warning(String message) {
/* 1128 */     LoggingAdapter$class.warning(this, message);
/*      */   }
/*      */   
/*      */   public void warning(String template, Object arg1) {
/* 1128 */     LoggingAdapter$class.warning(this, template, arg1);
/*      */   }
/*      */   
/*      */   public void warning(String template, Object arg1, Object arg2) {
/* 1128 */     LoggingAdapter$class.warning(this, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void warning(String template, Object arg1, Object arg2, Object arg3) {
/* 1128 */     LoggingAdapter$class.warning(this, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void warning(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1128 */     LoggingAdapter$class.warning(this, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void info(String message) {
/* 1128 */     LoggingAdapter$class.info(this, message);
/*      */   }
/*      */   
/*      */   public void info(String template, Object arg1) {
/* 1128 */     LoggingAdapter$class.info(this, template, arg1);
/*      */   }
/*      */   
/*      */   public void info(String template, Object arg1, Object arg2) {
/* 1128 */     LoggingAdapter$class.info(this, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void info(String template, Object arg1, Object arg2, Object arg3) {
/* 1128 */     LoggingAdapter$class.info(this, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void info(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1128 */     LoggingAdapter$class.info(this, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void debug(String message) {
/* 1128 */     LoggingAdapter$class.debug(this, message);
/*      */   }
/*      */   
/*      */   public void debug(String template, Object arg1) {
/* 1128 */     LoggingAdapter$class.debug(this, template, arg1);
/*      */   }
/*      */   
/*      */   public void debug(String template, Object arg1, Object arg2) {
/* 1128 */     LoggingAdapter$class.debug(this, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void debug(String template, Object arg1, Object arg2, Object arg3) {
/* 1128 */     LoggingAdapter$class.debug(this, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void debug(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1128 */     LoggingAdapter$class.debug(this, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void log(int level, String message) {
/* 1128 */     LoggingAdapter$class.log(this, level, message);
/*      */   }
/*      */   
/*      */   public void log(int level, String template, Object arg1) {
/* 1128 */     LoggingAdapter$class.log(this, level, template, arg1);
/*      */   }
/*      */   
/*      */   public void log(int level, String template, Object arg1, Object arg2) {
/* 1128 */     LoggingAdapter$class.log(this, level, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void log(int level, String template, Object arg1, Object arg2, Object arg3) {
/* 1128 */     LoggingAdapter$class.log(this, level, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void log(int level, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1128 */     LoggingAdapter$class.log(this, level, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public final boolean isEnabled(int level) {
/* 1128 */     return LoggingAdapter$class.isEnabled(this, level);
/*      */   }
/*      */   
/*      */   public final void notifyLog(int level, String message) {
/* 1128 */     LoggingAdapter$class.notifyLog(this, level, message);
/*      */   }
/*      */   
/*      */   public String format(String t, Seq arg) {
/* 1128 */     return LoggingAdapter$class.format(this, t, arg);
/*      */   }
/*      */   
/*      */   public LoggingBus bus() {
/* 1128 */     return this.bus;
/*      */   }
/*      */   
/*      */   public String logSource() {
/* 1128 */     return this.logSource;
/*      */   }
/*      */   
/*      */   public Class<?> logClass() {
/* 1128 */     return this.logClass;
/*      */   }
/*      */   
/*      */   public BusLogging(LoggingBus bus, String logSource, Class<?> logClass) {
/* 1128 */     LoggingAdapter$class.$init$(this);
/*      */   }
/*      */   
/*      */   public boolean isErrorEnabled() {
/* 1132 */     return Logging.LogLevel$.MODULE$.$greater$eq$extension(bus().logLevel(), Logging$.MODULE$.ErrorLevel());
/*      */   }
/*      */   
/*      */   public boolean isWarningEnabled() {
/* 1133 */     return Logging.LogLevel$.MODULE$.$greater$eq$extension(bus().logLevel(), Logging$.MODULE$.WarningLevel());
/*      */   }
/*      */   
/*      */   public boolean isInfoEnabled() {
/* 1134 */     return Logging.LogLevel$.MODULE$.$greater$eq$extension(bus().logLevel(), Logging$.MODULE$.InfoLevel());
/*      */   }
/*      */   
/*      */   public boolean isDebugEnabled() {
/* 1135 */     return Logging.LogLevel$.MODULE$.$greater$eq$extension(bus().logLevel(), Logging$.MODULE$.DebugLevel());
/*      */   }
/*      */   
/*      */   public void notifyError(String message) {
/* 1137 */     bus().publish(Logging.Error$.MODULE$.apply(logSource(), logClass(), message, mdc()));
/*      */   }
/*      */   
/*      */   public void notifyError(Throwable cause, String message) {
/* 1138 */     bus().publish(Logging.Error$.MODULE$.apply(cause, logSource(), logClass(), message, mdc()));
/*      */   }
/*      */   
/*      */   public void notifyWarning(String message) {
/* 1139 */     bus().publish(Logging.Warning$.MODULE$.apply(logSource(), logClass(), message, mdc()));
/*      */   }
/*      */   
/*      */   public void notifyInfo(String message) {
/* 1140 */     bus().publish(Logging.Info$.MODULE$.apply(logSource(), logClass(), message, mdc()));
/*      */   }
/*      */   
/*      */   public void notifyDebug(String message) {
/* 1141 */     bus().publish(Logging.Debug$.MODULE$.apply(logSource(), logClass(), message, mdc()));
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\BusLogging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */