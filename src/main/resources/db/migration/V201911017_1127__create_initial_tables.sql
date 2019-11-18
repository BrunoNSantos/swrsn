-- Cria sequences
CREATE SEQUENCE public.rebelde_seq;
CREATE SEQUENCE public.item_inventario_seq;
CREATE SEQUENCE public.report_traidor_seq;

-- Cria tabelas
CREATE TABLE public.rebelde
(
  id bigint NOT NULL DEFAULT nextval('rebelde_seq'::regclass),
  nome character varying(50) NOT NULL,
  idade integer NOT NULL,
  genero character(1) NOT NULL,
  traidor boolean NOT NULL DEFAULT false,
  latitude numeric(10,4) NOT NULL,
  longitude numeric(10,4) NOT NULL,
  nome_localizacao character varying(50) NOT NULL,
  CONSTRAINT pk_rebelde PRIMARY KEY (id)
);

CREATE TABLE public.item_inventario
(
  id bigint NOT NULL DEFAULT nextval('item_inventario_seq'::regclass),
  tipo character varying(30) NOT NULL,
  id_rebelde bigint NOT NULL,
  CONSTRAINT pk_item_inventario PRIMARY KEY (id),
  CONSTRAINT fk_rebelde_inventario FOREIGN KEY (id_rebelde)
      REFERENCES public.rebelde (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.report_traidor
(
  id bigint NOT NULL DEFAULT nextval('report_traidor_seq'::regclass),
  id_rebelde bigint NOT NULL,
  id_traidor bigint NOT NULL,
  CONSTRAINT pk_report_traidor PRIMARY KEY (id),
  CONSTRAINT "fK_rebelde" FOREIGN KEY (id_rebelde)
      REFERENCES public.rebelde (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_traidor FOREIGN KEY (id_traidor)
      REFERENCES public.rebelde (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




