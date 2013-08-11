package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.ctakes.core.resource.JdbcConnectionResourceImpl;
import org.apache.ctakes.dictionary.lookup.ae.LookupConsumer;
import org.apache.uima.UimaContext;

/**
 * Implementation that takes UMLS dictionary lookup hits and stores as NamedEntity objects only the
 * ones that have a SNOMED synonym, by looking in a database for SNOMED codes that map to the
 * identified CUI.
 * 
 * @author Ran Zhao
 */
public class UmlsToSnomedDbConsumerImpl extends UmlsToSnomedConsumerImpl implements LookupConsumer {

  private final String DB_CONN_RESRC_KEY_PRP_KEY = "dbConnExtResrcKey";

  private final String MAP_PREP_STMT_PRP_KEY = "mapPrepStmt";

  // ohnlp-Bugs-3296301 fix limited search results to fixed 100 records.
  // Added 'MaxListSize'
  private static int iv_maxListSize;

  private PreparedStatement mapPrepStmt;

  public UmlsToSnomedDbConsumerImpl(UimaContext aCtx, Properties properties, int maxListSize)
          throws Exception {
    super(aCtx, properties);
    iv_maxListSize = maxListSize;
    String resrcName = props.getProperty(DB_CONN_RESRC_KEY_PRP_KEY);
    // JdbcConnectionResource resrc = (JdbcConnectionResource) aCtx.getResourceObject(resrcName);

    JdbcConnectionResourceImpl resrc = new JdbcConnectionResourceImpl();
    resrc.initializeExternalResource("org.hsqldb.jdbcDriver",
            "jdbc:hsqldb:res:org/apache/ctakes/dictionary/lookup/umls2011ab/umls", "SA", "", true,
            "");

    String prepStmtSql = props.getProperty(MAP_PREP_STMT_PRP_KEY);
    Connection conn = resrc.getConnection();
    mapPrepStmt = conn.prepareStatement(prepStmtSql);

  }

  public UmlsToSnomedDbConsumerImpl(UimaContext aCtx, Properties properties) throws Exception {
    super(aCtx, properties);
    String resrcName = props.getProperty(DB_CONN_RESRC_KEY_PRP_KEY);
    // JdbcConnectionResource resrc = (JdbcConnectionResource) aCtx.getResourceObject(resrcName);
    JdbcConnectionResourceImpl resrc = new JdbcConnectionResourceImpl();
    resrc.initializeExternalResource("org.hsqldb.jdbcDriver",
            "jdbc:hsqldb:res:org/apache/ctakes/dictionary/lookup/umls2011ab/umls", "SA", "", true,
            null);
    String prepStmtSql = props.getProperty(MAP_PREP_STMT_PRP_KEY);
    Connection conn = resrc.getConnection();
    mapPrepStmt = conn.prepareStatement(prepStmtSql);

  }

  /**
   * Queries the given UMLS CUI against the DB. Returns a set of SNOMED codes.
   * 
   * @param umlsCode
   * @return
   * @throws SQLException
   */
  protected Set getSnomedCodes(String umlsCode) throws SQLException {
    Set codeSet = new HashSet();
    mapPrepStmt.setString(1, umlsCode);
    ResultSet rs = mapPrepStmt.executeQuery();
    while (rs.next()) {
      String snomedCode = rs.getString(1).trim();
      codeSet.add(snomedCode);
    }

    return codeSet;

  }

}