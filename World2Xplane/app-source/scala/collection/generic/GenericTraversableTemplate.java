/*     */ package scala.collection.generic;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.IntRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005EdaB\001\003!\003\r\t!\003\002\033\017\026tWM]5d)J\fg/\032:tC\ndW\rV3na2\fG/\032\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\013+\001\0322\001A\006\020!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\005!E\031b$D\001\003\023\t\021\"AA\007ICNtUm\036\"vS2$WM\035\t\003)Ua\001\001\002\004\027\001\021\025\ra\006\002\002\003F\021\001d\007\t\003\031eI!A\007\004\003\0179{G\017[5oOB\021A\002H\005\003;\031\0211!\0218zU\ty2\006E\002\025AM!a!\t\001\005\006\004\021#AA\"D+\t\031\023&\005\002\031IA\031QE\n\025\016\003\021I!a\n\003\003\035\035+g\016\026:bm\026\0248/\0312mKB\021A#\013\003\006U\001\022\ra\006\002\0021.\nA\006\005\002.e5\taF\003\0020a\005IQO\\2iK\016\\W\r\032\006\003c\031\t!\"\0318o_R\fG/[8o\023\t\031dFA\tv]\016DWmY6fIZ\013'/[1oG\026DQ!\016\001\005\002Y\na\001J5oSR$C#A\034\021\0051A\024BA\035\007\005\021)f.\033;\t\013m\002a\021\001\037\002\017\031|'/Z1dQV\021Q\b\022\013\003oyBQa\020\036A\002\001\013\021A\032\t\005\031\005\0332)\003\002C\r\tIa)\0368di&|g.\r\t\003)\021#Q!\022\036C\002]\021\021!\026\005\006\017\0021\t\001S\001\005Q\026\fG-F\001\024\021\025Q\005A\"\001L\003\035I7/R7qif,\022\001\024\t\003\0315K!A\024\004\003\017\t{w\016\\3b]\")\001\013\001D\001#\006I1m\\7qC:LwN\\\013\002%B\031\001cU+\n\005Q\023!\001E$f]\026\024\030nY\"p[B\fg.[8o!\t!\002\005\003\004X\001\001&\t\002W\001\013]\026<()^5mI\026\024X#A-\021\tik6cH\007\0027*\021A\fB\001\b[V$\030M\0317f\023\tq6LA\004Ck&dG-\032:\t\013\001\004A\021A1\002\035\035,g.\032:jG\n+\030\016\0343feV\021!-Z\013\002GB!!,\0303h!\t!R\rB\003g?\n\007qCA\001C!\r!\002\005\032\005\006S\002!IA[\001\013g\026\fX/\0328uS\006dW#A6\021\007\025b7#\003\002n\t\tyAK]1wKJ\034\030M\0317f\037:\034W\rC\003p\001\021\005\001/A\003v]jL\007/F\002ron$\"A]?\021\t1\031X/_\005\003i\032\021a\001V;qY\026\024\004c\001\013!mB\021Ac\036\003\006q:\024\ra\006\002\003\003F\0022\001\006\021{!\t!2\020B\003}]\n\007qC\001\002Be!)aP\034a\002\0061\021m\035)bSJ\004R\001D!\024\003\003\001B\001D:wu\"9\021Q\001\001\005\002\005\035\021AB;ou&\0048'\006\005\002\n\005U\0211DA\021)\021\tY!!\n\021\0231\ti!!\005\002\030\005u\021bAA\b\r\t1A+\0369mKN\002B\001\006\021\002\024A\031A#!\006\005\ra\f\031A1\001\030!\021!\002%!\007\021\007Q\tY\002\002\004}\003\007\021\ra\006\t\005)\001\ny\002E\002\025\003C!q!a\t\002\004\t\007qC\001\002Bg!A\021qEA\002\001\b\tI#\001\005bgR\023\030\016\0357f!\025a\021iEA\026!%a\021QBA\n\0033\ty\002C\004\0020\001!\t!!\r\002\017\031d\027\r\036;f]V!\0211GA\035)\021\t)$a\017\021\tQ\001\023q\007\t\004)\005eBA\0024\002.\t\007q\003\003\005\002>\0055\0029AA \0035\t7\017\026:bm\026\0248/\0312mKB)A\"Q\n\002BA)Q%a\021\0028%\031\021Q\t\003\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\005\b\003\023\002A\021AA&\003%!(/\0318ta>\034X-\006\003\002N\005]C\003BA(\0033\002B\001\006\021\002R)\032\0211K\026\021\tQ\001\023Q\013\t\004)\005]CA\0024\002H\t\007q\003\003\005\002>\005\035\0039AA.!\025a\021iEA/!\025)\0231IA+Q!\t9%!\031\002j\0055\004\003BA2\003Kj\021\001M\005\004\003O\002$!C7jOJ\fG/[8oC\t\tY'\001-aiJ\fgn\0359pg\026\004\007\005\0365s_^\034\b%\0318!A&cG.Z4bY\006\023x-^7f]R,\005pY3qi&|g\016\031\021jM\002\032w\016\0347fGRLwN\\:!CJ,\007E\\8uAUt\027NZ8s[2L\be]5{K\022t\023EAA8\003\025\021d&\017\0301\001")
/*     */ public interface GenericTraversableTemplate<A, CC extends scala.collection.GenTraversable<Object>> extends HasNewBuilder<A, CC> {
/*     */   <U> void foreach(Function1<A, U> paramFunction1);
/*     */   
/*     */   A head();
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   GenericCompanion<CC> companion();
/*     */   
/*     */   Builder<A, CC> newBuilder();
/*     */   
/*     */   <B> Builder<B, CC> genericBuilder();
/*     */   
/*     */   <A1, A2> Tuple2<CC, CC> unzip(Function1<A, Tuple2<A1, A2>> paramFunction1);
/*     */   
/*     */   <A1, A2, A3> Tuple3<CC, CC, CC> unzip3(Function1<A, Tuple3<A1, A2, A3>> paramFunction1);
/*     */   
/*     */   <B> CC flatten(Function1<A, GenTraversableOnce<B>> paramFunction1);
/*     */   
/*     */   <B> CC transpose(Function1<A, GenTraversableOnce<B>> paramFunction1);
/*     */   
/*     */   public class GenericTraversableTemplate$$anonfun$unzip$1 extends AbstractFunction1<A, Builder<A2, CC>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b1$1;
/*     */     
/*     */     private final Builder b2$1;
/*     */     
/*     */     private final Function1 asPair$1;
/*     */     
/*     */     public GenericTraversableTemplate$$anonfun$unzip$1(GenericTraversableTemplate $outer, Builder b1$1, Builder b2$1, Function1 asPair$1) {}
/*     */     
/*     */     public final Builder<A2, CC> apply(Object xy) {
/*  87 */       Tuple2 tuple2 = (Tuple2)this.asPair$1.apply(xy);
/*  87 */       if (tuple2 != null) {
/*  87 */         Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/*  87 */         Object x = tuple21._1(), y = tuple21._2();
/*  88 */         this.b1$1.$plus$eq(x);
/*  89 */         return this.b2$1.$plus$eq(y);
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */   }
/*     */   
/*     */   public class GenericTraversableTemplate$$anonfun$unzip3$1 extends AbstractFunction1<A, Builder<A3, CC>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b1$2;
/*     */     
/*     */     private final Builder b2$2;
/*     */     
/*     */     private final Builder b3$1;
/*     */     
/*     */     private final Function1 asTriple$1;
/*     */     
/*     */     public GenericTraversableTemplate$$anonfun$unzip3$1(GenericTraversableTemplate $outer, Builder b1$2, Builder b2$2, Builder b3$1, Function1 asTriple$1) {}
/*     */     
/*     */     public final Builder<A3, CC> apply(Object xyz) {
/* 111 */       Tuple3 tuple3 = (Tuple3)this.asTriple$1.apply(xyz);
/* 111 */       if (tuple3 != null) {
/* 111 */         Tuple3 tuple31 = new Tuple3(tuple3._1(), tuple3._2(), tuple3._3());
/* 111 */         Object x = tuple31._1(), y = tuple31._2(), z = tuple31._3();
/* 112 */         this.b1$2.$plus$eq(x);
/* 113 */         this.b2$2.$plus$eq(y);
/* 114 */         return this.b3$1.$plus$eq(z);
/*     */       } 
/*     */       throw new MatchError(tuple3);
/*     */     }
/*     */   }
/*     */   
/*     */   public class GenericTraversableTemplate$$anonfun$flatten$1 extends AbstractFunction1<A, Builder<B, CC>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$1;
/*     */     
/*     */     private final Function1 asTraversable$1;
/*     */     
/*     */     public GenericTraversableTemplate$$anonfun$flatten$1(GenericTraversableTemplate $outer, Builder b$1, Function1 asTraversable$1) {}
/*     */     
/*     */     public final Builder<B, CC> apply(Object xs) {
/* 146 */       return (Builder<B, CC>)this.b$1.$plus$plus$eq(((GenTraversableOnce)this.asTraversable$1.apply(xs)).seq());
/*     */     }
/*     */   }
/*     */   
/*     */   public class GenericTraversableTemplate$$anonfun$1 extends AbstractFunction0<Builder<B, CC>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Builder<B, CC> apply() {
/* 169 */       return this.$outer.genericBuilder();
/*     */     }
/*     */     
/*     */     public GenericTraversableTemplate$$anonfun$1(GenericTraversableTemplate $outer) {}
/*     */   }
/*     */   
/*     */   public class GenericTraversableTemplate$$anonfun$transpose$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int headSize$1;
/*     */     
/*     */     public final IndexedSeq bs$1;
/*     */     
/*     */     private final Function1 asTraversable$2;
/*     */     
/*     */     public GenericTraversableTemplate$$anonfun$transpose$1(GenericTraversableTemplate $outer, int headSize$1, IndexedSeq bs$1, Function1 asTraversable$2) {}
/*     */     
/*     */     public final void apply(Object xs) {
/* 171 */       IntRef i = new IntRef(0);
/* 172 */       ((GenTraversableOnce)this.asTraversable$2.apply(xs)).foreach((Function1)new GenericTraversableTemplate$$anonfun$transpose$1$$anonfun$apply$1(this, ($anonfun$transpose$1)i));
/* 177 */       if (i.elem != this.headSize$1)
/* 178 */         throw GenericTraversableTemplate$class.fail$1(this.$outer); 
/*     */     }
/*     */     
/*     */     public class GenericTraversableTemplate$$anonfun$transpose$1$$anonfun$apply$1 extends AbstractFunction1<B, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final IntRef i$1;
/*     */       
/*     */       public GenericTraversableTemplate$$anonfun$transpose$1$$anonfun$apply$1(GenericTraversableTemplate$$anonfun$transpose$1 $outer, IntRef i$1) {}
/*     */       
/*     */       public final void apply(Object x) {
/*     */         if (this.i$1.elem >= this.$outer.headSize$1)
/*     */           throw GenericTraversableTemplate$class.fail$1(this.$outer.$outer); 
/*     */         ((Builder)this.$outer.bs$1.apply(this.i$1.elem)).$plus$eq(x);
/*     */         this.i$1.elem++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class GenericTraversableTemplate$$anonfun$transpose$2 extends AbstractFunction1<Builder<B, CC>, Builder<CC, CC>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder bb$1;
/*     */     
/*     */     public final Builder<CC, CC> apply(Builder b) {
/* 181 */       return this.bb$1.$plus$eq(b.result());
/*     */     }
/*     */     
/*     */     public GenericTraversableTemplate$$anonfun$transpose$2(GenericTraversableTemplate $outer, Builder bb$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericTraversableTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */