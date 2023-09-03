CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS bank (
    id       UUID           NOT NULL    PRIMARY KEY,
    bankName VARCHAR(30)    NOT NULL    UNIQUE
    );

CREATE TABLE IF NOT EXISTS customer (
    id        UUID         NOT NULL    PRIMARY KEY,
    fullName VARCHAR(50)   NOT NULL
    );

CREATE TABLE IF NOT EXISTS account (
    id             UUID             NOT NULL    PRIMARY KEY,
    accountNumber  VARCHAR(16)      NOT NULL    UNIQUE,
    bankId         UUID             NOT NULL,
    ownerId        UUID             NOT NULL,
    currency       VARCHAR(10)      NOT NULL,
    amount         DECIMAL,
    openingDate    TIMESTAMP WITHOUT TIME ZONE  NOT NULL,

    CONSTRAINT fk_owner_id FOREIGN KEY (ownerId) REFERENCES customer (id) ON DELETE CASCADE,
    CONSTRAINT fk_bank_id FOREIGN KEY (bankId) REFERENCES bank (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS operation (
    id                      UUID            NOT NULL    PRIMARY KEY,
    senderId                UUID,
    recipientId             UUID,
    currency                VARCHAR(10)     NOT NULL,
    kind                    VARCHAR(20)     NOT NULL,
    amount                  DECIMAL         NOT NULL,
    operationDate           TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT fk_sender_id FOREIGN KEY (senderId) REFERENCES account (id) ON DELETE CASCADE,
    CONSTRAINT fk_recipient_id FOREIGN KEY (recipientId) REFERENCES account (id) ON DELETE CASCADE
    );