create table shop (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    identifier VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    date_shop DATE
);

create table shop_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_identifier VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    price FLOAT NOT NULL,
    shop_id BIGINT,
    FOREIGN KEY (shop_id) REFERENCES shop(id)
);