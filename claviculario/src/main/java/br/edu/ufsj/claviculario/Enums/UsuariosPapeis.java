package br.edu.ufsj.claviculario.Enums;

public enum UsuariosPapeis {
    ADMINISTRADOR,      //credencia gestores
    GESTOR,             //pega chaves se autorizado pelo gestor correspondente e autoriza usuários a pegarem as chaves das quais é gestor
    USUARIO             //somente pega as chaves, se autorizado por um gestor
}
