/*      */ package akka.event;
/*      */ 
/*      */ import scala.Serializable;
/*      */ import scala.collection.Seq;
/*      */ import scala.collection.immutable.Map;
/*      */ import scala.reflect.ScalaSignature;
/*      */ import scala.runtime.AbstractFunction1;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001\tubaB\001\003!\003\r\ta\002\002\017\031><w-\0338h\003\022\f\007\017^3s\025\t\031A!A\003fm\026tGOC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\t\013=\001A\021\001\t\002\r\021Jg.\033;%)\005\t\002CA\005\023\023\t\031\"B\001\003V]&$X\001B\013\001\001Y\0211!\024#D!\t92D\004\002\03135\t!!\003\002\033\005\0059Aj\\4hS:<\027BA\013\035\025\tQ\"\001C\003\037\001\021\005q$A\002nI\016,\022A\006\005\006C\0011\tAI\001\017SN,%O]8s\013:\f'\r\\3e+\005\031\003CA\005%\023\t)#BA\004C_>dW-\0318\t\013\035\002a\021\001\022\002!%\034x+\031:oS:<WI\\1cY\026$\007\"B\025\001\r\003\021\023!D5t\023:4w.\0228bE2,G\rC\003,\001\031\005!%\001\bjg\022+'-^4F]\006\024G.\0323\t\0135\002a\021\003\030\002\0279|G/\0334z\013J\024xN\035\013\003#=BQ\001\r\027A\002E\nq!\\3tg\006<W\r\005\0023k9\021\021bM\005\003i)\ta\001\025:fI\0264\027B\001\0348\005\031\031FO]5oO*\021AG\003\005\006[\0011\t\"\017\013\004#iB\005\"B\0369\001\004a\024!B2bkN,\007CA\037F\035\tq4I\004\002@\0056\t\001I\003\002B\r\0051AH]8pizJ\021aC\005\003\t*\tq\001]1dW\006<W-\003\002G\017\nIA\013\033:po\006\024G.\032\006\003\t*AQ\001\r\035A\002EBQA\023\001\007\022-\013QB\\8uS\032Lx+\031:oS:<GCA\tM\021\025\001\024\n1\0012\021\025q\005A\"\005P\003)qw\016^5gs&sgm\034\013\003#ACQ\001M'A\002EBQA\025\001\007\022M\0131B\\8uS\032LH)\0322vOR\021\021\003\026\005\006aE\003\r!\r\005\006-\002!\taV\001\006KJ\024xN\035\013\004#aK\006\"B\036V\001\004a\004\"\002\031V\001\004\t\004\"\002,\001\t\003YF\003B\t];~CQa\017.A\002qBQA\030.A\002E\n\001\002^3na2\fG/\032\005\006Aj\003\r!Y\001\005CJ<\027\007\005\002\nE&\0211M\003\002\004\003:L\b\"\002,\001\t\003)G#B\tgO\"L\007\"B\036e\001\004a\004\"\0020e\001\004\t\004\"\0021e\001\004\t\007\"\0026e\001\004\t\027\001B1sOJBQA\026\001\005\0021$b!E7o_B\f\b\"B\036l\001\004a\004\"\0020l\001\004\t\004\"\0021l\001\004\t\007\"\0026l\001\004\t\007\"\002:l\001\004\t\027\001B1sONBQA\026\001\005\002Q$r!E;wobL(\020C\003<g\002\007A\bC\003_g\002\007\021\007C\003ag\002\007\021\rC\003kg\002\007\021\rC\003sg\002\007\021\rC\003|g\002\007\021-\001\003be\036$\004\"\002,\001\t\003iHCA\t\021\025\001D\0201\0012\021\0311\006\001\"\001\002\002Q)\021#a\001\002\006!)al a\001c!)\001m a\001C\"1a\013\001C\001\003\023!r!EA\006\003\033\ty\001\003\004_\003\017\001\r!\r\005\007A\006\035\001\031A1\t\r)\f9\0011\001b\021\0311\006\001\"\001\002\024QI\021#!\006\002\030\005e\0211\004\005\007=\006E\001\031A\031\t\r\001\f\t\0021\001b\021\031Q\027\021\003a\001C\"1!/!\005A\002\005DaA\026\001\005\002\005}AcC\t\002\"\005\r\022QEA\024\003SAaAXA\017\001\004\t\004B\0021\002\036\001\007\021\r\003\004k\003;\001\r!\031\005\007e\006u\001\031A1\t\rm\fi\0021\001b\021\035\ti\003\001C\001\003_\tqa^1s]&tw\rF\002\022\003cAa\001MA\026\001\004\t\004bBA\027\001\021\005\021Q\007\013\006#\005]\022\021\b\005\007=\006M\002\031A\031\t\r\001\f\031\0041\001b\021\035\ti\003\001C\001\003{!r!EA \003\003\n\031\005\003\004_\003w\001\r!\r\005\007A\006m\002\031A1\t\r)\fY\0041\001b\021\035\ti\003\001C\001\003\017\"\022\"EA%\003\027\ni%a\024\t\ry\013)\0051\0012\021\031\001\027Q\ta\001C\"1!.!\022A\002\005DaA]A#\001\004\t\007bBA\027\001\021\005\0211\013\013\f#\005U\023qKA-\0037\ni\006\003\004_\003#\002\r!\r\005\007A\006E\003\031A1\t\r)\f\t\0061\001b\021\031\021\030\021\013a\001C\"110!\025A\002\005Dq!!\031\001\t\003\t\031'\001\003j]\032|GcA\t\002f!1\001'a\030A\002EBq!!\031\001\t\003\tI\007F\003\022\003W\ni\007\003\004_\003O\002\r!\r\005\007A\006\035\004\031A1\t\017\005\005\004\001\"\001\002rQ9\021#a\035\002v\005]\004B\0020\002p\001\007\021\007\003\004a\003_\002\r!\031\005\007U\006=\004\031A1\t\017\005\005\004\001\"\001\002|QI\021#! \002\000\005\005\0251\021\005\007=\006e\004\031A\031\t\r\001\fI\b1\001b\021\031Q\027\021\020a\001C\"1!/!\037A\002\005Dq!!\031\001\t\003\t9\tF\006\022\003\023\013Y)!$\002\020\006E\005B\0020\002\006\002\007\021\007\003\004a\003\013\003\r!\031\005\007U\006\025\005\031A1\t\rI\f)\t1\001b\021\031Y\030Q\021a\001C\"9\021Q\023\001\005\002\005]\025!\0023fEV<GcA\t\002\032\"1\001'a%A\002EBq!!&\001\t\003\ti\nF\003\022\003?\013\t\013\003\004_\0037\003\r!\r\005\007A\006m\005\031A1\t\017\005U\005\001\"\001\002&R9\021#a*\002*\006-\006B\0020\002$\002\007\021\007\003\004a\003G\003\r!\031\005\007U\006\r\006\031A1\t\017\005U\005\001\"\001\0020RI\021#!-\0024\006U\026q\027\005\007=\0065\006\031A\031\t\r\001\fi\0131\001b\021\031Q\027Q\026a\001C\"1!/!,A\002\005Dq!!&\001\t\003\tY\fF\006\022\003{\013y,!1\002D\006\025\007B\0020\002:\002\007\021\007\003\004a\003s\003\r!\031\005\007U\006e\006\031A1\t\rI\fI\f1\001b\021\031Y\030\021\030a\001C\"9\021\021\032\001\005\002\005-\027a\0017pOR)\021#!4\002X\"A\021qZAd\001\004\t\t.A\003mKZ,G\016E\002\030\003'L1!!6\035\005!aun\032'fm\026d\007B\002\031\002H\002\007\021\007C\004\002J\002!\t!a7\025\017E\ti.a8\002b\"A\021qZAm\001\004\t\t\016\003\004_\0033\004\r!\r\005\007A\006e\007\031A1\t\017\005%\007\001\"\001\002fRI\021#a:\002j\006-\030Q\036\005\t\003\037\f\031\0171\001\002R\"1a,a9A\002EBa\001YAr\001\004\t\007B\0026\002d\002\007\021\rC\004\002J\002!\t!!=\025\027E\t\0310!>\002x\006e\0301 \005\t\003\037\fy\0171\001\002R\"1a,a<A\002EBa\001YAx\001\004\t\007B\0026\002p\002\007\021\r\003\004s\003_\004\r!\031\005\b\003\023\004A\021AA\000)5\t\"\021\001B\002\005\013\0219A!\003\003\f!A\021qZA\001\004\t\t\016\003\004_\003{\004\r!\r\005\007A\006u\b\031A1\t\r)\fi\0201\001b\021\031\021\030Q a\001C\"110!@A\002\005DqAa\004\001\t\013\021\t\"A\005jg\026s\027M\0317fIR\0311Ea\005\t\021\005='Q\002a\001\003#DqAa\006\001\t\013\021I\"A\005o_RLg-\037'pOR)\021Ca\007\003\036!A\021q\032B\013\001\004\t\t\016\003\0041\005+\001\r!\r\005\b\005C\001A\021\002B\022\003\0351wN]7biF\"R!\rB\023\005SAqAa\n\003 \001\007\021'A\001u\021\035\021YCa\bA\002\005\f1!\031:h\021\035\021y\003\001C\001\005c\taAZ8s[\006$H#B\031\0034\tU\002b\002B\024\005[\001\r!\r\005\t\005W\021i\0031\001\0038A!\021B!\017b\023\r\021YD\003\002\013yI,\007/Z1uK\022t\004")
/*      */ public interface LoggingAdapter {
/*      */   Map<String, Object> mdc();
/*      */   
/*      */   boolean isErrorEnabled();
/*      */   
/*      */   boolean isWarningEnabled();
/*      */   
/*      */   boolean isInfoEnabled();
/*      */   
/*      */   boolean isDebugEnabled();
/*      */   
/*      */   void notifyError(String paramString);
/*      */   
/*      */   void notifyError(Throwable paramThrowable, String paramString);
/*      */   
/*      */   void notifyWarning(String paramString);
/*      */   
/*      */   void notifyInfo(String paramString);
/*      */   
/*      */   void notifyDebug(String paramString);
/*      */   
/*      */   void error(Throwable paramThrowable, String paramString);
/*      */   
/*      */   void error(Throwable paramThrowable, String paramString, Object paramObject);
/*      */   
/*      */   void error(Throwable paramThrowable, String paramString, Object paramObject1, Object paramObject2);
/*      */   
/*      */   void error(Throwable paramThrowable, String paramString, Object paramObject1, Object paramObject2, Object paramObject3);
/*      */   
/*      */   void error(Throwable paramThrowable, String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4);
/*      */   
/*      */   void error(String paramString);
/*      */   
/*      */   void error(String paramString, Object paramObject);
/*      */   
/*      */   void error(String paramString, Object paramObject1, Object paramObject2);
/*      */   
/*      */   void error(String paramString, Object paramObject1, Object paramObject2, Object paramObject3);
/*      */   
/*      */   void error(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4);
/*      */   
/*      */   void warning(String paramString);
/*      */   
/*      */   void warning(String paramString, Object paramObject);
/*      */   
/*      */   void warning(String paramString, Object paramObject1, Object paramObject2);
/*      */   
/*      */   void warning(String paramString, Object paramObject1, Object paramObject2, Object paramObject3);
/*      */   
/*      */   void warning(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4);
/*      */   
/*      */   void info(String paramString);
/*      */   
/*      */   void info(String paramString, Object paramObject);
/*      */   
/*      */   void info(String paramString, Object paramObject1, Object paramObject2);
/*      */   
/*      */   void info(String paramString, Object paramObject1, Object paramObject2, Object paramObject3);
/*      */   
/*      */   void info(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4);
/*      */   
/*      */   void debug(String paramString);
/*      */   
/*      */   void debug(String paramString, Object paramObject);
/*      */   
/*      */   void debug(String paramString, Object paramObject1, Object paramObject2);
/*      */   
/*      */   void debug(String paramString, Object paramObject1, Object paramObject2, Object paramObject3);
/*      */   
/*      */   void debug(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4);
/*      */   
/*      */   void log(int paramInt, String paramString);
/*      */   
/*      */   void log(int paramInt, String paramString, Object paramObject);
/*      */   
/*      */   void log(int paramInt, String paramString, Object paramObject1, Object paramObject2);
/*      */   
/*      */   void log(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3);
/*      */   
/*      */   void log(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4);
/*      */   
/*      */   boolean isEnabled(int paramInt);
/*      */   
/*      */   void notifyLog(int paramInt, String paramString);
/*      */   
/*      */   String format(String paramString, Seq<Object> paramSeq);
/*      */   
/*      */   public class LoggingAdapter$$anonfun$format1$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Object apply(Object x$10) {
/* 1038 */       return x$10;
/*      */     }
/*      */     
/*      */     public LoggingAdapter$$anonfun$format1$1(LoggingAdapter $outer) {}
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LoggingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */