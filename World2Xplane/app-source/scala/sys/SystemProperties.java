/*    */ package scala.sys;
/*    */ 
/*    */ import java.security.AccessControlException;
/*    */ import scala.Function0;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Option$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Iterator$;
/*    */ import scala.collection.JavaConverters$;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.MapLike;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.mutable.AbstractMap;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.collection.mutable.MapLike;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005e\001B\001\003\001\035\021\001cU=ti\026l\007K]8qKJ$\030.Z:\013\005\r!\021aA:zg*\tQ!A\003tG\006d\027m\001\001\024\007\001A\001\004\005\003\n\035A\001R\"\001\006\013\005-a\021aB7vi\006\024G.\032\006\003\033\021\t!bY8mY\026\034G/[8o\023\ty!BA\006BEN$(/Y2u\033\006\004\bCA\t\026\035\t\0212#D\001\005\023\t!B!\001\004Qe\026$WMZ\005\003-]\021aa\025;sS:<'B\001\013\005!\021I\021\004\005\t\n\005iQ!aA'ba\")A\004\001C\001;\0051A(\0338jiz\"\022A\b\t\003?\001i\021A\001\005\006C\001!\tEI\001\006K6\004H/_\013\002=!)A\005\001C!K\0059A-\0324bk2$HC\001\t'\021\02593\0051\001\021\003\rYW-\037\005\006S\001!\tAK\001\tSR,'/\031;peV\t1\006E\002-[=j\021\001D\005\003]1\021\001\"\023;fe\006$xN\035\t\005%A\002\002#\003\0022\t\t1A+\0369mKJBQa\r\001\005\002Q\n1aZ3u)\t)t\bE\002\023maJ!a\016\003\003\r=\003H/[8o!\tId(D\001;\025\tYD(\001\003mC:<'\"A\037\002\t)\fg/Y\005\003-iBQa\n\032A\002AAQ!\021\001\005B\t\013\001bY8oi\006Lgn\035\013\003\007\032\003\"A\005#\n\005\025#!a\002\"p_2,\027M\034\005\006O\001\003\r\001\005\005\006\021\002!\t!S\001\nI5Lg.^:%KF$\"AS&\016\003\001AQaJ$A\002AAQ!\024\001\005\0029\013\001\002\n9mkN$S-\035\013\003\025>CQ\001\025'A\002=\n!a\033<\t\013I\003A\021A*\002\025]\024\030\r]!dG\026\0348/\006\002U1R\021Q+\031\t\004%Y2\006CA,Y\031\001!Q!W)C\002i\023\021\001V\t\0037z\003\"A\005/\n\005u#!a\002(pi\"Lgn\032\t\003%}K!\001\031\003\003\007\005s\027\020\003\004c#\022\005\raY\001\005E>$\027\020E\002\023IZK!!\032\003\003\021q\022\027P\\1nKzB\021b\032\001\002\002\003%I\001\0336\002\035M,\b/\032:%G>tG/Y5ogR\0211)\033\005\006O\031\004\r\001E\005\003\003.L!\001\034\007\003\0175\013\007\017T5lK\036)aN\001E\001_\006\0012+_:uK6\004&o\0349feRLWm\035\t\003?A4Q!\001\002\t\002E\034\"\001\035:\021\005I\031\030B\001;\005\005\031\te.\037*fM\")A\004\035C\001mR\tq\016C\003ya\022\005\0210A\006fq\016dWo]5wK2LXC\001>})\tYX\020\005\002Xy\022)\021l\036b\0015\"1!m\036CA\002y\0042A\0053|\021\035\t\t\001\035C\002\003\007\t1d]=ti\026l\007K]8qKJ$\030.Z:U_\016{W\016]1oS>tG\003BA\003\003\017q!aH7\t\r\005%q\0201\001\037\003\005\001\bBCA\007a\"\025\r\021\"\003\002\020\005a\001O]8qKJ$\030\020S3maV\t\001\004C\005\002\024AD\t\021)Q\0051\005i\001O]8qKJ$\030\020S3ma\002Bq!a\006q\t\023\tI\"A\004bI\022DU\r\0349\026\t\005m\021q\004\013\007\003;\t)$a\016\021\007]\013y\002\002\005\002\"\005U!\031AA\022\005\005\001\026cA.\002&A\"\021qEA\030!\025y\022\021FA\027\023\r\tYC\001\002\005!J|\007\017E\002X\003_!1\"!\r\0024\005\005\t\021!B\0015\n\031q\fJ\031\005\021\005\005\022Q\003b\001\003GA\001\"!\003\002\026\001\007\021Q\004\005\b\003s\t)\0021\001\021\003!AW\r\0349UKb$\bbBA\037a\022%\021qH\001\004gR\024HCBA!\003\007\n)\005\005\003 \003S\001\002BB\024\002<\001\007\001\003C\004\002:\005m\002\031\001\t\t\017\005%\003\017\"\003\002L\005!!m\\8m)\031\ti%a\025\002VA\031q$a\024\n\007\005E#AA\006C_>dW-\0318Qe>\004\bBB\024\002H\001\007\001\003C\004\002:\005\035\003\031\001\t\t\017\005e\003\017\"\001\002\\\005!\001.\0327q)\r\001\022Q\f\005\007O\005]\003\031\001\t\t\025\005\005\004\017#b\001\n\003\t\031'\001\005iK\006$G.Z:t+\t\ti\005\003\006\002hAD\t\021)Q\005\003\033\n\021\002[3bI2,7o\035\021\t\025\005-\004\017#b\001\n\003\t\031'A\bqe\0264WM]%QmR\032F/Y2l\021)\ty\007\035E\001B\003&\021QJ\001\021aJ,g-\032:J!Z$4\013^1dW\002B!\"a\035q\021\013\007I\021AA2\003M\001(/\0324fe&\003fON!eIJ,7o]3t\021)\t9\b\035E\001B\003&\021QJ\001\025aJ,g-\032:J!Z4\024\t\0323sKN\034Xm\035\021\t\025\005m\004\017#b\001\n\003\t\031'A\to_R\023\030mY3TkB\024Xm]:j_:D!\"a q\021\003\005\013\025BA'\003Iqw\016\026:bG\026\034V\017\035:fgNLwN\034\021")
/*    */ public class SystemProperties extends AbstractMap<String, String> implements Map<String, String> {
/*    */   public SystemProperties empty() {
/* 32 */     return new SystemProperties();
/*    */   }
/*    */   
/*    */   public Iterator<Tuple2<String, String>> iterator() {
/*    */     Option<?> option;
/* 36 */     return (option = wrapAccess((Function0<?>)new SystemProperties$$anonfun$iterator$1(this))).isEmpty() ? Iterator$.MODULE$.empty() : (Iterator<Tuple2<String, String>>)option.get();
/*    */   }
/*    */   
/*    */   public class SystemProperties$$anonfun$iterator$1 extends AbstractFunction0<Iterator<Tuple2<String, String>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Iterator<Tuple2<String, String>> apply() {
/* 36 */       return ((MapLike)JavaConverters$.MODULE$.propertiesAsScalaMapConverter(System.getProperties()).asScala()).iterator();
/*    */     }
/*    */     
/*    */     public SystemProperties$$anonfun$iterator$1(SystemProperties $outer) {}
/*    */   }
/*    */   
/*    */   public Option<String> get(String key) {
/*    */     Option<?> option;
/* 38 */     return (option = wrapAccess((Function0<?>)new SystemProperties$$anonfun$get$1(this, key))).isEmpty() ? (Option<String>)None$.MODULE$ : (Option<String>)option.get();
/*    */   }
/*    */   
/*    */   public class SystemProperties$$anonfun$get$1 extends AbstractFunction0<Option<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String key$2;
/*    */     
/*    */     public final Option<String> apply() {
/* 38 */       return Option$.MODULE$.apply(System.getProperty(this.key$2));
/*    */     }
/*    */     
/*    */     public SystemProperties$$anonfun$get$1(SystemProperties $outer, String key$2) {}
/*    */   }
/*    */   
/*    */   public boolean scala$sys$SystemProperties$$super$contains(String key) {
/* 40 */     return MapLike.class.contains((MapLike)this, key);
/*    */   }
/*    */   
/*    */   public boolean contains(String key) {
/*    */     Option<?> option;
/* 40 */     return (!(option = wrapAccess((Function0<?>)new SystemProperties$$anonfun$contains$1(this, key))).isEmpty() && BoxesRunTime.unboxToBoolean(option.get()));
/*    */   }
/*    */   
/*    */   public class SystemProperties$$anonfun$contains$1 extends AbstractFunction0.mcZ.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String key$1;
/*    */     
/*    */     public final boolean apply() {
/* 40 */       return this.$outer.scala$sys$SystemProperties$$super$contains(this.key$1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZ$sp() {
/* 40 */       return this.$outer.scala$sys$SystemProperties$$super$contains(this.key$1);
/*    */     }
/*    */     
/*    */     public SystemProperties$$anonfun$contains$1(SystemProperties $outer, String key$1) {}
/*    */   }
/*    */   
/*    */   public SystemProperties $minus$eq(String key) {
/* 42 */     wrapAccess((Function0<?>)new SystemProperties$$anonfun$$minus$eq$1(this, key));
/* 42 */     return this;
/*    */   }
/*    */   
/*    */   public class SystemProperties$$anonfun$$minus$eq$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String key$3;
/*    */     
/*    */     public final String apply() {
/* 42 */       return System.clearProperty(this.key$3);
/*    */     }
/*    */     
/*    */     public SystemProperties$$anonfun$$minus$eq$1(SystemProperties $outer, String key$3) {}
/*    */   }
/*    */   
/*    */   public SystemProperties $plus$eq(Tuple2 kv) {
/* 43 */     wrapAccess((Function0<?>)new SystemProperties$$anonfun$$plus$eq$1(this, kv));
/* 43 */     return this;
/*    */   }
/*    */   
/*    */   public class SystemProperties$$anonfun$$plus$eq$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Tuple2 kv$1;
/*    */     
/*    */     public final String apply() {
/* 43 */       return System.setProperty((String)this.kv$1._1(), (String)this.kv$1._2());
/*    */     }
/*    */     
/*    */     public SystemProperties$$anonfun$$plus$eq$1(SystemProperties $outer, Tuple2 kv$1) {}
/*    */   }
/*    */   
/*    */   public <T> Option<T> wrapAccess(Function0 body) {
/*    */     try {
/*    */     
/* 46 */     } catch (AccessControlException accessControlException) {}
/* 46 */     return (Option<T>)None$.MODULE$;
/*    */   }
/*    */   
/*    */   public static BooleanProp noTraceSupression() {
/*    */     return SystemProperties$.MODULE$.noTraceSupression();
/*    */   }
/*    */   
/*    */   public static BooleanProp preferIPv6Addresses() {
/*    */     return SystemProperties$.MODULE$.preferIPv6Addresses();
/*    */   }
/*    */   
/*    */   public static BooleanProp preferIPv4Stack() {
/*    */     return SystemProperties$.MODULE$.preferIPv4Stack();
/*    */   }
/*    */   
/*    */   public static BooleanProp headless() {
/*    */     return SystemProperties$.MODULE$.headless();
/*    */   }
/*    */   
/*    */   public static String help(String paramString) {
/*    */     return SystemProperties$.MODULE$.help(paramString);
/*    */   }
/*    */   
/*    */   public static SystemProperties$ systemPropertiesToCompanion(SystemProperties paramSystemProperties) {
/*    */     return SystemProperties$.MODULE$.systemPropertiesToCompanion(paramSystemProperties);
/*    */   }
/*    */   
/*    */   public static <T> T exclusively(Function0<T> paramFunction0) {
/*    */     return SystemProperties$.MODULE$.exclusively(paramFunction0);
/*    */   }
/*    */   
/*    */   public String default(String key) {
/*    */     return null;
/*    */   }
/*    */   
/*    */   public static class SystemProperties$$anonfun$help$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/* 72 */       return "";
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\SystemProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */