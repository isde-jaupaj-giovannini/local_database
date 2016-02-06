package com.unitn.local_database;

import com.unitn.local_database.dao.Dao;
import com.unitn.local_database.entities.MeasureData;
import com.unitn.local_database.entities.UserData;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by erinda on 1/24/16.
 */
@WebService(endpointInterface = "com.unitn.local_database.LocalDB",
        serviceName = "LocalDatabase")
public class LocalDBImpl implements LocalDB {

    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException {
        String PROTOCOL = "http://";
        String HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        if (HOSTNAME.equals("127.0.0.1")) {
            HOSTNAME = "localhost";
        }
        String PORT = "6904";
        String BASE_URL = "/ws/localdb";

        if (String.valueOf(System.getenv("PORT")) != "null") {
            PORT = String.valueOf(System.getenv("PORT"));
        }

        String endpointUrl = PROTOCOL + HOSTNAME + ":" + PORT + BASE_URL;
        System.out.println("Starting " + LocalDB.class.getSimpleName() + "...");
        System.out.println("--> Published. Check out " + endpointUrl + "?wsdl");
        Endpoint.publish(endpointUrl, new LocalDBImpl());
    }

    @Override
    public String getDescription() {
        return LocalDB.class.getSimpleName();
    }

    @Override
    public void saveData(MeasureData md) {
        Dao.saveEntity(md);
    }

    @Override
    public List<MeasureData> getLatestData(int id, int limit) {
        EntityManager em = Dao.instance.createEntityManager();

        List<MeasureData> ls = em
                .createQuery(
                        "SELECT l from MeasureData l "
                                + "WHERE l.idTelegram = :id")
                .setParameter("id", id)
                .getResultList();

        Dao.instance.closeConnections(em);
        return ls;
    }


    @Override
    public boolean userExists(int id){
        if(getUser(id) != null){
            return true;
        }

        return false;
    }

    @Override
    public Integer totalSteps(int telegramId, long t1, long t2) {
        EntityManager em = Dao.instance.createEntityManager();

        List<Long> sum = em
                .createQuery(
                        "SELECT SUM(l.steps) from MeasureData l "
                                + " WHERE l.idTelegram = :id AND l.timestamp >  :t1 AND l.timestamp < :t2")
                .setParameter("id", telegramId)
                .setParameter("t1", t1)
                .setParameter("t2", t2)
                .getResultList();

        Dao.instance.closeConnections(em);


        return ( sum.get(0) != null ? sum.get(0).intValue() : 0 );
    }

    @Override
    public UserData getUser(int id){
        EntityManager em = Dao.instance.createEntityManager();
        UserData p = em.find(UserData.class, id);
        Dao.instance.closeConnections(em);

        return p;
    }


    @Override
    public void createUser(UserData user){
        Dao.saveEntity(user);
    }

}
