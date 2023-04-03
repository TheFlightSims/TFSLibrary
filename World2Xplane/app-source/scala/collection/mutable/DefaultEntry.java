/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001)3A!\001\002\003\023\taA)\0324bk2$XI\034;ss*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\rQQ\003I\n\005\001-y!\005\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032\004B\001E\t\024=5\t!!\003\002\023\005\tI\001*Y:i\013:$(/\037\t\003)Ua\001\001B\003\027\001\t\007qCA\001B#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\tA\0011c\b\t\003)\001\"Q!\t\001C\002]\021\021A\021\t\003\031\rJ!\001\n\004\003\031M+'/[1mSj\f'\r\\3\t\021\031\002!Q1A\005\002\035\n1a[3z+\005\031\002\002C\025\001\005\003\005\013\021B\n\002\t-,\027\020\t\005\tW\001\021\t\031!C\001Y\005)a/\0317vKV\tq\004\003\005/\001\t\005\r\021\"\0010\003%1\030\r\\;f?\022*\027\017\006\0021gA\021A\"M\005\003e\031\021A!\0268ji\"9A'LA\001\002\004y\022a\001=%c!Aa\007\001B\001B\003&q$\001\004wC2,X\r\t\005\006q\001!\t!O\001\007y%t\027\016\036 \025\007yQ4\bC\003'o\001\0071\003C\003,o\001\007q\004C\003>\001\021\005c(\001\005u_N#(/\0338h)\005y\004C\001!F\033\005\t%B\001\"D\003\021a\027M\\4\013\003\021\013AA[1wC&\021a)\021\002\007'R\024\030N\\4\t\013!\003A\021A%\002\027\rD\027-\0338TiJLgnZ\013\002\001")
/*    */ public final class DefaultEntry<A, B> implements HashEntry<A, DefaultEntry<A, B>>, Serializable {
/*    */   private final A key;
/*    */   
/*    */   private B value;
/*    */   
/*    */   private Object next;
/*    */   
/*    */   public DefaultEntry<A, B> next() {
/* 15 */     return (DefaultEntry<A, B>)this.next;
/*    */   }
/*    */   
/*    */   public void next_$eq(Object x$1) {
/* 15 */     this.next = x$1;
/*    */   }
/*    */   
/*    */   public A key() {
/* 15 */     return this.key;
/*    */   }
/*    */   
/*    */   public B value() {
/* 15 */     return this.value;
/*    */   }
/*    */   
/*    */   public void value_$eq(Object x$1) {
/* 15 */     this.value = (B)x$1;
/*    */   }
/*    */   
/*    */   public DefaultEntry(Object key, Object value) {
/* 15 */     HashEntry$class.$init$(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return chainString();
/*    */   }
/*    */   
/*    */   public String chainString() {
/* 21 */     return (new StringBuilder()).append("(kv: ").append(key()).append(", ").append(value()).append(")").append((next() == null) ? "" : (new StringBuilder()).append(" -> ").append(next().toString()).toString()).toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\DefaultEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */