package main

import org.h2.jdbcx.JdbcDataSource
import java.sql.Connection

object DataDefiner {
    val connection: Connection = JdbcDataSource().also {
        it.setURL("jdbc:h2:./dbfan/data/test")
    }.getConnection("ivan", "ivan")
}