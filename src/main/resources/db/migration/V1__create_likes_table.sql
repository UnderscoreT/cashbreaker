CREATE TABLE likes (
                       id   BIGSERIAL PRIMARY KEY,
                       page VARCHAR(255) NOT NULL,
                       count INTEGER     NOT NULL DEFAULT 0
);
