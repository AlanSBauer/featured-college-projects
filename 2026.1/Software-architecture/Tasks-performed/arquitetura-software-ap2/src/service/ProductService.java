package service;

import adapter.DatabaseStorage;
import adapter.PersistInterface;
import domain.EntityInterface;
import java.util.ArrayList;
import java.util.UUID;

public class ProductService implements ServiceInterface {
    PersistInterface armazenamento = new DatabaseStorage();

    @Override
    public void create(EntityInterface entity) {
        this.armazenamento.save(entity);
    }

    @Override
    public void delete(EntityInterface entity) {
        this.armazenamento.delete(entity);
    }

    @Override
    public void listAll() {
        ArrayList<EntityInterface> dados = armazenamento.listAll();
        for (int i = 0; i < dados.size(); i++) {
            System.out.println(dados.get(i)); // Changed IO.println to System.out.println
        }
    }

    public ArrayList<domain.Product> getAllProducts() {
        ArrayList<EntityInterface> dados = armazenamento.listAll();
        ArrayList<domain.Product> products = new ArrayList<>();
        for (EntityInterface e : dados) {
            if (e instanceof domain.Product) {
                products.add((domain.Product) e);
            }
        }
        return products;
    }

    @Override
    public EntityInterface getById(UUID id) {
        return armazenamento.findOneById(id);
    }

    public UUID generateUUID() {
        return UUID.randomUUID();
    }
}
