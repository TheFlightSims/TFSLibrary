package scala.collection.immutable;

import scala.Immutable;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0214q!\001\002\021\002\007\005\021BA\006Ue\0064XM]:bE2,'BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013)M1\001aC\b\036K%\002\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022CE\007\002\t%\021\021\001\002\t\003'Qa\001\001\002\004\026\001\021\025\rA\006\002\002\003F\021qC\007\t\003\031aI!!\007\004\003\0179{G\017[5oOB\021AbG\005\0039\031\0211!\0218z!\021q\022EE\022\016\003}Q!\001\t\003\002\017\035,g.\032:jG&\021!e\b\002\033\017\026tWM]5d)J\fg/\032:tC\ndW\rV3na2\fG/\032\t\003I\001i\021A\001\t\005!\031\022\002&\003\002(\t\tyAK]1wKJ\034\030M\0317f\031&\\W\rE\002%\001I\001\"\001\004\026\n\005-2!!C%n[V$\030M\0317f\021\025i\003\001\"\001/\003\031!\023N\\5uIQ\tq\006\005\002\ra%\021\021G\002\002\005+:LG\017C\0034\001\021\005C'A\005d_6\004\030M\\5p]V\tQ\007E\002\037m\rJ!aN\020\003!\035+g.\032:jG\016{W\016]1oS>t\007\"B\035\001\t\003R\024aA:fcV\t\001fB\003=\005!\005Q(A\006Ue\0064XM]:bE2,\007C\001\023?\r\025\t!\001#\001@'\rq\004i\021\t\004=\005\033\023B\001\" \005U9UM\034+sCZ,'o]1cY\0264\025m\031;pef\0042A\b#$\023\t)uD\001\nUe\0064XM]:bE2,g)Y2u_JL\b\"B$?\t\003A\025A\002\037j]&$h\bF\001>\021\025Qe\bb\001L\0031\031\027M\034\"vS2$gI]8n+\taU+F\001N!\025qb\n\025+W\023\tyuD\001\007DC:\024U/\0337e\rJ|W\016\005\002R%6\ta(\003\002Tm\t!1i\0347m!\t\031R\013B\003\026\023\n\007a\003E\002%\001QCQ\001\027 \005\002e\013!B\\3x\005VLG\016Z3s+\tQ&-F\001\\!\021av,Y2\016\003uS!A\030\003\002\0175,H/\0312mK&\021\001-\030\002\b\005VLG\016Z3s!\t\031\"\rB\003\026/\n\007a\003E\002%\001\005\004")
public interface Traversable<A> extends Traversable<A>, GenericTraversableTemplate<A, Traversable>, TraversableLike<A, Traversable<A>>, Immutable {
  GenericCompanion<Traversable> companion();
  
  Traversable<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Traversable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */