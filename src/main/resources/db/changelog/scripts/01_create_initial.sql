--liquibase formatted sql

--changeset mammadli:create-order-table
CREATE TABLE IF NOT EXISTS `order`
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT,
    date        DATE,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

--changeset mammadli:add-order-line-foreign-key
ALTER TABLE order_line
    ADD CONSTRAINT order_line_order_fk
        FOREIGN KEY (order_id) REFERENCES `order` (id);



--changeset mammadli:create-customer-table
CREATE TABLE IF NOT EXISTS customer (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          registration_code VARCHAR(255) UNIQUE,
                          full_name VARCHAR(255),
                          email VARCHAR(255) UNIQUE,
                          telephone VARCHAR(255)
);

--changeset mammadli:add-order-foreign-key
ALTER TABLE `order` ADD CONSTRAINT order_customer_fk
    FOREIGN KEY (customer_id) REFERENCES customer(id);



--changeset mammadli:create-order-line-table
CREATE TABLE IF NOT EXISTS order_line (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            order_id BIGINT,
                            product_id BIGINT,
                            quantity INT,
                            FOREIGN KEY (order_id) REFERENCES `order`(id),
                            FOREIGN KEY (product_id) REFERENCES product(id)
);



--changeset mammadli:create-product-table
CREATE TABLE IF NOT EXISTS product (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         sku_code VARCHAR(255) UNIQUE,
                         name VARCHAR(255),
                         unit_price DECIMAL(10,2)
);

