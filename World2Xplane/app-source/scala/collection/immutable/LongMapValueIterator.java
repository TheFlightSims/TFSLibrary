/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001E2Q!\001\002\001\005!\021A\003T8oO6\013\007OV1mk\026LE/\032:bi>\024(BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006,\"!\003\t\024\005\001Q\001\003B\006\r\0359i\021AA\005\003\033\t\021q\002T8oO6\013\007/\023;fe\006$xN\035\t\003\037Aa\001\001B\003\022\001\t\0071CA\001W\007\001\t\"\001\006\r\021\005U1R\"\001\004\n\005]1!a\002(pi\"Lgn\032\t\003+eI!A\007\004\003\007\005s\027\020\003\005\035\001\t\005\t\025!\003\036\003\tIG\017E\002\f=9I!a\b\002\003\0171{gnZ'ba\")\021\005\001C\001E\0051A(\0338jiz\"\"a\t\023\021\007-\001a\002C\003\035A\001\007Q\004C\003'\001\021\005q%A\004wC2,Xm\0244\025\0059A\003\"B\025&\001\004Q\023a\001;jaB\0311F\f\b\017\005-a\023BA\027\003\003\035auN\\4NCBL!a\f\031\003\007QK\007O\003\002.\005\001")
/*     */ public class LongMapValueIterator<V> extends LongMapIterator<V, V> {
/*     */   public LongMapValueIterator(LongMap<V> it) {
/* 131 */     super(it);
/*     */   }
/*     */   
/*     */   public V valueOf(LongMap.Tip<V> tip) {
/* 132 */     return tip.value();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LongMapValueIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */