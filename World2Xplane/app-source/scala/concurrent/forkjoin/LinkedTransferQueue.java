/*      */ package scala.concurrent.forkjoin;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.AbstractQueue;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.concurrent.locks.LockSupport;
/*      */ import scala.concurrent.util.Unsafe;
/*      */ import sun.misc.Unsafe;
/*      */ 
/*      */ public class LinkedTransferQueue<E> extends AbstractQueue<E> implements TransferQueue<E>, Serializable {
/*      */   private static final long serialVersionUID = -3223113410248163686L;
/*      */   
/*  382 */   private static final boolean MP = (Runtime.getRuntime().availableProcessors() > 1);
/*      */   
/*      */   private static final int FRONT_SPINS = 128;
/*      */   
/*      */   private static final int CHAINED_SPINS = 64;
/*      */   
/*      */   static final int SWEEP_THRESHOLD = 32;
/*      */   
/*      */   volatile transient Node head;
/*      */   
/*      */   private volatile transient Node tail;
/*      */   
/*      */   private volatile transient int sweepVotes;
/*      */   
/*      */   private static final int NOW = 0;
/*      */   
/*      */   private static final int ASYNC = 1;
/*      */   
/*      */   private static final int SYNC = 2;
/*      */   
/*      */   private static final int TIMED = 3;
/*      */   
/*      */   private static final Unsafe UNSAFE;
/*      */   
/*      */   private static final long headOffset;
/*      */   
/*      */   private static final long tailOffset;
/*      */   
/*      */   private static final long sweepVotesOffset;
/*      */   
/*      */   static final class Node {
/*      */     final boolean isData;
/*      */     
/*      */     volatile Object item;
/*      */     
/*      */     volatile Node next;
/*      */     
/*      */     volatile Thread waiter;
/*      */     
/*      */     private static final long serialVersionUID = -3375979862319811754L;
/*      */     
/*      */     private static final Unsafe UNSAFE;
/*      */     
/*      */     private static final long itemOffset;
/*      */     
/*      */     private static final long nextOffset;
/*      */     
/*      */     private static final long waiterOffset;
/*      */     
/*      */     final boolean casNext(Node cmp, Node val) {
/*  427 */       return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
/*      */     }
/*      */     
/*      */     final boolean casItem(Object cmp, Object val) {
/*  432 */       return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
/*      */     }
/*      */     
/*      */     Node(Object item, boolean isData) {
/*  440 */       UNSAFE.putObject(this, itemOffset, item);
/*  441 */       this.isData = isData;
/*      */     }
/*      */     
/*      */     final void forgetNext() {
/*  449 */       UNSAFE.putObject(this, nextOffset, this);
/*      */     }
/*      */     
/*      */     final void forgetContents() {
/*  462 */       UNSAFE.putObject(this, itemOffset, this);
/*  463 */       UNSAFE.putObject(this, waiterOffset, null);
/*      */     }
/*      */     
/*      */     final boolean isMatched() {
/*  471 */       Object x = this.item;
/*  472 */       return (x == this || ((x == null)) == this.isData);
/*      */     }
/*      */     
/*      */     final boolean isUnmatchedRequest() {
/*  479 */       return (!this.isData && this.item == null);
/*      */     }
/*      */     
/*      */     final boolean cannotPrecede(boolean haveData) {
/*  488 */       boolean d = this.isData;
/*      */       Object x;
/*  490 */       return (d != haveData && (x = this.item) != this && ((x != null)) == d);
/*      */     }
/*      */     
/*      */     final boolean tryMatchData() {
/*  498 */       Object x = this.item;
/*  499 */       if (x != null && x != this && casItem(x, null)) {
/*  500 */         LockSupport.unpark(this.waiter);
/*  501 */         return true;
/*      */       } 
/*  503 */       return false;
/*      */     }
/*      */     
/*      */     static {
/*      */       try {
/*  515 */         UNSAFE = LinkedTransferQueue.getUnsafe();
/*  516 */         Class<?> k = Node.class;
/*  517 */         itemOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("item"));
/*  519 */         nextOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("next"));
/*  521 */         waiterOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("waiter"));
/*  523 */       } catch (Exception e) {
/*  524 */         throw new Error(e);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean casTail(Node cmp, Node val) {
/*  540 */     return UNSAFE.compareAndSwapObject(this, tailOffset, cmp, val);
/*      */   }
/*      */   
/*      */   private boolean casHead(Node cmp, Node val) {
/*  544 */     return UNSAFE.compareAndSwapObject(this, headOffset, cmp, val);
/*      */   }
/*      */   
/*      */   private boolean casSweepVotes(int cmp, int val) {
/*  548 */     return UNSAFE.compareAndSwapInt(this, sweepVotesOffset, cmp, val);
/*      */   }
/*      */   
/*      */   static <E> E cast(Object item) {
/*  562 */     return (E)item;
/*      */   }
/*      */   
/*      */   private E xfer(E e, boolean haveData, int how, long nanos) {
/*  576 */     if (haveData && e == null)
/*  577 */       throw new NullPointerException(); 
/*  578 */     Node s = null;
/*      */     while (true) {
/*  583 */       for (Node h = this.head, p = h; p != null; ) {
/*  584 */         boolean isData = p.isData;
/*  585 */         Object item = p.item;
/*  586 */         if (item != p && ((item != null)) == isData) {
/*  587 */           if (isData == haveData)
/*      */             break; 
/*  589 */           if (p.casItem(item, e)) {
/*  590 */             for (Node q = p; q != h; ) {
/*  591 */               Node node = q.next;
/*  592 */               if (this.head == h && casHead(h, (node == null) ? q : node)) {
/*  593 */                 h.forgetNext();
/*      */                 break;
/*      */               } 
/*  596 */               if ((h = this.head) == null || (q = h.next) == null || !q.isMatched())
/*      */                 break; 
/*      */             } 
/*  600 */             LockSupport.unpark(p.waiter);
/*  601 */             return cast(item);
/*      */           } 
/*      */         } 
/*  604 */         Node n = p.next;
/*  605 */         p = (p != n) ? n : (h = this.head);
/*      */       } 
/*  608 */       if (how != 0) {
/*  609 */         if (s == null)
/*  610 */           s = new Node(e, haveData); 
/*  611 */         Node pred = tryAppend(s, haveData);
/*  612 */         if (pred == null)
/*      */           continue; 
/*  614 */         if (how != 1)
/*  615 */           return awaitMatch(s, pred, e, (how == 3), nanos); 
/*      */       } 
/*      */       break;
/*      */     } 
/*  617 */     return e;
/*      */   }
/*      */   
/*      */   private Node tryAppend(Node s, boolean haveData) {
/*  631 */     Node t = this.tail, p = t;
/*      */     while (true) {
/*  633 */       if (p == null && (p = this.head) == null) {
/*  634 */         if (casHead(null, s))
/*  635 */           return s; 
/*      */         continue;
/*      */       } 
/*  637 */       if (p.cannotPrecede(haveData))
/*  638 */         return null; 
/*      */       Node n;
/*  639 */       if ((n = p.next) != null) {
/*      */         Node u;
/*  640 */         p = (p != t && t != (u = this.tail)) ? (t = u) : ((p != n) ? n : null);
/*      */         continue;
/*      */       } 
/*  642 */       if (!p.casNext(null, s)) {
/*  643 */         p = p.next;
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/*  645 */     if (p != t)
/*  649 */       while ((this.tail != t || !casTail(t, s)) && (t = this.tail) != null && (s = t.next) != null && (s = s.next) != null && s != t); 
/*  651 */     return p;
/*      */   }
/*      */   
/*      */   private E awaitMatch(Node s, Node pred, E e, boolean timed, long nanos) {
/*  669 */     long lastTime = timed ? System.nanoTime() : 0L;
/*  670 */     Thread w = Thread.currentThread();
/*  671 */     int spins = -1;
/*  672 */     ThreadLocalRandom randomYields = null;
/*      */     while (true) {
/*  675 */       Object item = s.item;
/*  676 */       if (item != e) {
/*  678 */         s.forgetContents();
/*  679 */         return cast(item);
/*      */       } 
/*  681 */       if ((w.isInterrupted() || (timed && nanos <= 0L)) && s.casItem(e, s)) {
/*  683 */         unsplice(pred, s);
/*  684 */         return e;
/*      */       } 
/*  687 */       if (spins < 0) {
/*  688 */         if ((spins = spinsFor(pred, s.isData)) > 0)
/*  689 */           randomYields = ThreadLocalRandom.current(); 
/*      */         continue;
/*      */       } 
/*  691 */       if (spins > 0) {
/*  692 */         spins--;
/*  693 */         if (randomYields.nextInt(64) == 0)
/*  694 */           Thread.yield(); 
/*      */         continue;
/*      */       } 
/*  696 */       if (s.waiter == null) {
/*  697 */         s.waiter = w;
/*      */         continue;
/*      */       } 
/*  699 */       if (timed) {
/*  700 */         long now = System.nanoTime();
/*  701 */         if ((nanos -= now - lastTime) > 0L)
/*  702 */           LockSupport.parkNanos(this, nanos); 
/*  703 */         lastTime = now;
/*      */         continue;
/*      */       } 
/*  706 */       LockSupport.park(this);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int spinsFor(Node pred, boolean haveData) {
/*  716 */     if (MP && pred != null) {
/*  717 */       if (pred.isData != haveData)
/*  718 */         return 192; 
/*  719 */       if (pred.isMatched())
/*  720 */         return 128; 
/*  721 */       if (pred.waiter == null)
/*  722 */         return 64; 
/*      */     } 
/*  724 */     return 0;
/*      */   }
/*      */   
/*      */   final Node succ(Node p) {
/*  735 */     Node next = p.next;
/*  736 */     return (p == next) ? this.head : next;
/*      */   }
/*      */   
/*      */   private Node firstOfMode(boolean isData) {
/*  744 */     for (Node p = this.head; p != null; p = succ(p)) {
/*  745 */       if (!p.isMatched())
/*  746 */         return (p.isData == isData) ? p : null; 
/*      */     } 
/*  748 */     return null;
/*      */   }
/*      */   
/*      */   private E firstDataItem() {
/*  756 */     for (Node p = this.head; p != null; p = succ(p)) {
/*  757 */       Object item = p.item;
/*  758 */       if (p.isData) {
/*  759 */         if (item != null && item != p)
/*  760 */           return cast(item); 
/*  762 */       } else if (item == null) {
/*  763 */         return null;
/*      */       } 
/*      */     } 
/*  765 */     return null;
/*      */   }
/*      */   
/*      */   private int countOfMode(boolean data) {
/*  773 */     int count = 0;
/*  774 */     for (Node p = this.head; p != null; ) {
/*  775 */       if (!p.isMatched()) {
/*  776 */         if (p.isData != data)
/*  777 */           return 0; 
/*  778 */         if (++count == Integer.MAX_VALUE)
/*      */           break; 
/*      */       } 
/*  781 */       Node n = p.next;
/*  782 */       if (n != p) {
/*  783 */         p = n;
/*      */         continue;
/*      */       } 
/*  785 */       count = 0;
/*  786 */       p = this.head;
/*      */     } 
/*  789 */     return count;
/*      */   }
/*      */   
/*      */   final class Itr implements Iterator<E> {
/*      */     private LinkedTransferQueue.Node nextNode;
/*      */     
/*      */     private E nextItem;
/*      */     
/*      */     private LinkedTransferQueue.Node lastRet;
/*      */     
/*      */     private LinkedTransferQueue.Node lastPred;
/*      */     
/*      */     private void advance(LinkedTransferQueue.Node prev) {
/*      */       LinkedTransferQueue.Node r;
/*  813 */       if ((r = this.lastRet) != null && !r.isMatched()) {
/*  814 */         this.lastPred = r;
/*      */       } else {
/*      */         LinkedTransferQueue.Node b;
/*  815 */         if ((b = this.lastPred) == null || b.isMatched()) {
/*  816 */           this.lastPred = null;
/*      */         } else {
/*      */           LinkedTransferQueue.Node s;
/*      */           LinkedTransferQueue.Node n;
/*  821 */           while ((s = b.next) != null && s != b && s.isMatched() && (n = s.next) != null && n != s)
/*  822 */             b.casNext(s, n); 
/*      */         } 
/*      */       } 
/*  825 */       this.lastRet = prev;
/*  827 */       LinkedTransferQueue.Node p = prev;
/*      */       while (true) {
/*  828 */         LinkedTransferQueue.Node s = (p == null) ? LinkedTransferQueue.this.head : p.next;
/*  829 */         if (s == null)
/*      */           break; 
/*  831 */         if (s == p) {
/*  832 */           p = null;
/*      */           continue;
/*      */         } 
/*  835 */         Object item = s.item;
/*  836 */         if (s.isData) {
/*  837 */           if (item != null && item != s) {
/*  838 */             this.nextItem = LinkedTransferQueue.cast(item);
/*  839 */             this.nextNode = s;
/*      */             return;
/*      */           } 
/*  843 */         } else if (item == null) {
/*      */           break;
/*      */         } 
/*  846 */         if (p == null) {
/*  847 */           p = s;
/*      */           continue;
/*      */         } 
/*      */         LinkedTransferQueue.Node n;
/*  848 */         if ((n = s.next) == null)
/*      */           break; 
/*  850 */         if (s == n) {
/*  851 */           p = null;
/*      */           continue;
/*      */         } 
/*  853 */         p.casNext(s, n);
/*      */       } 
/*  855 */       this.nextNode = null;
/*  856 */       this.nextItem = null;
/*      */     }
/*      */     
/*      */     Itr() {
/*  860 */       advance(null);
/*      */     }
/*      */     
/*      */     public final boolean hasNext() {
/*  864 */       return (this.nextNode != null);
/*      */     }
/*      */     
/*      */     public final E next() {
/*  868 */       LinkedTransferQueue.Node p = this.nextNode;
/*  869 */       if (p == null)
/*  869 */         throw new NoSuchElementException(); 
/*  870 */       E e = this.nextItem;
/*  871 */       advance(p);
/*  872 */       return e;
/*      */     }
/*      */     
/*      */     public final void remove() {
/*  876 */       LinkedTransferQueue.Node lastRet = this.lastRet;
/*  877 */       if (lastRet == null)
/*  878 */         throw new IllegalStateException(); 
/*  879 */       this.lastRet = null;
/*  880 */       if (lastRet.tryMatchData())
/*  881 */         LinkedTransferQueue.this.unsplice(this.lastPred, lastRet); 
/*      */     }
/*      */   }
/*      */   
/*      */   final void unsplice(Node pred, Node s) {
/*  896 */     s.forgetContents();
/*  904 */     if (pred != null && pred != s && pred.next == s) {
/*  905 */       Node n = s.next;
/*  906 */       if (n == null || (n != s && pred.casNext(s, n) && pred.isMatched())) {
/*      */         while (true) {
/*  909 */           Node h = this.head;
/*  910 */           if (h == pred || h == s || h == null)
/*      */             return; 
/*  912 */           if (!h.isMatched())
/*      */             break; 
/*  914 */           Node hn = h.next;
/*  915 */           if (hn == null)
/*      */             return; 
/*  917 */           if (hn != h && casHead(h, hn))
/*  918 */             h.forgetNext(); 
/*      */         } 
/*  920 */         if (pred.next != pred && s.next != s)
/*      */           while (true) {
/*  922 */             int v = this.sweepVotes;
/*  923 */             if (v < 32) {
/*  924 */               if (casSweepVotes(v, v + 1))
/*      */                 break; 
/*      */               continue;
/*      */             } 
/*  927 */             if (casSweepVotes(v, 0)) {
/*  928 */               sweep();
/*      */               break;
/*      */             } 
/*      */           }  
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void sweep() {
/*      */     Node s;
/*  942 */     for (Node p = this.head; p != null && (s = p.next) != null; ) {
/*  943 */       if (!s.isMatched()) {
/*  945 */         p = s;
/*      */         continue;
/*      */       } 
/*      */       Node n;
/*  946 */       if ((n = s.next) == null)
/*      */         break; 
/*  948 */       if (s == n) {
/*  950 */         p = this.head;
/*      */         continue;
/*      */       } 
/*  952 */       p.casNext(s, n);
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean findAndRemove(Object e) {
/*  960 */     if (e != null)
/*  961 */       for (Node pred = null, p = this.head; p != null; ) {
/*  962 */         Object item = p.item;
/*  963 */         if (p.isData) {
/*  964 */           if (item != null && item != p && e.equals(item) && p.tryMatchData()) {
/*  966 */             unsplice(pred, p);
/*  967 */             return true;
/*      */           } 
/*  970 */         } else if (item == null) {
/*      */           break;
/*      */         } 
/*  972 */         pred = p;
/*  973 */         if ((p = p.next) == pred) {
/*  974 */           pred = null;
/*  975 */           p = this.head;
/*      */         } 
/*      */       }  
/*  979 */     return false;
/*      */   }
/*      */   
/*      */   public LinkedTransferQueue() {}
/*      */   
/*      */   public LinkedTransferQueue(Collection<? extends E> c) {
/*  999 */     this();
/* 1000 */     addAll(c);
/*      */   }
/*      */   
/*      */   public void put(E e) {
/* 1010 */     xfer(e, true, 1, 0L);
/*      */   }
/*      */   
/*      */   public boolean offer(E e, long timeout, TimeUnit unit) {
/* 1024 */     xfer(e, true, 1, 0L);
/* 1025 */     return true;
/*      */   }
/*      */   
/*      */   public boolean offer(E e) {
/* 1036 */     xfer(e, true, 1, 0L);
/* 1037 */     return true;
/*      */   }
/*      */   
/*      */   public boolean add(E e) {
/* 1049 */     xfer(e, true, 1, 0L);
/* 1050 */     return true;
/*      */   }
/*      */   
/*      */   public boolean tryTransfer(E e) {
/* 1064 */     return (xfer(e, true, 0, 0L) == null);
/*      */   }
/*      */   
/*      */   public void transfer(E e) throws InterruptedException {
/* 1079 */     if (xfer(e, true, 2, 0L) != null) {
/* 1080 */       Thread.interrupted();
/* 1081 */       throw new InterruptedException();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
/* 1101 */     if (xfer(e, true, 3, unit.toNanos(timeout)) == null)
/* 1102 */       return true; 
/* 1103 */     if (!Thread.interrupted())
/* 1104 */       return false; 
/* 1105 */     throw new InterruptedException();
/*      */   }
/*      */   
/*      */   public E take() throws InterruptedException {
/* 1109 */     E e = xfer((E)null, false, 2, 0L);
/* 1110 */     if (e != null)
/* 1111 */       return e; 
/* 1112 */     Thread.interrupted();
/* 1113 */     throw new InterruptedException();
/*      */   }
/*      */   
/*      */   public E poll(long timeout, TimeUnit unit) throws InterruptedException {
/* 1117 */     E e = xfer((E)null, false, 3, unit.toNanos(timeout));
/* 1118 */     if (e != null || !Thread.interrupted())
/* 1119 */       return e; 
/* 1120 */     throw new InterruptedException();
/*      */   }
/*      */   
/*      */   public E poll() {
/* 1124 */     return xfer((E)null, false, 0, 0L);
/*      */   }
/*      */   
/*      */   public int drainTo(Collection<? super E> c) {
/* 1132 */     if (c == null)
/* 1133 */       throw new NullPointerException(); 
/* 1134 */     if (c == this)
/* 1135 */       throw new IllegalArgumentException(); 
/* 1136 */     int n = 0;
/*      */     E e;
/* 1137 */     while ((e = poll()) != null) {
/* 1138 */       c.add(e);
/* 1139 */       n++;
/*      */     } 
/* 1141 */     return n;
/*      */   }
/*      */   
/*      */   public int drainTo(Collection<? super E> c, int maxElements) {
/* 1149 */     if (c == null)
/* 1150 */       throw new NullPointerException(); 
/* 1151 */     if (c == this)
/* 1152 */       throw new IllegalArgumentException(); 
/* 1153 */     int n = 0;
/*      */     E e;
/* 1154 */     while (n < maxElements && (e = poll()) != null) {
/* 1155 */       c.add(e);
/* 1156 */       n++;
/*      */     } 
/* 1158 */     return n;
/*      */   }
/*      */   
/*      */   public Iterator<E> iterator() {
/* 1175 */     return new Itr();
/*      */   }
/*      */   
/*      */   public E peek() {
/* 1179 */     return firstDataItem();
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1188 */     for (Node p = this.head; p != null; p = succ(p)) {
/* 1189 */       if (!p.isMatched())
/* 1190 */         return !p.isData; 
/*      */     } 
/* 1192 */     return true;
/*      */   }
/*      */   
/*      */   public boolean hasWaitingConsumer() {
/* 1196 */     return (firstOfMode(false) != null);
/*      */   }
/*      */   
/*      */   public int size() {
/* 1212 */     return countOfMode(true);
/*      */   }
/*      */   
/*      */   public int getWaitingConsumerCount() {
/* 1216 */     return countOfMode(false);
/*      */   }
/*      */   
/*      */   public boolean remove(Object o) {
/* 1231 */     return findAndRemove(o);
/*      */   }
/*      */   
/*      */   public boolean contains(Object o) {
/* 1243 */     if (o == null)
/* 1243 */       return false; 
/* 1244 */     for (Node p = this.head; p != null; p = succ(p)) {
/* 1245 */       Object item = p.item;
/* 1246 */       if (p.isData) {
/* 1247 */         if (item != null && item != p && o.equals(item))
/* 1248 */           return true; 
/* 1250 */       } else if (item == null) {
/*      */         break;
/*      */       } 
/*      */     } 
/* 1253 */     return false;
/*      */   }
/*      */   
/*      */   public int remainingCapacity() {
/* 1265 */     return Integer.MAX_VALUE;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1277 */     s.defaultWriteObject();
/* 1278 */     for (E e : this)
/* 1279 */       s.writeObject(e); 
/* 1281 */     s.writeObject(null);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1292 */     s.defaultReadObject();
/*      */     while (true) {
/* 1295 */       E item = (E)s.readObject();
/* 1296 */       if (item == null)
/*      */         break; 
/* 1299 */       offer(item);
/*      */     } 
/*      */   }
/*      */   
/*      */   static {
/*      */     try {
/* 1311 */       UNSAFE = getUnsafe();
/* 1312 */       Class<?> k = LinkedTransferQueue.class;
/* 1313 */       headOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("head"));
/* 1315 */       tailOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("tail"));
/* 1317 */       sweepVotesOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("sweepVotes"));
/* 1319 */     } catch (Exception e) {
/* 1320 */       throw new Error(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   static Unsafe getUnsafe() {
/* 1332 */     return Unsafe.instance;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\LinkedTransferQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */