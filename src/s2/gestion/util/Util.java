package s2.gestion.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.openxava.jpa.XPersistence;
import org.openxava.util.Users;

import com.openxava.naviox.model.User;

import s2.gestion.model.ficheros.Contador;
import s2.gestion.model.ficheros.Ejercicio;

public class Util {
    public static RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    public static BigDecimal ZERO = new BigDecimal(0).setScale(getNumeroDecimales(), DEFAULT_ROUNDING_MODE);
    public static BigDecimal ONE = new BigDecimal(1).setScale(getNumeroDecimales(), DEFAULT_ROUNDING_MODE);
    public static BigDecimal TEN = new BigDecimal(10).setScale(getNumeroDecimales(), DEFAULT_ROUNDING_MODE);
    public static BigDecimal ONE_HUNDRED = new BigDecimal(100).setScale(getNumeroDecimales(), DEFAULT_ROUNDING_MODE);
    public static BigDecimal ONE_THOUSAND = new BigDecimal(1000).setScale(getNumeroDecimales(), DEFAULT_ROUNDING_MODE);

    public static BigDecimal getPercentage(BigDecimal importe, BigDecimal tipo) {
	importe = createBigDecimal(importe);
	tipo = createBigDecimal(tipo);
	return importe.multiply(tipo);
    }

    public static BigDecimal addPercentage(BigDecimal importe, BigDecimal tipo) {
	importe = createBigDecimal(importe);
	return importe.add(getPercentage(importe, tipo));
    }

    public static BigDecimal discountPercentage(BigDecimal importe, BigDecimal tipo) {
	importe = createBigDecimal(importe);
	return importe.subtract(getPercentage(importe, tipo));
    }

    public static BigDecimal originalAmount(BigDecimal importe, BigDecimal tipo) {
	importe = createBigDecimal(importe);
	tipo = createBigDecimal(tipo);
	return importe.divide(ONE.add(tipo), getNumeroDecimales(), DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal createBigDecimal(BigDecimal number) {
	number = number == null ? ZERO : number;
	return number.setScale(getNumeroDecimales(), DEFAULT_ROUNDING_MODE);
    }

    public static String getEjercicio() {
	return Ejercicio.getDefault(User.find(Users.getCurrent())).getNombre();
    }

    public static int getNumeroDecimales() {
	long idContador = 0;
	Contador contador = XPersistence.getManager().find(Contador.class, idContador);
	if (contador == null)
	    return 2;
	else
	    return contador.getNumeroDecimales() == null ? 2 : contador.getNumeroDecimales();
    }
}
