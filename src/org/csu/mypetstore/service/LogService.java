package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.persistence.LogDAO;
import org.csu.mypetstore.persistence.impl.LogDAOImpl;

public class LogService {

    private LogDAO logDAO;

    public LogService() {
        logDAO = new LogDAOImpl();
    }

    public void insertLogInfo(Log log){
        logDAO.insertLogInfo(log);
    }

}
