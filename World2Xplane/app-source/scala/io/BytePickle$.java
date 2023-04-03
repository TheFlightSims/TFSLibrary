/*     */ package scala.io;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ 
/*     */ public final class BytePickle$ {
/*     */   public static final BytePickle$ MODULE$;
/*     */   
/*     */   private final int REF;
/*     */   
/*     */   private final int DEF;
/*     */   
/*     */   private BytePickle$() {
/*  23 */     MODULE$ = this;
/*  74 */     this.REF = 0;
/*  75 */     this.DEF = 1;
/*     */   }
/*     */   
/*     */   public <T> byte[] pickle(BytePickle.SPU<Object> p, Object a) {
/*     */     return p.appP(a, new BytePickle.PicklerState(new byte[0], new BytePickle.PicklerEnv())).stream();
/*     */   }
/*     */   
/*     */   public <T> T unpickle(BytePickle.SPU<T> p, byte[] stream) {
/*     */     return (T)p.appU(new BytePickle.UnPicklerState(stream, new BytePickle.UnPicklerEnv()))._1();
/*     */   }
/*     */   
/*     */   public <T> byte[] upickle(BytePickle.PU<Object> p, Object a) {
/*     */     return p.appP(a, new byte[0]);
/*     */   }
/*     */   
/*     */   public <T> T uunpickle(BytePickle.PU<T> p, byte[] stream) {
/*     */     return (T)p.appU(stream)._1();
/*     */   }
/*     */   
/*     */   public BytePickle.PU<BytePickle.RefDef> refDef() {
/*     */     return new BytePickle$$anon$1();
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$1 extends BytePickle.PU<BytePickle.RefDef> {
/*     */     public byte[] appP(BytePickle.RefDef b, byte[] s) {
/*     */       if (b instanceof BytePickle.Ref) {
/*     */         (new byte[2][])[0] = s;
/*     */         (new byte[2][])[1] = (byte[])scala.Array$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapByteArray(new byte[] { 0 }, ), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */         byte[] arrayOfByte = (byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */       } else {
/*     */         if (b instanceof BytePickle.Def) {
/*     */           (new byte[2][])[0] = s;
/*     */           (new byte[2][])[1] = (byte[])scala.Array$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapByteArray(new byte[] { 1 }, ), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */           return (byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */         } 
/*     */         throw new MatchError(b);
/*     */       } 
/*     */       return (byte[])SYNTHETIC_LOCAL_VARIABLE_3;
/*     */     }
/*     */     
/*     */     public Tuple2<BytePickle.RefDef, byte[]> appU(byte[] s) {
/*     */       return (s[0] == 0) ? new Tuple2(new BytePickle.Ref(), scala.Predef$.MODULE$.byteArrayOps(s).slice(1, s.length)) : new Tuple2(new BytePickle.Def(), scala.Predef$.MODULE$.byteArrayOps(s).slice(1, s.length));
/*     */     }
/*     */   }
/*     */   
/*     */   public int REF() {
/*     */     return this.REF;
/*     */   }
/*     */   
/*     */   public int DEF() {
/*  75 */     return this.DEF;
/*     */   }
/*     */   
/*     */   public BytePickle.PU<Object> unat() {
/*  77 */     return new BytePickle$$anon$2();
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$2 extends BytePickle.PU<Object> {
/*     */     public byte[] appP(int n, byte[] s) {
/*  79 */       (new byte[2][])[0] = s;
/*  79 */       (new byte[2][])[1] = BytePickle$.MODULE$.nat2Bytes(n);
/*  79 */       return (byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */     }
/*     */     
/*     */     public Tuple2<Object, byte[]> appU(byte[] s) {
/*  81 */       IntRef num = new IntRef(0);
/*  92 */       return new Tuple2(BoxesRunTime.boxToInteger(readNat$1(s, num)), scala.Predef$.MODULE$.byteArrayOps(s).slice(num.elem, s.length));
/*     */     }
/*     */     
/*     */     private final int readNat$1(byte[] s$1, IntRef num$1) {
/*     */       int x = 0;
/*     */       while (true) {
/*     */         b = s$1[num$1.elem];
/*     */         num$1.elem++;
/*     */         x = (x << 7) + (b & 0x7F);
/*     */         if ((b & 0x80) == 0)
/*     */           return x; 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public <a> BytePickle.SPU<a> share(BytePickle.SPU pa) {
/*  96 */     return new BytePickle$$anon$3(pa);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$3 extends BytePickle.SPU<a> {
/*     */     private final BytePickle.SPU pa$1;
/*     */     
/*     */     public BytePickle$$anon$3(BytePickle.SPU pa$1) {}
/*     */     
/*     */     public BytePickle.PicklerState appP(Object v, BytePickle.PicklerState state) {
/* 107 */       BytePickle.PicklerEnv pe = state.dict();
/* 108 */       Option option = pe.get(v);
/* 109 */       if (scala.None$.MODULE$ == null) {
/* 109 */         if (option != null)
/* 118 */           if (option instanceof Some) {
/* 118 */             Some some = (Some)option;
/* 119 */             byte[] sPrime = BytePickle$.MODULE$.refDef().appP(new BytePickle.Ref(), state.stream());
/* 121 */             return new BytePickle.PicklerState(BytePickle$.MODULE$.unat().appP(some.x(), sPrime), pe);
/*     */           }  
/*     */       } else {
/*     */         if (scala.None$.MODULE$.equals(option)) {
/*     */           byte[] sPrime = BytePickle$.MODULE$.refDef().appP(new BytePickle.Def(), state.stream());
/*     */           int l = pe.nextLoc();
/*     */           BytePickle.PicklerState sPrimePrime = this.pa$1.appP(v, new BytePickle.PicklerState(sPrime, pe));
/*     */           pe.update(v, BoxesRunTime.boxToInteger(l));
/*     */           return sPrimePrime;
/*     */         } 
/*     */         if (option instanceof Some) {
/*     */           Some some = (Some)option;
/*     */           byte[] arrayOfByte1 = BytePickle$.MODULE$.refDef().appP(new BytePickle.Ref(), state.stream());
/* 121 */           return new BytePickle.PicklerState(BytePickle$.MODULE$.unat().appP(some.x(), arrayOfByte1), pe);
/*     */         } 
/*     */       } 
/*     */       byte[] arrayOfByte = BytePickle$.MODULE$.refDef().appP(new BytePickle.Def(), state.stream());
/*     */       int i = pe.nextLoc();
/*     */       BytePickle.PicklerState picklerState = this.pa$1.appP(v, new BytePickle.PicklerState(arrayOfByte, pe));
/*     */       pe.update(v, BoxesRunTime.boxToInteger(i));
/*     */       return picklerState;
/*     */     }
/*     */     
/*     */     public Tuple2<a, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState state) {
/* 135 */       BytePickle.UnPicklerEnv upe = state.dict();
/* 136 */       Tuple2<BytePickle.RefDef, byte[]> res = BytePickle$.MODULE$.refDef().appU(state.stream());
/* 137 */       BytePickle.RefDef refDef = (BytePickle.RefDef)res._1();
/* 138 */       if (refDef instanceof BytePickle.Def) {
/* 139 */         int l = upe.nextLoc();
/* 140 */         Tuple2<a, BytePickle.UnPicklerState> res2 = this.pa$1.appU(new BytePickle.UnPicklerState((byte[])res._2(), upe));
/* 141 */         upe.update(BoxesRunTime.boxToInteger(l), res2._1());
/* 142 */         return res2;
/*     */       } 
/* 143 */       if (refDef instanceof BytePickle.Ref) {
/* 144 */         Tuple2<Object, byte[]> res2 = BytePickle$.MODULE$.unat().appU((byte[])res._2());
/* 145 */         Option option = upe.get(BoxesRunTime.boxToInteger(res2._1$mcI$sp()));
/* 146 */         if (scala.None$.MODULE$ == null) {
/* 146 */           if (option != null)
/* 147 */             if (option instanceof Some) {
/* 147 */               Some some = (Some)option;
/* 147 */               return new Tuple2(some.x(), new BytePickle.UnPicklerState((byte[])res2._2(), upe));
/*     */             }  
/*     */         } else {
/*     */           if (scala.None$.MODULE$.equals(option))
/*     */             throw new IllegalArgumentException("invalid unpickler environment"); 
/* 147 */           if (option instanceof Some) {
/* 147 */             Some some = (Some)option;
/* 147 */             return new Tuple2(some.x(), new BytePickle.UnPicklerState((byte[])res2._2(), upe));
/*     */           } 
/*     */         } 
/*     */         throw new IllegalArgumentException("invalid unpickler environment");
/*     */       } 
/*     */       throw new MatchError(refDef);
/*     */     }
/*     */   }
/*     */   
/*     */   public <t> BytePickle.PU<t> ulift(Object x) {
/* 153 */     return new BytePickle$$anon$4(x);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$4 extends BytePickle.PU<t> {
/*     */     private final Object x$4;
/*     */     
/*     */     public BytePickle$$anon$4(Object x$4) {}
/*     */     
/*     */     public byte[] appP(Object a, byte[] state) {
/*     */       Object object;
/* 155 */       if (((object = this.x$4) == a) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, a) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, a) : object.equals(a)))))
/* 155 */         return 
/* 156 */           state; 
/*     */       throw new IllegalArgumentException((new StringBuilder()).append("value to be pickled (").append(a).append(") != ").append(this.x$4).toString());
/*     */     }
/*     */     
/*     */     public Tuple2<t, byte[]> appU(byte[] state) {
/* 157 */       return new Tuple2(this.x$4, state);
/*     */     }
/*     */   }
/*     */   
/*     */   public <t> BytePickle.SPU<t> lift(Object x) {
/* 160 */     return new BytePickle$$anon$5(x);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$5 extends BytePickle.SPU<t> {
/*     */     private final Object x$5;
/*     */     
/*     */     public BytePickle$$anon$5(Object x$5) {}
/*     */     
/*     */     public BytePickle.PicklerState appP(Object a, BytePickle.PicklerState state) {
/*     */       Object object;
/* 162 */       return (((object = this.x$5) == a) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, a) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, a) : object.equals(a))))) ? 
/* 163 */         state : state;
/*     */     }
/*     */     
/*     */     public Tuple2<t, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState state) {
/* 164 */       return new Tuple2(this.x$5, state);
/*     */     }
/*     */   }
/*     */   
/*     */   public <t, u> BytePickle.PU<u> usequ(Function1 f, BytePickle.PU pa, Function1 k) {
/* 167 */     return new BytePickle$$anon$6(f, pa, k);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$6 extends BytePickle.PU<u> {
/*     */     private final Function1 f$1;
/*     */     
/*     */     private final BytePickle.PU pa$2;
/*     */     
/*     */     private final Function1 k$1;
/*     */     
/*     */     public BytePickle$$anon$6(Function1 f$1, BytePickle.PU pa$2, Function1 k$1) {}
/*     */     
/*     */     public byte[] appP(Object b, byte[] s) {
/* 169 */       Object a = this.f$1.apply(b);
/* 170 */       byte[] sPrime = this.pa$2.appP(a, s);
/* 171 */       BytePickle.PU<Object> pb = (BytePickle.PU)this.k$1.apply(a);
/* 172 */       byte[] sPrimePrime = pb.appP(b, sPrime);
/* 173 */       return sPrimePrime;
/*     */     }
/*     */     
/*     */     public Tuple2<u, byte[]> appU(byte[] s) {
/* 176 */       Tuple2 resPa = this.pa$2.appU(s);
/* 177 */       Object a = resPa._1();
/* 178 */       byte[] sPrime = (byte[])resPa._2();
/* 179 */       BytePickle.PU<u> pb = (BytePickle.PU)this.k$1.apply(a);
/* 180 */       return pb.appU(sPrime);
/*     */     }
/*     */   }
/*     */   
/*     */   public <t, u> BytePickle.SPU<u> sequ(Function1 f, BytePickle.SPU pa, Function1 k) {
/* 184 */     return new BytePickle$$anon$7(f, pa, k);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$7 extends BytePickle.SPU<u> {
/*     */     private final Function1 f$2;
/*     */     
/*     */     private final BytePickle.SPU pa$3;
/*     */     
/*     */     private final Function1 k$2;
/*     */     
/*     */     public BytePickle$$anon$7(Function1 f$2, BytePickle.SPU pa$3, Function1 k$2) {}
/*     */     
/*     */     public BytePickle.PicklerState appP(Object b, BytePickle.PicklerState s) {
/* 186 */       Object a = this.f$2.apply(b);
/* 187 */       BytePickle.PicklerState sPrime = this.pa$3.appP(a, s);
/* 188 */       BytePickle.SPU<Object> pb = (BytePickle.SPU)this.k$2.apply(a);
/* 189 */       return pb.appP(b, sPrime);
/*     */     }
/*     */     
/*     */     public Tuple2<u, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState s) {
/* 192 */       Tuple2 resPa = this.pa$3.appU(s);
/* 193 */       Object a = resPa._1();
/* 194 */       BytePickle.UnPicklerState sPrime = (BytePickle.UnPicklerState)resPa._2();
/* 195 */       BytePickle.SPU<u> pb = (BytePickle.SPU)this.k$2.apply(a);
/* 196 */       return pb.appU(sPrime);
/*     */     }
/*     */   }
/*     */   
/*     */   public final Object scala$io$BytePickle$$fst$1(Tuple2 p) {
/* 201 */     return p._1();
/*     */   }
/*     */   
/*     */   public final Object scala$io$BytePickle$$snd$1(Tuple2 p) {
/* 202 */     return p._2();
/*     */   }
/*     */   
/*     */   public <a, b> BytePickle.PU<Tuple2<a, b>> upair(BytePickle.PU pa, BytePickle.PU pb) {
/* 203 */     BytePickle$$anonfun$upair$2 bytePickle$$anonfun$upair$2 = new BytePickle$$anonfun$upair$2(pb);
/* 203 */     BytePickle$$anonfun$upair$1 bytePickle$$anonfun$upair$1 = new BytePickle$$anonfun$upair$1();
/* 203 */     return new BytePickle$$anon$6((Function1)bytePickle$$anonfun$upair$1, pa, (Function1)bytePickle$$anonfun$upair$2);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$upair$1 extends AbstractFunction1<Tuple2<a, b>, a> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final a apply(Tuple2 p) {
/* 203 */       return (a)BytePickle$.MODULE$.scala$io$BytePickle$$fst$1(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$upair$2 extends AbstractFunction1<a, BytePickle.PU<Tuple2<a, b>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.PU pb$1;
/*     */     
/*     */     public final BytePickle.PU<Tuple2<a, b>> apply(Object x) {
/* 203 */       return BytePickle$.MODULE$.usequ((Function1<Tuple2<a, b>, ?>)new BytePickle$$anonfun$upair$2$$anonfun$apply$1(this), this.pb$1, (Function1<?, BytePickle.PU<Tuple2<a, b>>>)new BytePickle$$anonfun$upair$2$$anonfun$apply$2(this, x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$upair$2(BytePickle.PU pb$1) {}
/*     */     
/*     */     public class BytePickle$$anonfun$upair$2$$anonfun$apply$1 extends AbstractFunction1<Tuple2<a, b>, b> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final b apply(Tuple2 p) {
/* 203 */         return (b)BytePickle$.MODULE$.scala$io$BytePickle$$snd$1(p);
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$upair$2$$anonfun$apply$1(BytePickle$$anonfun$upair$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class BytePickle$$anonfun$upair$2$$anonfun$apply$2 extends AbstractFunction1<b, BytePickle.PU<Tuple2<a, b>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object x$6;
/*     */       
/*     */       public final BytePickle.PU<Tuple2<a, b>> apply(Object y) {
/* 203 */         return BytePickle$.MODULE$.ulift(new Tuple2(this.x$6, y));
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$upair$2$$anonfun$apply$2(BytePickle$$anonfun$upair$2 $outer, Object x$6) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public final Object scala$io$BytePickle$$fst$2(Tuple2 p) {
/* 207 */     return p._1();
/*     */   }
/*     */   
/*     */   public final Object scala$io$BytePickle$$snd$2(Tuple2 p) {
/* 208 */     return p._2();
/*     */   }
/*     */   
/*     */   public <a, b> BytePickle.SPU<Tuple2<a, b>> pair(BytePickle.SPU pa, BytePickle.SPU pb) {
/* 209 */     BytePickle$$anonfun$pair$2 bytePickle$$anonfun$pair$2 = new BytePickle$$anonfun$pair$2(pb);
/* 209 */     BytePickle$$anonfun$pair$1 bytePickle$$anonfun$pair$1 = new BytePickle$$anonfun$pair$1();
/* 209 */     return new BytePickle$$anon$7((Function1)bytePickle$$anonfun$pair$1, pa, (Function1)bytePickle$$anonfun$pair$2);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$pair$1 extends AbstractFunction1<Tuple2<a, b>, a> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final a apply(Tuple2 p) {
/* 209 */       return (a)BytePickle$.MODULE$.scala$io$BytePickle$$fst$2(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$pair$2 extends AbstractFunction1<a, BytePickle.SPU<Tuple2<a, b>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.SPU pb$2;
/*     */     
/*     */     public final BytePickle.SPU<Tuple2<a, b>> apply(Object x) {
/* 209 */       return BytePickle$.MODULE$.sequ((Function1<Tuple2<a, b>, ?>)new BytePickle$$anonfun$pair$2$$anonfun$apply$3(this), this.pb$2, (Function1<?, BytePickle.SPU<Tuple2<a, b>>>)new BytePickle$$anonfun$pair$2$$anonfun$apply$4(this, x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$pair$2(BytePickle.SPU pb$2) {}
/*     */     
/*     */     public class BytePickle$$anonfun$pair$2$$anonfun$apply$3 extends AbstractFunction1<Tuple2<a, b>, b> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final b apply(Tuple2 p) {
/* 209 */         return (b)BytePickle$.MODULE$.scala$io$BytePickle$$snd$2(p);
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$pair$2$$anonfun$apply$3(BytePickle$$anonfun$pair$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class BytePickle$$anonfun$pair$2$$anonfun$apply$4 extends AbstractFunction1<b, BytePickle.SPU<Tuple2<a, b>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object x$7;
/*     */       
/*     */       public final BytePickle.SPU<Tuple2<a, b>> apply(Object y) {
/* 209 */         return BytePickle$.MODULE$.lift(new Tuple2(this.x$7, y));
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$pair$2$$anonfun$apply$4(BytePickle$$anonfun$pair$2 $outer, Object x$7) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public final Object scala$io$BytePickle$$fst$3(Tuple3 p) {
/* 213 */     return p._1();
/*     */   }
/*     */   
/*     */   public final Object scala$io$BytePickle$$snd$3(Tuple3 p) {
/* 214 */     return p._2();
/*     */   }
/*     */   
/*     */   public final Object scala$io$BytePickle$$trd$1(Tuple3 p) {
/* 215 */     return p._3();
/*     */   }
/*     */   
/*     */   public <a, b, c> BytePickle.SPU<Tuple3<a, b, c>> triple(BytePickle.SPU pa, BytePickle.SPU pb, BytePickle.SPU pc) {
/* 217 */     BytePickle$$anonfun$triple$2 bytePickle$$anonfun$triple$2 = 
/* 218 */       new BytePickle$$anonfun$triple$2(pb, pc);
/*     */     BytePickle$$anonfun$triple$1 bytePickle$$anonfun$triple$1 = new BytePickle$$anonfun$triple$1();
/*     */     return new BytePickle$$anon$7((Function1)bytePickle$$anonfun$triple$1, pa, (Function1)bytePickle$$anonfun$triple$2);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$triple$1 extends AbstractFunction1<Tuple3<a, b, c>, a> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final a apply(Tuple3 p) {
/*     */       return (a)BytePickle$.MODULE$.scala$io$BytePickle$$fst$3(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$triple$2 extends AbstractFunction1<a, BytePickle.SPU<Tuple3<a, b, c>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.SPU pb$3;
/*     */     
/*     */     public final BytePickle.SPU pc$1;
/*     */     
/*     */     public final BytePickle.SPU<Tuple3<a, b, c>> apply(Object x) {
/* 218 */       return BytePickle$.MODULE$.sequ((Function1<Tuple3<a, b, c>, ?>)new BytePickle$$anonfun$triple$2$$anonfun$apply$5(this), this.pb$3, 
/* 219 */           (Function1<?, BytePickle.SPU<Tuple3<a, b, c>>>)new BytePickle$$anonfun$triple$2$$anonfun$apply$6(this, x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$triple$2(BytePickle.SPU pb$3, BytePickle.SPU pc$1) {}
/*     */     
/*     */     public class BytePickle$$anonfun$triple$2$$anonfun$apply$5 extends AbstractFunction1<Tuple3<a, b, c>, b> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final b apply(Tuple3 p) {
/*     */         return (b)BytePickle$.MODULE$.scala$io$BytePickle$$snd$3(p);
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$triple$2$$anonfun$apply$5(BytePickle$$anonfun$triple$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class BytePickle$$anonfun$triple$2$$anonfun$apply$6 extends AbstractFunction1<b, BytePickle.SPU<Tuple3<a, b, c>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Object x$8;
/*     */       
/*     */       public final BytePickle.SPU<Tuple3<a, b, c>> apply(Object y) {
/* 219 */         return BytePickle$.MODULE$.sequ((Function1<Tuple3<a, b, c>, ?>)new BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$7(this), this.$outer.pc$1, 
/* 220 */             (Function1<?, BytePickle.SPU<Tuple3<a, b, c>>>)new BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$8(this, y));
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$triple$2$$anonfun$apply$6(BytePickle$$anonfun$triple$2 $outer, Object x$8) {}
/*     */       
/*     */       public class BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$7 extends AbstractFunction1<Tuple3<a, b, c>, c> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final c apply(Tuple3 p) {
/*     */           return (c)BytePickle$.MODULE$.scala$io$BytePickle$$trd$1(p);
/*     */         }
/*     */         
/*     */         public BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$7(BytePickle$$anonfun$triple$2$$anonfun$apply$6 $outer) {}
/*     */       }
/*     */       
/*     */       public class BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$8 extends AbstractFunction1<c, BytePickle.SPU<Tuple3<a, b, c>>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Object y$1;
/*     */         
/*     */         public final BytePickle.SPU<Tuple3<a, b, c>> apply(Object z) {
/* 220 */           return BytePickle$.MODULE$.lift(new Tuple3(this.$outer.x$8, this.y$1, z));
/*     */         }
/*     */         
/*     */         public BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$8(BytePickle$$anonfun$triple$2$$anonfun$apply$6 $outer, Object y$1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public <a, b> BytePickle.PU<b> uwrap(Function1 i, Function1 j, BytePickle.PU pa) {
/* 224 */     BytePickle$$anonfun$uwrap$1 bytePickle$$anonfun$uwrap$1 = new BytePickle$$anonfun$uwrap$1(i);
/* 224 */     return new BytePickle$$anon$6(j, pa, (Function1)bytePickle$$anonfun$uwrap$1);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$uwrap$1 extends AbstractFunction1<a, BytePickle.PU<b>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 i$1;
/*     */     
/*     */     public final BytePickle.PU<b> apply(Object x) {
/* 224 */       return BytePickle$.MODULE$.ulift((b)this.i$1.apply(x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$uwrap$1(Function1 i$1) {}
/*     */   }
/*     */   
/*     */   public <a, b> BytePickle.SPU<b> wrap(Function1 i, Function1 j, BytePickle.SPU pa) {
/* 227 */     BytePickle$$anonfun$wrap$1 bytePickle$$anonfun$wrap$1 = new BytePickle$$anonfun$wrap$1(i);
/* 227 */     return new BytePickle$$anon$7(j, pa, (Function1)bytePickle$$anonfun$wrap$1);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$wrap$1 extends AbstractFunction1<a, BytePickle.SPU<b>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 i$2;
/*     */     
/*     */     public final BytePickle.SPU<b> apply(Object x) {
/* 227 */       return BytePickle$.MODULE$.lift((b)this.i$2.apply(x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$wrap$1(Function1 i$2) {}
/*     */   }
/*     */   
/*     */   public byte[] appendByte(byte[] a, int b) {
/* 230 */     (new byte[2][])[0] = a;
/* 230 */     (new byte[1])[0] = (byte)b;
/* 230 */     (new byte[2][])[1] = new byte[1];
/* 230 */     return (byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */   }
/*     */   
/*     */   public byte[] nat2Bytes(int x) {
/* 233 */     ArrayBuffer buf = new ArrayBuffer();
/* 239 */     int y = x >>> 7;
/* 240 */     if (y != 0)
/* 240 */       writeNatPrefix$1(y, buf); 
/* 241 */     buf.$plus$eq(BoxesRunTime.boxToByte((byte)(x & 0x7F)));
/* 242 */     return (byte[])buf.toArray(scala.reflect.ClassTag$.MODULE$.Byte());
/*     */   }
/*     */   
/*     */   private final void writeNatPrefix$1(int x, ArrayBuffer buf$1) {
/*     */     int y = x >>> 7;
/*     */     if (y != 0)
/*     */       writeNatPrefix$1(y, buf$1); 
/*     */     buf$1.$plus$eq(BoxesRunTime.boxToByte((byte)(x & 0x7F | 0x80)));
/*     */   }
/*     */   
/*     */   public BytePickle.SPU<Object> nat() {
/* 245 */     return new BytePickle$$anon$8();
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$8 extends BytePickle.SPU<Object> {
/*     */     public BytePickle.PicklerState appP(int n, BytePickle.PicklerState s) {
/* 247 */       (new byte[2][])[0] = s.stream();
/* 247 */       (new byte[2][])[1] = BytePickle$.MODULE$.nat2Bytes(n);
/* 247 */       return new BytePickle.PicklerState((byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte()), s.dict());
/*     */     }
/*     */     
/*     */     public Tuple2<Object, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState s) {
/* 250 */       IntRef num = new IntRef(0);
/* 261 */       return new Tuple2(BoxesRunTime.boxToInteger(readNat$2(s, num)), new BytePickle.UnPicklerState((byte[])scala.Predef$.MODULE$.byteArrayOps(s.stream()).slice(num.elem, (s.stream()).length), s.dict()));
/*     */     }
/*     */     
/*     */     private final int readNat$2(BytePickle.UnPicklerState s$2, IntRef num$2) {
/*     */       int x = 0;
/*     */       while (true) {
/*     */         b = s$2.stream()[num$2.elem];
/*     */         num$2.elem++;
/*     */         x = (x << 7) + (b & 0x7F);
/*     */         if ((b & 0x80) == 0)
/*     */           return x; 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public BytePickle.SPU<Object> byte() {
/* 265 */     return new BytePickle$$anon$9();
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$9 extends BytePickle.SPU<Object> {
/*     */     public BytePickle.PicklerState appP(byte b, BytePickle.PicklerState s) {
/* 267 */       (new byte[2][])[0] = s.stream();
/* 267 */       (new byte[1])[0] = b;
/* 267 */       (new byte[2][])[1] = new byte[1];
/* 267 */       return new BytePickle.PicklerState((byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte()), s.dict());
/*     */     }
/*     */     
/*     */     public Tuple2<Object, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState s) {
/* 269 */       return new Tuple2(BoxesRunTime.boxToByte(s.stream()[0]), new BytePickle.UnPicklerState((byte[])scala.Predef$.MODULE$.byteArrayOps(s.stream()).slice(1, (s.stream()).length), s.dict()));
/*     */     }
/*     */   }
/*     */   
/*     */   public BytePickle.SPU<String> string() {
/* 272 */     BytePickle.SPU<byte[]> sPU = 
/*     */       
/* 275 */       bytearray();
/*     */     BytePickle$$anonfun$string$2 bytePickle$$anonfun$string$2 = new BytePickle$$anonfun$string$2();
/*     */     BytePickle$$anonfun$string$1 bytePickle$$anonfun$string$1 = new BytePickle$$anonfun$string$1();
/*     */     BytePickle$$anonfun$wrap$1 bytePickle$$anonfun$wrap$1 = new BytePickle$$anonfun$wrap$1((Function1)bytePickle$$anonfun$string$1);
/*     */     BytePickle$$anon$7 bytePickle$$anon$7 = new BytePickle$$anon$7((Function1)bytePickle$$anonfun$string$2, sPU, (Function1)bytePickle$$anonfun$wrap$1);
/*     */     return new BytePickle$$anon$3(bytePickle$$anon$7);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$string$1 extends AbstractFunction1<byte[], String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(byte[] a) {
/*     */       return scala.Predef$.MODULE$.charArrayOps(Codec$.MODULE$.fromUTF8(a)).mkString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$string$2 extends AbstractFunction1<String, byte[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final byte[] apply(String s) {
/*     */       return Codec$.MODULE$.toUTF8(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public BytePickle.SPU<byte[]> bytearray() {
/* 279 */     BytePickle.SPU<List<?>> sPU = list(new BytePickle$$anon$9());
/* 279 */     BytePickle$$anonfun$bytearray$2 bytePickle$$anonfun$bytearray$2 = new BytePickle$$anonfun$bytearray$2();
/* 279 */     BytePickle$$anonfun$bytearray$1 bytePickle$$anonfun$bytearray$1 = new BytePickle$$anonfun$bytearray$1();
/* 279 */     BytePickle$$anonfun$wrap$1 bytePickle$$anonfun$wrap$1 = new BytePickle$$anonfun$wrap$1((Function1)bytePickle$$anonfun$bytearray$1);
/* 279 */     return new BytePickle$$anon$7((Function1)bytePickle$$anonfun$bytearray$2, sPU, (Function1)bytePickle$$anonfun$wrap$1);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$bytearray$1 extends AbstractFunction1<List<Object>, byte[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final byte[] apply(List l) {
/* 279 */       return (byte[])l.toArray(scala.reflect.ClassTag$.MODULE$.Byte());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$bytearray$2 extends AbstractFunction1<byte[], List<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<Object> apply(byte[] x$1) {
/* 279 */       return scala.Predef$.MODULE$.byteArrayOps(x$1).toList();
/*     */     }
/*     */   }
/*     */   
/*     */   public final int scala$io$BytePickle$$toEnum$1(boolean b) {
/* 283 */     return b ? 1 : 0;
/*     */   }
/*     */   
/*     */   public final boolean scala$io$BytePickle$$fromEnum$1(int n) {
/* 284 */     return !(n == 0);
/*     */   }
/*     */   
/*     */   public BytePickle.SPU<Object> bool() {
/* 285 */     BytePickle$$anon$8 bytePickle$$anon$8 = new BytePickle$$anon$8();
/* 285 */     BytePickle$$anonfun$bool$2 bytePickle$$anonfun$bool$2 = new BytePickle$$anonfun$bool$2();
/* 285 */     BytePickle$$anonfun$bool$1 bytePickle$$anonfun$bool$1 = new BytePickle$$anonfun$bool$1();
/* 285 */     BytePickle$$anonfun$wrap$1 bytePickle$$anonfun$wrap$1 = new BytePickle$$anonfun$wrap$1((Function1)bytePickle$$anonfun$bool$1);
/* 285 */     return new BytePickle$$anon$7((Function1)bytePickle$$anonfun$bool$2, bytePickle$$anon$8, (Function1)bytePickle$$anonfun$wrap$1);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$bool$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(int n) {
/* 285 */       return BytePickle$.MODULE$.scala$io$BytePickle$$fromEnum$1(n);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int n) {
/* 285 */       return BytePickle$.MODULE$.scala$io$BytePickle$$fromEnum$1(n);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$bool$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(boolean b) {
/* 285 */       return BytePickle$.MODULE$.scala$io$BytePickle$$toEnum$1(b);
/*     */     }
/*     */   }
/*     */   
/*     */   public final List scala$io$BytePickle$$pairToList$1(Tuple2 p) {
/* 290 */     Object object = p._1();
/* 290 */     return ((List)p._2()).$colon$colon(object);
/*     */   }
/*     */   
/*     */   public final Tuple2 scala$io$BytePickle$$listToPair$1(List l) {
/* 292 */     if (l instanceof scala.collection.immutable..colon.colon) {
/* 292 */       scala.collection.immutable..colon.colon colon = (scala.collection.immutable..colon.colon)l;
/* 292 */       return new Tuple2(colon.hd$1(), colon.tl$1());
/*     */     } 
/* 292 */     throw new MatchError(l);
/*     */   }
/*     */   
/*     */   public <A> BytePickle.PU<List<A>> ufixedList(BytePickle.PU<?> pa, int n) {
/* 294 */     scala.collection.immutable.Nil$ nil$ = scala.collection.immutable.Nil$.MODULE$;
/* 296 */     BytePickle.PU<Tuple2<?, ?>> pU = upair(pa, ufixedList(pa, n - 1));
/* 296 */     BytePickle$$anonfun$ufixedList$2 bytePickle$$anonfun$ufixedList$2 = new BytePickle$$anonfun$ufixedList$2();
/* 296 */     BytePickle$$anonfun$ufixedList$1 bytePickle$$anonfun$ufixedList$1 = new BytePickle$$anonfun$ufixedList$1();
/* 296 */     BytePickle$$anonfun$uwrap$1 bytePickle$$anonfun$uwrap$1 = new BytePickle$$anonfun$uwrap$1((Function1)bytePickle$$anonfun$ufixedList$1);
/* 296 */     return (n == 0) ? new BytePickle$$anon$4(nil$) : new BytePickle$$anon$6((Function1)bytePickle$$anonfun$ufixedList$2, pU, (Function1)bytePickle$$anonfun$uwrap$1);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$ufixedList$1 extends AbstractFunction1<Tuple2<A, List<A>>, List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<A> apply(Tuple2 p) {
/* 296 */       return BytePickle$.MODULE$.scala$io$BytePickle$$pairToList$1(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$ufixedList$2 extends AbstractFunction1<List<A>, Tuple2<A, List<A>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<A, List<A>> apply(List l) {
/* 296 */       return BytePickle$.MODULE$.scala$io$BytePickle$$listToPair$1(l);
/*     */     }
/*     */   }
/*     */   
/*     */   public final List scala$io$BytePickle$$pairToList$2(Tuple2 p) {
/* 301 */     Object object = p._1();
/* 301 */     return ((List)p._2()).$colon$colon(object);
/*     */   }
/*     */   
/*     */   public final Tuple2 scala$io$BytePickle$$listToPair$2(List l) {
/* 303 */     if (l instanceof scala.collection.immutable..colon.colon) {
/* 303 */       scala.collection.immutable..colon.colon colon = (scala.collection.immutable..colon.colon)l;
/* 303 */       return new Tuple2(colon.hd$1(), colon.tl$1());
/*     */     } 
/* 303 */     throw new MatchError(l);
/*     */   }
/*     */   
/*     */   public <a> BytePickle.SPU<List<a>> fixedList(BytePickle.SPU<?> pa, int n) {
/* 305 */     scala.collection.immutable.Nil$ nil$ = scala.collection.immutable.Nil$.MODULE$;
/* 307 */     BytePickle.SPU<Tuple2<?, ?>> sPU = pair(pa, fixedList(pa, n - 1));
/* 307 */     BytePickle$$anonfun$fixedList$2 bytePickle$$anonfun$fixedList$2 = new BytePickle$$anonfun$fixedList$2();
/* 307 */     BytePickle$$anonfun$fixedList$1 bytePickle$$anonfun$fixedList$1 = new BytePickle$$anonfun$fixedList$1();
/* 307 */     BytePickle$$anonfun$wrap$1 bytePickle$$anonfun$wrap$1 = new BytePickle$$anonfun$wrap$1((Function1)bytePickle$$anonfun$fixedList$1);
/* 307 */     return (n == 0) ? new BytePickle$$anon$5(nil$) : new BytePickle$$anon$7((Function1)bytePickle$$anonfun$fixedList$2, sPU, (Function1)bytePickle$$anonfun$wrap$1);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$fixedList$1 extends AbstractFunction1<Tuple2<a, List<a>>, List<a>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<a> apply(Tuple2 p) {
/* 307 */       return BytePickle$.MODULE$.scala$io$BytePickle$$pairToList$2(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$fixedList$2 extends AbstractFunction1<List<a>, Tuple2<a, List<a>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<a, List<a>> apply(List l) {
/* 307 */       return BytePickle$.MODULE$.scala$io$BytePickle$$listToPair$2(l);
/*     */     }
/*     */   }
/*     */   
/*     */   public <a> BytePickle.SPU<List<a>> list(BytePickle.SPU pa) {
/* 311 */     BytePickle$$anonfun$list$2 bytePickle$$anonfun$list$2 = new BytePickle$$anonfun$list$2(pa);
/* 311 */     BytePickle$$anon$8 bytePickle$$anon$8 = new BytePickle$$anon$8();
/* 311 */     BytePickle$$anonfun$list$1 bytePickle$$anonfun$list$1 = new BytePickle$$anonfun$list$1();
/* 311 */     return new BytePickle$$anon$7((Function1)bytePickle$$anonfun$list$1, bytePickle$$anon$8, (Function1)bytePickle$$anonfun$list$2);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$list$1 extends AbstractFunction1<List<a>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(List l) {
/* 311 */       return l.length();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$list$2 extends AbstractFunction1<Object, BytePickle.SPU<List<a>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.SPU pa$4;
/*     */     
/*     */     public final BytePickle.SPU<List<a>> apply(int n) {
/* 311 */       return BytePickle$.MODULE$.fixedList(this.pa$4, n);
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$list$2(BytePickle.SPU pa$4) {}
/*     */   }
/*     */   
/*     */   public <a> BytePickle.PU<List<a>> ulist(BytePickle.PU pa) {
/* 314 */     BytePickle$$anonfun$ulist$2 bytePickle$$anonfun$ulist$2 = new BytePickle$$anonfun$ulist$2(pa);
/* 314 */     BytePickle$$anon$2 bytePickle$$anon$2 = new BytePickle$$anon$2();
/* 314 */     BytePickle$$anonfun$ulist$1 bytePickle$$anonfun$ulist$1 = new BytePickle$$anonfun$ulist$1();
/* 314 */     return new BytePickle$$anon$6((Function1)bytePickle$$anonfun$ulist$1, bytePickle$$anon$2, (Function1)bytePickle$$anonfun$ulist$2);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$ulist$1 extends AbstractFunction1<List<a>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(List l) {
/* 314 */       return l.length();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$ulist$2 extends AbstractFunction1<Object, BytePickle.PU<List<a>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.PU pa$5;
/*     */     
/*     */     public final BytePickle.PU<List<a>> apply(int n) {
/* 314 */       return BytePickle$.MODULE$.ufixedList(this.pa$5, n);
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$ulist$2(BytePickle.PU pa$5) {}
/*     */   }
/*     */   
/*     */   public <a> BytePickle.SPU<a> data(Function1 tag, List ps) {
/* 317 */     BytePickle$$anonfun$data$1 bytePickle$$anonfun$data$1 = new BytePickle$$anonfun$data$1(ps);
/* 317 */     BytePickle$$anon$8 bytePickle$$anon$8 = new BytePickle$$anon$8();
/* 317 */     return new BytePickle$$anon$7(tag, bytePickle$$anon$8, (Function1)bytePickle$$anonfun$data$1);
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$data$1 extends AbstractFunction1<Object, BytePickle.SPU<a>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final List ps$1;
/*     */     
/*     */     public final BytePickle.SPU<a> apply(int x) {
/* 317 */       return (BytePickle.SPU<a>)((Function0)this.ps$1.apply(x)).apply();
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$data$1(List ps$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\BytePickle$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */