/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.mutable.ArrayOps;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParSet;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t5aaB\001\003!\003\r\ta\002\002\b'\026$H*[6f\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\002\001+\rA1#H\n\007\001%iqE\013\031\021\005)YQ\"\001\003\n\0051!!AB!osJ+g\r\005\003\017\037EaR\"\001\002\n\005A\021!\001D%uKJ\f'\r\\3MS.,\007C\001\n\024\031\001!Q\001\006\001C\002U\021\021!Q\t\003-e\001\"AC\f\n\005a!!a\002(pi\"Lgn\032\t\003\025iI!a\007\003\003\007\005s\027\020\005\002\023;\0211a\004\001CC\002}\021A\001\0265jgF\021a\003\t\n\004C\r\"c\001\002\022\001\001\001\022A\002\020:fM&tW-\\3oiz\002BA\004\001\0229A\031a\"J\t\n\005\031\022!aA*fiB!a\002K\t\035\023\tI#A\001\006HK:\034V\r\036'jW\026\004Ba\013\030\02295\tAF\003\002.\005\0059q-\0328fe&\034\027BA\030-\0051\031VO\031;sC\016$\030M\0317f!\021q\021'E\032\n\005I\022!A\004)be\006dG.\0327ju\006\024G.\032\t\004i]\nR\"A\033\013\005Y\022\021\001\0039be\006dG.\0327\n\005a*$A\002)beN+G\017C\003;\001\021\0051(\001\004%S:LG\017\n\013\002yA\021!\"P\005\003}\021\021A!\0268ji\")\001\t\001D\001\003\006)Q-\0349usV\tA\004\003\004D\001\001&\t\006R\001\013]\026<()^5mI\026\024X#A#\021\t\031K\025\003H\007\002\017*\021\001JA\001\b[V$\030M\0317f\023\tQuIA\004Ck&dG-\032:\t\r1\003\001\025\"\025N\003-\001\030M]\"p[\nLg.\032:\026\0039\003B\001N(\022g%\021\001+\016\002\t\007>l'-\0338fe\")!\013\001C!'\006)Ao\\*fcV\tA\013E\002\017+FI!A\026\002\003\007M+\027\017C\003Y\001\021\005\023,\001\005u_\n+hMZ3s+\tQv,F\001\\!\r1ELX\005\003;\036\023aAQ;gM\026\024\bC\001\n`\t\025\001wK1\001b\005\t\t\025'\005\002\0223!)1\r\001C!I\006\031Q.\0319\026\007\025\004\b\016\006\002geR\021qM\033\t\003%!$Q!\0332C\002U\021A\001\0265bi\")1N\031a\002Y\006\021!M\032\t\006W5drnZ\005\003]2\022AbQ1o\005VLG\016\032$s_6\004\"A\0059\005\013E\024'\031A\013\003\003\tCQa\0352A\002Q\f\021A\032\t\005\025U\fr.\003\002w\t\tIa)\0368di&|g.\r\025\006Ebt\030\021\001\t\003srl\021A\037\006\003w\022\t!\"\0318o_R\fG/[8o\023\ti(PA\005nS\036\024\030\r^5p]\006\nq0A TKRtS.\0319!]><\bE]3ukJt7\017I1!'\026$H\006I:pA%$\be^5mY\002\"\027n]2be\022\004C-\0369mS\016\fG/\032\021wC2,Xm\035\030\"\005\005\r\021!\002\032/q9\002\004bBA\004\001\031\005\021\021B\001\tG>tG/Y5ogR!\0211BA\t!\rQ\021QB\005\004\003\037!!a\002\"p_2,\027M\034\005\b\003'\t)\0011\001\022\003\021)G.Z7\t\017\005]\001A\"\001\002\032\005)A\005\0357vgR\031A$a\007\t\017\005M\021Q\003a\001#!9\021q\003\001\005\002\005}Ac\002\017\002\"\005\025\022\021\006\005\b\003G\ti\0021\001\022\003\025)G.Z72\021\035\t9#!\bA\002E\tQ!\0327f[JB\001\"a\013\002\036\001\007\021QF\001\006K2,Wn\035\t\005\025\005=\022#C\002\0022\021\021!\002\020:fa\026\fG/\0323?\021\035\t)\004\001C\001\003o\t!\002\n9mkN$\003\017\\;t)\ra\022\021\b\005\t\003W\t\031\0041\001\002<A!a\"!\020\022\023\r\tyD\001\002\023\017\026tGK]1wKJ\034\030M\0317f\037:\034W\rC\004\002D\0011\t!!\022\002\r\021j\027N\\;t)\ra\022q\t\005\b\003'\t\t\0051\001\022\021\035\tY\005\001C!\003\033\nq![:F[B$\0300\006\002\002\f!9\021\021\013\001\005\002\005M\023!B;oS>tGc\001\017\002V!A\021qKA(\001\004\tI&\001\003uQ\006$\b\003\002\b\002\\EI1!!\030\003\005\0319UM\\*fi\"9\021\021\r\001\005\002\005\r\024\001\0023jM\032$2\001HA3\021!\t9&a\030A\002\005e\003bBA5\001\021\005\0211N\001\bgV\0247/\032;t)\021\ti'a\035\021\t9\ty\007H\005\004\003c\022!\001C%uKJ\fGo\034:\t\021\005U\024q\ra\001\003o\n1\001\\3o!\rQ\021\021P\005\004\003w\"!aA%oi\"9\021\021\016\001\005\002\005}TCAA7\r\031\t\031\t\001\003\002\006\nQ1+\0362tKR\034\030\n\036:\024\t\005\005\025q\021\t\005\035\005%E$C\002\002\f\n\021\001#\0212tiJ\f7\r^%uKJ\fGo\034:\t\027\005=\025\021\021B\001B\003%\021\021S\001\005K2l7\017\005\003\017\003'\013\022bAAK\005\tQ\021J\0343fq\026$7+Z9\t\027\005U\024\021\021B\001B\003%\021q\017\005\t\0037\013\t\t\"\001\002\036\0061A(\0338jiz\"b!a(\002$\006\025\006\003BAQ\003\003k\021\001\001\005\t\003\037\013I\n1\001\002\022\"A\021QOAM\001\004\t9\b\003\006\002*\006\005%\031!C\005\003W\013A!\0333ygV\021\021Q\026\t\006\025\005=\026qO\005\004\003c#!!B!se\006L\b\"CA[\003\003\003\013\021BAW\003\025IG\r_:!\021)\tI,!!A\002\023%\021QJ\001\t?\"\f7OT3yi\"Q\021QXAA\001\004%I!a0\002\031}C\027m\035(fqR|F%Z9\025\007q\n\t\r\003\006\002D\006m\026\021!a\001\003\027\t1\001\037\0232\021%\t9-!!!B\023\tY!A\005`Q\006\034h*\032=uA!A\0211ZAA\t\003\ti%A\004iCNtU\r\037;\t\021\005=\027\021\021C\001\003#\fAA\\3yiR\tA\004C\004\002V\002!\t%a6\002\031M$(/\0338h!J,g-\033=\026\005\005e\007\003BAn\003Ct1ACAo\023\r\ty\016B\001\007!J,G-\0324\n\t\005\r\030Q\035\002\007'R\024\030N\\4\013\007\005}G\001C\004\002j\002!\t%a;\002\021Q|7\013\036:j]\036$\"!!7\t\031\005=\b!!A\001\n\023\t\tPa\002\002\023M,\b/\032:%[\006\004XCBAz\005\003\tI\020\006\003\002v\n\rA\003BA|\003w\0042AEA}\t\031I\027Q\036b\001+!91.!<A\004\005u\bcB\026n9\005}\030q\037\t\004%\t\005AAB9\002n\n\007Q\003C\004t\003[\004\rA!\002\021\013))\030#a@\n\007\r\024I!C\002\003\f\t\021q\002\026:bm\026\0248/\0312mK2K7.\032")
/*     */ public interface SetLike<A, This extends SetLike<A, This> & Set<A>> extends IterableLike<A, This>, GenSetLike<A, This>, Subtractable<A, This>, Parallelizable<A, ParSet<A>> {
/*     */   <B, That> That scala$collection$SetLike$$super$map(Function1<A, B> paramFunction1, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   This empty();
/*     */   
/*     */   Builder<A, This> newBuilder();
/*     */   
/*     */   Combiner<A, ParSet<A>> parCombiner();
/*     */   
/*     */   Seq<A> toSeq();
/*     */   
/*     */   <A1> Buffer<A1> toBuffer();
/*     */   
/*     */   <B, That> That map(Function1<A, B> paramFunction1, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   boolean contains(A paramA);
/*     */   
/*     */   This $plus(A paramA);
/*     */   
/*     */   This $plus(A paramA1, A paramA2, Seq<A> paramSeq);
/*     */   
/*     */   This $plus$plus(GenTraversableOnce<A> paramGenTraversableOnce);
/*     */   
/*     */   This $minus(A paramA);
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   This union(GenSet<A> paramGenSet);
/*     */   
/*     */   This diff(GenSet<A> paramGenSet);
/*     */   
/*     */   Iterator<This> subsets(int paramInt);
/*     */   
/*     */   Iterator<This> subsets();
/*     */   
/*     */   String stringPrefix();
/*     */   
/*     */   String toString();
/*     */   
/*     */   public class SetLike$$anonfun$$plus$plus$1 extends AbstractFunction2<This, A, This> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final This apply(Set<Object> x$2, Object x$3) {
/* 128 */       return (This)x$2.$plus(x$3);
/*     */     }
/*     */     
/*     */     public SetLike$$anonfun$$plus$plus$1(SetLike $outer) {}
/*     */   }
/*     */   
/*     */   public class SetLike$$anon$1 extends AbstractIterator<This> {
/*     */     private final IndexedSeq<A> elms;
/*     */     
/*     */     private int len;
/*     */     
/*     */     private Iterator<This> itr;
/*     */     
/*     */     public SetLike$$anon$1(SetLike $outer) {
/* 176 */       this.elms = $outer.toIndexedSeq();
/* 177 */       this.len = 0;
/* 178 */       this.itr = (Iterator)Iterator$.MODULE$.empty();
/*     */     }
/*     */     
/*     */     private IndexedSeq<A> elms() {
/*     */       return this.elms;
/*     */     }
/*     */     
/*     */     private int len() {
/*     */       return this.len;
/*     */     }
/*     */     
/*     */     private void len_$eq(int x$1) {
/*     */       this.len = x$1;
/*     */     }
/*     */     
/*     */     private Iterator<This> itr() {
/* 178 */       return this.itr;
/*     */     }
/*     */     
/*     */     private void itr_$eq(Iterator<This> x$1) {
/* 178 */       this.itr = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 180 */       return (len() <= elms().size() || itr().hasNext());
/*     */     }
/*     */     
/*     */     public This next() {
/* 185 */       itr_$eq(new SetLike.SubsetsItr(this.$outer, (IndexedSeq<A>)elms(), len()));
/* 186 */       len_$eq(len() + 1);
/*     */       itr().hasNext() ? BoxedUnit.UNIT : ((len() > elms().size()) ? Iterator$.MODULE$.empty().next() : BoxedUnit.UNIT);
/* 190 */       return itr().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public class SubsetsItr extends AbstractIterator<This> {
/*     */     public final IndexedSeq<A> scala$collection$SetLike$SubsetsItr$$elms;
/*     */     
/*     */     private final int len;
/*     */     
/*     */     private final int[] scala$collection$SetLike$SubsetsItr$$idxs;
/*     */     
/*     */     private boolean _hasNext;
/*     */     
/*     */     public SubsetsItr(SetLike $outer, IndexedSeq<A> elms, int len) {
/* 202 */       this.scala$collection$SetLike$SubsetsItr$$idxs = Array$.MODULE$.range(0, len + 1);
/* 203 */       this._hasNext = true;
/* 204 */       scala$collection$SetLike$SubsetsItr$$idxs()[len] = elms.size();
/*     */     }
/*     */     
/*     */     public int[] scala$collection$SetLike$SubsetsItr$$idxs() {
/*     */       return this.scala$collection$SetLike$SubsetsItr$$idxs;
/*     */     }
/*     */     
/*     */     private boolean _hasNext() {
/*     */       return this._hasNext;
/*     */     }
/*     */     
/*     */     private void _hasNext_$eq(boolean x$1) {
/*     */       this._hasNext = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 206 */       return _hasNext();
/*     */     }
/*     */     
/*     */     public This next() {
/* 208 */       hasNext() ? BoxedUnit.UNIT : Iterator$.MODULE$.empty().next();
/* 210 */       Builder buf = scala$collection$SetLike$SubsetsItr$$$outer().newBuilder();
/* 211 */       int[] arrayOfInt1 = scala$collection$SetLike$SubsetsItr$$idxs();
/* 211 */       Predef$ predef$1 = Predef$.MODULE$;
/* 211 */       int j = this.len, arrayOfInt2[] = (int[])IndexedSeqOptimized$class.slice((IndexedSeqOptimized)new ArrayOps.ofInt(arrayOfInt1), 0, j);
/* 211 */       Predef$ predef$2 = Predef$.MODULE$;
/* 211 */       IndexedSeqOptimized$class.foreach((IndexedSeqOptimized)new ArrayOps.ofInt(arrayOfInt2), (Function1)new SetLike$SubsetsItr$$anonfun$next$2(this, (SubsetsItr)buf));
/* 212 */       Set result = (Set)buf.result();
/* 214 */       int i = this.len - 1;
/* 215 */       for (; i >= 0 && scala$collection$SetLike$SubsetsItr$$idxs()[i] == scala$collection$SetLike$SubsetsItr$$idxs()[i + 1] - 1; i--);
/* 217 */       if (i < 0) {
/* 217 */         _hasNext_$eq(false);
/*     */       } else {
/* 219 */         scala$collection$SetLike$SubsetsItr$$idxs()[i] = scala$collection$SetLike$SubsetsItr$$idxs()[i] + 1;
/* 220 */         int k = i + 1;
/* 220 */         Predef$ predef$ = Predef$.MODULE$;
/* 220 */         int m = this.len;
/* 220 */         Range$ range$ = Range$.MODULE$;
/* 220 */         SetLike$SubsetsItr$$anonfun$next$1 setLike$SubsetsItr$$anonfun$next$1 = new SetLike$SubsetsItr$$anonfun$next$1(this);
/*     */         Range range;
/* 220 */         if ((range = new Range(k, m, 1)).validateRangeBoundaries((Function1)setLike$SubsetsItr$$anonfun$next$1)) {
/*     */           int terminal1;
/*     */           int step1;
/*     */           int i1;
/* 220 */           for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 220 */             scala$collection$SetLike$SubsetsItr$$idxs()[i1] = scala$collection$SetLike$SubsetsItr$$idxs()[i1 - 1] + 1;
/* 220 */             i1 += step1;
/*     */           } 
/*     */         } 
/*     */       } 
/* 224 */       return (This)result;
/*     */     }
/*     */     
/*     */     public class SetLike$SubsetsItr$$anonfun$next$2 extends AbstractFunction1<Object, Builder<A, This>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Builder buf$1;
/*     */       
/*     */       public final Builder<A, This> apply(int idx) {
/*     */         return this.buf$1.$plus$eq(this.$outer.scala$collection$SetLike$SubsetsItr$$elms.apply(idx));
/*     */       }
/*     */       
/*     */       public SetLike$SubsetsItr$$anonfun$next$2(SetLike.SubsetsItr $outer, Builder buf$1) {}
/*     */     }
/*     */     
/*     */     public class SetLike$SubsetsItr$$anonfun$next$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply(int j) {
/*     */         apply$mcVI$sp(j);
/*     */       }
/*     */       
/*     */       public SetLike$SubsetsItr$$anonfun$next$1(SetLike.SubsetsItr $outer) {}
/*     */       
/*     */       public void apply$mcVI$sp(int j) {
/*     */         this.$outer.scala$collection$SetLike$SubsetsItr$$idxs()[j] = this.$outer.scala$collection$SetLike$SubsetsItr$$idxs()[j - 1] + 1;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SetLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */