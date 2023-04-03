package scala.collection;

import scala.Equals;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001y3q!\001\002\021\002\007\005qA\001\004HK:\034V-\035\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\003\021M\031b\001A\005\016;\001\032\003C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB!abD\t\035\033\005\021\021B\001\t\003\005)9UM\\*fc2K7.\032\t\003%Ma\001\001\002\004\025\001\021\025\r!\006\002\002\003F\021a#\007\t\003\025]I!\001\007\003\003\0179{G\017[5oOB\021!BG\005\0037\021\0211!\0218z!\rq\001!\005\t\004\035y\t\022BA\020\003\005-9UM\\%uKJ\f'\r\\3\021\005)\t\023B\001\022\005\005\031)\025/^1mgB!AeJ\t*\033\005)#B\001\024\003\003\0359WM\\3sS\016L!\001K\023\0035\035+g.\032:jGR\023\030M^3sg\006\024G.\032+f[Bd\027\r^3\021\0059\001\001\"B\026\001\t\003a\023A\002\023j]&$H\005F\001.!\tQa&\003\0020\t\t!QK\\5u\021\025\t\004A\"\0013\003\r\031X-]\013\002gA\031a\002N\t\n\005U\022!aA*fc\")q\007\001C!q\005I1m\\7qC:LwN\\\013\002sA\031AEO\025\n\005m*#\001E$f]\026\024\030nY\"p[B\fg.[8o\017\025i$\001#\001?\003\0319UM\\*fcB\021ab\020\004\006\003\tA\t\001Q\n\003\005\0032\001\n\"*\023\t\031UEA\013HK:$&/\031<feN\f'\r\\3GC\016$xN]=\t\013\025{D\021\001$\002\rqJg.\033;?)\005q\004\"\002%@\t\007I\025\001D2b]\n+\030\016\0343Ge>lWC\001&Q+\005Y\005c\001'N\0376\tq(\003\002O\005\n\031r)\0328fe&\0347)\0318Ck&dGM\022:p[B\021!\003\025\003\006)\035\023\r!\006\005\006%~\"\taU\001\013]\026<()^5mI\026\024XC\001+]+\005)\006\003\002,Z7vk\021a\026\006\0031\n\tq!\\;uC\ndW-\003\002[/\n9!)^5mI\026\024\bC\001\n]\t\025!\022K1\001\026!\rqAg\027")
public interface GenSeq<A> extends GenSeqLike<A, GenSeq<A>>, GenIterable<A>, Equals, GenericTraversableTemplate<A, GenSeq> {
  Seq<A> seq();
  
  GenericCompanion<GenSeq> companion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */