/*     */ package akka.util;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import scala.Function0;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005mc\001B\001\003\001\035\021aaU<ji\016D'BA\002\005\003\021)H/\0337\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fM\"Aq\002\001B\001B\003%\001#A\005ti\006\024H/Q:P]B\021\021\"E\005\003%)\021qAQ8pY\026\fg\016C\003\025\001\021\005Q#\001\004=S:LGO\020\013\003-a\001\"a\006\001\016\003\tAqaD\n\021\002\003\007\001\003C\004\033\001\t\007I\021B\016\002\rM<\030\016^2i+\005a\002CA\017&\033\005q\"BA\020!\003\031\tGo\\7jG*\021\021EI\001\013G>t7-\036:sK:$(BA\002$\025\005!\023\001\0026bm\006L!A\n\020\003\033\005#x.\\5d\005>|G.Z1o\021\031A\003\001)A\0059\00591o^5uG\"\004\003\"\002\026\001\t#Y\023!\003;sC:\0348-\0328e)\r\001BF\f\005\006[%\002\r\001E\001\005MJ|W\016\003\0040S\021\005\r\001M\001\007C\016$\030n\0348\021\007%\t4'\003\0023\025\tAAHY=oC6,g\b\005\002\ni%\021QG\003\002\005+:LG\017C\0038\001\021\005\001(A\005to&$8\r[(gMR\021\001#\017\005\007_Y\"\t\031\001\031\t\013m\002A\021\001\037\002\021M<\030\016^2i\037:$\"\001E\037\t\r=RD\0211\0011\021\0259\004\001\"\001@+\005\001\002\"B\036\001\t\003y\004\"\002\"\001\t\003\031\025!C5g\037:L\026.\0327e+\t!%\n\006\002F'B\031\021B\022%\n\005\035S!AB(qi&|g\016\005\002J\0252\001A!B&B\005\004a%!\001+\022\0055\003\006CA\005O\023\ty%BA\004O_RD\027N\\4\021\005%\t\026B\001*\013\005\r\te.\037\005\007_\005#\t\031\001+\021\007%\t\004\nC\003W\001\021\005q+\001\006jM>3g-W5fY\022,\"\001W.\025\005ec\006cA\005G5B\021\021j\027\003\006\027V\023\r\001\024\005\007_U#\t\031A/\021\007%\t$\fC\003`\001\021\005\001-\001\003jM>sGC\001\tb\021\031yc\f\"a\001a!)1\r\001C\001I\006)\021NZ(gMR\021\001#\032\005\007_\t$\t\031\001\031\t\013\035\004A\021\0015\002\031]D\027\016\\3P]fKW\r\0343\026\005%dGC\0016n!\rIai\033\t\003\0232$Qa\0234C\0021Caa\f4\005\002\004q\007cA\0052W\")\001\017\001C\001c\006iq\017[5mK>3g-W5fY\022,\"A];\025\005M4\bcA\005GiB\021\021*\036\003\006\027>\024\r\001\024\005\007_=$\t\031A<\021\007%\tD\017C\003z\001\021\005!0A\004xQ&dWm\0248\025\005AY\bBB\030y\t\003\007\001\007C\003~\001\021\005a0\001\005xQ&dWm\0244g)\t\001r\020\003\0040y\022\005\r\001\r\005\b\003\007\001A\021AA\003\003\0211w\016\0343\026\t\005\035\021Q\002\013\005\003\023\t)\002\006\003\002\f\005=\001cA%\002\016\02111*!\001C\0021C\021\"!\005\002\002\021\005\r!a\005\002\007=4g\r\005\003\nc\005-\001\"CA\f\003\003!\t\031AA\n\003\tyg\016C\004\002\034\001!\t!!\b\002\r1|7m[3e+\021\ty\"a\t\025\t\005\005\022Q\005\t\004\023\006\rBAB&\002\032\t\007A\nC\005\002(\005eA\0211\001\002*\005!1m\0343f!\021I\021'!\t\t\r\0055\002\001\"\001@\003\021I7o\0248\t\r\005E\002\001\"\001@\003\025I7o\0244g\017%\t)DAA\001\022\003\t9$\001\004To&$8\r\033\t\004/\005eb\001C\001\003\003\003E\t!a\017\024\007\005e\002\002C\004\025\003s!\t!a\020\025\005\005]\002BCA\"\003s\t\n\021\"\001\002F\005YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIE*\"!a\022+\007A\tIe\013\002\002LA!\021QJA,\033\t\tyE\003\003\002R\005M\023!C;oG\",7m[3e\025\r\t)FC\001\013C:tw\016^1uS>t\027\002BA-\003\037\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\001")
/*     */ public class Switch {
/*     */   private final AtomicBoolean switch;
/*     */   
/*     */   public Switch(boolean startAsOn) {
/*  23 */     this.switch = new AtomicBoolean(startAsOn);
/*     */   }
/*     */   
/*     */   private AtomicBoolean switch() {
/*  23 */     return this.switch;
/*     */   }
/*     */   
/*     */   public synchronized boolean transcend(boolean from, Function0 action) {
/*  26 */     if (switch().compareAndSet(from, !from))
/*     */       try {
/*     */       
/*     */       } finally {
/*  29 */         switch().compareAndSet(!from, from);
/*     */       }  
/*     */     return false;
/*     */   }
/*     */   
/*     */   public boolean switchOff(Function0<BoxedUnit> action) {
/*  41 */     return transcend(true, action);
/*     */   }
/*     */   
/*     */   public boolean switchOn(Function0<BoxedUnit> action) {
/*  48 */     return transcend(false, action);
/*     */   }
/*     */   
/*     */   public synchronized boolean switchOff() {
/*  53 */     return switch().compareAndSet(true, false);
/*     */   }
/*     */   
/*     */   public synchronized boolean switchOn() {
/*  58 */     return switch().compareAndSet(false, true);
/*     */   }
/*     */   
/*     */   public <T> Option<T> ifOnYield(Function0 action) {
/*  63 */     return switch().get() ? (Option<T>)new Some(action.apply()) : (Option<T>)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public <T> Option<T> ifOffYield(Function0 action) {
/*  68 */     return switch().get() ? (Option<T>)None$.MODULE$ : (Option<T>)new Some(action.apply());
/*     */   }
/*     */   
/*     */   public boolean ifOn(Function0 action) {
/*  75 */     action.apply$mcV$sp();
/*     */     return switch().get();
/*     */   }
/*     */   
/*     */   public boolean ifOff(Function0 action) {
/*  85 */     action.apply$mcV$sp();
/*     */     return !switch().get();
/*     */   }
/*     */   
/*     */   public synchronized <T> Option<T> whileOnYield(Function0 action) {
/*  94 */     return switch().get() ? (Option<T>)new Some(action.apply()) : (Option<T>)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public synchronized <T> Option<T> whileOffYield(Function0 action) {
/* 100 */     return switch().get() ? (Option<T>)None$.MODULE$ : (Option<T>)new Some(action.apply());
/*     */   }
/*     */   
/*     */   public synchronized boolean whileOn(Function0 action) {
/* 108 */     action.apply$mcV$sp();
/*     */     return switch().get();
/*     */   }
/*     */   
/*     */   public synchronized boolean whileOff(Function0 action) {
/* 119 */     action.apply$mcV$sp();
/*     */     return !switch().get();
/*     */   }
/*     */   
/*     */   public synchronized <T> T fold(Function0 on, Function0 off) {
/* 128 */     return switch().get() ? (T)on.apply() : (T)off.apply();
/*     */   }
/*     */   
/*     */   public synchronized <T> T locked(Function0 code) {
/* 133 */     return (T)code.apply();
/*     */   }
/*     */   
/*     */   public boolean isOn() {
/* 138 */     return switch().get();
/*     */   }
/*     */   
/*     */   public boolean isOff() {
/* 143 */     return !isOn();
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$1() {
/*     */     return Switch$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Switch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */