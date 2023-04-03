/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParSet;
/*     */ import scala.collection.script.Message;
/*     */ import scala.collection.script.Scriptable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%eaB\001\003!\003\r\t!\003\002\b'\026$H*[6f\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007)!bdE\005\001\027=IsF\r\035<}A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\tA\t\"#H\007\002\t%\021\021\001\002\t\003'Qa\001\001B\003\026\001\t\007aCA\001B#\t9\"\004\005\002\r1%\021\021D\002\002\b\035>$\b.\0338h!\ta1$\003\002\035\r\t\031\021I\\=\021\005MqBAB\020\001\t\013\007\001E\001\003UQ&\034\030CA\f\"%\r\021CE\n\004\005G\001\001\021E\001\007=e\0264\027N\\3nK:$h\b\005\003&\001IiR\"\001\002\021\007\025:##\003\002)\005\t\0311+\032;\021\007)j##D\001,\025\taC!\001\004tGJL\007\017^\005\003]-\022!bU2sSB$\030M\0317f!\021)\003GE\017\n\005E\022!a\002\"vS2$WM\035\t\004gY\022R\"\001\033\013\005U\"\021aB4f]\026\024\030nY\005\003oQ\022\001b\022:po\006\024G.\032\t\004ge\022\022B\001\0365\005)\031\006N]5oW\006\024G.\032\t\004Kq2\023BA\037\003\005%\031En\0348fC\ndW\r\005\003\021I\t\025B\001!\005\0059\001\026M]1mY\026d\027N_1cY\026\0042A\021$\023\033\005\031%BA\002E\025\t)E!\001\005qCJ\fG\016\\3m\023\t95I\001\004QCJ\034V\r\036\005\006\023\002!\tAS\001\007I%t\027\016\036\023\025\003-\003\"\001\004'\n\00553!\001B+oSRDaa\024\001!\n#\002\026A\0038fo\n+\030\016\0343feV\tq\006\003\004S\001\001&\tfU\001\fa\006\0248i\\7cS:,'/F\001U!\021)fKE!\016\003\021K!a\026#\003\021\r{WNY5oKJDQ!\027\001\005\002i\0131!\0313e)\tYf\f\005\002\r9&\021QL\002\002\b\005>|G.Z1o\021\025y\006\f1\001\023\003\021)G.Z7\t\013\005\004A\021\0012\002\rI,Wn\034<f)\tY6\rC\003`A\002\007!\003C\003f\001\021\005a-\001\004va\022\fG/\032\013\004\027\036D\007\"B0e\001\004\021\002\"B5e\001\004Y\026\001C5oG2,H-\0323\t\013-\004a\021\0017\002\021\021\002H.^:%KF$\"!\0348\016\003\001AQa\0306A\002IAQ\001\035\001\007\002E\f\021\002J7j]V\034H%Z9\025\0055\024\b\"B0p\001\004\021\002\"\002;\001\t\003)\030A\002:fi\006Lg\016\006\002Lm\")qo\035a\001q\006\t\001\017\005\003\rsJY\026B\001>\007\005%1UO\\2uS>t\027\007C\003}\001\021\005!*A\003dY\026\f'\017C\003\001\021\005s0A\003dY>tW\rF\001\036\021\031\t\031\001\001C\001\0061!/Z:vYRDq!a\002\001\t\003\nI!A\003%a2,8\017F\002\036\003\027AaaXA\003\001\004\021\002\006CA\003\003\037\tY\"a\b\021\t\005E\021qC\007\003\003'Q1!!\006\007\003)\tgN\\8uCRLwN\\\005\005\0033\t\031BA\005nS\036\024\030\r^5p]\006\022\021QD\001ZA.\002\007e\031:fCR,7\017I1!]\026<\be]3u]\001*6/\032\021aWu\002\007\005^8!C\022$\007%\0318!K2,W.\0328uAQ|\007\005\0365jg\002\032X\r\036\021b]\022\004#/\032;ve:\004C\017[1uAM,G\017I5ug\026dgML\021\003\003C\tQA\r\0309]ABq!a\002\001\t\003\n)\003F\004\036\003O\tY#a\f\t\017\005%\0221\005a\001%\005)Q\r\\3nc!9\021QFA\022\001\004\021\022!B3mK6\024\004\002CA\031\003G\001\r!a\r\002\013\025dW-\\:\021\t1\t)DE\005\004\003o1!A\003\037sKB,\027\r^3e}!B\0211EA\b\0037\ty\002C\004\002>\001!\t%a\020\002\025\021\002H.^:%a2,8\017F\002\036\003\003B\001\"a\021\002<\001\007\021QI\001\003qN\004B\001EA$%%\031\021\021\n\003\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\025\t\003w\ty!!\024\002 \005\022\021qJ\001ZA.Z\003\rI2sK\006$Xm\035\021bA9,w\017I:fi:\002Sk]3!A.ZS\b\031\021u_\002\nG\r\032\021fY\026lWM\034;tAQ|\007\005\0365jg\002\032X\r\036\021b]\022\004#/\032;ve:\004C\017[1uAM,G\017I5ug\026dgM\f\005\b\003'\002A\021IA+\003\031!S.\0338vgR\031Q$a\026\t\r}\013\t\0061\001\023Q!\t\t&a\004\002\\\005}\021EAA/\003y\003W\006\031\021de\026\fG/Z:!C\002rWm\036\021tKRt\003%V:fA\001lS\b\031\021u_\002\022X-\\8wK\002\ng\016I3mK6,g\016\036\021ge>l\007\005\0365jg\002\032X\r\036\021b]\022\004#/\032;ve:\004C\017[1uAM,G\017I5ug\026dgM\f\005\b\003'\002A\021IA1)\035i\0221MA3\003OBq!!\013\002`\001\007!\003C\004\002.\005}\003\031\001\n\t\021\005E\022q\fa\001\003gA\003\"a\030\002\020\005m\023q\004\005\b\003[\002A\021IA8\0031!S.\0338vg\022j\027N\\;t)\ri\022\021\017\005\t\003\007\nY\0071\001\002F!B\0211NA\b\003k\ny\"\t\002\002x\005q\006-L\027aA\r\024X-\031;fg\002\n\007E\\3xAM,GO\f\021Vg\026\004\003-L\027>A\002\"x\016\t:f[>4X\rI3mK6,g\016^:!MJ|W\016\t;iSN\0043/\032;!C:$\007E]3ukJt\007\005\0365bi\002\032X\r\036\021jiN,GN\032\030\t\017\005m\004\001\"\001\002~\005QA\005\\3tg\022bWm]:\025\007-\013y\b\003\005\002\002\006e\004\031AAB\003\r\031W\016\032\t\005U\005\025%#C\002\002\b.\022q!T3tg\006<W\r")
/*     */ public interface SetLike<A, This extends SetLike<A, This> & Set<A>> extends SetLike<A, This>, Scriptable<A>, Builder<A, This>, Growable<A>, Shrinkable<A>, Cloneable<Set<A>>, Parallelizable<A, ParSet<A>> {
/*     */   Builder<A, This> newBuilder();
/*     */   
/*     */   Combiner<A, ParSet<A>> parCombiner();
/*     */   
/*     */   boolean add(A paramA);
/*     */   
/*     */   boolean remove(A paramA);
/*     */   
/*     */   void update(A paramA, boolean paramBoolean);
/*     */   
/*     */   SetLike<A, This> $plus$eq(A paramA);
/*     */   
/*     */   SetLike<A, This> $minus$eq(A paramA);
/*     */   
/*     */   void retain(Function1<A, Object> paramFunction1);
/*     */   
/*     */   void clear();
/*     */   
/*     */   This clone();
/*     */   
/*     */   This result();
/*     */   
/*     */   This $plus(A paramA);
/*     */   
/*     */   This $plus(A paramA1, A paramA2, Seq<A> paramSeq);
/*     */   
/*     */   This $plus$plus(GenTraversableOnce<A> paramGenTraversableOnce);
/*     */   
/*     */   This $minus(A paramA);
/*     */   
/*     */   This $minus(A paramA1, A paramA2, Seq<A> paramSeq);
/*     */   
/*     */   This $minus$minus(GenTraversableOnce<A> paramGenTraversableOnce);
/*     */   
/*     */   void $less$less(Message<A> paramMessage);
/*     */   
/*     */   public class SetLike$$anonfun$clear$1 extends AbstractFunction1<A, SetLike<A, This>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final SetLike<A, This> apply(Object elem) {
/* 130 */       return this.$outer.$minus$eq((A)elem);
/*     */     }
/*     */     
/*     */     public SetLike$$anonfun$clear$1(SetLike $outer) {}
/*     */   }
/*     */   
/*     */   public class SetLike$$anonfun$$less$less$1 extends AbstractFunction1<Message<A>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Message cmd) {
/* 216 */       this.$outer.$less$less(cmd);
/*     */     }
/*     */     
/*     */     public SetLike$$anonfun$$less$less$1(SetLike $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SetLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */