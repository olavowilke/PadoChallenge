DROP TABLE IF EXISTS tb_device;

CREATE TABLE tb_device
(
    id        UUID PRIMARY KEY,
    name      VARCHAR(50) NOT NULL,
    mac       VARCHAR(50) NOT NULL,
    email     VARCHAR(50) NOT NULL,
    latitude  VARCHAR(50) NOT NULL,
    longitude VARCHAR(50) NOT NULL
);