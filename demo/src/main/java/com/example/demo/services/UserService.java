package com.example.demo.services;

import com.example.demo.model.Users;

public interface UserService {
    //Guardar Usuario
    Users saveUser (Users user);
    //Buscar Usuario por ID
    Users getUserById(Long id);
    //Eliminar Usuario
    void deleteUser(Long id);
    //Actualizar Usuario
    Users updateUser(Users user);
    //Obtener lista de usuarios
    Iterable<Users> getUsers();
    //Login
    
}
