package com.unitn.local_database;

import com.unitn.local_database.entities.MeasureData;
import com.unitn.local_database.entities.UserData;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by erinda on 1/24/16.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface LocalDB {
    @WebMethod()
    @WebResult()
    String getDescription();

    @WebMethod()
    @WebResult()
    void saveData(MeasureData md);

    @WebMethod()
    @WebResult()
    List<MeasureData> getLatestData(int telegramId, long timestamp);

    @WebMethod()
    @WebResult()
    boolean userExists(int id);

    @WebMethod()
    @WebResult()
    UserData getUser(int id);

    @WebMethod()
    @WebResult()
    void createUser(UserData user);

    @WebMethod()
    @WebResult()
    Integer totalSteps(int telegramId, long t1, long t2);
}
