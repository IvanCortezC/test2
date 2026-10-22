package com.example.test2.model;

import com.example.test2.dto.UsuarioDto;

import java.util.ArrayList;

public class UsuariosModel {
    private ArrayList<UsuarioDto> usuarios;
    public UsuariosModel() {
        this.usuarios = new ArrayList<>();
    }
    public ArrayList<UsuarioDto> getUsuarios() {
        return this.usuarios;
    }
    public void agregarUsuarios(UsuarioDto usuarioDto) {
        this.usuarios.add(usuarioDto);
    }

    public void eliminar(UsuarioDto usuarioDto) {
        this.usuarios.remove(usuarioDto);
    }
}
