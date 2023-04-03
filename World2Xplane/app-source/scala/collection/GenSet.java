package scala.collection;

import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001}3q!\001\002\021\002\007\005qA\001\004HK:\034V\r\036\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\003\021M\031R\001A\005\016;\001\002\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g!\021qq\"\005\017\016\003\tI!\001\005\002\003\025\035+gnU3u\031&\\W\r\005\002\023'1\001A!\002\013\001\005\004)\"!A!\022\005YI\002C\001\006\030\023\tABAA\004O_RD\027N\\4\021\005)Q\022BA\016\005\005\r\te.\037\t\004\035\001\t\002c\001\b\037#%\021qD\001\002\f\017\026t\027\n^3sC\ndW\r\005\003\"IE1S\"\001\022\013\005\r\022\021aB4f]\026\024\030nY\005\003K\t\022!cR3oKJL7mU3u)\026l\007\017\\1uKB\021a\002\001\005\006Q\001!\t!K\001\007I%t\027\016\036\023\025\003)\002\"AC\026\n\0051\"!\001B+oSRDQA\f\001\005B=\n\021bY8na\006t\027n\0348\026\003A\0022!I\031'\023\t\021$E\001\tHK:,'/[2D_6\004\030M\\5p]\")A\007\001D\001k\005\0311/Z9\026\003Y\0022AD\034\022\023\tA$AA\002TKR<QA\017\002\t\002m\naaR3o'\026$\bC\001\b=\r\025\t!\001#\001>'\tad\bE\002\"\031J!\001\021\022\003+\035+g\016\026:bm\026\0248/\0312mK\032\0137\r^8ss\")!\t\020C\001\007\0061A(\0338jiz\"\022a\017\005\006\013r\"\031AR\001\rG\006t')^5mI\032\023x.\\\013\003\0176+\022\001\023\t\004\023*cU\"\001\037\n\005-{$aE$f]\026\024\030nY\"b]\n+\030\016\0343Ge>l\007C\001\nN\t\025!BI1\001\026\021\025yE\b\"\001Q\003)qWm\036\"vS2$WM]\013\003#f+\022A\025\t\005'ZC&,D\001U\025\t)&!A\004nkR\f'\r\\3\n\005]#&a\002\"vS2$WM\035\t\003%e#Q\001\006(C\002U\0012a\0270Y\033\005a&BA/\003\003%IW.\\;uC\ndW-\003\00299\002")
public interface GenSet<A> extends GenSetLike<A, GenSet<A>>, GenIterable<A>, GenericSetTemplate<A, GenSet> {
  GenericCompanion<GenSet> companion();
  
  Set<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */