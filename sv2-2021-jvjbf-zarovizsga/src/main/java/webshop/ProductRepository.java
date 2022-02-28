package webshop;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;;
import javax.sql.DataSource;

public class ProductRepository {
    DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    public Product findProductById(long id){
        return jdbcTemplate.queryForObject("select * from products where id=?",
                (rs,i)->new Product(
                        rs.getLong("id"),
                        rs.getString("product_name"),
                        rs.getInt("price"),
                        rs.getInt("stock")),id);
    }

    public void updateProductStock(long id, int amount){
        jdbcTemplate.update("update products set stock = stock - ? where id = ?;", amount, id);
    }

    public long insertProduct(String productName, int price, int stock) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("insert into products (product_name, price, stock) values(?,?,?);",
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, productName);
            stmt.setInt(2, price);
            stmt.setInt(3, stock);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()){
                if (rs.next()){
                    return rs.getLong(1);
                }
                throw new IllegalStateException("Cannot get ID from database!");
            }
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot insert product", sqle);
        }


    }

}
