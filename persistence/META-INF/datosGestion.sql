--DATABASE
	-- Database: gestion
	
	-- DROP DATABASE gestion;
	
	CREATE DATABASE gestion
	  WITH OWNER = postgres
	       ENCODING = 'UTF8'
	       TABLESPACE = pg_default
	       LC_COLLATE = 'Spanish_Spain.1252'
	       LC_CTYPE = 'Spanish_Spain.1252'
	       CONNECTION LIMIT = -1;
--END DATABASE


--TABLES
	-- Table: public.oxusers
		
		-- DROP TABLE public.oxusers;
		
		CREATE TABLE public.oxusers
		(
		  name character varying(30) NOT NULL,
		  active character(1) NOT NULL,
		  authenticatewithldap character(1) NOT NULL,
		  birthdate timestamp without time zone,
		  creationdate timestamp without time zone,
		  email character varying(60),
		  failedloginattempts integer NOT NULL,
		  familyname character varying(30),
		  forcechangepassword character(1) NOT NULL,
		  givenname character varying(30),
		  jobtitle character varying(30),
		  lastlogindate timestamp without time zone,
		  lastpasswordchangedate timestamp without time zone,
		  middlename character varying(30),
		  nickname character varying(30),
		  password character varying(41),
		  recentpassword1 character varying(41),
		  recentpassword2 character varying(41),
		  recentpassword3 character varying(41),
		  recentpassword4 character varying(41),
		  CONSTRAINT oxusers_pkey PRIMARY KEY (name)
		)
		WITH (
		  OIDS=FALSE
		);
		ALTER TABLE public.oxusers
		  OWNER TO postgres;
	-- END Table: public.oxusers
	
		  
  	-- Table: public.ejercicio

		-- DROP TABLE public.ejercicio;
		
		CREATE TABLE public.ejercicio
		(
		  id bigserial,
		  versionoptblq integer,
		  documentos character varying(32),
		  nota text,
		  nombre character varying(255),
		  CONSTRAINT ejercicio_pkey PRIMARY KEY (id)
		)
		WITH (
		  OIDS=FALSE
		);
		ALTER TABLE public.ejercicio
		  OWNER TO postgres;
		
--		INSERT INTO public.ejercicio(nombre) VALUES ('public');		  
	-- End Table: public.ejercicio
	
	-- Table: public.contador_global
	
		-- DROP TABLE public.contador_global;
		
		CREATE TABLE public.contador_global
		(
		  id bigserial,
		  versionoptblq integer,		  
		  ejercicio_id bigint,
		  oxusers_name character varying(30),
		  CONSTRAINT contador_global_pkey PRIMARY KEY (id),
		  CONSTRAINT fk_ejercicio FOREIGN KEY (ejercicio_id)
		      REFERENCES public.ejercicio (id) MATCH SIMPLE
		      ON UPDATE NO ACTION ON DELETE NO ACTION,
		  CONSTRAINT fk_users FOREIGN KEY (oxusers_name)
		      REFERENCES public.oxusers (name) MATCH SIMPLE
		      ON UPDATE NO ACTION ON DELETE NO ACTION
		)
		WITH (
		  OIDS=FALSE
		);
		ALTER TABLE public.contador_global
		  OWNER TO postgres;

--		INSERT INTO public.contador_global(ejercicio_id, oxusers_name)
		    --select e.id,'admin' from ejercicio e where e.nombre='public';		  
	-- END Table: public.contador_global
	
--END TABLES	  
	  
-- VIEWS
	-- View: public.v_contador_global
	
		-- DROP VIEW public.v_contador_global;
		
		CREATE OR REPLACE VIEW public.v_contador_global AS 
		 SELECT *
		   FROM contador_global;
		
		ALTER TABLE public.v_contador_global
		  OWNER TO postgres;
	-- END View: public.v_contador_global
	-- View: public.v_ejercicio
	
		-- DROP VIEW public.v_ejercicio;
		
		CREATE OR REPLACE VIEW public.v_ejercicio AS 
		 SELECT *
		   FROM ejercicio;
		
		ALTER TABLE public.v_ejercicio
		  OWNER TO postgres;
	-- END View: public.v_ejercicio		  
-- END VIEWS

		  
-- FUNCTIONS

	-- Function: public.clone_schema(text, text, boolean)
	
		-- DROP FUNCTION public.clone_schema(text, text, boolean);
		
		CREATE OR REPLACE FUNCTION public.clone_schema(
		    source_schema text,
		    dest_schema text,
		    include_recs boolean)
		  RETURNS void AS
		$BODY$
		
		--  This function will clone all sequences, tables, data, views & functions from any existing schema to a new one
		-- SAMPLE CALL:
		-- SELECT clone_schema('public', 'new_schema', TRUE);
		
		DECLARE
		  src_oid          oid;
		  tbl_oid          oid;
		  func_oid         oid;
		  object           text;
		  buffer           text;
		  srctbl           text;
		  default_         text;
		  column_          text;
		  qry              text;
		  dest_qry         text;
		  v_def            text;
		  seqval           bigint;
		  sq_last_value    bigint;
		  sq_max_value     bigint;
		  sq_start_value   bigint;
		  sq_increment_by  bigint;
		  sq_min_value     bigint;
		  sq_cache_value   bigint;
		  sq_log_cnt       bigint;
		  sq_is_called     boolean;
		  sq_is_cycled     boolean;
		  sq_cycled        char(10);
		
		BEGIN
		
		-- Check that source_schema exists
		  SELECT oid INTO src_oid
		    FROM pg_namespace
		   WHERE nspname = quote_ident(source_schema);
		  IF NOT FOUND
		    THEN 
		    RAISE NOTICE 'source schema % does not exist!', source_schema;
		    RETURN ;
		  END IF;
		
		  -- Check that dest_schema does not yet exist
		  PERFORM nspname 
		    FROM pg_namespace
		   WHERE nspname = quote_ident(dest_schema);
		  IF FOUND
		    THEN 
		    RAISE NOTICE 'dest schema % already exists!', dest_schema;
		    RETURN ;
		  END IF;
		
		  EXECUTE 'CREATE SCHEMA ' || quote_ident(dest_schema) ;
		
		  -- Create sequences
		  -- TODO: Find a way to make this sequence's owner is the correct table.
		  FOR object IN
		    SELECT sequence_name::text 
		      FROM information_schema.sequences
		     WHERE sequence_schema = quote_ident(source_schema)
		  LOOP
		    EXECUTE 'CREATE SEQUENCE ' || quote_ident(dest_schema) || '.' || quote_ident(object);
		    srctbl := quote_ident(source_schema) || '.' || quote_ident(object);
		
		    EXECUTE 'SELECT last_value, max_value, start_value, increment_by, min_value, cache_value, log_cnt, is_cycled, is_called 
		              FROM ' || quote_ident(source_schema) || '.' || quote_ident(object) || ';' 
		              INTO sq_last_value, sq_max_value, sq_start_value, sq_increment_by, sq_min_value, sq_cache_value, sq_log_cnt, sq_is_cycled, sq_is_called ; 
		
		    IF sq_is_cycled 
		      THEN 
		        sq_cycled := 'CYCLE';
		    ELSE
		        sq_cycled := 'NO CYCLE';
		    END IF;
		
		    EXECUTE 'ALTER SEQUENCE '   || quote_ident(dest_schema) || '.' || quote_ident(object) 
		            || ' INCREMENT BY ' || sq_increment_by
		            || ' MINVALUE '     || sq_min_value 
		            || ' MAXVALUE '     || sq_max_value
		            || ' START WITH '   || sq_start_value
		            || ' RESTART '      || sq_min_value 
		            || ' CACHE '        || sq_cache_value 
		            || sq_cycled || ' ;' ;
		
		    buffer := quote_ident(dest_schema) || '.' || quote_ident(object);
		    IF include_recs 
		        THEN
		            EXECUTE 'SELECT setval( ''' || buffer || ''', ' || sq_last_value || ', ' || sq_is_called || ');' ; 
		    ELSE
		            EXECUTE 'SELECT setval( ''' || buffer || ''', ' || sq_start_value || ', ' || sq_is_called || ');' ;
		    END IF;
		
		  END LOOP;
		
		-- Create tables 
		  FOR object IN
		    SELECT TABLE_NAME::text 
		      FROM information_schema.tables 
		     WHERE table_schema = quote_ident(source_schema)
		       AND table_type = 'BASE TABLE'
		
		  LOOP
		    buffer := quote_ident(dest_schema) || '.' || quote_ident(object);
		    EXECUTE 'CREATE TABLE ' || buffer || ' (LIKE ' || quote_ident(source_schema) || '.' || quote_ident(object) 
		        || ' INCLUDING ALL)';
		
		    IF include_recs 
		      THEN 
		      -- Insert records from source table
		      EXECUTE 'INSERT INTO ' || buffer || ' SELECT * FROM ' || quote_ident(source_schema) || '.' || quote_ident(object) || ';';
		    END IF;
		 
		    FOR column_, default_ IN
		      SELECT column_name::text, 
		             REPLACE(column_default::text, source_schema, dest_schema) 
		        FROM information_schema.COLUMNS 
		       WHERE table_schema = dest_schema 
		         AND TABLE_NAME = object 
		         AND column_default LIKE 'nextval(%' || quote_ident(source_schema) || '%::regclass)'
		    LOOP
		      EXECUTE 'ALTER TABLE ' || buffer || ' ALTER COLUMN ' || column_ || ' SET DEFAULT ' || default_;
		    END LOOP;
		
		  END LOOP;
		
		--  add FK constraint
		  FOR qry IN
		    SELECT 'ALTER TABLE ' || quote_ident(dest_schema) || '.' || quote_ident(rn.relname) 
		                          || ' ADD CONSTRAINT ' || quote_ident(ct.conname) || ' ' || pg_get_constraintdef(ct.oid) || ';'
		      FROM pg_constraint ct
		      JOIN pg_class rn ON rn.oid = ct.conrelid
		     WHERE connamespace = src_oid
		       AND rn.relkind = 'r'
		       AND ct.contype = 'f'
		         
		    LOOP
		      EXECUTE qry;
		
		    END LOOP;
		
		
		-- Create views 
		  FOR object IN
		    SELECT table_name::text,
		           view_definition 
		      FROM information_schema.views
		     WHERE table_schema = quote_ident(source_schema)
		
		  LOOP
		    buffer := quote_ident(dest_schema) || '.' || quote_ident(object);
		    SELECT view_definition INTO v_def
		      FROM information_schema.views
		     WHERE table_schema = quote_ident(source_schema)
		       AND table_name = quote_ident(object);
		     
		    EXECUTE 'CREATE OR REPLACE VIEW ' || buffer || ' AS ' || v_def || ';' ;
		
		  END LOOP;
		
		-- Create functions 
		  FOR func_oid IN
		    SELECT oid
		      FROM pg_proc 
		     WHERE pronamespace = src_oid
		
		  LOOP      
		    SELECT pg_get_functiondef(func_oid) INTO qry;
		    SELECT replace(qry, source_schema, quote_ident(dest_schema)) INTO dest_qry;
		    EXECUTE dest_qry;
		
		  END LOOP;
		  
		  RETURN; 
		 
		END;
		 
		$BODY$
		  LANGUAGE plpgsql VOLATILE
		  COST 100;
		ALTER FUNCTION public.clone_schema(text, text, boolean)
		  OWNER TO postgres;
	-- END Function: public.clone_schema(text, text, boolean)
	
	-- Function: public.delete_schema(text)
	
		-- DROP FUNCTION public.delete_schema(text);
		
		CREATE OR REPLACE FUNCTION public.delete_schema(schema text)
		  RETURNS void AS
		$BODY$
		
		BEGIN
		  EXECUTE format('DROP SCHEMA %1$I CASCADE;', schema);
		  RETURN; 
		 
		END;
		 
		$BODY$
		  LANGUAGE plpgsql VOLATILE
		  COST 100;
		ALTER FUNCTION public.delete_schema(text)
		  OWNER TO postgres;
	-- END Function: public.delete_schema(text)
	  
	-- Function: public.newschema(character varying)
	
		-- DROP FUNCTION public.newschema(character varying);
		
		CREATE OR REPLACE FUNCTION public.newschema(nombre character varying)
		  RETURNS void AS
		$BODY$
		begin
		      EXECUTE format('CREATE SCHEMA %I AUTHORIZATION postgres;',nombre);
		end;
		$BODY$
		  LANGUAGE plpgsql VOLATILE
		  COST 100;
		ALTER FUNCTION public.newschema(character varying)
		  OWNER TO postgres;
	-- END Function: public.newschema(character varying) 
  
-- END FUNCTIONS
		  
		  
--DATA
	INSERT INTO oxusers	(name, active, authenticatewithldap, birthdate, creationdate, email, failedloginattempts, familyname, forcechangepassword, givenname, jobtitle, lastlogindate, lastpasswordchangedate, middlename, nickname, password, recentpassword1, recentpassword2, recentpassword3, recentpassword4) 
		VALUES 	('admin', 'Y', 'N', NULL, '2017-06-14 17:55:25.662', NULL, 0, NULL, 'N', NULL, NULL, NULL, '2017-06-14 17:55:25.661', NULL, NULL, '-2fcc1dd51cb7514a99f03debf513ca7af3b25669', NULL, NULL, NULL, NULL);

	INSERT INTO public.ejercicio(nombre)
	    VALUES ('public');
	    
	INSERT INTO public.contador_global(ejercicio_id, oxusers_name)
	    VALUES (1, 'admin');
--END DATA