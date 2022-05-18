package com.reavature.retoker.DAOS;

import java.io.IOException;


public interface Crudable<T> {


//CRUD Operations- Create, Read, Update, & Delete.

        T create(T newObject);

        T[] findAll() throws IOException;

        T findById(String id);

        public boolean update(T updatedObj);

        boolean delete(String id);

    }
