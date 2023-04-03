/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public final class Queues {
/*     */   public static abstract class SimpleQueue<E> implements BlockingQueue<E> {
/*     */     protected final boolean useLocks;
/*     */     
/*     */     protected final ReentrantLock[] locks;
/*     */     
/*     */     protected static final int TICK = 10000;
/*     */     
/*     */     protected final Engine engine;
/*     */     
/*     */     protected final Serializer<E> serializer;
/*     */     
/*     */     protected final Atomic.Long head;
/*     */     
/*     */     protected final Serializer<Node<E>> nodeSerializer;
/*     */     
/*     */     protected static class NodeSerializer<E> implements Serializer<Node<E>> {
/*     */       private final Serializer<E> serializer;
/*     */       
/*     */       public NodeSerializer(Serializer<E> serializer) {
/*  56 */         this.serializer = serializer;
/*     */       }
/*     */       
/*     */       public void serialize(DataOutput out, Queues.SimpleQueue.Node<E> value) throws IOException {
/*  61 */         if (value == Queues.SimpleQueue.Node.EMPTY)
/*     */           return; 
/*  62 */         DataOutput2.packLong(out, value.next);
/*  63 */         this.serializer.serialize(out, value.value);
/*     */       }
/*     */       
/*     */       public Queues.SimpleQueue.Node<E> deserialize(DataInput in, int available) throws IOException {
/*  68 */         if (available == 0)
/*  68 */           return (Queues.SimpleQueue.Node)Queues.SimpleQueue.Node.EMPTY; 
/*  69 */         return new Queues.SimpleQueue.Node<E>(DataInput2.unpackLong(in), this.serializer.deserialize(in, -1));
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/*  74 */         return -1;
/*     */       }
/*     */     }
/*     */     
/*     */     public SimpleQueue(Engine engine, Serializer<E> serializer, long headRecidRef, boolean useLocks) {
/*  83 */       this.engine = engine;
/*  84 */       this.serializer = serializer;
/*  85 */       this.head = new Atomic.Long(engine, headRecidRef);
/*  86 */       this.nodeSerializer = new NodeSerializer<E>(serializer);
/*  87 */       this.useLocks = useLocks;
/*  88 */       if (useLocks) {
/*  89 */         this.locks = new ReentrantLock[128];
/*  90 */         for (int i = 0; i < this.locks.length; i++)
/*  91 */           this.locks[i] = new ReentrantLock(false); 
/*     */       } else {
/*  93 */         this.locks = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 105 */       this.engine.close();
/*     */     }
/*     */     
/*     */     public E peek() {
/* 111 */       long head2 = this.head.get();
/* 112 */       if (this.useLocks)
/* 112 */         this.locks[Store.lockPos(head2)].lock(); 
/*     */       try {
/* 114 */         Node<?> n = this.engine.<Node>get(head2, (Serializer)this.nodeSerializer);
/* 115 */         if (n == Node.EMPTY)
/* 116 */           return null; 
/* 117 */         return n.value;
/*     */       } finally {
/* 119 */         if (this.useLocks)
/* 119 */           this.locks[Store.lockPos(head2)].unlock(); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public E poll() {
/*     */       while (true) {
/* 127 */         long head2 = this.head.get();
/* 128 */         if (this.useLocks)
/* 128 */           this.locks[Store.lockPos(head2)].lock(); 
/*     */         try {
/* 130 */           Node<?> n = this.engine.<Node>get(head2, (Serializer)this.nodeSerializer);
/* 131 */           if (n == Node.EMPTY)
/* 132 */             return null; 
/* 135 */           if (this.head.compareAndSet(head2, n.next)) {
/* 137 */             if (this.useLocks) {
/* 138 */               this.engine.delete(head2, this.nodeSerializer);
/*     */             } else {
/* 140 */               this.engine.update(head2, Node.EMPTY, this.nodeSerializer);
/*     */             } 
/* 142 */             return n.value;
/*     */           } 
/*     */         } finally {
/* 146 */           if (this.useLocks)
/* 146 */             this.locks[Store.lockPos(head2)].unlock(); 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected static final class Node<E> {
/* 154 */       protected static final Node<?> EMPTY = new Node(0L, null);
/*     */       
/*     */       protected final long next;
/*     */       
/*     */       protected final E value;
/*     */       
/*     */       public Node(long next, E value) {
/* 160 */         this.next = next;
/* 161 */         this.value = value;
/*     */       }
/*     */       
/*     */       public boolean equals(Object o) {
/* 166 */         if (this == o)
/* 166 */           return true; 
/* 167 */         if (o == null || getClass() != o.getClass())
/* 167 */           return false; 
/* 169 */         Node node = (Node)o;
/* 171 */         if (this.next != node.next)
/* 171 */           return false; 
/* 172 */         if ((this.value != null) ? !this.value.equals(node.value) : (node.value != null))
/* 172 */           return false; 
/* 174 */         return true;
/*     */       }
/*     */       
/*     */       public int hashCode() {
/* 179 */         int result = (int)(this.next ^ this.next >>> 32L);
/* 180 */         result = 31 * result + ((this.value != null) ? this.value.hashCode() : 0);
/* 181 */         return result;
/*     */       }
/*     */     }
/*     */     
/*     */     public void clear() {
/* 187 */       while (!isEmpty())
/* 188 */         poll(); 
/*     */     }
/*     */     
/*     */     public E remove() {
/* 194 */       E ret = poll();
/* 195 */       if (ret == null)
/* 195 */         throw new NoSuchElementException(); 
/* 196 */       return ret;
/*     */     }
/*     */     
/*     */     public E element() {
/* 202 */       E ret = peek();
/* 203 */       if (ret == null)
/* 203 */         throw new NoSuchElementException(); 
/* 204 */       return ret;
/*     */     }
/*     */     
/*     */     public boolean offer(E e) {
/*     */       try {
/* 211 */         return add(e);
/* 212 */       } catch (IllegalStateException ee) {
/* 213 */         return false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void put(E e) throws InterruptedException {
/* 220 */       while (!offer(e))
/* 221 */         Thread.sleep(0L, 10000); 
/*     */     }
/*     */     
/*     */     public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
/* 227 */       if (offer(e))
/* 227 */         return true; 
/* 228 */       long target = System.currentTimeMillis() + unit.toMillis(timeout);
/* 229 */       while (target >= System.currentTimeMillis()) {
/* 230 */         if (offer(e))
/* 231 */           return true; 
/* 232 */         Thread.sleep(0L, 10000);
/*     */       } 
/* 235 */       return false;
/*     */     }
/*     */     
/*     */     public E take() throws InterruptedException {
/* 240 */       E e = poll();
/* 241 */       while (e == null) {
/* 242 */         Thread.sleep(0L, 10000);
/* 243 */         e = poll();
/*     */       } 
/* 245 */       return e;
/*     */     }
/*     */     
/*     */     public E poll(long timeout, TimeUnit unit) throws InterruptedException {
/* 250 */       E e = poll();
/* 251 */       if (e != null)
/* 251 */         return e; 
/* 252 */       long target = System.currentTimeMillis() + unit.toMillis(timeout);
/* 253 */       while (target >= System.currentTimeMillis()) {
/* 254 */         Thread.sleep(0L, 10000);
/* 255 */         e = poll();
/* 256 */         if (e != null)
/* 257 */           return e; 
/*     */       } 
/* 259 */       return null;
/*     */     }
/*     */     
/*     */     public int drainTo(Collection<? super E> c) {
/* 264 */       return drainTo(c, 2147483647);
/*     */     }
/*     */     
/*     */     public int drainTo(Collection<? super E> c, int maxElements) {
/* 269 */       int counter = 0;
/* 270 */       while (counter < maxElements) {
/* 271 */         E e = poll();
/* 272 */         if (e == null)
/* 273 */           return counter; 
/* 274 */         c.add(e);
/* 275 */         counter++;
/*     */       } 
/* 277 */       return counter;
/*     */     }
/*     */     
/*     */     public int remainingCapacity() {
/* 282 */       return Integer.MAX_VALUE;
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 287 */       return (peek() == null);
/*     */     }
/*     */     
/*     */     public int size() {
/* 293 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 299 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator<E> iterator() {
/* 304 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 309 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public <T> T[] toArray(T[] a) {
/* 314 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 320 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection<?> c) {
/* 325 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection<? extends E> c) {
/* 330 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection<?> c) {
/* 335 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection<?> c) {
/* 340 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Stack<E> extends SimpleQueue<E> {
/*     */     public Stack(Engine engine, Serializer<E> serializer, long headerRecidRef, boolean useLocks) {
/* 356 */       super(engine, serializer, headerRecidRef, useLocks);
/*     */     }
/*     */     
/*     */     public boolean add(E e) {
/* 361 */       long head2 = this.head.get();
/* 362 */       Queues.SimpleQueue.Node<E> n = new Queues.SimpleQueue.Node<E>(head2, e);
/* 363 */       long recid = this.engine.put(n, this.nodeSerializer);
/* 364 */       while (!this.head.compareAndSet(head2, recid)) {
/* 366 */         head2 = this.head.get();
/* 367 */         n = new Queues.SimpleQueue.Node<E>(head2, e);
/* 368 */         this.engine.update(recid, n, this.nodeSerializer);
/*     */       } 
/* 370 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Queue<E> extends SimpleQueue<E> {
/*     */     protected final Atomic.Long tail;
/*     */     
/*     */     public Queue(Engine engine, Serializer<E> serializer, long headerRecid, long nextTailRecid, boolean useLocks) {
/* 386 */       super(engine, serializer, headerRecid, useLocks);
/* 387 */       this.tail = new Atomic.Long(engine, nextTailRecid);
/*     */     }
/*     */     
/*     */     public boolean add(E e) {
/* 392 */       long nextTail = this.engine.put(Queues.SimpleQueue.Node.EMPTY, this.nodeSerializer);
/* 393 */       long tail2 = this.tail.get();
/* 394 */       while (!this.tail.compareAndSet(tail2, nextTail))
/* 395 */         tail2 = this.tail.get(); 
/* 398 */       Queues.SimpleQueue.Node<E> n = new Queues.SimpleQueue.Node<E>(nextTail, e);
/* 399 */       this.engine.update(tail2, n, this.nodeSerializer);
/* 400 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CircularQueue<E> extends SimpleQueue<E> {
/*     */     protected final Atomic.Long headInsert;
/*     */     
/* 414 */     protected final Lock lock = new ReentrantLock(false);
/*     */     
/*     */     protected final long size;
/*     */     
/*     */     public CircularQueue(Engine engine, Serializer<E> serializer, long headRecid, long headInsertRecid, long size) {
/* 418 */       super(engine, serializer, headRecid, false);
/* 419 */       this.headInsert = new Atomic.Long(engine, headInsertRecid);
/* 420 */       this.size = size;
/*     */     }
/*     */     
/*     */     public boolean add(Object o) {
/* 425 */       this.lock.lock();
/*     */       try {
/* 427 */         long nRecid = this.headInsert.get();
/* 428 */         Queues.SimpleQueue.Node<E> n = this.engine.<Queues.SimpleQueue.Node<E>>get(nRecid, this.nodeSerializer);
/* 429 */         n = new Queues.SimpleQueue.Node<E>(n.next, (E)o);
/* 430 */         this.engine.update(nRecid, n, this.nodeSerializer);
/* 431 */         this.headInsert.set(n.next);
/* 433 */         this.head.compareAndSet(nRecid, n.next);
/* 434 */         return true;
/*     */       } finally {
/* 436 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void clear() {
/* 443 */       this.lock.lock();
/*     */       try {
/* 445 */         for (int i = 0; i < this.size; i++)
/* 446 */           poll(); 
/*     */       } finally {
/* 449 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public E poll() {
/* 455 */       this.lock.lock();
/*     */       try {
/* 457 */         long nRecid = this.head.get();
/* 458 */         Queues.SimpleQueue.Node<E> n = this.engine.<Queues.SimpleQueue.Node<E>>get(nRecid, this.nodeSerializer);
/* 459 */         this.engine.update(nRecid, new Queues.SimpleQueue.Node<E>(n.next, null), this.nodeSerializer);
/* 460 */         this.head.set(n.next);
/* 461 */         return n.value;
/*     */       } finally {
/* 463 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public E peek() {
/* 469 */       this.lock.lock();
/*     */       try {
/* 471 */         long nRecid = this.head.get();
/* 472 */         Queues.SimpleQueue.Node<E> n = this.engine.<Queues.SimpleQueue.Node<E>>get(nRecid, this.nodeSerializer);
/* 473 */         return n.value;
/*     */       } finally {
/* 475 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Queues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */