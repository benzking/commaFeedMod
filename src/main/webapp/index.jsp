<html>
<%@ page language="java" import="com.mod.backend.feed.FeedFecther"%>
<%@ page language="java" import="com.mod.backend.feed.OPMLImporter"%>
<%@ page language="java" import="org.joda.time.DateTime"%>
<%@ page language="java" import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" import="com.mod.WebContent"%>
<body>
<%
    ApplicationContext ctx;
    ctx = WebContent.getApplicationContext();
     FeedFecther fecther= (FeedFecther) ctx.getBean(FeedFecther.class);
     fecther.fetch("http://www.dgtle.com/rss/dgtle.xml",null,null);
  // OPMLImporter OPMLImporter= (OPMLImporter) ctx.getBean(OPMLImporter.class);
  // User user=new User();
  // user.setId("001");
  // String opml="";
  // OPMLImporter.importOpml(user,"");

%>
<h2>Hello World!213123</h2>
</body>
</html>
