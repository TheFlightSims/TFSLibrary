/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ 
/*     */ public final class MurmurHash$ {
/*     */   public static final MurmurHash$ MODULE$;
/*     */   
/*     */   private final int visibleMagic;
/*     */   
/*     */   private final int hiddenMagicA;
/*     */   
/*     */   private final int hiddenMagicB;
/*     */   
/*     */   private final int visibleMixer;
/*     */   
/*     */   private final int hiddenMixerA;
/*     */   
/*     */   private final int hiddenMixerB;
/*     */   
/*     */   private final int finalMixer1;
/*     */   
/*     */   private final int finalMixer2;
/*     */   
/*     */   private final int seedString;
/*     */   
/*     */   private final int seedArray;
/*     */   
/*     */   private final int[] storedMagicA;
/*     */   
/*     */   private final int[] storedMagicB;
/*     */   
/*     */   private MurmurHash$() {
/*  84 */     MODULE$ = this;
/* 102 */     MurmurHash.$anonfun$1 $anonfun$1 = new MurmurHash.$anonfun$1();
/* 102 */     Integer integer1 = BoxesRunTime.boxToInteger(-1789642873);
/* 102 */     scala.collection.Iterator$ iterator$1 = scala.collection.Iterator$.MODULE$;
/* 102 */     this.storedMagicA = (int[])(new Object(integer1, (Function1)$anonfun$1)).take(23).toArray(scala.reflect.ClassTag$.MODULE$.Int());
/* 106 */     MurmurHash.$anonfun$2 $anonfun$2 = new MurmurHash.$anonfun$2();
/* 106 */     Integer integer2 = BoxesRunTime.boxToInteger(718793509);
/* 106 */     scala.collection.Iterator$ iterator$2 = scala.collection.Iterator$.MODULE$;
/* 106 */     this.storedMagicB = (int[])(new Object(integer2, (Function1)$anonfun$2)).take(23).toArray(scala.reflect.ClassTag$.MODULE$.Int());
/*     */   }
/*     */   
/*     */   private final int visibleMagic() {
/*     */     return -1759636613;
/*     */   }
/*     */   
/*     */   private final int hiddenMagicA() {
/*     */     return -1789642873;
/*     */   }
/*     */   
/*     */   private final int hiddenMagicB() {
/*     */     return 718793509;
/*     */   }
/*     */   
/*     */   private final int visibleMixer() {
/*     */     return 1390208809;
/*     */   }
/*     */   
/*     */   private final int hiddenMixerA() {
/*     */     return 2071795100;
/*     */   }
/*     */   
/*     */   private final int hiddenMixerB() {
/*     */     return 1808688022;
/*     */   }
/*     */   
/*     */   private final int finalMixer1() {
/*     */     return -2048144789;
/*     */   }
/*     */   
/*     */   private final int finalMixer2() {
/*     */     return -1028477387;
/*     */   }
/*     */   
/*     */   private final int seedString() {
/*     */     return -137723950;
/*     */   }
/*     */   
/*     */   private final int seedArray() {
/*     */     return 1007110753;
/*     */   }
/*     */   
/*     */   public int[] storedMagicA() {
/*     */     return this.storedMagicA;
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int magicA) {
/*     */       return MurmurHash$.MODULE$.nextMagicA(magicA);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int magicA) {
/*     */       return MurmurHash$.MODULE$.nextMagicA(magicA);
/*     */     }
/*     */   }
/*     */   
/*     */   public int[] storedMagicB() {
/*     */     return this.storedMagicB;
/*     */   }
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int magicB) {
/* 106 */       return MurmurHash$.MODULE$.nextMagicB(magicB);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int magicB) {
/* 106 */       return MurmurHash$.MODULE$.nextMagicB(magicB);
/*     */     }
/*     */   }
/*     */   
/*     */   public int startHash(int seed) {
/* 109 */     return seed ^ 0x971E137B;
/*     */   }
/*     */   
/*     */   public int startMagicA() {
/* 112 */     return -1789642873;
/*     */   }
/*     */   
/*     */   public int startMagicB() {
/* 115 */     return 718793509;
/*     */   }
/*     */   
/*     */   public int extendHash(int hash, int value, int magicA, int magicB) {
/* 126 */     return (hash ^ Integer.rotateLeft(value * magicA, 11) * magicB) * 3 + 1390208809;
/*     */   }
/*     */   
/*     */   public int nextMagicA(int magicA) {
/* 130 */     return magicA * 5 + 2071795100;
/*     */   }
/*     */   
/*     */   public int nextMagicB(int magicB) {
/* 133 */     return magicB * 5 + 1808688022;
/*     */   }
/*     */   
/*     */   public int finalizeHash(int hash) {
/* 137 */     int i = hash ^ hash >>> 16;
/* 138 */     i *= -2048144789;
/* 140 */     i = (i ^ i >>> 13) * -1028477387;
/* 141 */     return i ^ i >>> 16;
/*     */   }
/*     */   
/*     */   public <T> int arrayHash(Object a) {
/* 147 */     int h = startHash(scala.runtime.ScalaRunTime$.MODULE$.array_length(a) * 1007110753);
/* 148 */     int c = -1789642873;
/* 149 */     int k = 718793509;
/* 150 */     int j = 0;
/* 151 */     while (j < scala.runtime.ScalaRunTime$.MODULE$.array_length(a)) {
/* 152 */       h = extendHash(h, scala.runtime.ScalaRunTime$.MODULE$.hash(scala.runtime.ScalaRunTime$.MODULE$.array_apply(a, j)), c, k);
/* 153 */       c = nextMagicA(c);
/* 154 */       k = nextMagicB(k);
/* 155 */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mZc$sp(boolean[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j] ? 1231 : 1237, c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mBc$sp(byte[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j], c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mCc$sp(char[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j], c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mDc$sp(double[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, scala.runtime.ScalaRunTime$.MODULE$.hash(a[j]), c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mFc$sp(float[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, scala.runtime.ScalaRunTime$.MODULE$.hash(a[j]), c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mIc$sp(int[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j], c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mJc$sp(long[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, scala.runtime.ScalaRunTime$.MODULE$.hash(a[j]), c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mSc$sp(short[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j], c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mVc$sp(BoxedUnit[] a) {
/*     */     int h = startHash(a.length * 1007110753);
/*     */     int c = -1789642873;
/*     */     int k = 718793509;
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, 0, c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 157 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int stringHash(String s) {
/* 162 */     int h = startHash(s.length() * -137723950);
/* 163 */     int c = -1789642873;
/* 164 */     int k = 718793509;
/* 165 */     int j = 0;
/* 166 */     while (j + 1 < s.length()) {
/* 167 */       int i = (s.charAt(j) << 16) + s.charAt(j + 1);
/* 168 */       h = extendHash(h, i, c, k);
/* 169 */       c = nextMagicA(c);
/* 170 */       k = nextMagicB(k);
/* 171 */       j += 2;
/*     */     } 
/* 173 */     if (j < s.length())
/* 173 */       h = extendHash(h, s.charAt(j), c, k); 
/* 174 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public <T> int symmetricHash(TraversableOnce xs, int seed) {
/* 182 */     IntRef a = new IntRef(0), b = new IntRef(0), n = new IntRef(0);
/* 183 */     IntRef c = new IntRef(1);
/* 184 */     xs.seq().foreach((Function1)new MurmurHash$$anonfun$symmetricHash$1(a, b, n, c));
/* 191 */     int h = startHash(seed * n.elem);
/* 192 */     h = extendHash(h, a.elem, storedMagicA()[0], storedMagicB()[0]);
/* 193 */     h = extendHash(h, b.elem, storedMagicA()[1], storedMagicB()[1]);
/* 194 */     h = extendHash(h, c.elem, storedMagicA()[2], storedMagicB()[2]);
/* 195 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public static class MurmurHash$$anonfun$symmetricHash$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef a$1;
/*     */     
/*     */     private final IntRef b$1;
/*     */     
/*     */     private final IntRef n$1;
/*     */     
/*     */     private final IntRef c$1;
/*     */     
/*     */     public MurmurHash$$anonfun$symmetricHash$1(IntRef a$1, IntRef b$1, IntRef n$1, IntRef c$1) {}
/*     */     
/*     */     public final void apply(Object i) {
/*     */       int h = scala.runtime.ScalaRunTime$.MODULE$.hash(i);
/*     */       this.a$1.elem += h;
/*     */       this.b$1.elem ^= h;
/*     */       if (h != 0)
/*     */         this.c$1.elem *= h; 
/*     */       this.n$1.elem++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\MurmurHash$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */