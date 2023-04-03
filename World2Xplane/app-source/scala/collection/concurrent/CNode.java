/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$DummyImplicit$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.forkjoin.ThreadLocalRandom;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.Ordering$String$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005ed!B\001\003\005\021A!!B\"O_\022,'BA\002\005\003)\031wN\\2veJ,g\016\036\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\fWcA\005\0219M\021\001A\003\t\005\0271q1$D\001\003\023\ti!AA\005D\035>$WMQ1tKB\021q\002\005\007\001\t\025\t\002A1\001\024\005\005Y5\001A\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\tyA\004B\003\036\001\t\0071CA\001W\021!y\002A!b\001\n\003\001\023A\0022ji6\f\007/F\001\"!\t)\"%\003\002$\r\t\031\021J\034;\t\021\025\002!\021!Q\001\n\005\nqAY5u[\006\004\b\005\003\005(\001\t\025\r\021\"\001)\003\025\t'O]1z+\005I\003cA\013+Y%\0211F\002\002\006\003J\024\030-\037\t\003\0275J!A\f\002\003\023\t\0137/[2O_\022,\007\002\003\031\001\005\003\005\013\021B\025\002\r\005\024(/Y=!\021!\021\004A!b\001\n\003\031\024aA4f]V\tA\007\005\002\fk%\021aG\001\002\004\017\026t\007\002\003\035\001\005\003\005\013\021\002\033\002\t\035,g\016\t\005\006u\001!\taO\001\007y%t\027\016\036 \025\tqjdh\020\t\005\027\001q1\004C\003 s\001\007\021\005C\003(s\001\007\021\006C\0033s\001\007A\007C\003B\001\021\005!)\001\006dC\016DW\rZ*ju\026$\"!I\"\t\013\021\003\005\031A#\002\005\r$\bCA\013G\023\t9eA\001\004B]f\024VM\032\005\006\023\002!IAS\001\fG>l\007/\036;f'&TX\r\006\002\"\027\")A\t\023a\001\031B!1\"\024\b\034\023\tq%AA\004Ue&,W*\0319\t\013A\003A\021A)\002\023U\004H-\031;fI\006#H\003\002\037S)ZCQaU(A\002\005\n1\001]8t\021\025)v\n1\001-\003\tqg\016C\0033\037\002\007A\007C\003Y\001\021\005\021,A\005sK6|g/\0323BiR!AHW.^\021\025\031v\0131\001\"\021\025av\0131\001\"\003\0211G.Y4\t\013I:\006\031\001\033\t\013}\003A\021\0011\002\025%t7/\032:uK\022\fE\017F\003=C\n\034G\rC\003T=\002\007\021\005C\003]=\002\007\021\005C\003V=\002\007A\006C\0033=\002\007A\007C\003g\001\021\005q-A\004sK:,w/\0323\025\007qB'\016C\003jK\002\007A'\001\003oO\026t\007\"\002#f\001\004a\005\"\0027\001\t\023i\027!\003:fgV\024(/Z2u)\racn\035\005\006_.\004\r\001]\001\006S:|G-\032\t\005\027Et1$\003\002s\005\t)\021JT8eK\")Ao\033a\001\013\006I\021N\\8eK6\f\027N\034\005\006m\002!\ta^\001\ri>\034uN\034;sC\016$X\r\032\013\003qn\004BaC=\0177%\021!P\001\002\t\033\006LgNT8eK\")A0\036a\001C\005\031A.\032<\t\013y\004A\021A@\002\031Q|7i\\7qe\026\0348/\0323\025\017a\f\t!a\001\002\006!)A) a\001\031\")A0 a\001C!)!' a\001i!A\021\021\002\001\005\002\t\tY!\001\004tiJLgn\032\013\005\003\033\tY\002\005\003\002\020\005UabA\013\002\022%\031\0211\003\004\002\rA\023X\rZ3g\023\021\t9\"!\007\003\rM#(/\0338h\025\r\t\031B\002\005\007y\006\035\001\031A\021\t\017\005}\001\001\"\003\002\"\005a1m\0347mK\016$X\t\\3ngV\021\0211\005\t\007\003K\t9#a\013\016\003\021I1!!\013\005\005\r\031V-\035\t\006+\0055bbG\005\004\003_1!A\002+va2,'\007C\004\0024\001!I!!\016\002#\r|G\016\\3di2{7-\0317FY\026l7/\006\002\0028A1\021QEA\024\003\033Aq!a\017\001\t\003\ni$\001\005u_N#(/\0338h)\t\tia\002\005\002B\tA\tAAA\"\003\025\031ej\0343f!\rY\021Q\t\004\b\003\tA\tAAA$'\r\t)%\022\005\bu\005\025C\021AA&)\t\t\031\005\003\005\002P\005\025C\021AA)\003\021!W/\0317\026\r\005M\023\021LA/)9\t)&a\030\002j\0055\024\021OA;\003o\002baC=\002X\005m\003cA\b\002Z\0211\021#!\024C\002M\0012aDA/\t\031i\022Q\nb\001'!A\021\021MA'\001\004\t\031'A\001y!\035Y\021QMA,\0037J1!a\032\003\005\025\031fj\0343f\021\035\tY'!\024A\002\005\n1\001\0375d\021!\ty'!\024A\002\005\r\024!A=\t\017\005M\024Q\na\001C\005\031\021\020[2\t\rq\fi\0051\001\"\021\031\021\024Q\na\001i\001")
/*     */ public final class CNode<K, V> extends CNodeBase<K, V> {
/*     */   private final int bitmap;
/*     */   
/*     */   private final BasicNode[] array;
/*     */   
/*     */   private final Gen gen;
/*     */   
/*     */   public int bitmap() {
/* 450 */     return this.bitmap;
/*     */   }
/*     */   
/*     */   public BasicNode[] array() {
/* 450 */     return this.array;
/*     */   }
/*     */   
/*     */   public Gen gen() {
/* 450 */     return this.gen;
/*     */   }
/*     */   
/*     */   public CNode(int bitmap, BasicNode[] array, Gen gen) {}
/*     */   
/*     */   public int cachedSize(Object ct) {
/* 453 */     int currsz = READ_SIZE();
/* 456 */     int sz = computeSize((TrieMap<K, V>)ct);
/* 457 */     for (; READ_SIZE() == -1; CAS_SIZE(-1, sz));
/* 458 */     return (currsz != -1) ? currsz : READ_SIZE();
/*     */   }
/*     */   
/*     */   private int computeSize(TrieMap ct) {
/* 468 */     int i = 0;
/* 469 */     int sz = 0;
/* 470 */     int offset = 
/* 471 */       ((array()).length > 0) ? 
/*     */       
/* 473 */       ThreadLocalRandom.current().nextInt(0, (array()).length) : 
/* 474 */       0;
/* 475 */     while (i < (array()).length) {
/* 476 */       int pos = (i + offset) % (array()).length;
/* 477 */       BasicNode basicNode = array()[pos];
/* 478 */       if (basicNode instanceof SNode) {
/* 478 */         sz++;
/* 479 */       } else if (basicNode instanceof INode) {
/* 479 */         INode iNode = (INode)basicNode;
/* 479 */         sz += iNode.cachedSize(ct);
/*     */       } else {
/*     */         throw new MatchError(basicNode);
/*     */       } 
/* 481 */       i++;
/*     */     } 
/* 483 */     return sz;
/*     */   }
/*     */   
/*     */   public CNode<K, V> updatedAt(int pos, BasicNode nn, Gen gen) {
/* 487 */     int len = (array()).length;
/* 488 */     BasicNode[] narr = new BasicNode[len];
/* 489 */     Array$.MODULE$.copy(array(), 0, narr, 0, len);
/* 490 */     narr[pos] = nn;
/* 491 */     return new CNode(bitmap(), narr, gen);
/*     */   }
/*     */   
/*     */   public CNode<K, V> removedAt(int pos, int flag, Gen gen) {
/* 495 */     BasicNode[] arr = array();
/* 496 */     int len = arr.length;
/* 497 */     BasicNode[] narr = new BasicNode[len - 1];
/* 498 */     Array$.MODULE$.copy(arr, 0, narr, 0, pos);
/* 499 */     Array$.MODULE$.copy(arr, pos + 1, narr, pos, len - pos - 1);
/* 500 */     return new CNode(bitmap() ^ flag, narr, gen);
/*     */   }
/*     */   
/*     */   public CNode<K, V> insertedAt(int pos, int flag, BasicNode nn, Gen gen) {
/* 504 */     int len = (array()).length;
/* 505 */     int bmp = bitmap();
/* 506 */     BasicNode[] narr = new BasicNode[len + 1];
/* 507 */     Array$.MODULE$.copy(array(), 0, narr, 0, pos);
/* 508 */     narr[pos] = nn;
/* 509 */     Array$.MODULE$.copy(array(), pos, narr, pos + 1, len - pos);
/* 510 */     return new CNode(bmp | flag, narr, gen);
/*     */   }
/*     */   
/*     */   public CNode<K, V> renewed(Gen ngen, TrieMap ct) {
/* 517 */     int i = 0;
/* 518 */     BasicNode[] arr = array();
/* 519 */     int len = arr.length;
/* 520 */     BasicNode[] narr = new BasicNode[len];
/* 521 */     while (i < len) {
/* 522 */       BasicNode basicNode = arr[i];
/* 523 */       if (basicNode instanceof INode) {
/* 523 */         INode iNode = (INode)basicNode;
/* 523 */         narr[i] = iNode.copyToGen(ngen, ct);
/* 524 */       } else if (basicNode != null) {
/* 524 */         narr[i] = basicNode;
/*     */       } else {
/*     */         throw new MatchError(basicNode);
/*     */       } 
/* 526 */       i++;
/*     */     } 
/* 528 */     return new CNode(bitmap(), narr, ngen);
/*     */   }
/*     */   
/*     */   private BasicNode resurrect(INode inode, Object inodemain) {
/*     */     INode iNode;
/* 531 */     if (inodemain instanceof TNode) {
/* 531 */       TNode tNode = (TNode)inodemain;
/* 531 */       SNode sNode = tNode.copyUntombed();
/*     */     } else {
/* 533 */       iNode = inode;
/*     */     } 
/*     */     return iNode;
/*     */   }
/*     */   
/*     */   public MainNode<K, V> toContracted(int lev) {
/*     */     CNode<K, V> cNode;
/* 536 */     BasicNode basicNode = array()[0];
/* 537 */     if (basicNode instanceof SNode) {
/* 537 */       SNode sNode = (SNode)basicNode;
/* 537 */       TNode tNode = sNode.copyTombed();
/*     */     } else {
/* 538 */       cNode = this;
/*     */     } 
/* 539 */     return ((array()).length == 1 && lev > 0) ? cNode : this;
/*     */   }
/*     */   
/*     */   public MainNode<K, V> toCompressed(TrieMap ct, int lev, Gen gen) {
/* 548 */     int bmp = bitmap();
/* 549 */     int i = 0;
/* 550 */     BasicNode[] arr = array();
/* 551 */     BasicNode[] tmparray = new BasicNode[arr.length];
/* 552 */     while (i < arr.length) {
/* 553 */       BasicNode sub = arr[i];
/* 554 */       if (sub instanceof INode) {
/* 554 */         INode<K, V> iNode = (INode)sub;
/* 556 */         MainNode inodemain = iNode.gcasRead(ct);
/* 557 */         Predef$.MODULE$.assert((inodemain != null));
/* 558 */         tmparray[i] = resurrect(iNode, inodemain);
/* 559 */       } else if (sub instanceof SNode) {
/* 559 */         SNode sNode = (SNode)sub;
/* 560 */         tmparray[i] = sNode;
/*     */       } else {
/*     */         throw new MatchError(sub);
/*     */       } 
/* 562 */       i++;
/*     */     } 
/* 565 */     return (new CNode(bmp, tmparray, gen)).toContracted(lev);
/*     */   }
/*     */   
/*     */   public String string(int lev) {
/* 568 */     Predef$ predef$ = Predef$.MODULE$;
/* 568 */     return (new StringOps("CNode %x\n%s")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(bitmap()), Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])array()).map((Function1)new CNode$$anonfun$string$1(this, lev), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(String.class)))).mkString("\n") }));
/*     */   }
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
/*     */   public Seq<Tuple2<K, V>> scala$collection$concurrent$CNode$$collectElems() {
/* 571 */     return (Seq<Tuple2<K, V>>)Predef$.MODULE$.refArrayOps((Object[])array()).flatMap((Function1)new CNode$$anonfun$scala$collection$concurrent$CNode$$collectElems$1(this), Array$.MODULE$.fallbackCanBuildFrom(Predef$DummyImplicit$.MODULE$.dummyImplicit()));
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
/* 571 */         Iterable iterable = Option$.MODULE$.option2Iterable((Option)new Some(sNode.kvPair()));
/*     */       } else {
/* 573 */         if (x0$1 instanceof INode) {
/*     */           Seq seq;
/* 573 */           INode iNode = (INode)x0$1;
/* 573 */           MainNode mainNode = iNode.mainnode;
/* 574 */           if (mainNode instanceof TNode) {
/* 574 */             TNode tNode = (TNode)mainNode;
/* 574 */             Iterable iterable = Option$.MODULE$.option2Iterable((Option)new Some(tNode.kvPair()));
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
/*     */   private Seq<String> collectLocalElems() {
/* 580 */     return (Seq<String>)Predef$.MODULE$.refArrayOps((Object[])array()).flatMap((Function1)new CNode$$anonfun$collectLocalElems$1(this), Array$.MODULE$.fallbackCanBuildFrom(Predef$DummyImplicit$.MODULE$.dummyImplicit()));
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
/* 580 */         Iterable iterable = Option$.MODULE$.option2Iterable((Option)new Some(sNode.kvPair()._2().toString()));
/*     */       } else {
/* 582 */         if (x0$2 instanceof INode) {
/* 582 */           INode iNode = (INode)x0$2;
/* 582 */           String str = iNode.toString();
/* 582 */           Predef$ predef$ = Predef$.MODULE$;
/* 582 */           return Option$.MODULE$.option2Iterable((Option)new Some((new StringBuilder()).append((new StringOps(str)).drop(14)).append("(").append(iNode.gen).append(")").toString()));
/*     */         } 
/*     */         throw new MatchError(x0$2);
/*     */       } 
/*     */       return (Iterable<String>)SYNTHETIC_LOCAL_VARIABLE_6;
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 586 */     Seq<String> elems = collectLocalElems();
/* 587 */     Predef$ predef$ = Predef$.MODULE$;
/* 587 */     return (new StringOps("CNode(sz: %d; %s)")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(elems.size()), ((TraversableOnce)elems.sorted((Ordering)Ordering$String$.MODULE$)).mkString(", ") }));
/*     */   }
/*     */   
/*     */   public static <K, V> MainNode<K, V> dual(SNode<K, V> paramSNode1, int paramInt1, SNode<K, V> paramSNode2, int paramInt2, int paramInt3, Gen paramGen) {
/*     */     return CNode$.MODULE$.dual(paramSNode1, paramInt1, paramSNode2, paramInt2, paramInt3, paramGen);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\CNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */