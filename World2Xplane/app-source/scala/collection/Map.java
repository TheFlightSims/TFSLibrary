/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055baB\001\003!\003\r\ta\002\002\004\033\006\004(BA\002\005\003)\031w\016\0347fGRLwN\034\006\002\013\005)1oY1mC\016\001Qc\001\005\027AM)\001!C\007#KA\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\0079y\021#D\001\003\023\t\001\"A\001\005Ji\026\024\030M\0317f!\021Q!\003F\020\n\005M!!A\002+va2,'\007\005\002\026-1\001A!B\f\001\005\004A\"!A!\022\005ea\002C\001\006\033\023\tYBAA\004O_RD\027N\\4\021\005)i\022B\001\020\005\005\r\te.\037\t\003+\001\"a!\t\001\005\006\004A\"!\001\"\021\t9\031CcH\005\003I\t\021aaR3o\033\006\004\b#\002\b')}A\023BA\024\003\005\035i\025\r\035'jW\026\004BA\004\001\025?!)!\006\001C\001W\0051A%\0338ji\022\"\022\001\f\t\003\0255J!A\f\003\003\tUs\027\016\036\005\006a\001!\t!M\001\006K6\004H/_\013\002Q!)1\007\001C!c\005\0311/Z9\b\013U\022\001\022\001\034\002\0075\013\007\017\005\002\017o\031)\021A\001E\001qM\021q'\017\t\004uuzT\"A\036\013\005q\022\021aB4f]\026\024\030nY\005\003}m\022!\"T1q\r\006\034Go\034:z!\tq\001\001C\003Bo\021\005!)\001\004=S:LGO\020\013\002m!)\001g\016C\001\tV\031Q\t\024(\026\003\031\003Ba\022&L\0336\t\001J\003\002J\005\005I\021.\\7vi\006\024G.Z\005\003\003!\003\"!\006'\005\013]\031%\031\001\r\021\005UqE!B\021D\005\004A\002\"\002)8\t\007\t\026\001D2b]\n+\030\016\0343Ge>lWc\001*_AV\t1\013E\003;)Zc\026-\003\002Vw\ta1)\0318Ck&dGM\022:p[B\021q\013W\007\002o%\021\021L\027\002\005\007>dG.\003\002\\w\tiq)\0328NCB4\025m\031;pef\004BA\003\n^?B\021QC\030\003\006/=\023\r\001\007\t\003+\001$Q!I(C\002a\001BA\004\001^?\032)1mNA\001I\nYq+\033;i\t\0264\027-\0367u+\r)'\016\\\n\005E\032lg\016\005\003\017O&\\\027B\0015\003\005-\t%m\035;sC\016$X*\0319\021\005UQG!B\fc\005\004A\002CA\013m\t\031\t#\r\"b\0011A!a\002A5l!\tQq.\003\002q\t\ta1+\032:jC2L'0\0312mK\"A!O\031B\001B\003%Q.\001\006v]\022,'\017\\=j]\036D\001\002\0362\003\002\003\006I!^\001\002IB!!B^5l\023\t9HAA\005Gk:\034G/[8oc!)\021I\031C\001sR\031!p\037?\021\t]\023\027n\033\005\006eb\004\r!\034\005\006ib\004\r!\036\005\006}\n$\te`\001\005g&TX-\006\002\002\002A\031!\"a\001\n\007\005\025AAA\002J]RDq!!\003c\t\003\tY!A\002hKR$B!!\004\002\024A!!\"a\004l\023\r\t\t\002\002\002\007\037B$\030n\0348\t\017\005U\021q\001a\001S\006\0311.Z=\t\017\005e!\r\"\001\002\034\005A\021\016^3sCR|'/\006\002\002\036A)a\"a\b\002$%\031\021\021\005\002\003\021%#XM]1u_J\004BA\003\njW\"9\021q\0052\005B\005%\022a\0023fM\006,H\016\036\013\004W\006-\002bBA\013\003K\001\r!\033")
/*    */ public interface Map<A, B> extends Iterable<Tuple2<A, B>>, GenMap<A, B>, MapLike<A, B, Map<A, B>> {
/*    */   Map<A, B> empty();
/*    */   
/*    */   Map<A, B> seq();
/*    */   
/*    */   public static abstract class WithDefault<A, B> extends AbstractMap<A, B> implements Map<A, B>, Serializable {
/*    */     private final Map<A, B> underlying;
/*    */     
/*    */     private final Function1<A, B> d;
/*    */     
/*    */     public WithDefault(Map<A, B> underlying, Function1<A, B> d) {}
/*    */     
/*    */     public int size() {
/* 49 */       return this.underlying.size();
/*    */     }
/*    */     
/*    */     public Option<B> get(Object key) {
/* 50 */       return this.underlying.get((A)key);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, B>> iterator() {
/* 51 */       return this.underlying.iterator();
/*    */     }
/*    */     
/*    */     public B default(Object key) {
/* 52 */       return (B)this.d.apply(key);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Map.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */