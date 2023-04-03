/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Cloneable;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.script.Message;
/*     */ import scala.collection.script.Scriptable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%g\001C\001\003!\003\r\t!\003\027\003\025\t+hMZ3s\031&\\WM\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Qc\001\006\030[MA\001aC\b!G%B4\b\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032\0042\001E\n\026\033\005\t\"B\001\n\005\003\0359WM\\3sS\016L!\001F\t\003\021\035\023xn^1cY\026\004\"AF\f\r\001\021)\001\004\001b\0013\t\t\021)\005\002\033;A\021AbG\005\0039\031\021qAT8uQ&tw\r\005\002\r=%\021qD\002\002\004\003:L\bc\001\t\"+%\021!%\005\002\013'\"\024\030N\\6bE2,\007c\001\023(+5\tQE\003\002'\t\00511o\031:jaRL!\001K\023\003\025M\033'/\0339uC\ndW\r\005\003\021UUa\023BA\026\022\0051\031VO\031;sC\016$\030M\0317f!\t1R\006\002\004/\001\021\025\ra\f\002\005)\"L7/\005\002\033aI\031\021gM\033\007\tI\002\001\001\r\002\ryI,g-\0338f[\026tGO\020\t\005i\001)B&D\001\003!\r!d'F\005\003o\t\021aAQ;gM\026\024\b\003\002\033:+1J!A\017\002\003\017M+\027\017T5lKB\021A\002P\005\003{\031\021\021b\0217p]\026\f'\r\\3\t\013}\002A\021\001!\002\r\021Jg.\033;%)\005\t\005C\001\007C\023\t\031eA\001\003V]&$\b\"B#\001\r\0031\025!B1qa2LHCA\013H\021\025AE\t1\001J\003\005q\007C\001\007K\023\tYeAA\002J]RDQ!\024\001\007\0029\013a!\0369eCR,GcA!P!\")\001\n\024a\001\023\")\021\013\024a\001+\0059a.Z<fY\026l\007\"B*\001\r\003!\026A\0027f]\036$\b.F\001J\021\0251\006A\"\001X\003!!\003\017\\;tI\025\fHC\001-Z\033\005\001\001\"\002.V\001\004)\022\001B3mK6DQ\001\030\001\007\002\001\013Qa\0317fCJDQA\030\001\007\002}\013a\002\n9mkN$S-\035\023d_2|g\016\006\002YA\")!,\030a\001+!)!\r\001D\001G\006I\021N\\:feR\fE\016\034\013\004\003\022,\007\"\002%b\001\004I\005\"\0024b\001\0049\027!B3mK6\034\bc\0015j+5\tA!\003\002k\t\tYAK]1wKJ\034\030M\0317f\021\025a\007A\"\001n\003\031\021X-\\8wKR\021QC\034\005\006\021.\004\r!\023\005\006Y\002!\t\001\035\013\004\003F\024\b\"\002%p\001\004I\005\"B:p\001\004I\025!B2pk:$\b\"B;\001\t\0031\030!\003\023nS:,8\017J3r)\tAv\017C\003yi\002\007Q#A\001y\021\025Q\b\001\"\001|\003M!\003\017\\;tIAdWo\035\023fc\022\032w\016\\8o)\tAF\020C\003~s\002\007a0\001\002ygB\031\001n`\013\n\007\005\005AAA\bUe\0064XM]:bE2,wJ\\2f\021\035\t)\001\001C\001\003\017\ta!\0319qK:$GcA!\002\n!9a-a\001A\002\005-\001\003\002\007\002\016UI1!a\004\007\005)a$/\0329fCR,GM\020\005\b\003'\001A\021AA\013\003%\t\007\017]3oI\006cG\016F\002B\003/Aa!`A\t\001\004q\bbBA\016\001\021\005\021QD\001\baJ,\007/\0328e)\r\t\025q\004\005\bM\006e\001\031AA\006\021\035\t\031\003\001C\001\003K\t!\002\035:fa\026tG-\0217m)\r\t\025q\005\005\007{\006\005\002\031\001@\t\017\005-\002\001\"\001\002.\0051\021N\\:feR$R!QA\030\003cAa\001SA\025\001\004I\005b\0024\002*\001\007\0211\002\005\b\003k\001A\021AA\034\003%!(/[7Ti\006\024H\017F\002B\003sAa\001SA\032\001\004I\005bBA\037\001\021\005\021qH\001\biJLW.\0228e)\r\t\025\021\t\005\007\021\006m\002\031A%\t\017\005\025\003\001\"\001\002H\005QA\005\\3tg\022bWm]:\025\007\005\013I\005\003\005\002L\005\r\003\031AA'\003\r\031W\016\032\t\005I\005=S#C\002\002R\025\022q!T3tg\006<W\rC\004\002V\001!\t%a\026\002\031M$(/\0338h!J,g-\033=\026\005\005e\003\003BA.\003Cr1\001DA/\023\r\tyFB\001\007!J,G-\0324\n\t\005\r\024Q\r\002\007'R\024\030N\\4\013\007\005}c\001C\004\002j\001!\t!a\033\002\021I,\027\rZ(oYf,\"!!\034\021\t!\fy'F\005\004\003c\"!aA*fc\"9\021Q\017\001\005\002\005]\024A\003\023qYV\034H\005\0357vgR\031A&!\037\t\017u\f\031\b1\001\002|A!\001.! \026\023\r\ty\b\002\002\023\017\026tGK]1wKJ\034\030M\0317f\037:\034W\r\013\005\002t\005\r\025qRAJ!\021\t))a#\016\005\005\035%bAAE\r\005Q\021M\0348pi\006$\030n\0348\n\t\0055\025q\021\002\n[&<'/\031;j_:\f#!!%\002M\002\\3\006\031\021de\026\fG/Z:!C\002rWm\036\021ck\0324WM\035\030!+N,\007\005Y\026,{\001\004Co\034\021bI\022\004\023M\034\021fY\026lWM\034;!MJ|W\016\t;iSN\004#-\0364gKJ\004\023M\0343!e\026$XO\0358!i\"\fG\017\t2vM\032,'\017I5ug\026dgML\021\003\003+\013QA\r\0309]ABq!!'\001\t\003\nY*\001\004%[&tWo\035\013\004Y\005u\005B\002.\002\030\002\007Q\003\013\005\002\030\006\r\025\021UAJC\t\t\031+A4a[\001\0043M]3bi\026\034\b%\031\021oK^\004#-\0364gKJt\003%V:fA\001lS\b\031\021u_\002\022X-\\8wK\002\ng\016I3mK6,g\016\036\021ge>l\007\005\0365jg\002\022WO\0324fe\002\ng\016\032\021sKR,(O\034\021uQ\006$\bEY;gM\026\024\b%\033;tK24g\006C\004\002\032\002!\t%a*\025\0171\nI+!,\0022\"9\0211VAS\001\004)\022!B3mK6\f\004bBAX\003K\003\r!F\001\006K2,WN\r\005\bM\006\025\006\031AA\006Q!\t)+a!\002\"\006M\005bBA\\\001\021\005\023\021X\001\rI5Lg.^:%[&tWo\035\013\004Y\005m\006bB?\0026\002\007\0211\020\025\t\003k\013\031)a0\002\024\006\022\021\021Y\001jA6j\003\rI2sK\006$Xm\035\021bA9,w\017\t2vM\032,'O\f\021Vg\026\004\003-L\027>A\002\"x\016\t:f[>4X\rI1oA\025dW-\\3oi\0022'o\\7!i\"L7\017\t2vM\032,'\017I1oI\002\022X\r^;s]\002\"\b.\031;!EV4g-\032:!SR\034X\r\0344/\021\035\t)\r\001C!\003\017\fQa\0317p]\026$\022\001\f")
/*     */ public interface BufferLike<A, This extends BufferLike<A, This> & Buffer<A>> extends Growable<A>, Shrinkable<A>, Scriptable<A>, Subtractable<A, This>, SeqLike<A, This>, Cloneable {
/*     */   A apply(int paramInt);
/*     */   
/*     */   void update(int paramInt, A paramA);
/*     */   
/*     */   int length();
/*     */   
/*     */   This $plus$eq(A paramA);
/*     */   
/*     */   void clear();
/*     */   
/*     */   This $plus$eq$colon(A paramA);
/*     */   
/*     */   void insertAll(int paramInt, Traversable<A> paramTraversable);
/*     */   
/*     */   A remove(int paramInt);
/*     */   
/*     */   void remove(int paramInt1, int paramInt2);
/*     */   
/*     */   This $minus$eq(A paramA);
/*     */   
/*     */   This $plus$plus$eq$colon(TraversableOnce<A> paramTraversableOnce);
/*     */   
/*     */   void append(Seq<A> paramSeq);
/*     */   
/*     */   void appendAll(TraversableOnce<A> paramTraversableOnce);
/*     */   
/*     */   void prepend(Seq<A> paramSeq);
/*     */   
/*     */   void prependAll(TraversableOnce<A> paramTraversableOnce);
/*     */   
/*     */   void insert(int paramInt, Seq<A> paramSeq);
/*     */   
/*     */   void trimStart(int paramInt);
/*     */   
/*     */   void trimEnd(int paramInt);
/*     */   
/*     */   void $less$less(Message<A> paramMessage);
/*     */   
/*     */   String stringPrefix();
/*     */   
/*     */   Seq<A> readOnly();
/*     */   
/*     */   This $plus$plus(GenTraversableOnce<A> paramGenTraversableOnce);
/*     */   
/*     */   This $minus(A paramA);
/*     */   
/*     */   This $minus(A paramA1, A paramA2, Seq<A> paramSeq);
/*     */   
/*     */   This $minus$minus(GenTraversableOnce<A> paramGenTraversableOnce);
/*     */   
/*     */   This clone();
/*     */   
/*     */   public class BufferLike$$anonfun$remove$1 extends AbstractFunction1<Object, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int n$1;
/*     */     
/*     */     public final A apply(int i) {
/* 116 */       return this.$outer.remove(this.n$1);
/*     */     }
/*     */     
/*     */     public BufferLike$$anonfun$remove$1(Buffer $outer, int n$1) {}
/*     */   }
/*     */   
/*     */   public class BufferLike$$anonfun$$less$less$1 extends AbstractFunction1<Message<A>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Message cmd) {
/* 202 */       this.$outer.$less$less(cmd);
/*     */     }
/*     */     
/*     */     public BufferLike$$anonfun$$less$less$1(Buffer $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\BufferLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */