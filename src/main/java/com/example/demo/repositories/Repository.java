package com.example.demo.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();
    List<T> getDTO(int id);
    List<T> getDTO(String id) ;
}
