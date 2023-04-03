package scala.xml.factory;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.util.logging.Logged;
import scala.xml.Comment;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.Node;
import scala.xml.ProcInstr;
import scala.xml.Text;

@ScalaSignature(bytes = "\006\001\005EdaB\001\003!\003\r\t!\003\002\022\031><w-\0323O_\022,g)Y2u_JL(BA\002\005\003\0351\027m\031;pefT!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001)\"AC\013\024\t\001Yqb\b\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007c\001\t\022'5\t!!\003\002\023\005\tYaj\0343f\r\006\034Go\034:z!\t!R\003\004\001\005\013Y\001!\031A\f\003\003\005\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001H\017\016\003\021I!A\b\003\003\t9{G-\032\t\003A\025j\021!\t\006\003E\r\nq\001\\8hO&twM\003\002%\r\005!Q\017^5m\023\t1\023E\001\004M_\036<W\r\032\005\006Q\001!\t!K\001\007I%t\027\016\036\023\025\003)\002\"\001D\026\n\00512!\001B+oSRDqA\f\001C\002\023\005q&A\004m_\036tu\016Z3\026\003A\002\"\001D\031\n\005I2!a\002\"p_2,\027M\034\005\007i\001\001\013\021\002\031\002\0211|wMT8eK\002BqA\016\001C\002\023\005q&A\004m_\036$V\r\037;\t\ra\002\001\025!\0031\003!awn\032+fqR\004\003b\002\036\001\005\004%\taL\001\013Y><7i\\7nK:$\bB\002\037\001A\003%\001'A\006m_\036\034u.\\7f]R\004\003b\002 \001\005\004%\taL\001\rY><\007K]8d\023:\034HO\035\005\007\001\002\001\013\021\002\031\002\0331|w\r\025:pG&s7\017\036:!\021\035\021\005A1A\005\006\r\013AAT(O\013V\tAiD\001F;\005\001\001BB$\001A\0035A)A\003O\037:+\005\005C\004J\001\t\007IQ\001&\002\013\r\0135\tS#\026\003-{\021\001T\017\002\003!1a\n\001Q\001\016-\013aaQ!D\021\026\003\003b\002)\001\005\004%)!U\001\005\rVcE*F\001S\037\005\031V$\001\002\t\rU\003\001\025!\004S\003\0251U\013\024'!\021\0359\006A1A\005\002a\013\001\003\\8h\007>l\007O]3tg2+g/\0327\026\003e\003\"\001\004.\n\005m3!aA%oi\"1Q\f\001Q\001\ne\013\021\003\\8h\007>l\007O]3tg2+g/\0327!\021\025y\006\001\"\021a\003!i\027m[3O_\022,GCB\nbU2\fh\017C\003c=\002\0071-A\002qe\026\004\"\001Z4\017\0051)\027B\0014\007\003\031\001&/\0323fM&\021\001.\033\002\007'R\024\030N\\4\013\005\0314\001\"B6_\001\004\031\027!\0027bE\026d\007\"B7_\001\004q\027aB1uiJ\034V-\035\t\0039=L!\001\035\003\003\0215+G/\031#bi\006DQA\0350A\002M\fQa]2pa\026\004\"\001\b;\n\005U$!\001\005(b[\026\034\b/Y2f\005&tG-\0338h\021\0259h\f1\001y\003!\031\007.\0337ee\026t\007\003B=\002\004mq!A_@\017\005mtX\"\001?\013\005uD\021A\002\037s_>$h(C\001\b\023\r\t\tAB\001\ba\006\0347.Y4f\023\021\t)!a\002\003\007M+\027OC\002\002\002\031Aq!a\003\001\t\003\ni!\001\005nC.,G+\032=u)\021\ty!!\006\021\007q\t\t\"C\002\002\024\021\021A\001V3yi\"9\021qCA\005\001\004\031\027!A:\t\017\005m\001\001\"\021\002\036\005YQ.Y6f\007>lW.\0328u)\021\ty\"a\n\021\013e\f\031!!\t\021\007q\t\031#C\002\002&\021\021qaQ8n[\026tG\017C\004\002\030\005e\001\031A2\t\017\005-\002\001\"\021\002.\005iQ.Y6f!J|7-\0238tiJ$b!a\f\0028\005m\002#B=\002\004\005E\002c\001\017\0024%\031\021Q\007\003\003\023A\023xnY%ogR\024\bbBA\035\003S\001\raY\001\002i\"9\021qCA\025\001\004\031\007\002DA \001\005\005\t\021\"\003\002B\005=\023AD:va\026\024H%\\1lK:{G-\032\013\f'\005\r\023QIA%\003\027\ni\005\003\004c\003{\001\ra\031\005\b\003\017\ni\0041\001d\003\021q\027-\\3\t\r5\fi\0041\001o\021\031\021\030Q\ba\001g\"1q/!\020A\002aL!aX\t\t\031\005M\003!!A\001\n\023\t)&!\027\002\035M,\b/\032:%[\006\\W\rV3yiR!\021qBA,\021\035\t9\"!\025A\002\rL1!a\003\022\0211\ti\006AA\001\002\023%\021qLA2\003E\031X\017]3sI5\f7.Z\"p[6,g\016\036\013\005\003?\t\t\007C\004\002\030\005m\003\031A2\n\007\005m\021\003\003\007\002h\001\t\t\021!C\005\003S\ny'A\ntkB,'\017J7bW\026\004&o\\2J]N$(\017\006\004\0020\005-\024Q\016\005\b\003s\t)\0071\001d\021\035\t9\"!\032A\002\rL1!a\013\022\001")
public interface LoggedNodeFactory<A extends Node> extends NodeFactory<A>, Logged {
  void scala$xml$factory$LoggedNodeFactory$_setter_$logNode_$eq(boolean paramBoolean);
  
  void scala$xml$factory$LoggedNodeFactory$_setter_$logText_$eq(boolean paramBoolean);
  
  void scala$xml$factory$LoggedNodeFactory$_setter_$logComment_$eq(boolean paramBoolean);
  
  void scala$xml$factory$LoggedNodeFactory$_setter_$logProcInstr_$eq(boolean paramBoolean);
  
  void scala$xml$factory$LoggedNodeFactory$_setter_$logCompressLevel_$eq(int paramInt);
  
  A scala$xml$factory$LoggedNodeFactory$$super$makeNode(String paramString1, String paramString2, MetaData paramMetaData, NamespaceBinding paramNamespaceBinding, Seq<Node> paramSeq);
  
  Text scala$xml$factory$LoggedNodeFactory$$super$makeText(String paramString);
  
  Seq<Comment> scala$xml$factory$LoggedNodeFactory$$super$makeComment(String paramString);
  
  Seq<ProcInstr> scala$xml$factory$LoggedNodeFactory$$super$makeProcInstr(String paramString1, String paramString2);
  
  boolean logNode();
  
  boolean logText();
  
  boolean logComment();
  
  boolean logProcInstr();
  
  int NONE();
  
  int CACHE();
  
  int FULL();
  
  int logCompressLevel();
  
  A makeNode(String paramString1, String paramString2, MetaData paramMetaData, NamespaceBinding paramNamespaceBinding, Seq<Node> paramSeq);
  
  Text makeText(String paramString);
  
  Seq<Comment> makeComment(String paramString);
  
  Seq<ProcInstr> makeProcInstr(String paramString1, String paramString2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\factory\LoggedNodeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */