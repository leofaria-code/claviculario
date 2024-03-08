package br.edu.ufsj.claviculario.Models;

import java.util.List;

public record User(int id, String matricula, String name, String phone
        , List<Chave> salasQueGerencia, List<Chave> salasQuePodeAcessar) {}
