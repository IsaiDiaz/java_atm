/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.modelo;
import java.util.Scanner;

/**
 *
 * @author ecampohermoso
 */
public class Cajero {
    private Banco banco;
    public Cajero (Banco banco){
        this.banco=banco;
    }
    public Cliente ingreso() {
        Scanner tc = new Scanner(System.in);
        System.out.println("Bienvenido al " + banco.getNombre());
        Cliente temp;
        do {
            System.out.println(" Ingrese su credenciales de ingreso");
            System.out.println(" Ingrese su codigo de cliente: ");
            String codigo = tc.nextLine();
            System.out.println(" Ingrese su PIN: ");
            String PIN = tc.nextLine();
            temp = banco.buscarClientePorCodigo(codigo, PIN);
            if (temp == null) {
                System.out.println("codigo o PIN incorrectos");
            }
        } while (temp == null);
        return temp;
    }

    public int menu(){
        try{
            Scanner tc= new Scanner(System.in);
            System.out.println ("Menú de opciones:\n 1: Ver saldo\n 2: Retiro\n 3: Deposito\n 4: Salir\n Seleccione una opción");
            int op=tc.nextInt();
            return op;
        }catch(Exception e) {
            System.out.println("ERROR: " + e);
            return 0;
        }
    }

    public void mostrarCuentas(Cliente cliente){
        int i=1;
        for(Cuenta cuenta:cliente.getCuentas()){
            System.out.println("Cuenta: "+i);
            System.out.println(cuenta.getMoneda());
            System.out.println(cuenta.getTipo());
            i++;
        }
        System.out.println("Seleccione una cuenta: ");
    }
    public void verSaldo(Cliente cliente){
        try{
            Scanner tc= new Scanner(System.in);
            mostrarCuentas(cliente);
            int sel = tc.nextInt();
            System.out.println(cliente.getCuentas().get(sel - 1).getSaldo());
        }catch(Exception e){
            System.out.println("ERROR: "+e);
        }
    }
    public void retiro(Cliente cliente){
        try {
            Scanner tc= new Scanner(System.in);
            mostrarCuentas(cliente);
            int sel = tc.nextInt();
            System.out.println("Ingrese el monto que desea retirar");
            double retiro = tc.nextDouble();
            boolean exito=cliente.getCuentas().get(sel - 1).retirar(retiro);
            verificaTrasaccion(exito);
        }catch (Exception e){
            System.out.println("ERROR: " + e);
        }
    }
    public void deposito(Cliente cliente){
        try {
            Scanner tc= new Scanner(System.in);
            mostrarCuentas(cliente);
            int sel = tc.nextInt();
            System.out.println("Ingrese el monto que desea depositar");
            double deposito = tc.nextDouble();
            boolean exito =cliente.getCuentas().get(sel - 1).depositar(deposito);
            verificaTrasaccion(exito);
        }catch (Exception e){
            System.out.println("ERROR: " + e);
        }
    }
    public void verificaTrasaccion (boolean transaccion){
        if(transaccion)
            System.out.println("Transaccion Exitosa");
        else
            System.out.println("Ocurrió un error");
    }
}
