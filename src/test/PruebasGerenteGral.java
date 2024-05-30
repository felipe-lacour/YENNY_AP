package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controladores.UsuarioControlador;
import controladores.SucuControlador;
import controladores.PromocionControlador;
import modelos.Usuario;
import modelos.Sucursal;
import modelos.Promocion;

import java.util.List;

public class PruebasGerenteGral {
    
    @Test
    public void testAgregarUsuario() {
        UsuarioControlador controlador = new UsuarioControlador();
        Usuario nuevoUsuario = new Usuario(0, "Test User", 1, 1, "testpass", "testuser");
        controlador.addUser(nuevoUsuario);
        
        boolean found = false;
        for (Usuario usuario : controlador.getAllUsers()) {
            if (usuario.getUserName().equals("testuser")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testModificarUsuario() {
        UsuarioControlador controlador = new UsuarioControlador();
        Usuario usuario = new Usuario(0, "Test User", 1, 1, "testpass", "testuser");
        controlador.addUser(usuario);
        
        for (Usuario user : controlador.getAllUsers()) {
            if (user.getUserName().equals("testuser")) {
                user.setNombre("Updated User");
                controlador.updateUser(user);
                break;
            }
        }
        
        boolean updated = false;
        for (Usuario user : controlador.getAllUsers()) {
            if (user.getUserName().equals("testuser") && user.getNombre().equals("Updated User")) {
                updated = true;
                break;
            }
        }
        assertTrue(updated);
    }

    @Test
    public void testEliminarUsuario() {
        UsuarioControlador controlador = new UsuarioControlador();
        Usuario usuario = new Usuario(0, "Test User", 1, 1, "testpass", "testuser");
        controlador.addUser(usuario);
        
        int userId = -1;
        for (Usuario user : controlador.getAllUsers()) {
            if (user.getUserName().equals("testuser")) {
                userId = user.getUsuarioId();
                controlador.deleteUser(userId);
                break;
            }
        }
        
        boolean deleted = true;
        for (Usuario user : controlador.getAllUsers()) {
            if (user.getUserName().equals("testuser")) {
                deleted = false;
                break;
            }
        }
        assertTrue(deleted);
    }

    @Test
    public void testAgregarSucursal() {
        SucuControlador controlador = new SucuControlador();
        Sucursal nuevaSucursal = new Sucursal(0, "Test Location", "Test Branch");
        controlador.addBranch(nuevaSucursal);
        
        boolean found = false;
        for (Sucursal sucursal : controlador.getAllBranches()) {
            if (sucursal.getNombre().equals("Test Branch")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testEliminarSucursal() {
        SucuControlador controlador = new SucuControlador();
        Sucursal nuevaSucursal = new Sucursal(0, "Test Location", "Test Branch");
        controlador.addBranch(nuevaSucursal);
        
        int branchId = -1;
        for (Sucursal sucursal : controlador.getAllBranches()) {
            if (sucursal.getNombre().equals("Test Branch")) {
                branchId = sucursal.getSucursalId();
                controlador.deteleBranch(branchId);
                break;
            }
        }
        
        boolean deleted = true;
        for (Sucursal sucursal : controlador.getAllBranches()) {
            if (sucursal.getNombre().equals("Test Branch")) {
                deleted = false;
                break;
            }
        }
        assertTrue(deleted);
    }

    @Test
    public void testAgregarPromocion() {
        PromocionControlador controlador = new PromocionControlador();
        Promocion nuevaPromocion = new Promocion(0, "Test Promo", true, null, 20.0);
        controlador.addPromo(nuevaPromocion);
        
        boolean found = false;
        for (Promocion promo : controlador.getAllPromos()) {
            if (promo.getNombre().equals("Test Promo")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testEliminarPromocion() {
        PromocionControlador controlador = new PromocionControlador();
        Promocion nuevaPromocion = new Promocion(0, "Test Promo", true, null, 20.0);
        controlador.addPromo(nuevaPromocion);
        
        int promoId = -1;
        for (Promocion promo : controlador.getAllPromos()) {
            if (promo.getNombre().equals("Test Promo")) {
                promoId = promo.getPromocionId();
                controlador.deletePromo(promoId);
                break;
            }
        }
        
        boolean deleted = true;
        for (Promocion promo : controlador.getAllPromos()) {
            if (promo.getNombre().equals("Test Promo")) {
                deleted = false;
                break;
            }
        }
        assertTrue(deleted);
    }
}

