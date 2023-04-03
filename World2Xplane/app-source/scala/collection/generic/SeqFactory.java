/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Some;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y2Q!\001\002\002\002%\021!bU3r\r\006\034Go\034:z\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)\t2c\001\001\fUA\031A\"D\b\016\003\tI!A\004\002\003\033\035+gnU3r\r\006\034Go\034:z!\t\001\022\003\004\001\005\013I\001!\031A\n\003\005\r\033UC\001\013\"#\t)\022\004\005\002\027/5\ta!\003\002\031\r\t9aj\034;iS:<'c\001\016\035O\031!1\004\001\001\032\0051a$/\0324j]\026lWM\034;?!\rib\004I\007\002\t%\021q\004\002\002\004'\026\f\bC\001\t\"\t\025\021\023C1\001$\005\005A\026CA\013%!\t1R%\003\002'\r\t\031\021I\\=\021\t1A\003eD\005\003S\t\021!dR3oKJL7\r\026:bm\026\0248/\0312mKR+W\016\0357bi\026\0042\001D\026\020\023\ta#A\001\nUe\0064XM]:bE2,g)Y2u_JL\b\"\002\030\001\t\003y\023A\002\037j]&$h\bF\0011!\ra\001a\004\005\006e\001!\taM\001\013k:\f\007\017\0357z'\026\fXC\001\033;)\t)D\bE\002\027maJ!a\016\004\003\tM{W.\032\t\004!EI\004C\001\t;\t\025Y\024G1\001$\005\005\t\005\"B\0372\001\004A\024!\001=")
/*    */ public abstract class SeqFactory<CC extends Seq<Object>> extends GenSeqFactory<CC> implements TraversableFactory<CC> {
/*    */   public <A> Some<CC> unapplySeq(Seq x) {
/* 27 */     return new Some(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\SeqFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */