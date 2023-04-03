/*      */ package org.jdom;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ 
/*      */ public final class Verifier {
/*      */   private static final String CVS_ID = "@(#) $RCSfile: Verifier.java,v $ $Revision: 1.57 $ $Date: 2009/07/23 05:54:23 $ $Name:  $";
/*      */   
/*      */   public static String checkElementName(String name) {
/*      */     String reason;
/*   92 */     if ((reason = checkXMLName(name)) != null)
/*   93 */       return reason; 
/*   97 */     if (name.indexOf(":") != -1)
/*   98 */       return "Element names cannot contain colons"; 
/*  102 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkAttributeName(String name) {
/*      */     String reason;
/*  116 */     if ((reason = checkXMLName(name)) != null)
/*  117 */       return reason; 
/*  121 */     if (name.indexOf(":") != -1)
/*  122 */       return "Attribute names cannot contain colons"; 
/*  126 */     if (name.equals("xmlns"))
/*  127 */       return "An Attribute name may not be \"xmlns\"; use the Namespace class to manage namespaces"; 
/*  132 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkCharacterData(String text) {
/*  154 */     if (text == null)
/*  155 */       return "A null is not a legal XML value"; 
/*  159 */     for (int i = 0, len = text.length(); i < len; i++) {
/*  161 */       int ch = text.charAt(i);
/*  164 */       if (isHighSurrogate((char)ch)) {
/*  166 */         i++;
/*  167 */         if (i < len) {
/*  168 */           char low = text.charAt(i);
/*  169 */           if (!isLowSurrogate(low))
/*  170 */             return "Illegal Surrogate Pair"; 
/*  174 */           ch = decodeSurrogatePair((char)ch, low);
/*      */         } else {
/*  177 */           return "Surrogate Pair Truncated";
/*      */         } 
/*      */       } 
/*  181 */       if (!isXMLCharacter(ch))
/*  185 */         return "0x" + Integer.toHexString(ch) + " is not a legal XML character"; 
/*      */     } 
/*  191 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkCDATASection(String data) {
/*  203 */     String reason = null;
/*  204 */     if ((reason = checkCharacterData(data)) != null)
/*  205 */       return reason; 
/*  208 */     if (data.indexOf("]]>") != -1)
/*  209 */       return "CDATA cannot internally contain a CDATA ending delimiter (]]>)"; 
/*  214 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkNamespacePrefix(String prefix) {
/*  227 */     if (prefix == null || prefix.equals(""))
/*  228 */       return null; 
/*  232 */     char first = prefix.charAt(0);
/*  233 */     if (isXMLDigit(first))
/*  234 */       return "Namespace prefixes cannot begin with a number"; 
/*  237 */     if (first == '$')
/*  238 */       return "Namespace prefixes cannot begin with a dollar sign ($)"; 
/*  241 */     if (first == '-')
/*  242 */       return "Namespace prefixes cannot begin with a hyphen (-)"; 
/*  245 */     if (first == '.')
/*  246 */       return "Namespace prefixes cannot begin with a period (.)"; 
/*  249 */     if (prefix.toLowerCase().startsWith("xml"))
/*  250 */       return "Namespace prefixes cannot begin with \"xml\" in any combination of case"; 
/*  255 */     for (int i = 0, len = prefix.length(); i < len; i++) {
/*  256 */       char c = prefix.charAt(i);
/*  257 */       if (!isXMLNameCharacter(c))
/*  258 */         return "Namespace prefixes cannot contain the character \"" + c + "\""; 
/*      */     } 
/*  264 */     if (prefix.indexOf(":") != -1)
/*  265 */       return "Namespace prefixes cannot contain colons"; 
/*  269 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkNamespaceURI(String uri) {
/*  282 */     if (uri == null || uri.equals(""))
/*  283 */       return null; 
/*  287 */     char first = uri.charAt(0);
/*  288 */     if (Character.isDigit(first))
/*  289 */       return "Namespace URIs cannot begin with a number"; 
/*  292 */     if (first == '$')
/*  293 */       return "Namespace URIs cannot begin with a dollar sign ($)"; 
/*  296 */     if (first == '-')
/*  297 */       return "Namespace URIs cannot begin with a hyphen (-)"; 
/*  301 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkNamespaceCollision(Namespace namespace, Namespace other) {
/*  316 */     String reason = null;
/*  317 */     String p1 = namespace.getPrefix();
/*  318 */     String u1 = namespace.getURI();
/*  319 */     String p2 = other.getPrefix();
/*  320 */     String u2 = other.getURI();
/*  321 */     if (p1.equals(p2) && !u1.equals(u2))
/*  322 */       reason = "The namespace prefix \"" + p1 + "\" collides"; 
/*  324 */     return reason;
/*      */   }
/*      */   
/*      */   public static String checkNamespaceCollision(Attribute attribute, Element element) {
/*  338 */     Namespace namespace = attribute.getNamespace();
/*  339 */     String prefix = namespace.getPrefix();
/*  340 */     if ("".equals(prefix))
/*  341 */       return null; 
/*  344 */     return checkNamespaceCollision(namespace, element);
/*      */   }
/*      */   
/*      */   public static String checkNamespaceCollision(Namespace namespace, Element element) {
/*  358 */     String reason = checkNamespaceCollision(namespace, element.getNamespace());
/*  360 */     if (reason != null)
/*  361 */       return reason + " with the element namespace prefix"; 
/*  364 */     reason = checkNamespaceCollision(namespace, element.getAdditionalNamespaces());
/*  366 */     if (reason != null)
/*  367 */       return reason; 
/*  370 */     reason = checkNamespaceCollision(namespace, element.getAttributes());
/*  371 */     if (reason != null)
/*  372 */       return reason; 
/*  375 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkNamespaceCollision(Namespace namespace, Attribute attribute) {
/*  389 */     String reason = null;
/*  390 */     if (!attribute.getNamespace().equals(Namespace.NO_NAMESPACE)) {
/*  391 */       reason = checkNamespaceCollision(namespace, attribute.getNamespace());
/*  393 */       if (reason != null)
/*  394 */         reason = reason + " with an attribute namespace prefix on the element"; 
/*      */     } 
/*  397 */     return reason;
/*      */   }
/*      */   
/*      */   public static String checkNamespaceCollision(Namespace namespace, List list) {
/*  411 */     if (list == null)
/*  412 */       return null; 
/*  415 */     String reason = null;
/*  416 */     Iterator i = list.iterator();
/*  417 */     while (reason == null && i.hasNext()) {
/*  418 */       Object obj = i.next();
/*  419 */       if (obj instanceof Attribute) {
/*  420 */         reason = checkNamespaceCollision(namespace, (Attribute)obj);
/*      */         continue;
/*      */       } 
/*  422 */       if (obj instanceof Element) {
/*  423 */         reason = checkNamespaceCollision(namespace, (Element)obj);
/*      */         continue;
/*      */       } 
/*  425 */       if (obj instanceof Namespace) {
/*  426 */         reason = checkNamespaceCollision(namespace, (Namespace)obj);
/*  427 */         if (reason != null)
/*  428 */           reason = reason + " with an additional namespace declared by the element"; 
/*      */       } 
/*      */     } 
/*  433 */     return reason;
/*      */   }
/*      */   
/*      */   public static String checkProcessingInstructionTarget(String target) {
/*      */     String reason;
/*  447 */     if ((reason = checkXMLName(target)) != null)
/*  448 */       return reason; 
/*  452 */     if (target.indexOf(":") != -1)
/*  453 */       return "Processing instruction targets cannot contain colons"; 
/*  457 */     if (target.equalsIgnoreCase("xml"))
/*  458 */       return "Processing instructions cannot have a target of \"xml\" in any combination of case. (Note that the \"<?xml ... ?>\" declaration at the beginning of a document is not a processing instruction and should not be added as one; it is written automatically during output, e.g. by XMLOutputter.)"; 
/*  467 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkProcessingInstructionData(String data) {
/*  482 */     String reason = checkCharacterData(data);
/*  484 */     if (reason == null && 
/*  485 */       data.indexOf("?>") >= 0)
/*  486 */       return "Processing instructions cannot contain the string \"?>\""; 
/*  491 */     return reason;
/*      */   }
/*      */   
/*      */   public static String checkCommentData(String data) {
/*  503 */     String reason = null;
/*  504 */     if ((reason = checkCharacterData(data)) != null)
/*  505 */       return reason; 
/*  508 */     if (data.indexOf("--") != -1)
/*  509 */       return "Comments cannot contain double hyphens (--)"; 
/*  511 */     if (data.endsWith("-"))
/*  512 */       return "Comment data cannot end with a hyphen."; 
/*  516 */     return null;
/*      */   }
/*      */   
/*      */   public static int decodeSurrogatePair(char high, char low) {
/*  526 */     return 65536 + (high - 55296) * 1024 + low - 56320;
/*      */   }
/*      */   
/*      */   public static boolean isXMLPublicIDCharacter(char c) {
/*  533 */     if (c >= 'a' && c <= 'z')
/*  533 */       return true; 
/*  534 */     if (c >= '?' && c <= 'Z')
/*  534 */       return true; 
/*  535 */     if (c >= '\'' && c <= ';')
/*  535 */       return true; 
/*  537 */     if (c == ' ')
/*  537 */       return true; 
/*  538 */     if (c == '!')
/*  538 */       return true; 
/*  539 */     if (c == '=')
/*  539 */       return true; 
/*  540 */     if (c == '#')
/*  540 */       return true; 
/*  541 */     if (c == '$')
/*  541 */       return true; 
/*  542 */     if (c == '_')
/*  542 */       return true; 
/*  543 */     if (c == '%')
/*  543 */       return true; 
/*  544 */     if (c == '\n')
/*  544 */       return true; 
/*  545 */     if (c == '\r')
/*  545 */       return true; 
/*  546 */     if (c == '\t')
/*  546 */       return true; 
/*  548 */     return false;
/*      */   }
/*      */   
/*      */   public static String checkPublicID(String publicID) {
/*  560 */     String reason = null;
/*  562 */     if (publicID == null)
/*  562 */       return null; 
/*  565 */     for (int i = 0; i < publicID.length(); i++) {
/*  566 */       char c = publicID.charAt(i);
/*  567 */       if (!isXMLPublicIDCharacter(c)) {
/*  568 */         reason = c + " is not a legal character in public IDs";
/*      */         break;
/*      */       } 
/*      */     } 
/*  573 */     return reason;
/*      */   }
/*      */   
/*      */   public static String checkSystemLiteral(String systemLiteral) {
/*  586 */     String reason = null;
/*  588 */     if (systemLiteral == null)
/*  588 */       return null; 
/*  591 */     if (systemLiteral.indexOf('\'') != -1 && systemLiteral.indexOf('"') != -1) {
/*  593 */       reason = "System literals cannot simultaneously contain both single and double quotes.";
/*      */     } else {
/*  597 */       reason = checkCharacterData(systemLiteral);
/*      */     } 
/*  600 */     return reason;
/*      */   }
/*      */   
/*      */   public static String checkXMLName(String name) {
/*  613 */     if (name == null || name.length() == 0 || name.trim().equals(""))
/*  615 */       return "XML names cannot be null or empty"; 
/*  620 */     char first = name.charAt(0);
/*  621 */     if (!isXMLNameStartCharacter(first))
/*  622 */       return "XML names cannot begin with the character \"" + first + "\""; 
/*  626 */     for (int i = 1, len = name.length(); i < len; i++) {
/*  627 */       char c = name.charAt(i);
/*  628 */       if (!isXMLNameCharacter(c))
/*  629 */         return "XML names cannot contain the character \"" + c + "\""; 
/*      */     } 
/*  634 */     return null;
/*      */   }
/*      */   
/*      */   public static String checkURI(String uri) {
/*  649 */     if (uri == null || uri.equals(""))
/*  650 */       return null; 
/*  653 */     for (int i = 0; i < uri.length(); i++) {
/*  654 */       char test = uri.charAt(i);
/*  655 */       if (!isURICharacter(test)) {
/*  656 */         String msgNumber = "0x" + Integer.toHexString(test);
/*  657 */         if (test <= '\t')
/*  657 */           msgNumber = "0x0" + Integer.toHexString(test); 
/*  658 */         return "URIs cannot contain " + msgNumber;
/*      */       } 
/*  660 */       if (test == '%')
/*      */         try {
/*  662 */           char firstDigit = uri.charAt(i + 1);
/*  663 */           char secondDigit = uri.charAt(i + 2);
/*  664 */           if (!isHexDigit(firstDigit) || !isHexDigit(secondDigit))
/*  666 */             return "Percent signs in URIs must be followed by exactly two hexadecimal digits."; 
/*  671 */         } catch (StringIndexOutOfBoundsException e) {
/*  672 */           return "Percent signs in URIs must be followed by exactly two hexadecimal digits.";
/*      */         }  
/*      */     } 
/*  679 */     return null;
/*      */   }
/*      */   
/*      */   public static boolean isHexDigit(char c) {
/*  699 */     if (c >= '0' && c <= '9')
/*  699 */       return true; 
/*  700 */     if (c >= 'A' && c <= 'F')
/*  700 */       return true; 
/*  701 */     if (c >= 'a' && c <= 'f')
/*  701 */       return true; 
/*  703 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isHighSurrogate(char ch) {
/*  714 */     return (ch >= '?' && ch <= '?');
/*      */   }
/*      */   
/*      */   public static boolean isLowSurrogate(char ch) {
/*  725 */     return (ch >= '?' && ch <= '?');
/*      */   }
/*      */   
/*      */   public static boolean isURICharacter(char c) {
/*  739 */     if (c >= 'a' && c <= 'z')
/*  739 */       return true; 
/*  740 */     if (c >= 'A' && c <= 'Z')
/*  740 */       return true; 
/*  741 */     if (c >= '0' && c <= '9')
/*  741 */       return true; 
/*  742 */     if (c == '/')
/*  742 */       return true; 
/*  743 */     if (c == '-')
/*  743 */       return true; 
/*  744 */     if (c == '.')
/*  744 */       return true; 
/*  745 */     if (c == '?')
/*  745 */       return true; 
/*  746 */     if (c == ':')
/*  746 */       return true; 
/*  747 */     if (c == '@')
/*  747 */       return true; 
/*  748 */     if (c == '&')
/*  748 */       return true; 
/*  749 */     if (c == '=')
/*  749 */       return true; 
/*  750 */     if (c == '+')
/*  750 */       return true; 
/*  751 */     if (c == '$')
/*  751 */       return true; 
/*  752 */     if (c == ',')
/*  752 */       return true; 
/*  753 */     if (c == '%')
/*  753 */       return true; 
/*  755 */     if (c == '_')
/*  755 */       return true; 
/*  756 */     if (c == '!')
/*  756 */       return true; 
/*  757 */     if (c == '~')
/*  757 */       return true; 
/*  758 */     if (c == '*')
/*  758 */       return true; 
/*  759 */     if (c == '\'')
/*  759 */       return true; 
/*  760 */     if (c == '(')
/*  760 */       return true; 
/*  761 */     if (c == ')')
/*  761 */       return true; 
/*  762 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isXMLCharacter(int c) {
/*  776 */     if (c == 10)
/*  776 */       return true; 
/*  777 */     if (c == 13)
/*  777 */       return true; 
/*  778 */     if (c == 9)
/*  778 */       return true; 
/*  780 */     if (c < 32)
/*  780 */       return false; 
/*  780 */     if (c <= 55295)
/*  780 */       return true; 
/*  781 */     if (c < 57344)
/*  781 */       return false; 
/*  781 */     if (c <= 65533)
/*  781 */       return true; 
/*  782 */     if (c < 65536)
/*  782 */       return false; 
/*  782 */     if (c <= 1114111)
/*  782 */       return true; 
/*  784 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isXMLNameCharacter(char c) {
/*  799 */     return (isXMLLetter(c) || isXMLDigit(c) || c == '.' || c == '-' || c == '_' || c == ':' || isXMLCombiningChar(c) || isXMLExtender(c));
/*      */   }
/*      */   
/*      */   public static boolean isXMLNameStartCharacter(char c) {
/*  817 */     return (isXMLLetter(c) || c == '_' || c == ':');
/*      */   }
/*      */   
/*      */   public static boolean isXMLLetterOrDigit(char c) {
/*  832 */     return (isXMLLetter(c) || isXMLDigit(c));
/*      */   }
/*      */   
/*      */   public static boolean isXMLLetter(char c) {
/*  849 */     if (c < 'A')
/*  849 */       return false; 
/*  849 */     if (c <= 'Z')
/*  849 */       return true; 
/*  850 */     if (c < 'a')
/*  850 */       return false; 
/*  850 */     if (c <= 'z')
/*  850 */       return true; 
/*  851 */     if (c < 'À')
/*  851 */       return false; 
/*  851 */     if (c <= 'Ö')
/*  851 */       return true; 
/*  852 */     if (c < 'Ø')
/*  852 */       return false; 
/*  852 */     if (c <= 'ö')
/*  852 */       return true; 
/*  853 */     if (c < 'ø')
/*  853 */       return false; 
/*  853 */     if (c <= 'ÿ')
/*  853 */       return true; 
/*  854 */     if (c < 'Ā')
/*  854 */       return false; 
/*  854 */     if (c <= 'ı')
/*  854 */       return true; 
/*  855 */     if (c < 'Ĵ')
/*  855 */       return false; 
/*  855 */     if (c <= 'ľ')
/*  855 */       return true; 
/*  856 */     if (c < 'Ł')
/*  856 */       return false; 
/*  856 */     if (c <= 'ň')
/*  856 */       return true; 
/*  857 */     if (c < 'Ŋ')
/*  857 */       return false; 
/*  857 */     if (c <= 'ž')
/*  857 */       return true; 
/*  858 */     if (c < 'ƀ')
/*  858 */       return false; 
/*  858 */     if (c <= 'ǃ')
/*  858 */       return true; 
/*  859 */     if (c < 'Ǎ')
/*  859 */       return false; 
/*  859 */     if (c <= 'ǰ')
/*  859 */       return true; 
/*  860 */     if (c < 'Ǵ')
/*  860 */       return false; 
/*  860 */     if (c <= 'ǵ')
/*  860 */       return true; 
/*  861 */     if (c < 'Ǻ')
/*  861 */       return false; 
/*  861 */     if (c <= 'ȗ')
/*  861 */       return true; 
/*  862 */     if (c < 'ɐ')
/*  862 */       return false; 
/*  862 */     if (c <= 'ʨ')
/*  862 */       return true; 
/*  863 */     if (c < 'ʻ')
/*  863 */       return false; 
/*  863 */     if (c <= 'ˁ')
/*  863 */       return true; 
/*  864 */     if (c == 'Ά')
/*  864 */       return true; 
/*  865 */     if (c < 'Έ')
/*  865 */       return false; 
/*  865 */     if (c <= 'Ί')
/*  865 */       return true; 
/*  866 */     if (c == 'Ό')
/*  866 */       return true; 
/*  867 */     if (c < 'Ύ')
/*  867 */       return false; 
/*  867 */     if (c <= 'Ρ')
/*  867 */       return true; 
/*  868 */     if (c < 'Σ')
/*  868 */       return false; 
/*  868 */     if (c <= 'ώ')
/*  868 */       return true; 
/*  869 */     if (c < 'ϐ')
/*  869 */       return false; 
/*  869 */     if (c <= 'ϖ')
/*  869 */       return true; 
/*  870 */     if (c == 'Ϛ')
/*  870 */       return true; 
/*  871 */     if (c == 'Ϝ')
/*  871 */       return true; 
/*  872 */     if (c == 'Ϟ')
/*  872 */       return true; 
/*  873 */     if (c == 'Ϡ')
/*  873 */       return true; 
/*  874 */     if (c < 'Ϣ')
/*  874 */       return false; 
/*  874 */     if (c <= 'ϳ')
/*  874 */       return true; 
/*  875 */     if (c < 'Ё')
/*  875 */       return false; 
/*  875 */     if (c <= 'Ќ')
/*  875 */       return true; 
/*  876 */     if (c < 'Ў')
/*  876 */       return false; 
/*  876 */     if (c <= 'я')
/*  876 */       return true; 
/*  877 */     if (c < 'ё')
/*  877 */       return false; 
/*  877 */     if (c <= 'ќ')
/*  877 */       return true; 
/*  878 */     if (c < 'ў')
/*  878 */       return false; 
/*  878 */     if (c <= 'ҁ')
/*  878 */       return true; 
/*  879 */     if (c < 'Ґ')
/*  879 */       return false; 
/*  879 */     if (c <= 'ӄ')
/*  879 */       return true; 
/*  880 */     if (c < 'Ӈ')
/*  880 */       return false; 
/*  880 */     if (c <= 'ӈ')
/*  880 */       return true; 
/*  881 */     if (c < 'Ӌ')
/*  881 */       return false; 
/*  881 */     if (c <= 'ӌ')
/*  881 */       return true; 
/*  882 */     if (c < 'Ӑ')
/*  882 */       return false; 
/*  882 */     if (c <= 'ӫ')
/*  882 */       return true; 
/*  883 */     if (c < 'Ӯ')
/*  883 */       return false; 
/*  883 */     if (c <= 'ӵ')
/*  883 */       return true; 
/*  884 */     if (c < 'Ӹ')
/*  884 */       return false; 
/*  884 */     if (c <= 'ӹ')
/*  884 */       return true; 
/*  885 */     if (c < 'Ա')
/*  885 */       return false; 
/*  885 */     if (c <= 'Ֆ')
/*  885 */       return true; 
/*  886 */     if (c == 'ՙ')
/*  886 */       return true; 
/*  887 */     if (c < 'ա')
/*  887 */       return false; 
/*  887 */     if (c <= 'ֆ')
/*  887 */       return true; 
/*  888 */     if (c < 'א')
/*  888 */       return false; 
/*  888 */     if (c <= 'ת')
/*  888 */       return true; 
/*  889 */     if (c < 'װ')
/*  889 */       return false; 
/*  889 */     if (c <= 'ײ')
/*  889 */       return true; 
/*  890 */     if (c < 'ء')
/*  890 */       return false; 
/*  890 */     if (c <= 'غ')
/*  890 */       return true; 
/*  891 */     if (c < 'ف')
/*  891 */       return false; 
/*  891 */     if (c <= 'ي')
/*  891 */       return true; 
/*  892 */     if (c < 'ٱ')
/*  892 */       return false; 
/*  892 */     if (c <= 'ڷ')
/*  892 */       return true; 
/*  893 */     if (c < 'ں')
/*  893 */       return false; 
/*  893 */     if (c <= 'ھ')
/*  893 */       return true; 
/*  894 */     if (c < 'ۀ')
/*  894 */       return false; 
/*  894 */     if (c <= 'ێ')
/*  894 */       return true; 
/*  895 */     if (c < 'ې')
/*  895 */       return false; 
/*  895 */     if (c <= 'ۓ')
/*  895 */       return true; 
/*  896 */     if (c == 'ە')
/*  896 */       return true; 
/*  897 */     if (c < 'ۥ')
/*  897 */       return false; 
/*  897 */     if (c <= 'ۦ')
/*  897 */       return true; 
/*  898 */     if (c < 'अ')
/*  898 */       return false; 
/*  898 */     if (c <= 'ह')
/*  898 */       return true; 
/*  899 */     if (c == 'ऽ')
/*  899 */       return true; 
/*  900 */     if (c < 'क़')
/*  900 */       return false; 
/*  900 */     if (c <= 'ॡ')
/*  900 */       return true; 
/*  901 */     if (c < 'অ')
/*  901 */       return false; 
/*  901 */     if (c <= 'ঌ')
/*  901 */       return true; 
/*  902 */     if (c < 'এ')
/*  902 */       return false; 
/*  902 */     if (c <= 'ঐ')
/*  902 */       return true; 
/*  903 */     if (c < 'ও')
/*  903 */       return false; 
/*  903 */     if (c <= 'ন')
/*  903 */       return true; 
/*  904 */     if (c < 'প')
/*  904 */       return false; 
/*  904 */     if (c <= 'র')
/*  904 */       return true; 
/*  905 */     if (c == 'ল')
/*  905 */       return true; 
/*  906 */     if (c < 'শ')
/*  906 */       return false; 
/*  906 */     if (c <= 'হ')
/*  906 */       return true; 
/*  907 */     if (c < 'ড়')
/*  907 */       return false; 
/*  907 */     if (c <= 'ঢ়')
/*  907 */       return true; 
/*  908 */     if (c < 'য়')
/*  908 */       return false; 
/*  908 */     if (c <= 'ৡ')
/*  908 */       return true; 
/*  909 */     if (c < 'ৰ')
/*  909 */       return false; 
/*  909 */     if (c <= 'ৱ')
/*  909 */       return true; 
/*  910 */     if (c < 'ਅ')
/*  910 */       return false; 
/*  910 */     if (c <= 'ਊ')
/*  910 */       return true; 
/*  911 */     if (c < 'ਏ')
/*  911 */       return false; 
/*  911 */     if (c <= 'ਐ')
/*  911 */       return true; 
/*  912 */     if (c < 'ਓ')
/*  912 */       return false; 
/*  912 */     if (c <= 'ਨ')
/*  912 */       return true; 
/*  913 */     if (c < 'ਪ')
/*  913 */       return false; 
/*  913 */     if (c <= 'ਰ')
/*  913 */       return true; 
/*  914 */     if (c < 'ਲ')
/*  914 */       return false; 
/*  914 */     if (c <= 'ਲ਼')
/*  914 */       return true; 
/*  915 */     if (c < 'ਵ')
/*  915 */       return false; 
/*  915 */     if (c <= 'ਸ਼')
/*  915 */       return true; 
/*  916 */     if (c < 'ਸ')
/*  916 */       return false; 
/*  916 */     if (c <= 'ਹ')
/*  916 */       return true; 
/*  917 */     if (c < 'ਖ਼')
/*  917 */       return false; 
/*  917 */     if (c <= 'ੜ')
/*  917 */       return true; 
/*  918 */     if (c == 'ਫ਼')
/*  918 */       return true; 
/*  919 */     if (c < 'ੲ')
/*  919 */       return false; 
/*  919 */     if (c <= 'ੴ')
/*  919 */       return true; 
/*  920 */     if (c < 'અ')
/*  920 */       return false; 
/*  920 */     if (c <= 'ઋ')
/*  920 */       return true; 
/*  921 */     if (c == 'ઍ')
/*  921 */       return true; 
/*  922 */     if (c < 'એ')
/*  922 */       return false; 
/*  922 */     if (c <= 'ઑ')
/*  922 */       return true; 
/*  923 */     if (c < 'ઓ')
/*  923 */       return false; 
/*  923 */     if (c <= 'ન')
/*  923 */       return true; 
/*  924 */     if (c < 'પ')
/*  924 */       return false; 
/*  924 */     if (c <= 'ર')
/*  924 */       return true; 
/*  925 */     if (c < 'લ')
/*  925 */       return false; 
/*  925 */     if (c <= 'ળ')
/*  925 */       return true; 
/*  926 */     if (c < 'વ')
/*  926 */       return false; 
/*  926 */     if (c <= 'હ')
/*  926 */       return true; 
/*  927 */     if (c == 'ઽ')
/*  927 */       return true; 
/*  928 */     if (c == 'ૠ')
/*  928 */       return true; 
/*  929 */     if (c < 'ଅ')
/*  929 */       return false; 
/*  929 */     if (c <= 'ଌ')
/*  929 */       return true; 
/*  930 */     if (c < 'ଏ')
/*  930 */       return false; 
/*  930 */     if (c <= 'ଐ')
/*  930 */       return true; 
/*  931 */     if (c < 'ଓ')
/*  931 */       return false; 
/*  931 */     if (c <= 'ନ')
/*  931 */       return true; 
/*  932 */     if (c < 'ପ')
/*  932 */       return false; 
/*  932 */     if (c <= 'ର')
/*  932 */       return true; 
/*  933 */     if (c < 'ଲ')
/*  933 */       return false; 
/*  933 */     if (c <= 'ଳ')
/*  933 */       return true; 
/*  934 */     if (c < 'ଶ')
/*  934 */       return false; 
/*  934 */     if (c <= 'ହ')
/*  934 */       return true; 
/*  935 */     if (c == 'ଽ')
/*  935 */       return true; 
/*  936 */     if (c < 'ଡ଼')
/*  936 */       return false; 
/*  936 */     if (c <= 'ଢ଼')
/*  936 */       return true; 
/*  937 */     if (c < 'ୟ')
/*  937 */       return false; 
/*  937 */     if (c <= 'ୡ')
/*  937 */       return true; 
/*  938 */     if (c < 'அ')
/*  938 */       return false; 
/*  938 */     if (c <= 'ஊ')
/*  938 */       return true; 
/*  939 */     if (c < 'எ')
/*  939 */       return false; 
/*  939 */     if (c <= 'ஐ')
/*  939 */       return true; 
/*  940 */     if (c < 'ஒ')
/*  940 */       return false; 
/*  940 */     if (c <= 'க')
/*  940 */       return true; 
/*  941 */     if (c < 'ங')
/*  941 */       return false; 
/*  941 */     if (c <= 'ச')
/*  941 */       return true; 
/*  942 */     if (c == 'ஜ')
/*  942 */       return true; 
/*  943 */     if (c < 'ஞ')
/*  943 */       return false; 
/*  943 */     if (c <= 'ட')
/*  943 */       return true; 
/*  944 */     if (c < 'ண')
/*  944 */       return false; 
/*  944 */     if (c <= 'த')
/*  944 */       return true; 
/*  945 */     if (c < 'ந')
/*  945 */       return false; 
/*  945 */     if (c <= 'ப')
/*  945 */       return true; 
/*  946 */     if (c < 'ம')
/*  946 */       return false; 
/*  946 */     if (c <= 'வ')
/*  946 */       return true; 
/*  947 */     if (c < 'ஷ')
/*  947 */       return false; 
/*  947 */     if (c <= 'ஹ')
/*  947 */       return true; 
/*  948 */     if (c < 'అ')
/*  948 */       return false; 
/*  948 */     if (c <= 'ఌ')
/*  948 */       return true; 
/*  949 */     if (c < 'ఎ')
/*  949 */       return false; 
/*  949 */     if (c <= 'ఐ')
/*  949 */       return true; 
/*  950 */     if (c < 'ఒ')
/*  950 */       return false; 
/*  950 */     if (c <= 'న')
/*  950 */       return true; 
/*  951 */     if (c < 'ప')
/*  951 */       return false; 
/*  951 */     if (c <= 'ళ')
/*  951 */       return true; 
/*  952 */     if (c < 'వ')
/*  952 */       return false; 
/*  952 */     if (c <= 'హ')
/*  952 */       return true; 
/*  953 */     if (c < 'ౠ')
/*  953 */       return false; 
/*  953 */     if (c <= 'ౡ')
/*  953 */       return true; 
/*  954 */     if (c < 'ಅ')
/*  954 */       return false; 
/*  954 */     if (c <= 'ಌ')
/*  954 */       return true; 
/*  955 */     if (c < 'ಎ')
/*  955 */       return false; 
/*  955 */     if (c <= 'ಐ')
/*  955 */       return true; 
/*  956 */     if (c < 'ಒ')
/*  956 */       return false; 
/*  956 */     if (c <= 'ನ')
/*  956 */       return true; 
/*  957 */     if (c < 'ಪ')
/*  957 */       return false; 
/*  957 */     if (c <= 'ಳ')
/*  957 */       return true; 
/*  958 */     if (c < 'ವ')
/*  958 */       return false; 
/*  958 */     if (c <= 'ಹ')
/*  958 */       return true; 
/*  959 */     if (c == 'ೞ')
/*  959 */       return true; 
/*  960 */     if (c < 'ೠ')
/*  960 */       return false; 
/*  960 */     if (c <= 'ೡ')
/*  960 */       return true; 
/*  961 */     if (c < 'അ')
/*  961 */       return false; 
/*  961 */     if (c <= 'ഌ')
/*  961 */       return true; 
/*  962 */     if (c < 'എ')
/*  962 */       return false; 
/*  962 */     if (c <= 'ഐ')
/*  962 */       return true; 
/*  963 */     if (c < 'ഒ')
/*  963 */       return false; 
/*  963 */     if (c <= 'ന')
/*  963 */       return true; 
/*  964 */     if (c < 'പ')
/*  964 */       return false; 
/*  964 */     if (c <= 'ഹ')
/*  964 */       return true; 
/*  965 */     if (c < 'ൠ')
/*  965 */       return false; 
/*  965 */     if (c <= 'ൡ')
/*  965 */       return true; 
/*  966 */     if (c < 'ก')
/*  966 */       return false; 
/*  966 */     if (c <= 'ฮ')
/*  966 */       return true; 
/*  967 */     if (c == 'ะ')
/*  967 */       return true; 
/*  968 */     if (c < 'า')
/*  968 */       return false; 
/*  968 */     if (c <= 'ำ')
/*  968 */       return true; 
/*  969 */     if (c < 'เ')
/*  969 */       return false; 
/*  969 */     if (c <= 'ๅ')
/*  969 */       return true; 
/*  970 */     if (c < 'ກ')
/*  970 */       return false; 
/*  970 */     if (c <= 'ຂ')
/*  970 */       return true; 
/*  971 */     if (c == 'ຄ')
/*  971 */       return true; 
/*  972 */     if (c < 'ງ')
/*  972 */       return false; 
/*  972 */     if (c <= 'ຈ')
/*  972 */       return true; 
/*  973 */     if (c == 'ຊ')
/*  973 */       return true; 
/*  974 */     if (c == 'ຍ')
/*  974 */       return true; 
/*  975 */     if (c < 'ດ')
/*  975 */       return false; 
/*  975 */     if (c <= 'ທ')
/*  975 */       return true; 
/*  976 */     if (c < 'ນ')
/*  976 */       return false; 
/*  976 */     if (c <= 'ຟ')
/*  976 */       return true; 
/*  977 */     if (c < 'ມ')
/*  977 */       return false; 
/*  977 */     if (c <= 'ຣ')
/*  977 */       return true; 
/*  978 */     if (c == 'ລ')
/*  978 */       return true; 
/*  979 */     if (c == 'ວ')
/*  979 */       return true; 
/*  980 */     if (c < 'ສ')
/*  980 */       return false; 
/*  980 */     if (c <= 'ຫ')
/*  980 */       return true; 
/*  981 */     if (c < 'ອ')
/*  981 */       return false; 
/*  981 */     if (c <= 'ຮ')
/*  981 */       return true; 
/*  982 */     if (c == 'ະ')
/*  982 */       return true; 
/*  983 */     if (c < 'າ')
/*  983 */       return false; 
/*  983 */     if (c <= 'ຳ')
/*  983 */       return true; 
/*  984 */     if (c == 'ຽ')
/*  984 */       return true; 
/*  985 */     if (c < 'ເ')
/*  985 */       return false; 
/*  985 */     if (c <= 'ໄ')
/*  985 */       return true; 
/*  986 */     if (c < 'ཀ')
/*  986 */       return false; 
/*  986 */     if (c <= 'ཇ')
/*  986 */       return true; 
/*  987 */     if (c < 'ཉ')
/*  987 */       return false; 
/*  987 */     if (c <= 'ཀྵ')
/*  987 */       return true; 
/*  988 */     if (c < 'Ⴀ')
/*  988 */       return false; 
/*  988 */     if (c <= 'Ⴥ')
/*  988 */       return true; 
/*  989 */     if (c < 'ა')
/*  989 */       return false; 
/*  989 */     if (c <= 'ჶ')
/*  989 */       return true; 
/*  990 */     if (c == 'ᄀ')
/*  990 */       return true; 
/*  991 */     if (c < 'ᄂ')
/*  991 */       return false; 
/*  991 */     if (c <= 'ᄃ')
/*  991 */       return true; 
/*  992 */     if (c < 'ᄅ')
/*  992 */       return false; 
/*  992 */     if (c <= 'ᄇ')
/*  992 */       return true; 
/*  993 */     if (c == 'ᄉ')
/*  993 */       return true; 
/*  994 */     if (c < 'ᄋ')
/*  994 */       return false; 
/*  994 */     if (c <= 'ᄌ')
/*  994 */       return true; 
/*  995 */     if (c < 'ᄎ')
/*  995 */       return false; 
/*  995 */     if (c <= 'ᄒ')
/*  995 */       return true; 
/*  996 */     if (c == 'ᄼ')
/*  996 */       return true; 
/*  997 */     if (c == 'ᄾ')
/*  997 */       return true; 
/*  998 */     if (c == 'ᅀ')
/*  998 */       return true; 
/*  999 */     if (c == 'ᅌ')
/*  999 */       return true; 
/* 1000 */     if (c == 'ᅎ')
/* 1000 */       return true; 
/* 1001 */     if (c == 'ᅐ')
/* 1001 */       return true; 
/* 1002 */     if (c < 'ᅔ')
/* 1002 */       return false; 
/* 1002 */     if (c <= 'ᅕ')
/* 1002 */       return true; 
/* 1003 */     if (c == 'ᅙ')
/* 1003 */       return true; 
/* 1004 */     if (c < 'ᅟ')
/* 1004 */       return false; 
/* 1004 */     if (c <= 'ᅡ')
/* 1004 */       return true; 
/* 1005 */     if (c == 'ᅣ')
/* 1005 */       return true; 
/* 1006 */     if (c == 'ᅥ')
/* 1006 */       return true; 
/* 1007 */     if (c == 'ᅧ')
/* 1007 */       return true; 
/* 1008 */     if (c == 'ᅩ')
/* 1008 */       return true; 
/* 1009 */     if (c < 'ᅭ')
/* 1009 */       return false; 
/* 1009 */     if (c <= 'ᅮ')
/* 1009 */       return true; 
/* 1010 */     if (c < 'ᅲ')
/* 1010 */       return false; 
/* 1010 */     if (c <= 'ᅳ')
/* 1010 */       return true; 
/* 1011 */     if (c == 'ᅵ')
/* 1011 */       return true; 
/* 1012 */     if (c == 'ᆞ')
/* 1012 */       return true; 
/* 1013 */     if (c == 'ᆨ')
/* 1013 */       return true; 
/* 1014 */     if (c == 'ᆫ')
/* 1014 */       return true; 
/* 1015 */     if (c < 'ᆮ')
/* 1015 */       return false; 
/* 1015 */     if (c <= 'ᆯ')
/* 1015 */       return true; 
/* 1016 */     if (c < 'ᆷ')
/* 1016 */       return false; 
/* 1016 */     if (c <= 'ᆸ')
/* 1016 */       return true; 
/* 1017 */     if (c == 'ᆺ')
/* 1017 */       return true; 
/* 1018 */     if (c < 'ᆼ')
/* 1018 */       return false; 
/* 1018 */     if (c <= 'ᇂ')
/* 1018 */       return true; 
/* 1019 */     if (c == 'ᇫ')
/* 1019 */       return true; 
/* 1020 */     if (c == 'ᇰ')
/* 1020 */       return true; 
/* 1021 */     if (c == 'ᇹ')
/* 1021 */       return true; 
/* 1022 */     if (c < 'Ḁ')
/* 1022 */       return false; 
/* 1022 */     if (c <= 'ẛ')
/* 1022 */       return true; 
/* 1023 */     if (c < 'Ạ')
/* 1023 */       return false; 
/* 1023 */     if (c <= 'ỹ')
/* 1023 */       return true; 
/* 1024 */     if (c < 'ἀ')
/* 1024 */       return false; 
/* 1024 */     if (c <= 'ἕ')
/* 1024 */       return true; 
/* 1025 */     if (c < 'Ἐ')
/* 1025 */       return false; 
/* 1025 */     if (c <= 'Ἕ')
/* 1025 */       return true; 
/* 1026 */     if (c < 'ἠ')
/* 1026 */       return false; 
/* 1026 */     if (c <= 'ὅ')
/* 1026 */       return true; 
/* 1027 */     if (c < 'Ὀ')
/* 1027 */       return false; 
/* 1027 */     if (c <= 'Ὅ')
/* 1027 */       return true; 
/* 1028 */     if (c < 'ὐ')
/* 1028 */       return false; 
/* 1028 */     if (c <= 'ὗ')
/* 1028 */       return true; 
/* 1029 */     if (c == 'Ὑ')
/* 1029 */       return true; 
/* 1030 */     if (c == 'Ὓ')
/* 1030 */       return true; 
/* 1031 */     if (c == 'Ὕ')
/* 1031 */       return true; 
/* 1032 */     if (c < 'Ὗ')
/* 1032 */       return false; 
/* 1032 */     if (c <= 'ώ')
/* 1032 */       return true; 
/* 1033 */     if (c < 'ᾀ')
/* 1033 */       return false; 
/* 1033 */     if (c <= 'ᾴ')
/* 1033 */       return true; 
/* 1034 */     if (c < 'ᾶ')
/* 1034 */       return false; 
/* 1034 */     if (c <= 'ᾼ')
/* 1034 */       return true; 
/* 1035 */     if (c == 'ι')
/* 1035 */       return true; 
/* 1036 */     if (c < 'ῂ')
/* 1036 */       return false; 
/* 1036 */     if (c <= 'ῄ')
/* 1036 */       return true; 
/* 1037 */     if (c < 'ῆ')
/* 1037 */       return false; 
/* 1037 */     if (c <= 'ῌ')
/* 1037 */       return true; 
/* 1038 */     if (c < 'ῐ')
/* 1038 */       return false; 
/* 1038 */     if (c <= 'ΐ')
/* 1038 */       return true; 
/* 1039 */     if (c < 'ῖ')
/* 1039 */       return false; 
/* 1039 */     if (c <= 'Ί')
/* 1039 */       return true; 
/* 1040 */     if (c < 'ῠ')
/* 1040 */       return false; 
/* 1040 */     if (c <= 'Ῥ')
/* 1040 */       return true; 
/* 1041 */     if (c < 'ῲ')
/* 1041 */       return false; 
/* 1041 */     if (c <= 'ῴ')
/* 1041 */       return true; 
/* 1042 */     if (c < 'ῶ')
/* 1042 */       return false; 
/* 1042 */     if (c <= 'ῼ')
/* 1042 */       return true; 
/* 1043 */     if (c == 'Ω')
/* 1043 */       return true; 
/* 1044 */     if (c < 'K')
/* 1044 */       return false; 
/* 1044 */     if (c <= 'Å')
/* 1044 */       return true; 
/* 1045 */     if (c == '℮')
/* 1045 */       return true; 
/* 1046 */     if (c < 'ↀ')
/* 1046 */       return false; 
/* 1046 */     if (c <= 'ↂ')
/* 1046 */       return true; 
/* 1047 */     if (c == '〇')
/* 1047 */       return true; 
/* 1048 */     if (c < '〡')
/* 1048 */       return false; 
/* 1048 */     if (c <= '〩')
/* 1048 */       return true; 
/* 1049 */     if (c < 'ぁ')
/* 1049 */       return false; 
/* 1049 */     if (c <= 'ゔ')
/* 1049 */       return true; 
/* 1050 */     if (c < 'ァ')
/* 1050 */       return false; 
/* 1050 */     if (c <= 'ヺ')
/* 1050 */       return true; 
/* 1051 */     if (c < 'ㄅ')
/* 1051 */       return false; 
/* 1051 */     if (c <= 'ㄬ')
/* 1051 */       return true; 
/* 1052 */     if (c < '一')
/* 1052 */       return false; 
/* 1052 */     if (c <= '龥')
/* 1052 */       return true; 
/* 1053 */     if (c < '가')
/* 1053 */       return false; 
/* 1053 */     if (c <= '힣')
/* 1053 */       return true; 
/* 1055 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isXMLCombiningChar(char c) {
/* 1070 */     if (c < '̀')
/* 1070 */       return false; 
/* 1070 */     if (c <= 'ͅ')
/* 1070 */       return true; 
/* 1071 */     if (c < '͠')
/* 1071 */       return false; 
/* 1071 */     if (c <= '͡')
/* 1071 */       return true; 
/* 1072 */     if (c < '҃')
/* 1072 */       return false; 
/* 1072 */     if (c <= '҆')
/* 1072 */       return true; 
/* 1073 */     if (c < '֑')
/* 1073 */       return false; 
/* 1073 */     if (c <= '֡')
/* 1073 */       return true; 
/* 1075 */     if (c < '֣')
/* 1075 */       return false; 
/* 1075 */     if (c <= 'ֹ')
/* 1075 */       return true; 
/* 1076 */     if (c < 'ֻ')
/* 1076 */       return false; 
/* 1076 */     if (c <= 'ֽ')
/* 1076 */       return true; 
/* 1077 */     if (c == 'ֿ')
/* 1077 */       return true; 
/* 1078 */     if (c < 'ׁ')
/* 1078 */       return false; 
/* 1078 */     if (c <= 'ׂ')
/* 1078 */       return true; 
/* 1080 */     if (c == 'ׄ')
/* 1080 */       return true; 
/* 1081 */     if (c < 'ً')
/* 1081 */       return false; 
/* 1081 */     if (c <= 'ْ')
/* 1081 */       return true; 
/* 1082 */     if (c == 'ٰ')
/* 1082 */       return true; 
/* 1083 */     if (c < 'ۖ')
/* 1083 */       return false; 
/* 1083 */     if (c <= 'ۜ')
/* 1083 */       return true; 
/* 1085 */     if (c < '۝')
/* 1085 */       return false; 
/* 1085 */     if (c <= '۟')
/* 1085 */       return true; 
/* 1086 */     if (c < '۠')
/* 1086 */       return false; 
/* 1086 */     if (c <= 'ۤ')
/* 1086 */       return true; 
/* 1087 */     if (c < 'ۧ')
/* 1087 */       return false; 
/* 1087 */     if (c <= 'ۨ')
/* 1087 */       return true; 
/* 1089 */     if (c < '۪')
/* 1089 */       return false; 
/* 1089 */     if (c <= 'ۭ')
/* 1089 */       return true; 
/* 1090 */     if (c < 'ँ')
/* 1090 */       return false; 
/* 1090 */     if (c <= 'ः')
/* 1090 */       return true; 
/* 1091 */     if (c == '़')
/* 1091 */       return true; 
/* 1092 */     if (c < 'ा')
/* 1092 */       return false; 
/* 1092 */     if (c <= 'ौ')
/* 1092 */       return true; 
/* 1094 */     if (c == '्')
/* 1094 */       return true; 
/* 1095 */     if (c < '॑')
/* 1095 */       return false; 
/* 1095 */     if (c <= '॔')
/* 1095 */       return true; 
/* 1096 */     if (c < 'ॢ')
/* 1096 */       return false; 
/* 1096 */     if (c <= 'ॣ')
/* 1096 */       return true; 
/* 1097 */     if (c < 'ঁ')
/* 1097 */       return false; 
/* 1097 */     if (c <= 'ঃ')
/* 1097 */       return true; 
/* 1099 */     if (c == '়')
/* 1099 */       return true; 
/* 1100 */     if (c == 'া')
/* 1100 */       return true; 
/* 1101 */     if (c == 'ি')
/* 1101 */       return true; 
/* 1102 */     if (c < 'ী')
/* 1102 */       return false; 
/* 1102 */     if (c <= 'ৄ')
/* 1102 */       return true; 
/* 1103 */     if (c < 'ে')
/* 1103 */       return false; 
/* 1103 */     if (c <= 'ৈ')
/* 1103 */       return true; 
/* 1105 */     if (c < 'ো')
/* 1105 */       return false; 
/* 1105 */     if (c <= '্')
/* 1105 */       return true; 
/* 1106 */     if (c == 'ৗ')
/* 1106 */       return true; 
/* 1107 */     if (c < 'ৢ')
/* 1107 */       return false; 
/* 1107 */     if (c <= 'ৣ')
/* 1107 */       return true; 
/* 1108 */     if (c == 'ਂ')
/* 1108 */       return true; 
/* 1109 */     if (c == '਼')
/* 1109 */       return true; 
/* 1111 */     if (c == 'ਾ')
/* 1111 */       return true; 
/* 1112 */     if (c == 'ਿ')
/* 1112 */       return true; 
/* 1113 */     if (c < 'ੀ')
/* 1113 */       return false; 
/* 1113 */     if (c <= 'ੂ')
/* 1113 */       return true; 
/* 1114 */     if (c < 'ੇ')
/* 1114 */       return false; 
/* 1114 */     if (c <= 'ੈ')
/* 1114 */       return true; 
/* 1116 */     if (c < 'ੋ')
/* 1116 */       return false; 
/* 1116 */     if (c <= '੍')
/* 1116 */       return true; 
/* 1117 */     if (c < 'ੰ')
/* 1117 */       return false; 
/* 1117 */     if (c <= 'ੱ')
/* 1117 */       return true; 
/* 1118 */     if (c < 'ઁ')
/* 1118 */       return false; 
/* 1118 */     if (c <= 'ઃ')
/* 1118 */       return true; 
/* 1119 */     if (c == '઼')
/* 1119 */       return true; 
/* 1121 */     if (c < 'ા')
/* 1121 */       return false; 
/* 1121 */     if (c <= 'ૅ')
/* 1121 */       return true; 
/* 1122 */     if (c < 'ે')
/* 1122 */       return false; 
/* 1122 */     if (c <= 'ૉ')
/* 1122 */       return true; 
/* 1123 */     if (c < 'ો')
/* 1123 */       return false; 
/* 1123 */     if (c <= '્')
/* 1123 */       return true; 
/* 1125 */     if (c < 'ଁ')
/* 1125 */       return false; 
/* 1125 */     if (c <= 'ଃ')
/* 1125 */       return true; 
/* 1126 */     if (c == '଼')
/* 1126 */       return true; 
/* 1127 */     if (c < 'ା')
/* 1127 */       return false; 
/* 1127 */     if (c <= 'ୃ')
/* 1127 */       return true; 
/* 1128 */     if (c < 'େ')
/* 1128 */       return false; 
/* 1128 */     if (c <= 'ୈ')
/* 1128 */       return true; 
/* 1130 */     if (c < 'ୋ')
/* 1130 */       return false; 
/* 1130 */     if (c <= '୍')
/* 1130 */       return true; 
/* 1131 */     if (c < 'ୖ')
/* 1131 */       return false; 
/* 1131 */     if (c <= 'ୗ')
/* 1131 */       return true; 
/* 1132 */     if (c < 'ஂ')
/* 1132 */       return false; 
/* 1132 */     if (c <= 'ஃ')
/* 1132 */       return true; 
/* 1134 */     if (c < 'ா')
/* 1134 */       return false; 
/* 1134 */     if (c <= 'ூ')
/* 1134 */       return true; 
/* 1135 */     if (c < 'ெ')
/* 1135 */       return false; 
/* 1135 */     if (c <= 'ை')
/* 1135 */       return true; 
/* 1136 */     if (c < 'ொ')
/* 1136 */       return false; 
/* 1136 */     if (c <= '்')
/* 1136 */       return true; 
/* 1137 */     if (c == 'ௗ')
/* 1137 */       return true; 
/* 1139 */     if (c < 'ఁ')
/* 1139 */       return false; 
/* 1139 */     if (c <= 'ః')
/* 1139 */       return true; 
/* 1140 */     if (c < 'ా')
/* 1140 */       return false; 
/* 1140 */     if (c <= 'ౄ')
/* 1140 */       return true; 
/* 1141 */     if (c < 'ె')
/* 1141 */       return false; 
/* 1141 */     if (c <= 'ై')
/* 1141 */       return true; 
/* 1143 */     if (c < 'ొ')
/* 1143 */       return false; 
/* 1143 */     if (c <= '్')
/* 1143 */       return true; 
/* 1144 */     if (c < 'ౕ')
/* 1144 */       return false; 
/* 1144 */     if (c <= 'ౖ')
/* 1144 */       return true; 
/* 1145 */     if (c < 'ಂ')
/* 1145 */       return false; 
/* 1145 */     if (c <= 'ಃ')
/* 1145 */       return true; 
/* 1147 */     if (c < 'ಾ')
/* 1147 */       return false; 
/* 1147 */     if (c <= 'ೄ')
/* 1147 */       return true; 
/* 1148 */     if (c < 'ೆ')
/* 1148 */       return false; 
/* 1148 */     if (c <= 'ೈ')
/* 1148 */       return true; 
/* 1149 */     if (c < 'ೊ')
/* 1149 */       return false; 
/* 1149 */     if (c <= '್')
/* 1149 */       return true; 
/* 1151 */     if (c < 'ೕ')
/* 1151 */       return false; 
/* 1151 */     if (c <= 'ೖ')
/* 1151 */       return true; 
/* 1152 */     if (c < 'ം')
/* 1152 */       return false; 
/* 1152 */     if (c <= 'ഃ')
/* 1152 */       return true; 
/* 1153 */     if (c < 'ാ')
/* 1153 */       return false; 
/* 1153 */     if (c <= 'ൃ')
/* 1153 */       return true; 
/* 1155 */     if (c < 'െ')
/* 1155 */       return false; 
/* 1155 */     if (c <= 'ൈ')
/* 1155 */       return true; 
/* 1156 */     if (c < 'ൊ')
/* 1156 */       return false; 
/* 1156 */     if (c <= '്')
/* 1156 */       return true; 
/* 1157 */     if (c == 'ൗ')
/* 1157 */       return true; 
/* 1158 */     if (c == 'ั')
/* 1158 */       return true; 
/* 1160 */     if (c < 'ิ')
/* 1160 */       return false; 
/* 1160 */     if (c <= 'ฺ')
/* 1160 */       return true; 
/* 1161 */     if (c < '็')
/* 1161 */       return false; 
/* 1161 */     if (c <= '๎')
/* 1161 */       return true; 
/* 1162 */     if (c == 'ັ')
/* 1162 */       return true; 
/* 1163 */     if (c < 'ິ')
/* 1163 */       return false; 
/* 1163 */     if (c <= 'ູ')
/* 1163 */       return true; 
/* 1165 */     if (c < 'ົ')
/* 1165 */       return false; 
/* 1165 */     if (c <= 'ຼ')
/* 1165 */       return true; 
/* 1166 */     if (c < '່')
/* 1166 */       return false; 
/* 1166 */     if (c <= 'ໍ')
/* 1166 */       return true; 
/* 1167 */     if (c < '༘')
/* 1167 */       return false; 
/* 1167 */     if (c <= '༙')
/* 1167 */       return true; 
/* 1168 */     if (c == '༵')
/* 1168 */       return true; 
/* 1170 */     if (c == '༷')
/* 1170 */       return true; 
/* 1171 */     if (c == '༹')
/* 1171 */       return true; 
/* 1172 */     if (c == '༾')
/* 1172 */       return true; 
/* 1173 */     if (c == '༿')
/* 1173 */       return true; 
/* 1174 */     if (c < 'ཱ')
/* 1174 */       return false; 
/* 1174 */     if (c <= '྄')
/* 1174 */       return true; 
/* 1176 */     if (c < '྆')
/* 1176 */       return false; 
/* 1176 */     if (c <= 'ྋ')
/* 1176 */       return true; 
/* 1177 */     if (c < 'ྐ')
/* 1177 */       return false; 
/* 1177 */     if (c <= 'ྕ')
/* 1177 */       return true; 
/* 1178 */     if (c == 'ྗ')
/* 1178 */       return true; 
/* 1179 */     if (c < 'ྙ')
/* 1179 */       return false; 
/* 1179 */     if (c <= 'ྭ')
/* 1179 */       return true; 
/* 1181 */     if (c < 'ྱ')
/* 1181 */       return false; 
/* 1181 */     if (c <= 'ྷ')
/* 1181 */       return true; 
/* 1182 */     if (c == 'ྐྵ')
/* 1182 */       return true; 
/* 1183 */     if (c < '⃐')
/* 1183 */       return false; 
/* 1183 */     if (c <= '⃜')
/* 1183 */       return true; 
/* 1184 */     if (c == '⃡')
/* 1184 */       return true; 
/* 1186 */     if (c < '〪')
/* 1186 */       return false; 
/* 1186 */     if (c <= '〯')
/* 1186 */       return true; 
/* 1187 */     if (c == '゙')
/* 1187 */       return true; 
/* 1188 */     if (c == '゚')
/* 1188 */       return true; 
/* 1190 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isXMLExtender(char c) {
/* 1204 */     if (c < '¶')
/* 1204 */       return false; 
/* 1207 */     if (c == '·')
/* 1207 */       return true; 
/* 1208 */     if (c == 'ː')
/* 1208 */       return true; 
/* 1209 */     if (c == 'ˑ')
/* 1209 */       return true; 
/* 1210 */     if (c == '·')
/* 1210 */       return true; 
/* 1211 */     if (c == 'ـ')
/* 1211 */       return true; 
/* 1212 */     if (c == 'ๆ')
/* 1212 */       return true; 
/* 1213 */     if (c == 'ໆ')
/* 1213 */       return true; 
/* 1214 */     if (c == '々')
/* 1214 */       return true; 
/* 1216 */     if (c < '〱')
/* 1216 */       return false; 
/* 1216 */     if (c <= '〵')
/* 1216 */       return true; 
/* 1217 */     if (c < 'ゝ')
/* 1217 */       return false; 
/* 1217 */     if (c <= 'ゞ')
/* 1217 */       return true; 
/* 1218 */     if (c < 'ー')
/* 1218 */       return false; 
/* 1218 */     if (c <= 'ヾ')
/* 1218 */       return true; 
/* 1220 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isXMLDigit(char c) {
/* 1234 */     if (c < '0')
/* 1234 */       return false; 
/* 1234 */     if (c <= '9')
/* 1234 */       return true; 
/* 1235 */     if (c < '٠')
/* 1235 */       return false; 
/* 1235 */     if (c <= '٩')
/* 1235 */       return true; 
/* 1236 */     if (c < '۰')
/* 1236 */       return false; 
/* 1236 */     if (c <= '۹')
/* 1236 */       return true; 
/* 1237 */     if (c < '०')
/* 1237 */       return false; 
/* 1237 */     if (c <= '९')
/* 1237 */       return true; 
/* 1239 */     if (c < '০')
/* 1239 */       return false; 
/* 1239 */     if (c <= '৯')
/* 1239 */       return true; 
/* 1240 */     if (c < '੦')
/* 1240 */       return false; 
/* 1240 */     if (c <= '੯')
/* 1240 */       return true; 
/* 1241 */     if (c < '૦')
/* 1241 */       return false; 
/* 1241 */     if (c <= '૯')
/* 1241 */       return true; 
/* 1243 */     if (c < '୦')
/* 1243 */       return false; 
/* 1243 */     if (c <= '୯')
/* 1243 */       return true; 
/* 1244 */     if (c < '௧')
/* 1244 */       return false; 
/* 1244 */     if (c <= '௯')
/* 1244 */       return true; 
/* 1245 */     if (c < '౦')
/* 1245 */       return false; 
/* 1245 */     if (c <= '౯')
/* 1245 */       return true; 
/* 1247 */     if (c < '೦')
/* 1247 */       return false; 
/* 1247 */     if (c <= '೯')
/* 1247 */       return true; 
/* 1248 */     if (c < '൦')
/* 1248 */       return false; 
/* 1248 */     if (c <= '൯')
/* 1248 */       return true; 
/* 1249 */     if (c < '๐')
/* 1249 */       return false; 
/* 1249 */     if (c <= '๙')
/* 1249 */       return true; 
/* 1251 */     if (c < '໐')
/* 1251 */       return false; 
/* 1251 */     if (c <= '໙')
/* 1251 */       return true; 
/* 1252 */     if (c < '༠')
/* 1252 */       return false; 
/* 1252 */     if (c <= '༩')
/* 1252 */       return true; 
/* 1254 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isXMLWhitespace(char c) {
/* 1266 */     if (c == ' ' || c == '\n' || c == '\t' || c == '\r')
/* 1267 */       return true; 
/* 1269 */     return false;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\Verifier.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */