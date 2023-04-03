/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.RichInt$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005baB\001\003!\003\r\ta\002\002\017\023:$W\r_3e'\026\fH*[6f\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\002\001+\rA1CG\n\004\001%i\001C\001\006\f\033\005!\021B\001\007\005\005\r\te.\037\t\005\035=\t\022$D\001\003\023\t\001\"AA\004TKFd\025n[3\021\005I\031B\002\001\003\007)\001!)\031A\013\003\003\005\013\"AF\005\021\005)9\022B\001\r\005\005\035qu\016\0365j]\036\004\"A\005\016\005\rm\001AQ1\001\026\005\021\021V\r\035:\t\013u\001A\021\001\020\002\r\021Jg.\033;%)\005y\002C\001\006!\023\t\tCA\001\003V]&$\b\"B\022\001\r\003!\023aA:fcV\tQ\005E\002\017MEI!a\n\002\003\025%sG-\032=fIN+\027\017C\003*\001\021\005#&\001\005iCND7i\0343f)\005Y\003C\001\006-\023\tiCAA\002J]RDaa\f\001!\n#\"\023A\004;iSN\034u\016\0347fGRLwN\034\005\007c\001\001K\021\013\032\002\031Q|7i\0347mK\016$\030n\0348\025\005\025\032\004\"\002\0331\001\004I\022\001\002:faJ4AA\016\001\to\tAQ\t\\3nK:$8o\005\0036qmr\004c\001\b:#%\021!H\001\002\021\003\n\034HO]1di&#XM]1u_J\0042A\004\037\022\023\ti$A\001\tCk\0324WM]3e\023R,'/\031;peB\021!bP\005\003\001\022\021AbU3sS\006d\027N_1cY\026D\001BQ\033\003\002\003\006IaK\001\006gR\f'\017\036\005\t\tV\022\t\021)A\005W\005\031QM\0343\t\013\031+D\021A$\002\rqJg.\033;?)\rA%j\023\t\003\023Vj\021\001\001\005\006\005\026\003\ra\013\005\006\t\026\003\ra\013\005\006\033V\"IAT\001\fS:LG/[1m'&TX-F\001,\021\035\001V\0071A\005\n9\013Q!\0338eKbDqAU\033A\002\023%1+A\005j]\022,\007p\030\023fcR\021q\004\026\005\b+F\013\t\0211\001,\003\rAH%\r\005\007/V\002\013\025B\026\002\r%tG-\032=!\021\025IV\007\"\003O\003%\tg/Y5mC\ndW\rC\003\\k\021\005A,A\004iCNtU\r\037;\026\003u\003\"A\0030\n\005}#!a\002\"p_2,\027M\034\005\006CV\"\tAY\001\005]\026DH\017F\001\022\021\025!W\007\"\001f\003\021AW-\0313\026\003EAQaZ\033\005B!\fA\001\032:paR\021\021\016\034\t\004\035)\f\022BA6\003\005!IE/\032:bi>\024\b\"B7g\001\004Y\023!\0018\t\013=,D\021\t9\002\tQ\f7.\032\013\003SFDQ!\0348A\002-BQa]\033\005BQ\fQa\0357jG\026$2![;x\021\0251(\0171\001,\003\0211'o\\7\t\013a\024\b\031A\026\002\013UtG/\0337)\007URX\020\005\002\013w&\021A\020\002\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\002\002G06br13/\b\005\007\002!\t%!\001\002\021%$XM]1u_J,\022!\033\005\b\003\013\001A\021IA\004\003!!xNQ;gM\026\024X\003BA\005\0033)\"!a\003\021\r\0055\0211CA\f\033\t\tyAC\002\002\022\t\tq!\\;uC\ndW-\003\003\002\026\005=!A\002\"vM\032,'\017E\002\023\0033!\001\"a\007\002\004\t\007\021Q\004\002\003\003F\n\"!E\005\021\t9\001\021#\007")
/*    */ public interface IndexedSeqLike<A, Repr> extends SeqLike<A, Repr> {
/*    */   IndexedSeq<A> seq();
/*    */   
/*    */   int hashCode();
/*    */   
/*    */   IndexedSeq<A> thisCollection();
/*    */   
/*    */   IndexedSeq<A> toCollection(Repr paramRepr);
/*    */   
/*    */   Iterator<A> iterator();
/*    */   
/*    */   <A1> Buffer<A1> toBuffer();
/*    */   
/*    */   public class Elements extends AbstractIterator<A> implements BufferedIterator<A>, Serializable {
/*    */     public static final long serialVersionUID = 1756321872811029277L;
/*    */     
/*    */     private final int start;
/*    */     
/*    */     private final int end;
/*    */     
/*    */     private int index;
/*    */     
/*    */     public BufferedIterator<A> buffered() {
/* 55 */       return BufferedIterator$class.buffered(this);
/*    */     }
/*    */     
/*    */     public Elements(IndexedSeqLike $outer, int start, int end) {
/* 55 */       BufferedIterator$class.$init$(this);
/* 57 */       this.index = start;
/*    */     }
/*    */     
/*    */     private int initialSize() {
/*    */       return (this.end <= this.start) ? 0 : (this.end - this.start);
/*    */     }
/*    */     
/*    */     private int index() {
/* 57 */       return this.index;
/*    */     }
/*    */     
/*    */     private void index_$eq(int x$1) {
/* 57 */       this.index = x$1;
/*    */     }
/*    */     
/*    */     private int available() {
/* 58 */       int i = this.end - index();
/* 58 */       Predef$ predef$ = Predef$.MODULE$;
/* 58 */       return RichInt$.MODULE$.max$extension(i, 0);
/*    */     }
/*    */     
/*    */     public boolean hasNext() {
/* 60 */       return (index() < this.end);
/*    */     }
/*    */     
/*    */     public A next() {
/* 63 */       (index() >= this.end) ? 
/* 64 */         Iterator$.MODULE$.empty().next() : BoxedUnit.UNIT;
/* 66 */       Object x = scala$collection$IndexedSeqLike$Elements$$$outer().apply(index());
/* 67 */       index_$eq(index() + 1);
/* 68 */       return (A)x;
/*    */     }
/*    */     
/*    */     public A head() {
/* 72 */       (index() >= this.end) ? 
/* 73 */         Iterator$.MODULE$.empty().next() : BoxedUnit.UNIT;
/* 75 */       return (A)scala$collection$IndexedSeqLike$Elements$$$outer().apply(index());
/*    */     }
/*    */     
/*    */     public Iterator<A> drop(int n) {
/* 79 */       return (n <= 0) ? new Elements(scala$collection$IndexedSeqLike$Elements$$$outer(), index(), this.end) : (
/* 80 */         (index() + n >= this.end) ? new Elements(scala$collection$IndexedSeqLike$Elements$$$outer(), this.end, this.end) : 
/* 81 */         new Elements(scala$collection$IndexedSeqLike$Elements$$$outer(), index() + n, this.end));
/*    */     }
/*    */     
/*    */     public Iterator<A> take(int n) {
/* 83 */       return (n <= 0) ? (Iterator)Iterator$.MODULE$.empty() : (
/* 84 */         (n <= available()) ? new Elements(scala$collection$IndexedSeqLike$Elements$$$outer(), index(), index() + n) : 
/* 85 */         new Elements(scala$collection$IndexedSeqLike$Elements$$$outer(), index(), this.end));
/*    */     }
/*    */     
/*    */     public Iterator<A> slice(int from, int until) {
/* 87 */       return take(until).drop(from);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IndexedSeqLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */