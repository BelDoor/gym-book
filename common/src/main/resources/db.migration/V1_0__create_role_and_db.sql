-- CREATE ROLE postgres WITH
--     LOGIN
--     SUPERUSER
--     INHERIT
--     CREATEDB
--     CREATEROLE
--     REPLICATION
--     ENCRYPTED PASSWORD 'root';
--
-- CREATE DATABASE gym_book
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     CONNECTION LIMIT = 20;
