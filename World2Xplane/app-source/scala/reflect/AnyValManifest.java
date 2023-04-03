/*    */ package scala.reflect;
/*    */ 
/*    */ import scala.Equals;
/*    */ import scala.Option;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.ArrayBuilder;
/*    */ import scala.collection.mutable.WrappedArray;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a3Q!\001\002\002\002\035\021a\"\0218z-\006dW*\0318jM\026\034HO\003\002\004\t\0059!/\0324mK\016$(\"A\003\002\013M\034\027\r\\1\004\001U\021\001bE\n\005\001%iA\004\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032\0042AD\b\022\033\005\021\021B\001\t\003\005!i\025M\\5gKN$\bC\001\n\024\031\001!Q\001\006\001C\002U\021\021\001V\t\003-e\001\"AC\f\n\005a!!a\002(pi\"Lgn\032\t\003\025iI!a\007\003\003\r\005s\027PV1m!\tQQ$\003\002\037\t\t1Q)];bYND\001\002\t\001\003\006\004%\t%I\001\ti>\034FO]5oOV\t!\005\005\002$M9\021!\002J\005\003K\021\ta\001\025:fI\0264\027BA\024)\005\031\031FO]5oO*\021Q\005\002\005\tU\001\021\t\021)A\005E\005IAo\\*ue&tw\r\t\005\006Y\001!\t!L\001\007y%t\027\016\036 \025\0059z\003c\001\b\001#!)\001e\013a\001E!)\021\007\001C!e\005\001B\005\\3tg\022\032w\016\\8oI1,7o\035\013\003gY\002\"A\003\033\n\005U\"!a\002\"p_2,\027M\034\005\006oA\002\r\001O\001\005i\"\fG\017\r\002:\003B\031!(\020!\017\0059Y\024B\001\037\003\003\035\001\030mY6bO\026L!AP \003\033\rc\027m]:NC:Lg-Z:u\025\ta$\001\005\002\023\003\022I!INA\001\002\003\025\ta\021\002\004?\022\022\024C\001\fE!\tQQ)\003\002G\t\t\031\021I\\=\t\013!\003A\021I%\002\021\r\fg.R9vC2$\"a\r&\t\013-;\005\031\001#\002\013=$\b.\032:\t\0135\003A\021\t(\002\r\025\fX/\0317t)\t\031t\nC\0038\031\002\007A\tC\004R\001\t\007I\021\t*\002\021!\f7\017[\"pI\026,\022a\025\t\003\025QK!!\026\003\003\007%sG\017\003\004X\001\001\006IaU\001\nQ\006\034\bnQ8eK\002\002")
/*    */ public abstract class AnyValManifest<T> implements Manifest<T>, Equals {
/*    */   private final String toString;
/*    */   
/*    */   private final int hashCode;
/*    */   
/*    */   public List<Manifest<?>> typeArguments() {
/* 66 */     return Manifest$class.typeArguments(this);
/*    */   }
/*    */   
/*    */   public Manifest<Object> arrayManifest() {
/* 66 */     return Manifest$class.arrayManifest(this);
/*    */   }
/*    */   
/*    */   public ClassTag<Object> wrap() {
/* 66 */     return ClassTag$class.wrap(this);
/*    */   }
/*    */   
/*    */   public Object newArray(int len) {
/* 66 */     return ClassTag$class.newArray(this, len);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(Object x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(byte x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(short x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(char x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(int x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(long x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(float x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(double x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(boolean x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Option<T> unapply(BoxedUnit x) {
/* 66 */     return ClassTag$class.unapply(this, x);
/*    */   }
/*    */   
/*    */   public Class<?> erasure() {
/* 66 */     return ClassManifestDeprecatedApis$class.erasure(this);
/*    */   }
/*    */   
/*    */   public boolean $greater$colon$greater(ClassTag that) {
/* 66 */     return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*    */   }
/*    */   
/*    */   public <T> Class<Object> arrayClass(Class tp) {
/* 66 */     return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*    */   }
/*    */   
/*    */   public Object[] newArray2(int len) {
/* 66 */     return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*    */   }
/*    */   
/*    */   public Object[][] newArray3(int len) {
/* 66 */     return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*    */   }
/*    */   
/*    */   public Object[][][] newArray4(int len) {
/* 66 */     return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*    */   }
/*    */   
/*    */   public Object[][][][] newArray5(int len) {
/* 66 */     return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*    */   }
/*    */   
/*    */   public WrappedArray<T> newWrappedArray(int len) {
/* 66 */     return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*    */   }
/*    */   
/*    */   public ArrayBuilder<T> newArrayBuilder() {
/* 66 */     return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*    */   }
/*    */   
/*    */   public String argString() {
/* 66 */     return ClassManifestDeprecatedApis$class.argString(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 66 */     return this.toString;
/*    */   }
/*    */   
/*    */   public AnyValManifest(String toString) {
/* 66 */     ClassManifestDeprecatedApis$class.$init$(this);
/* 66 */     ClassTag$class.$init$(this);
/* 66 */     Manifest$class.$init$(this);
/* 74 */     this.hashCode = System.identityHashCode(this);
/*    */   }
/*    */   
/*    */   public boolean $less$colon$less(ClassTag that) {
/*    */     return (that == this || that == package$.MODULE$.Manifest().Any() || that == package$.MODULE$.Manifest().AnyVal());
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object other) {
/*    */     boolean bool;
/*    */     if (other instanceof AnyValManifest) {
/*    */       bool = true;
/*    */     } else {
/*    */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public boolean equals(Object that) {
/*    */     return (this == that);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 74 */     return this.hashCode;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\AnyValManifest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */