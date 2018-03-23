--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 10.1

-- Started on 2018-03-22 20:46:59 -05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2679 (class 1262 OID 125942)
-- Name: sigc_sacha_dev; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE sigc_sacha_dev WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';


ALTER DATABASE sigc_sacha_dev OWNER TO postgres;

\connect sigc_sacha_dev

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12018)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2682 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 193 (class 1259 OID 126365)
-- Name: cat_adm_parametros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_adm_parametros (
    par_cod_secuencial integer NOT NULL,
    par_cod_parametro character varying(100),
    par_nom_descripcion text,
    par_nom_valor character varying(500),
    par_cod_grupo character varying(100),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_adm_parametros OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 126363)
-- Name: cat_adm_parametros_par_cod_secuencial_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_adm_parametros_par_cod_secuencial_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_adm_parametros_par_cod_secuencial_seq OWNER TO postgres;

--
-- TOC entry 2683 (class 0 OID 0)
-- Dependencies: 192
-- Name: cat_adm_parametros_par_cod_secuencial_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_adm_parametros_par_cod_secuencial_seq OWNED BY cat_adm_parametros.par_cod_secuencial;


--
-- TOC entry 178 (class 1259 OID 126287)
-- Name: cat_adm_reportes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_adm_reportes (
    rep_cod_secuencial integer NOT NULL,
    rep_nom_reporte character varying(100),
    rep_nom_archivo_reporte character varying(250),
    rep_nom_titulo character varying(500),
    rep_nom_archivo_descarga character varying(250)
);


ALTER TABLE cat_adm_reportes OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 126285)
-- Name: cat_adm_reportes_rep_cod_secuencial_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_adm_reportes_rep_cod_secuencial_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_adm_reportes_rep_cod_secuencial_seq OWNER TO postgres;

--
-- TOC entry 2684 (class 0 OID 0)
-- Dependencies: 177
-- Name: cat_adm_reportes_rep_cod_secuencial_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_adm_reportes_rep_cod_secuencial_seq OWNED BY cat_adm_reportes.rep_cod_secuencial;


--
-- TOC entry 198 (class 1259 OID 126390)
-- Name: cat_cat_arriendos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_arriendos (
    cod_predioarriendos integer NOT NULL,
    cod_catastral integer,
    sts_arriendo character(25),
    sts_arriendocontrato character(25),
    sts_arriendoregistro character(25),
    fec_arriendoinicio date,
    fec_arriendofin date,
    val_arriendovalor numeric(12,2)
);


ALTER TABLE cat_cat_arriendos OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 126619)
-- Name: cat_cat_avaluo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_avaluo (
    aval_id integer NOT NULL,
    cod_catastral integer NOT NULL,
    fecav_id integer NOT NULL,
    nomnomape character varying(100),
    cod_cedularuc character(15),
    nom_codigocatastral character(25),
    sts_barrio character(150),
    txt_direccion character(100),
    val_areapredio numeric(12,4),
    val_terreno numeric(18,2),
    val_areaconstruccion numeric(12,4),
    val_edifica numeric(18,2),
    val_predio numeric(18,2),
    val_impuesto numeric(18,2),
    val_cem numeric(18,2),
    val_noedifica numeric(18,2),
    val_emision numeric(18,2),
    val_bomberos numeric(18,2),
    val_ambientales numeric(18,2),
    val_imppredial numeric(18,2),
    cat_casosespeciales character(50),
    aval_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    val_prom_factores numeric(18,2),
    val_precio_base numeric(18,2),
    val_descuento_exoneracion numeric(18,2),
    val_construccion_obsoleta numeric(18,2)
);


ALTER TABLE cat_cat_avaluo OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 126617)
-- Name: cat_cat_avaluo_aval_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_avaluo_aval_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_avaluo_aval_id_seq OWNER TO postgres;

--
-- TOC entry 2685 (class 0 OID 0)
-- Dependencies: 245
-- Name: cat_cat_avaluo_aval_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_avaluo_aval_id_seq OWNED BY cat_cat_avaluo.aval_id;


--
-- TOC entry 229 (class 1259 OID 126541)
-- Name: cat_cat_bloques; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_bloques (
    cod_bloques integer NOT NULL,
    cod_catastral integer NOT NULL,
    nom_bloque character(25),
    val_nropisos character(10),
    val_areabloque numeric(18,4),
    val_bloque numeric(18,2),
    blo_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_bloques OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 126539)
-- Name: cat_cat_bloques_cod_bloques_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_bloques_cod_bloques_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_bloques_cod_bloques_seq OWNER TO postgres;

--
-- TOC entry 2686 (class 0 OID 0)
-- Dependencies: 228
-- Name: cat_cat_bloques_cod_bloques_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_bloques_cod_bloques_seq OWNED BY cat_cat_bloques.cod_bloques;


--
-- TOC entry 222 (class 1259 OID 126507)
-- Name: cat_cat_descuentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_descuentos (
    cod_descuento integer NOT NULL,
    cod_catastral integer NOT NULL,
    sts_codigo character(25),
    sts_grupo character(100),
    sts_subgrupo character(100),
    sts_descripcion character(100),
    desc_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_descuentos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 126505)
-- Name: cat_cat_descuentos_cod_descuento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_descuentos_cod_descuento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_descuentos_cod_descuento_seq OWNER TO postgres;

--
-- TOC entry 2687 (class 0 OID 0)
-- Dependencies: 221
-- Name: cat_cat_descuentos_cod_descuento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_descuentos_cod_descuento_seq OWNED BY cat_cat_descuentos.cod_descuento;


--
-- TOC entry 227 (class 1259 OID 126534)
-- Name: cat_cat_destinopredio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_destinopredio (
    det_secuencial numeric(10,0) NOT NULL,
    cat_secuencial numeric(10,0),
    des_predio character(50),
    des_codpredio character(50)
);


ALTER TABLE cat_cat_destinopredio OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 126611)
-- Name: cat_cat_detalles_avaluo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_detalles_avaluo (
    daval_id integer NOT NULL,
    daval_padre character varying(16),
    daval_relacion character varying(10),
    daval_valor character varying(100),
    daval_factor character varying(16),
    daval_descripcion character varying(100),
    cod_catastral integer NOT NULL,
    fecav_id integer NOT NULL,
    daval_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_detalles_avaluo OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 126609)
-- Name: cat_cat_detalles_avaluo_daval_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_detalles_avaluo_daval_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_detalles_avaluo_daval_id_seq OWNER TO postgres;

--
-- TOC entry 2688 (class 0 OID 0)
-- Dependencies: 243
-- Name: cat_cat_detalles_avaluo_daval_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_detalles_avaluo_daval_id_seq OWNED BY cat_cat_detalles_avaluo.daval_id;


--
-- TOC entry 191 (class 1259 OID 126352)
-- Name: cat_cat_dominios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_dominios (
    domi_id integer NOT NULL,
    domi_codigo character varying(16),
    domi_padre character varying(16),
    domi_relacion character varying(10),
    domi_descripcion character varying(100),
    domi_grupos character varying(30),
    domi_calculo character varying(50),
    domi_depreciacion character varying(10),
    domi_codame character varying(10),
    domi_coefic numeric(12,4),
    domi_unidad character varying(16),
    domi_estado character varying(10),
    domi_ficha character varying(16),
    domi_tipo character varying(10),
    domi_minimo numeric(12,4),
    domi_maximo numeric(12,4),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    domi_estado_registro character(1)
);


ALTER TABLE cat_cat_dominios OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 126350)
-- Name: cat_cat_dominios_domi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_dominios_domi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_dominios_domi_id_seq OWNER TO postgres;

--
-- TOC entry 2689 (class 0 OID 0)
-- Dependencies: 190
-- Name: cat_cat_dominios_domi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_dominios_domi_id_seq OWNED BY cat_cat_dominios.domi_id;


--
-- TOC entry 172 (class 1259 OID 126258)
-- Name: cat_cat_dpa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_dpa (
    cod_dpa character(6) NOT NULL,
    cod_nivel character(25),
    cod_provincia character(2),
    cod_canton character(4),
    nom_descripcion character(100),
    sts_estado smallint
);


ALTER TABLE cat_cat_dpa OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 126603)
-- Name: cat_cat_fechaavaluo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_fechaavaluo (
    fecav_id integer NOT NULL,
    fecav_fechaavaluo date,
    fecav_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    feav_descripcion character varying(100)
);


ALTER TABLE cat_cat_fechaavaluo OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 126601)
-- Name: cat_cat_fechaavaluo_fecav_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_fechaavaluo_fecav_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_fechaavaluo_fecav_id_seq OWNER TO postgres;

--
-- TOC entry 2690 (class 0 OID 0)
-- Dependencies: 241
-- Name: cat_cat_fechaavaluo_fecav_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_fechaavaluo_fecav_id_seq OWNED BY cat_cat_fechaavaluo.fecav_id;


--
-- TOC entry 184 (class 1259 OID 126320)
-- Name: cat_cat_financiamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_financiamiento (
    cod_financiamiento integer NOT NULL,
    cod_catastral integer,
    fin_fecha timestamp without time zone,
    fin_fuente character(50),
    fin_monto numeric(18,2),
    fin_plazo integer,
    cod_usuario integer
);


ALTER TABLE cat_cat_financiamiento OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 126428)
-- Name: cat_cat_fotos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_fotos (
    cod_fotos integer NOT NULL,
    cod_catastral integer NOT NULL,
    txt_descripcion character(100),
    txt_archivo character(150),
    foto_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_fotos OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 126426)
-- Name: cat_cat_fotos_cod_fotos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_fotos_cod_fotos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_fotos_cod_fotos_seq OWNER TO postgres;

--
-- TOC entry 2691 (class 0 OID 0)
-- Dependencies: 205
-- Name: cat_cat_fotos_cod_fotos_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_fotos_cod_fotos_seq OWNED BY cat_cat_fotos.cod_fotos;


--
-- TOC entry 202 (class 1259 OID 126409)
-- Name: cat_cat_log_predio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_log_predio (
    cod_log_predio integer NOT NULL,
    cod_catastral integer NOT NULL,
    cod_usu character varying(50),
    fec_log date,
    nom_ip character varying(30),
    txt_log text,
    log_accion character varying(50),
    log_estado character(1)
);


ALTER TABLE cat_cat_log_predio OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 126407)
-- Name: cat_cat_log_predio_cod_log_predio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_log_predio_cod_log_predio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_log_predio_cod_log_predio_seq OWNER TO postgres;

--
-- TOC entry 2692 (class 0 OID 0)
-- Dependencies: 201
-- Name: cat_cat_log_predio_cod_log_predio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_log_predio_cod_log_predio_seq OWNED BY cat_cat_log_predio.cod_log_predio;


--
-- TOC entry 180 (class 1259 OID 126302)
-- Name: cat_cat_maquinariaequipo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_maquinariaequipo (
    cod_maquinariaequipo integer NOT NULL,
    cod_catastral integer,
    sts_codigo character(25),
    txt_denominacion character(100),
    txt_marca character(100),
    sts_anio character(10),
    sts_estado character(25),
    sts_cantidad integer,
    val_unitario numeric(12,2),
    val_total numeric(12,2)
);


ALTER TABLE cat_cat_maquinariaequipo OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 126265)
-- Name: cat_cat_obras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_obras (
    cod_obras integer NOT NULL,
    nom_obras character(250),
    val_anioaplicacion integer,
    val_anioobra integer,
    val_aniodeprecia integer,
    txt_detalle text,
    val_porcentaje numeric(18,4),
    val_valor numeric(18,2),
    sts_aplicacionforma character(25),
    val_acobrar numeric(18,2),
    val_porcentajefrentistas numeric(18,2),
    val_porcentajeavaluo numeric(18,2),
    obr_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    sts_porcentajeaplica boolean,
    nom_obras_corto character(50)
);


ALTER TABLE cat_cat_obras OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 126263)
-- Name: cat_cat_obras_cod_obras_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_obras_cod_obras_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_obras_cod_obras_seq OWNER TO postgres;

--
-- TOC entry 2693 (class 0 OID 0)
-- Dependencies: 173
-- Name: cat_cat_obras_cod_obras_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_obras_cod_obras_seq OWNED BY cat_cat_obras.cod_obras;


--
-- TOC entry 210 (class 1259 OID 126447)
-- Name: cat_cat_obrasdetalle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_obrasdetalle (
    cod_obrasdetalle integer NOT NULL,
    cod_obras integer NOT NULL,
    cod_catastral integer NOT NULL,
    cod_predio integer,
    nom_codigocatastral character(25),
    obr_valor numeric(18,2),
    val_areafrente numeric(18,2),
    val_predio numeric(18,2),
    obd_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_obrasdetalle OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 126445)
-- Name: cat_cat_obrasdetalle_cod_obrasdetalle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_obrasdetalle_cod_obrasdetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_obrasdetalle_cod_obrasdetalle_seq OWNER TO postgres;

--
-- TOC entry 2694 (class 0 OID 0)
-- Dependencies: 209
-- Name: cat_cat_obrasdetalle_cod_obrasdetalle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_obrasdetalle_cod_obrasdetalle_seq OWNED BY cat_cat_obrasdetalle.cod_obrasdetalle;


--
-- TOC entry 197 (class 1259 OID 126384)
-- Name: cat_cat_parametros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_parametros (
    cod_parsecuencial integer NOT NULL,
    nom_grupo character varying(50),
    val_orden integer,
    cod_parametro character varying(25),
    nom_descripcion character varying(150)
);


ALTER TABLE cat_cat_parametros OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 126382)
-- Name: cat_cat_parametros_cod_parsecuencial_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_parametros_cod_parsecuencial_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_parametros_cod_parsecuencial_seq OWNER TO postgres;

--
-- TOC entry 2695 (class 0 OID 0)
-- Dependencies: 196
-- Name: cat_cat_parametros_cod_parsecuencial_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_parametros_cod_parsecuencial_seq OWNED BY cat_cat_parametros.cod_parsecuencial;


--
-- TOC entry 231 (class 1259 OID 126549)
-- Name: cat_cat_pisos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_pisos (
    cod_pisos integer NOT NULL,
    cod_bloques integer NOT NULL,
    nom_piso character(25),
    val_areapiso numeric(12,4),
    val_anioconstruccion integer,
    val_anioreparacion integer,
    val_pisonumero integer,
    sts_estado character(25),
    val_factordepreciacion numeric(18,4),
    val_facestado numeric(18,4),
    val_sumafactores numeric(18,4),
    val_constante numeric(18,4),
    val_metro2 numeric(18,4),
    val_piso numeric(18,4),
    pis_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    edad character(10)
);


ALTER TABLE cat_cat_pisos OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 126547)
-- Name: cat_cat_pisos_cod_pisos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_pisos_cod_pisos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_pisos_cod_pisos_seq OWNER TO postgres;

--
-- TOC entry 2696 (class 0 OID 0)
-- Dependencies: 230
-- Name: cat_cat_pisos_cod_pisos_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_pisos_cod_pisos_seq OWNED BY cat_cat_pisos.cod_pisos;


--
-- TOC entry 233 (class 1259 OID 126557)
-- Name: cat_cat_pisosdetalle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_pisosdetalle (
    cod_pisodetalle integer NOT NULL,
    cod_pisos integer NOT NULL,
    sts_codigo character(25),
    sts_grupo character(100),
    sts_subgrupo character(100),
    sts_descripcion character(100),
    sts_estado character(10),
    pid_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_pisosdetalle OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 126555)
-- Name: cat_cat_pisosdetalle_cod_pisodetalle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_pisosdetalle_cod_pisodetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_pisosdetalle_cod_pisodetalle_seq OWNER TO postgres;

--
-- TOC entry 2697 (class 0 OID 0)
-- Dependencies: 232
-- Name: cat_cat_pisosdetalle_cod_pisodetalle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_pisosdetalle_cod_pisodetalle_seq OWNED BY cat_cat_pisosdetalle.cod_pisodetalle;


--
-- TOC entry 200 (class 1259 OID 126397)
-- Name: cat_cat_predios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_predios (
    cod_catastral integer NOT NULL,
    sts_tipo character(25),
    nom_codigocatastral character(25),
    nom_codigocatastralanterior character(100),
    cod_dpa character(6),
    cod_zona character(2),
    cod_sector character(2),
    cod_manzana character(3),
    cod_predio character(4),
    cod_regimentenencia character(2),
    cod_horizontal character(3),
    nom_predio character(100),
    sts_urbanomarginal character(25),
    sts_sectorhomogeneo character(25),
    sts_barrio character(150),
    txt_direccion character(100),
    txt_ubicacion character(100),
    val_areapredio numeric(12,4),
    val_areafrente numeric(12,4),
    val_areafondo numeric(12,4),
    val_areaconstruccion numeric(12,4),
    sts_planospredio character(25),
    val_edifica numeric(18,2),
    val_predio numeric(18,2),
    val_impuesto numeric(18,2),
    val_bomberos numeric(18,2),
    val_emision numeric(18,2),
    val_cem numeric(18,2),
    val_noedifica numeric(18,2),
    val_ambientales numeric(18,2),
    val_imppredial numeric(18,2),
    val_otro1 numeric(18,2),
    val_otro2 numeric(18,2),
    cat_casosespeciales character(50),
    txt_observacion text,
    cat_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    nom_numero character(20),
    txt_norte character(100),
    txt_sur character(100),
    txt_este character varying(100),
    txt_oeste character(100),
    txt_dominio character varying(25),
    nom_informante character(50),
    nom_intervenido character(50),
    nom_cartografia character(50),
    nom_fotoaerea character(50),
    nom_cartootros character(50),
    val_coordenadaeste numeric(18,4),
    val_coordenadanorte numeric(18,4),
    cod_campo character(10),
    nom_propietarioant character(100),
    sts_ubicacioninfluencia character(1),
    val_terreno numeric(18,2),
    val_basura numeric(18,2),
    val_arecaudar numeric(18,2),
    val_coelote numeric(18,2),
    val_coeservicios numeric(18,2),
    val_coeestructura numeric(18,2),
    val_coeacabados numeric(18,2),
    val_coeextras numeric(18,2)
);


ALTER TABLE cat_cat_predios OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 126395)
-- Name: cat_cat_predios_cod_catastral_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_predios_cod_catastral_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_predios_cod_catastral_seq OWNER TO postgres;

--
-- TOC entry 2698 (class 0 OID 0)
-- Dependencies: 199
-- Name: cat_cat_predios_cod_catastral_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_predios_cod_catastral_seq OWNED BY cat_cat_predios.cod_catastral;


--
-- TOC entry 189 (class 1259 OID 126345)
-- Name: cat_cat_recursoshidricos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_recursoshidricos (
    cod_recursoshidricos integer NOT NULL,
    cod_catastral integer,
    sts_grupo character(100),
    txt_recursos character(100)
);


ALTER TABLE cat_cat_recursoshidricos OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 126335)
-- Name: cat_cat_semovientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_semovientes (
    cod_semovientes integer NOT NULL,
    cod_catastral integer,
    sts_codigo character(25),
    sts_especie character(60),
    sts_raza character(60),
    sts_sangre character(60),
    val_edad integer,
    val_cantidad integer,
    val_valorunitario numeric(12,2),
    val_valortotal numeric(12,2),
    sts_clasificaedad character(60),
    sts_calsificaespecie character(60)
);


ALTER TABLE cat_cat_semovientes OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 126436)
-- Name: cat_cat_servicios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_servicios (
    cod_servicios integer NOT NULL,
    cod_catastral integer NOT NULL,
    sts_codigo character(25),
    sts_grupo character(100),
    sts_subgrupo character(100),
    sts_descripcion character(100),
    sts_estado character(25),
    nom_medidor character(50),
    cod_usuario integer,
    num_medidores integer,
    ser_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_servicios OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 126434)
-- Name: cat_cat_servicios_cod_servicios_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_servicios_cod_servicios_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_servicios_cod_servicios_seq OWNER TO postgres;

--
-- TOC entry 2699 (class 0 OID 0)
-- Dependencies: 207
-- Name: cat_cat_servicios_cod_servicios_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_servicios_cod_servicios_seq OWNED BY cat_cat_servicios.cod_servicios;


--
-- TOC entry 204 (class 1259 OID 126420)
-- Name: cat_cat_terreno; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_terreno (
    cod_terrenodetalle integer NOT NULL,
    cod_catastral integer NOT NULL,
    sts_codigo character(25),
    sts_grupo character(100),
    sts_subgrupo character(100),
    sts_descripcion character(100),
    cod_usuario integer,
    ter_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_terreno OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 126418)
-- Name: cat_cat_terreno_cod_terrenodetalle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_terreno_cod_terrenodetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_terreno_cod_terrenodetalle_seq OWNER TO postgres;

--
-- TOC entry 2700 (class 0 OID 0)
-- Dependencies: 203
-- Name: cat_cat_terreno_cod_terrenodetalle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_terreno_cod_terrenodetalle_seq OWNED BY cat_cat_terreno.cod_terrenodetalle;


--
-- TOC entry 220 (class 1259 OID 126496)
-- Name: cat_cat_titulo_movimientos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_titulo_movimientos (
    cod_movimiento integer NOT NULL,
    cod_titulos integer NOT NULL,
    anio_emision character(5),
    fec_movimiento timestamp without time zone,
    nom_usu character varying(50),
    ip_usu character varying(30),
    txt_movimiento text,
    txt_razon text,
    estado_movimiento character(1),
    estado_titulo character(50)
);


ALTER TABLE cat_cat_titulo_movimientos OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 126494)
-- Name: cat_cat_titulo_movimientos_cod_movimiento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_titulo_movimientos_cod_movimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_titulo_movimientos_cod_movimiento_seq OWNER TO postgres;

--
-- TOC entry 2701 (class 0 OID 0)
-- Dependencies: 219
-- Name: cat_cat_titulo_movimientos_cod_movimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_titulo_movimientos_cod_movimiento_seq OWNED BY cat_cat_titulo_movimientos.cod_movimiento;


--
-- TOC entry 218 (class 1259 OID 126485)
-- Name: cat_cat_titulos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_titulos (
    cod_titulos integer NOT NULL,
    cod_catastral integer NOT NULL,
    nom_codigocatastral character(25),
    cod_propietarios integer NOT NULL,
    sts_casosexcepcion character(25),
    usu_codigo integer,
    fec_emision timestamp without time zone,
    fec_ultimomovimiento timestamp without time zone,
    fec_fpago timestamp without time zone,
    sts_anioavaluo character(5),
    sts_tipo character(25),
    sts_estado character(25),
    val_nroimpresion integer,
    txt_barrio character(150),
    txt_direccion character(100),
    val_areaterreno numeric(18,4),
    val_areaconstruccion numeric(18,4),
    val_valorterreno numeric(18,2),
    val_construccion numeric(18,2),
    val_baseimponible numeric(18,2),
    val_impuestopredial numeric(18,2),
    val_bomberos numeric(18,2),
    val_saludpublica numeric(18,2),
    val_educacion numeric(18,2),
    val_acilo numeric(18,2),
    val_rrp numeric(18,2),
    val_interes numeric(18,2),
    val_descuento numeric(18,2),
    val_prestamo numeric(18,2),
    val_serviciosadministrativos numeric(18,2),
    val_recoleccionbasura numeric(18,2),
    val_impuestomasadicionales numeric(18,2),
    val_seguridad numeric(18,2),
    val_cem numeric(18,2),
    val_noconstruido numeric(18,2),
    val_totalapagar numeric(18,2),
    val_descuentos numeric(18,2),
    val_pagado numeric(18,2),
    val_rebaja numeric(18,2),
    val_totalexcepciones numeric(18,2),
    val_interesaplicado numeric(18,2),
    val_descuentoaplicado numeric(18,2),
    titulo_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    cod_secuencial character(30),
    val_descuento_exoneracion numeric(18,2),
    val_construccion_obsoleta numeric(18,2)
);


ALTER TABLE cat_cat_titulos OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 126483)
-- Name: cat_cat_titulos_cod_titulos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_titulos_cod_titulos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_titulos_cod_titulos_seq OWNER TO postgres;

--
-- TOC entry 2702 (class 0 OID 0)
-- Dependencies: 217
-- Name: cat_cat_titulos_cod_titulos_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_titulos_cod_titulos_seq OWNED BY cat_cat_titulos.cod_titulos;


--
-- TOC entry 225 (class 1259 OID 126520)
-- Name: cat_cat_usosuelo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_usosuelo (
    cod_usopredio integer NOT NULL,
    cod_catastral integer NOT NULL,
    sts_codigo character varying(25),
    sts_grupo character varying(100),
    sts_subgrupo character varying(100),
    sts_descripcion character varying(100),
    sts_estado character varying(25),
    con_usuario integer,
    uss_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_cat_usosuelo OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 126518)
-- Name: cat_cat_usosuelo_cod_usopredio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_cat_usosuelo_cod_usopredio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_cat_usosuelo_cod_usopredio_seq OWNER TO postgres;

--
-- TOC entry 2703 (class 0 OID 0)
-- Dependencies: 224
-- Name: cat_cat_usosuelo_cod_usopredio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_cat_usosuelo_cod_usopredio_seq OWNED BY cat_cat_usosuelo.cod_usopredio;


--
-- TOC entry 183 (class 1259 OID 126315)
-- Name: cat_cat_usotierra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_usotierra (
    cod_usotierra integer NOT NULL,
    cod_catastral integer,
    cod_uso character(25),
    sts_tipo character(100),
    nom_detalleuso character(100),
    val_area numeric(12,4),
    val_edad integer,
    sts_estado character(25),
    val_densidad numeric(12,2),
    val_valorunitario numeric(12,2),
    val_superficieconriego numeric(12,4),
    val_superficiesinriego numeric(12,4),
    sts_clasetierra character(25),
    sts_grupo character(100),
    val_valortotal numeric(12,2),
    val_distanciaplantas numeric(10,2),
    val_totalplantas integer,
    val_distanciasurco numeric(10,2),
    sts_zonaagroecologica character(25),
    sts_geoeconomica character(25)
);


ALTER TABLE cat_cat_usotierra OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 126596)
-- Name: cat_cat_valoracionambiental; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_valoracionambiental (
    cod_valoracionambiental integer NOT NULL,
    cod_catastral integer,
    sts_factorambiental character(25),
    val_superficie numeric(12,4),
    val_puntaje numeric(12,2)
);


ALTER TABLE cat_cat_valoracionambiental OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 126325)
-- Name: cat_cat_valoracionforestal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_valoracionforestal (
    cod_valoracionforestal integer NOT NULL,
    cod_catastral integer,
    sts_zona character(25),
    sts_clasetierra character(25),
    val_superficie numeric(12,4),
    val_preciohectarea numeric(12,2),
    sts_especiemaderable character(100),
    sts_codigoespecie character(25),
    val_edad integer,
    val_plantashas integer,
    val_totalplantas integer,
    val_dap numeric(12,2),
    val_altura numeric(12,2),
    val_volumenmadera numeric(12,2),
    val_precioporplanta numeric(12,2),
    val_preciom3enpie numeric(12,2),
    val_distanplantas numeric(12,2),
    val_distansurco numeric(12,2),
    sts_raleo character(50),
    sts_tipocalculo character(50)
);


ALTER TABLE cat_cat_valoracionforestal OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 126340)
-- Name: cat_cat_valoracionpastisal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_valoracionpastisal (
    cod_valoracionpastizal integer NOT NULL,
    cod_catastral integer,
    sts_zona character(25),
    val_superficie numeric(12,4),
    val_precio numeric(12,2),
    sts_clasetierra character(25),
    txt_nombrepasto character(100),
    sts_codigo character(50),
    sts_tipo character(25),
    val_areaconriego numeric(12,4),
    val_areasinriego numeric(12,4)
);


ALTER TABLE cat_cat_valoracionpastisal OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 126513)
-- Name: cat_cat_valortierra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_cat_valortierra (
    cod_valortierra integer NOT NULL,
    cod_catastral integer,
    sts_zona character(25),
    sts_clasetierra character(25),
    sts_tipouso character(100),
    val_superficie numeric(12,4),
    val_precio numeric(12,2),
    val_preciototal numeric(12,2),
    val_conriego numeric(12,4),
    val_sinriego numeric(12,4),
    val_rango1 integer,
    val_rango2 integer,
    val_codigo integer
);


ALTER TABLE cat_cat_valortierra OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 126276)
-- Name: cat_ciu_personeria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_ciu_personeria (
    cod_personeria integer NOT NULL,
    sts_tipopersoneria character(20),
    cod_cedularuc character(15),
    nom_apellidos character(50),
    nom_nombres character(50),
    nom_razonsocial character(100),
    txt_nacionalidad character(50),
    fec_nacimiento date,
    sts_estadocivil character(20),
    txt_direccion character(150),
    txt_telefono character(50),
    txt_email character(50),
    sts_personeria character(50),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    nom_ciudaddomicilio character(100),
    txt_ciudad character(100),
    txt_representante character(50),
    cod_representantecedula character(10),
    txt_representantedireccion character(100),
    sts_especiales character varying(50)
);


ALTER TABLE cat_ciu_personeria OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 126274)
-- Name: cat_ciu_personeria_cod_personeria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_ciu_personeria_cod_personeria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_ciu_personeria_cod_personeria_seq OWNER TO postgres;

--
-- TOC entry 2704 (class 0 OID 0)
-- Dependencies: 175
-- Name: cat_ciu_personeria_cod_personeria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_ciu_personeria_cod_personeria_seq OWNED BY cat_ciu_personeria.cod_personeria;


--
-- TOC entry 214 (class 1259 OID 126466)
-- Name: cat_ciu_propietario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_ciu_propietario (
    cod_propietarios integer NOT NULL,
    cod_catastral integer NOT NULL,
    cod_personeria integer NOT NULL,
    sts_tenencia character(50),
    sts_tenenciaotro character(50),
    sts_transferenciadominio character(50),
    val_predioareaescritura numeric(12,4),
    txt_notaria character(100),
    txt_ciudad character(50),
    txt_registronumero character(150),
    txt_detalleregistro character(50),
    txt_informante character(50),
    txt_informanterelacion character(50),
    sts_situacion character(50),
    sts_escritura character(20),
    fec_inscripcion character(10),
    fec_escritura character(20),
    fec_registro character(10),
    sts_estado character(20),
    pro_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_ciu_propietario OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 126464)
-- Name: cat_ciu_propietario_cod_propietarios_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_ciu_propietario_cod_propietarios_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_ciu_propietario_cod_propietarios_seq OWNER TO postgres;

--
-- TOC entry 2705 (class 0 OID 0)
-- Dependencies: 213
-- Name: cat_ciu_propietario_cod_propietarios_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_ciu_propietario_cod_propietarios_seq OWNED BY cat_ciu_propietario.cod_propietarios;


--
-- TOC entry 186 (class 1259 OID 126330)
-- Name: cat_ciu_relacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_ciu_relacion (
    cod_relacion integer NOT NULL,
    cod_persona integer NOT NULL,
    cod_personarelacionada integer NOT NULL,
    sts_tiporelacion character(20),
    fec_inicio date,
    fec_finaliza date
);


ALTER TABLE cat_ciu_relacion OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 126477)
-- Name: cat_ciu_tenencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_ciu_tenencia (
    cod_tenencia integer NOT NULL,
    cod_propietarios integer NOT NULL,
    sts_codigo character(25),
    sts_grupo character(100),
    sts_subgrupo character(100),
    sts_descripcion character(100),
    ten_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_ciu_tenencia OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 126475)
-- Name: cat_ciu_tenencia_cod_tenencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_ciu_tenencia_cod_tenencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_ciu_tenencia_cod_tenencia_seq OWNER TO postgres;

--
-- TOC entry 2706 (class 0 OID 0)
-- Dependencies: 215
-- Name: cat_ciu_tenencia_cod_tenencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_ciu_tenencia_cod_tenencia_seq OWNED BY cat_ciu_tenencia.cod_tenencia;


--
-- TOC entry 195 (class 1259 OID 126376)
-- Name: cat_con_constantesdescuentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_con_constantesdescuentos (
    cod_constantesdescuentos integer NOT NULL,
    sts_anio character(5),
    sts_mes character(25),
    sts_quincena character(25),
    val_valor numeric(12,2),
    sts_estado character(25),
    estado_descuento character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_con_constantesdescuentos OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 126374)
-- Name: cat_con_constantesdescuentos_cod_constantesdescuentos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_con_constantesdescuentos_cod_constantesdescuentos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_con_constantesdescuentos_cod_constantesdescuentos_seq OWNER TO postgres;

--
-- TOC entry 2707 (class 0 OID 0)
-- Dependencies: 194
-- Name: cat_con_constantesdescuentos_cod_constantesdescuentos_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_con_constantesdescuentos_cod_constantesdescuentos_seq OWNED BY cat_con_constantesdescuentos.cod_constantesdescuentos;


--
-- TOC entry 182 (class 1259 OID 126309)
-- Name: cat_con_constantesimpuestos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_con_constantesimpuestos (
    cod_constantesimpuestos integer NOT NULL,
    sts_anio character(5),
    sts_tipo character(25),
    val_bomberos numeric(12,6),
    val_serviciosadministrativos numeric(12,2),
    val_cem numeric(12,6),
    val_basura numeric(12,6),
    val_ambientales numeric(12,6),
    val_tasaaplicada numeric(12,6),
    val_construcion numeric(12,6),
    val_forestales numeric(12,6),
    val_saludpublica numeric(12,6),
    val_educacion numeric(12,6),
    val_rrp numeric(12,6),
    val_acilo numeric(12,6),
    val_patrimonio numeric(12,6),
    val_noedifica numeric(12,6),
    val_timiteinferior numeric(12,6),
    val_seguridad numeric(12,6),
    val_rebajag numeric(12,6),
    conimp_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_con_constantesimpuestos OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 126307)
-- Name: cat_con_constantesimpuestos_cod_constantesimpuestos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_con_constantesimpuestos_cod_constantesimpuestos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_con_constantesimpuestos_cod_constantesimpuestos_seq OWNER TO postgres;

--
-- TOC entry 2708 (class 0 OID 0)
-- Dependencies: 181
-- Name: cat_con_constantesimpuestos_cod_constantesimpuestos_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_con_constantesimpuestos_cod_constantesimpuestos_seq OWNED BY cat_con_constantesimpuestos.cod_constantesimpuestos;


--
-- TOC entry 179 (class 1259 OID 126296)
-- Name: cat_con_constantesinteresmora; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_con_constantesinteresmora (
    cod_interesmora integer NOT NULL,
    sts_anio character(5),
    sts_anioaplica character(5),
    val_monto numeric(12,2),
    estado_interesmora character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    num_anios integer
);


ALTER TABLE cat_con_constantesinteresmora OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 126255)
-- Name: cat_con_constantesinteresmora_cod_interesmora_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_con_constantesinteresmora_cod_interesmora_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_con_constantesinteresmora_cod_interesmora_seq OWNER TO postgres;

--
-- TOC entry 2709 (class 0 OID 0)
-- Dependencies: 171
-- Name: cat_con_constantesinteresmora_cod_interesmora_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_con_constantesinteresmora_cod_interesmora_seq OWNED BY cat_con_constantesinteresmora.cod_interesmora;


--
-- TOC entry 226 (class 1259 OID 126529)
-- Name: cat_con_preciostierras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_con_preciostierras (
    cod_preciostierras integer NOT NULL,
    val_codigo integer,
    val_rango1 double precision,
    val_rango2 double precision,
    val_precioanterior double precision,
    val_preciodolares double precision,
    val_zona character(25),
    val_destinoeconomico integer,
    sts_clasetierra character(100)
);


ALTER TABLE cat_con_preciostierras OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 126579)
-- Name: cat_seg_fun_permisos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_seg_fun_permisos (
    pef_id integer NOT NULL,
    rol_id integer NOT NULL,
    per_id integer NOT NULL,
    pef_operaciones character(1),
    pef_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_seg_fun_permisos OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 126577)
-- Name: cat_seg_fun_permisos_pef_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_seg_fun_permisos_pef_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_seg_fun_permisos_pef_id_seq OWNER TO postgres;

--
-- TOC entry 2710 (class 0 OID 0)
-- Dependencies: 236
-- Name: cat_seg_fun_permisos_pef_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_seg_fun_permisos_pef_id_seq OWNED BY cat_seg_fun_permisos.pef_id;


--
-- TOC entry 212 (class 1259 OID 126455)
-- Name: cat_seg_funcionalidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_seg_funcionalidades (
    fun_id integer NOT NULL,
    fun_nombre character varying(50),
    fun_descripcion text,
    fun_menu character varying(250),
    fun_ruta character varying(500),
    fun_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    fun_id_padre integer,
    fun_orden integer,
    fun_menu_icono character varying(30)
);


ALTER TABLE cat_seg_funcionalidades OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 126453)
-- Name: cat_seg_funcionalidades_fun_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_seg_funcionalidades_fun_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_seg_funcionalidades_fun_id_seq OWNER TO postgres;

--
-- TOC entry 2711 (class 0 OID 0)
-- Dependencies: 211
-- Name: cat_seg_funcionalidades_fun_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_seg_funcionalidades_fun_id_seq OWNED BY cat_seg_funcionalidades.fun_id;


--
-- TOC entry 235 (class 1259 OID 126568)
-- Name: cat_seg_permisos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_seg_permisos (
    per_id integer NOT NULL,
    per_grupo character(30),
    per_descripcion text,
    per_operaciones character(100),
    per_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30)
);


ALTER TABLE cat_seg_permisos OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 126566)
-- Name: cat_seg_permisos_per_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_seg_permisos_per_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_seg_permisos_per_id_seq OWNER TO postgres;

--
-- TOC entry 2712 (class 0 OID 0)
-- Dependencies: 234
-- Name: cat_seg_permisos_per_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_seg_permisos_per_id_seq OWNED BY cat_seg_permisos.per_id;


--
-- TOC entry 239 (class 1259 OID 126587)
-- Name: cat_seg_usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cat_seg_usuarios (
    usu_id integer NOT NULL,
    per_id integer NOT NULL,
    usu_codigo character(11),
    usu_apellidos character(35),
    usu_nombres character(35),
    usu_direccion character(100),
    usu_telefono character(30),
    usu_fax character(30),
    usu_emai character(30),
    usu_responsable character(254),
    usu_palabraclave character(20),
    usu_usuario character(20),
    usu_password character(20),
    usu_foto character(50),
    usu_fnacimiento timestamp without time zone,
    usu_fingreso timestamp without time zone,
    usu_numingreso integer,
    usu_cargo character(40),
    usu_departamento character(30),
    usu_estado character(1),
    aud_ing_usu character varying(50),
    aud_ing_fec date,
    aud_ing_ip character varying(30),
    aud_mod_usu character varying(50),
    aud_mod_fec date,
    aud_mod_ip character varying(30),
    usu_password_hash character varying(100)
);


ALTER TABLE cat_seg_usuarios OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 126585)
-- Name: cat_seg_usuarios_usu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cat_seg_usuarios_usu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cat_seg_usuarios_usu_id_seq OWNER TO postgres;

--
-- TOC entry 2713 (class 0 OID 0)
-- Dependencies: 238
-- Name: cat_seg_usuarios_usu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cat_seg_usuarios_usu_id_seq OWNED BY cat_seg_usuarios.usu_id;


--
-- TOC entry 2347 (class 2604 OID 126368)
-- Name: cat_adm_parametros par_cod_secuencial; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_adm_parametros ALTER COLUMN par_cod_secuencial SET DEFAULT nextval('cat_adm_parametros_par_cod_secuencial_seq'::regclass);


--
-- TOC entry 2343 (class 2604 OID 126290)
-- Name: cat_adm_reportes rep_cod_secuencial; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_adm_reportes ALTER COLUMN rep_cod_secuencial SET DEFAULT nextval('cat_adm_reportes_rep_cod_secuencial_seq'::regclass);


--
-- TOC entry 2371 (class 2604 OID 126622)
-- Name: cat_cat_avaluo aval_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_avaluo ALTER COLUMN aval_id SET DEFAULT nextval('cat_cat_avaluo_aval_id_seq'::regclass);


--
-- TOC entry 2363 (class 2604 OID 126544)
-- Name: cat_cat_bloques cod_bloques; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_bloques ALTER COLUMN cod_bloques SET DEFAULT nextval('cat_cat_bloques_cod_bloques_seq'::regclass);


--
-- TOC entry 2361 (class 2604 OID 126510)
-- Name: cat_cat_descuentos cod_descuento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_descuentos ALTER COLUMN cod_descuento SET DEFAULT nextval('cat_cat_descuentos_cod_descuento_seq'::regclass);


--
-- TOC entry 2370 (class 2604 OID 126614)
-- Name: cat_cat_detalles_avaluo daval_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_detalles_avaluo ALTER COLUMN daval_id SET DEFAULT nextval('cat_cat_detalles_avaluo_daval_id_seq'::regclass);


--
-- TOC entry 2346 (class 2604 OID 126355)
-- Name: cat_cat_dominios domi_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_dominios ALTER COLUMN domi_id SET DEFAULT nextval('cat_cat_dominios_domi_id_seq'::regclass);


--
-- TOC entry 2369 (class 2604 OID 126606)
-- Name: cat_cat_fechaavaluo fecav_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_fechaavaluo ALTER COLUMN fecav_id SET DEFAULT nextval('cat_cat_fechaavaluo_fecav_id_seq'::regclass);


--
-- TOC entry 2353 (class 2604 OID 126431)
-- Name: cat_cat_fotos cod_fotos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_fotos ALTER COLUMN cod_fotos SET DEFAULT nextval('cat_cat_fotos_cod_fotos_seq'::regclass);


--
-- TOC entry 2351 (class 2604 OID 126412)
-- Name: cat_cat_log_predio cod_log_predio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_log_predio ALTER COLUMN cod_log_predio SET DEFAULT nextval('cat_cat_log_predio_cod_log_predio_seq'::regclass);


--
-- TOC entry 2341 (class 2604 OID 126268)
-- Name: cat_cat_obras cod_obras; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_obras ALTER COLUMN cod_obras SET DEFAULT nextval('cat_cat_obras_cod_obras_seq'::regclass);


--
-- TOC entry 2355 (class 2604 OID 126450)
-- Name: cat_cat_obrasdetalle cod_obrasdetalle; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_obrasdetalle ALTER COLUMN cod_obrasdetalle SET DEFAULT nextval('cat_cat_obrasdetalle_cod_obrasdetalle_seq'::regclass);


--
-- TOC entry 2349 (class 2604 OID 126387)
-- Name: cat_cat_parametros cod_parsecuencial; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_parametros ALTER COLUMN cod_parsecuencial SET DEFAULT nextval('cat_cat_parametros_cod_parsecuencial_seq'::regclass);


--
-- TOC entry 2364 (class 2604 OID 126552)
-- Name: cat_cat_pisos cod_pisos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_pisos ALTER COLUMN cod_pisos SET DEFAULT nextval('cat_cat_pisos_cod_pisos_seq'::regclass);


--
-- TOC entry 2365 (class 2604 OID 126560)
-- Name: cat_cat_pisosdetalle cod_pisodetalle; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_pisosdetalle ALTER COLUMN cod_pisodetalle SET DEFAULT nextval('cat_cat_pisosdetalle_cod_pisodetalle_seq'::regclass);


--
-- TOC entry 2350 (class 2604 OID 126400)
-- Name: cat_cat_predios cod_catastral; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_predios ALTER COLUMN cod_catastral SET DEFAULT nextval('cat_cat_predios_cod_catastral_seq'::regclass);


--
-- TOC entry 2354 (class 2604 OID 126439)
-- Name: cat_cat_servicios cod_servicios; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_servicios ALTER COLUMN cod_servicios SET DEFAULT nextval('cat_cat_servicios_cod_servicios_seq'::regclass);


--
-- TOC entry 2352 (class 2604 OID 126423)
-- Name: cat_cat_terreno cod_terrenodetalle; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_terreno ALTER COLUMN cod_terrenodetalle SET DEFAULT nextval('cat_cat_terreno_cod_terrenodetalle_seq'::regclass);


--
-- TOC entry 2360 (class 2604 OID 126499)
-- Name: cat_cat_titulo_movimientos cod_movimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_titulo_movimientos ALTER COLUMN cod_movimiento SET DEFAULT nextval('cat_cat_titulo_movimientos_cod_movimiento_seq'::regclass);


--
-- TOC entry 2359 (class 2604 OID 126488)
-- Name: cat_cat_titulos cod_titulos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_titulos ALTER COLUMN cod_titulos SET DEFAULT nextval('cat_cat_titulos_cod_titulos_seq'::regclass);


--
-- TOC entry 2362 (class 2604 OID 126523)
-- Name: cat_cat_usosuelo cod_usopredio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_usosuelo ALTER COLUMN cod_usopredio SET DEFAULT nextval('cat_cat_usosuelo_cod_usopredio_seq'::regclass);


--
-- TOC entry 2342 (class 2604 OID 126279)
-- Name: cat_ciu_personeria cod_personeria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_personeria ALTER COLUMN cod_personeria SET DEFAULT nextval('cat_ciu_personeria_cod_personeria_seq'::regclass);


--
-- TOC entry 2357 (class 2604 OID 126469)
-- Name: cat_ciu_propietario cod_propietarios; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_propietario ALTER COLUMN cod_propietarios SET DEFAULT nextval('cat_ciu_propietario_cod_propietarios_seq'::regclass);


--
-- TOC entry 2358 (class 2604 OID 126480)
-- Name: cat_ciu_tenencia cod_tenencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_tenencia ALTER COLUMN cod_tenencia SET DEFAULT nextval('cat_ciu_tenencia_cod_tenencia_seq'::regclass);


--
-- TOC entry 2348 (class 2604 OID 126379)
-- Name: cat_con_constantesdescuentos cod_constantesdescuentos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_con_constantesdescuentos ALTER COLUMN cod_constantesdescuentos SET DEFAULT nextval('cat_con_constantesdescuentos_cod_constantesdescuentos_seq'::regclass);


--
-- TOC entry 2345 (class 2604 OID 126312)
-- Name: cat_con_constantesimpuestos cod_constantesimpuestos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_con_constantesimpuestos ALTER COLUMN cod_constantesimpuestos SET DEFAULT nextval('cat_con_constantesimpuestos_cod_constantesimpuestos_seq'::regclass);


--
-- TOC entry 2344 (class 2604 OID 126299)
-- Name: cat_con_constantesinteresmora cod_interesmora; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_con_constantesinteresmora ALTER COLUMN cod_interesmora SET DEFAULT nextval('cat_con_constantesinteresmora_cod_interesmora_seq'::regclass);


--
-- TOC entry 2367 (class 2604 OID 126582)
-- Name: cat_seg_fun_permisos pef_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_fun_permisos ALTER COLUMN pef_id SET DEFAULT nextval('cat_seg_fun_permisos_pef_id_seq'::regclass);


--
-- TOC entry 2356 (class 2604 OID 126458)
-- Name: cat_seg_funcionalidades fun_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_funcionalidades ALTER COLUMN fun_id SET DEFAULT nextval('cat_seg_funcionalidades_fun_id_seq'::regclass);


--
-- TOC entry 2366 (class 2604 OID 126571)
-- Name: cat_seg_permisos per_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_permisos ALTER COLUMN per_id SET DEFAULT nextval('cat_seg_permisos_per_id_seq'::regclass);


--
-- TOC entry 2368 (class 2604 OID 126590)
-- Name: cat_seg_usuarios usu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_usuarios ALTER COLUMN usu_id SET DEFAULT nextval('cat_seg_usuarios_usu_id_seq'::regclass);


--
-- TOC entry 2621 (class 0 OID 126365)
-- Dependencies: 193
-- Data for Name: cat_adm_parametros; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cat_adm_parametros VALUES (1, 'NOMBRE_COMPLETO_MUNICIPIO', 'Nombre completo del municipio', '[Actualizar en Parametros]', 'DATOS_MUNICIPIO', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (3, 'RUTA_UBICACION_REPORTES', 'Ruta de ubicación de los reportes del sistema', '/opt/sigc/reportes/', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (4, 'RUTA_UBICACION_IMAGENES_REPORTE', 'Ruta de ubicación de imágenes de los reportes del sistema', '/opt/sigc/imagenes/', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (5, 'NOMBRE_CORTO_MUNICIPIO', 'Nombre corto del municipio', '[Actualizar en Parametros]', 'DATOS_MUNICIPIO', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (7, 'DIRECCION_VISOR_PREDIOS', 'Dirección del visor de predios', 'http://localhost:8090/catvisor/?predio=', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (8, 'DIRECCION_SERVICIO_IMAGEN_PREDIO', 'Dirección del servicio para las imágenes del predio', 'http://localhost:8080/geoserver/wms?LAYERS=pvm_catastro%3Ahe002_lote&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&STYLES=&FORMAT=image%2Fjpeg&SRS=EPSG%3A900913&WIDTH=533&HEIGHT=195&BBOX=', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (9, 'DIRECCION_IMAGENES_PREDIO', 'Dirección de imágenes de los predios dentro del sistema operativo', '/opt/sigc/imagenes/', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (10, 'DIRECCION_IMAGENES_SISTEMA', 'Dirección de imágenes del sistema', '/opt/sigc/config/imagenes/', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (11, 'IMAGEN_LOGO_MUNICIPIO_1', 'Ubicación de la imagen del primer logo del municipio', '/opt/sigc/config/imagenes/logo_1.png', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (12, 'IMAGEN_LOGO_MUNICIPIO_2', 'Ubicación de la imagen del segundo logo del municipio', '/opt/sigc/config/imagenes/logo_2.png', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (13, 'FORMATO_MONEDAS', 'Formato para la representación de las monedas en el sistema.', '$ ###,###.00', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (14, 'TASA_RBU', 'Valor de veinticinco Remuneraciones Básicas Unificadas (RBU).', '234375.00', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_adm_parametros VALUES (2, 'TITULO_SISTEMA', 'Título del sistema', '[Actualizar en Parametros]', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, 'admin', '2018-03-22', '192.168.100.28');
INSERT INTO cat_adm_parametros VALUES (6, 'NOMBRE_SISTEMA', 'Nombre del sistema', '[Actualizar en Parametros]', 'DATOS_SISTEMA', 'configuracion-inicial', NULL, NULL, 'admin', '2018-03-22', '192.168.100.28');


--
-- TOC entry 2606 (class 0 OID 126287)
-- Dependencies: 178
-- Data for Name: cat_adm_reportes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cat_adm_reportes VALUES (1, 'TABLA_CATASTRAL_URBANA_CONDENSADA', 'tablaCatastralUrbanaCondensada.jasper', 'Tabla Catastral Urbana Condensada', 'Tabla Catastral Urbana Condensada.');
INSERT INTO cat_adm_reportes VALUES (2, 'FICHA_RELEVAMIENTO_PREDIAL_URBANO', 'fichaRelevamientoPredialUrbano.jasper', 'Ficha Relevamiento Predial Urbano', 'Ficha Relevamiento Predial Urbano.');
INSERT INTO cat_adm_reportes VALUES (3, 'NOTIFICACION_AVALUO', 'notificacionAvaluo.jasper', 'Notificacion Avalúo', 'Notificacion Avalúo.');
INSERT INTO cat_adm_reportes VALUES (4, 'CERTIFICACION_AVALUO', 'certificacionAvaluo.jasper', 'Certificación Avalúo', 'Certificación Avalúo.');
INSERT INTO cat_adm_reportes VALUES (5, 'TITULO_CREDITO', 'tituloCredito.jasper', 'Titulo Crédito', 'Titulo Crédito.');
INSERT INTO cat_adm_reportes VALUES (6, 'TABLA_CATASTRAL_URBANA', 'tablaCatastralUrbana.jasper', 'Tabla Catastral Urbana', 'Tabla Catastral Urbana.');
INSERT INTO cat_adm_reportes VALUES (7, 'TITULO_GENERADO', 'GeneracionTitulos.jasper', 'Generación Títulos', 'Generacion Titulos.');
INSERT INTO cat_adm_reportes VALUES (8, 'LISTA_TITULOS_COMPLETA', 'ListaTitulosCompleta.jasper', 'Lista Títulos Completa', 'Lista Títulos Completa.');
INSERT INTO cat_adm_reportes VALUES (9, 'LISTA_LOG_PREDIOS', 'logPredios.jasper', 'Lista Log Predios', 'Lista Log Predios.');
INSERT INTO cat_adm_reportes VALUES (10, 'LISTA_TITULOS_DESMARCADOS', 'ListaTitulosDesmarcados.jasper', 'Lista Titulos Desmarcados', 'Lista Titulos Desmarcados.');
INSERT INTO cat_adm_reportes VALUES (11, 'DETALLE_COBROS_URBANO', 'detallesCobro.jasper', 'DETALLE DE COBROS URBANO', 'Detalle de Cobro Urbano.');
INSERT INTO cat_adm_reportes VALUES (12, 'LISTA_PREDIOS', 'ListaPredios.jasper', 'Lista Predios', 'Lista Predios.');
INSERT INTO cat_adm_reportes VALUES (13, 'CARTERA_VENCIDA', 'CarteraVencida.jasper', 'Cartera Vencida', 'Cartera Vencida.');
INSERT INTO cat_adm_reportes VALUES (14, 'LISTA_TITULOS_DE_BAJA', 'ListaTitulosDeBaja.jasper', 'Lista Títulos de Baja', 'Lista Títulos de Baja.');


--
-- TOC entry 2626 (class 0 OID 126390)
-- Dependencies: 198
-- Data for Name: cat_cat_arriendos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2674 (class 0 OID 126619)
-- Dependencies: 246
-- Data for Name: cat_cat_avaluo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2657 (class 0 OID 126541)
-- Dependencies: 229
-- Data for Name: cat_cat_bloques; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2650 (class 0 OID 126507)
-- Dependencies: 222
-- Data for Name: cat_cat_descuentos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2655 (class 0 OID 126534)
-- Dependencies: 227
-- Data for Name: cat_cat_destinopredio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2672 (class 0 OID 126611)
-- Dependencies: 244
-- Data for Name: cat_cat_detalles_avaluo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2619 (class 0 OID 126352)
-- Dependencies: 191
-- Data for Name: cat_cat_dominios; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2600 (class 0 OID 126258)
-- Dependencies: 172
-- Data for Name: cat_cat_dpa; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2670 (class 0 OID 126603)
-- Dependencies: 242
-- Data for Name: cat_cat_fechaavaluo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2612 (class 0 OID 126320)
-- Dependencies: 184
-- Data for Name: cat_cat_financiamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2634 (class 0 OID 126428)
-- Dependencies: 206
-- Data for Name: cat_cat_fotos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2630 (class 0 OID 126409)
-- Dependencies: 202
-- Data for Name: cat_cat_log_predio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2608 (class 0 OID 126302)
-- Dependencies: 180
-- Data for Name: cat_cat_maquinariaequipo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2602 (class 0 OID 126265)
-- Dependencies: 174
-- Data for Name: cat_cat_obras; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2638 (class 0 OID 126447)
-- Dependencies: 210
-- Data for Name: cat_cat_obrasdetalle; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2625 (class 0 OID 126384)
-- Dependencies: 197
-- Data for Name: cat_cat_parametros; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2659 (class 0 OID 126549)
-- Dependencies: 231
-- Data for Name: cat_cat_pisos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2661 (class 0 OID 126557)
-- Dependencies: 233
-- Data for Name: cat_cat_pisosdetalle; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2628 (class 0 OID 126397)
-- Dependencies: 200
-- Data for Name: cat_cat_predios; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2617 (class 0 OID 126345)
-- Dependencies: 189
-- Data for Name: cat_cat_recursoshidricos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2615 (class 0 OID 126335)
-- Dependencies: 187
-- Data for Name: cat_cat_semovientes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2636 (class 0 OID 126436)
-- Dependencies: 208
-- Data for Name: cat_cat_servicios; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2632 (class 0 OID 126420)
-- Dependencies: 204
-- Data for Name: cat_cat_terreno; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2648 (class 0 OID 126496)
-- Dependencies: 220
-- Data for Name: cat_cat_titulo_movimientos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2646 (class 0 OID 126485)
-- Dependencies: 218
-- Data for Name: cat_cat_titulos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2653 (class 0 OID 126520)
-- Dependencies: 225
-- Data for Name: cat_cat_usosuelo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2611 (class 0 OID 126315)
-- Dependencies: 183
-- Data for Name: cat_cat_usotierra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2668 (class 0 OID 126596)
-- Dependencies: 240
-- Data for Name: cat_cat_valoracionambiental; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2613 (class 0 OID 126325)
-- Dependencies: 185
-- Data for Name: cat_cat_valoracionforestal; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2616 (class 0 OID 126340)
-- Dependencies: 188
-- Data for Name: cat_cat_valoracionpastisal; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2651 (class 0 OID 126513)
-- Dependencies: 223
-- Data for Name: cat_cat_valortierra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2604 (class 0 OID 126276)
-- Dependencies: 176
-- Data for Name: cat_ciu_personeria; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2642 (class 0 OID 126466)
-- Dependencies: 214
-- Data for Name: cat_ciu_propietario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2614 (class 0 OID 126330)
-- Dependencies: 186
-- Data for Name: cat_ciu_relacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2644 (class 0 OID 126477)
-- Dependencies: 216
-- Data for Name: cat_ciu_tenencia; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2623 (class 0 OID 126376)
-- Dependencies: 195
-- Data for Name: cat_con_constantesdescuentos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2610 (class 0 OID 126309)
-- Dependencies: 182
-- Data for Name: cat_con_constantesimpuestos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2607 (class 0 OID 126296)
-- Dependencies: 179
-- Data for Name: cat_con_constantesinteresmora; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2654 (class 0 OID 126529)
-- Dependencies: 226
-- Data for Name: cat_con_preciostierras; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2665 (class 0 OID 126579)
-- Dependencies: 237
-- Data for Name: cat_seg_fun_permisos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cat_seg_fun_permisos VALUES (1, 1, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (2, 2, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (3, 3, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (4, 4, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (5, 5, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (6, 6, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (7, 7, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (8, 8, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (9, 9, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (10, 10, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (11, 11, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (12, 12, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (13, 13, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (14, 14, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (15, 15, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (16, 16, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (17, 17, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (18, 18, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (19, 19, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (20, 20, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (21, 21, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (22, 22, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (23, 23, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (24, 24, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (25, 25, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (26, 26, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (27, 27, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (28, 28, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);
INSERT INTO cat_seg_fun_permisos VALUES (29, 29, 1, 'A', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL);


--
-- TOC entry 2640 (class 0 OID 126455)
-- Dependencies: 212
-- Data for Name: cat_seg_funcionalidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cat_seg_funcionalidades VALUES (1, 'ADMINISTRACION', 'Administración del sistema', 'Administración del Sistema', '', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, NULL, 1, '');
INSERT INTO cat_seg_funcionalidades VALUES (2, 'ADMIN_FUNCIONALIDADES', 'Administración de Funcionalidades', 'Funcionalidades', '/privado/admin-funcionalidades.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 1, 1, 'fa fa-gears');
INSERT INTO cat_seg_funcionalidades VALUES (3, 'ADMIN_USUARIOS', 'Gestión Usuarios', 'Gestión Usuarios', '/privado/admin-usuarios.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 1, 2, 'fa fa-users');
INSERT INTO cat_seg_funcionalidades VALUES (4, 'ADMIN_ROLES_Y_PERMISOS', 'Administración de Roles y Permisos', 'Roles y Permisos', '/privado/admin-permisos.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 1, 3, 'fa fa-user-secret');
INSERT INTO cat_seg_funcionalidades VALUES (5, 'ADMIN_DOMINIOS', 'Administración de Dominios', 'Dominios Generales del Sistema', '/privado/admin-dominios.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 1, 4, 'fa fa-leanpub');
INSERT INTO cat_seg_funcionalidades VALUES (6, 'PARAMETROS_PARA_CALCULOS', 'Parámetros Constantes para Cálculos', 'Parámetros para Cálculos', '/privado/admin-parametros.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 1, 5, 'fa fa-calculator');
INSERT INTO cat_seg_funcionalidades VALUES (7, 'PARAMETROS_DEL_SISTEMA', 'Parámetros del sistema SIGC', 'Parámetros del Sistema', '/privado/admin/parametros-sistema.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 1, 6, 'fa fa-list-ul');
INSERT INTO cat_seg_funcionalidades VALUES (8, 'ADMIN_DESCUENTOS', 'Gestión de descuentos y recargos', 'Descuentos y Recargos', '/privado/admin-descuentos-recargos.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 1, 7, 'fa fa-minus');
INSERT INTO cat_seg_funcionalidades VALUES (9, 'ADMIN_MULTAS', 'Gestión de multas', 'Gestión de Multas', '/privado/admin-multas.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 1, 8, 'fa fa-plus');
INSERT INTO cat_seg_funcionalidades VALUES (10, 'CATASTRO_URBANO', 'Gestión Catastral Urbano', 'Gestión Catastral Urbano', '', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, NULL, 2, '');
INSERT INTO cat_seg_funcionalidades VALUES (11, 'GESTION_CONTRIBUYENTES', 'Gestión de Contribuyentes', 'Gestión de Contribuyentes', '/privado/admin-contribuyentes.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 10, 1, 'fa fa-users');
INSERT INTO cat_seg_funcionalidades VALUES (12, 'URBANO_FICHA_PREDIAL', 'Ficha Predial Urbana', 'Ficha Predial Urbana', '', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 10, 2, 'fa fa-list-alt');
INSERT INTO cat_seg_funcionalidades VALUES (13, 'NUEVOS_PREDIOS', 'Nuevos Predios', 'Nuevos Predios', '/privado/catastro/nuevo-predio.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 12, 1, 'fa fa-plus');
INSERT INTO cat_seg_funcionalidades VALUES (14, 'EDICION_PREDIOS', 'Edición Predios', 'Edición Predios', '/privado/admin-ficha-catastral.xhtml?accion=edicionFicha', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 12, 2, 'fa fa-edit');
INSERT INTO cat_seg_funcionalidades VALUES (15, 'ELIMINACION_PREDIOS', 'Eliminación Predios', 'Eliminación Predios', '/privado/admin-ficha-catastral.xhtml?accion=eliminacionFicha', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 12, 3, 'fa fa-trash-o');
INSERT INTO cat_seg_funcionalidades VALUES (16, 'TRASPASO_DOMINIO', 'Traspaso de Dominios', 'Traspaso de Dominios', '/privado/admin-propietario.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 12, 4, 'fa fa-building');
INSERT INTO cat_seg_funcionalidades VALUES (17, 'URBANO_AVALUO_FICHA_CATASTRAL', 'Avalúo de fichas catastrales', 'Cálculo de Avalúos', '/privado/avaluos/calcular-avaluo.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 10, 3, 'fa fa-laptop');
INSERT INTO cat_seg_funcionalidades VALUES (18, 'URBANO_CONTRIBUCION_MEJORAS', 'Contribución Especial de Mejoras', 'Contribución Especial de Mejoras', '/privado/admin-contribucion-mejoras.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 10, 4, 'fa fa-thumbs-o-up');
INSERT INTO cat_seg_funcionalidades VALUES (19, 'GESTION_TITULOS', 'Gestión de Títulos', 'Gestión de Títulos', '', 'A', 'configuracion-inicial', NULL, NULL, 'configuracion-inicial', NULL, NULL, 10, 5, '');
INSERT INTO cat_seg_funcionalidades VALUES (20, 'GENERACION_TITULOS', 'Generación de Títulos', 'Generación de Títulos', '/privado/rentas/generar-titulos.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 19, 1, 'fa fa-bolt');
INSERT INTO cat_seg_funcionalidades VALUES (21, 'COBRO_TITULOS', 'Cobro de Títulos', 'Cobro de Títulos', '/privado/tesoreria/cobro-titulos.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 19, 2, 'fa fa-money');
INSERT INTO cat_seg_funcionalidades VALUES (22, 'CONSULTA_TITULOS', 'Consulta de Títulos', 'Consulta de Títulos', '', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 19, 3, '');
INSERT INTO cat_seg_funcionalidades VALUES (23, 'CONSULTA_TITULOS_PREDIO', 'Consulta de Títulos por Predios', 'Títulos por Predios', '/privado/rentas/gestion-titulos.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 22, 1, 'fa fa-list-alt');
INSERT INTO cat_seg_funcionalidades VALUES (24, 'CONSULTA_TITULOS_CARACTERISTICAS', 'Consulta de Títulos por Características', 'Títulos por Características', '/privado/rentas/consulta-titulos.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 22, 2, 'fa fa-cubes');
INSERT INTO cat_seg_funcionalidades VALUES (25, 'REPORTE_TITULOS', 'Reporte de Títulos', 'Reporte de Títulos', '', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 22, 4, '');
INSERT INTO cat_seg_funcionalidades VALUES (26, 'TITULOS_DESMARCADOS', 'Reporte de Títulos Desmarcados', 'Títulos Desmarcados', '/privado/tesoreria/titulos-desmarcados.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 25, 1, 'fa fa-list');
INSERT INTO cat_seg_funcionalidades VALUES (27, 'TITULOS_DE_BAJA', 'Reporte de Títulos Dados de Baja', 'Títulos Dados de Baja', '/privado/tesoreria/titulos-de-baja.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 25, 2, 'fa fa-long-arrow-down');
INSERT INTO cat_seg_funcionalidades VALUES (28, 'TITULOS_COBRADOS', 'Reporte de Títulos Cobrados', 'Títulos Cobrados', '/privado/tesoreria/titulos-cobrados.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 25, 3, 'fa fa-money');
INSERT INTO cat_seg_funcionalidades VALUES (29, 'TITULOS_VENCIDOS', 'Reporte de Títulos Vencidos', 'Títulos Vencidos', '/privado/tesoreria/cartera-vencida.xhtml', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 25, 4, 'fa fa-clock-o');


--
-- TOC entry 2663 (class 0 OID 126568)
-- Dependencies: 235
-- Data for Name: cat_seg_permisos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cat_seg_permisos VALUES (1, 'Administrador                 ', 'Administrador del sistema, gestión de usuarios, opciones del sistema, parametrización', '0S1S2S3S4SS                                                                                         ', 'A', NULL, NULL, NULL, 'configuracion-inicial', NULL, NULL);


--
-- TOC entry 2667 (class 0 OID 126587)
-- Dependencies: 239
-- Data for Name: cat_seg_usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cat_seg_usuarios VALUES (1, 1, NULL, 'SIGC                               ', 'Administrador                      ', '                                                                                                    ', '                              ', '                              ', '                              ', '                                                                                                                                                                                                                                                              ', 'admin               ', 'admin               ', 'admin               ', '                                                  ', NULL, NULL, NULL, 'Administrador                           ', 'TICs                          ', 'A', 'configuracion-inicial', NULL, NULL, NULL, NULL, NULL, 'd033e22ae348aeb5660fc2140aec35850c4da997');


--
-- TOC entry 2714 (class 0 OID 0)
-- Dependencies: 192
-- Name: cat_adm_parametros_par_cod_secuencial_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_adm_parametros_par_cod_secuencial_seq', 1, false);


--
-- TOC entry 2715 (class 0 OID 0)
-- Dependencies: 177
-- Name: cat_adm_reportes_rep_cod_secuencial_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_adm_reportes_rep_cod_secuencial_seq', 1, false);


--
-- TOC entry 2716 (class 0 OID 0)
-- Dependencies: 245
-- Name: cat_cat_avaluo_aval_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_avaluo_aval_id_seq', 1, false);


--
-- TOC entry 2717 (class 0 OID 0)
-- Dependencies: 228
-- Name: cat_cat_bloques_cod_bloques_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_bloques_cod_bloques_seq', 1, false);


--
-- TOC entry 2718 (class 0 OID 0)
-- Dependencies: 221
-- Name: cat_cat_descuentos_cod_descuento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_descuentos_cod_descuento_seq', 1, false);


--
-- TOC entry 2719 (class 0 OID 0)
-- Dependencies: 243
-- Name: cat_cat_detalles_avaluo_daval_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_detalles_avaluo_daval_id_seq', 1, false);


--
-- TOC entry 2720 (class 0 OID 0)
-- Dependencies: 190
-- Name: cat_cat_dominios_domi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_dominios_domi_id_seq', 1, false);


--
-- TOC entry 2721 (class 0 OID 0)
-- Dependencies: 241
-- Name: cat_cat_fechaavaluo_fecav_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_fechaavaluo_fecav_id_seq', 1, false);


--
-- TOC entry 2722 (class 0 OID 0)
-- Dependencies: 205
-- Name: cat_cat_fotos_cod_fotos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_fotos_cod_fotos_seq', 1, false);


--
-- TOC entry 2723 (class 0 OID 0)
-- Dependencies: 201
-- Name: cat_cat_log_predio_cod_log_predio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_log_predio_cod_log_predio_seq', 1, false);


--
-- TOC entry 2724 (class 0 OID 0)
-- Dependencies: 173
-- Name: cat_cat_obras_cod_obras_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_obras_cod_obras_seq', 1, false);


--
-- TOC entry 2725 (class 0 OID 0)
-- Dependencies: 209
-- Name: cat_cat_obrasdetalle_cod_obrasdetalle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_obrasdetalle_cod_obrasdetalle_seq', 1, false);


--
-- TOC entry 2726 (class 0 OID 0)
-- Dependencies: 196
-- Name: cat_cat_parametros_cod_parsecuencial_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_parametros_cod_parsecuencial_seq', 1, false);


--
-- TOC entry 2727 (class 0 OID 0)
-- Dependencies: 230
-- Name: cat_cat_pisos_cod_pisos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_pisos_cod_pisos_seq', 1, false);


--
-- TOC entry 2728 (class 0 OID 0)
-- Dependencies: 232
-- Name: cat_cat_pisosdetalle_cod_pisodetalle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_pisosdetalle_cod_pisodetalle_seq', 1, false);


--
-- TOC entry 2729 (class 0 OID 0)
-- Dependencies: 199
-- Name: cat_cat_predios_cod_catastral_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_predios_cod_catastral_seq', 1, false);


--
-- TOC entry 2730 (class 0 OID 0)
-- Dependencies: 207
-- Name: cat_cat_servicios_cod_servicios_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_servicios_cod_servicios_seq', 1, false);


--
-- TOC entry 2731 (class 0 OID 0)
-- Dependencies: 203
-- Name: cat_cat_terreno_cod_terrenodetalle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_terreno_cod_terrenodetalle_seq', 1, false);


--
-- TOC entry 2732 (class 0 OID 0)
-- Dependencies: 219
-- Name: cat_cat_titulo_movimientos_cod_movimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_titulo_movimientos_cod_movimiento_seq', 1, false);


--
-- TOC entry 2733 (class 0 OID 0)
-- Dependencies: 217
-- Name: cat_cat_titulos_cod_titulos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_titulos_cod_titulos_seq', 1, false);


--
-- TOC entry 2734 (class 0 OID 0)
-- Dependencies: 224
-- Name: cat_cat_usosuelo_cod_usopredio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_cat_usosuelo_cod_usopredio_seq', 1, false);


--
-- TOC entry 2735 (class 0 OID 0)
-- Dependencies: 175
-- Name: cat_ciu_personeria_cod_personeria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_ciu_personeria_cod_personeria_seq', 1, false);


--
-- TOC entry 2736 (class 0 OID 0)
-- Dependencies: 213
-- Name: cat_ciu_propietario_cod_propietarios_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_ciu_propietario_cod_propietarios_seq', 1, false);


--
-- TOC entry 2737 (class 0 OID 0)
-- Dependencies: 215
-- Name: cat_ciu_tenencia_cod_tenencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_ciu_tenencia_cod_tenencia_seq', 1, false);


--
-- TOC entry 2738 (class 0 OID 0)
-- Dependencies: 194
-- Name: cat_con_constantesdescuentos_cod_constantesdescuentos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_con_constantesdescuentos_cod_constantesdescuentos_seq', 1, false);


--
-- TOC entry 2739 (class 0 OID 0)
-- Dependencies: 181
-- Name: cat_con_constantesimpuestos_cod_constantesimpuestos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_con_constantesimpuestos_cod_constantesimpuestos_seq', 1, false);


--
-- TOC entry 2740 (class 0 OID 0)
-- Dependencies: 171
-- Name: cat_con_constantesinteresmora_cod_interesmora_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_con_constantesinteresmora_cod_interesmora_seq', 1, false);


--
-- TOC entry 2741 (class 0 OID 0)
-- Dependencies: 236
-- Name: cat_seg_fun_permisos_pef_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_seg_fun_permisos_pef_id_seq', 1, false);


--
-- TOC entry 2742 (class 0 OID 0)
-- Dependencies: 211
-- Name: cat_seg_funcionalidades_fun_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_seg_funcionalidades_fun_id_seq', 29, true);


--
-- TOC entry 2743 (class 0 OID 0)
-- Dependencies: 234
-- Name: cat_seg_permisos_per_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_seg_permisos_per_id_seq', 1, false);


--
-- TOC entry 2744 (class 0 OID 0)
-- Dependencies: 238
-- Name: cat_seg_usuarios_usu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cat_seg_usuarios_usu_id_seq', 1, false);


--
-- TOC entry 2408 (class 2606 OID 126373)
-- Name: cat_adm_parametros cat_adm_parametros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_adm_parametros
    ADD CONSTRAINT cat_adm_parametros_pkey PRIMARY KEY (par_cod_secuencial);


--
-- TOC entry 2379 (class 2606 OID 126295)
-- Name: cat_adm_reportes cat_adm_reportes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_adm_reportes
    ADD CONSTRAINT cat_adm_reportes_pkey PRIMARY KEY (rep_cod_secuencial);


--
-- TOC entry 2414 (class 2606 OID 126394)
-- Name: cat_cat_arriendos cat_cat_arriendos_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_arriendos
    ADD CONSTRAINT cat_cat_arriendos_pk PRIMARY KEY (cod_predioarriendos);


--
-- TOC entry 2467 (class 2606 OID 126627)
-- Name: cat_cat_avaluo cat_cat_avaluo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_avaluo
    ADD CONSTRAINT cat_cat_avaluo_pkey PRIMARY KEY (aval_id);


--
-- TOC entry 2449 (class 2606 OID 126546)
-- Name: cat_cat_bloques cat_cat_bloques_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_bloques
    ADD CONSTRAINT cat_cat_bloques_pkey PRIMARY KEY (cod_bloques);


--
-- TOC entry 2439 (class 2606 OID 126512)
-- Name: cat_cat_descuentos cat_cat_descuentos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_descuentos
    ADD CONSTRAINT cat_cat_descuentos_pkey PRIMARY KEY (cod_descuento);


--
-- TOC entry 2447 (class 2606 OID 126538)
-- Name: cat_cat_destinopredio cat_cat_destinopredio_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_destinopredio
    ADD CONSTRAINT cat_cat_destinopredio_pk PRIMARY KEY (det_secuencial);


--
-- TOC entry 2465 (class 2606 OID 126616)
-- Name: cat_cat_detalles_avaluo cat_cat_detalles_avaluo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_detalles_avaluo
    ADD CONSTRAINT cat_cat_detalles_avaluo_pkey PRIMARY KEY (daval_id);


--
-- TOC entry 2401 (class 2606 OID 126357)
-- Name: cat_cat_dominios cat_cat_dominios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_dominios
    ADD CONSTRAINT cat_cat_dominios_pkey PRIMARY KEY (domi_id);


--
-- TOC entry 2373 (class 2606 OID 126262)
-- Name: cat_cat_dpa cat_cat_dpa_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_dpa
    ADD CONSTRAINT cat_cat_dpa_pk PRIMARY KEY (cod_dpa);


--
-- TOC entry 2463 (class 2606 OID 126608)
-- Name: cat_cat_fechaavaluo cat_cat_fechaavaluo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_fechaavaluo
    ADD CONSTRAINT cat_cat_fechaavaluo_pkey PRIMARY KEY (fecav_id);


--
-- TOC entry 2389 (class 2606 OID 126324)
-- Name: cat_cat_financiamiento cat_cat_financiamiento_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_financiamiento
    ADD CONSTRAINT cat_cat_financiamiento_pk PRIMARY KEY (cod_financiamiento);


--
-- TOC entry 2423 (class 2606 OID 126433)
-- Name: cat_cat_fotos cat_cat_fotos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_fotos
    ADD CONSTRAINT cat_cat_fotos_pkey PRIMARY KEY (cod_fotos);


--
-- TOC entry 2419 (class 2606 OID 126417)
-- Name: cat_cat_log_predio cat_cat_log_predio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_log_predio
    ADD CONSTRAINT cat_cat_log_predio_pkey PRIMARY KEY (cod_log_predio);


--
-- TOC entry 2383 (class 2606 OID 126306)
-- Name: cat_cat_maquinariaequipo cat_cat_maquinariaequipo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_maquinariaequipo
    ADD CONSTRAINT cat_cat_maquinariaequipo_pk PRIMARY KEY (cod_maquinariaequipo);


--
-- TOC entry 2375 (class 2606 OID 126273)
-- Name: cat_cat_obras cat_cat_obras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_obras
    ADD CONSTRAINT cat_cat_obras_pkey PRIMARY KEY (cod_obras);


--
-- TOC entry 2427 (class 2606 OID 126452)
-- Name: cat_cat_obrasdetalle cat_cat_obrasdetalle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_obrasdetalle
    ADD CONSTRAINT cat_cat_obrasdetalle_pkey PRIMARY KEY (cod_obrasdetalle);


--
-- TOC entry 2412 (class 2606 OID 126389)
-- Name: cat_cat_parametros cat_cat_parametros_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_parametros
    ADD CONSTRAINT cat_cat_parametros_pk PRIMARY KEY (cod_parsecuencial);


--
-- TOC entry 2451 (class 2606 OID 126554)
-- Name: cat_cat_pisos cat_cat_pisos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_pisos
    ADD CONSTRAINT cat_cat_pisos_pkey PRIMARY KEY (cod_pisos);


--
-- TOC entry 2453 (class 2606 OID 126565)
-- Name: cat_cat_pisosdetalle cat_cat_pisosdetalle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_pisosdetalle
    ADD CONSTRAINT cat_cat_pisosdetalle_pkey PRIMARY KEY (cod_pisodetalle);


--
-- TOC entry 2416 (class 2606 OID 126405)
-- Name: cat_cat_predios cat_cat_predios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_predios
    ADD CONSTRAINT cat_cat_predios_pkey PRIMARY KEY (cod_catastral);


--
-- TOC entry 2399 (class 2606 OID 126349)
-- Name: cat_cat_recursoshidricos cat_cat_recursoshidricos_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_recursoshidricos
    ADD CONSTRAINT cat_cat_recursoshidricos_pk PRIMARY KEY (cod_recursoshidricos);


--
-- TOC entry 2395 (class 2606 OID 126339)
-- Name: cat_cat_semovientes cat_cat_semovientes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_semovientes
    ADD CONSTRAINT cat_cat_semovientes_pk PRIMARY KEY (cod_semovientes);


--
-- TOC entry 2425 (class 2606 OID 126444)
-- Name: cat_cat_servicios cat_cat_servicios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_servicios
    ADD CONSTRAINT cat_cat_servicios_pkey PRIMARY KEY (cod_servicios);


--
-- TOC entry 2421 (class 2606 OID 126425)
-- Name: cat_cat_terreno cat_cat_terreno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_terreno
    ADD CONSTRAINT cat_cat_terreno_pkey PRIMARY KEY (cod_terrenodetalle);


--
-- TOC entry 2437 (class 2606 OID 126504)
-- Name: cat_cat_titulo_movimientos cat_cat_titulo_movimientos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_titulo_movimientos
    ADD CONSTRAINT cat_cat_titulo_movimientos_pkey PRIMARY KEY (cod_movimiento);


--
-- TOC entry 2435 (class 2606 OID 126493)
-- Name: cat_cat_titulos cat_cat_titulos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_titulos
    ADD CONSTRAINT cat_cat_titulos_pkey PRIMARY KEY (cod_titulos);


--
-- TOC entry 2443 (class 2606 OID 126528)
-- Name: cat_cat_usosuelo cat_cat_usosuelo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_usosuelo
    ADD CONSTRAINT cat_cat_usosuelo_pkey PRIMARY KEY (cod_usopredio);


--
-- TOC entry 2387 (class 2606 OID 126319)
-- Name: cat_cat_usotierra cat_cat_usotierra_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_usotierra
    ADD CONSTRAINT cat_cat_usotierra_pk PRIMARY KEY (cod_usotierra);


--
-- TOC entry 2461 (class 2606 OID 126600)
-- Name: cat_cat_valoracionambiental cat_cat_valoracionambiental_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_valoracionambiental
    ADD CONSTRAINT cat_cat_valoracionambiental_pk PRIMARY KEY (cod_valoracionambiental);


--
-- TOC entry 2391 (class 2606 OID 126329)
-- Name: cat_cat_valoracionforestal cat_cat_valoracionforestal_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_valoracionforestal
    ADD CONSTRAINT cat_cat_valoracionforestal_pk PRIMARY KEY (cod_valoracionforestal);


--
-- TOC entry 2397 (class 2606 OID 126344)
-- Name: cat_cat_valoracionpastisal cat_cat_valoracionpastisal_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_valoracionpastisal
    ADD CONSTRAINT cat_cat_valoracionpastisal_pk PRIMARY KEY (cod_valoracionpastizal);


--
-- TOC entry 2441 (class 2606 OID 126517)
-- Name: cat_cat_valortierra cat_cat_valortierra_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_valortierra
    ADD CONSTRAINT cat_cat_valortierra_pk PRIMARY KEY (cod_valortierra);


--
-- TOC entry 2377 (class 2606 OID 126284)
-- Name: cat_ciu_personeria cat_ciu_personeria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_personeria
    ADD CONSTRAINT cat_ciu_personeria_pkey PRIMARY KEY (cod_personeria);


--
-- TOC entry 2431 (class 2606 OID 126474)
-- Name: cat_ciu_propietario cat_ciu_propietario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_propietario
    ADD CONSTRAINT cat_ciu_propietario_pkey PRIMARY KEY (cod_propietarios);


--
-- TOC entry 2393 (class 2606 OID 126334)
-- Name: cat_ciu_relacion cat_ciu_relacion_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_relacion
    ADD CONSTRAINT cat_ciu_relacion_pk PRIMARY KEY (cod_relacion);


--
-- TOC entry 2433 (class 2606 OID 126482)
-- Name: cat_ciu_tenencia cat_ciu_tenencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_tenencia
    ADD CONSTRAINT cat_ciu_tenencia_pkey PRIMARY KEY (cod_tenencia);


--
-- TOC entry 2410 (class 2606 OID 126381)
-- Name: cat_con_constantesdescuentos cat_con_constantesdescuentos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_con_constantesdescuentos
    ADD CONSTRAINT cat_con_constantesdescuentos_pkey PRIMARY KEY (cod_constantesdescuentos);


--
-- TOC entry 2385 (class 2606 OID 126314)
-- Name: cat_con_constantesimpuestos cat_con_constantesimpuestos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_con_constantesimpuestos
    ADD CONSTRAINT cat_con_constantesimpuestos_pkey PRIMARY KEY (cod_constantesimpuestos);


--
-- TOC entry 2381 (class 2606 OID 126301)
-- Name: cat_con_constantesinteresmora cat_con_constantesinteresmora_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_con_constantesinteresmora
    ADD CONSTRAINT cat_con_constantesinteresmora_pkey PRIMARY KEY (cod_interesmora);


--
-- TOC entry 2445 (class 2606 OID 126533)
-- Name: cat_con_preciostierras cat_con_preciostierras_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_con_preciostierras
    ADD CONSTRAINT cat_con_preciostierras_pk PRIMARY KEY (cod_preciostierras);


--
-- TOC entry 2457 (class 2606 OID 126584)
-- Name: cat_seg_fun_permisos cat_seg_fun_permisos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_fun_permisos
    ADD CONSTRAINT cat_seg_fun_permisos_pkey PRIMARY KEY (pef_id);


--
-- TOC entry 2429 (class 2606 OID 126463)
-- Name: cat_seg_funcionalidades cat_seg_funcionalidades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_funcionalidades
    ADD CONSTRAINT cat_seg_funcionalidades_pkey PRIMARY KEY (fun_id);


--
-- TOC entry 2455 (class 2606 OID 126576)
-- Name: cat_seg_permisos cat_seg_permisos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_permisos
    ADD CONSTRAINT cat_seg_permisos_pkey PRIMARY KEY (per_id);


--
-- TOC entry 2459 (class 2606 OID 126595)
-- Name: cat_seg_usuarios cat_seg_usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_usuarios
    ADD CONSTRAINT cat_seg_usuarios_pkey PRIMARY KEY (usu_id);


--
-- TOC entry 2402 (class 1259 OID 126358)
-- Name: ind_cat_cat_dominios_domi_codigo; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ind_cat_cat_dominios_domi_codigo ON cat_cat_dominios USING hash (domi_codigo);


--
-- TOC entry 2403 (class 1259 OID 126359)
-- Name: ind_cat_cat_dominios_domi_codigo_domi_calculo; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ind_cat_cat_dominios_domi_codigo_domi_calculo ON cat_cat_dominios USING btree (domi_codigo, domi_calculo);


--
-- TOC entry 2404 (class 1259 OID 126360)
-- Name: ind_cat_cat_dominios_domi_descripcion_domi_calculo; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ind_cat_cat_dominios_domi_descripcion_domi_calculo ON cat_cat_dominios USING btree (domi_descripcion, domi_calculo);


--
-- TOC entry 2405 (class 1259 OID 126361)
-- Name: ind_cat_cat_dominios_domi_grupos_domi_relacion; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ind_cat_cat_dominios_domi_grupos_domi_relacion ON cat_cat_dominios USING btree (domi_grupos, domi_relacion);


--
-- TOC entry 2406 (class 1259 OID 126362)
-- Name: ind_cat_cat_dominios_domi_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ind_cat_cat_dominios_domi_id ON cat_cat_dominios USING btree (domi_id);


--
-- TOC entry 2417 (class 1259 OID 126406)
-- Name: ind_cat_cat_predios_cod_catastral; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ind_cat_cat_predios_cod_catastral ON cat_cat_predios USING btree (cod_catastral);


--
-- TOC entry 2490 (class 2606 OID 126638)
-- Name: cat_cat_avaluo cat_cat_avaluo_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_avaluo
    ADD CONSTRAINT cat_cat_avaluo_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2491 (class 2606 OID 126738)
-- Name: cat_cat_avaluo cat_cat_avaluo_fecav_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_avaluo
    ADD CONSTRAINT cat_cat_avaluo_fecav_id_fkey FOREIGN KEY (fecav_id) REFERENCES cat_cat_fechaavaluo(fecav_id);


--
-- TOC entry 2482 (class 2606 OID 126643)
-- Name: cat_cat_bloques cat_cat_bloques_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_bloques
    ADD CONSTRAINT cat_cat_bloques_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2480 (class 2606 OID 126648)
-- Name: cat_cat_descuentos cat_cat_descuentos_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_descuentos
    ADD CONSTRAINT cat_cat_descuentos_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2488 (class 2606 OID 126693)
-- Name: cat_cat_detalles_avaluo cat_cat_detalles_avaluo_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_detalles_avaluo
    ADD CONSTRAINT cat_cat_detalles_avaluo_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2489 (class 2606 OID 126743)
-- Name: cat_cat_detalles_avaluo cat_cat_detalles_avaluo_fecav_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_detalles_avaluo
    ADD CONSTRAINT cat_cat_detalles_avaluo_fecav_id_fkey FOREIGN KEY (fecav_id) REFERENCES cat_cat_fechaavaluo(fecav_id);


--
-- TOC entry 2470 (class 2606 OID 126673)
-- Name: cat_cat_fotos cat_cat_fotos_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_fotos
    ADD CONSTRAINT cat_cat_fotos_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2468 (class 2606 OID 126688)
-- Name: cat_cat_log_predio cat_cat_log_predio_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_log_predio
    ADD CONSTRAINT cat_cat_log_predio_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2473 (class 2606 OID 126663)
-- Name: cat_cat_obrasdetalle cat_cat_obrasdetalle_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_obrasdetalle
    ADD CONSTRAINT cat_cat_obrasdetalle_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2472 (class 2606 OID 126628)
-- Name: cat_cat_obrasdetalle cat_cat_obrasdetalle_cod_obras_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_obrasdetalle
    ADD CONSTRAINT cat_cat_obrasdetalle_cod_obras_fkey FOREIGN KEY (cod_obras) REFERENCES cat_cat_obras(cod_obras);


--
-- TOC entry 2483 (class 2606 OID 126718)
-- Name: cat_cat_pisos cat_cat_pisos_cod_bloques_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_pisos
    ADD CONSTRAINT cat_cat_pisos_cod_bloques_fkey FOREIGN KEY (cod_bloques) REFERENCES cat_cat_bloques(cod_bloques);


--
-- TOC entry 2484 (class 2606 OID 126723)
-- Name: cat_cat_pisosdetalle cat_cat_pisosdetalle_cod_pisos_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_pisosdetalle
    ADD CONSTRAINT cat_cat_pisosdetalle_cod_pisos_fkey FOREIGN KEY (cod_pisos) REFERENCES cat_cat_pisos(cod_pisos);


--
-- TOC entry 2471 (class 2606 OID 126668)
-- Name: cat_cat_servicios cat_cat_servicios_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_servicios
    ADD CONSTRAINT cat_cat_servicios_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2469 (class 2606 OID 126683)
-- Name: cat_cat_terreno cat_cat_terreno_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_terreno
    ADD CONSTRAINT cat_cat_terreno_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2479 (class 2606 OID 126713)
-- Name: cat_cat_titulo_movimientos cat_cat_titulo_movimientos_cod_titulos_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_titulo_movimientos
    ADD CONSTRAINT cat_cat_titulo_movimientos_cod_titulos_fkey FOREIGN KEY (cod_titulos) REFERENCES cat_cat_titulos(cod_titulos);


--
-- TOC entry 2477 (class 2606 OID 126678)
-- Name: cat_cat_titulos cat_cat_titulos_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_titulos
    ADD CONSTRAINT cat_cat_titulos_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2478 (class 2606 OID 126703)
-- Name: cat_cat_titulos cat_cat_titulos_cod_propietarios_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_titulos
    ADD CONSTRAINT cat_cat_titulos_cod_propietarios_fkey FOREIGN KEY (cod_propietarios) REFERENCES cat_ciu_propietario(cod_propietarios);


--
-- TOC entry 2481 (class 2606 OID 126653)
-- Name: cat_cat_usosuelo cat_cat_usosuelo_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_cat_usosuelo
    ADD CONSTRAINT cat_cat_usosuelo_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2475 (class 2606 OID 126658)
-- Name: cat_ciu_propietario cat_ciu_propietario_cod_catastral_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_propietario
    ADD CONSTRAINT cat_ciu_propietario_cod_catastral_fkey FOREIGN KEY (cod_catastral) REFERENCES cat_cat_predios(cod_catastral);


--
-- TOC entry 2474 (class 2606 OID 126633)
-- Name: cat_ciu_propietario cat_ciu_propietario_cod_personeria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_propietario
    ADD CONSTRAINT cat_ciu_propietario_cod_personeria_fkey FOREIGN KEY (cod_personeria) REFERENCES cat_ciu_personeria(cod_personeria);


--
-- TOC entry 2476 (class 2606 OID 126708)
-- Name: cat_ciu_tenencia cat_ciu_tenencia_cod_propietarios_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_ciu_tenencia
    ADD CONSTRAINT cat_ciu_tenencia_cod_propietarios_fkey FOREIGN KEY (cod_propietarios) REFERENCES cat_ciu_propietario(cod_propietarios);


--
-- TOC entry 2486 (class 2606 OID 126733)
-- Name: cat_seg_fun_permisos cat_seg_fun_permisos_per_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_fun_permisos
    ADD CONSTRAINT cat_seg_fun_permisos_per_id_fkey FOREIGN KEY (per_id) REFERENCES cat_seg_permisos(per_id);


--
-- TOC entry 2485 (class 2606 OID 126698)
-- Name: cat_seg_fun_permisos cat_seg_fun_permisos_rol_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_fun_permisos
    ADD CONSTRAINT cat_seg_fun_permisos_rol_id_fkey FOREIGN KEY (rol_id) REFERENCES cat_seg_funcionalidades(fun_id);


--
-- TOC entry 2487 (class 2606 OID 126728)
-- Name: cat_seg_usuarios cat_seg_usuarios_per_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cat_seg_usuarios
    ADD CONSTRAINT cat_seg_usuarios_per_id_fkey FOREIGN KEY (per_id) REFERENCES cat_seg_permisos(per_id);


--
-- TOC entry 2681 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: israelavila
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM israelavila;
GRANT ALL ON SCHEMA public TO israelavila;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-03-22 20:46:59 -05

--
-- PostgreSQL database dump complete
--

