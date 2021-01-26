package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.LogDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class LogDAOImpl implements LogDAO {
    private static final String INSERT_PROFILE = "INSERT INTO LOG (USERNAME, TIME, ACTION, DESCRIPTION) VALUES (?, ?, ?, ?)";

    @Override
    public void insertLogInfo(Log log) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_PROFILE);
            pStatement.setString(1, log.getUsername());
            pStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()) );
            pStatement.setString(3, log.getActionString());
            pStatement.setString(4, log.getDescription());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
