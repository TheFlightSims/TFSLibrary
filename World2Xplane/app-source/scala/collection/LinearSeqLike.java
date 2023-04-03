/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function2;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001q3\001\"\001\002\021\002\007\005q\001\b\002\016\031&tW-\031:TKFd\025n[3\013\005\r!\021AC2pY2,7\r^5p]*\tQ!A\003tG\006d\027m\001\001\026\007!\031RdE\002\001\0235\001\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g!\021qq\"\005\017\016\003\tI!\001\005\002\003\017M+\027\017T5lKB\021!c\005\007\001\t\031!\002\001\"b\001+\t\t\021)\005\002\0273A\021!bF\005\0031\021\021qAT8uQ&tw\r\005\002\0135%\0211\004\002\002\004\003:L\bC\001\n\036\t\031q\002\001\"b\001?\t!!+\0329s#\t1\002\005\005\003\017\001Ea\002\"\002\022\001\t\003\031\023A\002\023j]&$H\005F\001%!\tQQ%\003\002'\t\t!QK\\5u\021\031A\003\001)C)S\005qA\017[5t\007>dG.Z2uS>tW#\001\026\021\0079Y\023#\003\002-\005\tIA*\0338fCJ\034V-\035\005\007]\001\001K\021K\030\002\031Q|7i\0347mK\016$\030n\0348\025\005)\002\004\"B\031.\001\004a\022\001\002:faJDQa\r\001\007\002%\n1a]3r\021\025)\004\001\"\0217\003!A\027m\0355D_\022,G#A\034\021\005)A\024BA\035\005\005\rIe\016\036\005\006w\001!\t\005P\001\tSR,'/\031;peV\tQ\bE\002\017}EI!a\020\002\003\021%#XM]1u_JDQ!\021\001\005F\t\0131bY8se\026\034\bo\0348egV\0211I\024\013\003\tB#\"!\022%\021\005)1\025BA$\005\005\035\021un\0347fC:DQ!\023!A\002)\013\021\001\035\t\006\025-\013R*R\005\003\031\022\021\021BR;oGRLwN\034\032\021\005IqE!B(A\005\004)\"!\001\"\t\013E\003\005\031\001*\002\tQD\027\r\036\t\004\035Mk\025B\001+\003\005\0319UM\\*fc\"\022\001I\026\t\003/jk\021\001\027\006\0033\022\t!\"\0318o_R\fG/[8o\023\tY\006LA\004uC&d'/Z2")
/*    */ public interface LinearSeqLike<A, Repr extends LinearSeqLike<A, Repr>> extends SeqLike<A, Repr> {
/*    */   LinearSeq<A> thisCollection();
/*    */   
/*    */   LinearSeq<A> toCollection(Repr paramRepr);
/*    */   
/*    */   LinearSeq<A> seq();
/*    */   
/*    */   int hashCode();
/*    */   
/*    */   Iterator<A> iterator();
/*    */   
/*    */   <B> boolean corresponds(GenSeq<B> paramGenSeq, Function2<A, B, Object> paramFunction2);
/*    */   
/*    */   public class LinearSeqLike$$anon$1 extends AbstractIterator<A> {
/*    */     private Repr these;
/*    */     
/*    */     public LinearSeqLike$$anon$1(LinearSeqLike $outer) {
/* 57 */       this.these = (Repr)$outer;
/*    */     }
/*    */     
/*    */     private Repr these() {
/* 57 */       return this.these;
/*    */     }
/*    */     
/*    */     private void these_$eq(LinearSeqLike x$1) {
/* 57 */       this.these = (Repr)x$1;
/*    */     }
/*    */     
/*    */     public boolean hasNext() {
/* 58 */       return !these().isEmpty();
/*    */     }
/*    */     
/*    */     public A next() {
/* 61 */       Object result = these().head();
/* 61 */       these_$eq((Repr)these().tail());
/* 61 */       return hasNext() ? (A)result : 
/* 62 */         (A)Iterator$.MODULE$.empty().next();
/*    */     }
/*    */     
/*    */     public List<A> toList() {
/* 68 */       List<A> xs = these().toList();
/* 69 */       these_$eq((Repr)this.$outer.newBuilder().result());
/* 70 */       return xs;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\LinearSeqLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */