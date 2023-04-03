/*    */ package scala.sys;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005mcaB\001\003!\003\r\na\002\002\f\005>|G.Z1o!J|\007O\003\002\004\t\005\0311/_:\013\003\025\tQa]2bY\006\034\001aE\002\001\0211\001\"!\003\006\016\003\021I!a\003\003\003\r\005s\027PU3g!\ria\002E\007\002\005%\021qB\001\002\005!J|\007\017\005\002\n#%\021!\003\002\002\b\005>|G.Z1o\021\025!\002A\"\001\026\003\0251\030\r\\;f+\005\001\002\"B\f\001\r\003A\022AB3oC\ndW\rF\001\032!\tI!$\003\002\034\t\t!QK\\5u\021\025i\002A\"\001\031\003\035!\027n]1cY\026DQa\b\001\007\002a\ta\001^8hO2,w!B\021\003\021\003\021\023a\003\"p_2,\027M\034)s_B\004\"!D\022\007\013\005\021\001\022\001\023\024\005\rB\001\"\002\024$\t\0039\023A\002\037j]&$h\bF\001#\r\025I3\005\001\002+\005=\021un\0347fC:\004&o\0349J[Bd7c\001\025,]A\031Q\002\f\t\n\0055\022!\001\003)s_BLU\016\0357\021\0055\001\001\"\003\031)\005\003\005\013\021B\0319\003\rYW-\037\t\003eUr!!C\032\n\005Q\"\021A\002)sK\022,g-\003\0027o\t11\013\036:j]\036T!\001\016\003\n\005Ab\003\002\003\036)\005\003\005\013\021B\036\002\017Y\fG.^3G]B!\021\002P\031\021\023\tiDAA\005Gk:\034G/[8oc!)a\005\013C\001Q\031\001IQ\"\021\005\005CS\"A\022\t\013Ar\004\031A\031\t\013ir\004\031A\036\t\013\025CC\021\t$\002\021M,GOV1mk\026,\"a\022'\025\005AA\005\"B%E\001\004Q\025\001\0038foZ\013G.^3\021\005-cE\002\001\003\006\033\022\023\rA\024\002\003)F\n\"\001E(\021\005%\001\026BA)\005\005\r\te.\037\005\006/!\"\t\001\007\005\006;!\"\t\001\007\005\006?!\"\t\001\007\004\006-\016\002!a\026\002\r\007>t7\017^1oi&k\007\017\\\n\004+\"q\003\002\003\031V\005\013\007I\021A-\026\003EB\001bW+\003\002\003\006I!M\001\005W\026L\b\005\003\005\025+\n\025\r\021\"\001\026\021!qVK!A!\002\023\001\022A\002<bYV,\007\005C\003'+\022\005\001\rF\002bE\016\004\"!Q+\t\013Az\006\031A\031\t\013Qy\006\031\001\t\t\017\025,&\031!C\001+\005)\021n]*fi\"1q-\026Q\001\nA\ta![:TKR\004\003\"B5V\t\003Q\027aA:fiR\0211N\035\t\003YFl\021!\034\006\003]>\fA\001\\1oO*\t\001/\001\003kCZ\f\027B\001\034n\021\025I\005\0161\0012\021\025)U\013\"\001u+\t)\b\020\006\002\021m\")\021j\035a\001oB\0211\n\037\003\006\033N\024\rA\024\005\006uV#\t!W\001\004O\026$\bb\002?V\005\004%\t!`\001\006G2,\027M]\013\0023!1q0\026Q\001\ne\taa\0317fCJ\004\003bB\fV\005\004%\t! \005\b\003\013)\006\025!\003\032\003\035)g.\0312mK\002Bq!H+C\002\023\005Q\020C\004\002\fU\003\013\021B\r\002\021\021L7/\0312mK\002BqaH+C\002\023\005Q\020C\004\002\022U\003\013\021B\r\002\017Q|wm\0327fA!9\021QC+\005\002\005]\021AB8qi&|g.\006\002\002\032A!\021\"a\007\021\023\r\ti\002\002\002\007\037B$\030n\0348\t\r\005\005R\013\"\005\026\003\021QXM]8\t\017\005\0252\005\"\001\002(\005Ya/\0317vK&\033HK];f+\021\tI#!\f\025\0079\nY\003\003\0041\003G\001\r!\r\003\t\003_\t\031C1\001\0022\t\tA+E\002\0024=\0032!CA\033\023\r\t9\004\002\002\b\035>$\b.\0338h\021\035\tYd\tC\001\003{\t\021b[3z\013bL7\017^:\026\t\005}\0221\t\013\004]\005\005\003B\002\031\002:\001\007\021\007\002\005\0020\005e\"\031AA\031\021\035\t9e\tC\001\003\023\n\001bY8ogR\fg\016\036\013\006]\005-\023Q\n\005\007a\005\025\003\031A\031\t\017\005=\023Q\ta\001!\005!\021n](o\021\035\t\031f\tC\002\003+\nACY8pY\026\fg\016\025:pa\006\033(i\\8mK\006tGc\001\t\002X!9\021\021LA)\001\004q\023!\0012")
/*    */ public interface BooleanProp extends Prop<Object> {
/*    */   boolean value();
/*    */   
/*    */   void enable();
/*    */   
/*    */   void disable();
/*    */   
/*    */   void toggle();
/*    */   
/*    */   public static class BooleanPropImpl extends PropImpl<Object> implements BooleanProp {
/*    */     public BooleanPropImpl(String key, Function1<String, Object> valueFn) {
/* 35 */       super(key, valueFn);
/*    */     }
/*    */     
/*    */     public <T1> boolean setValue(Object newValue) {
/* 36 */       if (newValue instanceof Boolean) {
/* 36 */         boolean bool = BoxesRunTime.unboxToBoolean(newValue);
/* 36 */         if (!bool) {
/* 36 */           boolean old = BoxesRunTime.unboxToBoolean(value());
/* 36 */           clear();
/* 36 */           return old;
/*    */         } 
/*    */       } 
/* 36 */       return 
/*    */         
/* 38 */         BoxesRunTime.unboxToBoolean(super.setValue(newValue));
/*    */     }
/*    */     
/*    */     public void enable() {
/* 40 */       setValue(BoxesRunTime.boxToBoolean(true));
/*    */     }
/*    */     
/*    */     public void disable() {
/* 41 */       clear();
/*    */     }
/*    */     
/*    */     public void toggle() {
/* 42 */       if (BoxesRunTime.unboxToBoolean(value())) {
/* 42 */         disable();
/*    */       } else {
/* 42 */         enable();
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ConstantImpl implements BooleanProp {
/*    */     private final String key;
/*    */     
/*    */     private final boolean value;
/*    */     
/*    */     private final boolean isSet;
/*    */     
/*    */     private final BoxedUnit clear;
/*    */     
/*    */     private final BoxedUnit enable;
/*    */     
/*    */     private final BoxedUnit disable;
/*    */     
/*    */     private final BoxedUnit toggle;
/*    */     
/*    */     public String key() {
/* 45 */       return this.key;
/*    */     }
/*    */     
/*    */     public boolean value() {
/* 45 */       return this.value;
/*    */     }
/*    */     
/*    */     public ConstantImpl(String key, boolean value) {
/* 46 */       this.isSet = value;
/* 50 */       this.clear = BoxedUnit.UNIT;
/* 50 */       this.enable = BoxedUnit.UNIT;
/* 50 */       this.disable = BoxedUnit.UNIT;
/* 50 */       this.toggle = BoxedUnit.UNIT;
/*    */     }
/*    */     
/*    */     public boolean isSet() {
/*    */       return this.isSet;
/*    */     }
/*    */     
/*    */     public String set(String newValue) {
/*    */       return String.valueOf(BoxesRunTime.boxToBoolean(value()));
/*    */     }
/*    */     
/*    */     public <T1> boolean setValue(Object newValue) {
/*    */       return value();
/*    */     }
/*    */     
/*    */     public String get() {
/*    */       return String.valueOf(BoxesRunTime.boxToBoolean(value()));
/*    */     }
/*    */     
/*    */     public void clear() {}
/*    */     
/*    */     public void enable() {}
/*    */     
/*    */     public void disable() {}
/*    */     
/*    */     public void toggle() {}
/*    */     
/*    */     public Option<Object> option() {
/* 51 */       return isSet() ? (Option<Object>)new Some(BoxesRunTime.boxToBoolean(value())) : (Option<Object>)None$.MODULE$;
/*    */     }
/*    */     
/*    */     public boolean zero() {
/* 53 */       return false;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class BooleanProp$$anonfun$valueIsTrue$1 extends AbstractFunction1<String, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(String x$1) {
/* 62 */       if (x$1.toLowerCase() == null) {
/* 62 */         x$1.toLowerCase();
/* 62 */         if ("true" != null);
/* 62 */       } else if (x$1.toLowerCase().equals("true")) {
/*    */       
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public static class BooleanProp$$anonfun$keyExists$1 extends AbstractFunction1<String, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(String x$2) {
/* 70 */       return true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\BooleanProp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */