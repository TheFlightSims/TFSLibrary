/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0054Q!\001\002\002\002%\021!d\0217bgN$\026m\032+sCZ,'o]1cY\0264\025m\031;pefT!a\001\003\002\017\035,g.\032:jG*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"E\n\003\001-\0012\001D\007\020\033\005\021\021B\001\b\003\005a9UM\\3sS\016\034E.Y:t)\006<7i\\7qC:LwN\034\t\003!Ea\001\001B\003\023\001\t\0071C\001\002D\007V\021A#I\t\003+e\001\"AF\f\016\003\031I!\001\007\004\003\0179{G\017[5oOJ\031!\004H\024\007\tm\001\001!\007\002\ryI,g-\0338f[\026tGO\020\t\004;y\001S\"\001\003\n\005}!!a\003+sCZ,'o]1cY\026\004\"\001E\021\005\013\t\n\"\031A\022\003\003a\013\"!\006\023\021\005Y)\023B\001\024\007\005\r\te.\037\t\005\031!\002s\"\003\002*\005\t\021s)\0328fe&\0347\t\\1tgR\013w\r\026:bm\026\0248/\0312mKR+W\016\0357bi\026DQa\013\001\005\0021\na\001P5oSRtD#A\027\021\0071\001qB\002\0030\001\001\001$aE$f]\026\024\030nY\"b]\n+\030\016\0343Ge>lWCA\031?'\rq#'\016\t\003-MJ!\001\016\004\003\r\005s\027PU3g!\025aa\007O\037A\023\t9$A\001\007DC:\024U/\0337e\rJ|W\016\r\002:wA\031\001#\005\036\021\005AYD!\003\037/\003\003\005\tQ!\001$\005\ryF%\r\t\003!y\"Qa\020\030C\002\r\022\021!\021\t\004!Ei\004\002\003\"/\005\003\005\0131B\"\002\007Q\fw\rE\002E\017vj\021!\022\006\003\r\032\tqA]3gY\026\034G/\003\002I\013\nA1\t\\1tgR\013w\rC\003,]\021\005!\nF\001L)\tae\nE\002N]uj\021\001\001\005\006\005&\003\035a\021\005\006!:\"\t!U\001\006CB\004H.\037\013\003%b\003Ba\025,>\0016\tAK\003\002V\t\0059Q.\036;bE2,\027BA,U\005\035\021U/\0337eKJDQ!W(A\002i\013AA\032:p[B\0221,\030\t\004!Ea\006C\001\t^\t%q\006,!A\001\002\013\0051EA\002`IIBQ\001\025\030\005\002\001$\022A\025")
/*    */ public abstract class ClassTagTraversableFactory<CC extends Traversable<Object>> extends GenericClassTagCompanion<CC> {
/*    */   public class GenericCanBuildFrom<A> implements CanBuildFrom<CC, A, CC> {
/*    */     private final ClassTag<A> tag;
/*    */     
/*    */     public GenericCanBuildFrom(ClassTagTraversableFactory $outer, ClassTag<A> tag) {}
/*    */     
/*    */     public Builder<A, CC> apply(Traversable from) {
/* 29 */       return ((GenericClassTagTraversableTemplate)from).genericClassTagBuilder(this.tag);
/*    */     }
/*    */     
/*    */     public Builder<A, CC> apply() {
/* 30 */       return scala$collection$generic$ClassTagTraversableFactory$GenericCanBuildFrom$$$outer().newBuilder(this.tag);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\ClassTagTraversableFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */