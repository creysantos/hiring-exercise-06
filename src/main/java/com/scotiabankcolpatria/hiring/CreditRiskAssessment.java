package com.scotiabankcolpatria.hiring;

/**
 * Clase para realizar la logica que evalua el riesgo de un credito
 * 
 * @author crey
 */
public class CreditRiskAssessment {

	/**
	 * Metodo para calcular la desviacion estandar de acuerdo a un grupo de pagos
	 * efectuados
	 * 
	 * @param paymentDelays: Array que contiene la cantidad de dias de retraso en el
	 *                       pago
	 * @return la desviacion estandar calculada a partir de los datos ingresados.
	 */
	public double standardDeviation(int[] paymentDelays) {
		double promedioMuestra = 0;
		double sumaDesviacion = 0;
		double promedioDesviacion = 0;
		double sumaPromedio = 0;
		double resta = 0;

		for (int i : paymentDelays) {
			sumaPromedio += i;
		}
		promedioMuestra = sumaPromedio / paymentDelays.length;
		for (int i : paymentDelays) {
			resta = i - promedioMuestra;
			sumaDesviacion = sumaDesviacion + Math.pow(resta, 2);
		}
		promedioDesviacion = sumaDesviacion / paymentDelays.length;
		return Math.sqrt(promedioDesviacion);
	}

	/**
	 * Metodo que trae el indice maximo de retraso en el pago segun los datos
	 * pasados como parametros
	 * 
	 * @param paymentDelays: Array con el historial de dias de retraso en cada
	 *                       periodo
	 * @return posicion: indice del dato que contiene el pico mas alto
	 */
	public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
		int sel = 0;
		int posicion = 0;
		int restaPosicionSiguiente = 0;
		int restaPosicionAnterior = 0;
		int restaPosicionSiguienteActual = 0;
		int restaPosicionAnteriorActual = 0;
		for (int j = 1; j < paymentDelays.length - 1; j++) {
			restaPosicionSiguienteActual = paymentDelays[j] - paymentDelays[j + 1];
			restaPosicionAnteriorActual = paymentDelays[j] - paymentDelays[j - 1];
			if ((restaPosicionAnteriorActual > restaPosicionAnterior)
					&& (restaPosicionSiguienteActual > restaPosicionSiguiente)) {
				sel = paymentDelays[j];
				posicion = j;
				restaPosicionSiguiente = paymentDelays[j] - paymentDelays[j + 1];
				restaPosicionAnterior = paymentDelays[j] - paymentDelays[j - 1];
			}
		}
		if (paymentDelays[0] > sel && paymentDelays[0] > paymentDelays[1]) {
			sel = paymentDelays[0];
			posicion = 0;
		}
		if (paymentDelays[paymentDelays.length - 1] > sel
				&& paymentDelays[paymentDelays.length - 1] > paymentDelays[paymentDelays.length - 2]) {
			sel = paymentDelays[paymentDelays.length - 1];
			posicion = paymentDelays.length - 1;
		}

		if (sel == 0) {
			posicion = -1;
		}
		return posicion;

	}

	/**
	 * Metodo para consultar la probabilidad de pago atrasado por periodo
	 * 
	 * @param paymentDelays: Matriz que contiene la cantidad de productos y el
	 *                       comportamiento de pago en un periodo determinado
	 * @return probabilidadPago: Array con los datos de la probabilidad de pago.
	 */
	public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {

		int cantidadProductos = paymentDelays.length;
		int cantidadPeriodos = paymentDelays[0].length;
		double[] probabilidadPago = new double[cantidadPeriodos];
		for (int i = 0; i <= cantidadPeriodos - 1; i++) {
			double contador = 0.0;
			double promedio = 0.0;
			for (int j = 0; j <= cantidadProductos - 1; j++) {
				if (paymentDelays[j][i] != 0) {
					contador += 1;
				}
			}
			promedio = contador / cantidadProductos;
			probabilidadPago[i] = promedio;
		}

		return probabilidadPago;
	}

}
