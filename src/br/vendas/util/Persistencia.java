package br.vendas.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static <T> void salvar(List<T> lista, String arquivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados em " + arquivo);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> carregar(String arquivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}