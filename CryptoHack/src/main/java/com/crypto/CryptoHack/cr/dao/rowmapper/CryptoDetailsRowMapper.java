package com.crypto.CryptoHack.cr.dao.rowmapper;

import com.crypto.CryptoHack.backjpa.domain.SellingDataStore;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CryptoDetailsRowMapper implements RowMapper<SellingDataStore> {


    //id, market_cap, price, to_day, volume)
    @Override
    public SellingDataStore mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id") ;
        String marketCap = rs.getString("marketCap");
        Date instant = new Date(rs.getDate(3).getTime());
        String volume = rs.getString(3);

        return new SellingDataStore(id,volume,marketCap,marketCap,instant);
    }
}
