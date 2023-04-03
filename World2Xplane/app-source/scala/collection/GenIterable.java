package scala.collection;

import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m3q!\001\002\021\002\007\005qAA\006HK:LE/\032:bE2,'BA\002\005\003)\031w\016\0347fGRLwN\034\006\002\013\005)1oY1mC\016\001QC\001\005\024'\025\001\021\"D\017!!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\t\005\035=\tB$D\001\003\023\t\001\"AA\bHK:LE/\032:bE2,G*[6f!\t\0212\003\004\001\005\rQ\001AQ1\001\026\005\005\t\025C\001\f\032!\tQq#\003\002\031\t\t9aj\034;iS:<\007C\001\006\033\023\tYBAA\002B]f\0042A\004\001\022!\rqa$E\005\003?\t\021abR3o)J\fg/\032:tC\ndW\r\005\003\"IE1S\"\001\022\013\005\r\022\021aB4f]\026\024\030nY\005\003K\t\022!dR3oKJL7\r\026:bm\026\0248/\0312mKR+W\016\0357bi\026\004\"A\004\001\t\013!\002A\021A\025\002\r\021Jg.\033;%)\005Q\003C\001\006,\023\taCA\001\003V]&$\b\"\002\030\001\r\003y\023aA:fcV\t\001\007E\002\017cEI!A\r\002\003\021%#XM]1cY\026DQ\001\016\001\005BU\n\021bY8na\006t\027n\0348\026\003Y\0022!I\034'\023\tA$E\001\tHK:,'/[2D_6\004\030M\\5p]\036)!H\001E\001w\005Yq)\0328Ji\026\024\030M\0317f!\tqAHB\003\002\005!\005Qh\005\002=}A\031\021e\020\024\n\005\001\023#!F$f]R\023\030M^3sg\006\024G.\032$bGR|'/\037\005\006\005r\"\taQ\001\007y%t\027\016\036 \025\003mBQ!\022\037\005\004\031\013AbY1o\005VLG\016\032$s_6,\"aR'\026\003!\0032!\023&M\033\005a\024BA&@\005M9UM\\3sS\016\034\025M\034\"vS2$gI]8n!\t\021R\nB\003\025\t\n\007Q\003C\003Py\021\005\001+\001\006oK^\024U/\0337eKJ,\"!U-\026\003I\003Ba\025,Y56\tAK\003\002V\005\0059Q.\036;bE2,\027BA,U\005\035\021U/\0337eKJ\004\"AE-\005\013Qq%\031A\013\021\0079\t\004\f")
public interface GenIterable<A> extends GenIterableLike<A, GenIterable<A>>, GenTraversable<A>, GenericTraversableTemplate<A, GenIterable> {
  Iterable<A> seq();
  
  GenericCompanion<GenIterable> companion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */