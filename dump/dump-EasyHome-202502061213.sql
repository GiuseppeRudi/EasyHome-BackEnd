--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-02-06 12:13:39

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
-- TOC entry 4887 (class 1262 OID 17130)
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
-- TOC entry 5 (class 2615 OID 17131)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4888 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 17138)
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
-- TOC entry 220 (class 1259 OID 17143)
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
-- TOC entry 221 (class 1259 OID 17144)
-- Name: immobile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.immobile (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    tipo character varying(100),
    descrizione character varying(10000),
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
    venditore character varying,
    prezzo_scontato integer
);


ALTER TABLE public.immobile OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17149)
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
-- Dependencies: 222
-- Name: immobili_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.immobili_id_seq OWNED BY public.immobile.id;


--
-- TOC entry 223 (class 1259 OID 17150)
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
-- TOC entry 224 (class 1259 OID 17155)
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
-- Dependencies: 224
-- Name: messaggio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messaggio_id_seq OWNED BY public.messaggio.id;


--
-- TOC entry 217 (class 1259 OID 17132)
-- Name: recensione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recensione (
    id integer NOT NULL,
    rating integer,
    descrizione character varying,
    acquirente character varying(200),
    idimmobile integer
);


ALTER TABLE public.recensione OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17137)
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
-- TOC entry 4893 (class 0 OID 0)
-- Dependencies: 218
-- Name: recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_seq OWNED BY public.recensione.id;


--
-- TOC entry 225 (class 1259 OID 17156)
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
-- TOC entry 4715 (class 2604 OID 17161)
-- Name: contatti id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti ALTER COLUMN id SET DEFAULT nextval('public.contatti_id_seq'::regclass);


--
-- TOC entry 4716 (class 2604 OID 17162)
-- Name: immobile id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.immobile ALTER COLUMN id SET DEFAULT nextval('public.immobili_id_seq'::regclass);


--
-- TOC entry 4717 (class 2604 OID 17163)
-- Name: messaggio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messaggio ALTER COLUMN id SET DEFAULT nextval('public.messaggio_id_seq'::regclass);


--
-- TOC entry 4714 (class 2604 OID 17164)
-- Name: recensione id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id SET DEFAULT nextval('public.recensione_id_seq'::regclass);


--
-- TOC entry 4875 (class 0 OID 17138)
-- Dependencies: 219
-- Data for Name: contatti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contatti VALUES (4, 'Simone', 'Cozza', 'simonecozza02@hotmail.it', 'privato', 'Non riesco a vedere i messaggi , mi da errore 403 potete risolvere , grazie in anticipo.');
INSERT INTO public.contatti VALUES (5, 'Mirko', 'Sonotaca', 'sonotacamirko@gmail.com', 'privato', 'Non riesco a vedere i miei annunci , mi da un caricamento infinito come posso risolvere , potete aiutarmi , grazie in anticipo.');


--
-- TOC entry 4877 (class 0 OID 17144)
-- Dependencies: 221
-- Data for Name: immobile; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.immobile VALUES (46, 'Ampio appartamento con cantina e posto biciclette', 'Appartamento', 'Appartamento a ridosso della zona centrale, in piccola palazzina di 3 unità, ampio appartamento BICAMERE, recentemente ristrutturato. L''appartamento,di circa 120mq, si compone di una zona ingresso, una ampia zona giorno con angolo cottura arredato, camera matrimoniale con bagno padronale, una seconda camera matrimoniale, un secondo bagno. Utile cantinola e posto biciclette al piano terra.', 'Vendita', 249000, 120, 6, 2, 1962, '2025-02-05', 'Reggio Calabria', 38.362285, 16.239218, '{immobiliImages/46/casa2foto5.png,immobiliImages/46/casa2foto0.png,immobiliImages/46/casa2foto1.png,immobiliImages/46/casa2foto2.png,immobiliImages/46/casa2foto3.png,immobiliImages/46/casa2foto4.png,immobiliImages/46/casa2foto6.png,immobiliImages/46/casa2foto7.png,immobiliImages/46/casa2foto8.png}', 'SimoneCozza', NULL);
INSERT INTO public.immobile VALUES (50, 'Affitto Appartamento OPERA', 'Appartamento', 'Proponiamo in locazione bilocale di ca. 45 mq posto al primo piano di uno stabile anni ''50, di cui è stato appena deliberato il rifacimento di tutte le parti comuni.
L''appartamento è composto da ingresso su cucina abitabile arredata, soggiorno, camera e bagno finestrato. Viene locato completamente arredato a nuovo.
L''immobile è stato completamente ristrutturato con porta blindata, serramenti esterni con doppia camera d''aria, aria condizionata, gres su tutta la superficie.
Spese condominiali contenute. Libero dal 1 marzo 2025.', 'Affitto', 850, 45, 2, 1, 1955, '2025-02-05', 'Vibo Valentia', 38.67254, 16.10174, '{immobiliImages/50/casa0foto1.png,immobiliImages/50/casa0foto2.png,immobiliImages/50/casa0foto3.png,immobiliImages/50/casa0foto4.png,immobiliImages/50/casa0foto5.png,immobiliImages/50/casa0foto6.png,immobiliImages/50/casa0foto7.png,immobiliImages/50/casa0foto8.png,immobiliImages/50/casa0foto9.png,immobiliImages/50/casa0foto10.png,immobiliImages/50/casa0foto11.png,immobiliImages/50/casa0foto12.png,immobiliImages/50/casa0foto13.png,immobiliImages/50/casa0foto0.png}', 'FrancescoCristiano', NULL);
INSERT INTO public.immobile VALUES (49, 'Affitto appartamento piano terra', 'Appartamento', 'In centro al paese, proponiamo appartamento in affitto al piano terra arredato di cucina.
L''immobile si compone di soggiorno con angolo cottura, due comode camere da letto, bagno e giardino privato.
Al piano interrato è presente un posto auto coperto.
Le spese condominiali sono escluse dal prezzo di locazione.
', 'Affitto', 700, 65, 5, 1, 1981, '2025-02-05', 'Cosenza', 39.8147978, 16.2064247, '{immobiliImages/49/0.png,immobiliImages/49/casa4foto0.png,immobiliImages/49/casa4foto1.png,immobiliImages/49/casa4foto3.png,immobiliImages/49/casa4foto4.png,immobiliImages/49/casa4foto5.png,immobiliImages/49/casa4foto6.png}', 'MirkoSonotaca', NULL);
INSERT INTO public.immobile VALUES (48, 'Affitto appartamento condiviso', 'Appartamento', 'Stai cercando una camera in un’abitazione condivisa? Che abbia un canone comprensivo di tutte le spese e le utenze? Che sia a 10 minuti dai mezzi pubblici e in una zona residenziale servita?
Affittasi camera condivisa a persone con contratto di lavoro a tempo determinato sino a 30 giugno 2025.
Questa soluzione è perfetta per chi ha necessità di un alloggio a seguito di un lavoro a tempo determinato, (insegnanti, personale scolastico)

Disponibile dal 1° settembre 2024 al 30 Giugno 2025

In una graziosa villetta indipendente di mq. 120 con giardino, proponiamo 3 tipologie di camere completamente arredate, con un''ampia zona giorno, bagno, lavanderia condivisi.

Le camere attualmente disponibili sono 3:
? CAMERA 1 MATRIMONIALE con letto matrimoniale, materasso, armadio a 3 ante, cassettiera e comodino ad € 450,00mese;
? CAMERA 2 SINGOLA con letto singolo, materasso, armadio a 2 ante, scrivania e comodino ad € 400/mese;
? CAMERA 3 MATRIMONIALE con letto singolo, materasso, armadio a 3 ante, scrivania e comodino ad € 430/mese;
DEPOSITO CAUZIONELE 2 MENSILITA’

Gli spazi condivisi sono:
Spaziosa cucina abitabile completamente attrezzata; soggiorno composto da divano tre posti con vari mobili; 1 bagno finestrato con ampia doccia; vano caldaia con lavatrice, ampio terrazzo di mq. 70; giardino comune con accesso carraio automatizzato; posto auto esterno scoperto.

La villetta è provvista di TV, lavatrice, forno, forno microonde, lavastoviglie, asse e ferro da stiro, condizionatore con doppio split, possibilità di collegamento internet con WI-FI (previo accordo).

I prezzi sono comprensivi di consumi di acqua/luce e gas, spese condominiali e ogni altra spesa.

Nelle vicinanze sono presenti diverse attività commerciali, come farmacia, ufficio postale, supermercato, distretto sanitario, pizzerie ed altro ancora, raggiungibili comodamente a piedi poichè situate proprio fronte casa.', 'Affitto', 400, 120, 3, 1, 1948, '2025-02-05', 'Catanzaro', 38.5704234, 16.5675315, '{immobiliImages/48/casa3foto4.png,immobiliImages/48/casa3foto5.png,immobiliImages/48/casa3foto7.png,immobiliImages/48/casa3foto8.png,immobiliImages/48/casa3foto9.png,immobiliImages/48/casa3foto10.png,immobiliImages/48/casa3foto0.png,immobiliImages/48/casa3foto2.png,immobiliImages/48/casa3foto1.png}', 'MirkoSonotaca', NULL);
INSERT INTO public.immobile VALUES (51, 'Vendita villetta con posto auto', 'Villa', 'In piccolo ed elegante contesto, proponiamo appartamento bicamere al 1° piano servito da ascensore, composto da ingresso, ampio e luminoso salone, cucina abitabile, camera matrimoniale con cabina armadio e bagno padronale con vasca, camera singola e 2° bagno con doccia. Posto auto nelle immediate vicinanze.', 'Vendita', 185000, 120, 4, 1, 1939, '2025-02-05', 'Catanzaro', 39.0859168, 16.3659783, '{immobiliImages/51/casa5foto3.png,immobiliImages/51/casa5foto4.png,immobiliImages/51/casa5foto5.png,immobiliImages/51/casa5foto6.png,immobiliImages/51/casa5foto7.png,immobiliImages/51/casa5foto8.png,immobiliImages/51/casa5foto9.png,immobiliImages/51/casa5foto10.png,immobiliImages/51/casa5foto11.png,immobiliImages/51/casa5foto12.png,immobiliImages/51/casa5foto13.png,immobiliImages/51/casa5foto0.png,immobiliImages/51/casa5foto1.png,immobiliImages/51/casa5foto2.png,immobiliImages/51/casa5foto14.png}', 'FrancescoCristiano', NULL);
INSERT INTO public.immobile VALUES (54, 'Vendita villa sulla spiaggia', 'Villa', 'splendida villa confinante con la spiaggia composta da 10 locali e quattro bagni strutturata su tre livelli divisibile in tre unità', 'Aste', 230000, 82, 5, 1, 1962, '2025-02-05', ' Cosenza', 39.8042931, 16.4902988, '{immobiliImages/54/casa8foto4.png,immobiliImages/54/casa8foto1.png,immobiliImages/54/casa8foto2.png,immobiliImages/54/casa8foto3.png,immobiliImages/54/casa8foto0.png}', 'KristianReale', NULL);
INSERT INTO public.immobile VALUES (52, 'Attico all''asta con splendita terrazza', 'Appartamento', 'Signorile ultimo piano su 2 livelli con splendida terrazza. Siamo a Soverato, appartamento su 2 livelli corredato da una terrazza con pompeiana e con uno splendido affaccio su Badolato e il lungomare; ingresso al quarto piano con ascensore; l''appartamento si compone di soggiorno con affaccio su un terrazzo; cucinotto arredato con cucina della Veneta Cucine che a richiesta può rimanere e cambusa/ripostiglio; zona notte composta da 2 camere con terrazzi, uno studio e un ampio bagno finestrato; dal soggiorno una scala d''arredo ci conduce al livello superiore, open space con un bagno finestrato e una grande terrazza con pompeiana e vista aperta verso sud. Garage singolo al piano terra.', 'Aste', 290000, 190, 9, 2, 1984, '2025-02-05', 'Catanzaro', 38.6917509, 16.5461751, '{immobiliImages/52/casa6foto1.png,immobiliImages/52/casa6foto0.png,immobiliImages/52/casa6foto2.png,immobiliImages/52/casa6foto3.png,immobiliImages/52/casa6foto4.png,immobiliImages/52/casa6foto5.png,immobiliImages/52/casa6foto6.png,immobiliImages/52/casa6foto7.png}', 'MarcoForte', NULL);
INSERT INTO public.immobile VALUES (53, 'Villa con ampio giardino', 'Villa', 'IN UNA DELLE ZONE RESIDENZIALI PIU'' ELEGANTI E'' SITUATA QUESTA IMPORTANTE VILLA UNIFAMILIARE.
LA CASA E'' STATA REALIZZATA QUANDO L''OBBIETTIVO DEL PROGETTISTA ERA INNANZI TUTTI DI PENSARE AD AMPI LOCALI CHE GARANTISSERO VIVIBILITA'' AGLI OCCUPANTI E UNA ADEGUATA RAPPRESENTANZA DEGLI AMBIENTI.
ESSA SI SVILUPPA SU UN PIANO TERRA CON LA ZONA GIORNO, UN PRIMO PIANO CON LA ZONA NOTTE ED UN GRANDE INTERRATO CON GARAGE, TAVERNA E LOCALI DI SERVIZIO.
DISPONE DI AMPI TERRAZZI CON SCORCI PANORAMICI.
E'' CIRCONDATA DA UN GIARDINO CURATO E DOTATO DI IMPIATO DI IRRIGAZIONE CHE NE GARANTISCE UNA COMODA GESTIBILITA''.
PER GLI ATTUALI OCCUPANTI L''ABITAZIONE E'' DIVENUTA TROPPO GRANDE E MERITA QUINDI LE RINNOVATE ATTENZIONI DI UNA GIOVANE COPPIA O DI UNA FAMIGLIA, ANCHE NUMEROSA, CHE SAPPIA VALORIZZARLA E VIVERLA APPIENO.
LA POSSIBILITA'' DI RAGGIUNGERE IL CENTRO CITTA'' E LA CITTADELA DEGLI STUDI COMODAMENTE A PIEDI, NE FA UN PLUS IMPORTANTE, PER QUESTA SOLUZIONE DA VEDERE!.', 'Vendita', 270000, 135, 8, 2, 1976, '2025-02-05', 'Cosenza', 39.258526, 16.6999344, '{immobiliImages/53/casa7foto12.png,immobiliImages/53/casa7foto0.png,immobiliImages/53/casa7foto1.png,immobiliImages/53/casa7foto2.png,immobiliImages/53/casa7foto3.png,immobiliImages/53/casa7foto4.png,immobiliImages/53/casa7foto5.png,immobiliImages/53/casa7foto6.png,immobiliImages/53/casa7foto7.png,immobiliImages/53/casa7foto8.png,immobiliImages/53/casa7foto9.png,immobiliImages/53/casa7foto10.png,immobiliImages/53/casa7foto11.png,immobiliImages/53/casa7foto14.png,immobiliImages/53/casa7foto13.png}', 'KristianReale', NULL);
INSERT INTO public.immobile VALUES (45, 'Vendita appartamento con terrazza e garage', 'Appartamento', ' In posizione centralissima e tranquilla, proponiamo appartamento al piano secondo ed ultimo composto da ampio soggiorno con angolo cottura con terrazzo abitabile, camera matrimoniale con terrazzino, camera singola con balcone, ripostiglio e bagno.
Al piano superiore troviamo due camere da letto (una singola ed una matrimoniale) con un''ulteriore terrazza con vista sul castello e il secondo bagno.
Completa la proprietà una cantina e un ampio garage al piano interrato.
L''appartamento si presenta in ottime condizioni e quindi abitabile da subito, grazie ai numerosi lavori di ristrutturazione recentemente eseguiti.', 'Vendita', 300000, 160, 9, 2, 1983, '2025-02-05', 'Reggio Calabria', 38.3052555, 16.3199146, '{immobiliImages/45/casa1foto4.png,immobiliImages/45/casa1foto0.png,immobiliImages/45/casa1foto1.png,immobiliImages/45/casa1foto2.png,immobiliImages/45/casa1foto3.png,immobiliImages/45/casa1foto5.png,immobiliImages/45/casa1foto6.png,immobiliImages/45/casa1foto7.png,immobiliImages/45/casa1foto8.png,immobiliImages/45/casa1foto9.png}', 'SimoneCozza', NULL);


--
-- TOC entry 4879 (class 0 OID 17150)
-- Dependencies: 223
-- Data for Name: messaggio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.messaggio VALUES (5, ' Richiesta informazioni per Vendita villetta con posto auto', 'Buongiorno Francesco,
sono interessato all''immobile situato a Catanzaro, che ho visto nel suo annuncio. Vorrei ricevere maggiori dettagli riguardo la disposizione degli spazi, eventuali spese condominiali e lo stato generale dell’immobile.
Sarebbe possibile fissare un appuntamento per una visita?
Resto in attesa di un suo gentile riscontro.

Cordiali saluti,', 'SimoneCozza', 'simocozza02@hotmail.it', 3490767079, 51);
INSERT INTO public.messaggio VALUES (6, 'Interesse per l’immobile in vendita a Catanzaro', 'Buongiorno,
ho visto il suo annuncio per l’immobile in vendita a Badolato Marina e sarei molto interessato a saperne di più. Potrebbe fornirmi maggiori dettagli su metratura, condizioni interne e prezzo trattabile?
Se possibile, vorrei anche organizzare una visita nei prossimi giorni.

Grazie in anticipo.', 'SimoneCozza', 'simocozza02@hotmail.it', 3490767079, 48);
INSERT INTO public.messaggio VALUES (7, 'Disponibilità per una visita all''immobile a Mammola', 'Salve [Nome Venditore],
sono molto interessato all’immobile che ha messo in vendita a Mammola (RC). Vorrei sapere se è ancora disponibile e quali sono i dettagli principali (certificazioni energetiche, eventuali spese accessorie).
Sarebbe possibile organizzare una visita nei prossimi giorni?

Grazie per la disponibilità.', 'MirkoSonotaca', 'sonotacamirko@gmail.com', 3479975255, 46);
INSERT INTO public.messaggio VALUES (8, 'Interesse per la villa in vendita a Catanzaro', 'Buongiorno,

ho visto il suo annuncio per la villa situata in [indirizzo/località] e sono molto interessato. Vorrei ricevere maggiori informazioni riguardo la superficie totale, lo stato della proprietà, eventuali lavori di ristrutturazione recenti e i servizi inclusi (giardino, piscina, garage, ecc.).

Sarebbe possibile fissare un appuntamento per una visita nei prossimi giorni?
Attendo un suo gentile riscontro.

Cordiali saluti.', 'MirkoSonotaca', 'sonotacamirko@gmail.com', 3479975255, 51);


--
-- TOC entry 4873 (class 0 OID 17132)
-- Dependencies: 217
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.recensione VALUES (5, 4, 'Mi sono ritrovata di fronte una persona competente e preparata nel suo lavoro. Confermando la vendita dell'' immobile nel tempo stabilito. Grazie.', 'FrancescoCristiano', 46);
INSERT INTO public.recensione VALUES (7, 5, 'Eccellente esperienza!
Ho acquistato casa grazie a Simone e non potrei essere più soddisfatto! Professionale, disponibile e sempre pronto a rispondere a ogni mia domanda. Ha seguito ogni fase con cura, rendendo il processo di acquisto semplice e senza stress. Consigliatissimo!', 'FrancescoCristiano', 45);
INSERT INTO public.recensione VALUES (8, 3, 'Buono, ma migliorabile
Il venditore è stato abbastanza disponibile, ma ho dovuto sollecitare più volte per ricevere alcuni documenti. L''immobile corrispondeva alla descrizione, ma mi aspettavo un po'' più di supporto nella fase finale dell''acquisto.', 'MirkoSonotaca', 46);
INSERT INTO public.recensione VALUES (9, 2, 'Esperienza deludente
Purtroppo, la comunicazione non è stata delle migliori. Ho avuto difficoltà a ottenere risposte rapide e alcuni dettagli importanti mi sono stati comunicati all’ultimo momento. Sicuramente c''è margine di miglioramento.', 'MirkoSonotaca', 50);
INSERT INTO public.recensione VALUES (10, 4, 'Molto bravo, ma con qualche ritardo.
Il venditore è stato molto professionale e ha trovato l''immobile ideale per me. L''unico aspetto da migliorare è la rapidità nelle risposte: alcune informazioni sono arrivate con un po'' di ritardo. Per il resto, esperienza positiva!', 'SimoneCozza', 50);
INSERT INTO public.recensione VALUES (11, 3, 'Né bene né male
Venditore competente, ma ho avuto l’impressione che pensasse più alla vendita che alle mie reali esigenze. Alla fine ho acquistato, ma con qualche dubbio che avrei preferito chiarire prima.

', 'SimoneCozza', 51);
INSERT INTO public.recensione VALUES (6, 5, 'Sono stato seguito ad ogni passaggio e la comunicazione è stata perfetta. Mirko è stato un agente molto valido che auguro a tutti di incontrare, non posso che raccomandare i servizi dell''azienda.', 'FrancescoCristiano', 49);


--
-- TOC entry 4881 (class 0 OID 17156)
-- Dependencies: 225
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utente VALUES ('MirkoSonotaca', '$2a$10$BKOLuR.CwzKCPmcjfwUvsekON/rV1NP/0NxsX8ZKtpNYwt4HGwdEa', 'ROLE_USER', 'Mirko', 'Sonotaca', '2003-03-29', 'Italiana', 'sonotacamirko@gmail.com', 'Reggio Calabria', 'Locri', '3479975255', 'via dei giardini , 186', 'male', '89046', 'SNTMRK03C29D976R');
INSERT INTO public.utente VALUES ('KristianReale', '$2a$10$Oi2.MA91DtvLI6pjpG2k8.WnkhAOqUCPDPa01biVbO5xSFPb2GDpu', 'ROLE_USER', 'Kristian', 'Reale', '1985-08-22', 'Italia', 'kristianreale@gmail.com', 'Cosenza', 'Altomonte', '3335465822', 'Via arnaldo gerry 22', 'male', '87055', 'RLKRSTN02C54P668');
INSERT INTO public.utente VALUES ('MarcoForte', '$2a$10$cA4PgRxciLTnc0Ad.fx5p.olgNhwVtnnMWtwe4WQ8hpYDTgSfrSUO', 'ROLE_USER', 'Marco', 'Forte', '1999-06-07', 'Italiana', 'marcoforte@gmail.com', 'Reggio Calabria', 'Gioiosa Ionica', '3365458927', 'Via Nazionale 11', 'male', '89046', 'FRTMRC09P55C546W');
INSERT INTO public.utente VALUES ('FrancescoCristiano', '$2a$10$Yz7C.VDO3S7fr5H96o/X6uhYX5L.LY.IZ1HLmUjgrPKZgg/WIluWa', 'ROLE_USER', 'Francesco', 'Cristiano', '2003-09-08', 'Italiana', 'francesco.cristiano@hotmail.com', 'Catanzaro', 'Lametia', '3659985462', 'Via dei Cristiani 43', 'male', '87052', 'CRSTFRNCS58A88A6');
INSERT INTO public.utente VALUES ('GiuseppeRudi', '$2a$10$MBRa1qBPuY5Mci6G6cpXA.eW2HsKvMvXuBRkQtDWcY9ueiI9WNARC', 'ROLE_ADMIN', 'Giuseppe', 'Rudi', '2003-06-25', 'Italiana', 'rudigiuseppe@gmail.com', 'Catanzaro', 'Badolato', '3311311109', 'Via delle gardenie 22, Badolato, CZ', 'male', '88060', 'RDUGPP03H25I872W');
INSERT INTO public.utente VALUES ('SimoneCozza', '$2a$10$DUDRSxmg2QO.v7CLX/f2YeNmuTvBMtlyZFmKWZdoqoRQVa.HCJ/Ye', 'ROLE_USER', 'Simone', 'Cozza', '2002-09-07', 'Italiana', 'simocozza02@hotmail.it', 'Cosenza', 'Castrovillari', '3490767079', 'via silvio pastani,12', 'male', '87012', 'CZZSMN02P07C349D');


--
-- TOC entry 4894 (class 0 OID 0)
-- Dependencies: 220
-- Name: contatti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contatti_id_seq', 5, true);


--
-- TOC entry 4895 (class 0 OID 0)
-- Dependencies: 222
-- Name: immobili_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.immobili_id_seq', 55, true);


--
-- TOC entry 4896 (class 0 OID 0)
-- Dependencies: 224
-- Name: messaggio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messaggio_id_seq', 8, true);


--
-- TOC entry 4897 (class 0 OID 0)
-- Dependencies: 218
-- Name: recensione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_seq', 11, true);


--
-- TOC entry 4719 (class 2606 OID 17166)
-- Name: contatti contatti_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti
    ADD CONSTRAINT contatti_email_key UNIQUE (email);


--
-- TOC entry 4721 (class 2606 OID 17168)
-- Name: contatti contatti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contatti
    ADD CONSTRAINT contatti_pkey PRIMARY KEY (id);


--
-- TOC entry 4723 (class 2606 OID 17170)
-- Name: immobile immobili_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.immobile
    ADD CONSTRAINT immobili_pkey PRIMARY KEY (id);


--
-- TOC entry 4725 (class 2606 OID 17172)
-- Name: messaggio messaggio_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messaggio
    ADD CONSTRAINT messaggio_pk PRIMARY KEY (id);


--
-- TOC entry 4727 (class 2606 OID 17174)
-- Name: utente utente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (username);


--
-- TOC entry 4889 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2025-02-06 12:13:40

--
-- PostgreSQL database dump complete
--

