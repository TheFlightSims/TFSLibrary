/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001E2Q!\001\002\001\005!\0211#\0238u\033\006\004h+\0317vK&#XM]1u_JT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mCV\021\021\002E\n\003\001)\001Ba\003\007\017\0355\t!!\003\002\016\005\tq\021J\034;NCBLE/\032:bi>\024\bCA\b\021\031\001!Q!\005\001C\002M\021\021AV\002\001#\t!\002\004\005\002\026-5\ta!\003\002\030\r\t9aj\034;iS:<\007CA\013\032\023\tQbAA\002B]fD\001\002\b\001\003\002\003\006I!H\001\003SR\0042a\003\020\017\023\ty\"A\001\004J]Rl\025\r\035\005\006C\001!\tAI\001\007y%t\027\016\036 \025\005\r\"\003cA\006\001\035!)A\004\ta\001;!)a\005\001C\001O\0059a/\0317vK>3GC\001\b)\021\025IS\0051\001+\003\r!\030\016\035\t\004W9raBA\006-\023\ti#!\001\004J]Rl\025\r]\005\003_A\0221\001V5q\025\ti#\001")
/*     */ public class IntMapValueIterator<V> extends IntMapIterator<V, V> {
/*     */   public IntMapValueIterator(IntMap<V> it) {
/* 135 */     super(it);
/*     */   }
/*     */   
/*     */   public V valueOf(IntMap.Tip<V> tip) {
/* 136 */     return tip.value();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\IntMapValueIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */