/*     */ package akka.routing;
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
/*     */   private final int akka$routing$MurmurHash$$hiddenMagicA;
/*     */   
/*     */   private final int akka$routing$MurmurHash$$hiddenMagicB;
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
/*     */   private final int akka$routing$MurmurHash$$seedArray;
/*     */   
/*     */   private final int[] storedMagicA;
/*     */   
/*     */   private final int[] storedMagicB;
/*     */   
/*     */   private MurmurHash$() {
/*  35 */     MODULE$ = this;
/*  38 */     this.visibleMagic = -1759636613;
/*  39 */     this.akka$routing$MurmurHash$$hiddenMagicA = -1789642873;
/*  40 */     this.akka$routing$MurmurHash$$hiddenMagicB = 718793509;
/*  41 */     this.visibleMixer = 1390208809;
/*  42 */     this.hiddenMixerA = 2071795100;
/*  43 */     this.hiddenMixerB = 1808688022;
/*  44 */     this.finalMixer1 = -2048144789;
/*  45 */     this.finalMixer2 = -1028477387;
/*  48 */     this.seedString = -137723950;
/*  49 */     this.akka$routing$MurmurHash$$seedArray = 1007110753;
/*  52 */     this.storedMagicA = 
/*  53 */       (int[])scala.package$.MODULE$.Iterator().iterate(BoxesRunTime.boxToInteger(akka$routing$MurmurHash$$hiddenMagicA()), (Function1)new MurmurHash.$anonfun$1()).take(23).toArray(scala.reflect.ClassTag$.MODULE$.Int());
/*  56 */     this.storedMagicB = 
/*  57 */       (int[])scala.package$.MODULE$.Iterator().iterate(BoxesRunTime.boxToInteger(akka$routing$MurmurHash$$hiddenMagicB()), (Function1)new MurmurHash.$anonfun$2()).take(23).toArray(scala.reflect.ClassTag$.MODULE$.Int());
/*     */   }
/*     */   
/*     */   private final int visibleMagic() {
/*     */     return this.visibleMagic;
/*     */   }
/*     */   
/*     */   public final int akka$routing$MurmurHash$$hiddenMagicA() {
/*     */     return this.akka$routing$MurmurHash$$hiddenMagicA;
/*     */   }
/*     */   
/*     */   public final int akka$routing$MurmurHash$$hiddenMagicB() {
/*     */     return this.akka$routing$MurmurHash$$hiddenMagicB;
/*     */   }
/*     */   
/*     */   private final int visibleMixer() {
/*     */     return this.visibleMixer;
/*     */   }
/*     */   
/*     */   private final int hiddenMixerA() {
/*     */     return this.hiddenMixerA;
/*     */   }
/*     */   
/*     */   private final int hiddenMixerB() {
/*     */     return this.hiddenMixerB;
/*     */   }
/*     */   
/*     */   private final int finalMixer1() {
/*     */     return this.finalMixer1;
/*     */   }
/*     */   
/*     */   private final int finalMixer2() {
/*     */     return this.finalMixer2;
/*     */   }
/*     */   
/*     */   private final int seedString() {
/*     */     return this.seedString;
/*     */   }
/*     */   
/*     */   public final int akka$routing$MurmurHash$$seedArray() {
/*     */     return this.akka$routing$MurmurHash$$seedArray;
/*     */   }
/*     */   
/*     */   private int[] storedMagicA() {
/*     */     return this.storedMagicA;
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int magicA) {
/*     */       return apply$mcII$sp(magicA);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int magicA) {
/*     */       return MurmurHash$.MODULE$.nextMagicA(magicA);
/*     */     }
/*     */   }
/*     */   
/*     */   private int[] storedMagicB() {
/*     */     return this.storedMagicB;
/*     */   }
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int magicB) {
/*  57 */       return apply$mcII$sp(magicB);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int magicB) {
/*  57 */       return MurmurHash$.MODULE$.nextMagicB(magicB);
/*     */     }
/*     */   }
/*     */   
/*     */   public int startHash(int seed) {
/*  60 */     return seed ^ visibleMagic();
/*     */   }
/*     */   
/*     */   public int startMagicA() {
/*  63 */     return akka$routing$MurmurHash$$hiddenMagicA();
/*     */   }
/*     */   
/*     */   public int startMagicB() {
/*  66 */     return akka$routing$MurmurHash$$hiddenMagicB();
/*     */   }
/*     */   
/*     */   public int extendHash(int hash, int value, int magicA, int magicB) {
/*  78 */     return (hash ^ Integer.rotateLeft(value * magicA, 11) * magicB) * 3 + visibleMixer();
/*     */   }
/*     */   
/*     */   public int nextMagicA(int magicA) {
/*  81 */     return magicA * 5 + hiddenMixerA();
/*     */   }
/*     */   
/*     */   public int nextMagicB(int magicB) {
/*  84 */     return magicB * 5 + hiddenMixerB();
/*     */   }
/*     */   
/*     */   public int finalizeHash(int hash) {
/*  88 */     int i = hash ^ hash >>> 16;
/*  89 */     i *= finalMixer1();
/*  90 */     i ^= i >>> 13;
/*  91 */     i *= finalMixer2();
/*  92 */     i ^= i >>> 16;
/*  93 */     return i;
/*     */   }
/*     */   
/*     */   public <T> int arrayHash(Object a) {
/*  98 */     int h = startHash(scala.runtime.ScalaRunTime$.MODULE$.array_length(a) * akka$routing$MurmurHash$$seedArray());
/*  99 */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/* 100 */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/* 101 */     int j = 0;
/* 102 */     while (j < scala.runtime.ScalaRunTime$.MODULE$.array_length(a)) {
/* 103 */       h = extendHash(h, scala.runtime.ScalaRunTime$.MODULE$.hash(scala.runtime.ScalaRunTime$.MODULE$.array_apply(a, j)), c, k);
/* 104 */       c = nextMagicA(c);
/* 105 */       k = nextMagicB(k);
/* 106 */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mZc$sp(boolean[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j] ? 1231 : 1237, c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mBc$sp(byte[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j], c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mCc$sp(char[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j], c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mDc$sp(double[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, scala.runtime.ScalaRunTime$.MODULE$.hash(a[j]), c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mFc$sp(float[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, scala.runtime.ScalaRunTime$.MODULE$.hash(a[j]), c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mIc$sp(int[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j], c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mJc$sp(long[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, scala.runtime.ScalaRunTime$.MODULE$.hash(a[j]), c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mSc$sp(short[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, a[j], c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int arrayHash$mVc$sp(BoxedUnit[] a) {
/*     */     int h = startHash(a.length * akka$routing$MurmurHash$$seedArray());
/*     */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/*     */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/*     */     int j = 0;
/*     */     while (j < a.length) {
/*     */       h = extendHash(h, 0, c, k);
/*     */       c = nextMagicA(c);
/*     */       k = nextMagicB(k);
/*     */       j++;
/*     */     } 
/* 108 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int stringHash(String s) {
/* 113 */     int h = startHash(s.length() * seedString());
/* 114 */     int c = akka$routing$MurmurHash$$hiddenMagicA();
/* 115 */     int k = akka$routing$MurmurHash$$hiddenMagicB();
/* 116 */     int j = 0;
/* 117 */     while (j + 1 < s.length()) {
/* 118 */       int i = (s.charAt(j) << 16) + s.charAt(j + 1);
/* 119 */       h = extendHash(h, i, c, k);
/* 120 */       c = nextMagicA(c);
/* 121 */       k = nextMagicB(k);
/* 122 */       j += 2;
/*     */     } 
/* 124 */     if (j < s.length())
/* 124 */       h = extendHash(h, s.charAt(j), c, k); 
/* 125 */     return finalizeHash(h);
/*     */   }
/*     */   
/*     */   public <T> int symmetricHash(TraversableOnce xs, int seed) {
/* 134 */     IntRef a = new IntRef(0), b = new IntRef(0), n = new IntRef(0);
/* 135 */     IntRef c = new IntRef(1);
/* 136 */     xs.foreach((Function1)new MurmurHash$$anonfun$symmetricHash$1(a, b, n, c));
/* 143 */     int h = startHash(seed * n.elem);
/* 144 */     h = extendHash(h, a.elem, storedMagicA()[0], storedMagicB()[0]);
/* 145 */     h = extendHash(h, b.elem, storedMagicA()[1], storedMagicB()[1]);
/* 146 */     h = extendHash(h, c.elem, storedMagicA()[2], storedMagicB()[2]);
/* 147 */     return finalizeHash(h);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\MurmurHash$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */