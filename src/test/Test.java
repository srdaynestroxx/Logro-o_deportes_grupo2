package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Reserva;

class ReservaTest {


    @Test
    void testToString() {
        
        String dniPersona = "12345678A";
        int sesionCodigo = 1;
        
        Reserva re = new Reserva(dniPersona, sesionCodigo);
        String datos = re.toString();
        assertEquals(datos, re.toString());
    }

    @Test
    void testGetDniPersonaYSesionCodigo() {
        
        String dniPersona = "34567890C";
        int sesionCodigo = 3;
        
        Reserva re = new Reserva(dniPersona, sesionCodigo);
        re.getDniPersona();
        re.getSesionCodigo();
        assertEquals(dniPersona, "34567890C");
        assertEquals(sesionCodigo, 3);
    }

    @Test
    void testSetDniPersonaYSesionCodigo() {
        
        String dniPersona = "34567890C";
        int sesionCodigo = 3;
        
        Reserva re = new Reserva(dniPersona, sesionCodigo);
        re.setDniPersona(dniPersona);
        re.setSesionCodigo(sesionCodigo);
        assertEquals(dniPersona, "34567890C");
        assertEquals(sesionCodigo, 3);
        
    }
    
    @Test
    void testSetDniPersonaYSesionCodigoNotEquals() {
        
        String dniPersona = "34567890C";
        int sesionCodigo = 3;
        
        Reserva re = new Reserva(dniPersona, sesionCodigo);
        re.setDniPersona(dniPersona);
        re.setSesionCodigo(sesionCodigo);
        assertNotEquals(dniPersona, "DniErroneo");
        assertNotEquals(sesionCodigo, 404);
        
    }
}