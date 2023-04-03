/*     */ package akka.util;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Q2A!\001\002\001\017\t\0212i\0348dkJ\024XM\034;Nk2$\030.T1q\025\t\031A!\001\003vi&d'\"A\003\002\t\005\\7.Y\002\001+\rAq\002H\n\003\001%\001BAC\006\01675\t!!\003\002\r\005\t)\021J\0343fqB\021ab\004\007\001\t\025\001\002A1\001\022\005\005Y\025C\001\n\031!\t\031b#D\001\025\025\005)\022!B:dC2\f\027BA\f\025\005\035qu\016\0365j]\036\004\"aE\r\n\005i!\"aA!osB\021a\002\b\003\006;\001\021\r!\005\002\002-\"Iq\004\001B\001B\003%\001eI\001\b[\006\0048+\033>f!\t\031\022%\003\002#)\t\031\021J\034;\n\005}Y\001\"C\023\001\005\003\005\013\021\002\024.\003=1\030\r\\;f\007>l\007/\031:bi>\024\bcA\024,75\t\001F\003\002\004S)\t!&\001\003kCZ\f\027B\001\027)\005)\031u.\0349be\006$xN]\005\003K-AQa\f\001\005\002A\na\001P5oSRtDcA\0313gA!!\002A\007\034\021\025yb\0061\001!\021\025)c\0061\001'\001")
/*     */ public class ConcurrentMultiMap<K, V> extends Index<K, V> {
/*     */   public ConcurrentMultiMap(int mapSize, Comparator<V> valueComparator) {
/* 193 */     super(mapSize, valueComparator);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\ConcurrentMultiMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */