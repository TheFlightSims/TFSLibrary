/*    */ package scala;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.WeakHashMap;
/*    */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00154a!\001\002\002\002\t!!aD+oSF,XM\\3tg\016\0137\r[3\013\003\r\tQa]2bY\006,2!B\t\034'\t\001a\001\005\002\b\0215\t!!\003\002\n\005\t1\021I\\=SK\032DQa\003\001\005\0025\ta\001P5oSRt4\001\001\013\002\035A!q\001A\b\033!\t\001\022\003\004\001\005\013I\001!\031A\n\003\003-\013\"\001F\f\021\005\035)\022B\001\f\003\005\035qu\016\0365j]\036\004\"a\002\r\n\005e\021!aA!osB\021\001c\007\003\0069\001\021\r!\b\002\002-F\021ad\006\t\003\017}I!\001\t\002\003\t9+H\016\034\005\bE\001\021\r\021\"\003$\003\r\021x\017\\\013\002IA\021QEL\007\002M)\021q\005K\001\006Y>\0347n\035\006\003S)\n!bY8oGV\024(/\0328u\025\tYC&\001\003vi&d'\"A\027\002\t)\fg/Y\005\003_\031\022aCU3f]R\024\030M\034;SK\006$wK]5uK2{7m\033\005\007c\001\001\013\021\002\023\002\tI<H\016\t\005\bg\001\021\r\021\"\0035\003\025\021Hn\\2l+\005)\004C\001\034:\033\0059$B\001\035'\003Y\021V-\0328ue\006tGOU3bI^\023\030\016^3M_\016\\\027B\001\0368\005!\021V-\0313M_\016\\\007B\002\037\001A\003%Q'\001\004sY>\0347\016\t\005\b}\001\021\r\021\"\003@\003\0259Hn\\2l+\005\001\005C\001\034B\023\t\021uGA\005Xe&$X\rT8dW\"1A\t\001Q\001\n\001\013aa\0367pG.\004\003b\002$\001\005\004%IaR\001\004[\006\004X#\001%\021\t%Su\002T\007\002U%\0211J\013\002\f/\026\f7\016S1tQ6\013\007\017E\002N%ji\021A\024\006\003\037B\0131A]3g\025\t\tF&\001\003mC:<\027BA*O\00559V-Y6SK\032,'/\0328dK\"1Q\013\001Q\001\n!\013A!\\1qA!)q\013\001D\t1\006aa/\0317vK\032\023x.\\&fsR\021!$\027\005\0065Z\003\raD\001\002W\")A\f\001D\t;\006a1.Z=Ge>lg+\0317vKR\021a,\031\t\004\017}{\021B\0011\003\005\031y\005\017^5p]\")!m\027a\0015\005\ta\017C\003e\001\021\005Q-A\003baBd\027\020\006\002\033M\")qm\031a\001\037\005!a.Y7f\021\025I\007\001\"\001k\003\035)h.\0319qYf$\"AX6\t\0131D\007\031\001\016\002\013=$\b.\032:")
/*    */ public abstract class UniquenessCache<K, V> {
/* 48 */   private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
/*    */   
/*    */   private ReentrantReadWriteLock rwl() {
/* 48 */     return this.rwl;
/*    */   }
/*    */   
/* 49 */   private final ReentrantReadWriteLock.ReadLock rlock = rwl().readLock();
/*    */   
/*    */   private ReentrantReadWriteLock.ReadLock rlock() {
/* 49 */     return this.rlock;
/*    */   }
/*    */   
/* 50 */   private final ReentrantReadWriteLock.WriteLock wlock = rwl().writeLock();
/*    */   
/*    */   private ReentrantReadWriteLock.WriteLock wlock() {
/* 50 */     return this.wlock;
/*    */   }
/*    */   
/* 51 */   private final WeakHashMap<K, WeakReference<V>> map = new WeakHashMap<K, WeakReference<V>>();
/*    */   
/*    */   private WeakHashMap<K, WeakReference<V>> map() {
/* 51 */     return this.map;
/*    */   }
/*    */   
/*    */   private final Object cached$1(Object name$1) {
/* 58 */     rlock().lock();
/*    */     try {
/* 60 */       WeakReference reference = map().get(name$1);
/* 61 */       return (reference == null) ? null : 
/* 62 */         reference.get();
/*    */     } finally {
/* 64 */       rlock().unlock();
/*    */     } 
/*    */   }
/*    */   
/*    */   private final Object updateCache$1(Object name$1) {
/* 67 */     wlock().lock();
/*    */     try {
/* 69 */       Object res = cached$1(name$1);
/* 76 */       map().remove(name$1);
/* 77 */       Object sym = valueFromKey((K)name$1);
/* 78 */       map().put((K)name$1, new WeakReference<V>((V)sym));
/* 79 */       return (res == null) ? sym : res;
/*    */     } finally {
/* 82 */       wlock().unlock();
/*    */     } 
/*    */   }
/*    */   
/*    */   public V apply(Object name) {
/* 85 */     Object res = cached$1(name);
/* 86 */     return (res == null) ? (V)updateCache$1(name) : 
/* 87 */       (V)res;
/*    */   }
/*    */   
/*    */   public Option<K> unapply(Object other) {
/* 89 */     return keyFromValue((V)other);
/*    */   }
/*    */   
/*    */   public abstract V valueFromKey(K paramK);
/*    */   
/*    */   public abstract Option<K> keyFromValue(V paramV);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\UniquenessCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */