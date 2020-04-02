package by.itacademy.boldysh.web.controller;

//Обработка ошибок слой Dao
public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException() {
    }
}
