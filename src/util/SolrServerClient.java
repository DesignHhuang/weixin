package util;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class SolrServerClient {


    private String SOLR_URL = "http://192.168.200.100:8983/solr/%s";
    private  Map<String, HttpSolrServer> servers = null;
    private volatile static SolrServerClient solrServiceClient = null;

    private SolrServerClient() {
        servers = new HashMap<String, HttpSolrServer>();
    }
  
    public static SolrServerClient getInstance() {
        if (solrServiceClient == null) {
            synchronized (SolrServerClient.class) {
                if (solrServiceClient == null) {
                    solrServiceClient = new SolrServerClient();
                }
            }
        }
        return solrServiceClient;
    }

    public HttpSolrServer getServer(String coreName) {
    	HttpSolrServer server;
    	if (servers.containsKey(coreName)){
    		server = servers.get(coreName);
    	}else{
            server = new HttpSolrServer(String.format(SOLR_URL, coreName));
            server.setConnectionTimeout(3000);
            server.setDefaultMaxConnectionsPerHost(100);
            server.setMaxTotalConnections(100);
            servers.put(coreName, server);
        }
        return server;
    }
}
