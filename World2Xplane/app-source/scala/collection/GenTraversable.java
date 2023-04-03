package scala.collection;

import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m3q!\001\002\021\002\007\005qA\001\bHK:$&/\031<feN\f'\r\\3\013\005\r!\021AC2pY2,7\r^5p]*\tQ!A\003tG\006d\027m\001\001\026\005!\0312#\002\001\n\033u\001\003C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB!abD\t\035\033\005\021\021B\001\t\003\005I9UM\034+sCZ,'o]1cY\026d\025n[3\021\005I\031B\002\001\003\007)\001!)\031A\013\003\003\005\013\"AF\r\021\005)9\022B\001\r\005\005\035qu\016\0365j]\036\004\"A\003\016\n\005m!!aA!osB\031a\002A\t\021\0079q\022#\003\002 \005\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f!\021\tC%\005\024\016\003\tR!a\t\002\002\017\035,g.\032:jG&\021QE\t\002\033\017\026tWM]5d)J\fg/\032:tC\ndW\rV3na2\fG/\032\t\003\035\001AQ\001\013\001\005\002%\na\001J5oSR$C#\001\026\021\005)Y\023B\001\027\005\005\021)f.\033;\t\0139\002a\021A\030\002\007M,\027/F\0011!\rq\021'E\005\003e\t\0211\002\026:bm\026\0248/\0312mK\")A\007\001C\001k\005I1m\\7qC:LwN\\\013\002mA\031\021e\016\024\n\005a\022#\001E$f]\026\024\030nY\"p[B\fg.[8o\017\025Q$\001#\001<\00399UM\034+sCZ,'o]1cY\026\004\"A\004\037\007\013\005\021\001\022A\037\024\005qr\004cA\021@M%\021\001I\t\002\026\017\026tGK]1wKJ\034\030M\0317f\r\006\034Go\034:z\021\025\021E\b\"\001D\003\031a\024N\\5u}Q\t1\bC\003Fy\021\ra)\001\007dC:\024U/\0337e\rJ|W.\006\002H\033V\t\001\nE\002J\0252k\021\001P\005\003\027~\0221cR3oKJL7mQ1o\005VLG\016\032$s_6\004\"AE'\005\013Q!%\031A\013\t\013=cD\021\001)\002\0259,wOQ;jY\022,'/\006\002R3V\t!\013\005\003T-bSV\"\001+\013\005U\023\021aB7vi\006\024G.Z\005\003/R\023qAQ;jY\022,'\017\005\002\0233\022)AC\024b\001+A\031a\"\r-")
public interface GenTraversable<A> extends GenTraversableLike<A, GenTraversable<A>>, GenTraversableOnce<A>, GenericTraversableTemplate<A, GenTraversable> {
  Traversable<A> seq();
  
  GenericCompanion<GenTraversable> companion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenTraversable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */