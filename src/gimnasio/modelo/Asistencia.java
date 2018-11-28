/*
 * Copyright (C) 2018 wolix
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gimnasio.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author wolix
 */
@Entity
public class Asistencia implements Serializable {
    @Id
    private int idAsistencia;
    @Column
    private LocalDateTime horaIngreso;
    @Column
    private LocalDateTime horaSalida;
    @Column
    private String estadoAsistencia;
    
    
    public Asistencia(){
        
    }
    
    public Asistencia(LocalDateTime horaIngreso){
        this.horaIngreso = horaIngreso;
    }
    
    
}
