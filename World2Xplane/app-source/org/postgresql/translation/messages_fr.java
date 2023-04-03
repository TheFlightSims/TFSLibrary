/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_fr extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: head-fr\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2007-07-27 04:36-0600\nPO-Revision-Date: 2007-07-27 12:27+0200\nLast-Translator: \nLanguage-Team:  <en@li.org>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Generator: KBabel 1.11.4\nPlural-Forms:  nplurals=2; plural=(n > 1);\n");
/*   8 */     hashtable.put("Error loading default settings from driverconfig.properties", "Erreur de chargement des valeurs par défaut depuis driverconfig.properties");
/*   9 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Quelque chose d''inhabituel a provoqué l''échec du pilote. Veuillez faire un rapport sur cette erreur.");
/*  10 */     hashtable.put("Connection attempt timed out.", "La tentative de connexion a échoué dans le délai imparti.");
/*  11 */     hashtable.put("Interrupted while attempting to connect.", "Interrompu pendant l''établissement de la connexion.");
/*  12 */     hashtable.put("Method {0} is not yet implemented.", "La fonction {0} n''est pas encore implémentée.");
/*  13 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "Aucune connexion n''a pu être établie en utilisant le protocole demandé {0}. ");
/*  14 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Fin prématurée du flux en entrée, {0} octets attendus, mais seulement {1} lus.");
/*  15 */     hashtable.put("Expected an EOF from server, got: {0}", "Attendait une fin de fichier du serveur, reçu: {0}");
/*  16 */     hashtable.put("Illegal UTF-8 sequence: byte {0} of {1} byte sequence is not 10xxxxxx: {2}", "Séquence UTF-8 illégale: l''octet {0} de la séquence d''octet {1} n''est pas 10xxxxxx: {2}");
/*  17 */     hashtable.put("Illegal UTF-8 sequence: {0} bytes used to encode a {1} byte value: {2}", "Séquence UTF-8 illégale: {0} octets utilisé pour encoder une valeur à {1} octets: {2}");
/*  18 */     hashtable.put("Illegal UTF-8 sequence: initial byte is {0}: {1}", "Séquence UTF-8 illégale: le premier octet est {0}: {1}");
/*  19 */     hashtable.put("Illegal UTF-8 sequence: final value is out of range: {0}", "Séquence UTF-8 illégale: la valeur finale est en dehors des limites: {0}");
/*  20 */     hashtable.put("Illegal UTF-8 sequence: final value is a surrogate value: {0}", "Séquence UTF-8 illégale: la valeur finale est une valeur de remplacement: {0}");
/*  21 */     hashtable.put("Zero bytes may not occur in string parameters.", "Zéro octets ne devrait pas se produire dans les paramètres de type chaîne de caractères.");
/*  22 */     hashtable.put("Zero bytes may not occur in identifiers.", "Des octects à 0 ne devraient pas apparaître dans les identifiants.");
/*  23 */     hashtable.put("Cannot convert an instance of {0} to type {1}", "Impossible de convertir une instance de type {0} vers le type {1}");
/*  24 */     hashtable.put("The driver does not support SSL.", "Ce pilote ne supporte pas SSL.");
/*  25 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Connexion refusée. Vérifiez que le nom de machine et le port sont corrects et que postmaster accepte les connexions TCP/IP.");
/*  26 */     hashtable.put("The connection attempt failed.", "La tentative de connexion a échoué.");
/*  27 */     hashtable.put("The server does not support SSL.", "Le serveur ne supporte pas SSL.");
/*  28 */     hashtable.put("An error occured while setting up the SSL connection.", "Une erreur s''est produite pendant l''établissement de la connexion SSL.");
/*  29 */     hashtable.put("Connection rejected: {0}.", "Connexion rejetée : {0}.");
/*  30 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "Le serveur a demandé une authentification par mots de passe, mais aucun mot de passe n''a été fourni.");
/*  31 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "Le type d''authentification {0} n''est pas supporté. Vérifiez que vous avez configuré le fichier pg_hba.conf pour inclure l''adresse IP du client ou le sous-réseau et qu''il utilise un schéma d''authentification supporté par le pilote.");
/*  32 */     hashtable.put("Protocol error.  Session setup failed.", "Erreur de protocole. Ouverture de la session en échec.");
/*  33 */     hashtable.put("Backend start-up failed: {0}.", "Démarrage du serveur en échec : {0}.");
/*  34 */     hashtable.put("An unexpected result was returned by a query.", "Un résultat inattendu a été retourné par une requête.");
/*  35 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "L''indice de la colonne est hors limite : {0}, nombre de colonnes : {1}.");
/*  36 */     hashtable.put("No value specified for parameter {0}.", "Pas de valeur spécifiée pour le paramètre {0}.");
/*  37 */     hashtable.put("Expected command status BEGIN, got {0}.", "Attendait le statut de commande BEGIN, obtenu {0}.");
/*  38 */     hashtable.put("Unexpected command status: {0}.", "Statut de commande inattendu : {0}.");
/*  39 */     hashtable.put("An I/O error occured while sending to the backend.", "Une erreur d''entrée/sortie a eu lieu lors d''envoi vers le serveur.");
/*  40 */     hashtable.put("Unknown Response Type {0}.", "Type de réponse inconnu {0}.");
/*  41 */     hashtable.put("Ran out of memory retrieving query results.", "Ai manqué de mémoire en récupérant les résultats de la requête.");
/*  42 */     hashtable.put("Unable to interpret the update count in command completion tag: {0}.", "Incapable d''interpréter le nombre de mise à jour dans la balise de complétion de commande : {0}.");
/*  43 */     hashtable.put("Unable to bind parameter values for statement.", "Incapable de lier les valeurs des paramètres pour la commande.");
/*  44 */     hashtable.put("Bind message length {0} too long.  This can be caused by very large or incorrect length specifications on InputStream parameters.", "La longueur du message de liaison {0} est trop grande. Cela peut être causé par des spécification de longueur très grandes ou incorrectes pour les paramètres de type InputStream.");
/*  45 */     hashtable.put("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UNICODE for correct operation.", "Le paramètre client_encoding du serveur a été changé pour {0}. Le pilote JDBC nécessite l''affectation de la valeur UNICODE à client_encoding pour un fonctionnement correct.");
/*  46 */     hashtable.put("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", "Le paramètre DateStyle du serveur a été changé pour {0}. Le pilote JDBC nécessite que DateStyle commence par ISO pour un fonctionnement correct.");
/*  47 */     hashtable.put("The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.", "Le paramètre serveur standard_conforming_strings a pour valeur {0}. Le driver JDBC attend on ou off.");
/*  48 */     hashtable.put("The driver currently does not support COPY operations.", "Le pilote ne supporte pas actuellement les opérations COPY.");
/*  49 */     hashtable.put("This PooledConnection has already been closed.", "Cette PooledConnection a déjà été fermée.");
/*  50 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "La connexion a été fermée automatiquement car une nouvelle connexion a été ouverte pour la même PooledConnection ou la PooledConnection a été fermée.");
/*  51 */     hashtable.put("Connection has been closed.", "La connexion a été fermée.");
/*  52 */     hashtable.put("Statement has been closed.", "Statement a été fermé.");
/*  53 */     hashtable.put("DataSource has been closed.", "DataSource a été fermée.");
/*  54 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Appel Fastpath {0} - Aucun résultat n''a été retourné et nous attendions un entier.");
/*  55 */     hashtable.put("The fastpath function {0} is unknown.", "La fonction fastpath {0} est inconnue.");
/*  56 */     hashtable.put("Conversion to type {0} failed: {1}.", "La conversion vers le type {0} a échoué : {1}.");
/*  57 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "Impossible de dire si path est fermé ou ouvert : {0}.");
/*  58 */     hashtable.put("The array index is out of range: {0}", "L''indice du tableau est hors limites : {0}");
/*  59 */     hashtable.put("Multi-dimensional arrays are currently not supported.", "Les tableaux à plusieurs dimensions ne sont pas supportés pour le moment.");
/*  60 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "L''indice du tableau est hors limites : {0}, nombre d''éléments : {1}.");
/*  61 */     hashtable.put("Truncation of large objects is only implemented in 8.3 and later servers.", "Le troncage des large objects n''est implémenté que dans les serveurs 8.3 et supérieurs.");
/*  62 */     hashtable.put("LOB positioning offsets start at 1.", "Les décalages de position des LOB commencent à 1.");
/*  63 */     hashtable.put("PostgreSQL LOBs can only index to: {0}", "Les LOB PostgreSQL peuvent seulement s''indicer à: {0}");
/*  64 */     hashtable.put("free() was called on this LOB previously", "free() a été appelée auparavant sur ce LOB");
/*  65 */     hashtable.put("Unsupported value for stringtype parameter: {0}", "Valeur non supportée pour les paramètre de type chaîne de caractères : {0}");
/*  66 */     hashtable.put("No results were returned by the query.", "Aucun résultat retourné par la requête.");
/*  67 */     hashtable.put("A result was returned when none was expected.", "Un résultat a été retourné alors qu''aucun n''était attendu.");
/*  68 */     hashtable.put("Failed to create object for: {0}.", "Échec à la création de l''objet pour : {0}.");
/*  69 */     hashtable.put("Unable to load the class {0} responsible for the datatype {1}", "Incapable de charger la classe {0} responsable du type de données {1}");
/*  70 */     hashtable.put("Cannot change transaction read-only property in the middle of a transaction.", "Impossible de changer la propriété read-only d''une transaction au milieu d''une transaction.");
/*  71 */     hashtable.put("Cannot change transaction isolation level in the middle of a transaction.", "Impossible de changer le niveau d''isolation des transactions au milieu d''une transaction.");
/*  72 */     hashtable.put("Transaction isolation level {0} not supported.", "Le niveau d''isolation de transaction {0} n''est pas supporté.");
/*  73 */     hashtable.put("Finalizing a Connection that was never closed:", "Destruction d''une connection qui n''a jamais été fermée:");
/*  74 */     hashtable.put("Unable to translate data into the desired encoding.", "Impossible de traduire les données dans l''encodage désiré.");
/*  75 */     hashtable.put("Unable to determine a value for MaxIndexKeys due to missing system catalog data.", "Incapable de déterminer la valeur de MaxIndexKeys en raison de données manquante dans lecatalogue système.");
/*  76 */     hashtable.put("Unable to find name datatype in the system catalogs.", "Incapable de trouver le type de donnée name dans les catalogues systèmes.");
/*  77 */     hashtable.put("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY.", "L''opération nécessite un scrollable ResultSet, mais ce ResultSet est FORWARD_ONLY.");
/*  78 */     hashtable.put("Unexpected error while decoding character data from a large object.", "Erreur inattendue pendant le décodage des données caractères pour un large object.");
/*  79 */     hashtable.put("Can''t use relative move methods while on the insert row.", "Impossible d''utiliser les fonctions de déplacement relatif pendant l''insertion d''une ligne.");
/*  80 */     hashtable.put("Invalid fetch direction constant: {0}.", "Constante de direction pour la récupération invalide : {0}.");
/*  81 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "Impossible d''appeler cancelRowUpdates() pendant l''insertion d''une ligne.");
/*  82 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "Impossible d''appeler deleteRow() pendant l''insertion d''une ligne.");
/*  83 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "Actuellement positionné avant le début du ResultSet. Vous ne pouvez pas appeler deleteRow() ici.");
/*  84 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "Actuellement positionné après la fin du ResultSet. Vous ne pouvez pas appeler deleteRow() ici.");
/*  85 */     hashtable.put("There are no rows in this ResultSet.", "Il n''y pas pas de lignes dans ce ResultSet.");
/*  86 */     hashtable.put("Not on the insert row.", "Pas sur la ligne en insertion.");
/*  87 */     hashtable.put("You must specify at least one column value to insert a row.", "Vous devez spécifier au moins une valeur de colonne pour insérer une ligne.");
/*  88 */     hashtable.put("The JVM claims not to support the encoding: {0}", "La JVM prétend ne pas supporter l''encodage: {0}");
/*  89 */     hashtable.put("Provided InputStream failed.", "L''InputStream fourni a échoué.");
/*  90 */     hashtable.put("Provided Reader failed.", "Le Reader fourni a échoué.");
/*  91 */     hashtable.put("Can''t refresh the insert row.", "Impossible de rafraîchir la ligne insérée.");
/*  92 */     hashtable.put("Cannot call updateRow() when on the insert row.", "Impossible d''appeler updateRow() tant que l''on est sur la ligne insérée.");
/*  93 */     hashtable.put("Cannot update the ResultSet because it is either before the start or after the end of the results.", "Impossible de mettre à jour le ResultSet car c''est soit avant le début ou après la fin des résultats.");
/*  94 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "Les ResultSets avec la concurrence CONCUR_READ_ONLY ne peuvent être mis à jour.");
/*  95 */     hashtable.put("No primary key found for table {0}.", "Pas de clé primaire trouvée pour la table {0}.");
/*  96 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "Fetch size doit être une valeur supérieur ou égal à 0.");
/*  97 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Des données de caractères invalides ont été trouvées. C''est probablement causé par le stockage de caractères invalides pour le jeu de caractères de création de la base. L''exemple le plus courant est le stockage de données 8bit dans une base SQL_ASCII.");
/*  98 */     hashtable.put("Bad value for type {0} : {1}", "Mauvaise valeur pour le type {0} : {1}");
/*  99 */     hashtable.put("The column name {0} was not found in this ResultSet.", "Le nom de colonne {0} n''a pas été trouvé dans ce ResultSet.");
/* 100 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "Le ResultSet n''est pas modifiable. La requête qui a généré ce résultat doit sélectionner seulement une table, et doit sélectionner toutes les clés primaires de cette table. Voir la spécification de l''API JDBC 2.1, section 5.6 pour plus de détails.");
/* 101 */     hashtable.put("This ResultSet is closed.", "Ce ResultSet est fermé.");
/* 102 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "Le ResultSet n''est pas positionné correctement, vous devez peut-être appeler next().");
/* 103 */     hashtable.put("Can''t use query methods that take a query string on a PreparedStatement.", "Impossible d''utiliser les fonctions de requête qui utilisent une chaîne de caractères sur un PreparedStatement.");
/* 104 */     hashtable.put("Multiple ResultSets were returned by the query.", "Plusieurs ResultSets ont été retournés par la requête.");
/* 105 */     hashtable.put("A CallableStatement was executed with nothing returned.", "Un CallableStatement a été exécuté mais n''a rien retourné.");
/* 106 */     hashtable.put("A CallableStatement was excecuted with an invalid number of parameters", "Un CallableStatement a été exécuté avec un nombre de paramètres incorrect");
/* 107 */     hashtable.put("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", "Une fonction CallableStatement a été exécutée et le paramètre en sortie {0} était du type {1} alors que le type {2} était prévu.");
/* 108 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "Le nombre maximum de lignes doit être une valeur supérieure ou égale à 0.");
/* 109 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "Query timeout doit être une valeur supérieure ou égale à 0.");
/* 110 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "La taille maximum des champs doit être une valeur supérieure ou égale à 0.");
/* 111 */     hashtable.put("Unknown Types value.", "Valeur de Types inconnue.");
/* 112 */     hashtable.put("Invalid stream length {0}.", "Longueur de flux invalide {0}.");
/* 113 */     hashtable.put("The JVM claims not to support the {0} encoding.", "La JVM prétend ne pas supporter l''encodage {0}.");
/* 114 */     hashtable.put("Unknown type {0}.", "Type inconnu : {0}.");
/* 115 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "Impossible de convertir une instance de {0} vers le type {1}");
/* 116 */     hashtable.put("Unsupported Types value: {0}", "Valeur de type non supportée : {0}");
/* 117 */     hashtable.put("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", "Impossible de déduire le type SQL à utiliser pour une instance de {0}. Utilisez setObject() avec une valeur de type explicite pour spécifier le type à utiliser.");
/* 118 */     hashtable.put("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one.", "Cette requête ne déclare pas de paramètre OUT. Utilisez '{' ?= call ... '}' pour en déclarer un.");
/* 119 */     hashtable.put("wasNull cannot be call before fetching a result.", "wasNull ne peut pas être appelé avant la récupération d''un résultat.");
/* 120 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "Syntaxe de fonction ou d''échappement de procédure malformée à l''indice {0}.");
/* 121 */     hashtable.put("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", "Un paramètre de type {0} a été enregistré, mais un appel à get{1} (sqltype={2}) a été fait.");
/* 122 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "Un CallableStatement a été déclaré, mais aucun appel à registerOutParameter(1, <un type>) n''a été fait.");
/* 123 */     hashtable.put("No function outputs were registered.", "Aucune fonction outputs n''a été enregistrée.");
/* 124 */     hashtable.put("Results cannot be retrieved from a CallableStatement before it is executed.", "Les résultats ne peuvent être récupérés à partir d''un CallableStatement avant qu''il ne soit exécuté.");
/* 125 */     hashtable.put("This statement has been closed.", "Ce statement a été fermé.");
/* 126 */     hashtable.put("Too many update results were returned.", "Trop de résultats de mise à jour ont été retournés.");
/* 127 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "L''élément du batch {0} {1} a été annulé. Appeler getNextException pour en connaître la cause.");
/* 128 */     hashtable.put("Unexpected error writing large object to database.", "Erreur inattendue pendant l''écriture de large object dans la base.");
/* 129 */     hashtable.put("{0} function takes one and only one argument.", "La fonction {0} n''accepte qu''un et un seul argument.");
/* 130 */     hashtable.put("{0} function takes two and only two arguments.", "La fonction {0} n''accepte que deux et seulement deux arguments.");
/* 131 */     hashtable.put("{0} function takes four and only four argument.", "La fonction {0} n''accepte que quatre et seulement quatre arguments.");
/* 132 */     hashtable.put("{0} function takes two or three arguments.", "La fonction {0} n''accepte que deux ou trois arguments.");
/* 133 */     hashtable.put("{0} function doesn''t take any argument.", "La fonction {0} n''accepte aucun argument.");
/* 134 */     hashtable.put("{0} function takes three and only three arguments.", "La fonction {0} n''accepte que trois et seulement trois arguments.");
/* 135 */     hashtable.put("Interval {0} not yet implemented", "L''interval {0} n''est pas encore implémenté");
/* 136 */     hashtable.put("Infinite value found for timestamp/date. This cannot be represented as time.", "Valeur infinie trouvée pour une date/timestamp. Cette valeur ne peut être représenté comme une valeur temporelle.");
/* 137 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "La classe {0} n''implémente pas org.postgresql.util.PGobject.");
/* 138 */     hashtable.put("Unknown ResultSet holdability setting: {0}.", "Paramètre holdability du ResultSet inconnu : {0}.");
/* 139 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "Les serveurs de version antérieure à 8.0 ne supportent pas les savepoints.");
/* 140 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "Impossible d''établir un savepoint en mode auto-commit.");
/* 141 */     hashtable.put("Returning autogenerated keys is not supported.", "Le renvoi des clés automatiquement générées n''est pas supporté.");
/* 142 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "L''indice du paramètre est hors limites : {0}, nombre de paramètres : {1}.");
/* 143 */     hashtable.put("Cannot reference a savepoint after it has been released.", "Impossible de référencer un savepoint après qu''il ait été libéré.");
/* 144 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "Impossible de retrouver l''identifiant d''un savepoint nommé.");
/* 145 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "Impossible de retrouver le nom d''un savepoint sans nom.");
/* 146 */     hashtable.put("Failed to initialize LargeObject API", "Échec à l''initialisation de l''API LargeObject");
/* 147 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "Les Large Objects ne devraient pas être utilisés en mode auto-commit.");
/* 148 */     hashtable.put("The SSLSocketFactory class provided {0} could not be instantiated.", "La classe SSLSocketFactory fournie {0} n''a pas pu être instanciée.");
/* 149 */     hashtable.put("Conversion of interval failed", "La conversion de l''intervalle a échoué");
/* 150 */     hashtable.put("Conversion of money failed.", "La conversion de money a échoué.");
/* 151 */     hashtable.put("Exception: {0}", "Exception : {0}");
/* 152 */     hashtable.put("Stack Trace:", "Pile d''appel :");
/* 153 */     hashtable.put("End of Stack Trace", "Fin de la pile d''appel");
/* 154 */     hashtable.put("Exception generating stacktrace for: {0} encountered: {1}", "Exception en générant la pile d''appel pour : {0} erreur rencontrée : {1}");
/* 155 */     hashtable.put("Detail: {0}", "Détail : {0}");
/* 156 */     hashtable.put("Hint: {0}", "Indice : {0}");
/* 157 */     hashtable.put("Position: {0}", "Position : {0}");
/* 158 */     hashtable.put("Where: {0}", "Où : {0}");
/* 159 */     hashtable.put("Internal Query: {0}", "Requête interne: {0}");
/* 160 */     hashtable.put("Internal Position: {0}", "Position interne : {0}");
/* 161 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Localisation : Fichier : {0}, Routine : {1}, Ligne : {2}");
/* 162 */     hashtable.put("Server SQLState: {0}", "SQLState serveur : {0}");
/* 163 */     hashtable.put("Invalid flags", "Drapeaux invalides");
/* 164 */     hashtable.put("xid must not be null", "xid ne doit pas être nul");
/* 165 */     hashtable.put("Connection is busy with another transaction", "La connection est occupée avec une autre transaction");
/* 166 */     hashtable.put("suspend/resume not implemented", "suspend/resume pas implémenté");
/* 167 */     hashtable.put("Transaction interleaving not implemented", "L''entrelacement des transactions n''est pas implémenté");
/* 168 */     hashtable.put("Error disabling autocommit", "Erreur en désactivant autocommit");
/* 169 */     hashtable.put("tried to call end without corresponding start call", "tentative d''appel de fin sans l''appel start correspondant");
/* 170 */     hashtable.put("Not implemented: Prepare must be issued using the same connection that started the transaction", "Pas implémenté: Prepare doit être envoyé sur la même connection qui a démarré la transaction");
/* 171 */     hashtable.put("Prepare called before end", "Préparation appelée avant la fin");
/* 172 */     hashtable.put("Server versions prior to 8.1 do not support two-phase commit.", "Les serveurs de versions antérieures à 8.1 ne supportent pas le commit à deux phases.");
/* 173 */     hashtable.put("Error preparing transaction", "Erreur en préparant la transaction");
/* 174 */     hashtable.put("Invalid flag", "Drapeau invalide");
/* 175 */     hashtable.put("Error during recover", "Erreur durant la restauration");
/* 176 */     hashtable.put("Error rolling back prepared transaction", "Erreur en annulant une transaction préparée");
/* 177 */     hashtable.put("Not implemented: one-phase commit must be issued using the same connection that was used to start it", "Pas implémenté: le commit à une phase doit avoir lieu en utilisant la même connection que celle où il a commencé");
/* 178 */     hashtable.put("commit called before end", "Commit appelé avant la fin");
/* 179 */     hashtable.put("Error during one-phase commit", "Erreur pendant le commit à une phase");
/* 180 */     hashtable.put("Not implemented: 2nd phase commit must be issued using an idle connection", "Pas implémenté: le commit à deux phase doit être envoyé sur une connection inutilisée");
/* 181 */     hashtable.put("Heuristic commit/rollback not supported", "Heuristic commit/rollback non supporté");
/* 182 */     table = hashtable;
/*     */   }
/*     */   
/*     */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 185 */     return table.get(paramString);
/*     */   }
/*     */   
/*     */   public Enumeration getKeys() {
/* 188 */     return table.keys();
/*     */   }
/*     */   
/*     */   public ResourceBundle getParent() {
/* 191 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_fr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */