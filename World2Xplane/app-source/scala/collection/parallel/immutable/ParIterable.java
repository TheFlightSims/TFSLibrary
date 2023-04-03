/*    */ package scala.collection.parallel.immutable;
/*    */ 
/*    */ import scala.Immutable;
/*    */ import scala.Serializable;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericParTemplate;
/*    */ import scala.collection.immutable.Iterable;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParIterable;
/*    */ import scala.collection.parallel.ParIterableLike;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u4q!\001\002\021\002\007\0051BA\006QCJLE/\032:bE2,'BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005A\001/\031:bY2,GN\003\002\b\021\005Q1m\0347mK\016$\030n\0348\013\003%\tQa]2bY\006\034\001!\006\002\r/M9\001!D\t!G-\"\004C\001\b\020\033\005A\021B\001\t\t\005\031\te.\037*fMB\031!cE\013\016\003\031I!\001\006\004\003\027\035+g.\023;fe\006\024G.\032\t\003-]a\001\001\002\004\031\001\021\025\r!\007\002\002)F\021!$\b\t\003\035mI!\001\b\005\003\0179{G\017[5oOB\021aBH\005\003?!\0211!\0218z!\r\t#%F\007\002\t%\021\021\001\002\t\005I\035*\022&D\001&\025\t1c!A\004hK:,'/[2\n\005!*#AE$f]\026\024\030n\031)beR+W\016\0357bi\026\004\"A\013\001\016\003\t\001R!\t\027\026]=J!!\f\003\003\037A\013'/\023;fe\006\024G.\032'jW\026\0042A\013\001\026!\r\001$'F\007\002c)\0211AB\005\003gE\022\001\"\023;fe\006\024G.\032\t\003\035UJ!A\016\005\003\023%kW.\036;bE2,\007\"\002\035\001\t\003I\024A\002\023j]&$H\005F\001;!\tq1(\003\002=\021\t!QK\\5u\021\025q\004\001\"\021@\003%\031w.\0349b]&|g.F\001A%\r\t5I\022\004\005\005\002\001\001I\001\007=e\0264\027N\\3nK:$h\bE\002%\t&J!!R\023\003!\035+g.\032:jG\016{W\016]1oS>t\007c\001\023HS%\021\001*\n\002\024\017\026tWM]5d!\006\0248i\\7qC:LwN\034\005\006\025\002!\teS\001\013i>LE/\032:bE2,W#\001\030\t\0135\003A\021\t(\002\013Q|7+Z9\026\003=\0032A\013)\026\023\t\t&A\001\004QCJ\034V-]\004\006'\nA\t\001V\001\f!\006\024\030\n^3sC\ndW\r\005\002++\032)\021A\001E\001-N\021Qk\026\t\004IaK\023BA-&\005)\001\026M\035$bGR|'/\037\005\0067V#\t\001X\001\007y%t\027\016\036 \025\003QCQAX+\005\004}\013AbY1o\005VLG\016\032$s_6,\"\001Y5\026\003\005\004R\001\n2eQ*L!aY\023\003\035\r\013gnQ8nE&tWM\022:p[B\021QMZ\007\002+&\021q\r\022\002\005\007>dG\016\005\002\027S\022)\001$\030b\0013A\031!\006\0015\t\0131,F\021A7\002\0259,wOQ;jY\022,'/\006\002ogV\tq\016\005\003\"aJ$\030BA9\005\005!\031u.\0342j]\026\024\bC\001\ft\t\025A2N1\001\032!\rQ\003A\035\005\006mV#\ta^\001\f]\026<8i\\7cS:,'/\006\002ywV\t\021\020\005\003\"ajd\bC\001\f|\t\025ARO1\001\032!\rQ\003A\037")
/*    */ public interface ParIterable<T> extends GenIterable<T>, ParIterable<T>, GenericParTemplate<T, ParIterable>, ParIterableLike<T, ParIterable<T>, Iterable<T>>, Immutable {
/*    */   GenericCompanion<ParIterable> companion();
/*    */   
/*    */   ParIterable<T> toIterable();
/*    */   
/*    */   ParSeq<T> toSeq();
/*    */   
/*    */   public class ParIterable$$anonfun$toSeq$1 extends AbstractFunction0<Combiner<T, ParSeq<T>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Combiner<T, ParSeq<T>> apply() {
/* 44 */       return ParSeq$.MODULE$.newCombiner();
/*    */     }
/*    */     
/*    */     public ParIterable$$anonfun$toSeq$1(ParIterable $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */