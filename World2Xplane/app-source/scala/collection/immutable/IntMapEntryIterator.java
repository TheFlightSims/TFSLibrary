/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Tuple2;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001}2Q!\001\002\001\005!\0211#\0238u\033\006\004XI\034;ss&#XM]1u_JT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mCV\021\021\002E\n\003\001)\001Ba\003\007\01775\t!!\003\002\016\005\tq\021J\034;NCBLE/\032:bi>\024\bCA\b\021\031\001!Q!\005\001C\002M\021\021AV\002\001#\t!\002\004\005\002\026-5\ta!\003\002\030\r\t9aj\034;iS:<\007CA\013\032\023\tQbAA\002B]f\004B!\006\017\037\035%\021QD\002\002\007)V\004H.\032\032\021\005}\021cBA\006!\023\t\t#!A\006J]Rl\025\r]+uS2\034\030BA\022%\005\rIe\016^\005\003G\025R!AJ\024\002\033\tKGo\0249fe\006$\030n\0348t\025\tAC!A\004hK:,'/[2\t\021)\002!\021!Q\001\n-\n!!\033;\021\007-ac\"\003\002.\005\t1\021J\034;NCBDQa\f\001\005\002A\na\001P5oSRtDCA\0313!\rY\001A\004\005\006U9\002\ra\013\005\006i\001!\t!N\001\bm\006dW/Z(g)\tYb\007C\0038g\001\007\001(A\002uSB\0042!\017\037\017\035\tY!(\003\002<\005\0051\021J\034;NCBL!!\020 \003\007QK\007O\003\002<\005\001")
/*     */ public class IntMapEntryIterator<V> extends IntMapIterator<V, Tuple2<Object, V>> {
/*     */   public IntMapEntryIterator(IntMap<V> it) {
/* 131 */     super(it);
/*     */   }
/*     */   
/*     */   public Tuple2<Object, V> valueOf(IntMap.Tip tip) {
/* 132 */     return new Tuple2(BoxesRunTime.boxToInteger(tip.key()), tip.value());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\IntMapEntryIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */