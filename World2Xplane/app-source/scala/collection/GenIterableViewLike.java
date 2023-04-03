/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005%haB\001\003!\003\r\ta\002\002\024\017\026t\027\n^3sC\ndWMV5fo2K7.\032\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\005\021MQ\003e\005\004\001\0235aR\006\r\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007c\001\b\020#5\t!!\003\002\021\005\tYq)\0328Ji\026\024\030M\0317f!\t\0212\003\004\001\005\rQ\001AQ1\001\026\005\005\t\025C\001\f\032!\tQq#\003\002\031\t\t9aj\034;iS:<\007C\001\006\033\023\tYBAA\002B]f\004BAD\017\022?%\021aD\001\002\020\017\026t\027\n^3sC\ndW\rT5lKB\021!\003\t\003\007C\001!)\031\001\022\003\tQC\027n]\t\003-\r\0222\001\n\024-\r\021)\003\001A\022\003\031q\022XMZ5oK6,g\016\036 \021\t99\023#K\005\003Q\t\021qbR3o\023R,'/\0312mKZKWm\036\t\003%)\"aa\013\001\005\006\004)\"\001B\"pY2\004RA\004\001\022S}\001BA\004\030\022S%\021qF\001\002\023\017\026tGK]1wKJ\034\030M\0317f-&,w\017E\003\017cEIs$\003\0023\005\t1r)\0328Ue\0064XM]:bE2,g+[3x\031&\\W\rC\0035\001\021\005Q'\001\004%S:LG\017\n\013\002mA\021!bN\005\003q\021\021A!\0268ji\0329!\b\001I\001\004\003Y$a\003+sC:\034hm\034:nK\022,\"\001P \024\teJQ(\021\t\005\035\035r\024\006\005\002\023\0211\001)\017CC\002U\021\021A\021\t\004\005\016sT\"\001\001\n\005i\n\004\"\002\033:\t\003)\004\"\002$:\r\0039\025\001C5uKJ\fGo\034:\026\003!\0032AD%?\023\tQ%A\001\005Ji\026\024\030\r^8s\021\025a\025\b\"\021N\003\0351wN]3bG\",\"AT+\025\005Yz\005\"\002)L\001\004\t\026!\0014\021\t)\021f\bV\005\003'\022\021\021BR;oGRLwN\\\031\021\005I)F!\002,L\005\004)\"!A+\t\013aKD\021I-\002\021Q|7\013\036:j]\036$\022A\027\t\0037\002l\021\001\030\006\003;z\013A\001\\1oO*\tq,\001\003kCZ\f\027BA1]\005\031\031FO]5oO\")1-\017C!I\0069\021n]#naRLX#A3\021\005)1\027BA4\005\005\035\021un\0347fC:4q!\033\001\021\002\007\005!NA\005F[B$\030PV5foN!\001.C6m!\r\021\025H\006\t\003\0056L!![\031\t\013QBG\021A\033\t\013\031CGQ\0019\026\003E\0042AD%\027\r\035\031\b\001%A\002\002Q\024aAR8sG\026$WCA;z'\021\021\030B\036>\021\007\t;\b0\003\002tcA\021!#\037\003\006\001J\024\r!\006\t\004\005fB\b\"\002\033s\t\003)\004\"\002$s\t\003iX#\001@\021\0079I\005PB\005\002\002\001\001\n1!\001\002\004\t11\013\\5dK\022\034ba`\005\002\006\005%\001c\001\"\002\b%\031\021\021A\031\021\007\tK\024\003C\0035\022\005Q\007\003\004G\022\005\021qB\013\003\003#\0012AD%\022\r%\t)\002\001I\001\004\003\t9B\001\004NCB\004X\rZ\013\005\0033\t\tcE\004\002\024%\tY\"a\t\021\013\t\013i\"a\b\n\007\005U\021\007E\002\023\003C!a\001QA\n\005\004)\002\003\002\":\003?Aa\001NA\n\t\003)\004b\002$\002\024\021\005\021\021F\013\003\003W\001BAD%\002 \031I\021q\006\001\021\002\007\005\021\021\007\002\013\r2\fG/T1qa\026$W\003BA\032\003w\031r!!\f\n\003k\ti\004E\003C\003o\tI$C\002\0020E\0022AEA\036\t\031\001\025Q\006b\001+A!!)OA\035\021\031!\024Q\006C\001k!9a)!\f\005\002\005\rSCAA#!\021q\021*!\017\007\023\005%\003\001%A\002\002\005-#\001C!qa\026tG-\0323\026\t\0055\023QK\n\b\003\017J\021qJA-!\025\021\025\021KA*\023\r\tI%\r\t\004%\005UCa\002!\002H\t\007\021qK\t\003#e\001BAQ\035\002T!1A'a\022\005\002UBqARA$\t\003\ty&\006\002\002bA!a\"SA*\r%\t)\007\001I\001\004\003\t9G\001\005GS2$XM]3e'\035\t\031'CA5\003\023\0012AQA6\023\r\t)'\r\005\007i\005\rD\021A\033\t\017\031\013\031\007\"\001\002\020\031I\0211\017\001\021\002\007\005\021Q\017\002\013)\006\\WM\\,iS2,7cBA9\023\005]\024\021\002\t\004\005\006e\024bAA:c!1A'!\035\005\002UBqARA9\t\003\tyAB\005\002\002\002\001\n1!\001\002\004\naAI]8qa\026$w\013[5mKN9\021qP\005\002\006\006%\001c\001\"\002\b&\031\021\021Q\031\t\rQ\ny\b\"\0016\021\0351\025q\020C\001\003\0371\021\"a$\001!\003\r\t!!%\003\riK\007\017]3e+\021\t\031*a(\024\013\0055\025\"!&\021\t\tK\024q\023\t\007\025\005e\025#!(\n\007\005mEA\001\004UkBdWM\r\t\004%\005}EA\002!\002\016\n\007Q\003\003\0045\003\033#\t!\016\005\013\003K\013iI1Q\007\022\005\035\026!B8uQ\026\024XCAAU!\021qq\"!(\t\017\031\013i\t\"\001\002.V\021\021q\026\t\005\035%\0139\nC\005\0024\0065\005\025\"\026\0026\006qa/[3x\023\022,g\016^5gS\026\024X#\001.\007\023\005e\006\001%A\002\002\005m&!\003.jaB,G-\0217m+\031\ti,!2\002LN)\021qW\005\002@B!!)OAa!\035Q\021\021TAb\003\023\0042AEAc\t!\t9-a.C\002\005]#AA!2!\r\021\0221\032\003\007\001\006]&\031A\013\t\rQ\n9\f\"\0016\021)\t)+a.CB\033E\021\021[\013\003\003'\004BAD\b\002J\"Q\021q[A\\\005\0046\t\"!7\002\021QD\027n]#mK6,\"!a1\t\025\005u\027q\027b!\016#\ty.\001\005uQ\006$X\t\\3n+\t\tI\rC\005\0024\006]\006\025\"\026\0026\"9a)a.\005\002\005\025XCAAt!\021q\021*!1")
/*    */ public interface GenIterableViewLike<A, Coll, This extends GenIterableView<A, Coll> & GenIterableViewLike<A, Coll, This>> extends GenIterable<A>, GenIterableLike<A, This>, GenTraversableView<A, Coll>, GenTraversableViewLike<A, Coll, This> {
/*    */   public abstract class Transformed$class {
/*    */     public static void $init$(GenIterableViewLike.Transformed $this) {}
/*    */     
/*    */     public static void foreach(GenIterableViewLike.Transformed $this, Function1 f) {
/* 26 */       $this.iterator().foreach(f);
/*    */     }
/*    */     
/*    */     public static String toString(GenIterableViewLike.Transformed $this) {
/* 27 */       return $this.viewToString();
/*    */     }
/*    */     
/*    */     public static boolean isEmpty(GenIterableViewLike.Transformed $this) {
/* 28 */       return !$this.iterator().hasNext();
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class EmptyView$class {
/*    */     public static void $init$(GenIterableViewLike.EmptyView $this) {}
/*    */     
/*    */     public static final Iterator iterator(GenIterableViewLike.EmptyView $this) {
/* 32 */       return Iterator$.MODULE$.empty();
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class Forced$class {
/*    */     public static void $init$(GenIterableViewLike.Forced $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.Forced $this) {
/* 36 */       return $this.forced().iterator();
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class Sliced$class {
/*    */     public static void $init$(GenIterableViewLike.Sliced $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.Sliced $this) {
/* 40 */       return $this.scala$collection$GenIterableViewLike$Sliced$$$outer().iterator().slice($this.from(), $this.until());
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class Mapped$class {
/*    */     public static void $init$(GenIterableViewLike.Mapped $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.Mapped $this) {
/* 44 */       return $this.scala$collection$GenIterableViewLike$Mapped$$$outer().iterator().map($this.mapping());
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class FlatMapped$class {
/*    */     public static void $init$(GenIterableViewLike.FlatMapped $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.FlatMapped $this) {
/* 48 */       return $this.scala$collection$GenIterableViewLike$FlatMapped$$$outer().iterator().flatMap($this.mapping());
/*    */     }
/*    */   }
/*    */   
/*    */   public interface Appended<B> extends GenTraversableViewLike<A, Coll, This>.Appended<B>, Transformed<B> {
/*    */     Iterator<B> iterator();
/*    */     
/*    */     public class GenIterableViewLike$Appended$$anonfun$iterator$1 extends AbstractFunction0<GenTraversable<B>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final GenTraversable<B> apply() {
/* 52 */         return this.$outer.rest();
/*    */       }
/*    */       
/*    */       public GenIterableViewLike$Appended$$anonfun$iterator$1(GenIterableViewLike.Appended $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class Appended$class {
/*    */     public static void $init$(GenIterableViewLike.Appended $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike<A, Coll, This>.Appended<B> $this) {
/* 52 */       return $this.scala$collection$GenIterableViewLike$Appended$$$outer().iterator().$plus$plus((Function0)new GenIterableViewLike$Appended$$anonfun$iterator$1($this));
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class Filtered$class {
/*    */     public static void $init$(GenIterableViewLike.Filtered $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.Filtered $this) {
/* 56 */       return $this.scala$collection$GenIterableViewLike$Filtered$$$outer().iterator().filter($this.pred());
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class TakenWhile$class {
/*    */     public static void $init$(GenIterableViewLike.TakenWhile $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.TakenWhile $this) {
/* 60 */       return $this.scala$collection$GenIterableViewLike$TakenWhile$$$outer().iterator().takeWhile($this.pred());
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class DroppedWhile$class {
/*    */     public static void $init$(GenIterableViewLike.DroppedWhile $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.DroppedWhile $this) {
/* 64 */       return $this.scala$collection$GenIterableViewLike$DroppedWhile$$$outer().iterator().dropWhile($this.pred());
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class Zipped$class {
/*    */     public static void $init$(GenIterableViewLike.Zipped $this) {}
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.Zipped $this) {
/* 69 */       return $this.scala$collection$GenIterableViewLike$Zipped$$$outer().iterator().zip($this.other().iterator());
/*    */     }
/*    */     
/*    */     public static final String viewIdentifier(GenIterableViewLike.Zipped $this) {
/* 70 */       return "Z";
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class ZippedAll$class {
/*    */     public static void $init$(GenIterableViewLike.ZippedAll $this) {}
/*    */     
/*    */     public static final String viewIdentifier(GenIterableViewLike.ZippedAll $this) {
/* 77 */       return "Z";
/*    */     }
/*    */     
/*    */     public static Iterator iterator(GenIterableViewLike.ZippedAll $this) {
/* 79 */       return $this.scala$collection$GenIterableViewLike$ZippedAll$$$outer().iterator().zipAll($this.other().iterator(), $this.thisElem(), $this.thatElem());
/*    */     }
/*    */   }
/*    */   
/*    */   public interface Forced<B> extends GenTraversableViewLike<A, Coll, This>.Forced<B>, Transformed<B> {
/*    */     Iterator<B> iterator();
/*    */   }
/*    */   
/*    */   public interface Sliced extends GenTraversableViewLike<A, Coll, This>.Sliced, Transformed<A> {
/*    */     Iterator<A> iterator();
/*    */   }
/*    */   
/*    */   public interface Mapped<B> extends GenTraversableViewLike<A, Coll, This>.Mapped<B>, Transformed<B> {
/*    */     Iterator<B> iterator();
/*    */   }
/*    */   
/*    */   public interface Zipped<B> extends Transformed<Tuple2<A, B>> {
/*    */     GenIterable<B> other();
/*    */     
/*    */     Iterator<Tuple2<A, B>> iterator();
/*    */     
/*    */     String viewIdentifier();
/*    */   }
/*    */   
/*    */   public interface Filtered extends GenTraversableViewLike<A, Coll, This>.Filtered, Transformed<A> {
/*    */     Iterator<A> iterator();
/*    */   }
/*    */   
/*    */   public interface EmptyView extends Transformed<Nothing$>, GenTraversableViewLike<A, Coll, This>.EmptyView {
/*    */     Iterator<Nothing$> iterator();
/*    */   }
/*    */   
/*    */   public interface ZippedAll<A1, B> extends Transformed<Tuple2<A1, B>> {
/*    */     GenIterable<B> other();
/*    */     
/*    */     A1 thisElem();
/*    */     
/*    */     B thatElem();
/*    */     
/*    */     String viewIdentifier();
/*    */     
/*    */     Iterator<Tuple2<A1, B>> iterator();
/*    */   }
/*    */   
/*    */   public interface FlatMapped<B> extends GenTraversableViewLike<A, Coll, This>.FlatMapped<B>, Transformed<B> {
/*    */     Iterator<B> iterator();
/*    */   }
/*    */   
/*    */   public interface TakenWhile extends GenTraversableViewLike<A, Coll, This>.TakenWhile, Transformed<A> {
/*    */     Iterator<A> iterator();
/*    */   }
/*    */   
/*    */   public interface Transformed<B> extends GenIterableView<B, Coll>, GenTraversableViewLike<A, Coll, This>.Transformed<B> {
/*    */     Iterator<B> iterator();
/*    */     
/*    */     <U> void foreach(Function1<B, U> param1Function1);
/*    */     
/*    */     String toString();
/*    */     
/*    */     boolean isEmpty();
/*    */   }
/*    */   
/*    */   public interface DroppedWhile extends GenTraversableViewLike<A, Coll, This>.DroppedWhile, Transformed<A> {
/*    */     Iterator<A> iterator();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenIterableViewLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */