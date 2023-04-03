/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.AbstractSeq;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.IndexedSeq;
/*    */ import scala.collection.IndexedSeqLike;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.immutable.ParSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005uaaB\001\003!\003\r\t!\003\002\013\023:$W\r_3e'\026\f(BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013+M1\001aC\b\037C!\002\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022cE\007\002\005%\021!C\001\002\004'\026\f\bC\001\013\026\031\001!aA\006\001\005\006\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\004?\001\032R\"\001\003\n\005\005!\001\003\002\022&'\035j\021a\t\006\003I\021\tqaZ3oKJL7-\003\002'G\tQr)\0328fe&\034GK]1wKJ\034\030M\0317f)\026l\007\017\\1uKB\021\001\003\001\t\005?%\0322&\003\002+\t\tq\021J\0343fq\026$7+Z9MS.,\007c\001\t\001'!)Q\006\001C\001]\0051A%\0338ji\022\"\022a\f\t\003\031AJ!!\r\004\003\tUs\027\016\036\005\006g\001!\t\005N\001\nG>l\007/\0318j_:,\022!\016\t\004EY:\023BA\034$\005A9UM\\3sS\016\034u.\0349b]&|g\016C\003:\001\021\005#(\001\007u_&sG-\032=fIN+\027/F\001,\021\025a\004\001\"\021;\003\r\031X-]\004\006}\tA\taP\001\013\023:$W\r_3e'\026\f\bC\001\tA\r\025\t!\001#\001B'\t\001%\tE\002#\007\036J!\001R\022\003\025M+\027OR1di>\024\030\020C\003G\001\022\005q)\001\004=S:LGO\020\013\002!A\021\n\021EC\002\023\005#*A\006SKV\034\030M\0317f\007\n3U#A&\021\0071k\005$D\001A\023\tquJA\nHK:,'/[2DC:\024U/\0337e\rJ|W.\003\002QG\t)r)\0328Ue\0064XM]:bE2,g)Y2u_JL\b\002\003*A\021\003\005\013\025B&\002\031I+Wo]1cY\026\034%I\022\021\007\tQ\003\005!\026\002\005\0236\004H.\006\002W7N!1k\026/^!\ry\002LW\005\0033\022\0211\"\0212tiJ\f7\r^*fcB\021Ac\027\003\006-M\023\ra\006\t\004!\001Q\006C\001\007_\023\tyfA\001\007TKJL\027\r\\5{C\ndW\r\003\005b'\n\005\t\025!\003c\003\r\021WO\032\t\004G\032TV\"\0013\013\005\025$\021aB7vi\006\024G.Z\005\003O\022\0241\"\021:sCf\024UO\0324fe\")ai\025C\001SR\021!n\033\t\004\031NS\006\"B1i\001\004\021\007\"B7T\t\003q\027A\0027f]\036$\b.F\001p!\ta\001/\003\002r\r\t\031\021J\034;\t\013M\034F\021\001;\002\013\005\004\b\017\\=\025\005i+\b\"\002<s\001\004y\027aA5eq\")\001\020\021C\001s\006Qa.Z<Ck&dG-\032:\026\005i|X#A>\021\013\rdh0!\001\n\005u$'a\002\"vS2$WM\035\t\003)}$QAF<C\002]\0012\001\005\001\021\035\t)\001\021C\002\003\017\tAbY1o\005VLG\016\032$s_6,B!!\003\002\032U\021\0211\002\t\nE\0055\021\021CA\f\0037I1!a\004$\0051\031\025M\034\"vS2$gI]8n!\ra\0251C\005\004\003+1$\001B\"pY2\0042\001FA\r\t\0311\0221\001b\001/A!\001\003AA\f\001")
/*    */ public interface IndexedSeq<A> extends Seq<A>, IndexedSeq<A>, GenericTraversableTemplate<A, IndexedSeq>, IndexedSeqLike<A, IndexedSeq<A>> {
/*    */   GenericCompanion<IndexedSeq> companion();
/*    */   
/*    */   IndexedSeq<A> toIndexedSeq();
/*    */   
/*    */   IndexedSeq<A> seq();
/*    */   
/*    */   public static class Impl<A> extends AbstractSeq<A> implements IndexedSeq<A>, Serializable {
/*    */     private final ArrayBuffer<A> buf;
/*    */     
/*    */     public GenericCompanion<IndexedSeq> companion() {
/* 37 */       return IndexedSeq$class.companion(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> toIndexedSeq() {
/* 37 */       return IndexedSeq$class.toIndexedSeq(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> seq() {
/* 37 */       return IndexedSeq$class.seq(this);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 37 */       return IndexedSeqLike.class.hashCode(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> thisCollection() {
/* 37 */       return IndexedSeqLike.class.thisCollection(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> toCollection(Object repr) {
/* 37 */       return IndexedSeqLike.class.toCollection(this, repr);
/*    */     }
/*    */     
/*    */     public Iterator<A> iterator() {
/* 37 */       return IndexedSeqLike.class.iterator(this);
/*    */     }
/*    */     
/*    */     public <A1> Buffer<A1> toBuffer() {
/* 37 */       return IndexedSeqLike.class.toBuffer(this);
/*    */     }
/*    */     
/*    */     public Seq<A> toSeq() {
/* 37 */       return Seq$class.toSeq(this);
/*    */     }
/*    */     
/*    */     public Combiner<A, ParSeq<A>> parCombiner() {
/* 37 */       return Seq$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public Impl(ArrayBuffer<A> buf) {
/* 37 */       Traversable$class.$init$(this);
/* 37 */       Iterable$class.$init$(this);
/* 37 */       Seq$class.$init$(this);
/* 37 */       IndexedSeqLike.class.$init$(this);
/* 37 */       IndexedSeq.class.$init$(this);
/* 37 */       IndexedSeq$class.$init$(this);
/*    */     }
/*    */     
/*    */     public int length() {
/* 38 */       return this.buf.length();
/*    */     }
/*    */     
/*    */     public A apply(int idx) {
/* 39 */       return (A)this.buf.apply(idx);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\IndexedSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */