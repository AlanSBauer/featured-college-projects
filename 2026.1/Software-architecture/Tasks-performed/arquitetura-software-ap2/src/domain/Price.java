package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "price_history")
public class Price implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", length = 36)
    private UUID uuid;

    @Column(name = "price", nullable = false)
    private Float price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_price", nullable = false)
    private Date date;

    @Column(name = "store_name")
    private String storeName;

    @ManyToOne
    @JoinColumn(name = "product_uuid", nullable = false)
    private Product product;

    public Price() {
    }

    public Price(Float price, Date date) {
        this.price = price;
        this.date = date;
    }

    public Price(Float price, Date date, String storeName) {
        this.price = price;
        this.date = date;
        this.storeName = storeName;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", date=" + date +
                (storeName != null ? ", storeName='" + storeName + '\'' : "") +
                '}';
    }
}
