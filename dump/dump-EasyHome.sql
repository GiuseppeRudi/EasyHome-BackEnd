--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-02-04 12:03:51

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
-- TOC entry 4933 (class 1262 OID 25100)
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
-- TOC entry 217 (class 1259 OID 25107)
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
-- TOC entry 218 (class 1259 OID 25112)
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
-- TOC entry 4934 (class 0 OID 0)
-- Dependencies: 218
-- Name: contatti_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contatti_id_seq OWNED BY public.contatti.id;


--
-- TOC entry 219 (class 1259 OID 25113)
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
    data character varying(100),
    provincia character varying,
    latitudine double precision,
    longitudine double precision,
    immagini text[],
    venditore character varying
);


ALTER TABLE public.immobile OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 25118)
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
-- TOC entry 4935 (class 0 OID 0)
-- Dependencies: 220
-- Name: immobili_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.immobili_id_seq OWNED BY public.immobile.id;


--
-- TOC entry 221 (class 1259 OID 25119)
-- Name: messaggio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messaggio (
    id integer NOT NULL,
    oggetto character varying,
    descrizione character varying,
    acquirente character varying,
    email character varying,
    telefono character varying,
    idimmobile integer
);


ALTER TABLE public.messaggio OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 25124)
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
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 222
-- Name: messaggio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messaggio_id_seq OWNED BY public.messaggio.id;


--
-- TOC entry 224 (class 1259 OID 25150)
-- Name: recensione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recensione (
    id integer NOT NULL,
    rating integer,
    descrizione character varying,
    acquirente character varying,
    idimmobile integer
);


ALTER TABLE public.recensione OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25155)
-- Name: recensione_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recensione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recensione_id_seq OWNER TO postgres;

--
-- TOC entry 4937 (class 0 OID 0)
-- Dependencies: 225
-- Name: recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_seq OWNED BY public.recensione.id;


--
-- TOC entry 223 (class 1259 OID 25125)
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
-- TOC entry 4761 (class 2604 OID 25130)
-- Name: contatti id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti ALTER COLUMN id SET DEFAULT nextval('public.contatti_id_seq'::regclass);


--
-- TOC entry 4762 (class 2604 OID 25131)
-- Name: immobile id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.immobile ALTER COLUMN id SET DEFAULT nextval('public.immobili_id_seq'::regclass);


--
-- TOC entry 4763 (class 2604 OID 25132)
-- Name: messaggio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messaggio ALTER COLUMN id SET DEFAULT nextval('public.messaggio_id_seq'::regclass);


--
-- TOC entry 4919 (class 0 OID 25107)
-- Dependencies: 217
-- Data for Name: contatti; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4921 (class 0 OID 25113)
-- Dependencies: 219
-- Data for Name: immobile; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.immobile VALUES (36, 'fef', 'Villa', 'vvdd', 'Vendita', 2, 0, 0, 0, 0, 'fefef', 'c', 38.5742874, 16.5657335, '{immobiliImages/36/1737447259840.jpg}', 'prova');
INSERT INTO public.immobile VALUES (37, 'Rudi ', 'Villa', 'belllsia', 'Vendita', 2, 0, 0, 0, 0, 'ciao', 'reggio', 38.2698384, 16.2803362, '{immobiliImages/37/logoRistoranteZeno.jpg}', 'GiuseppeRudi');
INSERT INTO public.immobile VALUES (38, 'Villa A schiera nuova costruzione ', 'Villa', 'Lavori ultimati a fine 2024 , con impianto di riscaldamento a pavimento. Infissi di nuova generazione e classe energetica A.', 'Vendita', 200000, 120, 6, 2, 1988, 'Mia ', 'Reggio di Calabria', 38.3159017, 16.3111645, '{"immobiliImages38/UC4 SSD AUTENTIZAZIONE (3).jpg","immobiliImages38/UC2 SSD GESTIONE RICHIESTE (7).jpg","immobiliImages38/UC2 SSD GESTIONE RICHIESTE (6).jpg","immobiliImages38/UC2 SSD GESTIONE RICHIESTE (5).jpg"}', 'MirkoSonotaca');


--
-- TOC entry 4923 (class 0 OID 25119)
-- Dependencies: 221
-- Data for Name: messaggio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.messaggio VALUES (1, 'jvdvvd', 'coijccio', 'MirkoSonotaca', 'sonotacamirko@gmail.com', '3479975255', 38);


--
-- TOC entry 4926 (class 0 OID 25150)
-- Dependencies: 224
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.recensione VALUES (1, 4, 'scc', 'MirkoSonotaca', 38);


--
-- TOC entry 4925 (class 0 OID 25125)
-- Dependencies: 223
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
INSERT INTO public.utente VALUES ('MirkoSonotaca', '$2a$10$nJ3ctArInK3CvH2ajnGrh.sigJHgBAnvcvspK2Sjgq7YEQrqZgz3S', 'ROLE_USER', 'Mirko', 'Sonotaca', '2003-03-29', 'Italiana', 'sonotacamirko@gmail.com', 'Reggio Calabria', 'Locri', '3479975255', 'Via Emilio Segrè n 1 , Rende, CS ', 'other', '89046', 'SMTMRK03C29D976R');


--
-- TOC entry 4938 (class 0 OID 0)
-- Dependencies: 218
-- Name: contatti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contatti_id_seq', 1, false);


--
-- TOC entry 4939 (class 0 OID 0)
-- Dependencies: 220
-- Name: immobili_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.immobili_id_seq', 38, true);


--
-- TOC entry 4940 (class 0 OID 0)
-- Dependencies: 222
-- Name: messaggio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messaggio_id_seq', 1, true);


--
-- TOC entry 4941 (class 0 OID 0)
-- Dependencies: 225
-- Name: recensione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_seq', 1, true);


--
-- TOC entry 4765 (class 2606 OID 25135)
-- Name: contatti contatti_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti
    ADD CONSTRAINT contatti_email_key UNIQUE (email);


--
-- TOC entry 4767 (class 2606 OID 25137)
-- Name: contatti contatti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti
    ADD CONSTRAINT contatti_pkey PRIMARY KEY (id);


--
-- TOC entry 4769 (class 2606 OID 25139)
-- Name: immobile immobili_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.immobile
    ADD CONSTRAINT immobili_pkey PRIMARY KEY (id);


--
-- TOC entry 4771 (class 2606 OID 25141)
-- Name: messaggio messaggio_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messaggio
    ADD CONSTRAINT messaggio_pk PRIMARY KEY (id);


--
-- TOC entry 4773 (class 2606 OID 25143)
-- Name: utente utente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (username);


-- Completed on 2025-02-04 12:03:51

--
-- PostgreSQL database dump complete
--

