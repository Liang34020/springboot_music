CREATE TABLE IF NOT EXISTS music
(
    music_id            INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    music_name          VARCHAR(128)    NOT NULL,
    singer              VARCHAR(32)     NOT NULL,
    category            VARCHAR(32)     NOT NULL,
    youtube_url         VARCHAR(256)    NOT NULL,
    views               INT             NOT NULL,
    description         VARCHAR(1024),
    duration            VARCHAR(32)     NOT NULL,
    created_date        DATE            NOT NULL,
    last_modified_date  TIMESTAMP       NOT NULL
);