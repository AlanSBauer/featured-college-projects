package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "product_link")
public class ProductLink implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", length = 36)
    private UUID uuid;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_uuid", nullable = false)
    private Product product;

    public ProductLink() {
    }

    public ProductLink(String storeName, String url) {
        this.storeName = storeName;
        this.url = url;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductLink{" +
                "storeName='" + storeName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
