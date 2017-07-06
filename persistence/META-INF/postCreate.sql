-- Function: public.generate_num_factura()

-- DROP FUNCTION public.generate_num_factura();

	CREATE OR REPLACE FUNCTION public.generate_num_factura()
	  RETURNS trigger AS
	$BODY$
	declare
		num integer;
		cont integer;
	BEGIN
		if new.numero is null then
			LOCK table serie_documento IN ROW EXCLUSIVE MODE;
			
			select numero into num from serie_documento sd where sd.id=new.seriedocumento_id;
			num := num +1;
			loop
				select count(fv.id) into cont from factura_venta fv 
					where fv.seriedocumento_id = new.seriedocumento_id and fv.numero=num;
				if (cont = 0) then
					exit;
				else
					num:= num +1;
				end if;
			end loop;
			update serie_documento
				set numero = num
				where id=new.seriedocumento_id;		
			new.numero:=num;	  
		end if;
	   RETURN new;
	END;
	$BODY$
	  LANGUAGE plpgsql VOLATILE
	  COST 100;
	ALTER FUNCTION public.generate_num_factura()
	  OWNER TO postgres;
	  
	  
	CREATE TRIGGER generate_num_factura
	  BEFORE INSERT
	  ON public.factura_venta
	  FOR EACH ROW
	  EXECUTE PROCEDURE public.generate_num_factura();

--DATA
	INSERT INTO oxusers	(name, active, authenticatewithldap, birthdate, creationdate, email, failedloginattempts, familyname, forcechangepassword, givenname, jobtitle, lastlogindate, lastpasswordchangedate, middlename, nickname, password, recentpassword1, recentpassword2, recentpassword3, recentpassword4) 
		VALUES 	('admin', 'Y', 'N', NULL, '2017-06-14 17:55:25.662', NULL, 0, NULL, 'N', NULL, NULL, NULL, '2017-06-14 17:55:25.661', NULL, NULL, '-2fcc1dd51cb7514a99f03debf513ca7af3b25669', NULL, NULL, NULL, NULL);

	INSERT INTO public.ejercicio(nombre)
	    VALUES ('public');
	    
	INSERT INTO public.contador_global(ejercicio_nombre, oxusers_name)
	    VALUES ('public', 'admin');
--END DATA