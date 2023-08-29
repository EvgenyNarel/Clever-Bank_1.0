CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS bank (
    id                          UUID                            NOT NULL    PRIMARY KEY,
    bankName                    VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS customer (
    id                          UUID                            NOT NULL    PRIMARY KEY,
    fullName                    VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS account (
    id                                  UUID                        NOT NULL    PRIMARY KEY,
    accountNumber                       VARCHAR(16),
    bankId                              UUID,
    ownerId                             UUID,
    currency                            VARCHAR(10),
    amount                              DECIMAL,
    openningDate                        TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT fk_owner_id FOREIGN KEY (ownerId) REFERENCES customer (id) ON DELETE CASCADE,
    CONSTRAINT fk_bank_id FOREIGN KEY (bankId) REFERENCES bank (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS operation (
    id                      UUID                            NOT NULL    PRIMARY KEY,
    senderId                UUID,
    recipientId             UUID,
    kind                    VARCHAR(20),
    amount                  DECIMAL,
    operationDate           TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT fk_sender_id FOREIGN KEY (senderId) REFERENCES account (id) ON DELETE CASCADE,
    CONSTRAINT fk_recipient_id FOREIGN KEY (recipientId) REFERENCES account (id) ON DELETE CASCADE
    );