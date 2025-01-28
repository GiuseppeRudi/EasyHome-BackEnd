--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-01-28 14:18:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16727)
-- Name: utente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utente (
                               username character varying(100) NOT NULL,
                               password character varying(100),
                               role character varying(20),
                               nome character varying(50),
                               cognome character varying(20),
                               data_nascita character varying(20),
                               nazionalita character varying(20),
                               email character varying(50)
);


ALTER TABLE public.utente OWNER TO postgres;

--
-- TOC entry 4888 (class 0 OID 16727)
-- Dependencies: 217
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utente (username, password, role, nome, cognome, data_nascita, nazionalita, email)
VALUES
    ('john_doe', '$2a$10$ckUF9WPuCjy62Q9PCqS8kOgJWnY6vmbU7oTfZ643mM/f1r.UEbusm', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL),
    ('prova', '$2a$10$p5P812D3j/09xbJlJ.tLtOXPPgjgl/.k4d6Hlwe7qLwRMuqEIG29.', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL),
    ('GiuseppeRudi', '$2a$10$MBRa1qBPuY5Mci6G6cpXA.eW2HsKvMvXuBRkQtDWcY9ueiI9WNARC', 'ROLE_ADMIN', NULL, NULL, NULL, NULL, NULL),
    ('Zio', '$2a$10$wlb/jKKVjoa5WYh2RUYxKelKZo8jux3brLGZCPD3y6QOAzvC23Mxi', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL),
    ('mario.rossi', '$2a$10$WVWlBGaWN8EmN4c3yDm1mu/s0pTreYTSP2fxDJSgtu4JJtc7KX2hO', 'ROLE_USER', 'Mario', 'Rossi', '1990-05-15', 'Italiana', 'mario.rossi@example.com'),
    ('fef', '$2a$10$d6ABLlLkxUyno5pwODFg3.8e9vtHmvXAThIaoUiMzk48QbPnfDTu6', 'ROLE_USER', 'ffef', 'fefe', '2025-01-20', 'fef', 'fefe');


--
-- TOC entry 4742 (class 2606 OID 16731)
-- Name: utente utente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (username);


-- Completed on 2025-01-28 14:18:35

--
-- PostgreSQL database dump complete
--

