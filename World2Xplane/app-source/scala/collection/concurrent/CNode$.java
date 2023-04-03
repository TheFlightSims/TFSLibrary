/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class CNode$ {
/*     */   public static final CNode$ MODULE$;
/*     */   
/*     */   public class CNode$$anonfun$string$1 extends AbstractFunction1<BasicNode, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int lev$3;
/*     */     
/*     */     public final String apply(BasicNode x$2) {
/* 568 */       return x$2.string(this.lev$3 + 1);
/*     */     }
/*     */     
/*     */     public CNode$$anonfun$string$1(CNode $outer, int lev$3) {}
/*     */   }
/*     */   
/*     */   public class CNode$$anonfun$scala$collection$concurrent$CNode$$collectElems$1 extends AbstractFunction1<BasicNode, Iterable<Tuple2<K, V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public CNode$$anonfun$scala$collection$concurrent$CNode$$collectElems$1(CNode $outer) {}
/*     */     
/*     */     public final Iterable<Tuple2<K, V>> apply(BasicNode x0$1) {
/* 571 */       if (x0$1 instanceof SNode) {
/* 571 */         SNode sNode = (SNode)x0$1;
/* 571 */         Iterable iterable = scala.Option$.MODULE$.option2Iterable((Option)new Some(sNode.kvPair()));
/*     */       } else {
/* 573 */         if (x0$1 instanceof INode) {
/*     */           Seq seq;
/* 573 */           INode iNode = (INode)x0$1;
/* 573 */           MainNode mainNode = iNode.mainnode;
/* 574 */           if (mainNode instanceof TNode) {
/* 574 */             TNode tNode = (TNode)mainNode;
/* 574 */             Iterable iterable = scala.Option$.MODULE$.option2Iterable((Option)new Some(tNode.kvPair()));
/* 575 */           } else if (mainNode instanceof LNode) {
/* 575 */             LNode<K, V> lNode = (LNode)mainNode;
/* 575 */             List list = lNode.listmap().toList();
/* 576 */           } else if (mainNode instanceof CNode) {
/* 576 */             CNode cNode = (CNode)mainNode;
/* 576 */             seq = cNode.scala$collection$concurrent$CNode$$collectElems();
/*     */           } else {
/*     */             throw new MatchError(mainNode);
/*     */           } 
/*     */           return (Iterable<Tuple2<K, V>>)seq;
/*     */         } 
/*     */         throw new MatchError(x0$1);
/*     */       } 
/*     */       return (Iterable<Tuple2<K, V>>)SYNTHETIC_LOCAL_VARIABLE_8;
/*     */     }
/*     */   }
/*     */   
/*     */   public class CNode$$anonfun$collectLocalElems$1 extends AbstractFunction1<BasicNode, Iterable<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public CNode$$anonfun$collectLocalElems$1(CNode $outer) {}
/*     */     
/*     */     public final Iterable<String> apply(BasicNode x0$2) {
/* 580 */       if (x0$2 instanceof SNode) {
/* 580 */         SNode<K, V> sNode = (SNode)x0$2;
/* 580 */         Iterable iterable = scala.Option$.MODULE$.option2Iterable((Option)new Some(sNode.kvPair()._2().toString()));
/*     */       } else {
/* 582 */         if (x0$2 instanceof INode) {
/* 582 */           INode iNode = (INode)x0$2;
/* 582 */           String str = iNode.toString();
/* 582 */           scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 582 */           return scala.Option$.MODULE$.option2Iterable((Option)new Some((new StringBuilder()).append((new StringOps(str)).drop(14)).append("(").append(iNode.gen).append(")").toString()));
/*     */         } 
/*     */         throw new MatchError(x0$2);
/*     */       } 
/*     */       return (Iterable<String>)SYNTHETIC_LOCAL_VARIABLE_6;
/*     */     }
/*     */   }
/*     */   
/*     */   private CNode$() {
/* 592 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <K, V> MainNode<K, V> dual(SNode<Object, Object> x, int xhc, SNode<Object, Object> y, int yhc, int lev, Gen gen) {
/* 595 */     int xidx = xhc >>> lev & 0x1F;
/* 596 */     int yidx = yhc >>> lev & 0x1F;
/* 597 */     int bmp = 1 << xidx | 1 << yidx;
/* 599 */     INode<Object, Object> subinode = new INode<Object, Object>(gen);
/* 600 */     subinode.mainnode = dual(x, xhc, y, yhc, lev + 5, gen);
/* 601 */     (new BasicNode[1])[0] = subinode;
/* 603 */     (new BasicNode[2])[0] = x;
/* 603 */     (new BasicNode[2])[1] = y;
/* 604 */     (new BasicNode[2])[0] = y;
/* 604 */     (new BasicNode[2])[1] = x;
/* 604 */     return (lev < 35) ? ((xidx == yidx) ? new CNode<K, V>(bmp, new BasicNode[1], gen) : ((xidx < yidx) ? new CNode<K, V>(bmp, new BasicNode[2], gen) : new CNode<K, V>(bmp, new BasicNode[2], gen))) : 
/*     */       
/* 607 */       new LNode<K, V>((K)x.k(), (V)x.v(), (K)y.k(), (V)y.v());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\CNode$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */