package org.cimmyt.cril.ibwb.provider.dao;

import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Udflds;

import org.hibernate.criterion.DetachedCriteria;

public class UdfldsDAO extends AbstractDAO<Udflds, Integer> {

    public UdfldsDAO() {
        super(Udflds.class);
    }

    @Override
    public Udflds prepareToCreate(Udflds udflds) {
        if (isLocal()) {
            udflds.setFldno(getNextMin());
        }
        if (isCentral()) {
            udflds.setFldno(getNextMax());
        }
        return udflds;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Udflds filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "fldno";
    }

    @Override
    public String getConsulta(Udflds filtro) {
        String query = "from Udflds as u";

        if (filtro.getGlobalsearch() == null) {
            setQuery("u.fldno", filtro.getFldno());
            setQuery("u.ftable", filtro.getFtable());
            setQuery("u.ftype", filtro.getFtype());
            setQuery("u.fcode", filtro.getFcode());
            setQuery("u.fname", filtro.getFname());
            setQuery("u.ffmt", filtro.getFfmt());
            setQuery("u.fdesc", filtro.getFdesc());
            setQuery("u.lfldno", filtro.getLfldno());
            setQuery("u.fuid", filtro.getFuid());
            setQuery("u.fdate", filtro.getFdate());
            setQuery("u.scaleid", filtro.getScaleid());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("u.fldno", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("u.ftable", filtro.getGlobalsearch());
                setQueryInTo("u.ftype", filtro.getGlobalsearch());
                setQueryInTo("u.fcode", filtro.getGlobalsearch());
                setQueryInTo("u.fname", filtro.getGlobalsearch());
                setQueryInTo("u.ffmt", filtro.getGlobalsearch());
                setQueryInTo("u.fdesc", filtro.getGlobalsearch());
                setQuery("u.lfldno", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.fuid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.fdate", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.scaleid", Integer.valueOf(filtro.getGlobalsearch()));
            } else {
                setQueryInTo("u.ftable", filtro.getGlobalsearch());
                setQueryInTo("u.ftype", filtro.getGlobalsearch());
                setQueryInTo("u.fcode", filtro.getGlobalsearch());
                setQueryInTo("u.fname", filtro.getGlobalsearch());
                setQueryInTo("u.ffmt", filtro.getGlobalsearch());
                setQueryInTo("u.fdesc", filtro.getGlobalsearch());
            }
        }
        return query;
    }
    
    /**
     * Gets a list of Udffields accoding to a table and a field related
     * @param tableName Table name
     * @param fieldName Field name 
     * @return List of Udflds objects
     */
    public List <Udflds> getUdfldsList(final String tableName, final String fieldName) {
        List <Udflds> udfldsList;
        
        String queryString = "from Udflds as u WHERE u.ftable = ? and u.ftype = ? ";
        Object[] parameters = new Object[2];
        parameters[0] = tableName;
        parameters[1] = fieldName;
        
        udfldsList = getHibernateTemplate().find(queryString, parameters);
        
        return udfldsList;
    }
}