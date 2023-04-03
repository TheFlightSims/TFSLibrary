/*    */ package scala.sys;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.MapLike;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class SystemProperties$ {
/*    */   public static final SystemProperties$ MODULE$;
/*    */   
/*    */   private Map<String, String> propertyHelp;
/*    */   
/*    */   private BooleanProp headless;
/*    */   
/*    */   private BooleanProp preferIPv4Stack;
/*    */   
/*    */   private BooleanProp preferIPv6Addresses;
/*    */   
/*    */   private BooleanProp noTraceSupression;
/*    */   
/*    */   private volatile byte bitmap$0;
/*    */   
/*    */   public class SystemProperties$$anonfun$iterator$1 extends AbstractFunction0<Iterator<Tuple2<String, String>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Iterator<Tuple2<String, String>> apply() {
/* 36 */       return ((MapLike)scala.collection.JavaConverters$.MODULE$.propertiesAsScalaMapConverter(System.getProperties()).asScala()).iterator();
/*    */     }
/*    */     
/*    */     public SystemProperties$$anonfun$iterator$1(SystemProperties $outer) {}
/*    */   }
/*    */   
/*    */   public class SystemProperties$$anonfun$get$1 extends AbstractFunction0<Option<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String key$2;
/*    */     
/*    */     public final Option<String> apply() {
/* 38 */       return scala.Option$.MODULE$.apply(System.getProperty(this.key$2));
/*    */     }
/*    */     
/*    */     public SystemProperties$$anonfun$get$1(SystemProperties $outer, String key$2) {}
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
/*    */   private SystemProperties$() {
/* 55 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public synchronized <T> T exclusively(Function0 body) {
/* 59 */     return (T)body.apply();
/*    */   }
/*    */   
/*    */   public SystemProperties$ systemPropertiesToCompanion(SystemProperties p) {
/* 61 */     return this;
/*    */   }
/*    */   
/*    */   private Map propertyHelp$lzycompute() {
/* 62 */     synchronized (this) {
/* 62 */       if ((byte)(this.bitmap$0 & 0x1) == 0) {
/* 62 */         this.propertyHelp = (Map<String, String>)scala.collection.mutable.Map$.MODULE$.apply((Seq)scala.collection.immutable.Nil$.MODULE$);
/* 62 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/sys/SystemProperties$}} */
/* 62 */       return this.propertyHelp;
/*    */     } 
/*    */   }
/*    */   
/*    */   private Map<String, String> propertyHelp() {
/* 62 */     return ((byte)(this.bitmap$0 & 0x1) == 0) ? propertyHelp$lzycompute() : this.propertyHelp;
/*    */   }
/*    */   
/*    */   private <P extends Prop<?>> P addHelp(Prop p, String helpText) {
/* 64 */     propertyHelp().update(p.key(), helpText);
/* 65 */     return (P)p;
/*    */   }
/*    */   
/*    */   private Prop<String> str(String key, String helpText) {
/* 67 */     return addHelp(Prop$.MODULE$.apply(key, Prop.StringProp$.MODULE$), helpText);
/*    */   }
/*    */   
/*    */   private BooleanProp bool(String key, String helpText) {
/* 68 */     return addHelp(
/* 69 */         key.startsWith("java.") ? BooleanProp$.MODULE$.valueIsTrue(key) : BooleanProp$.MODULE$.keyExists(key), 
/* 70 */         helpText);
/*    */   }
/*    */   
/*    */   public String help(String key) {
/* 72 */     return (String)propertyHelp().getOrElse(key, (Function0)new SystemProperties$$anonfun$help$1());
/*    */   }
/*    */   
/*    */   public static class SystemProperties$$anonfun$help$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/* 72 */       return "";
/*    */     }
/*    */   }
/*    */   
/*    */   private BooleanProp headless$lzycompute() {
/* 77 */     synchronized (this) {
/* 77 */       if ((byte)(this.bitmap$0 & 0x2) == 0) {
/* 77 */         this.headless = bool("java.awt.headless", "system should not utilize a display device");
/* 77 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/sys/SystemProperties$}} */
/* 77 */       return this.headless;
/*    */     } 
/*    */   }
/*    */   
/*    */   public BooleanProp headless() {
/* 77 */     return ((byte)(this.bitmap$0 & 0x2) == 0) ? headless$lzycompute() : this.headless;
/*    */   }
/*    */   
/*    */   private BooleanProp preferIPv4Stack$lzycompute() {
/* 78 */     synchronized (this) {
/* 78 */       if ((byte)(this.bitmap$0 & 0x4) == 0) {
/* 78 */         this.preferIPv4Stack = bool("java.net.preferIPv4Stack", "system should prefer IPv4 sockets");
/* 78 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x4);
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/sys/SystemProperties$}} */
/* 78 */       return this.preferIPv4Stack;
/*    */     } 
/*    */   }
/*    */   
/*    */   public BooleanProp preferIPv4Stack() {
/* 78 */     return ((byte)(this.bitmap$0 & 0x4) == 0) ? preferIPv4Stack$lzycompute() : this.preferIPv4Stack;
/*    */   }
/*    */   
/*    */   private BooleanProp preferIPv6Addresses$lzycompute() {
/* 79 */     synchronized (this) {
/* 79 */       if ((byte)(this.bitmap$0 & 0x8) == 0) {
/* 79 */         this.preferIPv6Addresses = bool("java.net.preferIPv6Addresses", "system should prefer IPv6 addresses");
/* 79 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x8);
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/sys/SystemProperties$}} */
/* 79 */       return this.preferIPv6Addresses;
/*    */     } 
/*    */   }
/*    */   
/*    */   public BooleanProp preferIPv6Addresses() {
/* 79 */     return ((byte)(this.bitmap$0 & 0x8) == 0) ? preferIPv6Addresses$lzycompute() : this.preferIPv6Addresses;
/*    */   }
/*    */   
/*    */   private BooleanProp noTraceSupression$lzycompute() {
/* 80 */     synchronized (this) {
/* 80 */       if ((byte)(this.bitmap$0 & 0x10) == 0) {
/* 80 */         this.noTraceSupression = bool("scala.control.noTraceSuppression", "scala should not suppress any stack trace creation");
/* 80 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x10);
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/sys/SystemProperties$}} */
/* 80 */       return this.noTraceSupression;
/*    */     } 
/*    */   }
/*    */   
/*    */   public BooleanProp noTraceSupression() {
/* 80 */     return ((byte)(this.bitmap$0 & 0x10) == 0) ? noTraceSupression$lzycompute() : this.noTraceSupression;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\SystemProperties$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */