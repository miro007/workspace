package pl.jett.dao;

public interface DaoFactory {

    <T> BaseDaoImpl<T> getDao (Class<T> type);

}
