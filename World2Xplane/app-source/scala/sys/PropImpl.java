/*    */ package scala.sys;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u4Q!\001\002\001\005\031\021\001\002\025:pa&k\007\017\034\006\003\007\021\t1a]=t\025\005)\021!B:dC2\fWCA\004\023'\r\001\001\002\004\t\003\023)i\021\001B\005\003\027\021\021a!\0218z%\0264\007cA\007\017!5\t!!\003\002\020\005\t!\001K]8q!\t\t\"\003\004\001\005\rM\001AQ1\001\026\005\005!6\001A\t\003-e\001\"!C\f\n\005a!!a\002(pi\"Lgn\032\t\003\023iI!a\007\003\003\007\005s\027\020\003\005\036\001\t\025\r\021\"\001\037\003\rYW-_\013\002?A\021\001e\t\b\003\023\005J!A\t\003\002\rA\023X\rZ3g\023\t!SE\001\004TiJLgn\032\006\003E\021A\001b\n\001\003\002\003\006IaH\001\005W\026L\b\005\003\005*\001\t\005\t\025!\003+\003\0351\030\r\\;f\r:\004B!C\026 !%\021A\006\002\002\n\rVt7\r^5p]FBQA\f\001\005\002=\na\001P5oSRtDc\001\0312eA\031Q\002\001\t\t\013ui\003\031A\020\t\013%j\003\031\001\026\t\013Q\002A\021A\033\002\013Y\fG.^3\026\003AAQa\016\001\005\002a\nQ![:TKR,\022!\017\t\003\023iJ!a\017\003\003\017\t{w\016\\3b]\")Q\b\001C\001}\005\0311/\032;\025\005}y\004\"\002!=\001\004y\022\001\0038foZ\013G.^3\t\013\t\003A\021A\"\002\021M,GOV1mk\026,\"\001R$\025\005A)\005\"\002!B\001\0041\005CA\tH\t\025A\025I1\001J\005\t!\026'\005\002\0213!)1\n\001C\001=\005\031q-\032;\t\0135\003A\021\001(\002\013\rdW-\031:\025\003=\003\"!\003)\n\005E#!\001B+oSRDQa\025\001\005\002Q\013aa\0349uS>tW#A+\021\007%1\006#\003\002X\t\t1q\n\035;j_:DQ!\027\001\005\002i\013!a\034:\026\005mkFC\001/_!\t\tR\fB\003I1\n\007\021\n\003\004`1\022\005\r\001Y\001\004C2$\bcA\005b9&\021!\r\002\002\ty\tLh.Y7f}!)A\r\001C\tK\006QQO\0343fe2L\030N\\4\026\003\031\004Ba\0327 ?5\t\001N\003\002jU\0069Q.\036;bE2,'BA6\005\003)\031w\016\0347fGRLwN\\\005\003[\"\0241!T1q\021\025y\007\001\"\0056\003\021QXM]8\t\013E\004A\021\002:\002\023\035,Go\025;sS:<W#A:\021\005QLX\"A;\013\005Y<\030\001\0027b]\036T\021\001_\001\005U\0064\030-\003\002%k\")1\020\001C!y\006AAo\\*ue&tw\rF\001 \001")
/*    */ public class PropImpl<T> implements Prop<T> {
/*    */   private final String key;
/*    */   
/*    */   private final Function1<String, T> valueFn;
/*    */   
/*    */   public String key() {
/* 15 */     return this.key;
/*    */   }
/*    */   
/*    */   public PropImpl(String key, Function1<String, T> valueFn) {}
/*    */   
/*    */   public T value() {
/* 16 */     return isSet() ? (T)this.valueFn.apply(get()) : zero();
/*    */   }
/*    */   
/*    */   public boolean isSet() {
/* 17 */     return underlying().contains(key());
/*    */   }
/*    */   
/*    */   public String set(String newValue) {
/* 19 */     String old = isSet() ? get() : null;
/* 20 */     underlying().update(key(), newValue);
/* 21 */     return old;
/*    */   }
/*    */   
/*    */   public <T1> T setValue(Object newValue) {
/* 24 */     Object old = value();
/* 25 */     (newValue == null) ? set(null) : 
/* 26 */       set(String.valueOf(newValue));
/* 27 */     return (T)old;
/*    */   }
/*    */   
/*    */   public String get() {
/* 30 */     return isSet() ? (String)underlying().getOrElse(key(), (Function0)new PropImpl$$anonfun$get$1(this)) : 
/* 31 */       "";
/*    */   }
/*    */   
/*    */   public class PropImpl$$anonfun$get$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/*    */       return "";
/*    */     }
/*    */     
/*    */     public PropImpl$$anonfun$get$1(PropImpl $outer) {}
/*    */   }
/*    */   
/*    */   public void clear() {
/* 33 */     underlying().$minus$eq(key());
/*    */   }
/*    */   
/*    */   public Option<T> option() {
/* 34 */     return isSet() ? (Option<T>)new Some(value()) : (Option<T>)None$.MODULE$;
/*    */   }
/*    */   
/*    */   public <T1> T1 or(Function0 alt) {
/* 35 */     return isSet() ? (T1)value() : (T1)alt.apply();
/*    */   }
/*    */   
/*    */   public Map<String, String> underlying() {
/* 38 */     return package$.MODULE$.props();
/*    */   }
/*    */   
/*    */   private String getString() {
/* 40 */     return isSet() ? (new StringBuilder()).append("currently: ").append(get()).toString() : "unset";
/*    */   }
/*    */   
/*    */   public String toString() {
/* 41 */     Predef$ predef$ = Predef$.MODULE$;
/* 41 */     return (new StringOps("%s (%s)")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { key(), getString() }));
/*    */   }
/*    */   
/*    */   public T zero() {
/*    */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\PropImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */