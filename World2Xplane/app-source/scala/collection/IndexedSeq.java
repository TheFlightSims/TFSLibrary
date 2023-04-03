/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0314q!\001\002\021\002\007\005qA\001\006J]\022,\0070\0323TKFT!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b\007\001)\"\001C\n\024\013\001IQ\002H\022\021\005)YQ\"\001\003\n\0051!!AB!osJ+g\rE\002\017\037Ei\021AA\005\003!\t\0211aU3r!\t\0212\003\004\001\005\rQ\001AQ1\001\026\005\005\t\025C\001\f\032!\tQq#\003\002\031\t\t9aj\034;iS:<\007C\001\006\033\023\tYBAA\002B]f\004B!\b\021\022E5\taD\003\002 \005\0059q-\0328fe&\034\027BA\021\037\005i9UM\\3sS\016$&/\031<feN\f'\r\\3UK6\004H.\031;f!\tq\001\001\005\003\017IE1\023BA\023\003\0059Ie\016Z3yK\022\034V-\035'jW\026\0042A\004\001\022\021\025A\003\001\"\001*\003\031!\023N\\5uIQ\t!\006\005\002\013W%\021A\006\002\002\005+:LG\017C\003/\001\021\005s&A\005d_6\004\030M\\5p]V\t\001\007E\002\036c\tJ!A\r\020\003!\035+g.\032:jG\016{W\016]1oS>t\007\"\002\033\001\t\003*\024aA:fcV\taeB\0038\005!\005\001(\001\006J]\022,\0070\0323TKF\004\"AD\035\007\013\005\021\001\022\001\036\024\005eZ\004cA\017=E%\021QH\b\002\013'\026\fh)Y2u_JL\b\"B :\t\003\001\025A\002\037j]&$h\bF\0019\021!\021\025\b#b\001\n\003\032\025a\003*fkN\f'\r\\3D\005\032+\022\001\022\t\004\013\0323R\"A\035\n\005\035C%aE$f]\026\024\030nY\"b]\n+\030\016\0343Ge>l\027BA%\037\005U9UM\034+sCZ,'o]1cY\0264\025m\031;pefD\001bS\035\t\002\003\006K\001R\001\r%\026,8/\0312mK\016\023e\t\t\005\006\033f\"\tAT\001\013]\026<()^5mI\026\024XCA(X+\005\001\006\003B)U-bk\021A\025\006\003'\n\tq!\\;uC\ndW-\003\002V%\n9!)^5mI\026\024\bC\001\nX\t\025!BJ1\001\026!\rq\001A\026\005\0065f\"\031aW\001\rG\006t')^5mI\032\023x.\\\013\0039\022,\022!\030\t\006;y\0037-Z\005\003?z\021AbQ1o\005VLG\016\032$s_6\004\"!R1\n\005\t\f$\001B\"pY2\004\"A\0053\005\013QI&\031A\013\021\0079\0011\r")
/*    */ public interface IndexedSeq<A> extends Seq<A>, GenericTraversableTemplate<A, IndexedSeq>, IndexedSeqLike<A, IndexedSeq<A>> {
/*    */   GenericCompanion<IndexedSeq> companion();
/*    */   
/*    */   IndexedSeq<A> seq();
/*    */   
/*    */   public static class IndexedSeq$$anon$1 extends GenTraversableFactory<IndexedSeq>.GenericCanBuildFrom<Nothing$> {
/*    */     public IndexedSeq$$anon$1() {
/* 34 */       super((GenTraversableFactory)IndexedSeq$.MODULE$);
/*    */     }
/*    */     
/*    */     public Builder<Nothing$, IndexedSeq<Nothing$>> apply() {
/* 35 */       return IndexedSeq$.MODULE$.newBuilder();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IndexedSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */