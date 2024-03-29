package webshop;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;

public class ProductRepository {

    private JdbcTemplate jdbcTemplate;

    public ProductRepository(MariaDbDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into products (product_name, price, stock) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, productName);
            ps.setInt(2, price);
            ps.setInt(3, stock);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject("select * from products where id = ?",
                (rs, rowNum) -> {
            String productName = rs.getString("product_name");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");
            return new Product(id, productName, price, stock);
                }, id + "%");
    }

    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update("update products set stock = stock - ? where id = ?",
                amount, id);
    }
}