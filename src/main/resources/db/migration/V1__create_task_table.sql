CREATE TABLE task (
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(120) NOT NULL,
    description VARCHAR(500),
    status      VARCHAR(20)  NOT NULL,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ  NOT NULL DEFAULT now()
);
