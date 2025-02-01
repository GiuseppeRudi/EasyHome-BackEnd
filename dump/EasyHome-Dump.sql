--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-02-01 12:12:04

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
-- TOC entry 4926 (class 1262 OID 25063)
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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 221 (class 1259 OID 25082)
-- Name: recensione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recensione (
    id integer NOT NULL,
    rating integer,
    descrizione character varying
);


ALTER TABLE public.recensione OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 25081)
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
-- TOC entry 4927 (class 0 OID 0)
-- Dependencies: 220
-- Name: Recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Recensione_id_seq" OWNED BY public.recensione.id;


--
-- TOC entry 224 (class 1259 OID 25090)
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
-- TOC entry 222 (class 1259 OID 25088)
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
-- TOC entry 223 (class 1259 OID 25089)
-- Name: contatti_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contatti_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.contatti_id_seq1 OWNER TO postgres;

--
-- TOC entry 4928 (class 0 OID 0)
-- Dependencies: 223
-- Name: contatti_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contatti_id_seq1 OWNED BY public.contatti.id;


--
-- TOC entry 217 (class 1259 OID 25064)
-- Name: immobili; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.immobili (
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


ALTER TABLE public.immobili OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 25069)
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
-- TOC entry 4929 (class 0 OID 0)
-- Dependencies: 218
-- Name: immobili_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.immobili_id_seq OWNED BY public.immobili.id;


--
-- TOC entry 219 (class 1259 OID 25070)
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
-- TOC entry 4759 (class 2604 OID 25093)
-- Name: contatti id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti ALTER COLUMN id SET DEFAULT nextval('public.contatti_id_seq1'::regclass);


--
-- TOC entry 4757 (class 2604 OID 25073)
-- Name: immobili id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.immobili ALTER COLUMN id SET DEFAULT nextval('public.immobili_id_seq'::regclass);


--
-- TOC entry 4758 (class 2604 OID 25085)
-- Name: recensione id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id SET DEFAULT nextval('public."Recensione_id_seq"'::regclass);


--
-- TOC entry 4920 (class 0 OID 25090)
-- Dependencies: 224
-- Data for Name: contatti; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4913 (class 0 OID 25064)
-- Dependencies: 217
-- Data for Name: immobili; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.immobili VALUES (29, 'tyutuy', 'Villa', 'ryurtyu', 'Vendita', 654768, 0, 0, 0, 0, 'dgfhdfgj', 'Catanzaro', 34.65475677, 56.56476563, '{"immobiliImages/29/Screenshot 2024-02-14 113114.png","immobiliImages/29/Screenshot 2024-02-14 113200.png"}', NULL);
INSERT INTO public.immobili VALUES (30, 'dfafg', 'Appartamento', 'adfag', 'Affitto', 62353, 0, 0, 0, 0, 'ghjdhg', 'Catanzaro', 34.65475677, 56.56476563, '{"immobiliImages/30/Screenshot 2024-02-14 113114.png","immobiliImages/30/Screenshot 2024-02-14 113234.png"}', NULL);
INSERT INTO public.immobili VALUES (31, 'dfgh', 'Villa', 'dfgdhg', 'Affitto', 347, 0, 0, 0, 0, 'dfghdg', 'Catanzaro', 34.65475677, 56.56476563, '{"immobiliImages/31/Screenshot (12) - Copia.png","immobiliImages/31/Screenshot (12).png"}', 'prova');
INSERT INTO public.immobili VALUES (32, 'ryutyi', 'Casa', 'htruj', 'Vendita', 348, 0, 0, 0, 0, 'yurtuy', 'Catanzaro', 34.65475677, 56.56476563, '{"immobiliImages/32/Screenshot 2023-07-23 154509.png"}', 'prova');


--
-- TOC entry 4917 (class 0 OID 25082)
-- Dependencies: 221
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4915 (class 0 OID 25070)
-- Dependencies: 219
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utente VALUES ('john_doe', '$2a$10$ckUF9WPuCjy62Q9PCqS8kOgJWnY6vmbU7oTfZ643mM/f1r.UEbusm', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('prova', '$2a$10$p5P812D3j/09xbJlJ.tLtOXPPgjgl/.k4d6Hlwe7qLwRMuqEIG29.', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('GiuseppeRudi', '$2a$10$MBRa1qBPuY5Mci6G6cpXA.eW2HsKvMvXuBRkQtDWcY9ueiI9WNARC', 'ROLE_ADMIN', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('Zio', '$2a$10$wlb/jKKVjoa5WYh2RUYxKelKZo8jux3brLGZCPD3y6QOAzvC23Mxi', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.utente VALUES ('mario.rossi', '$2a$10$WVWlBGaWN8EmN4c3yDm1mu/s0pTreYTSP2fxDJSgtu4JJtc7KX2hO', 'ROLE_USER', 'Mario', 'Rossi', '1990-05-15', 'Italiana', 'mario.rossi@example.com');
INSERT INTO public.utente VALUES ('fef', '$2a$10$d6ABLlLkxUyno5pwODFg3.8e9vtHmvXAThIaoUiMzk48QbPnfDTu6', 'ROLE_USER', 'ffef', 'fefe', '2025-01-20', 'fef', 'fefe');


--
-- TOC entry 4930 (class 0 OID 0)
-- Dependencies: 220
-- Name: Recensione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Recensione_id_seq"', 1, false);


--
-- TOC entry 4931 (class 0 OID 0)
-- Dependencies: 222
-- Name: contatti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contatti_id_seq', 1, false);


--
-- TOC entry 4932 (class 0 OID 0)
-- Dependencies: 223
-- Name: contatti_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contatti_id_seq1', 1, false);


--
-- TOC entry 4933 (class 0 OID 0)
-- Dependencies: 218
-- Name: immobili_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.immobili_id_seq', 32, true);


--
-- TOC entry 4765 (class 2606 OID 25099)
-- Name: contatti contatti_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti
    ADD CONSTRAINT contatti_email_key UNIQUE (email);


--
-- TOC entry 4767 (class 2606 OID 25097)
-- Name: contatti contatti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti
    ADD CONSTRAINT contatti_pkey PRIMARY KEY (id);


--
-- TOC entry 4761 (class 2606 OID 25075)
-- Name: immobili immobili_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.immobili
    ADD CONSTRAINT immobili_pkey PRIMARY KEY (id);


--
-- TOC entry 4763 (class 2606 OID 25077)
-- Name: utente utente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (username);


-- Completed on 2025-02-01 12:12:04

--
-- PostgreSQL database dump complete
--

