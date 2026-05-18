package pedidoscafeteria;

import java.math.BigDecimal;
import java.util.UUID;

public class Pedido {
    private UUID id;
    private String descricao;
    private BigDecimal price;

    public Pedido(UUID id, String descricao, BigDecimal price) {
        this.id = id;
        this.descricao = descricao;
        this.price = price;

    }

    public Pedido() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
