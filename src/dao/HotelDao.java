package dao;

import entities.Hotel;
import entities.Room;
import util.TextUtil;

import java.util.ArrayList;
import java.util.List;


public class HotelDao implements Dao<Hotel> {

    @Override
    public void add(Hotel hotel) {
        //TODO Привести все поля обьекта Хотел, что передается в метод к видку как в example
        //и потом эту строку передать в метод writeToFile
        String example = "1:Hilton:Kyiv:3";
        TextUtil.writeToFile("Hotel", example);
    }

    @Override
    public void update(Hotel hotel) {
        //TODO Привести все поля обьекта Хотел, что передается в метод к видку как в example
        //и потом эту строку передать в метод writeToFile
        String example = "1:Hilton:Kyiv:3";
        TextUtil.updateInFile("Hotel", example);
    }

    @Override
    public void delete(long id) {
        //TODO Поле id поменять тип на int во всех сущностях и классах где оно используется
        // разницы никакой, просто быстрее все вводить и считывать :)
        TextUtil.deleteFromFile("Hotel", id);
    }

    @Override
    public Hotel findByID(long id) {
        List<Hotel> hotels = getAll();
        //TODO Перебрать все записи АрейЛиста, сравнивая айдишник, и вывести тот у кого совпадает.

        return null;
    }

    @Override
    public List<Hotel> getAll() {
        ArrayList<String> hotels = TextUtil.readFromFile("Hotel");
        //TODO Перебирая каждый элемент АрейЛиста расспарсить его с помощью метода split() класса String
        //на массив стрингов. Потом создать обьект Хотел передавая в конструктор элементы массива,
        //зная, что нулевой элемент это айди, первый это название отела и т.д.
        //Все созданные обьекты записать в новый АрейЛист с отелями и вывести.

        return null;
    }

    public List<Hotel> findByName(String name) {
        List<Hotel> hotels = getAll();
        //TODO Перебрать все записи Листа, сравнивая имя, совпадения записать в эрейлист и вывести.

        return null;
    }

    public List<Hotel> findByCity(String city) {
        List<Hotel> hotels = getAll();
        //TODO Перебрать все записи Листа, сравнивая город, совпадения записать в эрейлист и вывести.

        return null;
    }

    public List<Room> getAllNotReservedRooms() {
        List<Hotel> hotels = getAll();
        //TODO взять лист комнат. Перебрать все записи листа комнат, проверяя reservedForUser на null
        // совпадения записать в новый лист и вывести.

        return null;
    }
}
