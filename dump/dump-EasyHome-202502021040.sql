--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-02-02 10:40:50

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

DROP DATABASE "EasyHome";
--
-- TOC entry 4887 (class 1262 OID 16849)
-- Name: EasyHome; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "EasyHome" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Italian_Italy.1252';


ALTER DATABASE "EasyHome" OWNER TO postgres;

\connect "EasyHome"

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

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4888 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 223 (class 1259 OID 17057)
-- Name: recensione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recensione (
    id integer NOT NULL,
    rating integer,
    descrizione character varying
);


ALTER TABLE public.recensione OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17056)
-- Name: Recensione_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Recensione_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Recensione_id_seq" OWNER TO postgres;

--
-- TOC entry 4889 (class 0 OID 0)
-- Dependencies: 222
-- Name: Recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Recensione_id_seq" OWNED BY public.recensione.id;


--
-- TOC entry 221 (class 1259 OID 17042)
-- Name: contatti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contatti (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    cognome character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    tipo character varying(50) NOT NULL,
    domanda character varying(255) NOT NULL
);


ALTER TABLE public.contatti OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17041)
-- Name: contatti_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contatti_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.contatti_id_seq OWNER TO postgres;

--
-- TOC entry 4890 (class 0 OID 0)
-- Dependencies: 220
-- Name: contatti_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contatti_id_seq OWNED BY public.contatti.id;


--
-- TOC entry 219 (class 1259 OID 16908)
-- Name: immobile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.immobile (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    tipo character varying(100),
    descrizione character varying(255),
    categoria character varying(100),
    prezzo integer,
    mq integer,
    camere integer,
    bagni integer,
    anno integer,
    etichetta character varying(100),
    provincia character varying,
    latitudine double precision,
    longitudine double precision,
    immagini text[],
    venditore character varying
);


ALTER TABLE public.immobile OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16907)
-- Name: immobili_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.immobili_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.immobili_id_seq OWNER TO postgres;

--
-- TOC entry 4891 (class 0 OID 0)
-- Dependencies: 218
-- Name: immobili_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.immobili_id_seq OWNED BY public.immobile.id;


--
-- TOC entry 224 (class 1259 OID 17063)
-- Name: messaggio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messaggio (
    id integer NOT NULL,
    oggetto character varying,
    descrizione character varying,
    acquirente character varying,
    email character varying,
    telefono bigint,
    idimmobile integer
);


ALTER TABLE public.messaggio OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17066)
-- Name: messaggio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.messaggio_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.messaggio_id_seq OWNER TO postgres;

--
-- TOC entry 4892 (class 0 OID 0)
-- Dependencies: 225
-- Name: messaggio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messaggio_id_seq OWNED BY public.messaggio.id;


--
-- TOC entry 217 (class 1259 OID 16850)
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
    email character varying(50),
    provincia character varying,
    citta character varying,
    telefono character varying,
    indirizzo character varying,
    sesso character varying,
    cap character varying,
    cf character varying
);


ALTER TABLE public.utente OWNER TO postgres;

--
-- TOC entry 4715 (class 2604 OID 17045)
-- Name: contatti id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti ALTER COLUMN id SET DEFAULT nextval('public.contatti_id_seq'::regclass);


--
-- TOC entry 4714 (class 2604 OID 16911)
-- Name: immobile id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.immobile ALTER COLUMN id SET DEFAULT nextval('public.immobili_id_seq'::regclass);


--
-- TOC entry 4717 (class 2604 OID 17067)
-- Name: messaggio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messaggio ALTER COLUMN id SET DEFAULT nextval('public.messaggio_id_seq'::regclass);


--
-- TOC entry 4716 (class 2604 OID 17060)
-- Name: recensione id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id SET DEFAULT nextval('public."Recensione_id_seq"'::regclass);


--
-- TOC entry 4877 (class 0 OID 17042)
-- Dependencies: 221
-- Data for Name: contatti; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4875 (class 0 OID 16908)
-- Dependencies: 219
-- Data for Name: immobile; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.immobile VALUES (33, 'fdhdg', 'Appartamento', 'dfdgssdfg', 'Affitto', 3658, 0, 0, 0, 0, 'fsfgdf', 'Catanzaro', 0, 0, '{"immobiliImages/33/Screenshot (1) - Copia.png","immobiliImages/33/Screenshot (1).png"}', 'prova');
INSERT INTO public.immobile VALUES (34, 'sgdfgdfgd', 'Appartamento', 'fdfg', 'Vendita', 5365, 0, 0, 0, 0, 'sfgsfgs', 'Catanzaro', 41.5019168, 12.6301828, '{"immobiliImages/34/Screenshot (1).png"}', 'prova');
INSERT INTO public.immobile VALUES (29, 'tyutuy', 'Villa', 'ryurtyu', 'Vendita', 654768, 0, 0, 0, 0, 'dgfhdfgj', 'Catanzaro', 34.65475677, 56.56476563, '{"immobiliImages/29/Screenshot 2024-02-14 113114.png","immobiliImages/29/Screenshot 2024-02-14 113200.png"}', NULL);
INSERT INTO public.immobile VALUES (30, 'dfafg', 'Appartamento', 'adfag', 'Affitto', 62353, 0, 0, 0, 0, 'ghjdhg', 'Catanzaro', 34.65475677, 56.56476563, '{"immobiliImages/30/Screenshot 2024-02-14 113114.png","immobiliImages/30/Screenshot 2024-02-14 113234.png"}', NULL);
INSERT INTO public.immobile VALUES (31, 'dfgh', 'Villa', 'dfgdhg', 'Affitto', 347, 0, 0, 0, 0, 'dfghdg', 'Catanzaro', 34.65475677, 56.56476563, '{"immobiliImages/31/Screenshot (12) - Copia.png","immobiliImages/31/Screenshot (12).png"}', 'prova');
INSERT INTO public.immobile VALUES (32, 'ryutyi', 'Casa', 'htruj', 'Vendita', 348, 0, 0, 0, 0, 'yurtuy', 'Catanzaro', 34.65475677, 56.56476563, '{"immobiliImages/32/Screenshot 2023-07-23 154509.png"}', 'prova');


--
-- TOC entry 4880 (class 0 OID 17063)
-- Dependencies: 224
-- Data for Name: messaggio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4879 (class 0 OID 17057)
-- Dependencies: 223
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4873 (class 0 OID 16850)
-- Dependencies: 217
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utente VALUES ('john_doe', '$2a$10$ckUF9WPuCjy62Q9PCqS8kOgJWnY6vmbU7oTfZ643mM/f1r.UEbusm', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('prova', '$2a$10$p5P812D3j/09xbJlJ.tLtOXPPgjgl/.k4d6Hlwe7qLwRMuqEIG29.', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('GiuseppeRudi', '$2a$10$MBRa1qBPuY5Mci6G6cpXA.eW2HsKvMvXuBRkQtDWcY9ueiI9WNARC', 'ROLE_ADMIN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('Zio', '$2a$10$wlb/jKKVjoa5WYh2RUYxKelKZo8jux3brLGZCPD3y6QOAzvC23Mxi', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('mario.rossi', '$2a$10$WVWlBGaWN8EmN4c3yDm1mu/s0pTreYTSP2fxDJSgtu4JJtc7KX2hO', 'ROLE_USER', 'Mario', 'Rossi', '1990-05-15', 'Italiana', 'mario.rossi@example.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('fef', '$2a$10$d6ABLlLkxUyno5pwODFg3.8e9vtHmvXAThIaoUiMzk48QbPnfDTu6', 'ROLE_USER', 'ffef', 'fefe', '2025-01-20', 'fef', 'fefe', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('ertyerty', '$2a$10$ZkvimygmE0XRzPpDVBhakOs0wrQqhKWGj.f3isKhHON3HEHi21gyO', 'ROLE_USER', 'dgddfghgh', 'dhfh', '275760-06-05', 'Italia', 'ertyertu', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('ciccio', '$2a$10$Xctu8hyLvoishOo836xPju18k1B2KVgn4ksXId8wCMamILmcs3M7y', 'ROLE_USER', 'the', 'yjtiuit', '', 'yruyruy', 'etruy', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('genny', '$2a$10$umDZ5caFgHkvjPbcNAgLJefdr4lnR/GZnxvN1FLqbjPGZtl5raiBm', 'ROLE_USER', 'erter', 'terterte', '62563-03-24', 'dfghdghdg', 'cici@com', 'dfghdf', 'ghdfghd', '123456', 'eryhtrh', 'male', 'fghdghdgd', 'fghd');
INSERT INTO public.utente VALUES ('sofa', '$2a$10$oNGUwjqbfSWiQMnI/3lhtOnNOigMHV9rkcmr2n2JKPZr6xt4TAJ5m', 'ROLE_USER', 'dfgdfg', 'dfgsdfg', '0235-12-23', 'dfgdf', 'sfdsdfh@ggn.bn', 'gsdfhgfh', 'sdfgsdfg', '123123123', 'adgsdfh', 'male', '13245', 'fgsdfgdfhsgfjsgj');


--
-- TOC entry 4893 (class 0 OID 0)
-- Dependencies: 222
-- Name: Recensione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Recensione_id_seq"', 1, false);


--
-- TOC entry 4894 (class 0 OID 0)
-- Dependencies: 220
-- Name: contatti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contatti_id_seq', 1, false);


--
-- TOC entry 4895 (class 0 OID 0)
-- Dependencies: 218
-- Name: immobili_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.immobili_id_seq', 34, true);


--
-- TOC entry 4896 (class 0 OID 0)
-- Dependencies: 225
-- Name: messaggio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messaggio_id_seq', 1, false);


--
-- TOC entry 4723 (class 2606 OID 17051)
-- Name: contatti contatti_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti
    ADD CONSTRAINT contatti_email_key UNIQUE (email);


--
-- TOC entry 4725 (class 2606 OID 17049)
-- Name: contatti contatti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti
    ADD CONSTRAINT contatti_pkey PRIMARY KEY (id);


--
-- TOC entry 4721 (class 2606 OID 16916)
-- Name: immobile immobili_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.immobile
    ADD CONSTRAINT immobili_pkey PRIMARY KEY (id);


--
-- TOC entry 4727 (class 2606 OID 17072)
-- Name: messaggio messaggio_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messaggio
    ADD CONSTRAINT messaggio_pk PRIMARY KEY (id);


--
-- TOC entry 4719 (class 2606 OID 16854)
-- Name: utente utente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (username);


-- Completed on 2025-02-02 10:40:51

--
-- PostgreSQL database dump complete
--

