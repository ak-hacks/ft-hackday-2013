package com.ft.hack.dynamite.db;

import com.datastax.driver.core.*;

/**
 * User: anuragkapur
 * Date: 20/05/2013 16:54
 */

public class SimpleClient {

    private static Cluster cluster;
    private static Session session;

    public SimpleClient() {
        //TODO: read from property file
        String node = "107.22.151.207";
        cluster = Cluster.builder().addContactPoint(node).build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",metadata.getClusterName());

        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }

        session = cluster.connect();
    }

    public static Session getSession() {
        if(session == null) {
            SimpleClient client = new SimpleClient();
        }

        return session;
    }

    //TODO: when should the cluster connection be closed?
    public void close() {
        cluster.shutdown();
    }

    public static void main(String args[]) {
        SimpleClient client = new SimpleClient();
/*        ResultSet results = SimpleClient.getSession().execute("SELECT * FROM ftdynamite.user_activity_financialtimes");
        for(Row row : results) {
            System.out.println(row.getLong("count"));
        }*/
    }
}
