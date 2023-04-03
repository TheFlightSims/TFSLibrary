/*    */ package akka.io;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a3Q!\001\002\001\t\031\021A\003R5sK\016$()\037;f\005V4g-\032:Q_>d'BA\002\005\003\tIwNC\001\006\003\021\t7n[1\024\007\0019Q\002\005\002\t\0275\t\021BC\001\013\003\025\0318-\0317b\023\ta\021B\001\004B]f\024VM\032\t\003\035=i\021AA\005\003!\t\021!BQ;gM\026\024\bk\\8m\021!\021\002A!A!\002\023!\022!\0053fM\006,H\016\036\"vM\032,'oU5{K\016\001\001C\001\005\026\023\t1\022BA\002J]RD\001\002\007\001\003\002\003\006I\001F\001\017[\006D\bk\\8m\013:$(/[3t\021\025Q\002\001\"\001\034\003\031a\024N\\5u}Q\031A$\b\020\021\0059\001\001\"\002\n\032\001\004!\002\"\002\r\032\001\004!\002B\002\021\001A\003%\021%\001\004m_\016\\W\r\032\t\003E-j\021a\t\006\003I\025\na!\031;p[&\034'B\001\024(\003)\031wN\\2veJ,g\016\036\006\003Q%\nA!\036;jY*\t!&\001\003kCZ\f\027B\001\027$\0055\tEo\\7jG\n{w\016\\3b]\"1a\006\001Q\001\n=\nA\001]8pYB\031\001\002\r\032\n\005EJ!!B!se\006L\bCA\0327\033\005!$BA\033*\003\rq\027n\\\005\003oQ\022!BQ=uK\n+hMZ3s\021\031I\004\001)Q\005)\005i!-\0364gKJ\034\030J\034)p_2DQa\017\001\005\002q\nq!Y2rk&\024X\rF\0013\021\025q\004\001\"\001@\003\035\021X\r\\3bg\026$\"\001Q\"\021\005!\t\025B\001\"\n\005\021)f.\033;\t\013\021k\004\031\001\032\002\007\t,h\rC\003G\001\021%q)\001\005bY2|7-\031;f)\t\021\004\nC\003J\013\002\007A#\001\003tSj,\007\"B&\001\t\033a\024A\005;bW\026\024UO\0324fe\032\023x.\034)p_2D#AS'\021\0059\013V\"A(\013\005AK\021AC1o]>$\030\r^5p]&\021!k\024\002\bi\006LGN]3d\021\025!\006\001\"\004V\003EygMZ3s\005V4g-\032:U_B{w\016\034\013\003\001ZCQ\001R*A\002IB#aU'")
/*    */ public class DirectByteBufferPool implements BufferPool {
/*    */   private final int defaultBufferSize;
/*    */   
/*    */   private final int maxPoolEntries;
/*    */   
/*    */   private final AtomicBoolean locked;
/*    */   
/*    */   private final ByteBuffer[] pool;
/*    */   
/*    */   private int buffersInPool;
/*    */   
/*    */   public DirectByteBufferPool(int defaultBufferSize, int maxPoolEntries) {
/* 26 */     this.locked = new AtomicBoolean(false);
/* 27 */     this.pool = new ByteBuffer[maxPoolEntries];
/* 28 */     this.buffersInPool = 0;
/*    */   }
/*    */   
/*    */   public ByteBuffer acquire() {
/* 31 */     return takeBufferFromPool();
/*    */   }
/*    */   
/*    */   public void release(ByteBuffer buf) {
/* 34 */     offerBufferToPool(buf);
/*    */   }
/*    */   
/*    */   private ByteBuffer allocate(int size) {
/* 37 */     return ByteBuffer.allocateDirect(size);
/*    */   }
/*    */   
/*    */   private final ByteBuffer takeBufferFromPool() {
/*    */     do {
/*    */     
/* 41 */     } while (!this.locked.compareAndSet(false, true));
/*    */     try {
/* 44 */       this.buffersInPool--;
/* 46 */       null;
/* 47 */       this.locked.set(false);
/*    */       ByteBuffer buffer = (this.buffersInPool > 0) ? this.pool[this.buffersInPool] : null;
/* 50 */       return (buffer == null) ? 
/* 51 */         allocate(this.defaultBufferSize) : 
/*    */         
/* 54 */         buffer;
/*    */     } finally {
/*    */       this.locked.set(false);
/*    */     } 
/*    */   }
/*    */   
/*    */   private final void offerBufferToPool(ByteBuffer buf) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield locked : Ljava/util/concurrent/atomic/AtomicBoolean;
/*    */     //   4: iconst_0
/*    */     //   5: iconst_1
/*    */     //   6: invokevirtual compareAndSet : (ZZ)Z
/*    */     //   9: ifeq -> 62
/*    */     //   12: aload_0
/*    */     //   13: getfield buffersInPool : I
/*    */     //   16: aload_0
/*    */     //   17: getfield maxPoolEntries : I
/*    */     //   20: if_icmpge -> 49
/*    */     //   23: aload_0
/*    */     //   24: getfield pool : [Ljava/nio/ByteBuffer;
/*    */     //   27: aload_0
/*    */     //   28: getfield buffersInPool : I
/*    */     //   31: aload_1
/*    */     //   32: aastore
/*    */     //   33: aload_0
/*    */     //   34: aload_0
/*    */     //   35: getfield buffersInPool : I
/*    */     //   38: iconst_1
/*    */     //   39: iadd
/*    */     //   40: putfield buffersInPool : I
/*    */     //   43: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   46: goto -> 52
/*    */     //   49: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   52: aload_0
/*    */     //   53: getfield locked : Ljava/util/concurrent/atomic/AtomicBoolean;
/*    */     //   56: iconst_0
/*    */     //   57: invokevirtual set : (Z)V
/*    */     //   60: pop
/*    */     //   61: return
/*    */     //   62: aload_1
/*    */     //   63: astore_1
/*    */     //   64: goto -> 0
/*    */     //   67: astore_3
/*    */     //   68: aload_0
/*    */     //   69: getfield locked : Ljava/util/concurrent/atomic/AtomicBoolean;
/*    */     //   72: iconst_0
/*    */     //   73: invokevirtual set : (Z)V
/*    */     //   76: aload_3
/*    */     //   77: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #60	-> 0
/*    */     //   #61	-> 12
/*    */     //   #62	-> 23
/*    */     //   #63	-> 33
/*    */     //   #61	-> 49
/*    */     //   #65	-> 52
/*    */     //   #60	-> 60
/*    */     //   #66	-> 62
/*    */     //   #65	-> 67
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	78	0	this	Lakka/io/DirectByteBufferPool;
/*    */     //   0	78	1	buf	Ljava/nio/ByteBuffer;
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   12	52	67	finally
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\DirectByteBufferPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */