package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Log;

public interface LogDAO {
    void insertLogInfo(Log log);
}
