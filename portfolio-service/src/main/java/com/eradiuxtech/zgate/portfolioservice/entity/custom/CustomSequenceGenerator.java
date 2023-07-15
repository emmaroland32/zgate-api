package com.eradiuxtech.zgate.portfolioservice.entity.custom;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.SelectionQuery;

import java.io.Serializable;

public class CustomSequenceGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        String prefix = "IH";
        String suffix = "";
        try {
            SelectionQuery<?> resultSet = session.createSelectionQuery("select count(id) from customers");
                int id = (int) resultSet.uniqueResult() + 1;
                suffix = String.format(Integer.toString(id), "%08d");

            System.out.println("Got here" +  suffix);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefix + suffix;
    }

}


