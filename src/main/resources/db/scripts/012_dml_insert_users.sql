INSERT INTO users (username, enabled, password, authority_id)
VALUES ('root', TRUE, '$2a$10$kyp5V0wUccRm7XuxGzPBCuKcEngEWDq8REkAyYB.U3u90v0Abvw.6',
        (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));