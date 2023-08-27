CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS bank (
    id                          UUID                            NOT NULL    PRIMARY KEY,
    bank_name                   VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS customer (
    id                          UUID                            NOT NULL    PRIMARY KEY,
    full_name                   VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS account (
    id                                  UUID                        NOT NULL    PRIMARY KEY,
    number_account                      VARCHAR(16),
    bank_id                             UUID,
    owner_id                            UUID,
    currency                            VARCHAR(10),
    amount                              DECIMAL,
    openning_date                       TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES customer (id) ON DELETE CASCADE,
    CONSTRAINT fk_bank_id FOREIGN KEY (bank_id) REFERENCES bank (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS kind_operation
(
    id           UUID NOT NULL PRIMARY KEY,
    kind         VARCHAR(15)
);
CREATE TABLE IF NOT EXISTS operation (
    id                      UUID                            NOT NULL    PRIMARY KEY,
    sender_id               UUID,
    recipient_id            UUID,
    kind_id                 UUID,
    amount                  DECIMAL,
    date_operation          TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT fk_sender_id FOREIGN KEY (sender_id) REFERENCES account (id) ON DELETE CASCADE,
    CONSTRAINT fk_recipient_id FOREIGN KEY (recipient_id) REFERENCES account (id) ON DELETE CASCADE,
    CONSTRAINT fk_kind_id FOREIGN KEY (kind_id) REFERENCES kind_operation (id) ON DELETE CASCADE
);

