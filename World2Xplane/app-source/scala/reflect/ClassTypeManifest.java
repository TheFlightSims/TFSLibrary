/*     */ package scala.reflect;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.ArrayBuilder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Y4A!\001\002\005\017\t\t2\t\\1tgRK\b/Z'b]&4Wm\035;\013\005\r!\021a\002:fM2,7\r\036\006\002\013\005)1oY1mC\016\001QC\001\005\030'\r\001\021\"\004\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007c\001\b\023+9\021q\002E\007\002\005%\021\021CA\001\ba\006\0347.Y4f\023\t\031BCA\007DY\006\0348/T1oS\032,7\017\036\006\003#\t\001\"AF\f\r\001\021)\001\004\001b\0013\t\tA+\005\002\033;A\021!bG\005\0039\021\021qAT8uQ&tw\r\005\002\013=%\021q\004\002\002\004\003:L\b\002C\021\001\005\003\005\013\021\002\022\002\rA\024XMZ5y!\rQ1%J\005\003I\021\021aa\0249uS>t\007G\001\024+!\ryq%K\005\003Q\t\0211b\0249u\033\006t\027NZ3tiB\021aC\013\003\nW\001\n\t\021!A\003\002e\021Aa\030\0233o!AQ\006\001BC\002\023\005a&\001\007sk:$\030.\\3DY\006\0348/F\0010a\t\001\024\bE\0022maj\021A\r\006\003gQ\nA\001\\1oO*\tQ'\001\003kCZ\f\027BA\0343\005\025\031E.Y:t!\t1\022\bB\005;w\005\005\t\021!B\0013\t!q\f\n\0329\021!a\004A!A!\002\023i\024!\004:v]RLW.Z\"mCN\034\b\005\r\002?\001B\031\021GN \021\005Y\001E!\003\036<\003\003\005\tQ!\001\032\021!\021\005A!b\001\n\003\032\025!\004;za\026\f%oZ;nK:$8/F\001E!\r)Ej\024\b\003\r.s!a\022&\016\003!S!!\023\004\002\rq\022xn\034;?\023\005)\021BA\t\005\023\tieJ\001\003MSN$(BA\t\005a\t\001&\013E\002\020OE\003\"A\006*\005\023M#\026\021!A\001\006\003I\"\001B0%eeB\001\"\026\001\003\002\003\006IAV\001\017if\004X-\021:hk6,g\016^:!!\r)Ej\026\031\0031j\0032aD\024Z!\t1\"\fB\005T)\006\005\t\021!B\0013!)A\f\001C\001;\0061A(\0338jiz\"BAX0fUB\031q\002A\013\t\013\005Z\006\031\0011\021\007)\031\023\r\r\002cIB\031qbJ2\021\005Y!G!C\026`\003\003\005\tQ!\001\032\021\025i3\f1\001ga\t9\027\016E\0022m!\004\"AF5\005\023i*\027\021!A\001\006\003I\002\"\002\"\\\001\004Y\007cA#MYB\022Qn\034\t\004\037\035r\007C\001\fp\t%\031&.!A\001\002\013\005\021\004C\003r\001\021\005#/\001\005u_N#(/\0338h)\005\031\bCA\031u\023\t)(G\001\004TiJLgn\032")
/*     */ public class ClassTypeManifest<T> implements ClassTag<T> {
/*     */   private final Option<OptManifest<?>> prefix;
/*     */   
/*     */   private final Class<?> runtimeClass;
/*     */   
/*     */   private final List<OptManifest<?>> typeArguments;
/*     */   
/*     */   public ClassTag<Object> wrap() {
/* 231 */     return ClassTag$class.wrap(this);
/*     */   }
/*     */   
/*     */   public Object newArray(int len) {
/* 231 */     return ClassTag$class.newArray(this, len);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(Object x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(byte x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(short x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(char x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(int x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(long x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(float x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(double x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(boolean x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public Option<T> unapply(BoxedUnit x) {
/* 231 */     return ClassTag$class.unapply(this, x);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x) {
/* 231 */     return ClassTag$class.canEqual(this, x);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x) {
/* 231 */     return ClassTag$class.equals(this, x);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 231 */     return ClassTag$class.hashCode(this);
/*     */   }
/*     */   
/*     */   public Class<?> erasure() {
/* 231 */     return ClassManifestDeprecatedApis$class.erasure(this);
/*     */   }
/*     */   
/*     */   public boolean $less$colon$less(ClassTag that) {
/* 231 */     return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */   }
/*     */   
/*     */   public boolean $greater$colon$greater(ClassTag that) {
/* 231 */     return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */   }
/*     */   
/*     */   public <T> Class<Object> arrayClass(Class tp) {
/* 231 */     return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */   }
/*     */   
/*     */   public ClassTag<Object> arrayManifest() {
/* 231 */     return ClassManifestDeprecatedApis$class.arrayManifest(this);
/*     */   }
/*     */   
/*     */   public Object[] newArray2(int len) {
/* 231 */     return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */   }
/*     */   
/*     */   public Object[][] newArray3(int len) {
/* 231 */     return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */   }
/*     */   
/*     */   public Object[][][] newArray4(int len) {
/* 231 */     return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */   }
/*     */   
/*     */   public Object[][][][] newArray5(int len) {
/* 231 */     return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> newWrappedArray(int len) {
/* 231 */     return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */   }
/*     */   
/*     */   public ArrayBuilder<T> newArrayBuilder() {
/* 231 */     return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */   }
/*     */   
/*     */   public String argString() {
/* 231 */     return ClassManifestDeprecatedApis$class.argString(this);
/*     */   }
/*     */   
/*     */   public ClassTypeManifest(Option<OptManifest<?>> prefix, Class<?> runtimeClass, List<OptManifest<?>> typeArguments) {
/* 231 */     ClassManifestDeprecatedApis$class.$init$(this);
/* 231 */     ClassTag$class.$init$(this);
/*     */   }
/*     */   
/*     */   public Class<?> runtimeClass() {
/* 233 */     return this.runtimeClass;
/*     */   }
/*     */   
/*     */   public List<OptManifest<?>> typeArguments() {
/* 234 */     return this.typeArguments;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 237 */     if (this.prefix.isEmpty()) {
/*     */     
/*     */     } else {
/*     */     
/*     */     } 
/* 238 */     return (new StringBuilder()).append((new StringBuilder()).append(this.prefix.get().toString()).append("#").toString()).append(erasure().isArray() ? "Array" : erasure().getName())
/* 239 */       .append(argString()).toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ClassTypeManifest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */