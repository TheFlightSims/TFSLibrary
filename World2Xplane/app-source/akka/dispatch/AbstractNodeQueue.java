/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.util.Unsafe;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ 
/*     */ public abstract class AbstractNodeQueue<T> extends AtomicReference<AbstractNodeQueue.Node<T>> {
/*     */   private volatile Node<T> _tailDoNotCallMeDirectly;
/*     */   
/*     */   private static final long tailOffset;
/*     */   
/*     */   protected AbstractNodeQueue() {
/*  22 */     Node<T> node = new Node();
/*  23 */     this._tailDoNotCallMeDirectly = node;
/*  24 */     set(node);
/*     */   }
/*     */   
/*     */   protected final Node<T> peekNode() {
/*     */     Node<T> node1;
/*     */     Node<T> node2;
/*     */     do {
/*  33 */       node1 = (Node)Unsafe.instance.getObjectVolatile(this, tailOffset);
/*  34 */       node2 = node1.next();
/*  35 */     } while (node2 == null && get() != node1);
/*  36 */     return node2;
/*     */   }
/*     */   
/*     */   public final T peek() {
/*  41 */     Node<T> node = peekNode();
/*  42 */     return (node != null) ? node.value : null;
/*     */   }
/*     */   
/*     */   public final void add(T paramT) {
/*  46 */     Node<T> node = new Node<T>(paramT);
/*  47 */     getAndSet(node).setNext(node);
/*     */   }
/*     */   
/*     */   public final void addNode(Node<T> paramNode) {
/*  51 */     paramNode.setNext(null);
/*  52 */     getAndSet(paramNode).setNext(paramNode);
/*     */   }
/*     */   
/*     */   public final boolean isEmpty() {
/*  56 */     return (peek() == null);
/*     */   }
/*     */   
/*     */   public final int count() {
/*  60 */     byte b = 0;
/*  61 */     for (Node<T> node = peekNode(); node != null; node = node.next())
/*  62 */       b++; 
/*  63 */     return b;
/*     */   }
/*     */   
/*     */   public final T poll() {
/*  70 */     Node<T> node = peekNode();
/*  71 */     if (node == null)
/*  71 */       return null; 
/*  73 */     T t = node.value;
/*  74 */     node.value = null;
/*  75 */     Unsafe.instance.putOrderedObject(this, tailOffset, node);
/*  76 */     return t;
/*     */   }
/*     */   
/*     */   public final Node<T> pollNode() {
/*     */     Node<T> node;
/*     */     Node node1;
/*     */     do {
/*  85 */       node = (Node)Unsafe.instance.getObjectVolatile(this, tailOffset);
/*  86 */       node1 = node.next();
/*  87 */     } while (node1 == null && get() != node);
/*  90 */     if (node1 == null)
/*  90 */       return null; 
/*  92 */     node.value = node1.value;
/*  93 */     node1.value = null;
/*  94 */     Unsafe.instance.putOrderedObject(this, tailOffset, node1);
/*  95 */     return node;
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/* 103 */       tailOffset = Unsafe.instance.objectFieldOffset(AbstractNodeQueue.class.getDeclaredField("_tailDoNotCallMeDirectly"));
/* 104 */     } catch (Throwable throwable) {
/* 105 */       throw new ExceptionInInitializerError(throwable);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Node<T> {
/*     */     public T value;
/*     */     
/*     */     private volatile Node<T> _nextDoNotCallMeDirectly;
/*     */     
/*     */     private static final long nextOffset;
/*     */     
/*     */     public Node() {
/* 115 */       this(null);
/*     */     }
/*     */     
/*     */     public Node(T param1T) {
/* 119 */       this.value = param1T;
/*     */     }
/*     */     
/*     */     public final Node<T> next() {
/* 124 */       return (Node<T>)Unsafe.instance.getObjectVolatile(this, nextOffset);
/*     */     }
/*     */     
/*     */     protected final void setNext(Node<T> param1Node) {
/* 128 */       Unsafe.instance.putOrderedObject(this, nextOffset, param1Node);
/*     */     }
/*     */     
/*     */     static {
/*     */       try {
/* 135 */         nextOffset = Unsafe.instance.objectFieldOffset(Node.class.getDeclaredField("_nextDoNotCallMeDirectly"));
/* 136 */       } catch (Throwable throwable) {
/* 137 */         throw new ExceptionInInitializerError(throwable);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\AbstractNodeQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */